package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.FinancialManagement.FinancialStatements.FinancialSituation;

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
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class FinancialSituationSumaryFragment extends Fragment {

    PieChart pieChart;
    DatabaseReference companyRef;
    String post_key,total_assets,total_liabilities,total_equity,asset_percent_st,liabilities_percent_st,equity_percent_st;
    DecimalFormat decimalFormat;
    int day,month,year,last_year,percent_assets,percent_liabilities,percent_equity;
    double total_assets_db,total_liabilities_db,total_equity_db,total_sum,asset_percent,liabilities_percent,equity_percent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_financial_situation_sumary, container, false);

        pieChart = view.findViewById(R.id.pieChart);

        post_key = getActivity().getIntent().getExtras().getString("post_key");
        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");

        decimalFormat = new DecimalFormat("0.00");

        Date date= new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        day = cal.get(Calendar.DAY_OF_MONTH);
        month = cal.get(Calendar.MONTH)+1;
        year = cal.get(Calendar.YEAR);
        last_year = year-1;

        companyRef.child(post_key).child("Financial Statements").child("Financial Situation").child(last_year+"").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                total_assets = dataSnapshot.child("total_assets").getValue().toString();
                total_liabilities = dataSnapshot.child("total_liabilities").getValue().toString();
                total_equity = dataSnapshot.child("total_equity").getValue().toString();

                total_assets_db = Double.parseDouble(total_assets);
                total_liabilities_db = Double.parseDouble(total_liabilities);
                total_equity_db = Double.parseDouble(total_equity);

                total_sum = total_assets_db+total_liabilities_db+total_equity_db;
                asset_percent = (total_assets_db/total_sum)*100;
                liabilities_percent = (total_liabilities_db/total_sum)*100;
                equity_percent = (total_equity_db/total_sum)*100;

                asset_percent_st = decimalFormat.format(asset_percent);
                liabilities_percent_st = decimalFormat.format(liabilities_percent);
                equity_percent_st = decimalFormat.format(equity_percent);

                percent_assets = (int) asset_percent;
                percent_liabilities = (int) liabilities_percent;
                percent_equity = (int) equity_percent;

                showPieChart();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return view;
    }

    private void showPieChart(){

        ArrayList<PieEntry> pieEntries = new ArrayList<>();

        //initializing data
        Map<String, Integer> typeAmountMap = new HashMap<>();
        typeAmountMap.put("Activos",percent_assets);
        typeAmountMap.put("Pasivos",percent_liabilities);
        typeAmountMap.put("Patrimonio",percent_equity);

        //initializing colors for the entries
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.parseColor("#304567"));
        colors.add(Color.parseColor("#309967"));
        colors.add(Color.parseColor("#309356"));

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
