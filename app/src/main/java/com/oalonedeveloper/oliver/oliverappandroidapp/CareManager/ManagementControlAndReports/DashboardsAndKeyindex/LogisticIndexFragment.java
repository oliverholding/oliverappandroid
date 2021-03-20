package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.ManagementControlAndReports.DashboardsAndKeyindex;

import android.graphics.Color;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anychart.graphics.vector.Fill;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Logistic.LogisticActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class LogisticIndexFragment extends Fragment {

    BarChart barChart;
    DatabaseReference companyRef;
    String post_key;
    long count1,count2,count3,count4,count5,count6,count7,count8;
    double sum_dispatch, percent_1,percent_2;
    DecimalFormat decimalFormat;
    int percent_1_st,percent_2_st;
    PieChart pieChart;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_logistic_index, container, false);

        barChart = view.findViewById(R.id.barChart);
        post_key = getActivity().getIntent().getExtras().getString("post_key");
        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");

        pieChart = view.findViewById(R.id.pieChart);

        decimalFormat = new DecimalFormat("0.00");

        companyRef.child(post_key).child("Purchased Orders").orderByChild("purchase_order_state").equalTo("pending").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                count1 = dataSnapshot.getChildrenCount();

                companyRef.child(post_key).child("Purchased Orders").orderByChild("purchase_order_state").equalTo("ready").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        count2 = dataSnapshot.getChildrenCount();

                        companyRef.child(post_key).child("Purchased Orders").orderByChild("purchase_order_state").equalTo("ready_delayed").addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                count3 = dataSnapshot.getChildrenCount();

                                companyRef.child(post_key).child("Purchased Orders").orderByChild("purchase_order_state").equalTo("returned").addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        count4 = dataSnapshot.getChildrenCount();

                                        companyRef.child(post_key).child("Purchased Orders").orderByChild("purchase_order_state").equalTo("supplier_rejected").addValueEventListener(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                count5 = dataSnapshot.getChildrenCount();

                                                companyRef.child(post_key).child("Purchased Orders").orderByChild("purchase_order_state").equalTo("canceled").addValueEventListener(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                                        count6 = dataSnapshot.getChildrenCount();
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



        companyRef.child(post_key).child("Dispatches").orderByChild("dispatch_state").equalTo("pending").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                count7 = dataSnapshot.getChildrenCount();

                companyRef.child(post_key).child("Dispatches").orderByChild("dispatch_state").equalTo("ready").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        count8 = dataSnapshot.getChildrenCount();

                        sum_dispatch = count7+count8;

                        percent_1 = (count7/sum_dispatch);
                        percent_2 = (count8/sum_dispatch);

                        percent_1_st = (int) count7;
                        percent_2_st = (int) count8;

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



        return view;
    }

    private void showPieChart() {
        ArrayList<PieEntry> pieEntries = new ArrayList<>();

        //initializing data
        Map<String, Integer> typeAmountMap = new HashMap<>();
        typeAmountMap.put("Pendiente",percent_1_st);
        typeAmountMap.put("Despachado",percent_2_st);


        //initializing colors for the entries
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.parseColor("#E91E63"));
        colors.add(Color.parseColor("#FFC107"));

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

    private void showChart() {

        ArrayList<BarEntry> value1 = new ArrayList<>();
        ArrayList<BarEntry> value2 = new ArrayList<>();
        ArrayList<BarEntry> value3 = new ArrayList<>();
        ArrayList<BarEntry> value4 = new ArrayList<>();
        ArrayList<BarEntry> value5 = new ArrayList<>();
        ArrayList<BarEntry> value6 = new ArrayList<>();

        value1.add(new BarEntry(1,count1));
        value2.add(new BarEntry(2,count2));
        value3.add(new BarEntry(3,count3));
        value4.add(new BarEntry(4,count4));
        value5.add(new BarEntry(5,count5));
        value6.add(new BarEntry(6,count6));

        BarDataSet set1,set2,set3,set4,set5,set6;


        set1 = new BarDataSet(value1, "Enviado");
        set2 = new BarDataSet(value2, "Entregado");
        set3 = new BarDataSet(value3, "Entregado con retraso");
        set4 = new BarDataSet(value4, "Devuelto");
        set5 = new BarDataSet(value5, "Rechazado por Proveedor");
        set6 = new BarDataSet(value6, "Cancelado");


        ArrayList<Integer> colors1 = new ArrayList<>();
        ArrayList<Integer> colors2 = new ArrayList<>();
        ArrayList<Integer> colors3 = new ArrayList<>();
        ArrayList<Integer> colors4 = new ArrayList<>();
        ArrayList<Integer> colors5 = new ArrayList<>();
        ArrayList<Integer> colors6 = new ArrayList<>();
        colors1.add(Color.parseColor("#E91E63"));
        colors2.add(Color.parseColor("#FFC107"));
        colors3.add(Color.parseColor("#009688"));
        colors4.add(Color.parseColor("#001de9"));
        colors5.add(Color.parseColor("#00b1f7"));
        colors6.add(Color.parseColor("#47484D"));


        set1.setColors(colors1);
        set2.setColors(colors2);
        set3.setColors(colors3);
        set4.setColors(colors4);
        set5.setColors(colors5);
        set6.setColors(colors6);

        ArrayList<IBarDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);
        dataSets.add(set2);
        dataSets.add(set3);
        dataSets.add(set4);
        dataSets.add(set5);
        dataSets.add(set6);

        BarData data = new BarData(dataSets);
        data.setValueTextSize(10f);

        data.setBarWidth(0.9f);


        barChart.setData(data);
    }


}
