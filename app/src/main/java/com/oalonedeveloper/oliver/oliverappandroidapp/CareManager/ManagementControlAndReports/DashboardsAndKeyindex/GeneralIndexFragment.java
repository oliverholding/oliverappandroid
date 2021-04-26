package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.ManagementControlAndReports.DashboardsAndKeyindex;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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


public class GeneralIndexFragment extends Fragment {

    DatabaseReference myCompanyRef;
    String post_key,customer_def;
    long children_count, contact_count;
    DecimalFormat decimalFormat;
    PieChart pieChart;
    int percent_1,percent_2,percent_3,percent_4,percent_5,percent_6;

    int day,month,year;
    DatabaseReference companyRef;
    String sales_1,sales_2,sales_3,sales_4,sales_5,sales_6,sales_7,sales_8,sales_9,sales_10,sales_11,sales_12;
    double sum1,sum2,sum3,sum4,sum5,sum6,sum7,sum8,sum9,sum10,sum11,sum12;
    float f1,f2,f3,f4,f5,f6,f7,f8,f9,f10,f11,f12;
    LineChart mLineChart;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_general_index, container, false);

        post_key = getActivity().getIntent().getExtras().getString("post_key");
        myCompanyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");
        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");

        pieChart = view.findViewById(R.id.pieChart);
        mLineChart = view.findViewById(R.id.linechart);

        Date date= new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        day = cal.get(Calendar.DAY_OF_MONTH);
        month = cal.get(Calendar.MONTH)+1;
        year = cal.get(Calendar.YEAR);

        decimalFormat = new DecimalFormat("0.00");

        myCompanyRef.child(post_key).child("Customers").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                children_count = dataSnapshot.getChildrenCount();

                myCompanyRef.child(post_key).child("Customers").orderByChild("customer_define").equalTo("contact").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        contact_count = dataSnapshot.getChildrenCount();

                        //Calculate percent
                        float percent = ((float)contact_count/children_count)*100;
                        percent_1 = (int) percent;

                        myCompanyRef.child(post_key).child("Customers").orderByChild("customer_define").equalTo("interested").addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                contact_count = dataSnapshot.getChildrenCount();

                                //Calculate percent
                                float percent = ((float)contact_count/children_count)*100;
                                percent_2 = (int) percent;


                                myCompanyRef.child(post_key).child("Customers").orderByChild("customer_define").equalTo("prospect").addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        contact_count = dataSnapshot.getChildrenCount();

                                        //Calculate percent
                                        float percent = ((float)contact_count/children_count)*100;
                                        percent_3 = (int) percent;


                                        myCompanyRef.child(post_key).child("Customers").orderByChild("customer_define").equalTo("potencial").addValueEventListener(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                contact_count = dataSnapshot.getChildrenCount();

                                                //Calculate percent
                                                float percent = ((float)contact_count/children_count)*100;
                                                percent_4 = (int) percent;

                                                myCompanyRef.child(post_key).child("Customers").orderByChild("customer_define").equalTo("final").addValueEventListener(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                                        contact_count = dataSnapshot.getChildrenCount();

                                                        //Calculate percent
                                                        float percent = ((float)contact_count/children_count)*100;
                                                        percent_5 = (int) percent;

                                                        myCompanyRef.child(post_key).child("Customers").orderByChild("customer_define").equalTo("customer").addValueEventListener(new ValueEventListener() {
                                                            @Override
                                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                                contact_count = dataSnapshot.getChildrenCount();

                                                                //Calculate percent
                                                                float percent = ((float)contact_count/children_count)*100;
                                                                percent_6 = (int) percent;

                                                                showChart();
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


        return view;
    }

    private ArrayList<Entry> dataValues1()
    {
        f1 = Float.parseFloat(sales_1);
        f2 = Float.parseFloat(sales_2);
        f3 = Float.parseFloat(sales_3);
        f4 = Float.parseFloat(sales_4);
        f5 = Float.parseFloat(sales_5);
        f6 = Float.parseFloat(sales_6);
        f7 = Float.parseFloat(sales_7);
        f8 = Float.parseFloat(sales_8);
        f9 = Float.parseFloat(sales_9);
        f10 = Float.parseFloat(sales_10);
        f11 = Float.parseFloat(sales_11);
        f12 = Float.parseFloat(sales_12);

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
        LineDataSet lineDataSet1 = new LineDataSet(dataValues1(),"Ingresos");

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

    private void showChart() {

        ArrayList<PieEntry> pieEntries = new ArrayList<>();

        //initializing data
        Map<String, Integer> typeAmountMap = new HashMap<>();
        typeAmountMap.put("Contacto",percent_1);
        typeAmountMap.put("Interesado",percent_2);
        typeAmountMap.put("Prospecto",percent_3);
        typeAmountMap.put("Potencial",percent_4);
        typeAmountMap.put("Finalista",percent_5);
        typeAmountMap.put("Cliente",percent_6);

        //initializing colors for the entries
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.parseColor("#E91E63"));
        colors.add(Color.parseColor("#FFC107"));
        colors.add(Color.parseColor("#009688"));
        colors.add(Color.parseColor("#001de9"));
        colors.add(Color.parseColor("#00b1f7"));
        colors.add(Color.parseColor("#E36F1E"));

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
}
