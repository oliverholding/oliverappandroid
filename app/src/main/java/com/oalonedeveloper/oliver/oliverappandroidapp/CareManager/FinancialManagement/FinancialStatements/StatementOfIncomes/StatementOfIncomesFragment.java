package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.FinancialManagement.FinancialStatements.StatementOfIncomes;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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


public class StatementOfIncomesFragment extends Fragment {

    TextView txtPeriod,txtSales,txtSalesCosts,txtGrossProfit,txtWorkers,txtSalesExpenses,txtOperativeProfit,txtProfitBeforeTaxes,txtTaxes,txtNetProfit;
    int day,month,year,last_year;
    DatabaseReference companyRef,ratesRef;
    String post_key,sales_st,costs_st,gross_profit_st,workers_st,operative_profit_st,profit_before_taxes_st,taxes_st,net_profit_st;
    DecimalFormat decimalFormat;
    double sales,costs,gross_profit,workers,sales_expenses,operative_profit,financial_incomes,financial_outcomes,other_incomes,other_outcomes,profit_before_taxes,rent_tax,taxes,net_profit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_statement_of_incomes, container, false);

        txtPeriod = view.findViewById(R.id.txtPeriod);
        txtSales = view.findViewById(R.id.txtSales);
        txtSalesCosts = view.findViewById(R.id.txtSalesCosts);
        txtGrossProfit = view.findViewById(R.id.txtGrossProfit);
        txtWorkers = view.findViewById(R.id.txtWorkers);
        txtSalesExpenses = view.findViewById(R.id.txtSalesExpenses);
        txtOperativeProfit = view.findViewById(R.id.txtOperativeProfit);
        txtProfitBeforeTaxes = view.findViewById(R.id.txtProfitBeforeTaxes);
        txtTaxes = view.findViewById(R.id.txtTaxes);
        txtNetProfit = view.findViewById(R.id.txtNetProfit);

        Date date= new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        day = cal.get(Calendar.DAY_OF_MONTH);
        month = cal.get(Calendar.MONTH)+1;
        year = cal.get(Calendar.YEAR);
        last_year = year-1;

        post_key = getActivity().getIntent().getExtras().getString("post_key");
        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");
        ratesRef = FirebaseDatabase.getInstance().getReference().child("Rates");

        decimalFormat = new DecimalFormat("0.00");

        txtPeriod.setText("Al 31 de Diciembre del "+last_year);

        ratesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                rent_tax = dataSnapshot.child("rent_tax").getValue(Double.class);

                calculateSales();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        return view;
    }

    private void calculateSales() {
        companyRef.child(post_key).child("My Bills").orderByChild("issuing_year").equalTo(last_year).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                sales = 0;
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String state = ds.child("state").getValue().toString();
                    int issuing_year = ds.child("issuing_year").getValue(Integer.class);
                    String expiration_year = ds.child("expiration_year").getValue().toString();
                    if (state.equals("paid")) {
                        Map<String, Object> map = (Map<String, Object>) ds.getValue();
                        Object bill_amount = map.get("bill_amount");
                        double bill_amount_db = Double.parseDouble(String.valueOf(bill_amount));
                        sales += bill_amount_db-(bill_amount_db*0.18);
                        sales_st = decimalFormat.format(sales);
                        txtSales.setText(sales_st);
                    }
                }

                calculateCosts();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void calculateCosts() {
        companyRef.child(post_key).child("Purchased Orders").orderByChild("operation_year").equalTo(last_year+"").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                costs = 0;
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String purchase_payment_state = ds.child("purchase_payment_state").getValue().toString();
                    if (purchase_payment_state.equals("paid")) {
                        Map<String, Object> map = (Map<String, Object>) ds.getValue();
                        Object purchase_order_total_amount = map.get("purchase_order_total_amount");
                        double purchase_order_total_amount_db = Double.parseDouble((String) purchase_order_total_amount);
                        costs += purchase_order_total_amount_db-(purchase_order_total_amount_db*0.18);
                        costs_st = decimalFormat.format(costs);
                        txtSalesCosts.setText(costs_st);
                    }
                }

                calculateGrossProfit();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void calculateGrossProfit() {
        gross_profit = sales-costs;
        gross_profit_st = decimalFormat.format(gross_profit);
        txtGrossProfit.setText(gross_profit_st);

        calculateWorkersPayment();

    }

    private void calculateWorkersPayment() {
        companyRef.child(post_key).child("Worker Bills").orderByChild("operation_year").equalTo(last_year+"").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                workers = 0;
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Map<String, Object> map = (Map<String, Object>) ds.getValue();
                    Object total_payment = map.get("total_payment");
                    double total_payment_db = Double.parseDouble(String.valueOf(total_payment));
                    workers += total_payment_db;
                    workers_st = decimalFormat.format(workers);
                    txtWorkers.setText(workers_st);
                }
                
                calculateOperativeProfit();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void calculateOperativeProfit() {
        operative_profit = gross_profit-workers-sales_expenses;
        operative_profit_st = decimalFormat.format(operative_profit);
        txtOperativeProfit.setText(operative_profit_st);

        calculateProfitBeforeTaxes();
    }

    private void calculateProfitBeforeTaxes() {
        profit_before_taxes = operative_profit+financial_incomes-financial_outcomes+other_incomes-other_outcomes;
        profit_before_taxes_st = decimalFormat.format(profit_before_taxes);
        txtProfitBeforeTaxes.setText(profit_before_taxes_st);

        calculateTaxes();
    }

    private void calculateTaxes() {
        taxes = profit_before_taxes*(rent_tax/100);
        taxes_st = decimalFormat.format(taxes);
        txtTaxes.setText(taxes_st);

        calculateNetProfit();
    }

    private void calculateNetProfit() {

        if (profit_before_taxes <= 0) {
            net_profit = profit_before_taxes;
            net_profit_st = decimalFormat.format(net_profit);
            txtNetProfit.setText(net_profit_st);

        } else {
            net_profit = profit_before_taxes-taxes;
            net_profit_st = decimalFormat.format(net_profit);
            txtNetProfit.setText(net_profit_st);
        }


    }
}
