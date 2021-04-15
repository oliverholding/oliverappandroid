package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Logistic.Outsoursing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
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

import de.hdodenhof.circleimageview.CircleImageView;
import es.dmoral.toasty.Toasty;

public class OutsoursingActivity extends AppCompatActivity {

    DatabaseReference companyRef;
    String post_key;
    RecyclerView recyclerView;
    DecimalFormat decimalFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outsoursing);

        post_key = getIntent().getExtras().getString("post_key");
        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");

        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        decimalFormat = new DecimalFormat("0.00");

        showCompanyProducts();
    }

    private void showCompanyProducts() {
        Query query = companyRef.child(post_key).child("My Products").orderByChild("timestamp");
        FirebaseRecyclerAdapter<ProductsModel, CompanyProductsViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<ProductsModel, CompanyProductsViewHolder>
                (ProductsModel.class,R.layout.product_outsoursing_item,CompanyProductsViewHolder.class,query) {
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
                Picasso.with(OutsoursingActivity.this).load(viewHolder.my_product_image).fit().into(viewHolder.imgProduct);

                companyRef.child(post_key).child("My Products").child(postKey).child("Outsourcing").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            if (dataSnapshot.hasChild("wait")) {
                                String value = dataSnapshot.child("wait").getValue().toString();
                                viewHolder.txtWaitingTime.setText(value+"%");
                            }
                            if (dataSnapshot.hasChild("extra")) {
                                String value = dataSnapshot.child("extra").getValue().toString();
                                viewHolder.txtExtraProcess.setText(value+"%");
                            }
                            if (dataSnapshot.hasChild("weight")) {
                                String value = dataSnapshot.child("weight").getValue().toString();
                                viewHolder.txtTotal.setText(value+"%");
                            }

                        } else {
                            viewHolder.txtWaitingTime.setText("-");
                            viewHolder.txtExtraProcess.setText("-");
                            viewHolder.txtTotal.setText("-");
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });



                viewHolder.btnActionButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showEditInformation();
                    }

                    private void showEditInformation() {
                        final AlertDialog dialog = new AlertDialog.Builder(OutsoursingActivity.this).create();

                        LayoutInflater inflater = LayoutInflater.from(OutsoursingActivity.this);
                        View finance_method = inflater.inflate(R.layout.outsourcing_set_data_dialog,null);

                        final EditText edtWait,edtExtraProcess;
                        Button btnRegister;
                        final RelativeLayout rootLayout;

                        edtWait = finance_method.findViewById(R.id.edtWait);
                        edtExtraProcess = finance_method.findViewById(R.id.edtExtraProcess);
                        btnRegister = finance_method.findViewById(R.id.btnRegister);
                        rootLayout = finance_method.findViewById(R.id.rootLayout);

                        btnRegister.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                double wait = Double.parseDouble(edtWait.getText().toString());
                                double extra = Double.parseDouble(edtExtraProcess.getText().toString());

                                double wait_weight = wait*0.45;
                                double extra_weight = extra*0.55;

                                double weighted = wait_weight+extra_weight;

                                String my_wait = decimalFormat.format(wait_weight);
                                String my_extra = decimalFormat.format(extra_weight);
                                String my_weight = decimalFormat.format(weighted);

                                if (TextUtils.isEmpty(edtExtraProcess.getText().toString())) {
                                    Snackbar.make(rootLayout,"Debes compeltar el proceso extra",Snackbar.LENGTH_SHORT).show();
                                } else if (TextUtils.isEmpty(edtWait.getText().toString())) {
                                    Snackbar.make(rootLayout, "Debes compeltar la espera", Snackbar.LENGTH_SHORT).show();
                                } else {
                                    companyRef.child(post_key).child("My Products").child(postKey).child("Outsourcing").child("wait").setValue(my_wait);
                                    companyRef.child(post_key).child("My Products").child(postKey).child("Outsourcing").child("extra").setValue(my_extra);
                                    companyRef.child(post_key).child("My Products").child(postKey).child("Outsourcing").child("weight").setValue(my_weight);
                                    Toasty.success(OutsoursingActivity.this, "Registrado", Toast.LENGTH_LONG).show();
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
        TextView txtProductName,txtWaitingTime,txtExtraProcess,txtTotal;
        ImageView btnActionButton;

        public CompanyProductsViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;

            imgProduct = mView.findViewById(R.id.imgProduct);
            txtProductName = mView.findViewById(R.id.txtProductName);
            txtWaitingTime = mView.findViewById(R.id.txtWaitingTime);
            txtExtraProcess = mView.findViewById(R.id.txtExtraProcess);
            txtTotal = mView.findViewById(R.id.txtTotal);
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
