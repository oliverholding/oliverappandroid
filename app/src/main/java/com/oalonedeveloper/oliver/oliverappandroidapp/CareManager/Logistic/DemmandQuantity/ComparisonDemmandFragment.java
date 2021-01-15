package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Logistic.DemmandQuantity;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;


public class ComparisonDemmandFragment extends Fragment {

    String post_key,product_id;
    DatabaseReference companyRef;
    int day,month,year,last_year;
    DecimalFormat decimalFormat;
    double sum1,sum2,sum3,sum4,sum5,sum6,sum7,sum8,sum9,sum10,sum11,sum12;
    double sumq1,sumq2,sumq3,sumq4,sumq5,sumq6,sumq7,sumq8,sumq9,sumq10,sumq11,sumq12;
    double sums1,sums2,sums3,sums4,sums5,sums6,sums7,sums8,sums9,sums10,sums11,sums12;
    double sumsr1,sumsr2,sumsr3,sumsr4,sumsr5,sumsr6,sumsr7,sumsr8,sumsr9,sumsr10,sumsr11,sumsr12;
    TextView txtUnits,txtMoney;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_comparison_demmand, container, false);

        post_key = getActivity().getIntent().getExtras().getString("post_key");
        product_id = getActivity().getIntent().getExtras().getString("product_id");
        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");

        decimalFormat = new DecimalFormat("0.00");

        txtUnits = view.findViewById(R.id.txtUnits);
        txtMoney = view.findViewById(R.id.txtMoney);

        Date date= new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        day = cal.get(Calendar.DAY_OF_MONTH);
        month = cal.get(Calendar.MONTH)+1;
        year = cal.get(Calendar.YEAR);
        last_year = year-1;

        sum1 = 0.00;
        sum2 = 0.00;
        sum3 = 0.00;
        sum4 = 0.00;
        sum5 = 0.00;
        sum6 = 0.00;
        sum7 = 0.00;
        sum8 = 0.00;
        sum9 = 0.00;
        sum10 = 0.00;
        sum11 = 0.00;
        sum12 = 0.00;

        sumq1 = 0.00;
        sumq2 = 0.00;
        sumq3 = 0.00;
        sumq4 = 0.00;
        sumq5 = 0.00;
        sumq6 = 0.00;
        sumq7 = 0.00;
        sumq8 = 0.00;
        sumq9 = 0.00;
        sumq10 = 0.00;
        sumq11 = 0.00;
        sumq12 = 0.00;

        sums1 = 0.00;
        sums2 = 0.00;
        sums3 = 0.00;
        sums4 = 0.00;
        sums5 = 0.00;
        sums6 = 0.00;
        sums7 = 0.00;
        sums8 = 0.00;
        sums9 = 0.00;
        sums10 = 0.00;
        sums11 = 0.00;
        sums12 = 0.00;

        sumsr1 = 0.00;
        sumsr2 = 0.00;
        sumsr3 = 0.00;
        sumsr4 = 0.00;
        sumsr5 = 0.00;
        sumsr6 = 0.00;
        sumsr7 = 0.00;
        sumsr8 = 0.00;
        sumsr9 = 0.00;
        sumsr10 = 0.00;
        sumsr11 = 0.00;
        sumsr12 = 0.00;

        companyRef.child(post_key).child("My Products").child(product_id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild(last_year + "1" + "quantity")) {
                    double last_year_quantity1 = dataSnapshot.child(last_year + "1"  + "quantity").getValue(Double.class);
                    sumq1 = last_year_quantity1;
                }
                else {

                }
                if (dataSnapshot.hasChild(last_year + "1" + "quantity_projection")) {
                    double last_year_quantity_projection1 = dataSnapshot.child(last_year + "1"  + "quantity_projection").getValue(Double.class);
                    sum1 = last_year_quantity_projection1;
                }
                else {

                }
                if (dataSnapshot.hasChild(last_year + "1" + "quantity") && dataSnapshot.hasChild(last_year + "1" + "quantity_projection")) {
                    double last_year_quantity1 = dataSnapshot.child(last_year + "1"  + "quantity").getValue(Double.class);
                    double last_year_quantity_projection1 = dataSnapshot.child(last_year + "1"  + "quantity_projection").getValue(Double.class);

                    double up = (last_year_quantity1-last_year_quantity_projection1);
                    double var1 = (up/last_year_quantity_projection1)*100;
                    String var1_st = decimalFormat.format(var1);

                } else {

                }

                if (dataSnapshot.hasChild(last_year + "2" + "quantity")) {
                    double last_year_quantity1 = dataSnapshot.child(last_year + "2"  + "quantity").getValue(Double.class);
                    sumq2 = last_year_quantity1;
                }
                else {

                }
                if (dataSnapshot.hasChild(last_year + "2" + "quantity_projection")) {
                    double last_year_quantity_projection1 = dataSnapshot.child(last_year + "2"  + "quantity_projection").getValue(Double.class);
                    sum2 = last_year_quantity_projection1;
                }
                else {

                }
                if (dataSnapshot.hasChild(last_year + "2" + "quantity") && dataSnapshot.hasChild(last_year + "2" + "quantity_projection")) {
                    double last_year_quantity1 = dataSnapshot.child(last_year + "2"  + "quantity").getValue(Double.class);
                    double last_year_quantity_projection1 = dataSnapshot.child(last_year + "2"  + "quantity_projection").getValue(Double.class);

                    double up = (last_year_quantity1-last_year_quantity_projection1);
                    double var1 = (up/last_year_quantity_projection1)*100;
                    String var1_st = decimalFormat.format(var1);

                } else {

                }

                if (dataSnapshot.hasChild(last_year + "3" + "quantity")) {
                    double last_year_quantity1 = dataSnapshot.child(last_year + "3"  + "quantity").getValue(Double.class);
                    sumq3 = last_year_quantity1;
                }
                else {

                }
                if (dataSnapshot.hasChild(last_year + "3" + "quantity_projection")) {
                    double last_year_quantity_projection1 = dataSnapshot.child(last_year + "3"  + "quantity_projection").getValue(Double.class);

                    sum3 = last_year_quantity_projection1;
                }
                else {

                }
                if (dataSnapshot.hasChild(last_year + "3" + "quantity") && dataSnapshot.hasChild(last_year + "3" + "quantity_projection")) {
                    double last_year_quantity1 = dataSnapshot.child(last_year + "3"  + "quantity").getValue(Double.class);
                    double last_year_quantity_projection1 = dataSnapshot.child(last_year + "3"  + "quantity_projection").getValue(Double.class);

                    double up = (last_year_quantity1-last_year_quantity_projection1);
                    double var1 = (up/last_year_quantity_projection1)*100;
                    String var1_st = decimalFormat.format(var1);

                } else {

                }

                if (dataSnapshot.hasChild(last_year + "4" + "quantity")) {
                    double last_year_quantity1 = dataSnapshot.child(last_year + "4"  + "quantity").getValue(Double.class);
                    sumq4 = last_year_quantity1;
                }
                else {

                }
                if (dataSnapshot.hasChild(last_year + "4" + "quantity_projection")) {
                    double last_year_quantity_projection1 = dataSnapshot.child(last_year + "4"  + "quantity_projection").getValue(Double.class);

                    sum4 = last_year_quantity_projection1;
                }
                else {

                }
                if (dataSnapshot.hasChild(last_year + "4" + "quantity") && dataSnapshot.hasChild(last_year + "4" + "quantity_projection")) {
                    double last_year_quantity1 = dataSnapshot.child(last_year + "4"  + "quantity").getValue(Double.class);
                    double last_year_quantity_projection1 = dataSnapshot.child(last_year + "4"  + "quantity_projection").getValue(Double.class);

                    double up = (last_year_quantity1-last_year_quantity_projection1);
                    double var1 = (up/last_year_quantity_projection1)*100;
                    String var1_st = decimalFormat.format(var1);

                } else {

                }

                if (dataSnapshot.hasChild(last_year + "5" + "quantity")) {
                    double last_year_quantity1 = dataSnapshot.child(last_year + "5"  + "quantity").getValue(Double.class);
                    sumq5 = last_year_quantity1;
                }
                else {

                }
                if (dataSnapshot.hasChild(last_year + "5" + "quantity_projection")) {
                    double last_year_quantity_projection1 = dataSnapshot.child(last_year + "5"  + "quantity_projection").getValue(Double.class);

                    sum5 = last_year_quantity_projection1;
                }
                else {

                }
                if (dataSnapshot.hasChild(last_year + "5" + "quantity") && dataSnapshot.hasChild(last_year + "5" + "quantity_projection")) {
                    double last_year_quantity1 = dataSnapshot.child(last_year + "5"  + "quantity").getValue(Double.class);
                    double last_year_quantity_projection1 = dataSnapshot.child(last_year + "5"  + "quantity_projection").getValue(Double.class);

                    double up = (last_year_quantity1-last_year_quantity_projection1);
                    double var1 = (up/last_year_quantity_projection1)*100;
                    String var1_st = decimalFormat.format(var1);

                } else {

                }

                if (dataSnapshot.hasChild(last_year + "6" + "quantity")) {
                    double last_year_quantity1 = dataSnapshot.child(last_year + "6"  + "quantity").getValue(Double.class);
                    sumq5 = last_year_quantity1;
                }
                else {

                }
                if (dataSnapshot.hasChild(last_year + "6" + "quantity_projection")) {
                    double last_year_quantity_projection1 = dataSnapshot.child(last_year + "6"  + "quantity_projection").getValue(Double.class);

                    sum6 = last_year_quantity_projection1;
                }
                else {

                }
                if (dataSnapshot.hasChild(last_year + "6" + "quantity") && dataSnapshot.hasChild(last_year + "6" + "quantity_projection")) {
                    double last_year_quantity1 = dataSnapshot.child(last_year + "6"  + "quantity").getValue(Double.class);
                    double last_year_quantity_projection1 = dataSnapshot.child(last_year + "6"  + "quantity_projection").getValue(Double.class);

                    double up = (last_year_quantity1-last_year_quantity_projection1);
                    double var1 = (up/last_year_quantity_projection1)*100;
                    String var1_st = decimalFormat.format(var1);

                } else {

                }

                if (dataSnapshot.hasChild(last_year + "7" + "quantity")) {
                    double last_year_quantity1 = dataSnapshot.child(last_year + "7"  + "quantity").getValue(Double.class);
                    sumq6 = last_year_quantity1;
                }
                else {

                }
                if (dataSnapshot.hasChild(last_year + "7" + "quantity_projection")) {
                    double last_year_quantity_projection1 = dataSnapshot.child(last_year + "7"  + "quantity_projection").getValue(Double.class);

                    sum7 = last_year_quantity_projection1;
                }
                else {

                }
                if (dataSnapshot.hasChild(last_year + "7" + "quantity") && dataSnapshot.hasChild(last_year + "7" + "quantity_projection")) {
                    double last_year_quantity1 = dataSnapshot.child(last_year + "7"  + "quantity").getValue(Double.class);
                    double last_year_quantity_projection1 = dataSnapshot.child(last_year + "7"  + "quantity_projection").getValue(Double.class);

                    double up = (last_year_quantity1-last_year_quantity_projection1);
                    double var1 = (up/last_year_quantity_projection1)*100;
                    String var1_st = decimalFormat.format(var1);

                } else {

                }

                if (dataSnapshot.hasChild(last_year + "8" + "quantity")) {
                    double last_year_quantity1 = dataSnapshot.child(last_year + "8"  + "quantity").getValue(Double.class);
                    sumq8 = last_year_quantity1;
                }
                else {

                }
                if (dataSnapshot.hasChild(last_year + "8" + "quantity_projection")) {
                    double last_year_quantity_projection1 = dataSnapshot.child(last_year + "8"  + "quantity_projection").getValue(Double.class);
                    sum8 = last_year_quantity_projection1;
                }
                else {

                }
                if (dataSnapshot.hasChild(last_year + "8" + "quantity") && dataSnapshot.hasChild(last_year + "8" + "quantity_projection")) {
                    double last_year_quantity1 = dataSnapshot.child(last_year + "8"  + "quantity").getValue(Double.class);
                    double last_year_quantity_projection1 = dataSnapshot.child(last_year + "8"  + "quantity_projection").getValue(Double.class);

                    double up = (last_year_quantity1-last_year_quantity_projection1);
                    double var1 = (up/last_year_quantity_projection1)*100;
                    String var1_st = decimalFormat.format(var1);

                } else {

                }

                if (dataSnapshot.hasChild(last_year + "9" + "quantity")) {
                    double last_year_quantity1 = dataSnapshot.child(last_year + "9"  + "quantity").getValue(Double.class);
                    sumq9 = last_year_quantity1;
                }
                else {

                }
                if (dataSnapshot.hasChild(last_year + "9" + "quantity_projection")) {
                    double last_year_quantity_projection1 = dataSnapshot.child(last_year + "9"  + "quantity_projection").getValue(Double.class);

                    sum9 = last_year_quantity_projection1;
                }
                else {

                }
                if (dataSnapshot.hasChild(last_year + "9" + "quantity") && dataSnapshot.hasChild(last_year + "9" + "quantity_projection")) {
                    double last_year_quantity1 = dataSnapshot.child(last_year + "9"  + "quantity").getValue(Double.class);
                    double last_year_quantity_projection1 = dataSnapshot.child(last_year + "9"  + "quantity_projection").getValue(Double.class);

                    double up = (last_year_quantity1-last_year_quantity_projection1);
                    double var1 = (up/last_year_quantity_projection1)*100;
                    String var1_st = decimalFormat.format(var1);

                } else {

                }

                if (dataSnapshot.hasChild(last_year + "10" + "quantity")) {
                    double last_year_quantity1 = dataSnapshot.child(last_year + "10"  + "quantity").getValue(Double.class);
                    sumq10 = last_year_quantity1;
                }
                else {

                }
                if (dataSnapshot.hasChild(last_year + "10" + "quantity_projection")) {
                    double last_year_quantity_projection1 = dataSnapshot.child(last_year + "10"  + "quantity_projection").getValue(Double.class);

                    sum10 = last_year_quantity_projection1;
                }
                else {

                }
                if (dataSnapshot.hasChild(last_year + "10" + "quantity") && dataSnapshot.hasChild(last_year + "10" + "quantity_projection")) {
                    double last_year_quantity1 = dataSnapshot.child(last_year + "10"  + "quantity").getValue(Double.class);
                    double last_year_quantity_projection1 = dataSnapshot.child(last_year + "10"  + "quantity_projection").getValue(Double.class);

                    double up = (last_year_quantity1-last_year_quantity_projection1);
                    double var1 = (up/last_year_quantity_projection1)*100;
                    String var1_st = decimalFormat.format(var1);

                } else {

                }

                if (dataSnapshot.hasChild(last_year + "11" + "quantity")) {
                    double last_year_quantity1 = dataSnapshot.child(last_year + "11"  + "quantity").getValue(Double.class);
                    sumq11 = last_year_quantity1;
                }
                else {

                }
                if (dataSnapshot.hasChild(last_year + "11" + "quantity_projection")) {
                    double last_year_quantity_projection1 = dataSnapshot.child(last_year + "11"  + "quantity_projection").getValue(Double.class);
                    sum11 = last_year_quantity_projection1;
                }
                else {

                }
                if (dataSnapshot.hasChild(last_year + "11" + "quantity") && dataSnapshot.hasChild(last_year + "11" + "quantity_projection")) {
                    double last_year_quantity1 = dataSnapshot.child(last_year + "11"  + "quantity").getValue(Double.class);
                    double last_year_quantity_projection1 = dataSnapshot.child(last_year + "11"  + "quantity_projection").getValue(Double.class);

                    double up = (last_year_quantity1-last_year_quantity_projection1);
                    double var1 = (up/last_year_quantity_projection1)*100;
                    String var1_st = decimalFormat.format(var1);

                } else {

                }

                if (dataSnapshot.hasChild(last_year + "12" + "quantity")) {
                    double last_year_quantity1 = dataSnapshot.child(last_year + "12"  + "quantity").getValue(Double.class);
                    sumq12 = last_year_quantity1;
                }
                else {

                }
                if (dataSnapshot.hasChild(last_year + "12" + "quantity_projection")) {
                    double last_year_quantity_projection1 = dataSnapshot.child(last_year + "12"  + "quantity_projection").getValue(Double.class);

                    sum12 = last_year_quantity_projection1;
                }
                else {

                }
                if (dataSnapshot.hasChild(last_year + "12" + "quantity") && dataSnapshot.hasChild(last_year + "12" + "quantity_projection")) {
                    double last_year_quantity1 = dataSnapshot.child(last_year + "12"  + "quantity").getValue(Double.class);
                    double last_year_quantity_projection1 = dataSnapshot.child(last_year + "12"  + "quantity_projection").getValue(Double.class);

                    double up = (last_year_quantity1-last_year_quantity_projection1);
                    double var1 = (up/last_year_quantity_projection1)*100;
                    String var1_st = decimalFormat.format(var1);

                } else {

                }

                double sum = (sum1+sum2+sum3+sum4+sum5+sum6+sum7+sum8+sum9+sum10+sum11+sum12);
                double sumq = (sumq1+sumq2+sumq3+sumq4+sumq5+sumq6+sumq7+sumq8+sumq9+sumq10+sumq11+sumq12);

                double var_units = ((sumq/sum)-1)*100;
                String var_units_st = decimalFormat.format(var_units);

                txtUnits.setText("CRECIMIENTO EN UNIDADES: "+var_units_st+"%");




                if (dataSnapshot.hasChild(last_year + "1" + "sales")) {
                    double last_year_quantity1 = dataSnapshot.child(last_year + "1"  + "sales").getValue(Double.class);
                    sumsr1 = last_year_quantity1;
                }
                else {

                }
                if (dataSnapshot.hasChild(last_year + "1" + "sales_projection")) {
                    double last_year_quantity_projection1 = dataSnapshot.child(last_year + "1"  + "sales_projection").getValue(Double.class);

                    sums1 = last_year_quantity_projection1;
                }
                else {

                }
                if (dataSnapshot.hasChild(last_year + "1" + "sales") && dataSnapshot.hasChild(last_year + "1" + "sales_projection")) {
                    double last_year_quantity1 = dataSnapshot.child(last_year + "1"  + "sales").getValue(Double.class);
                    double last_year_quantity_projection1 = dataSnapshot.child(last_year + "1"  + "sales_projection").getValue(Double.class);

                    double up = (last_year_quantity1-last_year_quantity_projection1);
                    double var1 = (up/last_year_quantity_projection1)*100;
                    String var1_st = decimalFormat.format(var1);

                } else {

                }

                if (dataSnapshot.hasChild(last_year + "2" + "sales")) {
                    double last_year_quantity1 = dataSnapshot.child(last_year + "2"  + "sales").getValue(Double.class);
                    sumsr2 = last_year_quantity1;
                }
                else {

                }
                if (dataSnapshot.hasChild(last_year + "2" + "sales_projection")) {
                    double last_year_quantity_projection1 = dataSnapshot.child(last_year + "2"  + "sales_projection").getValue(Double.class);
                    sums2 = last_year_quantity_projection1;
                }
                else {
                }
                if (dataSnapshot.hasChild(last_year + "2" + "sales") && dataSnapshot.hasChild(last_year + "2" + "sales_projection")) {
                    double last_year_quantity1 = dataSnapshot.child(last_year + "2"  + "sales").getValue(Double.class);
                    double last_year_quantity_projection1 = dataSnapshot.child(last_year + "2"  + "sales_projection").getValue(Double.class);

                    double up = (last_year_quantity1-last_year_quantity_projection1);
                    double var1 = (up/last_year_quantity_projection1)*100;
                    String var1_st = decimalFormat.format(var1);

                } else {

                }

                if (dataSnapshot.hasChild(last_year + "3" + "sales")) {
                    double last_year_quantity1 = dataSnapshot.child(last_year + "3"  + "sales").getValue(Double.class);
                    sumsr3 = last_year_quantity1;
                }
                else {

                }
                if (dataSnapshot.hasChild(last_year + "3" + "sales_projection")) {
                    double last_year_quantity_projection1 = dataSnapshot.child(last_year + "3"  + "sales_projection").getValue(Double.class);

                    sums3 = last_year_quantity_projection1;
                }
                else {

                }
                if (dataSnapshot.hasChild(last_year + "3" + "sales") && dataSnapshot.hasChild(last_year + "3" + "sales_projection")) {
                    double last_year_quantity1 = dataSnapshot.child(last_year + "3"  + "sales").getValue(Double.class);
                    double last_year_quantity_projection1 = dataSnapshot.child(last_year + "3"  + "sales_projection").getValue(Double.class);

                    double up = (last_year_quantity1-last_year_quantity_projection1);
                    double var1 = (up/last_year_quantity_projection1)*100;
                    String var1_st = decimalFormat.format(var1);

                } else {

                }

                if (dataSnapshot.hasChild(last_year + "4" + "sales")) {
                    double last_year_quantity1 = dataSnapshot.child(last_year + "4"  + "sales").getValue(Double.class);
                    sumsr4 = last_year_quantity1;
                }
                else {

                }
                if (dataSnapshot.hasChild(last_year + "4" + "sales_projection")) {
                    double last_year_quantity_projection1 = dataSnapshot.child(last_year + "4"  + "sales_projection").getValue(Double.class);
                    sums4 = last_year_quantity_projection1;
                }
                else {

                }
                if (dataSnapshot.hasChild(last_year + "4" + "sales") && dataSnapshot.hasChild(last_year + "4" + "sales_projection")) {
                    double last_year_quantity1 = dataSnapshot.child(last_year + "4"  + "sales").getValue(Double.class);
                    double last_year_quantity_projection1 = dataSnapshot.child(last_year + "4"  + "sales_projection").getValue(Double.class);

                    double up = (last_year_quantity1-last_year_quantity_projection1);
                    double var1 = (up/last_year_quantity_projection1)*100;
                    String var1_st = decimalFormat.format(var1);

                } else {

                }

                if (dataSnapshot.hasChild(last_year + "5" + "quantity")) {
                    double last_year_quantity1 = dataSnapshot.child(last_year + "5"  + "sales").getValue(Double.class);
                    sumsr5 = last_year_quantity1;
                }
                else {

                }
                if (dataSnapshot.hasChild(last_year + "5" + "sales_projection")) {
                    double last_year_quantity_projection1 = dataSnapshot.child(last_year + "5"  + "sales_projection").getValue(Double.class);

                    sums5 = last_year_quantity_projection1;
                }
                else {

                }
                if (dataSnapshot.hasChild(last_year + "5" + "sales") && dataSnapshot.hasChild(last_year + "5" + "sales_projection")) {
                    double last_year_quantity1 = dataSnapshot.child(last_year + "5"  + "sales").getValue(Double.class);
                    double last_year_quantity_projection1 = dataSnapshot.child(last_year + "5"  + "sales_projection").getValue(Double.class);

                    double up = (last_year_quantity1-last_year_quantity_projection1);
                    double var1 = (up/last_year_quantity_projection1)*100;
                    String var1_st = decimalFormat.format(var1);

                } else {

                }

                if (dataSnapshot.hasChild(last_year + "6" + "sales")) {
                    double last_year_quantity1 = dataSnapshot.child(last_year + "6"  + "sales").getValue(Double.class);
                    sumsr6 = last_year_quantity1;
                }
                else {

                }
                if (dataSnapshot.hasChild(last_year + "6" + "sales_projection")) {
                    double last_year_quantity_projection1 = dataSnapshot.child(last_year + "6"  + "sales_projection").getValue(Double.class);

                    sums6 = last_year_quantity_projection1;
                }
                else {

                }
                if (dataSnapshot.hasChild(last_year + "6" + "sales") && dataSnapshot.hasChild(last_year + "6" + "sales_projection")) {
                    double last_year_quantity1 = dataSnapshot.child(last_year + "6"  + "sales").getValue(Double.class);
                    double last_year_quantity_projection1 = dataSnapshot.child(last_year + "6"  + "sales_projection").getValue(Double.class);

                    double up = (last_year_quantity1-last_year_quantity_projection1);
                    double var1 = (up/last_year_quantity_projection1)*100;
                    String var1_st = decimalFormat.format(var1);

                } else {

                }

                if (dataSnapshot.hasChild(last_year + "7" + "sales")) {
                    double last_year_quantity1 = dataSnapshot.child(last_year + "7"  + "sales").getValue(Double.class);
                    sumsr7 = last_year_quantity1;
                }
                else {

                }
                if (dataSnapshot.hasChild(last_year + "7" + "sales_projection")) {
                    double last_year_quantity_projection1 = dataSnapshot.child(last_year + "7"  + "sales_projection").getValue(Double.class);
                    sums7 = last_year_quantity_projection1;
                }
                else {

                }
                if (dataSnapshot.hasChild(last_year + "7" + "sales") && dataSnapshot.hasChild(last_year + "7" + "sales_projection")) {
                    double last_year_quantity1 = dataSnapshot.child(last_year + "7"  + "sales").getValue(Double.class);
                    double last_year_quantity_projection1 = dataSnapshot.child(last_year + "7"  + "sales_projection").getValue(Double.class);

                    double up = (last_year_quantity1-last_year_quantity_projection1);
                    double var1 = (up/last_year_quantity_projection1)*100;
                    String var1_st = decimalFormat.format(var1);

                } else {

                }

                if (dataSnapshot.hasChild(last_year + "8" + "sales")) {
                    double last_year_quantity1 = dataSnapshot.child(last_year + "8"  + "sales").getValue(Double.class);
                    sumsr8 = last_year_quantity1;
                }
                else {

                }
                if (dataSnapshot.hasChild(last_year + "8" + "sales_projection")) {
                    double last_year_quantity_projection1 = dataSnapshot.child(last_year + "8"  + "sales_projection").getValue(Double.class);
                    sums8 = last_year_quantity_projection1;
                }
                else {
                }
                if (dataSnapshot.hasChild(last_year + "8" + "sales") && dataSnapshot.hasChild(last_year + "8" + "sales_projection")) {
                    double last_year_quantity1 = dataSnapshot.child(last_year + "8"  + "sales").getValue(Double.class);
                    double last_year_quantity_projection1 = dataSnapshot.child(last_year + "8"  + "sales_projection").getValue(Double.class);

                    double up = (last_year_quantity1-last_year_quantity_projection1);
                    double var1 = (up/last_year_quantity_projection1)*100;
                    String var1_st = decimalFormat.format(var1);

                } else {

                }

                if (dataSnapshot.hasChild(last_year + "9" + "sales")) {
                    double last_year_quantity1 = dataSnapshot.child(last_year + "9"  + "sales").getValue(Double.class);
                    sumsr9 = last_year_quantity1;
                }
                else {

                }
                if (dataSnapshot.hasChild(last_year + "9" + "sales_projection")) {
                    double last_year_quantity_projection1 = dataSnapshot.child(last_year + "9"  + "sales_projection").getValue(Double.class);

                    sums9 = last_year_quantity_projection1;
                }
                else {

                }
                if (dataSnapshot.hasChild(last_year + "9" + "sales") && dataSnapshot.hasChild(last_year + "9" + "sales_projection")) {
                    double last_year_quantity1 = dataSnapshot.child(last_year + "9"  + "sales").getValue(Double.class);
                    double last_year_quantity_projection1 = dataSnapshot.child(last_year + "9"  + "sales_projection").getValue(Double.class);

                    double up = (last_year_quantity1-last_year_quantity_projection1);
                    double var1 = (up/last_year_quantity_projection1)*100;
                    String var1_st = decimalFormat.format(var1);

                } else {

                }

                if (dataSnapshot.hasChild(last_year + "10" + "sales")) {
                    double last_year_quantity1 = dataSnapshot.child(last_year + "10"  + "sales").getValue(Double.class);
                    sumsr10 = last_year_quantity1;
                }
                else {

                }
                if (dataSnapshot.hasChild(last_year + "10" + "sales_projection")) {
                    double last_year_quantity_projection1 = dataSnapshot.child(last_year + "10"  + "sales_projection").getValue(Double.class);

                    sums10 = last_year_quantity_projection1;
                }
                else {

                }
                if (dataSnapshot.hasChild(last_year + "10" + "sales") && dataSnapshot.hasChild(last_year + "10" + "sales_projection")) {
                    double last_year_quantity1 = dataSnapshot.child(last_year + "10"  + "sales").getValue(Double.class);
                    double last_year_quantity_projection1 = dataSnapshot.child(last_year + "10"  + "sales_projection").getValue(Double.class);

                    double up = (last_year_quantity1-last_year_quantity_projection1);
                    double var1 = (up/last_year_quantity_projection1)*100;
                    String var1_st = decimalFormat.format(var1);

                } else {

                }

                if (dataSnapshot.hasChild(last_year + "11" + "sales")) {
                    double last_year_quantity1 = dataSnapshot.child(last_year + "11"  + "sales").getValue(Double.class);
                    sumsr11 = last_year_quantity1;
                }
                else {

                }
                if (dataSnapshot.hasChild(last_year + "11" + "sales_projection")) {
                    double last_year_quantity_projection1 = dataSnapshot.child(last_year + "11"  + "sales_projection").getValue(Double.class);

                    sums11 = last_year_quantity_projection1;
                }
                else {

                }
                if (dataSnapshot.hasChild(last_year + "11" + "sales") && dataSnapshot.hasChild(last_year + "11" + "sales_projection")) {
                    double last_year_quantity1 = dataSnapshot.child(last_year + "11"  + "sales").getValue(Double.class);
                    double last_year_quantity_projection1 = dataSnapshot.child(last_year + "11"  + "sales_projection").getValue(Double.class);

                    double up = (last_year_quantity1-last_year_quantity_projection1);
                    double var1 = (up/last_year_quantity_projection1)*100;
                    String var1_st = decimalFormat.format(var1);

                } else {

                }

                if (dataSnapshot.hasChild(last_year + "12" + "sales")) {
                    double last_year_quantity1 = dataSnapshot.child(last_year + "12"  + "sales").getValue(Double.class);
                    sumsr12 = last_year_quantity1;
                }
                else {

                }
                if (dataSnapshot.hasChild(last_year + "12" + "sales_projection")) {
                    double last_year_quantity_projection1 = dataSnapshot.child(last_year + "12"  + "sales_projection").getValue(Double.class);

                    sums12 = last_year_quantity_projection1;
                }
                else {

                }
                if (dataSnapshot.hasChild(last_year + "12" + "sales") && dataSnapshot.hasChild(last_year + "12" + "sales_projection")) {
                    double last_year_quantity1 = dataSnapshot.child(last_year + "12"  + "sales").getValue(Double.class);
                    double last_year_quantity_projection1 = dataSnapshot.child(last_year + "12"  + "sales_projection").getValue(Double.class);

                    double up = (last_year_quantity1-last_year_quantity_projection1);
                    double var1 = (up/last_year_quantity_projection1)*100;
                    String var1_st = decimalFormat.format(var1);

                } else {

                }

                double sums = (sums1+sums2+sums3+sums4+sums5+sums6+sums7+sums8+sums9+sums10+sums11+sums12);
                double sumrs = (sumsr1+sumsr2+sumsr3+sumsr4+sumsr5+sumsr6+sumsr7+sumsr8+sumsr9+sumsr10+sumsr11+sumsr12);

                double var_money = ((sumrs/sums)-1)*100;
                String var_money_st = decimalFormat.format(var_money);

                txtMoney.setText("CRECIMIENTO EN DINERO: "+var_money_st+"%");

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return view;
    }
}
