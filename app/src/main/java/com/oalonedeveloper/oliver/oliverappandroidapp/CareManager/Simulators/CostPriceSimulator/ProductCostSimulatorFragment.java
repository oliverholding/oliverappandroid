package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Simulators.CostPriceSimulator;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.oalonedeveloper.oliver.oliverappandroidapp.R;

import java.text.DecimalFormat;


public class ProductCostSimulatorFragment extends Fragment {

    EditText edtCost1,edtCost2,edtCost3,edtProfit,edtTaxes;
    TextView txtPrice;
    DecimalFormat decimalFormat;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_product_cost_simulator, container, false);


        edtCost1 = view.findViewById(R.id.edtCost1);
        edtCost2 = view.findViewById(R.id.edtCost2);
        edtCost3 = view.findViewById(R.id.edtCost3);
        edtProfit = view.findViewById(R.id.edtProfit);
        edtTaxes = view.findViewById(R.id.edtTaxes);
        txtPrice = view.findViewById(R.id.txtPrice);

        decimalFormat = new DecimalFormat("0.00");

        edtTaxes.setText("18.00");

        edtCost1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!edtCost1.getText().toString().equals("") && !edtCost2.getText().toString().equals("") && !edtCost3.getText().toString().equals("") &&!edtProfit.getText().toString().equals("")
                && !edtTaxes.getText().toString().equals("")) {
                    calculatePrice();
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
                if (!edtCost1.getText().toString().equals("") && !edtCost2.getText().toString().equals("") && !edtCost3.getText().toString().equals("") &&!edtProfit.getText().toString().equals("")
                        && !edtTaxes.getText().toString().equals("")) {
                    calculatePrice();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        edtCost3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!edtCost1.getText().toString().equals("") && !edtCost2.getText().toString().equals("") && !edtCost3.getText().toString().equals("") &&!edtProfit.getText().toString().equals("")
                        && !edtTaxes.getText().toString().equals("")) {
                    calculatePrice();
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
                if (!edtCost1.getText().toString().equals("") && !edtCost2.getText().toString().equals("") && !edtCost3.getText().toString().equals("") &&!edtProfit.getText().toString().equals("")
                        && !edtTaxes.getText().toString().equals("")) {
                    calculatePrice();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        edtTaxes.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!edtCost1.getText().toString().equals("") && !edtCost2.getText().toString().equals("") && !edtCost3.getText().toString().equals("") &&!edtProfit.getText().toString().equals("")
                        && !edtTaxes.getText().toString().equals("")) {
                    calculatePrice();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        return view;
    }

    private void calculatePrice() {
        double cost1 = Double.parseDouble(edtCost1.getText().toString());
        double cost2 = Double.parseDouble(edtCost2.getText().toString());
        double cost3 = Double.parseDouble(edtCost3.getText().toString());

        double profit = Double.parseDouble(edtProfit.getText().toString());
        double taxes = Double.parseDouble(edtTaxes.getText().toString());

        double total_cost = cost1+cost2+cost3;
        double total_cost_profit = total_cost*(1+(profit/100));
        double total_cost_profit_taxes = total_cost_profit*(1+(taxes/100));

        String price = decimalFormat.format(total_cost_profit_taxes);

        txtPrice.setText("S/ "+price);
    }
}
