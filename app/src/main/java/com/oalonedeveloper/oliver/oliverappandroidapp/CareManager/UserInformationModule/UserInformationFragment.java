package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.UserInformationModule;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.IntructionModule.IntroductionModuleActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;


public class UserInformationFragment extends Fragment {

    TextView txtCommercialName,txtSocialReason,txtRuc,txtLocation,txtCompanyType,txtEconomicActivity,txtInscriptionDate,txtCompanyState,txtFullName,txtDni,txtUserLocation,txtDateOfBirth,txtSex,txtOccupation,txtAcademicDegree;
    DatabaseReference companyRef, userRef;
    String post_key,company_name,company_social_reason,company_ruc,company_address,company_type,company_economic_activity,company_bth_day,company_bth_month,company_bth_year,company_state,uid,fullname,document_number,department,province,district,address,bth_day,bth_month,bth_year,gender,occupation,
            academic_degree;
    ImageButton btnTest1,btnTest2,btnTest3,btnTest4,btnTest5,btnTest6,btnTest7,btnTest8;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_information, container, false);

        post_key = getActivity().getIntent().getExtras().getString("post_key");

        txtCommercialName = view.findViewById(R.id.txtCommercialName);
        txtSocialReason = view.findViewById(R.id.txtSocialReason);
        txtRuc = view.findViewById(R.id.txtRuc);
        txtLocation = view.findViewById(R.id.txtLocation);
        txtCompanyType = view.findViewById(R.id.txtCompanyType);
        txtEconomicActivity = view.findViewById(R.id.txtEconomicActivity);
        txtInscriptionDate = view.findViewById(R.id.txtInscriptionDate);
        txtCompanyState = view.findViewById(R.id.txtCompanyState);
        txtFullName = view.findViewById(R.id.txtFullName);
        txtDni = view.findViewById(R.id.txtDni);
        txtUserLocation = view.findViewById(R.id.txtUserLocation);
        txtDateOfBirth = view.findViewById(R.id.txtDateOfBirth);
        txtSex = view.findViewById(R.id.txtSex);
        txtOccupation = view.findViewById(R.id.txtOccupation);
        txtAcademicDegree = view.findViewById(R.id.txtAcademicDegree);
        btnTest1 = view.findViewById(R.id.btnTest1);
        btnTest2 = view.findViewById(R.id.btnTest2);
        btnTest3 = view.findViewById(R.id.btnTest3);
        btnTest4 = view.findViewById(R.id.btnTest4);
        btnTest5 = view.findViewById(R.id.btnTest5);
        btnTest6 = view.findViewById(R.id.btnTest6);
        btnTest7 = view.findViewById(R.id.btnTest7);
        btnTest8 = view.findViewById(R.id.btnTest8);


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

                txtCommercialName.setText("NOMBRE COMERCIAL: "+company_name.toUpperCase());
                txtSocialReason.setText("RAZÓN SOCIAL: "+company_social_reason);
                txtRuc.setText("RUC: "+company_ruc);
                txtLocation.setText("UBICACIÓN: "+company_address);
                txtCompanyType.setText("TIPO DE EMPRESA: "+company_type);
                txtEconomicActivity.setText("ACTIVIDAD ECONÓMICA: "+company_economic_activity);
                txtInscriptionDate.setText("FECHA DE INSCRIPCIÓN (SUNAT): "+company_bth_day+"/"+company_bth_month+"/"+company_bth_year);
                txtCompanyState.setText("ESTADO DE LA EMPRESA: "+company_state);

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

        btnTest1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), UserInformationModuleActivity.class);
                intent.putExtra("FRAGMENT_ID", 1);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });
        btnTest2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), UserInformationModuleActivity.class);
                intent.putExtra("FRAGMENT_ID", 2);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });
        btnTest3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), UserInformationModuleActivity.class);
                intent.putExtra("FRAGMENT_ID", 3);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });
        btnTest4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), UserInformationModuleActivity.class);
                intent.putExtra("FRAGMENT_ID", 4);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });
        btnTest5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), UserInformationModuleActivity.class);
                intent.putExtra("FRAGMENT_ID", 5);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });
        btnTest6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), UserInformationModuleActivity.class);
                intent.putExtra("FRAGMENT_ID", 6);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });
        btnTest7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), UserInformationModuleActivity.class);
                intent.putExtra("FRAGMENT_ID", 7);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });
        btnTest8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), UserInformationModuleActivity.class);
                intent.putExtra("FRAGMENT_ID", 8);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });

        return view;
    }
}
