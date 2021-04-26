package com.oalonedeveloper.oliver.oliverappandroidapp.Companies;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;

public class RegisterCompanyActivity extends AppCompatActivity {

    Fragment fragment1,fragment2,fragment3,fragment4;
    TextView txtText1,txtText2,txtText3,txtText4;


    private static final int MY_CAMERA_REQUEST_CODE = 100;
    private static final int MY_STORAGE_REQUEST_CODE = 200;

    FirebaseAuth mAuth;
    DatabaseReference userRef,ratesRef, myCompaniesRef;
    String currentUserID;
    String profile_image_verification,sunat_api,document_number,name,surname, database_name,json_name, personal_data_verification,contact_data_verification,additional_data_verification,access_data_verification,
            docs_verification,dni_exist,saveCurrentDate,saveCurrentTime,company_bth_day,company_bth_month,company_bth_year,company_profileimage, department,province,district,ruc_file;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_company);

        mAuth = FirebaseAuth.getInstance();
        currentUserID = mAuth.getCurrentUser().getUid();
        userRef = FirebaseDatabase.getInstance().getReference().child("Users").child(currentUserID).child("Company Registration");
        ratesRef = FirebaseDatabase.getInstance().getReference().child("Rates");
        myCompaniesRef = FirebaseDatabase.getInstance().getReference().child("My Companies");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_REQUEST_CODE);
            }
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_STORAGE_REQUEST_CODE);
            }
        }

        txtText1 = findViewById(R.id.txtText1);
        txtText2 = findViewById(R.id.txtText2);
        txtText3 = findViewById(R.id.txtText3);
        txtText4 = findViewById(R.id.txtText4);

        fragment1 = new RegisterCompanyData1Fragment();
        fragment2 = new RegisterCompanyData2Fragment();
        fragment3 = new RegisterCompanyData4Fragment();
        fragment4 = new CompanyDataSumaryFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.contentFragment,fragment1).commit();

        ratesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                sunat_api = dataSnapshot.child("sunat_api").getValue().toString();

                userRef.addValueEventListener(new ValueEventListener() {
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

                            txtText2.setText("");
                            txtText2.setBackgroundResource(R.drawable.check);
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
                            txtText1.setText("");
                            txtText1.setBackgroundResource(R.drawable.check);
                        }

                        if (dataSnapshot.hasChild("customer_output") && dataSnapshot.hasChild("company_value")) {

                            txtText3.setText("");
                            txtText3.setBackgroundResource(R.drawable.check);


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

        txtText1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.contentFragment,fragment1).commit();
            }
        });
        txtText2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.contentFragment,fragment2).commit();
            }
        });
        txtText3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.contentFragment,fragment3).commit();
            }
        });
        txtText4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.contentFragment,fragment4).commit();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_CAMERA_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "AL RECHAZAR LOS PERMISOS ALGUNAS FUNCIONES NO ESTARÁN DISPONIBLES", Toast.LENGTH_LONG).show();
            }
        }
        if (requestCode == MY_STORAGE_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "AL RECHAZAR LOS PERMISOS ALGUNAS FUNCIONES NO ESTARÁN DISPONIBLES", Toast.LENGTH_LONG).show();
            }
        }
    }
}
