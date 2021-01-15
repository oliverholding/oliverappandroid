package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Logistic.DemmandPlanning;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
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
import java.util.Calendar;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

public class DemmandPlanningFragment extends Fragment {

    DatabaseReference companyRef;
    String post_key;
    RecyclerView recyclerView;
    int day,month,year,q1,q2,q3,q4,q5,q6,q7,q8,q9,q10,q11,q12,prom_sum_q,frequency_factor;
    DecimalFormat decimalFormat;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_demmand_planning, container, false);

        post_key = getActivity().getIntent().getExtras().getString("post_key");
        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");

        decimalFormat = new DecimalFormat("0.00");

        Date date= new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        day = cal.get(Calendar.DAY_OF_MONTH);
        month = cal.get(Calendar.MONTH)+1;
        year = cal.get(Calendar.YEAR);

        frequency_factor = 12;

        recyclerView = view.findViewById(R.id.recyclerView);

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        showCompanyProducts();

        return view;
    }

    private void showCompanyProducts() {
        Query query = companyRef.child(post_key).child("My Products").orderByChild(year+""+month+"quantity");
        FirebaseRecyclerAdapter<ProductsModel, CompanyProductsViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<ProductsModel, CompanyProductsViewHolder>
                (ProductsModel.class,R.layout.product_demand_planning_item,CompanyProductsViewHolder.class,query) {
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
                viewHolder.txtCurrentStock.setText(viewHolder.my_product_stock);



                companyRef.child(post_key).child("My Products").child(postKey).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChild("security_stock_frequency")) {
                            String security_stock_frequency = dataSnapshot.child("security_stock_frequency").getValue().toString();
                            if (security_stock_frequency.equals("Mensual")) {
                                frequency_factor = 12;
                            }
                            if (security_stock_frequency.equals("Quincenal")) {
                                frequency_factor = 24;
                            }
                            if (security_stock_frequency.equals("Semanal")) {
                                frequency_factor = 48;
                            }
                            if (security_stock_frequency.equals("Diario")) {
                                frequency_factor = 360;
                            }

                        } else {
                            companyRef.child(post_key).child("My Products").child(postKey).child("security_stock_frequency").setValue("Mensual");
                            frequency_factor = 12;
                        }


                        if (dataSnapshot.hasChild(year - 1 + "" + 1 + "quantity")) {
                            q1 = dataSnapshot.child(year - 1 + "" + 1 + "quantity").getValue(Integer.class);
                        } else {
                            q1 = 0;
                        }

                        if (dataSnapshot.hasChild(year - 1 + "" + 2 + "quantity")) {
                            q2 = dataSnapshot.child(year - 1 + "" + 2 + "quantity").getValue(Integer.class);
                        } else {
                            q2 = 0;
                        }

                        if (dataSnapshot.hasChild(year - 1 + "" + 3 + "quantity")) {
                            q3 = dataSnapshot.child(year - 1 + "" + 3 + "quantity").getValue(Integer.class);
                        } else {
                            q3 = 0;
                        }

                        if (dataSnapshot.hasChild(year - 1 + "" + 4 + "quantity")) {
                            q4 = dataSnapshot.child(year - 1 + "" + 4 + "quantity").getValue(Integer.class);
                        } else {
                            q4 = 0;
                        }

                        if (dataSnapshot.hasChild(year - 1 + "" + 5 + "quantity")) {
                            q5 = dataSnapshot.child(year - 1 + "" + 5 + "quantity").getValue(Integer.class);
                        } else {
                            q5 = 0;
                        }

                        if (dataSnapshot.hasChild(year - 1 + "" + 6 + "quantity")) {
                            q6 = dataSnapshot.child(year - 1 + "" + 6 + "quantity").getValue(Integer.class);
                        } else {
                            q6 = 0;
                        }

                        if (dataSnapshot.hasChild(year - 1 + "" + 7 + "quantity")) {
                            q7 = dataSnapshot.child(year - 1 + "" + 7 + "quantity").getValue(Integer.class);
                        } else {
                            q7 = 0;
                        }

                        if (dataSnapshot.hasChild(year - 1 + "" + 8 + "quantity")) {
                            q8 = dataSnapshot.child(year - 1 + "" + 8 + "quantity").getValue(Integer.class);
                        } else {
                            q8 = 0;
                        }
                        if (dataSnapshot.hasChild(year - 1 + "" + 9 + "quantity")) {
                            q9 = dataSnapshot.child(year - 1 + "" + 9 + "quantity").getValue(Integer.class);
                        } else {
                            q9 = 0;
                        }

                        if (dataSnapshot.hasChild(year - 1 + "" + 10 + "quantity")) {
                            q10 = dataSnapshot.child(year - 1 + "" + 10 + "quantity").getValue(Integer.class);
                        } else {
                            q10 = 0;
                        }

                        if (dataSnapshot.hasChild(year - 1 + "" + 11 + "quantity")) {
                            q11 = dataSnapshot.child(year - 1 + "" + 11 + "quantity").getValue(Integer.class);
                        } else {
                            q11 = 0;
                        }

                        if (dataSnapshot.hasChild(year - 1 + "" + 12 + "quantity")) {
                            q12 = dataSnapshot.child(year - 1 + "" + 12 + "quantity").getValue(Integer.class);
                        } else {
                            q12 = 0;
                        }

                        prom_sum_q = (q1+q2+q3+q4+q5+q6+q7+q8+q9+q10+q11+q12)/frequency_factor;
                        double current_stock = Double.parseDouble(viewHolder.my_product_stock);

                        if (month == 1) {
                            viewHolder.txDemand.setText(q1+"");
                            double purchase = (q1+prom_sum_q)-current_stock;
                            viewHolder.txtPurchase.setText(purchase+"");
                            double final_stock = (current_stock+purchase)-q1;
                            viewHolder.txtFinalStock.setText(final_stock+"");
                        }
                        if (month == 2) {
                            viewHolder.txDemand.setText(q2+"");
                        }
                        if (month == 3) {
                            viewHolder.txDemand.setText(q3+"");
                        }
                        if (month == 4) {
                            viewHolder.txDemand.setText(q4+"");
                        }
                        if (month == 5) {
                            viewHolder.txDemand.setText(q5+"");
                        }
                        if (month == 6) {
                            viewHolder.txDemand.setText(q6+"");
                        }
                        if (month == 7) {
                            viewHolder.txDemand.setText(q7+"");
                        }
                        if (month == 8) {
                            viewHolder.txDemand.setText(q8+"");
                        }
                        if (month == 9) {
                            viewHolder.txDemand.setText(q9+"");
                        }
                        if (month == 10) {
                            viewHolder.txDemand.setText(q10+"");
                        }
                        if (month == 11) {
                            viewHolder.txDemand.setText(q11+"");
                        }
                        if (month == 12) {
                            viewHolder.txDemand.setText(q12+"");
                            double purchase = (q12+prom_sum_q)-current_stock;
                            viewHolder.txtPurchase.setText(purchase+"");
                            double final_stock = (current_stock+purchase)-q12;
                            viewHolder.txtFinalStock.setText(final_stock+"");
                        }


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        };
        recyclerView.setAdapter(firebaseRecyclerAdapter);


    }

    public static class CompanyProductsViewHolder extends RecyclerView.ViewHolder {
        View mView;
        String my_product_image,my_product_name,my_product_stock;
        CircleImageView imgProduct;
        TextView txtProductName,txtCurrentStock,txtPurchase,txDemand,txtFinalStock;

        public CompanyProductsViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;

            imgProduct = mView.findViewById(R.id.imgProduct);
            txtProductName = mView.findViewById(R.id.txtProductName);
            txtCurrentStock = mView.findViewById(R.id.txtCurrentStock);
            txtPurchase = mView.findViewById(R.id.txtPurchase);
            txDemand = mView.findViewById(R.id.txDemand);
            txtFinalStock = mView.findViewById(R.id.txtFinalStock);

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
            my_product_stock = product_stock;
        }
    }
}
