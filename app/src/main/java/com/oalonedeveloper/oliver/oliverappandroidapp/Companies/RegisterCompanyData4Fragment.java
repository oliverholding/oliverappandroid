package com.oalonedeveloper.oliver.oliverappandroidapp.Companies;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RelativeLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;


public class RegisterCompanyData4Fragment extends Fragment {

    FirebaseAuth mAuth;
    DatabaseReference userRef;
    String currentUserID;
    RelativeLayout rootLayout;
    ProgressDialog loadingBar;
    RadioButton rdProducts,rdServices,rdPrice,rdQuality,rdSaleService;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register_company_data4, container, false);

        loadingBar = new ProgressDialog(getActivity());

        mAuth = FirebaseAuth.getInstance();
        currentUserID = mAuth.getCurrentUser().getUid();
        userRef = FirebaseDatabase.getInstance().getReference().child("Users").child(currentUserID).child("Company Registration");

        loadingBar.setTitle("Preparando todo...");
        loadingBar.setMessage("Cargando...");
        loadingBar.show();
        loadingBar.setCanceledOnTouchOutside(false);
        loadingBar.setCancelable(false);

        rdProducts = view.findViewById(R.id.rdProducts);
        rdServices = view.findViewById(R.id.rdServices);
        rdPrice = view.findViewById(R.id.rdPrice);
        rdQuality = view.findViewById(R.id.rdQuality);
        rdSaleService = view.findViewById(R.id.rdSaleService);

        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild("customer_output")) {
                    String customer_output = dataSnapshot.child("customer_output").getValue().toString();
                    if (customer_output.equals("Productos")) {
                        rdProducts.setChecked(true);
                    }
                    else if (customer_output.equals("Servicios")) {
                        rdServices.setChecked(true);
                    }

                }
                if (dataSnapshot.hasChild("company_value")) {
                    String company_value = dataSnapshot.child("company_value").getValue().toString();
                    if (company_value.equals("Precio")) {
                        rdPrice.setChecked(true);
                    }
                    else if (company_value.equals("Calidad")) {
                        rdQuality.setChecked(true);
                    }
                    else if (company_value.equals("Servicio Post Venta")) {
                        rdSaleService.setChecked(true);
                    }

                }

                loadingBar.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        rdProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userRef.child("customer_output").setValue(rdProducts.getText().toString());
            }
        });
        rdServices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userRef.child("customer_output").setValue(rdServices.getText().toString());
            }
        });

        rdPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userRef.child("company_value").setValue(rdPrice.getText().toString());
            }
        });
        rdQuality.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userRef.child("company_value").setValue(rdQuality.getText().toString());
            }
        });
        rdSaleService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userRef.child("company_value").setValue(rdSaleService.getText().toString());
            }
        });

        return view;
    }
}
