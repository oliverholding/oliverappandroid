package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Commercialization.Dashboards;

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
import com.oalonedeveloper.oliver.oliverappandroidapp.R;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;


public class GraphicsFragment extends Fragment {

    LineChart mLineChart;
    TextView txtCurrentYear,txtMonth12;
    int day,month,year;
    DatabaseReference companyRef;
    String post_key,sales_12;
    double sum12;
    float f12;
    DecimalFormat decimalFormat;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_graphics, container, false);

        post_key = getActivity().getIntent().getExtras().getString("post_key");
        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");

        LineDataSet lineDataSet1 = new LineDataSet(dataValues1(),"Ingreso Mensual");
        lineDataSet1.setLineWidth(2);
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(lineDataSet1);

        mLineChart = view.findViewById(R.id.linechart);
        txtCurrentYear = view.findViewById(R.id.txtCurrentYear);
        txtMonth12 = view.findViewById(R.id.txtMonth12);

        Date date= new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        day = cal.get(Calendar.DAY_OF_MONTH);
        month = cal.get(Calendar.MONTH)+1;
        year = cal.get(Calendar.YEAR);

        LineData data = new LineData(dataSets);
        mLineChart.setData(data);
        mLineChart.invalidate();
        mLineChart.getDescription().setEnabled(false);
        decimalFormat = new DecimalFormat("0.00");

        txtCurrentYear.setText("AÑO: "+year);
        //total_sales
        companyRef.child(post_key).child("My Bills").orderByChild("issuing_year").equalTo(year).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                sum12 = 0;
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    int the_month = ds.child("issuing_month").getValue(Integer.class);
                    if (the_month == month) {
                        Map<String, Object> map = (Map<String, Object>) ds.getValue();
                        Object bill_amount = map.get("bill_amount");
                        double total_value = Double.parseDouble(String.valueOf(bill_amount));
                        sum12 += total_value;
                        sales_12 = decimalFormat.format(sum12);
                        txtMonth12.setText("S/ "+sales_12);
                    }






                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return view;
    }
    private ArrayList<Entry> dataValues1()
    {


        /*f1 = Float.parseFloat(quote_value1);
        f2 = Float.parseFloat(quote_value2);
        f3 = Float.parseFloat(quote_value3);
        f4 = Float.parseFloat(quote_value4);
        f5 = Float.parseFloat(quote_value5);
        f6 = Float.parseFloat(quote_value6);
        f7 = Float.parseFloat(quote_value7);
        f8 = Float.parseFloat(quote_value8);
        f9 = Float.parseFloat(quote_value9);
        f10 = Float.parseFloat(quote_value10);
        f11 = Float.parseFloat(quote_value11);
        f12 = Float.parseFloat(quote_value12);*/
        //f12 = Float.parseFloat(sales_12);

        ArrayList<Entry> dataVals = new ArrayList<Entry>();
        dataVals.add(new Entry(1, 0));
        dataVals.add(new Entry(2, 0));
        dataVals.add(new Entry(3, 0));
        dataVals.add(new Entry(4, 0));
        dataVals.add(new Entry(5, 0));
        dataVals.add(new Entry(6,  0));
        dataVals.add(new Entry(7, 0));
        dataVals.add(new Entry(8, 0));
        dataVals.add(new Entry(9, 0));
        dataVals.add(new Entry(10, 0));
        dataVals.add(new Entry(11, 0));
        dataVals.add(new Entry(12, f12));
        return dataVals;
    }
}
