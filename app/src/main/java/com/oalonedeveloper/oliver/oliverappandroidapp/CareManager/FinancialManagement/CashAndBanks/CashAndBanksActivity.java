package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.FinancialManagement.CashAndBanks;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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
import java.util.Map;

public class CashAndBanksActivity extends AppCompatActivity {

    TextView txtLastYear,txtCurrentYear,txtCashSales2,txtCreditSales2,txtCashSales1,txtCreditSales1,txtWorkerPayment2,txtWorkerPayment1,txtIgv2,txtIgv1,txtPurchaseAmount2,txtPurchaseAmount1,txtTotalIncomes2,txtTotalIncomes1,txtTotalOutcomes2,txtTotalOutcomes1,
            txtInitialCash2,txtFinalCash2,txtInitialCash1,txtFinalCash1;
    int day,month,year,last_year,before_last_year;
    String post_key,cash_incomes_2_st,credit_incomes_2_st,cash_incomes_1_st,credit_incomes_1_st,igv_taxes_1_st,igv_taxes_2_st,purchase_total_1_st,purchase_total_2_st,cash_flow_1,cash_flow_0;
    DatabaseReference companyRef;
    double cash_incomes_2,credit_incomes_2,cash_incomes_1,credit_incomes_1,total_salary_2,total_salary_1,igv_taxes_1,igv_taxes_2,cash_sum_sales_1,cash_sum_sales_2,credit_sum_sales_1,credit_sum_sales_2,total_incomes_2,total_incomes_1,purchase_total_1,purchase_total_2,cash_flow_1_db,
            cash_flow_0_db;
    DecimalFormat decimalFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash_and_banks);

        post_key = getIntent().getExtras().getString("post_key");
        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");

        txtLastYear = findViewById(R.id.txtLastYear);
        txtCurrentYear = findViewById(R.id.txtCurrentYear);
        txtCashSales2 = findViewById(R.id.txtCashSales2);
        txtCreditSales2 = findViewById(R.id.txtCreditSales2);
        txtCashSales1 = findViewById(R.id.txtCashSales1);
        txtCreditSales1 = findViewById(R.id.txtCreditSales1);
        txtWorkerPayment2 = findViewById(R.id.txtWorkerPayment2);
        txtWorkerPayment1 = findViewById(R.id.txtWorkerPayment1);
        txtIgv2 = findViewById(R.id.txtIgv2);
        txtIgv1 = findViewById(R.id.txtIgv1);
        txtPurchaseAmount2 = findViewById(R.id.txtPurchaseAmount2);
        txtPurchaseAmount1 = findViewById(R.id.txtPurchaseAmount1);
        txtTotalIncomes2 = findViewById(R.id.txtTotalIncomes2);
        txtTotalIncomes1 = findViewById(R.id.txtTotalIncomes1);
        txtTotalOutcomes2 = findViewById(R.id.txtTotalOutcomes2);
        txtTotalOutcomes1 = findViewById(R.id.txtTotalOutcomes1);
        txtInitialCash2 = findViewById(R.id.txtInitialCash2);
        txtFinalCash2 = findViewById(R.id.txtFinalCash2);
        txtInitialCash1 = findViewById(R.id.txtInitialCash1);
        txtFinalCash1 = findViewById(R.id.txtFinalCash1);

        decimalFormat = new DecimalFormat("0.00");

        Date date= new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        day = cal.get(Calendar.DAY_OF_MONTH);
        month = cal.get(Calendar.MONTH)+1;
        year = cal.get(Calendar.YEAR);
        last_year = year-1;
        before_last_year = last_year-1;

        txtLastYear.setText(last_year+"");
        txtCurrentYear.setText(year+"");

        companyRef.child(post_key).child("Cash Flow").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild("cash_flow" + last_year)) {
                    cash_flow_1 = dataSnapshot.child("cash_flow" + last_year).getValue().toString();
                    cash_flow_1_db = Double.parseDouble(cash_flow_1);
                    txtInitialCash2.setText("S/ "+cash_flow_1);

                } else {
                    cash_flow_1_db = 0.00;
                }

                if (dataSnapshot.hasChild("cash_flow" + before_last_year)) {
                    cash_flow_0 = dataSnapshot.child("cash_flow" + before_last_year).getValue().toString();
                    cash_flow_0_db = Double.parseDouble(cash_flow_0);
                    txtInitialCash1.setText("S/ "+cash_flow_0);

                } else {
                    cash_flow_0_db = 0.00;
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

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
                        Map<String, Object> map = (Map<String, Object>) ds.getValue();
                        Object bill_amount = map.get("bill_amount");
                        cash_sum_sales_2 = Double.parseDouble(String.valueOf(bill_amount));
                        cash_incomes_2 += cash_sum_sales_2;
                        cash_incomes_2_st = decimalFormat.format(cash_incomes_2);
                        txtCashSales2.setText("S/ "+cash_incomes_2_st);

                    }
                    if (state.equals("paid") && !issuing_date.equals(expiration_date)) {
                        Map<String, Object> map = (Map<String, Object>) ds.getValue();
                        Object bill_amount = map.get("bill_amount");
                        credit_sum_sales_2 = Double.parseDouble(String.valueOf(bill_amount));
                        credit_incomes_2 += credit_sum_sales_2;
                        credit_incomes_2_st = decimalFormat.format(credit_incomes_2);
                        txtCreditSales2.setText("S/ "+credit_incomes_2_st);

                    }

                    double total_incomes_2 = cash_incomes_2+credit_incomes_2;
                    String total_incomes_2_st = decimalFormat.format(total_incomes_2);
                    txtTotalIncomes2.setText("S/ "+total_incomes_2_st);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        companyRef.child(post_key).child("My Bills").orderByChild("issuing_year").equalTo(last_year).addListenerForSingleValueEvent(new ValueEventListener() {
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
                        Map<String, Object> map = (Map<String, Object>) ds.getValue();
                        Object bill_amount = map.get("bill_amount");
                        cash_sum_sales_1 = Double.parseDouble(String.valueOf(bill_amount));
                        cash_incomes_1 += cash_sum_sales_1;
                        cash_incomes_1_st = decimalFormat.format(cash_incomes_1);
                        txtCashSales1.setText("S/ "+cash_incomes_1_st);

                    }
                    if (state.equals("paid") && !issuing_date.equals(expiration_date)) {
                        Map<String, Object> map = (Map<String, Object>) ds.getValue();
                        Object bill_amount = map.get("bill_amount");
                        credit_sum_sales_1 = Double.parseDouble(String.valueOf(bill_amount));
                        credit_incomes_1 += credit_sum_sales_1;
                        credit_incomes_1_st = decimalFormat.format(credit_incomes_1);
                        txtCreditSales1.setText("S/ "+credit_incomes_1_st);

                    }

                    double total_incomes_1 = cash_incomes_1+credit_incomes_1;
                    String total_incomes_1_st = decimalFormat.format(total_incomes_1);
                    txtTotalIncomes1.setText("S/ "+total_incomes_1_st);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        companyRef.child(post_key).child("Job Profile").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    if (ds.child("Personal File").hasChild("Company Worker Data")) {
                        String begin_working_day = ds.child("Personal File").child("Company Worker Data").child("begin_working_day").getValue().toString();
                        String begin_working_month = ds.child("Personal File").child("Company Worker Data").child("begin_working_month").getValue().toString();
                        String begin_working_year = ds.child("Personal File").child("Company Worker Data").child("begin_working_year").getValue().toString();
                        String payment_amount = ds.child("Personal File").child("Company Worker Data").child("payment_amount").getValue().toString();
                        int begin_month = Integer.parseInt(begin_working_month);
                        double payment = Double.parseDouble(payment_amount);

                        if (begin_working_year.equals(year + "")) {

                            if (month > begin_month) {

                                int diff = month-begin_month;
                                double period_salary = payment*diff;
                                total_salary_2 += period_salary;
                                String total_salary_2_st = decimalFormat.format(total_salary_2);
                                txtWorkerPayment2.setText("S/ "+total_salary_2_st);
                            }

                        }
                        if (begin_working_year.equals(last_year + "")) {
                            int diff = 13-begin_month;
                            double period_salary = payment*diff;
                            total_salary_1 += period_salary;
                            String total_salary_1_st = decimalFormat.format(total_salary_1);
                            txtWorkerPayment1.setText("S/ "+total_salary_1_st);

                            int diff2 = month-1;
                            double period_salary2 = payment*diff2;
                            total_salary_2 += period_salary2;
                            String total_salary_2_st = decimalFormat.format(total_salary_2);
                            txtWorkerPayment2.setText("S/ "+total_salary_2_st);
                        }

                    }

                }
                companyRef.child(post_key).child("Purchased Orders").orderByChild("operation_year").equalTo(year+"").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        purchase_total_2 = 0;
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            Map<String, Object> map = (Map<String, Object>) ds.getValue();
                            Object purchase_order_total_amount = map.get("purchase_order_total_amount");
                            double purchase_order_total_amount_db = Double.parseDouble((String) purchase_order_total_amount);
                            purchase_total_2 += purchase_order_total_amount_db;
                            purchase_total_2_st = decimalFormat.format(purchase_total_2);
                            txtPurchaseAmount2.setText("S/ "+purchase_total_2_st);
                        }

                        total_incomes_2 = cash_incomes_2+credit_incomes_2;
                        igv_taxes_2 = (total_incomes_2*0.18)-(purchase_total_2*0.18);
                        igv_taxes_2_st = decimalFormat.format(igv_taxes_2);
                        txtIgv2.setText("S/ "+igv_taxes_2_st);

                        double total_outcomes_2 = total_salary_2+purchase_total_2+igv_taxes_2;
                        String total_outcomes_st = decimalFormat.format(total_outcomes_2);
                        txtTotalOutcomes2.setText("S/ "+total_outcomes_st);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                companyRef.child(post_key).child("Purchased Orders").orderByChild("operation_year").equalTo(last_year+"").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        purchase_total_1 = 0;
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            Map<String, Object> map = (Map<String, Object>) ds.getValue();
                            Object purchase_order_total_amount = map.get("purchase_order_total_amount");
                            double purchase_order_total_amount_db = Double.parseDouble((String) purchase_order_total_amount);
                            purchase_total_1 += purchase_order_total_amount_db;
                            purchase_total_1_st = decimalFormat.format(purchase_total_1);
                            txtPurchaseAmount1.setText("S/ "+purchase_total_1_st);
                        }

                        total_incomes_1 = cash_incomes_1+credit_incomes_1;
                        igv_taxes_1 = (total_incomes_1*0.18)-(purchase_total_1*0.18);
                        igv_taxes_1_st = decimalFormat.format(igv_taxes_1);
                        txtIgv1.setText("S/ "+igv_taxes_1_st);

                        double total_outcomes_1 = total_salary_1+purchase_total_1+igv_taxes_1;
                        String total_outcomes_st = decimalFormat.format(total_outcomes_1);
                        txtTotalOutcomes1.setText("S/ "+total_outcomes_st);

                        double cash_flow_1 = total_incomes_1-total_outcomes_1+cash_flow_0_db;
                        String cash_flow_1_st = decimalFormat.format(cash_flow_1);
                        txtFinalCash1.setText("S/ "+cash_flow_1_st);
                        txtInitialCash2.setText("S/ "+cash_flow_1_st);

                        companyRef.child(post_key).child("Cash Flow").child("cash_flow"+last_year).setValue(cash_flow_1_st);

                        total_incomes_2 = cash_incomes_2+credit_incomes_2;
                        igv_taxes_2 = (total_incomes_2*0.18)-(purchase_total_2*0.18);
                        double total_outcomes_2 = total_salary_2+purchase_total_2+igv_taxes_2;
                        double cash_flow_2 = total_incomes_2-total_outcomes_2+cash_flow_1;
                        String cash_flow_2_st = decimalFormat.format(cash_flow_2);
                        txtFinalCash2.setText("S/ "+cash_flow_2_st);
                        companyRef.child(post_key).child("Cash Flow").child("cash_flow"+year).setValue(cash_flow_2_st);
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
