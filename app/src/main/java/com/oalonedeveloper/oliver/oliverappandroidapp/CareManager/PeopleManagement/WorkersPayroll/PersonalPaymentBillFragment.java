package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.PeopleManagement.WorkersPayroll;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;

import java.util.Calendar;
import java.util.Date;

public class PersonalPaymentBillFragment extends Fragment {

    TextView txtBillPeriod,txtPeriods,txtCompanyName,txtCompanyRuc,txtCompanyAddress,txtWorkerName,txtWorkerDocument,txtCharge,txtBeginDate;
    int day,month,year,first_day,last_day;
    String post_key,profile_id,company_social_reason,company_ruc,company_address,job_profile_name,job_profile_surname,job_profile_job_name;
    DatabaseReference companyRef;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_personal_payment_bill, container, false);

        txtBillPeriod = view.findViewById(R.id.txtBillPeriod);
        txtPeriods = view.findViewById(R.id.txtPeriods);
        txtCompanyName = view.findViewById(R.id.txtCompanyName);
        txtCompanyRuc = view.findViewById(R.id.txtCompanyRuc);
        txtCompanyAddress = view.findViewById(R.id.txtCompanyAddress);
        txtWorkerName = view.findViewById(R.id.txtWorkerName);
        txtWorkerDocument = view.findViewById(R.id.txtWorkerDocument);
        txtCharge = view.findViewById(R.id.txtCharge);
        txtBeginDate = view.findViewById(R.id.txtBeginDate);

        post_key = getActivity().getIntent().getExtras().getString("post_key");
        profile_id = getActivity().getIntent().getExtras().getString("profile_id");
        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");

        Date date= new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        day = cal.get(Calendar.DAY_OF_MONTH);
        month = cal.get(Calendar.MONTH)+1;
        year = cal.get(Calendar.YEAR);
        first_day = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
        last_day = cal.getActualMaximum(Calendar.DAY_OF_MONTH);


        txtBillPeriod.setText("BOLETA DE PAGO - "+year+" "+month);

        txtPeriods.setText("DEL "+first_day+"/"+month+"/"+year+" AL "+last_day+"/"+month+"/"+year);

        companyRef.child(post_key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                company_social_reason = dataSnapshot.child("company_social_reason").getValue().toString();
                company_ruc = dataSnapshot.child("company_ruc").getValue().toString();
                company_address = dataSnapshot.child("company_address").getValue().toString();

                txtCompanyName.setText(company_social_reason);
                txtCompanyRuc.setText("RUC: "+company_ruc);
                txtCompanyAddress.setText(company_address);

                companyRef.child(post_key).child("Job Profile").child(profile_id).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        job_profile_name = dataSnapshot.child("job_profile_name").getValue().toString();
                        job_profile_surname = dataSnapshot.child("job_profile_surname").getValue().toString();
                        job_profile_job_name = dataSnapshot.child("job_profile_job_name").getValue().toString();

                        txtWorkerName.setText("Apellidos y Nombres: "+job_profile_surname+" "+job_profile_name);
                        txtCharge.setText("Cargo: "+job_profile_job_name);

                        if (dataSnapshot.hasChild("Birth Data")) {
                            String document_number = dataSnapshot.child("Birth Data").child("document_number").getValue().toString();
                            txtWorkerDocument.setText("Documento: "+document_number);
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

        return view;
    }
}
