package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Production.ProductionOrders;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
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
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Logistic.Storage.WarehousesModel;
import com.oalonedeveloper.oliver.oliverappandroidapp.Companies.ProductsModel;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;
import es.dmoral.toasty.Toasty;

public class ProductionOrderProductDetailActivity extends AppCompatActivity {

    DatabaseReference companyRef;
    String post_key,product_id,product_name,product_image, warehousePostKey,code,product_currency,product_description,product_measure,product_price;
    TextView txtProductName;
    CircleImageView imgProduct;
    ImageView btnAddProduct;
    AlertDialog dialog_list,dialog_list1;
    RecyclerView recyclerView1,recyclerView2,recyclerView,recyclerView3;
    Button btnRegister;
    double quantity_to_produce;
    DecimalFormat decimalFormat = new DecimalFormat();
    Button btnProduce;
    int day,month,year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_production_order_product_detail);

        post_key = getIntent().getExtras().getString("post_key");
        product_id = getIntent().getExtras().getString("product_id");
        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");

        txtProductName = findViewById(R.id.txtProductName);
        imgProduct = findViewById(R.id.imgProduct);
        btnAddProduct = findViewById(R.id.btnAddProduct);
        btnRegister = findViewById(R.id.btnRegister);
        decimalFormat = new DecimalFormat("0.00");

        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        Date date= new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        day = cal.get(Calendar.DAY_OF_MONTH);
        month = cal.get(Calendar.MONTH)+1;
        year = cal.get(Calendar.YEAR);

        companyRef.child(post_key).child("My Products").child(product_id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                product_name = dataSnapshot.child("product_name").getValue().toString();
                product_image = dataSnapshot.child("product_image").getValue().toString();
                code = dataSnapshot.child("code").getValue().toString();
                product_currency = dataSnapshot.child("product_currency").getValue().toString();
                product_description = dataSnapshot.child("product_description").getValue().toString();
                product_measure = dataSnapshot.child("product_measure").getValue().toString();
                product_price = dataSnapshot.child("product_price").getValue().toString();


                txtProductName.setText(product_name);
                Picasso.with(ProductionOrderProductDetailActivity.this).load(product_image).fit().into(imgProduct);

                showProductMaterials();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        btnAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showWarehousesDialog();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showProductionDialog();
            }
        });
    }

    private void showProductionDialog() {
        final AlertDialog dialog = new AlertDialog.Builder(ProductionOrderProductDetailActivity.this).create();

        LayoutInflater inflater = LayoutInflater.from(ProductionOrderProductDetailActivity.this);
        View finance_method = inflater.inflate(R.layout.send_production_order_dialog,null);

        final EditText edtQuantity;
        TextView txtMessage;

        recyclerView3 = finance_method.findViewById(R.id.recyclerView3);
        edtQuantity = finance_method.findViewById(R.id.edtQuantity);
        btnProduce = finance_method.findViewById(R.id.btnProduce);
        txtMessage = finance_method.findViewById(R.id.txtMessage);

        txtMessage.setText("¿Cuantas unidades de "+product_name+" vas a producir?");

        edtQuantity.setText("1");
        quantity_to_produce = 1;

        edtQuantity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(edtQuantity.getText().toString())) {
                    quantity_to_produce = 1;
                } else {
                    quantity_to_produce = Double.parseDouble(edtQuantity.getText().toString());
                    showProductionMaterials();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        btnProduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calForDate = Calendar.getInstance();
                SimpleDateFormat currentDate = new SimpleDateFormat("dd-MM-yyyy");
                final String saveCurrentDate = currentDate.format(calForDate.getTime());

                Calendar calForTime = Calendar.getInstance();
                SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss");
                final String saveCurrentTime = currentTime.format(calForTime.getTime());
                Long tsLong = System.currentTimeMillis()/1000;
                final String timestamp = tsLong.toString();

                companyRef.child(post_key).child("Production Chain").child(timestamp).child("product_id").setValue(product_id);
                companyRef.child(post_key).child("Production Chain").child(timestamp).child("product_name").setValue(product_name);
                companyRef.child(post_key).child("Production Chain").child(timestamp).child("product_image").setValue(product_image);
                companyRef.child(post_key).child("Production Chain").child(timestamp).child("code").setValue(code);
                companyRef.child(post_key).child("Production Chain").child(timestamp).child("product_currency").setValue(product_currency);
                companyRef.child(post_key).child("Production Chain").child(timestamp).child("product_description").setValue(product_description);
                companyRef.child(post_key).child("Production Chain").child(timestamp).child("product_measure").setValue(product_measure);
                companyRef.child(post_key).child("Production Chain").child(timestamp).child("product_price").setValue(product_price);
                companyRef.child(post_key).child("Production Chain").child(timestamp).child("product_quantity_production").setValue(edtQuantity.getText().toString());
                companyRef.child(post_key).child("Production Chain").child(timestamp).child("state").setValue("production");
                companyRef.child(post_key).child("Production Chain").child(timestamp).child("date").setValue(saveCurrentDate);
                companyRef.child(post_key).child("Production Chain").child(timestamp).child("time").setValue(saveCurrentTime);
                companyRef.child(post_key).child("Production Chain").child(timestamp).child("timestamp").setValue(ServerValue.TIMESTAMP);

                companyRef.child(post_key).child("My Products").child(product_id).child("Production Items").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            Map<String, Object> map = (Map<String, Object>) ds.getValue();
                            Object item_quantity = map.get("item_quantity");
                            Object item_id = map.get("item_id");
                            final Object warehouse_id = map.get("warehouse_id");
                            String my_item_quantity = (String) item_quantity;
                            final String my_item_id = (String) item_id;
                            final String my_warehouse_id = (String) warehouse_id;

                            double quantity = Double.parseDouble(edtQuantity.getText().toString());
                            double item_quantity_db = Double.parseDouble(my_item_quantity);
                            final double total_q = quantity*item_quantity_db;
                            final String total_q_st = decimalFormat.format(total_q);

                            companyRef.child(post_key).child("Warehouses").child(my_warehouse_id).child("Products").child(my_item_id).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    String product_stock = dataSnapshot.child("product_stock").getValue().toString();
                                    String product_price = dataSnapshot.child("product_price").getValue().toString();

                                    double product_stock_db = Double.parseDouble(product_stock);
                                    double result = product_stock_db-total_q;
                                    String result_st = decimalFormat.format(result);

                                    companyRef.child(post_key).child("Warehouses").child(my_warehouse_id).child("Products").child(my_item_id).child("product_stock").setValue(result_st);

                                    Long tsLong = System.currentTimeMillis()/1000;
                                    final String timestamp = tsLong.toString();
                                    Random rand = new Random(); //instance of random class
                                    int upperbound = 999;
                                    //generate random values from 0-24
                                    int int_random = rand.nextInt(upperbound);
                                    String my_timestamp = timestamp+""+int_random;

                                    companyRef.child(post_key).child("Kardex").child(my_timestamp).child("product_id").setValue(my_item_id);
                                    companyRef.child(post_key).child("Kardex").child(my_timestamp).child("product_price").setValue(product_price);
                                    companyRef.child(post_key).child("Kardex").child(my_timestamp).child("warehouse_origin_id").setValue(my_warehouse_id);
                                    companyRef.child(post_key).child("Kardex").child(my_timestamp).child("warehouse_destination_id").setValue("production");
                                    companyRef.child(post_key).child("Kardex").child(my_timestamp).child("stock").setValue(total_q_st);
                                    companyRef.child(post_key).child("Kardex").child(my_timestamp).child("operation_date").setValue(saveCurrentDate);
                                    companyRef.child(post_key).child("Kardex").child(my_timestamp).child("operation_time").setValue(saveCurrentTime);
                                    companyRef.child(post_key).child("Kardex").child(my_timestamp).child("operation_day").setValue(day+"");
                                    companyRef.child(post_key).child("Kardex").child(my_timestamp).child("operation_month").setValue(month+"");
                                    companyRef.child(post_key).child("Kardex").child(my_timestamp).child("operation_year").setValue(year+"");
                                    companyRef.child(post_key).child("Kardex").child(my_timestamp).child("operation_type").setValue("production");
                                    companyRef.child(post_key).child("Kardex").child(my_timestamp).child("timestamp").setValue(ServerValue.TIMESTAMP);

                                    Toasty.success(ProductionOrderProductDetailActivity.this, "Enviado a la cadena de producción", Toast.LENGTH_LONG).show();
                                    dialog.dismiss();

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

            }
        });


        recyclerView3.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(false);
        linearLayoutManager.setStackFromEnd(false);
        recyclerView3.setLayoutManager(linearLayoutManager);

        showProductionMaterials();

        dialog.setView(finance_method);
        dialog.show();

    }

    private void showProductionMaterials() {
        Query query = companyRef.child(post_key).child("My Products").child(product_id).child("Production Items").orderByChild("timestamp");
        FirebaseRecyclerAdapter<ProductMaterialsModel, ProductMaterialsViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<ProductMaterialsModel, ProductMaterialsViewHolder>
                (ProductMaterialsModel.class, R.layout.material_production_item,ProductMaterialsViewHolder.class,query) {
            @Override
            protected void populateViewHolder(final ProductMaterialsViewHolder viewHolder, ProductMaterialsModel model, int position) {
                final String postKey = getRef(position).getKey();
                viewHolder.setItem_id(model.getItem_id());
                viewHolder.setItem_image(model.getItem_image());
                viewHolder.setItem_measure(model.getItem_measure());
                viewHolder.setItem_name(model.getItem_name());
                viewHolder.setItem_price(model.getItem_price());
                viewHolder.setItem_quantity(model.getItem_quantity());

                viewHolder.txtProductName.setText(viewHolder.my_item_name);
                viewHolder.txtProductQuantity.setText(viewHolder.my_item_quantity);

                double item_q = Double.parseDouble(viewHolder.my_item_quantity);

                final double total_q_to_use = item_q*quantity_to_produce;
                final String total_q_to_use_st = decimalFormat.format(total_q_to_use);
                viewHolder.txtProductQuantity.setText(total_q_to_use_st);

                companyRef.child(post_key).child("My Products").child(product_id).child("Production Items").child(postKey).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        final String warehouse_id = dataSnapshot.child("warehouse_id").getValue().toString();

                        companyRef.child(post_key).child("Warehouses").child(warehouse_id).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                String warehouse_name = dataSnapshot.child("warehouse_name").getValue().toString();

                                viewHolder.txtWarehouseName.setText(warehouse_name);

                                companyRef.child(post_key).child("Warehouses").child(warehouse_id).child("Products").child(postKey).addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {

                                        String product_stock = dataSnapshot.child("product_stock").getValue().toString();
                                        viewHolder.txtWarehouseStock.setText(product_stock);

                                        double stock = Double.parseDouble(product_stock);

                                        if (stock < total_q_to_use) {
                                            btnProduce.setText("STOCK INUFICIENTE");
                                            btnProduce.setBackgroundColor(Color.RED);
                                            btnProduce.setEnabled(false);
                                        } else {
                                            //btnProduce.setText("EVNIAR A CADENA DE PRODUCCIÓN");
                                            //btnProduce.setEnabled(true);
                                            //btnProduce.setBackgroundResource(R.drawable.gradient_start2);
                                        }


                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {

                                    }
                                });
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        };
        recyclerView3.setAdapter(firebaseRecyclerAdapter);
    }

    private void showProductMaterials() {
        Query query = companyRef.child(post_key).child("My Products").child(product_id).child("Production Items").orderByChild("timestamp");
        FirebaseRecyclerAdapter<ProductMaterialsModel, ProductMaterialsViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<ProductMaterialsModel, ProductMaterialsViewHolder>
                (ProductMaterialsModel.class, R.layout.product_material_item,ProductMaterialsViewHolder.class,query) {
            @Override
            protected void populateViewHolder(ProductMaterialsViewHolder viewHolder, ProductMaterialsModel model, int position) {
                final String postKey = getRef(position).getKey();
                viewHolder.setItem_id(model.getItem_id());
                viewHolder.setItem_image(model.getItem_image());
                viewHolder.setItem_measure(model.getItem_measure());
                viewHolder.setItem_name(model.getItem_name());
                viewHolder.setItem_price(model.getItem_price());
                viewHolder.setItem_quantity(model.getItem_quantity());

                Picasso.with(ProductionOrderProductDetailActivity.this).load(viewHolder.my_item_image).fit().into(viewHolder.imgProduct);
                viewHolder.txtProductName.setText(viewHolder.my_item_name);
                viewHolder.txtProductQuantity.setText(viewHolder.my_item_quantity);
                if (viewHolder.my_item_measure.equals("quantity")) {
                    viewHolder.txtProductMeasure.setText("Unidades");
                }

                viewHolder.btnActionButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        companyRef.child(post_key).child("My Products").child(product_id).child("Production Items").child(postKey).removeValue();
                    }
                });
            }
        };
        recyclerView.setAdapter(firebaseRecyclerAdapter);

    }

    private void showWarehousesDialog() {
        dialog_list = new AlertDialog.Builder(this).create();

        LayoutInflater inflater = LayoutInflater.from(this);
        View finance_method = inflater.inflate(R.layout.warehouses_dialog,null);

        TextView txtMessage;

        recyclerView1 = finance_method.findViewById(R.id.recyclerView1);
        txtMessage = finance_method.findViewById(R.id.txtMessage);

        txtMessage.setText("Selecciona el almacén donde se encuentran tus insumos");

        recyclerView1.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(false);
        linearLayoutManager.setStackFromEnd(false);
        recyclerView1.setLayoutManager(linearLayoutManager);

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
                        warehousePostKey = postKey;
                        showWarehouseProductsDialog();
                    }

                    private void showWarehouseProductsDialog() {
                        dialog_list1 = new AlertDialog.Builder(ProductionOrderProductDetailActivity.this).create();

                        LayoutInflater inflater = LayoutInflater.from(ProductionOrderProductDetailActivity.this);
                        View finance_method = inflater.inflate(R.layout.add_store_products_to_warehouse_dialog,null);

                        TextView txtTitle,txtMessage;
                        Button btnRegisterNewProduct;

                        txtTitle = finance_method.findViewById(R.id.txtTitle);
                        txtMessage = finance_method.findViewById(R.id.txtMessage);
                        btnRegisterNewProduct = finance_method.findViewById(R.id.btnRegisterNewProduct);
                        recyclerView2 = finance_method.findViewById(R.id.recyclerView);

                        txtTitle.setText("MIS EXISTENCIAS EN ALMACÉN: "+viewHolder.ware_name);
                        txtMessage.setText("Selecciona la existencia que servirá como insumo para la fabricación de "+product_name);

                        btnRegisterNewProduct.setVisibility(View.GONE);


                        recyclerView2.setHasFixedSize(true);
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ProductionOrderProductDetailActivity.this);
                        linearLayoutManager.setReverseLayout(false);
                        linearLayoutManager.setStackFromEnd(false);
                        recyclerView2.setLayoutManager(linearLayoutManager);

                        showWareHouseProducts();

                        dialog_list1.setView(finance_method);
                        dialog_list1.show();

                    }

                    private void showWareHouseProducts() {
                        Query query = companyRef.child(post_key).child("Warehouses").child(postKey).child("Products").orderByChild("timestamp");
                        FirebaseRecyclerAdapter<ProductsModel, CompanyProductsViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<ProductsModel, CompanyProductsViewHolder>
                                (ProductsModel.class,R.layout.product_bill_item, CompanyProductsViewHolder.class,query) {
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
                                viewHolder.txtProductPrice.setText(viewHolder.my_product_stock);
                                Picasso.with(ProductionOrderProductDetailActivity.this).load(viewHolder.my_product_image).fit().into(viewHolder.imgProduct);

                                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        addToMaterialListDialog();
                                    }

                                    private void addToMaterialListDialog() {
                                        final AlertDialog dialog = new AlertDialog.Builder(ProductionOrderProductDetailActivity.this).create();

                                        LayoutInflater inflater = LayoutInflater.from(ProductionOrderProductDetailActivity.this);
                                        View finance_method = inflater.inflate(R.layout.transfer_items_dialog,null);

                                        CircleImageView imgProductDialog;
                                        TextView txtProductName,txtMessage;
                                        final EditText edtQuantity;
                                        Button btnRegister;
                                        final RelativeLayout rootLayout;

                                        imgProductDialog = finance_method.findViewById(R.id.imgProduct);
                                        txtProductName = finance_method.findViewById(R.id.txtProductName);
                                        edtQuantity = finance_method.findViewById(R.id.edtQuantity);
                                        btnRegister = finance_method.findViewById(R.id.btnRegister);
                                        rootLayout = finance_method.findViewById(R.id.rootLayout);
                                        txtMessage = finance_method.findViewById(R.id.txtMessage);

                                        Picasso.with(ProductionOrderProductDetailActivity.this).load(viewHolder.my_product_image).fit().into(imgProductDialog);
                                        txtProductName.setText("Stock actual en Almacémn de "+viewHolder.my_product_name+": "+viewHolder.my_product_stock);
                                        txtMessage.setText("Ingresa la cantidad de "+viewHolder.my_product_name+" que necesitarás para producir 1 "+product_name);
                                        btnRegister.setText("REGISTRAR INSUMO");

                                        btnRegister.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                final double current_stock = Double.parseDouble(viewHolder.my_product_stock);
                                                final double stock_to_transfer = Double.parseDouble(edtQuantity.getText().toString());

                                                companyRef.child(post_key).child("My Products").child(product_id).child("Production Items").child(postKey).child("item_name").setValue(viewHolder.my_product_name);
                                                companyRef.child(post_key).child("My Products").child(product_id).child("Production Items").child(postKey).child("item_measure").setValue(viewHolder.my_product_measure);
                                                companyRef.child(post_key).child("My Products").child(product_id).child("Production Items").child(postKey).child("item_image").setValue(viewHolder.my_product_image);
                                                companyRef.child(post_key).child("My Products").child(product_id).child("Production Items").child(postKey).child("item_price").setValue(viewHolder.my_product_price);
                                                companyRef.child(post_key).child("My Products").child(product_id).child("Production Items").child(postKey).child("item_quantity").setValue(edtQuantity.getText().toString());
                                                companyRef.child(post_key).child("My Products").child(product_id).child("Production Items").child(postKey).child("item_id").setValue(postKey);
                                                companyRef.child(post_key).child("My Products").child(product_id).child("Production Items").child(postKey).child("warehouse_id").setValue(warehousePostKey);
                                                companyRef.child(post_key).child("My Products").child(product_id).child("Production Items").child(postKey).child("timestamp").setValue(ServerValue.TIMESTAMP);
                                                Toasty.success(ProductionOrderProductDetailActivity.this, "Registrado con éxito", Toast.LENGTH_LONG).show();
                                                dialog.dismiss();
                                                dialog_list1.dismiss();
                                                dialog_list.dismiss();

                                            }
                                        });

                                        dialog.setView(finance_method);
                                        dialog.show();
                                    }


                                });
                            }
                        };
                        recyclerView2.setAdapter(firebaseRecyclerAdapter);
                    }


                });

            }
        };
        recyclerView1.setAdapter(firebaseRecyclerAdapter);
    }

    public static class ProductMaterialsViewHolder extends RecyclerView.ViewHolder {
        View mView;

        CircleImageView imgProduct;
        TextView txtProductName,txtProductQuantity,txtProductMeasure,txtWarehouseStock,txtWarehouseName;
        String my_item_name,my_item_measure,my_item_image,my_item_price,my_item_quantity,my_item_id;
        ImageView btnActionButton;

        public ProductMaterialsViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;

            imgProduct = mView.findViewById(R.id.imgProduct);
            txtProductName = mView.findViewById(R.id.txtProductName);
            txtProductQuantity = mView.findViewById(R.id.txtProductQuantity);
            txtProductMeasure = mView.findViewById(R.id.txtProductMeasure);
            btnActionButton = mView.findViewById(R.id.btnActionButton);
            txtWarehouseStock = mView.findViewById(R.id.txtWarehouseStock);
            txtWarehouseName = mView.findViewById(R.id.txtWarehouseName);
        }
        public void setItem_name(String item_name) {
            my_item_name = item_name;
        }

        public void setItem_measure(String item_measure) {
            my_item_measure = item_measure;
        }

        public void setItem_image(String item_image) {
            my_item_image = item_image;
        }

        public void setItem_price(String item_price) {
            my_item_price = item_price;
        }

        public void setItem_quantity(String item_quantity) {
            my_item_quantity = item_quantity;
        }

        public void setItem_id(String item_id) {
            my_item_id = item_id;
        }
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
