package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Logistic.PurchaseOrder;

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
import android.widget.LinearLayout;
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
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Commercialization.BillsIssuing.CreateBillActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Production.ProductionOrders.ProductionOrderProductDetailActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.Companies.ProductsModel;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import de.hdodenhof.circleimageview.CircleImageView;
import es.dmoral.toasty.Toasty;
import in.galaxyofandroid.spinerdialog.OnSpinerItemClick;
import in.galaxyofandroid.spinerdialog.SpinnerDialog;

import static com.oalonedeveloper.oliver.oliverappandroidapp.R.layout.activity_purchase_order;

public class PurchaseOrderActivity extends AppCompatActivity {

    CircleImageView imgCompanyProfile;
    TextView txtCompanyName,txtCompanyAddress,txtCurrentDate,txtOrderCode,txtSupplier,txtContact,txtPhone,txtEmail,txtAddress,txtSalesConditions,txtTaxes,txtTotalAmount,txtExpirationDate;
    DatabaseReference companyRef;
    String post_key,company_image,company_social_reason,company_address,timestamp,supplier_id,address_value,sales_conditions_value,total_amount_st,total_taxes_st,image_verification,current_time,currentPhotoPath,downloadUrl,expiration_day,expiration_month,expiration_year,
            exp_validation;
    DecimalFormat decimalFormat;
    int day,month,year;
    RecyclerView recyclerView,recyclerView2,recyclerView3;
    AlertDialog dialog;
    Button btnAddProduct,btnRegisterPurchaseOrder;
    ImageView imgProduct;
    ProgressDialog loadingBar;
    Uri imageUri;
    StorageReference userProfileImageRef;
    final static int Gallery_Pick = 2;
    RelativeLayout rootLayout;

    ArrayList<String> bthDay =new ArrayList<>();
    SpinnerDialog bthDayDialog;

    ArrayList<String> bthMonth =new ArrayList<>();
    SpinnerDialog bthMonthDialog;

    ArrayList<String> bthYear =new ArrayList<>();
    SpinnerDialog bthYearDialog;

    long diff,expiration_days;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_purchase_order);

        imgCompanyProfile = findViewById(R.id.imgCompanyProfile);
        txtCompanyName = findViewById(R.id.txtCompanyName);
        txtCompanyAddress = findViewById(R.id.txtCompanyAddress);
        txtCurrentDate = findViewById(R.id.txtCurrentDate);
        txtOrderCode = findViewById(R.id.txtOrderCode);
        txtSupplier = findViewById(R.id.txtSupplier);
        txtContact = findViewById(R.id.txtContact);
        txtPhone = findViewById(R.id.txtPhone);
        txtEmail = findViewById(R.id.txtEmail);
        txtAddress = findViewById(R.id.txtAddress);
        txtSalesConditions = findViewById(R.id.txtSalesConditions);
        btnAddProduct = findViewById(R.id.btnAddProduct);
        txtTaxes = findViewById(R.id.txtTaxes);
        txtTotalAmount = findViewById(R.id.txtTotalAmount);
        btnRegisterPurchaseOrder = findViewById(R.id.btnRegisterPurchaseOrder);
        rootLayout = findViewById(R.id.rootLayout);
        txtExpirationDate = findViewById(R.id.txtExpirationDate);

        image_verification = "";
        supplier_id = "";

        post_key = getIntent().getExtras().getString("post_key");
        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");
        userProfileImageRef = FirebaseStorage.getInstance().getReference().child("Product Images");
        decimalFormat = new DecimalFormat("0.00");

        loadingBar = new ProgressDialog(this);

        recyclerView2 = findViewById(R.id.recyclerView2);
        recyclerView2.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView2.setLayoutManager(linearLayoutManager);

        Date date= new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        day = cal.get(Calendar.DAY_OF_MONTH);
        month = cal.get(Calendar.MONTH)+1;
        year = cal.get(Calendar.YEAR);

        expiration_day = day+"";
        expiration_month = month+"";
        expiration_year = year+"";

        txtExpirationDate.setText("VENCIMIENTO: "+expiration_day+"/"+expiration_month+"/"+expiration_year);

        Long tsLong = System.currentTimeMillis()/1000;
        timestamp = tsLong.toString();

        txtSupplier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSupplierDialog();
            }
        });

        txtCurrentDate.setText("Fecha de emisión: "+day+"/"+month+"/"+year);
        txtOrderCode.setText("Nº: "+timestamp);

        txtExpirationDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showExpirationDateDialog();
            }
        });

        companyRef.child(post_key).child("Purchased Order Items").removeValue();

        companyRef.child(post_key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                company_image = dataSnapshot.child("company_image").getValue().toString();
                company_social_reason = dataSnapshot.child("company_social_reason").getValue().toString();
                company_address = dataSnapshot.child("company_address").getValue().toString();

                Picasso.with(PurchaseOrderActivity.this).load(company_image).fit().into(imgCompanyProfile);
                txtCompanyName.setText(company_social_reason);
                txtCompanyAddress.setText(company_address);

                showOrderPurchaseProducts();
                showTotalAmount();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        txtAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddAddressDialog();
            }
        });
        txtSalesConditions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog dialog = new AlertDialog.Builder(PurchaseOrderActivity.this).create();

                LayoutInflater inflater = LayoutInflater.from(PurchaseOrderActivity.this);
                View finance_method = inflater.inflate(R.layout.add_purchase_order_conditions,null);

                final EditText edtInput;
                Button btnRegister;

                edtInput = finance_method.findViewById(R.id.edtInput);
                btnRegister = finance_method.findViewById(R.id.btnRegister);

                btnRegister.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        txtSalesConditions.setText("DIRECCIÓN DE ENTREGA: "+edtInput.getText().toString());
                        sales_conditions_value = edtInput.getText().toString();
                        dialog.dismiss();
                    }
                });

                dialog.setView(finance_method);
                dialog.show();
            }
        });

        btnAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (supplier_id.equals("")) {
                    Snackbar.make(rootLayout,"Debes seleccionar primero al Proveedor", Snackbar.LENGTH_LONG).show();
                } else {
                    showAddProductDialog();
                }

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

                companyRef.child(post_key).child("Purchased Orders").child(timestamp).child("purchase_order_id").setValue(timestamp);
                companyRef.child(post_key).child("Purchased Orders").child(timestamp).child("purchase_order_state").setValue("pending");
                companyRef.child(post_key).child("Purchased Orders").child(timestamp).child("purchase_order_supplier_id").setValue(supplier_id);
                companyRef.child(post_key).child("Purchased Orders").child(timestamp).child("purchase_order_destination_address").setValue(address_value);
                companyRef.child(post_key).child("Purchased Orders").child(timestamp).child("purchase_order_sales_conditions").setValue(sales_conditions_value);
                companyRef.child(post_key).child("Purchased Orders").child(timestamp).child("purchase_order_total_amount").setValue(total_amount_st);
                companyRef.child(post_key).child("Purchased Orders").child(timestamp).child("timestamp").setValue(ServerValue.TIMESTAMP);
                companyRef.child(post_key).child("Purchased Orders").child(timestamp).child("operation_date").setValue(saveCurrentDate);
                companyRef.child(post_key).child("Purchased Orders").child(timestamp).child("operation_time").setValue(saveCurrentTime);
                companyRef.child(post_key).child("Purchased Orders").child(timestamp).child("operation_day").setValue(day+"");
                companyRef.child(post_key).child("Purchased Orders").child(timestamp).child("operation_month").setValue(month+"");
                companyRef.child(post_key).child("Purchased Orders").child(timestamp).child("operation_year").setValue(year+"");
                companyRef.child(post_key).child("Purchased Orders").child(timestamp).child("expiration_day").setValue(expiration_day);
                companyRef.child(post_key).child("Purchased Orders").child(timestamp).child("expiration_month").setValue(expiration_month);
                companyRef.child(post_key).child("Purchased Orders").child(timestamp).child("expiration_year").setValue(expiration_year);
                companyRef.child(post_key).child("Purchased Orders").child(timestamp).child("purchase_payment_state").setValue("no_paid");
                companyRef.child(post_key).child("Purchased Orders").child(timestamp).child("purchase_order_currency").setValue("PEN");


                companyRef.child(post_key).child("Purchased Order Items").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        companyRef.child(post_key).child("Purchased Orders").child(timestamp).child("Items").setValue(dataSnapshot.getValue(), new DatabaseReference.CompletionListener() {
                            @Override
                            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                                if (databaseError != null) {
                                    Toast.makeText(PurchaseOrderActivity.this, "Hubo un Error", Toast.LENGTH_SHORT).show();
                                } else {
                                    companyRef.child(post_key).child("Purchased Order Items").removeValue();
                                    Intent intent = new Intent(PurchaseOrderActivity.this, PurchaseOrdersListActivity.class);
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

    private void showExpirationDateDialog() {
        final AlertDialog dialog = new AlertDialog.Builder(this).create();

        LayoutInflater inflater = LayoutInflater.from(this);
        View finance_method = inflater.inflate(R.layout.add_expiration_date_dialog,null);

        final Button edtBthDay,edtBthMonth,edtBthYear,btnRegister;
        final RelativeLayout rootLayout;

        edtBthDay = finance_method.findViewById(R.id.edtBthDay);
        edtBthMonth = finance_method.findViewById(R.id.edtBthMonth);
        edtBthYear = finance_method.findViewById(R.id.edtBthYear);
        btnRegister = finance_method.findViewById(R.id.btnRegister);
        rootLayout = finance_method.findViewById(R.id.rootLayout);

        bthDay.add("1"); bthDay.add("2"); bthDay.add("3"); bthDay.add("4"); bthDay.add("5"); bthDay.add("6"); bthDay.add("7"); bthDay.add("8"); bthDay.add("9"); bthDay.add("10");
        bthDay.add("11"); bthDay.add("12"); bthDay.add("13"); bthDay.add("14"); bthDay.add("15"); bthDay.add("16"); bthDay.add("17"); bthDay.add("18"); bthDay.add("19"); bthDay.add("20");
        bthDay.add("21"); bthDay.add("22"); bthDay.add("23"); bthDay.add("24"); bthDay.add("25"); bthDay.add("26"); bthDay.add("27"); bthDay.add("28"); bthDay.add("29"); bthDay.add("30");
        bthDay.add("31");

        edtBthDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bthDayDialog.showSpinerDialog();
            }
        });

        bthDayDialog = new SpinnerDialog(this,bthDay, "Selecciona el Día de Vencimiento");
        bthDayDialog.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String item2, int position2) {
                edtBthDay.setText(item2);
            }
        });

        bthMonth.add("1");bthMonth.add("2");bthMonth.add("3");bthMonth.add("4");bthMonth.add("5");bthMonth.add("6");bthMonth.add("7");bthMonth.add("8");bthMonth.add("9");bthMonth.add("10");
        bthMonth.add("11");bthMonth.add("12");

        edtBthMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bthMonthDialog.showSpinerDialog();
            }
        });

        bthMonthDialog = new SpinnerDialog(this,bthMonth, "Selecciona el Mes de Vencimiento");
        bthMonthDialog.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String item2, int position2) {
                edtBthMonth.setText(item2);
            }
        });

        bthYear.add("2021");bthYear.add("2022");bthYear.add("2023");bthYear.add("2024");bthYear.add("2025");bthYear.add("2026");bthYear.add("2027");bthYear.add("2028");bthYear.add("2029");
        bthYear.add("2030");

        edtBthYear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bthYearDialog.showSpinerDialog();
            }
        });

        bthYearDialog = new SpinnerDialog(this,bthYear, "Selecciona el Año de Vencimiento");
        bthYearDialog.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String item2, int position2) {
                edtBthYear.setText(item2);
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDateFormat myFormat = new SimpleDateFormat("dd MM yyyy");
                String inputString1 = day+" "+month+" "+year;
                String inputString2 = edtBthDay.getText().toString()+" "+edtBthMonth.getText().toString()+" "+edtBthYear.getText().toString();
                try {
                    Date date1 = myFormat.parse(inputString1);
                    Date date2 = myFormat.parse(inputString2);
                    diff = date2.getTime() - date1.getTime();

                    expiration_days = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);

                    if (diff <= 0) {
                        exp_validation = "false";
                    } else {
                        exp_validation = "true";
                    }

                } catch (ParseException e) {
                    e.printStackTrace();
                }

                if (exp_validation.equals("false")) {
                    Snackbar.make(rootLayout, "La fecha de vencimiento no puede ser menor a la fecha actual", Snackbar.LENGTH_LONG).show();
                    return;
                }
                else if (TextUtils.isEmpty(edtBthDay.getText().toString())) {
                    Snackbar.make(rootLayout, "Debes ingresar el día de vencimiento", Snackbar.LENGTH_LONG).show();
                    return;
                }
                else if (TextUtils.isEmpty(edtBthMonth.getText().toString())) {
                    Snackbar.make(rootLayout, "Debes ingresar el mes de vencimiento", Snackbar.LENGTH_LONG).show();
                    return;
                } else if (TextUtils.isEmpty(edtBthYear.getText().toString())) {
                    Snackbar.make(rootLayout, "Debes ingresar el año de vencimiento", Snackbar.LENGTH_LONG).show();
                    return;
                } else {
                    expiration_day = edtBthDay.getText().toString();
                    expiration_month = edtBthMonth.getText().toString();
                    expiration_year = edtBthYear.getText().toString();
                    txtExpirationDate.setText("VENCIMIENTO: "+edtBthDay.getText().toString()+"/"+edtBthMonth.getText().toString()+"/"+edtBthYear.getText().toString()+" ("+expiration_days+" días)");
                    dialog.dismiss();
                }


            }
        });


        dialog.setView(finance_method);
        dialog.show();
    }

    private void showTotalAmount() {
        companyRef.child(post_key).child("Purchased Order Items").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                double sum = 0;

                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Map<String, Object> map = (Map<String, Object>) ds.getValue();
                    Object price = map.get("item_total");
                    double total_value = Double.parseDouble(String.valueOf(price));
                    sum += total_value;

                    total_amount_st = decimalFormat.format(sum);

                    double taxes_db = sum*0.18;
                    double taxes_round = Math.round(taxes_db*100.0)/100.0;
                    total_taxes_st = decimalFormat.format(taxes_round);

                    txtTaxes.setText("IGV: S/ "+total_taxes_st);
                    txtTotalAmount.setText("TOTAL: S/ "+total_amount_st);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void showOrderPurchaseProducts() {
        Query query = companyRef.child(post_key).child("Purchased Order Items").orderByChild("timestamp");
        FirebaseRecyclerAdapter<SupplierProductsModel, SupplierProductsViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<SupplierProductsModel, SupplierProductsViewHolder>
                (SupplierProductsModel.class,R.layout.purchase_order_product_item,SupplierProductsViewHolder.class,query) {
            @Override
            protected void populateViewHolder(SupplierProductsViewHolder viewHolder, SupplierProductsModel model, int position) {
                viewHolder.setItem_description(model.getItem_description());
                viewHolder.setItem_name(model.getItem_name());
                viewHolder.setItem_price(model.getItem_price());
                viewHolder.setItem_quantity(model.getItem_quantity());
                viewHolder.setItem_total(model.getItem_total());

                viewHolder.txtItemName.setText(viewHolder.my_item_name);
                viewHolder.txtPrice.setText("S/ "+viewHolder.my_item_price);
                viewHolder.txtQuantity.setText(viewHolder.my_item_quantity);
                viewHolder.txtTotal.setText("S/ "+viewHolder.my_item_total);
            }
        };
        recyclerView2.setAdapter(firebaseRecyclerAdapter);
    }

    private void showAddProductDialog() {
        final AlertDialog dialog = new AlertDialog.Builder(PurchaseOrderActivity.this).create();

        LayoutInflater inflater = LayoutInflater.from(PurchaseOrderActivity.this);
        View finance_method = inflater.inflate(R.layout.add_purchase_product_dialog,null);

        Button btnRegisterNewProduct;

        btnRegisterNewProduct = finance_method.findViewById(R.id.btnRegisterNewProduct);

        btnRegisterNewProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AlertDialog dialog = new AlertDialog.Builder(PurchaseOrderActivity.this).create();

                LayoutInflater inflater = LayoutInflater.from(PurchaseOrderActivity.this);
                View finance_method = inflater.inflate(R.layout.add_new_item_purchase_dialog,null);

                Button btnCamera,btnGallery,btnRegisterNewProduct;
                final EditText edtProductName,edtPrice;
                final RelativeLayout rootLayout;

                imgProduct = finance_method.findViewById(R.id.imgProduct);
                btnCamera = finance_method.findViewById(R.id.btnCamera);
                btnGallery = finance_method.findViewById(R.id.btnGallery);
                edtProductName = finance_method.findViewById(R.id.edtProductName);
                edtPrice = finance_method.findViewById(R.id.edtPrice);
                btnRegisterNewProduct = finance_method.findViewById(R.id.btnRegisterNewProduct);
                rootLayout = finance_method.findViewById(R.id.rootLayout);

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
                            hashMap.put("timestamp", ServerValue.TIMESTAMP);
                            companyRef.child(post_key).child("Purchased Items").child(post_key+day+""+month+""+year+""+current_time).updateChildren(hashMap).addOnCompleteListener(new OnCompleteListener() {
                                @Override
                                public void onComplete(@NonNull Task task) {
                                    Toasty.success(PurchaseOrderActivity.this, "Artículo Registrado", Toast.LENGTH_LONG).show();
                                    dialog.dismiss();
                                    loadingBar.dismiss();
                                    showPurchaseProducts();
                                }
                            });
                        }
                    }
                });


                dialog.setView(finance_method);
                dialog.show();
            }
        });

        recyclerView3 = finance_method.findViewById(R.id.recyclerView3);

        recyclerView3.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(false);
        linearLayoutManager.setStackFromEnd(false);
        recyclerView3.setLayoutManager(linearLayoutManager);

        showPurchaseProducts();

        dialog.setView(finance_method);
        dialog.show();
    }

    private void showPurchaseProducts() {
        Query query = companyRef.child(post_key).child("Purchased Items").orderByChild("timestamp");
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
                viewHolder.txtProductPrice.setText("S/ "+viewHolder.my_product_price);
                Picasso.with(PurchaseOrderActivity.this).load(viewHolder.my_product_image).fit().into(viewHolder.imgProduct);

                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final AlertDialog dialog = new AlertDialog.Builder(PurchaseOrderActivity.this).create();

                        LayoutInflater inflater = LayoutInflater.from(PurchaseOrderActivity.this);
                        View finance_method = inflater.inflate(R.layout.set_quantity_price_item_purchase_dialog,null);

                        CircleImageView imgProduct;
                        TextView txtProductName,txtMessage;
                        final EditText edtQuantity,edtPrice;
                        Button btnRegister;
                        final RelativeLayout rootLayout;

                        imgProduct = finance_method.findViewById(R.id.imgProduct);
                        txtProductName = finance_method.findViewById(R.id.txtProductName);
                        txtMessage = finance_method.findViewById(R.id.txtMessage);
                        edtQuantity = finance_method.findViewById(R.id.edtQuantity);
                        edtPrice = finance_method.findViewById(R.id.edtPrice);
                        btnRegister = finance_method.findViewById(R.id.btnRegister);
                        rootLayout = finance_method.findViewById(R.id.rootLayout);

                        Picasso.with(PurchaseOrderActivity.this).load(viewHolder.my_product_image).fit().into(imgProduct);
                        txtProductName.setText(viewHolder.my_product_name);
                        txtMessage.setText("Ingresa la cantidad a comprar de "+viewHolder.my_product_name+" y su precio actual");
                        edtPrice.setText(viewHolder.my_product_price);


                        btnRegister.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (TextUtils.isEmpty(edtPrice.getText().toString())) {
                                    Snackbar.make(rootLayout,"Debes registrar el precio de "+viewHolder.my_product_name,Snackbar.LENGTH_LONG);
                                } else if (TextUtils.isEmpty(edtQuantity.getText().toString())) {
                                    Snackbar.make(rootLayout, "Debes registrar la cantidad de " + viewHolder.my_product_name, Snackbar.LENGTH_LONG);
                                } else {
                                    Long tsLong = System.currentTimeMillis()/1000;
                                    final String timestamp = tsLong.toString();

                                    double quantity = Double.parseDouble(edtQuantity.getText().toString());
                                    double price = Double.parseDouble(edtPrice.getText().toString());
                                    double total = quantity*price;
                                    String total_st = decimalFormat.format(total);

                                    companyRef.child(post_key).child("Purchased Order Items").child(postKey).child("item_image").setValue(viewHolder.my_product_image);
                                    companyRef.child(post_key).child("Purchased Order Items").child(postKey).child("item_measure").setValue("quantity");
                                    companyRef.child(post_key).child("Purchased Order Items").child(postKey).child("item_currency").setValue("PEN");
                                    companyRef.child(post_key).child("Purchased Order Items").child(postKey).child("item_description").setValue(viewHolder.my_product_description);
                                    companyRef.child(post_key).child("Purchased Order Items").child(postKey).child("item_name").setValue(viewHolder.my_product_name);
                                    companyRef.child(post_key).child("Purchased Order Items").child(postKey).child("item_price").setValue(edtPrice.getText().toString());
                                    companyRef.child(post_key).child("Purchased Order Items").child(postKey).child("item_quantity").setValue(edtQuantity.getText().toString());
                                    companyRef.child(post_key).child("Purchased Order Items").child(postKey).child("item_total").setValue(total_st);
                                    companyRef.child(post_key).child("Purchased Order Items").child(postKey).child("timestamp").setValue(ServerValue.TIMESTAMP);

                                    companyRef.child(post_key).child("Purchased Items").child(postKey).child("product_price").setValue(edtPrice.getText().toString());

                                    companyRef.child(post_key).child("Purchased Items").child(postKey).child("Suppliers").child(supplier_id).child("supplier_id").setValue(supplier_id);
                                    companyRef.child(post_key).child("Purchased Items").child(postKey).child("Suppliers").child(supplier_id).child("Prices").child(year+"").child(year+""+month).child("price").setValue(edtPrice.getText().toString());

                                    Toasty.success(PurchaseOrderActivity.this, viewHolder.my_product_name+ "Registrado", Toast.LENGTH_LONG).show();
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
        recyclerView3.setAdapter(firebaseRecyclerAdapter);
    }

    private void showAddAddressDialog() {
        final AlertDialog dialog = new AlertDialog.Builder(PurchaseOrderActivity.this).create();

        LayoutInflater inflater = LayoutInflater.from(PurchaseOrderActivity.this);
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

    private void showSupplierDialog() {
        dialog = new AlertDialog.Builder(PurchaseOrderActivity.this).create();

        LayoutInflater inflater = LayoutInflater.from(PurchaseOrderActivity.this);
        View finance_method = inflater.inflate(R.layout.supplier_list_dialog,null);

        Button btnAddSupplier;

        btnAddSupplier = finance_method.findViewById(R.id.btnAddSupplier);
        recyclerView = finance_method.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(PurchaseOrderActivity.this);
        linearLayoutManager.setReverseLayout(false);
        linearLayoutManager.setStackFromEnd(false);
        recyclerView.setLayoutManager(linearLayoutManager);

        showSuppliers();

        btnAddSupplier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddSupplierDialog();
            }
        });

        dialog.setView(finance_method);
        dialog.show();
    }

    private void showSuppliers() {
        Query query = companyRef.child(post_key).child("My Suppliers").orderByChild("timestamp");
        FirebaseRecyclerAdapter<SupplierModel, SuppliersViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<SupplierModel, SuppliersViewHolder>
                (SupplierModel.class, R.layout.supplier_item,SuppliersViewHolder.class,query) {
            @Override
            protected void populateViewHolder(final SuppliersViewHolder viewHolder, SupplierModel model, int position) {
                viewHolder.setSupplier_contact(model.getSupplier_contact());
                viewHolder.setSupplier_document_number(model.getSupplier_document_number());
                viewHolder.setSupplier_email(model.getSupplier_email());
                viewHolder.setSupplier_id(model.getSupplier_id());
                viewHolder.setSupplier_name(model.getSupplier_name());
                viewHolder.setSupplier_phone(model.getSupplier_phone());

                viewHolder.txtName.setText(viewHolder.my_supplier_name);
                viewHolder.txtPhone.setText("Teléfono: "+viewHolder.my_supplier_phone);
                viewHolder.txtEmail.setText("Correo electrónico: "+viewHolder.my_supplier_email);
                viewHolder.txtContact.setText("Contacto: "+viewHolder.my_supplier_contact);

                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        txtSupplier.setText("PROVEEDOR: "+viewHolder.my_supplier_name);
                        txtContact.setText("CONTACTO: "+viewHolder.my_supplier_contact);
                        txtPhone.setText("TELÉFONO: "+viewHolder.my_supplier_phone);
                        txtEmail.setText("CORREO: "+viewHolder.my_supplier_email);
                        supplier_id = viewHolder.my_supplier_id;
                        dialog.dismiss();
                    }
                });
            }
        };
        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    private void showAddSupplierDialog() {
        final AlertDialog dialog = new AlertDialog.Builder(PurchaseOrderActivity.this).create();

        LayoutInflater inflater = LayoutInflater.from(PurchaseOrderActivity.this);
        View finance_method = inflater.inflate(R.layout.add_supplier_dialog,null);

        final EditText edtName,edtDocumentNumber,edtEmail,edtPhoneNumber,edtContact;
        final CountryCodePicker ccpPhoneCode;
        Button btnAddCustomer;
        TextView txtCancel;
        final LinearLayout rootLayout;

        edtName = finance_method.findViewById(R.id.edtName);
        edtDocumentNumber = finance_method.findViewById(R.id.edtDocumentNumber);
        edtEmail = finance_method.findViewById(R.id.edtEmail);
        edtPhoneNumber = finance_method.findViewById(R.id.edtPhoneNumber);
        edtContact = finance_method.findViewById(R.id.edtContact);
        ccpPhoneCode = finance_method.findViewById(R.id.ccpPhoneCode);
        btnAddCustomer = finance_method.findViewById(R.id.btnAddCustomer);
        txtCancel = finance_method.findViewById(R.id.txtCancel);
        rootLayout = finance_method.findViewById(R.id.rootLayout);

        btnAddCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(edtName.getText().toString())) {
                    Snackbar.make(rootLayout,"Debes completar todos los datos",Snackbar.LENGTH_LONG);
                }
                else  if (TextUtils.isEmpty(edtDocumentNumber.getText().toString())) {
                    Snackbar.make(rootLayout,"Debes completar todos los datos",Snackbar.LENGTH_LONG);
                }
                else  if (TextUtils.isEmpty(edtEmail.getText().toString())) {
                    Snackbar.make(rootLayout,"Debes completar todos los datos",Snackbar.LENGTH_LONG);
                }
                else  if (TextUtils.isEmpty(edtPhoneNumber.getText().toString())) {
                    Snackbar.make(rootLayout,"Debes completar todos los datos",Snackbar.LENGTH_LONG);
                }
                else  if (TextUtils.isEmpty(edtContact.getText().toString())) {
                    Snackbar.make(rootLayout,"Debes completar todos los datos",Snackbar.LENGTH_LONG);
                } else {
                    Long tsLong = System.currentTimeMillis()/1000;
                    final String timestamp = tsLong.toString();

                    companyRef.child(post_key).child("My Suppliers").child(timestamp).child("supplier_name").setValue(edtName.getText().toString());
                    companyRef.child(post_key).child("My Suppliers").child(timestamp).child("supplier_document_number").setValue(edtDocumentNumber.getText().toString());
                    companyRef.child(post_key).child("My Suppliers").child(timestamp).child("supplier_email").setValue(edtEmail.getText().toString());
                    companyRef.child(post_key).child("My Suppliers").child(timestamp).child("supplier_phone").setValue(ccpPhoneCode.getSelectedCountryCode()+""+edtPhoneNumber.getText().toString());
                    companyRef.child(post_key).child("My Suppliers").child(timestamp).child("supplier_contact").setValue(edtContact.getText().toString());
                    companyRef.child(post_key).child("My Suppliers").child(timestamp).child("supplier_id").setValue(timestamp);
                    Toasty.success(PurchaseOrderActivity.this, "Registrado", Toast.LENGTH_LONG).show();
                    dialog.dismiss();

                }
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

    public static class SuppliersViewHolder extends RecyclerView.ViewHolder {
        View mView;
        String my_supplier_name,my_supplier_document_number,my_supplier_email,my_supplier_phone,my_supplier_contact,my_supplier_id;
        TextView txtName,txtPhone,txtEmail,txtContact;

        public SuppliersViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;

            txtName = mView.findViewById(R.id.txtName);
            txtPhone = mView.findViewById(R.id.txtPhone);
            txtEmail = mView.findViewById(R.id.txtEmail);
            txtContact = mView.findViewById(R.id.txtContact);

        }
        public void setSupplier_name(String supplier_name) {
            my_supplier_name = supplier_name;
        }


        public void setSupplier_document_number(String supplier_document_number) {
            my_supplier_document_number = supplier_document_number;
        }

        public void setSupplier_email(String supplier_email) {
            my_supplier_email = supplier_email;
        }


        public void setSupplier_phone(String supplier_phone) {
            my_supplier_phone = supplier_phone;
        }

        public void setSupplier_contact(String supplier_contact) {
            my_supplier_contact = supplier_contact;
        }

        public void setSupplier_id(String supplier_id) {
            my_supplier_id = supplier_id;
        }
    }

    public static class SupplierProductsViewHolder extends RecyclerView.ViewHolder {

        View mView;
        String my_item_description,my_item_name,my_item_price,my_item_quantity,my_item_total;
        TextView txtItemName,txtPrice,txtQuantity,txtTotal;

        public SupplierProductsViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;

            txtItemName = mView.findViewById(R.id.txtItemName);
            txtPrice = mView.findViewById(R.id.txtPrice);
            txtQuantity = mView.findViewById(R.id.txtQuantity);
            txtTotal = mView.findViewById(R.id.txtTotal);
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
                Bitmap imageBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
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
                int scaleFactor = Math.min(photoW / targetW, photoH / targetH);

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
