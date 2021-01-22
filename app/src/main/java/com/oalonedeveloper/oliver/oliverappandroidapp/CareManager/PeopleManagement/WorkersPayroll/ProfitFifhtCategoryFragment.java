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
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;


public class ProfitFifhtCategoryFragment extends Fragment {

    DatabaseReference ratesRef,companyRef;
    double tax_unit_value,retention1,retention2,retention3,retention4,uit5,uit20,uit35,uit45,uit50;
    TextView txtTaxUnit,txtMonthlyRetention;
    EditText edtSalary,edtMonths,edtGratification1,edtGratification2;
    String post_key,profile_id,payment_amount;
    DecimalFormat decimalFormat;
    int day,month,year;
    double monthly_retention1,monthly_retention2,monthly_retention3,monthly_retention4,monthly_retention5,monthly_retention6,monthly_retention7,monthly_retention8,monthly_retention9,monthly_retention10,monthly_retention11,monthly_retention12;
    String month_retention_st;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profit_fifht_category, container, false);

        post_key = getActivity().getIntent().getExtras().getString("post_key");
        profile_id = getActivity().getIntent().getExtras().getString("profile_id");
        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");
        ratesRef = FirebaseDatabase.getInstance().getReference().child("Rates");

        decimalFormat = new DecimalFormat("0.00");

        txtTaxUnit = view.findViewById(R.id.txtTaxUnit);

        edtSalary = view.findViewById(R.id.edtSalary);
        edtMonths = view.findViewById(R.id.edtMonths);
        edtGratification1 = view.findViewById(R.id.edtGratification1);
        edtGratification2 = view.findViewById(R.id.edtGratification2);
        txtMonthlyRetention = view.findViewById(R.id.txtMonthlyRetention);

        Date date= new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        day = cal.get(Calendar.DAY_OF_MONTH);
        month = cal.get(Calendar.MONTH)+1;
        year = cal.get(Calendar.YEAR);

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

        edtMonths.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(edtMonths.getText().toString())) {

                } else {
                    calculateTax();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        edtGratification1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(edtGratification1.getText().toString())) {

                } else {
                    calculateTax();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        edtGratification2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(edtGratification2.getText().toString())) {

                } else {
                    calculateTax();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        ratesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                tax_unit_value = dataSnapshot.child("tax_unit_value").getValue(Double.class);
                txtTaxUnit.setText("Valor UIT: S/ "+tax_unit_value);

                companyRef.child(post_key).child("Job Profile").child(profile_id).child("Personal File").child("Company Worker Data").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            if (dataSnapshot.hasChild("payment_amount")) {
                                payment_amount = dataSnapshot.child("payment_amount").getValue().toString();
                                edtSalary.setText(payment_amount);
                                edtGratification1.setText(payment_amount);
                                edtGratification2.setText(payment_amount);
                                int real_mont = 12-month;
                                edtMonths.setText(real_mont+"");

                                calculateTax();
                            }
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

    private void calculateTax() {
        double salary = Double.parseDouble(edtSalary.getText().toString());
        int months = Integer.parseInt(edtMonths.getText().toString());
        double gratification1 = Double.parseDouble(edtGratification1.getText().toString());
        double gratification2 = Double.parseDouble(edtGratification2.getText().toString());

        double total_salary = salary*months;
        double annual_salary = total_salary+salary+gratification1+gratification2;

        double deduction = tax_unit_value*7;

        double real_annual_salary = annual_salary-deduction;

        if (real_annual_salary > 0.00) {

            uit5 = tax_unit_value*5;
            uit20 = tax_unit_value*20;
            uit35 = tax_unit_value*35;
            uit45 = tax_unit_value*45;


            if (real_annual_salary <= uit5) {
                retention1 = real_annual_salary*0.08;
                txtMonthlyRetention.setText("IMPUESTO PROYECTADO: "+retention1);

            } else if (real_annual_salary > uit5) {
                retention1 = uit5*0.08;
                real_annual_salary = real_annual_salary-uit5;
                retention2 = real_annual_salary*0.14;

                if (real_annual_salary > uit20 && real_annual_salary <= uit35) {
                    retention2 = uit20*0.14;
                    real_annual_salary = real_annual_salary-uit20;
                }

                if (real_annual_salary > uit35 && real_annual_salary <= uit45) {
                    retention3 = uit35*0.17;
                    real_annual_salary = real_annual_salary-uit35;
                }

                if (real_annual_salary > uit45) {
                    retention4 = uit45*0.20;
                }

                double total_annual_retention = retention1+retention2;

                txtMonthlyRetention.setText("IMPUESTO PROYECTADO: "+total_annual_retention);

            }

        }

    }
}
