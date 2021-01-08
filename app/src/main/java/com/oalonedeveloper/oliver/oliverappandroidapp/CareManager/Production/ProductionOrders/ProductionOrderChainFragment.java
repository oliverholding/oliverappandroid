package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Production.ProductionOrders;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
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

import es.dmoral.toasty.Toasty;


public class ProductionOrderChainFragment extends Fragment {

    DatabaseReference companyRef;
    String post_key,product_key,item_quantity, chain_id,the_code,the_currency,the_description,the_measure,the_price,the_image,the_name;
    RecyclerView recyclerView;
    AlertDialog dialog_list;
    RecyclerView recyclerView1;
    DecimalFormat decimalFormat;
    int day,month,year;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_production_order_chain, container, false);

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
        Query query = companyRef.child(post_key).child("Production Chain").orderByChild("timestamp");
        FirebaseRecyclerAdapter<ChainModel, OrderChainViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<ChainModel, OrderChainViewHolder>
                (ChainModel.class,R.layout.product_order_chain_item, OrderChainViewHolder.class, query) {
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
                if (viewHolder.my_state.equals("production")) {
                    viewHolder.txtState.setText("En Producción");
                }
                if (viewHolder.my_state.equals("stop")) {
                    viewHolder.txtState.setText("Detenido");
                }
                if (viewHolder.my_state.equals("ready")) {
                    viewHolder.txtState.setText("Listo");
                    viewHolder.btnActionButton.setEnabled(false);
                    viewHolder.btnActionButton.setVisibility(View.GONE);
                }

                viewHolder.btnActionButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showChainStateDialog();
                    }

                    private void showChainStateDialog() {
                        final AlertDialog dialog = new AlertDialog.Builder(getActivity()).create();

                        LayoutInflater inflater = LayoutInflater.from(getActivity());
                        View finance_method = inflater.inflate(R.layout.chain_state_dialog,null);

                        ImageButton btnProduction,btnStop,btnReady;

                        btnProduction = finance_method.findViewById(R.id.btnProduction);
                        btnStop = finance_method.findViewById(R.id.btnStop);
                        btnReady = finance_method.findViewById(R.id.btnReady);

                        btnProduction.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                companyRef.child(post_key).child("Production Chain").child(postKey).child("state").setValue("production");
                                Toasty.success(getActivity(), "Actualizado", Toast.LENGTH_LONG).show();
                                dialog.dismiss();
                            }
                        });

                        btnStop.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                companyRef.child(post_key).child("Production Chain").child(postKey).child("state").setValue("stop");
                                Toasty.success(getActivity(), "Actualizado", Toast.LENGTH_LONG).show();
                                dialog.dismiss();
                            }
                        });

                        btnReady.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                showWarehouseDialog();

                            }

                            private void showWarehouseDialog() {
                                dialog_list = new AlertDialog.Builder(getActivity()).create();

                                LayoutInflater inflater = LayoutInflater.from(getActivity());
                                View finance_method = inflater.inflate(R.layout.warehouses_dialog,null);

                                recyclerView1 = finance_method.findViewById(R.id.recyclerView1);

                                recyclerView1.setHasFixedSize(true);
                                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                                linearLayoutManager.setReverseLayout(false);
                                linearLayoutManager.setStackFromEnd(false);
                                recyclerView1.setLayoutManager(linearLayoutManager);

                                product_key = viewHolder.my_product_id;
                                item_quantity = viewHolder.my_product_quantity_production;
                                chain_id = postKey;
                                the_code = viewHolder.my_code;
                                the_currency = viewHolder.my_product_currency;
                                the_description = viewHolder.my_product_description;
                                the_measure = viewHolder.my_product_measure;
                                the_price = viewHolder.my_product_price;
                                the_image = viewHolder.my_product_image;
                                the_name = viewHolder.my_product_name;

                                showWareHouses();

                                dialog_list.setView(finance_method);
                                dialog_list.show();

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


                                        viewHolder.btnManageWarehouse.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {

                                                companyRef.child(post_key).child("Warehouses").child(postKey).child("Products").child(product_key).addListenerForSingleValueEvent(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                                        if (dataSnapshot.exists()) {
                                                            String product_stock = dataSnapshot.child("product_stock").getValue().toString();
                                                            String product_price = dataSnapshot.child("product_price").getValue().toString();
                                                            double current_stock = Double.parseDouble(product_stock);
                                                            double item_quantity_db = Double.parseDouble(item_quantity);

                                                            double total_stock = current_stock+item_quantity_db;
                                                            String total_stock_st = decimalFormat.format(total_stock);

                                                            companyRef.child(post_key).child("Warehouses").child(postKey).child("Products").child(product_key).child("product_stock").setValue(total_stock_st);

                                                            companyRef.child(post_key).child("Production Chain").child(chain_id).child("state").setValue("ready");

                                                            //Register Kardex Operation

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
                                                            companyRef.child(post_key).child("Kardex").child(timestamp).child("warehouse_origin_id").setValue("chain");
                                                            companyRef.child(post_key).child("Kardex").child(timestamp).child("warehouse_destination_id").setValue(postKey);
                                                            companyRef.child(post_key).child("Kardex").child(timestamp).child("stock").setValue(item_quantity);
                                                            companyRef.child(post_key).child("Kardex").child(timestamp).child("operation_date").setValue(saveCurrentDate);
                                                            companyRef.child(post_key).child("Kardex").child(timestamp).child("operation_time").setValue(saveCurrentTime);
                                                            companyRef.child(post_key).child("Kardex").child(timestamp).child("operation_day").setValue(day+"");
                                                            companyRef.child(post_key).child("Kardex").child(timestamp).child("operation_month").setValue(month+"");
                                                            companyRef.child(post_key).child("Kardex").child(timestamp).child("operation_year").setValue(year+"");
                                                            companyRef.child(post_key).child("Kardex").child(timestamp).child("operation_type").setValue("purchase");
                                                            companyRef.child(post_key).child("Kardex").child(timestamp).child("timestamp").setValue(ServerValue.TIMESTAMP);

                                                            Toasty.success(getActivity(), "Actualizado", Toast.LENGTH_LONG).show();
                                                            dialog.dismiss();
                                                            dialog_list.dismiss();

                                                        } else {

                                                            companyRef.child(post_key).child("Warehouses").child(postKey).child("Products").child(product_key).child("product_stock").setValue(item_quantity);
                                                            companyRef.child(post_key).child("Warehouses").child(postKey).child("Products").child(product_key).child("company_id").setValue(post_key);
                                                            companyRef.child(post_key).child("Warehouses").child(postKey).child("Products").child(product_key).child("product_currency").setValue(the_currency);
                                                            companyRef.child(post_key).child("Warehouses").child(postKey).child("Products").child(product_key).child("product_description").setValue(the_description);
                                                            companyRef.child(post_key).child("Warehouses").child(postKey).child("Products").child(product_key).child("product_image").setValue(the_image);
                                                            companyRef.child(post_key).child("Warehouses").child(postKey).child("Products").child(product_key).child("product_measure").setValue(the_measure);
                                                            companyRef.child(post_key).child("Warehouses").child(postKey).child("Products").child(product_key).child("product_name").setValue(the_name);
                                                            companyRef.child(post_key).child("Warehouses").child(postKey).child("Products").child(product_key).child("product_price").setValue(the_price);
                                                            companyRef.child(post_key).child("Warehouses").child(postKey).child("Products").child(product_key).child("product_stock").setValue(item_quantity);
                                                            companyRef.child(post_key).child("Warehouses").child(postKey).child("Products").child(product_key).child("timestamp").setValue(ServerValue.TIMESTAMP);


                                                            //Kardex

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
                                                            companyRef.child(post_key).child("Kardex").child(timestamp).child("product_price").setValue(the_price);
                                                            companyRef.child(post_key).child("Kardex").child(timestamp).child("warehouse_origin_id").setValue("chain");
                                                            companyRef.child(post_key).child("Kardex").child(timestamp).child("warehouse_destination_id").setValue(postKey);
                                                            companyRef.child(post_key).child("Kardex").child(timestamp).child("stock").setValue(item_quantity);
                                                            companyRef.child(post_key).child("Kardex").child(timestamp).child("operation_date").setValue(saveCurrentDate);
                                                            companyRef.child(post_key).child("Kardex").child(timestamp).child("operation_time").setValue(saveCurrentTime);
                                                            companyRef.child(post_key).child("Kardex").child(timestamp).child("operation_day").setValue(day+"");
                                                            companyRef.child(post_key).child("Kardex").child(timestamp).child("operation_month").setValue(month+"");
                                                            companyRef.child(post_key).child("Kardex").child(timestamp).child("operation_year").setValue(year+"");
                                                            companyRef.child(post_key).child("Kardex").child(timestamp).child("operation_type").setValue("purchase");
                                                            companyRef.child(post_key).child("Kardex").child(timestamp).child("timestamp").setValue(ServerValue.TIMESTAMP);

                                                            Toasty.success(getActivity(), "Actualizado", Toast.LENGTH_LONG).show();
                                                            dialog.dismiss();
                                                            dialog_list.dismiss();


                                                        }

                                                    }

                                                    @Override
                                                    public void onCancelled(DatabaseError databaseError) {

                                                    }
                                                });


                                            }

                                        });

                                    }
                                };
                                recyclerView1.setAdapter(firebaseRecyclerAdapter);
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
