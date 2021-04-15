package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Logistic.DemmandQuantity;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.oalonedeveloper.oliver.oliverappandroidapp.Companies.ProductsModel;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;
import es.dmoral.toasty.Toasty;

import static com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Logistic.DemmandQuantity.DemmandNextYearProjectionFragment.round;

public class DemmandCurrentYearProjectionFragment extends Fragment {

    TextView txtProjectionCurrentYear,txtCurrentPeriod;
    DatabaseReference companyRef;
    String post_key;
    RecyclerView recyclerView;
    int day,month,year,next_year;
    DecimalFormat decimalFormat;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_demmand_current_year_projection, container, false);

        post_key = getActivity().getIntent().getExtras().getString("post_key");
        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");

        decimalFormat = new DecimalFormat("0.00");

        Date date= new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        day = cal.get(Calendar.DAY_OF_MONTH);
        month = cal.get(Calendar.MONTH)+1;
        year = cal.get(Calendar.YEAR);

        next_year = year;

        recyclerView = view.findViewById(R.id.recyclerView);

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        SimpleDateFormat month_date = new SimpleDateFormat("MMMM");
        String month_name = month_date.format(cal.getTime());

        txtProjectionCurrentYear = view.findViewById(R.id.txtProjectionCurrentYear);
        txtCurrentPeriod = view.findViewById(R.id.txtCurrentPeriod);

        txtCurrentPeriod.setText("PROYECTADO "+month_name.toUpperCase()+" "+year+": ");
        txtProjectionCurrentYear.setText("PROYECTADO "+year+"-"+month);

        showProducts();

        return view;
    }

    private void showProducts() {
        Query query = companyRef.child(post_key).child("My Products").orderByChild(year+""+month+"quantity");
        FirebaseRecyclerAdapter<ProductsModel, CompanyProductsViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<ProductsModel, CompanyProductsViewHolder>
                (ProductsModel.class,R.layout.demmand_product_projection_item,CompanyProductsViewHolder.class,query) {
            @Override
            protected void populateViewHolder(final CompanyProductsViewHolder viewHolder, ProductsModel model, int position) {
                final String postKey = getRef(position).getKey();
                viewHolder.setProduct_image(model.getProduct_image());
                viewHolder.setProduct_currency(model.getProduct_currency());
                viewHolder.setProduct_description(model.getProduct_description());
                viewHolder.setProduct_measure(model.getProduct_measure());
                viewHolder.setUid(model.getUid());
                viewHolder.setCode(model.getCode());
                viewHolder.setProduct_name(model.getProduct_name());
                viewHolder.setProduct_price(model.getProduct_price());
                viewHolder.setProduct_stock(model.getProduct_stock());

                viewHolder.txtProductName.setText(viewHolder.my_product_name);
                Picasso.with(getActivity()).load(viewHolder.my_product_image).fit().into(viewHolder.imgProduct);

                companyRef.child(post_key).child("My Products").child(postKey).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChild(year + "" + month + "quantity_projection")) {
                            int last_year_month_quantity = dataSnapshot.child(year + "" + month + "quantity_projection").getValue(Integer.class);
                            viewHolder.txtQuantity.setText(last_year_month_quantity+"");


                        } else {
                            viewHolder.txtQuantity.setText("-");
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                viewHolder.btnActionButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showSetProjectionDialog();
                    }

                    private void showSetProjectionDialog() {

                        final AlertDialog dialog = new AlertDialog.Builder(getActivity()).create();

                        LayoutInflater inflater = LayoutInflater.from(getActivity());
                        View finance_method = inflater.inflate(R.layout.quantity_next_year_projections_dialog,null);

                        TextView txtProjectionPeriod,txtProductName;
                        ImageView imgProduct;

                        txtProjectionPeriod = finance_method.findViewById(R.id.txtProjectionPeriod);
                        txtProductName = finance_method.findViewById(R.id.txtProductName);
                        imgProduct = finance_method.findViewById(R.id.imgProduct);

                        txtProjectionPeriod.setText("PROYECCIÓN "+next_year);

                        Picasso.with(getActivity()).load(viewHolder.my_product_image).fit().into(imgProduct);
                        txtProductName.setText(viewHolder.my_product_name);

                        final EditText edtQuantity1,edtQuantity2,edtQuantity3,edtQuantity4,edtQuantity5,edtQuantity6,edtQuantity7,edtQuantity8,edtQuantity9,edtQuantity10,edtQuantity11,edtQuantity12;
                        final EditText edtPrice1,edtPrice2,edtPrice3,edtPrice4,edtPrice5,edtPrice6,edtPrice7,edtPrice8,edtPrice9,edtPrice10,edtPrice11,edtPrice12;
                        Button btnRegisterProjection;
                        final RelativeLayout rootLayout;

                        edtQuantity1 = finance_method.findViewById(R.id.edtQuantity1);
                        edtQuantity2 = finance_method.findViewById(R.id.edtQuantity2);
                        edtQuantity3 = finance_method.findViewById(R.id.edtQuantity3);
                        edtQuantity4 = finance_method.findViewById(R.id.edtQuantity4);
                        edtQuantity5 = finance_method.findViewById(R.id.edtQuantity5);
                        edtQuantity6 = finance_method.findViewById(R.id.edtQuantity6);
                        edtQuantity7 = finance_method.findViewById(R.id.edtQuantity7);
                        edtQuantity8 = finance_method.findViewById(R.id.edtQuantity8);
                        edtQuantity9 = finance_method.findViewById(R.id.edtQuantity9);
                        edtQuantity10 = finance_method.findViewById(R.id.edtQuantity10);
                        edtQuantity11 = finance_method.findViewById(R.id.edtQuantity11);
                        edtQuantity12 = finance_method.findViewById(R.id.edtQuantity12);
                        btnRegisterProjection = finance_method.findViewById(R.id.btnRegisterProjection);
                        rootLayout = finance_method.findViewById(R.id.rootLayout);

                        edtPrice1 = finance_method.findViewById(R.id.edtPrice1);
                        edtPrice2 = finance_method.findViewById(R.id.edtPrice2);
                        edtPrice3 = finance_method.findViewById(R.id.edtPrice3);
                        edtPrice4 = finance_method.findViewById(R.id.edtPrice4);
                        edtPrice5 = finance_method.findViewById(R.id.edtPrice5);
                        edtPrice6 = finance_method.findViewById(R.id.edtPrice6);
                        edtPrice7 = finance_method.findViewById(R.id.edtPrice7);
                        edtPrice8 = finance_method.findViewById(R.id.edtPrice8);
                        edtPrice9 = finance_method.findViewById(R.id.edtPrice9);
                        edtPrice10 = finance_method.findViewById(R.id.edtPrice10);
                        edtPrice11 = finance_method.findViewById(R.id.edtPrice11);
                        edtPrice12 = finance_method.findViewById(R.id.edtPrice12);

                        companyRef.child(post_key).child("My Products").child(postKey).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                String product_price = dataSnapshot.child("product_price").getValue().toString();
                                edtPrice1.setText(product_price);
                                edtPrice2.setText(product_price);
                                edtPrice3.setText(product_price);
                                edtPrice4.setText(product_price);
                                edtPrice5.setText(product_price);
                                edtPrice6.setText(product_price);
                                edtPrice7.setText(product_price);
                                edtPrice8.setText(product_price);
                                edtPrice9.setText(product_price);
                                edtPrice10.setText(product_price);
                                edtPrice11.setText(product_price);
                                edtPrice12.setText(product_price);


                                if (dataSnapshot.hasChild(next_year + "1" + "quantity_projection")) {
                                    int last_year_month_quantity = dataSnapshot.child(next_year + "1" + "quantity_projection").getValue(Integer.class);
                                    edtQuantity1.setText(last_year_month_quantity+"");

                                } else {
                                    edtQuantity1.setText("0");
                                }

                                if (dataSnapshot.hasChild(next_year + "2" + "quantity_projection")) {
                                    int last_year_month_quantity = dataSnapshot.child(next_year + "2" + "quantity_projection").getValue(Integer.class);
                                    edtQuantity2.setText(last_year_month_quantity+"");

                                } else {
                                    edtQuantity2.setText("0");
                                }

                                if (dataSnapshot.hasChild(next_year + "3" + "quantity_projection")) {
                                    int last_year_month_quantity = dataSnapshot.child(next_year + "3" + "quantity_projection").getValue(Integer.class);
                                    edtQuantity3.setText(last_year_month_quantity+"");

                                } else {
                                    edtQuantity3.setText("0");
                                }

                                if (dataSnapshot.hasChild(next_year + "4" + "quantity_projection")) {
                                    int last_year_month_quantity = dataSnapshot.child(next_year + "4" + "quantity_projection").getValue(Integer.class);
                                    edtQuantity4.setText(last_year_month_quantity+"");

                                } else {
                                    edtQuantity4.setText("0");
                                }

                                if (dataSnapshot.hasChild(next_year + "5" + "quantity_projection")) {
                                    int last_year_month_quantity = dataSnapshot.child(next_year + "5" + "quantity_projection").getValue(Integer.class);
                                    edtQuantity5.setText(last_year_month_quantity+"");

                                } else {
                                    edtQuantity5.setText("0");
                                }

                                if (dataSnapshot.hasChild(next_year + "6" + "quantity_projection")) {
                                    int last_year_month_quantity = dataSnapshot.child(next_year + "6" + "quantity_projection").getValue(Integer.class);
                                    edtQuantity6.setText(last_year_month_quantity+"");

                                } else {
                                    edtQuantity6.setText("0");
                                }

                                if (dataSnapshot.hasChild(next_year + "7" + "quantity_projection")) {
                                    int last_year_month_quantity = dataSnapshot.child(next_year + "7" + "quantity_projection").getValue(Integer.class);
                                    edtQuantity7.setText(last_year_month_quantity+"");

                                } else {
                                    edtQuantity7.setText("0");
                                }

                                if (dataSnapshot.hasChild(next_year + "8" + "quantity_projection")) {
                                    int last_year_month_quantity = dataSnapshot.child(next_year + "8" + "quantity_projection").getValue(Integer.class);
                                    edtQuantity8.setText(last_year_month_quantity+"");

                                } else {
                                    edtQuantity8.setText("0");
                                }

                                if (dataSnapshot.hasChild(next_year + "9" + "quantity_projection")) {
                                    int last_year_month_quantity = dataSnapshot.child(next_year + "9" + "quantity_projection").getValue(Integer.class);
                                    edtQuantity9.setText(last_year_month_quantity+"");

                                } else {
                                    edtQuantity9.setText("0");
                                }

                                if (dataSnapshot.hasChild(next_year + "10" + "quantity_projection")) {
                                    int last_year_month_quantity = dataSnapshot.child(next_year + "10" + "quantity_projection").getValue(Integer.class);
                                    edtQuantity10.setText(last_year_month_quantity+"");

                                } else {
                                    edtQuantity10.setText("0");
                                }

                                if (dataSnapshot.hasChild(next_year + "11" + "quantity_projection")) {
                                    int last_year_month_quantity = dataSnapshot.child(next_year + "11" + "quantity_projection").getValue(Integer.class);
                                    edtQuantity11.setText(last_year_month_quantity+"");

                                } else {
                                    edtQuantity11.setText("0");
                                }

                                if (dataSnapshot.hasChild(next_year + "12" + "quantity_projection")) {
                                    int last_year_month_quantity = dataSnapshot.child(next_year + "12" + "quantity_projection").getValue(Integer.class);
                                    edtQuantity12.setText(last_year_month_quantity+"");

                                } else {
                                    edtQuantity12.setText("0");
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });

                        btnRegisterProjection.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (TextUtils.isEmpty(edtQuantity1.getText().toString())) {
                                    Snackbar.make(rootLayout,"Todos los campos deben contener un valor",Snackbar.LENGTH_LONG);
                                }
                                else if (TextUtils.isEmpty(edtQuantity2.getText().toString())) {
                                    Snackbar.make(rootLayout,"Todos los campos deben contener un valor",Snackbar.LENGTH_LONG);
                                }
                                else if (TextUtils.isEmpty(edtQuantity3.getText().toString())) {
                                    Snackbar.make(rootLayout,"Todos los campos deben contener un valor",Snackbar.LENGTH_LONG);
                                }
                                else if (TextUtils.isEmpty(edtQuantity4.getText().toString())) {
                                    Snackbar.make(rootLayout,"Todos los campos deben contener un valor",Snackbar.LENGTH_LONG);
                                }
                                else if (TextUtils.isEmpty(edtQuantity5.getText().toString())) {
                                    Snackbar.make(rootLayout,"Todos los campos deben contener un valor",Snackbar.LENGTH_LONG);
                                }
                                else if (TextUtils.isEmpty(edtQuantity6.getText().toString())) {
                                    Snackbar.make(rootLayout,"Todos los campos deben contener un valor",Snackbar.LENGTH_LONG);
                                }
                                else if (TextUtils.isEmpty(edtQuantity7.getText().toString())) {
                                    Snackbar.make(rootLayout,"Todos los campos deben contener un valor",Snackbar.LENGTH_LONG);
                                }
                                else if (TextUtils.isEmpty(edtQuantity8.getText().toString())) {
                                    Snackbar.make(rootLayout,"Todos los campos deben contener un valor",Snackbar.LENGTH_LONG);
                                }
                                else if (TextUtils.isEmpty(edtQuantity9.getText().toString())) {
                                    Snackbar.make(rootLayout,"Todos los campos deben contener un valor",Snackbar.LENGTH_LONG);
                                }
                                else if (TextUtils.isEmpty(edtQuantity10.getText().toString())) {
                                    Snackbar.make(rootLayout,"Todos los campos deben contener un valor",Snackbar.LENGTH_LONG);
                                }
                                else if (TextUtils.isEmpty(edtQuantity11.getText().toString())) {
                                    Snackbar.make(rootLayout,"Todos los campos deben contener un valor",Snackbar.LENGTH_LONG);
                                } else if (TextUtils.isEmpty(edtQuantity12.getText().toString())) {
                                    Snackbar.make(rootLayout, "Todos los campos deben contener un valor", Snackbar.LENGTH_LONG);
                                } else {

                                    double period1 = Double.parseDouble(edtQuantity1.getText().toString());
                                    double period2 = Double.parseDouble(edtQuantity2.getText().toString());
                                    double period3 = Double.parseDouble(edtQuantity3.getText().toString());
                                    double period4 = Double.parseDouble(edtQuantity4.getText().toString());
                                    double period5 = Double.parseDouble(edtQuantity5.getText().toString());
                                    double period6 = Double.parseDouble(edtQuantity6.getText().toString());
                                    double period7 = Double.parseDouble(edtQuantity7.getText().toString());
                                    double period8 = Double.parseDouble(edtQuantity8.getText().toString());
                                    double period9 = Double.parseDouble(edtQuantity9.getText().toString());
                                    double period10 = Double.parseDouble(edtQuantity10.getText().toString());
                                    double period11 = Double.parseDouble(edtQuantity11.getText().toString());
                                    double period12 = Double.parseDouble(edtQuantity12.getText().toString());

                                    companyRef.child(post_key).child("My Products").child(postKey).child(next_year + "1" + "quantity_projection").setValue(period1);
                                    companyRef.child(post_key).child("My Products").child(postKey).child(next_year + "2" + "quantity_projection").setValue(period2);
                                    companyRef.child(post_key).child("My Products").child(postKey).child(next_year + "3" + "quantity_projection").setValue(period3);
                                    companyRef.child(post_key).child("My Products").child(postKey).child(next_year + "4" + "quantity_projection").setValue(period4);
                                    companyRef.child(post_key).child("My Products").child(postKey).child(next_year + "5" + "quantity_projection").setValue(period5);
                                    companyRef.child(post_key).child("My Products").child(postKey).child(next_year + "6" + "quantity_projection").setValue(period6);
                                    companyRef.child(post_key).child("My Products").child(postKey).child(next_year + "7" + "quantity_projection").setValue(period7);
                                    companyRef.child(post_key).child("My Products").child(postKey).child(next_year + "8" + "quantity_projection").setValue(period8);
                                    companyRef.child(post_key).child("My Products").child(postKey).child(next_year + "9" + "quantity_projection").setValue(period9);
                                    companyRef.child(post_key).child("My Products").child(postKey).child(next_year + "10" + "quantity_projection").setValue(period10);
                                    companyRef.child(post_key).child("My Products").child(postKey).child(next_year + "11" + "quantity_projection").setValue(period11);
                                    companyRef.child(post_key).child("My Products").child(postKey).child(next_year + "12" + "quantity_projection").setValue(period12);

                                    double price1 = Double.parseDouble(edtPrice1.getText().toString());
                                    double price2 = Double.parseDouble(edtPrice2.getText().toString());
                                    double price3 = Double.parseDouble(edtPrice3.getText().toString());
                                    double price4 = Double.parseDouble(edtPrice4.getText().toString());
                                    double price5 = Double.parseDouble(edtPrice5.getText().toString());
                                    double price6 = Double.parseDouble(edtPrice6.getText().toString());
                                    double price7 = Double.parseDouble(edtPrice7.getText().toString());
                                    double price8 = Double.parseDouble(edtPrice8.getText().toString());
                                    double price9 = Double.parseDouble(edtPrice9.getText().toString());
                                    double price10 = Double.parseDouble(edtPrice10.getText().toString());
                                    double price11 = Double.parseDouble(edtPrice11.getText().toString());
                                    double price12 = Double.parseDouble(edtPrice12.getText().toString());

                                    double total1 = round(period1*price1,2);
                                    double total2 = round(period2*price2,2);
                                    double total3 = round(period3*price3,2);
                                    double total4 = round(period4*price4,2);
                                    double total5 = round(period5*price5,2);
                                    double total6 = round(period6*price6,2);
                                    double total7 = round(period7*price7,2);
                                    double total8 = round(period8*price8,2);
                                    double total9 = round(period9*price9,2);
                                    double total10 = round(period10*price10,2);
                                    double total11 = round(period11*price11,2);
                                    double total12 = round(period12*price12,2);


                                    companyRef.child(post_key).child("My Products").child(postKey).child(next_year + "1" + "sales_projection").setValue(total1);
                                    companyRef.child(post_key).child("My Products").child(postKey).child(next_year + "2" + "sales_projection").setValue(total2);
                                    companyRef.child(post_key).child("My Products").child(postKey).child(next_year + "3" + "sales_projection").setValue(total3);
                                    companyRef.child(post_key).child("My Products").child(postKey).child(next_year + "4" + "sales_projection").setValue(total4);
                                    companyRef.child(post_key).child("My Products").child(postKey).child(next_year + "5" + "sales_projection").setValue(total5);
                                    companyRef.child(post_key).child("My Products").child(postKey).child(next_year + "6" + "sales_projection").setValue(total6);
                                    companyRef.child(post_key).child("My Products").child(postKey).child(next_year + "7" + "sales_projection").setValue(total7);
                                    companyRef.child(post_key).child("My Products").child(postKey).child(next_year + "8" + "sales_projection").setValue(total8);
                                    companyRef.child(post_key).child("My Products").child(postKey).child(next_year + "9" + "sales_projection").setValue(total9);
                                    companyRef.child(post_key).child("My Products").child(postKey).child(next_year + "10" + "sales_projection").setValue(total10);
                                    companyRef.child(post_key).child("My Products").child(postKey).child(next_year + "11" + "sales_projection").setValue(total11);
                                    companyRef.child(post_key).child("My Products").child(postKey).child(next_year + "12" + "sales_projection").setValue(total12);

                                    Toasty.success(getActivity(), "Proyección actualizada con éxito", Toast.LENGTH_LONG).show();
                                    dialog.dismiss();
                                }
                            }
                        });


                        dialog.setView(finance_method);
                        dialog.show();

                    }
                });

            }
        };
        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    public static class CompanyProductsViewHolder extends RecyclerView.ViewHolder {
        View mView;
        String my_product_image,my_product_name;
        CircleImageView imgProduct;
        TextView txtProductName,txtQuantity,txtPercent;
        ImageView btnActionButton;

        public CompanyProductsViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;

            imgProduct = mView.findViewById(R.id.imgProduct);
            txtProductName = mView.findViewById(R.id.txtProductName);
            txtQuantity = mView.findViewById(R.id.txtQuantity);
            txtPercent = mView.findViewById(R.id.txtPercent);
            btnActionButton = mView.findViewById(R.id.btnActionButton);

        }
        public void setProduct_image(String product_image) {
            my_product_image = product_image;
        }
        public void setProduct_currency(String product_currency) {

        }
        public void setProduct_description(String product_description) {

        }
        public void setProduct_measure(String product_measure) {

        }
        public void setUid(String uid) {

        }
        public void setCode(String code) {

        }
        public void setProduct_name(String product_name) {
            my_product_name = product_name;
        }
        public void setProduct_price(String product_price) {

        }
        public void setProduct_stock(String product_stock) {

        }
    }
}
