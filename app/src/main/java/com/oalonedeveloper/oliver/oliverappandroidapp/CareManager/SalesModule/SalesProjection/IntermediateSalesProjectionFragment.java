package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.SalesModule.SalesProjection;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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

import in.galaxyofandroid.spinerdialog.OnSpinerItemClick;
import in.galaxyofandroid.spinerdialog.SpinnerDialog;


public class IntermediateSalesProjectionFragment extends Fragment {

    TextView txtCurrentYear,txtCurrentYear2,txtCurrentYear3;
    EditText txtMonth1,txtMonth2,txtMonth3,txtMonth4,txtMonth5,txtMonth6,txtMonth7,txtMonth8,txtMonth9,txtMonth10,txtMonth11,txtMonth12,txtMonth1t2,txtMonth2t2,txtMonth3t2,txtMonth4t2,txtMonth5t2,txtMonth6t2,txtMonth7t2,
            txtMonth8t2,txtMonth9t2,txtMonth10t2,txtMonth11t2,txtMonth12t2,txtMonth1t3,txtMonth2t3,txtMonth3t3,txtMonth4t3,txtMonth5t3,txtMonth6t3,txtMonth7t3, txtMonth8t3,txtMonth9t3,txtMonth10t3,txtMonth11t3,txtMonth12t3;
    int day,month,year,year2;
    DatabaseReference companyRef;
    String post_key,sales_1,sales_2,sales_3,sales_4,sales_5,sales_6,sales_7,sales_8,sales_9,sales_10,sales_11,sales_12,month_value,foreseeable_value,uncertain_value;
    double sum1,sum2,sum3,sum4,sum5,sum6,sum7,sum8,sum9,sum10,sum11,sum12,foreseeable_value_db,uncertain_value_db;
    float f1,f2,f3,f4,f5,f6,f7,f8,f9,f10,f11,f12;
    DecimalFormat decimalFormat;
    LineChart mLineChart;
    Button btnSalesProjection,btnAction1,btnAction2;

    ArrayList<String> foreseeable_arr =new ArrayList<>();
    SpinnerDialog foreseeableDialog;

    ArrayList<String> uncertain_arr =new ArrayList<>();
    SpinnerDialog uncertainDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_intermediate_sales_projection, container, false);

        post_key = getActivity().getIntent().getExtras().getString("post_key");
        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");

        foreseeable_arr.add("Totalmente Previsible");foreseeable_arr.add("Muy Previsible");foreseeable_arr.add("Algo Previsible");foreseeable_arr.add("Poco Previsible");foreseeable_arr.add("Nada Previsible");
        uncertain_arr.add("Poco Incierto");uncertain_arr.add("Algo Incierto");uncertain_arr.add("Muy Incierto");uncertain_arr.add("Totalmente Incierto");



        mLineChart = view.findViewById(R.id.linechart);
        txtCurrentYear = view.findViewById(R.id.txtCurrentYear);
        txtCurrentYear2 = view.findViewById(R.id.txtCurrentYear2);
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
        txtMonth1t2 = view.findViewById(R.id.txtMonth1t2);
        txtMonth2t2 = view.findViewById(R.id.txtMonth2t2);
        txtMonth3t2 = view.findViewById(R.id.txtMonth3t2);
        txtMonth4t2 = view.findViewById(R.id.txtMonth4t2);
        txtMonth5t2 = view.findViewById(R.id.txtMonth5t2);
        txtMonth6t2 = view.findViewById(R.id.txtMonth6t2);
        txtMonth7t2 = view.findViewById(R.id.txtMonth7t2);
        txtMonth8t2 = view.findViewById(R.id.txtMonth8t2);
        txtMonth9t2 = view.findViewById(R.id.txtMonth9t2);
        txtMonth10t2 = view.findViewById(R.id.txtMonth10t2);
        txtMonth11t2 = view.findViewById(R.id.txtMonth11t2);
        txtMonth12t2 = view.findViewById(R.id.txtMonth12t2);
        txtMonth1t3 = view.findViewById(R.id.txtMonth1t3);
        txtMonth2t3 = view.findViewById(R.id.txtMonth2t3);
        txtMonth3t3 = view.findViewById(R.id.txtMonth3t3);
        txtMonth4t3 = view.findViewById(R.id.txtMonth4t3);
        txtMonth5t3 = view.findViewById(R.id.txtMonth5t3);
        txtMonth6t3 = view.findViewById(R.id.txtMonth6t3);
        txtMonth7t3 = view.findViewById(R.id.txtMonth7t3);
        txtMonth8t3 = view.findViewById(R.id.txtMonth8t3);
        txtMonth9t3 = view.findViewById(R.id.txtMonth9t3);
        txtMonth10t3 = view.findViewById(R.id.txtMonth10t3);
        txtMonth11t3 = view.findViewById(R.id.txtMonth11t3);
        txtMonth12t3 = view.findViewById(R.id.txtMonth12t3);
        btnAction1 = view.findViewById(R.id.btnAction1);
        btnAction2 = view.findViewById(R.id.btnAction2);
        txtCurrentYear3 = view.findViewById(R.id.txtCurrentYear3);
        btnSalesProjection = view.findViewById(R.id.btnSalesProjection);

        Date date= new Date();
        final Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        day = cal.get(Calendar.DAY_OF_MONTH);
        month = cal.get(Calendar.MONTH)+1;
        year = cal.get(Calendar.YEAR);
        year2 = cal.get(Calendar.YEAR)+1;

        decimalFormat = new DecimalFormat("0.00");

        txtCurrentYear.setText("TOTAL (S/) "+year);
        txtCurrentYear2.setText("PREVISIBLE "+year2);
        txtCurrentYear3.setText("INCIERTO "+year2);


        /*txtMonth1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                calculateVar();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        txtMonth1t2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                companyRef.child(post_key).child("Module 7").child("Intermediate Sales Projection").child(year2+""+1).setValue(txtMonth1t2.getText().toString().trim());
                calculateVar();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        txtMonth2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                calculateVar();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        txtMonth2t2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                companyRef.child(post_key).child("Module 7").child("Intermediate Sales Projection").child(year2+""+2).setValue(txtMonth2t2.getText().toString().trim());
                calculateVar();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        txtMonth3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                calculateVar();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        txtMonth3t2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                companyRef.child(post_key).child("Module 7").child("Intermediate Sales Projection").child(year2+""+3).setValue(txtMonth3t2.getText().toString().trim());
                calculateVar();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        txtMonth4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                calculateVar();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        txtMonth4t2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                companyRef.child(post_key).child("Module 7").child("Intermediate Sales Projection").child(year2+""+4).setValue(txtMonth4t2.getText().toString().trim());
                calculateVar();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        txtMonth5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                calculateVar();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        txtMonth5t2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                companyRef.child(post_key).child("Module 7").child("Intermediate Sales Projection").child(year2+""+5).setValue(txtMonth5t2.getText().toString().trim());
                calculateVar();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        txtMonth6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                calculateVar();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        txtMonth6t2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                companyRef.child(post_key).child("Module 7").child("Intermediate Sales Projection").child(year2+""+6).setValue(txtMonth6t2.getText().toString().trim());
                calculateVar();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        txtMonth7.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                calculateVar();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        txtMonth7t2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                companyRef.child(post_key).child("Module 7").child("Intermediate Sales Projection").child(year2+""+7).setValue(txtMonth7t2.getText().toString().trim());
                calculateVar();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        txtMonth8.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                calculateVar();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        txtMonth8t2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                companyRef.child(post_key).child("Module 7").child("Intermediate Sales Projection").child(year2+""+8).setValue(txtMonth8t2.getText().toString().trim());
                calculateVar();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        txtMonth9.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                calculateVar();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        txtMonth9t2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                companyRef.child(post_key).child("Module 7").child("Intermediate Sales Projection").child(year2+""+9).setValue(txtMonth9t2.getText().toString().trim());
                calculateVar();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        txtMonth10.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                calculateVar();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        txtMonth10t2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                companyRef.child(post_key).child("Module 7").child("Intermediate Sales Projection").child(year2+""+10).setValue(txtMonth10t2.getText().toString().trim());
                calculateVar();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        txtMonth11.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                calculateVar();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        txtMonth11t2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                companyRef.child(post_key).child("Module 7").child("Intermediate Sales Projection").child(year2+""+11).setValue(txtMonth11t2.getText().toString().trim());
                calculateVar();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        txtMonth12.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                calculateVar();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        txtMonth12t2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                companyRef.child(post_key).child("Module 7").child("Intermediate Sales Projection").child(year2+""+12).setValue(txtMonth12t2.getText().toString().trim());
                calculateVar();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });*/

        btnAction1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                foreseeableDialog.showSpinerDialog();
            }
        });

        foreseeable_value_db = 0.00;
        uncertain_value_db = 0.00;

        foreseeableDialog = new SpinnerDialog(getActivity(),foreseeable_arr, "Selecciona la opción más adecuada");
        foreseeableDialog.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String item2, int position2) {
                btnAction1.setText(item2);
                if (item2.equals("Totalmente Previsible")) {
                    foreseeable_value_db = 0.0;
                }
                if (item2.equals("Muy Previsible")) {
                    foreseeable_value_db = 0.2;
                }
                if (item2.equals("Algo Previsible")) {
                    foreseeable_value_db = 0.3;
                }
                if (item2.equals("Poco Previsible")) {
                    foreseeable_value_db = 0.4;
                }
                if (item2.equals("Nada Previsible")) {
                    foreseeable_value_db = 0.5;
                }
                companyRef.child(post_key).child("Module 7").child("Intermediate Sales Projection").child("foreseeable_value").setValue(item2);
                calculateVar();
            }
        });

        btnAction2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uncertainDialog.showSpinerDialog();

            }
        });

        uncertainDialog = new SpinnerDialog(getActivity(),uncertain_arr, "Selecciona la opción más adecuada");
        uncertainDialog.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String item2, int position2) {
                btnAction2.setText(item2);
                if (item2.equals("Poco Incierto")) {
                    uncertain_value_db = 0.7;
                }
                if (item2.equals("Algo Incierto")) {
                    uncertain_value_db = 0.8;
                }
                if (item2.equals("Muy Incierto")) {
                    uncertain_value_db = 0.9;
                }
                if (item2.equals("Totalmente Incierto")) {
                    uncertain_value_db = 1.0;
                }
                companyRef.child(post_key).child("Module 7").child("Intermediate Sales Projection").child("uncertain_value").setValue(item2);
                calculateVar();
            }
        });

        companyRef.child(post_key).child("Module 7").child("Intermediate Sales Projection").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild("foreseeable_value")) {
                    foreseeable_value = dataSnapshot.child("foreseeable_value").getValue().toString();
                    if (foreseeable_value.equals("Totalmente Previsible")) {
                        foreseeable_value_db = 0.0;
                    }
                    if (foreseeable_value.equals("Muy Previsible")) {
                        foreseeable_value_db = 0.2;
                    }
                    if (foreseeable_value.equals("Algo Previsible")) {
                        foreseeable_value_db = 0.3;
                    }
                    if (foreseeable_value.equals("Poco Previsible")) {
                        foreseeable_value_db = 0.4;
                    }
                    if (foreseeable_value.equals("Nada Previsible")) {
                        foreseeable_value_db = 0.5;
                    }
                    btnAction1.setText(foreseeable_value);
                    calculateVar();

                }
                if (dataSnapshot.hasChild("uncertain_value")) {
                    uncertain_value = dataSnapshot.child("uncertain_value").getValue().toString();
                    if (uncertain_value.equals("Poco Incierto")) {
                        uncertain_value_db = 0.7;
                    }
                    if (uncertain_value.equals("Algo Incierto")) {
                        uncertain_value_db = 0.8;
                    }
                    if (uncertain_value.equals("Muy Incierto")) {
                        uncertain_value_db = 0.9;
                    }
                    if (uncertain_value.equals("Totalmente Incierto")) {
                        uncertain_value_db = 1.0;
                    }
                    btnAction2.setText(uncertain_value);
                    calculateVar();

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        companyRef.child(post_key).child("Module 7").child("Sales Projection").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild(year2 + "" + 1)) {
                    month_value = dataSnapshot.child(year2 + "" + 1).getValue().toString();
                    txtMonth1t2.setText(month_value);
                    txtMonth1t3.setText(month_value);

                }

                calculateVar();
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
                        txtMonth1.setText(sales_1);

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
                        txtMonth2.setText(sales_2);

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
                        txtMonth3.setText(sales_3);

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
                        txtMonth4.setText(sales_4);

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
                        txtMonth5.setText(sales_5);

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
                        txtMonth6.setText(sales_6);

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
                        txtMonth7.setText(sales_7);

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
                        txtMonth8.setText(sales_8);

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
                        txtMonth9.setText(sales_9);

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
                        txtMonth10.setText(sales_10);

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
                        txtMonth11.setText(sales_11);

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
                        txtMonth12.setText(sales_12);

                        setValueOnGraph();
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        btnSalesProjection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                calculateVar();

            }
        });


        return view;
    }

    private ArrayList<Entry> dataValues1()
    {
        f1 = Float.parseFloat(txtMonth1.getText().toString());
        f2 = Float.parseFloat(txtMonth2.getText().toString());
        f3 = Float.parseFloat(txtMonth3.getText().toString());
        f4 = Float.parseFloat(txtMonth4.getText().toString());
        f5 = Float.parseFloat(txtMonth5.getText().toString());
        f6 = Float.parseFloat(txtMonth6.getText().toString());
        f7 = Float.parseFloat(txtMonth7.getText().toString());
        f8 = Float.parseFloat(txtMonth8.getText().toString());
        f9 = Float.parseFloat(txtMonth9.getText().toString());
        f10 = Float.parseFloat(txtMonth10.getText().toString());
        f11 = Float.parseFloat(txtMonth11.getText().toString());
        f12 = Float.parseFloat(txtMonth12.getText().toString());

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

        f1 = Float.parseFloat(txtMonth1t2.getText().toString());
        f2 = Float.parseFloat(txtMonth2t2.getText().toString());
        f3 = Float.parseFloat(txtMonth3t2.getText().toString());
        f4 = Float.parseFloat(txtMonth4t2.getText().toString());
        f5 = Float.parseFloat(txtMonth5t2.getText().toString());
        f6 = Float.parseFloat(txtMonth6t2.getText().toString());
        f7 = Float.parseFloat(txtMonth7t2.getText().toString());
        f8 = Float.parseFloat(txtMonth8t2.getText().toString());
        f9 = Float.parseFloat(txtMonth9t2.getText().toString());
        f10 = Float.parseFloat(txtMonth10t2.getText().toString());
        f11 = Float.parseFloat(txtMonth11t2.getText().toString());
        f12 = Float.parseFloat(txtMonth12t2.getText().toString());

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

    private ArrayList<Entry> dataValues3()
    {

        f1 = Float.parseFloat(txtMonth1t3.getText().toString());
        f2 = Float.parseFloat(txtMonth2t3.getText().toString());
        f3 = Float.parseFloat(txtMonth3t3.getText().toString());
        f4 = Float.parseFloat(txtMonth4t3.getText().toString());
        f5 = Float.parseFloat(txtMonth5t3.getText().toString());
        f6 = Float.parseFloat(txtMonth6t3.getText().toString());
        f7 = Float.parseFloat(txtMonth7t3.getText().toString());
        f8 = Float.parseFloat(txtMonth8t3.getText().toString());
        f9 = Float.parseFloat(txtMonth9t3.getText().toString());
        f10 = Float.parseFloat(txtMonth10t3.getText().toString());
        f11 = Float.parseFloat(txtMonth11t3.getText().toString());
        f12 = Float.parseFloat(txtMonth12t3.getText().toString());

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

    private void setValueOnGraph() {
        LineDataSet lineDataSet1 = new LineDataSet(dataValues1(),"Real");
        LineDataSet lineDataSet2 = new LineDataSet(dataValues2(),"Previsible");
        LineDataSet lineDataSet3 = new LineDataSet(dataValues2(),"Incierto");
        lineDataSet1.setLineWidth(2);
        lineDataSet1.setColor(Color.GREEN);
        lineDataSet1.setCircleColor(Color.GREEN);
        lineDataSet1.setCircleColorHole(Color.GREEN);
        lineDataSet2.setColor(Color.DKGRAY);
        lineDataSet2.setCircleColor(Color.DKGRAY);
        lineDataSet2.setCircleColorHole(Color.DKGRAY);
        lineDataSet3.setColor(Color.RED);
        lineDataSet3.setCircleColor(Color.RED);
        lineDataSet3.setCircleColorHole(Color.RED);
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(lineDataSet1);
        dataSets.add(lineDataSet3);
        dataSets.add(lineDataSet2);


        LineData data = new LineData(dataSets);
        mLineChart.setData(data);
        mLineChart.invalidate();
        mLineChart.getDescription().setEnabled(false);
    }

    private void calculateVar() {
        double month2t1 = Double.parseDouble(txtMonth2.getText().toString());
        double month1t2 = Double.parseDouble(txtMonth1t2.getText().toString());
        double foreseeable2 = (foreseeable_value_db*month2t1)+((1-foreseeable_value_db)*month1t2);
        String foreseeable2_st = decimalFormat.format(foreseeable2);
        txtMonth2t2.setText(foreseeable2_st);
        companyRef.child(post_key).child("Module 7").child("Intermediate Sales Projection").child("Foreseeable").child(year2+""+1).setValue(txtMonth1t2.getText().toString());
        companyRef.child(post_key).child("Module 7").child("Intermediate Sales Projection").child("Foreseeable").child(year2+""+2).setValue(foreseeable2_st);

        double month3t1 = Double.parseDouble(txtMonth3.getText().toString());
        double month2t2 = Double.parseDouble(txtMonth2t2.getText().toString());
        double foreseeable3 = (foreseeable_value_db*month3t1)+((1-foreseeable_value_db)*month2t2);
        String foreseeable3_st = decimalFormat.format(foreseeable3);
        txtMonth3t2.setText(foreseeable3_st);
        companyRef.child(post_key).child("Module 7").child("Intermediate Sales Projection").child("Foreseeable").child(year2+""+3).setValue(foreseeable3_st);

        double month4t1 = Double.parseDouble(txtMonth4.getText().toString());
        double month3t2 = Double.parseDouble(txtMonth3t2.getText().toString());
        double foreseeable4 = (foreseeable_value_db*month4t1)+((1-foreseeable_value_db)*month3t2);
        String foreseeable4_st = decimalFormat.format(foreseeable4);
        txtMonth4t2.setText(foreseeable4_st);
        companyRef.child(post_key).child("Module 7").child("Intermediate Sales Projection").child("Foreseeable").child(year2+""+4).setValue(foreseeable4_st);

        double month5t1 = Double.parseDouble(txtMonth5.getText().toString());
        double month4t2 = Double.parseDouble(txtMonth4t2.getText().toString());
        double foreseeable5 = (foreseeable_value_db*month5t1)+((1-foreseeable_value_db)*month4t2);
        String foreseeable5_st = decimalFormat.format(foreseeable5);
        txtMonth5t2.setText(foreseeable5_st);
        companyRef.child(post_key).child("Module 7").child("Intermediate Sales Projection").child("Foreseeable").child(year2+""+5).setValue(foreseeable5_st);

        double month6t1 = Double.parseDouble(txtMonth6.getText().toString());
        double month5t2 = Double.parseDouble(txtMonth5t2.getText().toString());
        double foreseeable6 = (foreseeable_value_db*month6t1)+((1-foreseeable_value_db)*month5t2);
        String foreseeable6_st = decimalFormat.format(foreseeable6);
        txtMonth6t2.setText(foreseeable6_st);
        companyRef.child(post_key).child("Module 7").child("Intermediate Sales Projection").child("Foreseeable").child(year2+""+6).setValue(foreseeable6_st);

        double month7t1 = Double.parseDouble(txtMonth7.getText().toString());
        double month6t2 = Double.parseDouble(txtMonth6t2.getText().toString());
        double foreseeable7 = (foreseeable_value_db*month7t1)+((1-foreseeable_value_db)*month6t2);
        String foreseeable7_st = decimalFormat.format(foreseeable7);
        txtMonth7t2.setText(foreseeable7_st);
        companyRef.child(post_key).child("Module 7").child("Intermediate Sales Projection").child("Foreseeable").child(year2+""+7).setValue(foreseeable7_st);

        double month8t1 = Double.parseDouble(txtMonth8.getText().toString());
        double month7t2 = Double.parseDouble(txtMonth7t2.getText().toString());
        double foreseeable8 = (foreseeable_value_db*month8t1)+((1-foreseeable_value_db)*month7t2);
        String foreseeable8_st = decimalFormat.format(foreseeable8);
        txtMonth8t2.setText(foreseeable8_st);
        companyRef.child(post_key).child("Module 7").child("Intermediate Sales Projection").child("Foreseeable").child(year2+""+8).setValue(foreseeable8_st);

        double month9t1 = Double.parseDouble(txtMonth9.getText().toString());
        double month8t2 = Double.parseDouble(txtMonth8t2.getText().toString());
        double foreseeable9 = (foreseeable_value_db*month9t1)+((1-foreseeable_value_db)*month8t2);
        String foreseeable9_st = decimalFormat.format(foreseeable9);
        txtMonth9t2.setText(foreseeable9_st);
        companyRef.child(post_key).child("Module 7").child("Intermediate Sales Projection").child("Foreseeable").child(year2+""+9).setValue(foreseeable9_st);

        double month10t1 = Double.parseDouble(txtMonth10.getText().toString());
        double month9t2 = Double.parseDouble(txtMonth9t2.getText().toString());
        double foreseeable10 = (foreseeable_value_db*month10t1)+((1-foreseeable_value_db)*month9t2);
        String foreseeable10_st = decimalFormat.format(foreseeable10);
        txtMonth10t2.setText(foreseeable10_st);
        companyRef.child(post_key).child("Module 7").child("Intermediate Sales Projection").child("Foreseeable").child(year2+""+10).setValue(foreseeable10_st);

        double month11t1 = Double.parseDouble(txtMonth11.getText().toString());
        double month10t2 = Double.parseDouble(txtMonth10t2.getText().toString());
        double foreseeable11 = (foreseeable_value_db*month11t1)+((1-foreseeable_value_db)*month10t2);
        String foreseeable11_st = decimalFormat.format(foreseeable11);
        txtMonth11t2.setText(foreseeable11_st);
        companyRef.child(post_key).child("Module 7").child("Intermediate Sales Projection").child("Foreseeable").child(year2+""+11).setValue(foreseeable11_st);

        double month12t1 = Double.parseDouble(txtMonth12.getText().toString());
        double month11t2 = Double.parseDouble(txtMonth11t2.getText().toString());
        double foreseeable12 = (foreseeable_value_db*month12t1)+((1-foreseeable_value_db)*month11t2);
        String foreseeable12_st = decimalFormat.format(foreseeable12);
        txtMonth12t2.setText(foreseeable12_st);
        companyRef.child(post_key).child("Module 7").child("Intermediate Sales Projection").child("Foreseeable").child(year2+""+12).setValue(foreseeable12_st);

        //UNCERTAIN PROJECTION

        double month1t3 = Double.parseDouble(txtMonth1t3.getText().toString());
        double uncertain2 = (uncertain_value_db*month2t1)+((1-uncertain_value_db)*month1t3);
        String uncertain2_st = decimalFormat.format(uncertain2);
        txtMonth2t3.setText(uncertain2_st);
        companyRef.child(post_key).child("Module 7").child("Intermediate Sales Projection").child("Uncertain").child(year2+""+1).setValue(txtMonth1t3.getText().toString());
        companyRef.child(post_key).child("Module 7").child("Intermediate Sales Projection").child("Uncertain").child(year2+""+2).setValue(uncertain2_st);

        double month2t3 = Double.parseDouble(txtMonth2t3.getText().toString());
        double uncertain3 = (uncertain_value_db*month3t1)+((1-uncertain_value_db)*month2t3);
        String uncertain3_st = decimalFormat.format(uncertain3);
        txtMonth3t3.setText(uncertain3_st);
        companyRef.child(post_key).child("Module 7").child("Intermediate Sales Projection").child("Uncertain").child(year2+""+3).setValue(uncertain3_st);

        double month3t3 = Double.parseDouble(txtMonth3t3.getText().toString());
        double uncertain4 = (uncertain_value_db*month4t1)+((1-uncertain_value_db)*month3t3);
        String uncertain4_st = decimalFormat.format(uncertain4);
        txtMonth4t3.setText(uncertain4_st);
        companyRef.child(post_key).child("Module 7").child("Intermediate Sales Projection").child("Uncertain").child(year2+""+4).setValue(uncertain4_st);

        double month4t3 = Double.parseDouble(txtMonth4t3.getText().toString());
        double uncertain5 = (uncertain_value_db*month5t1)+((1-uncertain_value_db)*month4t3);
        String uncertain5_st = decimalFormat.format(uncertain5);
        txtMonth5t3.setText(uncertain5_st);
        companyRef.child(post_key).child("Module 7").child("Intermediate Sales Projection").child("Uncertain").child(year2+""+5).setValue(uncertain5_st);

        double month5t3 = Double.parseDouble(txtMonth5t3.getText().toString());
        double uncertain6 = (uncertain_value_db*month6t1)+((1-uncertain_value_db)*month5t3);
        String uncertain6_st = decimalFormat.format(uncertain6);
        txtMonth6t3.setText(uncertain6_st);
        companyRef.child(post_key).child("Module 7").child("Intermediate Sales Projection").child("Uncertain").child(year2+""+6).setValue(uncertain6_st);

        double month6t3 = Double.parseDouble(txtMonth6t3.getText().toString());
        double uncertain7 = (uncertain_value_db*month7t1)+((1-uncertain_value_db)*month6t3);
        String uncertain7_st = decimalFormat.format(uncertain7);
        txtMonth7t3.setText(uncertain7_st);
        companyRef.child(post_key).child("Module 7").child("Intermediate Sales Projection").child("Uncertain").child(year2+""+7).setValue(uncertain7_st);

        double month7t3 = Double.parseDouble(txtMonth7t3.getText().toString());
        double uncertain8 = (uncertain_value_db*month8t1)+((1-uncertain_value_db)*month7t3);
        String uncertain8_st = decimalFormat.format(uncertain8);
        txtMonth8t3.setText(uncertain8_st);
        companyRef.child(post_key).child("Module 7").child("Intermediate Sales Projection").child("Uncertain").child(year2+""+8).setValue(uncertain8_st);

        double month8t3 = Double.parseDouble(txtMonth8t3.getText().toString());
        double uncertain9 = (uncertain_value_db*month9t1)+((1-uncertain_value_db)*month8t3);
        String uncertain9_st = decimalFormat.format(uncertain9);
        txtMonth9t3.setText(uncertain9_st);
        companyRef.child(post_key).child("Module 7").child("Intermediate Sales Projection").child("Uncertain").child(year2+""+9).setValue(uncertain9_st);

        double month9t3 = Double.parseDouble(txtMonth9t3.getText().toString());
        double uncertain10 = (uncertain_value_db*month10t1)+((1-uncertain_value_db)*month9t3);
        String uncertain10_st = decimalFormat.format(uncertain10);
        txtMonth10t3.setText(uncertain10_st);
        companyRef.child(post_key).child("Module 7").child("Intermediate Sales Projection").child("Uncertain").child(year2+""+10).setValue(uncertain10_st);

        double month10t3 = Double.parseDouble(txtMonth10t3.getText().toString());
        double uncertain11 = (uncertain_value_db*month11t1)+((1-uncertain_value_db)*month10t3);
        String uncertain11_st = decimalFormat.format(uncertain11);
        txtMonth11t3.setText(uncertain11_st);
        companyRef.child(post_key).child("Module 7").child("Intermediate Sales Projection").child("Uncertain").child(year2+""+11).setValue(uncertain11_st);

        double month11t3 = Double.parseDouble(txtMonth11t3.getText().toString());
        double uncertain12 = (uncertain_value_db*month12t1)+((1-uncertain_value_db)*month11t3);
        String uncertain12_st = decimalFormat.format(uncertain12);
        txtMonth12t3.setText(uncertain12_st);
        companyRef.child(post_key).child("Module 7").child("Intermediate Sales Projection").child("Uncertain").child(year2+""+12).setValue(uncertain12_st);

        dataValues1();
        dataValues2();
        dataValues3();
        setValueOnGraph();
    }
}
