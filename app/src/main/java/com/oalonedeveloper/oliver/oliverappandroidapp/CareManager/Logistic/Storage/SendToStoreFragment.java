package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Logistic.Storage;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
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
import com.google.firebase.storage.FirebaseStorage;
import com.oalonedeveloper.oliver.oliverappandroidapp.Companies.ProductsModel;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;
import es.dmoral.toasty.Toasty;

public class SendToStoreFragment extends Fragment {

    String post_key,warehouse_id,image_verification,product_key,downloadUrl,product_price,warehouse_name;
    DatabaseReference companyRef;
    RecyclerView recyclerView,recyclerView1;
    ImageView imgProduct;
    ProgressDialog loadingBar;
    DecimalFormat decimalFormat;
    int day,month,year;
    AlertDialog dialog_options,dialog_list;
    String origin_warehouse_product_stock;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_send_to_store, container, false);;

        post_key = getActivity().getIntent().getExtras().getString("post_key");
        warehouse_id = getActivity().getIntent().getExtras().getString("warehouse_id");
        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");

        decimalFormat = new DecimalFormat("0.00");

        loadingBar = new ProgressDialog(getActivity());

        recyclerView = view.findViewById(R.id.recyclerView);

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        image_verification = "";

        Date date= new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        day = cal.get(Calendar.DAY_OF_MONTH);
        month = cal.get(Calendar.MONTH)+1;
        year = cal.get(Calendar.YEAR);


        companyRef.child(post_key).child("Warehouses").child(warehouse_id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                warehouse_name = dataSnapshot.child("warehouse_name").getValue().toString();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        showWarehouseProducts();
        return view;
    }

    private void showWarehouseProducts() {
        Query query = companyRef.child(post_key).child("Warehouses").child(warehouse_id).child("Products").orderByChild("warehouse_stock");
        FirebaseRecyclerAdapter<ProductsModel, CompanyProductsViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<ProductsModel, CompanyProductsViewHolder>
                (ProductsModel.class,R.layout.product_warehouse_to_send_item,CompanyProductsViewHolder.class,query) {
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
                viewHolder.txtWarehouseStock.setText(viewHolder.my_product_stock);
                companyRef.child(post_key).child("My Products").child(postKey).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChild("product_stock")) {
                            String product_stock = dataSnapshot.child("product_stock").getValue().toString();
                            viewHolder.txtStoreStock.setText(product_stock);
                        }else {
                            viewHolder.txtStoreStock.setText("0");
                        }

                        if (!dataSnapshot.exists()) {
                            viewHolder.txtStoreStock.setText("0");
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                viewHolder.btnActionButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showTransferOptions();
                    }

                    private void showTransferOptions() {
                        dialog_options = new AlertDialog.Builder(getActivity()).create();

                        LayoutInflater inflater = LayoutInflater.from(getActivity());
                        View finance_method = inflater.inflate(R.layout.warehouse_transfer_options,null);

                        ImageButton btnSendToStore,btnSendToWarehouse;

                        btnSendToStore = finance_method.findViewById(R.id.btnSendToStore);
                        btnSendToWarehouse = finance_method.findViewById(R.id.btnSendToWarehouse);

                        btnSendToStore.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                showSetQuantityToStore();
                            }

                            private void showSetQuantityToStore() {
                                final AlertDialog dialog = new AlertDialog.Builder(getActivity()).create();

                                LayoutInflater inflater = LayoutInflater.from(getActivity());
                                View finance_method = inflater.inflate(R.layout.set_quantity_send_to_store_dialog,null);

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
                                txtProductName.setText("Stock actual en almacén de "+viewHolder.my_product_name+": "+viewHolder.my_product_stock);

                                btnRegister.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        final double current_stock = Double.parseDouble(viewHolder.my_product_stock);
                                        final double stock_to_transfer = Double.parseDouble(edtQuantity.getText().toString());

                                        if (current_stock < stock_to_transfer) {
                                            Snackbar.make(rootLayout, "Este monto sobrepasa tu stock", Snackbar.LENGTH_LONG).show();
                                        } else {

                                            companyRef.child(post_key).child("My Products").child(postKey).addListenerForSingleValueEvent(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(DataSnapshot dataSnapshot) {
                                                    double warehouse_product_stock;
                                                    if (dataSnapshot.hasChild("product_stock")) {

                                                        String product_stock = dataSnapshot.child("product_stock").getValue().toString();
                                                        warehouse_product_stock = Double.parseDouble(product_stock);

                                                        double stock_on_store = current_stock - stock_to_transfer;
                                                        String stock_on_store_st = decimalFormat.format(stock_on_store);
                                                        companyRef.child(post_key).child("Warehouses").child(warehouse_id).child("Products").child(postKey).child("product_stock").setValue(stock_on_store_st);
                                                        double stock_on_warehouse = warehouse_product_stock + stock_to_transfer;
                                                        String stock_on_warehouse_st = decimalFormat.format(stock_on_warehouse);
                                                        companyRef.child(post_key).child("My Products").child(postKey).child("product_stock").setValue(stock_on_warehouse_st);

                                                        registerKardexOperation();

                                                    } else {

                                                        companyRef.child(post_key).child("Warehouses").child(warehouse_id).child("Products").child(postKey).addListenerForSingleValueEvent(new ValueEventListener() {
                                                            @Override
                                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                                String product_image = dataSnapshot.child("product_image").getValue().toString();
                                                                String product_currency = dataSnapshot.child("product_currency").getValue().toString();
                                                                String product_description = dataSnapshot.child("product_description").getValue().toString();
                                                                String product_measure = dataSnapshot.child("product_measure").getValue().toString();
                                                                String company_id = dataSnapshot.child("company_id").getValue().toString();
                                                                String code = dataSnapshot.child("code").getValue().toString();
                                                                String product_name = dataSnapshot.child("product_name").getValue().toString();
                                                                product_price = dataSnapshot.child("product_price").getValue().toString();
                                                                String product_stock = dataSnapshot.child("product_stock").getValue().toString();
                                                                long timestamp = dataSnapshot.child("timestamp").getValue(Long.class);



                                                                double stock_on_store = current_stock - stock_to_transfer;
                                                                String stock_on_store_st = decimalFormat.format(stock_on_store);
                                                                companyRef.child(post_key).child("Warehouses").child(warehouse_id).child("Products").child(postKey).child("product_stock").setValue(stock_on_store_st);

                                                                companyRef.child(post_key).child("My Products").child(postKey).child("product_stock").setValue(edtQuantity.getText().toString());
                                                                companyRef.child(post_key).child("My Products").child(postKey).child("product_image").setValue(product_image);
                                                                companyRef.child(post_key).child("My Products").child(postKey).child("product_currency").setValue(product_currency);
                                                                companyRef.child(post_key).child("My Products").child(postKey).child("product_description").setValue(product_description);
                                                                companyRef.child(post_key).child("My Products").child(postKey).child("product_measure").setValue(product_measure);
                                                                companyRef.child(post_key).child("My Products").child(postKey).child("company_id").setValue(company_id);
                                                                companyRef.child(post_key).child("My Products").child(postKey).child("code").setValue(code);
                                                                companyRef.child(post_key).child("My Products").child(postKey).child("product_name").setValue(product_name);
                                                                companyRef.child(post_key).child("My Products").child(postKey).child("product_price").setValue(product_price);
                                                                companyRef.child(post_key).child("My Products").child(postKey).child("timestamp").setValue(timestamp);

                                                                registerKardexOperation();


                                                            }

                                                            @Override
                                                            public void onCancelled(DatabaseError databaseError) {

                                                            }
                                                        });


                                                    }
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
                                                    companyRef.child(post_key).child("Kardex").child(timestamp).child("warehouse_destination_id").setValue("store");
                                                    companyRef.child(post_key).child("Kardex").child(timestamp).child("stock").setValue(edtQuantity.getText().toString());
                                                    companyRef.child(post_key).child("Kardex").child(timestamp).child("operation_date").setValue(saveCurrentDate);
                                                    companyRef.child(post_key).child("Kardex").child(timestamp).child("operation_time").setValue(saveCurrentTime);
                                                    companyRef.child(post_key).child("Kardex").child(timestamp).child("operation_day").setValue(day+"");
                                                    companyRef.child(post_key).child("Kardex").child(timestamp).child("operation_month").setValue(month+"");
                                                    companyRef.child(post_key).child("Kardex").child(timestamp).child("operation_year").setValue(year+"");
                                                    companyRef.child(post_key).child("Kardex").child(timestamp).child("operation_type").setValue("to_store_transfer");
                                                    companyRef.child(post_key).child("Kardex").child(timestamp).child("timestamp").setValue(ServerValue.TIMESTAMP);

                                                    Toasty.success(getActivity(), "Transferencia realizada con éxito", Toast.LENGTH_LONG).show();
                                                    dialog.dismiss();

                                                }

                                                @Override
                                                public void onCancelled(DatabaseError databaseError) {

                                                }
                                            });

                                        }


                                    }
                                });

                                dialog.setView(finance_method);
                                dialog.show();
                            }
                        });

                        btnSendToWarehouse.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                showWarehousesToSend();
                            }

                            private void showWarehousesToSend() {
                                dialog_list = new AlertDialog.Builder(getActivity()).create();

                                LayoutInflater inflater = LayoutInflater.from(getActivity());
                                View finance_method = inflater.inflate(R.layout.warehouses_dialog,null);

                                recyclerView1 = finance_method.findViewById(R.id.recyclerView1);

                                recyclerView1.setHasFixedSize(true);
                                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                                linearLayoutManager.setReverseLayout(false);
                                linearLayoutManager.setStackFromEnd(false);
                                recyclerView1.setLayoutManager(linearLayoutManager);

                                product_key = postKey;

                                showWareHouses();

                                dialog_list.setView(finance_method);
                                dialog_list.show();
                            }
                        });

                        dialog_options.setView(finance_method);
                        dialog_options.show();
                    }
                });

            }
        };
        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    private void showWareHouses() {
        Query query = companyRef.child(post_key).child("Warehouses").orderByChild("timestamp");
        FirebaseRecyclerAdapter<WarehousesModel, WarehousesViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<WarehousesModel, WarehousesViewHolder>
                (WarehousesModel.class,R.layout.warehouse_item,WarehousesViewHolder.class,query) {
            @Override
            protected void populateViewHolder(final WarehousesViewHolder viewHolder, WarehousesModel model, int position) {
                final String postKey = getRef(position).getKey();
                viewHolder.setWarehouse_destination(model.getWarehouse_destination());
                viewHolder.setWarehouse_name(model.getWarehouse_name());

                viewHolder.txtWarehouseName.setText(viewHolder.ware_name);

                if (viewHolder.ware_destiny.equals("products")) {
                    viewHolder.txtWarehouseDestination.setText("Destino: Productos Terminados");
                }
                if (viewHolder.ware_destiny.equals("materials")) {
                    viewHolder.txtWarehouseDestination.setText("Destino: Materiales Terminados");
                }

                if (warehouse_id.equals(postKey)) {
                    viewHolder.mView.setVisibility(View.GONE);
                    viewHolder.mView.getLayoutParams().height = 0;
                }

                viewHolder.btnManageWarehouse.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setQuantityToSendToWarehouse();
                    }

                    private void setQuantityToSendToWarehouse() {
                        final AlertDialog dialog = new AlertDialog.Builder(getActivity()).create();

                        LayoutInflater inflater = LayoutInflater.from(getActivity());
                        View finance_method = inflater.inflate(R.layout.set_quantity_warehouse_towarehouse_dialog,null);

                        final CircleImageView imgProductDialog;
                        final TextView txtProductName;
                        final EditText edtQuantity;
                        final Button btnRegister;
                        final RelativeLayout rootLayout;

                        imgProductDialog = finance_method.findViewById(R.id.imgProduct);
                        txtProductName = finance_method.findViewById(R.id.txtProductName);
                        edtQuantity = finance_method.findViewById(R.id.edtQuantity);
                        btnRegister = finance_method.findViewById(R.id.btnRegister);
                        rootLayout = finance_method.findViewById(R.id.rootLayout);

                        btnRegister.setEnabled(false);

                        companyRef.child(post_key).child("Warehouses").child(warehouse_id).child("Products").child(product_key).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {

                                String product_name = dataSnapshot.child("product_name").getValue().toString();
                                String product_image = dataSnapshot.child("product_image").getValue().toString();
                                origin_warehouse_product_stock = dataSnapshot.child("product_stock").getValue().toString();

                                Picasso.with(getActivity()).load(product_image).fit().into(imgProductDialog);
                                txtProductName.setText("Stock actual de "+product_name+" en "+warehouse_name+": "+origin_warehouse_product_stock);
                                btnRegister.setEnabled(true);
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });


                        btnRegister.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                final double current_stock = Double.parseDouble(origin_warehouse_product_stock);
                                final double stock_to_transfer = Double.parseDouble(edtQuantity.getText().toString());

                                if (current_stock < stock_to_transfer) {
                                    Snackbar.make(rootLayout, "Este monto sobrepasa tu stock", Snackbar.LENGTH_LONG).show();
                                } else {

                                    companyRef.child(post_key).child("Warehouses").child(postKey).child("Products").child(product_key).addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {
                                            double warehouse_product_stock;
                                            if (dataSnapshot.hasChild("product_stock")) {

                                                String product_stock = dataSnapshot.child("product_stock").getValue().toString();
                                                product_price = dataSnapshot.child("product_price").getValue().toString();
                                                warehouse_product_stock = Double.parseDouble(product_stock);

                                                double stock_on_store = current_stock - stock_to_transfer;
                                                String stock_on_store_st = decimalFormat.format(stock_on_store);
                                                companyRef.child(post_key).child("Warehouses").child(warehouse_id).child("Products").child(product_key).child("product_stock").setValue(stock_on_store_st);
                                                double stock_on_warehouse = warehouse_product_stock + stock_to_transfer;
                                                String stock_on_warehouse_st = decimalFormat.format(stock_on_warehouse);
                                                companyRef.child(post_key).child("Warehouses").child(postKey).child("Products").child(product_key).child("product_stock").setValue(stock_on_warehouse_st);

                                                registerKardexOperation();

                                            } else {

                                                companyRef.child(post_key).child("Warehouses").child(warehouse_id).child("Products").child(product_key).addListenerForSingleValueEvent(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                                        String product_image = dataSnapshot.child("product_image").getValue().toString();
                                                        String product_currency = dataSnapshot.child("product_currency").getValue().toString();
                                                        String product_description = dataSnapshot.child("product_description").getValue().toString();
                                                        String product_measure = dataSnapshot.child("product_measure").getValue().toString();
                                                        String company_id = dataSnapshot.child("company_id").getValue().toString();
                                                        String code = dataSnapshot.child("code").getValue().toString();
                                                        String product_name = dataSnapshot.child("product_name").getValue().toString();
                                                        product_price = dataSnapshot.child("product_price").getValue().toString();
                                                        String product_stock = dataSnapshot.child("product_stock").getValue().toString();
                                                        long timestamp = dataSnapshot.child("timestamp").getValue(Long.class);



                                                        double stock_on_store = current_stock - stock_to_transfer;
                                                        String stock_on_store_st = decimalFormat.format(stock_on_store);
                                                        companyRef.child(post_key).child("Warehouses").child(warehouse_id).child("Products").child(product_key).child("product_stock").setValue(stock_on_store_st);

                                                        companyRef.child(post_key).child("Warehouses").child(postKey).child("Products").child(product_key).child("product_stock").setValue(edtQuantity.getText().toString());
                                                        companyRef.child(post_key).child("Warehouses").child(postKey).child("Products").child(product_key).child("product_image").setValue(product_image);
                                                        companyRef.child(post_key).child("Warehouses").child(postKey).child("Products").child(product_key).child("product_currency").setValue(product_currency);
                                                        companyRef.child(post_key).child("Warehouses").child(postKey).child("Products").child(product_key).child("product_description").setValue(product_description);
                                                        companyRef.child(post_key).child("Warehouses").child(postKey).child("Products").child(product_key).child("product_measure").setValue(product_measure);
                                                        companyRef.child(post_key).child("Warehouses").child(postKey).child("Products").child(product_key).child("company_id").setValue(company_id);
                                                        companyRef.child(post_key).child("Warehouses").child(postKey).child("Products").child(product_key).child("code").setValue(code);
                                                        companyRef.child(post_key).child("Warehouses").child(postKey).child("Products").child(product_key).child("product_name").setValue(product_name);
                                                        companyRef.child(post_key).child("Warehouses").child(postKey).child("Products").child(product_key).child("product_price").setValue(product_price);
                                                        companyRef.child(post_key).child("Warehouses").child(postKey).child("Products").child(product_key).child("timestamp").setValue(timestamp);

                                                        registerKardexOperation();



                                                    }

                                                    @Override
                                                    public void onCancelled(DatabaseError databaseError) {

                                                    }
                                                });


                                            }
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

                                            companyRef.child(post_key).child("Kardex").child(timestamp).child("product_id").setValue(product_key);
                                            companyRef.child(post_key).child("Kardex").child(timestamp).child("product_price").setValue(product_price);
                                            companyRef.child(post_key).child("Kardex").child(timestamp).child("warehouse_origin_id").setValue(warehouse_id);
                                            companyRef.child(post_key).child("Kardex").child(timestamp).child("warehouse_destination_id").setValue(postKey);
                                            companyRef.child(post_key).child("Kardex").child(timestamp).child("stock").setValue(edtQuantity.getText().toString());
                                            companyRef.child(post_key).child("Kardex").child(timestamp).child("operation_date").setValue(saveCurrentDate);
                                            companyRef.child(post_key).child("Kardex").child(timestamp).child("operation_time").setValue(saveCurrentTime);
                                            companyRef.child(post_key).child("Kardex").child(timestamp).child("operation_day").setValue(day+"");
                                            companyRef.child(post_key).child("Kardex").child(timestamp).child("operation_month").setValue(month+"");
                                            companyRef.child(post_key).child("Kardex").child(timestamp).child("operation_year").setValue(year+"");
                                            companyRef.child(post_key).child("Kardex").child(timestamp).child("operation_type").setValue("warehouse_transfer");
                                            companyRef.child(post_key).child("Kardex").child(timestamp).child("timestamp").setValue(ServerValue.TIMESTAMP);

                                            Toasty.success(getActivity(), "Transferencia realizada con éxito a "+viewHolder.ware_name, Toast.LENGTH_LONG).show();
                                            dialog.dismiss();

                                        }

                                        @Override
                                        public void onCancelled(DatabaseError databaseError) {

                                        }
                                    });

                                }


                            }
                        });


                        dialog.setView(finance_method);
                        dialog.show();
                    }
                });

            }
        };
        recyclerView1.setAdapter(firebaseRecyclerAdapter);
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

    public static class WarehousesViewHolder extends RecyclerView.ViewHolder {

        View mView;
        String ware_name,ware_destiny;
        TextView txtWarehouseName,txtWarehouseDestination,btnManageWarehouse;

        public WarehousesViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;

            txtWarehouseName = mView.findViewById(R.id.txtWarehouseName);
            txtWarehouseDestination = mView.findViewById(R.id.txtWarehouseDestination);
            btnManageWarehouse = mView.findViewById(R.id.btnManageWarehouse);

            btnManageWarehouse.setText("SELECCIONAR");
        }

        public void setWarehouse_name(String warehouse_name) {
            ware_name = warehouse_name;
        }

        public void setWarehouse_destination(String warehouse_destination) {
            ware_destiny = warehouse_destination;
        }
    }
}
