package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.SalesModule.SalesProjection;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
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

public class SalesProjectionActivity extends AppCompatActivity {

    TextView txtCurrentYear,txtCurrentYear2;
    EditText txtMonth1,txtMonth2,txtMonth3,txtMonth4,txtMonth5,txtMonth6,txtMonth7,txtMonth8,txtMonth9,txtMonth10,txtMonth11,txtMonth12,txtMonth1t2,txtMonth2t2,txtMonth3t2,txtMonth4t2,txtMonth5t2,txtMonth6t2,txtMonth7t2,
            txtMonth8t2,txtMonth9t2,txtMonth10t2,txtMonth11t2,txtMonth12t2;
    int day,month,year,year2;
    DatabaseReference companyRef;
    TextView txtVar1,txtVar2,txtVar3,txtVar4,txtVar5,txtVar6,txtVar7,txtVar8,txtVar9,txtVar10,txtVar11,txtVar12;
    String post_key,sales_1,sales_2,sales_3,sales_4,sales_5,sales_6,sales_7,sales_8,sales_9,sales_10,sales_11,sales_12;
    double sum1,sum2,sum3,sum4,sum5,sum6,sum7,sum8,sum9,sum10,sum11,sum12;
    float f1,f2,f3,f4,f5,f6,f7,f8,f9,f10,f11,f12;
    DecimalFormat decimalFormat;
    LineChart mLineChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_projection);

        post_key = getIntent().getExtras().getString("post_key");
        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");

        mLineChart = findViewById(R.id.linechart);
        txtCurrentYear = findViewById(R.id.txtCurrentYear);
        txtCurrentYear2 = findViewById(R.id.txtCurrentYear2);
        txtMonth1 = findViewById(R.id.txtMonth1);
        txtMonth2 = findViewById(R.id.txtMonth2);
        txtMonth3 = findViewById(R.id.txtMonth3);
        txtMonth4 = findViewById(R.id.txtMonth4);
        txtMonth5 = findViewById(R.id.txtMonth5);
        txtMonth6 = findViewById(R.id.txtMonth6);
        txtMonth7 = findViewById(R.id.txtMonth7);
        txtMonth8 = findViewById(R.id.txtMonth8);
        txtMonth9 = findViewById(R.id.txtMonth9);
        txtMonth10 = findViewById(R.id.txtMonth10);
        txtMonth11 = findViewById(R.id.txtMonth11);
        txtMonth12 = findViewById(R.id.txtMonth12);
        txtMonth1t2 = findViewById(R.id.txtMonth1t2);
        txtMonth2t2 = findViewById(R.id.txtMonth2t2);
        txtMonth3t2 = findViewById(R.id.txtMonth3t2);
        txtMonth4t2 = findViewById(R.id.txtMonth4t2);
        txtMonth5t2 = findViewById(R.id.txtMonth5t2);
        txtMonth6t2 = findViewById(R.id.txtMonth6t2);
        txtMonth7t2 = findViewById(R.id.txtMonth7t2);
        txtMonth8t2 = findViewById(R.id.txtMonth8t2);
        txtMonth9t2 = findViewById(R.id.txtMonth9t2);
        txtMonth10t2 = findViewById(R.id.txtMonth10t2);
        txtMonth11t2 = findViewById(R.id.txtMonth11t2);
        txtMonth12t2 = findViewById(R.id.txtMonth12t2);
        txtVar1 = findViewById(R.id.txtVar1);
        txtVar2 = findViewById(R.id.txtVar2);
        txtVar3 = findViewById(R.id.txtVar3);
        txtVar4 = findViewById(R.id.txtVar4);
        txtVar5 = findViewById(R.id.txtVar5);
        txtVar6 = findViewById(R.id.txtVar6);
        txtVar7 = findViewById(R.id.txtVar7);
        txtVar8 = findViewById(R.id.txtVar8);
        txtVar9 = findViewById(R.id.txtVar9);
        txtVar10 = findViewById(R.id.txtVar10);
        txtVar11 = findViewById(R.id.txtVar11);
        txtVar12 = findViewById(R.id.txtVar12);

        Date date= new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        day = cal.get(Calendar.DAY_OF_MONTH);
        month = cal.get(Calendar.MONTH)+1;
        year = cal.get(Calendar.YEAR);
        year2 = cal.get(Calendar.YEAR)+1;

        decimalFormat = new DecimalFormat("0.00");

        txtCurrentYear.setText("TOTAL (S/) "+year);
        txtCurrentYear2.setText("TOTAL (S/) "+year2);

        txtMonth1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                calculateVar();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        txtMonth1t2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                companyRef.child(post_key).child("Module 7").child("Sales Projection").child(year2+""+1).setValue(txtMonth1t2.getText().toString().trim());
                calculateVar();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        txtMonth2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                calculateVar();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        txtMonth2t2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                companyRef.child(post_key).child("Module 7").child("Sales Projection").child(year2+""+2).setValue(txtMonth2t2.getText().toString().trim());
                calculateVar();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        txtMonth3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                calculateVar();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        txtMonth3t2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                companyRef.child(post_key).child("Module 7").child("Sales Projection").child(year2+""+3).setValue(txtMonth3t2.getText().toString().trim());
                calculateVar();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        txtMonth4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                calculateVar();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        txtMonth4t2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                companyRef.child(post_key).child("Module 7").child("Sales Projection").child(year2+""+4).setValue(txtMonth4t2.getText().toString().trim());
                calculateVar();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        txtMonth5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                calculateVar();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        txtMonth5t2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                companyRef.child(post_key).child("Module 7").child("Sales Projection").child(year2+""+5).setValue(txtMonth5t2.getText().toString().trim());
                calculateVar();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        txtMonth6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                calculateVar();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        txtMonth6t2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                companyRef.child(post_key).child("Module 7").child("Sales Projection").child(year2+""+6).setValue(txtMonth6t2.getText().toString().trim());
                calculateVar();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        txtMonth7.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                calculateVar();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        txtMonth7t2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                companyRef.child(post_key).child("Module 7").child("Sales Projection").child(year2+""+7).setValue(txtMonth7t2.getText().toString().trim());
                calculateVar();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        txtMonth8.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                calculateVar();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        txtMonth8t2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                companyRef.child(post_key).child("Module 7").child("Sales Projection").child(year2+""+8).setValue(txtMonth8t2.getText().toString().trim());
                calculateVar();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        txtMonth9.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                calculateVar();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        txtMonth9t2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                companyRef.child(post_key).child("Module 7").child("Sales Projection").child(year2+""+9).setValue(txtMonth9t2.getText().toString().trim());
                calculateVar();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        txtMonth10.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                calculateVar();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        txtMonth10t2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                companyRef.child(post_key).child("Module 7").child("Sales Projection").child(year2+""+10).setValue(txtMonth10t2.getText().toString().trim());
                calculateVar();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        txtMonth11.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                calculateVar();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        txtMonth11t2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                companyRef.child(post_key).child("Module 7").child("Sales Projection").child(year2+""+11).setValue(txtMonth11t2.getText().toString().trim());
                calculateVar();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        txtMonth12.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                calculateVar();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        txtMonth12t2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                companyRef.child(post_key).child("Module 7").child("Sales Projection").child(year2+""+12).setValue(txtMonth12t2.getText().toString().trim());
                calculateVar();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        companyRef.child(post_key).child("Module 7").child("Sales Projection").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild(year2 + "" + 1)) {
                    String month_value = dataSnapshot.child(year2 + "" + 1).getValue().toString();
                    txtMonth1t2.setText(month_value);

                }
                if (dataSnapshot.hasChild(year2 + "" + 2)) {
                    String month_value = dataSnapshot.child(year2 + "" + 2).getValue().toString();
                    txtMonth2t2.setText(month_value);
                }
                if (dataSnapshot.hasChild(year2 + "" + 3)) {
                    String month_value = dataSnapshot.child(year2 + "" + 3).getValue().toString();
                    txtMonth3t2.setText(month_value);
                }
                if (dataSnapshot.hasChild(year2 + "" + 4)) {
                    String month_value = dataSnapshot.child(year2 + "" + 4).getValue().toString();
                    txtMonth4t2.setText(month_value);
                }
                if (dataSnapshot.hasChild(year2 + "" + 5)) {
                    String month_value = dataSnapshot.child(year2 + "" + 5).getValue().toString();
                    txtMonth5t2.setText(month_value);
                }
                if (dataSnapshot.hasChild(year2 + "" + 6)) {
                    String month_value = dataSnapshot.child(year2 + "" + 6).getValue().toString();
                    txtMonth6t2.setText(month_value);
                }
                if (dataSnapshot.hasChild(year2 + "" + 7)) {
                    String month_value = dataSnapshot.child(year2 + "" + 7).getValue().toString();
                    txtMonth7t2.setText(month_value);
                }
                if (dataSnapshot.hasChild(year2 + "" + 8)) {
                    String month_value = dataSnapshot.child(year2 + "" + 8).getValue().toString();
                    txtMonth8t2.setText(month_value);
                }
                if (dataSnapshot.hasChild(year2 + "" + 9)) {
                    String month_value = dataSnapshot.child(year2 + "" + 9).getValue().toString();
                    txtMonth9t2.setText(month_value);
                }
                if (dataSnapshot.hasChild(year2 + "" + 10)) {
                    String month_value = dataSnapshot.child(year2 + "" + 10).getValue().toString();
                    txtMonth10t2.setText(month_value);
                }
                if (dataSnapshot.hasChild(year2 + "" + 11)) {
                    String month_value = dataSnapshot.child(year2 + "" + 11).getValue().toString();
                    txtMonth11t2.setText(month_value);
                }
                if (dataSnapshot.hasChild(year2 + "" + 12)) {
                    String month_value = dataSnapshot.child(year2 + "" + 12).getValue().toString();
                    txtMonth12t2.setText(month_value);
                }

                calculateVar();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        companyRef.child(post_key).child("My Bills").orderByChild("issuing_year").equalTo(year).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                sum1 = 0;
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    int the_month = ds.child("issuing_month").getValue(Integer.class);
                    if (the_month == 1) {
                        Map<String, Object> map = (Map<String, Object>) ds.getValue();
                        Object bill_amount = map.get("bill_amount");
                        double total_value = Double.parseDouble(String.valueOf(bill_amount));
                        sum1 += total_value;
                        sales_1 = decimalFormat.format(sum1);
                        txtMonth1.setText(sales_1);

                        setValueOnGraph();
                    }
                }

                sum2 = 0;
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    int the_month = ds.child("issuing_month").getValue(Integer.class);
                    if (the_month == 2) {
                        Map<String, Object> map = (Map<String, Object>) ds.getValue();
                        Object bill_amount = map.get("bill_amount");
                        double total_value = Double.parseDouble(String.valueOf(bill_amount));
                        sum2 += total_value;
                        sales_2 = decimalFormat.format(sum2);
                        txtMonth2.setText(sales_2);

                        setValueOnGraph();
                    }
                }

                sum3 = 0;
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    int the_month = ds.child("issuing_month").getValue(Integer.class);
                    if (the_month == 3) {
                        Map<String, Object> map = (Map<String, Object>) ds.getValue();
                        Object bill_amount = map.get("bill_amount");
                        double total_value = Double.parseDouble(String.valueOf(bill_amount));
                        sum3 += total_value;
                        sales_3 = decimalFormat.format(sum3);
                        txtMonth3.setText(sales_3);

                        setValueOnGraph();
                    }
                }

                sum4 = 0;
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    int the_month = ds.child("issuing_month").getValue(Integer.class);
                    if (the_month == 4) {
                        Map<String, Object> map = (Map<String, Object>) ds.getValue();
                        Object bill_amount = map.get("bill_amount");
                        double total_value = Double.parseDouble(String.valueOf(bill_amount));
                        sum4 += total_value;
                        sales_4 = decimalFormat.format(sum4);
                        txtMonth4.setText(sales_4);

                        setValueOnGraph();
                    }
                }

                sum5 = 0;
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    int the_month = ds.child("issuing_month").getValue(Integer.class);
                    if (the_month == 5) {
                        Map<String, Object> map = (Map<String, Object>) ds.getValue();
                        Object bill_amount = map.get("bill_amount");
                        double total_value = Double.parseDouble(String.valueOf(bill_amount));
                        sum5 += total_value;
                        sales_5 = decimalFormat.format(sum5);
                        txtMonth5.setText(sales_5);

                        setValueOnGraph();
                    }
                }

                sum6 = 0;
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    int the_month = ds.child("issuing_month").getValue(Integer.class);
                    if (the_month == 6) {
                        Map<String, Object> map = (Map<String, Object>) ds.getValue();
                        Object bill_amount = map.get("bill_amount");
                        double total_value = Double.parseDouble(String.valueOf(bill_amount));
                        sum6 += total_value;
                        sales_6 = decimalFormat.format(sum6);
                        txtMonth6.setText(sales_6);

                        setValueOnGraph();
                    }
                }

                sum7 = 0;
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    int the_month = ds.child("issuing_month").getValue(Integer.class);
                    if (the_month == 7) {
                        Map<String, Object> map = (Map<String, Object>) ds.getValue();
                        Object bill_amount = map.get("bill_amount");
                        double total_value = Double.parseDouble(String.valueOf(bill_amount));
                        sum7 += total_value;
                        sales_7 = decimalFormat.format(sum7);
                        txtMonth7.setText(sales_7);

                        setValueOnGraph();
                    }
                }

                sum8 = 0;
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    int the_month = ds.child("issuing_month").getValue(Integer.class);
                    if (the_month == 8) {
                        Map<String, Object> map = (Map<String, Object>) ds.getValue();
                        Object bill_amount = map.get("bill_amount");
                        double total_value = Double.parseDouble(String.valueOf(bill_amount));
                        sum8 += total_value;
                        sales_8 = decimalFormat.format(sum8);
                        txtMonth8.setText(sales_8);

                        setValueOnGraph();
                    }
                }

                sum9 = 0;
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    int the_month = ds.child("issuing_month").getValue(Integer.class);
                    if (the_month == 9) {
                        Map<String, Object> map = (Map<String, Object>) ds.getValue();
                        Object bill_amount = map.get("bill_amount");
                        double total_value = Double.parseDouble(String.valueOf(bill_amount));
                        sum9 += total_value;
                        sales_9 = decimalFormat.format(sum9);
                        txtMonth9.setText(sales_9);

                        setValueOnGraph();
                    }
                }

                sum10 = 0;
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    int the_month = ds.child("issuing_month").getValue(Integer.class);
                    if (the_month == 10) {
                        Map<String, Object> map = (Map<String, Object>) ds.getValue();
                        Object bill_amount = map.get("bill_amount");
                        double total_value = Double.parseDouble(String.valueOf(bill_amount));
                        sum10 += total_value;
                        sales_10 = decimalFormat.format(sum10);
                        txtMonth10.setText(sales_10);

                        setValueOnGraph();
                    }
                }

                sum11 = 0;
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    int the_month = ds.child("issuing_month").getValue(Integer.class);
                    if (the_month == 11) {
                        Map<String, Object> map = (Map<String, Object>) ds.getValue();
                        Object bill_amount = map.get("bill_amount");
                        double total_value = Double.parseDouble(String.valueOf(bill_amount));
                        sum11 += total_value;
                        sales_11 = decimalFormat.format(sum11);
                        txtMonth11.setText(sales_11);

                        setValueOnGraph();
                    }
                }

                sum12 = 0;
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    int the_month = ds.child("issuing_month").getValue(Integer.class);
                    if (the_month == 12) {
                        Map<String, Object> map = (Map<String, Object>) ds.getValue();
                        Object bill_amount = map.get("bill_amount");
                        double total_value = Double.parseDouble(String.valueOf(bill_amount));
                        sum12 += total_value;
                        sales_12 = decimalFormat.format(sum12);
                        txtMonth12.setText(sales_12);

                        setValueOnGraph();
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        sales_1 = "0.00";
        sales_2 = "0.00";
        sales_3 = "0.00";
        sales_4 = "0.00";
        sales_5 = "0.00";
        sales_6 = "0.00";
        sales_7 = "0.00";
        sales_8 = "0.00";
        sales_9 = "0.00";
        sales_10 = "0.00";
        sales_11 = "0.00";
        sales_12 = "0.00";


    }

    private void calculateVar() {
        double month1t1 = Double.parseDouble(txtMonth1.getText().toString());
        double month1t2 = Double.parseDouble(txtMonth1t2.getText().toString());

        double var1 = ((month1t2-month1t1)/month1t1)*100;
        String var1_st = decimalFormat.format(var1);
        if (var1 > 0.00) {
            txtVar1.setTextColor(Color.GREEN);
        }
        else if (var1 < 0.00) {
            txtVar1.setTextColor(Color.RED);
        }
        else if (var1 == 0.00) {
            txtVar1.setTextColor(Color.GRAY);
        }
        txtVar1.setText(var1_st+"%");

        double month2t1 = Double.parseDouble(txtMonth2.getText().toString());
        double month2t2 = Double.parseDouble(txtMonth2t2.getText().toString());

        double var2 = ((month2t2-month2t1)/month2t1)*100;
        String var2_st = decimalFormat.format(var2);
        if (var2 > 0.00) {
            txtVar2.setTextColor(Color.GREEN);
        }
        else if (var2 < 0.00) {
            txtVar2.setTextColor(Color.RED);
        }
        else if (var2 == 0.00) {
            txtVar2.setTextColor(Color.GRAY);
        }
        txtVar2.setText(var2_st+"%");

        double month3t1 = Double.parseDouble(txtMonth3.getText().toString());
        double month3t2 = Double.parseDouble(txtMonth3t2.getText().toString());

        double var3 = ((month3t2-month3t1)/month3t1)*100;
        String var3_st = decimalFormat.format(var3);
        if (var3 > 0.00) {
            txtVar3.setTextColor(Color.GREEN);
        }
        else if (var3 < 0.00) {
            txtVar3.setTextColor(Color.RED);
        }
        else if (var3 == 0.00) {
            txtVar3.setTextColor(Color.GRAY);
        }
        txtVar3.setText(var3_st+"%");

        double month4t1 = Double.parseDouble(txtMonth4.getText().toString());
        double month4t2 = Double.parseDouble(txtMonth4t2.getText().toString());

        double var4 = ((month4t2-month4t1)/month4t1)*100;
        String var4_st = decimalFormat.format(var4);
        if (var4 > 0.00) {
            txtVar4.setTextColor(Color.GREEN);
        }
        else if (var4 < 0.00) {
            txtVar4.setTextColor(Color.RED);
        }
        else if (var4 == 0.00) {
            txtVar4.setTextColor(Color.GRAY);
        }
        txtVar4.setText(var4_st+"%");

        double month5t1 = Double.parseDouble(txtMonth5.getText().toString());
        double month5t2 = Double.parseDouble(txtMonth5t2.getText().toString());

        double var5 = ((month5t2-month5t1)/month5t1)*100;
        String var5_st = decimalFormat.format(var5);
        if (var5 > 0.00) {
            txtVar5.setTextColor(Color.GREEN);
        }
        else if (var5 < 0.00) {
            txtVar5.setTextColor(Color.RED);
        }
        else if (var5 == 0.00) {
            txtVar5.setTextColor(Color.GRAY);
        }
        txtVar5.setText(var5_st+"%");

        double month6t1 = Double.parseDouble(txtMonth6.getText().toString());
        double month6t2 = Double.parseDouble(txtMonth6t2.getText().toString());

        double var6 = ((month6t2-month6t1)/month6t1)*100;
        String var6_st = decimalFormat.format(var6);
        if (var6 > 0.00) {
            txtVar6.setTextColor(Color.GREEN);
        }
        else if (var6 < 0.00) {
            txtVar6.setTextColor(Color.RED);
        }
        else if (var6 == 0.00) {
            txtVar6.setTextColor(Color.GRAY);
        }
        txtVar6.setText(var6_st+"%");

        double month7t1 = Double.parseDouble(txtMonth7.getText().toString());
        double month7t2 = Double.parseDouble(txtMonth7t2.getText().toString());

        double var7 = ((month7t2-month7t1)/month7t1)*100;
        String var7_st = decimalFormat.format(var7);
        if (var7 > 0.00) {
            txtVar7.setTextColor(Color.GREEN);
        }
        else if (var7 < 0.00) {
            txtVar7.setTextColor(Color.RED);
        }
        else if (var7 == 0.00) {
            txtVar7.setTextColor(Color.GRAY);
        }
        txtVar7.setText(var7_st+"%");

        double month8t1 = Double.parseDouble(txtMonth8.getText().toString());
        double month8t2 = Double.parseDouble(txtMonth8t2.getText().toString());

        double var8 = ((month8t2-month8t1)/month8t1)*100;
        String var8_st = decimalFormat.format(var8);
        if (var8 > 0.00) {
            txtVar8.setTextColor(Color.GREEN);
        }
        else if (var8 < 0.00) {
            txtVar8.setTextColor(Color.RED);
        }
        else if (var8 == 0.00) {
            txtVar8.setTextColor(Color.GRAY);
        }
        txtVar8.setText(var8_st+"%");

        double month9t1 = Double.parseDouble(txtMonth9.getText().toString());
        double month9t2 = Double.parseDouble(txtMonth9t2.getText().toString());

        double var9 = ((month9t2-month9t1)/month9t1)*100;
        String var9_st = decimalFormat.format(var9);
        if (var9 > 0.00) {
            txtVar9.setTextColor(Color.GREEN);
        }
        else if (var9 < 0.00) {
            txtVar9.setTextColor(Color.RED);
        }
        else if (var9 == 0.00) {
            txtVar9.setTextColor(Color.GRAY);
        }
        txtVar9.setText(var9_st+"%");

        double month10t1 = Double.parseDouble(txtMonth10.getText().toString());
        double month10t2 = Double.parseDouble(txtMonth10t2.getText().toString());

        double var10 = ((month10t2-month10t1)/month10t1)*100;
        String var10_st = decimalFormat.format(var10);
        if (var10 > 0.00) {
            txtVar10.setTextColor(Color.GREEN);
        }
        else if (var10 < 0.00) {
            txtVar10.setTextColor(Color.RED);
        }
        else if (var10 == 0.00) {
            txtVar10.setTextColor(Color.GRAY);
        }
        txtVar10.setText(var10_st+"%");

        double month11t1 = Double.parseDouble(txtMonth11.getText().toString());
        double month11t2 = Double.parseDouble(txtMonth11t2.getText().toString());

        double var11 = ((month11t2-month11t1)/month11t1)*100;
        String var11_st = decimalFormat.format(var11);
        if (var11 > 0.00) {
            txtVar11.setTextColor(Color.GREEN);
        }
        else if (var11 < 0.00) {
            txtVar11.setTextColor(Color.RED);
        }
        else if (var11 == 0.00) {
            txtVar11.setTextColor(Color.GRAY);
        }
        txtVar11.setText(var11_st+"%");

        double month12t1 = Double.parseDouble(txtMonth12.getText().toString());
        double month12t2 = Double.parseDouble(txtMonth12t2.getText().toString());

        double var12 = ((month12t2-month12t1)/month12t1)*100;
        String var12_st = decimalFormat.format(var12);
        if (var12 > 0.00) {
            txtVar12.setTextColor(Color.GREEN);
        }
        else if (var12 < 0.00) {
            txtVar12.setTextColor(Color.RED);
        }
        else if (var12 == 0.00) {
            txtVar12.setTextColor(Color.GRAY);
        }
        txtVar12.setText(var12_st+"%");
    }

    private void setValueOnGraph() {
    }
}
