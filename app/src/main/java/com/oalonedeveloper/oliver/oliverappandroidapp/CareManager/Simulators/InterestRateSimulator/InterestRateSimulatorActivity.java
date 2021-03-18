package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Simulators.InterestRateSimulator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

import in.galaxyofandroid.spinerdialog.OnSpinerItemClick;
import in.galaxyofandroid.spinerdialog.SpinnerDialog;

public class InterestRateSimulatorActivity extends AppCompatActivity {

    EditText edtRate;
    Button btnMonths,btnDays;
    TextView txtRate1,txtRate2;
    RelativeLayout rootLayout;

    ArrayList<String> arr_months =new ArrayList<>();
    SpinnerDialog spinnerMonthsDialog;
    ArrayList<String> arr_days =new ArrayList<>();
    SpinnerDialog spinnerDaysDialog;

    DecimalFormat decimalFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interest_rate_simulator);

        edtRate = findViewById(R.id.edtRate);
        btnMonths = findViewById(R.id.btnMonths);
        btnDays = findViewById(R.id.btnDays);
        txtRate1 = findViewById(R.id.txtRate1);
        txtRate2 = findViewById(R.id.txtRate2);

        rootLayout = findViewById(R.id.rootLayout);

        decimalFormat = new DecimalFormat("0.00");

        arr_months.add("1"); arr_months.add("2"); arr_months.add("3"); arr_months.add("4"); arr_months.add("5"); arr_months.add("6"); arr_months.add("7"); arr_months.add("8");
        arr_months.add("9"); arr_months.add("10"); arr_months.add("11"); arr_months.add("12"); arr_months.add("13"); arr_months.add("14"); arr_months.add("15"); arr_months.add("16");
        arr_months.add("17"); arr_months.add("18"); arr_months.add("19"); arr_months.add("20"); arr_months.add("21"); arr_months.add("22"); arr_months.add("23"); arr_months.add("24");

        arr_days.add("1"); arr_days.add("2"); arr_days.add("3"); arr_days.add("4"); arr_days.add("5"); arr_days.add("6"); arr_days.add("7"); arr_days.add("8"); arr_days.add("9"); arr_days.add("10");
        arr_days.add("11"); arr_days.add("12"); arr_days.add("13"); arr_days.add("14"); arr_days.add("15"); arr_days.add("16"); arr_days.add("17"); arr_days.add("18"); arr_days.add("19"); arr_days.add("20");
        arr_days.add("21"); arr_days.add("22"); arr_days.add("23"); arr_days.add("24"); arr_days.add("25"); arr_days.add("26"); arr_days.add("27"); arr_days.add("28"); arr_days.add("29"); arr_days.add("30");
        arr_days.add("31"); arr_days.add("32"); arr_days.add("33"); arr_days.add("34"); arr_days.add("35"); arr_days.add("36"); arr_days.add("37"); arr_days.add("38"); arr_days.add("39"); arr_days.add("40");
        arr_days.add("41"); arr_days.add("42"); arr_days.add("43"); arr_days.add("44"); arr_days.add("45"); arr_days.add("46"); arr_days.add("47"); arr_days.add("48"); arr_days.add("49"); arr_days.add("50");
        arr_days.add("51"); arr_days.add("52"); arr_days.add("53"); arr_days.add("54"); arr_days.add("55"); arr_days.add("56"); arr_days.add("57"); arr_days.add("58"); arr_days.add("59"); arr_days.add("60");

        btnMonths.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinnerMonthsDialog.showSpinerDialog();
            }
        });

        spinnerMonthsDialog = new SpinnerDialog(InterestRateSimulatorActivity.this,arr_months, "Selecciona el Número de meses");
        spinnerMonthsDialog.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String item2, int position2) {
                if (TextUtils.isEmpty(edtRate.getText().toString())) {
                    Snackbar.make(rootLayout, "Ingresa la tasa efectiva anual", Snackbar.LENGTH_SHORT).show();
                } else {
                    btnMonths.setText(item2);
                    double annual_rate = Double.parseDouble(edtRate.getText().toString())/100;
                    double months = Double.parseDouble(item2);

                    double pow = (months/12);
                    double tem = (Math.pow((1+annual_rate),pow)-1)*100;
                    String tem_st = decimalFormat.format(tem);
                    txtRate1.setText(tem_st+"%");
                }



            }
        });

        btnDays.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinnerDaysDialog.showSpinerDialog();
            }
        });

        spinnerDaysDialog = new SpinnerDialog(InterestRateSimulatorActivity.this,arr_days, "Selecciona el Número de días");
        spinnerDaysDialog.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String item2, int position2) {
                if (TextUtils.isEmpty(edtRate.getText().toString())) {
                    Snackbar.make(rootLayout, "Ingresa la tasa efectiva anual", Snackbar.LENGTH_SHORT).show();
                } else {
                    btnDays.setText(item2);
                    double annual_rate = Double.parseDouble(edtRate.getText().toString())/100;
                    double months = Double.parseDouble(item2);

                    double pow = (months/30);
                    double tem = (Math.pow((1+annual_rate),pow)-1)*100;
                    String tem_st = decimalFormat.format(tem);
                    txtRate2.setText(tem_st+"%");
                }



            }
        });

    }
}
