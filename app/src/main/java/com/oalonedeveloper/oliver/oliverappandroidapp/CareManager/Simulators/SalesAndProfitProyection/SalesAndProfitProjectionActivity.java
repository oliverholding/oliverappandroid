package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Simulators.SalesAndProfitProyection;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.oalonedeveloper.oliver.oliverappandroidapp.R;

import java.text.DecimalFormat;

public class SalesAndProfitProjectionActivity extends AppCompatActivity {

    EditText edtCost1,edtCost2,edtCost3,edtPrice,edtQuantity,edtProfit,edtIgv,edtReturnTax;
    TextView txtProfit,txtMessage,txtProfit2;
    DecimalFormat decimalFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_and_profit_projection);

        edtCost1 = findViewById(R.id.edtCost1);
        edtCost2 = findViewById(R.id.edtCost2);
        edtCost3 = findViewById(R.id.edtCost3);
        edtPrice = findViewById(R.id.edtPrice);
        edtQuantity = findViewById(R.id.edtQuantity);
        edtProfit = findViewById(R.id.edtProfit);
        edtIgv = findViewById(R.id.edtIgv);
        edtReturnTax = findViewById(R.id.edtReturnTax);
        txtProfit = findViewById(R.id.txtProfit);
        txtProfit2 = findViewById(R.id.txtProfit2);
        txtMessage = findViewById(R.id.txtMessage);

        decimalFormat = new DecimalFormat("0.00");

        edtCost1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!edtCost1.getText().toString().equals("") && !edtCost2.getText().toString().equals("") && !edtPrice.getText().toString().equals("") &&!edtPrice.getText().toString().equals("")
                        &&!edtQuantity.getText().toString().equals("")  &&!edtProfit.getText().toString().equals("")  &&!edtIgv.getText().toString().equals("")  &&!edtReturnTax.getText().toString().equals("")) {
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
                if (!edtCost1.getText().toString().equals("") && !edtCost2.getText().toString().equals("") && !edtPrice.getText().toString().equals("") &&!edtPrice.getText().toString().equals("")
                        &&!edtQuantity.getText().toString().equals("")  &&!edtProfit.getText().toString().equals("")  &&!edtIgv.getText().toString().equals("")  &&!edtReturnTax.getText().toString().equals("")) {
                    calculate();
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
                if (!edtCost1.getText().toString().equals("") && !edtCost2.getText().toString().equals("") && !edtPrice.getText().toString().equals("") &&!edtPrice.getText().toString().equals("")
                        &&!edtQuantity.getText().toString().equals("")  &&!edtProfit.getText().toString().equals("")  &&!edtIgv.getText().toString().equals("")  &&!edtReturnTax.getText().toString().equals("")) {
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
                if (!edtCost1.getText().toString().equals("") && !edtCost2.getText().toString().equals("") && !edtPrice.getText().toString().equals("") &&!edtPrice.getText().toString().equals("")
                        &&!edtQuantity.getText().toString().equals("")  &&!edtProfit.getText().toString().equals("")  &&!edtIgv.getText().toString().equals("")  &&!edtReturnTax.getText().toString().equals("")) {
                    calculate();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        edtQuantity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!edtCost1.getText().toString().equals("") && !edtCost2.getText().toString().equals("") && !edtPrice.getText().toString().equals("") &&!edtPrice.getText().toString().equals("")
                        &&!edtQuantity.getText().toString().equals("")  &&!edtProfit.getText().toString().equals("")  &&!edtIgv.getText().toString().equals("")  &&!edtReturnTax.getText().toString().equals("")) {
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
                if (!edtCost1.getText().toString().equals("") && !edtCost2.getText().toString().equals("") && !edtPrice.getText().toString().equals("") &&!edtPrice.getText().toString().equals("")
                        &&!edtQuantity.getText().toString().equals("")  &&!edtProfit.getText().toString().equals("")  &&!edtIgv.getText().toString().equals("")  &&!edtReturnTax.getText().toString().equals("")) {
                    calculate();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });edtIgv.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!edtCost1.getText().toString().equals("") && !edtCost2.getText().toString().equals("") && !edtPrice.getText().toString().equals("") &&!edtPrice.getText().toString().equals("")
                        &&!edtQuantity.getText().toString().equals("")  &&!edtProfit.getText().toString().equals("")  &&!edtIgv.getText().toString().equals("")  &&!edtReturnTax.getText().toString().equals("")) {
                    calculate();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        edtReturnTax.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!edtCost1.getText().toString().equals("") && !edtCost2.getText().toString().equals("") && !edtPrice.getText().toString().equals("") &&!edtPrice.getText().toString().equals("")
                        &&!edtQuantity.getText().toString().equals("")  &&!edtProfit.getText().toString().equals("")  &&!edtIgv.getText().toString().equals("")  &&!edtReturnTax.getText().toString().equals("")) {
                    calculate();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    private void calculate() {
        double cost1 = Double.parseDouble(edtCost1.getText().toString());
        double cost2 = Double.parseDouble(edtCost2.getText().toString());
        double cost3 = Double.parseDouble(edtCost3.getText().toString());
        double price = Double.parseDouble(edtPrice.getText().toString());
        double quantity = Double.parseDouble(edtQuantity.getText().toString());
        double profit_rate = Double.parseDouble(edtProfit.getText().toString());
        double igv_rate = Double.parseDouble(edtIgv.getText().toString());
        double ir_rate = Double.parseDouble(edtReturnTax.getText().toString());

        double total_cost = cost1+(cost2*quantity)+cost3;
        double total_incomes = price*quantity;
        double igv_amount = total_incomes*(igv_rate/100);
        double profit_before_taxes = total_incomes-igv_amount-total_cost;
        double ir_amount;

        if (profit_before_taxes > 0.00) {
            ir_amount = profit_before_taxes*(ir_rate/100);
        } else {
            ir_amount = 0.00;
        }

        double result = profit_before_taxes-ir_amount;
        String result_st = decimalFormat.format(result);
        txtProfit.setText("S/ "+result_st);


        double expected_incomes = (cost1+(cost2*quantity)+cost3)*(1+(profit_rate/100));
        String expected_incomes_st = decimalFormat.format(expected_incomes);
        txtProfit2.setText("S/ "+expected_incomes_st);

        if (expected_incomes > result) {
            txtMessage.setText("Tu utilidad probable real no supera tu utilidad esperada, recomendamos gestionar mejor los costos o aumentar el precio");
        } else  {
            txtMessage.setText("Tu utilidad probable real supera tu utilidad esperada, parece que estás haciendo un buen trabajo");
        }


    }
}
