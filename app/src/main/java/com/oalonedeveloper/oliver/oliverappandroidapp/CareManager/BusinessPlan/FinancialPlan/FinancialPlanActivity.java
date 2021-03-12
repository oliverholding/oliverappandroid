package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.BusinessPlan.FinancialPlan;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
import java.util.Calendar;
import java.util.Date;

import es.dmoral.toasty.Toasty;

public class FinancialPlanActivity extends AppCompatActivity {

    TextView txtCashSales1,txtCreditSales1,txtFinancialIncomes1,txtOtherIncomes1,txtTotalIncomes1,txtStartCash1,txtWorkerPayment1,txtPurchases1,txtIgv1,txtFinancialOutcomes,txtOtherOutcomes,txtTotalOutcomes1,txtFinalCash1,
            txtCashSales2,txtCreditSales2,txtFinancialIncomes2,txtOtherIncomes2,txtTotalIncomes2,txtWorkerPayment2,txtPurchases2,txtIgv2,txtFinancialOutcomes2,txtOtherOutcomes2,txtTotalOutcomes2,txtFinalCash2,txtStartCash2,
            txtCashSales3,txtCreditSales3,txtFinancialIncomes3,txtOtherIncomes3,txtTotalIncomes3,txtCashSales4,txtCreditSales4,txtFinancialIncomes4,txtOtherIncomes4,txtTotalIncomes4,txtCashSales5,txtCreditSales5,txtFinancialIncomes5,txtOtherIncomes5,txtTotalIncomes5,
            txtCashSales6,txtCreditSales6,txtFinancialIncomes6,txtOtherIncomes6,txtTotalIncomes6,txtCashSales7,txtCreditSales7,txtFinancialIncomes7,txtOtherIncomes7,txtTotalIncomes7,txtCashSales8,txtCreditSales8,txtFinancialIncomes8,txtOtherIncomes8,txtTotalIncomes8,
            txtCashSales9,txtCreditSales9,txtFinancialIncomes9,txtOtherIncomes9,txtTotalIncomes9,txtCashSales10,txtCreditSales10,txtFinancialIncomes10,txtOtherIncomes10,txtTotalIncomes10,txtCashSales11,txtCreditSales11,txtFinancialIncomes11,txtOtherIncomes11,txtTotalIncomes11,
            txtCashSales12,txtCreditSales12,txtFinancialIncomes12,txtOtherIncomes12,txtTotalIncomes12,txtWorkerPayment3,txtPurchases3,txtIgv3,txtFinancialOutcomes3,txtOtherOutcomes3,txtTotalOutcomes3,txtWorkerPayment4,txtPurchases4,txtIgv4,txtFinancialOutcomes4,txtOtherOutcomes4,txtTotalOutcomes4,
            txtWorkerPayment5,txtPurchases5,txtIgv5,txtFinancialOutcomes5,txtOtherOutcomes5,txtTotalOutcomes5,txtWorkerPayment6,txtPurchases6,txtIgv6,txtFinancialOutcomes6,txtOtherOutcomes6,txtTotalOutcomes6,txtWorkerPayment7,txtPurchases7,txtIgv7,txtFinancialOutcomes7,txtOtherOutcomes7,txtTotalOutcomes7,
            txtWorkerPayment8,txtPurchases8,txtIgv8,txtFinancialOutcomes8,txtOtherOutcomes8,txtTotalOutcomes8,txtWorkerPayment9,txtPurchases9,txtIgv9,txtFinancialOutcomes9,txtOtherOutcomes9,txtTotalOutcomes9,txtWorkerPayment10,txtPurchases10,txtIgv10,txtFinancialOutcomes10,txtOtherOutcomes10,txtTotalOutcomes10,
            txtWorkerPayment11,txtPurchases11,txtIgv11,txtFinancialOutcomes11,txtOtherOutcomes11,txtTotalOutcomes11,txtWorkerPayment12,txtPurchases12,txtIgv12,txtFinancialOutcomes12,txtOtherOutcomes12,txtTotalOutcomes12,txtBudgetPeriod;
    int day,month,year,last_year,before_last_year,next_year;
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
    DecimalFormat decimalFormat;
    DatabaseReference companyRef;
    String post_key;
    String cash_flow_1,cash_flow_0,cash_incomes_1_st,credit_incomes_1_st,purchase_total_1_st,igv_taxes_1_st,cash_incomes_2_st,credit_incomes_2_st,purchase_total_2_st,igv_taxes_2_st,cash_incomes_3_st,cash_incomes_4_st,cash_incomes_5_st,cash_incomes_6_st,
            cash_incomes_7_st,cash_incomes_8_st,cash_incomes_9_st,cash_incomes_10_st,cash_incomes_11_st,cash_incomes_12_st,credit_incomes_3_st,credit_incomes_4_st,credit_incomes_5_st,credit_incomes_6_st,credit_incomes_7_st,credit_incomes_8_st,credit_incomes_9_st,
            credit_incomes_10_st,credit_incomes_11_st,credit_incomes_12_st,financial_incomes_1_st,financial_incomes_2_st,financial_incomes_3_st,financial_incomes_4_st,financial_incomes_5_st,financial_incomes_6_st,financial_incomes_7_st,financial_incomes_8_st,financial_incomes_9_st,
            financial_incomes_10_st,financial_incomes_11_st,financial_incomes_12_st,other_incomes_1_st,other_incomes_2_st,other_incomes_3_st,other_incomes_4_st,other_incomes_5_st,other_incomes_6_st,other_incomes_7_st,other_incomes_8_st,other_incomes_9_st,
            other_incomes_10_st,other_incomes_11_st,other_incomes_12_st,purchase_total_3_st,purchase_total_4_st,purchase_total_5_st,purchase_total_6_st,purchase_total_7_st,purchase_total_8_st,purchase_total_9_st,purchase_total_10_st,purchase_total_11_st,
            purchase_total_12_st,igv_taxes_3_st,igv_taxes_4_st,igv_taxes_5_st,igv_taxes_6_st,igv_taxes_7_st,igv_taxes_8_st,igv_taxes_9_st,igv_taxes_10_st,igv_taxes_11_st,igv_taxes_12_st,total_salary_1_st,total_salary_2_st,total_salary_3_st,total_salary_4_st,total_salary_5_st,total_salary_6_st,
            total_salary_7_st,total_salary_8_st,total_salary_9_st,total_salary_10_st,total_salary_11_st,total_salary_12_st,financial_outcomes_1_st,financial_outcomes_2_st,financial_outcomes_3_st,financial_outcomes_4_st,financial_outcomes_5_st,financial_outcomes_6_st,
            financial_outcomes_7_st,financial_outcomes_8_st,financial_outcomes_9_st,financial_outcomes_10_st,financial_outcomes_11_st,financial_outcomes_12_st,other_outcomes_1_st,other_outcomes_2_st,
            other_outcomes_3_st,other_outcomes_4_st,other_outcomes_5_st,other_outcomes_6_st,other_outcomes_7_st,other_outcomes_8_st,other_outcomes_9_st,other_outcomes_10_st,other_outcomes_11_st,other_outcomes_12_st;

    TextView txtIndexName1,txtIndexName2,txtIndexName3,txtIndexFormula1,txtIndexFormula2,txtIndexFormula3,txtIndexResult1,txtIndexResult2,txtIndexResult3;
    ImageView btnIndex1,btnIndex2,btnIndex3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_financial_plan);

        txtCashSales1 = findViewById(R.id.txtCashSales1);
        txtCreditSales1 = findViewById(R.id.txtCreditSales1);
        txtFinancialIncomes1 = findViewById(R.id.txtFinancialIncomes1);
        txtOtherIncomes1 = findViewById(R.id.txtOtherIncomes1);
        txtTotalIncomes1 = findViewById(R.id.txtTotalIncomes1);
        txtCashSales2 = findViewById(R.id.txtCashSales2);
        txtCreditSales2 =findViewById(R.id.txtCreditSales2);
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

        txtIndexName1 = findViewById(R.id.txtIndexName1);
        txtIndexName2 = findViewById(R.id.txtIndexName2);
        txtIndexName3 = findViewById(R.id.txtIndexName3);

        txtIndexFormula1 = findViewById(R.id.txtIndexFormula1);
        txtIndexFormula2 = findViewById(R.id.txtIndexFormula2);
        txtIndexFormula3 = findViewById(R.id.txtIndexFormula3);

        txtIndexResult1 = findViewById(R.id.txtIndexResult1);
        txtIndexResult2 = findViewById(R.id.txtIndexResult2);
        txtIndexResult3 = findViewById(R.id.txtIndexResult3);

        btnIndex1 = findViewById(R.id.btnIndex1);
        btnIndex2 = findViewById(R.id.btnIndex2);
        btnIndex3 = findViewById(R.id.btnIndex3);

        post_key = getIntent().getExtras().getString("post_key");
        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");

        txtBudgetPeriod = findViewById(R.id.txtBudgetPeriod);

        decimalFormat = new DecimalFormat("0.00");

        Date date= new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        day = cal.get(Calendar.DAY_OF_MONTH);
        month = cal.get(Calendar.MONTH)+1;
        year = cal.get(Calendar.YEAR);
        last_year = year-1;
        before_last_year = last_year-1;
        next_year = year+1;

        txtBudgetPeriod.setText("Proyección de Flujos para: "+next_year);

        txtCashSales1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account_name = "Ventas al contado";
                String month = "Enero";
                String data_name = "Cash Sales "+next_year;
                String path = "1";
                TextView textView = txtCashSales1;
                showSetValueDialog(account_name,month,textView,path,data_name);
            }
        });
        txtCashSales2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account_name = "Ventas al contado";
                String month = "Febrero";
                String data_name = "Cash Sales "+next_year;
                String path = "2";
                TextView textView = txtCashSales2;
                showSetValueDialog(account_name,month,textView,path,data_name);
            }
        });
        txtCashSales3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account_name = "Ventas al contado";
                String month = "Marzo";
                String data_name = "Cash Sales "+next_year;
                String path = "3";
                TextView textView = txtCashSales3;
                showSetValueDialog(account_name,month,textView,path,data_name);
            }
        });
        txtCashSales4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account_name = "Ventas al contado";
                String month = "Abril";
                String data_name = "Cash Sales "+next_year;
                String path = "4";
                TextView textView = txtCashSales4;
                showSetValueDialog(account_name,month,textView,path,data_name);
            }
        });
        txtCashSales5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account_name = "Ventas al contado";
                String month = "Mayo";
                String data_name = "Cash Sales "+next_year;
                String path = "5";
                TextView textView = txtCashSales5;
                showSetValueDialog(account_name,month,textView,path,data_name);
            }
        });
        txtCashSales6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account_name = "Ventas al contado";
                String month = "Junio";
                String data_name = "Cash Sales "+next_year;
                String path = "6";
                TextView textView = txtCashSales6;
                showSetValueDialog(account_name,month,textView,path,data_name);
            }
        });
        txtCashSales7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account_name = "Ventas al contado";
                String month = "Julio";
                String data_name = "Cash Sales "+next_year;
                String path = "7";
                TextView textView = txtCashSales7;
                showSetValueDialog(account_name,month,textView,path,data_name);
            }
        });
        txtCashSales8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account_name = "Ventas al contado";
                String month = "Agosto";
                String data_name = "Cash Sales "+next_year;
                String path = "8";
                TextView textView = txtCashSales8;
                showSetValueDialog(account_name,month,textView,path,data_name);
            }
        });
        txtCashSales9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account_name = "Ventas al contado";
                String month = "Septiembre";
                String data_name = "Cash Sales "+next_year;
                String path = "9";
                TextView textView = txtCashSales9;
                showSetValueDialog(account_name,month,textView,path,data_name);
            }
        });
        txtCashSales10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account_name = "Ventas al contado";
                String month = "Octubre";
                String data_name = "Cash Sales "+next_year;
                String path = "10";
                TextView textView = txtCashSales10;
                showSetValueDialog(account_name,month,textView,path,data_name);
            }
        });
        txtCashSales11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account_name = "Ventas al contado";
                String month = "Noviembre";
                String data_name = "Cash Sales "+next_year;
                String path = "11";
                TextView textView = txtCashSales11;
                showSetValueDialog(account_name,month,textView,path,data_name);
            }
        });
        txtCashSales12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account_name = "Ventas al contado";
                String month = "Diciembre";
                String data_name = "Cash Sales "+next_year;
                String path = "12";
                TextView textView = txtCashSales12;
                showSetValueDialog(account_name,month,textView,path,data_name);
            }
        });

        txtCreditSales1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account_name = "Ventas al crédito";
                String month = "Enero";
                String data_name = "Credit Sales "+next_year;
                String path = "1";
                TextView textView = txtCreditSales1;
                showSetValueDialog(account_name,month,textView,path,data_name);
            }
        });
        txtCreditSales2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account_name = "Ventas al crédito";
                String month = "Febrero";
                String data_name = "Credit Sales "+next_year;
                String path = "2";
                TextView textView = txtCreditSales2;
                showSetValueDialog(account_name,month,textView,path,data_name);
            }
        });
        txtCreditSales3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account_name = "Ventas al crédito";
                String month = "Marzo";
                String data_name = "Credit Sales "+next_year;
                String path = "3";
                TextView textView = txtCreditSales3;
                showSetValueDialog(account_name,month,textView,path,data_name);
            }
        });
        txtCreditSales4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account_name = "Ventas al crédito";
                String month = "Abril";
                String data_name = "Credit Sales "+next_year;
                String path = "4";
                TextView textView = txtCreditSales4;
                showSetValueDialog(account_name,month,textView,path,data_name);
            }
        });
        txtCreditSales5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account_name = "Ventas al crédito";
                String month = "Mayo";
                String data_name = "Credit Sales "+next_year;
                String path = "5";
                TextView textView = txtCreditSales5;
                showSetValueDialog(account_name,month,textView,path,data_name);
            }
        });
        txtCreditSales6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account_name = "Ventas al crédito";
                String month = "Junio";
                String data_name = "Credit Sales "+next_year;
                String path = "6";
                TextView textView = txtCreditSales6;
                showSetValueDialog(account_name,month,textView,path,data_name);
            }
        });
        txtCreditSales7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account_name = "Ventas al crédito";
                String month = "Julio";
                String data_name = "Credit Sales "+next_year;
                String path = "7";
                TextView textView = txtCreditSales7;
                showSetValueDialog(account_name,month,textView,path,data_name);
            }
        });
        txtCreditSales8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account_name = "Ventas al crédito";
                String month = "Agosto";
                String data_name = "Credit Sales "+next_year;
                String path = "8";
                TextView textView = txtCreditSales8;
                showSetValueDialog(account_name,month,textView,path,data_name);
            }
        });
        txtCreditSales9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account_name = "Ventas al crédito";
                String month = "Septiembre";
                String data_name = "Credit Sales "+next_year;
                String path = "9";
                TextView textView = txtCreditSales9;
                showSetValueDialog(account_name,month,textView,path,data_name);
            }
        });
        txtCreditSales10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account_name = "Ventas al crédito";
                String month = "Octubre";
                String data_name = "Credit Sales "+next_year;
                String path = "10";
                TextView textView = txtCreditSales10;
                showSetValueDialog(account_name,month,textView,path,data_name);
            }
        });
        txtCreditSales11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account_name = "Ventas al crédito";
                String month = "Noviembre";
                String data_name = "Credit Sales "+next_year;
                String path = "11";
                TextView textView = txtCreditSales11;
                showSetValueDialog(account_name,month,textView,path,data_name);
            }
        });
        txtCreditSales12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account_name = "Ventas al crédito";
                String month = "Diciembre";
                String data_name = "Credit Sales "+next_year;
                String path = "12";
                TextView textView = txtCreditSales12;
                showSetValueDialog(account_name,month,textView,path,data_name);
            }
        });

        txtFinancialIncomes1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account_name = "Ingresos Financieros";
                String month = "Enero";
                String data_name = "Financial Incomes "+next_year;
                String path = "1";
                TextView textView = txtFinancialIncomes1;
                showSetValueDialog(account_name,month,textView,path,data_name);
            }
        });
        txtFinancialIncomes2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account_name = "Ingresos Financieros";
                String month = "Febrero";
                String data_name = "Financial Incomes "+next_year;
                String path = "2";
                TextView textView = txtFinancialIncomes2;
                showSetValueDialog(account_name,month,textView,path,data_name);
            }
        });
        txtFinancialIncomes3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account_name = "Ingresos Financieros";
                String month = "Marzo";
                String data_name = "Financial Incomes "+next_year;
                String path = "3";
                TextView textView = txtFinancialIncomes3;
                showSetValueDialog(account_name,month,textView,path,data_name);
            }
        });
        txtFinancialIncomes4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account_name = "Ingresos Financieros";
                String month = "Abril";
                String data_name = "Financial Incomes "+next_year;
                String path = "4";
                TextView textView = txtFinancialIncomes4;
                showSetValueDialog(account_name,month,textView,path,data_name);
            }
        });
        txtFinancialIncomes5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account_name = "Ingresos Financieros";
                String month = "Mayo";
                String data_name = "Financial Incomes "+next_year;
                String path = "5";
                TextView textView = txtFinancialIncomes5;
                showSetValueDialog(account_name,month,textView,path,data_name);
            }
        });
        txtFinancialIncomes6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account_name = "Ingresos Financieros";
                String month = "Junio";
                String data_name = "Financial Incomes "+next_year;
                String path = "6";
                TextView textView = txtFinancialIncomes6;
                showSetValueDialog(account_name,month,textView,path,data_name);
            }
        });
        txtFinancialIncomes7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account_name = "Ingresos Financieros";
                String month = "Julio";
                String data_name = "Financial Incomes "+next_year;
                String path = "7";
                TextView textView = txtFinancialIncomes7;
                showSetValueDialog(account_name,month,textView,path,data_name);
            }
        });
        txtFinancialIncomes8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account_name = "Ingresos Financieros";
                String month = "Agosto";
                String data_name = "Financial Incomes "+next_year;
                String path = "8";
                TextView textView = txtFinancialIncomes8;
                showSetValueDialog(account_name,month,textView,path,data_name);
            }
        });
        txtFinancialIncomes9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account_name = "Ingresos Financieros";
                String month = "Septiembre";
                String data_name = "Financial Incomes "+next_year;
                String path = "9";
                TextView textView = txtFinancialIncomes9;
                showSetValueDialog(account_name,month,textView,path,data_name);
            }
        });
        txtFinancialIncomes10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account_name = "Ingresos Financieros";
                String month = "Octubre";
                String data_name = "Financial Incomes "+next_year;
                String path = "10";
                TextView textView = txtFinancialIncomes10;
                showSetValueDialog(account_name,month,textView,path,data_name);
            }
        });
        txtFinancialIncomes11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account_name = "Ingresos Financieros";
                String month = "Noviembre";
                String data_name = "Financial Incomes "+next_year;
                String path = "11";
                TextView textView = txtFinancialIncomes11;
                showSetValueDialog(account_name,month,textView,path,data_name);
            }
        });
        txtFinancialIncomes12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account_name = "Ingresos Financieros";
                String month = "Diciembre";
                String data_name = "Financial Incomes "+next_year;
                String path = "12";
                TextView textView = txtFinancialIncomes12;
                showSetValueDialog(account_name,month,textView,path,data_name);
            }
        });

        txtOtherIncomes1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account_name = "Otros Ingresos";
                String month = "Enero";
                String data_name = "Other Incomes "+next_year;
                String path = "1";
                TextView textView = txtOtherIncomes1;
                showSetValueDialog(account_name,month,textView,path,data_name);
            }
        });
        txtOtherIncomes2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account_name = "Otros Ingresos";
                String month = "Febrero";
                String data_name = "Other Incomes "+next_year;
                String path = "2";
                TextView textView = txtOtherIncomes2;
                showSetValueDialog(account_name,month,textView,path,data_name);
            }
        });
        txtOtherIncomes3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account_name = "Otros Ingresos";
                String month = "Marzo";
                String data_name = "Other Incomes "+next_year;
                String path = "3";
                TextView textView = txtOtherIncomes3;
                showSetValueDialog(account_name,month,textView,path,data_name);
            }
        });
        txtOtherIncomes4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account_name = "Otros Ingresos";
                String month = "Abril";
                String data_name = "Other Incomes "+next_year;
                String path = "4";
                TextView textView = txtOtherIncomes4;
                showSetValueDialog(account_name,month,textView,path,data_name);
            }
        });
        txtOtherIncomes5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account_name = "Otros Ingresos";
                String month = "Mayo";
                String data_name = "Other Incomes "+next_year;
                String path = "5";
                TextView textView = txtOtherIncomes5;
                showSetValueDialog(account_name,month,textView,path,data_name);
            }
        });
        txtOtherIncomes6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account_name = "Otros Ingresos";
                String month = "Junio";
                String data_name = "Other Incomes "+next_year;
                String path = "6";
                TextView textView = txtOtherIncomes6;
                showSetValueDialog(account_name,month,textView,path,data_name);
            }
        });
        txtOtherIncomes7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account_name = "Otros Ingresos";
                String month = "Julio";
                String data_name = "Other Incomes "+next_year;
                String path = "7";
                TextView textView = txtOtherIncomes7;
                showSetValueDialog(account_name,month,textView,path,data_name);
            }
        });
        txtOtherIncomes8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account_name = "Otros Ingresos";
                String month = "Agosto";
                String data_name = "Other Incomes "+next_year;
                String path = "8";
                TextView textView = txtOtherIncomes8;
                showSetValueDialog(account_name,month,textView,path,data_name);
            }
        });
        txtOtherIncomes9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account_name = "Otros Ingresos";
                String month = "Septiembre";
                String data_name = "Other Incomes "+next_year;
                String path = "9";
                TextView textView = txtOtherIncomes9;
                showSetValueDialog(account_name,month,textView,path,data_name);
            }
        });
        txtOtherIncomes10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account_name = "Otros Ingresos";
                String month = "Octubre";
                String data_name = "Other Incomes "+next_year;
                String path = "10";
                TextView textView = txtOtherIncomes10;
                showSetValueDialog(account_name,month,textView,path,data_name);
            }
        });
        txtOtherIncomes11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account_name = "Otros Ingresos";
                String month = "Noviembre";
                String data_name = "Other Incomes "+next_year;
                String path = "11";
                TextView textView = txtOtherIncomes11;
                showSetValueDialog(account_name,month,textView,path,data_name);
            }
        });
        txtOtherIncomes12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account_name = "Otros Ingresos";
                String month = "Diciembre";
                String data_name = "Other Incomes "+next_year;
                String path = "12";
                TextView textView = txtOtherIncomes12;
                showSetValueDialog(account_name,month,textView,path,data_name);
            }
        });


        //OUTCOMES

        txtWorkerPayment1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account_name = "Pago de Reumuneraciones";
                String month = "Enero";
                String data_name = "Worker Payments "+next_year;
                String path = "1";
                TextView textView = txtWorkerPayment1;
                showSetValueDialog(account_name,month,textView,path,data_name);
            }
        });
        txtWorkerPayment2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account_name = "Pago de Reumuneraciones";
                String month = "Febrero";
                String data_name = "Worker Payments "+next_year;
                String path = "2";
                TextView textView = txtWorkerPayment2;
                showSetValueDialog(account_name,month,textView,path,data_name);
            }
        });
        txtWorkerPayment3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account_name = "Pago de Reumuneraciones";
                String month = "Marzo";
                String data_name = "Worker Payments "+next_year;
                String path = "3";
                TextView textView = txtWorkerPayment3;
                showSetValueDialog(account_name,month,textView,path,data_name);
            }
        });
        txtWorkerPayment4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account_name = "Pago de Reumuneraciones";
                String month = "Abril";
                String data_name = "Worker Payments "+next_year;
                String path = "4";
                TextView textView = txtWorkerPayment4;
                showSetValueDialog(account_name,month,textView,path,data_name);
            }
        });
        txtWorkerPayment5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account_name = "Pago de Reumuneraciones";
                String month = "Mayo";
                String data_name = "Worker Payments "+next_year;
                String path = "5";
                TextView textView = txtWorkerPayment5;
                showSetValueDialog(account_name,month,textView,path,data_name);
            }
        });
        txtWorkerPayment6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account_name = "Pago de Reumuneraciones";
                String month = "Junio";
                String data_name = "Worker Payments "+next_year;
                String path = "6";
                TextView textView = txtWorkerPayment6;
                showSetValueDialog(account_name,month,textView,path,data_name);
            }
        });
        txtWorkerPayment7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account_name = "Pago de Reumuneraciones";
                String month = "Julio";
                String data_name = "Worker Payments "+next_year;
                String path = "7";
                TextView textView = txtWorkerPayment7;
                showSetValueDialog(account_name,month,textView,path,data_name);
            }
        });
        txtWorkerPayment8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account_name = "Pago de Reumuneraciones";
                String month = "Agosto";
                String data_name = "Worker Payments "+next_year;
                String path = "8";
                TextView textView = txtWorkerPayment8;
                showSetValueDialog(account_name,month,textView,path,data_name);
            }
        });
        txtWorkerPayment9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account_name = "Pago de Reumuneraciones";
                String month = "Septiembre";
                String data_name = "Worker Payments "+next_year;
                String path = "9";
                TextView textView = txtWorkerPayment9;
                showSetValueDialog(account_name,month,textView,path,data_name);
            }
        });
        txtWorkerPayment10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account_name = "Pago de Reumuneraciones";
                String month = "Octubre";
                String data_name = "Worker Payments "+next_year;
                String path = "10";
                TextView textView = txtWorkerPayment10;
                showSetValueDialog(account_name,month,textView,path,data_name);
            }
        });
        txtWorkerPayment11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account_name = "Pago de Reumuneraciones";
                String month = "Noviembre";
                String data_name = "Worker Payments "+next_year;
                String path = "11";
                TextView textView = txtWorkerPayment11;
                showSetValueDialog(account_name,month,textView,path,data_name);
            }
        });
        txtWorkerPayment12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account_name = "Pago de Reumuneraciones";
                String month = "Diciembre";
                String data_name = "Worker Payments "+next_year;
                String path = "12";
                TextView textView = txtWorkerPayment12;
                showSetValueDialog(account_name,month,textView,path,data_name);
            }
        });


        txtIgv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account_name = "Pago de IGV";
                String month = "Enero";
                String data_name = "IGV Payments "+next_year;
                String path = "1";
                TextView textView = txtIgv1;
                showSetValueDialog(account_name,month,textView,path,data_name);
            }
        });
        txtIgv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account_name = "Pago de IGV";
                String month = "Febrero";
                String data_name = "IGV Payments "+next_year;
                String path = "2";
                TextView textView = txtIgv2;
                showSetValueDialog(account_name,month,textView,path,data_name);
            }
        });
        txtIgv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account_name = "Pago de IGV";
                String month = "Marzo";
                String data_name = "IGV Payments "+next_year;
                String path = "3";
                TextView textView = txtIgv3;
                showSetValueDialog(account_name,month,textView,path,data_name);
            }
        });
        txtIgv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account_name = "Pago de IGV";
                String month = "Abril";
                String data_name = "IGV Payments "+next_year;
                String path = "4";
                TextView textView = txtIgv4;
                showSetValueDialog(account_name,month,textView,path,data_name);
            }
        });
        txtIgv5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account_name = "Pago de IGV";
                String month = "Mayo";
                String data_name = "IGV Payments "+next_year;
                String path = "5";
                TextView textView = txtIgv5;
                showSetValueDialog(account_name,month,textView,path,data_name);
            }
        });
        txtIgv6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account_name = "Pago de IGV";
                String month = "Junio";
                String data_name = "IGV Payments "+next_year;
                String path = "6";
                TextView textView = txtIgv6;
                showSetValueDialog(account_name,month,textView,path,data_name);
            }
        });
        txtIgv7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account_name = "Pago de IGV";
                String month = "Julio";
                String data_name = "Worker Payments "+next_year;
                String path = "7";
                TextView textView = txtIgv7;
                showSetValueDialog(account_name,month,textView,path,data_name);
            }
        });
        txtIgv8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account_name = "Pago de IGV";
                String month = "Agosto";
                String data_name = "IGV Payments "+next_year;
                String path = "8";
                TextView textView = txtIgv8;
                showSetValueDialog(account_name,month,textView,path,data_name);
            }
        });
        txtIgv9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account_name = "Pago de IGV";
                String month = "Septiembre";
                String data_name = "IGV Payments "+next_year;
                String path = "9";
                TextView textView = txtIgv9;
                showSetValueDialog(account_name,month,textView,path,data_name);
            }
        });
        txtIgv10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account_name = "Pago de IGV";
                String month = "Octubre";
                String data_name = "IGV Payments "+next_year;
                String path = "10";
                TextView textView = txtIgv10;
                showSetValueDialog(account_name,month,textView,path,data_name);
            }
        });
        txtIgv11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account_name = "Pago de IGV";
                String month = "Noviembre";
                String data_name = "IGV Payments "+next_year;
                String path = "11";
                TextView textView = txtIgv11;
                showSetValueDialog(account_name,month,textView,path,data_name);
            }
        });
        txtIgv12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account_name = "Pago de IGV";
                String month = "Diciembre";
                String data_name = "IGV Payments "+next_year;
                String path = "12";
                TextView textView = txtIgv12;
                showSetValueDialog(account_name,month,textView,path,data_name);
            }
        });

        txtPurchases1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account_name = "Pago a Proveedores";
                String month = "Enero";
                String data_name = "Purchases "+next_year;
                String path = "1";
                TextView textView = txtPurchases1;
                showSetValueDialog(account_name,month,textView,path,data_name);
            }
        });
        txtPurchases2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account_name = "Pago a Proveedores";
                String month = "Febrero";
                String data_name = "Purchases "+next_year;
                String path = "2";
                TextView textView = txtPurchases2;
                showSetValueDialog(account_name,month,textView,path,data_name);
            }
        });
        txtPurchases3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account_name = "Pago a Proveedores";
                String month = "Marzo";
                String data_name = "Purchases "+next_year;
                String path = "3";
                TextView textView = txtPurchases3;
                showSetValueDialog(account_name,month,textView,path,data_name);
            }
        });
        txtPurchases4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account_name = "Pago a Proveedores";
                String month = "Abril";
                String data_name = "Purchases "+next_year;
                String path = "4";
                TextView textView = txtPurchases4;
                showSetValueDialog(account_name,month,textView,path,data_name);
            }
        });
        txtPurchases5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account_name = "Pago a Proveedores";
                String month = "Mayo";
                String data_name = "Purchases "+next_year;
                String path = "5";
                TextView textView = txtPurchases5;
                showSetValueDialog(account_name,month,textView,path,data_name);
            }
        });
        txtPurchases6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account_name = "Pago a Proveedores";
                String month = "Junio";
                String data_name = "Purchases "+next_year;
                String path = "6";
                TextView textView = txtPurchases6;
                showSetValueDialog(account_name,month,textView,path,data_name);
            }
        });
        txtPurchases7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account_name = "Pago a Proveedores";
                String month = "Julio";
                String data_name = "Purchases "+next_year;
                String path = "7";
                TextView textView = txtPurchases7;
                showSetValueDialog(account_name,month,textView,path,data_name);
            }
        });
        txtPurchases8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account_name = "Pago a Proveedores";
                String month = "Agosto";
                String data_name = "Purchases "+next_year;
                String path = "8";
                TextView textView = txtPurchases8;
                showSetValueDialog(account_name,month,textView,path,data_name);
            }
        });
        txtPurchases9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account_name = "Pago de IGV";
                String month = "Septiembre";
                String data_name = "Purchases "+next_year;
                String path = "9";
                TextView textView = txtPurchases9;
                showSetValueDialog(account_name,month,textView,path,data_name);
            }
        });
        txtPurchases10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account_name = "Pago a Proveedores";
                String month = "Octubre";
                String data_name = "Purchases "+next_year;
                String path = "10";
                TextView textView = txtPurchases10;
                showSetValueDialog(account_name,month,textView,path,data_name);
            }
        });
        txtPurchases11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account_name = "Pago a Proveedores";
                String month = "Noviembre";
                String data_name = "Purchases "+next_year;
                String path = "11";
                TextView textView = txtPurchases11;
                showSetValueDialog(account_name,month,textView,path,data_name);
            }
        });
        txtPurchases12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account_name = "Pago a Proveedores";
                String month = "Diciembre";
                String data_name = "Purchases "+next_year;
                String path = "12";
                TextView textView = txtPurchases12;
                showSetValueDialog(account_name,month,textView,path,data_name);
            }
        });

        txtFinancialOutcomes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account_name = "Egresos Financieros";
                String month = "Enero";
                String data_name = "Financial Outcomes "+next_year;
                String path = "1";
                TextView textView = txtFinancialOutcomes;
                showSetValueDialog(account_name,month,textView,path,data_name);
            }
        });
        txtFinancialOutcomes2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account_name = "Egresos Financieros";
                String month = "Febrero";
                String data_name = "Financial Outcomes "+next_year;
                String path = "2";
                TextView textView = txtFinancialOutcomes2;
                showSetValueDialog(account_name,month,textView,path,data_name);
            }
        });
        txtFinancialOutcomes3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account_name = "Egresos Financieros";
                String month = "Marzo";
                String data_name = "Financial Outcomes "+next_year;
                String path = "3";
                TextView textView = txtFinancialOutcomes3;
                showSetValueDialog(account_name,month,textView,path,data_name);
            }
        });
        txtFinancialOutcomes4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account_name = "Egresos Financieros";
                String month = "Abril";
                String data_name = "Financial Outcomes "+next_year;
                String path = "4";
                TextView textView = txtFinancialOutcomes4;
                showSetValueDialog(account_name,month,textView,path,data_name);
            }
        });
        txtFinancialOutcomes5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account_name = "Egresos Financieros";
                String month = "Mayo";
                String data_name = "Financial Outcomes "+next_year;
                String path = "5";
                TextView textView = txtFinancialOutcomes5;
                showSetValueDialog(account_name,month,textView,path,data_name);
            }
        });
        txtFinancialOutcomes6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account_name = "Egresos Financieros";
                String month = "Junio";
                String data_name = "Financial Outcomes "+next_year;
                String path = "6";
                TextView textView = txtFinancialOutcomes6;
                showSetValueDialog(account_name,month,textView,path,data_name);
            }
        });
        txtFinancialOutcomes7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account_name = "Egresos Financieros";
                String month = "Julio";
                String data_name = "Financial Outcomes "+next_year;
                String path = "7";
                TextView textView = txtFinancialOutcomes7;
                showSetValueDialog(account_name,month,textView,path,data_name);
            }
        });
        txtFinancialOutcomes8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account_name = "Egresos Financieros";
                String month = "Agosto";
                String data_name = "Financial Outcomes "+next_year;
                String path = "8";
                TextView textView = txtFinancialOutcomes8;
                showSetValueDialog(account_name,month,textView,path,data_name);
            }
        });
        txtFinancialOutcomes9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account_name = "Egresos Financieros";
                String month = "Septiembre";
                String data_name = "Financial Outcomes "+next_year;
                String path = "9";
                TextView textView = txtFinancialOutcomes9;
                showSetValueDialog(account_name,month,textView,path,data_name);
            }
        });
        txtFinancialOutcomes10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account_name = "Egresos Financieros";
                String month = "Octubre";
                String data_name = "Financial Outcomes "+next_year;
                String path = "10";
                TextView textView = txtFinancialOutcomes10;
                showSetValueDialog(account_name,month,textView,path,data_name);
            }
        });
        txtFinancialOutcomes11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account_name = "Egresos Financieros";
                String month = "Noviembre";
                String data_name = "Financial Outcomes "+next_year;
                String path = "11";
                TextView textView = txtFinancialOutcomes11;
                showSetValueDialog(account_name,month,textView,path,data_name);
            }
        });
        txtFinancialOutcomes12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account_name = "Egresos Financieros";
                String month = "Diciembre";
                String data_name = "Financial Outcomes "+next_year;
                String path = "12";
                TextView textView = txtFinancialOutcomes12;
                showSetValueDialog(account_name,month,textView,path,data_name);
            }
        });


        txtOtherOutcomes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account_name = "Otros Egresos";
                String month = "Enero";
                String data_name = "Other Outcomes "+next_year;
                String path = "1";
                TextView textView = txtOtherOutcomes;
                showSetValueDialog(account_name,month,textView,path,data_name);
            }
        });
        txtOtherOutcomes2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account_name = "Otros Egresos";
                String month = "Febrero";
                String data_name = "Other Outcomes "+next_year;
                String path = "2";
                TextView textView = txtOtherOutcomes2;
                showSetValueDialog(account_name,month,textView,path,data_name);
            }
        });
        txtOtherOutcomes3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account_name = "Otros Egresos";
                String month = "Marzo";
                String data_name = "Other Outcomes "+next_year;
                String path = "3";
                TextView textView = txtOtherOutcomes3;
                showSetValueDialog(account_name,month,textView,path,data_name);
            }
        });
        txtOtherOutcomes4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account_name = "Otros Egresos";
                String month = "Abril";
                String data_name = "Other Outcomes "+next_year;
                String path = "4";
                TextView textView = txtOtherOutcomes4;
                showSetValueDialog(account_name,month,textView,path,data_name);
            }
        });
        txtOtherOutcomes5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account_name = "Otros Egresos";
                String month = "Mayo";
                String data_name = "Other Outcomes "+next_year;
                String path = "5";
                TextView textView = txtOtherOutcomes5;
                showSetValueDialog(account_name,month,textView,path,data_name);
            }
        });
        txtOtherOutcomes6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account_name = "Otros Egresos";
                String month = "Junio";
                String data_name = "Other Outcomes "+next_year;
                String path = "6";
                TextView textView = txtOtherOutcomes6;
                showSetValueDialog(account_name,month,textView,path,data_name);
            }
        });
        txtOtherOutcomes7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account_name = "Otros Egresos";
                String month = "Julio";
                String data_name = "Other Outcomes "+next_year;
                String path = "7";
                TextView textView = txtOtherOutcomes7;
                showSetValueDialog(account_name,month,textView,path,data_name);
            }
        });
        txtOtherOutcomes8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account_name = "Otros Egresos";
                String month = "Agosto";
                String data_name = "Other Outcomes "+next_year;
                String path = "8";
                TextView textView = txtOtherOutcomes8;
                showSetValueDialog(account_name,month,textView,path,data_name);
            }
        });
        txtOtherOutcomes9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account_name = "Otros Egresos";
                String month = "Septiembre";
                String data_name = "Other Outcomes "+next_year;
                String path = "9";
                TextView textView = txtOtherOutcomes9;
                showSetValueDialog(account_name,month,textView,path,data_name);
            }
        });
        txtOtherOutcomes10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account_name = "Otros Egresos";
                String month = "Octubre";
                String data_name = "Other Outcomes "+next_year;
                String path = "10";
                TextView textView = txtOtherOutcomes10;
                showSetValueDialog(account_name,month,textView,path,data_name);
            }
        });
        txtOtherOutcomes11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account_name = "Otros Egresos";
                String month = "Noviembre";
                String data_name = "Other Outcomes "+next_year;
                String path = "11";
                TextView textView = txtOtherOutcomes11;
                showSetValueDialog(account_name,month,textView,path,data_name);
            }
        });
        txtOtherOutcomes12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account_name = "Otros Egresos";
                String month = "Diciembre";
                String data_name = "Other Outcomes "+next_year;
                String path = "12";
                TextView textView = txtOtherOutcomes12;
                showSetValueDialog(account_name,month,textView,path,data_name);
            }
        });

        updateData();

    }

    private void updateData() {
        companyRef.child(post_key).child("Budget").child("Cash Sales "+next_year).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild("1")) {
                    cash_incomes_1_st = dataSnapshot.child("1").child("value").getValue().toString();
                    cash_incomes_1 = Double.parseDouble(cash_incomes_1_st);
                    txtCashSales1.setText(cash_incomes_1_st);
                } else {
                    cash_incomes_1 = 0.00;
                }
                if (dataSnapshot.hasChild("2")) {
                    cash_incomes_2_st = dataSnapshot.child("2").child("value").getValue().toString();
                    cash_incomes_2 = Double.parseDouble(cash_incomes_2_st);
                    txtCashSales2.setText(cash_incomes_2_st);
                } else {
                    cash_incomes_2 = 0.00;
                }
                if (dataSnapshot.hasChild("3")) {
                    cash_incomes_3_st = dataSnapshot.child("3").child("value").getValue().toString();
                    cash_incomes_3 = Double.parseDouble(cash_incomes_3_st);
                    txtCashSales3.setText(cash_incomes_3_st);
                } else {
                    cash_incomes_3 = 0.00;
                }
                if (dataSnapshot.hasChild("4")) {
                    cash_incomes_4_st = dataSnapshot.child("4").child("value").getValue().toString();
                    cash_incomes_4 = Double.parseDouble(cash_incomes_4_st);
                    txtCashSales4.setText(cash_incomes_4_st);
                } else {
                    cash_incomes_4 = 0.00;
                }
                if (dataSnapshot.hasChild("5")) {
                    cash_incomes_5_st = dataSnapshot.child("5").child("value").getValue().toString();
                    cash_incomes_5 = Double.parseDouble(cash_incomes_5_st);
                    txtCashSales5.setText(cash_incomes_5_st);
                } else {
                    cash_incomes_5 = 0.00;
                }
                if (dataSnapshot.hasChild("6")) {
                    cash_incomes_6_st = dataSnapshot.child("6").child("value").getValue().toString();
                    cash_incomes_6 = Double.parseDouble(cash_incomes_6_st);
                    txtCashSales6.setText(cash_incomes_6_st);
                } else {
                    cash_incomes_6 = 0.00;
                }
                if (dataSnapshot.hasChild("7")) {
                    cash_incomes_7_st = dataSnapshot.child("7").child("value").getValue().toString();
                    cash_incomes_7 = Double.parseDouble(cash_incomes_7_st);
                    txtCashSales7.setText(cash_incomes_7_st);
                } else {
                    cash_incomes_7 = 0.00;
                }
                if (dataSnapshot.hasChild("8")) {
                    cash_incomes_8_st = dataSnapshot.child("8").child("value").getValue().toString();
                    cash_incomes_8 = Double.parseDouble(cash_incomes_8_st);
                    txtCashSales8.setText(cash_incomes_8_st);
                } else {
                    cash_incomes_8 = 0.00;
                }
                if (dataSnapshot.hasChild("9")) {
                    cash_incomes_9_st = dataSnapshot.child("9").child("value").getValue().toString();
                    cash_incomes_9 = Double.parseDouble(cash_incomes_9_st);
                    txtCashSales9.setText(cash_incomes_9_st);
                } else {
                    cash_incomes_9 = 0.00;
                }
                if (dataSnapshot.hasChild("10")) {
                    cash_incomes_10_st = dataSnapshot.child("10").child("value").getValue().toString();
                    cash_incomes_10 = Double.parseDouble(cash_incomes_10_st);
                    txtCashSales10.setText(cash_incomes_10_st);
                } else {
                    cash_incomes_10 = 0.00;
                }
                if (dataSnapshot.hasChild("11")) {
                    cash_incomes_11_st = dataSnapshot.child("11").child("value").getValue().toString();
                    cash_incomes_11 = Double.parseDouble(cash_incomes_11_st);
                    txtCashSales11.setText(cash_incomes_11_st);
                } else {
                    cash_incomes_11 = 0.00;
                }
                if (dataSnapshot.hasChild("12")) {
                    cash_incomes_12_st = dataSnapshot.child("12").child("value").getValue().toString();
                    cash_incomes_12 = Double.parseDouble(cash_incomes_12_st);
                    txtCashSales12.setText(cash_incomes_12_st);
                } else {
                    cash_incomes_12 = 0.00;
                }


                companyRef.child(post_key).child("Budget").child("Credit Sales "+next_year).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChild("1")) {
                            credit_incomes_1_st = dataSnapshot.child("1").child("value").getValue().toString();
                            credit_incomes_1 = Double.parseDouble(credit_incomes_1_st);
                            txtCreditSales1.setText(credit_incomes_1_st);
                        } else {
                            credit_incomes_1 = 0.00;
                        }
                        if (dataSnapshot.hasChild("2")) {
                            credit_incomes_2_st = dataSnapshot.child("2").child("value").getValue().toString();
                            credit_incomes_2 = Double.parseDouble(credit_incomes_2_st);
                            txtCreditSales2.setText(credit_incomes_2_st);
                        } else {
                            credit_incomes_2 = 0.00;
                        }
                        if (dataSnapshot.hasChild("3")) {
                            credit_incomes_3_st = dataSnapshot.child("3").child("value").getValue().toString();
                            credit_incomes_3 = Double.parseDouble(credit_incomes_3_st);
                            txtCreditSales3.setText(credit_incomes_3_st);
                        } else {
                            credit_incomes_3 = 0.00;
                        }
                        if (dataSnapshot.hasChild("4")) {
                            credit_incomes_4_st = dataSnapshot.child("4").child("value").getValue().toString();
                            credit_incomes_4 = Double.parseDouble(credit_incomes_4_st);
                            txtCreditSales4.setText(credit_incomes_4_st);
                        } else {
                            credit_incomes_4 = 0.00;
                        }
                        if (dataSnapshot.hasChild("5")) {
                            credit_incomes_5_st = dataSnapshot.child("5").child("value").getValue().toString();
                            credit_incomes_5 = Double.parseDouble(credit_incomes_5_st);
                            txtCreditSales5.setText(credit_incomes_5_st);
                        } else {
                            credit_incomes_5 = 0.00;
                        }
                        if (dataSnapshot.hasChild("6")) {
                            credit_incomes_6_st = dataSnapshot.child("6").child("value").getValue().toString();
                            credit_incomes_6 = Double.parseDouble(credit_incomes_6_st);
                            txtCreditSales6.setText(credit_incomes_6_st);
                        } else {
                            credit_incomes_6 = 0.00;
                        }
                        if (dataSnapshot.hasChild("7")) {
                            credit_incomes_7_st = dataSnapshot.child("7").child("value").getValue().toString();
                            credit_incomes_7 = Double.parseDouble(credit_incomes_7_st);
                            txtCreditSales7.setText(credit_incomes_7_st);
                        } else {
                            credit_incomes_7 = 0.00;
                        }
                        if (dataSnapshot.hasChild("8")) {
                            credit_incomes_8_st = dataSnapshot.child("8").child("value").getValue().toString();
                            credit_incomes_8 = Double.parseDouble(credit_incomes_8_st);
                            txtCreditSales8.setText(credit_incomes_8_st);
                        } else {
                            credit_incomes_8 = 0.00;
                        }
                        if (dataSnapshot.hasChild("9")) {
                            credit_incomes_9_st = dataSnapshot.child("9").child("value").getValue().toString();
                            credit_incomes_9 = Double.parseDouble(credit_incomes_9_st);
                            txtCreditSales9.setText(credit_incomes_9_st);
                        } else {
                            credit_incomes_9 = 0.00;
                        }
                        if (dataSnapshot.hasChild("10")) {
                            credit_incomes_10_st = dataSnapshot.child("10").child("value").getValue().toString();
                            credit_incomes_10 = Double.parseDouble(credit_incomes_10_st);
                            txtCreditSales10.setText(credit_incomes_10_st);
                        } else {
                            credit_incomes_10 = 0.00;
                        }
                        if (dataSnapshot.hasChild("11")) {
                            credit_incomes_11_st = dataSnapshot.child("11").child("value").getValue().toString();
                            credit_incomes_11 = Double.parseDouble(credit_incomes_11_st);
                            txtCreditSales11.setText(credit_incomes_11_st);
                        } else {
                            credit_incomes_11 = 0.00;
                        }
                        if (dataSnapshot.hasChild("12")) {
                            credit_incomes_12_st = dataSnapshot.child("12").child("value").getValue().toString();
                            credit_incomes_12 = Double.parseDouble(credit_incomes_12_st);
                            txtCreditSales12.setText(credit_incomes_12_st);
                        } else {
                            credit_incomes_12 = 0.00;
                        }

                        companyRef.child(post_key).child("Budget").child("Financial Incomes "+next_year).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                if (dataSnapshot.hasChild("1")) {
                                    financial_incomes_1_st = dataSnapshot.child("1").child("value").getValue().toString();
                                    financial_incomes_1 = Double.parseDouble(financial_incomes_1_st);
                                    txtFinancialIncomes1.setText(financial_incomes_1_st);
                                } else {
                                    financial_incomes_1 = 0.00;
                                }
                                if (dataSnapshot.hasChild("2")) {
                                    financial_incomes_2_st = dataSnapshot.child("2").child("value").getValue().toString();
                                    financial_incomes_2 = Double.parseDouble(financial_incomes_2_st);
                                    txtFinancialIncomes2.setText(financial_incomes_2_st);
                                } else {
                                    financial_incomes_2 = 0.00;
                                }
                                if (dataSnapshot.hasChild("3")) {
                                    financial_incomes_3_st = dataSnapshot.child("3").child("value").getValue().toString();
                                    financial_incomes_3 = Double.parseDouble(financial_incomes_3_st);
                                    txtFinancialIncomes3.setText(financial_incomes_3_st);
                                } else {
                                    financial_incomes_3 = 0.00;
                                }
                                if (dataSnapshot.hasChild("4")) {
                                    financial_incomes_4_st = dataSnapshot.child("4").child("value").getValue().toString();
                                    financial_incomes_4 = Double.parseDouble(financial_incomes_4_st);
                                    txtFinancialIncomes4.setText(financial_incomes_4_st);
                                } else {
                                    financial_incomes_4 = 0.00;
                                }
                                if (dataSnapshot.hasChild("5")) {
                                    financial_incomes_5_st = dataSnapshot.child("5").child("value").getValue().toString();
                                    financial_incomes_5 = Double.parseDouble(financial_incomes_5_st);
                                    txtFinancialIncomes5.setText(financial_incomes_5_st);
                                } else {
                                    financial_incomes_5 = 0.00;
                                }
                                if (dataSnapshot.hasChild("6")) {
                                    financial_incomes_6_st = dataSnapshot.child("6").child("value").getValue().toString();
                                    financial_incomes_6 = Double.parseDouble(financial_incomes_6_st);
                                    txtFinancialIncomes6.setText(financial_incomes_6_st);
                                } else {
                                    financial_incomes_6 = 0.00;
                                }
                                if (dataSnapshot.hasChild("7")) {
                                    financial_incomes_7_st = dataSnapshot.child("7").child("value").getValue().toString();
                                    financial_incomes_7 = Double.parseDouble(financial_incomes_7_st);
                                    txtFinancialIncomes7.setText(financial_incomes_7_st);
                                } else {
                                    financial_incomes_7 = 0.00;
                                }
                                if (dataSnapshot.hasChild("8")) {
                                    financial_incomes_8_st = dataSnapshot.child("8").child("value").getValue().toString();
                                    financial_incomes_8 = Double.parseDouble(financial_incomes_8_st);
                                    txtFinancialIncomes8.setText(financial_incomes_8_st);
                                } else {
                                    financial_incomes_8 = 0.00;
                                }
                                if (dataSnapshot.hasChild("9")) {
                                    financial_incomes_9_st = dataSnapshot.child("9").child("value").getValue().toString();
                                    financial_incomes_9 = Double.parseDouble(financial_incomes_9_st);
                                    txtFinancialIncomes9.setText(financial_incomes_9_st);
                                } else {
                                    financial_incomes_9 = 0.00;
                                }
                                if (dataSnapshot.hasChild("10")) {
                                    financial_incomes_10_st = dataSnapshot.child("10").child("value").getValue().toString();
                                    financial_incomes_10 = Double.parseDouble(financial_incomes_10_st);
                                    txtFinancialIncomes10.setText(financial_incomes_10_st);
                                } else {
                                    financial_incomes_10 = 0.00;
                                }
                                if (dataSnapshot.hasChild("11")) {
                                    financial_incomes_11_st = dataSnapshot.child("11").child("value").getValue().toString();
                                    financial_incomes_11 = Double.parseDouble(financial_incomes_11_st);
                                    txtFinancialIncomes11.setText(financial_incomes_11_st);
                                } else {
                                    financial_incomes_11 = 0.00;
                                }
                                if (dataSnapshot.hasChild("12")) {
                                    financial_incomes_12_st = dataSnapshot.child("12").child("value").getValue().toString();
                                    financial_incomes_12 = Double.parseDouble(financial_incomes_12_st);
                                    txtFinancialIncomes12.setText(financial_incomes_12_st);
                                } else {
                                    financial_incomes_12 = 0.00;
                                }

                                companyRef.child(post_key).child("Budget").child("Other Incomes "+next_year).addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        if (dataSnapshot.hasChild("1")) {
                                            other_incomes_1_st = dataSnapshot.child("1").child("value").getValue().toString();
                                            other_incomes_1 = Double.parseDouble(other_incomes_1_st);
                                            txtOtherIncomes1.setText(other_incomes_1_st);
                                        } else {
                                            financial_incomes_1 = 0.00;
                                        }
                                        if (dataSnapshot.hasChild("2")) {
                                            other_incomes_2_st = dataSnapshot.child("2").child("value").getValue().toString();
                                            other_incomes_2 = Double.parseDouble(other_incomes_2_st);
                                            txtOtherIncomes2.setText(other_incomes_2_st);
                                        } else {
                                            financial_incomes_2 = 0.00;
                                        }
                                        if (dataSnapshot.hasChild("3")) {
                                            other_incomes_3_st = dataSnapshot.child("3").child("value").getValue().toString();
                                            other_incomes_3 = Double.parseDouble(other_incomes_3_st);
                                            txtOtherIncomes3.setText(other_incomes_3_st);
                                        } else {
                                            financial_incomes_3 = 0.00;
                                        }
                                        if (dataSnapshot.hasChild("4")) {
                                            other_incomes_4_st = dataSnapshot.child("4").child("value").getValue().toString();
                                            other_incomes_4 = Double.parseDouble(other_incomes_4_st);
                                            txtOtherIncomes4.setText(other_incomes_4_st);
                                        } else {
                                            financial_incomes_4 = 0.00;
                                        }
                                        if (dataSnapshot.hasChild("5")) {
                                            other_incomes_5_st = dataSnapshot.child("5").child("value").getValue().toString();
                                            other_incomes_5 = Double.parseDouble(other_incomes_5_st);
                                            txtOtherIncomes5.setText(other_incomes_5_st);
                                        } else {
                                            financial_incomes_5 = 0.00;
                                        }
                                        if (dataSnapshot.hasChild("6")) {
                                            other_incomes_6_st = dataSnapshot.child("6").child("value").getValue().toString();
                                            other_incomes_6 = Double.parseDouble(other_incomes_6_st);
                                            txtOtherIncomes6.setText(other_incomes_6_st);
                                        } else {
                                            financial_incomes_6 = 0.00;
                                        }
                                        if (dataSnapshot.hasChild("7")) {
                                            other_incomes_7_st = dataSnapshot.child("7").child("value").getValue().toString();
                                            other_incomes_7 = Double.parseDouble(other_incomes_7_st);
                                            txtOtherIncomes7.setText(other_incomes_7_st);
                                        } else {
                                            financial_incomes_7 = 0.00;
                                        }
                                        if (dataSnapshot.hasChild("8")) {
                                            other_incomes_8_st = dataSnapshot.child("8").child("value").getValue().toString();
                                            other_incomes_8 = Double.parseDouble(other_incomes_8_st);
                                            txtOtherIncomes8.setText(other_incomes_8_st);
                                        } else {
                                            financial_incomes_8 = 0.00;
                                        }
                                        if (dataSnapshot.hasChild("9")) {
                                            other_incomes_9_st = dataSnapshot.child("9").child("value").getValue().toString();
                                            other_incomes_9 = Double.parseDouble(other_incomes_9_st);
                                            txtOtherIncomes9.setText(other_incomes_9_st);
                                        } else {
                                            financial_incomes_9 = 0.00;
                                        }
                                        if (dataSnapshot.hasChild("10")) {
                                            other_incomes_10_st = dataSnapshot.child("10").child("value").getValue().toString();
                                            other_incomes_10 = Double.parseDouble(other_incomes_10_st);
                                            txtOtherIncomes10.setText(other_incomes_10_st);
                                        } else {
                                            financial_incomes_10 = 0.00;
                                        }
                                        if (dataSnapshot.hasChild("11")) {
                                            other_incomes_11_st = dataSnapshot.child("11").child("value").getValue().toString();
                                            other_incomes_11 = Double.parseDouble(other_incomes_11_st);
                                            txtOtherIncomes11.setText(other_incomes_11_st);
                                        } else {
                                            financial_incomes_11 = 0.00;
                                        }
                                        if (dataSnapshot.hasChild("12")) {
                                            other_incomes_12_st = dataSnapshot.child("12").child("value").getValue().toString();
                                            other_incomes_12 = Double.parseDouble(other_incomes_12_st);
                                            txtOtherIncomes12.setText(other_incomes_12_st);
                                        } else {
                                            financial_incomes_12 = 0.00;
                                        }


                                        total_incomes_1 = cash_incomes_1+credit_incomes_1+financial_incomes_1+other_incomes_1;
                                        String total_incomes_1_st = decimalFormat.format(total_incomes_1);
                                        txtTotalIncomes1.setText(total_incomes_1_st);

                                        total_incomes_2 = cash_incomes_2+credit_incomes_2+financial_incomes_2+other_incomes_2;
                                        String total_incomes_2_st = decimalFormat.format(total_incomes_2);
                                        txtTotalIncomes2.setText(total_incomes_2_st);

                                        total_incomes_3 = cash_incomes_3+credit_incomes_3+financial_incomes_3+other_incomes_3;
                                        String total_incomes_3_st = decimalFormat.format(total_incomes_3);
                                        txtTotalIncomes3.setText(total_incomes_3_st);

                                        total_incomes_4 = cash_incomes_4+credit_incomes_4+financial_incomes_4+other_incomes_4;
                                        String total_incomes_4_st = decimalFormat.format(total_incomes_4);
                                        txtTotalIncomes4.setText(total_incomes_4_st);

                                        total_incomes_5 = cash_incomes_5+credit_incomes_5+financial_incomes_5+other_incomes_5;
                                        String total_incomes_5_st = decimalFormat.format(total_incomes_5);
                                        txtTotalIncomes5.setText(total_incomes_5_st);

                                        total_incomes_6 = cash_incomes_6+credit_incomes_6+financial_incomes_6+other_incomes_6;
                                        String total_incomes_6_st = decimalFormat.format(total_incomes_6);
                                        txtTotalIncomes6.setText(total_incomes_6_st);

                                        total_incomes_7 = cash_incomes_7+credit_incomes_7+financial_incomes_7+other_incomes_7;
                                        String total_incomes_7_st = decimalFormat.format(total_incomes_7);
                                        txtTotalIncomes7.setText(total_incomes_7_st);

                                        total_incomes_8 = cash_incomes_8+credit_incomes_8+financial_incomes_8+other_incomes_8;
                                        String total_incomes_8_st = decimalFormat.format(total_incomes_8);
                                        txtTotalIncomes8.setText(total_incomes_8_st);

                                        total_incomes_9 = cash_incomes_9+credit_incomes_9+financial_incomes_9+other_incomes_9;
                                        String total_incomes_9_st = decimalFormat.format(total_incomes_9);
                                        txtTotalIncomes9.setText(total_incomes_9_st);

                                        total_incomes_10 = cash_incomes_10+credit_incomes_10+financial_incomes_10+other_incomes_10;
                                        String total_incomes_10_st = decimalFormat.format(total_incomes_10);
                                        txtTotalIncomes10.setText(total_incomes_10_st);

                                        total_incomes_11 = cash_incomes_11+credit_incomes_11+financial_incomes_11+other_incomes_11;
                                        String total_incomes_11_st = decimalFormat.format(total_incomes_11);
                                        txtTotalIncomes11.setText(total_incomes_11_st);

                                        total_incomes_12 = cash_incomes_12+credit_incomes_12+financial_incomes_12+other_incomes_12;
                                        String total_incomes_12_st = decimalFormat.format(total_incomes_12);
                                        txtTotalIncomes12.setText(total_incomes_12_st);

                                        companyRef.child(post_key).child("Budget").child("Worker Payments "+next_year).addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                if (dataSnapshot.hasChild("1")) {
                                                    total_salary_1_st = dataSnapshot.child("1").child("value").getValue().toString();
                                                    total_salary_1 = Double.parseDouble(total_salary_1_st);
                                                    txtWorkerPayment1.setText(total_salary_1_st);
                                                } else {
                                                    total_salary_1 = 0.00;
                                                }
                                                if (dataSnapshot.hasChild("2")) {
                                                    total_salary_2_st = dataSnapshot.child("2").child("value").getValue().toString();
                                                    total_salary_2 = Double.parseDouble(total_salary_2_st);
                                                    txtWorkerPayment2.setText(total_salary_2_st);
                                                } else {
                                                    total_salary_2 = 0.00;
                                                }
                                                if (dataSnapshot.hasChild("3")) {
                                                    total_salary_3_st = dataSnapshot.child("3").child("value").getValue().toString();
                                                    total_salary_3 = Double.parseDouble(total_salary_3_st);
                                                    txtWorkerPayment3.setText(total_salary_3_st);
                                                } else {
                                                    total_salary_3 = 0.00;
                                                }
                                                if (dataSnapshot.hasChild("4")) {
                                                    total_salary_4_st = dataSnapshot.child("4").child("value").getValue().toString();
                                                    total_salary_4 = Double.parseDouble(total_salary_4_st);
                                                    txtWorkerPayment4.setText(total_salary_4_st);
                                                } else {
                                                    total_salary_4 = 0.00;
                                                }
                                                if (dataSnapshot.hasChild("5")) {
                                                    total_salary_5_st = dataSnapshot.child("5").child("value").getValue().toString();
                                                    total_salary_5 = Double.parseDouble(total_salary_5_st);
                                                    txtWorkerPayment5.setText(total_salary_5_st);
                                                } else {
                                                    total_salary_5 = 0.00;
                                                }
                                                if (dataSnapshot.hasChild("6")) {
                                                    total_salary_6_st = dataSnapshot.child("6").child("value").getValue().toString();
                                                    total_salary_6 = Double.parseDouble(total_salary_6_st);
                                                    txtWorkerPayment6.setText(total_salary_6_st);
                                                } else {
                                                    total_salary_6 = 0.00;
                                                }
                                                if (dataSnapshot.hasChild("7")) {
                                                    total_salary_7_st = dataSnapshot.child("7").child("value").getValue().toString();
                                                    total_salary_7 = Double.parseDouble(total_salary_7_st);
                                                    txtWorkerPayment7.setText(total_salary_7_st);
                                                } else {
                                                    total_salary_7 = 0.00;
                                                }
                                                if (dataSnapshot.hasChild("8")) {
                                                    total_salary_8_st = dataSnapshot.child("8").child("value").getValue().toString();
                                                    total_salary_8 = Double.parseDouble(total_salary_8_st);
                                                    txtWorkerPayment7.setText(total_salary_8_st);
                                                } else {
                                                    total_salary_8 = 0.00;
                                                }
                                                if (dataSnapshot.hasChild("9")) {
                                                    total_salary_9_st = dataSnapshot.child("9").child("value").getValue().toString();
                                                    total_salary_9 = Double.parseDouble(total_salary_9_st);
                                                    txtWorkerPayment8.setText(total_salary_9_st);
                                                } else {
                                                    total_salary_9 = 0.00;
                                                }
                                                if (dataSnapshot.hasChild("10")) {
                                                    total_salary_10_st = dataSnapshot.child("10").child("value").getValue().toString();
                                                    total_salary_10 = Double.parseDouble(total_salary_10_st);
                                                    txtWorkerPayment10.setText(total_salary_10_st);
                                                } else {
                                                    total_salary_10 = 0.00;
                                                }
                                                if (dataSnapshot.hasChild("11")) {
                                                    total_salary_11_st = dataSnapshot.child("11").child("value").getValue().toString();
                                                    total_salary_11 = Double.parseDouble(total_salary_11_st);
                                                    txtWorkerPayment11.setText(total_salary_11_st);
                                                } else {
                                                    total_salary_11 = 0.00;
                                                }
                                                if (dataSnapshot.hasChild("12")) {
                                                    total_salary_12_st = dataSnapshot.child("12").child("value").getValue().toString();
                                                    total_salary_12 = Double.parseDouble(total_salary_12_st);
                                                    txtWorkerPayment12.setText(total_salary_12_st);
                                                } else {
                                                    total_salary_12 = 0.00;
                                                }

                                                companyRef.child(post_key).child("Budget").child("IGV Payments "+next_year).addListenerForSingleValueEvent(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                                        if (dataSnapshot.hasChild("1")) {
                                                            igv_taxes_1_st = dataSnapshot.child("1").child("value").getValue().toString();
                                                            igv_taxes_1 = Double.parseDouble(igv_taxes_1_st);
                                                            txtIgv1.setText(igv_taxes_1_st);
                                                        } else {
                                                            igv_taxes_1 = 0.00;
                                                        }
                                                        if (dataSnapshot.hasChild("2")) {
                                                            igv_taxes_2_st = dataSnapshot.child("2").child("value").getValue().toString();
                                                            igv_taxes_2 = Double.parseDouble(igv_taxes_2_st);
                                                            txtIgv2.setText(igv_taxes_2_st);
                                                        } else {
                                                            igv_taxes_2 = 0.00;
                                                        }
                                                        if (dataSnapshot.hasChild("3")) {
                                                            igv_taxes_3_st = dataSnapshot.child("3").child("value").getValue().toString();
                                                            igv_taxes_3 = Double.parseDouble(igv_taxes_3_st);
                                                            txtIgv3.setText(igv_taxes_3_st);
                                                        } else {
                                                            igv_taxes_3 = 0.00;
                                                        }
                                                        if (dataSnapshot.hasChild("4")) {
                                                            igv_taxes_4_st = dataSnapshot.child("4").child("value").getValue().toString();
                                                            igv_taxes_4 = Double.parseDouble(igv_taxes_4_st);
                                                            txtIgv4.setText(igv_taxes_4_st);
                                                        } else {
                                                            igv_taxes_4 = 0.00;
                                                        }
                                                        if (dataSnapshot.hasChild("5")) {
                                                            igv_taxes_5_st = dataSnapshot.child("5").child("value").getValue().toString();
                                                            igv_taxes_5 = Double.parseDouble(igv_taxes_5_st);
                                                            txtIgv5.setText(igv_taxes_5_st);
                                                        } else {
                                                            igv_taxes_5 = 0.00;
                                                        }
                                                        if (dataSnapshot.hasChild("6")) {
                                                            igv_taxes_6_st = dataSnapshot.child("6").child("value").getValue().toString();
                                                            igv_taxes_6 = Double.parseDouble(igv_taxes_6_st);
                                                            txtIgv6.setText(igv_taxes_6_st);
                                                        } else {
                                                            igv_taxes_6 = 0.00;
                                                        }
                                                        if (dataSnapshot.hasChild("7")) {
                                                            igv_taxes_7_st = dataSnapshot.child("7").child("value").getValue().toString();
                                                            igv_taxes_7 = Double.parseDouble(igv_taxes_7_st);
                                                            txtIgv7.setText(igv_taxes_7_st);
                                                        } else {
                                                            igv_taxes_7 = 0.00;
                                                        }
                                                        if (dataSnapshot.hasChild("8")) {
                                                            igv_taxes_8_st = dataSnapshot.child("8").child("value").getValue().toString();
                                                            igv_taxes_8 = Double.parseDouble(igv_taxes_8_st);
                                                            txtIgv8.setText(igv_taxes_8_st);
                                                        } else {
                                                            igv_taxes_8 = 0.00;
                                                        }
                                                        if (dataSnapshot.hasChild("9")) {
                                                            igv_taxes_9_st = dataSnapshot.child("9").child("value").getValue().toString();
                                                            igv_taxes_9 = Double.parseDouble(igv_taxes_9_st);
                                                            txtIgv9.setText(igv_taxes_9_st);
                                                        } else {
                                                            igv_taxes_9 = 0.00;
                                                        }
                                                        if (dataSnapshot.hasChild("10")) {
                                                            igv_taxes_10_st = dataSnapshot.child("10").child("value").getValue().toString();
                                                            igv_taxes_10 = Double.parseDouble(igv_taxes_10_st);
                                                            txtIgv10.setText(igv_taxes_10_st);
                                                        } else {
                                                            igv_taxes_10 = 0.00;
                                                        }
                                                        if (dataSnapshot.hasChild("11")) {
                                                            igv_taxes_11_st = dataSnapshot.child("11").child("value").getValue().toString();
                                                            igv_taxes_11 = Double.parseDouble(igv_taxes_11_st);
                                                            txtIgv11.setText(igv_taxes_11_st);
                                                        } else {
                                                            igv_taxes_11 = 0.00;
                                                        }
                                                        if (dataSnapshot.hasChild("12")) {
                                                            igv_taxes_12_st = dataSnapshot.child("12").child("value").getValue().toString();
                                                            igv_taxes_12 = Double.parseDouble(igv_taxes_12_st);
                                                            txtIgv12.setText(igv_taxes_12_st);
                                                        } else {
                                                            igv_taxes_12 = 0.00;
                                                        }

                                                        companyRef.child(post_key).child("Budget").child("Purchases "+next_year).addListenerForSingleValueEvent(new ValueEventListener() {
                                                            @Override
                                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                                if (dataSnapshot.hasChild("1")) {
                                                                    purchase_total_1_st = dataSnapshot.child("1").child("value").getValue().toString();
                                                                    purchase_total_1 = Double.parseDouble(purchase_total_1_st);
                                                                    txtPurchases1.setText(purchase_total_1_st);
                                                                } else {
                                                                    purchase_total_1 = 0.00;
                                                                }
                                                                if (dataSnapshot.hasChild("2")) {
                                                                    purchase_total_2_st = dataSnapshot.child("2").child("value").getValue().toString();
                                                                    purchase_total_2 = Double.parseDouble(purchase_total_2_st);
                                                                    txtPurchases2.setText(purchase_total_2_st);
                                                                } else {
                                                                    purchase_total_2 = 0.00;
                                                                }
                                                                if (dataSnapshot.hasChild("3")) {
                                                                    purchase_total_3_st = dataSnapshot.child("3").child("value").getValue().toString();
                                                                    purchase_total_3 = Double.parseDouble(purchase_total_3_st);
                                                                    txtPurchases3.setText(purchase_total_3_st);
                                                                } else {
                                                                    purchase_total_3 = 0.00;
                                                                }
                                                                if (dataSnapshot.hasChild("4")) {
                                                                    purchase_total_4_st = dataSnapshot.child("4").child("value").getValue().toString();
                                                                    purchase_total_4 = Double.parseDouble(purchase_total_4_st);
                                                                    txtPurchases4.setText(purchase_total_4_st);
                                                                } else {
                                                                    purchase_total_4 = 0.00;
                                                                }
                                                                if (dataSnapshot.hasChild("5")) {
                                                                    purchase_total_5_st = dataSnapshot.child("5").child("value").getValue().toString();
                                                                    purchase_total_5 = Double.parseDouble(purchase_total_5_st);
                                                                    txtPurchases5.setText(purchase_total_5_st);
                                                                } else {
                                                                    purchase_total_5 = 0.00;
                                                                }
                                                                if (dataSnapshot.hasChild("6")) {
                                                                    purchase_total_6_st = dataSnapshot.child("6").child("value").getValue().toString();
                                                                    purchase_total_6 = Double.parseDouble(purchase_total_6_st);
                                                                    txtPurchases6.setText(purchase_total_6_st);
                                                                } else {
                                                                    purchase_total_6 = 0.00;
                                                                }
                                                                if (dataSnapshot.hasChild("7")) {
                                                                    purchase_total_7_st = dataSnapshot.child("7").child("value").getValue().toString();
                                                                    purchase_total_7 = Double.parseDouble(purchase_total_7_st);
                                                                    txtPurchases7.setText(purchase_total_7_st);
                                                                } else {
                                                                    purchase_total_7 = 0.00;
                                                                }
                                                                if (dataSnapshot.hasChild("8")) {
                                                                    purchase_total_8_st = dataSnapshot.child("8").child("value").getValue().toString();
                                                                    purchase_total_8 = Double.parseDouble(purchase_total_8_st);
                                                                    txtPurchases8.setText(purchase_total_8_st);
                                                                } else {
                                                                    purchase_total_8 = 0.00;
                                                                }
                                                                if (dataSnapshot.hasChild("9")) {
                                                                    purchase_total_9_st = dataSnapshot.child("9").child("value").getValue().toString();
                                                                    purchase_total_9 = Double.parseDouble(purchase_total_9_st);
                                                                    txtPurchases9.setText(purchase_total_9_st);
                                                                } else {
                                                                    purchase_total_9 = 0.00;
                                                                }
                                                                if (dataSnapshot.hasChild("10")) {
                                                                    purchase_total_10_st = dataSnapshot.child("10").child("value").getValue().toString();
                                                                    purchase_total_10 = Double.parseDouble(purchase_total_10_st);
                                                                    txtPurchases10.setText(purchase_total_10_st);
                                                                } else {
                                                                    purchase_total_10 = 0.00;
                                                                }
                                                                if (dataSnapshot.hasChild("11")) {
                                                                    purchase_total_11_st = dataSnapshot.child("11").child("value").getValue().toString();
                                                                    purchase_total_11 = Double.parseDouble(purchase_total_11_st);
                                                                    txtPurchases11.setText(purchase_total_11_st);
                                                                } else {
                                                                    purchase_total_11 = 0.00;
                                                                }
                                                                if (dataSnapshot.hasChild("12")) {
                                                                    purchase_total_12_st = dataSnapshot.child("12").child("value").getValue().toString();
                                                                    purchase_total_12 = Double.parseDouble(purchase_total_12_st);
                                                                    txtPurchases12.setText(purchase_total_12_st);
                                                                } else {
                                                                    purchase_total_12 = 0.00;
                                                                }

                                                                companyRef.child(post_key).child("Budget").child("Financial Outcomes "+next_year).addListenerForSingleValueEvent(new ValueEventListener() {
                                                                    @Override
                                                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                                                        if (dataSnapshot.hasChild("1")) {
                                                                            financial_outcomes_1_st = dataSnapshot.child("1").child("value").getValue().toString();
                                                                            financial_outcomes_1 = Double.parseDouble(financial_outcomes_1_st);
                                                                            txtFinancialOutcomes.setText(financial_outcomes_1_st);
                                                                        } else {
                                                                            financial_outcomes_1 = 0.00;
                                                                        }
                                                                        if (dataSnapshot.hasChild("2")) {
                                                                            financial_outcomes_2_st = dataSnapshot.child("2").child("value").getValue().toString();
                                                                            financial_outcomes_2 = Double.parseDouble(financial_outcomes_2_st);
                                                                            txtFinancialOutcomes2.setText(financial_outcomes_2_st);
                                                                        } else {
                                                                            financial_outcomes_2 = 0.00;
                                                                        }
                                                                        if (dataSnapshot.hasChild("3")) {
                                                                            financial_outcomes_3_st = dataSnapshot.child("3").child("value").getValue().toString();
                                                                            financial_outcomes_3 = Double.parseDouble(financial_outcomes_3_st);
                                                                            txtFinancialOutcomes3.setText(financial_outcomes_3_st);
                                                                        } else {
                                                                            financial_outcomes_3 = 0.00;
                                                                        }
                                                                        if (dataSnapshot.hasChild("4")) {
                                                                            financial_outcomes_4_st = dataSnapshot.child("4").child("value").getValue().toString();
                                                                            financial_outcomes_4 = Double.parseDouble(financial_outcomes_4_st);
                                                                            txtFinancialOutcomes4.setText(financial_outcomes_4_st);
                                                                        } else {
                                                                            financial_outcomes_4 = 0.00;
                                                                        }
                                                                        if (dataSnapshot.hasChild("5")) {
                                                                            financial_outcomes_5_st = dataSnapshot.child("5").child("value").getValue().toString();
                                                                            financial_outcomes_5 = Double.parseDouble(financial_outcomes_5_st);
                                                                            txtFinancialOutcomes5.setText(financial_outcomes_5_st);
                                                                        } else {
                                                                            financial_outcomes_5 = 0.00;
                                                                        }
                                                                        if (dataSnapshot.hasChild("6")) {
                                                                            financial_outcomes_6_st = dataSnapshot.child("6").child("value").getValue().toString();
                                                                            financial_outcomes_6 = Double.parseDouble(financial_outcomes_6_st);
                                                                            txtFinancialOutcomes6.setText(financial_outcomes_6_st);
                                                                        } else {
                                                                            financial_outcomes_6 = 0.00;
                                                                        }
                                                                        if (dataSnapshot.hasChild("7")) {
                                                                            financial_outcomes_7_st = dataSnapshot.child("7").child("value").getValue().toString();
                                                                            financial_outcomes_7 = Double.parseDouble(financial_outcomes_7_st);
                                                                            txtFinancialOutcomes7.setText(financial_outcomes_7_st);
                                                                        } else {
                                                                            financial_outcomes_7 = 0.00;
                                                                        }
                                                                        if (dataSnapshot.hasChild("8")) {
                                                                            financial_outcomes_8_st = dataSnapshot.child("8").child("value").getValue().toString();
                                                                            financial_outcomes_8 = Double.parseDouble(financial_outcomes_8_st);
                                                                            txtFinancialOutcomes8.setText(financial_outcomes_8_st);
                                                                        } else {
                                                                            financial_outcomes_8 = 0.00;
                                                                        }
                                                                        if (dataSnapshot.hasChild("9")) {
                                                                            financial_outcomes_9_st = dataSnapshot.child("9").child("value").getValue().toString();
                                                                            financial_outcomes_9 = Double.parseDouble(financial_outcomes_9_st);
                                                                            txtFinancialOutcomes9.setText(financial_outcomes_9_st);
                                                                        } else {
                                                                            financial_outcomes_9 = 0.00;
                                                                        }
                                                                        if (dataSnapshot.hasChild("10")) {
                                                                            financial_outcomes_10_st = dataSnapshot.child("10").child("value").getValue().toString();
                                                                            financial_outcomes_10 = Double.parseDouble(financial_outcomes_10_st);
                                                                            txtFinancialOutcomes10.setText(financial_outcomes_10_st);
                                                                        } else {
                                                                            financial_outcomes_10 = 0.00;
                                                                        }
                                                                        if (dataSnapshot.hasChild("11")) {
                                                                            financial_outcomes_11_st = dataSnapshot.child("11").child("value").getValue().toString();
                                                                            financial_outcomes_11 = Double.parseDouble(financial_outcomes_11_st);
                                                                            txtFinancialOutcomes11.setText(financial_outcomes_11_st);
                                                                        } else {
                                                                            financial_outcomes_11 = 0.00;
                                                                        }
                                                                        if (dataSnapshot.hasChild("12")) {
                                                                            financial_outcomes_12_st = dataSnapshot.child("12").child("value").getValue().toString();
                                                                            financial_outcomes_12 = Double.parseDouble(financial_outcomes_12_st);
                                                                            txtFinancialOutcomes12.setText(financial_outcomes_12_st);
                                                                        } else {
                                                                            financial_outcomes_12 = 0.00;
                                                                        }

                                                                        companyRef.child(post_key).child("Budget").child("Other Outcomes "+next_year).addListenerForSingleValueEvent(new ValueEventListener() {
                                                                            @Override
                                                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                                                if (dataSnapshot.hasChild("1")) {
                                                                                    other_outcomes_1_st = dataSnapshot.child("1").child("value").getValue().toString();
                                                                                    other_outcomes_1 = Double.parseDouble(other_outcomes_1_st);
                                                                                    txtOtherOutcomes.setText(other_outcomes_1_st);
                                                                                } else {
                                                                                    other_outcomes_1 = 0.00;
                                                                                }
                                                                                if (dataSnapshot.hasChild("2")) {
                                                                                    other_outcomes_2_st = dataSnapshot.child("2").child("value").getValue().toString();
                                                                                    other_outcomes_2 = Double.parseDouble(other_outcomes_2_st);
                                                                                    txtOtherOutcomes2.setText(other_outcomes_2_st);
                                                                                } else {
                                                                                    other_outcomes_2 = 0.00;
                                                                                }
                                                                                if (dataSnapshot.hasChild("3")) {
                                                                                    other_outcomes_3_st = dataSnapshot.child("3").child("value").getValue().toString();
                                                                                    other_outcomes_3 = Double.parseDouble(other_outcomes_3_st);
                                                                                    txtOtherOutcomes3.setText(other_outcomes_3_st);
                                                                                } else {
                                                                                    other_outcomes_3 = 0.00;
                                                                                }
                                                                                if (dataSnapshot.hasChild("4")) {
                                                                                    other_outcomes_4_st = dataSnapshot.child("4").child("value").getValue().toString();
                                                                                    other_outcomes_4 = Double.parseDouble(other_outcomes_4_st);
                                                                                    txtOtherOutcomes4.setText(other_outcomes_4_st);
                                                                                } else {
                                                                                    other_outcomes_4 = 0.00;
                                                                                }
                                                                                if (dataSnapshot.hasChild("5")) {
                                                                                    other_outcomes_5_st = dataSnapshot.child("5").child("value").getValue().toString();
                                                                                    other_outcomes_5 = Double.parseDouble(other_outcomes_5_st);
                                                                                    txtOtherOutcomes5.setText(other_outcomes_5_st);
                                                                                } else {
                                                                                    other_outcomes_5 = 0.00;
                                                                                }
                                                                                if (dataSnapshot.hasChild("6")) {
                                                                                    other_outcomes_6_st = dataSnapshot.child("6").child("value").getValue().toString();
                                                                                    other_outcomes_6 = Double.parseDouble(other_outcomes_6_st);
                                                                                    txtOtherOutcomes6.setText(other_outcomes_6_st);
                                                                                } else {
                                                                                    other_outcomes_6 = 0.00;
                                                                                }
                                                                                if (dataSnapshot.hasChild("7")) {
                                                                                    other_outcomes_7_st = dataSnapshot.child("7").child("value").getValue().toString();
                                                                                    other_outcomes_7 = Double.parseDouble(other_outcomes_7_st);
                                                                                    txtOtherOutcomes7.setText(other_outcomes_7_st);
                                                                                } else {
                                                                                    other_outcomes_7 = 0.00;
                                                                                }
                                                                                if (dataSnapshot.hasChild("8")) {
                                                                                    other_outcomes_8_st = dataSnapshot.child("8").child("value").getValue().toString();
                                                                                    other_outcomes_8 = Double.parseDouble(other_outcomes_8_st);
                                                                                    txtOtherOutcomes8.setText(other_outcomes_8_st);
                                                                                } else {
                                                                                    other_outcomes_8 = 0.00;
                                                                                }
                                                                                if (dataSnapshot.hasChild("9")) {
                                                                                    other_outcomes_9_st = dataSnapshot.child("9").child("value").getValue().toString();
                                                                                    other_outcomes_9 = Double.parseDouble(other_outcomes_9_st);
                                                                                    txtOtherOutcomes9.setText(other_outcomes_9_st);
                                                                                } else {
                                                                                    other_outcomes_9 = 0.00;
                                                                                }
                                                                                if (dataSnapshot.hasChild("10")) {
                                                                                    other_outcomes_10_st = dataSnapshot.child("10").child("value").getValue().toString();
                                                                                    other_outcomes_10 = Double.parseDouble(other_outcomes_10_st);
                                                                                    txtOtherOutcomes10.setText(other_outcomes_10_st);
                                                                                } else {
                                                                                    other_outcomes_10 = 0.00;
                                                                                }
                                                                                if (dataSnapshot.hasChild("11")) {
                                                                                    other_outcomes_11_st = dataSnapshot.child("11").child("value").getValue().toString();
                                                                                    other_outcomes_11 = Double.parseDouble(other_outcomes_11_st);
                                                                                    txtOtherOutcomes11.setText(other_outcomes_11_st);
                                                                                } else {
                                                                                    other_outcomes_11 = 0.00;
                                                                                }
                                                                                if (dataSnapshot.hasChild("12")) {
                                                                                    other_outcomes_12_st = dataSnapshot.child("12").child("value").getValue().toString();
                                                                                    other_outcomes_12 = Double.parseDouble(other_outcomes_12_st);
                                                                                    txtOtherOutcomes12.setText(other_outcomes_12_st);
                                                                                } else {
                                                                                    other_outcomes_12 = 0.00;
                                                                                }

                                                                                total_outcomes_1 = total_salary_1+igv_taxes_1+purchase_total_1+financial_outcomes_1+other_outcomes_1;
                                                                                String total_outcomes_1_st = decimalFormat.format(total_outcomes_1);
                                                                                txtTotalOutcomes1.setText(total_outcomes_1_st);

                                                                                total_outcomes_2 = total_salary_2+igv_taxes_2+purchase_total_2+financial_outcomes_2+other_outcomes_2;
                                                                                String total_outcomes_2_st = decimalFormat.format(total_outcomes_2);
                                                                                txtTotalOutcomes2.setText(total_outcomes_2_st);

                                                                                total_outcomes_3 = total_salary_3+igv_taxes_3+purchase_total_3+financial_outcomes_3+other_outcomes_3;
                                                                                String total_outcomes_3_st = decimalFormat.format(total_outcomes_3);
                                                                                txtTotalOutcomes3.setText(total_outcomes_3_st);

                                                                                total_outcomes_4 = total_salary_4+igv_taxes_4+purchase_total_4+financial_outcomes_4+other_outcomes_4;
                                                                                String total_outcomes_4_st = decimalFormat.format(total_outcomes_4);
                                                                                txtTotalOutcomes4.setText(total_outcomes_4_st);

                                                                                total_outcomes_5 = total_salary_5+igv_taxes_5+purchase_total_5+financial_outcomes_5+other_outcomes_5;
                                                                                String total_outcomes_5_st = decimalFormat.format(total_outcomes_5);
                                                                                txtTotalOutcomes5.setText(total_outcomes_5_st);

                                                                                total_outcomes_6 = total_salary_6+igv_taxes_6+purchase_total_6+financial_outcomes_6+other_outcomes_6;
                                                                                String total_outcomes_6_st = decimalFormat.format(total_outcomes_6);
                                                                                txtTotalOutcomes6.setText(total_outcomes_6_st);

                                                                                total_outcomes_7 = total_salary_7+igv_taxes_7+purchase_total_7+financial_outcomes_7+other_outcomes_7;
                                                                                String total_outcomes_7_st = decimalFormat.format(total_outcomes_7);
                                                                                txtTotalOutcomes7.setText(total_outcomes_7_st);

                                                                                total_outcomes_8 = total_salary_8+igv_taxes_8+purchase_total_8+financial_outcomes_8+other_outcomes_8;
                                                                                String total_outcomes_8_st = decimalFormat.format(total_outcomes_8);
                                                                                txtTotalOutcomes8.setText(total_outcomes_8_st);

                                                                                total_outcomes_9 = total_salary_9+igv_taxes_9+purchase_total_9+financial_outcomes_9+other_outcomes_9;
                                                                                String total_outcomes_9_st = decimalFormat.format(total_outcomes_9);
                                                                                txtTotalOutcomes9.setText(total_outcomes_9_st);

                                                                                total_outcomes_10 = total_salary_10+igv_taxes_10+purchase_total_10+financial_outcomes_10+other_outcomes_10;
                                                                                String total_outcomes_10_st = decimalFormat.format(total_outcomes_10);
                                                                                txtTotalOutcomes10.setText(total_outcomes_10_st);

                                                                                total_outcomes_11 = total_salary_11+igv_taxes_11+purchase_total_11+financial_outcomes_11+other_outcomes_11;
                                                                                String total_outcomes_11_st = decimalFormat.format(total_outcomes_11);
                                                                                txtTotalOutcomes11.setText(total_outcomes_11_st);

                                                                                total_outcomes_12 = total_salary_12+igv_taxes_12+purchase_total_12+financial_outcomes_12+other_outcomes_12;
                                                                                String total_outcomes_12_st = decimalFormat.format(total_outcomes_12);
                                                                                txtTotalOutcomes12.setText(total_outcomes_12_st);
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

        companyRef.child(post_key).child("Business Plan").child("Financial Plan").child("Index").child("index_1").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    if (dataSnapshot.hasChild("index_name")) {
                        String value = dataSnapshot.child("index_name").getValue().toString();
                        txtIndexName1.setText(value);
                    }
                    if (dataSnapshot.hasChild("index_formula")) {
                        String value = dataSnapshot.child("index_formula").getValue().toString();
                        txtIndexFormula1.setText(value);
                    }
                    if (dataSnapshot.hasChild("index_result")) {
                        String value = dataSnapshot.child("index_result").getValue().toString();
                        txtIndexResult1.setText(value);
                    }

                }

                companyRef.child(post_key).child("Business Plan").child("Financial Plan").child("Index").child("index_2").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            if (dataSnapshot.hasChild("index_name")) {
                                String value = dataSnapshot.child("index_name").getValue().toString();
                                txtIndexName2.setText(value);
                            }
                            if (dataSnapshot.hasChild("index_formula")) {
                                String value = dataSnapshot.child("index_formula").getValue().toString();
                                txtIndexFormula2.setText(value);
                            }
                            if (dataSnapshot.hasChild("index_result")) {
                                String value = dataSnapshot.child("index_result").getValue().toString();
                                txtIndexResult2.setText(value);
                            }
                        }

                        companyRef.child(post_key).child("Business Plan").child("Financial Plan").child("Index").child("index_3").addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                if (dataSnapshot.exists()) {
                                    if (dataSnapshot.hasChild("index_name")) {
                                        String value = dataSnapshot.child("index_name").getValue().toString();
                                        txtIndexName3.setText(value);
                                    }
                                    if (dataSnapshot.hasChild("index_formula")) {
                                        String value = dataSnapshot.child("index_formula").getValue().toString();
                                        txtIndexFormula3.setText(value);
                                    }
                                    if (dataSnapshot.hasChild("index_result")) {
                                        String value = dataSnapshot.child("index_result").getValue().toString();
                                        txtIndexResult3.setText(value);
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
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        btnIndex1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path = "index_1";

                showIndexDialog(path);
            }
        });
        btnIndex2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path = "index_2";

                showIndexDialog(path);
            }
        });
        btnIndex3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path = "index_3";

                showIndexDialog(path);
            }
        });
    }

    private void showIndexDialog(final String path) {
        final AlertDialog dialog = new AlertDialog.Builder(this).create();

        LayoutInflater inflater = LayoutInflater.from(this);
        View finance_method = inflater.inflate(R.layout.financial_index_dialog,null);

        final EditText edtName,edFormula,edtResult;
        Button btnFinish;

        edtName = finance_method.findViewById(R.id.edtName);
        edFormula = finance_method.findViewById(R.id.edFormula);
        edtResult = finance_method.findViewById(R.id.edtResult);
        btnFinish = finance_method.findViewById(R.id.btnFinish);

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                companyRef.child(post_key).child("Business Plan").child("Financial Plan").child("Index").child(path).child("index_name").setValue(edtName.getText().toString());
                companyRef.child(post_key).child("Business Plan").child("Financial Plan").child("Index").child(path).child("index_formula").setValue(edFormula.getText().toString());
                companyRef.child(post_key).child("Business Plan").child("Financial Plan").child("Index").child(path).child("index_result").setValue(edtResult.getText().toString());
                Toasty.success(FinancialPlanActivity.this, "Registrado", Toast.LENGTH_LONG).show();
                dialog.dismiss();
            }
        });

        dialog.setView(finance_method);
        dialog.show();
    }

    private void showSetValueDialog(String account_name, String month, final TextView textView, final String path, final String data_name) {
        final AlertDialog dialog = new AlertDialog.Builder(this).create();

        LayoutInflater inflater = LayoutInflater.from(this);
        View finance_method = inflater.inflate(R.layout.set_budget_value_dialog,null);

        TextView txtMessage;
        final EditText edtValue;
        Button btnRegister;
        final LinearLayout rootLayout;

        txtMessage = finance_method.findViewById(R.id.txtMessage);
        edtValue = finance_method.findViewById(R.id.edtValue);
        btnRegister = finance_method.findViewById(R.id.btnRegister);
        rootLayout = finance_method.findViewById(R.id.rootLayout);

        txtMessage.setText("Ingresa el monto para "+account_name+" de "+month);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(edtValue.getText().toString())) {
                    Snackbar.make(rootLayout, "Debes ingresar el monto", Snackbar.LENGTH_SHORT).show();
                } else {
                    textView.setText(edtValue.getText().toString());
                    companyRef.child(post_key).child("Budget").child(data_name).child(path).child("value").setValue(edtValue.getText().toString());
                    Toasty.success(FinancialPlanActivity.this, "Registrado", Toast.LENGTH_LONG).show();
                    dialog.dismiss();
                    updateData();
                }
            }
        });

        dialog.setView(finance_method);
        dialog.show();
    }
}
