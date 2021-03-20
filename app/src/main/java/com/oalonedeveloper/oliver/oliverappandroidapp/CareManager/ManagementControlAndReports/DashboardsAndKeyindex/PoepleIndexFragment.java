package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.ManagementControlAndReports.DashboardsAndKeyindex;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Map;

public class PoepleIndexFragment extends Fragment {

    BarChart barChart,barChar2;
    DatabaseReference companyRef;
    String post_key;
    DecimalFormat decimalFormat;
    int sum1,sum2,sum3,sum4;
    int evaluated,no_evaluated;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_poeple_index, container, false);

        barChart = view.findViewById(R.id.barChart);
        barChar2 = view.findViewById(R.id.barChart2);
        post_key = getActivity().getIntent().getExtras().getString("post_key");
        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");
        decimalFormat = new DecimalFormat("0.00");

        companyRef.child(post_key).child("People Management Objectives").orderByChild("objective_qualification1").equalTo("Excelente").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                sum1 = 0;
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    sum1 += 1;

                }

                companyRef.child(post_key).child("People Management Objectives").orderByChild("objective_qualification1").equalTo("Bueno").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        sum2 = 0;
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            sum2 += 1;

                        }

                        companyRef.child(post_key).child("People Management Objectives").orderByChild("objective_qualification1").equalTo("Regular").addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                sum3 = 0;
                                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                                    sum3 += 1;

                                }

                                companyRef.child(post_key).child("People Management Objectives").orderByChild("objective_qualification1").equalTo("Malo").addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        sum4 = 0;
                                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                                            sum4 += 1;

                                        }

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

        companyRef.child(post_key).child("Job Profile").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                evaluated = 0;
                for (final DataSnapshot ds : dataSnapshot.getChildren()) {
                    if (ds.hasChild("Evaluation")) {
                        evaluated += 1;
                    } else {
                        no_evaluated +=1;
                    }

                    showChart2();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return view;
    }

    private void showChart2() {
        ArrayList<BarEntry> value1 = new ArrayList<>();
        ArrayList<BarEntry> value2 = new ArrayList<>();

        value1.add(new BarEntry(1,evaluated));
        value2.add(new BarEntry(2,no_evaluated));

        BarDataSet set1,set2;

        set1 = new BarDataSet(value1, "Evaluados");
        set2 = new BarDataSet(value2, "No Evaluados");

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


        barChar2.setData(data);
    }


    private void showChart() {

        ArrayList<BarEntry> value1 = new ArrayList<>();
        ArrayList<BarEntry> value2 = new ArrayList<>();
        ArrayList<BarEntry> value3 = new ArrayList<>();
        ArrayList<BarEntry> value4 = new ArrayList<>();


        value1.add(new BarEntry(1,sum1));
        value2.add(new BarEntry(2,sum2));
        value3.add(new BarEntry(3,sum3));
        value4.add(new BarEntry(4,sum4));

        BarDataSet set1,set2,set3,set4;

        set1 = new BarDataSet(value1, "Excelente");
        set2 = new BarDataSet(value2, "Bueno");
        set3 = new BarDataSet(value3, "Regular");
        set4 = new BarDataSet(value4, "Malo");

        ArrayList<Integer> colors1 = new ArrayList<>();
        ArrayList<Integer> colors2 = new ArrayList<>();
        ArrayList<Integer> colors3 = new ArrayList<>();
        ArrayList<Integer> colors4 = new ArrayList<>();

        colors1.add(Color.parseColor("#E91E63"));
        colors2.add(Color.parseColor("#FFC107"));
        colors3.add(Color.parseColor("#009688"));
        colors4.add(Color.parseColor("#001de9"));

        set1.setColors(colors1);
        set2.setColors(colors2);
        set3.setColors(colors3);
        set4.setColors(colors4);

        ArrayList<IBarDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);
        dataSets.add(set2);
        dataSets.add(set3);
        dataSets.add(set4);

        BarData data = new BarData(dataSets);
        data.setValueTextSize(10f);

        data.setBarWidth(0.9f);


        barChart.setData(data);
    }

}
