package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.ManagementControlAndReports.DashboardsAndKeyindex;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
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


public class GeneralIndexFragment extends Fragment {

    DatabaseReference myCompanyRef;
    String post_key,customer_def;
    long children_count, contact_count;
    DecimalFormat decimalFormat;
    PieChart pieChart;
    int percent_1,percent_2,percent_3,percent_4,percent_5,percent_6;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_general_index, container, false);

        post_key = getActivity().getIntent().getExtras().getString("post_key");
        myCompanyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");

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


        return view;
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
