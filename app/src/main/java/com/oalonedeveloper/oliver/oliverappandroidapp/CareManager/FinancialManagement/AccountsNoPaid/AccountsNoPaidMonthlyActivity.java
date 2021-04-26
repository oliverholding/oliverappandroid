package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.FinancialManagement.AccountsNoPaid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
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

public class AccountsNoPaidMonthlyActivity extends AppCompatActivity {

    TextView txtPeriod,txtMonth1,txtMonth2,txtMonth3,txtMonth4,txtMonth5,txtMonth6,txtMonth7,txtMonth8,txtMonth9,txtMonth10,txtMonth11,txtMonth12;
    int day,month,year;
    DatabaseReference companyRef;
    DecimalFormat decimalFormat;
    String post_key,month1,month2,month3,month4,month5,month6,month7,month8,month9,month10,month11,month12;
    double sum1,sum2,sum3,sum4,sum5,sum6,sum7,sum8,sum9,sum10,sum11,sum12;
    LineChart mLineChart;
    float f1,f2,f3,f4,f5,f6,f7,f8,f9,f10,f11,f12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accounts_no_paid_monthly);

        txtPeriod = findViewById(R.id.txtPeriod);
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
        mLineChart = findViewById(R.id.linechart);

        month1 = "0.00";
        month2 = "0.00";
        month3 = "0.00";
        month4 = "0.00";
        month5 = "0.00";
        month6 = "0.00";
        month7 = "0.00";
        month8 = "0.00";
        month9 = "0.00";
        month10 = "0.00";
        month11 = "0.00";
        month12 = "0.00";

        post_key = getIntent().getExtras().getString("post_key");
        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");

        decimalFormat = new DecimalFormat("0.00");

        Date date= new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        day = cal.get(Calendar.DAY_OF_MONTH);
        month = cal.get(Calendar.MONTH)+1;
        year = cal.get(Calendar.YEAR);

        txtPeriod.setText("AÑO "+year);

        companyRef.child(post_key).child("My Bills").orderByChild("issuing_year").equalTo(year).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                sum1 = 0;
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String state = ds.child("state").getValue().toString();
                    int issuing_day = ds.child("issuing_day").getValue(Integer.class);
                    int issuing_month = ds.child("issuing_month").getValue(Integer.class);
                    int issuing_year = ds.child("issuing_year").getValue(Integer.class);
                    String expiration_day = ds.child("expiration_day").getValue().toString();
                    String expiration_month = ds.child("expiration_month").getValue().toString();
                    String expiration_year = ds.child("expiration_year").getValue().toString();

                    if (state.equals("no_paid") || state.equals("expired")) {
                        if (expiration_month.equals("1")) {
                            Map<String, Object> map = (Map<String, Object>) ds.getValue();
                            Object bill_amount = map.get("bill_amount");
                            double bill_amount_db = Double.parseDouble(String.valueOf(bill_amount));
                            sum1 += bill_amount_db;
                            month1 = decimalFormat.format(sum1);
                            txtMonth1.setText(month1);
                        }

                        if (expiration_month.equals("2")) {
                            Map<String, Object> map = (Map<String, Object>) ds.getValue();
                            Object bill_amount = map.get("bill_amount");
                            double bill_amount_db = Double.parseDouble(String.valueOf(bill_amount));
                            sum2 += bill_amount_db;
                            month2 = decimalFormat.format(sum2);
                            txtMonth2.setText(month2);
                        }

                        if (expiration_month.equals("3")) {
                            Map<String, Object> map = (Map<String, Object>) ds.getValue();
                            Object bill_amount = map.get("bill_amount");
                            double bill_amount_db = Double.parseDouble(String.valueOf(bill_amount));
                            sum3 += bill_amount_db;
                            month3 = decimalFormat.format(sum3);
                            txtMonth3.setText(month3);
                        }

                        if (expiration_month.equals("4")) {
                            Map<String, Object> map = (Map<String, Object>) ds.getValue();
                            Object bill_amount = map.get("bill_amount");
                            double bill_amount_db = Double.parseDouble(String.valueOf(bill_amount));
                            sum4 += bill_amount_db;
                            month4 = decimalFormat.format(sum4);
                            txtMonth4.setText(month4);
                        }

                        if (expiration_month.equals("5")) {
                            Map<String, Object> map = (Map<String, Object>) ds.getValue();
                            Object bill_amount = map.get("bill_amount");
                            double bill_amount_db = Double.parseDouble(String.valueOf(bill_amount));
                            sum5 += bill_amount_db;
                            month5 = decimalFormat.format(sum5);
                            txtMonth5.setText(month5);
                        }

                        if (expiration_month.equals("6")) {
                            Map<String, Object> map = (Map<String, Object>) ds.getValue();
                            Object bill_amount = map.get("bill_amount");
                            double bill_amount_db = Double.parseDouble(String.valueOf(bill_amount));
                            sum6 += bill_amount_db;
                            month6 = decimalFormat.format(sum6);
                            txtMonth6.setText(month6);
                        }

                        if (expiration_month.equals("7")) {
                            Map<String, Object> map = (Map<String, Object>) ds.getValue();
                            Object bill_amount = map.get("bill_amount");
                            double bill_amount_db = Double.parseDouble(String.valueOf(bill_amount));
                            sum7 += bill_amount_db;
                            month7 = decimalFormat.format(sum7);
                            txtMonth7.setText(month7);
                        }

                        if (expiration_month.equals("8")) {
                            Map<String, Object> map = (Map<String, Object>) ds.getValue();
                            Object bill_amount = map.get("bill_amount");
                            double bill_amount_db = Double.parseDouble(String.valueOf(bill_amount));
                            sum8 += bill_amount_db;
                            month8 = decimalFormat.format(sum8);
                            txtMonth8.setText(month8);
                        }

                        if (expiration_month.equals("9")) {
                            Map<String, Object> map = (Map<String, Object>) ds.getValue();
                            Object bill_amount = map.get("bill_amount");
                            double bill_amount_db = Double.parseDouble(String.valueOf(bill_amount));
                            sum9 += bill_amount_db;
                            month9 = decimalFormat.format(sum9);
                            txtMonth9.setText(month9);
                        }

                        if (expiration_month.equals("10")) {
                            Map<String, Object> map = (Map<String, Object>) ds.getValue();
                            Object bill_amount = map.get("bill_amount");
                            double bill_amount_db = Double.parseDouble(String.valueOf(bill_amount));
                            sum10 += bill_amount_db;
                            month10 = decimalFormat.format(sum10);
                            txtMonth10.setText(month10);
                        }

                        if (expiration_month.equals("11")) {
                            Map<String, Object> map = (Map<String, Object>) ds.getValue();
                            Object bill_amount = map.get("bill_amount");
                            double bill_amount_db = Double.parseDouble(String.valueOf(bill_amount));
                            sum11 += bill_amount_db;
                            month11 = decimalFormat.format(sum11);
                            txtMonth11.setText(month11);
                        }

                        if (expiration_month.equals("12")) {
                            Map<String, Object> map = (Map<String, Object>) ds.getValue();
                            Object bill_amount = map.get("bill_amount");
                            double bill_amount_db = Double.parseDouble(String.valueOf(bill_amount));
                            sum12 += bill_amount_db;
                            month12 = decimalFormat.format(sum12);
                            txtMonth12.setText(month12);
                        }

                        setValueOnGraph();

                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    private ArrayList<Entry> dataValues1()
    {
        f1 = Float.parseFloat(month1);
        f2 = Float.parseFloat(month2);
        f3 = Float.parseFloat(month3);
        f4 = Float.parseFloat(month4);
        f5 = Float.parseFloat(month5);
        f6 = Float.parseFloat(month6);
        f7 = Float.parseFloat(month7);
        f8 = Float.parseFloat(month8);
        f9 = Float.parseFloat(month9);
        f10 = Float.parseFloat(month10);
        f11 = Float.parseFloat(month11);
        f12 = Float.parseFloat(month12);

        ArrayList<Entry> dataVals = new ArrayList<Entry>();
        dataVals.add(new Entry(1, f1));
        dataVals.add(new Entry(2, f2));
        dataVals.add(new Entry(3, f3));
        dataVals.add(new Entry(4, f4));
        dataVals.add(new Entry(5, f5));
        dataVals.add(new Entry(6,  f6));
        dataVals.add(new Entry(7, f7));
        dataVals.add(new Entry(8, f8));
        dataVals.add(new Entry(9, f9));
        dataVals.add(new Entry(10, f10));
        dataVals.add(new Entry(11, f11));
        dataVals.add(new Entry(12, f12));
        return dataVals;
    }


    private void setValueOnGraph() {
        LineDataSet lineDataSet1 = new LineDataSet(dataValues1(),"Cuentas por Cobrar");

        lineDataSet1.setLineWidth(2);
        lineDataSet1.setColor(R.color.colorGradientCenterRed);
        lineDataSet1.setCircleColor(R.color.colorGradientCenterRed);
        lineDataSet1.setCircleColorHole(R.color.colorGradientCenterRed);
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(lineDataSet1);

        LineData data = new LineData(dataSets);
        mLineChart.setData(data);
        mLineChart.invalidate();
        mLineChart.getDescription().setEnabled(false);
    }
}
