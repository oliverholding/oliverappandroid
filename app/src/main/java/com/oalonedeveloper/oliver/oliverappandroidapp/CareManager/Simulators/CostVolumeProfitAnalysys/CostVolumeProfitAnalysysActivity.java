package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Simulators.CostVolumeProfitAnalysys;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import com.oalonedeveloper.oliver.oliverappandroidapp.R;

import java.text.DecimalFormat;

public class CostVolumeProfitAnalysysActivity extends AppCompatActivity {

    EditText edtCost1,edtCost2,edtPrice,edtProfit;
    TextView txtQuantity1,txtQuantity2;
    DecimalFormat decimalFormat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cost_volume_profit_analysys);

        edtCost1 = findViewById(R.id.edtCost1);
        edtCost2 = findViewById(R.id.edtCost2);
        edtPrice = findViewById(R.id.edtPrice);
        edtProfit = findViewById(R.id.edtProfit);
        txtQuantity1 = findViewById(R.id.txtQuantity1);
        txtQuantity2 = findViewById(R.id.txtQuantity2);

        decimalFormat = new DecimalFormat("0.00");

        edtCost1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!edtCost1.getText().toString().equals("") && !edtCost2.getText().toString().equals("") && !edtPrice.getText().toString().equals("") &&!edtProfit.getText().toString().equals("")) {
                    calculate();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        edtCost2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!edtCost1.getText().toString().equals("") && !edtCost2.getText().toString().equals("") && !edtPrice.getText().toString().equals("") &&!edtProfit.getText().toString().equals("")) {
                    calculate();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        edtPrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!edtCost1.getText().toString().equals("") && !edtCost2.getText().toString().equals("") && !edtPrice.getText().toString().equals("") &&!edtProfit.getText().toString().equals("")) {
                    calculate();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        edtProfit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!edtCost1.getText().toString().equals("") && !edtCost2.getText().toString().equals("") && !edtPrice.getText().toString().equals("") &&!edtProfit.getText().toString().equals("")) {
                    calculate();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void calculate() {
        double total_fixed_cost = Double.parseDouble(edtCost1.getText().toString());
        double variable_unit_cost = Double.parseDouble(edtCost2.getText().toString());
        double price = Double.parseDouble(edtPrice.getText().toString());
        double profits = Double.parseDouble(edtProfit.getText().toString());

        double equilibrium_point = (total_fixed_cost/(price-variable_unit_cost));
        String equilibrium_point_st = decimalFormat.format(equilibrium_point);
        txtQuantity1.setText(equilibrium_point_st+" unidades");

        double goal =  ((total_fixed_cost+profits)/(price-variable_unit_cost));
        String goal_st = decimalFormat.format(goal);
        txtQuantity2.setText(goal_st +" unidades");

    }
}
