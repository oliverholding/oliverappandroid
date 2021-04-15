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
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
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
import com.hbb20.CountryCodePicker;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.SalesModule.MySellers.MySellersModel;
import com.oalonedeveloper.oliver.oliverappandroidapp.Companies.CompanyCustomersModel;
import com.oalonedeveloper.oliver.oliverappandroidapp.Companies.EconomicActivitiesModel;
import com.oalonedeveloper.oliver.oliverappandroidapp.Companies.ProductsModel;
import com.oalonedeveloper.oliver.oliverappandroidapp.Locations.PeruLocationsModel;
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

public class CreateInvoiceActivity extends AppCompatActivity {

    TextView txtSocialReason,txtCompanyAddress,txtRuc,txtExpirationDate,txtIssueDate,txtCustomerName,txtDocumentNumber,txtObservation,txtTotalAmount,txtTaxes,txtSaleValue;
    String post_key;
    DatabaseReference companyRef, fromPath,fromPath2, toPath,toPath2,peruLocations,economic_activities;
    String company_social_reason,company_address,company_ruc,currentPhotoPath,downloadUrl,image_verification,current_time,currentUid,customerPostKey,province_code,customer_info_exist,product_stock,current_product_stock,sellerPostKey;
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
    String expiration_day,expiration_month,expiration_year,customer_name,customer_document_number,observations,total_amount_st,total_taxes_st,bill_sale_type,exp_validation,current_customer_purchase;
    RelativeLayout rootLayout;
    RecyclerView recyclerView2,recyclerView3;
    AlertDialog dialog_customers,departmentDialog,sellerDialog;
    EditText edtSearch;

    double current_mont_year_sales,current_product_stock_db;
    double current_month_year_q;

    int day,month,year;
    long diff,expiration_days;

    ArrayList<String> bthMonth =new ArrayList<>();
    SpinnerDialog bthMonthDialog;

    ArrayList<String> bthYear =new ArrayList<>();
    SpinnerDialog bthYearDialog;

    Button btnAddProduct,btnRegisterSale,btnDepartment,btnProvince,btnDistrict,btnEconomicActivities,btnAddSeller;

    ImageView imgProduct;

    SpinnerDialog bthDayDialog2;

    SpinnerDialog bthMonthDialog2;

    ArrayList<String> bthYear2 =new ArrayList<>();
    SpinnerDialog bthYearDialog2;

    ArrayList<String> ocupations =new ArrayList<>();
    SpinnerDialog spinnerOcupation;

    ArrayList<String> academicDegrees =new ArrayList<>();
    SpinnerDialog spinnerAcademicDegrees;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_invoice);

        expiration_day = "";
        expiration_month = "";
        expiration_year = "";
        customer_name = "";
        customer_document_number = "";
        observations = "Sin Observaciones";
        total_amount_st = "";
        total_taxes_st = "";
        sellerPostKey = "";


        customer_info_exist = "false";

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
        btnAddSeller = findViewById(R.id.btnAddSeller);
        rootLayout = findViewById(R.id.rootLayout);

        loadingBar = new ProgressDialog(this);
        post_key = getIntent().getExtras().getString("post_key");
        bill_sale_type =getIntent().getExtras().getString("bill_sale_type");
        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");
        userProfileImageRef = FirebaseStorage.getInstance().getReference().child("Product Images");
        peruLocations= FirebaseDatabase.getInstance().getReference().child("Peru Locations");
        economic_activities = FirebaseDatabase.getInstance().getReference().child("Economic Activities");
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
                //showCustomerNameDialog();
                showCustomersDialog();
            }
        });

        txtDocumentNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (customer_name.equals("")) {
                    Snackbar.make(rootLayout, "Debes seleccionar a tu cliente primero", Snackbar.LENGTH_LONG).show();

                } else {
                    showDocumentNumnerDialog();
                }

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
                    Snackbar.make(rootLayout, "Debes ingresar el RUC del cliente", Snackbar.LENGTH_LONG).show();
                    return;
                } else if (observations.equals("")) {
                    Snackbar.make(rootLayout, "Debes ingresar alguna observación", Snackbar.LENGTH_LONG).show();
                    return;
                } else {
                    showAddProductDialog();
                }
            }
        });

        btnAddSeller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddSellersDialog();
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

        if (bill_sale_type.equals("cash_now")) {
            txtExpirationDate.setEnabled(false);
            txtExpirationDate.setText("VENTA AL CONTADO");
            expiration_day = day+"";
            expiration_month = month+"";
            expiration_year = year+"";

        } else if (bill_sale_type.equals("credit")) {

        }

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
                } else if (sellerPostKey.equals("")) {
                    Snackbar.make(rootLayout, "Debes registrar al vendedor de esta venta", Snackbar.LENGTH_LONG).show();
                    return;
                } else {
                    if (customer_info_exist.equals("true")) {
                        registerBill();
                    }
                    else if (customer_info_exist.equals("false")) {
                        showCustomerInfoDialog();
                    }

                }
            }
        });

    }

    private void showAddSellersDialog() {
        sellerDialog = new AlertDialog.Builder(CreateInvoiceActivity.this).create();

        LayoutInflater inflater = LayoutInflater.from(CreateInvoiceActivity.this);
        View finance_method = inflater.inflate(R.layout.add_seller_bill_dialog,null);

        Button btnRegisterSeller;

        btnRegisterSeller = finance_method.findViewById(R.id.btnRegisterSeller);

        recyclerView3 = finance_method.findViewById(R.id.recyclerView);
        recyclerView3.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView3.setLayoutManager(linearLayoutManager);

        showMySellers();

        btnRegisterSeller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRegisterSellerDialog();
            }

            private void showRegisterSellerDialog() {
                final AlertDialog dialog = new AlertDialog.Builder(CreateInvoiceActivity.this).create();

                LayoutInflater inflater = LayoutInflater.from(CreateInvoiceActivity.this);
                View finance_method = inflater.inflate(R.layout.register_seller_dialog,null);

                final EditText edtName,edtDocumentNumber,edtEmail,edtPhoneNumber;
                final CountryCodePicker ccpPhoneCode;
                Button btnAddSeller;

                edtName = finance_method.findViewById(R.id.edtName);
                edtDocumentNumber = finance_method.findViewById(R.id.edtDocumentNumber);
                edtEmail = finance_method.findViewById(R.id.edtEmail);
                edtPhoneNumber = finance_method.findViewById(R.id.edtPhoneNumber);
                ccpPhoneCode = finance_method.findViewById(R.id.ccpPhoneCode);
                btnAddSeller = finance_method.findViewById(R.id.btnAddSeller);

                btnAddSeller.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Calendar calForDate = Calendar.getInstance();
                        SimpleDateFormat currentDate = new SimpleDateFormat("dd-MM-yyyy");
                        String saveCurrentDate = currentDate.format(calForDate.getTime());

                        Calendar calForTime = Calendar.getInstance();
                        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss");
                        String saveCurrentTime = currentTime.format(calForTime.getTime());

                        HashMap hashMap = new HashMap();
                        hashMap.put("seller_name", edtName.getText().toString());
                        hashMap.put("seller_email", edtEmail.getText().toString()+"");
                        hashMap.put("seller_document_number", edtDocumentNumber.getText().toString());
                        hashMap.put("seller_phone", ccpPhoneCode.getSelectedCountryCode()+edtPhoneNumber.getText().toString());
                        hashMap.put("register_date", saveCurrentDate);
                        hashMap.put("register_time", saveCurrentTime);
                        hashMap.put("timestamp", ServerValue.TIMESTAMP);

                        companyRef.child(post_key).child("Sellers").child(edtDocumentNumber.getText().toString()).updateChildren(hashMap).addOnCompleteListener(new OnCompleteListener() {
                            @Override
                            public void onComplete(@NonNull Task task) {
                                Toasty.success(CreateInvoiceActivity.this, "Registrado", Toast.LENGTH_LONG).show();
                                dialog.dismiss();
                            }
                        });

                    }
                });

                dialog.setView(finance_method);
                dialog.show();
            }
        });

        sellerDialog.setView(finance_method);
        sellerDialog.show();
    }

    private void showMySellers() {
        Query query = companyRef.child(post_key).child("Sellers").orderByChild("timestamp");
        FirebaseRecyclerAdapter<MySellersModel, MySellersViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<MySellersModel, MySellersViewHolder>
                (MySellersModel.class, R.layout.seller_item, MySellersViewHolder.class,query) {
            @Override
            protected void populateViewHolder(final MySellersViewHolder viewHolder, MySellersModel model, int position) {
                final String postKey = getRef(position).getKey();
                viewHolder.setSeller_document_number(model.getSeller_document_number());
                viewHolder.setSeller_email(model.getSeller_email());
                viewHolder.setSeller_name(model.getSeller_name());
                viewHolder.setSeller_phone(model.getSeller_phone());

                viewHolder.txtName.setText(viewHolder.name);
                viewHolder.txtPhone.setText(viewHolder.phone);
                viewHolder.txtEmail.setText(viewHolder.email);
                viewHolder.txtDocumentNumber.setText(viewHolder.document_number);

                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sellerPostKey = postKey;
                        btnAddSeller.setText(viewHolder.name);
                        sellerDialog.dismiss();
                        Toasty.success(CreateInvoiceActivity.this, "Registrado", Toast.LENGTH_LONG).show();
                    }
                });

            }
        };
        recyclerView3.setAdapter(firebaseRecyclerAdapter);
    }

    public static class MySellersViewHolder extends RecyclerView.ViewHolder {

        View mView;
        TextView txtName,txtPhone,txtEmail,txtDocumentNumber;
        String email,name,phone,document_number;

        public MySellersViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;

            txtName = mView.findViewById(R.id.txtName);
            txtPhone = mView.findViewById(R.id.txtPhone);
            txtEmail = mView.findViewById(R.id.txtEmail);
            txtDocumentNumber = mView.findViewById(R.id.txtDocumentNumber);
        }

        public void setSeller_name(String seller_name) {
            name = seller_name;
        }

        public void setSeller_email(String seller_email) {
            email = seller_email;
        }


        public void setSeller_document_number(String seller_document_number) {
            document_number = seller_document_number;
        }


        public void setSeller_phone(String seller_phone) {
            phone = seller_phone;
        }
    }

    private void showDistrictDialog() {
        departmentDialog = new AlertDialog.Builder(this).create();

        LayoutInflater inflater = LayoutInflater.from(this);
        View finance_method = inflater.inflate(R.layout.departments_locations_dialog,null);

        edtSearch = finance_method.findViewById(R.id.edtSearch);
        recyclerView = finance_method.findViewById(R.id.recyclerView);

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(false);
        linearLayoutManager.setStackFromEnd(false);
        recyclerView.setLayoutManager(linearLayoutManager);

        showDistrictLocations();

        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                searchDistricts();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        departmentDialog.setView(finance_method);
        departmentDialog.show();
    }

    private void searchDistricts() {
        Query query = peruLocations.child("Districs").child(province_code).orderByChild("nombre_ubigeo").startAt(edtSearch.getText().toString()).endAt(edtSearch.getText().toString()+"\uf8ff");
        FirebaseRecyclerAdapter<PeruLocationsModel, LocationsViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<PeruLocationsModel, LocationsViewHolder>
                (PeruLocationsModel.class, R.layout.location_item, LocationsViewHolder.class, query) {
            @Override
            protected void populateViewHolder(final LocationsViewHolder viewHolder, PeruLocationsModel model, final int position) {
                viewHolder.setNombre_ubigeo(model.getNombre_ubigeo());
                viewHolder.setId_ubigeo(model.getId_ubigeo());

                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        btnDistrict.setText(viewHolder.location_name);
                        departmentDialog.dismiss();
                    }
                });
            }
        };
        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    private void showDistrictLocations() {
        Query query = peruLocations.child("Districs").child(province_code).orderByChild("nombre_ubigeo");
        FirebaseRecyclerAdapter<PeruLocationsModel, LocationsViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<PeruLocationsModel, LocationsViewHolder>
                (PeruLocationsModel.class, R.layout.location_item, LocationsViewHolder.class, query) {
            @Override
            protected void populateViewHolder(final LocationsViewHolder viewHolder, PeruLocationsModel model, final int position) {
                viewHolder.setNombre_ubigeo(model.getNombre_ubigeo());
                viewHolder.setId_ubigeo(model.getId_ubigeo());

                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        btnDistrict.setText(viewHolder.location_name);
                        departmentDialog.dismiss();
                    }
                });
            }
        };
        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    private void showProvinceDialog() {
        departmentDialog = new AlertDialog.Builder(this).create();

        LayoutInflater inflater = LayoutInflater.from(this);
        View finance_method = inflater.inflate(R.layout.departments_locations_dialog,null);

        edtSearch = finance_method.findViewById(R.id.edtSearch);
        recyclerView = finance_method.findViewById(R.id.recyclerView);

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(false);
        linearLayoutManager.setStackFromEnd(false);
        recyclerView.setLayoutManager(linearLayoutManager);

        showProvinceLocations();

        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                searchProvinces();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        departmentDialog.setView(finance_method);
        departmentDialog.show();

    }

    private void searchProvinces() {
        Query query = peruLocations.child("Provinces").child(province_code).orderByChild("nombre_ubigeo").startAt(edtSearch.getText().toString()).endAt(edtSearch.getText().toString()+"\uf8ff");
        FirebaseRecyclerAdapter<PeruLocationsModel,LocationsViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<PeruLocationsModel, LocationsViewHolder>
                (PeruLocationsModel.class, R.layout.location_item, LocationsViewHolder.class, query) {
            @Override
            protected void populateViewHolder(final LocationsViewHolder viewHolder, PeruLocationsModel model, final int position) {
                viewHolder.setNombre_ubigeo(model.getNombre_ubigeo());
                viewHolder.setId_ubigeo(model.getId_ubigeo());

                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        btnProvince.setText(viewHolder.location_name);
                        btnDistrict.setText("");
                        province_code = viewHolder.id_location;
                        departmentDialog.dismiss();
                    }
                });
            }
        };
        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    private void showProvinceLocations() {
        Query query = peruLocations.child("Provinces").child(province_code).orderByChild("nombre_ubigeo");
        FirebaseRecyclerAdapter<PeruLocationsModel, LocationsViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<PeruLocationsModel, LocationsViewHolder>
                (PeruLocationsModel.class, R.layout.location_item, LocationsViewHolder.class, query) {
            @Override
            protected void populateViewHolder(final LocationsViewHolder viewHolder, PeruLocationsModel model, final int position) {
                viewHolder.setNombre_ubigeo(model.getNombre_ubigeo());
                viewHolder.setId_ubigeo(model.getId_ubigeo());

                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        btnProvince.setText(viewHolder.location_name);
                        btnDistrict.setText("");
                        province_code = viewHolder.id_location;
                        departmentDialog.dismiss();
                    }
                });
            }
        };
        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    private void showDepartmentDilog() {
        departmentDialog = new AlertDialog.Builder(this).create();

        LayoutInflater inflater = LayoutInflater.from(this);
        View finance_method = inflater.inflate(R.layout.departments_locations_dialog,null);

        edtSearch = finance_method.findViewById(R.id.edtSearch);
        recyclerView = finance_method.findViewById(R.id.recyclerView);

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(false);
        linearLayoutManager.setStackFromEnd(false);
        recyclerView.setLayoutManager(linearLayoutManager);

        showLocations();

        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                searchDepartments();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        departmentDialog.setView(finance_method);
        departmentDialog.show();

    }

    private void searchDepartments() {
        Query query = peruLocations.child("Departments").orderByChild("nombre_ubigeo").startAt(edtSearch.getText().toString()).endAt(edtSearch.getText().toString()+"\uf8ff");
        FirebaseRecyclerAdapter<PeruLocationsModel, LocationsViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<PeruLocationsModel, LocationsViewHolder>
                (PeruLocationsModel.class, R.layout.location_item, LocationsViewHolder.class, query) {
            @Override
            protected void populateViewHolder(final LocationsViewHolder viewHolder, PeruLocationsModel model, int position) {
                viewHolder.setNombre_ubigeo(model.getNombre_ubigeo());
                viewHolder.setId_ubigeo(model.getId_ubigeo());

                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        btnDepartment.setText(viewHolder.location_name);
                        btnProvince.setText("");
                        btnDistrict.setText("");
                        province_code = viewHolder.id_location;
                        departmentDialog.dismiss();
                    }
                });
            }
        };
        recyclerView.setAdapter(firebaseRecyclerAdapter);

    }

    private void showLocations() {
        Query query = peruLocations.child("Departments").orderByChild("nombre_ubigeo");
        FirebaseRecyclerAdapter<PeruLocationsModel, LocationsViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<PeruLocationsModel, LocationsViewHolder>
                (PeruLocationsModel.class, R.layout.location_item,LocationsViewHolder.class, query) {
            @Override
            protected void populateViewHolder(final LocationsViewHolder viewHolder, PeruLocationsModel model, final int position) {
                viewHolder.setNombre_ubigeo(model.getNombre_ubigeo());
                viewHolder.setId_ubigeo(model.getId_ubigeo());

                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        btnDepartment.setText(viewHolder.location_name);
                        btnProvince.setText("");
                        btnDistrict.setText("");
                        province_code = viewHolder.id_location;
                        departmentDialog.dismiss();
                    }
                });
            }
        };
        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    public static class LocationsViewHolder extends RecyclerView.ViewHolder {
        View mView;
        String id_location,location_name;
        FirebaseAuth mAuth;
        DatabaseReference userRef;
        String currentUserID;

        public LocationsViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            mAuth = FirebaseAuth.getInstance();
            currentUserID = mAuth.getCurrentUser().getUid();
            userRef = FirebaseDatabase.getInstance().getReference().child("Users");
        }

        public void setNombre_ubigeo(String nombre_ubigeo) {
            TextView textView = mView.findViewById(R.id.txtName);
            textView.setText(nombre_ubigeo);
            location_name = nombre_ubigeo;
        }

        public void setId_ubigeo(String id_ubigeo) {
            id_location = id_ubigeo;
        }
    }

    private void showCustomerInfoDialog() {
        final AlertDialog dialog = new AlertDialog.Builder(CreateInvoiceActivity.this).create();

        LayoutInflater inflater = LayoutInflater.from(CreateInvoiceActivity.this);
        View finance_method = inflater.inflate(R.layout.customer_companies_info_request_invoice_dialog,null);

        final Button btnFinish,edtBthDay,edtBthMonth,edtBthYear;
        final EditText edtWorkerNumber;
        TextView txtOmit;
        final RelativeLayout rootLayout;

        btnFinish = finance_method.findViewById(R.id.btnFinish);
        rootLayout = finance_method.findViewById(R.id.rootLayout);
        btnEconomicActivities = finance_method.findViewById(R.id.btnEconomicActivities);
        edtBthDay = finance_method.findViewById(R.id.edtBthDay);
        edtBthMonth = finance_method.findViewById(R.id.edtBthMonth);
        edtBthYear = finance_method.findViewById(R.id.edtBthYear);
        edtWorkerNumber = finance_method.findViewById(R.id.edtWorkerNumber);
        txtOmit = finance_method.findViewById(R.id.txtOmit);
        btnDepartment = finance_method.findViewById(R.id.btnDepartment);
        btnProvince = finance_method.findViewById(R.id.btnProvince);
        btnDistrict = finance_method.findViewById(R.id.btnDistrict);

        btnEconomicActivities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showEconomicActtivitiesDialog();
            }
        });

        btnDepartment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDepartmentDilog();
            }
        });

        btnProvince.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(btnDepartment.getText().toString())) {
                    Snackbar.make(rootLayout, "Debes seleccionar un departamento o región primero", Snackbar.LENGTH_LONG).show();
                    return;

                } else if (TextUtils.isEmpty(btnDepartment.getText().toString())) {
                    Snackbar.make(rootLayout, "Debes seleccionar un departamento o región primero", Snackbar.LENGTH_LONG).show();
                    return;
                } else {
                    showProvinceDialog();
                }
            }
        });

        bthDay.add("1"); bthDay.add("2"); bthDay.add("3"); bthDay.add("4"); bthDay.add("5"); bthDay.add("6"); bthDay.add("7"); bthDay.add("8"); bthDay.add("9"); bthDay.add("10");
        bthDay.add("11"); bthDay.add("12"); bthDay.add("13"); bthDay.add("14"); bthDay.add("15"); bthDay.add("16"); bthDay.add("17"); bthDay.add("18"); bthDay.add("19"); bthDay.add("20");
        bthDay.add("21"); bthDay.add("22"); bthDay.add("23"); bthDay.add("24"); bthDay.add("25"); bthDay.add("26"); bthDay.add("27"); bthDay.add("28"); bthDay.add("29"); bthDay.add("30");
        bthDay.add("31");


        edtBthDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bthDayDialog2.showSpinerDialog();
            }
        });

        bthDayDialog2 = new SpinnerDialog(this,bthDay, "Selecciona el Día de Creación de la Empresa");
        bthDayDialog2.bindOnSpinerListener(new OnSpinerItemClick() {
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
                bthMonthDialog2.showSpinerDialog();
            }
        });

        bthMonthDialog2 = new SpinnerDialog(this,bthMonth, "Selecciona el Mes de Creación de la Empresa");
        bthMonthDialog2.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String item2, int position2) {
                edtBthMonth.setText(item2);

            }
        });



        bthYear2.add("2019");bthYear2.add("2018");bthYear2.add("2017");bthYear2.add("2016");bthYear2.add("2015");bthYear2.add("2014");bthYear2.add("2013");bthYear2.add("2012");bthYear2.add("2011");bthYear2.add("2010");
        bthYear2.add("2009");bthYear2.add("2008");bthYear2.add("2007");bthYear2.add("2006");bthYear2.add("2005");bthYear2.add("2004");bthYear2.add("2003");bthYear2.add("2002");bthYear2.add("2001");bthYear2.add("2000");
        bthYear2.add("1999");bthYear2.add("1998");bthYear2.add("1997");bthYear2.add("1996");bthYear2.add("1995");bthYear2.add("1994");bthYear2.add("1993");bthYear2.add("1992");bthYear2.add("1991");bthYear2.add("1990");
        bthYear2.add("1989");bthYear2.add("1988");bthYear2.add("1987");bthYear2.add("1986");bthYear2.add("1985");bthYear2.add("1984");bthYear2.add("1983");bthYear2.add("1982");bthYear2.add("1981");bthYear2.add("1980");
        bthYear2.add("1979");bthYear2.add("1978");bthYear2.add("1977");bthYear2.add("1976");bthYear2.add("1975");bthYear2.add("1974");bthYear2.add("1973");bthYear2.add("1972");bthYear2.add("1971");bthYear2.add("1970");
        bthYear2.add("1969");bthYear2.add("1968");bthYear2.add("1967");bthYear2.add("1966");bthYear2.add("1965");bthYear2.add("1964");bthYear2.add("1963");bthYear2.add("1962");bthYear2.add("1961");bthYear2.add("1960");
        bthYear2.add("1959");bthYear2.add("1958");bthYear2.add("1957");bthYear2.add("1956");bthYear2.add("1955");bthYear2.add("1954");bthYear2.add("1953");bthYear2.add("1952");bthYear2.add("1951");bthYear2.add("1950");
        bthYear2.add("1949");bthYear2.add("1948");bthYear2.add("1947");bthYear2.add("1946");bthYear2.add("1945");bthYear2.add("1944");bthYear2.add("1943");bthYear2.add("1942");bthYear2.add("1941");bthYear2.add("1940");
        bthYear2.add("1939");bthYear2.add("1938");bthYear2.add("1937");bthYear2.add("1936");bthYear2.add("1935");bthYear2.add("1934");bthYear2.add("1933");bthYear2.add("1932");bthYear2.add("1931");bthYear2.add("1930");

        edtBthYear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bthYearDialog2.showSpinerDialog();
            }
        });

        bthYearDialog2 = new SpinnerDialog(this,bthYear2, "Selecciona el año de Creación de la Empresa");
        bthYearDialog2.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String item2, int position2) {
                edtBthYear.setText(item2);
            }
        });


        btnDistrict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(btnDepartment.getText().toString())) {
                    Snackbar.make(rootLayout, "Debes seleccionar un departamento o región primero", Snackbar.LENGTH_LONG).show();
                    return;

                } else if (TextUtils.isEmpty(btnProvince.getText().toString())) {
                    Snackbar.make(rootLayout, "Debes seleccionar una provincia o región primero", Snackbar.LENGTH_LONG).show();
                    return;
                } else if (TextUtils.isEmpty(btnProvince.getText().toString())) {
                    Snackbar.make(rootLayout, "Debes seleccionar una provincia o región primero", Snackbar.LENGTH_LONG).show();
                    return;
                } else {
                    showDistrictDialog();
                }
            }
        });

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(btnEconomicActivities.getText().toString())) {
                    Snackbar.make(rootLayout,"Debes seleccionar el giro del negocio",Snackbar.LENGTH_LONG);
                }
                if (TextUtils.isEmpty(edtWorkerNumber.getText().toString())) {
                    Snackbar.make(rootLayout,"Debes ingresar el numero de trabajadores",Snackbar.LENGTH_LONG);
                } else if (TextUtils.isEmpty(edtBthDay.getText().toString())) {
                    Snackbar.make(rootLayout,"Debes seleccionar el día de creación del negocio",Snackbar.LENGTH_LONG);
                }
                else if (TextUtils.isEmpty(edtBthMonth.getText().toString())) {
                    Snackbar.make(rootLayout,"Debes seleccionar el mes de creación del negocio",Snackbar.LENGTH_LONG);
                } else if (TextUtils.isEmpty(edtBthYear.getText().toString())) {
                    Snackbar.make(rootLayout,"Debes seleccionar el año de creación del negocioe",Snackbar.LENGTH_LONG);
                } else if (TextUtils.isEmpty(btnDepartment.getText().toString())) {
                    Snackbar.make(rootLayout,"Debes seleccionar el departamento donde vive tu cliente",Snackbar.LENGTH_LONG);
                } else if (TextUtils.isEmpty(btnProvince.getText().toString())) {
                    Snackbar.make(rootLayout,"Debes seleccionar la provincia donde vive tu cliente",Snackbar.LENGTH_LONG);
                } else if (TextUtils.isEmpty(btnDistrict.getText().toString())) {
                    Snackbar.make(rootLayout, "Debes seleccionar el distrito donde vive tu cliente", Snackbar.LENGTH_LONG);
                } else {
                    companyRef.child(post_key).child("Customers").child(customerPostKey).child("customer_workers_number").setValue(edtWorkerNumber.getText().toString());
                    companyRef.child(post_key).child("Customers").child(customerPostKey).child("customer_economic_activity").setValue(btnEconomicActivities.getText().toString());
                    companyRef.child(post_key).child("Customers").child(customerPostKey).child("customer_bth_day").setValue(edtBthDay.getText().toString());
                    companyRef.child(post_key).child("Customers").child(customerPostKey).child("customer_bth_month").setValue(edtBthMonth.getText().toString());
                    companyRef.child(post_key).child("Customers").child(customerPostKey).child("customer_bth_year").setValue(edtBthYear.getText().toString());
                    companyRef.child(post_key).child("Customers").child(customerPostKey).child("customer_department").setValue(btnDepartment.getText().toString());
                    companyRef.child(post_key).child("Customers").child(customerPostKey).child("customer_province").setValue(btnProvince.getText().toString());
                    companyRef.child(post_key).child("Customers").child(customerPostKey).child("customer_district").setValue(btnDistrict.getText().toString());
                    dialog.dismiss();
                    registerBill();
                }
            }
        });

        txtOmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                registerBill();
            }
        });


        dialog.setView(finance_method);
        dialog.show();

    }

    private void showEconomicActtivitiesDialog() {
        departmentDialog = new AlertDialog.Builder(this).create();

        LayoutInflater inflater = LayoutInflater.from(this);
        View finance_method = inflater.inflate(R.layout.departments_locations_dialog,null);

        edtSearch = finance_method.findViewById(R.id.edtSearch);
        recyclerView = finance_method.findViewById(R.id.recyclerView);

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(false);
        linearLayoutManager.setStackFromEnd(false);
        recyclerView.setLayoutManager(linearLayoutManager);

        showEconomicActivities();

        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                searchEconomicActivities();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });




        departmentDialog.setView(finance_method);
        departmentDialog.show();
    }

    private void searchEconomicActivities() {
        Query query = economic_activities.orderByChild("name").startAt(edtSearch.getText().toString().toUpperCase()).endAt(edtSearch.getText().toString().toUpperCase()+"\uf8ff");
        FirebaseRecyclerAdapter<EconomicActivitiesModel, EconomicActivitiesViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<EconomicActivitiesModel, EconomicActivitiesViewHolder>
                (EconomicActivitiesModel.class, R.layout.location_item, EconomicActivitiesViewHolder.class, query) {
            @Override
            protected void populateViewHolder(final EconomicActivitiesViewHolder viewHolder, EconomicActivitiesModel model, final int position) {
                viewHolder.setName(model.getName());

                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        btnEconomicActivities.setText(viewHolder.act_name);
                        departmentDialog.dismiss();
                    }
                });
            }
        };
        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    private void showEconomicActivities() {
        Query query = economic_activities.orderByValue();
        FirebaseRecyclerAdapter<EconomicActivitiesModel, EconomicActivitiesViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<EconomicActivitiesModel, EconomicActivitiesViewHolder>
                (EconomicActivitiesModel.class, R.layout.location_item, EconomicActivitiesViewHolder.class, query) {
            @Override
            protected void populateViewHolder(final EconomicActivitiesViewHolder viewHolder, EconomicActivitiesModel model, final int position) {
                final String postKey = getRef(position).getKey();
                viewHolder.setName(model.getName());

                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        btnEconomicActivities.setText(viewHolder.act_name);
                        departmentDialog.dismiss();
                    }
                });
            }
        };
        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    public static class EconomicActivitiesViewHolder extends RecyclerView.ViewHolder {
        View mView;
        TextView txtName;
        String act_name;

        public EconomicActivitiesViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            txtName = mView.findViewById(R.id.txtName);
        }
        public void setName(String name) {
            TextView textView = mView.findViewById(R.id.txtName);
            textView.setText(name);
            act_name = name;
        }


    }

    private void showCustomersDialog() {
        dialog_customers = new AlertDialog.Builder(CreateInvoiceActivity.this).create();

        LayoutInflater inflater = LayoutInflater.from(CreateInvoiceActivity.this);
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
                final AlertDialog dialog = new AlertDialog.Builder(CreateInvoiceActivity.this).create();

                LayoutInflater inflater = LayoutInflater.from(CreateInvoiceActivity.this);
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
                                Toasty.success(CreateInvoiceActivity.this, "Registrado", Toast.LENGTH_LONG).show();
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
        Query query = companyRef.child(post_key).child("Customers").orderByChild("customer_type").equalTo("Empresa");
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
                                txtCustomerName.setText("DESTINATARIO: "+customer_name.toUpperCase());
                                if (dataSnapshot.hasChild("customer_document_number")) {
                                    customer_document_number = dataSnapshot.child("customer_document_number").getValue().toString();
                                    txtDocumentNumber.setText("RUC:" + customer_document_number);

                                } else {
                                    customer_document_number = "";
                                    txtDocumentNumber.setText("RUC: COMPLETAR");
                                }

                                if (dataSnapshot.hasChild("customer_bth_day")) {
                                    customer_info_exist = "true";
                                } else {
                                    customer_info_exist = "false";
                                }

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

    private void registerBill() {
        loadingBar.setTitle("Registrando Venta...");
        loadingBar.setMessage("Cargando...");
        loadingBar.show();
        loadingBar.setCanceledOnTouchOutside(false);
        loadingBar.setCancelable(false);

        current_time = new SimpleDateFormat("HHmmss").format(Calendar.getInstance().getTime());
        final String billId = day+""+month+""+year+""+current_time;
        fromPath = FirebaseDatabase.getInstance().getReference().child("My Companies").child(post_key).child("Product Bill");
        fromPath2 = FirebaseDatabase.getInstance().getReference().child("My Companies").child(post_key).child("Sale Processing");
        toPath = FirebaseDatabase.getInstance().getReference().child("My Companies").child(post_key).child("My Bills").child(billId).child("Product List");
        toPath2 = FirebaseDatabase.getInstance().getReference().child("My Companies").child(post_key).child("My Products");

        fromPath.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                toPath.setValue(dataSnapshot.getValue(), new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                        if (databaseError != null) {
                            Toast.makeText(CreateInvoiceActivity.this, "Hubo un Error", Toast.LENGTH_SHORT).show();
                        } else {
                            if (bill_sale_type.equals("cash_now")) {
                                companyRef.child(post_key).child("My Bills").child(billId).child("expiration_day").setValue(day+"");
                                companyRef.child(post_key).child("My Bills").child(billId).child("expiration_month").setValue(month+"");
                                companyRef.child(post_key).child("My Bills").child(billId).child("expiration_year").setValue(year+"");
                                companyRef.child(post_key).child("My Bills").child(billId).child("state").setValue("paid");

                            } else if (bill_sale_type.equals("credit")) {
                                companyRef.child(post_key).child("My Bills").child(billId).child("expiration_day").setValue(expiration_day);
                                companyRef.child(post_key).child("My Bills").child(billId).child("expiration_month").setValue(expiration_month);
                                companyRef.child(post_key).child("My Bills").child(billId).child("expiration_year").setValue(expiration_year);
                                companyRef.child(post_key).child("My Bills").child(billId).child("state").setValue("no_paid");
                            }
                            companyRef.child(post_key).child("My Bills").child(billId).child("type").setValue("invoice");
                            companyRef.child(post_key).child("My Bills").child(billId).child("company_id").setValue(post_key);
                            companyRef.child(post_key).child("My Bills").child(billId).child("bill_currency").setValue("PEN");
                            companyRef.child(post_key).child("My Bills").child(billId).child("bill_id").setValue(billId);

                            companyRef.child(post_key).child("My Bills").child(billId).child("issuing_day").setValue(day);
                            companyRef.child(post_key).child("My Bills").child(billId).child("issuing_month").setValue(month);
                            companyRef.child(post_key).child("My Bills").child(billId).child("issuing_year").setValue(year);
                            companyRef.child(post_key).child("My Bills").child(billId).child("customer_id").setValue(customerPostKey);
                            companyRef.child(post_key).child("My Bills").child(billId).child("customer_name").setValue(customer_name);
                            companyRef.child(post_key).child("My Bills").child(billId).child("customer_document_number").setValue(customer_document_number);
                            companyRef.child(post_key).child("My Bills").child(billId).child("bill_observations").setValue(observations);
                            companyRef.child(post_key).child("My Bills").child(billId).child("bill_amount").setValue(total_amount_st);
                            companyRef.child(post_key).child("My Bills").child(billId).child("bill_igv_taxes").setValue(total_taxes_st);
                            companyRef.child(post_key).child("My Bills").child(billId).child("seller_id").setValue(sellerPostKey);

                            companyRef.child(post_key).child("Customers").child(customerPostKey).child("Purchased").child(billId).child("bill_amount").setValue(total_amount_st);

                            companyRef.child(post_key).child("My Bills").child(billId).child("timestamp").setValue(ServerValue.TIMESTAMP);


                            companyRef.child(post_key).child("Sale Processing").addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {


                                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                                        Map<Double, Object> map = (Map<Double, Object>) ds.getValue();
                                        Object quantity = map.get(year+""+ month + "quantity");
                                        Object sales = map.get(year+""+ month + "sales");

                                        companyRef.child(post_key).child("My Products").child(ds.getKey()).child(year+""+ month + "quantity").setValue(quantity);
                                        companyRef.child(post_key).child("My Products").child(ds.getKey()).child("Sales Quantity").child(year+""+ month + "quantity").setValue(quantity);

                                        companyRef.child(post_key).child("My Products").child(ds.getKey()).child(year+""+ month + "sales").setValue(sales);
                                        companyRef.child(post_key).child("My Products").child(ds.getKey()).child("Sales Period").child(year+""+ month + "sales").setValue(sales);

                                        Object stock = map.get("product_stock");

                                        companyRef.child(post_key).child("My Products").child(ds.getKey()).child("product_stock").setValue(stock+"");

                                        Object quantity_purchased = map.get("quantity_purchased");
                                        companyRef.child(post_key).child("My Products").child(ds.getKey()).child("Buyers").child("Companies").child(customerPostKey).child("quantity_purchased").setValue(quantity_purchased);
                                    }
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });

                            fromPath.removeValue();
                            fromPath2.removeValue();
                            companyRef.child(post_key).child("Sale Processing").removeValue();
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
                        companyRef.child(post_key).child("Sale Processing").child(postKey).removeValue();
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
                            hashMap.put("company_id",post_key);
                            hashMap.put("code",day+""+month+""+year+""+current_time);
                            hashMap.put("product_name",edtProductName.getText().toString().toUpperCase());
                            hashMap.put("product_price",edtPrice.getText().toString());
                            hashMap.put("product_stock",edtStock.getText().toString());
                            hashMap.put("timestamp", ServerValue.TIMESTAMP);
                            companyRef.child(post_key).child("My Products").child(post_key+day+""+month+""+year+""+current_time).updateChildren(hashMap).addOnCompleteListener(new OnCompleteListener() {
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

                                    companyRef.child(post_key).child("Product Bill").child(postKey).child("quantity").setValue(edtQuantity.getText().toString());
                                    companyRef.child(post_key).child("Product Bill").child(postKey).child("measure").setValue("quantity");
                                    companyRef.child(post_key).child("Product Bill").child(postKey).child("code").setValue(viewHolder.my_code);
                                    companyRef.child(post_key).child("Product Bill").child(postKey).child("name").setValue(viewHolder.my_product_name);
                                    companyRef.child(post_key).child("Product Bill").child(postKey).child("price").setValue(viewHolder.my_product_price);
                                    companyRef.child(post_key).child("Product Bill").child(postKey).child("discount").setValue("0.00");
                                    companyRef.child(post_key).child("Product Bill").child(postKey).child("total").setValue(total_st);
                                    companyRef.child(post_key).child("Product Bill").child(postKey).child("product_id").setValue(postKey);
                                    companyRef.child(post_key).child("Product Bill").child(postKey).child("timestamp").setValue(ServerValue.TIMESTAMP);

                                    Date date = new Date();
                                    Calendar cal = Calendar.getInstance();
                                    cal.setTime(date);
                                    day = cal.get(Calendar.DAY_OF_MONTH);
                                    month = cal.get(Calendar.MONTH) + 1;
                                    year = cal.get(Calendar.YEAR);

                                    companyRef.child(post_key).child("My Products").child(postKey).addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {
                                            if (dataSnapshot.hasChild(year + "" + month + "sales")) {
                                                current_mont_year_sales = dataSnapshot.child(year + "" + month + "sales").getValue(Double.class);
                                                current_mont_year_sales = current_mont_year_sales + total_round;
                                                companyRef.child(post_key).child("Sale Processing").child(postKey).child(year + "" + month + "sales").setValue(current_mont_year_sales);

                                            } else {
                                                current_mont_year_sales = total_round;
                                                companyRef.child(post_key).child("Sale Processing").child(postKey).child(year + "" + month + "sales").setValue(current_mont_year_sales);
                                            }

                                            if (dataSnapshot.hasChild(year + "" + month + "quantity")) {
                                                current_mont_year_sales = dataSnapshot.child(year + "" + month + "quantity").getValue(Double.class);
                                                current_month_year_q = current_mont_year_sales + q;
                                                companyRef.child(post_key).child("Sale Processing").child(postKey).child(year + "" + month + "quantity").setValue(current_month_year_q);

                                            } else {
                                                current_month_year_q = q;
                                                companyRef.child(post_key).child("Sale Processing").child(postKey).child(year + "" + month + "quantity").setValue(current_month_year_q);
                                            }

                                            product_stock = dataSnapshot.child("product_stock").getValue().toString();

                                            double stock_db = Double.parseDouble(product_stock);
                                            double current_stock = stock_db - q;

                                            companyRef.child(post_key).child("Sale Processing").child(postKey).child("product_stock").setValue(current_stock);

                                            if (dataSnapshot.child("Buyers").child("Companies").hasChild(customerPostKey)) {
                                                current_customer_purchase = dataSnapshot.child("Buyers").child("Companies").child(customerPostKey).child("quantity_purchased").getValue().toString();
                                                double current_customer_purchase_db = Double.parseDouble(current_customer_purchase);
                                                double new_customer_purchase = current_customer_purchase_db + q;
                                                companyRef.child(post_key).child("Sale Processing").child(postKey).child("quantity_purchased").setValue(new_customer_purchase+"");
                                            } else {
                                                companyRef.child(post_key).child("Sale Processing").child(postKey).child("quantity_purchased").setValue(q+"");
                                            }

                                        }

                                        @Override
                                        public void onCancelled(DatabaseError databaseError) {

                                        }
                                    });


                                    showTotalAmount();
                                    dialog.dismiss();
                                    dialog_list.dismiss();
                                    Toasty.success(CreateInvoiceActivity.this, viewHolder.my_product_name + " agregado con éxito", Toast.LENGTH_LONG).show();
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
                } else {
                    customer_document_number = edtDocumentNumber.getText().toString();
                    txtDocumentNumber.setText("RUC: "+customer_document_number);
                    companyRef.child(post_key).child("Customers").child(customerPostKey).child("customer_document_number").setValue(customer_document_number);
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
                    txtCustomerName.setText("DESTINATARIO: "+customer_name.toUpperCase());
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
