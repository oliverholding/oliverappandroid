package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.ManagementControlAndReports.DashboardsAndKeyindex;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class ProductionIndexFragment extends Fragment {

    PieChart pieChart;
    DatabaseReference companyRef;
    String post_key;
    DecimalFormat decimalFormat;
    long count1,count2,count3, total_chain;
    double total, percent1,percent2,percent3,quality,no_quality;
    int p1,p2,p3,p4,p5;

    BarChart barChart;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_production_index, container, false);

        pieChart = view.findViewById(R.id.pieChart);

        post_key = getActivity().getIntent().getExtras().getString("post_key");
        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");

        decimalFormat = new DecimalFormat("0.00");

        barChart = view.findViewById(R.id.barChart);


        companyRef.child(post_key).child("Production Chain").orderByChild("state").equalTo("production").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                count1 = dataSnapshot.getChildrenCount();

                companyRef.child(post_key).child("Production Chain").orderByChild("state").equalTo("stop").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        count2 = dataSnapshot.getChildrenCount();

                        companyRef.child(post_key).child("Production Chain").orderByChild("state").equalTo("ready").addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                count3 = dataSnapshot.getChildrenCount();

                                total = (count1+count2+count3);

                                percent1 = count1/total;
                                percent2 = count2/total;
                                percent3 = count3/total;

                                p1 = (int) count1;
                                p2 = (int) count2;
                                p3 = (int) count3;

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

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        companyRef.child(post_key).child("Production Chain").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                quality = 0;
                no_quality = 0;
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    if (ds.hasChild("Quality Control")) {
                        quality += 1;
                    } else {
                        no_quality +=1;
                    }
                }

                p4 = (int) quality;
                p5 = (int) no_quality;

                showChart();

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
        typeAmountMap.put("En Producción",p1);
        typeAmountMap.put("Detenido",p2);
        typeAmountMap.put("Listo",p3);


        //initializing colors for the entries
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.parseColor("#E91E63"));
        colors.add(Color.parseColor("#FFC107"));
        colors.add(Color.parseColor("#009688"));

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


        value1.add(new BarEntry(1,p4));
        value2.add(new BarEntry(2,p5));

        BarDataSet set1,set2;


        set1 = new BarDataSet(value1, "Con Control de Calidad");
        set2 = new BarDataSet(value2, "Sin Control de Calidad");



        ArrayList<Integer> colors1 = new ArrayList<>();
        ArrayList<Integer> colors2 = new ArrayList<>();
        colors1.add(Color.parseColor("#E91E63"));
        colors2.add(Color.parseColor("#FFC107"));

        set1.setColors(colors1);
        set2.setColors(colors2);

        ArrayList<IBarDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);
        dataSets.add(set2);

        BarData data = new BarData(dataSets);
        data.setValueTextSize(10f);

        data.setBarWidth(0.9f);


        barChart.setData(data);
    }
}
