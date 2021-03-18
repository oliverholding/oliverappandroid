package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Simulators.FinancialAppeceamentSimulator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.oalonedeveloper.oliver.oliverappandroidapp.R;

import java.text.DecimalFormat;

public class FinancialAppeceamentSimulatorActivity extends AppCompatActivity {

    EditText edtCapital,edtMonths,edtRate;
    TextView txtResult;
    DecimalFormat decimalFormat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_financial_appeceament_simulator);

        edtCapital = findViewById(R.id.edtCapital);
        edtMonths = findViewById(R.id.edtMonths);
        edtRate = findViewById(R.id.edtRate);
        txtResult = findViewById(R.id.txtResult);

        decimalFormat = new DecimalFormat("0.00");

        edtCapital.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!edtCapital.getText().toString().equals("") &&!edtMonths.getText().toString().equals("")  &&!edtRate.getText().toString().equals("")) {
                    calculate();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        edtMonths.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!edtCapital.getText().toString().equals("") &&!edtMonths.getText().toString().equals("")  &&!edtRate.getText().toString().equals("")) {
                    calculate();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        edtRate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!edtCapital.getText().toString().equals("") &&!edtMonths.getText().toString().equals("")  &&!edtRate.getText().toString().equals("")) {
                    calculate();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void calculate() {
        double capital = Double.parseDouble(edtCapital.getText().toString());
        double months = Double.parseDouble(edtMonths.getText().toString());
        double rate = Double.parseDouble(edtRate.getText().toString())/100;

        double pow = (1.0/12.0);
        double tem = (Math.pow((1+rate),pow)-1);

        double rent_up = (capital*tem);
        double rent_down = 1-(Math.pow((1+tem),-months));
        double rent = rent_up/rent_down;

        String rent_st = decimalFormat.format(rent);
        txtResult.setText("S/ "+rent_st);

    }
}
