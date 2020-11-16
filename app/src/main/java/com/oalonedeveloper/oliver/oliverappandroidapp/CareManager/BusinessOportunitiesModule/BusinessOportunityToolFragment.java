package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.BusinessOportunitiesModule;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.oalonedeveloper.oliver.oliverappandroidapp.Companies.MyCustomersActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;

import java.util.ArrayList;


public class BusinessOportunityToolFragment extends Fragment {

    Button btnMyCustomers,btnSendSurvey;
    String post_key,customers_exist,company_social_reason,customer_name,customer_phone;
    DatabaseReference myCompanyRef;
    RelativeLayout rootLayout;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_business_oportunity_tool, container, false);

        post_key = getActivity().getIntent().getExtras().getString("post_key");

        btnMyCustomers = view.findViewById(R.id.btnMyCustomers);
        btnSendSurvey = view.findViewById(R.id.btnSendSurvey);
        rootLayout = view.findViewById(R.id.rootLayout);

        myCompanyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");

        customers_exist = "false";

        myCompanyRef.child(post_key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild("Customers")) {
                    customers_exist = "true";
                }
                company_social_reason = dataSnapshot.child("company_social_reason").getValue().toString();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        btnMyCustomers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MyCustomersActivity.class);
                intent.putExtra("post_key",post_key);
                startActivity(intent);
            }
        });

        btnSendSurvey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (customers_exist.equals("true")) {
                    sendSmsMethod();
                } else {
                    Snackbar.make(rootLayout, "Debes tener al menos un cliente registrado", Snackbar.LENGTH_LONG).show();
                    return;
                }
            }
        });

        return view;
    }

    private void sendSmsMethod() {
        myCompanyRef.child(post_key).child("Customers").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot myDatasnapshot : dataSnapshot.getChildren()) {
                    customer_name = myDatasnapshot.child("customer_name").getValue().toString();
                    customer_phone = myDatasnapshot.child("customer_phone").getValue().toString();


                    String message = "Hola "+customer_name+", en Oliver te invitamos a responder una encuesta de "+company_social_reason+". Enlace de la encuesta: https://oliver.com.pe/company_survey/"+post_key;

                    SmsManager smsManager = SmsManager.getDefault();

                    ArrayList<String> parts =smsManager.divideMessage(message);

                    smsManager.sendMultipartTextMessage(customer_phone,null,parts,null,null);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
