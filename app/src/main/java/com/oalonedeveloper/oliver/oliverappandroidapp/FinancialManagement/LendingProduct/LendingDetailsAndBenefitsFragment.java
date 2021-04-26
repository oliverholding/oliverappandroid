package com.oalonedeveloper.oliver.oliverappandroidapp.FinancialManagement.LendingProduct;

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


public class LendingDetailsAndBenefitsFragment extends Fragment {

    TextView txtLendingDescription,txtBenefit1,txtBenefit2,txtBenefit3,txtBenefit4,txtMinAmount;
    DatabaseReference financialInstitutionsRef;
    String product_key,institution_key,product_completed_description,product_benefit1,product_benefit2,product_benefit3,product_benefit4,product_currency;
    double product_min_capital;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lending_details_and_benefits, container, false);

        product_key = getActivity().getIntent().getExtras().getString("product_key");
        institution_key = getActivity().getIntent().getExtras().getString("institution_key");

        financialInstitutionsRef = FirebaseDatabase.getInstance().getReference().child("Financial Institutions");

        txtLendingDescription = view.findViewById(R.id.txtLendingDescription);
        txtBenefit1 = view.findViewById(R.id.txtBenefit1);
        txtBenefit2 = view.findViewById(R.id.txtBenefit2);
        txtBenefit3 = view.findViewById(R.id.txtBenefit3);
        txtBenefit4 = view.findViewById(R.id.txtBenefit4);
        txtMinAmount = view.findViewById(R.id.txtMinAmount);

        financialInstitutionsRef.child(institution_key).child("Products").child(product_key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                product_completed_description = dataSnapshot.child("product_completed_description").getValue().toString();
                product_benefit1 = dataSnapshot.child("product_benefit1").getValue().toString();
                product_benefit2 = dataSnapshot.child("product_benefit2").getValue().toString();
                product_benefit3 = dataSnapshot.child("product_benefit3").getValue().toString();
                product_benefit4 = dataSnapshot.child("product_benefit4").getValue().toString();
                product_currency = dataSnapshot.child("product_currency").getValue().toString();
                product_min_capital = dataSnapshot.child("product_min_capital").getValue(Double.class);

                txtLendingDescription.setText(product_completed_description);
                txtBenefit1.setText("1. "+product_benefit1);
                txtBenefit2.setText("2. "+product_benefit2);
                txtBenefit3.setText("3. "+product_benefit3);
                txtBenefit4.setText("4. "+product_benefit4);

                if (product_currency.equals("PEN")) {
                    txtMinAmount.setText("Solicítalo desde S/ "+product_min_capital);
                }
                if (product_currency.equals("USD")) {
                    txtMinAmount.setText("Solicítalo desde $ "+product_min_capital);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return view;
    }
}
