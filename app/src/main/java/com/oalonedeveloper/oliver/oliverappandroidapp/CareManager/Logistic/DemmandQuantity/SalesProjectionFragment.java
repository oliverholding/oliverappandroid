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


public class SalesProjectionFragment extends Fragment {

    TextView txtLastPeriod,txtReal1,txtReal2,txtReal3,txtReal4,txtReal5,txtReal6,txtReal7,txtReal8,txtReal9,txtReal10,txtReal11,txtReal12,txtProjection1,txtProjection2,txtProjection3,txtProjection4,txtProjection5,txtProjection6,txtProjection7,
            txtProjection8,txtProjection9,txtProjection10,txtProjection11,txtProjection12,txtVar1,txtVar2,txtVar3,txtVar4,txtVar5,txtVar6,txtVar7,txtVar8,txtVar9,txtVar10,txtVar11,txtVar12;
    String post_key,product_id;
    DatabaseReference companyRef;
    int day,month,year,last_year;
    DecimalFormat decimalFormat;
    double sum1,sum2,sum3,sum4,sum5,sum6,sum7,sum8,sum9,sum10,sum11,sum12;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sales_projection, container, false);

        post_key = getActivity().getIntent().getExtras().getString("post_key");
        product_id = getActivity().getIntent().getExtras().getString("product_id");
        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");

        decimalFormat = new DecimalFormat("0.00");

        txtReal1 = view.findViewById(R.id.txtReal1);
        txtReal2 = view.findViewById(R.id.txtReal2);
        txtReal3 = view.findViewById(R.id.txtReal3);
        txtReal4 = view.findViewById(R.id.txtReal4);
        txtReal5 = view.findViewById(R.id.txtReal5);
        txtReal6 = view.findViewById(R.id.txtReal6);
        txtReal7 = view.findViewById(R.id.txtReal7);
        txtReal8 = view.findViewById(R.id.txtReal8);
        txtReal9 = view.findViewById(R.id.txtReal9);
        txtReal10 = view.findViewById(R.id.txtReal10);
        txtReal11 = view.findViewById(R.id.txtReal11);
        txtReal12 = view.findViewById(R.id.txtReal12);
        txtLastPeriod = view.findViewById(R.id.txtLastPeriod);

        txtProjection1 = view.findViewById(R.id.txtProjection1);
        txtProjection2 = view.findViewById(R.id.txtProjection2);
        txtProjection3 = view.findViewById(R.id.txtProjection3);
        txtProjection4 = view.findViewById(R.id.txtProjection4);
        txtProjection5 = view.findViewById(R.id.txtProjection5);
        txtProjection6 = view.findViewById(R.id.txtProjection6);
        txtProjection7 = view.findViewById(R.id.txtProjection7);
        txtProjection8 = view.findViewById(R.id.txtProjection8);
        txtProjection9 = view.findViewById(R.id.txtProjection9);
        txtProjection10 = view.findViewById(R.id.txtProjection10);
        txtProjection11 = view.findViewById(R.id.txtProjection11);
        txtProjection12 = view.findViewById(R.id.txtProjection12);

        txtVar1 = view.findViewById(R.id.txtVar1);
        txtVar2 = view.findViewById(R.id.txtVar2);
        txtVar3 = view.findViewById(R.id.txtVar3);
        txtVar4 = view.findViewById(R.id.txtVar4);
        txtVar5 = view.findViewById(R.id.txtVar5);
        txtVar6 = view.findViewById(R.id.txtVar6);
        txtVar7 = view.findViewById(R.id.txtVar7);
        txtVar8 = view.findViewById(R.id.txtVar8);
        txtVar9 = view.findViewById(R.id.txtVar9);
        txtVar10 = view.findViewById(R.id.txtVar10);
        txtVar11 = view.findViewById(R.id.txtVar11);
        txtVar12 = view.findViewById(R.id.txtVar12);

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

        txtLastPeriod.setText("PERÍODO "+last_year);

        companyRef.child(post_key).child("My Products").child(product_id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild(last_year + "1" + "sales")) {
                    double last_year_quantity1 = dataSnapshot.child(last_year + "1"  + "sales").getValue(Double.class);
                    txtReal1.setText(last_year_quantity1+"");
                }
                else {
                    txtReal1.setText("-");
                }
                if (dataSnapshot.hasChild(last_year + "1" + "sales_projection")) {
                    double last_year_quantity_projection1 = dataSnapshot.child(last_year + "1"  + "sales_projection").getValue(Double.class);
                    txtProjection1.setText(last_year_quantity_projection1+"");
                    sum1 = last_year_quantity_projection1;
                }
                else {
                    txtProjection1.setText("-");
                }
                if (dataSnapshot.hasChild(last_year + "1" + "sales") && dataSnapshot.hasChild(last_year + "1" + "sales_projection")) {
                    double last_year_quantity1 = dataSnapshot.child(last_year + "1"  + "sales").getValue(Double.class);
                    double last_year_quantity_projection1 = dataSnapshot.child(last_year + "1"  + "sales_projection").getValue(Double.class);

                    double up = (last_year_quantity1-last_year_quantity_projection1);
                    double var1 = (up/last_year_quantity_projection1)*100;
                    String var1_st = decimalFormat.format(var1);
                    txtVar1.setText(var1_st+"%");
                } else {
                    txtVar1.setText("-");
                }

                if (dataSnapshot.hasChild(last_year + "2" + "sales")) {
                    double last_year_quantity1 = dataSnapshot.child(last_year + "2"  + "sales").getValue(Double.class);
                    txtReal2.setText(last_year_quantity1+"");
                }
                else {
                    txtReal2.setText("-");
                }
                if (dataSnapshot.hasChild(last_year + "2" + "sales_projection")) {
                    double last_year_quantity_projection1 = dataSnapshot.child(last_year + "2"  + "sales_projection").getValue(Double.class);
                    txtProjection2.setText(last_year_quantity_projection1+"");
                    sum2 = last_year_quantity_projection1;
                }
                else {
                    txtProjection2.setText("-");
                }
                if (dataSnapshot.hasChild(last_year + "2" + "sales") && dataSnapshot.hasChild(last_year + "2" + "sales_projection")) {
                    double last_year_quantity1 = dataSnapshot.child(last_year + "2"  + "sales").getValue(Double.class);
                    double last_year_quantity_projection1 = dataSnapshot.child(last_year + "2"  + "sales_projection").getValue(Double.class);

                    double up = (last_year_quantity1-last_year_quantity_projection1);
                    double var1 = (up/last_year_quantity_projection1)*100;
                    String var1_st = decimalFormat.format(var1);
                    txtVar2.setText(var1_st+"%");
                } else {
                    txtVar2.setText("-");
                }

                if (dataSnapshot.hasChild(last_year + "3" + "sales")) {
                    double last_year_quantity1 = dataSnapshot.child(last_year + "3"  + "sales").getValue(Double.class);
                    txtReal3.setText(last_year_quantity1+"");
                }
                else {
                    txtReal3.setText("-");
                }
                if (dataSnapshot.hasChild(last_year + "3" + "sales_projection")) {
                    double last_year_quantity_projection1 = dataSnapshot.child(last_year + "3"  + "sales_projection").getValue(Double.class);
                    txtProjection3.setText(last_year_quantity_projection1+"");
                    sum3 = last_year_quantity_projection1;
                }
                else {
                    txtProjection3.setText("-");
                }
                if (dataSnapshot.hasChild(last_year + "3" + "sales") && dataSnapshot.hasChild(last_year + "3" + "sales_projection")) {
                    double last_year_quantity1 = dataSnapshot.child(last_year + "3"  + "sales").getValue(Double.class);
                    double last_year_quantity_projection1 = dataSnapshot.child(last_year + "3"  + "sales_projection").getValue(Double.class);

                    double up = (last_year_quantity1-last_year_quantity_projection1);
                    double var1 = (up/last_year_quantity_projection1)*100;
                    String var1_st = decimalFormat.format(var1);
                    txtVar3.setText(var1_st+"%");
                } else {
                    txtVar3.setText("-");
                }

                if (dataSnapshot.hasChild(last_year + "4" + "sales")) {
                    double last_year_quantity1 = dataSnapshot.child(last_year + "4"  + "sales").getValue(Double.class);
                    txtReal4.setText(last_year_quantity1+"");
                }
                else {
                    txtReal4.setText("-");
                }
                if (dataSnapshot.hasChild(last_year + "4" + "sales_projection")) {
                    double last_year_quantity_projection1 = dataSnapshot.child(last_year + "4"  + "sales_projection").getValue(Double.class);
                    txtProjection4.setText(last_year_quantity_projection1+"");
                    sum4 = last_year_quantity_projection1;
                }
                else {
                    txtProjection4.setText("-");
                }
                if (dataSnapshot.hasChild(last_year + "4" + "sales") && dataSnapshot.hasChild(last_year + "4" + "sales_projection")) {
                    double last_year_quantity1 = dataSnapshot.child(last_year + "4"  + "sales").getValue(Double.class);
                    double last_year_quantity_projection1 = dataSnapshot.child(last_year + "4"  + "sales_projection").getValue(Double.class);

                    double up = (last_year_quantity1-last_year_quantity_projection1);
                    double var1 = (up/last_year_quantity_projection1)*100;
                    String var1_st = decimalFormat.format(var1);
                    txtVar4.setText(var1_st+"%");
                } else {
                    txtVar4.setText("-");
                }

                if (dataSnapshot.hasChild(last_year + "5" + "quantity")) {
                    double last_year_quantity1 = dataSnapshot.child(last_year + "5"  + "sales").getValue(Double.class);
                    txtReal5.setText(last_year_quantity1+"");
                }
                else {
                    txtReal5.setText("-");
                }
                if (dataSnapshot.hasChild(last_year + "5" + "sales_projection")) {
                    double last_year_quantity_projection1 = dataSnapshot.child(last_year + "5"  + "sales_projection").getValue(Double.class);
                    txtProjection5.setText(last_year_quantity_projection1+"");
                    sum5 = last_year_quantity_projection1;
                }
                else {
                    txtProjection5.setText("-");
                }
                if (dataSnapshot.hasChild(last_year + "5" + "sales") && dataSnapshot.hasChild(last_year + "5" + "sales_projection")) {
                    double last_year_quantity1 = dataSnapshot.child(last_year + "5"  + "sales").getValue(Double.class);
                    double last_year_quantity_projection1 = dataSnapshot.child(last_year + "5"  + "sales_projection").getValue(Double.class);

                    double up = (last_year_quantity1-last_year_quantity_projection1);
                    double var1 = (up/last_year_quantity_projection1)*100;
                    String var1_st = decimalFormat.format(var1);
                    txtVar5.setText(var1_st+"%");
                } else {
                    txtVar5.setText("-");
                }

                if (dataSnapshot.hasChild(last_year + "6" + "sales")) {
                    double last_year_quantity1 = dataSnapshot.child(last_year + "6"  + "sales").getValue(Double.class);
                    txtReal6.setText(last_year_quantity1+"");
                }
                else {
                    txtReal6.setText("-");
                }
                if (dataSnapshot.hasChild(last_year + "6" + "sales_projection")) {
                    double last_year_quantity_projection1 = dataSnapshot.child(last_year + "6"  + "sales_projection").getValue(Double.class);
                    txtProjection6.setText(last_year_quantity_projection1+"");
                    sum6 = last_year_quantity_projection1;
                }
                else {
                    txtProjection6.setText("-");
                }
                if (dataSnapshot.hasChild(last_year + "6" + "sales") && dataSnapshot.hasChild(last_year + "6" + "sales_projection")) {
                    double last_year_quantity1 = dataSnapshot.child(last_year + "6"  + "sales").getValue(Double.class);
                    double last_year_quantity_projection1 = dataSnapshot.child(last_year + "6"  + "sales_projection").getValue(Double.class);

                    double up = (last_year_quantity1-last_year_quantity_projection1);
                    double var1 = (up/last_year_quantity_projection1)*100;
                    String var1_st = decimalFormat.format(var1);
                    txtVar6.setText(var1_st+"%");
                } else {
                    txtVar6.setText("-");
                }

                if (dataSnapshot.hasChild(last_year + "7" + "sales")) {
                    double last_year_quantity1 = dataSnapshot.child(last_year + "7"  + "sales").getValue(Double.class);
                    txtReal7.setText(last_year_quantity1+"");
                }
                else {
                    txtReal7.setText("-");
                }
                if (dataSnapshot.hasChild(last_year + "7" + "sales_projection")) {
                    double last_year_quantity_projection1 = dataSnapshot.child(last_year + "7"  + "sales_projection").getValue(Double.class);
                    txtProjection7.setText(last_year_quantity_projection1+"");
                    sum7 = last_year_quantity_projection1;
                }
                else {
                    txtProjection7.setText("-");
                }
                if (dataSnapshot.hasChild(last_year + "7" + "sales") && dataSnapshot.hasChild(last_year + "7" + "sales_projection")) {
                    double last_year_quantity1 = dataSnapshot.child(last_year + "7"  + "sales").getValue(Double.class);
                    double last_year_quantity_projection1 = dataSnapshot.child(last_year + "7"  + "sales_projection").getValue(Double.class);

                    double up = (last_year_quantity1-last_year_quantity_projection1);
                    double var1 = (up/last_year_quantity_projection1)*100;
                    String var1_st = decimalFormat.format(var1);
                    txtVar7.setText(var1_st+"%");
                } else {
                    txtVar7.setText("-");
                }

                if (dataSnapshot.hasChild(last_year + "8" + "sales")) {
                    double last_year_quantity1 = dataSnapshot.child(last_year + "8"  + "sales").getValue(Double.class);
                    txtReal8.setText(last_year_quantity1+"");
                }
                else {
                    txtReal8.setText("-");
                }
                if (dataSnapshot.hasChild(last_year + "8" + "sales_projection")) {
                    double last_year_quantity_projection1 = dataSnapshot.child(last_year + "8"  + "sales_projection").getValue(Double.class);
                    txtProjection8.setText(last_year_quantity_projection1+"");
                    sum8 = last_year_quantity_projection1;
                }
                else {
                    txtProjection8.setText("-");
                }
                if (dataSnapshot.hasChild(last_year + "8" + "sales") && dataSnapshot.hasChild(last_year + "8" + "sales_projection")) {
                    double last_year_quantity1 = dataSnapshot.child(last_year + "8"  + "sales").getValue(Double.class);
                    double last_year_quantity_projection1 = dataSnapshot.child(last_year + "8"  + "sales_projection").getValue(Double.class);

                    double up = (last_year_quantity1-last_year_quantity_projection1);
                    double var1 = (up/last_year_quantity_projection1)*100;
                    String var1_st = decimalFormat.format(var1);
                    txtVar8.setText(var1_st+"%");
                } else {
                    txtVar8.setText("-");
                }

                if (dataSnapshot.hasChild(last_year + "9" + "sales")) {
                    double last_year_quantity1 = dataSnapshot.child(last_year + "9"  + "sales").getValue(Double.class);
                    txtReal9.setText(last_year_quantity1+"");
                }
                else {
                    txtReal9.setText("-");
                }
                if (dataSnapshot.hasChild(last_year + "9" + "sales_projection")) {
                    double last_year_quantity_projection1 = dataSnapshot.child(last_year + "9"  + "sales_projection").getValue(Double.class);
                    txtProjection9.setText(last_year_quantity_projection1+"");
                    sum9 = last_year_quantity_projection1;
                }
                else {
                    txtProjection9.setText("-");
                }
                if (dataSnapshot.hasChild(last_year + "9" + "sales") && dataSnapshot.hasChild(last_year + "9" + "sales_projection")) {
                    double last_year_quantity1 = dataSnapshot.child(last_year + "9"  + "sales").getValue(Double.class);
                    double last_year_quantity_projection1 = dataSnapshot.child(last_year + "9"  + "sales_projection").getValue(Double.class);

                    double up = (last_year_quantity1-last_year_quantity_projection1);
                    double var1 = (up/last_year_quantity_projection1)*100;
                    String var1_st = decimalFormat.format(var1);
                    txtVar9.setText(var1_st+"%");
                } else {
                    txtVar9.setText("-");
                }

                if (dataSnapshot.hasChild(last_year + "10" + "sales")) {
                    double last_year_quantity1 = dataSnapshot.child(last_year + "10"  + "sales").getValue(Double.class);
                    txtReal10.setText(last_year_quantity1+"");
                }
                else {
                    txtReal10.setText("-");
                }
                if (dataSnapshot.hasChild(last_year + "10" + "sales_projection")) {
                    double last_year_quantity_projection1 = dataSnapshot.child(last_year + "10"  + "sales_projection").getValue(Double.class);
                    txtProjection10.setText(last_year_quantity_projection1+"");
                    sum10 = last_year_quantity_projection1;
                }
                else {
                    txtProjection10.setText("-");
                }
                if (dataSnapshot.hasChild(last_year + "10" + "sales") && dataSnapshot.hasChild(last_year + "10" + "sales_projection")) {
                    double last_year_quantity1 = dataSnapshot.child(last_year + "10"  + "sales").getValue(Double.class);
                    double last_year_quantity_projection1 = dataSnapshot.child(last_year + "10"  + "sales_projection").getValue(Double.class);

                    double up = (last_year_quantity1-last_year_quantity_projection1);
                    double var1 = (up/last_year_quantity_projection1)*100;
                    String var1_st = decimalFormat.format(var1);
                    txtVar10.setText(var1_st+"%");
                } else {
                    txtVar10.setText("-");
                }

                if (dataSnapshot.hasChild(last_year + "11" + "sales")) {
                    double last_year_quantity1 = dataSnapshot.child(last_year + "11"  + "sales").getValue(Double.class);
                    txtReal11.setText(last_year_quantity1+"");
                }
                else {
                    txtReal11.setText("-");
                }
                if (dataSnapshot.hasChild(last_year + "11" + "sales_projection")) {
                    double last_year_quantity_projection1 = dataSnapshot.child(last_year + "11"  + "sales_projection").getValue(Double.class);
                    txtProjection11.setText(last_year_quantity_projection1+"");
                    sum11 = last_year_quantity_projection1;
                }
                else {
                    txtProjection11.setText("-");
                }
                if (dataSnapshot.hasChild(last_year + "11" + "sales") && dataSnapshot.hasChild(last_year + "11" + "sales_projection")) {
                    double last_year_quantity1 = dataSnapshot.child(last_year + "11"  + "sales").getValue(Double.class);
                    double last_year_quantity_projection1 = dataSnapshot.child(last_year + "11"  + "sales_projection").getValue(Double.class);

                    double up = (last_year_quantity1-last_year_quantity_projection1);
                    double var1 = (up/last_year_quantity_projection1)*100;
                    String var1_st = decimalFormat.format(var1);
                    txtVar11.setText(var1_st+"%");
                } else {
                    txtVar11.setText("-");
                }

                if (dataSnapshot.hasChild(last_year + "12" + "sales")) {
                    double last_year_quantity1 = dataSnapshot.child(last_year + "12"  + "sales").getValue(Double.class);
                    txtReal12.setText(last_year_quantity1+"");
                }
                else {
                    txtReal12.setText("-");
                }
                if (dataSnapshot.hasChild(last_year + "12" + "sales_projection")) {
                    double last_year_quantity_projection1 = dataSnapshot.child(last_year + "12"  + "sales_projection").getValue(Double.class);
                    txtProjection12.setText(last_year_quantity_projection1+"");
                    sum12 = last_year_quantity_projection1;
                }
                else {
                    txtProjection11.setText("-");
                }
                if (dataSnapshot.hasChild(last_year + "12" + "sales") && dataSnapshot.hasChild(last_year + "12" + "sales_projection")) {
                    double last_year_quantity1 = dataSnapshot.child(last_year + "12"  + "sales").getValue(Double.class);
                    double last_year_quantity_projection1 = dataSnapshot.child(last_year + "12"  + "sales_projection").getValue(Double.class);

                    double up = (last_year_quantity1-last_year_quantity_projection1);
                    double var1 = (up/last_year_quantity_projection1)*100;
                    String var1_st = decimalFormat.format(var1);
                    txtVar12.setText(var1_st+"%");
                } else {
                    txtVar12.setText("-");
                }

                double sum = (sum1+sum2+sum3+sum4+sum5+sum6+sum7+sum8+sum9+sum10+sum11+sum12)/12;

                if (sum1 < sum) {
                    txtProjection1.setBackgroundColor(Color.RED);
                }
                if (sum2 < sum) {
                    txtProjection2.setBackgroundColor(Color.RED);
                }
                if (sum3 < sum) {
                    txtProjection3.setBackgroundColor(Color.RED);
                }
                if (sum4 < sum) {
                    txtProjection4.setBackgroundColor(Color.RED);
                }
                if (sum5 < sum) {
                    txtProjection5.setBackgroundColor(Color.RED);
                }
                if (sum6 < sum) {
                    txtProjection6.setBackgroundColor(Color.RED);
                }
                if (sum7 < sum) {
                    txtProjection7.setBackgroundColor(Color.RED);
                }
                if (sum8 < sum) {
                    txtProjection8.setBackgroundColor(Color.RED);
                }
                if (sum9 < sum) {
                    txtProjection9.setBackgroundColor(Color.RED);
                }
                if (sum10 < sum) {
                    txtProjection10.setBackgroundColor(Color.RED);
                }
                if (sum11 < sum) {
                    txtProjection11.setBackgroundColor(Color.RED);
                }
                if (sum12 < sum) {
                    txtProjection12.setBackgroundColor(Color.RED);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return view;
    }
}
