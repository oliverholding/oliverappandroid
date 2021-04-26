package com.oalonedeveloper.oliver.oliverappandroidapp.FinancialManagement.CompanyLendingProduct;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.oalonedeveloper.oliver.oliverappandroidapp.FinancialManagement.LendingProduct.DocumentRequiredModel;
import com.oalonedeveloper.oliver.oliverappandroidapp.FinancialManagement.LendingProduct.FileUploadedModel;
import com.oalonedeveloper.oliver.oliverappandroidapp.FinancialManagement.LendingProduct.LoanRequestActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.FinancialManagement.LendingProduct.LoanRequestSentSuccessfullyActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;
import es.dmoral.toasty.Toasty;
import in.galaxyofandroid.spinerdialog.OnSpinerItemClick;
import in.galaxyofandroid.spinerdialog.SpinnerDialog;

public class CompanyLoanRequestActivity extends AppCompatActivity {

    String product_key,company_id,institution_key, financial_institution_name,financial_institution_image,financial_institution_background_image,currentUid, downloadUrl, product_img,document_number,
            product_name,product_tea_st;
    TextView txtProductName,txtFinancialInstitutionName,txtText1,txtText2,txtSimulationMessage;
    DatabaseReference financialInstitutionsRef,expressLoanRef,toPath,userRef;
    ImageView imgBackground,imgBackgroundButton,btnArrowSimulation;
    CircleImageView imgProductImage,imgState1;
    LinearLayout btnRequestLoan;
    RecyclerView recyclerView;
    LinearLayout simulationLayout;
    RelativeLayout rootLayout;
    String item_verify_1;
    ProgressDialog loadingBar;
    StorageReference mStorage;
    String doc_path;
    CardView simulationCardView;
    double desgravamen_rate,product_fixed_fee;

    static final int RESULT_LOAD_IMAGE = 1;

    EditText edtAmount;
    RadioButton rdPen,rdUsd;
    Button btnPaymentMonth,btnGraceMonth,btnFinish,btnSendRequest;
    TextView txtPayment;
    double product_tea,product_min_capital;
    int product_min_month,product_max_month,day,month,year;

    ArrayList<String> quotes =new ArrayList<>();
    SpinnerDialog spinnerQuotes;
    ArrayList<String> gracesPeriod =new ArrayList<>();
    SpinnerDialog spinnerGrace;

    DecimalFormat decimalFormat,decimalFormat2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_loan_request);

        company_id = getIntent().getExtras().getString("company_id");

        currentUid = company_id;

        product_key = getIntent().getExtras().getString("product_key");
        institution_key = getIntent().getExtras().getString("institution_key");
        financialInstitutionsRef = FirebaseDatabase.getInstance().getReference().child("Financial Institutions");
        expressLoanRef = FirebaseDatabase.getInstance().getReference().child("My Companies").child(currentUid).child(institution_key).child(product_key);
        toPath = FirebaseDatabase.getInstance().getReference().child("Financial Institutions").child(institution_key).child("Company Loan Requests");
        userRef = FirebaseDatabase.getInstance().getReference().child("My Companies");
        mStorage = FirebaseStorage.getInstance().getReference();

        decimalFormat = new DecimalFormat("0.00");
        decimalFormat2 = new DecimalFormat("0,000.00");

        txtProductName = findViewById(R.id.txtProductName);
        txtFinancialInstitutionName = findViewById(R.id.txtFinancialInstitutionName);
        imgBackground = findViewById(R.id.imgBackground);
        imgProductImage = findViewById(R.id.imgProductImage);
        imgBackgroundButton = findViewById(R.id.imgBackgroundButton);
        btnRequestLoan = findViewById(R.id.btnRequestLoan);
        btnArrowSimulation = findViewById(R.id.btnArrowSimulation);
        simulationLayout = findViewById(R.id.simulationLayout);
        btnFinish = findViewById(R.id.btnFinish);
        imgState1 = findViewById(R.id.imgState1);
        txtSimulationMessage = findViewById(R.id.txtSimulationMessage);
        simulationCardView = findViewById(R.id.simulationCardView);
        btnSendRequest = findViewById(R.id.btnSendRequest);
        txtText1 = findViewById(R.id.txtText1);
        txtText2 = findViewById(R.id.txtText2);

        loadingBar = new ProgressDialog(this);

        item_verify_1 = "close";
        simulationLayout.setVisibility(View.GONE);

        btnArrowSimulation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (item_verify_1.equals("close")) {
                    btnArrowSimulation.setImageResource(R.drawable.blue_arrow_up_vector_asset);
                    simulationLayout.setVisibility(View.VISIBLE);
                    item_verify_1 = "open";

                } else if (item_verify_1.equals("open")) {
                    btnArrowSimulation.setImageResource(R.drawable.blue_arrow_down_vector_asset);
                    simulationLayout.setVisibility(View.GONE);
                    item_verify_1 = "close";
                }
            }
        });

        edtAmount = findViewById(R.id.edtAmount);
        rdPen = findViewById(R.id.rdPen);
        rdUsd = findViewById(R.id.rdUsd);
        btnPaymentMonth = findViewById(R.id.btnPaymentMonth);
        btnGraceMonth = findViewById(R.id.btnGraceMonth);
        txtPayment = findViewById(R.id.txtPayment);

        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(false);
        linearLayoutManager.setStackFromEnd(false);
        recyclerView.setLayoutManager(linearLayoutManager);

        btnSendRequest.setVisibility(View.GONE);

        financialInstitutionsRef.child(institution_key).child("Company Products").child(product_key).child("Required Documentation").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                final long count1 = dataSnapshot.getChildrenCount();

                expressLoanRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        long count2 = dataSnapshot.getChildrenCount();

                        if (count1 + 1 == count2) {
                            btnSendRequest.setVisibility(View.VISIBLE);
                            txtText2.setText("");
                            txtText2.setBackgroundResource(R.drawable.check_azul);
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

        expressLoanRef.child("Simulation").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    imgState1.setImageResource(R.color.greenColor);

                    if (dataSnapshot.hasChild("requested_amount") && dataSnapshot.hasChild("requested_currency") && dataSnapshot.hasChild("requested_month")) {
                        String requested_amount = dataSnapshot.child("requested_amount").getValue().toString();
                        String requested_currency = dataSnapshot.child("requested_currency").getValue().toString();
                        String requested_month = dataSnapshot.child("requested_month").getValue().toString();

                        if (requested_currency.equals("PEN")) {
                            txtSimulationMessage.setText("Solicitud de S/ "+requested_amount+" a "+requested_month+" meses");
                        }
                        if (requested_currency.equals("USD")) {
                            txtSimulationMessage.setText("Solicitud de $ "+requested_amount+" a "+requested_month+" meses");
                        }
                        txtSimulationMessage.setTextColor(Color.GREEN);
                        //btnSendRequest.setVisibility(View.VISIBLE);
                        txtText1.setText("");
                        txtText1.setBackgroundResource(R.drawable.check_azul);

                    }


                } else {
                    imgState1.setImageResource(R.color.redColor);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        financialInstitutionsRef.child(institution_key).child("Company Products").child(product_key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                product_tea_st = dataSnapshot.child("product_tea").getValue().toString();
                product_tea = Double.parseDouble(product_tea_st);
                product_min_capital = dataSnapshot.child("product_min_capital").getValue(Double.class);

                product_min_month = dataSnapshot.child("product_min_month").getValue(Integer.class);
                product_max_month = dataSnapshot.child("product_max_month").getValue(Integer.class);

                desgravamen_rate = dataSnapshot.child("desgravamen_rate").getValue(Double.class);
                product_fixed_fee = dataSnapshot.child("product_fixed_fee").getValue(Double.class);

                product_img = dataSnapshot.child("product_img").getValue().toString();
                product_name = dataSnapshot.child("product_name").getValue().toString();

                btnPaymentMonth.setText("12");
                btnGraceMonth.setText(product_min_month+"");
                edtAmount.setText(product_min_capital+"");
                rdPen.setChecked(true);

                simulateQuote();

                for (int i = product_min_month; i < product_max_month+1; ++i) {
                    gracesPeriod.add(i+"");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        userRef.child(currentUid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                document_number = dataSnapshot.child("company_ruc").getValue().toString();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        edtAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(edtAmount.getText().toString())) {
                    txtPayment.setText("0.00");
                } else if (!TextUtils.isEmpty(edtAmount.getText().toString())&&!TextUtils.isEmpty(btnPaymentMonth.getText().toString())&&!TextUtils.isEmpty(btnGraceMonth.getText().toString())){
                    simulateQuote();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        rootLayout = findViewById(R.id.rootLayout);

        financialInstitutionsRef.child(institution_key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                financial_institution_name = dataSnapshot.child("financial_institution_name").getValue().toString();
                financial_institution_image = dataSnapshot.child("financial_institution_image").getValue().toString();
                financial_institution_background_image = dataSnapshot.child("financial_institution_background_image").getValue().toString();
                txtFinancialInstitutionName.setText("Por "+financial_institution_name);
                Picasso.with(CompanyLoanRequestActivity.this).load(financial_institution_background_image).fit().into(imgBackground);

                financialInstitutionsRef.child(institution_key).child("Company Products").child(product_key).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        product_name = dataSnapshot.child("product_name").getValue().toString();
                        product_img = dataSnapshot.child("product_img").getValue().toString();

                        Picasso.with(CompanyLoanRequestActivity.this).load(product_img).fit().into(imgProductImage);
                        txtProductName.setText(product_name);

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

        quotes.add("1");quotes.add("2");quotes.add("3");quotes.add("4");quotes.add("5");quotes.add("6");quotes.add("7");quotes.add("8");quotes.add("9");quotes.add("10");
        quotes.add("11");quotes.add("12");quotes.add("13");quotes.add("14");quotes.add("15");quotes.add("16");quotes.add("17");quotes.add("18");quotes.add("19");quotes.add("20");
        quotes.add("21");quotes.add("22");quotes.add("23");quotes.add("24");quotes.add("25");quotes.add("26");quotes.add("27");quotes.add("28");quotes.add("29");quotes.add("30");
        quotes.add("31");quotes.add("32");quotes.add("33");quotes.add("34");quotes.add("35");quotes.add("36");quotes.add("37");quotes.add("38");quotes.add("39");quotes.add("40");
        quotes.add("41");quotes.add("42");quotes.add("43");quotes.add("44");quotes.add("45");quotes.add("46");quotes.add("47");quotes.add("48");



        btnPaymentMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spinnerQuotes.showSpinerDialog();
            }
        });

        spinnerQuotes = new SpinnerDialog(CompanyLoanRequestActivity.this,quotes,"Selecciona el número de cuotas");
        spinnerQuotes.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String item, int i) {

                btnPaymentMonth.setText(item);
                simulateQuote();

            }
        });

        btnGraceMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spinnerGrace.showSpinerDialog();
            }
        });

        spinnerGrace = new SpinnerDialog(CompanyLoanRequestActivity.this,gracesPeriod,"Selecciona el período de gracia");
        spinnerGrace.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String item, int i) {

                btnGraceMonth.setText(item);
                simulateQuote();

            }
        });

        rdPen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                simulateQuote();
            }
        });
        rdUsd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                simulateQuote();
            }
        });

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnArrowSimulation.setImageResource(R.drawable.blue_arrow_down_vector_asset);
                simulationLayout.setVisibility(View.GONE);
                item_verify_1 = "close";
            }
        });

        showDocumentation();

        btnSendRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expressLoanRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        btnSendRequest.setEnabled(false);
                        btnSendRequest.setText("Enviando Solicitud...");

                        Long tsLong = System.currentTimeMillis()/1000;
                        final String timestamp = tsLong.toString();

                        Calendar calForTime = Calendar.getInstance();
                        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss");
                        final String saveCurrentTime = currentTime.format(calForTime.getTime());

                        Date date= new Date();
                        Calendar cal = Calendar.getInstance();
                        cal.setTime(date);
                        day = cal.get(Calendar.DAY_OF_MONTH);
                        month = cal.get(Calendar.MONTH)+1;
                        year = cal.get(Calendar.YEAR);



                        toPath.child(timestamp+currentUid).setValue(dataSnapshot.getValue(), new DatabaseReference.CompletionListener() {
                            @Override
                            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                                if (databaseError != null) {
                                    Toast.makeText(CompanyLoanRequestActivity.this, "Hubo un Error", Toast.LENGTH_SHORT).show();
                                } else {
                                    toPath.child(timestamp+currentUid).child("customer_id").setValue(currentUid);
                                    toPath.child(timestamp+currentUid).child("desgravamen_rate").setValue(desgravamen_rate);
                                    toPath.child(timestamp+currentUid).child("document_number").setValue(document_number);
                                    toPath.child(timestamp+currentUid).child("operation_id").setValue(year+""+month+""+day+":"+saveCurrentTime);
                                    toPath.child(timestamp+currentUid).child("product_fixed_fee").setValue(product_fixed_fee);
                                    toPath.child(timestamp+currentUid).child("product_id").setValue(product_key);
                                    toPath.child(timestamp+currentUid).child("product_img").setValue(product_img);
                                    toPath.child(timestamp+currentUid).child("product_name").setValue(product_name);
                                    toPath.child(timestamp+currentUid).child("product_tea").setValue(product_tea+"");
                                    toPath.child(timestamp+currentUid).child("request_day").setValue(day+"");
                                    toPath.child(timestamp+currentUid).child("request_month").setValue(month+"");
                                    toPath.child(timestamp+currentUid).child("request_year").setValue(year);
                                    toPath.child(timestamp+currentUid).child("request_state").setValue("sent");
                                    toPath.child(timestamp+currentUid).child("requested_date").setValue(day+"/"+month+"/"+year);
                                    toPath.child(timestamp+currentUid).child("requested_time").setValue(saveCurrentTime);
                                    toPath.child(timestamp+currentUid).child("timestamp").setValue(timestamp);

                                    expressLoanRef.removeValue();
                                    Intent intent = new Intent(CompanyLoanRequestActivity.this, CompanyLoanRequestSentSuccessfullyActivity.class);
                                    intent.putExtra("institution_key",institution_key);
                                    intent.putExtra("company_id",company_id);
                                    intent.putExtra("product_key",product_key);
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

    private void showDocumentation() {
        Query query = financialInstitutionsRef.child(institution_key).child("Company Products").child(product_key).child("Required Documentation").orderByChild("financial_institution_name");
        FirebaseRecyclerAdapter<DocumentRequiredModel,DocumentRequiredViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<DocumentRequiredModel, DocumentRequiredViewHolder>
                (DocumentRequiredModel.class,R.layout.financial_institution_documentation_customer_product_required_item,DocumentRequiredViewHolder.class,query) {
            @Override
            protected void populateViewHolder(final DocumentRequiredViewHolder viewHolder, DocumentRequiredModel model, int position) {
                final String postKey = getRef(position).getKey();
                viewHolder.setStep(model.getStep());
                viewHolder.setDoc_title(model.getDoc_title());
                viewHolder.setDoc_description(model.getDoc_description());

                doc_path = postKey;

                viewHolder.txtDocName.setText(viewHolder.my_doc_tittle);
                viewHolder.txtDocState.setText(viewHolder.my_doc_description);

                viewHolder.docsLayout.setVisibility(View.GONE);

                viewHolder.btnArrow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (viewHolder.doc_layout_verification.equals("close")) {
                            viewHolder.btnArrow.setImageResource(R.drawable.blue_arrow_up_vector_asset);
                            viewHolder.docsLayout.setVisibility(View.VISIBLE);
                            viewHolder.doc_layout_verification = "open";

                            simulationCardView.setVisibility(View.GONE);


                        } else if (viewHolder.doc_layout_verification.equals("open")) {
                            viewHolder.btnArrow.setImageResource(R.drawable.blue_arrow_down_vector_asset);
                            viewHolder.docsLayout.setVisibility(View.GONE);
                            viewHolder.doc_layout_verification = "close";

                            simulationCardView.setVisibility(View.VISIBLE);
                        }
                    }
                });

                viewHolder.recyclerView.setHasFixedSize(true);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(CompanyLoanRequestActivity.this);
                linearLayoutManager.setReverseLayout(false);
                linearLayoutManager.setStackFromEnd(false);
                viewHolder.recyclerView.setLayoutManager(linearLayoutManager);

                expressLoanRef.child(postKey).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            long count = dataSnapshot.getChildrenCount();
                            viewHolder.txtDocState.setText(count+" archivos cargados");
                            viewHolder.txtDocState.setTextColor(Color.GREEN);
                            viewHolder.imgState.setImageResource(R.color.greenColor);
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                Query query = expressLoanRef.child(postKey).orderByChild("file_name");
                FirebaseRecyclerAdapter<FileUploadedModel, myPostViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<FileUploadedModel, myPostViewHolder>
                        (FileUploadedModel.class,R.layout.upload_file_item, myPostViewHolder.class,query) {
                    @Override
                    protected void populateViewHolder(myPostViewHolder viewHolder, FileUploadedModel model, int position) {
                        final String postKey2 = getRef(position).getKey();
                        viewHolder.setFile_name(model.getFile_name());
                        viewHolder.setUrl(model.getUrl());

                        viewHolder.txtFileName.setText(viewHolder.my_file_name);

                        viewHolder.btnDeleteFile.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                expressLoanRef.child(postKey).child(postKey2).removeValue();
                            }
                        });
                    }
                };
                viewHolder.recyclerView.setAdapter(firebaseRecyclerAdapter);

                viewHolder.btnAddDocuments.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String[] mimeTypes =
                                {"image/*",
                                        "application/pdf"};

                        Intent intent = new Intent();
                        intent.addCategory(Intent.CATEGORY_OPENABLE);

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                            intent.setType(mimeTypes.length == 1 ? mimeTypes[0] : "*/*");
                            if (mimeTypes.length > 0) {
                                intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
                            }
                        } else {
                            String mimeTypesStr = "";
                            for (String mimeType : mimeTypes) {
                                mimeTypesStr += mimeType + "|";
                            }
                            intent.setType(mimeTypesStr.substring(0,mimeTypesStr.length() - 1));
                        }
                        //intent.setType("image/*");
                        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(Intent.createChooser(intent,"Selecciona los archivos"),RESULT_LOAD_IMAGE);

                        doc_path = postKey;

                    }
                });

            }
        };
        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    private void simulateQuote() {
        double amount = Double.parseDouble(edtAmount.getText().toString());
        double months = Double.parseDouble(btnPaymentMonth.getText().toString());
        double tea_decimal = product_tea/100;
        double rate = Math.pow((1+tea_decimal),(1/12.00))-1;

        double factor_top = Math.pow((1+rate),months)*rate;
        double factor_up = Math.pow((1+rate),months)-1;
        double quote = (factor_top/factor_up)*amount;
        String quote_st = decimalFormat.format(quote);

        expressLoanRef.child("Simulation").child("requested_amount").setValue(edtAmount.getText().toString());
        if (rdPen.isChecked()) {
            expressLoanRef.child("Simulation").child("requested_currency").setValue("PEN");
            txtPayment.setText("S/ "+quote_st);
        } else if (rdUsd.isChecked()) {
            expressLoanRef.child("Simulation").child("requested_currency").setValue("USD");
            txtPayment.setText("$ "+quote_st);
        }

        expressLoanRef.child("Simulation").child("requested_month").setValue(btnPaymentMonth.getText().toString());
        expressLoanRef.child("Simulation").child("requested_grace").setValue(btnGraceMonth.getText().toString());
        expressLoanRef.child("Simulation").child("expected_quote").setValue(quote_st);
    }

    public static class DocumentRequiredViewHolder extends RecyclerView.ViewHolder {
        View mView;
        CircleImageView imgState;
        TextView txtDocName,txtDocState;
        String my_doc_description,my_doc_tittle,my_step, doc_layout_verification;
        ImageView btnArrow;
        LinearLayout docsLayout;
        RecyclerView recyclerView;
        Button btnAddDocuments;

        public DocumentRequiredViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;

            imgState = mView.findViewById(R.id.imgState);
            txtDocName = mView.findViewById(R.id.txtDocName);
            txtDocState = mView.findViewById(R.id.txtDocState);
            btnArrow = mView.findViewById(R.id.btnArrow);
            docsLayout = mView.findViewById(R.id.docsLayout);
            recyclerView = mView.findViewById(R.id.recyclerView);
            btnAddDocuments = mView.findViewById(R.id.btnAddDocuments);

            doc_layout_verification = "close";

        }

        public void setDoc_description(String doc_description) {
            my_doc_description = doc_description;
        }


        public void setDoc_title(String doc_title) {
            my_doc_tittle = doc_title;
        }


        public void setStep(String step) {
            my_step = step;
        }
    }

    public String getFileName(Uri uri) {
        String result = null;
        if (uri.getScheme().equals("content")) {
            Cursor cursor = getContentResolver().query(uri, null,null,null,null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            } finally {
                cursor.close();
            }
        }
        if (result == null) {
            result = uri.getPath();
            int cut = result.lastIndexOf('/');
            if (cut != -1) {
                result = result.substring(cut + 1);
            }
        }
        return result;

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK) {
            if (data.getClipData() != null) {
                int totalItemSelected = data.getClipData().getItemCount();

                for (int i = 0; i < totalItemSelected; i++) {
                    loadingBar.setTitle("Subiendo archivos...");
                    loadingBar.setMessage("Cargando...");
                    loadingBar.show();
                    loadingBar.setCanceledOnTouchOutside(false);
                    loadingBar.setCancelable(false);

                    Uri fileUri = data.getClipData().getItemAt(i).getUri();

                    final String filename = getFileName(fileUri);
                    final String refName = String.valueOf(i);

                    Random rand = new Random();
                    final int value = rand.nextInt(999999999);

                    final int finalI = i;
                    final int countSelection = totalItemSelected;
                    StorageReference fileToUpload = mStorage.child("Document Required").child(filename);
                    fileToUpload.putFile(fileUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                            if (task.isSuccessful()) {
                                downloadUrl = task.getResult().getDownloadUrl().toString();

                                expressLoanRef.child(doc_path).child("file"+value).child("url").setValue(downloadUrl);
                                expressLoanRef.child(doc_path).child("file"+value).child("file_name").setValue(filename);
                                Toasty.success(CompanyLoanRequestActivity.this, "Archivo "+filename+" cargado con éxito", Toast.LENGTH_LONG).show();

                                if (finalI == (countSelection-1)) {
                                    loadingBar.dismiss();
                                }

                            }
                        }
                    });

                }
            } else if (data.getData() != null) {
                int totalItemSelected = 1;
                for (int i = 0; i < totalItemSelected; i++) {
                    loadingBar.setTitle("Subiendo archivo...");
                    loadingBar.setMessage("Cargando...");
                    loadingBar.show();
                    loadingBar.setCanceledOnTouchOutside(false);
                    loadingBar.setCancelable(false);
                    Uri fileUri = data.getData();

                    final String filename = getFileName(fileUri);
                    final String refName = String.valueOf(i);

                    Random rand = new Random();
                    final int value = rand.nextInt(999999999);

                    StorageReference fileToUpload = mStorage.child("Document Required").child(filename);
                    fileToUpload.putFile(fileUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                            if (task.isSuccessful()) {
                                downloadUrl = task.getResult().getDownloadUrl().toString();

                                expressLoanRef.child(doc_path).child("file"+value).child("url").setValue(downloadUrl);
                                expressLoanRef.child(doc_path).child("file"+value).child("file_name").setValue(filename);
                                Toasty.success(CompanyLoanRequestActivity.this, "Archivo "+filename+" cargado con éxito", Toast.LENGTH_LONG).show();
                                loadingBar.dismiss();
                            }
                        }
                    });

                }

            }
        }
    }

    public static class myPostViewHolder extends RecyclerView.ViewHolder {
        View mView;
        String my_url, my_file_name;
        TextView txtFileName;
        ImageView btnDeleteFile;

        public myPostViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            txtFileName = mView.findViewById(R.id.txtFileName);
            btnDeleteFile = mView.findViewById(R.id.btnDeleteFile);

        }
        public void setUrl(String url) {
            my_url = url;
        }

        public void setFile_name(String file_name) {
            my_file_name = file_name;
        }
    }
}
