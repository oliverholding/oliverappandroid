package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Commercialization.BillsIssuing;

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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
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
import com.oalonedeveloper.oliver.oliverappandroidapp.Companies.ProductsModel;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;
import es.dmoral.toasty.Toasty;
import in.galaxyofandroid.spinerdialog.OnSpinerItemClick;
import in.galaxyofandroid.spinerdialog.SpinnerDialog;

public class CreateInvoiceActivity extends AppCompatActivity {

    TextView txtSocialReason,txtCompanyAddress,txtRuc,txtExpirationDate,txtIssueDate,txtCustomerName,txtDocumentNumber,txtObservation,txtTotalAmount,txtTaxes,txtSaleValue;
    String post_key;
    DatabaseReference companyRef, fromPath, toPath;
    String company_social_reason,company_address,company_ruc,currentPhotoPath,downloadUrl,image_verification,current_time,currentUid;
    ArrayList<String> bthDay =new ArrayList<>();
    SpinnerDialog bthDayDialog;
    Uri imageUri;
    ProgressDialog loadingBar;
    StorageReference userProfileImageRef;
    final static int Gallery_Pick = 2;
    FirebaseAuth mAuth;
    RecyclerView recyclerView;
    DecimalFormat decimalFormat;
    AlertDialog dialog_list;
    String expiration_day,expiration_month,expiration_year,customer_name,customer_document_number,observations,total_amount_st,total_taxes_st;
    RelativeLayout rootLayout;

    int day,month,year;

    ArrayList<String> bthMonth =new ArrayList<>();
    SpinnerDialog bthMonthDialog;

    ArrayList<String> bthYear =new ArrayList<>();
    SpinnerDialog bthYearDialog;

    Button btnAddProduct,btnRegisterSale;

    ImageView imgProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_invoice);

        expiration_day = "";
        expiration_month = "";
        expiration_year = "";
        customer_name = "";
        customer_document_number = "";
        observations = "";
        total_amount_st = "";
        total_taxes_st = "";


        txtSocialReason = findViewById(R.id.txtSocialReason);
        txtCompanyAddress = findViewById(R.id.txtCompanyAddress);
        txtRuc = findViewById(R.id.txtRuc);
        txtExpirationDate = findViewById(R.id.txtExpirationDate);
        txtIssueDate = findViewById(R.id.txtIssueDate);
        txtCustomerName = findViewById(R.id.txtCustomerName);
        txtDocumentNumber = findViewById(R.id.txtDocumentNumber);
        txtObservation = findViewById(R.id.txtObservation);
        btnAddProduct = findViewById(R.id.btnAddProduct);
        txtTotalAmount = findViewById(R.id.txtTotalAmount);
        txtTaxes = findViewById(R.id.txtTaxes);
        btnRegisterSale = findViewById(R.id.btnRegisterSale);
        txtSaleValue = findViewById(R.id.txtSaleValue);
        rootLayout = findViewById(R.id.rootLayout);

        loadingBar = new ProgressDialog(this);
        post_key = getIntent().getExtras().getString("post_key");
        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");
        userProfileImageRef = FirebaseStorage.getInstance().getReference().child("Product Images");
        mAuth = FirebaseAuth.getInstance();
        image_verification = "";
        decimalFormat = new DecimalFormat("0.00");
        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(false);
        linearLayoutManager.setStackFromEnd(false);
        recyclerView.setLayoutManager(linearLayoutManager);

        currentUid = mAuth.getCurrentUser().getUid();

        current_time = new SimpleDateFormat("HHmmss").format(Calendar.getInstance().getTime());

        txtExpirationDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showExpirationDateDialog();
            }
        });

        txtCustomerName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomerNameDialog();
            }
        });

        txtDocumentNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDocumentNumnerDialog();
            }
        });

        txtObservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showObservationsDialog();
            }
        });

        btnAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddProductDialog();
            }
        });


        Date date= new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        day = cal.get(Calendar.DAY_OF_MONTH);
        month = cal.get(Calendar.MONTH)+1;
        year = cal.get(Calendar.YEAR);

        txtIssueDate.setText("EMISIÓN: "+day+"/"+month+"/"+year);

        companyRef.child(post_key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                company_social_reason = dataSnapshot.child("company_social_reason").getValue().toString();
                company_address = dataSnapshot.child("company_address").getValue().toString();
                company_ruc = dataSnapshot.child("company_ruc").getValue().toString();

                txtSocialReason.setText(company_social_reason);
                txtCompanyAddress.setText(company_address);
                txtRuc.setText("RUC: "+company_ruc);

                showItemsBill();

                showTotalAmount();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        btnRegisterSale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (expiration_day.equals("")) {
                    Snackbar.make(rootLayout, "Debes ingresar el día de vencimiento", Snackbar.LENGTH_LONG).show();
                    return;
                }
                else if (expiration_month.equals("")) {
                    Snackbar.make(rootLayout, "Debes ingresar el mes de vencimiento", Snackbar.LENGTH_LONG).show();
                    return;
                } else if (expiration_year.equals("")) {
                    Snackbar.make(rootLayout, "Debes ingresar el año de vencimiento", Snackbar.LENGTH_LONG).show();
                    return;
                } else if (customer_name.equals("")) {
                    Snackbar.make(rootLayout, "Debes ingresar el nombre del cliente", Snackbar.LENGTH_LONG).show();
                    return;
                } else if (customer_document_number.equals("")) {
                    Snackbar.make(rootLayout, "Debes ingresar el DNI del cliente", Snackbar.LENGTH_LONG).show();
                    return;
                } else if (observations.equals("")) {
                    Snackbar.make(rootLayout, "Debes ingresar alguna observación", Snackbar.LENGTH_LONG).show();
                    return;
                } else if (total_amount_st.equals("")) {
                    Snackbar.make(rootLayout, "Debes registrar un item en la boleta", Snackbar.LENGTH_LONG).show();
                    return;
                } else {
                    registerBill();
                }
            }
        });
    }

    private void registerBill() {
        loadingBar.setTitle("Registrando Venta...");
        loadingBar.setMessage("Cargando...");
        loadingBar.show();
        loadingBar.setCanceledOnTouchOutside(false);
        loadingBar.setCancelable(false);

        current_time = new SimpleDateFormat("HHmmss").format(Calendar.getInstance().getTime());
        fromPath = FirebaseDatabase.getInstance().getReference().child("My Companies").child(post_key).child("Product Bill");
        toPath = FirebaseDatabase.getInstance().getReference().child("My Companies").child(post_key).child("My Bills").child(day+month+year+current_time).child("Product List");

        fromPath.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                toPath.setValue(dataSnapshot.getValue(), new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                        if (databaseError != null) {
                            Toast.makeText(CreateInvoiceActivity.this, "Hubo un Error", Toast.LENGTH_SHORT).show();
                        } else {
                            companyRef.child(post_key).child("My Bills").child(day+month+year+current_time).child("type").setValue("invoice");
                            companyRef.child(post_key).child("My Bills").child(day+month+year+current_time).child("company_id").setValue(post_key);
                            companyRef.child(post_key).child("My Bills").child(day+month+year+current_time).child("bill_currency").setValue("PEN");
                            companyRef.child(post_key).child("My Bills").child(day+month+year+current_time).child("bill_id").setValue(day+month+year+current_time);
                            companyRef.child(post_key).child("My Bills").child(day+month+year+current_time).child("expiration_day").setValue(expiration_day);
                            companyRef.child(post_key).child("My Bills").child(day+month+year+current_time).child("expiration_month").setValue(expiration_month);
                            companyRef.child(post_key).child("My Bills").child(day+month+year+current_time).child("expiration_year").setValue(expiration_year);
                            companyRef.child(post_key).child("My Bills").child(day+month+year+current_time).child("issuing_day").setValue(day);
                            companyRef.child(post_key).child("My Bills").child(day+month+year+current_time).child("issuing_month").setValue(month);
                            companyRef.child(post_key).child("My Bills").child(day+month+year+current_time).child("issuing_year").setValue(year);
                            companyRef.child(post_key).child("My Bills").child(day+month+year+current_time).child("customer_name").setValue(customer_name);
                            companyRef.child(post_key).child("My Bills").child(day+month+year+current_time).child("customer_document_number").setValue(customer_document_number);
                            companyRef.child(post_key).child("My Bills").child(day+month+year+current_time).child("bill_observations").setValue(observations);
                            companyRef.child(post_key).child("My Bills").child(day+month+year+current_time).child("bill_amount").setValue(total_amount_st);
                            companyRef.child(post_key).child("My Bills").child(day+month+year+current_time).child("bill_igv_taxes").setValue(total_taxes_st);
                            companyRef.child(post_key).child("My Bills").child(day+month+year+current_time).child("state").setValue("no_paid");
                            companyRef.child(post_key).child("My Bills").child(day+month+year+current_time).child("timestamp").setValue(ServerValue.TIMESTAMP);

                            fromPath.removeValue();
                            loadingBar.dismiss();

                            Intent intent = new Intent(CreateInvoiceActivity.this, SuccesfulBillActivity.class);
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

    private void showTotalAmount() {
        companyRef.child(post_key).child("Product Bill").addListenerForSingleValueEvent(new ValueEventListener() {
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

    private void showItemsBill() {
        Query query = companyRef.child(post_key).child("Product Bill").orderByChild("timestamp");
        FirebaseRecyclerAdapter<BillItemsModel, CompanyBillItemsViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<BillItemsModel, CompanyBillItemsViewHolder>
                (BillItemsModel.class, R.layout.bill_product_item, CompanyBillItemsViewHolder.class, query) {
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


                viewHolder.txtCode.setText(viewHolder.my_code);
                viewHolder.txtDiscount.setText(viewHolder.my_discount);
                viewHolder.txtProductName.setText(viewHolder.my_name);
                viewHolder.txtProductPrice.setText("S/ "+viewHolder.my_price);
                viewHolder.txtQuantity.setText(viewHolder.my_quantity);
                viewHolder.txtTotal.setText("S/ "+viewHolder.my_total);


                viewHolder.btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        companyRef.child(post_key).child("Product Bill").child(postKey).removeValue();
                        showTotalAmount();
                    }
                });



            }
        };
        recyclerView.setAdapter(firebaseRecyclerAdapter);


    }

    private void showAddProductDialog() {
        dialog_list = new AlertDialog.Builder(this).create();

        LayoutInflater inflater = LayoutInflater.from(this);
        View finance_method = inflater.inflate(R.layout.my_product_list_dialog,null);

        Button btnRegisterNewProduct;

        btnRegisterNewProduct = finance_method.findViewById(R.id.btnRegisterNewProduct);
        recyclerView = finance_method.findViewById(R.id.recyclerView);

        btnRegisterNewProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRegisterNewProductDialog();
            }

            private void showRegisterNewProductDialog() {
                final AlertDialog dialog = new AlertDialog.Builder(CreateInvoiceActivity.this).create();

                LayoutInflater inflater = LayoutInflater.from(CreateInvoiceActivity.this);
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
                            hashMap.put("uid",currentUid);
                            hashMap.put("code",day+month+year+current_time);
                            hashMap.put("product_name",edtProductName.getText().toString().toUpperCase());
                            hashMap.put("product_price",edtPrice.getText().toString());
                            hashMap.put("product_stock",edtStock.getText().toString());
                            hashMap.put("timestamp", ServerValue.TIMESTAMP);
                            companyRef.child(post_key).child("My Products").child(post_key+day+month+year+current_time).updateChildren(hashMap).addOnCompleteListener(new OnCompleteListener() {
                                @Override
                                public void onComplete(@NonNull Task task) {
                                    Toasty.success(CreateInvoiceActivity.this, "Producto Registrado", Toast.LENGTH_LONG).show();
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

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(false);
        linearLayoutManager.setStackFromEnd(false);
        recyclerView.setLayoutManager(linearLayoutManager);

        showCompanyProducts();

        dialog_list.setView(finance_method);
        dialog_list.show();
    }

    private void showCompanyProducts() {
        Query query = companyRef.child(post_key).child("My Products").orderByChild("timestamp");
        FirebaseRecyclerAdapter<ProductsModel,CompanyProductsViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<ProductsModel,CompanyProductsViewHolder>
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
                Picasso.with(CreateInvoiceActivity.this).load(viewHolder.my_product_image).fit().into(viewHolder.imgProduct);

                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showSetQuantityDialog();
                    }

                    private void showSetQuantityDialog() {
                        final AlertDialog dialog = new AlertDialog.Builder(CreateInvoiceActivity.this).create();

                        LayoutInflater inflater = LayoutInflater.from(CreateInvoiceActivity.this);
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

                        Picasso.with(CreateInvoiceActivity.this).load(viewHolder.my_product_image).fit().into(imgProductDialog);
                        txtProductName.setText("Cantidad para "+viewHolder.my_product_name);

                        btnRegister.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (TextUtils.isEmpty(edtQuantity.getText().toString())) {
                                    Snackbar.make(rootLayout, "Debes ingresar la cantidad", Snackbar.LENGTH_LONG).show();
                                    return;
                                } else {
                                    //Calculate total:
                                    double quantity_db = Double.parseDouble(edtQuantity.getText().toString());
                                    double price = Double.parseDouble(viewHolder.my_product_price);

                                    double total_db = quantity_db*price;
                                    double total_round = Math.round(total_db*100.0)/100.0;
                                    String total_st = decimalFormat.format(total_round);

                                    companyRef.child(post_key).child("Product Bill").child(postKey).child("quantity").setValue(edtQuantity.getText().toString());
                                    companyRef.child(post_key).child("Product Bill").child(postKey).child("measure").setValue("quantity");
                                    companyRef.child(post_key).child("Product Bill").child(postKey).child("code").setValue(viewHolder.my_code);
                                    companyRef.child(post_key).child("Product Bill").child(postKey).child("name").setValue(viewHolder.my_product_name);
                                    companyRef.child(post_key).child("Product Bill").child(postKey).child("price").setValue(viewHolder.my_product_price);
                                    companyRef.child(post_key).child("Product Bill").child(postKey).child("discount").setValue("0.00");
                                    companyRef.child(post_key).child("Product Bill").child(postKey).child("total").setValue(total_st);
                                    companyRef.child(post_key).child("Product Bill").child(postKey).child("timestamp").setValue(ServerValue.TIMESTAMP);
                                    showTotalAmount();
                                    dialog.dismiss();
                                    dialog_list.dismiss();
                                    Toasty.success(CreateInvoiceActivity.this, viewHolder.my_product_name+" agregado con éxito", Toast.LENGTH_LONG).show();
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

    public static class CompanyBillItemsViewHolder extends RecyclerView.ViewHolder {
        View mView;
        String my_code,my_discount,my_measure,my_name,my_price,my_quantity,my_total;
        TextView txtQuantity,txtCode,txtProductName,txtProductPrice,txtDiscount,txtTotal;
        ImageView btnDelete;


        public CompanyBillItemsViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;

            txtQuantity = mView.findViewById(R.id.txtQuantity);
            txtCode = mView.findViewById(R.id.txtCode);
            txtProductName = mView.findViewById(R.id.txtProductName);
            txtProductPrice = mView.findViewById(R.id.txtProductPrice);
            txtDiscount = mView.findViewById(R.id.txtDiscount);
            txtTotal = mView.findViewById(R.id.txtTotal);
            btnDelete = mView.findViewById(R.id.btnDelete);


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

    private void showObservationsDialog() {
        final AlertDialog dialog = new AlertDialog.Builder(this).create();

        LayoutInflater inflater = LayoutInflater.from(this);
        View finance_method = inflater.inflate(R.layout.add_observations_dialog,null);

        final RelativeLayout rootLayout;
        Button btnRegister;
        final EditText edtObservation;

        btnRegister = finance_method.findViewById(R.id.btnRegister);
        rootLayout = finance_method.findViewById(R.id.rootLayout);
        edtObservation = finance_method.findViewById(R.id.edtObservation);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(edtObservation.getText().toString())) {
                    Snackbar.make(rootLayout, "Debes ingresar una observación", Snackbar.LENGTH_LONG).show();
                    return;
                } else {
                    observations = edtObservation.getText().toString();
                    txtObservation.setText("OBSERVACIONES: "+edtObservation.getText().toString().toUpperCase());
                    dialog.dismiss();
                }
            }
        });

        dialog.setView(finance_method);
        dialog.show();
    }

    private void showDocumentNumnerDialog() {
        final AlertDialog dialog = new AlertDialog.Builder(this).create();

        LayoutInflater inflater = LayoutInflater.from(this);
        View finance_method = inflater.inflate(R.layout.add_ruc_number_dialog,null);

        Button btnRegister;
        final RelativeLayout rootLayout;
        final EditText edtDocumentNumber;

        edtDocumentNumber = finance_method.findViewById(R.id.edtDocumentNumber);
        btnRegister = finance_method.findViewById(R.id.btnRegister);
        rootLayout = finance_method.findViewById(R.id.rootLayout);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(edtDocumentNumber.getText().toString())) {
                    Snackbar.make(rootLayout, "Debes ingresar el RUC del cliente", Snackbar.LENGTH_LONG).show();
                    return;
                } else {
                    customer_document_number = edtDocumentNumber.getText().toString();
                    txtDocumentNumber.setText("RUC DEL CLIENTE: "+edtDocumentNumber.getText().toString().toUpperCase());
                    dialog.dismiss();
                }
            }
        });

        dialog.setView(finance_method);
        dialog.show();
    }

    private void showCustomerNameDialog() {
        final AlertDialog dialog = new AlertDialog.Builder(this).create();

        LayoutInflater inflater = LayoutInflater.from(this);
        View finance_method = inflater.inflate(R.layout.add_customer_name_dialog,null);

        final EditText edtName;
        Button btnRegister;
        final RelativeLayout rootLayout;

        edtName = finance_method.findViewById(R.id.edtName);
        btnRegister = finance_method.findViewById(R.id.btnRegister);
        rootLayout = finance_method.findViewById(R.id.rootLayout);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(edtName.getText().toString())) {
                    Snackbar.make(rootLayout, "Debes ingresar el nombre del cliente", Snackbar.LENGTH_LONG).show();
                    return;
                } else {
                    customer_name = edtName.getText().toString().toUpperCase();
                    txtCustomerName.setText("DESTINATARIO: "+edtName.getText().toString().toUpperCase());
                    dialog.dismiss();
                }
            }
        });

        dialog.setView(finance_method);
        dialog.show();
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

        bthYear.add("2020");bthYear.add("2021");bthYear.add("2022");bthYear.add("2023");bthYear.add("2024");bthYear.add("2025");bthYear.add("2026");bthYear.add("2027");bthYear.add("2028");bthYear.add("2029");
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
                if (TextUtils.isEmpty(edtBthDay.getText().toString())) {
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
                    txtExpirationDate.setText("VENCIMIENTO: "+edtBthDay.getText().toString()+"/"+edtBthMonth.getText().toString()+"/"+edtBthYear.getText().toString());
                    dialog.dismiss();
                }


            }
        });


        dialog.setView(finance_method);
        dialog.show();
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

        String path = MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, "Title", null);
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
