package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Production.CriticalInventoryControl;

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
import com.google.firebase.database.ServerValue;
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


public class CriticalInventoryCategoryFragment extends Fragment {

    String post_key,warehouse_id,category;
    DatabaseReference companyRef;
    RecyclerView recyclerView;
    DecimalFormat decimalFormat;
    int day,month,year;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_critical_inventory_category, container, false);


        post_key = getActivity().getIntent().getExtras().getString("post_key");
        warehouse_id = getActivity().getIntent().getExtras().getString("warehouse_id");
        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");

        decimalFormat = new DecimalFormat("0.00");

        recyclerView = view.findViewById(R.id.recyclerView);

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        showWarehouseProducts();


        return view;
    }

    private void showWarehouseProducts() {
        Query query = companyRef.child(post_key).child("Warehouses").child(warehouse_id).child("Products").orderByChild("warehouse_stock");
        FirebaseRecyclerAdapter<ProductsModel, CompanyProductsViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<ProductsModel, CompanyProductsViewHolder>
                (ProductsModel.class,R.layout.inventory_control_diff_item,CompanyProductsViewHolder.class,query) {
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

                viewHolder.btnActionButton.setEnabled(false);

                companyRef.child(post_key).child("Warehouses").child(warehouse_id).child("Products").child(postKey).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {


                        if (dataSnapshot.hasChild("product_stock_manual_count")) {
                            String product_stock_manual_count = dataSnapshot.child("product_stock_manual_count").getValue().toString();
                            double product_stock_manual_count_db = Double.parseDouble(product_stock_manual_count);
                            double current_stock_db = Double.parseDouble(viewHolder.my_product_stock);
                            double stock_diff = product_stock_manual_count_db - current_stock_db;
                            String stock_diff_st = decimalFormat.format(stock_diff);
                            viewHolder.txtProductName.setText(product_stock_manual_count);
                            viewHolder.txtWarehouseStock.setText(stock_diff_st);

                            double stock_diff_abs = Math.abs(stock_diff);
                            double price = Double.parseDouble(viewHolder.my_product_price);
                            double diff_value = stock_diff_abs*price;
                            String diff_value_st = decimalFormat.format(diff_value);

                            viewHolder.btnActionButton.setEnabled(true);

                        } else {
                            viewHolder.txtWarehouseStock.setText("0");
                            viewHolder.txtProductName.setText("0");
                            viewHolder.btnActionButton.setEnabled(true);
                        }

                        if (dataSnapshot.hasChild("product_category")) {
                            String product_category = dataSnapshot.child("product_category").getValue().toString();
                            viewHolder.txtStoreStock.setText(product_category);
                        } else {
                            viewHolder.txtStoreStock.setText("-");
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                viewHolder.btnActionButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        showSetCategoryDialog();

                    }

                    private void showSetCategoryDialog() {
                        final AlertDialog dialog = new AlertDialog.Builder(getActivity()).create();

                        LayoutInflater inflater = LayoutInflater.from(getActivity());
                        View finance_method = inflater.inflate(R.layout.set_product_inventory_category_dialog,null);

                        CircleImageView imgProduct;
                        TextView txtProductName;
                        final EditText edtDays;
                        Button btnRegister;
                        final RelativeLayout rootLayout;

                        imgProduct = finance_method.findViewById(R.id.imgProduct);
                        txtProductName = finance_method.findViewById(R.id.txtProductName);
                        edtDays = finance_method.findViewById(R.id.edtDays);
                        btnRegister = finance_method.findViewById(R.id.btnRegister);
                        rootLayout = finance_method.findViewById(R.id.rootLayout);

                        Picasso.with(getActivity()).load(viewHolder.my_product_image).fit().into(imgProduct);
                        txtProductName.setText(viewHolder.my_product_name);

                        btnRegister.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (TextUtils.isEmpty(edtDays.getText().toString())) {
                                    Snackbar.make(rootLayout, "Debes ingresar el número de días", Snackbar.LENGTH_LONG).show();
                                } else {

                                    int days = Integer.parseInt(edtDays.getText().toString());


                                    if (days >= 10) {
                                        category = "A";
                                    }
                                    if (days >= 5 && days <= 9) {
                                        category = "B";
                                    }
                                    if (days <= 4) {
                                        category = "C";
                                    }

                                    companyRef.child(post_key).child("Warehouses").child(warehouse_id).child("Products").child(postKey).child("product_category").setValue(category);

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
        String my_product_image,my_product_currency,my_product_description,my_product_measure,my_uid, my_code,my_product_name,my_product_price,my_product_stock;
        CircleImageView imgProduct;
        TextView txtProductName,txtWarehouseStock,txtStoreStock,txtProductPrice;
        ImageView btnActionButton;

        public CompanyProductsViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;

            imgProduct = mView.findViewById(R.id.imgProduct);
            txtProductName = mView.findViewById(R.id.txtProductName);
            txtWarehouseStock = mView.findViewById(R.id.txtWarehouseStock);
            txtStoreStock = mView.findViewById(R.id.txtStoreStock);
            txtProductPrice = mView.findViewById(R.id.txtProductPrice);
            btnActionButton = mView.findViewById(R.id.btnActionButton);
        }
        public void setProduct_image(String product_image) {
            my_product_image = product_image;
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
        public void setUid(String uid) {
            my_uid = uid;
        }
        public void setCode(String code) {
            my_code = code;
        }
        public void setProduct_name(String product_name) {
            my_product_name = product_name;
        }
        public void setProduct_price(String product_price) {
            my_product_price = product_price;
        }
        public void setProduct_stock(String product_stock) {
            my_product_stock = product_stock;
        }

    }
}
