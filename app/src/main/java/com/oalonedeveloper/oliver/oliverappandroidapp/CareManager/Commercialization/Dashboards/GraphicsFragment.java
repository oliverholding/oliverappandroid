package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Commercialization.Dashboards;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

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
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;


public class GraphicsFragment extends Fragment {

    TextView txtCurrentYear,txtMonth1,txtMonth2,txtMonth3,txtMonth4,txtMonth5,txtMonth6,txtMonth7,txtMonth8,txtMonth9,txtMonth10,txtMonth11,txtMonth12;
    int day,month,year;
    DatabaseReference companyRef;
    String post_key,sales_1,sales_2,sales_3,sales_4,sales_5,sales_6,sales_7,sales_8,sales_9,sales_10,sales_11,sales_12;
    double sum1,sum2,sum3,sum4,sum5,sum6,sum7,sum8,sum9,sum10,sum11,sum12;
    float f1,f2,f3,f4,f5,f6,f7,f8,f9,f10,f11,f12;
    DecimalFormat decimalFormat;
    LineChart mLineChart;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_graphics, container, false);

        post_key = getActivity().getIntent().getExtras().getString("post_key");
        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");


        mLineChart = view.findViewById(R.id.linechart);
        txtCurrentYear = view.findViewById(R.id.txtCurrentYear);
        txtMonth1 = view.findViewById(R.id.txtMonth1);
        txtMonth2 = view.findViewById(R.id.txtMonth2);
        txtMonth3 = view.findViewById(R.id.txtMonth3);
        txtMonth4 = view.findViewById(R.id.txtMonth4);
        txtMonth5 = view.findViewById(R.id.txtMonth5);
        txtMonth6 = view.findViewById(R.id.txtMonth6);
        txtMonth7 = view.findViewById(R.id.txtMonth7);
        txtMonth8 = view.findViewById(R.id.txtMonth8);
        txtMonth9 = view.findViewById(R.id.txtMonth9);
        txtMonth10 = view.findViewById(R.id.txtMonth10);
        txtMonth11 = view.findViewById(R.id.txtMonth11);
        txtMonth12 = view.findViewById(R.id.txtMonth12);

        Date date= new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        day = cal.get(Calendar.DAY_OF_MONTH);
        month = cal.get(Calendar.MONTH)+1;
        year = cal.get(Calendar.YEAR);


        decimalFormat = new DecimalFormat("0.00");

        txtCurrentYear.setText("AÑO: "+year);
        //total_sales
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
                        txtMonth1.setText("S/ "+sales_1);

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
                        txtMonth2.setText("S/ "+sales_2);

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
                        txtMonth3.setText("S/ "+sales_3);

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
                        txtMonth4.setText("S/ "+sales_4);

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
                        txtMonth5.setText("S/ "+sales_5);

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
                        txtMonth6.setText("S/ "+sales_6);

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
                        txtMonth7.setText("S/ "+sales_7);

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
                        txtMonth8.setText("S/ "+sales_8);

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
                        txtMonth9.setText("S/ "+sales_9);

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
                        txtMonth10.setText("S/ "+sales_10);

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
                        txtMonth11.setText("S/ "+sales_11);

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
                        txtMonth12.setText("S/ "+sales_12);

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

    private ArrayList<Entry> dataValues2()
    {


        ArrayList<Entry> dataVals = new ArrayList<Entry>();
        dataVals.add(new Entry(1, 14));
        dataVals.add(new Entry(2, 15));
        dataVals.add(new Entry(3, 45));
        dataVals.add(new Entry(4, 56));
        dataVals.add(new Entry(5, 54));
        dataVals.add(new Entry(6,  56));
        dataVals.add(new Entry(7, 67));
        dataVals.add(new Entry(8, 45));
        dataVals.add(new Entry(9, 24));
        dataVals.add(new Entry(10, 56));
        dataVals.add(new Entry(11, 24));
        dataVals.add(new Entry(12, 56));
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

}
