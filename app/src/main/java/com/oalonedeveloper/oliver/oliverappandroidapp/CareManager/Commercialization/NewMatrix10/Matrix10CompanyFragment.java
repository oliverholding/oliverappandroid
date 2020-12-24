package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Commercialization.NewMatrix10;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;
import es.dmoral.toasty.Toasty;

import static android.app.Activity.RESULT_OK;


public class Matrix10CompanyFragment extends Fragment {

    LinearLayout btnProduct1,btnProduct2,btnProduct3,btnProduct4,btnProduct5,btnProduct6,btnProduct7,btnProduct8,btnProduct9,btnProduct10;
    DatabaseReference companyRef;
    String post_key,image_verification,current_time,currentUid,downloadUrl,currentPhotoPath,customer_id,customerKeyProduct1;
    AlertDialog dialog_list;
    RecyclerView recyclerView;
    ImageView imgProduct;
    Uri imageUri;
    ProgressDialog loadingBar;
    FirebaseAuth mAuth;
    int day,month,year;
    final static int Gallery_Pick = 2;
    StorageReference userProfileImageRef;
    String productRef;
    CircleImageView imgProduct1,imgProduct2,imgProduct3,imgProduct4,imgProduct5,imgProduct6,imgProduct7,imgProduct8,imgProduct9,imgProduct10;
    TextView txtProduct1,txtProduct2,txtProduct3,txtProduct4,txtProduct5,txtProduct6,txtProduct7,txtProduct8,txtProduct9,txtProduct10,txtProduct1A,txtProduct1B,txtProduct1C,txtProduct1D,
            txtProduct2A,txtProduct2B,txtProduct2C,txtProduct2D,txtProduct3A,txtProduct3B,txtProduct3C,txtProduct3D,
            txtProduct4A,txtProduct4B,txtProduct4C,txtProduct4D,txtProduct5A,txtProduct5B,txtProduct5C,txtProduct5D,
            txtProduct6A,txtProduct6B,txtProduct6C,txtProduct6D,txtProduct7A,txtProduct7B,txtProduct7C,txtProduct7D,
            txtProduct8A,txtProduct8B,txtProduct8C,txtProduct8D,txtProduct9A,txtProduct9B,txtProduct9C,txtProduct9D,
            txtProduct10A,txtProduct10B,txtProduct10C,txtProduct10D;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_matrix10_company, container, false);

        loadingBar = new ProgressDialog(getActivity());

        btnProduct1 = view.findViewById(R.id.btnProduct1);
        btnProduct2 = view.findViewById(R.id.btnProduct2);
        btnProduct3 = view.findViewById(R.id.btnProduct3);
        btnProduct4 = view.findViewById(R.id.btnProduct4);
        btnProduct5 = view.findViewById(R.id.btnProduct5);
        btnProduct6 = view.findViewById(R.id.btnProduct6);
        btnProduct7 = view.findViewById(R.id.btnProduct7);
        btnProduct8 = view.findViewById(R.id.btnProduct8);
        btnProduct9 = view.findViewById(R.id.btnProduct9);
        btnProduct10 = view.findViewById(R.id.btnProduct10);
        imgProduct1 = view.findViewById(R.id.imgProduct1);
        imgProduct2 = view.findViewById(R.id.imgProduct2);
        imgProduct3 = view.findViewById(R.id.imgProduct3);
        imgProduct4 = view.findViewById(R.id.imgProduct4);
        imgProduct5 = view.findViewById(R.id.imgProduct5);
        imgProduct6 = view.findViewById(R.id.imgProduct6);
        imgProduct7 = view.findViewById(R.id.imgProduct7);
        imgProduct8 = view.findViewById(R.id.imgProduct8);
        imgProduct9 = view.findViewById(R.id.imgProduct9);
        imgProduct10 = view.findViewById(R.id.imgProduct10);
        txtProduct1 = view.findViewById(R.id.txtProduct1);
        txtProduct2 = view.findViewById(R.id.txtProduct2);
        txtProduct3 = view.findViewById(R.id.txtProduct3);
        txtProduct4 = view.findViewById(R.id.txtProduct4);
        txtProduct5 = view.findViewById(R.id.txtProduct5);
        txtProduct6 = view.findViewById(R.id.txtProduct6);
        txtProduct7 = view.findViewById(R.id.txtProduct7);
        txtProduct8 = view.findViewById(R.id.txtProduct8);
        txtProduct9 = view.findViewById(R.id.txtProduct9);
        txtProduct10 = view.findViewById(R.id.txtProduct10);
        txtProduct1A = view.findViewById(R.id.txtProduct1A);
        txtProduct1B = view.findViewById(R.id.txtProduct1B);
        txtProduct1C = view.findViewById(R.id.txtProduct1C);
        txtProduct1D = view.findViewById(R.id.txtProduct1D);
        txtProduct2A = view.findViewById(R.id.txtProduct2A);
        txtProduct2B = view.findViewById(R.id.txtProduct2B);
        txtProduct2C = view.findViewById(R.id.txtProduct2C);
        txtProduct2D = view.findViewById(R.id.txtProduct2D);
        txtProduct3A = view.findViewById(R.id.txtProduct3A);
        txtProduct3B = view.findViewById(R.id.txtProduct3B);
        txtProduct3C = view.findViewById(R.id.txtProduct3C);
        txtProduct3D = view.findViewById(R.id.txtProduct3D);
        txtProduct4A = view.findViewById(R.id.txtProduct4A);
        txtProduct4B = view.findViewById(R.id.txtProduct4B);
        txtProduct4C = view.findViewById(R.id.txtProduct4C);
        txtProduct4D = view.findViewById(R.id.txtProduct4D);
        txtProduct5A = view.findViewById(R.id.txtProduct5A);
        txtProduct5B = view.findViewById(R.id.txtProduct5B);
        txtProduct5C = view.findViewById(R.id.txtProduct5C);
        txtProduct5D = view.findViewById(R.id.txtProduct5D);
        txtProduct6A = view.findViewById(R.id.txtProduct6A);
        txtProduct6B = view.findViewById(R.id.txtProduct6B);
        txtProduct6C = view.findViewById(R.id.txtProduct6C);
        txtProduct6D = view.findViewById(R.id.txtProduct6D);
        txtProduct7A = view.findViewById(R.id.txtProduct7A);
        txtProduct7B = view.findViewById(R.id.txtProduct7B);
        txtProduct7C = view.findViewById(R.id.txtProduct7C);
        txtProduct7D = view.findViewById(R.id.txtProduct7D);
        txtProduct8A = view.findViewById(R.id.txtProduct8A);
        txtProduct8B = view.findViewById(R.id.txtProduct8B);
        txtProduct8C = view.findViewById(R.id.txtProduct8C);
        txtProduct8D = view.findViewById(R.id.txtProduct8D);
        txtProduct9A = view.findViewById(R.id.txtProduct9A);
        txtProduct9B = view.findViewById(R.id.txtProduct9B);
        txtProduct9C = view.findViewById(R.id.txtProduct9C);
        txtProduct9D = view.findViewById(R.id.txtProduct9D);
        txtProduct10A = view.findViewById(R.id.txtProduct10A);
        txtProduct10B = view.findViewById(R.id.txtProduct10B);
        txtProduct10C = view.findViewById(R.id.txtProduct10C);
        txtProduct10D = view.findViewById(R.id.txtProduct10D);

        post_key = getActivity().getIntent().getExtras().getString("post_key");
        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");
        current_time = new SimpleDateFormat("HHmmss").format(Calendar.getInstance().getTime());
        userProfileImageRef = FirebaseStorage.getInstance().getReference().child("Product Images");

        mAuth = FirebaseAuth.getInstance();
        currentUid = mAuth.getCurrentUser().getUid();

        Date date= new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        day = cal.get(Calendar.DAY_OF_MONTH);
        month = cal.get(Calendar.MONTH)+1;
        year = cal.get(Calendar.YEAR);

        companyRef.child(post_key).child("Module 6").child("Matrix 10 Companies").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(final DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild("Product1")) {
                    final String product_id = dataSnapshot.child("Product1").child("product_id").getValue().toString();
                    companyRef.child(post_key).child("My Products").child(product_id).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            String product_image = dataSnapshot.child("product_image").getValue().toString();
                            String product_name = dataSnapshot.child("product_name").getValue().toString();
                            Picasso.with(getActivity()).load(product_image).fit().into(imgProduct1);
                            txtProduct1.setText(product_name);
                            if (dataSnapshot.child("Buyers").child("Companies").exists()) {
                                Query query = companyRef.child(post_key).child("My Products").child(product_id).child("Buyers").child("Companies").orderByChild("quantity_purchased").limitToLast(1);
                                query.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        for (DataSnapshot childSnapshot: dataSnapshot.getChildren()) {
                                            customerKeyProduct1 = childSnapshot.getKey();
                                        }
                                        companyRef.child(post_key).child("Customers").child(customerKeyProduct1).addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                if (dataSnapshot.hasChild("customer_economic_activity")) {
                                                    String customer_gender = dataSnapshot.child("customer_economic_activity").getValue().toString();
                                                    String customer_bth_day = dataSnapshot.child("customer_bth_day").getValue().toString();
                                                    String customer_bth_month = dataSnapshot.child("customer_bth_month").getValue().toString();
                                                    String customer_bth_year = dataSnapshot.child("customer_bth_year").getValue().toString();
                                                    String customer_academic_degree = dataSnapshot.child("customer_workers_number").getValue().toString();
                                                    String customer_department = dataSnapshot.child("customer_department").getValue().toString();
                                                    String customer_province = dataSnapshot.child("customer_province").getValue().toString();
                                                    String customer_district = dataSnapshot.child("customer_district").getValue().toString();

                                                    int customer_bth_day_int = Integer.parseInt(customer_bth_day);
                                                    int customer_bth_month_int = Integer.parseInt(customer_bth_month);
                                                    int customer_bth_year_int = Integer.parseInt(customer_bth_year);

                                                    Calendar dob = Calendar.getInstance();
                                                    Calendar today = Calendar.getInstance();

                                                    dob.set(customer_bth_year_int,customer_bth_month_int,customer_bth_day_int);

                                                    int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);

                                                    if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)){
                                                        age--;
                                                    }

                                                    Integer ageInt = new Integer(age);
                                                    String ageS = ageInt.toString();

                                                    txtProduct1A.setText(customer_gender.toUpperCase());
                                                    txtProduct1B.setText(ageS+" AÑOS");
                                                    txtProduct1C.setText(customer_academic_degree.toUpperCase());
                                                    txtProduct1D.setText(customer_department.toUpperCase()+"/"+customer_province.toUpperCase()+"/"+customer_district.toUpperCase());

                                                } else {
                                                    txtProduct1A.setText("DESCONOCIDO");
                                                    txtProduct1B.setText("DESCONOCIDO");
                                                    txtProduct1C.setText("DESCONOCIDO");
                                                    txtProduct1D.setText("DESCONOCIDO");
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
                            } else {
                                txtProduct1A.setText("DESCONOCIDO");
                                txtProduct1B.setText("DESCONOCIDO");
                                txtProduct1C.setText("DESCONOCIDO");
                                txtProduct1D.setText("DESCONOCIDO");
                            }


                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
                if (dataSnapshot.hasChild("Product2")) {
                    final String product_id = dataSnapshot.child("Product2").child("product_id").getValue().toString();
                    companyRef.child(post_key).child("My Products").child(product_id).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            String product_image = dataSnapshot.child("product_image").getValue().toString();
                            String product_name = dataSnapshot.child("product_name").getValue().toString();
                            Picasso.with(getActivity()).load(product_image).fit().into(imgProduct2);
                            txtProduct2.setText(product_name);
                            if (dataSnapshot.child("Buyers").child("Companies").exists()) {
                                Query query = companyRef.child(post_key).child("My Products").child(product_id).child("Buyers").child("Companies").orderByChild("quantity_purchased").limitToLast(1);
                                query.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                                            customerKeyProduct1 = childSnapshot.getKey();

                                        }
                                        companyRef.child(post_key).child("Customers").child(customerKeyProduct1).addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                if (dataSnapshot.hasChild("customer_economic_activity")) {
                                                    String customer_gender = dataSnapshot.child("customer_economic_activity").getValue().toString();
                                                    String customer_bth_day = dataSnapshot.child("customer_bth_day").getValue().toString();
                                                    String customer_bth_month = dataSnapshot.child("customer_bth_month").getValue().toString();
                                                    String customer_bth_year = dataSnapshot.child("customer_bth_year").getValue().toString();
                                                    String customer_academic_degree = dataSnapshot.child("customer_workers_number").getValue().toString();
                                                    String customer_department = dataSnapshot.child("customer_department").getValue().toString();
                                                    String customer_province = dataSnapshot.child("customer_province").getValue().toString();
                                                    String customer_district = dataSnapshot.child("customer_district").getValue().toString();

                                                    int customer_bth_day_int = Integer.parseInt(customer_bth_day);
                                                    int customer_bth_month_int = Integer.parseInt(customer_bth_month);
                                                    int customer_bth_year_int = Integer.parseInt(customer_bth_year);

                                                    Calendar dob = Calendar.getInstance();
                                                    Calendar today = Calendar.getInstance();

                                                    dob.set(customer_bth_year_int,customer_bth_month_int,customer_bth_day_int);

                                                    int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);

                                                    if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)){
                                                        age--;
                                                    }

                                                    Integer ageInt = new Integer(age);
                                                    String ageS = ageInt.toString();

                                                    txtProduct2A.setText(customer_gender.toUpperCase());
                                                    txtProduct2B.setText(ageS+" AÑOS");
                                                    txtProduct2C.setText(customer_academic_degree.toUpperCase());
                                                    txtProduct2D.setText(customer_department.toUpperCase()+"/"+customer_province.toUpperCase()+"/"+customer_district.toUpperCase());

                                                } else {
                                                    txtProduct2A.setText("DESCONOCIDO");
                                                    txtProduct2B.setText("DESCONOCIDO");
                                                    txtProduct2C.setText("DESCONOCIDO");
                                                    txtProduct2D.setText("DESCONOCIDO");

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
                            } else {
                                txtProduct2A.setText("DESCONOCIDO");
                                txtProduct2B.setText("DESCONOCIDO");
                                txtProduct2C.setText("DESCONOCIDO");
                                txtProduct2D.setText("DESCONOCIDO");
                            }



                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
                if (dataSnapshot.hasChild("Product3")) {
                    final String product_id = dataSnapshot.child("Product3").child("product_id").getValue().toString();
                    companyRef.child(post_key).child("My Products").child(product_id).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            String product_image = dataSnapshot.child("product_image").getValue().toString();
                            String product_name = dataSnapshot.child("product_name").getValue().toString();
                            Picasso.with(getActivity()).load(product_image).fit().into(imgProduct3);
                            txtProduct3.setText(product_name);

                            if (dataSnapshot.child("Buyers").child("Companies").exists()) {

                                Query query = companyRef.child(post_key).child("My Products").child(product_id).child("Buyers").child("Companies").orderByChild("quantity_purchased").limitToLast(1);
                                query.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                                            customerKeyProduct1 = childSnapshot.getKey();

                                        }
                                        companyRef.child(post_key).child("Customers").child(customerKeyProduct1).addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                if (dataSnapshot.hasChild("customer_economic_activity")) {
                                                    String customer_gender = dataSnapshot.child("customer_economic_activity").getValue().toString();
                                                    String customer_bth_day = dataSnapshot.child("customer_bth_day").getValue().toString();
                                                    String customer_bth_month = dataSnapshot.child("customer_bth_month").getValue().toString();
                                                    String customer_bth_year = dataSnapshot.child("customer_bth_year").getValue().toString();
                                                    String customer_academic_degree = dataSnapshot.child("customer_workers_number").getValue().toString();
                                                    String customer_department = dataSnapshot.child("customer_department").getValue().toString();
                                                    String customer_province = dataSnapshot.child("customer_province").getValue().toString();
                                                    String customer_district = dataSnapshot.child("customer_district").getValue().toString();

                                                    int customer_bth_day_int = Integer.parseInt(customer_bth_day);
                                                    int customer_bth_month_int = Integer.parseInt(customer_bth_month);
                                                    int customer_bth_year_int = Integer.parseInt(customer_bth_year);

                                                    Calendar dob = Calendar.getInstance();
                                                    Calendar today = Calendar.getInstance();

                                                    dob.set(customer_bth_year_int,customer_bth_month_int,customer_bth_day_int);

                                                    int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);

                                                    if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)){
                                                        age--;
                                                    }

                                                    Integer ageInt = new Integer(age);
                                                    String ageS = ageInt.toString();

                                                    txtProduct3A.setText(customer_gender.toUpperCase());
                                                    txtProduct3B.setText(ageS+" AÑOS");
                                                    txtProduct3C.setText(customer_academic_degree.toUpperCase());
                                                    txtProduct3D.setText(customer_department.toUpperCase()+"/"+customer_province.toUpperCase()+"/"+customer_district.toUpperCase());

                                                } else {
                                                    txtProduct3A.setText("DESCONOCIDO");
                                                    txtProduct3B.setText("DESCONOCIDO");
                                                    txtProduct3C.setText("DESCONOCIDO");
                                                    txtProduct3D.setText("DESCONOCIDO");
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
                            } else {
                                txtProduct3A.setText("DESCONOCIDO");
                                txtProduct3B.setText("DESCONOCIDO");
                                txtProduct3C.setText("DESCONOCIDO");
                                txtProduct3D.setText("DESCONOCIDO");
                            }



                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
                if (dataSnapshot.hasChild("Product4")) {
                    final String product_id = dataSnapshot.child("Product4").child("product_id").getValue().toString();
                    companyRef.child(post_key).child("My Products").child(product_id).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            String product_image = dataSnapshot.child("product_image").getValue().toString();
                            String product_name = dataSnapshot.child("product_name").getValue().toString();
                            Picasso.with(getActivity()).load(product_image).fit().into(imgProduct4);
                            txtProduct4.setText(product_name);

                            if (dataSnapshot.child("Buyers").child("Companies").exists()) {
                                Query query = companyRef.child(post_key).child("My Products").child(product_id).child("Buyers").child("Companies").orderByChild("quantity_purchased").limitToLast(1);
                                query.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                                            customerKeyProduct1 = childSnapshot.getKey();

                                        }
                                        companyRef.child(post_key).child("Customers").child(customerKeyProduct1).addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                if (dataSnapshot.hasChild("customer_economic_activity")) {
                                                    String customer_gender = dataSnapshot.child("customer_economic_activity").getValue().toString();
                                                    String customer_bth_day = dataSnapshot.child("customer_bth_day").getValue().toString();
                                                    String customer_bth_month = dataSnapshot.child("customer_bth_month").getValue().toString();
                                                    String customer_bth_year = dataSnapshot.child("customer_bth_year").getValue().toString();
                                                    String customer_academic_degree = dataSnapshot.child("customer_workers_number").getValue().toString();
                                                    String customer_department = dataSnapshot.child("customer_department").getValue().toString();
                                                    String customer_province = dataSnapshot.child("customer_province").getValue().toString();
                                                    String customer_district = dataSnapshot.child("customer_district").getValue().toString();

                                                    int customer_bth_day_int = Integer.parseInt(customer_bth_day);
                                                    int customer_bth_month_int = Integer.parseInt(customer_bth_month);
                                                    int customer_bth_year_int = Integer.parseInt(customer_bth_year);

                                                    Calendar dob = Calendar.getInstance();
                                                    Calendar today = Calendar.getInstance();

                                                    dob.set(customer_bth_year_int,customer_bth_month_int,customer_bth_day_int);

                                                    int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);

                                                    if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)){
                                                        age--;
                                                    }

                                                    Integer ageInt = new Integer(age);
                                                    String ageS = ageInt.toString();

                                                    txtProduct4A.setText(customer_gender.toUpperCase());
                                                    txtProduct4B.setText(ageS+" AÑOS");
                                                    txtProduct4C.setText(customer_academic_degree.toUpperCase());
                                                    txtProduct4D.setText(customer_department.toUpperCase()+"/"+customer_province.toUpperCase()+"/"+customer_district.toUpperCase());

                                                } else {
                                                    txtProduct4A.setText("DESCONOCIDO");
                                                    txtProduct4B.setText("DESCONOCIDO");
                                                    txtProduct4C.setText("DESCONOCIDO");
                                                    txtProduct4D.setText("DESCONOCIDO");
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
                            } else {
                                txtProduct4A.setText("DESCONOCIDO");
                                txtProduct4B.setText("DESCONOCIDO");
                                txtProduct4C.setText("DESCONOCIDO");
                                txtProduct4D.setText("DESCONOCIDO");
                            }


                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
                if (dataSnapshot.hasChild("Product5")) {
                    final String product_id = dataSnapshot.child("Product5").child("product_id").getValue().toString();
                    companyRef.child(post_key).child("My Products").child(product_id).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            String product_image = dataSnapshot.child("product_image").getValue().toString();
                            String product_name = dataSnapshot.child("product_name").getValue().toString();
                            Picasso.with(getActivity()).load(product_image).fit().into(imgProduct5);
                            txtProduct5.setText(product_name);

                            if (dataSnapshot.child("Buyers").child("Companies").exists()) {
                                Query query = companyRef.child(post_key).child("My Products").child(product_id).child("Buyers").child("Companies").orderByChild("quantity_purchased").limitToLast(1);
                                query.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                                            customerKeyProduct1 = childSnapshot.getKey();

                                        }
                                        companyRef.child(post_key).child("Customers").child(customerKeyProduct1).addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                if (dataSnapshot.hasChild("customer_economic_activity")) {
                                                    String customer_gender = dataSnapshot.child("customer_economic_activity").getValue().toString();
                                                    String customer_bth_day = dataSnapshot.child("customer_bth_day").getValue().toString();
                                                    String customer_bth_month = dataSnapshot.child("customer_bth_month").getValue().toString();
                                                    String customer_bth_year = dataSnapshot.child("customer_bth_year").getValue().toString();
                                                    String customer_academic_degree = dataSnapshot.child("customer_workers_number").getValue().toString();
                                                    String customer_department = dataSnapshot.child("customer_department").getValue().toString();
                                                    String customer_province = dataSnapshot.child("customer_province").getValue().toString();
                                                    String customer_district = dataSnapshot.child("customer_district").getValue().toString();

                                                    int customer_bth_day_int = Integer.parseInt(customer_bth_day);
                                                    int customer_bth_month_int = Integer.parseInt(customer_bth_month);
                                                    int customer_bth_year_int = Integer.parseInt(customer_bth_year);

                                                    Calendar dob = Calendar.getInstance();
                                                    Calendar today = Calendar.getInstance();

                                                    dob.set(customer_bth_year_int,customer_bth_month_int,customer_bth_day_int);

                                                    int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);

                                                    if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)){
                                                        age--;
                                                    }

                                                    Integer ageInt = new Integer(age);
                                                    String ageS = ageInt.toString();

                                                    txtProduct5A.setText(customer_gender.toUpperCase());
                                                    txtProduct5B.setText(ageS+" AÑOS");
                                                    txtProduct5C.setText(customer_academic_degree.toUpperCase());
                                                    txtProduct5D.setText(customer_department.toUpperCase()+"/"+customer_province.toUpperCase()+"/"+customer_district.toUpperCase());

                                                } else {
                                                    txtProduct5A.setText("DESCONOCIDO");
                                                    txtProduct5B.setText("DESCONOCIDO");
                                                    txtProduct5C.setText("DESCONOCIDO");
                                                    txtProduct5D.setText("DESCONOCIDO");
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
                            } else {
                                txtProduct5A.setText("DESCONOCIDO");
                                txtProduct5B.setText("DESCONOCIDO");
                                txtProduct5C.setText("DESCONOCIDO");
                                txtProduct5D.setText("DESCONOCIDO");
                            }


                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
                if (dataSnapshot.hasChild("Product6")) {
                    final String product_id = dataSnapshot.child("Product6").child("product_id").getValue().toString();
                    companyRef.child(post_key).child("My Products").child(product_id).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            String product_image = dataSnapshot.child("product_image").getValue().toString();
                            String product_name = dataSnapshot.child("product_name").getValue().toString();
                            Picasso.with(getActivity()).load(product_image).fit().into(imgProduct6);
                            txtProduct6.setText(product_name);


                            if (dataSnapshot.child("Buyers").child("Companies").exists()) {
                                Query query = companyRef.child(post_key).child("My Products").child(product_id).child("Buyers").child("Companies").orderByChild("quantity_purchased").limitToLast(1);
                                query.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                                            customerKeyProduct1 = childSnapshot.getKey();

                                        }
                                        companyRef.child(post_key).child("Customers").child(customerKeyProduct1).addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                if (dataSnapshot.hasChild("customer_economic_activity")) {
                                                    String customer_gender = dataSnapshot.child("customer_economic_activity").getValue().toString();
                                                    String customer_bth_day = dataSnapshot.child("customer_bth_day").getValue().toString();
                                                    String customer_bth_month = dataSnapshot.child("customer_bth_month").getValue().toString();
                                                    String customer_bth_year = dataSnapshot.child("customer_bth_year").getValue().toString();
                                                    String customer_academic_degree = dataSnapshot.child("customer_workers_number").getValue().toString();
                                                    String customer_department = dataSnapshot.child("customer_department").getValue().toString();
                                                    String customer_province = dataSnapshot.child("customer_province").getValue().toString();
                                                    String customer_district = dataSnapshot.child("customer_district").getValue().toString();

                                                    int customer_bth_day_int = Integer.parseInt(customer_bth_day);
                                                    int customer_bth_month_int = Integer.parseInt(customer_bth_month);
                                                    int customer_bth_year_int = Integer.parseInt(customer_bth_year);

                                                    Calendar dob = Calendar.getInstance();
                                                    Calendar today = Calendar.getInstance();

                                                    dob.set(customer_bth_year_int,customer_bth_month_int,customer_bth_day_int);

                                                    int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);

                                                    if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)){
                                                        age--;
                                                    }

                                                    Integer ageInt = new Integer(age);
                                                    String ageS = ageInt.toString();

                                                    txtProduct6A.setText(customer_gender.toUpperCase());
                                                    txtProduct6B.setText(ageS+" AÑOS");
                                                    txtProduct6C.setText(customer_academic_degree.toUpperCase());
                                                    txtProduct6D.setText(customer_department.toUpperCase()+"/"+customer_province.toUpperCase()+"/"+customer_district.toUpperCase());

                                                } else {
                                                    txtProduct6A.setText("DESCONOCIDO");
                                                    txtProduct6B.setText("DESCONOCIDO");
                                                    txtProduct6C.setText("DESCONOCIDO");
                                                    txtProduct6D.setText("DESCONOCIDO");
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
                            } else {
                                txtProduct6A.setText("DESCONOCIDO");
                                txtProduct6B.setText("DESCONOCIDO");
                                txtProduct6C.setText("DESCONOCIDO");
                                txtProduct6D.setText("DESCONOCIDO");
                            }


                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
                if (dataSnapshot.hasChild("Product7")) {
                    final String product_id = dataSnapshot.child("Product7").child("product_id").getValue().toString();
                    companyRef.child(post_key).child("My Products").child(product_id).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            String product_image = dataSnapshot.child("product_image").getValue().toString();
                            String product_name = dataSnapshot.child("product_name").getValue().toString();
                            Picasso.with(getActivity()).load(product_image).fit().into(imgProduct7);
                            txtProduct7.setText(product_name);

                            if (dataSnapshot.child("Buyers").child("Companies").exists()) {
                                Query query = companyRef.child(post_key).child("My Products").child(product_id).child("Buyers").child("Companies").orderByChild("quantity_purchased").limitToLast(1);
                                query.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                                            customerKeyProduct1 = childSnapshot.getKey();

                                        }
                                        companyRef.child(post_key).child("Customers").child(customerKeyProduct1).addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                if (dataSnapshot.hasChild("customer_economic_activity")) {
                                                    String customer_gender = dataSnapshot.child("customer_economic_activity").getValue().toString();
                                                    String customer_bth_day = dataSnapshot.child("customer_bth_day").getValue().toString();
                                                    String customer_bth_month = dataSnapshot.child("customer_bth_month").getValue().toString();
                                                    String customer_bth_year = dataSnapshot.child("customer_bth_year").getValue().toString();
                                                    String customer_academic_degree = dataSnapshot.child("customer_workers_number").getValue().toString();
                                                    String customer_department = dataSnapshot.child("customer_department").getValue().toString();
                                                    String customer_province = dataSnapshot.child("customer_province").getValue().toString();
                                                    String customer_district = dataSnapshot.child("customer_district").getValue().toString();

                                                    int customer_bth_day_int = Integer.parseInt(customer_bth_day);
                                                    int customer_bth_month_int = Integer.parseInt(customer_bth_month);
                                                    int customer_bth_year_int = Integer.parseInt(customer_bth_year);

                                                    Calendar dob = Calendar.getInstance();
                                                    Calendar today = Calendar.getInstance();

                                                    dob.set(customer_bth_year_int,customer_bth_month_int,customer_bth_day_int);

                                                    int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);

                                                    if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)){
                                                        age--;
                                                    }

                                                    Integer ageInt = new Integer(age);
                                                    String ageS = ageInt.toString();

                                                    txtProduct7A.setText(customer_gender.toUpperCase());
                                                    txtProduct7B.setText(ageS+" AÑOS");
                                                    txtProduct7C.setText(customer_academic_degree.toUpperCase());
                                                    txtProduct7D.setText(customer_department.toUpperCase()+"/"+customer_province.toUpperCase()+"/"+customer_district.toUpperCase());

                                                } else {
                                                    txtProduct7A.setText("DESCONOCIDO");
                                                    txtProduct7B.setText("DESCONOCIDO");
                                                    txtProduct7C.setText("DESCONOCIDO");
                                                    txtProduct7D.setText("DESCONOCIDO");
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
                            } else {
                                txtProduct7A.setText("DESCONOCIDO");
                                txtProduct7B.setText("DESCONOCIDO");
                                txtProduct7C.setText("DESCONOCIDO");
                                txtProduct7D.setText("DESCONOCIDO");
                            }

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
                if (dataSnapshot.hasChild("Product8")) {
                    final String product_id = dataSnapshot.child("Product8").child("product_id").getValue().toString();
                    companyRef.child(post_key).child("My Products").child(product_id).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            String product_image = dataSnapshot.child("product_image").getValue().toString();
                            String product_name = dataSnapshot.child("product_name").getValue().toString();
                            Picasso.with(getActivity()).load(product_image).fit().into(imgProduct8);
                            txtProduct8.setText(product_name);

                            if (dataSnapshot.child("Buyers").child("Companies").exists()) {
                                Query query = companyRef.child(post_key).child("My Products").child(product_id).child("Buyers").child("Companies").orderByChild("quantity_purchased").limitToLast(1);
                                query.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                                            customerKeyProduct1 = childSnapshot.getKey();

                                        }
                                        companyRef.child(post_key).child("Customers").child(customerKeyProduct1).addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                if (dataSnapshot.hasChild("customer_economic_activity")) {
                                                    String customer_gender = dataSnapshot.child("customer_economic_activity").getValue().toString();
                                                    String customer_bth_day = dataSnapshot.child("customer_bth_day").getValue().toString();
                                                    String customer_bth_month = dataSnapshot.child("customer_bth_month").getValue().toString();
                                                    String customer_bth_year = dataSnapshot.child("customer_bth_year").getValue().toString();
                                                    String customer_academic_degree = dataSnapshot.child("customer_workers_number").getValue().toString();
                                                    String customer_department = dataSnapshot.child("customer_department").getValue().toString();
                                                    String customer_province = dataSnapshot.child("customer_province").getValue().toString();
                                                    String customer_district = dataSnapshot.child("customer_district").getValue().toString();

                                                    int customer_bth_day_int = Integer.parseInt(customer_bth_day);
                                                    int customer_bth_month_int = Integer.parseInt(customer_bth_month);
                                                    int customer_bth_year_int = Integer.parseInt(customer_bth_year);

                                                    Calendar dob = Calendar.getInstance();
                                                    Calendar today = Calendar.getInstance();

                                                    dob.set(customer_bth_year_int,customer_bth_month_int,customer_bth_day_int);

                                                    int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);

                                                    if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)){
                                                        age--;
                                                    }

                                                    Integer ageInt = new Integer(age);
                                                    String ageS = ageInt.toString();

                                                    txtProduct8A.setText(customer_gender.toUpperCase());
                                                    txtProduct8B.setText(ageS+" AÑOS");
                                                    txtProduct8C.setText(customer_academic_degree.toUpperCase());
                                                    txtProduct8D.setText(customer_department.toUpperCase()+"/"+customer_province.toUpperCase()+"/"+customer_district.toUpperCase());

                                                } else {
                                                    txtProduct8A.setText("DESCONOCIDO");
                                                    txtProduct8B.setText("DESCONOCIDO");
                                                    txtProduct8C.setText("DESCONOCIDO");
                                                    txtProduct8D.setText("DESCONOCIDO");
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
                            } else {
                                txtProduct8A.setText("DESCONOCIDO");
                                txtProduct8B.setText("DESCONOCIDO");
                                txtProduct8C.setText("DESCONOCIDO");
                                txtProduct8D.setText("DESCONOCIDO");
                            }


                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
                if (dataSnapshot.hasChild("Product9")) {
                    final String product_id = dataSnapshot.child("Product9").child("product_id").getValue().toString();
                    companyRef.child(post_key).child("My Products").child(product_id).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            String product_image = dataSnapshot.child("product_image").getValue().toString();
                            String product_name = dataSnapshot.child("product_name").getValue().toString();
                            Picasso.with(getActivity()).load(product_image).fit().into(imgProduct9);
                            txtProduct9.setText(product_name);

                            if (dataSnapshot.child("Buyers").child("Companies").exists()) {
                                Query query = companyRef.child(post_key).child("My Products").child(product_id).child("Buyers").child("Companies").orderByChild("quantity_purchased").limitToLast(1);
                                query.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                                            customerKeyProduct1 = childSnapshot.getKey();

                                        }
                                        companyRef.child(post_key).child("Customers").child(customerKeyProduct1).addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                if (dataSnapshot.hasChild("customer_economic_activity")) {
                                                    String customer_gender = dataSnapshot.child("customer_economic_activity").getValue().toString();
                                                    String customer_bth_day = dataSnapshot.child("customer_bth_day").getValue().toString();
                                                    String customer_bth_month = dataSnapshot.child("customer_bth_month").getValue().toString();
                                                    String customer_bth_year = dataSnapshot.child("customer_bth_year").getValue().toString();
                                                    String customer_academic_degree = dataSnapshot.child("customer_workers_number").getValue().toString();
                                                    String customer_department = dataSnapshot.child("customer_department").getValue().toString();
                                                    String customer_province = dataSnapshot.child("customer_province").getValue().toString();
                                                    String customer_district = dataSnapshot.child("customer_district").getValue().toString();

                                                    int customer_bth_day_int = Integer.parseInt(customer_bth_day);
                                                    int customer_bth_month_int = Integer.parseInt(customer_bth_month);
                                                    int customer_bth_year_int = Integer.parseInt(customer_bth_year);

                                                    Calendar dob = Calendar.getInstance();
                                                    Calendar today = Calendar.getInstance();

                                                    dob.set(customer_bth_year_int,customer_bth_month_int,customer_bth_day_int);

                                                    int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);

                                                    if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)){
                                                        age--;
                                                    }

                                                    Integer ageInt = new Integer(age);
                                                    String ageS = ageInt.toString();

                                                    txtProduct9A.setText(customer_gender.toUpperCase());
                                                    txtProduct9B.setText(ageS+" AÑOS");
                                                    txtProduct9C.setText(customer_academic_degree.toUpperCase());
                                                    txtProduct9D.setText(customer_department.toUpperCase()+"/"+customer_province.toUpperCase()+"/"+customer_district.toUpperCase());

                                                } else {
                                                    txtProduct9A.setText("DESCONOCIDO");
                                                    txtProduct9B.setText("DESCONOCIDO");
                                                    txtProduct9C.setText("DESCONOCIDO");
                                                    txtProduct9D.setText("DESCONOCIDO");
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
                            } else {
                                txtProduct9A.setText("DESCONOCIDO");
                                txtProduct9B.setText("DESCONOCIDO");
                                txtProduct9C.setText("DESCONOCIDO");
                                txtProduct9D.setText("DESCONOCIDO");
                            }


                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
                if (dataSnapshot.hasChild("Product10")) {
                    final String product_id = dataSnapshot.child("Product10").child("product_id").getValue().toString();
                    companyRef.child(post_key).child("My Products").child(product_id).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            String product_image = dataSnapshot.child("product_image").getValue().toString();
                            String product_name = dataSnapshot.child("product_name").getValue().toString();
                            Picasso.with(getActivity()).load(product_image).fit().into(imgProduct10);
                            txtProduct10.setText(product_name);

                            if (dataSnapshot.child("Buyers").child("Companies").exists()) {

                                Query query = companyRef.child(post_key).child("My Products").child(product_id).child("Buyers").child("Companies").orderByChild("quantity_purchased").limitToLast(1);
                                query.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                                            customerKeyProduct1 = childSnapshot.getKey();

                                        }
                                        companyRef.child(post_key).child("Customers").child(customerKeyProduct1).addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                if (dataSnapshot.hasChild("customer_economic_activity")) {
                                                    String customer_gender = dataSnapshot.child("customer_economic_activity").getValue().toString();
                                                    String customer_bth_day = dataSnapshot.child("customer_bth_day").getValue().toString();
                                                    String customer_bth_month = dataSnapshot.child("customer_bth_month").getValue().toString();
                                                    String customer_bth_year = dataSnapshot.child("customer_bth_year").getValue().toString();
                                                    String customer_academic_degree = dataSnapshot.child("customer_workers_number").getValue().toString();
                                                    String customer_department = dataSnapshot.child("customer_department").getValue().toString();
                                                    String customer_province = dataSnapshot.child("customer_province").getValue().toString();
                                                    String customer_district = dataSnapshot.child("customer_district").getValue().toString();

                                                    int customer_bth_day_int = Integer.parseInt(customer_bth_day);
                                                    int customer_bth_month_int = Integer.parseInt(customer_bth_month);
                                                    int customer_bth_year_int = Integer.parseInt(customer_bth_year);

                                                    Calendar dob = Calendar.getInstance();
                                                    Calendar today = Calendar.getInstance();

                                                    dob.set(customer_bth_year_int,customer_bth_month_int,customer_bth_day_int);

                                                    int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);

                                                    if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)){
                                                        age--;
                                                    }

                                                    Integer ageInt = new Integer(age);
                                                    String ageS = ageInt.toString();

                                                    txtProduct10A.setText(customer_gender.toUpperCase());
                                                    txtProduct10B.setText(ageS+" AÑOS");
                                                    txtProduct10C.setText(customer_academic_degree.toUpperCase());
                                                    txtProduct10D.setText(customer_department.toUpperCase()+"/"+customer_province.toUpperCase()+"/"+customer_district.toUpperCase());

                                                } else {
                                                    txtProduct10A.setText("DESCONOCIDO");
                                                    txtProduct10B.setText("DESCONOCIDO");
                                                    txtProduct10C.setText("DESCONOCIDO");
                                                    txtProduct10D.setText("DESCONOCIDO");
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
                            } else {
                                txtProduct10A.setText("DESCONOCIDO");
                                txtProduct10B.setText("DESCONOCIDO");
                                txtProduct10C.setText("DESCONOCIDO");
                                txtProduct10D.setText("DESCONOCIDO");
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


        btnProduct1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productRef = "Product1";
                showAddProductDialog();
            }
        });

        btnProduct2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productRef = "Product2";
                showAddProductDialog();
            }
        });

        btnProduct3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productRef = "Product3";
                showAddProductDialog();
            }
        });

        btnProduct4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productRef = "Product4";
                showAddProductDialog();
            }
        });

        btnProduct5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productRef = "Product5";
                showAddProductDialog();
            }
        });

        btnProduct6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productRef = "Product6";
                showAddProductDialog();
            }
        });

        btnProduct7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productRef = "Product7";
                showAddProductDialog();
            }
        });

        btnProduct8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productRef = "Product8";
                showAddProductDialog();
            }
        });

        btnProduct9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productRef = "Product9";
                showAddProductDialog();
            }
        });

        btnProduct10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productRef = "Product10";
                showAddProductDialog();
            }
        });


        return view;
    }

    private void showAddProductDialog() {
        dialog_list = new AlertDialog.Builder(getActivity()).create();

        LayoutInflater inflater = LayoutInflater.from(getActivity());
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
                final AlertDialog dialog = new AlertDialog.Builder(getActivity()).create();

                LayoutInflater inflater = LayoutInflater.from(getActivity());
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
                                    Toasty.success(getActivity(), "Producto Registrado", Toast.LENGTH_LONG).show();
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
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
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
                Picasso.with(getActivity()).load(viewHolder.my_product_image).fit().into(viewHolder.imgProduct);

                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setProductOnMatrix();
                    }

                    private void setProductOnMatrix() {
                        companyRef.child(post_key).child("Module 6").child("Matrix 10 Companies").child(productRef).child("product_id").setValue(postKey);
                        companyRef.child(post_key).child("Module 6").child("Matrix 10 Companies").child(productRef).child("item1").setValue("false");
                        companyRef.child(post_key).child("Module 6").child("Matrix 10 Companies").child(productRef).child("item2").setValue("false");
                        companyRef.child(post_key).child("Module 6").child("Matrix 10 Companies").child(productRef).child("item3").setValue("false");
                        companyRef.child(post_key).child("Module 6").child("Matrix 10 Companies").child(productRef).child("item4").setValue("false");
                        companyRef.child(post_key).child("Module 6").child("Matrix 10 Companies").child(productRef).child("item5").setValue("false");

                        dialog_list.dismiss();
                        Toasty.success(getActivity(), viewHolder.my_product_name+" agregado con éxito", Toast.LENGTH_LONG).show();
                    }

                });

            }
        };
        recyclerView.setAdapter(firebaseRecyclerAdapter);


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
        File storageDir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
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
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File...
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(getActivity(),
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

        String path = MediaStore.Images.Media.insertImage(getActivity().getContentResolver(), bitmap, "Title", null);
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
        getActivity().sendBroadcast(mediaScanIntent);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
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
                Bitmap imageBitmap =MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), imageUri);
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
