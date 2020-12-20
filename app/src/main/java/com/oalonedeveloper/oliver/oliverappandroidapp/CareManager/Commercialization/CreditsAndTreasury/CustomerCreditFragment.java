package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Commercialization.CreditsAndTreasury;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.face.Face;
import com.google.android.gms.vision.face.FaceDetector;
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
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Commercialization.BillsIssuing.CreateInvoiceActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.Companies.CompanyCustomersModel;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import es.dmoral.toasty.Toasty;
import in.galaxyofandroid.spinerdialog.OnSpinerItemClick;
import in.galaxyofandroid.spinerdialog.SpinnerDialog;

import static android.app.Activity.RESULT_OK;


public class CustomerCreditFragment extends Fragment {

    DatabaseReference myCompanyRef;
    RecyclerView recyclerView;
    String post_key, dni_1,downloadUrl,downloadUrl2,downloadUrl3;
    Button btnAddCustomer;
    RadioButton radioButton1,radioButton2,radioButton3,radioButton4,radioButton5;

    StorageReference userProfileImageRef;

    ArrayList<String> quotes_num =new ArrayList<>();
    SpinnerDialog bthQuotesDialog;

    ArrayList<String> frequency =new ArrayList<>();
    SpinnerDialog bthQFrequencyDialog;

    ImageButton btnImageButton1,btnImageButton2,btnImageButton3;

    final static int Gallery_Pick = 2;
    final static int Gallery_Pick2 = 3;
    final static int Gallery_Pick3 = 4;

    Uri imageUri;

    ProgressDialog loadingBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_customer_credit, container, false);

        post_key = getActivity().getIntent().getExtras().getString("post_key");

        btnAddCustomer = view.findViewById(R.id.btnAddCustomer);

        loadingBar = new ProgressDialog(getActivity());

        myCompanyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");

        userProfileImageRef = FirebaseStorage.getInstance().getReference().child("Customer DNI");

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setReverseLayout(false);
        linearLayoutManager.setStackFromEnd(false);
        recyclerView.setLayoutManager(linearLayoutManager);

        downloadUrl = "null";
        downloadUrl2 = "null";

        showCompanyCustomers();

        btnAddCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddCustomerDialog();
            }
        });

        return view;
    }

    private void showCompanyCustomers() {
        Query query = myCompanyRef.child(post_key).child("Customers").orderByChild("name");
        FirebaseRecyclerAdapter<CompanyCustomersModel, CompanyCustomersViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<CompanyCustomersModel, CompanyCustomersViewHolder>
                (CompanyCustomersModel.class, R.layout.company_customer_credit_item, CompanyCustomersViewHolder.class, query) {
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


                myCompanyRef.child(post_key).child("Customers").child(postKey).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.child("Internal Evaluation").hasChild("customer_max_credit")) {
                            String customer_max_credit = dataSnapshot.child("Internal Evaluation").child("customer_max_credit").getValue().toString();
                            viewHolder.txtLineOfCredit.setText("Crédito Aprobado: S/ "+customer_max_credit);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                viewHolder.btnDefineCredit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showCustomerCreditDefineDialog();
                    }

                    private void showCustomerCreditDefineDialog() {
                        final AlertDialog dialog = new AlertDialog.Builder(getActivity()).create();

                        LayoutInflater inflater = LayoutInflater.from(getActivity());
                        final View finance_method = inflater.inflate(R.layout.customer_set_credit_information_dialog,null);



                        final RadioGroup radioGroup1,radioGroup2,radioGroup3,radioGroup4;
                        final Button btnDefineCredit,btnQuotesNumber,btnPaymentFrequency;
                        final EditText edtMaxAmount,edtQuoteAmount;
                        final ScrollView rootLayout;
                        RadioButton rdYes,rdNo,rdYes2,rdNo2;
                        final LinearLayout photoDniLayout,photoServicesLayout;
                        TextView txtMessage;

                        radioGroup1 = finance_method.findViewById(R.id.radioGroup1);
                        radioGroup2 = finance_method.findViewById(R.id.radioGroup2);
                        radioGroup3 = finance_method.findViewById(R.id.radioGroup3);
                        radioGroup4 = finance_method.findViewById(R.id.radioGroup4);
                        edtMaxAmount = finance_method.findViewById(R.id.edtMaxAmount);
                        edtQuoteAmount = finance_method.findViewById(R.id.edtQuoteAmount);
                        btnDefineCredit = finance_method.findViewById(R.id.btnDefineCredit);
                        btnQuotesNumber = finance_method.findViewById(R.id.btnQuotesNumber);
                        btnPaymentFrequency = finance_method.findViewById(R.id.btnPaymentFrequency);
                        rdYes = finance_method.findViewById(R.id.rdYes);
                        rdNo = finance_method.findViewById(R.id.rdNo);
                        rootLayout = finance_method.findViewById(R.id.rootLayout);
                        photoDniLayout = finance_method.findViewById(R.id.photoDniLayout);
                        btnImageButton1 = finance_method.findViewById(R.id.btnImageButton1);
                        btnImageButton2 = finance_method.findViewById(R.id.btnImageButton2);
                        rdYes2 = finance_method.findViewById(R.id.rdYes2);
                        rdNo2 = finance_method.findViewById(R.id.rdNo2);
                        photoServicesLayout = finance_method.findViewById(R.id.photoServicesLayout);
                        btnImageButton3 = finance_method.findViewById(R.id.btnImageButton3);
                        txtMessage = finance_method.findViewById(R.id.txtMessage);

                        btnImageButton1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent galleryIntent = new Intent();
                                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                                galleryIntent.setType("image/*");
                                startActivityForResult(galleryIntent, Gallery_Pick);
                            }
                        });

                        btnImageButton2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent galleryIntent = new Intent();
                                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                                galleryIntent.setType("image/*");
                                startActivityForResult(galleryIntent, Gallery_Pick2);
                            }
                        });

                        btnImageButton3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent galleryIntent = new Intent();
                                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                                galleryIntent.setType("image/*");
                                startActivityForResult(galleryIntent, Gallery_Pick3);
                            }
                        });

                        txtMessage.setText("Evaluación interna para "+viewHolder.name.toUpperCase());

                        photoDniLayout.setVisibility(View.GONE);

                        rdYes.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                photoDniLayout.setVisibility(View.VISIBLE);
                            }
                        });

                        rdNo.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                photoDniLayout.setVisibility(View.GONE);
                            }
                        });

                        photoServicesLayout.setVisibility(View.GONE);

                        rdYes2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                photoServicesLayout.setVisibility(View.VISIBLE);
                            }
                        });

                        rdNo2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                photoServicesLayout.setVisibility(View.GONE);
                            }
                        });

                        quotes_num.add("1");quotes_num.add("2");quotes_num.add("3");quotes_num.add("4");quotes_num.add("5");quotes_num.add("6");quotes_num.add("7");quotes_num.add("8");quotes_num.add("9");quotes_num.add("10");
                        quotes_num.add("11");quotes_num.add("12");quotes_num.add("13");quotes_num.add("14");quotes_num.add("15");quotes_num.add("16");quotes_num.add("17");quotes_num.add("18");quotes_num.add("19");quotes_num.add("20");
                        quotes_num.add("21");quotes_num.add("22");quotes_num.add("23");quotes_num.add("24");quotes_num.add("25");quotes_num.add("26");quotes_num.add("27");quotes_num.add("28");quotes_num.add("29");quotes_num.add("30");

                        btnQuotesNumber.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (TextUtils.isEmpty(edtMaxAmount.getText().toString())) {
                                    Snackbar.make(rootLayout, "Debes ingresar el monto maximo de credito a otorogar", Snackbar.LENGTH_LONG).show();
                                } else {
                                    bthQuotesDialog.showSpinerDialog();
                                }

                            }
                        });

                        bthQuotesDialog = new SpinnerDialog(getActivity(),quotes_num, "Selecciona el número de cuota");
                        bthQuotesDialog.bindOnSpinerListener(new OnSpinerItemClick() {
                            @Override
                            public void onClick(String item2, int position2) {
                                btnQuotesNumber.setText(item2);
                                double quote_number = Double.parseDouble(item2);
                                double max_amount = Double.parseDouble(edtMaxAmount.getText().toString());
                                double quote_amount = max_amount/quote_number;
                                double quote_amount_st = Math.round(quote_amount);
                                edtQuoteAmount.setText(quote_amount_st+"");

                            }
                        });

                        frequency.add("Diario"); frequency.add("Semanal"); frequency.add("Quincenal"); frequency.add("Mensual");

                        btnPaymentFrequency.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                bthQFrequencyDialog.showSpinerDialog();
                            }
                        });

                        bthQFrequencyDialog = new SpinnerDialog(getActivity(),frequency, "Selecciona la frecuencia de pago");
                        bthQFrequencyDialog.bindOnSpinerListener(new OnSpinerItemClick() {
                            @Override
                            public void onClick(String item2, int position2) {
                                btnPaymentFrequency.setText(item2);

                            }
                        });


                        btnDefineCredit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (radioGroup1.getCheckedRadioButtonId() == -1)
                                {
                                    Snackbar.make(rootLayout, "Debes seleccionar un valor", Snackbar.LENGTH_LONG).show();
                                }
                                else if (radioGroup2.getCheckedRadioButtonId() == -1)
                                {
                                    Snackbar.make(rootLayout, "Debes seleccionar un valor", Snackbar.LENGTH_LONG).show();
                                }
                                else if (radioGroup3.getCheckedRadioButtonId() == -1)
                                {
                                    Snackbar.make(rootLayout, "Debes seleccionar un valor", Snackbar.LENGTH_LONG).show();
                                }
                                else if (radioGroup4.getCheckedRadioButtonId() == -1)
                                {
                                    Snackbar.make(rootLayout, "Debes seleccionar un valor", Snackbar.LENGTH_LONG).show();
                                }
                                else if (TextUtils.isEmpty(edtMaxAmount.getText().toString()))
                                {
                                    Snackbar.make(rootLayout, "Debes ingresar el monto maximo de credito a otorogar", Snackbar.LENGTH_LONG).show();
                                }
                                else if (TextUtils.isEmpty(btnQuotesNumber.getText().toString()))
                                {
                                    Snackbar.make(rootLayout, "Debes seleccionar el número de cuotas", Snackbar.LENGTH_LONG).show();
                                }
                                else if (TextUtils.isEmpty(btnPaymentFrequency.getText().toString()))
                                {
                                    Snackbar.make(rootLayout, "Debes seleccionar la frecuencia de pago", Snackbar.LENGTH_LONG).show();
                                }else if (TextUtils.isEmpty(edtQuoteAmount.getText().toString())) {
                                    Snackbar.make(rootLayout, "Debes ingresar el monto de la cuota", Snackbar.LENGTH_LONG).show();
                                } else {
                                    btnDefineCredit.setText("Cargando...");
                                    btnDefineCredit.setEnabled(false);
                                    int selectedId1 = radioGroup1.getCheckedRadioButtonId();
                                    radioButton1 = finance_method.findViewById(selectedId1);

                                    int selectedId2 = radioGroup2.getCheckedRadioButtonId();
                                    radioButton2 = finance_method.findViewById(selectedId2);

                                    int selectedId3 = radioGroup3.getCheckedRadioButtonId();
                                    radioButton3 = finance_method.findViewById(selectedId3);

                                    int selectedId4 = radioGroup4.getCheckedRadioButtonId();
                                    radioButton4 = finance_method.findViewById(selectedId4);

                                    myCompanyRef.child(post_key).child("Customers").child(postKey).child("Internal Evaluation").child("customer_id_verification").setValue(radioButton1.getText());
                                    myCompanyRef.child(post_key).child("Customers").child(postKey).child("Internal Evaluation").child("credit_score").setValue(radioButton2.getText());
                                    myCompanyRef.child(post_key).child("Customers").child(postKey).child("Internal Evaluation").child("customer_address_verification").setValue(radioButton3.getText());
                                    myCompanyRef.child(post_key).child("Customers").child(postKey).child("Internal Evaluation").child("customer_incomes_fund").setValue(radioButton4.getText());
                                    myCompanyRef.child(post_key).child("Customers").child(postKey).child("Internal Evaluation").child("customer_max_credit").setValue(edtMaxAmount.getText().toString());
                                    myCompanyRef.child(post_key).child("Customers").child(postKey).child("Internal Evaluation").child("customer_quotes_number").setValue(btnQuotesNumber.getText());
                                    myCompanyRef.child(post_key).child("Customers").child(postKey).child("Internal Evaluation").child("customer_payment_frequency").setValue(btnPaymentFrequency.getText());
                                    myCompanyRef.child(post_key).child("Customers").child(postKey).child("Internal Evaluation").child("customer_quote_amount").setValue(edtQuoteAmount.getText().toString());
                                    myCompanyRef.child(post_key).child("Customers").child(postKey).child("Internal Evaluation").child("customer_dni_1").setValue(downloadUrl);
                                    myCompanyRef.child(post_key).child("Customers").child(postKey).child("Internal Evaluation").child("customer_dni_2").setValue(downloadUrl2);
                                    myCompanyRef.child(post_key).child("Customers").child(postKey).child("Internal Evaluation").child("customer_dni_3").setValue(downloadUrl3);
                                    dialog.dismiss();
                                    Toasty.success(getActivity(), "Información registrada", Toast.LENGTH_LONG).show();
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

    public static class CompanyCustomersViewHolder extends RecyclerView.ViewHolder {
        View mView;
        TextView txtName,txtPhone,txtEmail,txtType,txtLineOfCredit;
        String email,name,phone,type;
        Button btnDefineCredit;

        public CompanyCustomersViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;

            txtName = mView.findViewById(R.id.txtName);
            txtPhone = mView.findViewById(R.id.txtPhone);
            txtEmail = mView.findViewById(R.id.txtEmail);
            txtType = mView.findViewById(R.id.txtType);
            btnDefineCredit = mView.findViewById(R.id.btnDefineCredit);
            txtLineOfCredit = mView.findViewById(R.id.txtLineOfCredit);
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

    private void showAddCustomerDialog() {
        final AlertDialog dialog = new AlertDialog.Builder(getActivity()).create();

        LayoutInflater inflater = LayoutInflater.from(getActivity());
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
                hashMap.put("customer_define", "contact");
                hashMap.put("customer_phone", ccpPhoneCode.getSelectedCountryCode()+edtPhoneNumber.getText().toString());
                if (rdPerson.isChecked()) {
                    hashMap.put("customer_type", rdPerson.getText().toString());
                }
                else if (rdCompany.isChecked()) {
                    hashMap.put("customer_type", rdCompany.getText().toString());
                }
                hashMap.put("register_date", saveCurrentDate);
                hashMap.put("register_time", saveCurrentTime);
                myCompanyRef.child(post_key).child("Customers").child(postRandomName).updateChildren(hashMap).addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        myCompanyRef.child(post_key).child("Achievements").child("Module2 Add Customer").child("score").setValue("50");
                        myCompanyRef.child(post_key).child("Achievements").child("Module2 Add Customer").child("message").setValue("Has registrado satisfactoriamente a tu primer cliente");
                        myCompanyRef.child(post_key).child("Achievements").child("Module2 Add Customer").child("timestamp").setValue(ServerValue.TIMESTAMP);
                        Toasty.success(getActivity(), "Registrado", Toast.LENGTH_LONG).show();
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Gallery_Pick && resultCode == RESULT_OK && data != null) {

            imageUri = data.getData();
            try {
                Bitmap imageBitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), imageUri);
                btnImageButton1.setImageBitmap(imageBitmap);
                // Get the dimensions of the View
                int targetW = btnImageButton1.getWidth();
                int targetH = btnImageButton1.getHeight();

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


                btnImageButton1.setImageBitmap(imageBitmap);
                savePhotoOnDatabase();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (requestCode == Gallery_Pick2 && resultCode == RESULT_OK && data != null) {

            imageUri = data.getData();
            try {
                Bitmap imageBitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), imageUri);
                btnImageButton2.setImageBitmap(imageBitmap);
                // Get the dimensions of the View
                int targetW = btnImageButton2.getWidth();
                int targetH = btnImageButton2.getHeight();

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

                btnImageButton2.setImageBitmap(imageBitmap);
                savePhotoOnDatabase2();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (requestCode == Gallery_Pick3 && resultCode == RESULT_OK && data != null) {

            imageUri = data.getData();
            try {
                Bitmap imageBitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), imageUri);
                btnImageButton3.setImageBitmap(imageBitmap);
                // Get the dimensions of the View
                int targetW = btnImageButton3.getWidth();
                int targetH = btnImageButton3.getHeight();

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

                btnImageButton3.setImageBitmap(imageBitmap);
                savePhotoOnDatabase3();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void savePhotoOnDatabase3() {
        loadingBar.setTitle("Subiendo foto...");
        loadingBar.setMessage("Cargando...");
        loadingBar.show();
        loadingBar.setCanceledOnTouchOutside(false);
        loadingBar.setCancelable(false);

        StorageReference filePath = userProfileImageRef.child(imageUri.getLastPathSegment()+post_key+".jpg");
        filePath.putFile(imageUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                if (task.isSuccessful()) {
                    downloadUrl3 = task.getResult().getDownloadUrl().toString();
                    loadingBar.dismiss();
                }
            }
        });
    }

    private void savePhotoOnDatabase2() {
        loadingBar.setTitle("Subiendo foto Dorsal del DNI...");
        loadingBar.setMessage("Cargando...");
        loadingBar.show();
        loadingBar.setCanceledOnTouchOutside(false);
        loadingBar.setCancelable(false);

        StorageReference filePath = userProfileImageRef.child(imageUri.getLastPathSegment()+post_key+".jpg");
        filePath.putFile(imageUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                if (task.isSuccessful()) {
                    downloadUrl2 = task.getResult().getDownloadUrl().toString();
                    loadingBar.dismiss();
                }
            }
        });
    }

    private void savePhotoOnDatabase() {
        loadingBar.setTitle("Subiendo foto Frontal del DNI...");
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
                    loadingBar.dismiss();
                }
            }
        });
    }
}
