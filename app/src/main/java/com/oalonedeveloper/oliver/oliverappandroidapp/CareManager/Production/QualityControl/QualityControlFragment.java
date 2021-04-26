package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Production.QualityControl;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Logistic.Storage.WarehousesModel;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Production.ProductionOrders.ChainModel;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;
import es.dmoral.toasty.Toasty;


public class QualityControlFragment extends Fragment {

    DatabaseReference companyRef;
    String post_key;
    RecyclerView recyclerView;
    DecimalFormat decimalFormat;
    int day,month,year;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_quality_control, container, false);

        post_key = getActivity().getIntent().getExtras().getString("post_key");
        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");
        decimalFormat = new DecimalFormat("0.00");

        recyclerView = view.findViewById(R.id.recyclerView);

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        showChainProducts();

        return view;
    }

    private void showChainProducts() {
        Query query = companyRef.child(post_key).child("Production Chain").orderByChild("state").equalTo("ready");
        FirebaseRecyclerAdapter<ChainModel, OrderChainViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<ChainModel, OrderChainViewHolder>
                (ChainModel.class,R.layout.quality_control_product_item, OrderChainViewHolder.class, query) {
            @Override
            protected void populateViewHolder(final OrderChainViewHolder viewHolder, ChainModel model, int position) {
                final String postKey = getRef(position).getKey();
                viewHolder.setDate(model.getDate());
                viewHolder.setProduct_id(model.getProduct_id());
                viewHolder.setProduct_image(model.getProduct_image());
                viewHolder.setProduct_name(model.getProduct_name());
                viewHolder.setProduct_quantity_production(model.getProduct_quantity_production());
                viewHolder.setState(model.getState());
                viewHolder.setTime(model.getTime());
                viewHolder.setCode(model.getCode());
                viewHolder.setProduct_currency(model.getProduct_currency());
                viewHolder.setProduct_description(model.getProduct_description());
                viewHolder.setProduct_measure(model.getProduct_measure());
                viewHolder.setProduct_price(model.getProduct_price());

                viewHolder.txtDate.setText(viewHolder.my_date);
                viewHolder.txtProductName.setText(viewHolder.my_product_name);
                viewHolder.txtProductQuantity.setText(viewHolder.my_product_quantity_production);

                companyRef.child(post_key).child("Production Chain").child(postKey).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChild("Quality Control")) {
                            viewHolder.txtState.setText("Listo");
                        } else {
                            viewHolder.txtState.setText("Sin Control");
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                viewHolder.btnActionButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final AlertDialog dialog = new AlertDialog.Builder(getActivity()).create();

                        LayoutInflater inflater = LayoutInflater.from(getActivity());
                        View finance_method = inflater.inflate(R.layout.set_quality_production_dialog,null);

                        CircleImageView imgProduct;
                        TextView txtProductName,txtQuantity;
                        final EditText edtQuantity1,edtQuantity2,edtQuantity3,edtQuantity4,edtQuantity5,edtQuantity6;
                        final LinearLayout rootLayout;
                        Button btnRegister;

                        imgProduct = finance_method.findViewById(R.id.imgProduct);
                        txtProductName = finance_method.findViewById(R.id.txtProductName);
                        txtQuantity = finance_method.findViewById(R.id.txtQuantity);
                        edtQuantity1 = finance_method.findViewById(R.id.edtQuantity1);
                        edtQuantity2 = finance_method.findViewById(R.id.edtQuantity2);
                        edtQuantity3 = finance_method.findViewById(R.id.edtQuantity3);
                        edtQuantity4 = finance_method.findViewById(R.id.edtQuantity4);
                        edtQuantity5 = finance_method.findViewById(R.id.edtQuantity5);
                        edtQuantity6 = finance_method.findViewById(R.id.edtQuantity6);
                        btnRegister = finance_method.findViewById(R.id.btnRegister);

                        rootLayout = finance_method.findViewById(R.id.rootLayout);

                        Picasso.with(getActivity()).load(viewHolder.my_product_image).fit().into(imgProduct);
                        txtProductName.setText(viewHolder.my_product_name);
                        txtQuantity.setText("Cantidad Producida: "+viewHolder.my_product_quantity_production);



                        final double production_quantity = Double.parseDouble(viewHolder.my_product_quantity_production);

                        btnRegister.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                final double quantity_1 = Double.parseDouble(edtQuantity1.getText().toString());
                                final double quantity_2 = Double.parseDouble(edtQuantity2.getText().toString());
                                final double quantity_3 = Double.parseDouble(edtQuantity3.getText().toString());
                                final double quantity_4 = Double.parseDouble(edtQuantity4.getText().toString());
                                final double quantity_5 = Double.parseDouble(edtQuantity5.getText().toString());
                                final double quantity_6 = Double.parseDouble(edtQuantity6.getText().toString());

                                if (production_quantity < quantity_1) {
                                    Snackbar.make(rootLayout,"La cantidad NO CONFORME debe ser menor o igual al totoal de la cantidad producida",Snackbar.LENGTH_LONG).show();
                                }
                                else if (production_quantity < quantity_2) {
                                    Snackbar.make(rootLayout,"La cantidad NO CONFORME debe ser menor o igual al totoal de la cantidad producida",Snackbar.LENGTH_LONG).show();
                                }
                                else if (production_quantity < quantity_3) {
                                    Snackbar.make(rootLayout,"La cantidad NO CONFORME debe ser menor o igual al totoal de la cantidad producida",Snackbar.LENGTH_LONG).show();
                                }
                                else if (production_quantity < quantity_4) {
                                    Snackbar.make(rootLayout,"La cantidad NO CONFORME debe ser menor o igual al totoal de la cantidad producida",Snackbar.LENGTH_LONG).show();
                                }
                                else if (production_quantity < quantity_5) {
                                    Snackbar.make(rootLayout,"La cantidad NO CONFORME debe ser menor o igual al totoal de la cantidad producida",Snackbar.LENGTH_LONG).show();
                                } else if (production_quantity < quantity_6) {
                                    Snackbar.make(rootLayout, "La cantidad NO CONFORME debe ser menor o igual al totoal de la cantidad producida", Snackbar.LENGTH_LONG).show();
                                } else {
                                    companyRef.child(post_key).child("Production Chain").child(postKey).child("Quality Control").child("quality_control_1").setValue(quantity_1);
                                    companyRef.child(post_key).child("Production Chain").child(postKey).child("Quality Control").child("quality_control_2").setValue(quantity_2);
                                    companyRef.child(post_key).child("Production Chain").child(postKey).child("Quality Control").child("quality_control_3").setValue(quantity_3);
                                    companyRef.child(post_key).child("Production Chain").child(postKey).child("Quality Control").child("quality_control_4").setValue(quantity_4);
                                    companyRef.child(post_key).child("Production Chain").child(postKey).child("Quality Control").child("quality_control_5").setValue(quantity_5);
                                    companyRef.child(post_key).child("Production Chain").child(postKey).child("Quality Control").child("quality_control_6").setValue(quantity_6);
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

    public static class OrderChainViewHolder extends RecyclerView.ViewHolder {

        View mView;
        TextView txtDate,txtProductName,txtProductQuantity,txtState;
        String my_product_id,my_product_name,my_product_image,my_product_quantity_production,my_state,my_date,my_time,my_code,my_product_currency,my_product_description,my_product_measure,my_product_price;
        ImageView btnActionButton;


        public OrderChainViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;

            txtDate = mView.findViewById(R.id.txtDate);
            txtProductName = mView.findViewById(R.id.txtProductName);
            txtProductQuantity = mView.findViewById(R.id.txtProductQuantity);
            txtState = mView.findViewById(R.id.txtState);
            btnActionButton = mView.findViewById(R.id.btnActionButton);
        }
        public void setProduct_id(String product_id) {
            my_product_id = product_id;
        }

        public void setProduct_name(String product_name) {
            my_product_name = product_name;
        }

        public void setProduct_image(String product_image) {
            my_product_image = product_image;
        }

        public void setProduct_quantity_production(String product_quantity_production) {
            my_product_quantity_production = product_quantity_production;
        }

        public void setState(String state) {
            my_state = state;
        }

        public void setDate(String date) {
            my_date = date;
        }

        public void setTime(String time) {
            my_time = time;
        }

        public void setCode(String code) {
            my_code = my_date;
        }

        public void setProduct_currency(String product_currency) {
            my_product_currency = product_currency;
        }

        public void setProduct_description(String product_description) {
            my_product_description = product_description;
        }

        public void setProduct_measure(String product_measure) {
            my_product_measure = product_measure;
        }


        public void setProduct_price(String product_price) {
            my_product_price = product_price;
        }
    }
}
