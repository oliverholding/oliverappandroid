package com.oalonedeveloper.oliver.oliverappandroidapp.FinancialManagement.LendingProduct;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;

import in.galaxyofandroid.spinerdialog.OnSpinerItemClick;
import in.galaxyofandroid.spinerdialog.SpinnerDialog;

public class LoanSimulationActivity extends AppCompatActivity {

    EditText edtAmount;
    RadioButton rdSoles,rdDollars;
    Button btnMonths,btnGrace,btnContinue;
    TextView txtQuoteAmount;
    DecimalFormat decimalFormat,decimalFormat2;
    String currentUid,product_key,institution_key;
    DatabaseReference expressLoanRef,financialInstitutionsRef;
    ArrayList<String> quotes =new ArrayList<>();
    SpinnerDialog spinnerQuotes;
    ArrayList<String> gracesPeriod =new ArrayList<>();
    SpinnerDialog spinnerGrace;
    FirebaseAuth mAuth;
    RelativeLayout rootLayout;
    LinearLayout btnRequestLoan;
    double product_tea,product_min_capital;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_simulation);

        edtAmount = findViewById(R.id.edtAmount);
        rdSoles = findViewById(R.id.rdSoles);
        rdDollars = findViewById(R.id.rdDollars);
        btnMonths = findViewById(R.id.btnMonths);
        btnGrace = findViewById(R.id.btnGrace);
        txtQuoteAmount = findViewById(R.id.txtQuoteAmount);
        btnContinue = findViewById(R.id.btnContinue);
        btnRequestLoan = findViewById(R.id.btnRequestLoan);
        rootLayout = findViewById(R.id.rootLayout);

        mAuth = FirebaseAuth.getInstance();
        currentUid = mAuth.getCurrentUser().getUid();

        product_key = getIntent().getExtras().getString("product_key");
        institution_key = getIntent().getExtras().getString("institution_key");

        expressLoanRef = FirebaseDatabase.getInstance().getReference().child("Users").child(currentUid).child(institution_key).child(product_key).child("Simulation");
        financialInstitutionsRef = FirebaseDatabase.getInstance().getReference().child("Financial Institutions");

        decimalFormat = new DecimalFormat("0.00");
        decimalFormat2 = new DecimalFormat("0,000.00");
        decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
        decimalFormat2.setRoundingMode(RoundingMode.HALF_UP);

        quotes.add("1");quotes.add("2");quotes.add("3");quotes.add("4");quotes.add("5");quotes.add("6");quotes.add("7");quotes.add("8");quotes.add("9");quotes.add("10");
        quotes.add("11");quotes.add("12");quotes.add("13");quotes.add("14");quotes.add("15");quotes.add("16");quotes.add("17");quotes.add("18");quotes.add("19");quotes.add("20");
        quotes.add("21");quotes.add("22");quotes.add("23");quotes.add("24");quotes.add("25");quotes.add("26");quotes.add("27");quotes.add("28");quotes.add("29");quotes.add("30");
        quotes.add("31");quotes.add("32");quotes.add("33");quotes.add("34");quotes.add("35");quotes.add("36");quotes.add("37");quotes.add("38");quotes.add("39");quotes.add("40");
        quotes.add("41");quotes.add("42");quotes.add("43");quotes.add("44");quotes.add("45");quotes.add("46");quotes.add("47");quotes.add("48");

        gracesPeriod.add("0");gracesPeriod.add("1");gracesPeriod.add("2");

        financialInstitutionsRef.child(institution_key).child("Products").child(product_key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                product_tea = dataSnapshot.child("product_tea").getValue(Double.class);
                product_min_capital = dataSnapshot.child("product_min_capital").getValue(Double.class);

                edtAmount.setText(product_min_capital+"");
                rdSoles.setChecked(true);
                btnMonths.setText("12");
                btnGrace.setText("0");
                simulateQuote();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        edtAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (TextUtils.isEmpty(edtAmount.getText().toString())) {
                    txtQuoteAmount.setText("0.00");
                } else if (!TextUtils.isEmpty(edtAmount.getText().toString())&&!TextUtils.isEmpty(btnMonths.getText().toString())&&!TextUtils.isEmpty(btnGrace.getText().toString())){
                    simulateQuote();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        btnMonths.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spinnerQuotes.showSpinerDialog();
            }
        });

        spinnerQuotes = new SpinnerDialog(LoanSimulationActivity.this,quotes,"Selecciona el número de cuotas");
        spinnerQuotes.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String item, int i) {

                btnMonths.setText(item);
                simulateQuote();

            }
        });

        btnGrace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spinnerGrace.showSpinerDialog();
            }
        });

        spinnerGrace = new SpinnerDialog(LoanSimulationActivity.this,gracesPeriod,"Selecciona el período de gracia");
        spinnerGrace.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String item, int i) {

                btnGrace.setText(item);
                simulateQuote();

            }
        });

        rdSoles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                simulateQuote();
            }
        });
        rdDollars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                simulateQuote();
            }
        });

        btnRequestLoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoanSimulationActivity.this, LoanRequestActivity.class);
                intent.putExtra("product_key",product_key);
                intent.putExtra("institution_key",institution_key);
                startActivity(intent);
                finish();
            }
        });

    }

    private void simulateQuote() {
        double amount = Double.parseDouble(edtAmount.getText().toString());
        double months = Double.parseDouble(btnMonths.getText().toString());
        double tea_decimal = product_tea/100;
        double rate = Math.pow((1+tea_decimal),(1/12.00))-1;

        double factor_top = Math.pow((1+rate),months)*rate;
        double factor_up = Math.pow((1+rate),months)-1;
        double quote = (factor_top/factor_up)*amount;
        String quote_st = decimalFormat.format(quote);

        expressLoanRef.child("requested_amount").setValue(edtAmount.getText().toString());
        if (rdSoles.isChecked()) {
            expressLoanRef.child("requested_currency").setValue("PEN");
            txtQuoteAmount.setText("S/ "+quote_st);
        } else if (rdDollars.isChecked()) {
            expressLoanRef.child("requested_currency").setValue("USD");
            txtQuoteAmount.setText("$ "+quote_st);
        }

        expressLoanRef.child("requested_month").setValue(btnMonths.getText().toString());
        expressLoanRef.child("requested_grace").setValue(btnGrace.getText().toString());
        expressLoanRef.child("expected_quote").setValue(quote_st);

    }
}
