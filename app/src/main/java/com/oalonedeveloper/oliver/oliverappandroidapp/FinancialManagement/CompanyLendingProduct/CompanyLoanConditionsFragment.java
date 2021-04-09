package com.oalonedeveloper.oliver.oliverappandroidapp.FinancialManagement.CompanyLendingProduct;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.oalonedeveloper.oliver.oliverappandroidapp.FinancialManagement.LendingProduct.LendingSimulationAdapter;
import com.oalonedeveloper.oliver.oliverappandroidapp.FinancialManagement.LendingProduct.LendingSimulationModel;
import com.oalonedeveloper.oliver.oliverappandroidapp.FinancialManagement.LendingProduct.LoanContractFragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.FinancialManagement.LendingProduct.LoanRequestsListActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class CompanyLoanConditionsFragment extends Fragment {

    ArrayList<LendingSimulationModel> list;
    RecyclerView recyclerView;
    String operation_id,post_key,request_id,verification_arrow,company_id;
    DatabaseReference lendingRef,userRef,financialInstitutionsRef, userRealRef;
    TextView txtLoanAmount,txtLoanMonth,txtGraceMonth,txtTcea,txtReferenceDate,txtCancelLoan;
    int day,month,year,payment_year;
    String capital_st,amortization_st,interest_st,desgravament_st,fee_st,total_quote_st,fixed_quote_st;
    DecimalFormat decimalFormat;
    CheckBox checkBox;
    Button btnContinue;
    RelativeLayout rootLayout;
    Fragment fragment2;
    LinearLayout conditionsLayout;
    ImageView btnOpenClose;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_company_loan_conditions, container, false);

        operation_id = getActivity().getIntent().getExtras().getString("operation_id");
        post_key = getActivity().getIntent().getExtras().getString("post_key");
        company_id = getActivity().getIntent().getExtras().getString("company_id");
        request_id = getActivity().getIntent().getExtras().getString("request_id");
        lendingRef = FirebaseDatabase.getInstance().getReference().child("Company Lendings");
        userRef = FirebaseDatabase.getInstance().getReference().child("My Companies");
        userRealRef = FirebaseDatabase.getInstance().getReference().child("Users");
        financialInstitutionsRef = FirebaseDatabase.getInstance().getReference().child("Financial Institutions");

        decimalFormat = new DecimalFormat("0.00");


        list = new ArrayList<>();

        recyclerView = view.findViewById(R.id.recyclerView);

        recyclerView.setHasFixedSize(true);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        LendingSimulationAdapter adapter = new LendingSimulationAdapter(list);
        recyclerView.setAdapter(adapter);

        txtLoanAmount = view.findViewById(R.id.txtLoanAmount);
        txtLoanMonth = view.findViewById(R.id.txtLoanMonth);
        txtGraceMonth = view.findViewById(R.id.txtGraceMonth);
        txtTcea = view.findViewById(R.id.txtTcea);
        txtReferenceDate = view.findViewById(R.id.txtReferenceDate);
        checkBox = view.findViewById(R.id.checkBox);
        btnContinue = view.findViewById(R.id.btnContinue);
        txtCancelLoan = view.findViewById(R.id.txtCancelLoan);
        conditionsLayout = view.findViewById(R.id.conditionsLayout);
        btnOpenClose = view.findViewById(R.id.btnOpenClose);

        fragment2 = new CompanyLoanContractFragment();

        Date date= new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        day = cal.get(Calendar.DAY_OF_MONTH);
        month = cal.get(Calendar.MONTH)+1;
        year = cal.get(Calendar.YEAR);

        btnContinue.setVisibility(View.GONE);

        verification_arrow = "open";

        btnOpenClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (verification_arrow.equals("open")) {
                    verification_arrow = "close";
                    btnOpenClose.setImageResource(R.drawable.blue_arrow_down_vector_asset);
                    conditionsLayout.setVisibility(View.GONE);
                }
                else if (verification_arrow.equals("close")) {
                    verification_arrow = "open";
                    btnOpenClose.setImageResource(R.drawable.blue_arrow_up_vector_asset);
                    conditionsLayout.setVisibility(View.VISIBLE);
                }
            }
        });

        lendingRef.child(operation_id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String customer_id = dataSnapshot.child("customer_id").getValue().toString();
                String issuing_amount = dataSnapshot.child("issuing_amount").getValue().toString();
                String issuing_currency = dataSnapshot.child("issuing_currency").getValue().toString();
                double issuing_customer_fixed_payment = dataSnapshot.child("issuing_customer_fixed_payment").getValue(Double.class);
                String issuing_customer_payment = dataSnapshot.child("issuing_customer_payment").getValue().toString();
                String issuing_day = dataSnapshot.child("issuing_day").getValue().toString();
                String issuing_desgravamen_rate = dataSnapshot.child("issuing_desgravamen_rate").getValue().toString();
                String issuing_document_number = dataSnapshot.child("issuing_document_number").getValue().toString();
                String issuing_expiration_day = dataSnapshot.child("issuing_expiration_day").getValue().toString();
                String issuing_expiration_month = dataSnapshot.child("issuing_expiration_month").getValue().toString();
                String issuing_expiration_year = dataSnapshot.child("issuing_expiration_year").getValue().toString();
                String issuing_fee = dataSnapshot.child("issuing_fee").getValue().toString();
                String issuing_lending_month = dataSnapshot.child("issuing_lending_month").getValue().toString();
                String issuing_month = dataSnapshot.child("issuing_month").getValue().toString();
                String issuing_product_id = dataSnapshot.child("issuing_product_id").getValue().toString();
                String issuing_tcea = dataSnapshot.child("issuing_tcea").getValue().toString();
                String issuing_tea = dataSnapshot.child("issuing_tea").getValue().toString();
                String issuing_worker_id = dataSnapshot.child("issuing_worker_id").getValue().toString();
                int issuing_year = dataSnapshot.child("issuing_year").getValue(Integer.class);
                String lending_state = dataSnapshot.child("lending_state").getValue().toString();
                String operation_id = dataSnapshot.child("operation_id").getValue().toString();
                String issuing_grace_month = dataSnapshot.child("issuing_grace_month").getValue().toString();

                if (issuing_currency.equals("PEN")) {
                    txtLoanAmount.setText("Monto del Préstamo: S/ "+issuing_amount);
                }
                if (issuing_currency.equals("USD")) {
                    txtLoanAmount.setText("Monto del Préstamo: $ "+issuing_amount);
                }

                double tcea_db = Double.parseDouble(issuing_tcea);
                String tcea_st = decimalFormat.format(tcea_db);

                txtLoanMonth.setText("Duración del Préstamo: "+issuing_lending_month+" meses");
                txtGraceMonth.setText("Período de Gracia: "+issuing_grace_month+" meses");
                txtTcea.setText("Tasa de Costo Efectivo Anual: "+tcea_st+"%");
                txtReferenceDate.setText("Fecha Rerencial de Inicio de Pago: ");

                int issuing_month_int = Integer.parseInt(issuing_month);
                int issuing_grace_month_int = Integer.parseInt(issuing_grace_month);

                int payment_month = issuing_month_int+1+issuing_grace_month_int;

                if (payment_month > 12) {
                    payment_year = year+1;
                    payment_month = payment_month-12;
                    txtReferenceDate.setText("Fecha Rerencial de Inicio de Pago: "+day+"/"+payment_month+"/"+payment_year);

                } else {
                    txtReferenceDate.setText("Fecha Rerencial de Inicio de Pago: "+day+"/"+payment_month+"/"+year);
                }

                int issuing_lending_month_int = Integer.parseInt(issuing_lending_month);

                userRef.child(customer_id).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        String uid = dataSnapshot.child("uid").getValue().toString();
                        final String company_social_reason = dataSnapshot.child("company_social_reason").getValue().toString();

                        userRealRef.child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                String document_number = dataSnapshot.child("document_number").getValue().toString();
                                String document_type = dataSnapshot.child("document_type").getValue().toString();
                                String fullname = dataSnapshot.child("fullname").getValue().toString();

                                checkBox.setText("Yo, "+fullname+" con "+document_type+": "+document_number+", representante legal de la empresa "+company_social_reason+" afirmo que estoy de acuerdo con estas condiciones.");
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

                if (issuing_customer_payment.equals("variable")) {

                    double capital = Double.parseDouble(issuing_amount);
                    double months = Double.parseDouble(issuing_lending_month);
                    double tea = Double.parseDouble(issuing_tea)/100;
                    double desgravamen_rate = Double.parseDouble(issuing_desgravamen_rate)/100;
                    double fee = Double.parseDouble(issuing_fee);

                    double rate = Math.pow((1+tea),(1/12.00))-1;

                    double factor_top = Math.pow((1+rate),months)*rate;
                    double factor_up = Math.pow((1+rate),months)-1;
                    double quote = (factor_top/factor_up)*capital;

                    double interest = capital*rate;
                    double amortization = quote-interest;
                    double desgravamen = capital*desgravamen_rate;
                    double total_quote = quote+desgravamen+fee;

                    amortization_st = decimalFormat.format(amortization);
                    interest_st = decimalFormat.format(interest);
                    capital_st = decimalFormat.format(capital);
                    desgravament_st = decimalFormat.format(desgravamen);
                    fee_st = decimalFormat.format(fee);
                    total_quote_st = decimalFormat.format(total_quote);

                    int quote_number = 0;
                    payment_month = payment_month-1;
                    for (int i = 1; i <= issuing_lending_month_int; i++) {

                        quote_number = quote_number+1;

                        if (payment_month >= 12) {
                            payment_month = payment_month-11;
                            payment_year = year+1;
                        } else {
                            payment_month = payment_month+1;
                            payment_year = year;
                        }

                        list.add(new LendingSimulationModel(quote_number+"",day+"/"+payment_month+"/"+payment_year,capital_st+"",amortization_st+"",interest_st+"",desgravament_st+"",fee_st,total_quote_st+""));

                        capital = capital-amortization;

                        interest = capital*rate;
                        amortization = quote-interest;
                        desgravamen = capital*desgravamen_rate;
                        total_quote = quote+desgravamen+fee;

                        capital_st = decimalFormat.format(capital) ;
                        amortization_st = decimalFormat.format(amortization);
                        interest_st = decimalFormat.format(interest);
                        desgravament_st = decimalFormat.format(desgravamen);
                        total_quote_st = decimalFormat.format(total_quote);
                    }

                } else {
                    double capital = Double.parseDouble(issuing_amount);
                    double months = Double.parseDouble(issuing_lending_month);
                    double tea = Double.parseDouble(issuing_tea)/100;
                    double desgravamen_rate = Double.parseDouble(issuing_desgravamen_rate)/100;
                    double fee = Double.parseDouble(issuing_fee);

                    double rate = Math.pow((1+tea),(1/12.00))-1;

                    double factor_top = Math.pow((1+rate),months)*rate;
                    double factor_up = Math.pow((1+rate),months)-1;
                    double quote = (factor_top/factor_up)*capital;

                    double interest = capital*rate;
                    double amortization = quote-interest;
                    double desgravamen = capital*desgravamen_rate;
                    double total_quote = quote+desgravamen+fee;

                    amortization_st = decimalFormat.format(amortization);
                    interest_st = decimalFormat.format(interest);
                    capital_st = decimalFormat.format(capital);
                    desgravament_st = decimalFormat.format(desgravamen);
                    fee_st = decimalFormat.format(fee);
                    total_quote_st = decimalFormat.format(total_quote);

                    fixed_quote_st = decimalFormat.format(issuing_customer_fixed_payment);

                    int quote_number = 0;
                    payment_month = payment_month-1;
                    for (int i = 1; i <= issuing_lending_month_int; i++) {

                        quote_number = quote_number+1;

                        if (payment_month >= 12) {
                            payment_month = payment_month-11;
                            payment_year = year+1;
                        } else {
                            payment_month = payment_month+1;
                            payment_year = year;
                        }

                        list.add(new LendingSimulationModel(quote_number+"",day+"/"+payment_month+"/"+payment_year,capital_st+"",amortization_st+"",interest_st+"",desgravament_st+"",fee_st,fixed_quote_st+""));

                        capital = capital-amortization;

                        interest = capital*rate;
                        amortization = quote-interest;
                        desgravamen = capital*desgravamen_rate;
                        total_quote = quote+desgravamen+fee;

                        capital_st = decimalFormat.format(capital) ;
                        amortization_st = decimalFormat.format(amortization);
                        interest_st = decimalFormat.format(interest);
                        desgravament_st = decimalFormat.format(desgravamen);
                        total_quote_st = decimalFormat.format(total_quote);
                    }
                }



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox.isChecked()) {
                    btnContinue.setVisibility(View.VISIBLE);

                } else {
                    btnContinue.setVisibility(View.GONE);
                }
            }
        });

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox.isChecked()) {
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.contentFragment,fragment2).commit();

                } else {
                    Snackbar.make(rootLayout,"Debes aceptar las condiciones",Snackbar.LENGTH_SHORT).show();
                }
            }
        });

        txtCancelLoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AlertDialog dialog = new AlertDialog.Builder(getActivity()).create();

                LayoutInflater inflater = LayoutInflater.from(getActivity());
                View finance_method = inflater.inflate(R.layout.decline_loan_dialog,null);

                Button btnReturn;
                TextView txtReject;

                btnReturn = finance_method.findViewById(R.id.btnReturn);
                txtReject = finance_method.findViewById(R.id.txtReject);

                btnReturn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                txtReject.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        financialInstitutionsRef.child(post_key).child("Company Loan Requests").child(request_id).child("request_state").setValue("canceled");
                        Intent intent = new Intent(getActivity(), CompanyLoanRequestsListActivity.class);
                        intent.putExtra("post_key",post_key);
                        intent.putExtra("company_id",company_id);
                        startActivity(intent);
                        getActivity().finish();
                        dialog.dismiss();
                    }
                });

                dialog.setView(finance_method);
                dialog.show();

            }
        });

        return view;
    }
}
