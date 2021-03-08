package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.FinancialManagement.CashAndBanks;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import pl.droidsonroids.gif.GifImageView;

public class CashAndBanksMonthlyActivity extends AppCompatActivity {

    TextView txtCashSales1,txtCreditSales1,txtFinancialIncomes1,txtOtherIncomes1,txtTotalIncomes1,txtStartCash1,txtWorkerPayment1,txtPurchases1,txtIgv1,txtFinancialOutcomes,txtOtherOutcomes,txtTotalOutcomes1,txtFinalCash1,
            txtCashSales2,txtCreditSales2,txtFinancialIncomes2,txtOtherIncomes2,txtTotalIncomes2,txtWorkerPayment2,txtPurchases2,txtIgv2,txtFinancialOutcomes2,txtOtherOutcomes2,txtTotalOutcomes2,txtFinalCash2,txtStartCash2,
            txtCashSales3,txtCreditSales3,txtFinancialIncomes3,txtOtherIncomes3,txtTotalIncomes3,txtCashSales4,txtCreditSales4,txtFinancialIncomes4,txtOtherIncomes4,txtTotalIncomes4,txtCashSales5,txtCreditSales5,txtFinancialIncomes5,txtOtherIncomes5,txtTotalIncomes5,
            txtCashSales6,txtCreditSales6,txtFinancialIncomes6,txtOtherIncomes6,txtTotalIncomes6,txtCashSales7,txtCreditSales7,txtFinancialIncomes7,txtOtherIncomes7,txtTotalIncomes7,txtCashSales8,txtCreditSales8,txtFinancialIncomes8,txtOtherIncomes8,txtTotalIncomes8,
            txtCashSales9,txtCreditSales9,txtFinancialIncomes9,txtOtherIncomes9,txtTotalIncomes9,txtCashSales10,txtCreditSales10,txtFinancialIncomes10,txtOtherIncomes10,txtTotalIncomes10,txtCashSales11,txtCreditSales11,txtFinancialIncomes11,txtOtherIncomes11,txtTotalIncomes11,
            txtCashSales12,txtCreditSales12,txtFinancialIncomes12,txtOtherIncomes12,txtTotalIncomes12,txtWorkerPayment3,txtPurchases3,txtIgv3,txtFinancialOutcomes3,txtOtherOutcomes3,txtTotalOutcomes3,txtWorkerPayment4,txtPurchases4,txtIgv4,txtFinancialOutcomes4,txtOtherOutcomes4,txtTotalOutcomes4,
            txtWorkerPayment5,txtPurchases5,txtIgv5,txtFinancialOutcomes5,txtOtherOutcomes5,txtTotalOutcomes5,txtWorkerPayment6,txtPurchases6,txtIgv6,txtFinancialOutcomes6,txtOtherOutcomes6,txtTotalOutcomes6,txtWorkerPayment7,txtPurchases7,txtIgv7,txtFinancialOutcomes7,txtOtherOutcomes7,txtTotalOutcomes7,
            txtWorkerPayment8,txtPurchases8,txtIgv8,txtFinancialOutcomes8,txtOtherOutcomes8,txtTotalOutcomes8,txtWorkerPayment9,txtPurchases9,txtIgv9,txtFinancialOutcomes9,txtOtherOutcomes9,txtTotalOutcomes9,txtWorkerPayment10,txtPurchases10,txtIgv10,txtFinancialOutcomes10,txtOtherOutcomes10,txtTotalOutcomes10,
            txtWorkerPayment11,txtPurchases11,txtIgv11,txtFinancialOutcomes11,txtOtherOutcomes11,txtTotalOutcomes11,txtWorkerPayment12,txtPurchases12,txtIgv12,txtFinancialOutcomes12,txtOtherOutcomes12,txtTotalOutcomes12,txtStartCash3,txtStartCash4,txtStartCash5,txtStartCash6,
            txtStartCash7,txtStartCash8,txtStartCash9,txtStartCash10,txtStartCash11,txtStartCash12,txtFinalCash3,txtFinalCash4,txtFinalCash5,txtFinalCash6,txtFinalCash7,txtFinalCash8,txtFinalCash9,txtFinalCash10,txtFinalCash11,txtFinalCash12,txtSituation1,txtSituation2,txtSituation3,txtSituation4,txtSituation5,
            txtSituation6,txtSituation7,txtSituation8,txtSituation9,txtSituation10,txtSituation11,txtSituation12;
    int day,month,year,last_year,before_last_year;
    DatabaseReference companyRef;
    DecimalFormat decimalFormat;
    String post_key,cash_flow_1,cash_flow_0,cash_incomes_1_st,credit_incomes_1_st,purchase_total_1_st,igv_taxes_1_st,cash_incomes_2_st,credit_incomes_2_st,purchase_total_2_st,igv_taxes_2_st,cash_incomes_3_st,cash_incomes_4_st,cash_incomes_5_st,cash_incomes_6_st,
            cash_incomes_7_st,cash_incomes_8_st,cash_incomes_9_st,cash_incomes_10_st,cash_incomes_11_st,cash_incomes_12_st,credit_incomes_3_st,credit_incomes_4_st,credit_incomes_5_st,credit_incomes_6_st,credit_incomes_7_st,credit_incomes_8_st,credit_incomes_9_st,
            credit_incomes_10_st,credit_incomes_11_st,credit_incomes_12_st,purchase_total_3_st,purchase_total_4_st,purchase_total_5_st,purchase_total_6_st,purchase_total_7_st,purchase_total_8_st,purchase_total_9_st,purchase_total_10_st,purchase_total_11_st,
            purchase_total_12_st,igv_taxes_3_st,igv_taxes_4_st,igv_taxes_5_st,igv_taxes_6_st,igv_taxes_7_st,igv_taxes_8_st,igv_taxes_9_st,igv_taxes_10_st,igv_taxes_11_st,igv_taxes_12_st;
    double cash_flow_1_db,cash_flow_0_db,cash_incomes_1,credit_incomes_1,financial_incomes_1,other_incomes_1,cash_incomes_0,credit_incomes_0,total_salary_1,total_salary_0,purchase_total_1,total_incomes_1,igv_taxes_1,financial_outcomes_2,other_outcomes_1,total_incomes_0,
            total_outcomes_1,purchase_total_0,total_outcomes_0,igv_taxes_0,credit_incomes_2,cash_incomes_2,financial_incomes_2,other_incomes_2,financial_outcomes_1,total_salary_2,purchase_total_2,total_incomes_2,igv_taxes_2,other_outcomes_2,total_outcomes_2,cash_flow_2_month,cash_flow_1_month,
            cash_incomes_3,cash_incomes_4,cash_incomes_5,cash_incomes_6,cash_incomes_7,cash_incomes_8,cash_incomes_9,cash_incomes_10,cash_incomes_11,cash_incomes_12,credit_incomes_3,credit_incomes_4,credit_incomes_5,credit_incomes_6,credit_incomes_7,credit_incomes_8,
            credit_incomes_9,credit_incomes_10,credit_incomes_11,credit_incomes_12,financial_incomes_3,financial_incomes_4,financial_incomes_5,financial_incomes_6,financial_incomes_7,financial_incomes_8,financial_incomes_9,financial_incomes_10,financial_incomes_11,
            financial_incomes_12,other_incomes_3,other_incomes_4,other_incomes_5,other_incomes_6,other_incomes_7,other_incomes_8,other_incomes_9,other_incomes_10,other_incomes_11,other_incomes_12,total_salary_3,total_salary_4,total_salary_5,total_salary_6,total_salary_7,
            total_salary_8,total_salary_9,total_salary_10,total_salary_11,total_salary_12,purchase_total_3,purchase_total_4,purchase_total_5,purchase_total_6,purchase_total_7,purchase_total_8,purchase_total_9,purchase_total_10,purchase_total_11,purchase_total_12,total_incomes_3,total_incomes_4,
            total_incomes_5,total_incomes_6,total_incomes_7,total_incomes_8,total_incomes_9,total_incomes_10,total_incomes_11,total_incomes_12,igv_taxes_3,igv_taxes_4,igv_taxes_5,igv_taxes_6,igv_taxes_7,igv_taxes_8,igv_taxes_9,igv_taxes_10,igv_taxes_11,igv_taxes_12,total_outcomes_3,total_outcomes_4,
            total_outcomes_5,total_outcomes_6,total_outcomes_7,total_outcomes_8,total_outcomes_9,total_outcomes_10,total_outcomes_11,total_outcomes_12,financial_outcomes_3,financial_outcomes_4,financial_outcomes_5,financial_outcomes_6,financial_outcomes_7,financial_outcomes_8,
            financial_outcomes_9,financial_outcomes_10,financial_outcomes_11,financial_outcomes_12,other_outcomes_3,other_outcomes_4,other_outcomes_5,other_outcomes_6,other_outcomes_7,other_outcomes_8,other_outcomes_9,other_outcomes_10,other_outcomes_11,other_outcomes_12,
            cash_flow_3_month,cash_flow_4_month,cash_flow_5_month,cash_flow_6_month,cash_flow_7_month,cash_flow_8_month,cash_flow_9_month,cash_flow_10_month,cash_flow_11_month,cash_flow_12_month;

    GifImageView imgSituation1,imgSituation2,imgSituation3,imgSituation4,imgSituation5,imgSituation6,imgSituation7,imgSituation8,imgSituation9,imgSituation10,imgSituation11,imgSituation12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash_and_banks_monthly);

        txtCashSales1 = findViewById(R.id.txtCashSales1);
        txtCreditSales1 = findViewById(R.id.txtCreditSales1);
        txtFinancialIncomes1 = findViewById(R.id.txtFinancialIncomes1);
        txtOtherIncomes1 = findViewById(R.id.txtOtherIncomes1);
        txtTotalIncomes1 = findViewById(R.id.txtTotalIncomes1);
        txtCashSales2 = findViewById(R.id.txtCashSales2);
        txtCreditSales2 = findViewById(R.id.txtCreditSales2);
        txtFinancialIncomes2 = findViewById(R.id.txtFinancialIncomes2);
        txtOtherIncomes2 = findViewById(R.id.txtOtherIncomes2);
        txtTotalIncomes2 = findViewById(R.id.txtTotalIncomes2);
        txtCashSales3 = findViewById(R.id.txtCashSales3);
        txtCreditSales3 = findViewById(R.id.txtCreditSales3);
        txtFinancialIncomes3 = findViewById(R.id.txtFinancialIncomes3);
        txtOtherIncomes3 = findViewById(R.id.txtOtherIncomes3);
        txtTotalIncomes3 = findViewById(R.id.txtTotalIncomes3);
        txtCashSales4 = findViewById(R.id.txtCashSales4);
        txtCreditSales4 = findViewById(R.id.txtCreditSales4);
        txtFinancialIncomes4 = findViewById(R.id.txtFinancialIncomes4);
        txtOtherIncomes4 = findViewById(R.id.txtOtherIncomes4);
        txtTotalIncomes4 = findViewById(R.id.txtTotalIncomes4);
        txtCashSales5 = findViewById(R.id.txtCashSales5);
        txtCreditSales5 = findViewById(R.id.txtCreditSales5);
        txtFinancialIncomes5 = findViewById(R.id.txtFinancialIncomes5);
        txtOtherIncomes5 = findViewById(R.id.txtOtherIncomes5);
        txtTotalIncomes5 = findViewById(R.id.txtTotalIncomes5);
        txtCashSales6 = findViewById(R.id.txtCashSales6);
        txtCreditSales6 = findViewById(R.id.txtCreditSales6);
        txtFinancialIncomes6 = findViewById(R.id.txtFinancialIncomes6);
        txtOtherIncomes6 = findViewById(R.id.txtOtherIncomes6);
        txtTotalIncomes6 = findViewById(R.id.txtTotalIncomes6);
        txtCashSales7 = findViewById(R.id.txtCashSales7);
        txtCreditSales7 = findViewById(R.id.txtCreditSales7);
        txtFinancialIncomes7 = findViewById(R.id.txtFinancialIncomes7);
        txtOtherIncomes7 = findViewById(R.id.txtOtherIncomes7);
        txtTotalIncomes7 = findViewById(R.id.txtTotalIncomes7);
        txtCashSales8 = findViewById(R.id.txtCashSales8);
        txtCreditSales8 = findViewById(R.id.txtCreditSales8);
        txtFinancialIncomes8 = findViewById(R.id.txtFinancialIncomes8);
        txtOtherIncomes8 = findViewById(R.id.txtOtherIncomes8);
        txtTotalIncomes8 = findViewById(R.id.txtTotalIncomes8);
        txtCashSales9 = findViewById(R.id.txtCashSales9);
        txtCreditSales9 = findViewById(R.id.txtCreditSales9);
        txtFinancialIncomes9 = findViewById(R.id.txtFinancialIncomes9);
        txtOtherIncomes9 = findViewById(R.id.txtOtherIncomes9);
        txtTotalIncomes9 = findViewById(R.id.txtTotalIncomes9);
        txtCashSales10 = findViewById(R.id.txtCashSales10);
        txtCreditSales10 = findViewById(R.id.txtCreditSales10);
        txtFinancialIncomes10 = findViewById(R.id.txtFinancialIncomes10);
        txtOtherIncomes10 = findViewById(R.id.txtOtherIncomes10);
        txtTotalIncomes10 = findViewById(R.id.txtTotalIncomes10);
        txtCashSales11 = findViewById(R.id.txtCashSales11);
        txtCreditSales11 = findViewById(R.id.txtCreditSales11);
        txtFinancialIncomes11 = findViewById(R.id.txtFinancialIncomes11);
        txtOtherIncomes11 = findViewById(R.id.txtOtherIncomes11);
        txtTotalIncomes11 = findViewById(R.id.txtTotalIncomes11);
        txtCashSales12 = findViewById(R.id.txtCashSales12);
        txtCreditSales12 = findViewById(R.id.txtCreditSales12);
        txtFinancialIncomes12 = findViewById(R.id.txtFinancialIncomes12);
        txtOtherIncomes12 = findViewById(R.id.txtOtherIncomes12);
        txtTotalIncomes12 = findViewById(R.id.txtTotalIncomes12);

        txtWorkerPayment1 = findViewById(R.id.txtWorkerPayment1);
        txtPurchases1 = findViewById(R.id.txtPurchases1);
        txtIgv1 = findViewById(R.id.txtIgv1);
        txtFinancialOutcomes = findViewById(R.id.txtFinancialOutcomes);
        txtOtherOutcomes = findViewById(R.id.txtOtherOutcomes);
        txtTotalOutcomes1 = findViewById(R.id.txtTotalOutcomes1);
        txtWorkerPayment2 = findViewById(R.id.txtWorkerPayment2);
        txtPurchases2 = findViewById(R.id.txtPurchases2);
        txtIgv2 = findViewById(R.id.txtIgv2);
        txtFinancialOutcomes2 = findViewById(R.id.txtFinancialOutcomes2);
        txtOtherOutcomes2 = findViewById(R.id.txtOtherOutcomes2);
        txtTotalOutcomes2 = findViewById(R.id.txtTotalOutcomes2);
        txtWorkerPayment3 = findViewById(R.id.txtWorkerPayment3);
        txtPurchases3 = findViewById(R.id.txtPurchases3);
        txtIgv3 = findViewById(R.id.txtIgv3);
        txtFinancialOutcomes3 = findViewById(R.id.txtFinancialOutcomes3);
        txtOtherOutcomes3 = findViewById(R.id.txtOtherOutcomes3);
        txtTotalOutcomes3 = findViewById(R.id.txtTotalOutcomes3);
        txtWorkerPayment4 = findViewById(R.id.txtWorkerPayment4);
        txtPurchases4 = findViewById(R.id.txtPurchases4);
        txtIgv4 = findViewById(R.id.txtIgv4);
        txtFinancialOutcomes4 = findViewById(R.id.txtFinancialOutcomes4);
        txtOtherOutcomes4 = findViewById(R.id.txtOtherOutcomes4);
        txtTotalOutcomes4 = findViewById(R.id.txtTotalOutcomes4);
        txtWorkerPayment5 = findViewById(R.id.txtWorkerPayment5);
        txtPurchases5 = findViewById(R.id.txtPurchases5);
        txtIgv5 = findViewById(R.id.txtIgv5);
        txtFinancialOutcomes5 = findViewById(R.id.txtFinancialOutcomes5);
        txtOtherOutcomes5 = findViewById(R.id.txtOtherOutcomes5);
        txtTotalOutcomes5 = findViewById(R.id.txtTotalOutcomes5);
        txtWorkerPayment6 = findViewById(R.id.txtWorkerPayment6);
        txtPurchases6 = findViewById(R.id.txtPurchases6);
        txtIgv6 = findViewById(R.id.txtIgv6);
        txtFinancialOutcomes6 = findViewById(R.id.txtFinancialOutcomes6);
        txtOtherOutcomes6 = findViewById(R.id.txtOtherOutcomes6);
        txtTotalOutcomes6 = findViewById(R.id.txtTotalOutcomes6);
        txtWorkerPayment7 = findViewById(R.id.txtWorkerPayment7);
        txtPurchases7 = findViewById(R.id.txtPurchases7);
        txtIgv7 = findViewById(R.id.txtIgv7);
        txtFinancialOutcomes7 = findViewById(R.id.txtFinancialOutcomes7);
        txtOtherOutcomes7 = findViewById(R.id.txtOtherOutcomes7);
        txtTotalOutcomes7 = findViewById(R.id.txtTotalOutcomes7);
        txtWorkerPayment8 = findViewById(R.id.txtWorkerPayment8);
        txtPurchases8 = findViewById(R.id.txtPurchases8);
        txtIgv8 = findViewById(R.id.txtIgv8);
        txtFinancialOutcomes8 = findViewById(R.id.txtFinancialOutcomes8);
        txtOtherOutcomes8 = findViewById(R.id.txtOtherOutcomes8);
        txtTotalOutcomes8 = findViewById(R.id.txtTotalOutcomes8);
        txtWorkerPayment9 = findViewById(R.id.txtWorkerPayment9);
        txtPurchases9 = findViewById(R.id.txtPurchases9);
        txtIgv9 = findViewById(R.id.txtIgv9);
        txtFinancialOutcomes9 = findViewById(R.id.txtFinancialOutcomes9);
        txtOtherOutcomes9 = findViewById(R.id.txtOtherOutcomes9);
        txtTotalOutcomes9 = findViewById(R.id.txtTotalOutcomes9);
        txtWorkerPayment10 = findViewById(R.id.txtWorkerPayment10);
        txtPurchases10 = findViewById(R.id.txtPurchases10);
        txtIgv10 = findViewById(R.id.txtIgv10);
        txtFinancialOutcomes10 = findViewById(R.id.txtFinancialOutcomes10);
        txtOtherOutcomes10 = findViewById(R.id.txtOtherOutcomes10);
        txtTotalOutcomes10 = findViewById(R.id.txtTotalOutcomes10);
        txtWorkerPayment11 = findViewById(R.id.txtWorkerPayment11);
        txtPurchases11 = findViewById(R.id.txtPurchases11);
        txtIgv11 = findViewById(R.id.txtIgv11);
        txtFinancialOutcomes11 = findViewById(R.id.txtFinancialOutcomes11);
        txtOtherOutcomes11 = findViewById(R.id.txtOtherOutcomes11);
        txtTotalOutcomes11 = findViewById(R.id.txtTotalOutcomes11);
        txtWorkerPayment12 = findViewById(R.id.txtWorkerPayment12);
        txtPurchases12 = findViewById(R.id.txtPurchases12);
        txtIgv12 = findViewById(R.id.txtIgv12);
        txtFinancialOutcomes12 = findViewById(R.id.txtFinancialOutcomes12);
        txtOtherOutcomes12 = findViewById(R.id.txtOtherOutcomes12);
        txtTotalOutcomes12 = findViewById(R.id.txtTotalOutcomes12);

        txtStartCash1 = findViewById(R.id.txtStartCash1);
        txtFinalCash1 = findViewById(R.id.txtFinalCash1);
        txtStartCash2 = findViewById(R.id.txtStartCash2);
        txtFinalCash2 = findViewById(R.id.txtFinalCash2);
        txtStartCash3 = findViewById(R.id.txtStartCash3);
        txtFinalCash3 = findViewById(R.id.txtFinalCash3);
        txtStartCash4 = findViewById(R.id.txtStartCash4);
        txtFinalCash4 = findViewById(R.id.txtFinalCash4);
        txtStartCash5 = findViewById(R.id.txtStartCash5);
        txtFinalCash5 = findViewById(R.id.txtFinalCash5);
        txtStartCash6 = findViewById(R.id.txtStartCash6);
        txtFinalCash6 = findViewById(R.id.txtFinalCash6);
        txtStartCash7 = findViewById(R.id.txtStartCash7);
        txtFinalCash7 = findViewById(R.id.txtFinalCash7);
        txtStartCash8 = findViewById(R.id.txtStartCash8);
        txtFinalCash8 = findViewById(R.id.txtFinalCash8);
        txtStartCash9 = findViewById(R.id.txtStartCash9);
        txtFinalCash9 = findViewById(R.id.txtFinalCash9);
        txtStartCash10 = findViewById(R.id.txtStartCash10);
        txtFinalCash10 = findViewById(R.id.txtFinalCash10);
        txtStartCash11 = findViewById(R.id.txtStartCash11);
        txtFinalCash11 = findViewById(R.id.txtFinalCash11);
        txtStartCash12 = findViewById(R.id.txtStartCash12);
        txtFinalCash12 = findViewById(R.id.txtFinalCash12);

        imgSituation1 = findViewById(R.id.imgSituation1);
        imgSituation2 = findViewById(R.id.imgSituation2);
        imgSituation3 = findViewById(R.id.imgSituation3);
        imgSituation4 = findViewById(R.id.imgSituation4);
        imgSituation5 = findViewById(R.id.imgSituation5);
        imgSituation6 = findViewById(R.id.imgSituation6);
        imgSituation7 = findViewById(R.id.imgSituation7);
        imgSituation8 = findViewById(R.id.imgSituation8);
        imgSituation9 = findViewById(R.id.imgSituation9);
        imgSituation10 = findViewById(R.id.imgSituation10);
        imgSituation11 = findViewById(R.id.imgSituation11);
        imgSituation12 = findViewById(R.id.imgSituation12);

        txtSituation1 = findViewById(R.id.txtSituation1);
        txtSituation2 = findViewById(R.id.txtSituation2);
        txtSituation3 = findViewById(R.id.txtSituation3);
        txtSituation4 = findViewById(R.id.txtSituation4);
        txtSituation5 = findViewById(R.id.txtSituation5);
        txtSituation6 = findViewById(R.id.txtSituation6);
        txtSituation7 = findViewById(R.id.txtSituation7);
        txtSituation8 = findViewById(R.id.txtSituation8);
        txtSituation9 = findViewById(R.id.txtSituation9);
        txtSituation10 = findViewById(R.id.txtSituation10);
        txtSituation11 = findViewById(R.id.txtSituation11);
        txtSituation12 = findViewById(R.id.txtSituation12);

        post_key = getIntent().getExtras().getString("post_key");
        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");

        decimalFormat = new DecimalFormat("0.00");

        Date date= new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        day = cal.get(Calendar.DAY_OF_MONTH);
        month = cal.get(Calendar.MONTH)+1;
        year = cal.get(Calendar.YEAR);
        last_year = year-1;
        before_last_year = last_year-1;

        companyRef.child(post_key).child("Cash Flow").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild("cash_flow" + last_year)) {
                    cash_flow_0 = dataSnapshot.child("cash_flow" + last_year).getValue().toString();
                    cash_flow_0_db = Double.parseDouble(cash_flow_0);

                    txtStartCash1.setText(cash_flow_0);

                } else {
                    showSetInitialCashFlow();
                    cash_flow_0_db = 0.00;
                    txtStartCash1.setText("0.00");
                }

                calculateMonth1();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




    }

    private void showSetInitialCashFlow() {
        final AlertDialog dialog = new AlertDialog.Builder(this).create();

        LayoutInflater inflater = LayoutInflater.from(this);
        View finance_method = inflater.inflate(R.layout.set_initial_cash_flow_dialog,null);

        final EditText edtInitialCashFlow;
        Button btnRegister;
        final LinearLayout rootLayout;

        edtInitialCashFlow = finance_method.findViewById(R.id.edtInitialCashFlow);
        btnRegister = finance_method.findViewById(R.id.btnRegister);
        rootLayout = finance_method.findViewById(R.id.rootLayout);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(edtInitialCashFlow.getText().toString())) {
                    Snackbar.make(rootLayout, "Debes ingresar la caja inicial", Snackbar.LENGTH_SHORT).show();
                } else {
                    companyRef.child(post_key).child("Cash Flow").child("cash_flow" + last_year).setValue(edtInitialCashFlow.getText().toString());
                    cash_flow_0_db = Double.parseDouble(edtInitialCashFlow.getText().toString());
                    cash_flow_0 = decimalFormat.format(cash_flow_0_db);
                    txtStartCash1.setText(cash_flow_0);
                    calculateMonth1();
                    dialog.dismiss();

                }
            }
        });


        dialog.setView(finance_method);
        dialog.show();
    }

    private void calculateMonth12() {
        companyRef.child(post_key).child("My Bills").orderByChild("issuing_year").equalTo(year).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                cash_incomes_12 = 0;
                credit_incomes_12 = 0;
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String state = ds.child("state").getValue().toString();
                    int issuing_day = ds.child("issuing_day").getValue(Integer.class);
                    int issuing_month = ds.child("issuing_month").getValue(Integer.class);
                    int issuing_year = ds.child("issuing_year").getValue(Integer.class);
                    String expiration_day = ds.child("expiration_day").getValue().toString();
                    String expiration_month = ds.child("expiration_month").getValue().toString();
                    String expiration_year = ds.child("expiration_year").getValue().toString();

                    String issuing_date = issuing_day+"/"+issuing_month+"/"+issuing_year;
                    String expiration_date = expiration_day+"/"+expiration_month+"/"+expiration_year;


                    if (state.equals("paid") && issuing_date.equals(expiration_date)) {
                        if (expiration_month.equals("12")) {
                            Map<String, Object> map = (Map<String, Object>) ds.getValue();
                            Object bill_amount = map.get("bill_amount");
                            double bill_amount_db = Double.parseDouble(String.valueOf(bill_amount));
                            cash_incomes_12 += bill_amount_db;
                            cash_incomes_12_st = decimalFormat.format(cash_incomes_12);
                            txtCashSales12.setText(cash_incomes_12_st);
                        }


                    }
                    if (state.equals("paid") && !issuing_date.equals(expiration_date)) {
                        if (expiration_month.equals("12")) {
                            Map<String, Object> map = (Map<String, Object>) ds.getValue();
                            Object bill_amount = map.get("bill_amount");
                            double bill_amount_db = Double.parseDouble(String.valueOf(bill_amount));
                            credit_incomes_12 += bill_amount_db;
                            credit_incomes_12_st = decimalFormat.format(credit_incomes_12);
                            txtCreditSales12.setText(credit_incomes_12_st);
                        }


                    }

                    double total_incomes_12 = cash_incomes_12+credit_incomes_12+financial_incomes_12+other_incomes_12;
                    String total_incomes_12_st = decimalFormat.format(total_incomes_12);
                    txtTotalIncomes12.setText(total_incomes_12_st);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        companyRef.child(post_key).child("Worker Bills").orderByChild("operation_year").equalTo(year+"").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                total_salary_12 = 0;
                for (DataSnapshot ds : dataSnapshot.getChildren()) {

                    String operation_month = ds.child("operation_month").getValue().toString();

                    if (operation_month.equals("11")) {
                        Map<String, Object> map = (Map<String, Object>) ds.getValue();
                        Object total_payment = map.get("total_payment");
                        double total_payment_db = Double.parseDouble(String.valueOf(total_payment));
                        total_salary_12 += total_payment_db;
                        String total_salary_12_st = decimalFormat.format(total_salary_12);
                        txtWorkerPayment12.setText(total_salary_12_st);
                    }


                }

                companyRef.child(post_key).child("Purchased Orders").orderByChild("operation_year").equalTo(year+"").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        purchase_total_12 = 0;
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            String purchase_payment_state = ds.child("purchase_payment_state").getValue().toString();
                            String expiration_day = ds.child("expiration_day").getValue().toString();
                            String expiration_month = ds.child("expiration_month").getValue().toString();
                            String expiration_year = ds.child("expiration_year").getValue().toString();
                            String operation_day = ds.child("operation_day").getValue().toString();
                            String operation_month = ds.child("operation_month").getValue().toString();
                            String operation_time = ds.child("operation_time").getValue().toString();

                            String issuing_date = operation_day+"/"+operation_month+"/"+operation_time;
                            String expiration_date = expiration_day+"/"+expiration_month+"/"+expiration_year;

                            if (purchase_payment_state.equals("paid")) {

                                if (expiration_month.equals("11")) {
                                    Map<String, Object> map = (Map<String, Object>) ds.getValue();
                                    Object purchase_order_total_amount = map.get("purchase_order_total_amount");
                                    double purchase_order_total_amount_db = Double.parseDouble((String) purchase_order_total_amount);
                                    purchase_total_12 += purchase_order_total_amount_db;
                                    purchase_total_12_st = decimalFormat.format(purchase_total_12);
                                    txtPurchases12.setText(purchase_total_12_st);
                                }

                            }

                        }

                        total_incomes_12 = cash_incomes_12+credit_incomes_12+financial_incomes_12+other_incomes_12;
                        igv_taxes_12 = (total_incomes_12*0.18)-(purchase_total_12*0.18);
                        igv_taxes_12_st = decimalFormat.format(igv_taxes_12);
                        txtIgv12.setText(igv_taxes_12_st);

                        total_outcomes_12 = total_salary_12+purchase_total_12+igv_taxes_12+financial_outcomes_12+other_outcomes_12;
                        String total_outcomes_12_st = decimalFormat.format(total_outcomes_12);
                        txtTotalOutcomes12.setText(total_outcomes_12_st);


                        cash_flow_12_month = total_incomes_12-total_outcomes_12+cash_flow_11_month;
                        String cash_flow_12_st = decimalFormat.format(cash_flow_12_month);
                        txtFinalCash12.setText(cash_flow_12_st);
                        companyRef.child(post_key).child("Cash Flow").child("cash_flow"+year).setValue(cash_flow_12_st);

                        if (cash_flow_12_month < cash_flow_11_month) {
                            imgSituation12.setImageResource(R.drawable.perdida_gif);
                            txtSituation12.setTextColor(Color.RED);
                            txtSituation12.setText("PÉRDIDA");
                        }
                        else if (cash_flow_12_month > cash_flow_11_month) {
                            imgSituation12.setImageResource(R.drawable.ganancia_gif);
                            txtSituation12.setTextColor(Color.GREEN);
                            txtSituation12.setText("GANANCIA");
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

    private void calculateMonth11() {
        companyRef.child(post_key).child("My Bills").orderByChild("issuing_year").equalTo(year).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                cash_incomes_11 = 0;
                credit_incomes_11 = 0;
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String state = ds.child("state").getValue().toString();
                    int issuing_day = ds.child("issuing_day").getValue(Integer.class);
                    int issuing_month = ds.child("issuing_month").getValue(Integer.class);
                    int issuing_year = ds.child("issuing_year").getValue(Integer.class);
                    String expiration_day = ds.child("expiration_day").getValue().toString();
                    String expiration_month = ds.child("expiration_month").getValue().toString();
                    String expiration_year = ds.child("expiration_year").getValue().toString();

                    String issuing_date = issuing_day+"/"+issuing_month+"/"+issuing_year;
                    String expiration_date = expiration_day+"/"+expiration_month+"/"+expiration_year;


                    if (state.equals("paid") && issuing_date.equals(expiration_date)) {
                        if (expiration_month.equals("11")) {
                            Map<String, Object> map = (Map<String, Object>) ds.getValue();
                            Object bill_amount = map.get("bill_amount");
                            double bill_amount_db = Double.parseDouble(String.valueOf(bill_amount));
                            cash_incomes_11 += bill_amount_db;
                            cash_incomes_11_st = decimalFormat.format(cash_incomes_11);
                            txtCashSales11.setText(cash_incomes_11_st);
                        }


                    }
                    if (state.equals("paid") && !issuing_date.equals(expiration_date)) {
                        if (expiration_month.equals("11")) {
                            Map<String, Object> map = (Map<String, Object>) ds.getValue();
                            Object bill_amount = map.get("bill_amount");
                            double bill_amount_db = Double.parseDouble(String.valueOf(bill_amount));
                            credit_incomes_11 += bill_amount_db;
                            credit_incomes_11_st = decimalFormat.format(credit_incomes_11);
                            txtCreditSales11.setText(credit_incomes_11_st);
                        }


                    }

                    double total_incomes_11 = cash_incomes_11+credit_incomes_11+financial_incomes_11+other_incomes_11;
                    String total_incomes_11_st = decimalFormat.format(total_incomes_11);
                    txtTotalIncomes11.setText(total_incomes_11_st);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        companyRef.child(post_key).child("Worker Bills").orderByChild("operation_year").equalTo(year+"").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                total_salary_11 = 0;
                for (DataSnapshot ds : dataSnapshot.getChildren()) {

                    String operation_month = ds.child("operation_month").getValue().toString();

                    if (operation_month.equals("11")) {
                        Map<String, Object> map = (Map<String, Object>) ds.getValue();
                        Object total_payment = map.get("total_payment");
                        double total_payment_db = Double.parseDouble(String.valueOf(total_payment));
                        total_salary_11 += total_payment_db;
                        String total_salary_11_st = decimalFormat.format(total_salary_11);
                        txtWorkerPayment11.setText(total_salary_11_st);
                    }


                }

                companyRef.child(post_key).child("Purchased Orders").orderByChild("operation_year").equalTo(year+"").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        purchase_total_11 = 0;
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            String purchase_payment_state = ds.child("purchase_payment_state").getValue().toString();
                            String expiration_day = ds.child("expiration_day").getValue().toString();
                            String expiration_month = ds.child("expiration_month").getValue().toString();
                            String expiration_year = ds.child("expiration_year").getValue().toString();
                            String operation_day = ds.child("operation_day").getValue().toString();
                            String operation_month = ds.child("operation_month").getValue().toString();
                            String operation_time = ds.child("operation_time").getValue().toString();

                            String issuing_date = operation_day+"/"+operation_month+"/"+operation_time;
                            String expiration_date = expiration_day+"/"+expiration_month+"/"+expiration_year;

                            if (purchase_payment_state.equals("paid")) {

                                if (expiration_month.equals("11")) {
                                    Map<String, Object> map = (Map<String, Object>) ds.getValue();
                                    Object purchase_order_total_amount = map.get("purchase_order_total_amount");
                                    double purchase_order_total_amount_db = Double.parseDouble((String) purchase_order_total_amount);
                                    purchase_total_11 += purchase_order_total_amount_db;
                                    purchase_total_11_st = decimalFormat.format(purchase_total_11);
                                    txtPurchases11.setText(purchase_total_11_st);
                                }

                            }

                        }

                        total_incomes_11 = cash_incomes_11+credit_incomes_11+financial_incomes_11+other_incomes_11;
                        igv_taxes_11 = (total_incomes_11*0.18)-(purchase_total_11*0.18);
                        igv_taxes_11_st = decimalFormat.format(igv_taxes_11);
                        txtIgv11.setText(igv_taxes_11_st);

                        total_outcomes_11 = total_salary_11+purchase_total_11+igv_taxes_11+financial_outcomes_11+other_outcomes_11;
                        String total_outcomes_11_st = decimalFormat.format(total_outcomes_11);
                        txtTotalOutcomes11.setText(total_outcomes_11_st);


                        cash_flow_11_month = total_incomes_11-total_outcomes_11+cash_flow_10_month;
                        String cash_flow_11_st = decimalFormat.format(cash_flow_11_month);
                        txtFinalCash11.setText(cash_flow_11_st);
                        txtStartCash12.setText(cash_flow_11_st);
                        companyRef.child(post_key).child("Cash Flow").child("cash_flow"+year).setValue(cash_flow_11_st);

                        if (cash_flow_11_month < cash_flow_10_month) {
                            imgSituation11.setImageResource(R.drawable.perdida_gif);
                            txtSituation11.setTextColor(Color.RED);
                            txtSituation11.setText("PÉRDIDA");
                        }
                        else if (cash_flow_11_month > cash_flow_10_month) {
                            imgSituation11.setImageResource(R.drawable.ganancia_gif);
                            txtSituation11.setTextColor(Color.GREEN);
                            txtSituation11.setText("GANANCIA");
                        }

                        calculateMonth12();

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

    private void calculateMonth10() {
        companyRef.child(post_key).child("My Bills").orderByChild("issuing_year").equalTo(year).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                cash_incomes_10 = 0;
                credit_incomes_10 = 0;
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String state = ds.child("state").getValue().toString();
                    int issuing_day = ds.child("issuing_day").getValue(Integer.class);
                    int issuing_month = ds.child("issuing_month").getValue(Integer.class);
                    int issuing_year = ds.child("issuing_year").getValue(Integer.class);
                    String expiration_day = ds.child("expiration_day").getValue().toString();
                    String expiration_month = ds.child("expiration_month").getValue().toString();
                    String expiration_year = ds.child("expiration_year").getValue().toString();

                    String issuing_date = issuing_day+"/"+issuing_month+"/"+issuing_year;
                    String expiration_date = expiration_day+"/"+expiration_month+"/"+expiration_year;


                    if (state.equals("paid") && issuing_date.equals(expiration_date)) {
                        if (expiration_month.equals("10")) {
                            Map<String, Object> map = (Map<String, Object>) ds.getValue();
                            Object bill_amount = map.get("bill_amount");
                            double bill_amount_db = Double.parseDouble(String.valueOf(bill_amount));
                            cash_incomes_10 += bill_amount_db;
                            cash_incomes_10_st = decimalFormat.format(cash_incomes_10);
                            txtCashSales10.setText(cash_incomes_10_st);
                        }


                    }
                    if (state.equals("paid") && !issuing_date.equals(expiration_date)) {
                        if (expiration_month.equals("10")) {
                            Map<String, Object> map = (Map<String, Object>) ds.getValue();
                            Object bill_amount = map.get("bill_amount");
                            double bill_amount_db = Double.parseDouble(String.valueOf(bill_amount));
                            credit_incomes_10 += bill_amount_db;
                            credit_incomes_10_st = decimalFormat.format(credit_incomes_10);
                            txtCreditSales10.setText(credit_incomes_10_st);
                        }


                    }

                    double total_incomes_10 = cash_incomes_10+credit_incomes_10+financial_incomes_10+other_incomes_10;
                    String total_incomes_10_st = decimalFormat.format(total_incomes_10);
                    txtTotalIncomes10.setText(total_incomes_10_st);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        companyRef.child(post_key).child("Worker Bills").orderByChild("operation_year").equalTo(year+"").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                total_salary_10 = 0;
                for (DataSnapshot ds : dataSnapshot.getChildren()) {

                    String operation_month = ds.child("operation_month").getValue().toString();

                    if (operation_month.equals("10")) {
                        Map<String, Object> map = (Map<String, Object>) ds.getValue();
                        Object total_payment = map.get("total_payment");
                        double total_payment_db = Double.parseDouble(String.valueOf(total_payment));
                        total_salary_10 += total_payment_db;
                        String total_salary_10_st = decimalFormat.format(total_salary_10);
                        txtWorkerPayment10.setText(total_salary_10_st);
                    }


                }

                companyRef.child(post_key).child("Purchased Orders").orderByChild("operation_year").equalTo(year+"").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        purchase_total_10 = 0;
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            String purchase_payment_state = ds.child("purchase_payment_state").getValue().toString();
                            String expiration_day = ds.child("expiration_day").getValue().toString();
                            String expiration_month = ds.child("expiration_month").getValue().toString();
                            String expiration_year = ds.child("expiration_year").getValue().toString();
                            String operation_day = ds.child("operation_day").getValue().toString();
                            String operation_month = ds.child("operation_month").getValue().toString();
                            String operation_time = ds.child("operation_time").getValue().toString();

                            String issuing_date = operation_day+"/"+operation_month+"/"+operation_time;
                            String expiration_date = expiration_day+"/"+expiration_month+"/"+expiration_year;

                            if (purchase_payment_state.equals("paid")) {

                                if (expiration_month.equals("10")) {
                                    Map<String, Object> map = (Map<String, Object>) ds.getValue();
                                    Object purchase_order_total_amount = map.get("purchase_order_total_amount");
                                    double purchase_order_total_amount_db = Double.parseDouble((String) purchase_order_total_amount);
                                    purchase_total_10 += purchase_order_total_amount_db;
                                    purchase_total_10_st = decimalFormat.format(purchase_total_10);
                                    txtPurchases10.setText(purchase_total_10_st);
                                }

                            }

                        }

                        total_incomes_10 = cash_incomes_10+credit_incomes_10+financial_incomes_10+other_incomes_10;
                        igv_taxes_10 = (total_incomes_10*0.18)-(purchase_total_10*0.18);
                        igv_taxes_10_st = decimalFormat.format(igv_taxes_10);
                        txtIgv10.setText(igv_taxes_10_st);

                        total_outcomes_10 = total_salary_10+purchase_total_10+igv_taxes_10+financial_outcomes_10+other_outcomes_10;
                        String total_outcomes_10_st = decimalFormat.format(total_outcomes_10);
                        txtTotalOutcomes10.setText(total_outcomes_10_st);


                        cash_flow_10_month = total_incomes_10-total_outcomes_10+cash_flow_9_month;
                        String cash_flow_10_st = decimalFormat.format(cash_flow_10_month);
                        txtFinalCash10.setText(cash_flow_10_st);
                        txtStartCash11.setText(cash_flow_10_st);
                        companyRef.child(post_key).child("Cash Flow").child("cash_flow"+year).setValue(cash_flow_10_st);

                        if (cash_flow_10_month < cash_flow_9_month) {
                            imgSituation10.setImageResource(R.drawable.perdida_gif);
                            txtSituation10.setTextColor(Color.RED);
                            txtSituation10.setText("PÉRDIDA");
                        }
                        else if (cash_flow_10_month > cash_flow_9_month) {
                            imgSituation10.setImageResource(R.drawable.ganancia_gif);
                            txtSituation10.setTextColor(Color.GREEN);
                            txtSituation10.setText("GANANCIA");
                        }

                        calculateMonth11();

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

    private void calculateMonth9() {
        companyRef.child(post_key).child("My Bills").orderByChild("issuing_year").equalTo(year).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                cash_incomes_9 = 0;
                credit_incomes_9 = 0;
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String state = ds.child("state").getValue().toString();
                    int issuing_day = ds.child("issuing_day").getValue(Integer.class);
                    int issuing_month = ds.child("issuing_month").getValue(Integer.class);
                    int issuing_year = ds.child("issuing_year").getValue(Integer.class);
                    String expiration_day = ds.child("expiration_day").getValue().toString();
                    String expiration_month = ds.child("expiration_month").getValue().toString();
                    String expiration_year = ds.child("expiration_year").getValue().toString();

                    String issuing_date = issuing_day+"/"+issuing_month+"/"+issuing_year;
                    String expiration_date = expiration_day+"/"+expiration_month+"/"+expiration_year;


                    if (state.equals("paid") && issuing_date.equals(expiration_date)) {
                        if (expiration_month.equals("9")) {
                            Map<String, Object> map = (Map<String, Object>) ds.getValue();
                            Object bill_amount = map.get("bill_amount");
                            double bill_amount_db = Double.parseDouble(String.valueOf(bill_amount));
                            cash_incomes_9 += bill_amount_db;
                            cash_incomes_9_st = decimalFormat.format(cash_incomes_9);
                            txtCashSales9.setText(cash_incomes_9_st);
                        }


                    }
                    if (state.equals("paid") && !issuing_date.equals(expiration_date)) {
                        if (expiration_month.equals("9")) {
                            Map<String, Object> map = (Map<String, Object>) ds.getValue();
                            Object bill_amount = map.get("bill_amount");
                            double bill_amount_db = Double.parseDouble(String.valueOf(bill_amount));
                            credit_incomes_9 += bill_amount_db;
                            credit_incomes_9_st = decimalFormat.format(credit_incomes_9);
                            txtCreditSales9.setText(credit_incomes_9_st);
                        }


                    }

                    double total_incomes_9 = cash_incomes_9+credit_incomes_9+financial_incomes_9+other_incomes_9;
                    String total_incomes_9_st = decimalFormat.format(total_incomes_9);
                    txtTotalIncomes9.setText(total_incomes_9_st);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        companyRef.child(post_key).child("Worker Bills").orderByChild("operation_year").equalTo(year+"").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                total_salary_9 = 0;
                for (DataSnapshot ds : dataSnapshot.getChildren()) {

                    String operation_month = ds.child("operation_month").getValue().toString();

                    if (operation_month.equals("9")) {
                        Map<String, Object> map = (Map<String, Object>) ds.getValue();
                        Object total_payment = map.get("total_payment");
                        double total_payment_db = Double.parseDouble(String.valueOf(total_payment));
                        total_salary_9 += total_payment_db;
                        String total_salary_9_st = decimalFormat.format(total_salary_9);
                        txtWorkerPayment9.setText(total_salary_9_st);
                    }


                }

                companyRef.child(post_key).child("Purchased Orders").orderByChild("operation_year").equalTo(year+"").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        purchase_total_9 = 0;
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            String purchase_payment_state = ds.child("purchase_payment_state").getValue().toString();
                            String expiration_day = ds.child("expiration_day").getValue().toString();
                            String expiration_month = ds.child("expiration_month").getValue().toString();
                            String expiration_year = ds.child("expiration_year").getValue().toString();
                            String operation_day = ds.child("operation_day").getValue().toString();
                            String operation_month = ds.child("operation_month").getValue().toString();
                            String operation_time = ds.child("operation_time").getValue().toString();

                            String issuing_date = operation_day+"/"+operation_month+"/"+operation_time;
                            String expiration_date = expiration_day+"/"+expiration_month+"/"+expiration_year;

                            if (purchase_payment_state.equals("paid")) {

                                if (expiration_month.equals("9")) {
                                    Map<String, Object> map = (Map<String, Object>) ds.getValue();
                                    Object purchase_order_total_amount = map.get("purchase_order_total_amount");
                                    double purchase_order_total_amount_db = Double.parseDouble((String) purchase_order_total_amount);
                                    purchase_total_9 += purchase_order_total_amount_db;
                                    purchase_total_9_st = decimalFormat.format(purchase_total_9);
                                    txtPurchases9.setText(purchase_total_9_st);
                                }

                            }

                        }

                        total_incomes_9 = cash_incomes_9+credit_incomes_9+financial_incomes_9+other_incomes_9;
                        igv_taxes_9 = (total_incomes_9*0.18)-(purchase_total_9*0.18);
                        igv_taxes_9_st = decimalFormat.format(igv_taxes_9);
                        txtIgv9.setText(igv_taxes_9_st);

                        total_outcomes_9 = total_salary_9+purchase_total_9+igv_taxes_9+financial_outcomes_9+other_outcomes_9;
                        String total_outcomes_9_st = decimalFormat.format(total_outcomes_9);
                        txtTotalOutcomes9.setText(total_outcomes_9_st);


                        cash_flow_9_month = total_incomes_9-total_outcomes_9+cash_flow_8_month;
                        String cash_flow_9_st = decimalFormat.format(cash_flow_9_month);
                        txtFinalCash9.setText(cash_flow_9_st);
                        txtStartCash10.setText(cash_flow_9_st);
                        companyRef.child(post_key).child("Cash Flow").child("cash_flow"+year).setValue(cash_flow_9_st);

                        if (cash_flow_9_month < cash_flow_8_month) {
                            imgSituation9.setImageResource(R.drawable.perdida_gif);
                            txtSituation9.setTextColor(Color.RED);
                            txtSituation9.setText("PÉRDIDA");
                        }
                        else if (cash_flow_9_month > cash_flow_8_month) {
                            imgSituation9.setImageResource(R.drawable.ganancia_gif);
                            txtSituation9.setTextColor(Color.GREEN);
                            txtSituation9.setText("GANANCIA");
                        }

                        calculateMonth10();

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

    private void calculateMonth8() {
        companyRef.child(post_key).child("My Bills").orderByChild("issuing_year").equalTo(year).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                cash_incomes_8 = 0;
                credit_incomes_8 = 0;
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String state = ds.child("state").getValue().toString();
                    int issuing_day = ds.child("issuing_day").getValue(Integer.class);
                    int issuing_month = ds.child("issuing_month").getValue(Integer.class);
                    int issuing_year = ds.child("issuing_year").getValue(Integer.class);
                    String expiration_day = ds.child("expiration_day").getValue().toString();
                    String expiration_month = ds.child("expiration_month").getValue().toString();
                    String expiration_year = ds.child("expiration_year").getValue().toString();

                    String issuing_date = issuing_day+"/"+issuing_month+"/"+issuing_year;
                    String expiration_date = expiration_day+"/"+expiration_month+"/"+expiration_year;


                    if (state.equals("paid") && issuing_date.equals(expiration_date)) {
                        if (expiration_month.equals("8")) {
                            Map<String, Object> map = (Map<String, Object>) ds.getValue();
                            Object bill_amount = map.get("bill_amount");
                            double bill_amount_db = Double.parseDouble(String.valueOf(bill_amount));
                            cash_incomes_8 += bill_amount_db;
                            cash_incomes_8_st = decimalFormat.format(cash_incomes_8);
                            txtCashSales8.setText(cash_incomes_8_st);
                        }


                    }
                    if (state.equals("paid") && !issuing_date.equals(expiration_date)) {
                        if (expiration_month.equals("8")) {
                            Map<String, Object> map = (Map<String, Object>) ds.getValue();
                            Object bill_amount = map.get("bill_amount");
                            double bill_amount_db = Double.parseDouble(String.valueOf(bill_amount));
                            credit_incomes_8 += bill_amount_db;
                            credit_incomes_8_st = decimalFormat.format(credit_incomes_8);
                            txtCreditSales8.setText(credit_incomes_8_st);
                        }


                    }

                    double total_incomes_8 = cash_incomes_8+credit_incomes_8+financial_incomes_8+other_incomes_8;
                    String total_incomes_8_st = decimalFormat.format(total_incomes_8);
                    txtTotalIncomes8.setText(total_incomes_8_st);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        companyRef.child(post_key).child("Worker Bills").orderByChild("operation_year").equalTo(year+"").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                total_salary_8 = 0;
                for (DataSnapshot ds : dataSnapshot.getChildren()) {

                    String operation_month = ds.child("operation_month").getValue().toString();

                    if (operation_month.equals("8")) {
                        Map<String, Object> map = (Map<String, Object>) ds.getValue();
                        Object total_payment = map.get("total_payment");
                        double total_payment_db = Double.parseDouble(String.valueOf(total_payment));
                        total_salary_8 += total_payment_db;
                        String total_salary_8_st = decimalFormat.format(total_salary_8);
                        txtWorkerPayment8.setText(total_salary_8_st);
                    }


                }

                companyRef.child(post_key).child("Purchased Orders").orderByChild("operation_year").equalTo(year+"").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        purchase_total_8 = 0;
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            String purchase_payment_state = ds.child("purchase_payment_state").getValue().toString();
                            String expiration_day = ds.child("expiration_day").getValue().toString();
                            String expiration_month = ds.child("expiration_month").getValue().toString();
                            String expiration_year = ds.child("expiration_year").getValue().toString();
                            String operation_day = ds.child("operation_day").getValue().toString();
                            String operation_month = ds.child("operation_month").getValue().toString();
                            String operation_time = ds.child("operation_time").getValue().toString();

                            String issuing_date = operation_day+"/"+operation_month+"/"+operation_time;
                            String expiration_date = expiration_day+"/"+expiration_month+"/"+expiration_year;

                            if (purchase_payment_state.equals("paid")) {

                                if (expiration_month.equals("8")) {
                                    Map<String, Object> map = (Map<String, Object>) ds.getValue();
                                    Object purchase_order_total_amount = map.get("purchase_order_total_amount");
                                    double purchase_order_total_amount_db = Double.parseDouble((String) purchase_order_total_amount);
                                    purchase_total_8 += purchase_order_total_amount_db;
                                    purchase_total_8_st = decimalFormat.format(purchase_total_8);
                                    txtPurchases8.setText(purchase_total_8_st);
                                }

                            }

                        }

                        total_incomes_8 = cash_incomes_8+credit_incomes_8+financial_incomes_8+other_incomes_8;
                        igv_taxes_8 = (total_incomes_8*0.18)-(purchase_total_8*0.18);
                        igv_taxes_8_st = decimalFormat.format(igv_taxes_8);
                        txtIgv8.setText(igv_taxes_8_st);

                        total_outcomes_8 = total_salary_8+purchase_total_8+igv_taxes_8+financial_outcomes_8+other_outcomes_8;
                        String total_outcomes_8_st = decimalFormat.format(total_outcomes_8);
                        txtTotalOutcomes8.setText(total_outcomes_8_st);


                        cash_flow_8_month = total_incomes_8-total_outcomes_8+cash_flow_7_month;
                        String cash_flow_8_st = decimalFormat.format(cash_flow_8_month);
                        txtFinalCash8.setText(cash_flow_8_st);
                        txtStartCash9.setText(cash_flow_8_st);
                        companyRef.child(post_key).child("Cash Flow").child("cash_flow"+year).setValue(cash_flow_8_st);

                        if (cash_flow_8_month < cash_flow_7_month) {
                            imgSituation8.setImageResource(R.drawable.perdida_gif);
                            txtSituation8.setTextColor(Color.RED);
                            txtSituation8.setText("PÉRDIDA");
                        }
                        else if (cash_flow_8_month > cash_flow_7_month) {
                            imgSituation8.setImageResource(R.drawable.ganancia_gif);
                            txtSituation8.setTextColor(Color.GREEN);
                            txtSituation8.setText("GANANCIA");
                        }

                        calculateMonth9();

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

    private void calculateMonth7() {
        companyRef.child(post_key).child("My Bills").orderByChild("issuing_year").equalTo(year).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                cash_incomes_7 = 0;
                credit_incomes_7 = 0;
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String state = ds.child("state").getValue().toString();
                    int issuing_day = ds.child("issuing_day").getValue(Integer.class);
                    int issuing_month = ds.child("issuing_month").getValue(Integer.class);
                    int issuing_year = ds.child("issuing_year").getValue(Integer.class);
                    String expiration_day = ds.child("expiration_day").getValue().toString();
                    String expiration_month = ds.child("expiration_month").getValue().toString();
                    String expiration_year = ds.child("expiration_year").getValue().toString();

                    String issuing_date = issuing_day+"/"+issuing_month+"/"+issuing_year;
                    String expiration_date = expiration_day+"/"+expiration_month+"/"+expiration_year;


                    if (state.equals("paid") && issuing_date.equals(expiration_date)) {
                        if (expiration_month.equals("7")) {
                            Map<String, Object> map = (Map<String, Object>) ds.getValue();
                            Object bill_amount = map.get("bill_amount");
                            double bill_amount_db = Double.parseDouble(String.valueOf(bill_amount));
                            cash_incomes_7 += bill_amount_db;
                            cash_incomes_7_st = decimalFormat.format(cash_incomes_7);
                            txtCashSales7.setText(cash_incomes_7_st);
                        }


                    }
                    if (state.equals("paid") && !issuing_date.equals(expiration_date)) {
                        if (expiration_month.equals("7")) {
                            Map<String, Object> map = (Map<String, Object>) ds.getValue();
                            Object bill_amount = map.get("bill_amount");
                            double bill_amount_db = Double.parseDouble(String.valueOf(bill_amount));
                            credit_incomes_7 += bill_amount_db;
                            credit_incomes_7_st = decimalFormat.format(credit_incomes_7);
                            txtCreditSales7.setText(credit_incomes_7_st);
                        }


                    }

                    double total_incomes_7 = cash_incomes_7+credit_incomes_7+financial_incomes_7+other_incomes_7;
                    String total_incomes_7_st = decimalFormat.format(total_incomes_7);
                    txtTotalIncomes7.setText(total_incomes_7_st);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        companyRef.child(post_key).child("Worker Bills").orderByChild("operation_year").equalTo(year+"").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                total_salary_7 = 0;
                for (DataSnapshot ds : dataSnapshot.getChildren()) {

                    String operation_month = ds.child("operation_month").getValue().toString();

                    if (operation_month.equals("7")) {
                        Map<String, Object> map = (Map<String, Object>) ds.getValue();
                        Object total_payment = map.get("total_payment");
                        double total_payment_db = Double.parseDouble(String.valueOf(total_payment));
                        total_salary_7 += total_payment_db;
                        String total_salary_7_st = decimalFormat.format(total_salary_7);
                        txtWorkerPayment7.setText(total_salary_7_st);
                    }


                }

                companyRef.child(post_key).child("Purchased Orders").orderByChild("operation_year").equalTo(year+"").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        purchase_total_7 = 0;
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            String purchase_payment_state = ds.child("purchase_payment_state").getValue().toString();
                            String expiration_day = ds.child("expiration_day").getValue().toString();
                            String expiration_month = ds.child("expiration_month").getValue().toString();
                            String expiration_year = ds.child("expiration_year").getValue().toString();
                            String operation_day = ds.child("operation_day").getValue().toString();
                            String operation_month = ds.child("operation_month").getValue().toString();
                            String operation_time = ds.child("operation_time").getValue().toString();

                            String issuing_date = operation_day+"/"+operation_month+"/"+operation_time;
                            String expiration_date = expiration_day+"/"+expiration_month+"/"+expiration_year;

                            if (purchase_payment_state.equals("paid")) {

                                if (expiration_month.equals("7")) {
                                    Map<String, Object> map = (Map<String, Object>) ds.getValue();
                                    Object purchase_order_total_amount = map.get("purchase_order_total_amount");
                                    double purchase_order_total_amount_db = Double.parseDouble((String) purchase_order_total_amount);
                                    purchase_total_7 += purchase_order_total_amount_db;
                                    purchase_total_7_st = decimalFormat.format(purchase_total_7);
                                    txtPurchases7.setText(purchase_total_7_st);
                                }

                            }

                        }

                        total_incomes_7 = cash_incomes_7+credit_incomes_7+financial_incomes_7+other_incomes_7;
                        igv_taxes_7 = (total_incomes_7*0.18)-(purchase_total_7*0.18);
                        igv_taxes_7_st = decimalFormat.format(igv_taxes_7);
                        txtIgv7.setText(igv_taxes_7_st);

                        total_outcomes_7 = total_salary_7+purchase_total_7+igv_taxes_7+financial_outcomes_7+other_outcomes_7;
                        String total_outcomes_7_st = decimalFormat.format(total_outcomes_7);
                        txtTotalOutcomes7.setText(total_outcomes_7_st);


                        cash_flow_7_month = total_incomes_7-total_outcomes_7+cash_flow_6_month;
                        String cash_flow_7_st = decimalFormat.format(cash_flow_7_month);
                        txtFinalCash7.setText(cash_flow_7_st);
                        txtStartCash8.setText(cash_flow_7_st);
                        companyRef.child(post_key).child("Cash Flow").child("cash_flow"+year).setValue(cash_flow_7_st);

                        if (cash_flow_7_month < cash_flow_6_month) {
                            imgSituation7.setImageResource(R.drawable.perdida_gif);
                            txtSituation7.setTextColor(Color.RED);
                            txtSituation7.setText("PÉRDIDA");
                        }
                        else if (cash_flow_7_month > cash_flow_6_month) {
                            imgSituation7.setImageResource(R.drawable.ganancia_gif);
                            txtSituation7.setTextColor(Color.GREEN);
                            txtSituation7.setText("GANANCIA");
                        }

                        calculateMonth8();

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

    private void calculateMonth6() {
        companyRef.child(post_key).child("My Bills").orderByChild("issuing_year").equalTo(year).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                cash_incomes_6 = 0;
                credit_incomes_6 = 0;
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String state = ds.child("state").getValue().toString();
                    int issuing_day = ds.child("issuing_day").getValue(Integer.class);
                    int issuing_month = ds.child("issuing_month").getValue(Integer.class);
                    int issuing_year = ds.child("issuing_year").getValue(Integer.class);
                    String expiration_day = ds.child("expiration_day").getValue().toString();
                    String expiration_month = ds.child("expiration_month").getValue().toString();
                    String expiration_year = ds.child("expiration_year").getValue().toString();

                    String issuing_date = issuing_day+"/"+issuing_month+"/"+issuing_year;
                    String expiration_date = expiration_day+"/"+expiration_month+"/"+expiration_year;


                    if (state.equals("paid") && issuing_date.equals(expiration_date)) {
                        if (expiration_month.equals("6")) {
                            Map<String, Object> map = (Map<String, Object>) ds.getValue();
                            Object bill_amount = map.get("bill_amount");
                            double bill_amount_db = Double.parseDouble(String.valueOf(bill_amount));
                            cash_incomes_6 += bill_amount_db;
                            cash_incomes_6_st = decimalFormat.format(cash_incomes_6);
                            txtCashSales6.setText(cash_incomes_6_st);
                        }


                    }
                    if (state.equals("paid") && !issuing_date.equals(expiration_date)) {
                        if (expiration_month.equals("6")) {
                            Map<String, Object> map = (Map<String, Object>) ds.getValue();
                            Object bill_amount = map.get("bill_amount");
                            double bill_amount_db = Double.parseDouble(String.valueOf(bill_amount));
                            credit_incomes_6 += bill_amount_db;
                            credit_incomes_6_st = decimalFormat.format(credit_incomes_6);
                            txtCreditSales6.setText(credit_incomes_6_st);
                        }


                    }

                    double total_incomes_6 = cash_incomes_6+credit_incomes_6+financial_incomes_6+other_incomes_6;
                    String total_incomes_6_st = decimalFormat.format(total_incomes_6);
                    txtTotalIncomes6.setText(total_incomes_6_st);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        companyRef.child(post_key).child("Worker Bills").orderByChild("operation_year").equalTo(year+"").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                total_salary_6 = 0;
                for (DataSnapshot ds : dataSnapshot.getChildren()) {

                    String operation_month = ds.child("operation_month").getValue().toString();

                    if (operation_month.equals("6")) {
                        Map<String, Object> map = (Map<String, Object>) ds.getValue();
                        Object total_payment = map.get("total_payment");
                        double total_payment_db = Double.parseDouble(String.valueOf(total_payment));
                        total_salary_6 += total_payment_db;
                        String total_salary_6_st = decimalFormat.format(total_salary_6);
                        txtWorkerPayment6.setText(total_salary_6_st);
                    }


                }

                companyRef.child(post_key).child("Purchased Orders").orderByChild("operation_year").equalTo(year+"").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        purchase_total_6 = 0;
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            String purchase_payment_state = ds.child("purchase_payment_state").getValue().toString();
                            String expiration_day = ds.child("expiration_day").getValue().toString();
                            String expiration_month = ds.child("expiration_month").getValue().toString();
                            String expiration_year = ds.child("expiration_year").getValue().toString();
                            String operation_day = ds.child("operation_day").getValue().toString();
                            String operation_month = ds.child("operation_month").getValue().toString();
                            String operation_time = ds.child("operation_time").getValue().toString();

                            String issuing_date = operation_day+"/"+operation_month+"/"+operation_time;
                            String expiration_date = expiration_day+"/"+expiration_month+"/"+expiration_year;

                            if (purchase_payment_state.equals("paid")) {

                                if (expiration_month.equals("6")) {
                                    Map<String, Object> map = (Map<String, Object>) ds.getValue();
                                    Object purchase_order_total_amount = map.get("purchase_order_total_amount");
                                    double purchase_order_total_amount_db = Double.parseDouble((String) purchase_order_total_amount);
                                    purchase_total_6 += purchase_order_total_amount_db;
                                    purchase_total_6_st = decimalFormat.format(purchase_total_6);
                                    txtPurchases6.setText(purchase_total_6_st);
                                }

                            }

                        }

                        total_incomes_6 = cash_incomes_6+credit_incomes_6+financial_incomes_6+other_incomes_6;
                        igv_taxes_6 = (total_incomes_6*0.18)-(purchase_total_6*0.18);
                        igv_taxes_6_st = decimalFormat.format(igv_taxes_6);
                        txtIgv6.setText(igv_taxes_6_st);

                        total_outcomes_6 = total_salary_6+purchase_total_6+igv_taxes_6+financial_outcomes_6+other_outcomes_6;
                        String total_outcomes_6_st = decimalFormat.format(total_outcomes_6);
                        txtTotalOutcomes6.setText(total_outcomes_6_st);


                        cash_flow_6_month = total_incomes_6-total_outcomes_6+cash_flow_5_month;
                        String cash_flow_6_st = decimalFormat.format(cash_flow_6_month);
                        txtFinalCash6.setText(cash_flow_6_st);
                        txtStartCash7.setText(cash_flow_6_st);
                        companyRef.child(post_key).child("Cash Flow").child("cash_flow"+year).setValue(cash_flow_6_st);

                        if (cash_flow_6_month < cash_flow_5_month) {
                            imgSituation6.setImageResource(R.drawable.perdida_gif);
                            txtSituation6.setTextColor(Color.RED);
                            txtSituation6.setText("PÉRDIDA");
                        }
                        else if (cash_flow_6_month > cash_flow_5_month) {
                            imgSituation6.setImageResource(R.drawable.ganancia_gif);
                            txtSituation6.setTextColor(Color.GREEN);
                            txtSituation6.setText("GANANCIA");
                        }

                        calculateMonth7();

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

    private void calculateMonth5() {
        companyRef.child(post_key).child("My Bills").orderByChild("issuing_year").equalTo(year).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                cash_incomes_5 = 0;
                credit_incomes_5 = 0;
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String state = ds.child("state").getValue().toString();
                    int issuing_day = ds.child("issuing_day").getValue(Integer.class);
                    int issuing_month = ds.child("issuing_month").getValue(Integer.class);
                    int issuing_year = ds.child("issuing_year").getValue(Integer.class);
                    String expiration_day = ds.child("expiration_day").getValue().toString();
                    String expiration_month = ds.child("expiration_month").getValue().toString();
                    String expiration_year = ds.child("expiration_year").getValue().toString();

                    String issuing_date = issuing_day+"/"+issuing_month+"/"+issuing_year;
                    String expiration_date = expiration_day+"/"+expiration_month+"/"+expiration_year;


                    if (state.equals("paid") && issuing_date.equals(expiration_date)) {
                        if (expiration_month.equals("5")) {
                            Map<String, Object> map = (Map<String, Object>) ds.getValue();
                            Object bill_amount = map.get("bill_amount");
                            double bill_amount_db = Double.parseDouble(String.valueOf(bill_amount));
                            cash_incomes_5 += bill_amount_db;
                            cash_incomes_5_st = decimalFormat.format(cash_incomes_5);
                            txtCashSales5.setText(cash_incomes_5_st);
                        }


                    }
                    if (state.equals("paid") && !issuing_date.equals(expiration_date)) {
                        if (expiration_month.equals("5")) {
                            Map<String, Object> map = (Map<String, Object>) ds.getValue();
                            Object bill_amount = map.get("bill_amount");
                            double bill_amount_db = Double.parseDouble(String.valueOf(bill_amount));
                            credit_incomes_5 += bill_amount_db;
                            credit_incomes_5_st = decimalFormat.format(credit_incomes_5);
                            txtCreditSales5.setText(credit_incomes_5_st);
                        }


                    }

                    double total_incomes_5 = cash_incomes_5+credit_incomes_5+financial_incomes_5+other_incomes_5;
                    String total_incomes_5_st = decimalFormat.format(total_incomes_5);
                    txtTotalIncomes5.setText(total_incomes_5_st);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        companyRef.child(post_key).child("Worker Bills").orderByChild("operation_year").equalTo(year+"").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                total_salary_5 = 0;
                for (DataSnapshot ds : dataSnapshot.getChildren()) {

                    String operation_month = ds.child("operation_month").getValue().toString();

                    if (operation_month.equals("5")) {
                        Map<String, Object> map = (Map<String, Object>) ds.getValue();
                        Object total_payment = map.get("total_payment");
                        double total_payment_db = Double.parseDouble(String.valueOf(total_payment));
                        total_salary_5 += total_payment_db;
                        String total_salary_5_st = decimalFormat.format(total_salary_5);
                        txtWorkerPayment5.setText(total_salary_5_st);
                    }


                }

                companyRef.child(post_key).child("Purchased Orders").orderByChild("operation_year").equalTo(year+"").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        purchase_total_5 = 0;
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            String purchase_payment_state = ds.child("purchase_payment_state").getValue().toString();
                            String expiration_day = ds.child("expiration_day").getValue().toString();
                            String expiration_month = ds.child("expiration_month").getValue().toString();
                            String expiration_year = ds.child("expiration_year").getValue().toString();
                            String operation_day = ds.child("operation_day").getValue().toString();
                            String operation_month = ds.child("operation_month").getValue().toString();
                            String operation_time = ds.child("operation_time").getValue().toString();

                            String issuing_date = operation_day+"/"+operation_month+"/"+operation_time;
                            String expiration_date = expiration_day+"/"+expiration_month+"/"+expiration_year;

                            if (purchase_payment_state.equals("paid")) {

                                if (expiration_month.equals("5")) {
                                    Map<String, Object> map = (Map<String, Object>) ds.getValue();
                                    Object purchase_order_total_amount = map.get("purchase_order_total_amount");
                                    double purchase_order_total_amount_db = Double.parseDouble((String) purchase_order_total_amount);
                                    purchase_total_5 += purchase_order_total_amount_db;
                                    purchase_total_5_st = decimalFormat.format(purchase_total_5);
                                    txtPurchases5.setText(purchase_total_5_st);
                                }

                            }

                        }

                        total_incomes_5 = cash_incomes_5+credit_incomes_5+financial_incomes_5+other_incomes_5;
                        igv_taxes_5 = (total_incomes_5*0.18)-(purchase_total_5*0.18);
                        igv_taxes_5_st = decimalFormat.format(igv_taxes_5);
                        txtIgv5.setText(igv_taxes_5_st);

                        total_outcomes_5 = total_salary_5+purchase_total_5+igv_taxes_5+financial_outcomes_5+other_outcomes_5;
                        String total_outcomes_5_st = decimalFormat.format(total_outcomes_5);
                        txtTotalOutcomes5.setText(total_outcomes_5_st);


                        cash_flow_5_month = total_incomes_5-total_outcomes_5+cash_flow_4_month;
                        String cash_flow_5_st = decimalFormat.format(cash_flow_5_month);
                        txtFinalCash5.setText(cash_flow_5_st);
                        txtStartCash6.setText(cash_flow_5_st);
                        companyRef.child(post_key).child("Cash Flow").child("cash_flow"+year).setValue(cash_flow_5_st);

                        if (cash_flow_5_month < cash_flow_4_month) {
                            imgSituation5.setImageResource(R.drawable.perdida_gif);
                            txtSituation5.setTextColor(Color.RED);
                            txtSituation5.setText("PÉRDIDA");
                        }
                        else if (cash_flow_5_month > cash_flow_4_month) {
                            imgSituation5.setImageResource(R.drawable.ganancia_gif);
                            txtSituation5.setTextColor(Color.GREEN);
                            txtSituation5.setText("GANANCIA");
                        }

                        calculateMonth6();

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

    private void calculateMonth4() {
        companyRef.child(post_key).child("My Bills").orderByChild("issuing_year").equalTo(year).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                cash_incomes_4 = 0;
                credit_incomes_4 = 0;
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String state = ds.child("state").getValue().toString();
                    int issuing_day = ds.child("issuing_day").getValue(Integer.class);
                    int issuing_month = ds.child("issuing_month").getValue(Integer.class);
                    int issuing_year = ds.child("issuing_year").getValue(Integer.class);
                    String expiration_day = ds.child("expiration_day").getValue().toString();
                    String expiration_month = ds.child("expiration_month").getValue().toString();
                    String expiration_year = ds.child("expiration_year").getValue().toString();

                    String issuing_date = issuing_day+"/"+issuing_month+"/"+issuing_year;
                    String expiration_date = expiration_day+"/"+expiration_month+"/"+expiration_year;


                    if (state.equals("paid") && issuing_date.equals(expiration_date)) {
                        if (expiration_month.equals("4")) {
                            Map<String, Object> map = (Map<String, Object>) ds.getValue();
                            Object bill_amount = map.get("bill_amount");
                            double bill_amount_db = Double.parseDouble(String.valueOf(bill_amount));
                            cash_incomes_4 += bill_amount_db;
                            cash_incomes_4_st = decimalFormat.format(cash_incomes_4);
                            txtCashSales4.setText(cash_incomes_4_st);
                        }


                    }
                    if (state.equals("paid") && !issuing_date.equals(expiration_date)) {
                        if (expiration_month.equals("4")) {
                            Map<String, Object> map = (Map<String, Object>) ds.getValue();
                            Object bill_amount = map.get("bill_amount");
                            double bill_amount_db = Double.parseDouble(String.valueOf(bill_amount));
                            credit_incomes_4 += bill_amount_db;
                            credit_incomes_4_st = decimalFormat.format(credit_incomes_4);
                            txtCreditSales4.setText(credit_incomes_4_st);
                        }


                    }

                    double total_incomes_4 = cash_incomes_4+credit_incomes_4+financial_incomes_4+other_incomes_4;
                    String total_incomes_4_st = decimalFormat.format(total_incomes_4);
                    txtTotalIncomes4.setText(total_incomes_4_st);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        companyRef.child(post_key).child("Worker Bills").orderByChild("operation_year").equalTo(year+"").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                total_salary_4 = 0;
                for (DataSnapshot ds : dataSnapshot.getChildren()) {

                    String operation_month = ds.child("operation_month").getValue().toString();

                    if (operation_month.equals("4")) {
                        Map<String, Object> map = (Map<String, Object>) ds.getValue();
                        Object total_payment = map.get("total_payment");
                        double total_payment_db = Double.parseDouble(String.valueOf(total_payment));
                        total_salary_4 += total_payment_db;
                        String total_salary_4_st = decimalFormat.format(total_salary_4);
                        txtWorkerPayment4.setText(total_salary_4_st);
                    }


                }

                companyRef.child(post_key).child("Purchased Orders").orderByChild("operation_year").equalTo(year+"").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        purchase_total_4 = 0;
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            String purchase_payment_state = ds.child("purchase_payment_state").getValue().toString();
                            String expiration_day = ds.child("expiration_day").getValue().toString();
                            String expiration_month = ds.child("expiration_month").getValue().toString();
                            String expiration_year = ds.child("expiration_year").getValue().toString();
                            String operation_day = ds.child("operation_day").getValue().toString();
                            String operation_month = ds.child("operation_month").getValue().toString();
                            String operation_time = ds.child("operation_time").getValue().toString();

                            String issuing_date = operation_day+"/"+operation_month+"/"+operation_time;
                            String expiration_date = expiration_day+"/"+expiration_month+"/"+expiration_year;

                            if (purchase_payment_state.equals("paid")) {

                                if (expiration_month.equals("4")) {
                                    Map<String, Object> map = (Map<String, Object>) ds.getValue();
                                    Object purchase_order_total_amount = map.get("purchase_order_total_amount");
                                    double purchase_order_total_amount_db = Double.parseDouble((String) purchase_order_total_amount);
                                    purchase_total_4 += purchase_order_total_amount_db;
                                    purchase_total_4_st = decimalFormat.format(purchase_total_4);
                                    txtPurchases4.setText(purchase_total_4_st);
                                }

                            }

                        }

                        total_incomes_4 = cash_incomes_4+credit_incomes_4+financial_incomes_4+other_incomes_4;
                        igv_taxes_4 = (total_incomes_4*0.18)-(purchase_total_4*0.18);
                        igv_taxes_4_st = decimalFormat.format(igv_taxes_4);
                        txtIgv4.setText(igv_taxes_4_st);

                        total_outcomes_4 = total_salary_4+purchase_total_4+igv_taxes_4+financial_outcomes_4+other_outcomes_4;
                        String total_outcomes_4_st = decimalFormat.format(total_outcomes_4);
                        txtTotalOutcomes4.setText(total_outcomes_4_st);


                        cash_flow_4_month = total_incomes_4-total_outcomes_4+cash_flow_3_month;
                        String cash_flow_4_st = decimalFormat.format(cash_flow_4_month);
                        txtFinalCash4.setText(cash_flow_4_st);
                        txtStartCash5.setText(cash_flow_4_st);
                        companyRef.child(post_key).child("Cash Flow").child("cash_flow"+year).setValue(cash_flow_4_st);

                        if (cash_flow_4_month < cash_flow_3_month) {
                            imgSituation4.setImageResource(R.drawable.perdida_gif);
                            txtSituation4.setTextColor(Color.RED);
                            txtSituation4.setText("PÉRDIDA");
                        }
                        else if (cash_flow_4_month > cash_flow_3_month) {
                            imgSituation4.setImageResource(R.drawable.ganancia_gif);
                            txtSituation4.setTextColor(Color.GREEN);
                            txtSituation4.setText("GANANCIA");
                        }

                        calculateMonth5();

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

    private void calculateMonth3() {
        companyRef.child(post_key).child("My Bills").orderByChild("issuing_year").equalTo(year).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                cash_incomes_3 = 0;
                credit_incomes_3 = 0;
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String state = ds.child("state").getValue().toString();
                    int issuing_day = ds.child("issuing_day").getValue(Integer.class);
                    int issuing_month = ds.child("issuing_month").getValue(Integer.class);
                    int issuing_year = ds.child("issuing_year").getValue(Integer.class);
                    String expiration_day = ds.child("expiration_day").getValue().toString();
                    String expiration_month = ds.child("expiration_month").getValue().toString();
                    String expiration_year = ds.child("expiration_year").getValue().toString();

                    String issuing_date = issuing_day+"/"+issuing_month+"/"+issuing_year;
                    String expiration_date = expiration_day+"/"+expiration_month+"/"+expiration_year;


                    if (state.equals("paid") && issuing_date.equals(expiration_date)) {
                        if (expiration_month.equals("3")) {
                            Map<String, Object> map = (Map<String, Object>) ds.getValue();
                            Object bill_amount = map.get("bill_amount");
                            double bill_amount_db = Double.parseDouble(String.valueOf(bill_amount));
                            cash_incomes_3 += bill_amount_db;
                            cash_incomes_3_st = decimalFormat.format(cash_incomes_3);
                            txtCashSales3.setText(cash_incomes_3_st);
                        }


                    }
                    if (state.equals("paid") && !issuing_date.equals(expiration_date)) {
                        if (expiration_month.equals("3")) {
                            Map<String, Object> map = (Map<String, Object>) ds.getValue();
                            Object bill_amount = map.get("bill_amount");
                            double bill_amount_db = Double.parseDouble(String.valueOf(bill_amount));
                            credit_incomes_3 += bill_amount_db;
                            credit_incomes_3_st = decimalFormat.format(credit_incomes_3);
                            txtCreditSales3.setText(credit_incomes_3_st);
                        }


                    }

                    double total_incomes_3 = cash_incomes_3+credit_incomes_3+financial_incomes_3+other_incomes_3;
                    String total_incomes_3_st = decimalFormat.format(total_incomes_3);
                    txtTotalIncomes3.setText(total_incomes_3_st);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        companyRef.child(post_key).child("Worker Bills").orderByChild("operation_year").equalTo(year+"").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                total_salary_3 = 0;
                for (DataSnapshot ds : dataSnapshot.getChildren()) {

                    String operation_month = ds.child("operation_month").getValue().toString();

                    if (operation_month.equals("3")) {
                        Map<String, Object> map = (Map<String, Object>) ds.getValue();
                        Object total_payment = map.get("total_payment");
                        double total_payment_db = Double.parseDouble(String.valueOf(total_payment));
                        total_salary_3 += total_payment_db;
                        String total_salary_3_st = decimalFormat.format(total_salary_3);
                        txtWorkerPayment3.setText(total_salary_3_st);
                    }


                }

                companyRef.child(post_key).child("Purchased Orders").orderByChild("operation_year").equalTo(year+"").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        purchase_total_3 = 0;
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            String purchase_payment_state = ds.child("purchase_payment_state").getValue().toString();
                            String expiration_day = ds.child("expiration_day").getValue().toString();
                            String expiration_month = ds.child("expiration_month").getValue().toString();
                            String expiration_year = ds.child("expiration_year").getValue().toString();
                            String operation_day = ds.child("operation_day").getValue().toString();
                            String operation_month = ds.child("operation_month").getValue().toString();
                            String operation_time = ds.child("operation_time").getValue().toString();

                            String issuing_date = operation_day+"/"+operation_month+"/"+operation_time;
                            String expiration_date = expiration_day+"/"+expiration_month+"/"+expiration_year;

                            if (purchase_payment_state.equals("paid")) {

                                if (expiration_month.equals("3")) {
                                    Map<String, Object> map = (Map<String, Object>) ds.getValue();
                                    Object purchase_order_total_amount = map.get("purchase_order_total_amount");
                                    double purchase_order_total_amount_db = Double.parseDouble((String) purchase_order_total_amount);
                                    purchase_total_3 += purchase_order_total_amount_db;
                                    purchase_total_3_st = decimalFormat.format(purchase_total_3);
                                    txtPurchases3.setText(purchase_total_3_st);
                                }

                            }

                        }

                        total_incomes_3 = cash_incomes_3+credit_incomes_3+financial_incomes_3+other_incomes_3;
                        igv_taxes_3 = (total_incomes_3*0.18)-(purchase_total_3*0.18);
                        igv_taxes_3_st = decimalFormat.format(igv_taxes_3);
                        txtIgv3.setText(igv_taxes_3_st);

                        total_outcomes_3 = total_salary_3+purchase_total_3+igv_taxes_3+financial_outcomes_3+other_outcomes_3;
                        String total_outcomes_3_st = decimalFormat.format(total_outcomes_3);
                        txtTotalOutcomes3.setText(total_outcomes_3_st);


                        cash_flow_3_month = total_incomes_3-total_outcomes_3+cash_flow_2_month;
                        String cash_flow_3_st = decimalFormat.format(cash_flow_3_month);
                        txtFinalCash3.setText(cash_flow_3_st);
                        txtStartCash4.setText(cash_flow_3_st);
                        companyRef.child(post_key).child("Cash Flow").child("cash_flow"+year).setValue(cash_flow_3_st);

                        if (cash_flow_3_month < cash_flow_2_month) {
                            imgSituation3.setImageResource(R.drawable.perdida_gif);
                            txtSituation3.setTextColor(Color.RED);
                            txtSituation3.setText("PÉRDIDA");
                        }
                        else if (cash_flow_3_month > cash_flow_2_month) {
                            imgSituation3.setImageResource(R.drawable.ganancia_gif);
                            txtSituation3.setTextColor(Color.GREEN);
                            txtSituation3.setText("GANANCIA");
                        }

                        calculateMonth4();

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

    private void calculateMonth2() {
        companyRef.child(post_key).child("My Bills").orderByChild("issuing_year").equalTo(year).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                cash_incomes_2 = 0;
                credit_incomes_2 = 0;
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String state = ds.child("state").getValue().toString();
                    int issuing_day = ds.child("issuing_day").getValue(Integer.class);
                    int issuing_month = ds.child("issuing_month").getValue(Integer.class);
                    int issuing_year = ds.child("issuing_year").getValue(Integer.class);
                    String expiration_day = ds.child("expiration_day").getValue().toString();
                    String expiration_month = ds.child("expiration_month").getValue().toString();
                    String expiration_year = ds.child("expiration_year").getValue().toString();

                    String issuing_date = issuing_day+"/"+issuing_month+"/"+issuing_year;
                    String expiration_date = expiration_day+"/"+expiration_month+"/"+expiration_year;


                    if (state.equals("paid") && issuing_date.equals(expiration_date)) {
                        if (expiration_month.equals("2")) {
                            Map<String, Object> map = (Map<String, Object>) ds.getValue();
                            Object bill_amount = map.get("bill_amount");
                            double bill_amount_db = Double.parseDouble(String.valueOf(bill_amount));
                            cash_incomes_2 += bill_amount_db;
                            cash_incomes_2_st = decimalFormat.format(cash_incomes_2);
                            txtCashSales2.setText(cash_incomes_2_st);
                        }


                    }
                    if (state.equals("paid") && !issuing_date.equals(expiration_date)) {
                        if (expiration_month.equals("2")) {
                            Map<String, Object> map = (Map<String, Object>) ds.getValue();
                            Object bill_amount = map.get("bill_amount");
                            double bill_amount_db = Double.parseDouble(String.valueOf(bill_amount));
                            credit_incomes_2 += bill_amount_db;
                            credit_incomes_2_st = decimalFormat.format(credit_incomes_2);
                            txtCreditSales2.setText(credit_incomes_2_st);
                        }


                    }

                    double total_incomes_2 = cash_incomes_2+credit_incomes_2+financial_incomes_2+other_incomes_2;
                    String total_incomes_2_st = decimalFormat.format(total_incomes_2);
                    txtTotalIncomes2.setText(total_incomes_2_st);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        companyRef.child(post_key).child("Worker Bills").orderByChild("operation_year").equalTo(year+"").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                total_salary_2 = 0;
                for (DataSnapshot ds : dataSnapshot.getChildren()) {

                    String operation_month = ds.child("operation_month").getValue().toString();

                    if (operation_month.equals("2")) {
                        Map<String, Object> map = (Map<String, Object>) ds.getValue();
                        Object total_payment = map.get("total_payment");
                        double total_payment_db = Double.parseDouble(String.valueOf(total_payment));
                        total_salary_2 += total_payment_db;
                        String total_salary_2_st = decimalFormat.format(total_salary_2);
                        txtWorkerPayment2.setText(total_salary_2_st);
                    }


                }

                companyRef.child(post_key).child("Purchased Orders").orderByChild("operation_year").equalTo(year+"").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        purchase_total_2 = 0;
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            String purchase_payment_state = ds.child("purchase_payment_state").getValue().toString();
                            String expiration_day = ds.child("expiration_day").getValue().toString();
                            String expiration_month = ds.child("expiration_month").getValue().toString();
                            String expiration_year = ds.child("expiration_year").getValue().toString();
                            String operation_day = ds.child("operation_day").getValue().toString();
                            String operation_month = ds.child("operation_month").getValue().toString();
                            String operation_time = ds.child("operation_time").getValue().toString();

                            String issuing_date = operation_day+"/"+operation_month+"/"+operation_time;
                            String expiration_date = expiration_day+"/"+expiration_month+"/"+expiration_year;

                            if (purchase_payment_state.equals("paid")) {

                                if (expiration_month.equals("2")) {
                                    Map<String, Object> map = (Map<String, Object>) ds.getValue();
                                    Object purchase_order_total_amount = map.get("purchase_order_total_amount");
                                    double purchase_order_total_amount_db = Double.parseDouble((String) purchase_order_total_amount);
                                    purchase_total_2 += purchase_order_total_amount_db;
                                    purchase_total_2_st = decimalFormat.format(purchase_total_2);
                                    txtPurchases2.setText(purchase_total_2_st);
                                }


                            }


                        }

                        total_incomes_2 = cash_incomes_2+credit_incomes_2+financial_incomes_2+other_incomes_2;
                        igv_taxes_2 = (total_incomes_2*0.18)-(purchase_total_2*0.18);
                        igv_taxes_2_st = decimalFormat.format(igv_taxes_2);
                        txtIgv2.setText(igv_taxes_2_st);

                        total_outcomes_2 = total_salary_2+purchase_total_2+igv_taxes_2+financial_outcomes_2+other_outcomes_2;
                        String total_outcomes_2_st = decimalFormat.format(total_outcomes_2);
                        txtTotalOutcomes2.setText(total_outcomes_2_st);


                        cash_flow_2_month = total_incomes_2-total_outcomes_2+cash_flow_1_month;
                        String cash_flow_2_st = decimalFormat.format(cash_flow_2_month);
                        txtFinalCash2.setText(cash_flow_2_st);
                        txtStartCash3.setText(cash_flow_2_st);
                        companyRef.child(post_key).child("Cash Flow").child("cash_flow"+year).setValue(cash_flow_2_st);

                        if (cash_flow_2_month < cash_flow_1_month) {
                            imgSituation2.setImageResource(R.drawable.perdida_gif);
                            txtSituation2.setTextColor(Color.RED);
                            txtSituation2.setText("PÉRDIDA");
                        }
                        else if (cash_flow_2_month > cash_flow_1_month) {
                            imgSituation2.setImageResource(R.drawable.ganancia_gif);
                            txtSituation2.setTextColor(Color.GREEN);
                            txtSituation2.setText("GANANCIA");
                        }

                        calculateMonth3();

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

    private void calculateMonth1() {
        companyRef.child(post_key).child("My Bills").orderByChild("issuing_year").equalTo(year).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                cash_incomes_1 = 0;
                credit_incomes_1 = 0;
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String state = ds.child("state").getValue().toString();
                    int issuing_day = ds.child("issuing_day").getValue(Integer.class);
                    int issuing_month = ds.child("issuing_month").getValue(Integer.class);
                    int issuing_year = ds.child("issuing_year").getValue(Integer.class);
                    String expiration_day = ds.child("expiration_day").getValue().toString();
                    String expiration_month = ds.child("expiration_month").getValue().toString();
                    String expiration_year = ds.child("expiration_year").getValue().toString();

                    String issuing_date = issuing_day+"/"+issuing_month+"/"+issuing_year;
                    String expiration_date = expiration_day+"/"+expiration_month+"/"+expiration_year;


                    if (state.equals("paid") && issuing_date.equals(expiration_date)) {
                        if (expiration_month.equals("1")) {
                            Map<String, Object> map = (Map<String, Object>) ds.getValue();
                            Object bill_amount = map.get("bill_amount");
                            double bill_amount_db = Double.parseDouble(String.valueOf(bill_amount));
                            cash_incomes_1 += bill_amount_db;
                            cash_incomes_1_st = decimalFormat.format(cash_incomes_1);
                            txtCashSales1.setText(cash_incomes_1_st);
                        }


                    }
                    if (state.equals("paid") && !issuing_date.equals(expiration_date)) {
                        if (expiration_month.equals("1")) {
                            Map<String, Object> map = (Map<String, Object>) ds.getValue();
                            Object bill_amount = map.get("bill_amount");
                            double bill_amount_db = Double.parseDouble(String.valueOf(bill_amount));
                            credit_incomes_1 += bill_amount_db;
                            credit_incomes_1_st = decimalFormat.format(credit_incomes_1);
                            txtCreditSales1.setText(credit_incomes_1_st);
                        }


                    }

                    double total_incomes_1 = cash_incomes_1+credit_incomes_1;
                    String total_incomes_1_st = decimalFormat.format(total_incomes_1);
                    txtTotalIncomes1.setText(total_incomes_1_st);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        companyRef.child(post_key).child("My Bills").orderByChild("issuing_year").equalTo(last_year).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                cash_incomes_0 = 0;
                credit_incomes_0 = 0;
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String state = ds.child("state").getValue().toString();
                    int issuing_day = ds.child("issuing_day").getValue(Integer.class);
                    int issuing_month = ds.child("issuing_month").getValue(Integer.class);
                    int issuing_year = ds.child("issuing_year").getValue(Integer.class);
                    String expiration_day = ds.child("expiration_day").getValue().toString();
                    String expiration_month = ds.child("expiration_month").getValue().toString();
                    String expiration_year = ds.child("expiration_year").getValue().toString();

                    String issuing_date = issuing_day+"/"+issuing_month+"/"+issuing_year;
                    String expiration_date = expiration_day+"/"+expiration_month+"/"+expiration_year;

                    if (state.equals("paid") && issuing_date.equals(expiration_date)) {
                        Map<String, Object> map = (Map<String, Object>) ds.getValue();
                        Object bill_amount = map.get("bill_amount");
                        double cash_sum_sales_0 = Double.parseDouble(String.valueOf(bill_amount));
                        cash_incomes_0 += cash_sum_sales_0;

                    }
                    if (state.equals("paid") && !issuing_date.equals(expiration_date)) {
                        Map<String, Object> map = (Map<String, Object>) ds.getValue();
                        Object bill_amount = map.get("bill_amount");
                        double credit_sum_sales_0 = Double.parseDouble(String.valueOf(bill_amount));
                        credit_incomes_0 += credit_sum_sales_0;

                    }

                    total_incomes_0 = cash_incomes_1+credit_incomes_1+financial_incomes_1+other_incomes_1;
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        companyRef.child(post_key).child("Worker Bills").orderByChild("operation_year").equalTo(last_year+"").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                total_salary_0 = 0;
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Map<String, Object> map = (Map<String, Object>) ds.getValue();
                    Object total_payment = map.get("total_payment");
                    double total_payment_db = Double.parseDouble(String.valueOf(total_payment));
                    total_salary_0 += total_payment_db;

                }

                companyRef.child(post_key).child("Worker Bills").orderByChild("operation_year").equalTo(year+"").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        total_salary_1 = 0;
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {

                            String operation_month = ds.child("operation_month").getValue().toString();

                            if (operation_month.equals("1")) {
                                Map<String, Object> map = (Map<String, Object>) ds.getValue();
                                Object total_payment = map.get("total_payment");
                                double total_payment_db = Double.parseDouble(String.valueOf(total_payment));
                                total_salary_1 += total_payment_db;
                                String total_salary_1_st = decimalFormat.format(total_salary_1);
                                txtWorkerPayment1.setText(total_salary_1_st);
                            }


                        }

                        companyRef.child(post_key).child("Purchased Orders").orderByChild("operation_year").equalTo(year+"").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                purchase_total_1 = 0;
                                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                                    String purchase_payment_state = ds.child("purchase_payment_state").getValue().toString();
                                    String expiration_day = ds.child("expiration_day").getValue().toString();
                                    String expiration_month = ds.child("expiration_month").getValue().toString();
                                    String expiration_year = ds.child("expiration_year").getValue().toString();
                                    String operation_day = ds.child("operation_day").getValue().toString();
                                    String operation_month = ds.child("operation_month").getValue().toString();
                                    String operation_time = ds.child("operation_time").getValue().toString();

                                    String issuing_date = operation_day+"/"+operation_month+"/"+operation_time;
                                    String expiration_date = expiration_day+"/"+expiration_month+"/"+expiration_year;

                                    if (purchase_payment_state.equals("paid")) {

                                        if (expiration_month.equals("1")) {
                                            Map<String, Object> map = (Map<String, Object>) ds.getValue();
                                            Object purchase_order_total_amount = map.get("purchase_order_total_amount");
                                            double purchase_order_total_amount_db = Double.parseDouble((String) purchase_order_total_amount);
                                            purchase_total_1 += purchase_order_total_amount_db;
                                            purchase_total_1_st = decimalFormat.format(purchase_total_1);
                                            txtPurchases1.setText(purchase_total_1_st);
                                        }


                                    }


                                }

                                total_incomes_1 = cash_incomes_1+credit_incomes_1+financial_incomes_1+other_incomes_1;
                                igv_taxes_1 = (total_incomes_1*0.18)-(purchase_total_1*0.18);
                                igv_taxes_1_st = decimalFormat.format(igv_taxes_1);
                                txtIgv1.setText(igv_taxes_1_st);

                                double total_outcomes_1 = total_salary_1+purchase_total_1+igv_taxes_1+financial_outcomes_1+other_outcomes_1;
                                String total_outcomes_1_st = decimalFormat.format(total_outcomes_1);
                                txtTotalOutcomes1.setText(total_outcomes_1_st);
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });

                        companyRef.child(post_key).child("Purchased Orders").orderByChild("operation_year").equalTo(last_year+"").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                purchase_total_0 = 0;
                                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                                    Map<String, Object> map = (Map<String, Object>) ds.getValue();
                                    Object purchase_order_total_amount = map.get("purchase_order_total_amount");
                                    double purchase_order_total_amount_db = Double.parseDouble((String) purchase_order_total_amount);
                                    purchase_total_0 += purchase_order_total_amount_db;
                                }



                                total_incomes_1 = cash_incomes_1+credit_incomes_1+financial_incomes_1+other_incomes_1;
                                igv_taxes_1 = (total_incomes_1*0.18)-(purchase_total_1*0.18);
                                total_outcomes_1 = total_salary_1+purchase_total_1+igv_taxes_1;
                                cash_flow_1_month = total_incomes_1-total_outcomes_1+cash_flow_0_db;
                                String cash_flow_1_st = decimalFormat.format(cash_flow_1_month);
                                txtFinalCash1.setText(cash_flow_1_st);
                                txtStartCash2.setText(cash_flow_1_st);
                                companyRef.child(post_key).child("Cash Flow").child("cash_flow"+year).setValue(cash_flow_1_st);


                                if (cash_flow_1_month < cash_flow_0_db) {
                                    imgSituation1.setImageResource(R.drawable.perdida_gif);
                                    txtSituation1.setTextColor(Color.RED);
                                    txtSituation1.setText("PÉRDIDA");
                                }
                                else if (cash_flow_1_month > cash_flow_0_db) {
                                    imgSituation1.setImageResource(R.drawable.ganancia_gif);
                                    txtSituation1.setTextColor(Color.GREEN);
                                    txtSituation1.setText("GANANCIA");
                                }

                                calculateMonth2();
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
    }
}
