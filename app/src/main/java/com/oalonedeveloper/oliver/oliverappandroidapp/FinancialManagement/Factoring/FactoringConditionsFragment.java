package com.oalonedeveloper.oliver.oliverappandroidapp.FinancialManagement.Factoring;

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

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.oalonedeveloper.oliver.oliverappandroidapp.FinancialManagement.CompanyLendingProduct.CompanyLoanContractFragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.FinancialManagement.CompanyLendingProduct.CompanyLoanRequestsListActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.FinancialManagement.LendingProduct.LendingSimulationAdapter;
import com.oalonedeveloper.oliver.oliverappandroidapp.FinancialManagement.LendingProduct.LendingSimulationModel;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;


public class FactoringConditionsFragment extends Fragment {

    String operation_id,post_key,request_id,verification_arrow,company_id;
    DatabaseReference lendingRef,userRef,financialInstitutionsRef, userRealRef;
    TextView txtCancelLoan;
    int day,month,year,payment_year;
    DecimalFormat decimalFormat;
    CheckBox checkBox;
    Button btnContinue;
    RelativeLayout rootLayout;
    Fragment fragment2;
    LinearLayout conditionsLayout;
    ImageView btnOpenClose;
    TextView txtInvoiceAmount,txtAmountToFinance,txtInvoiceExpirationDate,txtDaysToFinance,txtDiscount,txtFee,txtInterest,txtIgv,txtRuc,txtSocialReason,txtFactoringAmount;
    long diff,expiration_days_ago;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_factoring_conditions, container, false);

        operation_id = getActivity().getIntent().getExtras().getString("operation_id");
        post_key = getActivity().getIntent().getExtras().getString("post_key");
        company_id = getActivity().getIntent().getExtras().getString("company_id");
        request_id = getActivity().getIntent().getExtras().getString("request_id");
        lendingRef = FirebaseDatabase.getInstance().getReference().child("Factoring");
        userRef = FirebaseDatabase.getInstance().getReference().child("My Companies");
        userRealRef = FirebaseDatabase.getInstance().getReference().child("Users");
        financialInstitutionsRef = FirebaseDatabase.getInstance().getReference().child("Financial Institutions");

        decimalFormat = new DecimalFormat("0.00");


        checkBox = view.findViewById(R.id.checkBox);
        btnContinue = view.findViewById(R.id.btnContinue);
        txtCancelLoan = view.findViewById(R.id.txtCancelLoan);
        conditionsLayout = view.findViewById(R.id.conditionsLayout);
        btnOpenClose = view.findViewById(R.id.btnOpenClose);

        txtInvoiceAmount = view.findViewById(R.id.txtInvoiceAmount);
        txtAmountToFinance = view.findViewById(R.id.txtAmountToFinance);
        txtInvoiceExpirationDate = view.findViewById(R.id.txtInvoiceExpirationDate);
        txtDaysToFinance = view.findViewById(R.id.txtDaysToFinance);
        txtDiscount = view.findViewById(R.id.txtDiscount);
        txtFee = view.findViewById(R.id.txtFee);
        txtInterest = view.findViewById(R.id.txtInterest);
        txtIgv = view.findViewById(R.id.txtIgv);
        txtRuc = view.findViewById(R.id.txtRuc);
        txtSocialReason = view.findViewById(R.id.txtSocialReason);
        txtFactoringAmount = view.findViewById(R.id.txtFactoringAmount);

        fragment2 = new FactoringContractFragment();

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
                String invoice_amount_st = dataSnapshot.child("invoice_amount").getValue().toString();
                String invoice_currency = dataSnapshot.child("invoice_currency").getValue().toString();

                String invoice_expiration_day = dataSnapshot.child("invoice_expiration_day").getValue().toString();
                String invoice_expiration_month = dataSnapshot.child("invoice_expiration_month").getValue().toString();
                String invoice_expiration_year = dataSnapshot.child("invoice_expiration_year").getValue().toString();

                String factoring_fee_amount = dataSnapshot.child("factoring_fee_amount").getValue().toString();

                double tea = dataSnapshot.child("factoring_tea").getValue(Double.class)/100;
                double warranty_rate = dataSnapshot.child("factoring_warranty_factor_amount").getValue(Double.class)/100;
                double factoring_max_rate = dataSnapshot.child("factoring_max_rate").getValue(Double.class)/100;
                double product_rate_fee = Double.parseDouble(factoring_fee_amount)/100;

                double invoice_amount = Double.parseDouble(invoice_amount_st);

                String factoring_customer_ruc = dataSnapshot.child("factoring_customer_ruc").getValue().toString();


                SimpleDateFormat myFormat = new SimpleDateFormat("dd MM yyyy");
                String inputString1 = day+" "+month+" "+year;
                String inputString2 = invoice_expiration_day+" "+invoice_expiration_month+" "+invoice_expiration_year;

                txtRuc.setText("RUC: "+factoring_customer_ruc);

                try {
                    Date date1  = myFormat.parse(inputString1);
                    Date date2 = myFormat.parse(inputString2);
                    diff = date2.getTime() - date1.getTime();

                    expiration_days_ago = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
                    expiration_days_ago = Math.abs(expiration_days_ago);

                    txtDaysToFinance.setText("Días a Financiar: "+expiration_days_ago+" días");

                } catch (ParseException e) {
                    e.printStackTrace();
                }

                double amount_to_finance = invoice_amount*factoring_max_rate;
                double ted = Math.pow((1+tea),(1.00/360.00))-1;

                double discount = invoice_amount-amount_to_finance;
                double interest = (amount_to_finance*(1-(1/(Math.pow((1+ted),expiration_days_ago)))));
                double fee = (invoice_amount*(product_rate_fee));
                double igv = fee*0.18;
                double customer_payment = warranty_rate*amount_to_finance;
                double factoring = amount_to_finance-(interest+igv)-customer_payment;

                String factoring_st = decimalFormat.format(factoring);
                String amount_to_finance_st = decimalFormat.format(amount_to_finance);
                String discount_st = decimalFormat.format(discount);
                String fee_st = decimalFormat.format(fee);
                String interest_st = decimalFormat.format(interest);
                String igv_st = decimalFormat.format(igv);

                txtInvoiceExpirationDate.setText("Vencimiento de la Factura: "+invoice_expiration_day+"/"+invoice_expiration_month+"/"+invoice_expiration_year);

                if (invoice_currency.equals("PEN")) {
                    txtFactoringAmount.setText("MONTO A RECIBIR HOY: S/ "+factoring_st);
                    txtInvoiceAmount.setText("Monto de la Factura:  S/ "+invoice_amount_st);
                    txtAmountToFinance.setText("Monto a Financiar: S/ "+amount_to_finance_st);
                    txtDiscount.setText("Descuento: S/ "+discount_st);
                    txtFee.setText("Comisión: S/ "+fee_st);
                    txtInterest.setText("Interéses: S/ "+interest_st);
                    txtIgv.setText("IGV: S/ "+igv_st);

                } else if (invoice_currency.equals("USD")) {
                    txtFactoringAmount.setText("MONTO A RECIBIR HOY: $ "+factoring_st);
                    txtInvoiceAmount.setText("Monto de la Factura:  $ "+invoice_amount_st);
                    txtAmountToFinance.setText("Monto a Financiar: $ "+amount_to_finance_st);
                    txtDiscount.setText("Descuento: $ "+discount_st);
                    txtFee.setText("Comisión: $ "+fee_st);
                    txtInterest.setText("Interéses: $ "+interest_st);
                    txtIgv.setText("IGV: $ "+igv_st);

                }


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
                        Intent intent = new Intent(getActivity(), FactoringRequestsListActivity.class);
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
