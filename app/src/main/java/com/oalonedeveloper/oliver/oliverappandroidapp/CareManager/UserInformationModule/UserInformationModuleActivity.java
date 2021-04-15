package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.UserInformationModule;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.StrategicDirection.LeanCanvas.RealLeanCanvasActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;
import es.dmoral.toasty.Toasty;

public class UserInformationModuleActivity extends AppCompatActivity {

    TextView txtCommercialName,txtSocialReason,txtRuc,txtLocation,txtCompanyType,txtEconomicActivity,txtInscriptionDate,txtCompanyState,txtFullName,txtDni,txtUserLocation,txtDateOfBirth,txtSex,txtOccupation,txtAcademicDegree;
    DatabaseReference companyRef, userRef;
    String post_key,company_name,company_social_reason,company_ruc,company_address,company_type,company_economic_activity,company_bth_day,company_bth_month,company_bth_year,company_state,uid,fullname,document_number,department,province,district,address,bth_day,bth_month,bth_year,gender,occupation,
            academic_degree,company_image;

    CircleImageView imgCompanyProfile;
    ImageView btnEdit1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_information_module);

        post_key = getIntent().getExtras().getString("post_key");

        txtCommercialName = findViewById(R.id.txtCommercialName);
        txtSocialReason = findViewById(R.id.txtSocialReason);
        txtRuc = findViewById(R.id.txtRuc);
        txtLocation = findViewById(R.id.txtLocation);
        txtCompanyType = findViewById(R.id.txtCompanyType);
        txtEconomicActivity = findViewById(R.id.txtEconomicActivity);
        txtInscriptionDate = findViewById(R.id.txtInscriptionDate);
        txtCompanyState = findViewById(R.id.txtCompanyState);
        txtFullName = findViewById(R.id.txtFullName);
        txtDni = findViewById(R.id.txtDni);
        txtUserLocation = findViewById(R.id.txtUserLocation);
        txtDateOfBirth = findViewById(R.id.txtDateOfBirth);
        txtSex = findViewById(R.id.txtSex);
        txtOccupation = findViewById(R.id.txtOccupation);
        txtAcademicDegree = findViewById(R.id.txtAcademicDegree);
        imgCompanyProfile = findViewById(R.id.imgCompanyProfile);
        btnEdit1 = findViewById(R.id.btnEdit1);


        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");
        userRef = FirebaseDatabase.getInstance().getReference().child("Users");

        companyRef.child(post_key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                company_name = dataSnapshot.child("company_name").getValue().toString();
                company_social_reason = dataSnapshot.child("company_social_reason").getValue().toString();
                company_ruc = dataSnapshot.child("company_ruc").getValue().toString();
                company_address = dataSnapshot.child("company_address").getValue().toString();
                company_type = dataSnapshot.child("company_type").getValue().toString();
                company_economic_activity = dataSnapshot.child("company_economic_activity").getValue().toString();
                company_bth_day = dataSnapshot.child("company_bth_day").getValue().toString();
                company_bth_month = dataSnapshot.child("company_bth_month").getValue().toString();
                company_bth_year = dataSnapshot.child("company_bth_year").getValue().toString();
                company_state = dataSnapshot.child("company_state").getValue().toString();
                uid = dataSnapshot.child("uid").getValue().toString();
                company_image = dataSnapshot.child("company_image").getValue().toString();

                txtCommercialName.setText("NOMBRE COMERCIAL: "+company_name.toUpperCase());
                txtSocialReason.setText("RAZÓN SOCIAL: "+company_social_reason);
                txtRuc.setText("RUC: "+company_ruc);
                txtLocation.setText("UBICACIÓN: "+company_address);
                txtCompanyType.setText("TIPO DE EMPRESA: "+company_type);
                txtEconomicActivity.setText("ACTIVIDAD ECONÓMICA: "+company_economic_activity);
                txtInscriptionDate.setText("FECHA DE INSCRIPCIÓN (SUNAT): "+company_bth_day+"/"+company_bth_month+"/"+company_bth_year);
                txtCompanyState.setText("ESTADO DE LA EMPRESA: "+company_state);

                Picasso.with(UserInformationModuleActivity.this).load(company_image).fit().into(imgCompanyProfile);

                userRef.child(uid).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        fullname = dataSnapshot.child("fullname").getValue().toString();
                        document_number = dataSnapshot.child("document_number").getValue().toString();
                        department = dataSnapshot.child("department").getValue().toString();
                        province = dataSnapshot.child("province").getValue().toString();
                        district = dataSnapshot.child("district").getValue().toString();
                        address = dataSnapshot.child("address").getValue().toString();
                        bth_day = dataSnapshot.child("bth_day").getValue().toString();
                        bth_month = dataSnapshot.child("bth_month").getValue().toString();
                        bth_year = dataSnapshot.child("bth_year").getValue().toString();
                        gender = dataSnapshot.child("gender").getValue().toString();
                        occupation = dataSnapshot.child("occupation").getValue().toString();
                        academic_degree = dataSnapshot.child("academic_degree").getValue().toString();

                        txtFullName.setText("NOMBRE COMPLETO: "+fullname.toUpperCase());
                        txtDni.setText("D.N.I: "+document_number);
                        txtUserLocation.setText("UBICACIÓN: "+department.toUpperCase()+", "+province.toUpperCase()+", "+district.toUpperCase()+", "+address.toUpperCase());
                        txtDateOfBirth.setText("FECHA DE NACIMIENTO: "+bth_day+"/"+bth_month+"/"+bth_year);
                        txtSex.setText("SEXO: "+gender.toUpperCase());
                        txtOccupation.setText("OCUPACIÓN: "+occupation);
                        txtAcademicDegree.setText("GRADO ACADÉMICO: "+academic_degree);
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

        btnEdit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showEditCompanyDialog();
            }
        });

    }

    private void showEditCompanyDialog() {
        final AlertDialog dialog = new AlertDialog.Builder(this).create();
        dialog.setCancelable(false);

        LayoutInflater inflater = LayoutInflater.from(this);
        View finance_method = inflater.inflate(R.layout.edit_company_information_dialog,null);

        final EditText edtName,edtSocialReason,edtRuc,edtLocation,edtCompanyType,edtEconomicActivity,edtBthDay,edtBthMonth,edtBthYear,edtCompanyState;
        Button btnUpdateData;

        edtName = finance_method.findViewById(R.id.edtName);
        edtSocialReason = finance_method.findViewById(R.id.edtSocialReason);
        edtRuc = finance_method.findViewById(R.id.edtRuc);
        edtLocation = finance_method.findViewById(R.id.edtLocation);
        edtCompanyType = finance_method.findViewById(R.id.edtCompanyType);
        edtEconomicActivity = finance_method.findViewById(R.id.edtEconomicActivity);
        edtBthDay = finance_method.findViewById(R.id.edtBthDay);
        edtBthMonth = finance_method.findViewById(R.id.edtBthMonth);
        edtBthYear = finance_method.findViewById(R.id.edtBthYear);
        edtCompanyState = finance_method.findViewById(R.id.edtCompanyState);
        btnUpdateData = finance_method.findViewById(R.id.btnUpdateData);

        companyRef.child(post_key).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                company_name = dataSnapshot.child("company_name").getValue().toString();
                company_social_reason = dataSnapshot.child("company_social_reason").getValue().toString();
                company_ruc = dataSnapshot.child("company_ruc").getValue().toString();
                company_address = dataSnapshot.child("company_address").getValue().toString();
                company_type = dataSnapshot.child("company_type").getValue().toString();
                company_economic_activity = dataSnapshot.child("company_economic_activity").getValue().toString();
                company_bth_day = dataSnapshot.child("company_bth_day").getValue().toString();
                company_bth_month = dataSnapshot.child("company_bth_month").getValue().toString();
                company_bth_year = dataSnapshot.child("company_bth_year").getValue().toString();
                company_state = dataSnapshot.child("company_state").getValue().toString();
                uid = dataSnapshot.child("uid").getValue().toString();
                company_image = dataSnapshot.child("company_image").getValue().toString();

                edtName.setText(company_name);
                edtSocialReason.setText(company_social_reason);
                edtRuc.setText(company_ruc);
                edtLocation.setText(company_address);
                edtCompanyType.setText(company_type);
                edtEconomicActivity.setText(company_economic_activity);
                edtBthDay.setText(company_bth_day);
                edtBthMonth.setText(company_bth_month);
                edtBthYear.setText(company_bth_year);
                edtCompanyState.setText(company_state);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        btnUpdateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                companyRef.child(post_key).child("company_name").setValue(edtName.getText().toString());
                companyRef.child(post_key).child("company_social_reason").setValue(edtSocialReason.getText().toString());
                companyRef.child(post_key).child("company_ruc").setValue(edtRuc.getText().toString());
                companyRef.child(post_key).child("company_address").setValue(edtLocation.getText().toString());
                companyRef.child(post_key).child("company_type").setValue(edtCompanyType.getText().toString());
                companyRef.child(post_key).child("company_economic_activity").setValue(edtEconomicActivity.getText().toString());
                companyRef.child(post_key).child("company_bth_day").setValue(edtBthDay.getText().toString());
                companyRef.child(post_key).child("company_bth_month").setValue(edtBthMonth.getText().toString());
                companyRef.child(post_key).child("company_bth_year").setValue(edtBthYear.getText().toString());
                companyRef.child(post_key).child("company_state").setValue(edtCompanyState.getText().toString());
                dialog.dismiss();
                Toasty.success(UserInformationModuleActivity.this, "Actualizado", Toast.LENGTH_LONG).show();
            }
        });


        dialog.setView(finance_method);
        dialog.show();
    }


}
