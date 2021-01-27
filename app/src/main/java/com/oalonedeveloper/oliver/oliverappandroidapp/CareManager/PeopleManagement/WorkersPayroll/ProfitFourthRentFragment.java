package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.PeopleManagement.WorkersPayroll;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;

import java.text.DecimalFormat;

public class ProfitFourthRentFragment extends Fragment {

    DatabaseReference ratesRef,companyRef;
    double tax_unit_value,four_category_max;
    TextView txtMonthlyRetention;
    String post_key,profile_id;
    DecimalFormat decimalFormat;
    EditText edtSalary;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profit_fourth_rent, container, false);

        post_key = getActivity().getIntent().getExtras().getString("post_key");
        profile_id = getActivity().getIntent().getExtras().getString("profile_id");
        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");
        ratesRef = FirebaseDatabase.getInstance().getReference().child("Rates");

        decimalFormat = new DecimalFormat("0.00");

        edtSalary = view.findViewById(R.id.edtSalary);

        txtMonthlyRetention = view.findViewById(R.id.txtMonthlyRetention);

        ratesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                tax_unit_value = dataSnapshot.child("tax_unit_value").getValue(Double.class);
                four_category_max = dataSnapshot.child("four_category_max").getValue(Double.class);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        edtSalary.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(edtSalary.getText().toString())) {

                } else {
                    calculateTax();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        return view;
    }

    private void calculateTax() {
        double salary = Double.parseDouble(edtSalary.getText().toString());

        if (salary > four_category_max) {
            double retention = salary * 0.08;
            double monthly_retention = retention/12;
            String retention_st = decimalFormat.format(retention);
            String monthly_retention_st = decimalFormat.format(monthly_retention);
            txtMonthlyRetention.setText("RETENCIÓN MENSUAL: S/ " + monthly_retention_st);
        } else {
            txtMonthlyRetention.setText("RETENCIÓN MENSUAL: S/ 0.00");
        }
    }
}
