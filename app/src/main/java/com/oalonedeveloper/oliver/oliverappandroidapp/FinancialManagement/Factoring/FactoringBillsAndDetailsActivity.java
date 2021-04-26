package com.oalonedeveloper.oliver.oliverappandroidapp.FinancialManagement.Factoring;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
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
import com.oalonedeveloper.oliver.oliverappandroidapp.R;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class FactoringBillsAndDetailsActivity extends AppCompatActivity {

    String operation_id,post_key,request_id,company_id;
    DatabaseReference lendingRef,userRef,financialInstitutionsRef, userRealRef;
    int day,month,year,payment_year;
    DecimalFormat decimalFormat;
    Button btnContinue;
    RelativeLayout rootLayout;
    LinearLayout conditionsLayout;
    ImageView btnOpenClose;
    TextView txtInvoiceAmount,txtAmountToFinance,txtInvoiceExpirationDate,txtDaysToFinance,txtDiscount,txtFee,txtInterest,txtIgv,txtRuc,txtSocialReason,txtFactoringAmount;
    long diff,expiration_days_ago;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_factoring_bills_and_details);

        operation_id = getIntent().getExtras().getString("operation_id");
        post_key = getIntent().getExtras().getString("post_key");
        company_id = getIntent().getExtras().getString("company_id");
        request_id = getIntent().getExtras().getString("request_id");
        lendingRef = FirebaseDatabase.getInstance().getReference().child("Factoring");
        userRef = FirebaseDatabase.getInstance().getReference().child("My Companies");
        userRealRef = FirebaseDatabase.getInstance().getReference().child("Users");
        financialInstitutionsRef = FirebaseDatabase.getInstance().getReference().child("Financial Institutions");

        decimalFormat = new DecimalFormat("0.00");


        btnContinue = findViewById(R.id.btnContinue);
        conditionsLayout = findViewById(R.id.conditionsLayout);
        btnOpenClose = findViewById(R.id.btnOpenClose);

        txtInvoiceAmount = findViewById(R.id.txtInvoiceAmount);
        txtAmountToFinance = findViewById(R.id.txtAmountToFinance);
        txtInvoiceExpirationDate = findViewById(R.id.txtInvoiceExpirationDate);
        txtDaysToFinance = findViewById(R.id.txtDaysToFinance);
        txtDiscount = findViewById(R.id.txtDiscount);
        txtFee = findViewById(R.id.txtFee);
        txtInterest = findViewById(R.id.txtInterest);
        txtIgv = findViewById(R.id.txtIgv);
        txtRuc = findViewById(R.id.txtRuc);
        txtSocialReason = findViewById(R.id.txtSocialReason);
        txtFactoringAmount = findViewById(R.id.txtFactoringAmount);


        Date date= new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        day = cal.get(Calendar.DAY_OF_MONTH);
        month = cal.get(Calendar.MONTH)+1;
        year = cal.get(Calendar.YEAR);

        lendingRef.child(operation_id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String customer_id = dataSnapshot.child("customer_id").getValue().toString();
                final String invoice_amount_st = dataSnapshot.child("invoice_amount").getValue().toString();
                final String invoice_currency = dataSnapshot.child("invoice_currency").getValue().toString();

                final String invoice_expiration_day = dataSnapshot.child("invoice_expiration_day").getValue().toString();
                final String invoice_expiration_month = dataSnapshot.child("invoice_expiration_month").getValue().toString();
                final String invoice_expiration_year = dataSnapshot.child("invoice_expiration_year").getValue().toString();

                String factoring_fee_amount = dataSnapshot.child("factoring_fee_amount").getValue().toString();

                final double tea = dataSnapshot.child("factoring_tea").getValue(Double.class)/100;
                final double warranty_rate = dataSnapshot.child("factoring_warranty_factor_amount").getValue(Double.class)/100;
                final double factoring_max_rate = dataSnapshot.child("factoring_max_rate").getValue(Double.class)/100;
                final double product_rate_fee = Double.parseDouble(factoring_fee_amount)/100;

                final double invoice_amount = Double.parseDouble(invoice_amount_st);

                final String factoring_customer_ruc = dataSnapshot.child("factoring_customer_ruc").getValue().toString();

                lendingRef.child(operation_id).child("Cash Outlay").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        int cash_outlay_day = dataSnapshot.child("cash_outlay_day").getValue(Integer.class);
                        int cash_outlay_month = dataSnapshot.child("cash_outlay_month").getValue(Integer.class);
                        int cash_outlay_year = dataSnapshot.child("cash_outlay_year").getValue(Integer.class);

                        SimpleDateFormat myFormat = new SimpleDateFormat("dd MM yyyy");
                        String inputString1 = cash_outlay_day+" "+cash_outlay_month+" "+cash_outlay_year;
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
}
