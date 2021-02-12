package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.FinancialManagement.CashFlow;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import in.galaxyofandroid.spinerdialog.OnSpinerItemClick;
import in.galaxyofandroid.spinerdialog.SpinnerDialog;

public class CashFlowActivity extends AppCompatActivity {

    TextView txtCashSales1,txtCreditSales1,txtFinancialIncomes1,txtOtherIncomes1,txtTotalIncomes1,txtWorkerPayment1,txtPurchases1,txtIgv1,txtFinancialOutcomes,txtOtherOutcomes,txtTotalOutcomes1,txtFinalCash1,
            txtCashSales2,txtCreditSales2,txtFinancialIncomes2,txtOtherIncomes2,txtTotalIncomes2,txtWorkerPayment2,txtPurchases2,txtIgv2,txtFinancialOutcomes2,txtOtherOutcomes2,txtTotalOutcomes2,txtFinalCash2,
            txtCashSales3,txtCreditSales3,txtFinancialIncomes3,txtOtherIncomes3,txtTotalIncomes3,txtCashSales4,txtCreditSales4,txtFinancialIncomes4,txtOtherIncomes4,txtTotalIncomes4,txtCashSales5,txtCreditSales5,txtFinancialIncomes5,txtOtherIncomes5,txtTotalIncomes5,
            txtCashSales6,txtCreditSales6,txtFinancialIncomes6,txtOtherIncomes6,txtTotalIncomes6,txtCashSales7,txtCreditSales7,txtFinancialIncomes7,txtOtherIncomes7,txtTotalIncomes7,txtCashSales8,txtCreditSales8,txtFinancialIncomes8,txtOtherIncomes8,txtTotalIncomes8,
            txtCashSales9,txtCreditSales9,txtFinancialIncomes9,txtOtherIncomes9,txtTotalIncomes9,txtCashSales10,txtCreditSales10,txtFinancialIncomes10,txtOtherIncomes10,txtTotalIncomes10,txtCashSales11,txtCreditSales11,txtFinancialIncomes11,txtOtherIncomes11,txtTotalIncomes11,
            txtCashSales12,txtCreditSales12,txtFinancialIncomes12,txtOtherIncomes12,txtTotalIncomes12,txtWorkerPayment3,txtPurchases3,txtIgv3,txtFinancialOutcomes3,txtOtherOutcomes3,txtTotalOutcomes3,txtWorkerPayment4,txtPurchases4,txtIgv4,txtFinancialOutcomes4,txtOtherOutcomes4,txtTotalOutcomes4,
            txtWorkerPayment5,txtPurchases5,txtIgv5,txtFinancialOutcomes5,txtOtherOutcomes5,txtTotalOutcomes5,txtWorkerPayment6,txtPurchases6,txtIgv6,txtFinancialOutcomes6,txtOtherOutcomes6,txtTotalOutcomes6,txtWorkerPayment7,txtPurchases7,txtIgv7,txtFinancialOutcomes7,txtOtherOutcomes7,txtTotalOutcomes7,
            txtWorkerPayment8,txtPurchases8,txtIgv8,txtFinancialOutcomes8,txtOtherOutcomes8,txtTotalOutcomes8,txtWorkerPayment9,txtPurchases9,txtIgv9,txtFinancialOutcomes9,txtOtherOutcomes9,txtTotalOutcomes9,txtWorkerPayment10,txtPurchases10,txtIgv10,txtFinancialOutcomes10,txtOtherOutcomes10,txtTotalOutcomes10,
            txtWorkerPayment11,txtPurchases11,txtIgv11,txtFinancialOutcomes11,txtOtherOutcomes11,txtTotalOutcomes11,txtWorkerPayment12,txtPurchases12,txtIgv12,txtFinancialOutcomes12,txtOtherOutcomes12,txtTotalOutcomes12,txtFinalCash3,txtFinalCash4,txtFinalCash5,txtFinalCash6,txtFinalCash7,txtFinalCash8,txtFinalCash9,txtFinalCash10,txtFinalCash11,txtFinalCash12,
            txtPeriod;
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
    Button btnChangePeriod;

    ArrayList<String> periods =new ArrayList<>();
    SpinnerDialog spinnerPeriods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash_flow);

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

        txtFinalCash1 = findViewById(R.id.txtFinalCash1);
        txtFinalCash2 = findViewById(R.id.txtFinalCash2);
        txtFinalCash3 = findViewById(R.id.txtFinalCash3);
        txtFinalCash4 = findViewById(R.id.txtFinalCash4);
        txtFinalCash5 = findViewById(R.id.txtFinalCash5);
        txtFinalCash6 = findViewById(R.id.txtFinalCash6);
        txtFinalCash7 = findViewById(R.id.txtFinalCash7);
        txtFinalCash8 = findViewById(R.id.txtFinalCash8);
        txtFinalCash9 = findViewById(R.id.txtFinalCash9);
        txtFinalCash10 = findViewById(R.id.txtFinalCash10);
        txtFinalCash11 = findViewById(R.id.txtFinalCash11);
        txtFinalCash12 = findViewById(R.id.txtFinalCash12);

        txtPeriod = findViewById(R.id.txtPeriod);
        btnChangePeriod = findViewById(R.id.btnChangePeriod);

        post_key = getIntent().getExtras().getString("post_key");
        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");

        decimalFormat = new DecimalFormat("0.00");


        Date date= new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        day = cal.get(Calendar.DAY_OF_MONTH);
        month = cal.get(Calendar.MONTH)+1;
        year = cal.get(Calendar.YEAR)-1;
        last_year = year-1;
        before_last_year = last_year-1;

        txtPeriod.setText("PERIODO: "+year);

        startToShowData();

        btnChangePeriod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                periods.add(year+"");periods.add(year-1+"");periods.add(year-2+"");periods.add(year-3+"");periods.add(year-4+"");periods.add(year-5+"");periods.add(year-6+"");periods.add(year-7+"");periods.add(year-8+"");periods.add(year-9+"");periods.add(year-10+"");

                spinnerPeriods.showSpinerDialog();

            }
        });

        spinnerPeriods = new SpinnerDialog(CashFlowActivity.this,periods, "Selecciona el Período");
        spinnerPeriods.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String item2, int position2) {

                year = Integer.parseInt(item2);
                last_year = year-1;
                before_last_year = last_year-1;
                txtPeriod.setText("PERIODO: "+year);

                txtCashSales1.setText("0.00");
                txtCashSales2.setText("0.00");
                txtCashSales3.setText("0.00");
                txtCashSales4.setText("0.00");
                txtCashSales5.setText("0.00");
                txtCashSales6.setText("0.00");
                txtCashSales7.setText("0.00");
                txtCashSales8.setText("0.00");
                txtCashSales9.setText("0.00");
                txtCashSales10.setText("0.00");
                txtCashSales11.setText("0.00");
                txtCashSales12.setText("0.00");

                txtCreditSales1.setText("0.00");
                txtCreditSales2.setText("0.00");
                txtCreditSales3.setText("0.00");
                txtCreditSales4.setText("0.00");
                txtCreditSales5.setText("0.00");
                txtCreditSales6.setText("0.00");
                txtCreditSales7.setText("0.00");
                txtCreditSales8.setText("0.00");
                txtCreditSales9.setText("0.00");
                txtCreditSales10.setText("0.00");
                txtCreditSales11.setText("0.00");
                txtCreditSales12.setText("0.00");

                txtFinancialIncomes1.setText("0.00");
                txtFinancialIncomes2.setText("0.00");
                txtFinancialIncomes3.setText("0.00");
                txtFinancialIncomes4.setText("0.00");
                txtFinancialIncomes5.setText("0.00");
                txtFinancialIncomes6.setText("0.00");
                txtFinancialIncomes7.setText("0.00");
                txtFinancialIncomes8.setText("0.00");
                txtFinancialIncomes9.setText("0.00");
                txtFinancialIncomes10.setText("0.00");
                txtFinancialIncomes11.setText("0.00");
                txtFinancialIncomes12.setText("0.00");

                txtOtherIncomes1.setText("0.00");
                txtOtherIncomes2.setText("0.00");
                txtOtherIncomes3.setText("0.00");
                txtOtherIncomes4.setText("0.00");
                txtOtherIncomes5.setText("0.00");
                txtOtherIncomes6.setText("0.00");
                txtOtherIncomes7.setText("0.00");
                txtOtherIncomes8.setText("0.00");
                txtOtherIncomes9.setText("0.00");
                txtOtherIncomes10.setText("0.00");
                txtOtherIncomes11.setText("0.00");
                txtOtherIncomes12.setText("0.00");

                txtTotalIncomes1.setText("0.00");
                txtTotalIncomes2.setText("0.00");
                txtTotalIncomes3.setText("0.00");
                txtTotalIncomes4.setText("0.00");
                txtTotalIncomes5.setText("0.00");
                txtTotalIncomes6.setText("0.00");
                txtTotalIncomes7.setText("0.00");
                txtTotalIncomes8.setText("0.00");
                txtTotalIncomes9.setText("0.00");
                txtTotalIncomes10.setText("0.00");
                txtTotalIncomes11.setText("0.00");
                txtTotalIncomes12.setText("0.00");

                txtWorkerPayment1.setText("0.00");
                txtWorkerPayment2.setText("0.00");
                txtWorkerPayment3.setText("0.00");
                txtWorkerPayment4.setText("0.00");
                txtWorkerPayment5.setText("0.00");
                txtWorkerPayment6.setText("0.00");
                txtWorkerPayment7.setText("0.00");
                txtWorkerPayment8.setText("0.00");
                txtWorkerPayment9.setText("0.00");
                txtWorkerPayment10.setText("0.00");
                txtWorkerPayment11.setText("0.00");
                txtWorkerPayment12.setText("0.00");

                txtIgv1.setText("0.00");
                txtIgv2.setText("0.00");
                txtIgv3.setText("0.00");
                txtIgv4.setText("0.00");
                txtIgv5.setText("0.00");
                txtIgv6.setText("0.00");
                txtIgv7.setText("0.00");
                txtIgv8.setText("0.00");
                txtIgv9.setText("0.00");
                txtIgv10.setText("0.00");
                txtIgv11.setText("0.00");
                txtIgv12.setText("0.00");

                txtPurchases1.setText("0.00");
                txtPurchases2.setText("0.00");
                txtPurchases3.setText("0.00");
                txtPurchases4.setText("0.00");
                txtPurchases5.setText("0.00");
                txtPurchases6.setText("0.00");
                txtPurchases7.setText("0.00");
                txtPurchases8.setText("0.00");
                txtPurchases9.setText("0.00");
                txtPurchases10.setText("0.00");
                txtPurchases11.setText("0.00");
                txtPurchases12.setText("0.00");

                txtFinancialOutcomes.setText("0.00");
                txtFinancialOutcomes2.setText("0.00");
                txtFinancialOutcomes3.setText("0.00");
                txtFinancialOutcomes4.setText("0.00");
                txtFinancialOutcomes5.setText("0.00");
                txtFinancialOutcomes6.setText("0.00");
                txtFinancialOutcomes7.setText("0.00");
                txtFinancialOutcomes8.setText("0.00");
                txtFinancialOutcomes9.setText("0.00");
                txtFinancialOutcomes10.setText("0.00");
                txtFinancialOutcomes11.setText("0.00");
                txtFinancialOutcomes12.setText("0.00");

                txtOtherOutcomes.setText("0.00");
                txtOtherOutcomes2.setText("0.00");
                txtOtherOutcomes3.setText("0.00");
                txtOtherOutcomes4.setText("0.00");
                txtOtherOutcomes5.setText("0.00");
                txtOtherOutcomes6.setText("0.00");
                txtOtherOutcomes7.setText("0.00");
                txtOtherOutcomes8.setText("0.00");
                txtOtherOutcomes9.setText("0.00");
                txtOtherOutcomes10.setText("0.00");
                txtOtherOutcomes11.setText("0.00");
                txtOtherOutcomes12.setText("0.00");

                txtTotalOutcomes1.setText("0.00");
                txtTotalOutcomes2.setText("0.00");
                txtTotalOutcomes3.setText("0.00");
                txtTotalOutcomes4.setText("0.00");
                txtTotalOutcomes5.setText("0.00");
                txtTotalOutcomes6.setText("0.00");
                txtTotalOutcomes7.setText("0.00");
                txtTotalOutcomes8.setText("0.00");
                txtTotalOutcomes9.setText("0.00");
                txtTotalOutcomes10.setText("0.00");
                txtTotalOutcomes11.setText("0.00");
                txtTotalOutcomes12.setText("0.00");

                txtFinalCash1.setText("0.00");
                txtFinalCash2.setText("0.00");
                txtFinalCash3.setText("0.00");
                txtFinalCash4.setText("0.00");
                txtFinalCash5.setText("0.00");
                txtFinalCash6.setText("0.00");
                txtFinalCash7.setText("0.00");
                txtFinalCash8.setText("0.00");
                txtFinalCash9.setText("0.00");
                txtFinalCash10.setText("0.00");
                txtFinalCash11.setText("0.00");
                txtFinalCash12.setText("0.00");

                startToShowData();

            }
        });



    }

    private void startToShowData() {


        companyRef.child(post_key).child("Cash Flow").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild("cash_flow" + last_year)) {
                    cash_flow_0 = dataSnapshot.child("cash_flow" + last_year).getValue().toString();
                    cash_flow_0_db = Double.parseDouble(cash_flow_0);


                } else {
                    cash_flow_0_db = 0.00;
                }

                calculateMonth1();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
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
