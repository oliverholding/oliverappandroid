package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Logistic.OrderProcessing;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.hbb20.CountryCodePicker;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Commercialization.BillsIssuing.BillItemsModel;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Commercialization.BillsIssuing.CreateBillActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Logistic.PurchaseOrder.PurchaseOrderActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Logistic.PurchaseOrder.PurchaseOrdersListActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.Companies.CompanyCustomersModel;
import com.oalonedeveloper.oliver.oliverappandroidapp.Companies.ProductsModel;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;
import es.dmoral.toasty.Toasty;

public class OrderProcessingActivity extends AppCompatActivity {

    CircleImageView imgCompanyProfile;
    TextView txtCompanyName,txtCompanyAddress,txtCurrentDate,txtOrderCode,txtCustomerName,txtPhone,txtEmail,txtAddress,txtTaxes,txtTotalAmount,txtSaleValue;
    DatabaseReference companyRef;
    String post_key,company_image,company_social_reason,company_address,timestamp,customer_id,address_value,total_amount_st,total_taxes_st,image_verification,current_time,currentPhotoPath,downloadUrl,customer_name,customerPostKey,customer_phone,customer_email,current_product_stock;
    DecimalFormat decimalFormat;
    int day,month,year;
    RecyclerView recyclerView,recyclerView2,recyclerView3;
    AlertDialog dialog,dialog_customers,dialog_list;
    Button btnAddProduct,btnRegisterPurchaseOrder;
    ImageView imgProduct;
    ProgressDialog loadingBar;
    Uri imageUri;
    StorageReference userProfileImageRef;
    final static int Gallery_Pick = 2;
    RelativeLayout rootLayout;
    double current_product_stock_db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_processing);

        imgCompanyProfile = findViewById(R.id.imgCompanyProfile);
        txtCompanyName = findViewById(R.id.txtCompanyName);
        txtCompanyAddress = findViewById(R.id.txtCompanyAddress);
        txtCurrentDate = findViewById(R.id.txtCurrentDate);
        txtOrderCode = findViewById(R.id.txtOrderCode);
        txtCustomerName = findViewById(R.id.txtCustomerName);
        txtPhone = findViewById(R.id.txtPhone);
        txtEmail = findViewById(R.id.txtEmail);
        txtAddress = findViewById(R.id.txtAddress);
        btnAddProduct = findViewById(R.id.btnAddProduct);
        txtTaxes = findViewById(R.id.txtTaxes);
        txtTotalAmount = findViewById(R.id.txtTotalAmount);
        txtSaleValue = findViewById(R.id.txtSaleValue);
        btnRegisterPurchaseOrder = findViewById(R.id.btnRegisterPurchaseOrder);
        rootLayout = findViewById(R.id.rootLayout);

        image_verification = "";
        customer_id = "";
        customer_name = "";

        post_key = getIntent().getExtras().getString("post_key");
        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");
        userProfileImageRef = FirebaseStorage.getInstance().getReference().child("Product Images");
        decimalFormat = new DecimalFormat("0.00");

        loadingBar = new ProgressDialog(this);

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

        Long tsLong = System.currentTimeMillis()/1000;
        timestamp = tsLong.toString();

        txtCurrentDate.setText("Fecha de emisión: "+day+"/"+month+"/"+year);
        txtOrderCode.setText("Nº: "+timestamp);


        companyRef.child(post_key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                company_image = dataSnapshot.child("company_image").getValue().toString();
                company_social_reason = dataSnapshot.child("company_social_reason").getValue().toString();
                company_address = dataSnapshot.child("company_address").getValue().toString();

                Picasso.with(OrderProcessingActivity.this).load(company_image).fit().into(imgCompanyProfile);
                txtCompanyName.setText(company_social_reason);
                txtCompanyAddress.setText(company_address);

                showListProducts();
                showTotalAmount();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        txtCustomerName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomersDialog();
            }
        });

        btnAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if (customer_name.equals("")) {
                   Snackbar.make(rootLayout, "Debes ingresar el nombre del cliente", Snackbar.LENGTH_LONG).show();

               }
               else {
                    showAddProductDialog();
                }

            }
        });

        txtAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddAddressDialog();
            }
        });

        btnRegisterPurchaseOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Long tsLong = System.currentTimeMillis()/1000;
                final String timestamp = tsLong.toString();
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

                companyRef.child(post_key).child("Orders Processing").child(timestamp).child("order_processing_id").setValue(timestamp);
                companyRef.child(post_key).child("Orders Processing").child(timestamp).child("order_processing_state").setValue("pending");
                companyRef.child(post_key).child("Orders Processing").child(timestamp).child("order_processing_customer_id").setValue(customerPostKey);
                companyRef.child(post_key).child("Orders Processing").child(timestamp).child("order_processing_destination_address").setValue(address_value);
                companyRef.child(post_key).child("Orders Processing").child(timestamp).child("order_processing_total_amount").setValue(total_amount_st);
                companyRef.child(post_key).child("Orders Processing").child(timestamp).child("timestamp").setValue(ServerValue.TIMESTAMP);
                companyRef.child(post_key).child("Orders Processing").child(timestamp).child("operation_date").setValue(saveCurrentDate);
                companyRef.child(post_key).child("Orders Processing").child(timestamp).child("operation_time").setValue(saveCurrentTime);
                companyRef.child(post_key).child("Orders Processing").child(timestamp).child("operation_day").setValue(day+"");
                companyRef.child(post_key).child("Orders Processing").child(timestamp).child("operation_month").setValue(month+"");
                companyRef.child(post_key).child("Orders Processing").child(timestamp).child("operation_year").setValue(year+"");
                companyRef.child(post_key).child("Orders Processing").child(timestamp).child("order_processing_currency").setValue("PEN");


                companyRef.child(post_key).child("Order Processing Items").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        companyRef.child(post_key).child("Orders Processing").child(timestamp).child("Items").setValue(dataSnapshot.getValue(), new DatabaseReference.CompletionListener() {
                            @Override
                            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                                if (databaseError != null) {
                                    Toast.makeText(OrderProcessingActivity.this, "Hubo un Error", Toast.LENGTH_SHORT).show();
                                } else {
                                    companyRef.child(post_key).child("Order Processing Items").removeValue();
                                    Intent intent = new Intent(OrderProcessingActivity.this, OrderProcessingListActivity.class);
                                    intent.putExtra("post_key",post_key);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        });
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        });

    }

    private void showAddAddressDialog() {
        final AlertDialog dialog = new AlertDialog.Builder(OrderProcessingActivity.this).create();

        LayoutInflater inflater = LayoutInflater.from(OrderProcessingActivity.this);
        View finance_method = inflater.inflate(R.layout.add_purchase_order_address,null);

        final EditText edtInput;
        Button btnRegister;

        edtInput = finance_method.findViewById(R.id.edtInput);
        btnRegister = finance_method.findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtAddress.setText("DIRECCIÓN DE ENTREGA: "+edtInput.getText().toString());
                address_value = edtInput.getText().toString();
                dialog.dismiss();
            }
        });

        dialog.setView(finance_method);
        dialog.show();
    }

    private void showListProducts() {
        Query query = companyRef.child(post_key).child("Order Processing Items").orderByChild("timestamp");
        FirebaseRecyclerAdapter<BillItemsModel, CompanyBillItemsViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<BillItemsModel, CompanyBillItemsViewHolder>
                (BillItemsModel.class, R.layout.purchase_order_product_item, CompanyBillItemsViewHolder.class, query) {
            @Override
            protected void populateViewHolder(CompanyBillItemsViewHolder viewHolder, BillItemsModel model, int position) {
                final String postKey = getRef(position).getKey();
                viewHolder.setCode(model.getCode());
                viewHolder.setDiscount(model.getDiscount());
                viewHolder.setMeasure(model.getMeasure());
                viewHolder.setName(model.getName());
                viewHolder.setPrice(model.getPrice());
                viewHolder.setQuantity(model.getQuantity());
                viewHolder.setTotal(model.getTotal());

                viewHolder.txtItemName.setText(viewHolder.my_name);
                viewHolder.txtPrice.setText("S/ "+viewHolder.my_price);
                viewHolder.txtQuantity.setText(viewHolder.my_quantity);
                viewHolder.txtTotal.setText("S/ "+viewHolder.my_total);


            }
        };
        recyclerView.setAdapter(firebaseRecyclerAdapter);

    }


    private void showTotalAmount() {
        companyRef.child(post_key).child("Order Processing Items").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                double sum = 0;

                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Map<String, Object> map = (Map<String, Object>) ds.getValue();
                    Object price = map.get("total");
                    double total_value = Double.parseDouble(String.valueOf(price));
                    sum += total_value;

                    total_amount_st = decimalFormat.format(sum);

                    double taxes_db = sum*0.18;
                    double taxes_round = Math.round(taxes_db*100.0)/100.0;
                    total_taxes_st = decimalFormat.format(taxes_round);

                    double sales_value = sum-taxes_round;

                    txtTaxes.setText("IGV: S/ "+total_taxes_st);
                    txtTotalAmount.setText("TOTAL: S/ "+total_amount_st);
                    txtSaleValue.setText("VALOR VENTA: S/ "+sales_value);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void showAddProductDialog() {
        dialog_list = new AlertDialog.Builder(this).create();

        LayoutInflater inflater = LayoutInflater.from(this);
        View finance_method = inflater.inflate(R.layout.my_product_list_dialog,null);

        Button btnRegisterNewProduct;

        btnRegisterNewProduct = finance_method.findViewById(R.id.btnRegisterNewProduct);
        recyclerView2 = finance_method.findViewById(R.id.recyclerView);

        btnRegisterNewProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRegisterNewProductDialog();
            }

            private void showRegisterNewProductDialog() {
                final AlertDialog dialog = new AlertDialog.Builder(OrderProcessingActivity.this).create();

                LayoutInflater inflater = LayoutInflater.from(OrderProcessingActivity.this);
                View finance_method = inflater.inflate(R.layout.add_new_product_dialog,null);


                Button btnCamera,btnGallery,btnRegisterNewProduct;
                final EditText edtProductName,edtPrice,edtStock;
                final RelativeLayout rootLayout;

                imgProduct = finance_method.findViewById(R.id.imgProduct);
                btnCamera = finance_method.findViewById(R.id.btnCamera);
                btnGallery = finance_method.findViewById(R.id.btnGallery);
                edtProductName = finance_method.findViewById(R.id.edtProductName);
                edtPrice = finance_method.findViewById(R.id.edtPrice);
                btnRegisterNewProduct = finance_method.findViewById(R.id.btnRegisterNewProduct);
                rootLayout = finance_method.findViewById(R.id.rootLayout);
                edtStock = finance_method.findViewById(R.id.edtStock);

                btnCamera.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dispatchTakePictureIntent();
                    }
                });

                btnGallery.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent galleryIntent = new Intent();
                        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                        galleryIntent.setType("image/*");
                        startActivityForResult(galleryIntent, Gallery_Pick);
                    }
                });

                btnRegisterNewProduct.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!image_verification.equals("true")) {
                            Snackbar.make(rootLayout, "Debes subir una foto de perfil", Snackbar.LENGTH_LONG).show();
                            return;
                        }
                        else if (TextUtils.isEmpty(edtProductName.getText().toString())) {
                            Snackbar.make(rootLayout, "Debes ingresar el nombre de tu producto", Snackbar.LENGTH_LONG).show();
                            return;
                        } else if (TextUtils.isEmpty(edtPrice.getText().toString())) {
                            Snackbar.make(rootLayout, "Debes ingresar el precio de tu producto", Snackbar.LENGTH_LONG).show();
                            return;
                        } else {
                            loadingBar.setTitle("Registrando de tu Producto...");
                            loadingBar.setMessage("Cargando...");
                            loadingBar.show();
                            loadingBar.setCanceledOnTouchOutside(false);
                            loadingBar.setCancelable(false);

                            current_time = new SimpleDateFormat("HHmmss").format(Calendar.getInstance().getTime());

                            HashMap hashMap = new HashMap();
                            hashMap.put("product_image",downloadUrl);
                            hashMap.put("product_currency","PEN");
                            hashMap.put("product_description","");
                            hashMap.put("product_measure","quantity");
                            hashMap.put("company_id",post_key);
                            hashMap.put("code",day+""+month+""+year+""+current_time);
                            hashMap.put("product_name",edtProductName.getText().toString().toUpperCase());
                            hashMap.put("product_price",edtPrice.getText().toString());
                            hashMap.put("product_stock",edtStock.getText().toString());
                            hashMap.put("timestamp", ServerValue.TIMESTAMP);
                            companyRef.child(post_key).child("My Products").child(post_key+day+""+month+""+year+""+current_time).updateChildren(hashMap).addOnCompleteListener(new OnCompleteListener() {
                                @Override
                                public void onComplete(@NonNull Task task) {
                                    Toasty.success(OrderProcessingActivity.this, "Producto Registrado", Toast.LENGTH_LONG).show();
                                    dialog.dismiss();
                                    loadingBar.dismiss();
                                    showCompanyProducts();
                                }
                            });
                        }
                    }
                });

                dialog.setView(finance_method);
                dialog.show();
            }
        });

        recyclerView2.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(false);
        linearLayoutManager.setStackFromEnd(false);
        recyclerView2.setLayoutManager(linearLayoutManager);

        showCompanyProducts();

        dialog_list.setView(finance_method);
        dialog_list.show();
    }

    private void showCompanyProducts() {
        Query query = companyRef.child(post_key).child("My Products").orderByChild("timestamp");
        FirebaseRecyclerAdapter<ProductsModel, CompanyProductsViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<ProductsModel, CompanyProductsViewHolder>
                (ProductsModel.class,R.layout.product_bill_item,CompanyProductsViewHolder.class,query) {
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
                viewHolder.txtProductPrice.setText("S/ "+viewHolder.my_product_price);
                Picasso.with(OrderProcessingActivity.this).load(viewHolder.my_product_image).fit().into(viewHolder.imgProduct);


                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showSetQuantityDialog();
                    }

                    private void showSetQuantityDialog() {
                        final AlertDialog dialog = new AlertDialog.Builder(OrderProcessingActivity.this).create();

                        LayoutInflater inflater = LayoutInflater.from(OrderProcessingActivity.this);
                        View finance_method = inflater.inflate(R.layout.set_quantity_dialog,null);

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

                        Picasso.with(OrderProcessingActivity.this).load(viewHolder.my_product_image).fit().into(imgProductDialog);
                        txtProductName.setText("Cantidad para "+viewHolder.my_product_name);

                        companyRef.child(post_key).child("My Products").child(postKey).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                current_product_stock = dataSnapshot.child("product_stock").getValue().toString();


                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });

                        btnRegister.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                current_product_stock_db = Double.parseDouble(current_product_stock);
                                double quantity_db = Double.parseDouble(edtQuantity.getText().toString());

                                if (TextUtils.isEmpty(edtQuantity.getText().toString())) {
                                    Snackbar.make(rootLayout, "Debes ingresar la cantidad", Snackbar.LENGTH_LONG).show();
                                    return;
                                } else if (current_product_stock_db < quantity_db) {
                                    Snackbar.make(rootLayout, "No cuentas con stock suficiente", Snackbar.LENGTH_LONG).show();
                                    return;
                                }
                                else {
                                    //Calculate total:
                                    quantity_db = Double.parseDouble(edtQuantity.getText().toString());
                                    double price = Double.parseDouble(viewHolder.my_product_price);

                                    double total_db = quantity_db * price;
                                    final double total_round = Math.round(total_db * 100.0) / 100.0;
                                    String total_st = decimalFormat.format(total_round);

                                    final double q = Double.parseDouble(edtQuantity.getText().toString());



                                    companyRef.child(post_key).child("Order Processing Items").child(postKey).child("quantity").setValue(edtQuantity.getText().toString());
                                    companyRef.child(post_key).child("Order Processing Items").child(postKey).child("measure").setValue("quantity");
                                    companyRef.child(post_key).child("Order Processing Items").child(postKey).child("code").setValue(viewHolder.my_code);
                                    companyRef.child(post_key).child("Order Processing Items").child(postKey).child("name").setValue(viewHolder.my_product_name);
                                    companyRef.child(post_key).child("Order Processing Items").child(postKey).child("price").setValue(viewHolder.my_product_price);
                                    companyRef.child(post_key).child("Order Processing Items").child(postKey).child("discount").setValue("0.00");
                                    companyRef.child(post_key).child("Order Processing Items").child(postKey).child("total").setValue(total_st);
                                    companyRef.child(post_key).child("Order Processing Items").child(postKey).child("product_id").setValue(postKey);
                                    companyRef.child(post_key).child("Order Processing Items").child(postKey).child("timestamp").setValue(ServerValue.TIMESTAMP);

                                    Date date = new Date();
                                    Calendar cal = Calendar.getInstance();
                                    cal.setTime(date);
                                    day = cal.get(Calendar.DAY_OF_MONTH);
                                    month = cal.get(Calendar.MONTH) + 1;
                                    year = cal.get(Calendar.YEAR);


                                    showTotalAmount();
                                    dialog.dismiss();
                                    dialog_list.dismiss();
                                    Toasty.success(OrderProcessingActivity.this, viewHolder.my_product_name + " agregado con éxito", Toast.LENGTH_LONG).show();
                                }
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

    private void showCustomersDialog() {
        dialog_customers = new AlertDialog.Builder(OrderProcessingActivity.this).create();

        LayoutInflater inflater = LayoutInflater.from(OrderProcessingActivity.this);
        View finance_method = inflater.inflate(R.layout.customer_bill_dialog,null);

        Button btnAddCustomer;

        recyclerView2 = finance_method.findViewById(R.id.recyclerView2);
        btnAddCustomer = finance_method.findViewById(R.id.btnAddCustomer);
        recyclerView2.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(false);
        linearLayoutManager.setStackFromEnd(false);
        recyclerView2.setLayoutManager(linearLayoutManager);

        showCompanyCustomers();

        btnAddCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddCustomerDialog();
            }

            private void showAddCustomerDialog() {
                final AlertDialog dialog = new AlertDialog.Builder(OrderProcessingActivity.this).create();

                LayoutInflater inflater = LayoutInflater.from(OrderProcessingActivity.this);
                View finance_method = inflater.inflate(R.layout.add_customer_dialog,null);


                Button btnAddCustomer;
                TextView txtCancel;
                final EditText edtName,edtEmail,edtPhoneNumber;
                final CountryCodePicker ccpPhoneCode;
                final RadioButton rdPerson,rdCompany;

                btnAddCustomer = finance_method.findViewById(R.id.btnAddCustomer);
                txtCancel = finance_method.findViewById(R.id.txtCancel);
                edtName = finance_method.findViewById(R.id.edtName);
                edtEmail = finance_method.findViewById(R.id.edtEmail);
                edtPhoneNumber = finance_method.findViewById(R.id.edtPhoneNumber);
                ccpPhoneCode = finance_method.findViewById(R.id.ccpPhoneCode);
                rdPerson = finance_method.findViewById(R.id.rdPerson);
                rdCompany = finance_method.findViewById(R.id.rdCompany);

                btnAddCustomer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Calendar calForDate = Calendar.getInstance();
                        SimpleDateFormat currentDate = new SimpleDateFormat("dd-MM-yyyy");
                        String saveCurrentDate = currentDate.format(calForDate.getTime());

                        Calendar calForTime = Calendar.getInstance();
                        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss");
                        String saveCurrentTime = currentTime.format(calForTime.getTime());

                        String postRandomName = saveCurrentDate + saveCurrentTime;


                        HashMap hashMap = new HashMap();
                        hashMap.put("customer_name", edtName.getText().toString());
                        hashMap.put("customer_email", edtEmail.getText().toString());
                        hashMap.put("customer_phone", ccpPhoneCode.getSelectedCountryCode()+edtPhoneNumber.getText().toString());
                        if (rdPerson.isChecked()) {
                            hashMap.put("customer_type", rdPerson.getText().toString());
                        }
                        else if (rdCompany.isChecked()) {
                            hashMap.put("customer_type", rdCompany.getText().toString());
                        }
                        hashMap.put("customer_define","contact");
                        hashMap.put("register_date", saveCurrentDate);
                        hashMap.put("register_time", saveCurrentTime);
                        companyRef.child(post_key).child("Customers").child(postRandomName).updateChildren(hashMap).addOnCompleteListener(new OnCompleteListener() {
                            @Override
                            public void onComplete(@NonNull Task task) {
                                companyRef.child(post_key).child("Achievements").child("Module2 Add Customer").child("score").setValue("50");
                                companyRef.child(post_key).child("Achievements").child("Module2 Add Customer").child("message").setValue("Has registrado satisfactoriamente a tu primer cliente");
                                companyRef.child(post_key).child("Achievements").child("Module2 Add Customer").child("timestamp").setValue(ServerValue.TIMESTAMP);
                                Toasty.success(OrderProcessingActivity.this, "Registrado", Toast.LENGTH_LONG).show();
                                dialog.dismiss();
                            }
                        });

                    }
                });

                txtCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.setView(finance_method);
                dialog.show();
            }
        });

        dialog_customers.setView(finance_method);
        dialog_customers.show();

    }

    private void showCompanyCustomers() {
        Query query = companyRef.child(post_key).child("Customers").orderByChild("customer_type");
        FirebaseRecyclerAdapter<CompanyCustomersModel, CompanyCustomersViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<CompanyCustomersModel, CompanyCustomersViewHolder>
                (CompanyCustomersModel.class, R.layout.customer_bill_item, CompanyCustomersViewHolder.class, query) {
            @Override
            protected void populateViewHolder(final CompanyCustomersViewHolder viewHolder, CompanyCustomersModel model, final int position) {
                final String postKey = getRef(position).getKey();
                viewHolder.setCustomer_email(model.getCustomer_email());
                viewHolder.setCustomer_name(model.getCustomer_name());
                viewHolder.setCustomer_phone(model.getCustomer_phone());
                viewHolder.setCustomer_type(model.getCustomer_type());

                viewHolder.txtName.setText(viewHolder.name);
                viewHolder.txtPhone.setText("Teléfono: "+viewHolder.phone);
                viewHolder.txtEmail.setText("Correo: "+viewHolder.email);
                viewHolder.txtType.setText("Tipo: "+viewHolder.type);

                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        companyRef.child(post_key).child("Customers").child(postKey).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                customer_name = dataSnapshot.child("customer_name").getValue().toString();
                                customer_phone = dataSnapshot.child("customer_phone").getValue().toString();
                                customer_email = dataSnapshot.child("customer_email").getValue().toString();
                                txtCustomerName.setText("CLLIENTE: "+customer_name.toUpperCase());
                                txtPhone.setText("TELÉFONO: "+customer_phone);
                                txtEmail.setText("CORREO: "+customer_email);

                                customerPostKey = postKey;
                                dialog_customers.dismiss();
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                    }
                });

            }
        };
        recyclerView2.setAdapter(firebaseRecyclerAdapter);
    }

    public static class CompanyProductsViewHolder extends RecyclerView.ViewHolder {
        View mView;
        String my_product_image,my_product_currency,my_product_description,my_product_measure,my_uid, my_code,my_product_name,my_product_price,my_product_stock;
        CircleImageView imgProduct;
        TextView txtProductName,txtProductPrice;

        public CompanyProductsViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;

            imgProduct = mView.findViewById(R.id.imgProduct);
            txtProductName = mView.findViewById(R.id.txtProductName);
            txtProductPrice = mView.findViewById(R.id.txtProductPrice);
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

    public static class CompanyBillItemsViewHolder extends RecyclerView.ViewHolder {
        View mView;
        String my_code,my_discount,my_measure,my_name,my_price,my_quantity,my_total;
        TextView txtItemName,txtPrice,txtQuantity,txtTotal;
        ImageView btnDelete;


        public CompanyBillItemsViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;

            txtItemName = mView.findViewById(R.id.txtItemName);
            txtPrice = mView.findViewById(R.id.txtPrice);
            txtQuantity = mView.findViewById(R.id.txtQuantity);
            txtTotal = mView.findViewById(R.id.txtTotal);


        }
        public void setCode(String code) {
            my_code = code;
        }
        public void setDiscount(String discount) {
            my_discount = discount;
        }
        public void setMeasure(String measure) {
            my_measure = measure;
        }
        public void setName(String name) {
            my_name = name;
        }
        public void setPrice(String price) {
            my_price = price;
        }
        public void setQuantity(String quantity) {
            my_quantity = quantity;
        }
        public void setTotal(String total) {
            my_total= total;
        }

    }

    public static class CompanyCustomersViewHolder extends RecyclerView.ViewHolder {
        View mView;
        TextView txtName,txtPhone,txtEmail,txtType;
        String email,name,phone,type;

        public CompanyCustomersViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;

            txtName = mView.findViewById(R.id.txtName);
            txtPhone = mView.findViewById(R.id.txtPhone);
            txtEmail = mView.findViewById(R.id.txtEmail);
            txtType = mView.findViewById(R.id.txtType);
        }
        public void setCustomer_email(String customer_email) {
            email = customer_email;
        }

        public void setCustomer_name(String customer_name) {
            name = customer_name;
        }


        public void setCustomer_phone(String customer_phone) {
            phone = customer_phone;
        }


        public void setCustomer_type(String customer_type) {
            type = customer_type;
        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }

    static final int REQUEST_TAKE_PHOTO = 1;

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File...
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.oalonedeveloper.oliver.oliverappandroidapp.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }

    private void setPic() {
        // Get the dimensions of the View
        int targetW = imgProduct.getWidth();
        int targetH = imgProduct.getHeight();

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;

        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = Math.min(photoW/targetW, photoH/targetH);

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        Bitmap bitmap = BitmapFactory.decodeFile(currentPhotoPath, bmOptions);
        imgProduct.setImageBitmap(bitmap);

        Long tsLong = System.currentTimeMillis()/1000;
        String timestamp = tsLong.toString();

        String path = MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, "Title"+timestamp, null);
        imageUri = imageUri.parse(path);


        savePhotoOnDatabase();

    }

    private void savePhotoOnDatabase() {
        loadingBar.setTitle("Subiendo la imágen de tu Producto...");
        loadingBar.setMessage("Cargando...");
        loadingBar.show();
        loadingBar.setCanceledOnTouchOutside(false);
        loadingBar.setCancelable(false);

        StorageReference filePath = userProfileImageRef.child(imageUri.getLastPathSegment()+post_key+".jpg");
        filePath.putFile(imageUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                if (task.isSuccessful()) {
                    downloadUrl = task.getResult().getDownloadUrl().toString();
                    image_verification = "true";
                    loadingBar.dismiss();
                }
            }
        });

    }

    private void galleryAddPic() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(currentPhotoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        sendBroadcast(mediaScanIntent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
            galleryAddPic();
            setPic();

            /*Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            profileImage.setImageBitmap(imageBitmap);*/
        }
        if (requestCode == Gallery_Pick && resultCode == RESULT_OK && data != null) {

            imageUri = data.getData();
            try {
                Bitmap imageBitmap =MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                imgProduct.setImageBitmap(imageBitmap);
                // Get the dimensions of the View
                int targetW = imgProduct.getWidth();
                int targetH = imgProduct.getHeight();

                // Get the dimensions of the bitmap
                BitmapFactory.Options bmOptions = new BitmapFactory.Options();
                bmOptions.inJustDecodeBounds = true;

                int photoW = bmOptions.outWidth;
                int photoH = bmOptions.outHeight;

                // Determine how much to scale down the image
                int scaleFactor = Math.min(photoW/targetW, photoH/targetH);

                // Decode the image file into a Bitmap sized to fill the View
                bmOptions.inJustDecodeBounds = false;
                bmOptions.inSampleSize = scaleFactor;
                bmOptions.inPurgeable = true;

                //imageBitmap = BitmapFactory.decodeFile(currentPhotoPath, bmOptions);


                imgProduct.setImageBitmap(imageBitmap);

                /*ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, bytes);
                String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), imageBitmap, "Title", null);
                imageUri = imageUri.parse(path);*/
                //txtImageVerification.setText("Imágen cargada con éxito");
                savePhotoOnDatabase();


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
