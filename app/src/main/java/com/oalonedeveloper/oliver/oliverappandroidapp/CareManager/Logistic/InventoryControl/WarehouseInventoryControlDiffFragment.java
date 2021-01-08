package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Logistic.InventoryControl;

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
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
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

public class WarehouseInventoryControlDiffFragment extends Fragment {

    String post_key,warehouse_id;
    DatabaseReference companyRef;
    RecyclerView recyclerView;
    DecimalFormat decimalFormat;
    int day,month,year;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_warehouse_inventory_control_diff, container, false);

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
                            viewHolder.txtStoreStock.setText("S/ "+diff_value_st);
                            viewHolder.btnActionButton.setEnabled(true);

                        } else {
                            viewHolder.txtWarehouseStock.setText("0");
                            viewHolder.txtProductName.setText("0");
                            viewHolder.txtStoreStock.setText("S/ 0.00");
                            viewHolder.btnActionButton.setEnabled(true);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                viewHolder.btnActionButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showPhysicalCountDialog();
                    }

                    private void showPhysicalCountDialog() {
                        final AlertDialog dialog = new AlertDialog.Builder(getActivity()).create();

                        LayoutInflater inflater = LayoutInflater.from(getActivity());
                        View finance_method = inflater.inflate(R.layout.physical_count_dialog,null);

                        CircleImageView imgProductDialog;
                        TextView txtProductName;
                        final EditText edtQuantity;
                        Button btnRegister;
                        final RelativeLayout rootLayout;

                        imgProductDialog = finance_method.findViewById(R.id.imgProduct);
                        txtProductName = finance_method.findViewById(R.id.txtProductName);
                        edtQuantity = finance_method.findViewById(R.id.edtQuantity);
                        btnRegister = finance_method.findViewById(R.id.btnRegister);
                        rootLayout = finance_method.findViewById(R.id.rootLayout);

                        Picasso.with(getActivity()).load(viewHolder.my_product_image).fit().into(imgProductDialog);
                        txtProductName.setText("Conteo físico para "+viewHolder.my_product_name);

                        btnRegister.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                companyRef.child(post_key).child("Warehouses").child(warehouse_id).child("Products").child(postKey).child("product_stock_manual_count").setValue(edtQuantity.getText().toString());

                                registerKardexOperation();
                            }

                            private void registerKardexOperation() {
                                Long tsLong = System.currentTimeMillis()/1000;
                                String timestamp = tsLong.toString();
                                Calendar calForDate = Calendar.getInstance();
                                SimpleDateFormat currentDate = new SimpleDateFormat("dd-MM-yyyy");
                                String saveCurrentDate = currentDate.format(calForDate.getTime());

                                Calendar calForTime = Calendar.getInstance();
                                SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss");
                                String saveCurrentTime = currentTime.format(calForTime.getTime());

                                Date date= new Date();
                                Calendar cal = Calendar.getInstance();
                                cal.setTime(date);
                                day = cal.get(Calendar.DAY_OF_MONTH);
                                month = cal.get(Calendar.MONTH)+1;
                                year = cal.get(Calendar.YEAR);

                                companyRef.child(post_key).child("Kardex").child(timestamp).child("product_id").setValue(postKey);
                                companyRef.child(post_key).child("Kardex").child(timestamp).child("product_price").setValue(viewHolder.my_product_price);
                                companyRef.child(post_key).child("Kardex").child(timestamp).child("warehouse_origin_id").setValue(warehouse_id);
                                companyRef.child(post_key).child("Kardex").child(timestamp).child("warehouse_destination_id").setValue(warehouse_id);
                                companyRef.child(post_key).child("Kardex").child(timestamp).child("stock").setValue(edtQuantity.getText().toString());
                                companyRef.child(post_key).child("Kardex").child(timestamp).child("operation_date").setValue(saveCurrentDate);
                                companyRef.child(post_key).child("Kardex").child(timestamp).child("operation_time").setValue(saveCurrentTime);
                                companyRef.child(post_key).child("Kardex").child(timestamp).child("operation_day").setValue(day+"");
                                companyRef.child(post_key).child("Kardex").child(timestamp).child("operation_month").setValue(month+"");
                                companyRef.child(post_key).child("Kardex").child(timestamp).child("operation_year").setValue(year+"");
                                companyRef.child(post_key).child("Kardex").child(timestamp).child("operation_type").setValue("inventory_control");
                                companyRef.child(post_key).child("Kardex").child(timestamp).child("timestamp").setValue(ServerValue.TIMESTAMP);

                                Toasty.success(getActivity(), "Conteo Físico registrado con éxito", Toast.LENGTH_LONG).show();
                                dialog.dismiss();
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
