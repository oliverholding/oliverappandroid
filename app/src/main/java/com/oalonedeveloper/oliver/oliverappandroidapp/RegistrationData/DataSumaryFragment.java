package com.oalonedeveloper.oliver.oliverappandroidapp.RegistrationData;

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
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.zxing.WriterException;
import com.oalonedeveloper.oliver.oliverappandroidapp.HttpsTrustManager;
import com.oalonedeveloper.oliver.oliverappandroidapp.JavaMailAPI;
import com.oalonedeveloper.oliver.oliverappandroidapp.OliverAppActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.PlatformSelectionActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Objects;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;
import androidmads.library.qrgenearator.QRGSaver;

import static android.content.Context.WINDOW_SERVICE;


public class DataSumaryFragment extends Fragment {

    FirebaseAuth mAuth;
    DatabaseReference userRef,ratesRef;
    String currentUserID;
    ProgressDialog loadingBar;
    ImageView imgOne,imgTwo,imgThree,imgFour,imgFive;
    String profile_image_verification,dni_api,document_number,name,surname, database_name,json_name, personal_data_verification,contact_data_verification,additional_data_verification,access_data_verification,
            docs_verification,dni_exist,saveCurrentDate,saveCurrentTime;
    TextView txtProfileImage,txtPersonalData,txtContactData,txtAditionalData,txtAccessData,txtTermsAndConditions;
    String nombres,apellido_paterno,apellido_materno,olbk_phone_number,username_exist,username,postRandomName,phoneNumber,email,document_state;
    CheckBox cbTermsAndConditions;
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
        View view = inflater.inflate(R.layout.fragment_data_sumary, container, false);

        loadingBar = new ProgressDialog(getActivity());

        mAuth = FirebaseAuth.getInstance();
        currentUserID = mAuth.getCurrentUser().getUid();
        userRef = FirebaseDatabase.getInstance().getReference().child("Users");
        ratesRef = FirebaseDatabase.getInstance().getReference().child("Rates");
        userProfileImageRef = FirebaseStorage.getInstance().getReference().child("Profile Images");

        phoneNumber = mAuth.getCurrentUser().getPhoneNumber();

        imgOne = view.findViewById(R.id.imgOne);
        txtProfileImage = view.findViewById(R.id.txtProfileImage);
        imgTwo = view.findViewById(R.id.imgTwo);
        txtPersonalData = view.findViewById(R.id.txtPersonalData);
        imgThree = view.findViewById(R.id.imgThree);
        txtContactData = view.findViewById(R.id.txtContactData);
        imgFour = view.findViewById(R.id.imgFour);
        txtAditionalData = view.findViewById(R.id.txtAditionalData);
        imgFive = view.findViewById(R.id.imgFive);
        txtAccessData = view.findViewById(R.id.txtAccessData);
        cbTermsAndConditions = view.findViewById(R.id.cbTermsAndConditions);
        txtTermsAndConditions = view.findViewById(R.id.txtTermsAndConditions);
        btnContinue = view.findViewById(R.id.btnContinue);
        rootLayout = view.findViewById(R.id.rootLayout);

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


        ratesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    dni_api = dataSnapshot.child("dni_api").getValue().toString();

                    userRef.child(currentUserID).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (dataSnapshot.hasChild("name") && dataSnapshot.hasChild("surname") && dataSnapshot.hasChild("document_type") && dataSnapshot.hasChild("document_number") &&
                                    dataSnapshot.hasChild("gender") && dataSnapshot.hasChild("bth_day") && dataSnapshot.hasChild("bth_month") && dataSnapshot.hasChild("bth_year") &&
                                    dataSnapshot.hasChild("nacionality")) {

                                document_number = dataSnapshot.child("document_number").getValue().toString();
                                name = dataSnapshot.child("name").getValue().toString();
                                surname = dataSnapshot.child("surname").getValue().toString();

                                userRef.orderByChild("document_number").equalTo(document_number).addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        long docs_count = dataSnapshot.getChildrenCount();

                                        if (docs_count >= 2) {
                                            showDocumentExistenceDialog();
                                            imgTwo.setImageResource(R.drawable.error);
                                            txtPersonalData.setText("El documento ha sido registrado por otro usuario");
                                            txtPersonalData.setTextColor(Color.RED);
                                            dni_exist = "true";
                                        } else if (docs_count == 1) {
                                            dni_exist = "false";
                                        }

                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {

                                    }
                                });

                                //Check reniec api
                                if (dni_api.equals("true")) {
                                    getReniecInformation();
                                } else if (dni_api.equals("false")) {
                                    imgTwo.setImageResource(R.drawable.check);
                                    txtPersonalData.setText("Datos personales completado");
                                    txtPersonalData.setTextColor(Color.GREEN);
                                    personal_data_verification = "true";
                                    loadingBar.dismiss();
                                }

                            } else if (!dataSnapshot.hasChild("name") && !dataSnapshot.hasChild("surname") && !dataSnapshot.hasChild("document_type") && !dataSnapshot.hasChild("document_number") &&
                                    !dataSnapshot.hasChild("gender") && !dataSnapshot.hasChild("bth_day") && !dataSnapshot.hasChild("bth_month") && !dataSnapshot.hasChild("bth_year") &&
                                    !dataSnapshot.hasChild("nacionality")) {
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

                    userRef.child(currentUserID).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (dataSnapshot.hasChild("profileimage")) {
                                imgOne.setImageResource(R.drawable.check);
                                txtProfileImage.setText("Foto de perfil cargada con éxito");
                                txtProfileImage.setTextColor(Color.GREEN);
                                profile_image_verification = "true";
                            }


                            if (dataSnapshot.hasChild("email") && dataSnapshot.hasChild("department") && dataSnapshot.hasChild("province") && dataSnapshot.hasChild("district") &&
                                    dataSnapshot.hasChild("address")) {

                                email = dataSnapshot.child("email").getValue().toString();

                                imgThree.setImageResource(R.drawable.check);
                                txtContactData.setText("Datos de contacto completado");
                                txtContactData.setTextColor(Color.GREEN);
                                contact_data_verification = "true";
                                loadingBar.dismiss();

                            } else if (!dataSnapshot.hasChild("email") && !dataSnapshot.hasChild("department") && !dataSnapshot.hasChild("province") && !dataSnapshot.hasChild("district") &&
                                    !dataSnapshot.hasChild("address")) {
                                loadingBar.dismiss();

                            } else {
                                imgThree.setImageResource(R.drawable.error);
                                txtContactData.setText("Falta completar datos");
                                txtContactData.setTextColor(Color.RED);
                                loadingBar.dismiss();
                            }


                            if (dataSnapshot.hasChild("occupation") && dataSnapshot.hasChild("academic_degree")) {
                                imgFour.setImageResource(R.drawable.check);
                                txtAditionalData.setText("Datos adicionales completado");
                                txtAditionalData.setTextColor(Color.GREEN);
                                additional_data_verification = "true";
                                loadingBar.dismiss();
                            } else if (!dataSnapshot.hasChild("occupation") && !dataSnapshot.hasChild("academic_degree")) {
                                loadingBar.dismiss();
                            } else {
                                imgFour.setImageResource(R.drawable.error);
                                txtAditionalData.setText("Falta completar datos");
                                txtAditionalData.setTextColor(Color.RED);
                                loadingBar.dismiss();
                            }

                            if (dataSnapshot.hasChild("username") && dataSnapshot.hasChild("pin")) {
                                imgFive.setImageResource(R.drawable.check);
                                txtAccessData.setText("Datos de acceso completado");
                                txtAccessData.setTextColor(Color.GREEN);
                                access_data_verification = "true";
                                loadingBar.dismiss();

                                username = dataSnapshot.child("username").getValue().toString();

                                userRef.orderByChild("username").equalTo(username).addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        long docs_count = dataSnapshot.getChildrenCount();

                                        if (docs_count >= 2) {
                                            imgFive.setImageResource(R.drawable.error);
                                            txtAccessData.setText("El nombre de usuario ha sido registrado por otro usuario");
                                            txtAccessData.setTextColor(Color.RED);
                                            username_exist = "true";
                                        }
                                        else if (docs_count == 1) {
                                            username_exist = "false";
                                        }

                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {

                                    }
                                });


                            } else if (!dataSnapshot.hasChild("username") && !dataSnapshot.hasChild("pin")) {
                                loadingBar.dismiss();
                            } else {
                                imgFive.setImageResource(R.drawable.error);
                                txtAccessData.setText("Falta completar datos");
                                txtAccessData.setTextColor(Color.RED);
                                loadingBar.dismiss();
                            }


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

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (profile_image_verification.equals("false")) {
                    Snackbar.make(rootLayout, "Debes subir una foto de perfil", Snackbar.LENGTH_LONG).show();
                    return;
                }
                else if (personal_data_verification.equals("false")) {
                    Snackbar.make(rootLayout, "Debes completar tu información personal", Snackbar.LENGTH_LONG).show();
                    return;
                }
                else if (dni_exist.equals("true")) {
                    Snackbar.make(rootLayout, "El número de documento registrado ya está siendo usado por otro usario de Oliver Bank", Snackbar.LENGTH_LONG).show();
                    return;
                }
                else if (contact_data_verification.equals("false")) {
                    Snackbar.make(rootLayout, "Debes completar tu información de contacto", Snackbar.LENGTH_LONG).show();
                    return;
                }
                else if (additional_data_verification.equals("false")) {
                    Snackbar.make(rootLayout, "Debes completar tu información adicional", Snackbar.LENGTH_LONG).show();
                    return;
                } else if (access_data_verification.equals("false")) {
                    Snackbar.make(rootLayout, "Debes completar tu información de acceso", Snackbar.LENGTH_LONG).show();
                    return;
                }
                else if (username_exist.equals("true")) {
                    Snackbar.make(rootLayout, "El nombre de usuario ya está siendo usado por otro usario de Oliver Bank", Snackbar.LENGTH_LONG).show();
                    return;
                }
                else if (!cbTermsAndConditions.isChecked()) {
                    Snackbar.make(rootLayout, "Debes aceptar los términos y condiciones", Snackbar.LENGTH_LONG).show();
                    return;
                }
                else if (permission.equals("false")) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (getActivity().checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_STORAGE_REQUEST_CODE);
                        }
                    }
                    Snackbar.make(rootLayout, "Debes permitir el acceso a la memoria del teléfono para almacenar tu código QR de pagos", Snackbar.LENGTH_LONG).show();
                    return;

                }
                else if (permission.equals("false")) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (getActivity().checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_STORAGE_REQUEST_CODE);
                        }
                    }
                    Snackbar.make(rootLayout, "Debes permitir el acceso a la memoria del teléfono para almacenar tu código QR de pagos", Snackbar.LENGTH_LONG).show();
                    return;

                } else {
                    loadingBar.setTitle("Registrando tu cuenta...");
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

                    postRandomName = saveCurrentDate+saveCurrentTime;

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
        String subject = name+", Bienvenido a Oliver";
        String message = "";
        JavaMailAPI javaMailAPI = new JavaMailAPI(getActivity(), email,subject,message);
        javaMailAPI.execute();
    }

    private void sendQrCodeToDatabase() {

        Long tsLong = System.currentTimeMillis()/1000;
        String timestamp = tsLong.toString();

        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(getActivity().getContentResolver(), bitmap, timestamp, null);
        uri = Uri.parse(path);

        StorageReference filePath = userProfileImageRef.child(uri.getLastPathSegment()+postRandomName+".jpg");
        filePath.putFile(uri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                if (task.isSuccessful()) {
                    downloadUrlQr = task.getResult().getDownloadUrl().toString();

                    HashMap userMap = new HashMap();
                    userMap.put("user_verification","progress");
                    userMap.put("document_verification",document_state);
                    userMap.put("basic_account_pen","0.00");
                    userMap.put("basic_account_usd","0.00");
                    userMap.put("state","offline");
                    userMap.put("credit_line_pen","false");
                    userMap.put("credit_line_pen_available","0.00");
                    userMap.put("credit_line_pen_tcea","800.00");
                    userMap.put("credit_line_pen_total","0.00");
                    userMap.put("credit_line_pen_used","0.00");
                    userMap.put("credit_line_usd","false");
                    userMap.put("credit_line_usd_available","0.00");
                    userMap.put("credit_line_usd_tcea","800.00");
                    userMap.put("credit_line_usd_total","0.00");
                    userMap.put("credit_line_usd_used","0.00");
                    userMap.put("credit_risk_param","0.00");
                    userMap.put("credit_score","5");
                    userMap.put("credit_line_pen_request_state","false");
                    userMap.put("credit_line_usd_request_state","false");
                    userMap.put("qr_code_image",downloadUrlQr);
                    userMap.put("timestamp", ServerValue.TIMESTAMP);

                    userMap.put("my_company_number","0");

                    userMap.put("daily_claim_pen_account","false");
                    userMap.put("daily_claim_usd_account","false");
                    userMap.put("pen_accoount_is_enabled","true");
                    userMap.put("usd_accoount_is_enabled","true");

                    userMap.put("user_is_enabled","true");
                    userMap.put("pin_attempts", 0);

                    userMap.put("phone_number",phoneNumber);

                    userMap.put("register_date",saveCurrentDate);
                    userMap.put("register_time",saveCurrentTime);

                    userMap.put("lending_notification","false");

                    userMap.put("app_identifier","olb");

                    userMap.put("main_activity","none");

                    userMap.put("user_type","customer");

                    userRef.child(currentUserID).updateChildren(userMap).addOnCompleteListener(new OnCompleteListener() {
                        @Override
                        public void onComplete(@NonNull Task task) {
                            if (task.isSuccessful()) {
                                Intent intent = new Intent(getActivity(), PlatformSelectionActivity.class);
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
                    String message = "Hola, deseo reportar que el número de documento "+document_number+ " me pertenece y está siendo usado por otro usuario";
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


    private void generateQrCode() {
        inputValue = currentUserID;
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

    private void getReniecInformation() {

        String url = "https://api.reniec.cloud/dni/"+document_number;
        HttpsTrustManager.allowAllSSL();

        RequestQueue requestQueue = Volley.newRequestQueue(Objects.requireNonNull(getActivity()));
        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    nombres = response.getString("nombres");
                    apellido_paterno = response.getString("apellido_paterno");
                    apellido_materno = response.getString("apellido_materno");

                    String proces_names = Normalizer.normalize(name.toUpperCase(), Normalizer.Form.NFD);
                    final String normal_name = proces_names.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
                    final String real_name = normal_name.replaceAll("\\s+","");

                    String process_surname = Normalizer.normalize(surname.toUpperCase(), Normalizer.Form.NFD);
                    final String normal_surnames = process_surname.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
                    final String real_surname = normal_surnames.replaceAll("\\s+","");


                    String process_json_name = Normalizer.normalize(nombres.toUpperCase(), Normalizer.Form.NFD);
                    String normal_json_name = process_json_name.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
                    String real_json_name = normal_json_name.replaceAll("\\s+","");


                    String process_json_father_surname = Normalizer.normalize(apellido_paterno, Normalizer.Form.NFD);
                    String normal_json_father_surname = process_json_father_surname.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
                    String real_json_father_surname = normal_json_father_surname.replaceAll("\\s+","");

                    String process_json_mother_surname = Normalizer.normalize(apellido_materno, Normalizer.Form.NFD);
                    String normal_json_mother_surname = process_json_mother_surname.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
                    String real_json_mother_surname = normal_json_mother_surname.replaceAll("\\s+","");

                    database_name = real_name+real_surname;
                    json_name =real_json_name+real_json_father_surname+real_json_mother_surname;

                    if (!database_name.equals(json_name)) {
                        showNamesErrorDialog();
                        imgTwo.setImageResource(R.drawable.error);
                        txtPersonalData.setText("Error en los nombres");
                        txtPersonalData.setTextColor(Color.RED);
                    } else {
                        imgTwo.setImageResource(R.drawable.check);
                        txtPersonalData.setText("Datos personales completado");
                        txtPersonalData.setTextColor(Color.GREEN);
                        personal_data_verification = "true";
                    }

                    loadingBar.dismiss();


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(objectRequest);
    }

    private void showNamesErrorDialog() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = LayoutInflater.from(getActivity());
        final View add_bank_account = inflater.inflate(R.layout.names_dialog_error,null);

        dialog.setPositiveButton("Entendido", new DialogInterface.OnClickListener() {
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