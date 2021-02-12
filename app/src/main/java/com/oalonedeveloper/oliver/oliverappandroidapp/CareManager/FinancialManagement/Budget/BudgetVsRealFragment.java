package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.FinancialManagement.Budget;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
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

public class BudgetVsRealFragment extends Fragment {

    TextView txtBudgetPeriod,txtCashSales1,txtCashSales2,txtCashSales3,txtCashSales4,txtCashSales5,txtCashSales6,txtCashSales7,txtCashSales8,txtCashSales9,txtCashSales10,txtCashSales11,txtCashSales12,
            txtCreditSales1,txtCreditSales2,txtCreditSales3,txtCreditSales4,txtCreditSales5,txtCreditSales6,txtCreditSales7,txtCreditSales8,txtCreditSales9,txtCreditSales10,txtCreditSales11,txtCreditSales12,
            txtFinancialIncomes1,txtFinancialIncomes2,txtFinancialIncomes3,txtFinancialIncomes4,txtFinancialIncomes5,txtFinancialIncomes6,txtFinancialIncomes7,txtFinancialIncomes8,txtFinancialIncomes9,txtFinancialIncomes10,txtFinancialIncomes11,txtFinancialIncomes12,
            txtOtherIncomes1,txtOtherIncomes2,txtOtherIncomes3,txtOtherIncomes4,txtOtherIncomes5,txtOtherIncomes6,txtOtherIncomes7,txtOtherIncomes8,txtOtherIncomes9,txtOtherIncomes10,txtOtherIncomes11,txtOtherIncomes12,
            txtTotalIncomes1,txtTotalIncomes2,txtTotalIncomes3,txtTotalIncomes4,txtTotalIncomes5,txtTotalIncomes6,txtTotalIncomes7,txtTotalIncomes8,txtTotalIncomes9,txtTotalIncomes10,txtTotalIncomes11,txtTotalIncomes12,
            txtWorkerPayment1,txtWorkerPayment2,txtWorkerPayment3,txtWorkerPayment4,txtWorkerPayment5,txtWorkerPayment6,txtWorkerPayment7,txtWorkerPayment8,txtWorkerPayment9,txtWorkerPayment10,txtWorkerPayment11,txtWorkerPayment12,
            txtIgv1,txtIgv2,txtIgv3,txtIgv4,txtIgv5,txtIgv6,txtIgv7,txtIgv8,txtIgv9,txtIgv10,txtIgv11,txtIgv12,
            txtPurchases1,txtPurchases2,txtPurchases3,txtPurchases4,txtPurchases5,txtPurchases6,txtPurchases7,txtPurchases8,txtPurchases9,txtPurchases10,txtPurchases11,txtPurchases12,
            txtFinancialOutcomes,txtFinancialOutcomes2,txtFinancialOutcomes3,txtFinancialOutcomes4,txtFinancialOutcomes5,txtFinancialOutcomes6,txtFinancialOutcomes7,txtFinancialOutcomes8,txtFinancialOutcomes9,txtFinancialOutcomes10,txtFinancialOutcomes11,txtFinancialOutcomes12,
            txtOtherOutcomes,txtOtherOutcomes2,txtOtherOutcomes3,txtOtherOutcomes4,txtOtherOutcomes5,txtOtherOutcomes6,txtOtherOutcomes7,txtOtherOutcomes8,txtOtherOutcomes9,txtOtherOutcomes10,txtOtherOutcomes11,txtOtherOutcomes12,
            txtTotalOutcomes1,txtTotalOutcomes2,txtTotalOutcomes3,txtTotalOutcomes4,txtTotalOutcomes5,txtTotalOutcomes6,txtTotalOutcomes7,txtTotalOutcomes8,txtTotalOutcomes9,txtTotalOutcomes10,txtTotalOutcomes11,txtTotalOutcomes12,
            txtCashSalesReal1,txtCashSalesReal2,txtCashSalesReal3,txtCashSalesReal4,txtCashSalesReal5,txtCashSalesReal6,txtCashSalesReal7,txtCashSalesReal8,txtCashSalesReal9,txtCashSalesReal10,txtCashSalesReal11,txtCashSalesReal12,
            txtCreditSalesReal1,txtCreditSalesReal2,txtCreditSalesReal3,txtCreditSalesReal4,txtCreditSalesReal5,txtCreditSalesReal6,txtCreditSalesReal7,txtCreditSalesReal8,txtCreditSalesReal9,txtCreditSalesReal10,txtCreditSalesReal11,txtCreditSalesReal12,
            txtFinancialIncomesReal1,txtFinancialIncomesReal2,txtFinancialIncomesReal3,txtFinancialIncomesReal4,txtFinancialIncomesReal5,txtFinancialIncomesReal6,txtFinancialIncomesReal7,txtFinancialIncomesReal8,txtFinancialIncomesReal9,txtFinancialIncomesReal10,txtFinancialIncomesReal11,txtFinancialIncomesReal12,
            txtOtherIncomesReal1,txtOtherIncomesReal2,txtOtherIncomesReal3,txtOtherIncomesReal4,txtOtherIncomesReal5,txtOtherIncomesReal6,txtOtherIncomesReal7,txtOtherIncomesReal8,txtOtherIncomesReal9,txtOtherIncomesReal10,txtOtherIncomesReal11,txtOtherIncomesReal12,
            txtTotalIncomesReal1,txtTotalIncomesReal2,txtTotalIncomesReal3,txtTotalIncomesReal4,txtTotalIncomesReal5,txtTotalIncomesReal6,txtTotalIncomesReal7,txtTotalIncomesReal8,txtTotalIncomesReal9,txtTotalIncomesReal10,txtTotalIncomesReal11,txtTotalIncomesReal12,
            txtWorkerPaymentReal1,txtWorkerPaymentReal2,txtWorkerPaymentReal3,txtWorkerPaymentReal4,txtWorkerPaymentReal5,txtWorkerPaymentReal6,txtWorkerPaymentReal7,txtWorkerPaymentReal8,txtWorkerPaymentReal9,txtWorkerPaymentReal10,txtWorkerPaymentReal11,txtWorkerPaymentReal12,
            txtIgvReal1,txtIgvReal2,txtIgvReal3,txtIgvReal4,txtIgvReal5,txtIgvReal6,txtIgvReal7,txtIgvReal8,txtIgvReal9,txtIgvReal10,txtIgvReal11,txtIgvReal12,
            txtPurchasesReal1,txtPurchasesReal2,txtPurchasesReal3,txtPurchasesReal4,txtPurchasesReal5,txtPurchasesReal6,txtPurchasesReal7,txtPurchasesReal8,txtPurchasesReal9,txtPurchasesReal10,txtPurchasesReal11,txtPurchasesReal12,
            txtFinancialOutcomesReal1,txtFinancialOutcomesReal2,txtFinancialOutcomesReal3,txtFinancialOutcomesReal4,txtFinancialOutcomesReal5,txtFinancialOutcomesReal6,txtFinancialOutcomesReal7,txtFinancialOutcomesReal8,txtFinancialOutcomesReal9,txtFinancialOutcomesReal10,txtFinancialOutcomesReal11,txtFinancialOutcomesReal12,
            txtOtherOutcomesReal1,txtOtherOutcomesReal2,txtOtherOutcomesReal3,txtOtherOutcomesReal4,txtOtherOutcomesReal5,txtOtherOutcomesReal6,txtOtherOutcomesReal7,txtOtherOutcomesReal8,txtOtherOutcomesReal9,txtOtherOutcomesReal10,txtOtherOutcomesReal11,txtOtherOutcomesReal12,
            txtTotalOutcomesReal1,txtTotalOutcomesReal2,txtTotalOutcomesReal3,txtTotalOutcomesReal4,txtTotalOutcomesReal5,txtTotalOutcomesReal6,txtTotalOutcomesReal7,txtTotalOutcomesReal8,txtTotalOutcomesReal9,txtTotalOutcomesReal10,txtTotalOutcomesReal11,txtTotalOutcomesReal12,
            txtCashSalesDiff1,txtCashSalesDiff2,txtCashSalesDiff3,txtCashSalesDiff4,txtCashSalesDiff5,txtCashSalesDiff6,txtCashSalesDiff7,txtCashSalesDiff8,txtCashSalesDiff9,txtCashSalesDiff10,txtCashSalesDiff11,txtCashSalesDiff12,
            txtCreditSalesDiff1,txtCreditSalesDiff2,txtCreditSalesDiff3,txtCreditSalesDiff4,txtCreditSalesDiff5,txtCreditSalesDiff6,txtCreditSalesDiff7,txtCreditSalesDiff8,txtCreditSalesDiff9,txtCreditSalesDiff10,txtCreditSalesDiff11,txtCreditSalesDiff12,
            txtFinancialIncomesDiff1,txtFinancialIncomesDiff2,txtFinancialIncomesDiff3,txtFinancialIncomesDiff4,txtFinancialIncomesDiff5,txtFinancialIncomesDiff6,txtFinancialIncomesDiff7,txtFinancialIncomesDiff8,txtFinancialIncomesDiff9,txtFinancialIncomesDiff10,txtFinancialIncomesDiff11,txtFinancialIncomesDiff12,
            txtOtherIncomesDiff1,txtOtherIncomesDiff2,txtOtherIncomesDiff3,txtOtherIncomesDiff4,txtOtherIncomesDiff5,txtOtherIncomesDiff6,txtOtherIncomesDiff7,txtOtherIncomesDiff8,txtOtherIncomesDiff9,txtOtherIncomesDiff10,txtOtherIncomesDiff11,txtOtherIncomesDiff12,
            txtTotalIncomesDiff1,txtTotalIncomesDiff2,txtTotalIncomesDiff3,txtTotalIncomesDiff4,txtTotalIncomesDiff5,txtTotalIncomesDiff6,txtTotalIncomesDiff7,txtTotalIncomesDiff8,txtTotalIncomesDiff9,txtTotalIncomesDiff10,txtTotalIncomesDiff11,txtTotalIncomesDiff12,
            txtWorkerPaymentDiff1,txtWorkerPaymentDiff2,txtWorkerPaymentDiff3,txtWorkerPaymentDiff4,txtWorkerPaymentDiff5,txtWorkerPaymentDiff6,txtWorkerPaymentDiff7,txtWorkerPaymentDiff8,txtWorkerPaymentDiff9,txtWorkerPaymentDiff10,txtWorkerPaymentDiff11,txtWorkerPaymentDiff12,
            txtIgvDiff1,txtIgvDiff2,txtIgvDiff3,txtIgvDiff4,txtIgvDiff5,txtIgvDiff6,txtIgvDiff7,txtIgvDiff8,txtIgvDiff9,txtIgvDiff10,txtIgvDiff11,txtIgvDiff12,
            txtPurchasesDiff1,txtPurchasesDiff2,txtPurchasesDiff3,txtPurchasesDiff4,txtPurchasesDiff5,txtPurchasesDiff6,txtPurchasesDiff7,txtPurchasesDiff8,txtPurchasesDiff9,txtPurchasesDiff10,txtPurchasesDiff11,txtPurchasesDiff12,
            txtFinancialOutcomesDiff1,txtFinancialOutcomesDiff2,txtFinancialOutcomesDiff3,txtFinancialOutcomesDiff4,txtFinancialOutcomesDiff5,txtFinancialOutcomesDiff6,txtFinancialOutcomesDiff7,txtFinancialOutcomesDiff8,txtFinancialOutcomesDiff9,txtFinancialOutcomesDiff10,txtFinancialOutcomesDiff11,txtFinancialOutcomesDiff12,
            txtOtherOutcomesDiff1,txtOtherOutcomesDiff2,txtOtherOutcomesDiff3,txtOtherOutcomesDiff4,txtOtherOutcomesDiff5,txtOtherOutcomesDiff6,txtOtherOutcomesDiff7,txtOtherOutcomesDiff8,txtOtherOutcomesDiff9,txtOtherOutcomesDiff10,txtOtherOutcomesDiff11,txtOtherOutcomesDiff12,
            txtTotalOutcomesDiff1,txtTotalOutcomesDiff2,txtTotalOutcomesDiff3,txtTotalOutcomesDiff4,txtTotalOutcomesDiff5,txtTotalOutcomesDiff6,txtTotalOutcomesDiff7,txtTotalOutcomesDiff8,txtTotalOutcomesDiff9,txtTotalOutcomesDiff10,txtTotalOutcomesDiff11,txtTotalOutcomesDiff12;


    int day,month,year,last_year,before_last_year;
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_budget_vs_real, container, false);

        txtCashSales1 = view.findViewById(R.id.txtCashSales1);
        txtCreditSales1 = view.findViewById(R.id.txtCreditSales1);
        txtFinancialIncomes1 = view.findViewById(R.id.txtFinancialIncomes1);
        txtOtherIncomes1 = view.findViewById(R.id.txtOtherIncomes1);
        txtTotalIncomes1 = view.findViewById(R.id.txtTotalIncomes1);
        txtCashSales2 = view.findViewById(R.id.txtCashSales2);
        txtCreditSales2 = view.findViewById(R.id.txtCreditSales2);
        txtFinancialIncomes2 = view.findViewById(R.id.txtFinancialIncomes2);
        txtOtherIncomes2 = view.findViewById(R.id.txtOtherIncomes2);
        txtTotalIncomes2 = view.findViewById(R.id.txtTotalIncomes2);
        txtCashSales3 = view.findViewById(R.id.txtCashSales3);
        txtCreditSales3 = view.findViewById(R.id.txtCreditSales3);
        txtFinancialIncomes3 = view.findViewById(R.id.txtFinancialIncomes3);
        txtOtherIncomes3 = view.findViewById(R.id.txtOtherIncomes3);
        txtTotalIncomes3 = view.findViewById(R.id.txtTotalIncomes3);
        txtCashSales4 = view.findViewById(R.id.txtCashSales4);
        txtCreditSales4 = view.findViewById(R.id.txtCreditSales4);
        txtFinancialIncomes4 = view.findViewById(R.id.txtFinancialIncomes4);
        txtOtherIncomes4 = view.findViewById(R.id.txtOtherIncomes4);
        txtTotalIncomes4 = view.findViewById(R.id.txtTotalIncomes4);
        txtCashSales5 = view.findViewById(R.id.txtCashSales5);
        txtCreditSales5 = view.findViewById(R.id.txtCreditSales5);
        txtFinancialIncomes5 = view.findViewById(R.id.txtFinancialIncomes5);
        txtOtherIncomes5 = view.findViewById(R.id.txtOtherIncomes5);
        txtTotalIncomes5 = view.findViewById(R.id.txtTotalIncomes5);
        txtCashSales6 = view.findViewById(R.id.txtCashSales6);
        txtCreditSales6 = view.findViewById(R.id.txtCreditSales6);
        txtFinancialIncomes6 = view.findViewById(R.id.txtFinancialIncomes6);
        txtOtherIncomes6 = view.findViewById(R.id.txtOtherIncomes6);
        txtTotalIncomes6 = view.findViewById(R.id.txtTotalIncomes6);
        txtCashSales7 = view.findViewById(R.id.txtCashSales7);
        txtCreditSales7 = view.findViewById(R.id.txtCreditSales7);
        txtFinancialIncomes7 = view.findViewById(R.id.txtFinancialIncomes7);
        txtOtherIncomes7 = view.findViewById(R.id.txtOtherIncomes7);
        txtTotalIncomes7 = view.findViewById(R.id.txtTotalIncomes7);
        txtCashSales8 = view.findViewById(R.id.txtCashSales8);
        txtCreditSales8 = view.findViewById(R.id.txtCreditSales8);
        txtFinancialIncomes8 = view.findViewById(R.id.txtFinancialIncomes8);
        txtOtherIncomes8 = view.findViewById(R.id.txtOtherIncomes8);
        txtTotalIncomes8 = view.findViewById(R.id.txtTotalIncomes8);
        txtCashSales9 = view.findViewById(R.id.txtCashSales9);
        txtCreditSales9 = view.findViewById(R.id.txtCreditSales9);
        txtFinancialIncomes9 = view.findViewById(R.id.txtFinancialIncomes9);
        txtOtherIncomes9 = view.findViewById(R.id.txtOtherIncomes9);
        txtTotalIncomes9 = view.findViewById(R.id.txtTotalIncomes9);
        txtCashSales10 = view.findViewById(R.id.txtCashSales10);
        txtCreditSales10 = view.findViewById(R.id.txtCreditSales10);
        txtFinancialIncomes10 = view.findViewById(R.id.txtFinancialIncomes10);
        txtOtherIncomes10 = view.findViewById(R.id.txtOtherIncomes10);
        txtTotalIncomes10 = view.findViewById(R.id.txtTotalIncomes10);
        txtCashSales11 = view.findViewById(R.id.txtCashSales11);
        txtCreditSales11 = view.findViewById(R.id.txtCreditSales11);
        txtFinancialIncomes11 = view.findViewById(R.id.txtFinancialIncomes11);
        txtOtherIncomes11 = view.findViewById(R.id.txtOtherIncomes11);
        txtTotalIncomes11 = view.findViewById(R.id.txtTotalIncomes11);
        txtCashSales12 = view.findViewById(R.id.txtCashSales12);
        txtCreditSales12 = view.findViewById(R.id.txtCreditSales12);
        txtFinancialIncomes12 = view.findViewById(R.id.txtFinancialIncomes12);
        txtOtherIncomes12 = view.findViewById(R.id.txtOtherIncomes12);
        txtTotalIncomes12 = view.findViewById(R.id.txtTotalIncomes12);

        txtWorkerPayment1 = view.findViewById(R.id.txtWorkerPayment1);
        txtPurchases1 = view.findViewById(R.id.txtPurchases1);
        txtIgv1 = view.findViewById(R.id.txtIgv1);
        txtFinancialOutcomes = view.findViewById(R.id.txtFinancialOutcomes);
        txtOtherOutcomes = view.findViewById(R.id.txtOtherOutcomes);
        txtTotalOutcomes1 = view.findViewById(R.id.txtTotalOutcomes1);
        txtWorkerPayment2 = view.findViewById(R.id.txtWorkerPayment2);
        txtPurchases2 = view.findViewById(R.id.txtPurchases2);
        txtIgv2 = view.findViewById(R.id.txtIgv2);
        txtFinancialOutcomes2 = view.findViewById(R.id.txtFinancialOutcomes2);
        txtOtherOutcomes2 = view.findViewById(R.id.txtOtherOutcomes2);
        txtTotalOutcomes2 = view.findViewById(R.id.txtTotalOutcomes2);
        txtWorkerPayment3 = view.findViewById(R.id.txtWorkerPayment3);
        txtPurchases3 = view.findViewById(R.id.txtPurchases3);
        txtIgv3 = view.findViewById(R.id.txtIgv3);
        txtFinancialOutcomes3 = view.findViewById(R.id.txtFinancialOutcomes3);
        txtOtherOutcomes3 = view.findViewById(R.id.txtOtherOutcomes3);
        txtTotalOutcomes3 = view.findViewById(R.id.txtTotalOutcomes3);
        txtWorkerPayment4 = view.findViewById(R.id.txtWorkerPayment4);
        txtPurchases4 = view.findViewById(R.id.txtPurchases4);
        txtIgv4 = view.findViewById(R.id.txtIgv4);
        txtFinancialOutcomes4 = view.findViewById(R.id.txtFinancialOutcomes4);
        txtOtherOutcomes4 = view.findViewById(R.id.txtOtherOutcomes4);
        txtTotalOutcomes4 = view.findViewById(R.id.txtTotalOutcomes4);
        txtWorkerPayment5 = view.findViewById(R.id.txtWorkerPayment5);
        txtPurchases5 = view.findViewById(R.id.txtPurchases5);
        txtIgv5 = view.findViewById(R.id.txtIgv5);
        txtFinancialOutcomes5 = view.findViewById(R.id.txtFinancialOutcomes5);
        txtOtherOutcomes5 = view.findViewById(R.id.txtOtherOutcomes5);
        txtTotalOutcomes5 = view.findViewById(R.id.txtTotalOutcomes5);
        txtWorkerPayment6 = view.findViewById(R.id.txtWorkerPayment6);
        txtPurchases6 = view.findViewById(R.id.txtPurchases6);
        txtIgv6 = view.findViewById(R.id.txtIgv6);
        txtFinancialOutcomes6 = view.findViewById(R.id.txtFinancialOutcomes6);
        txtOtherOutcomes6 = view.findViewById(R.id.txtOtherOutcomes6);
        txtTotalOutcomes6 = view.findViewById(R.id.txtTotalOutcomes6);
        txtWorkerPayment7 = view.findViewById(R.id.txtWorkerPayment7);
        txtPurchases7 = view.findViewById(R.id.txtPurchases7);
        txtIgv7 = view.findViewById(R.id.txtIgv7);
        txtFinancialOutcomes7 = view.findViewById(R.id.txtFinancialOutcomes7);
        txtOtherOutcomes7 = view.findViewById(R.id.txtOtherOutcomes7);
        txtTotalOutcomes7 = view.findViewById(R.id.txtTotalOutcomes7);
        txtWorkerPayment8 = view.findViewById(R.id.txtWorkerPayment8);
        txtPurchases8 = view.findViewById(R.id.txtPurchases8);
        txtIgv8 = view.findViewById(R.id.txtIgv8);
        txtFinancialOutcomes8 = view.findViewById(R.id.txtFinancialOutcomes8);
        txtOtherOutcomes8 = view.findViewById(R.id.txtOtherOutcomes8);
        txtTotalOutcomes8 = view.findViewById(R.id.txtTotalOutcomes8);
        txtWorkerPayment9 = view.findViewById(R.id.txtWorkerPayment9);
        txtPurchases9 = view.findViewById(R.id.txtPurchases9);
        txtIgv9 = view.findViewById(R.id.txtIgv9);
        txtFinancialOutcomes9 = view.findViewById(R.id.txtFinancialOutcomes9);
        txtOtherOutcomes9 = view.findViewById(R.id.txtOtherOutcomes9);
        txtTotalOutcomes9 = view.findViewById(R.id.txtTotalOutcomes9);
        txtWorkerPayment10 = view.findViewById(R.id.txtWorkerPayment10);
        txtPurchases10 = view.findViewById(R.id.txtPurchases10);
        txtIgv10 = view.findViewById(R.id.txtIgv10);
        txtFinancialOutcomes10 = view.findViewById(R.id.txtFinancialOutcomes10);
        txtOtherOutcomes10 = view.findViewById(R.id.txtOtherOutcomes10);
        txtTotalOutcomes10 = view.findViewById(R.id.txtTotalOutcomes10);
        txtWorkerPayment11 = view.findViewById(R.id.txtWorkerPayment11);
        txtPurchases11 = view.findViewById(R.id.txtPurchases11);
        txtIgv11 = view.findViewById(R.id.txtIgv11);
        txtFinancialOutcomes11 = view.findViewById(R.id.txtFinancialOutcomes11);
        txtOtherOutcomes11 = view.findViewById(R.id.txtOtherOutcomes11);
        txtTotalOutcomes11 = view.findViewById(R.id.txtTotalOutcomes11);
        txtWorkerPayment12 = view.findViewById(R.id.txtWorkerPayment12);
        txtPurchases12 = view.findViewById(R.id.txtPurchases12);
        txtIgv12 = view.findViewById(R.id.txtIgv12);
        txtFinancialOutcomes12 = view.findViewById(R.id.txtFinancialOutcomes12);
        txtOtherOutcomes12 = view.findViewById(R.id.txtOtherOutcomes12);
        txtTotalOutcomes12 = view.findViewById(R.id.txtTotalOutcomes12);

        txtCashSalesReal1 = view.findViewById(R.id.txtCashSalesReal1);
        txtCashSalesReal2 = view.findViewById(R.id.txtCashSalesReal2);
        txtCashSalesReal3 = view.findViewById(R.id.txtCashSalesReal3);
        txtCashSalesReal4 = view.findViewById(R.id.txtCashSalesReal4);
        txtCashSalesReal5 = view.findViewById(R.id.txtCashSalesReal5);
        txtCashSalesReal6 = view.findViewById(R.id.txtCashSalesReal6);
        txtCashSalesReal7 = view.findViewById(R.id.txtCashSalesReal7);
        txtCashSalesReal8 = view.findViewById(R.id.txtCashSalesReal8);
        txtCashSalesReal9 = view.findViewById(R.id.txtCashSalesReal9);
        txtCashSalesReal10 = view.findViewById(R.id.txtCashSalesReal10);
        txtCashSalesReal11 = view.findViewById(R.id.txtCashSalesReal11);
        txtCashSalesReal12 = view.findViewById(R.id.txtCashSalesReal12);

        txtCreditSalesReal1 = view.findViewById(R.id.txtCreditSalesReal1);
        txtCreditSalesReal2 = view.findViewById(R.id.txtCreditSalesReal2);
        txtCreditSalesReal3 = view.findViewById(R.id.txtCreditSalesReal3);
        txtCreditSalesReal4 = view.findViewById(R.id.txtCreditSalesReal4);
        txtCreditSalesReal5 = view.findViewById(R.id.txtCreditSalesReal5);
        txtCreditSalesReal6 = view.findViewById(R.id.txtCreditSalesReal6);
        txtCreditSalesReal7 = view.findViewById(R.id.txtCreditSalesReal7);
        txtCreditSalesReal8 = view.findViewById(R.id.txtCreditSalesReal8);
        txtCreditSalesReal9 = view.findViewById(R.id.txtCreditSalesReal9);
        txtCreditSalesReal10 = view.findViewById(R.id.txtCreditSalesReal10);
        txtCreditSalesReal11 = view.findViewById(R.id.txtCreditSalesReal11);
        txtCreditSalesReal12 = view.findViewById(R.id.txtCreditSalesReal12);

        txtFinancialIncomesReal1 = view.findViewById(R.id.txtFinancialIncomesReal1);
        txtFinancialIncomesReal2 = view.findViewById(R.id.txtFinancialIncomesReal2);
        txtFinancialIncomesReal3 = view.findViewById(R.id.txtFinancialIncomesReal3);
        txtFinancialIncomesReal4 = view.findViewById(R.id.txtFinancialIncomesReal4);
        txtFinancialIncomesReal5 = view.findViewById(R.id.txtFinancialIncomesReal5);
        txtFinancialIncomesReal6 = view.findViewById(R.id.txtFinancialIncomesReal6);
        txtFinancialIncomesReal7 = view.findViewById(R.id.txtFinancialIncomesReal7);
        txtFinancialIncomesReal8 = view.findViewById(R.id.txtFinancialIncomesReal8);
        txtFinancialIncomesReal9 = view.findViewById(R.id.txtFinancialIncomesReal9);
        txtFinancialIncomesReal10 = view.findViewById(R.id.txtFinancialIncomesReal10);
        txtFinancialIncomesReal11 = view.findViewById(R.id.txtFinancialIncomesReal11);
        txtFinancialIncomesReal12 = view.findViewById(R.id.txtFinancialIncomesReal12);

        txtOtherIncomesReal1 = view.findViewById(R.id.txtOtherIncomesReal1);
        txtOtherIncomesReal2 = view.findViewById(R.id.txtOtherIncomesReal2);
        txtOtherIncomesReal3 = view.findViewById(R.id.txtOtherIncomesReal3);
        txtOtherIncomesReal4 = view.findViewById(R.id.txtOtherIncomesReal4);
        txtOtherIncomesReal5 = view.findViewById(R.id.txtOtherIncomesReal5);
        txtOtherIncomesReal6 = view.findViewById(R.id.txtOtherIncomesReal6);
        txtOtherIncomesReal7 = view.findViewById(R.id.txtOtherIncomesReal7);
        txtOtherIncomesReal8 = view.findViewById(R.id.txtOtherIncomesReal8);
        txtOtherIncomesReal9 = view.findViewById(R.id.txtOtherIncomesReal9);
        txtOtherIncomesReal10 = view.findViewById(R.id.txtOtherIncomesReal10);
        txtOtherIncomesReal11 = view.findViewById(R.id.txtOtherIncomesReal11);
        txtOtherIncomesReal12 = view.findViewById(R.id.txtOtherIncomesReal12);

        txtTotalIncomesReal1 = view.findViewById(R.id.txtTotalIncomesReal1);
        txtTotalIncomesReal2 = view.findViewById(R.id.txtTotalIncomesReal2);
        txtTotalIncomesReal3 = view.findViewById(R.id.txtTotalIncomesReal3);
        txtTotalIncomesReal4 = view.findViewById(R.id.txtTotalIncomesReal4);
        txtTotalIncomesReal5 = view.findViewById(R.id.txtTotalIncomesReal5);
        txtTotalIncomesReal6 = view.findViewById(R.id.txtTotalIncomesReal6);
        txtTotalIncomesReal7 = view.findViewById(R.id.txtTotalIncomesReal7);
        txtTotalIncomesReal8 = view.findViewById(R.id.txtTotalIncomesReal8);
        txtTotalIncomesReal9 = view.findViewById(R.id.txtTotalIncomesReal9);
        txtTotalIncomesReal10 = view.findViewById(R.id.txtTotalIncomesReal10);
        txtTotalIncomesReal11 = view.findViewById(R.id.txtTotalIncomesReal11);
        txtTotalIncomesReal12 = view.findViewById(R.id.txtTotalIncomesReal12);

        txtWorkerPaymentReal1 = view.findViewById(R.id.txtWorkerPaymentReal1);
        txtWorkerPaymentReal2 = view.findViewById(R.id.txtWorkerPaymentReal2);
        txtWorkerPaymentReal3 = view.findViewById(R.id.txtWorkerPaymentReal3);
        txtWorkerPaymentReal4 = view.findViewById(R.id.txtWorkerPaymentReal4);
        txtWorkerPaymentReal5 = view.findViewById(R.id.txtWorkerPaymentReal5);
        txtWorkerPaymentReal6 = view.findViewById(R.id.txtWorkerPaymentReal6);
        txtWorkerPaymentReal7 = view.findViewById(R.id.txtWorkerPaymentReal7);
        txtWorkerPaymentReal8 = view.findViewById(R.id.txtWorkerPaymentReal8);
        txtWorkerPaymentReal9 = view.findViewById(R.id.txtWorkerPaymentReal9);
        txtWorkerPaymentReal10 = view.findViewById(R.id.txtWorkerPaymentReal10);
        txtWorkerPaymentReal11 = view.findViewById(R.id.txtWorkerPaymentReal11);
        txtWorkerPaymentReal12 = view.findViewById(R.id.txtWorkerPaymentReal12);

        txtIgvReal1 = view.findViewById(R.id.txtIgvReal1);
        txtIgvReal2 = view.findViewById(R.id.txtIgvReal2);
        txtIgvReal3 = view.findViewById(R.id.txtIgvReal3);
        txtIgvReal4 = view.findViewById(R.id.txtIgvReal4);
        txtIgvReal5 = view.findViewById(R.id.txtIgvReal5);
        txtIgvReal6 = view.findViewById(R.id.txtIgvReal6);
        txtIgvReal7 = view.findViewById(R.id.txtIgvReal7);
        txtIgvReal8 = view.findViewById(R.id.txtIgvReal8);
        txtIgvReal9 = view.findViewById(R.id.txtIgvReal9);
        txtIgvReal10 = view.findViewById(R.id.txtIgvReal10);
        txtIgvReal11 = view.findViewById(R.id.txtIgvReal11);
        txtIgvReal12 = view.findViewById(R.id.txtIgvReal12);

        txtPurchasesReal1 = view.findViewById(R.id.txtPurchasesReal1);
        txtPurchasesReal2 = view.findViewById(R.id.txtPurchasesReal2);
        txtPurchasesReal3 = view.findViewById(R.id.txtPurchasesReal3);
        txtPurchasesReal4 = view.findViewById(R.id.txtPurchasesReal4);
        txtPurchasesReal5 = view.findViewById(R.id.txtPurchasesReal5);
        txtPurchasesReal6 = view.findViewById(R.id.txtPurchasesReal6);
        txtPurchasesReal7 = view.findViewById(R.id.txtPurchasesReal7);
        txtPurchasesReal8 = view.findViewById(R.id.txtPurchasesReal8);
        txtPurchasesReal9 = view.findViewById(R.id.txtPurchasesReal9);
        txtPurchasesReal10 = view.findViewById(R.id.txtPurchasesReal10);
        txtPurchasesReal11 = view.findViewById(R.id.txtPurchasesReal11);
        txtPurchasesReal12 = view.findViewById(R.id.txtPurchasesReal12);

        txtFinancialOutcomesReal1 = view.findViewById(R.id.txtFinancialOutcomesReal1);
        txtFinancialOutcomesReal2 = view.findViewById(R.id.txtFinancialOutcomesReal2);
        txtFinancialOutcomesReal3 = view.findViewById(R.id.txtFinancialOutcomesReal3);
        txtFinancialOutcomesReal4 = view.findViewById(R.id.txtFinancialOutcomesReal4);
        txtFinancialOutcomesReal5 = view.findViewById(R.id.txtFinancialOutcomesReal5);
        txtFinancialOutcomesReal6 = view.findViewById(R.id.txtFinancialOutcomesReal6);
        txtFinancialOutcomesReal7 = view.findViewById(R.id.txtFinancialOutcomesReal7);
        txtFinancialOutcomesReal8 = view.findViewById(R.id.txtFinancialOutcomesReal8);
        txtFinancialOutcomesReal9 = view.findViewById(R.id.txtFinancialOutcomesReal9);
        txtFinancialOutcomesReal10 = view.findViewById(R.id.txtFinancialOutcomesReal10);
        txtFinancialOutcomesReal11 = view.findViewById(R.id.txtFinancialOutcomesReal11);
        txtFinancialOutcomesReal12 = view.findViewById(R.id.txtFinancialOutcomesReal12);

        txtOtherOutcomesReal1 = view.findViewById(R.id.txtOtherOutcomesReal1);
        txtOtherOutcomesReal2 = view.findViewById(R.id.txtOtherOutcomesReal2);
        txtOtherOutcomesReal3 = view.findViewById(R.id.txtOtherOutcomesReal3);
        txtOtherOutcomesReal4 = view.findViewById(R.id.txtOtherOutcomesReal4);
        txtOtherOutcomesReal5 = view.findViewById(R.id.txtOtherOutcomesReal5);
        txtOtherOutcomesReal6 = view.findViewById(R.id.txtOtherOutcomesReal6);
        txtOtherOutcomesReal7 = view.findViewById(R.id.txtOtherOutcomesReal7);
        txtOtherOutcomesReal8 = view.findViewById(R.id.txtOtherOutcomesReal8);
        txtOtherOutcomesReal9 = view.findViewById(R.id.txtOtherOutcomesReal9);
        txtOtherOutcomesReal10 = view.findViewById(R.id.txtOtherOutcomesReal10);
        txtOtherOutcomesReal11 = view.findViewById(R.id.txtOtherOutcomesReal11);
        txtOtherOutcomesReal12 = view.findViewById(R.id.txtOtherOutcomesReal12);

        txtTotalOutcomesReal1 = view.findViewById(R.id.txtTotalOutcomesReal1);
        txtTotalOutcomesReal2 = view.findViewById(R.id.txtTotalOutcomesReal2);
        txtTotalOutcomesReal3 = view.findViewById(R.id.txtTotalOutcomesReal3);
        txtTotalOutcomesReal4 = view.findViewById(R.id.txtTotalOutcomesReal4);
        txtTotalOutcomesReal5 = view.findViewById(R.id.txtTotalOutcomesReal5);
        txtTotalOutcomesReal6 = view.findViewById(R.id.txtTotalOutcomesReal6);
        txtTotalOutcomesReal7 = view.findViewById(R.id.txtTotalOutcomesReal7);
        txtTotalOutcomesReal8 = view.findViewById(R.id.txtTotalOutcomesReal8);
        txtTotalOutcomesReal9 = view.findViewById(R.id.txtTotalOutcomesReal9);
        txtTotalOutcomesReal10 = view.findViewById(R.id.txtTotalOutcomesReal10);
        txtTotalOutcomesReal11 = view.findViewById(R.id.txtTotalOutcomesReal11);
        txtTotalOutcomesReal12 = view.findViewById(R.id.txtTotalOutcomesReal12);

        txtCashSalesDiff1 = view.findViewById(R.id.txtCashSalesDiff1);
        txtCashSalesDiff2 = view.findViewById(R.id.txtCashSalesDiff2);
        txtCashSalesDiff3 = view.findViewById(R.id.txtCashSalesDiff3);
        txtCashSalesDiff4 = view.findViewById(R.id.txtCashSalesDiff4);
        txtCashSalesDiff5 = view.findViewById(R.id.txtCashSalesDiff5);
        txtCashSalesDiff6 = view.findViewById(R.id.txtCashSalesDiff6);
        txtCashSalesDiff7 = view.findViewById(R.id.txtCashSalesDiff7);
        txtCashSalesDiff8 = view.findViewById(R.id.txtCashSalesDiff8);
        txtCashSalesDiff9 = view.findViewById(R.id.txtCashSalesDiff9);
        txtCashSalesDiff10 = view.findViewById(R.id.txtCashSalesDiff10);
        txtCashSalesDiff11 = view.findViewById(R.id.txtCashSalesDiff11);
        txtCashSalesDiff12 = view.findViewById(R.id.txtCashSalesDiff12);

        txtCreditSalesDiff1 = view.findViewById(R.id.txtCreditSalesDiff1);
        txtCreditSalesDiff2 = view.findViewById(R.id.txtCreditSalesDiff2);
        txtCreditSalesDiff3 = view.findViewById(R.id.txtCreditSalesDiff3);
        txtCreditSalesDiff4 = view.findViewById(R.id.txtCreditSalesDiff4);
        txtCreditSalesDiff5 = view.findViewById(R.id.txtCreditSalesDiff5);
        txtCreditSalesDiff6 = view.findViewById(R.id.txtCreditSalesDiff6);
        txtCreditSalesDiff7 = view.findViewById(R.id.txtCreditSalesDiff7);
        txtCreditSalesDiff8 = view.findViewById(R.id.txtCreditSalesDiff8);
        txtCreditSalesDiff9 = view.findViewById(R.id.txtCreditSalesDiff9);
        txtCreditSalesDiff10 = view.findViewById(R.id.txtCreditSalesDiff10);
        txtCreditSalesDiff11 = view.findViewById(R.id.txtCreditSalesDiff11);
        txtCreditSalesDiff12 = view.findViewById(R.id.txtCreditSalesDiff12);

        txtFinancialIncomesDiff1 = view.findViewById(R.id.txtFinancialIncomesDiff1);
        txtFinancialIncomesDiff2 = view.findViewById(R.id.txtFinancialIncomesDiff2);
        txtFinancialIncomesDiff3 = view.findViewById(R.id.txtFinancialIncomesDiff3);
        txtFinancialIncomesDiff4 = view.findViewById(R.id.txtFinancialIncomesDiff4);
        txtFinancialIncomesDiff5 = view.findViewById(R.id.txtFinancialIncomesDiff5);
        txtFinancialIncomesDiff6 = view.findViewById(R.id.txtFinancialIncomesDiff6);
        txtFinancialIncomesDiff7 = view.findViewById(R.id.txtFinancialIncomesDiff7);
        txtFinancialIncomesDiff8 = view.findViewById(R.id.txtFinancialIncomesDiff8);
        txtFinancialIncomesDiff9 = view.findViewById(R.id.txtFinancialIncomesDiff9);
        txtFinancialIncomesDiff10 = view.findViewById(R.id.txtFinancialIncomesDiff10);
        txtFinancialIncomesDiff11 = view.findViewById(R.id.txtFinancialIncomesDiff11);
        txtFinancialIncomesDiff12 = view.findViewById(R.id.txtFinancialIncomesDiff12);

        txtOtherIncomesDiff1 = view.findViewById(R.id.txtOtherIncomesDiff1);
        txtOtherIncomesDiff2 = view.findViewById(R.id.txtOtherIncomesDiff2);
        txtOtherIncomesDiff3 = view.findViewById(R.id.txtOtherIncomesDiff3);
        txtOtherIncomesDiff4 = view.findViewById(R.id.txtOtherIncomesDiff4);
        txtOtherIncomesDiff5 = view.findViewById(R.id.txtOtherIncomesDiff5);
        txtOtherIncomesDiff6 = view.findViewById(R.id.txtOtherIncomesDiff6);
        txtOtherIncomesDiff7 = view.findViewById(R.id.txtOtherIncomesDiff7);
        txtOtherIncomesDiff8 = view.findViewById(R.id.txtOtherIncomesDiff8);
        txtOtherIncomesDiff9 = view.findViewById(R.id.txtOtherIncomesDiff9);
        txtOtherIncomesDiff10 = view.findViewById(R.id.txtOtherIncomesDiff10);
        txtOtherIncomesDiff11 = view.findViewById(R.id.txtOtherIncomesDiff11);
        txtOtherIncomesDiff12 = view.findViewById(R.id.txtOtherIncomesDiff12);

        txtTotalIncomesDiff1 = view.findViewById(R.id.txtTotalIncomesDiff1);
        txtTotalIncomesDiff2 = view.findViewById(R.id.txtTotalIncomesDiff2);
        txtTotalIncomesDiff3 = view.findViewById(R.id.txtTotalIncomesDiff3);
        txtTotalIncomesDiff4 = view.findViewById(R.id.txtTotalIncomesDiff4);
        txtTotalIncomesDiff5 = view.findViewById(R.id.txtTotalIncomesDiff5);
        txtTotalIncomesDiff6 = view.findViewById(R.id.txtTotalIncomesDiff6);
        txtTotalIncomesDiff7 = view.findViewById(R.id.txtTotalIncomesDiff7);
        txtTotalIncomesDiff8 = view.findViewById(R.id.txtTotalIncomesDiff8);
        txtTotalIncomesDiff9 = view.findViewById(R.id.txtTotalIncomesDiff9);
        txtTotalIncomesDiff10 = view.findViewById(R.id.txtTotalIncomesDiff10);
        txtTotalIncomesDiff11 = view.findViewById(R.id.txtTotalIncomesDiff11);
        txtTotalIncomesDiff12 = view.findViewById(R.id.txtTotalIncomesDiff12);

        txtWorkerPaymentDiff1 = view.findViewById(R.id.txtWorkerPaymentDiff1);
        txtWorkerPaymentDiff2 = view.findViewById(R.id.txtWorkerPaymentDiff2);
        txtWorkerPaymentDiff3 = view.findViewById(R.id.txtWorkerPaymentDiff3);
        txtWorkerPaymentDiff4 = view.findViewById(R.id.txtWorkerPaymentDiff4);
        txtWorkerPaymentDiff5 = view.findViewById(R.id.txtWorkerPaymentDiff5);
        txtWorkerPaymentDiff6 = view.findViewById(R.id.txtWorkerPaymentDiff6);
        txtWorkerPaymentDiff7 = view.findViewById(R.id.txtWorkerPaymentDiff7);
        txtWorkerPaymentDiff8 = view.findViewById(R.id.txtWorkerPaymentDiff8);
        txtWorkerPaymentDiff9 = view.findViewById(R.id.txtWorkerPaymentDiff9);
        txtWorkerPaymentDiff10 = view.findViewById(R.id.txtWorkerPaymentDiff10);
        txtWorkerPaymentDiff11 = view.findViewById(R.id.txtWorkerPaymentDiff11);
        txtWorkerPaymentDiff12 = view.findViewById(R.id.txtWorkerPaymentDiff12);

        txtIgvDiff1 = view.findViewById(R.id.txtIgvDiff1);
        txtIgvDiff2 = view.findViewById(R.id.txtIgvDiff2);
        txtIgvDiff3 = view.findViewById(R.id.txtIgvDiff3);
        txtIgvDiff4 = view.findViewById(R.id.txtIgvDiff4);
        txtIgvDiff5 = view.findViewById(R.id.txtIgvDiff5);
        txtIgvDiff6 = view.findViewById(R.id.txtIgvDiff6);
        txtIgvDiff7 = view.findViewById(R.id.txtIgvDiff7);
        txtIgvDiff8 = view.findViewById(R.id.txtIgvDiff8);
        txtIgvDiff9 = view.findViewById(R.id.txtIgvDiff9);
        txtIgvDiff10 = view.findViewById(R.id.txtIgvDiff10);
        txtIgvDiff11 = view.findViewById(R.id.txtIgvDiff11);
        txtIgvDiff12 = view.findViewById(R.id.txtIgvDiff12);

        txtPurchasesDiff1 = view.findViewById(R.id.txtPurchasesDiff1);
        txtPurchasesDiff2 = view.findViewById(R.id.txtPurchasesDiff1);
        txtPurchasesDiff3 = view.findViewById(R.id.txtPurchasesDiff1);
        txtPurchasesDiff4 = view.findViewById(R.id.txtPurchasesDiff1);
        txtPurchasesDiff5 = view.findViewById(R.id.txtPurchasesDiff1);
        txtPurchasesDiff6 = view.findViewById(R.id.txtPurchasesDiff1);
        txtPurchasesDiff7 = view.findViewById(R.id.txtPurchasesDiff1);
        txtPurchasesDiff8 = view.findViewById(R.id.txtPurchasesDiff1);
        txtPurchasesDiff9 = view.findViewById(R.id.txtPurchasesDiff1);
        txtPurchasesDiff10 = view.findViewById(R.id.txtPurchasesDiff1);
        txtPurchasesDiff11 = view.findViewById(R.id.txtPurchasesDiff1);
        txtPurchasesDiff12 = view.findViewById(R.id.txtPurchasesDiff1);

        txtFinancialOutcomesDiff1 = view.findViewById(R.id.txtFinancialOutcomesDiff1);
        txtFinancialOutcomesDiff2 = view.findViewById(R.id.txtFinancialOutcomesDiff2);
        txtFinancialOutcomesDiff3 = view.findViewById(R.id.txtFinancialOutcomesDiff3);
        txtFinancialOutcomesDiff4 = view.findViewById(R.id.txtFinancialOutcomesDiff4);
        txtFinancialOutcomesDiff5 = view.findViewById(R.id.txtFinancialOutcomesDiff5);
        txtFinancialOutcomesDiff6 = view.findViewById(R.id.txtFinancialOutcomesDiff6);
        txtFinancialOutcomesDiff7 = view.findViewById(R.id.txtFinancialOutcomesDiff7);
        txtFinancialOutcomesDiff8 = view.findViewById(R.id.txtFinancialOutcomesDiff8);
        txtFinancialOutcomesDiff9 = view.findViewById(R.id.txtFinancialOutcomesDiff9);
        txtFinancialOutcomesDiff10 = view.findViewById(R.id.txtFinancialOutcomesDiff10);
        txtFinancialOutcomesDiff11 = view.findViewById(R.id.txtFinancialOutcomesDiff11);
        txtFinancialOutcomesDiff12 = view.findViewById(R.id.txtFinancialOutcomesDiff12);

        txtOtherOutcomesDiff1 = view.findViewById(R.id.txtOtherOutcomesDiff1);
        txtOtherOutcomesDiff2=  view.findViewById(R.id.txtOtherOutcomesDiff2);
        txtOtherOutcomesDiff3 = view.findViewById(R.id.txtOtherOutcomesDiff3);
        txtOtherOutcomesDiff4 = view.findViewById(R.id.txtOtherOutcomesDiff4);
        txtOtherOutcomesDiff5 = view.findViewById(R.id.txtOtherOutcomesDiff5);
        txtOtherOutcomesDiff6 = view.findViewById(R.id.txtOtherOutcomesDiff6);
        txtOtherOutcomesDiff7 = view.findViewById(R.id.txtOtherOutcomesDiff7);
        txtOtherOutcomesDiff8 = view.findViewById(R.id.txtOtherOutcomesDiff8);
        txtOtherOutcomesDiff9 = view.findViewById(R.id.txtOtherOutcomesDiff9);
        txtOtherOutcomesDiff10 = view.findViewById(R.id.txtOtherOutcomesDiff10);
        txtOtherOutcomesDiff11 = view.findViewById(R.id.txtOtherOutcomesDiff11);
        txtOtherOutcomesDiff12 = view.findViewById(R.id.txtOtherOutcomesDiff12);

        txtTotalOutcomesDiff1 = view.findViewById(R.id.txtTotalOutcomesDiff1);
        txtTotalOutcomesDiff2 = view.findViewById(R.id.txtTotalOutcomesDiff2);
        txtTotalOutcomesDiff3 = view.findViewById(R.id.txtTotalOutcomesDiff3);
        txtTotalOutcomesDiff4 = view.findViewById(R.id.txtTotalOutcomesDiff4);
        txtTotalOutcomesDiff5 = view.findViewById(R.id.txtTotalOutcomesDiff5);
        txtTotalOutcomesDiff6 = view.findViewById(R.id.txtTotalOutcomesDiff6);
        txtTotalOutcomesDiff7 = view.findViewById(R.id.txtTotalOutcomesDiff7);
        txtTotalOutcomesDiff8 = view.findViewById(R.id.txtTotalOutcomesDiff8);
        txtTotalOutcomesDiff9 = view.findViewById(R.id.txtTotalOutcomesDiff9);
        txtTotalOutcomesDiff10 = view.findViewById(R.id.txtTotalOutcomesDiff10);
        txtTotalOutcomesDiff11 = view.findViewById(R.id.txtTotalOutcomesDiff11);
        txtTotalOutcomesDiff12 = view.findViewById(R.id.txtTotalOutcomesDiff12);

        post_key = getActivity().getIntent().getExtras().getString("post_key");
        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");

        txtBudgetPeriod = view.findViewById(R.id.txtBudgetPeriod);

        decimalFormat = new DecimalFormat("0.00");

        Date date= new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        day = cal.get(Calendar.DAY_OF_MONTH);
        month = cal.get(Calendar.MONTH)+1;
        year = cal.get(Calendar.YEAR);
        last_year = year-1;
        before_last_year = last_year-1;

        txtBudgetPeriod.setText("PRESUPUESTADO "+year+" VS REAL "+year);

        updateData();

        return view;
    }

    private void updateData() {
        companyRef.child(post_key).child("Budget").child("Cash Sales "+year).addListenerForSingleValueEvent(new ValueEventListener() {
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


                companyRef.child(post_key).child("Budget").child("Credit Sales "+year).addListenerForSingleValueEvent(new ValueEventListener() {
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

                        companyRef.child(post_key).child("Budget").child("Financial Incomes "+year).addListenerForSingleValueEvent(new ValueEventListener() {
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

                                companyRef.child(post_key).child("Budget").child("Other Incomes "+year).addListenerForSingleValueEvent(new ValueEventListener() {
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

                                        companyRef.child(post_key).child("Budget").child("Worker Payments "+year).addListenerForSingleValueEvent(new ValueEventListener() {
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

                                                companyRef.child(post_key).child("Budget").child("IGV Payments "+year).addListenerForSingleValueEvent(new ValueEventListener() {
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

                                                        companyRef.child(post_key).child("Budget").child("Purchases "+year).addListenerForSingleValueEvent(new ValueEventListener() {
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

                                                                companyRef.child(post_key).child("Budget").child("Financial Outcomes "+year).addListenerForSingleValueEvent(new ValueEventListener() {
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

                                                                        companyRef.child(post_key).child("Budget").child("Other Outcomes "+year).addListenerForSingleValueEvent(new ValueEventListener() {
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

                                                                                calculateMonth1();

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
    }

    double cash_incomes_1_real,cash_incomes_2_real,cash_incomes_3_real,cash_incomes_4_real,cash_incomes_5_real,cash_incomes_6_real,cash_incomes_7_real,cash_incomes_8_real,cash_incomes_9_real,cash_incomes_10_real,cash_incomes_11_real,cash_incomes_12_real,
            credit_incomes_1_real,credit_incomes_2_real,credit_incomes_3_real,credit_incomes_4_real,credit_incomes_5_real,credit_incomes_6_real,credit_incomes_7_real,credit_incomes_8_real,credit_incomes_9_real,credit_incomes_10_real,credit_incomes_11_real,credit_incomes_12_real,
            total_salary_1_real,total_salary_2_real,total_salary_3_real,total_salary_4_real,total_salary_5_real,total_salary_6_real,total_salary_7_real,total_salary_8_real,total_salary_9_real,total_salary_10_real,total_salary_11_real,total_salary_12_real,
            total_incomes_1_real,total_incomes_2_real,total_incomes_3_real,total_incomes_4_real,total_incomes_5_real,total_incomes_6_real,total_incomes_7_real,total_incomes_8_real,total_incomes_9_real,total_incomes_10_real,total_incomes_11_real,total_incomes_12_real,
            purchase_total_1_real,purchase_total_2_real,purchase_total_3_real,purchase_total_4_real,purchase_total_5_real,purchase_total_6_real,purchase_total_7_real,purchase_total_8_real,purchase_total_9_real,purchase_total_10_real,purchase_total_11_real,purchase_total_12_real,
            financial_incomes_1_real,financial_incomes_2_real,financial_incomes_3_real,financial_incomes_4_real,financial_incomes_5_real,financial_incomes_6_real,financial_incomes_7_real,financial_incomes_8_real,financial_incomes_9_real,financial_incomes_10_real,financial_incomes_11_real,financial_incomes_12_real,
            igv_taxes_1_real,igv_taxes_2_real,igv_taxes_3_real,igv_taxes_4_real,igv_taxes_5_real,igv_taxes_6_real,igv_taxes_7_real,igv_taxes_8_real,igv_taxes_9_real,igv_taxes_10_real,igv_taxes_11_real,igv_taxes_12_real,
            other_incomes_1_real,other_incomes_2_real,other_incomes_3_real,other_incomes_4_real,other_incomes_5_real,other_incomes_6_real,other_incomes_7_real,other_incomes_8_real,other_incomes_9_real,other_incomes_10_real,other_incomes_11_real,other_incomes_12_real,
            financial_outcomes_1_real,financial_outcomes_2_real,financial_outcomes_3_real,financial_outcomes_4_real,financial_outcomes_5_real,financial_outcomes_6_real,financial_outcomes_7_real,financial_outcomes_8_real,financial_outcomes_9_real,financial_outcomes_10_real,financial_outcomes_11_real,financial_outcomes_12_real,
            other_outcomes_1_real,other_outcomes_2_real,other_outcomes_3_real,other_outcomes_4_real,other_outcomes_5_real,other_outcomes_6_real,other_outcomes_7_real,other_outcomes_8_real,other_outcomes_9_real,other_outcomes_10_real,other_outcomes_11_real,other_outcomes_12_real;
    String cash_incomes_1_st_real,cash_incomes_2_st_real,cash_incomes_3_st_real,cash_incomes_4_st_real,cash_incomes_5_st_real,cash_incomes_6_st_real,cash_incomes_7_st_real,cash_incomes_8_st_real,cash_incomes_9_st_real,cash_incomes_10_st_real,cash_incomes_11_st_real,cash_incomes_12_st_real,
            credit_incomes_1_st_real,credit_incomes_2_st_real,credit_incomes_3_st_real,credit_incomes_4_st_real,credit_incomes_5_st_real,credit_incomes_6_st_real,credit_incomes_7_st_real,credit_incomes_8_st_real,credit_incomes_9_st_real,credit_incomes_10_st_real,credit_incomes_11_st_real,credit_incomes_12_st_real,
            purchase_total_1_st_real,purchase_total_2_st_real,purchase_total_3_st_real,purchase_total_4_st_real,purchase_total_5_st_real,purchase_total_6_st_real,purchase_total_7_st_real,purchase_total_8_st_real,purchase_total_9_st_real,purchase_total_10_st_real,purchase_total_11_st_real,purchase_total_12_st_real,
            igv_taxes_1_st_real,igv_taxes_2_st_real,igv_taxes_3_st_real,igv_taxes_4_st_real,igv_taxes_5_st_real,igv_taxes_6_st_real,igv_taxes_7_st_real,igv_taxes_8_st_real,igv_taxes_9_st_real,igv_taxes_10_st_real,igv_taxes_11_st_real,igv_taxes_12_st_real;

    private void calculateMonth1() {
        companyRef.child(post_key).child("My Bills").orderByChild("issuing_year").equalTo(year).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                cash_incomes_1_real = 0;
                credit_incomes_1_real = 0;
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
                            cash_incomes_1_real += bill_amount_db;
                            cash_incomes_1_st_real = decimalFormat.format(cash_incomes_1_real);
                            txtCashSalesReal1.setText(cash_incomes_1_st_real);
                        }


                    }
                    if (state.equals("paid") && !issuing_date.equals(expiration_date)) {
                        if (expiration_month.equals("1")) {
                            Map<String, Object> map = (Map<String, Object>) ds.getValue();
                            Object bill_amount = map.get("bill_amount");
                            double bill_amount_db = Double.parseDouble(String.valueOf(bill_amount));
                            credit_incomes_1_real += bill_amount_db;
                            credit_incomes_1_st_real = decimalFormat.format(credit_incomes_1_real);
                            txtCreditSalesReal1.setText(credit_incomes_1_st_real);
                        }


                    }

                    double total_incomes_1_real = cash_incomes_1_real+credit_incomes_1_real;
                    String total_incomes_1_st_real = decimalFormat.format(total_incomes_1_real);
                    txtTotalIncomesReal1.setText(total_incomes_1_st_real);

                    double cash_sales_var = ((cash_incomes_1_real-cash_incomes_1)/cash_incomes_1)*100;
                    String cash_sales_var_st = decimalFormat.format(cash_sales_var);
                    txtCashSalesDiff1.setText(cash_sales_var_st+"%");
                    if (cash_sales_var < 0) {
                        txtCashSalesDiff1.setTextColor(Color.RED);
                    }

                    double credit_sales_var = ((credit_incomes_1_real-credit_incomes_1)/credit_incomes_1)*100;
                    String credit_sales_var_st = decimalFormat.format(credit_sales_var);
                    txtCreditSalesDiff1.setText(credit_sales_var_st+"%");
                    if (credit_sales_var < 0) {
                        txtCreditSalesDiff1.setTextColor(Color.RED);
                    }


                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        companyRef.child(post_key).child("Worker Bills").orderByChild("operation_year").equalTo(year+"").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                total_salary_1_real = 0;
                for (DataSnapshot ds : dataSnapshot.getChildren()) {

                    String operation_month = ds.child("operation_month").getValue().toString();

                    if (operation_month.equals("1")) {
                        Map<String, Object> map = (Map<String, Object>) ds.getValue();
                        Object total_payment = map.get("total_payment");
                        double total_payment_db = Double.parseDouble(String.valueOf(total_payment));
                        total_salary_1_real += total_payment_db;
                        String total_salary_1_st_real = decimalFormat.format(total_salary_1_real);
                        txtWorkerPaymentReal1.setText(total_salary_1_st_real);
                    }

                    double salary_var = ((total_salary_1_real-total_salary_1)/total_salary_1)*100;
                    String salary_var_var_st = decimalFormat.format(salary_var);
                    txtWorkerPaymentDiff1.setText(salary_var_var_st+"%");
                    if (salary_var < 0) {
                        txtWorkerPaymentDiff1.setTextColor(Color.RED);
                    }


                }

                companyRef.child(post_key).child("Purchased Orders").orderByChild("operation_year").equalTo(year+"").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        purchase_total_1_real = 0;
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
                                    purchase_total_1_real += purchase_order_total_amount_db;
                                    purchase_total_1_st_real = decimalFormat.format(purchase_total_1_real);
                                    txtPurchasesReal1.setText(purchase_total_1_st_real);
                                }

                            }

                        }

                        total_incomes_1_real = cash_incomes_1_real+credit_incomes_1_real+financial_incomes_1_real+other_incomes_1_real;
                        igv_taxes_1_real = (total_incomes_1_real*0.18)-(purchase_total_1_real*0.18);
                        igv_taxes_1_st_real = decimalFormat.format(igv_taxes_1_real);
                        txtIgvReal1.setText(igv_taxes_1_st_real);

                        double total_outcomes_1_real = total_salary_1_real+purchase_total_1_real+igv_taxes_1_real+financial_outcomes_1_real+other_outcomes_1_real;
                        String total_outcomes_1_st_real = decimalFormat.format(total_outcomes_1_real);
                        txtTotalOutcomesReal1.setText(total_outcomes_1_st_real);

                        double igv_var = ((igv_taxes_1_real-igv_taxes_1)/igv_taxes_1)*100;
                        String igv_var_st = decimalFormat.format(igv_var);
                        txtIgvDiff1.setText(igv_var_st+"%");
                        if (igv_var < 0) {
                            txtIgvDiff1.setTextColor(Color.RED);
                        }

                        double purchase_var = ((purchase_total_1_real-purchase_total_1)/purchase_total_1)*100;
                        String purchase_var_st = decimalFormat.format(purchase_var);
                        txtPurchasesDiff1.setText(purchase_var_st+"%");
                        if (igv_var < 0) {
                            txtPurchasesDiff1.setTextColor(Color.RED);
                        }

                        double incomes_var = ((total_incomes_1_real-total_incomes_1)/total_incomes_1)*100;
                        String incomes_var_st = decimalFormat.format(incomes_var);
                        txtTotalIncomesDiff1.setText(incomes_var_st+"%");
                        if (incomes_var < 0) {
                            txtTotalIncomesDiff1.setTextColor(Color.RED);
                        }

                        double outcomes_var = ((total_outcomes_1_real-total_outcomes_1)/total_outcomes_1)*100;
                        String outcomes_var_st = decimalFormat.format(outcomes_var);
                        txtTotalOutcomesDiff1.setText(outcomes_var_st+"%");
                        if (outcomes_var < 0) {
                            txtTotalOutcomesDiff1.setTextColor(Color.RED);
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

    private void calculateMonth2() {
        companyRef.child(post_key).child("My Bills").orderByChild("issuing_year").equalTo(year).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                cash_incomes_2_real = 0;
                credit_incomes_2_real = 0;
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
                            cash_incomes_2_real += bill_amount_db;
                            cash_incomes_2_st_real = decimalFormat.format(cash_incomes_2_real);
                            txtCashSalesReal2.setText(cash_incomes_2_st_real);
                        }


                    }
                    if (state.equals("paid") && !issuing_date.equals(expiration_date)) {
                        if (expiration_month.equals("2")) {
                            Map<String, Object> map = (Map<String, Object>) ds.getValue();
                            Object bill_amount = map.get("bill_amount");
                            double bill_amount_db = Double.parseDouble(String.valueOf(bill_amount));
                            credit_incomes_2_real += bill_amount_db;
                            credit_incomes_2_st_real = decimalFormat.format(credit_incomes_2_real);
                            txtCreditSalesReal2.setText(credit_incomes_2_st_real);
                        }


                    }

                    double total_incomes_2_real = cash_incomes_2_real+credit_incomes_2_real;
                    String total_incomes_2_st_real = decimalFormat.format(total_incomes_2_real);
                    txtTotalIncomesReal2.setText(total_incomes_2_st_real);

                    double cash_sales_var = ((cash_incomes_2_real-cash_incomes_2)/cash_incomes_2)*100;
                    String cash_sales_var_st = decimalFormat.format(cash_sales_var);
                    txtCashSalesDiff2.setText(cash_sales_var_st+"%");
                    if (cash_sales_var < 0) {
                        txtCashSalesDiff2.setTextColor(Color.RED);
                    }

                    double credit_sales_var = ((credit_incomes_2_real-credit_incomes_2)/credit_incomes_2)*100;
                    String credit_sales_var_st = decimalFormat.format(credit_sales_var);
                    txtCreditSalesDiff2.setText(credit_sales_var_st+"%");
                    if (credit_sales_var < 0) {
                        txtCreditSalesDiff2.setTextColor(Color.RED);
                    }

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        companyRef.child(post_key).child("Worker Bills").orderByChild("operation_year").equalTo(year+"").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                total_salary_2_real = 0;
                for (DataSnapshot ds : dataSnapshot.getChildren()) {

                    String operation_month = ds.child("operation_month").getValue().toString();

                    if (operation_month.equals("2")) {
                        Map<String, Object> map = (Map<String, Object>) ds.getValue();
                        Object total_payment = map.get("total_payment");
                        double total_payment_db = Double.parseDouble(String.valueOf(total_payment));
                        total_salary_2_real += total_payment_db;
                        String total_salary_2_st_real = decimalFormat.format(total_salary_2_real);
                        txtWorkerPaymentReal2.setText(total_salary_2_st_real);
                    }

                    double salary_var = ((total_salary_2_real-total_salary_2)/total_salary_2)*100;
                    String salary_var_var_st = decimalFormat.format(salary_var);
                    txtWorkerPaymentDiff2.setText(salary_var_var_st+"%");
                    if (salary_var < 0) {
                        txtWorkerPaymentDiff2.setTextColor(Color.RED);
                    }


                }

                companyRef.child(post_key).child("Purchased Orders").orderByChild("operation_year").equalTo(year+"").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        purchase_total_2_real = 0;
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
                                    purchase_total_2_real += purchase_order_total_amount_db;
                                    purchase_total_2_st_real = decimalFormat.format(purchase_total_2_real);
                                    txtPurchasesReal2.setText(purchase_total_2_st_real);
                                }

                            }

                        }

                        total_incomes_2_real = cash_incomes_2_real+credit_incomes_2_real+financial_incomes_2_real+other_incomes_2_real;
                        igv_taxes_2_real = (total_incomes_2_real*0.18)-(purchase_total_2_real*0.18);
                        igv_taxes_2_st_real = decimalFormat.format(igv_taxes_2_real);
                        txtIgvReal2.setText(igv_taxes_2_st_real);

                        double total_outcomes_2_real = total_salary_2_real+purchase_total_2_real+igv_taxes_2_real+financial_outcomes_2_real+other_outcomes_2_real;
                        String total_outcomes_2_st_real = decimalFormat.format(total_outcomes_2_real);
                        txtTotalOutcomesReal2.setText(total_outcomes_2_st_real);

                        double igv_var = ((igv_taxes_2_real-igv_taxes_2)/igv_taxes_2)*100;
                        String igv_var_st = decimalFormat.format(igv_var);
                        txtIgvDiff2.setText(igv_var_st+"%");
                        if (igv_var < 0) {
                            txtIgvDiff2.setTextColor(Color.RED);
                        }

                        double purchase_var = ((purchase_total_2_real-purchase_total_2)/purchase_total_2)*100;
                        String purchase_var_st = decimalFormat.format(purchase_var);
                        txtPurchasesDiff2.setText(purchase_var_st+"%");
                        if (igv_var < 0) {
                            txtPurchasesDiff2.setTextColor(Color.RED);
                        }

                        double incomes_var = ((total_incomes_2_real-total_incomes_2)/total_incomes_2)*100;
                        String incomes_var_st = decimalFormat.format(incomes_var);
                        txtTotalIncomesDiff2.setText(incomes_var_st+"%");
                        if (incomes_var < 0) {
                            txtTotalIncomesDiff2.setTextColor(Color.RED);
                        }

                        double outcomes_var = ((total_outcomes_2_real-total_outcomes_2)/total_outcomes_2)*100;
                        String outcomes_var_st = decimalFormat.format(outcomes_var);
                        txtTotalOutcomesDiff2.setText(outcomes_var_st+"%");
                        if (outcomes_var < 0) {
                            txtTotalOutcomesDiff2.setTextColor(Color.RED);
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

    private void calculateMonth3() {
        companyRef.child(post_key).child("My Bills").orderByChild("issuing_year").equalTo(year).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                cash_incomes_3_real = 0;
                credit_incomes_3_real = 0;
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
                            cash_incomes_3_real += bill_amount_db;
                            cash_incomes_3_st_real = decimalFormat.format(cash_incomes_3_real);
                            txtCashSalesReal3.setText(cash_incomes_3_st_real);
                        }


                    }
                    if (state.equals("paid") && !issuing_date.equals(expiration_date)) {
                        if (expiration_month.equals("3")) {
                            Map<String, Object> map = (Map<String, Object>) ds.getValue();
                            Object bill_amount = map.get("bill_amount");
                            double bill_amount_db = Double.parseDouble(String.valueOf(bill_amount));
                            credit_incomes_3_real += bill_amount_db;
                            credit_incomes_3_st_real = decimalFormat.format(credit_incomes_3_real);
                            txtCreditSalesReal3.setText(credit_incomes_3_st_real);
                        }


                    }

                    double total_incomes_3_real = cash_incomes_3_real+credit_incomes_3_real;
                    String total_incomes_3_st_real = decimalFormat.format(total_incomes_3_real);
                    txtTotalIncomesReal3.setText(total_incomes_3_st_real);

                    double cash_sales_var = ((cash_incomes_3_real-cash_incomes_3)/cash_incomes_3)*100;
                    String cash_sales_var_st = decimalFormat.format(cash_sales_var);
                    txtCashSalesDiff3.setText(cash_sales_var_st+"%");
                    if (cash_sales_var < 0) {
                        txtCashSalesDiff3.setTextColor(Color.RED);
                    }

                    double credit_sales_var = ((credit_incomes_3_real-credit_incomes_3)/credit_incomes_3)*100;
                    String credit_sales_var_st = decimalFormat.format(credit_sales_var);
                    txtCreditSalesDiff3.setText(credit_sales_var_st+"%");
                    if (credit_sales_var < 0) {
                        txtCreditSalesDiff3.setTextColor(Color.RED);
                    }

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        companyRef.child(post_key).child("Worker Bills").orderByChild("operation_year").equalTo(year+"").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                total_salary_3_real = 0;
                for (DataSnapshot ds : dataSnapshot.getChildren()) {

                    String operation_month = ds.child("operation_month").getValue().toString();

                    if (operation_month.equals("3")) {
                        Map<String, Object> map = (Map<String, Object>) ds.getValue();
                        Object total_payment = map.get("total_payment");
                        double total_payment_db = Double.parseDouble(String.valueOf(total_payment));
                        total_salary_3_real += total_payment_db;
                        String total_salary_3_st_real = decimalFormat.format(total_salary_3_real);
                        txtWorkerPaymentReal3.setText(total_salary_3_st_real);
                    }

                    double salary_var = ((total_salary_3_real-total_salary_3)/total_salary_3)*100;
                    String salary_var_var_st = decimalFormat.format(salary_var);
                    txtWorkerPaymentDiff3.setText(salary_var_var_st+"%");
                    if (salary_var < 0) {
                        txtWorkerPaymentDiff3.setTextColor(Color.RED);
                    }


                }

                companyRef.child(post_key).child("Purchased Orders").orderByChild("operation_year").equalTo(year+"").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        purchase_total_3_real = 0;
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
                                    purchase_total_3_real += purchase_order_total_amount_db;
                                    purchase_total_3_st_real = decimalFormat.format(purchase_total_3_real);
                                    txtPurchasesReal3.setText(purchase_total_3_st_real);
                                }

                            }

                        }

                        total_incomes_3_real = cash_incomes_3_real+credit_incomes_3_real+financial_incomes_3_real+other_incomes_3_real;
                        igv_taxes_3_real = (total_incomes_3_real*0.18)-(purchase_total_3_real*0.18);
                        igv_taxes_3_st_real = decimalFormat.format(igv_taxes_3_real);
                        txtIgvReal3.setText(igv_taxes_3_st_real);

                        double total_outcomes_3_real = total_salary_3_real+purchase_total_3_real+igv_taxes_3_real+financial_outcomes_3_real+other_outcomes_3_real;
                        String total_outcomes_3_st_real = decimalFormat.format(total_outcomes_3_real);
                        txtTotalOutcomesReal3.setText(total_outcomes_3_st_real);

                        double igv_var = ((igv_taxes_3_real-igv_taxes_3)/igv_taxes_3)*100;
                        String igv_var_st = decimalFormat.format(igv_var);
                        txtIgvDiff3.setText(igv_var_st+"%");
                        if (igv_var < 0) {
                            txtIgvDiff3.setTextColor(Color.RED);
                        }

                        double purchase_var = ((purchase_total_3_real-purchase_total_3)/purchase_total_3)*100;
                        String purchase_var_st = decimalFormat.format(purchase_var);
                        txtPurchasesDiff3.setText(purchase_var_st+"%");
                        if (igv_var < 0) {
                            txtPurchasesDiff3.setTextColor(Color.RED);
                        }

                        double incomes_var = ((total_incomes_3_real-total_incomes_3)/total_incomes_3)*100;
                        String incomes_var_st = decimalFormat.format(incomes_var);
                        txtTotalIncomesDiff3.setText(incomes_var_st+"%");
                        if (incomes_var < 0) {
                            txtTotalIncomesDiff3.setTextColor(Color.RED);
                        }

                        double outcomes_var = ((total_outcomes_3_real-total_outcomes_3)/total_outcomes_3)*100;
                        String outcomes_var_st = decimalFormat.format(outcomes_var);
                        txtTotalOutcomesDiff3.setText(outcomes_var_st+"%");
                        if (outcomes_var < 0) {
                            txtTotalOutcomesDiff3.setTextColor(Color.RED);
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

    private void calculateMonth4() {
        companyRef.child(post_key).child("My Bills").orderByChild("issuing_year").equalTo(year).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                cash_incomes_4_real = 0;
                credit_incomes_4_real = 0;
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
                            cash_incomes_4_real += bill_amount_db;
                            cash_incomes_4_st_real = decimalFormat.format(cash_incomes_4_real);
                            txtCashSalesReal4.setText(cash_incomes_4_st_real);
                        }


                    }
                    if (state.equals("paid") && !issuing_date.equals(expiration_date)) {
                        if (expiration_month.equals("4")) {
                            Map<String, Object> map = (Map<String, Object>) ds.getValue();
                            Object bill_amount = map.get("bill_amount");
                            double bill_amount_db = Double.parseDouble(String.valueOf(bill_amount));
                            credit_incomes_4_real += bill_amount_db;
                            credit_incomes_4_st_real = decimalFormat.format(credit_incomes_4_real);
                            txtCreditSalesReal4.setText(credit_incomes_4_st_real);
                        }


                    }

                    double total_incomes_4_real = cash_incomes_4_real+credit_incomes_4_real;
                    String total_incomes_4_st_real = decimalFormat.format(total_incomes_4_real);
                    txtTotalIncomesReal4.setText(total_incomes_4_st_real);

                    double cash_sales_var = ((cash_incomes_4_real-cash_incomes_4)/cash_incomes_4)*100;
                    String cash_sales_var_st = decimalFormat.format(cash_sales_var);
                    txtCashSalesDiff4.setText(cash_sales_var_st+"%");
                    if (cash_sales_var < 0) {
                        txtCashSalesDiff4.setTextColor(Color.RED);
                    }

                    double credit_sales_var = ((credit_incomes_4_real-credit_incomes_4)/credit_incomes_4)*100;
                    String credit_sales_var_st = decimalFormat.format(credit_sales_var);
                    txtCreditSalesDiff4.setText(credit_sales_var_st+"%");
                    if (credit_sales_var < 0) {
                        txtCreditSalesDiff4.setTextColor(Color.RED);
                    }

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        companyRef.child(post_key).child("Worker Bills").orderByChild("operation_year").equalTo(year+"").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                total_salary_4_real = 0;
                for (DataSnapshot ds : dataSnapshot.getChildren()) {

                    String operation_month = ds.child("operation_month").getValue().toString();

                    if (operation_month.equals("4")) {
                        Map<String, Object> map = (Map<String, Object>) ds.getValue();
                        Object total_payment = map.get("total_payment");
                        double total_payment_db = Double.parseDouble(String.valueOf(total_payment));
                        total_salary_4_real += total_payment_db;
                        String total_salary_4_st_real = decimalFormat.format(total_salary_4_real);
                        txtWorkerPaymentReal4.setText(total_salary_4_st_real);
                    }

                    double salary_var = ((total_salary_4_real-total_salary_4)/total_salary_4)*100;
                    String salary_var_var_st = decimalFormat.format(salary_var);
                    txtWorkerPaymentDiff4.setText(salary_var_var_st+"%");
                    if (salary_var < 0) {
                        txtWorkerPaymentDiff4.setTextColor(Color.RED);
                    }


                }

                companyRef.child(post_key).child("Purchased Orders").orderByChild("operation_year").equalTo(year+"").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        purchase_total_4_real = 0;
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
                                    purchase_total_4_real += purchase_order_total_amount_db;
                                    purchase_total_4_st_real = decimalFormat.format(purchase_total_4_real);
                                    txtPurchasesReal4.setText(purchase_total_4_st_real);
                                }

                            }

                        }

                        total_incomes_4_real = cash_incomes_4_real+credit_incomes_4_real+financial_incomes_4_real+other_incomes_4_real;
                        igv_taxes_4_real = (total_incomes_4_real*0.18)-(purchase_total_4_real*0.18);
                        igv_taxes_4_st_real = decimalFormat.format(igv_taxes_4_real);
                        txtIgvReal4.setText(igv_taxes_4_st_real);

                        double total_outcomes_4_real = total_salary_4_real+purchase_total_4_real+igv_taxes_4_real+financial_outcomes_4_real+other_outcomes_4_real;
                        String total_outcomes_4_st_real = decimalFormat.format(total_outcomes_4_real);
                        txtTotalOutcomesReal4.setText(total_outcomes_4_st_real);

                        double igv_var = ((igv_taxes_4_real-igv_taxes_4)/igv_taxes_4)*100;
                        String igv_var_st = decimalFormat.format(igv_var);
                        txtIgvDiff4.setText(igv_var_st+"%");
                        if (igv_var < 0) {
                            txtIgvDiff4.setTextColor(Color.RED);
                        }

                        double purchase_var = ((purchase_total_4_real-purchase_total_4)/purchase_total_4)*100;
                        String purchase_var_st = decimalFormat.format(purchase_var);
                        txtPurchasesDiff4.setText(purchase_var_st+"%");
                        if (igv_var < 0) {
                            txtPurchasesDiff4.setTextColor(Color.RED);
                        }

                        double incomes_var = ((total_incomes_4_real-total_incomes_4)/total_incomes_4)*100;
                        String incomes_var_st = decimalFormat.format(incomes_var);
                        txtTotalIncomesDiff4.setText(incomes_var_st+"%");
                        if (incomes_var < 0) {
                            txtTotalIncomesDiff4.setTextColor(Color.RED);
                        }

                        double outcomes_var = ((total_outcomes_4_real-total_outcomes_4)/total_outcomes_4)*100;
                        String outcomes_var_st = decimalFormat.format(outcomes_var);
                        txtTotalOutcomesDiff4.setText(outcomes_var_st+"%");
                        if (outcomes_var < 0) {
                            txtTotalOutcomesDiff4.setTextColor(Color.RED);
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

    private void calculateMonth5() {
        companyRef.child(post_key).child("My Bills").orderByChild("issuing_year").equalTo(year).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                cash_incomes_5_real = 0;
                credit_incomes_5_real = 0;
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
                            cash_incomes_5_real += bill_amount_db;
                            cash_incomes_5_st_real = decimalFormat.format(cash_incomes_5_real);
                            txtCashSalesReal5.setText(cash_incomes_5_st_real);
                        }


                    }
                    if (state.equals("paid") && !issuing_date.equals(expiration_date)) {
                        if (expiration_month.equals("5")) {
                            Map<String, Object> map = (Map<String, Object>) ds.getValue();
                            Object bill_amount = map.get("bill_amount");
                            double bill_amount_db = Double.parseDouble(String.valueOf(bill_amount));
                            credit_incomes_5_real += bill_amount_db;
                            credit_incomes_5_st_real = decimalFormat.format(credit_incomes_5_real);
                            txtCreditSalesReal5.setText(credit_incomes_5_st_real);
                        }


                    }

                    double total_incomes_5_real = cash_incomes_5_real+credit_incomes_5_real;
                    String total_incomes_5_st_real = decimalFormat.format(total_incomes_5_real);
                    txtTotalIncomesReal5.setText(total_incomes_5_st_real);

                    double cash_sales_var = ((cash_incomes_5_real-cash_incomes_5)/cash_incomes_5)*100;
                    String cash_sales_var_st = decimalFormat.format(cash_sales_var);
                    txtCashSalesDiff5.setText(cash_sales_var_st+"%");
                    if (cash_sales_var < 0) {
                        txtCashSalesDiff5.setTextColor(Color.RED);
                    }

                    double credit_sales_var = ((credit_incomes_5_real-credit_incomes_5)/credit_incomes_5)*100;
                    String credit_sales_var_st = decimalFormat.format(credit_sales_var);
                    txtCreditSalesDiff5.setText(credit_sales_var_st+"%");
                    if (credit_sales_var < 0) {
                        txtCreditSalesDiff5.setTextColor(Color.RED);
                    }

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        companyRef.child(post_key).child("Worker Bills").orderByChild("operation_year").equalTo(year+"").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                total_salary_5_real = 0;
                for (DataSnapshot ds : dataSnapshot.getChildren()) {

                    String operation_month = ds.child("operation_month").getValue().toString();

                    if (operation_month.equals("5")) {
                        Map<String, Object> map = (Map<String, Object>) ds.getValue();
                        Object total_payment = map.get("total_payment");
                        double total_payment_db = Double.parseDouble(String.valueOf(total_payment));
                        total_salary_5_real += total_payment_db;
                        String total_salary_5_st_real = decimalFormat.format(total_salary_5_real);
                        txtWorkerPaymentReal5.setText(total_salary_5_st_real);
                    }

                    double salary_var = ((total_salary_5_real-total_salary_5)/total_salary_5)*100;
                    String salary_var_var_st = decimalFormat.format(salary_var);
                    txtWorkerPaymentDiff5.setText(salary_var_var_st+"%");
                    if (salary_var < 0) {
                        txtWorkerPaymentDiff5.setTextColor(Color.RED);
                    }

                }

                companyRef.child(post_key).child("Purchased Orders").orderByChild("operation_year").equalTo(year+"").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        purchase_total_5_real = 0;
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
                                    purchase_total_5_real += purchase_order_total_amount_db;
                                    purchase_total_5_st_real = decimalFormat.format(purchase_total_5_real);
                                    txtPurchasesReal5.setText(purchase_total_5_st_real);
                                }

                            }

                        }

                        total_incomes_5_real = cash_incomes_5_real+credit_incomes_5_real+financial_incomes_5_real+other_incomes_5_real;
                        igv_taxes_5_real = (total_incomes_5_real*0.18)-(purchase_total_5_real*0.18);
                        igv_taxes_5_st_real = decimalFormat.format(igv_taxes_5_real);
                        txtIgvReal5.setText(igv_taxes_5_st_real);

                        double total_outcomes_5_real = total_salary_5_real+purchase_total_5_real+igv_taxes_5_real+financial_outcomes_5_real+other_outcomes_5_real;
                        String total_outcomes_5_st_real = decimalFormat.format(total_outcomes_5_real);
                        txtTotalOutcomesReal5.setText(total_outcomes_5_st_real);

                        double igv_var = ((igv_taxes_5_real-igv_taxes_5)/igv_taxes_5)*100;
                        String igv_var_st = decimalFormat.format(igv_var);
                        txtIgvDiff5.setText(igv_var_st+"%");
                        if (igv_var < 0) {
                            txtIgvDiff5.setTextColor(Color.RED);
                        }

                        double purchase_var = ((purchase_total_5_real-purchase_total_5)/purchase_total_5)*100;
                        String purchase_var_st = decimalFormat.format(purchase_var);
                        txtPurchasesDiff5.setText(purchase_var_st+"%");
                        if (igv_var < 0) {
                            txtPurchasesDiff5.setTextColor(Color.RED);
                        }

                        double incomes_var = ((total_incomes_5_real-total_incomes_5)/total_incomes_5)*100;
                        String incomes_var_st = decimalFormat.format(incomes_var);
                        txtTotalIncomesDiff5.setText(incomes_var_st+"%");
                        if (incomes_var < 0) {
                            txtTotalIncomesDiff5.setTextColor(Color.RED);
                        }

                        double outcomes_var = ((total_outcomes_5_real-total_outcomes_5)/total_outcomes_5)*100;
                        String outcomes_var_st = decimalFormat.format(outcomes_var);
                        txtTotalOutcomesDiff5.setText(outcomes_var_st+"%");
                        if (outcomes_var < 0) {
                            txtTotalOutcomesDiff5.setTextColor(Color.RED);
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

    private void calculateMonth6() {
        companyRef.child(post_key).child("My Bills").orderByChild("issuing_year").equalTo(year).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                cash_incomes_6_real = 0;
                credit_incomes_6_real = 0;
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
                            cash_incomes_6_real += bill_amount_db;
                            cash_incomes_6_st_real = decimalFormat.format(cash_incomes_6_real);
                            txtCashSalesReal6.setText(cash_incomes_6_st_real);
                        }


                    }
                    if (state.equals("paid") && !issuing_date.equals(expiration_date)) {
                        if (expiration_month.equals("6")) {
                            Map<String, Object> map = (Map<String, Object>) ds.getValue();
                            Object bill_amount = map.get("bill_amount");
                            double bill_amount_db = Double.parseDouble(String.valueOf(bill_amount));
                            credit_incomes_6_real += bill_amount_db;
                            credit_incomes_6_st_real = decimalFormat.format(credit_incomes_6_real);
                            txtCreditSalesReal6.setText(credit_incomes_6_st_real);
                        }


                    }

                    double total_incomes_6_real = cash_incomes_6_real+credit_incomes_6_real;
                    String total_incomes_6_st_real = decimalFormat.format(total_incomes_6_real);
                    txtTotalIncomesReal6.setText(total_incomes_6_st_real);

                    double cash_sales_var = ((cash_incomes_6_real-cash_incomes_6)/cash_incomes_6)*100;
                    String cash_sales_var_st = decimalFormat.format(cash_sales_var);
                    txtCashSalesDiff6.setText(cash_sales_var_st+"%");
                    if (cash_sales_var < 0) {
                        txtCashSalesDiff6.setTextColor(Color.RED);
                    }

                    double credit_sales_var = ((credit_incomes_6_real-credit_incomes_6)/credit_incomes_6)*100;
                    String credit_sales_var_st = decimalFormat.format(credit_sales_var);
                    txtCreditSalesDiff6.setText(credit_sales_var_st+"%");
                    if (credit_sales_var < 0) {
                        txtCreditSalesDiff6.setTextColor(Color.RED);
                    }

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        companyRef.child(post_key).child("Worker Bills").orderByChild("operation_year").equalTo(year+"").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                total_salary_6_real = 0;
                for (DataSnapshot ds : dataSnapshot.getChildren()) {

                    String operation_month = ds.child("operation_month").getValue().toString();

                    if (operation_month.equals("6")) {
                        Map<String, Object> map = (Map<String, Object>) ds.getValue();
                        Object total_payment = map.get("total_payment");
                        double total_payment_db = Double.parseDouble(String.valueOf(total_payment));
                        total_salary_6_real += total_payment_db;
                        String total_salary_6_st_real = decimalFormat.format(total_salary_6_real);
                        txtWorkerPaymentReal6.setText(total_salary_6_st_real);
                    }

                    double salary_var = ((total_salary_6_real-total_salary_6)/total_salary_6)*100;
                    String salary_var_var_st = decimalFormat.format(salary_var);
                    txtWorkerPaymentDiff6.setText(salary_var_var_st+"%");
                    if (salary_var < 0) {
                        txtWorkerPaymentDiff6.setTextColor(Color.RED);
                    }


                }

                companyRef.child(post_key).child("Purchased Orders").orderByChild("operation_year").equalTo(year+"").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        purchase_total_6_real = 0;
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
                                    purchase_total_6_real += purchase_order_total_amount_db;
                                    purchase_total_6_st_real = decimalFormat.format(purchase_total_6_real);
                                    txtPurchasesReal6.setText(purchase_total_6_st_real);
                                }

                            }

                        }

                        total_incomes_6_real = cash_incomes_6_real+credit_incomes_6_real+financial_incomes_6_real+other_incomes_6_real;
                        igv_taxes_6_real = (total_incomes_6_real*0.18)-(purchase_total_6_real*0.18);
                        igv_taxes_6_st_real = decimalFormat.format(igv_taxes_6_real);
                        txtIgvReal6.setText(igv_taxes_6_st_real);

                        double total_outcomes_6_real = total_salary_6_real+purchase_total_6_real+igv_taxes_6_real+financial_outcomes_6_real+other_outcomes_6_real;
                        String total_outcomes_6_st_real = decimalFormat.format(total_outcomes_6_real);
                        txtTotalOutcomesReal6.setText(total_outcomes_6_st_real);

                        double igv_var = ((igv_taxes_6_real-igv_taxes_6)/igv_taxes_6)*100;
                        String igv_var_st = decimalFormat.format(igv_var);
                        txtIgvDiff6.setText(igv_var_st+"%");
                        if (igv_var < 0) {
                            txtIgvDiff6.setTextColor(Color.RED);
                        }

                        double purchase_var = ((purchase_total_6_real-purchase_total_6)/purchase_total_6)*100;
                        String purchase_var_st = decimalFormat.format(purchase_var);
                        txtPurchasesDiff6.setText(purchase_var_st+"%");
                        if (igv_var < 0) {
                            txtPurchasesDiff6.setTextColor(Color.RED);
                        }

                        double incomes_var = ((total_incomes_6_real-total_incomes_6)/total_incomes_6)*100;
                        String incomes_var_st = decimalFormat.format(incomes_var);
                        txtTotalIncomesDiff6.setText(incomes_var_st+"%");
                        if (incomes_var < 0) {
                            txtTotalIncomesDiff6.setTextColor(Color.RED);
                        }

                        double outcomes_var = ((total_outcomes_6_real-total_outcomes_6)/total_outcomes_6)*100;
                        String outcomes_var_st = decimalFormat.format(outcomes_var);
                        txtTotalOutcomesDiff6.setText(outcomes_var_st+"%");
                        if (outcomes_var < 0) {
                            txtTotalOutcomesDiff6.setTextColor(Color.RED);
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

    private void calculateMonth7() {
        companyRef.child(post_key).child("My Bills").orderByChild("issuing_year").equalTo(year).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                cash_incomes_7_real = 0;
                credit_incomes_7_real = 0;
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
                            cash_incomes_7_real += bill_amount_db;
                            cash_incomes_7_st_real = decimalFormat.format(cash_incomes_7_real);
                            txtCashSalesReal7.setText(cash_incomes_7_st_real);
                        }


                    }
                    if (state.equals("paid") && !issuing_date.equals(expiration_date)) {
                        if (expiration_month.equals("7")) {
                            Map<String, Object> map = (Map<String, Object>) ds.getValue();
                            Object bill_amount = map.get("bill_amount");
                            double bill_amount_db = Double.parseDouble(String.valueOf(bill_amount));
                            credit_incomes_7_real += bill_amount_db;
                            credit_incomes_7_st_real = decimalFormat.format(credit_incomes_7_real);
                            txtCreditSalesReal7.setText(credit_incomes_7_st_real);
                        }


                    }

                    double total_incomes_7_real = cash_incomes_7_real+credit_incomes_7_real;
                    String total_incomes_7_st_real = decimalFormat.format(total_incomes_7_real);
                    txtTotalIncomesReal7.setText(total_incomes_7_st_real);

                    double cash_sales_var = ((cash_incomes_7_real-cash_incomes_7)/cash_incomes_7)*100;
                    String cash_sales_var_st = decimalFormat.format(cash_sales_var);
                    txtCashSalesDiff7.setText(cash_sales_var_st+"%");
                    if (cash_sales_var < 0) {
                        txtCashSalesDiff7.setTextColor(Color.RED);
                    }

                    double credit_sales_var = ((credit_incomes_7_real-credit_incomes_7)/credit_incomes_7)*100;
                    String credit_sales_var_st = decimalFormat.format(credit_sales_var);
                    txtCreditSalesDiff7.setText(credit_sales_var_st+"%");
                    if (credit_sales_var < 0) {
                        txtCreditSalesDiff7.setTextColor(Color.RED);
                    }

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        companyRef.child(post_key).child("Worker Bills").orderByChild("operation_year").equalTo(year+"").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                total_salary_7_real = 0;
                for (DataSnapshot ds : dataSnapshot.getChildren()) {

                    String operation_month = ds.child("operation_month").getValue().toString();

                    if (operation_month.equals("7")) {
                        Map<String, Object> map = (Map<String, Object>) ds.getValue();
                        Object total_payment = map.get("total_payment");
                        double total_payment_db = Double.parseDouble(String.valueOf(total_payment));
                        total_salary_7_real += total_payment_db;
                        String total_salary_7_st_real = decimalFormat.format(total_salary_7_real);
                        txtWorkerPaymentReal7.setText(total_salary_7_st_real);
                    }

                    double salary_var = ((total_salary_7_real-total_salary_7)/total_salary_7)*100;
                    String salary_var_var_st = decimalFormat.format(salary_var);
                    txtWorkerPaymentDiff7.setText(salary_var_var_st+"%");
                    if (salary_var < 0) {
                        txtWorkerPaymentDiff7.setTextColor(Color.RED);
                    }


                }

                companyRef.child(post_key).child("Purchased Orders").orderByChild("operation_year").equalTo(year+"").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        purchase_total_7_real = 0;
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
                                    purchase_total_7_real += purchase_order_total_amount_db;
                                    purchase_total_7_st_real = decimalFormat.format(purchase_total_7_real);
                                    txtPurchasesReal7.setText(purchase_total_7_st_real);
                                }

                            }

                        }

                        total_incomes_7_real = cash_incomes_7_real+credit_incomes_7_real+financial_incomes_7_real+other_incomes_7_real;
                        igv_taxes_7_real = (total_incomes_7_real*0.18)-(purchase_total_7_real*0.18);
                        igv_taxes_7_st_real = decimalFormat.format(igv_taxes_7_real);
                        txtIgvReal7.setText(igv_taxes_7_st_real);

                        double total_outcomes_7_real = total_salary_7_real+purchase_total_7_real+igv_taxes_7_real+financial_outcomes_7_real+other_outcomes_7_real;
                        String total_outcomes_7_st_real = decimalFormat.format(total_outcomes_7_real);
                        txtTotalOutcomesReal7.setText(total_outcomes_7_st_real);

                        double igv_var = ((igv_taxes_7_real-igv_taxes_7)/igv_taxes_7)*100;
                        String igv_var_st = decimalFormat.format(igv_var);
                        txtIgvDiff7.setText(igv_var_st+"%");
                        if (igv_var < 0) {
                            txtIgvDiff7.setTextColor(Color.RED);
                        }

                        double purchase_var = ((purchase_total_7_real-purchase_total_7)/purchase_total_7)*100;
                        String purchase_var_st = decimalFormat.format(purchase_var);
                        txtPurchasesDiff7.setText(purchase_var_st+"%");
                        if (igv_var < 0) {
                            txtPurchasesDiff7.setTextColor(Color.RED);
                        }

                        double incomes_var = ((total_incomes_7_real-total_incomes_7)/total_incomes_7)*100;
                        String incomes_var_st = decimalFormat.format(incomes_var);
                        txtTotalIncomesDiff7.setText(incomes_var_st+"%");
                        if (incomes_var < 0) {
                            txtTotalIncomesDiff7.setTextColor(Color.RED);
                        }

                        double outcomes_var = ((total_outcomes_7_real-total_outcomes_7)/total_outcomes_7)*100;
                        String outcomes_var_st = decimalFormat.format(outcomes_var);
                        txtTotalOutcomesDiff7.setText(outcomes_var_st+"%");
                        if (outcomes_var < 0) {
                            txtTotalOutcomesDiff7.setTextColor(Color.RED);
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

    private void calculateMonth8() {
        companyRef.child(post_key).child("My Bills").orderByChild("issuing_year").equalTo(year).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                cash_incomes_8_real = 0;
                credit_incomes_8_real = 0;
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
                            cash_incomes_8_real += bill_amount_db;
                            cash_incomes_8_st_real = decimalFormat.format(cash_incomes_8_real);
                            txtCashSalesReal8.setText(cash_incomes_8_st_real);
                        }


                    }
                    if (state.equals("paid") && !issuing_date.equals(expiration_date)) {
                        if (expiration_month.equals("8")) {
                            Map<String, Object> map = (Map<String, Object>) ds.getValue();
                            Object bill_amount = map.get("bill_amount");
                            double bill_amount_db = Double.parseDouble(String.valueOf(bill_amount));
                            credit_incomes_8_real += bill_amount_db;
                            credit_incomes_8_st_real = decimalFormat.format(credit_incomes_8_real);
                            txtCreditSalesReal8.setText(credit_incomes_8_st_real);
                        }


                    }

                    double total_incomes_8_real = cash_incomes_8_real+credit_incomes_8_real;
                    String total_incomes_8_st_real = decimalFormat.format(total_incomes_8_real);
                    txtTotalIncomesReal8.setText(total_incomes_8_st_real);

                    double cash_sales_var = ((cash_incomes_8_real-cash_incomes_8)/cash_incomes_8)*100;
                    String cash_sales_var_st = decimalFormat.format(cash_sales_var);
                    txtCashSalesDiff8.setText(cash_sales_var_st+"%");
                    if (cash_sales_var < 0) {
                        txtCashSalesDiff8.setTextColor(Color.RED);
                    }

                    double credit_sales_var = ((credit_incomes_8_real-credit_incomes_8)/credit_incomes_8)*100;
                    String credit_sales_var_st = decimalFormat.format(credit_sales_var);
                    txtCreditSalesDiff8.setText(credit_sales_var_st+"%");
                    if (credit_sales_var < 0) {
                        txtCreditSalesDiff8.setTextColor(Color.RED);
                    }

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        companyRef.child(post_key).child("Worker Bills").orderByChild("operation_year").equalTo(year+"").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                total_salary_8_real = 0;
                for (DataSnapshot ds : dataSnapshot.getChildren()) {

                    String operation_month = ds.child("operation_month").getValue().toString();

                    if (operation_month.equals("8")) {
                        Map<String, Object> map = (Map<String, Object>) ds.getValue();
                        Object total_payment = map.get("total_payment");
                        double total_payment_db = Double.parseDouble(String.valueOf(total_payment));
                        total_salary_8_real += total_payment_db;
                        String total_salary_8_st_real = decimalFormat.format(total_salary_8_real);
                        txtWorkerPaymentReal8.setText(total_salary_8_st_real);
                    }

                    double salary_var = ((total_salary_8_real-total_salary_8)/total_salary_8)*100;
                    String salary_var_var_st = decimalFormat.format(salary_var);
                    txtWorkerPaymentDiff8.setText(salary_var_var_st+"%");
                    if (salary_var < 0) {
                        txtWorkerPaymentDiff8.setTextColor(Color.RED);
                    }


                }

                companyRef.child(post_key).child("Purchased Orders").orderByChild("operation_year").equalTo(year+"").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        purchase_total_8_real = 0;
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
                                    purchase_total_8_real += purchase_order_total_amount_db;
                                    purchase_total_8_st_real = decimalFormat.format(purchase_total_8_real);
                                    txtPurchasesReal8.setText(purchase_total_8_st_real);
                                }

                            }

                        }

                        total_incomes_8_real = cash_incomes_8_real+credit_incomes_8_real+financial_incomes_8_real+other_incomes_8_real;
                        igv_taxes_8_real = (total_incomes_8_real*0.18)-(purchase_total_8_real*0.18);
                        igv_taxes_8_st_real = decimalFormat.format(igv_taxes_8_real);
                        txtIgvReal8.setText(igv_taxes_8_st_real);

                        double total_outcomes_8_real = total_salary_8_real+purchase_total_8_real+igv_taxes_8_real+financial_outcomes_8_real+other_outcomes_8_real;
                        String total_outcomes_8_st_real = decimalFormat.format(total_outcomes_8_real);
                        txtTotalOutcomesReal8.setText(total_outcomes_8_st_real);

                        double igv_var = ((igv_taxes_8_real-igv_taxes_8)/igv_taxes_8)*100;
                        String igv_var_st = decimalFormat.format(igv_var);
                        txtIgvDiff8.setText(igv_var_st+"%");
                        if (igv_var < 0) {
                            txtIgvDiff8.setTextColor(Color.RED);
                        }

                        double purchase_var = ((purchase_total_8_real-purchase_total_8)/purchase_total_8)*100;
                        String purchase_var_st = decimalFormat.format(purchase_var);
                        txtPurchasesDiff8.setText(purchase_var_st+"%");
                        if (igv_var < 0) {
                            txtPurchasesDiff8.setTextColor(Color.RED);
                        }

                        double incomes_var = ((total_incomes_8_real-total_incomes_8)/total_incomes_8)*100;
                        String incomes_var_st = decimalFormat.format(incomes_var);
                        txtTotalIncomesDiff8.setText(incomes_var_st+"%");
                        if (incomes_var < 0) {
                            txtTotalIncomesDiff8.setTextColor(Color.RED);
                        }

                        double outcomes_var = ((total_outcomes_8_real-total_outcomes_8)/total_outcomes_8)*100;
                        String outcomes_var_st = decimalFormat.format(outcomes_var);
                        txtTotalOutcomesDiff8.setText(outcomes_var_st+"%");
                        if (outcomes_var < 0) {
                            txtTotalOutcomesDiff8.setTextColor(Color.RED);
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

    private void calculateMonth9() {
        companyRef.child(post_key).child("My Bills").orderByChild("issuing_year").equalTo(year).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                cash_incomes_9_real = 0;
                credit_incomes_9_real = 0;
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
                            cash_incomes_9_real += bill_amount_db;
                            cash_incomes_9_st_real = decimalFormat.format(cash_incomes_9_real);
                            txtCashSalesReal9.setText(cash_incomes_9_st_real);
                        }


                    }
                    if (state.equals("paid") && !issuing_date.equals(expiration_date)) {
                        if (expiration_month.equals("9")) {
                            Map<String, Object> map = (Map<String, Object>) ds.getValue();
                            Object bill_amount = map.get("bill_amount");
                            double bill_amount_db = Double.parseDouble(String.valueOf(bill_amount));
                            credit_incomes_9_real += bill_amount_db;
                            credit_incomes_9_st_real = decimalFormat.format(credit_incomes_9_real);
                            txtCreditSalesReal9.setText(credit_incomes_9_st_real);
                        }


                    }

                    double total_incomes_9_real = cash_incomes_9_real+credit_incomes_9_real;
                    String total_incomes_9_st_real = decimalFormat.format(total_incomes_9_real);
                    txtTotalIncomesReal9.setText(total_incomes_9_st_real);

                    double cash_sales_var = ((cash_incomes_9_real-cash_incomes_9)/cash_incomes_9)*100;
                    String cash_sales_var_st = decimalFormat.format(cash_sales_var);
                    txtCashSalesDiff9.setText(cash_sales_var_st+"%");
                    if (cash_sales_var < 0) {
                        txtCashSalesDiff9.setTextColor(Color.RED);
                    }

                    double credit_sales_var = ((credit_incomes_9_real-credit_incomes_9)/credit_incomes_9)*100;
                    String credit_sales_var_st = decimalFormat.format(credit_sales_var);
                    txtCreditSalesDiff9.setText(credit_sales_var_st+"%");
                    if (credit_sales_var < 0) {
                        txtCreditSalesDiff9.setTextColor(Color.RED);
                    }

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        companyRef.child(post_key).child("Worker Bills").orderByChild("operation_year").equalTo(year+"").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                total_salary_9_real = 0;
                for (DataSnapshot ds : dataSnapshot.getChildren()) {

                    String operation_month = ds.child("operation_month").getValue().toString();

                    if (operation_month.equals("9")) {
                        Map<String, Object> map = (Map<String, Object>) ds.getValue();
                        Object total_payment = map.get("total_payment");
                        double total_payment_db = Double.parseDouble(String.valueOf(total_payment));
                        total_salary_9_real += total_payment_db;
                        String total_salary_9_st_real = decimalFormat.format(total_salary_9_real);
                        txtWorkerPaymentReal9.setText(total_salary_9_st_real);
                    }

                    double salary_var = ((total_salary_9_real-total_salary_9)/total_salary_9)*100;
                    String salary_var_var_st = decimalFormat.format(salary_var);
                    txtWorkerPaymentDiff9.setText(salary_var_var_st+"%");
                    if (salary_var < 0) {
                        txtWorkerPaymentDiff9.setTextColor(Color.RED);
                    }


                }

                companyRef.child(post_key).child("Purchased Orders").orderByChild("operation_year").equalTo(year+"").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        purchase_total_9_real = 0;
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
                                    purchase_total_9_real += purchase_order_total_amount_db;
                                    purchase_total_9_st_real = decimalFormat.format(purchase_total_9_real);
                                    txtPurchasesReal9.setText(purchase_total_9_st_real);
                                }

                            }

                        }

                        total_incomes_9_real = cash_incomes_9_real+credit_incomes_9_real+financial_incomes_9_real+other_incomes_9_real;
                        igv_taxes_9_real = (total_incomes_9_real*0.18)-(purchase_total_9_real*0.18);
                        igv_taxes_9_st_real = decimalFormat.format(igv_taxes_9_real);
                        txtIgvReal9.setText(igv_taxes_9_st_real);

                        double total_outcomes_9_real = total_salary_9_real+purchase_total_9_real+igv_taxes_9_real+financial_outcomes_9_real+other_outcomes_9_real;
                        String total_outcomes_9_st_real = decimalFormat.format(total_outcomes_9_real);
                        txtTotalOutcomesReal9.setText(total_outcomes_9_st_real);

                        double igv_var = ((igv_taxes_9_real-igv_taxes_9)/igv_taxes_9)*100;
                        String igv_var_st = decimalFormat.format(igv_var);
                        txtIgvDiff9.setText(igv_var_st+"%");
                        if (igv_var < 0) {
                            txtIgvDiff9.setTextColor(Color.RED);
                        }

                        double purchase_var = ((purchase_total_9_real-purchase_total_9)/purchase_total_9)*100;
                        String purchase_var_st = decimalFormat.format(purchase_var);
                        txtPurchasesDiff9.setText(purchase_var_st+"%");
                        if (igv_var < 0) {
                            txtPurchasesDiff9.setTextColor(Color.RED);
                        }

                        double incomes_var = ((total_incomes_9_real-total_incomes_9)/total_incomes_9)*100;
                        String incomes_var_st = decimalFormat.format(incomes_var);
                        txtTotalIncomesDiff9.setText(incomes_var_st+"%");
                        if (incomes_var < 0) {
                            txtTotalIncomesDiff9.setTextColor(Color.RED);
                        }

                        double outcomes_var = ((total_outcomes_9_real-total_outcomes_9)/total_outcomes_9)*100;
                        String outcomes_var_st = decimalFormat.format(outcomes_var);
                        txtTotalOutcomesDiff9.setText(outcomes_var_st+"%");
                        if (outcomes_var < 0) {
                            txtTotalOutcomesDiff9.setTextColor(Color.RED);
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

    private void calculateMonth10() {
        companyRef.child(post_key).child("My Bills").orderByChild("issuing_year").equalTo(year).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                cash_incomes_10_real = 0;
                credit_incomes_10_real = 0;
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
                            cash_incomes_10_real += bill_amount_db;
                            cash_incomes_10_st_real = decimalFormat.format(cash_incomes_10_real);
                            txtCashSalesReal10.setText(cash_incomes_10_st_real);
                        }


                    }
                    if (state.equals("paid") && !issuing_date.equals(expiration_date)) {
                        if (expiration_month.equals("10")) {
                            Map<String, Object> map = (Map<String, Object>) ds.getValue();
                            Object bill_amount = map.get("bill_amount");
                            double bill_amount_db = Double.parseDouble(String.valueOf(bill_amount));
                            credit_incomes_10_real += bill_amount_db;
                            credit_incomes_10_st_real = decimalFormat.format(credit_incomes_10_real);
                            txtCreditSalesReal10.setText(credit_incomes_10_st_real);
                        }


                    }

                    double total_incomes_10_real = cash_incomes_10_real+credit_incomes_10_real;
                    String total_incomes_10_st_real = decimalFormat.format(total_incomes_10_real);
                    txtTotalIncomesReal10.setText(total_incomes_10_st_real);

                    double cash_sales_var = ((cash_incomes_10_real-cash_incomes_10)/cash_incomes_10)*100;
                    String cash_sales_var_st = decimalFormat.format(cash_sales_var);
                    txtCashSalesDiff10.setText(cash_sales_var_st+"%");
                    if (cash_sales_var < 0) {
                        txtCashSalesDiff10.setTextColor(Color.RED);
                    }

                    double credit_sales_var = ((credit_incomes_10_real-credit_incomes_10)/credit_incomes_10)*100;
                    String credit_sales_var_st = decimalFormat.format(credit_sales_var);
                    txtCreditSalesDiff10.setText(credit_sales_var_st+"%");
                    if (credit_sales_var < 0) {
                        txtCreditSalesDiff10.setTextColor(Color.RED);
                    }

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        companyRef.child(post_key).child("Worker Bills").orderByChild("operation_year").equalTo(year+"").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                total_salary_10_real = 0;
                for (DataSnapshot ds : dataSnapshot.getChildren()) {

                    String operation_month = ds.child("operation_month").getValue().toString();

                    if (operation_month.equals("10")) {
                        Map<String, Object> map = (Map<String, Object>) ds.getValue();
                        Object total_payment = map.get("total_payment");
                        double total_payment_db = Double.parseDouble(String.valueOf(total_payment));
                        total_salary_10_real += total_payment_db;
                        String total_salary_10_st_real = decimalFormat.format(total_salary_10_real);
                        txtWorkerPaymentReal10.setText(total_salary_10_st_real);
                    }

                    double salary_var = ((total_salary_10_real-total_salary_10)/total_salary_10)*100;
                    String salary_var_var_st = decimalFormat.format(salary_var);
                    txtWorkerPaymentDiff10.setText(salary_var_var_st+"%");
                    if (salary_var < 0) {
                        txtWorkerPaymentDiff10.setTextColor(Color.RED);
                    }

                }

                companyRef.child(post_key).child("Purchased Orders").orderByChild("operation_year").equalTo(year+"").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        purchase_total_10_real = 0;
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
                                    purchase_total_10_real += purchase_order_total_amount_db;
                                    purchase_total_10_st_real = decimalFormat.format(purchase_total_10_real);
                                    txtPurchasesReal10.setText(purchase_total_10_st_real);
                                }

                            }

                        }

                        total_incomes_10_real = cash_incomes_10_real+credit_incomes_10_real+financial_incomes_10_real+other_incomes_10_real;
                        igv_taxes_10_real = (total_incomes_10_real*0.18)-(purchase_total_10_real*0.18);
                        igv_taxes_10_st_real = decimalFormat.format(igv_taxes_10_real);
                        txtIgvReal10.setText(igv_taxes_10_st_real);

                        double total_outcomes_10_real = total_salary_10_real+purchase_total_10_real+igv_taxes_10_real+financial_outcomes_10_real+other_outcomes_10_real;
                        String total_outcomes_10_st_real = decimalFormat.format(total_outcomes_10_real);
                        txtTotalOutcomesReal10.setText(total_outcomes_10_st_real);

                        double igv_var = ((igv_taxes_10_real-igv_taxes_10)/igv_taxes_10)*100;
                        String igv_var_st = decimalFormat.format(igv_var);
                        txtIgvDiff10.setText(igv_var_st+"%");
                        if (igv_var < 0) {
                            txtIgvDiff10.setTextColor(Color.RED);
                        }

                        double purchase_var = ((purchase_total_10_real-purchase_total_10)/purchase_total_10)*100;
                        String purchase_var_st = decimalFormat.format(purchase_var);
                        txtPurchasesDiff10.setText(purchase_var_st+"%");
                        if (igv_var < 0) {
                            txtPurchasesDiff10.setTextColor(Color.RED);
                        }

                        double incomes_var = ((total_incomes_10_real-total_incomes_10)/total_incomes_10)*100;
                        String incomes_var_st = decimalFormat.format(incomes_var);
                        txtTotalIncomesDiff10.setText(incomes_var_st+"%");
                        if (incomes_var < 0) {
                            txtTotalIncomesDiff10.setTextColor(Color.RED);
                        }

                        double outcomes_var = ((total_outcomes_10_real-total_outcomes_10)/total_outcomes_10)*100;
                        String outcomes_var_st = decimalFormat.format(outcomes_var);
                        txtTotalOutcomesDiff10.setText(outcomes_var_st+"%");
                        if (outcomes_var < 0) {
                            txtTotalOutcomesDiff10.setTextColor(Color.RED);
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

    private void calculateMonth11() {
        companyRef.child(post_key).child("My Bills").orderByChild("issuing_year").equalTo(year).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                cash_incomes_11_real = 0;
                credit_incomes_11_real = 0;
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
                            cash_incomes_11_real += bill_amount_db;
                            cash_incomes_11_st_real = decimalFormat.format(cash_incomes_11_real);
                            txtCashSalesReal11.setText(cash_incomes_11_st_real);
                        }


                    }
                    if (state.equals("paid") && !issuing_date.equals(expiration_date)) {
                        if (expiration_month.equals("11")) {
                            Map<String, Object> map = (Map<String, Object>) ds.getValue();
                            Object bill_amount = map.get("bill_amount");
                            double bill_amount_db = Double.parseDouble(String.valueOf(bill_amount));
                            credit_incomes_11_real += bill_amount_db;
                            credit_incomes_11_st_real = decimalFormat.format(credit_incomes_11_real);
                            txtCreditSalesReal11.setText(credit_incomes_11_st_real);
                        }


                    }

                    double total_incomes_11_real = cash_incomes_11_real+credit_incomes_11_real;
                    String total_incomes_11_st_real = decimalFormat.format(total_incomes_11_real);
                    txtTotalIncomesReal11.setText(total_incomes_11_st_real);

                    double cash_sales_var = ((cash_incomes_11_real-cash_incomes_11)/cash_incomes_11)*100;
                    String cash_sales_var_st = decimalFormat.format(cash_sales_var);
                    txtCashSalesDiff11.setText(cash_sales_var_st+"%");
                    if (cash_sales_var < 0) {
                        txtCashSalesDiff11.setTextColor(Color.RED);
                    }

                    double credit_sales_var = ((credit_incomes_11_real-credit_incomes_11)/credit_incomes_11)*100;
                    String credit_sales_var_st = decimalFormat.format(credit_sales_var);
                    txtCreditSalesDiff11.setText(credit_sales_var_st+"%");
                    if (credit_sales_var < 0) {
                        txtCreditSalesDiff11.setTextColor(Color.RED);
                    }

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        companyRef.child(post_key).child("Worker Bills").orderByChild("operation_year").equalTo(year+"").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                total_salary_11_real = 0;
                for (DataSnapshot ds : dataSnapshot.getChildren()) {

                    String operation_month = ds.child("operation_month").getValue().toString();

                    if (operation_month.equals("11")) {
                        Map<String, Object> map = (Map<String, Object>) ds.getValue();
                        Object total_payment = map.get("total_payment");
                        double total_payment_db = Double.parseDouble(String.valueOf(total_payment));
                        total_salary_11_real += total_payment_db;
                        String total_salary_11_st_real = decimalFormat.format(total_salary_11_real);
                        txtWorkerPaymentReal11.setText(total_salary_11_st_real);
                    }

                    double salary_var = ((total_salary_11_real-total_salary_11)/total_salary_11)*100;
                    String salary_var_var_st = decimalFormat.format(salary_var);
                    txtWorkerPaymentDiff11.setText(salary_var_var_st+"%");
                    if (salary_var < 0) {
                        txtWorkerPaymentDiff11.setTextColor(Color.RED);
                    }

                }

                companyRef.child(post_key).child("Purchased Orders").orderByChild("operation_year").equalTo(year+"").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        purchase_total_11_real = 0;
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
                                    purchase_total_11_real += purchase_order_total_amount_db;
                                    purchase_total_11_st_real = decimalFormat.format(purchase_total_11_real);
                                    txtPurchasesReal11.setText(purchase_total_11_st_real);
                                }

                            }

                        }

                        total_incomes_11_real = cash_incomes_11_real+credit_incomes_11_real+financial_incomes_11_real+other_incomes_11_real;
                        igv_taxes_11_real = (total_incomes_11_real*0.18)-(purchase_total_11_real*0.18);
                        igv_taxes_11_st_real = decimalFormat.format(igv_taxes_11_real);
                        txtIgvReal11.setText(igv_taxes_11_st_real);

                        double total_outcomes_11_real = total_salary_11_real+purchase_total_11_real+igv_taxes_11_real+financial_outcomes_11_real+other_outcomes_11_real;
                        String total_outcomes_11_st_real = decimalFormat.format(total_outcomes_11_real);
                        txtTotalOutcomesReal11.setText(total_outcomes_11_st_real);

                        double igv_var = ((igv_taxes_11_real-igv_taxes_11)/igv_taxes_11)*100;
                        String igv_var_st = decimalFormat.format(igv_var);
                        txtIgvDiff11.setText(igv_var_st+"%");
                        if (igv_var < 0) {
                            txtIgvDiff11.setTextColor(Color.RED);
                        }

                        double purchase_var = ((purchase_total_11_real-purchase_total_11)/purchase_total_11)*100;
                        String purchase_var_st = decimalFormat.format(purchase_var);
                        txtPurchasesDiff11.setText(purchase_var_st+"%");
                        if (igv_var < 0) {
                            txtPurchasesDiff11.setTextColor(Color.RED);
                        }

                        double incomes_var = ((total_incomes_11_real-total_incomes_11)/total_incomes_11)*100;
                        String incomes_var_st = decimalFormat.format(incomes_var);
                        txtTotalIncomesDiff11.setText(incomes_var_st+"%");
                        if (incomes_var < 0) {
                            txtTotalIncomesDiff11.setTextColor(Color.RED);
                        }

                        double outcomes_var = ((total_outcomes_11_real-total_outcomes_11)/total_outcomes_11)*100;
                        String outcomes_var_st = decimalFormat.format(outcomes_var);
                        txtTotalOutcomesDiff11.setText(outcomes_var_st+"%");
                        if (outcomes_var < 0) {
                            txtTotalOutcomesDiff11.setTextColor(Color.RED);
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

    private void calculateMonth12() {
        companyRef.child(post_key).child("My Bills").orderByChild("issuing_year").equalTo(year).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                cash_incomes_12_real = 0;
                credit_incomes_12_real = 0;
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
                            cash_incomes_12_real += bill_amount_db;
                            cash_incomes_12_st_real = decimalFormat.format(cash_incomes_12_real);
                            txtCashSalesReal12.setText(cash_incomes_12_st_real);
                        }


                    }
                    if (state.equals("paid") && !issuing_date.equals(expiration_date)) {
                        if (expiration_month.equals("12")) {
                            Map<String, Object> map = (Map<String, Object>) ds.getValue();
                            Object bill_amount = map.get("bill_amount");
                            double bill_amount_db = Double.parseDouble(String.valueOf(bill_amount));
                            credit_incomes_12_real += bill_amount_db;
                            credit_incomes_12_st_real = decimalFormat.format(credit_incomes_12_real);
                            txtCreditSalesReal12.setText(credit_incomes_12_st_real);
                        }


                    }

                    double total_incomes_12_real = cash_incomes_12_real+credit_incomes_12_real;
                    String total_incomes_12_st_real = decimalFormat.format(total_incomes_12_real);
                    txtTotalIncomesReal12.setText(total_incomes_12_st_real);

                    double cash_sales_var = ((cash_incomes_12_real-cash_incomes_12)/cash_incomes_12)*100;
                    String cash_sales_var_st = decimalFormat.format(cash_sales_var);
                    txtCashSalesDiff12.setText(cash_sales_var_st+"%");
                    if (cash_sales_var < 0) {
                        txtCashSalesDiff12.setTextColor(Color.RED);
                    }

                    double credit_sales_var = ((credit_incomes_12_real-credit_incomes_12)/credit_incomes_12)*100;
                    String credit_sales_var_st = decimalFormat.format(credit_sales_var);
                    txtCreditSalesDiff12.setText(credit_sales_var_st+"%");
                    if (credit_sales_var < 0) {
                        txtCreditSalesDiff12.setTextColor(Color.RED);
                    }

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        companyRef.child(post_key).child("Worker Bills").orderByChild("operation_year").equalTo(year+"").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                total_salary_12_real = 0;
                for (DataSnapshot ds : dataSnapshot.getChildren()) {

                    String operation_month = ds.child("operation_month").getValue().toString();

                    if (operation_month.equals("12")) {
                        Map<String, Object> map = (Map<String, Object>) ds.getValue();
                        Object total_payment = map.get("total_payment");
                        double total_payment_db = Double.parseDouble(String.valueOf(total_payment));
                        total_salary_12_real += total_payment_db;
                        String total_salary_12_st_real = decimalFormat.format(total_salary_12_real);
                        txtWorkerPaymentReal12.setText(total_salary_12_st_real);
                    }

                    double salary_var = ((total_salary_12_real-total_salary_12)/total_salary_12)*100;
                    String salary_var_var_st = decimalFormat.format(salary_var);
                    txtWorkerPaymentDiff12.setText(salary_var_var_st+"%");
                    if (salary_var < 0) {
                        txtWorkerPaymentDiff12.setTextColor(Color.RED);
                    }


                }

                companyRef.child(post_key).child("Purchased Orders").orderByChild("operation_year").equalTo(year+"").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        purchase_total_12_real = 0;
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

                                if (expiration_month.equals("12")) {
                                    Map<String, Object> map = (Map<String, Object>) ds.getValue();
                                    Object purchase_order_total_amount = map.get("purchase_order_total_amount");
                                    double purchase_order_total_amount_db = Double.parseDouble((String) purchase_order_total_amount);
                                    purchase_total_12_real += purchase_order_total_amount_db;
                                    purchase_total_12_st_real = decimalFormat.format(purchase_total_12_real);
                                    txtPurchasesReal12.setText(purchase_total_12_st_real);
                                }

                            }

                        }

                        total_incomes_12_real = cash_incomes_12_real+credit_incomes_12_real+financial_incomes_12_real+other_incomes_12_real;
                        igv_taxes_12_real = (total_incomes_12_real*0.18)-(purchase_total_12_real*0.18);
                        igv_taxes_12_st_real = decimalFormat.format(igv_taxes_12_real);
                        txtIgvReal12.setText(igv_taxes_12_st_real);

                        double total_outcomes_12_real = total_salary_12_real+purchase_total_12_real+igv_taxes_12_real+financial_outcomes_12_real+other_outcomes_12_real;
                        String total_outcomes_12_st_real = decimalFormat.format(total_outcomes_12_real);
                        txtTotalOutcomesReal12.setText(total_outcomes_12_st_real);

                        double igv_var = ((igv_taxes_12_real-igv_taxes_12)/igv_taxes_12)*100;
                        String igv_var_st = decimalFormat.format(igv_var);
                        txtIgvDiff12.setText(igv_var_st+"%");
                        if (igv_var < 0) {
                            txtIgvDiff12.setTextColor(Color.RED);
                        }

                        double purchase_var = ((purchase_total_12_real-purchase_total_12)/purchase_total_12)*100;
                        String purchase_var_st = decimalFormat.format(purchase_var);
                        txtPurchasesDiff12.setText(purchase_var_st+"%");
                        if (igv_var < 0) {
                            txtPurchasesDiff12.setTextColor(Color.RED);
                        }

                        double incomes_var = ((total_incomes_12_real-total_incomes_12)/total_incomes_12)*100;
                        String incomes_var_st = decimalFormat.format(incomes_var);
                        txtTotalIncomesDiff12.setText(incomes_var_st+"%");
                        if (incomes_var < 0) {
                            txtTotalIncomesDiff12.setTextColor(Color.RED);
                        }

                        double outcomes_var = ((total_outcomes_12_real-total_outcomes_12)/total_outcomes_12)*100;
                        String outcomes_var_st = decimalFormat.format(outcomes_var);
                        txtTotalOutcomesDiff12.setText(outcomes_var_st+"%");
                        if (outcomes_var < 0) {
                            txtTotalOutcomesDiff12.setTextColor(Color.RED);
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
