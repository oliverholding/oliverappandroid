package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Logistic.PurchaseOrder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
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
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Logistic.Storage.WarehousesModel;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Random;

import es.dmoral.toasty.Toasty;

public class PurchaseOrdersListActivity extends AppCompatActivity {

    String post_key,item_id,purchase_order_id;
    RecyclerView recyclerView,recyclerView2,recyclerView1;
    DatabaseReference companyRef;
    Button btnCreatePurchaseOrder;
    AlertDialog warehouse_dialog;
    int day,month,year;
    DecimalFormat decimalFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_orders_list);

        post_key = getIntent().getExtras().getString("post_key");
        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        decimalFormat = new DecimalFormat("0.00");

        btnCreatePurchaseOrder = findViewById(R.id.btnCreatePurchaseOrder);

        btnCreatePurchaseOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PurchaseOrdersListActivity.this, PurchaseOrderActivity.class);
                intent.putExtra("post_key",post_key);
                startActivity(intent);
            }
        });
        
        showPurchaseOrders();
    }

    private void showPurchaseOrders() {
        Query query = companyRef.child(post_key).child("Purchased Orders").orderByChild("timestamp");
        FirebaseRecyclerAdapter<PurchaseOrdersModel,PurchaseOrdersViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<PurchaseOrdersModel, PurchaseOrdersViewHolder>
                (PurchaseOrdersModel.class,R.layout.purchase_order_item,PurchaseOrdersViewHolder.class,query) {
            @Override
            protected void populateViewHolder(final PurchaseOrdersViewHolder viewHolder, PurchaseOrdersModel model, int position) {
                final String postKey = getRef(position).getKey();
                viewHolder.setOperation_date(model.getOperation_date());
                viewHolder.setOperation_day(model.getOperation_day());
                viewHolder.setOperation_month(model.getOperation_month());
                viewHolder.setOperation_time(model.getOperation_time());
                viewHolder.setOperation_year(model.getOperation_year());
                viewHolder.setPurchase_order_id(model.getPurchase_order_id());
                viewHolder.setPurchase_order_supplier_id(model.getPurchase_order_supplier_id());
                viewHolder.setPurchase_order_total_amount(model.getPurchase_order_total_amount());
                viewHolder.setPurchase_order_state(model.getPurchase_order_state());

                if (viewHolder.my_purchase_order_state != null) {
                    if (viewHolder.my_purchase_order_state.equals("pending")) {
                        viewHolder.txtCode.setText("Enviado");
                    }
                    if (viewHolder.my_purchase_order_state.equals("ready")) {
                        viewHolder.txtCode.setText("Entregado");
                    }
                    if (viewHolder.my_purchase_order_state.equals("ready_delayed")) {
                        viewHolder.txtCode.setText("Entregado con Retraso");
                    }
                    if (viewHolder.my_purchase_order_state.equals("returned")) {
                        viewHolder.txtCode.setText("Devuelto");
                    }
                    if (viewHolder.my_purchase_order_state.equals("supplier_rejected")) {
                        viewHolder.txtCode.setText("Rechazado por Proveedor");
                    }
                    if (viewHolder.my_purchase_order_state.equals("canceled")) {
                        viewHolder.txtCode.setText("Cancelado");
                    }
                }



                viewHolder.txtDate.setText(viewHolder.my_operation_date);

                viewHolder.txtTotal.setText("S/ "+viewHolder.my_purchase_order_total_amount);

                if (viewHolder.my_purchase_order_supplier_id != null) {
                    companyRef.child(post_key).child("My Suppliers").child(viewHolder.my_purchase_order_supplier_id).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            String supplier_name = dataSnapshot.child("supplier_name").getValue().toString();
                            viewHolder.txtSupplier.setText(supplier_name);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }

                viewHolder.btnActionButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final AlertDialog dialog = new AlertDialog.Builder(PurchaseOrdersListActivity.this).create();

                        LayoutInflater inflater = LayoutInflater.from(PurchaseOrdersListActivity.this);
                        View finance_method = inflater.inflate(R.layout.purchase_state_options_dialog,null);

                        ImageButton btnReady,btnReturned;

                        btnReady = finance_method.findViewById(R.id.btnReady);
                        btnReturned = finance_method.findViewById(R.id.btnReturned);

                        purchase_order_id = postKey;

                        btnReady.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                final AlertDialog dialog = new AlertDialog.Builder(PurchaseOrdersListActivity.this).create();

                                LayoutInflater inflater = LayoutInflater.from(PurchaseOrdersListActivity.this);
                                View finance_method = inflater.inflate(R.layout.purchase_order_ready_dialog,null);

                                final TextView txtSupplierName;
                                Button btnRegister;
                                final RadioButton rdLate,rdOnTime;

                                txtSupplierName = finance_method.findViewById(R.id.txtSupplierName);
                                btnRegister = finance_method.findViewById(R.id.btnRegister);
                                rdLate = finance_method.findViewById(R.id.rdLate);
                                rdOnTime = finance_method.findViewById(R.id.rdOnTime);

                                btnRegister.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        companyRef.child(post_key).child("Purchased Orders").child(postKey).child("Items").addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(DataSnapshot dataSnapshot) {

                                                for (final DataSnapshot ds : dataSnapshot.getChildren()) {
                                                    //String warehouse_destination_id = ds.child(" warehouse_destination_id").getValue().toString();
                                                    Map<String, Object> map = (Map<String, Object>) ds.getValue();
                                                    Object warehouse_destination_id = map.get("warehouse_destination_id");
                                                    final Object item_quantity = map.get("item_quantity");
                                                    final Object item_image = map.get("item_image");
                                                    final Object item_measure = map.get("item_measure");
                                                    final Object item_currency = map.get("item_currency");
                                                    final Object item_description = map.get("item_description");
                                                    final Object item_name = map.get("item_name");
                                                    final Object item_price = map.get("item_price");
                                                    final String warehouse_destination_id_st = (String) warehouse_destination_id;
                                                    final String item_quantity_st = (String) item_quantity;
                                                    final String item_image_st = (String) item_image;
                                                    final String item_measure_st = (String) item_measure;
                                                    final String item_currency_st = (String) item_currency;
                                                    final String item_description_st = (String) item_description;
                                                    final String item_name_st = (String) item_name;
                                                    final String item_price_st = (String) item_price;

                                                    companyRef.child(post_key).child("Warehouses").child(warehouse_destination_id_st).child("Products").child(ds.getKey()).addListenerForSingleValueEvent(new ValueEventListener() {
                                                        @Override
                                                        public void onDataChange(DataSnapshot dataSnapshot) {
                                                            if (dataSnapshot.exists()) {
                                                                String product_stock = dataSnapshot.child("product_stock").getValue().toString();
                                                                String product_price = dataSnapshot.child("product_price").getValue().toString();
                                                                double current_stock = Double.parseDouble(product_stock);
                                                                double item_quantity_db = Double.parseDouble(item_quantity_st);

                                                                double total_stock = current_stock+item_quantity_db;
                                                                String total_stock_st = decimalFormat.format(total_stock);

                                                                if (rdLate.isChecked()) {
                                                                    companyRef.child(post_key).child("Purchased Orders").child(postKey).child("purchase_order_state").setValue("ready_delayed");
                                                                    companyRef.child(post_key).child("Purchased Items").child(ds.getKey()).child("Suppliers").child(viewHolder.my_purchase_order_supplier_id).child("Delivery Delayed").child(postKey).setValue(item_quantity_st);
                                                                }
                                                                if (rdOnTime.isChecked()) {
                                                                    companyRef.child(post_key).child("Purchased Orders").child(postKey).child("purchase_order_state").setValue("ready");
                                                                    companyRef.child(post_key).child("Purchased Items").child(ds.getKey()).child("Suppliers").child(viewHolder.my_purchase_order_supplier_id).child("Delivery on Time").child(postKey).setValue(item_quantity_st);
                                                                }

                                                                companyRef.child(post_key).child("Warehouses").child(warehouse_destination_id_st).child("Products").child(ds.getKey()).child("product_stock").setValue(total_stock_st);



                                                                //Register Kardex Operation
                                                                Random rand = new Random(); //instance of random class
                                                                int upperbound = 9999;
                                                                //generate random values from 0-24
                                                                int int_random = rand.nextInt(upperbound);

                                                                Long tsLong = (System.currentTimeMillis()/1000)+int_random;
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

                                                                companyRef.child(post_key).child("Kardex").child(timestamp).child("product_id").setValue(ds.getKey());
                                                                companyRef.child(post_key).child("Kardex").child(timestamp).child("product_price").setValue(product_price);
                                                                companyRef.child(post_key).child("Kardex").child(timestamp).child("warehouse_origin_id").setValue("chain");
                                                                companyRef.child(post_key).child("Kardex").child(timestamp).child("warehouse_destination_id").setValue(warehouse_destination_id_st);
                                                                companyRef.child(post_key).child("Kardex").child(timestamp).child("stock").setValue(item_quantity);
                                                                companyRef.child(post_key).child("Kardex").child(timestamp).child("operation_date").setValue(saveCurrentDate);
                                                                companyRef.child(post_key).child("Kardex").child(timestamp).child("operation_time").setValue(saveCurrentTime);
                                                                companyRef.child(post_key).child("Kardex").child(timestamp).child("operation_day").setValue(day+"");
                                                                companyRef.child(post_key).child("Kardex").child(timestamp).child("operation_month").setValue(month+"");
                                                                companyRef.child(post_key).child("Kardex").child(timestamp).child("operation_year").setValue(year+"");
                                                                companyRef.child(post_key).child("Kardex").child(timestamp).child("operation_type").setValue("purchase");
                                                                companyRef.child(post_key).child("Kardex").child(timestamp).child("timestamp").setValue(ServerValue.TIMESTAMP);

                                                                Toasty.success(PurchaseOrdersListActivity.this, "Actualizado", Toast.LENGTH_LONG).show();
                                                                dialog.dismiss();


                                                            } else {

                                                                if (rdLate.isChecked()) {
                                                                    companyRef.child(post_key).child("Purchased Orders").child(postKey).child("purchase_order_state").setValue("ready_delayed");
                                                                }
                                                                if (rdOnTime.isChecked()) {
                                                                    companyRef.child(post_key).child("Purchased Orders").child(postKey).child("purchase_order_state").setValue("ready");
                                                                }

                                                                companyRef.child(post_key).child("Warehouses").child(warehouse_destination_id_st).child("Products").child(ds.getKey()).child("product_stock").setValue(item_quantity);
                                                                companyRef.child(post_key).child("Warehouses").child(warehouse_destination_id_st).child("Products").child(ds.getKey()).child("company_id").setValue(post_key);
                                                                companyRef.child(post_key).child("Warehouses").child(warehouse_destination_id_st).child("Products").child(ds.getKey()).child("product_currency").setValue(item_currency_st);
                                                                companyRef.child(post_key).child("Warehouses").child(warehouse_destination_id_st).child("Products").child(ds.getKey()).child("product_description").setValue(item_description_st);
                                                                companyRef.child(post_key).child("Warehouses").child(warehouse_destination_id_st).child("Products").child(ds.getKey()).child("product_image").setValue(item_image_st);
                                                                companyRef.child(post_key).child("Warehouses").child(warehouse_destination_id_st).child("Products").child(ds.getKey()).child("product_measure").setValue(item_measure_st);
                                                                companyRef.child(post_key).child("Warehouses").child(warehouse_destination_id_st).child("Products").child(ds.getKey()).child("product_name").setValue(item_name_st);
                                                                companyRef.child(post_key).child("Warehouses").child(warehouse_destination_id_st).child("Products").child(ds.getKey()).child("product_price").setValue(item_price_st);
                                                                companyRef.child(post_key).child("Warehouses").child(warehouse_destination_id_st).child("Products").child(ds.getKey()).child("product_stock").setValue(item_quantity);
                                                                companyRef.child(post_key).child("Warehouses").child(warehouse_destination_id_st).child("Products").child(ds.getKey()).child("timestamp").setValue(ServerValue.TIMESTAMP);


                                                                //Kardex
                                                                //Register Kardex Operation
                                                                Random rand = new Random(); //instance of random class
                                                                int upperbound = 9999;
                                                                //generate random values from 0-24
                                                                int int_random = rand.nextInt(upperbound);

                                                                Long tsLong = (System.currentTimeMillis()/1000)+int_random;
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

                                                                companyRef.child(post_key).child("Kardex").child(timestamp).child("product_id").setValue(ds.getKey());
                                                                companyRef.child(post_key).child("Kardex").child(timestamp).child("product_price").setValue(item_price_st);
                                                                companyRef.child(post_key).child("Kardex").child(timestamp).child("warehouse_origin_id").setValue("chain");
                                                                companyRef.child(post_key).child("Kardex").child(timestamp).child("warehouse_destination_id").setValue(warehouse_destination_id_st);
                                                                companyRef.child(post_key).child("Kardex").child(timestamp).child("stock").setValue(item_quantity);
                                                                companyRef.child(post_key).child("Kardex").child(timestamp).child("operation_date").setValue(saveCurrentDate);
                                                                companyRef.child(post_key).child("Kardex").child(timestamp).child("operation_time").setValue(saveCurrentTime);
                                                                companyRef.child(post_key).child("Kardex").child(timestamp).child("operation_day").setValue(day+"");
                                                                companyRef.child(post_key).child("Kardex").child(timestamp).child("operation_month").setValue(month+"");
                                                                companyRef.child(post_key).child("Kardex").child(timestamp).child("operation_year").setValue(year+"");
                                                                companyRef.child(post_key).child("Kardex").child(timestamp).child("operation_type").setValue("purchase");
                                                                companyRef.child(post_key).child("Kardex").child(timestamp).child("timestamp").setValue(ServerValue.TIMESTAMP);

                                                                Toasty.success(PurchaseOrdersListActivity.this, "Actualizado", Toast.LENGTH_LONG).show();
                                                                dialog.dismiss();


                                                            }
                                                        }

                                                        @Override
                                                        public void onCancelled(DatabaseError databaseError) {

                                                        }
                                                    });

                                                    Toast.makeText(PurchaseOrdersListActivity.this, "warehouse_id: "+warehouse_destination_id, Toast.LENGTH_SHORT).show();
                                                }


                                            }

                                            @Override
                                            public void onCancelled(DatabaseError databaseError) {

                                            }
                                        });

                                    }
                                });

                                companyRef.child(post_key).child("My Suppliers").child(viewHolder.my_purchase_order_supplier_id).addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        String supplier_name = dataSnapshot.child("supplier_name").getValue().toString();

                                        txtSupplierName.setText(supplier_name);
                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {

                                    }
                                });

                                recyclerView2 = finance_method.findViewById(R.id.recyclerView2);
                                recyclerView2.setHasFixedSize(true);
                                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(PurchaseOrdersListActivity.this);
                                linearLayoutManager.setReverseLayout(true);
                                linearLayoutManager.setStackFromEnd(true);
                                recyclerView2.setLayoutManager(linearLayoutManager);

                                showPurchaseOrderItems();

                                dialog.setView(finance_method);
                                dialog.show();
                            }

                            private void showPurchaseOrderItems() {
                                Query query = companyRef.child(post_key).child("Purchased Orders").child(postKey).child("Items").orderByChild("timestamp");
                                FirebaseRecyclerAdapter<SupplierProductsModel, SupplierProductsViewHolder> firebaseRecyclerAdapter1 = new FirebaseRecyclerAdapter<SupplierProductsModel, SupplierProductsViewHolder>
                                        (SupplierProductsModel.class,R.layout.purchase_order_item_ready_item,SupplierProductsViewHolder.class,query) {
                                    @Override
                                    protected void populateViewHolder(final SupplierProductsViewHolder viewHolder1, SupplierProductsModel model, int position1) {
                                        final String postKey = getRef(position1).getKey();
                                        viewHolder1.setItem_description(model.getItem_description());
                                        viewHolder1.setItem_name(model.getItem_name());
                                        viewHolder1.setItem_price(model.getItem_price());
                                        viewHolder1.setItem_quantity(model.getItem_quantity());
                                        viewHolder1.setItem_total(model.getItem_total());

                                        viewHolder1.txtItemName.setText(viewHolder1.my_item_name);
                                        viewHolder1.txtQuantity.setText(viewHolder1.my_item_quantity);
                                        viewHolder1.txtPrice.setText(viewHolder1.my_item_price);
                                        viewHolder1.txtTotal.setText(viewHolder1.my_item_total);

                                        companyRef.child(post_key).child("Purchased Orders").child(purchase_order_id).child("Items").child(postKey).addValueEventListener(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                if (dataSnapshot.hasChild("warehouse_destination_id")) {
                                                    String warehouse_destination_id = dataSnapshot.child("warehouse_destination_id").getValue().toString();
                                                    companyRef.child(post_key).child("Warehouses").child(warehouse_destination_id).addValueEventListener(new ValueEventListener() {
                                                        @Override
                                                        public void onDataChange(DataSnapshot dataSnapshot) {
                                                            String warehouse_name = dataSnapshot.child("warehouse_name").getValue().toString();
                                                            viewHolder1.txtWarehouseName.setText(warehouse_name);
                                                        }

                                                        @Override
                                                        public void onCancelled(DatabaseError databaseError) {

                                                        }
                                                    });


                                                }
                                            }

                                            @Override
                                            public void onCancelled(DatabaseError databaseError) {

                                            }
                                        });

                                        viewHolder1.btnActionButton.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                warehouse_dialog = new AlertDialog.Builder(PurchaseOrdersListActivity.this).create();

                                                LayoutInflater inflater = LayoutInflater.from(PurchaseOrdersListActivity.this);
                                                View finance_method = inflater.inflate(R.layout.warehouses_dialog,null);

                                                recyclerView1 = finance_method.findViewById(R.id.recyclerView1);

                                                item_id = postKey;

                                                recyclerView1.setHasFixedSize(true);
                                                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(PurchaseOrdersListActivity.this);
                                                linearLayoutManager.setReverseLayout(false);
                                                linearLayoutManager.setStackFromEnd(false);
                                                recyclerView1.setLayoutManager(linearLayoutManager);


                                                showWareHouses();

                                                warehouse_dialog.setView(finance_method);
                                                warehouse_dialog.show();
                                            }

                                            private void showWareHouses() {
                                                Query query = companyRef.child(post_key).child("Warehouses").orderByChild("timestamp");
                                                FirebaseRecyclerAdapter<WarehousesModel, WarehousesViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<WarehousesModel, WarehousesViewHolder>
                                                        (WarehousesModel.class,R.layout.warehouse_item,WarehousesViewHolder.class,query) {
                                                    @Override
                                                    protected void populateViewHolder(WarehousesViewHolder viewHolder, WarehousesModel model1, int position2) {
                                                        final String postKey = getRef(position2).getKey();
                                                        viewHolder.setWarehouse_destination(model1.getWarehouse_destination());
                                                        viewHolder.setWarehouse_name(model1.getWarehouse_name());

                                                        viewHolder.txtWarehouseName.setText(viewHolder.ware_name);

                                                        if (viewHolder.ware_destiny.equals("products")) {
                                                            viewHolder.txtWarehouseDestination.setText("Destino: Productos Terminados");
                                                        }
                                                        if (viewHolder.ware_destiny.equals("materials")) {
                                                            viewHolder.txtWarehouseDestination.setText("Destino: Materiales e Insumos");
                                                        }

                                                        viewHolder.btnManageWarehouse.setOnClickListener(new View.OnClickListener() {
                                                            @Override
                                                            public void onClick(View v) {
                                                                companyRef.child(post_key).child("Purchased Orders").child(purchase_order_id).child("Items").child(item_id).child("warehouse_destination_id").setValue(postKey);
                                                                warehouse_dialog.dismiss();
                                                            }
                                                        });
                                                    }
                                                };
                                                recyclerView1.setAdapter(firebaseRecyclerAdapter);
                                            }
                                        });

                                    }
                                };
                                recyclerView2.setAdapter(firebaseRecyclerAdapter1);
                            }
                        });

                        btnReturned.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {


                                companyRef.child(post_key).child("Purchased Orders").child(postKey).child("Items").addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        for (final DataSnapshot ds : dataSnapshot.getChildren()) {
                                            companyRef.child(post_key).child("Purchased Items").child(ds.getKey()).child("Suppliers").child(viewHolder.my_purchase_order_supplier_id).child("Returned").child(postKey).setValue(postKey);
                                        }
                                        companyRef.child(post_key).child("Purchased Orders").child(postKey).child("purchase_order_state").setValue("returned");
                                        Toasty.success(PurchaseOrdersListActivity.this, "Actualizado", Toast.LENGTH_LONG).show();
                                        dialog.dismiss();
                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {

                                    }
                                });


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

    public static class SupplierProductsViewHolder extends RecyclerView.ViewHolder {

        View mView;
        String my_item_description,my_item_name,my_item_price,my_item_quantity,my_item_total;
        TextView txtItemName,txtPrice,txtQuantity,txtTotal,txtWarehouseName;
        ImageView btnActionButton;

        public SupplierProductsViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;

            txtItemName = mView.findViewById(R.id.txtItemName);
            txtPrice = mView.findViewById(R.id.txtPrice);
            txtQuantity = mView.findViewById(R.id.txtQuantity);
            txtTotal = mView.findViewById(R.id.txtTotal);
            btnActionButton = mView.findViewById(R.id.btnActionButton);
            txtWarehouseName = mView.findViewById(R.id.txtWarehouseName);
        }
        public void setItem_description(String item_description) {
            my_item_description = item_description;
        }

        public void setItem_name(String item_name) {
            my_item_name = item_name;
        }
        public void setItem_price(String item_price) {
            my_item_price = item_price;
        }

        public void setItem_quantity(String item_quantity) {
            my_item_quantity = item_quantity;
        }

        public void setItem_total(String item_total) {
            my_item_total = item_total;
        }
    }

    public static class PurchaseOrdersViewHolder extends RecyclerView.ViewHolder {
        View mView;
        String my_operation_date,my_operation_day,my_operation_month,my_operation_time,my_operation_year,my_purchase_order_id,my_purchase_order_supplier_id,my_purchase_order_total_amount,my_purchase_order_state;
        TextView txtCode,txtSupplier,txtDate,txtTotal;
        ImageView btnActionButton;

        public PurchaseOrdersViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;

            txtCode = mView.findViewById(R.id.txtCode);
            txtSupplier = mView.findViewById(R.id.txtSupplier);
            txtDate = mView.findViewById(R.id.txtDate);
            txtTotal = mView.findViewById(R.id.txtTotal);
            btnActionButton = mView.findViewById(R.id.btnActionButton);
        }

        public void setOperation_date(String operation_date) {
            my_operation_date = operation_date;
        }

        public void setOperation_day(String operation_day) {
            my_operation_day = operation_day;
        }

        public void setOperation_month(String operation_month) {
            my_operation_month = operation_month;
        }

        public void setOperation_time(String operation_time) {
            my_operation_time = operation_time;
        }

        public void setOperation_year(String operation_year) {
            my_operation_year = operation_year;
        }
        public void setPurchase_order_id(String purchase_order_id) {
            my_purchase_order_id = purchase_order_id;
        }
        public void setPurchase_order_supplier_id(String purchase_order_supplier_id) {
            my_purchase_order_supplier_id = purchase_order_supplier_id;
        }

        public void setPurchase_order_total_amount(String purchase_order_total_amount) {
            my_purchase_order_total_amount = purchase_order_total_amount;
        }
        public void setPurchase_order_state(String purchase_order_state) {
            my_purchase_order_state = purchase_order_state;
        }
    }
}
