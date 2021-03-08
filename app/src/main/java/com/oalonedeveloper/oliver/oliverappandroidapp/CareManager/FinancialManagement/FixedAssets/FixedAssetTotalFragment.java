package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.FinancialManagement.FixedAssets;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
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


public class FixedAssetTotalFragment extends Fragment {

    PieChart pieChart;
    float f1,f2;
    DatabaseReference companyRef;
    String post_key, tangible_st,intangible_st;
    double sum_tangible,sum_intangible,total_assets;
    TextView txtTangibleAssets,txtIntangibleAssets;
    DecimalFormat decimalFormat;
    int sum_tangible_int,sum_intangible_int;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fixed_asset_total, container, false);

        pieChart = view.findViewById(R.id.pieChart);

        post_key = getActivity().getIntent().getExtras().getString("post_key");
        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");

        decimalFormat = new DecimalFormat("0.00");

        txtTangibleAssets = view.findViewById(R.id.txtTangibleAssets);
        txtIntangibleAssets = view.findViewById(R.id.txtIntangibleAssets);

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
                    txtTangibleAssets.setText("S/ "+tangible_st);

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
                            txtIntangibleAssets.setText("S/ "+intangible_st);

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
}
