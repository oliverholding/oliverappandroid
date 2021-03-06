package com.oalonedeveloper.oliver.oliverappandroidapp.InformalCompanies;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.zxing.WriterException;
import com.oalonedeveloper.oliver.oliverappandroidapp.Companies.MyCompaniesActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.HttpsTrustManager;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Objects;
import java.util.Random;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;
import androidmads.library.qrgenearator.QRGSaver;

import static android.content.Context.WINDOW_SERVICE;


public class RegisterInformalCompany4Fragment extends Fragment {

    FirebaseAuth mAuth;
    DatabaseReference userRef,ratesRef, myCompaniesRef;
    String currentUserID;
    ProgressDialog loadingBar;
    ImageView imgOne,imgTwo,imgFour;
    String profile_image_verification,sunat_api,document_number,name,surname, database_name,json_name, personal_data_verification,contact_data_verification,additional_data_verification,access_data_verification,
            docs_verification,dni_exist,saveCurrentDate,saveCurrentTime,company_bth_day,company_bth_month,company_bth_year,company_profileimage, department,province,district,ruc_file;
    TextView txtProfileImage,txtPersonalData,txtAditionalData,txtAccessData,txtTermsAndConditions,txtAdditionalData;
    String olbk_phone_number,username_exist,username,postRandomName,phoneNumber,email,document_state, razon_social,fecha_inscripcion,contribuyente_tipo,contribuyente_estado,register_date,domicilio_fiscal,company_economic_activity,customer_output,company_value;
    CheckBox cbTermsAndConditions,cbLegalRepresentation;
    Button btnContinue;
    RelativeLayout rootLayout;

    private StorageReference userProfileImageRef;
    String TAG = "GenerateQRCode",downloadUrlQr;
    String edtValue;
    String inputValue;
    String savePath = Environment.getExternalStorageDirectory().getPath() + "/QRCode/";
    Bitmap bitmap;
    QRGEncoder qrgEncoder;
    Uri uri;
    private static final int MY_STORAGE_REQUEST_CODE = 200;
    String permission = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register_informal_company4, container, false);

        loadingBar = new ProgressDialog(getActivity());

        mAuth = FirebaseAuth.getInstance();
        currentUserID = mAuth.getCurrentUser().getUid();
        userRef = FirebaseDatabase.getInstance().getReference().child("Users").child(currentUserID).child("Company Registration");
        ratesRef = FirebaseDatabase.getInstance().getReference().child("Rates");
        userProfileImageRef = FirebaseStorage.getInstance().getReference().child("Profile Images");
        myCompaniesRef = FirebaseDatabase.getInstance().getReference().child("My Companies");

        phoneNumber = mAuth.getCurrentUser().getPhoneNumber();

        imgOne = view.findViewById(R.id.imgOne);
        txtProfileImage = view.findViewById(R.id.txtProfileImage);
        imgTwo = view.findViewById(R.id.imgTwo);
        txtPersonalData = view.findViewById(R.id.txtPersonalData);
        txtAccessData = view.findViewById(R.id.txtAccessData);
        cbTermsAndConditions = view.findViewById(R.id.cbTermsAndConditions);
        txtTermsAndConditions = view.findViewById(R.id.txtTermsAndConditions);
        cbLegalRepresentation = view.findViewById(R.id.cbLegalRepresentation);
        imgFour = view.findViewById(R.id.imgFour);
        btnContinue = view.findViewById(R.id.btnContinue);
        rootLayout = view.findViewById(R.id.rootLayout);
        txtAditionalData = view.findViewById(R.id.txtAditionalData);

        loadingBar.setTitle("Preparando todo...");
        loadingBar.setMessage("Cargando...");
        loadingBar.show();
        loadingBar.setCanceledOnTouchOutside(false);
        loadingBar.setCancelable(false);

        profile_image_verification = "false";
        personal_data_verification = "false";
        contact_data_verification = "false";
        additional_data_verification = "false";
        access_data_verification = "false";
        docs_verification = "false";
        dni_exist = "false";
        username_exist = "false";
        document_state = "none";

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (getActivity().checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_STORAGE_REQUEST_CODE);
            }
        }

        txtTermsAndConditions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://oliver.com.pe/terms-conditions-companies/"));
                startActivity(browserIntent);
            }
        });

        ratesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                sunat_api = dataSnapshot.child("sunat_api").getValue().toString();

                userRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChild("commercial_name") && dataSnapshot.hasChild("company_document_number") && dataSnapshot.hasChild("company_bth_day")
                                && dataSnapshot.hasChild("company_bth_month") && dataSnapshot.hasChild("company_bth_year") && dataSnapshot.hasChild("department")&& dataSnapshot.hasChild("province")&& dataSnapshot.hasChild("district")
                                && dataSnapshot.hasChild("economic_activity")) {

                            document_number = dataSnapshot.child("company_document_number").getValue().toString();
                            name = dataSnapshot.child("commercial_name").getValue().toString();
                            company_bth_day = dataSnapshot.child("company_bth_day").getValue().toString();
                            company_bth_month = dataSnapshot.child("company_bth_month").getValue().toString();
                            company_bth_year = dataSnapshot.child("company_bth_year").getValue().toString();
                            department = dataSnapshot.child("department").getValue().toString();
                            province = dataSnapshot.child("province").getValue().toString();
                            district = dataSnapshot.child("district").getValue().toString();
                            company_economic_activity = dataSnapshot.child("economic_activity").getValue().toString();


                            register_date = company_bth_year +"-"+company_bth_month+"-"+company_bth_day;

                            imgTwo.setImageResource(R.drawable.check);
                            txtPersonalData.setText("Datos del Negocio completado");
                            txtPersonalData.setTextColor(Color.GREEN);
                            personal_data_verification = "true";
                            loadingBar.dismiss();

                        } else if (!dataSnapshot.hasChild("commercial_name") && !dataSnapshot.hasChild("company_document_number")  && !dataSnapshot.hasChild("company_bth_day")
                                && !dataSnapshot.hasChild("company_bth_month") && !dataSnapshot.hasChild("company_bth_year") && !dataSnapshot.hasChild("department")&& !dataSnapshot.hasChild("province")&& !dataSnapshot.hasChild("district")
                                && !dataSnapshot.hasChild("economic_activity")) {
                            loadingBar.dismiss();
                        } else {
                            imgTwo.setImageResource(R.drawable.error);
                            txtPersonalData.setText("Falta completar datos");
                            txtPersonalData.setTextColor(Color.RED);
                            loadingBar.dismiss();
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                userRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChild("company_profileimage")) {
                            company_profileimage = dataSnapshot.child("company_profileimage").getValue().toString();
                            imgOne.setImageResource(R.drawable.check);
                            txtProfileImage.setText("Foto subida con éxito");
                            txtProfileImage.setTextColor(Color.GREEN);
                            profile_image_verification = "true";
                        }

                        if (dataSnapshot.hasChild("customer_output") && dataSnapshot.hasChild("company_value")) {
                            customer_output = dataSnapshot.child("customer_output").getValue().toString();
                            company_value = dataSnapshot.child("company_value").getValue().toString();
                            imgFour.setImageResource(R.drawable.check);
                            txtAditionalData.setText("Datos adicionales completado");
                            txtAditionalData.setTextColor(Color.GREEN);
                            additional_data_verification = "true";
                            loadingBar.dismiss();
                        } else if (!dataSnapshot.hasChild("customer_output") && !dataSnapshot.hasChild("company_value")) {
                            loadingBar.dismiss();
                        } else {
                            imgFour.setImageResource(R.drawable.error);
                            txtAditionalData.setText("Falta completar datos");
                            txtAditionalData.setTextColor(Color.RED);
                            loadingBar.dismiss();
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

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (profile_image_verification.equals("false")) {
                    Snackbar.make(rootLayout, "Debes subir la imágen de tu negocio", Snackbar.LENGTH_LONG).show();
                    return;
                }
                else if (personal_data_verification.equals("false")) {
                    Snackbar.make(rootLayout, "Debes completar la información del negocio", Snackbar.LENGTH_LONG).show();
                    return;
                }
                else if (dni_exist.equals("true")) {
                    Snackbar.make(rootLayout, "El número de RUC ya está siendo usado por otro usario de Oliver", Snackbar.LENGTH_LONG).show();
                    return;
                }
                else if (additional_data_verification.equals("false")) {
                    Snackbar.make(rootLayout, "Debes completar la información adicional", Snackbar.LENGTH_LONG).show();
                    return;
                } else if (!cbTermsAndConditions.isChecked()) {
                    Snackbar.make(rootLayout, "Debes aceptar los términos y condiciones", Snackbar.LENGTH_LONG).show();
                    return;
                } else if (!cbLegalRepresentation.isChecked()) {
                    Snackbar.make(rootLayout, "Debes confirmar que eres el representante legal", Snackbar.LENGTH_LONG).show();
                    return;
                }else if (permission.equals("false")) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (getActivity().checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_STORAGE_REQUEST_CODE);
                        }
                    }
                    Snackbar.make(rootLayout, "Debes permitir el acceso a la memoria del teléfono para almacenar tu código QR de pagos", Snackbar.LENGTH_LONG).show();
                    return;

                } else if (permission.equals("false")) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (getActivity().checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_STORAGE_REQUEST_CODE);
                        }
                    }
                    Snackbar.make(rootLayout, "Debes permitir el acceso a la memoria del teléfono para almacenar tu código QR de pagos", Snackbar.LENGTH_LONG).show();
                    return;

                } else {
                    loadingBar.setTitle("Registrando tu negocio...");
                    loadingBar.setMessage("Cargando...");
                    loadingBar.show();
                    loadingBar.setCanceledOnTouchOutside(false);
                    loadingBar.setCancelable(false);

                    Calendar calForDate = Calendar.getInstance();
                    SimpleDateFormat currentDate = new SimpleDateFormat("dd-MM-yyyy");
                    saveCurrentDate = currentDate.format(calForDate.getTime());

                    Calendar calForTime = Calendar.getInstance();
                    SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss");
                    saveCurrentTime = currentTime.format(calForTime.getTime());

                    postRandomName = saveCurrentDate + saveCurrentTime;

                    sendEmail();
                    generateQrCode();
                    saveQrCode();
                    sendQrCodeToDatabase();

                }
            }
        });

        return view;
    }

    private void sendEmail() {
    }

    private void sendQrCodeToDatabase() {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        Random rand = new Random(); //instance of random class
        int upperbound = 9999;
        //generate random values from 0-24
        int int_random = rand.nextInt(upperbound);
        String path = MediaStore.Images.Media.insertImage(getActivity().getContentResolver(), bitmap, "qr"+int_random, null);
        uri = Uri.parse(path);

        StorageReference filePath = userProfileImageRef.child(uri.getLastPathSegment()+postRandomName+".jpg");
        filePath.putFile(uri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                if (task.isSuccessful()) {
                    downloadUrlQr = task.getResult().getDownloadUrl().toString();

                    HashMap postMap = new HashMap();
                    postMap.put("uid",currentUserID);
                    postMap.put("date",saveCurrentDate);
                    postMap.put("time",saveCurrentTime);
                    postMap.put("company_image",company_profileimage);
                    postMap.put("company_name",name);
                    postMap.put("company_social_reason",name);
                    postMap.put("company_ruc",document_number);
                    postMap.put("company_type","Negocio Personal");
                    postMap.put("company_state","null");
                    postMap.put("company_department",department);
                    postMap.put("company_province",province);
                    postMap.put("company_district",district);
                    postMap.put("company_address","Sin Especificar");
                    postMap.put("company_bth_day",company_bth_day);
                    postMap.put("company_bth_month",company_bth_month);
                    postMap.put("company_bth_year",company_bth_year);
                    postMap.put("ruc_file","null");
                    postMap.put("customer_output",customer_output);
                    postMap.put("company_value",company_value);
                    postMap.put("company_economic_activity",company_economic_activity);
                    postMap.put("company_verification","progress");
                    postMap.put("current_account_pen","0.00");
                    postMap.put("current_account_usd","0.00");
                    postMap.put("pen_account_enabled","true");
                    postMap.put("usd_account_enabled","true");
                    postMap.put("company_condition","Empresa Informal");
                    postMap.put("qr_code_image",downloadUrlQr);

                    postMap.put("credit_line_pen","false");
                    postMap.put("credit_line_pen_available","0.00");
                    postMap.put("credit_line_pen_tcea","800.00");
                    postMap.put("credit_line_pen_total","0.00");
                    postMap.put("credit_line_pen_used","0.00");
                    postMap.put("credit_line_usd","false");
                    postMap.put("credit_line_usd_available","0.00");
                    postMap.put("credit_line_usd_tcea","800.00");
                    postMap.put("credit_line_usd_total","0.00");
                    postMap.put("credit_line_usd_used","0.00");
                    postMap.put("credit_risk_param","0.00");
                    postMap.put("credit_score","5");
                    postMap.put("credit_line_pen_request_state","false");
                    postMap.put("credit_line_usd_request_state","false");

                    //Company Score by Care
                    postMap.put("company_level","1");

                    postMap.put("pen_accoount_is_enabled","true");
                    postMap.put("usd_accoount_is_enabled","true");

                    postMap.put("is_financcial_institution","false");
                    //Abvo
                    postMap.put("customer_rating","0.00");
                    postMap.put("abvo_store_state","none");

                    Long tsLong = System.currentTimeMillis()/1000;
                    String timestamp = tsLong.toString();

                    myCompaniesRef.child(currentUserID+timestamp).updateChildren(postMap).addOnCompleteListener(new OnCompleteListener() {
                        @Override
                        public void onComplete(@NonNull Task task) {
                            if (task.isSuccessful()) {
                                Intent intent = new Intent(getActivity(), MyCompaniesActivity.class);
                                userRef.removeValue();
                                loadingBar.dismiss();
                                startActivity(intent);
                                getActivity().finish();
                            }
                            else
                            {
                                String message = task.getException().getMessage();
                                Toast.makeText(getActivity(), "Error: "+message, Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();

                            }
                        }
                    });

                }
            }
        });
    }

    private void saveQrCode() {
        boolean save;
        String result;
        try {
            save = QRGSaver.save(savePath, postRandomName.trim(), bitmap, QRGContents.ImageType.IMAGE_JPEG);
            result = save ? "Image Saved" : "Image Not Saved";
            //Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void generateQrCode() {
        inputValue = currentUserID+postRandomName;
        if (inputValue.length() > 0) {
            WindowManager manager = (WindowManager) getActivity().getSystemService(WINDOW_SERVICE);
            Display display = manager.getDefaultDisplay();
            Point point = new Point();
            display.getSize(point);
            int width = point.x;
            int height = point.y;
            int smallerDimension = width < height ? width : height;
            smallerDimension = smallerDimension * 3 / 4;

            qrgEncoder = new QRGEncoder(
                    inputValue, null,
                    QRGContents.Type.TEXT,
                    smallerDimension);
            try {
                bitmap = qrgEncoder.encodeAsBitmap();
                //qrCode.setImageBitmap(bitmap);
            } catch (WriterException e) {
                Log.v(TAG, e.toString());
            }
        } else {
            Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
        }
    }


    private void showDocumentExistenceDialog() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = LayoutInflater.from(getActivity());
        final View add_bank_account = inflater.inflate(R.layout.document_non_existence_dialog,null);

        TextView txtDocMessage;
        Button btnReport;

        txtDocMessage = add_bank_account.findViewById(R.id.txtDocMessage);
        btnReport = add_bank_account.findViewById(R.id.btnReport);

        txtDocMessage.setText("EL DOCUMENTO "+document_number+" HA SIDO REGISTRADO POR OTRO USUARIO DE OLIVER BANK");

        btnReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                olbk_phone_number = "51947625082";

                boolean installed = appInstalledOrNot("com.whatsapp");
                if (installed) {
                    String message = "Hola, deseo reportar que el número de RUC "+document_number+ " me pertenece y está siendo usado por otro usuario";
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("http://api.whatsapp.com/send?phone=" + olbk_phone_number + "&text=" + message));
                    startActivity(intent);
                } else {
                    Toast.makeText(getActivity(), "No tienes instalado WhatsApp", Toast.LENGTH_SHORT).show();
                }
            }
        });

        dialog.setPositiveButton("Cerrar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        dialog.setView(add_bank_account);
        dialog.show();
    }

    private boolean appInstalledOrNot(String url) {
        PackageManager packageManager = getActivity().getPackageManager();
        boolean app_installed;

        try {
            packageManager.getPackageInfo(url, PackageManager.GET_ACTIVITIES);
            app_installed = true;
        } catch (PackageManager.NameNotFoundException e) {
            app_installed = false;
        }

        return app_installed;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_STORAGE_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                permission = "true";
                //Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();
            } else {
                permission = "false";
                Toast.makeText(getActivity(), "AL RECHAZAR LOS PERMISOS ALGUNAS FUNCIONES NO ESTARÁN DISPONIBLES", Toast.LENGTH_LONG).show();
            }
        }
    }
}
