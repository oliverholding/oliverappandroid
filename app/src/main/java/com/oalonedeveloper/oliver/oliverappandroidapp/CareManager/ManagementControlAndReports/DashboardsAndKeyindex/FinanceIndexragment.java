package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.ManagementControlAndReports.DashboardsAndKeyindex;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
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
import java.util.HashMap;
import java.util.Map;


public class FinanceIndexragment extends Fragment {

    int day,month,year;
    DatabaseReference companyRef;
    DecimalFormat decimalFormat;
    String post_key,month1,month2,month3,month4,month5,month6,month7,month8,month9,month10,month11,month12;
    double sum1,sum2,sum3,sum4,sum5,sum6,sum7,sum8,sum9,sum10,sum11,sum12;
    LineChart mLineChart;
    float f1,f2,f3,f4,f5,f6,f7,f8,f9,f10,f11,f12;

    String month1s,month2s,month3s,month4s,month5s,month6s,month7s,month8s,month9s,month10s,month11s,month12s;
    double sum1s,sum2s,sum3s,sum4s,sum5s,sum6s,sum7s,sum8s,sum9s,sum10s,sum11s,sum12s;

    PieChart pieChart;
    String tangible_st,intangible_st;
    double sum_tangible,sum_intangible,total_assets;
    int sum_tangible_int,sum_intangible_int;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_finance_indexragment, container, false);

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

        month1s = "0.00";
        month2s= "0.00";
        month3s = "0.00";
        month4s = "0.00";
        month5s = "0.00";
        month6s = "0.00";
        month7s= "0.00";
        month8s = "0.00";
        month9s = "0.00";
        month10s = "0.00";
        month11s = "0.00";
        month12s = "0.00";

        post_key = getActivity().getIntent().getExtras().getString("post_key");
        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");

        decimalFormat = new DecimalFormat("0.00");

        Date date= new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        day = cal.get(Calendar.DAY_OF_MONTH);
        month = cal.get(Calendar.MONTH)+1;
        year = cal.get(Calendar.YEAR);

        mLineChart = view.findViewById(R.id.linechart);
        pieChart = view.findViewById(R.id.pieChart);


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

                        }

                        if (expiration_month.equals("2")) {
                            Map<String, Object> map = (Map<String, Object>) ds.getValue();
                            Object bill_amount = map.get("bill_amount");
                            double bill_amount_db = Double.parseDouble(String.valueOf(bill_amount));
                            sum2 += bill_amount_db;
                            month2 = decimalFormat.format(sum2);

                        }

                        if (expiration_month.equals("3")) {
                            Map<String, Object> map = (Map<String, Object>) ds.getValue();
                            Object bill_amount = map.get("bill_amount");
                            double bill_amount_db = Double.parseDouble(String.valueOf(bill_amount));
                            sum3 += bill_amount_db;
                            month3 = decimalFormat.format(sum3);

                        }

                        if (expiration_month.equals("4")) {
                            Map<String, Object> map = (Map<String, Object>) ds.getValue();
                            Object bill_amount = map.get("bill_amount");
                            double bill_amount_db = Double.parseDouble(String.valueOf(bill_amount));
                            sum4 += bill_amount_db;
                            month4 = decimalFormat.format(sum4);

                        }

                        if (expiration_month.equals("5")) {
                            Map<String, Object> map = (Map<String, Object>) ds.getValue();
                            Object bill_amount = map.get("bill_amount");
                            double bill_amount_db = Double.parseDouble(String.valueOf(bill_amount));
                            sum5 += bill_amount_db;
                            month5 = decimalFormat.format(sum5);

                        }

                        if (expiration_month.equals("6")) {
                            Map<String, Object> map = (Map<String, Object>) ds.getValue();
                            Object bill_amount = map.get("bill_amount");
                            double bill_amount_db = Double.parseDouble(String.valueOf(bill_amount));
                            sum6 += bill_amount_db;
                            month6 = decimalFormat.format(sum6);

                        }

                        if (expiration_month.equals("7")) {
                            Map<String, Object> map = (Map<String, Object>) ds.getValue();
                            Object bill_amount = map.get("bill_amount");
                            double bill_amount_db = Double.parseDouble(String.valueOf(bill_amount));
                            sum7 += bill_amount_db;
                            month7 = decimalFormat.format(sum7);

                        }

                        if (expiration_month.equals("8")) {
                            Map<String, Object> map = (Map<String, Object>) ds.getValue();
                            Object bill_amount = map.get("bill_amount");
                            double bill_amount_db = Double.parseDouble(String.valueOf(bill_amount));
                            sum8 += bill_amount_db;
                            month8 = decimalFormat.format(sum8);

                        }

                        if (expiration_month.equals("9")) {
                            Map<String, Object> map = (Map<String, Object>) ds.getValue();
                            Object bill_amount = map.get("bill_amount");
                            double bill_amount_db = Double.parseDouble(String.valueOf(bill_amount));
                            sum9 += bill_amount_db;
                            month9 = decimalFormat.format(sum9);

                        }

                        if (expiration_month.equals("10")) {
                            Map<String, Object> map = (Map<String, Object>) ds.getValue();
                            Object bill_amount = map.get("bill_amount");
                            double bill_amount_db = Double.parseDouble(String.valueOf(bill_amount));
                            sum10 += bill_amount_db;
                            month10 = decimalFormat.format(sum10);

                        }

                        if (expiration_month.equals("11")) {
                            Map<String, Object> map = (Map<String, Object>) ds.getValue();
                            Object bill_amount = map.get("bill_amount");
                            double bill_amount_db = Double.parseDouble(String.valueOf(bill_amount));
                            sum11 += bill_amount_db;
                            month11 = decimalFormat.format(sum11);

                        }

                        if (expiration_month.equals("12")) {
                            Map<String, Object> map = (Map<String, Object>) ds.getValue();
                            Object bill_amount = map.get("bill_amount");
                            double bill_amount_db = Double.parseDouble(String.valueOf(bill_amount));
                            sum12 += bill_amount_db;
                            month12 = decimalFormat.format(sum12);

                        }


                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        companyRef.child(post_key).child("Purchased Orders").orderByChild("operation_year").equalTo(year+"").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                sum1s = 0;
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String state = ds.child("purchase_payment_state").getValue().toString();
                    String expiration_day = ds.child("expiration_day").getValue().toString();
                    String expiration_month = ds.child("expiration_month").getValue().toString();
                    String expiration_year = ds.child("expiration_year").getValue().toString();
                    String operation_day = ds.child("operation_day").getValue().toString();
                    String operation_month = ds.child("operation_month").getValue().toString();
                    String operation_time = ds.child("operation_time").getValue().toString();

                    if (state.equals("no_paid") || state.equals("expired")) {
                        if (expiration_month.equals("1")) {
                            Map<String, Object> map = (Map<String, Object>) ds.getValue();
                            Object bill_amount = map.get("purchase_order_total_amount");
                            double bill_amount_db = Double.parseDouble(String.valueOf(bill_amount));
                            sum1s += bill_amount_db;
                            month1s = decimalFormat.format(sum1s);

                        }

                        if (expiration_month.equals("2")) {
                            Map<String, Object> map = (Map<String, Object>) ds.getValue();
                            Object bill_amount = map.get("purchase_order_total_amount");
                            double bill_amount_db = Double.parseDouble(String.valueOf(bill_amount));
                            sum2s += bill_amount_db;
                            month2s = decimalFormat.format(sum2s);

                        }

                        if (expiration_month.equals("3")) {
                            Map<String, Object> map = (Map<String, Object>) ds.getValue();
                            Object bill_amount = map.get("purchase_order_total_amount");
                            double bill_amount_db = Double.parseDouble(String.valueOf(bill_amount));
                            sum3s += bill_amount_db;
                            month3s = decimalFormat.format(sum3s);

                        }

                        if (expiration_month.equals("4")) {
                            Map<String, Object> map = (Map<String, Object>) ds.getValue();
                            Object bill_amount = map.get("purchase_order_total_amount");
                            double bill_amount_db = Double.parseDouble(String.valueOf(bill_amount));
                            sum4s += bill_amount_db;
                            month4s = decimalFormat.format(sum4s);

                        }

                        if (expiration_month.equals("5")) {
                            Map<String, Object> map = (Map<String, Object>) ds.getValue();
                            Object bill_amount = map.get("purchase_order_total_amount");
                            double bill_amount_db = Double.parseDouble(String.valueOf(bill_amount));
                            sum5s += bill_amount_db;
                            month5s = decimalFormat.format(sum5s);

                        }

                        if (expiration_month.equals("6")) {
                            Map<String, Object> map = (Map<String, Object>) ds.getValue();
                            Object bill_amount = map.get("purchase_order_total_amount");
                            double bill_amount_db = Double.parseDouble(String.valueOf(bill_amount));
                            sum6s += bill_amount_db;
                            month6s = decimalFormat.format(sum6s);

                        }

                        if (expiration_month.equals("7")) {
                            Map<String, Object> map = (Map<String, Object>) ds.getValue();
                            Object bill_amount = map.get("purchase_order_total_amount");
                            double bill_amount_db = Double.parseDouble(String.valueOf(bill_amount));
                            sum7s += bill_amount_db;
                            month7s = decimalFormat.format(sum7s);

                        }

                        if (expiration_month.equals("8")) {
                            Map<String, Object> map = (Map<String, Object>) ds.getValue();
                            Object bill_amount = map.get("purchase_order_total_amount");
                            double bill_amount_db = Double.parseDouble(String.valueOf(bill_amount));
                            sum8s += bill_amount_db;
                            month8s = decimalFormat.format(sum8s);

                        }

                        if (expiration_month.equals("9")) {
                            Map<String, Object> map = (Map<String, Object>) ds.getValue();
                            Object bill_amount = map.get("purchase_order_total_amount");
                            double bill_amount_db = Double.parseDouble(String.valueOf(bill_amount));
                            sum9s += bill_amount_db;
                            month9s = decimalFormat.format(sum9s);

                        }

                        if (expiration_month.equals("10")) {
                            Map<String, Object> map = (Map<String, Object>) ds.getValue();
                            Object bill_amount = map.get("purchase_order_total_amount");
                            double bill_amount_db = Double.parseDouble(String.valueOf(bill_amount));
                            sum10s += bill_amount_db;
                            month10s = decimalFormat.format(sum10s);

                        }

                        if (expiration_month.equals("11")) {
                            Map<String, Object> map = (Map<String, Object>) ds.getValue();
                            Object bill_amount = map.get("purchase_order_total_amount");
                            double bill_amount_db = Double.parseDouble(String.valueOf(bill_amount));
                            sum11s += bill_amount_db;
                            month11s = decimalFormat.format(sum11s);

                        }

                        if (expiration_month.equals("12")) {
                            Map<String, Object> map = (Map<String, Object>) ds.getValue();
                            Object bill_amount = map.get("purchase_order_total_amount");
                            double bill_amount_db = Double.parseDouble(String.valueOf(bill_amount));
                            sum12s += bill_amount_db;
                            month12s = decimalFormat.format(sum12s);

                        }

                        setValueOnGraph();

                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        calculateTotalSum();

        return view;
    }

    private void showPieChart(){

        ArrayList<PieEntry> pieEntries = new ArrayList<>();

        //initializing data
        Map<String, Integer> typeAmountMap = new HashMap<>();
        typeAmountMap.put("Tangibles",sum_tangible_int);
        typeAmountMap.put("Inangibles",sum_intangible_int);

        //initializing colors for the entries
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.parseColor("#304567"));
        colors.add(Color.parseColor("#309967"));

        //input data and fit data into pie chart entry
        for(String type: typeAmountMap.keySet()){
            pieEntries.add(new PieEntry(typeAmountMap.get(type).floatValue(), type));
        }

        //collecting the entries with label name
        PieDataSet pieDataSet = new PieDataSet(pieEntries,"");
        //setting text size of the value
        pieDataSet.setValueTextSize(12f);
        //providing color list for coloring different entries
        pieDataSet.setColors(colors);
        //grouping the data set from entry to chart
        PieData pieData = new PieData(pieDataSet);
        //showing the value of the entries, default true if not set
        pieData.setDrawValues(true);

        pieChart.setData(pieData);
        pieChart.invalidate();

        pieData.setValueFormatter(new PercentFormatter());
    }

    private void calculateTotalSum() {
        companyRef.child(post_key).child("Fixed Assets").child("Tangible").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                sum_tangible = 0;
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Map<String, Object> map = (Map<String, Object>) ds.getValue();
                    Object asset_price = map.get("asset_price");
                    Object asset_quantity = map.get("asset_quantity");
                    double asset_price_db = Double.parseDouble((String) asset_price);
                    double asset_quantity_db = Double.parseDouble((String) asset_quantity);
                    double total = asset_price_db*asset_quantity_db;
                    sum_tangible += total;
                    tangible_st = decimalFormat.format(sum_tangible);


                }

                companyRef.child(post_key).child("Fixed Assets").child("Intangible").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        sum_intangible = 0;
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            Map<String, Object> map = (Map<String, Object>) ds.getValue();
                            Object asset_price = map.get("asset_price");
                            Object asset_quantity = map.get("asset_quantity");
                            double asset_price_db = Double.parseDouble((String) asset_price);
                            double asset_quantity_db = Double.parseDouble((String) asset_quantity);
                            double total = asset_price_db*asset_quantity_db;
                            sum_intangible += total;
                            intangible_st = decimalFormat.format(sum_intangible);

                        }
                        total_assets = sum_tangible+sum_intangible;

                        double tan_percent = (sum_tangible/total_assets)*100;
                        double intan_percent = (sum_intangible/total_assets)*100;
                        sum_tangible_int = (int) tan_percent;
                        sum_intangible_int = (int) intan_percent;

                        showPieChart();
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

    private ArrayList<Entry> dataValues2()
    {

        f1 = Float.parseFloat(month1s);
        f2 = Float.parseFloat(month2s);
        f3 = Float.parseFloat(month3s);
        f4 = Float.parseFloat(month4s);
        f5 = Float.parseFloat(month5s);
        f6 = Float.parseFloat(month6s);
        f7 = Float.parseFloat(month7s);
        f8 = Float.parseFloat(month8s);
        f9 = Float.parseFloat(month9s);
        f10 = Float.parseFloat(month10s);
        f11 = Float.parseFloat(month11s);
        f12 = Float.parseFloat(month12s);

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
        LineDataSet lineDataSet1 = new LineDataSet(dataValues1(),"Por Cobrar");
        LineDataSet lineDataSet2 = new LineDataSet(dataValues2(),"Por Pagar");
        lineDataSet1.setLineWidth(2);
        lineDataSet1.setColor(Color.GREEN);
        lineDataSet1.setCircleColor(Color.GREEN);
        lineDataSet1.setCircleColorHole(Color.GREEN);
        lineDataSet2.setColor(Color.DKGRAY);
        lineDataSet2.setCircleColor(Color.DKGRAY);
        lineDataSet2.setCircleColorHole(Color.DKGRAY);
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(lineDataSet1);
        dataSets.add(lineDataSet2);


        LineData data = new LineData(dataSets);
        mLineChart.setData(data);
        mLineChart.invalidate();
        mLineChart.getDescription().setEnabled(false);

    }
}
