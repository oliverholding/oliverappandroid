package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.ManagementControlAndReports.DashboardsAndKeyindex;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;

import java.text.DecimalFormat;
import java.util.ArrayList;


public class NormativityIndexFragment extends Fragment {

    String post_key;
    DatabaseReference companyRef;
    DecimalFormat decimalFormat;
    int valuesnorm1,valuesnorm2,valuesnorm3,valuesnorm4,valuesnorm5;

    BarChart barChart;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_normativity_index, container, false);

        decimalFormat = new DecimalFormat("0.00");

        barChart = view.findViewById(R.id.barChart);

        barChart.setNoDataText("Aún no hay datos disponibles");

        post_key = getActivity().getIntent().getExtras().getString("post_key");
        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");

        companyRef.child(post_key).child("Normativity").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild("Taxes")) {
                    int norm_1 = dataSnapshot.child("Taxes").child("norm_1").getValue(Integer.class);
                    int norm_2 = dataSnapshot.child("Taxes").child("norm_2").getValue(Integer.class);
                    int norm_3 = dataSnapshot.child("Taxes").child("norm_3").getValue(Integer.class);
                    int norm_4 = dataSnapshot.child("Taxes").child("norm_4").getValue(Integer.class);
                    int norm_5 = dataSnapshot.child("Taxes").child("norm_5").getValue(Integer.class);
                    int norm_6 = dataSnapshot.child("Taxes").child("norm_6").getValue(Integer.class);

                    double total_percent = (norm_1+norm_2+norm_3+norm_4+norm_5+norm_6);
                    double total_percent_real = (total_percent/6)*100;
                    String total_percent_st = decimalFormat.format(total_percent_real);

                    valuesnorm1 = (int) total_percent_real;

                    showChart();


                } if (dataSnapshot.hasChild("Labour")) {
                    int norm_1 = dataSnapshot.child("Labour").child("norm_1").getValue(Integer.class);
                    int norm_2 = dataSnapshot.child("Labour").child("norm_2").getValue(Integer.class);
                    int norm_3 = dataSnapshot.child("Labour").child("norm_3").getValue(Integer.class);
                    int norm_4 = dataSnapshot.child("Labour").child("norm_4").getValue(Integer.class);
                    int norm_5 = dataSnapshot.child("Labour").child("norm_5").getValue(Integer.class);
                    int norm_6 = dataSnapshot.child("Labour").child("norm_6").getValue(Integer.class);
                    int norm_7 = dataSnapshot.child("Labour").child("norm_7").getValue(Integer.class);
                    int norm_8 = dataSnapshot.child("Labour").child("norm_8").getValue(Integer.class);
                    int norm_9 = dataSnapshot.child("Labour").child("norm_9").getValue(Integer.class);

                    double total_percent = (norm_1+norm_2+norm_3+norm_4+norm_5+norm_6+norm_7+norm_8+norm_9);
                    double total_percent_real = (total_percent/9)*100;
                    String total_percent_st = decimalFormat.format(total_percent_real);

                    valuesnorm2 = (int) total_percent_real;

                    showChart();


                }
                if (dataSnapshot.hasChild("Sanitary")) {
                    int norm_1 = dataSnapshot.child("Sanitary").child("norm_1").getValue(Integer.class);
                    int norm_2 = dataSnapshot.child("Sanitary").child("norm_2").getValue(Integer.class);
                    int norm_3 = dataSnapshot.child("Sanitary").child("norm_3").getValue(Integer.class);
                    int norm_4 = dataSnapshot.child("Sanitary").child("norm_4").getValue(Integer.class);
                    int norm_5 = dataSnapshot.child("Sanitary").child("norm_5").getValue(Integer.class);
                    int norm_6 = dataSnapshot.child("Sanitary").child("norm_6").getValue(Integer.class);

                    double total_percent = (norm_1+norm_2+norm_3+norm_4+norm_5+norm_6);
                    double total_percent_real = (total_percent/6)*100;
                    String total_percent_st = decimalFormat.format(total_percent_real);

                    valuesnorm3 = (int) total_percent_real;

                    showChart();


                }
                if (dataSnapshot.hasChild("Environmental")) {
                    int norm_1 = dataSnapshot.child("Environmental").child("norm_1").getValue(Integer.class);
                    int norm_2 = dataSnapshot.child("Environmental").child("norm_2").getValue(Integer.class);
                    int norm_3 = dataSnapshot.child("Environmental").child("norm_3").getValue(Integer.class);
                    int norm_4 = dataSnapshot.child("Environmental").child("norm_4").getValue(Integer.class);
                    int  norm_5 = dataSnapshot.child("Environmental").child("norm_5").getValue(Integer.class);
                    int norm_6 = dataSnapshot.child("Environmental").child("norm_6").getValue(Integer.class);

                    double total_percent = (norm_1+norm_2+norm_3+norm_4+norm_5+norm_6);
                    double total_percent_real = (total_percent/6)*100;
                    String total_percent_st = decimalFormat.format(total_percent_real);

                    valuesnorm4 = (int) total_percent_real;

                    showChart();

                }
                if (dataSnapshot.hasChild("Civil")) {
                    int norm_1 = dataSnapshot.child("Civil").child("norm_1").getValue(Integer.class);
                    int norm_2 = dataSnapshot.child("Civil").child("norm_2").getValue(Integer.class);
                    int norm_3 = dataSnapshot.child("Civil").child("norm_3").getValue(Integer.class);
                    int norm_4 = dataSnapshot.child("Civil").child("norm_4").getValue(Integer.class);
                    int norm_5 = dataSnapshot.child("Civil").child("norm_5").getValue(Integer.class);
                    int norm_6 = dataSnapshot.child("Civil").child("norm_6").getValue(Integer.class);
                    int norm_7 = dataSnapshot.child("Civil").child("norm_7").getValue(Integer.class);
                    int norm_8 = dataSnapshot.child("Civil").child("norm_8").getValue(Integer.class);

                    double total_percent = (norm_1+norm_2+norm_3+norm_4+norm_5+norm_6+norm_7+norm_8);
                    double total_percent_real = (total_percent/8)*100;
                    String total_percent_st = decimalFormat.format(total_percent_real);

                    valuesnorm5 = (int) total_percent_real;

                    showChart();

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        return view;
    }

    private void showChart() {

        ArrayList<BarEntry> value1 = new ArrayList<>();
        ArrayList<BarEntry> value2 = new ArrayList<>();
        ArrayList<BarEntry> value3 = new ArrayList<>();
        ArrayList<BarEntry> value4 = new ArrayList<>();
        ArrayList<BarEntry> value5 = new ArrayList<>();

        value1.add(new BarEntry(1,valuesnorm1));
        value2.add(new BarEntry(2,valuesnorm2));
        value3.add(new BarEntry(3,valuesnorm3));
        value4.add(new BarEntry(4,valuesnorm4));
        value5.add(new BarEntry(5,valuesnorm5));

        BarDataSet set1,set2,set3,set4,set5,set6;


        set1 = new BarDataSet(value1, "Tributario");
        set2 = new BarDataSet(value2, "Laboral");
        set3 = new BarDataSet(value3, "Sanitario");
        set4 = new BarDataSet(value4, "Ambiental");
        set5 = new BarDataSet(value5, "Civil");


        ArrayList<Integer> colors1 = new ArrayList<>();
        ArrayList<Integer> colors2 = new ArrayList<>();
        ArrayList<Integer> colors3 = new ArrayList<>();
        ArrayList<Integer> colors4 = new ArrayList<>();
        ArrayList<Integer> colors5 = new ArrayList<>();
        colors1.add(Color.parseColor("#E91E63"));
        colors2.add(Color.parseColor("#FFC107"));
        colors3.add(Color.parseColor("#009688"));
        colors4.add(Color.parseColor("#001de9"));
        colors5.add(Color.parseColor("#00b1f7"));


        set1.setColors(colors1);
        set2.setColors(colors2);
        set3.setColors(colors3);
        set4.setColors(colors4);
        set5.setColors(colors5);

        ArrayList<IBarDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);
        dataSets.add(set2);
        dataSets.add(set3);
        dataSets.add(set4);
        dataSets.add(set5);

        BarData data = new BarData(dataSets);
        data.setValueTextSize(10f);

        data.setBarWidth(0.9f);

        barChart.setData(data);
        barChart.invalidate();
    }

}
