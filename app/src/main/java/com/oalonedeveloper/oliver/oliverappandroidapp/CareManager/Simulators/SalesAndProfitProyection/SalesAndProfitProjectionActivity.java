package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Simulators.SalesAndProfitProyection;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.oalonedeveloper.oliver.oliverappandroidapp.R;

import java.text.DecimalFormat;

public class SalesAndProfitProjectionActivity extends AppCompatActivity {

    EditText edtCost1,edtCost2,edtCost3,edtPrice,edtQuantity,edtProfit,edtIgv,edtReturnTax;
    TextView txtProfit,txtMessage,txtProfit2;
    DecimalFormat decimalFormat;
    ImageView txtInfo1,txtInfo2,txtInfo3,txtInfo4,txtInfo5,txtInfo6,txtInfo7,txtInfo8;

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

        txtInfo1 = findViewById(R.id.txtInfo1);
        txtInfo2 = findViewById(R.id.txtInfo2);
        txtInfo3 = findViewById(R.id.txtInfo3);
        txtInfo4 = findViewById(R.id.txtInfo4);
        txtInfo5 = findViewById(R.id.txtInfo5);
        txtInfo6 = findViewById(R.id.txtInfo6);
        txtInfo7 = findViewById(R.id.txtInfo7);
        txtInfo8 = findViewById(R.id.txtInfo8);

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

        txtInfo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = "Costos Fijos Totales";
                String info = "Se refiere a los egresos de forma recurrente que no dependen de la producción o cantidad comericializada en conjunto";
                int img = R.drawable.costos;

                showInfoDialog(title,info,img);
            }
        });

        txtInfo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = "Costo variable Unitario";
                String info = "Son los costos que cambian al mismo tiempo que cambia la cantidad producida o comercializada";
                int img = R.drawable.costos;

                showInfoDialog(title,info,img);
            }
        });

        txtInfo3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = "Otros Costos";
                String info = "Se refieren a otros costos en general";
                int img = R.drawable.costos;

                showInfoDialog(title,info,img);
            }
        });

        txtInfo4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = "Precio de Venta Unitario";
                String info = "Es el valor al que venderás tu producto o servicio";
                int img = R.drawable.ventas;

                showInfoDialog(title,info,img);
            }
        });
        txtInfo5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = "Unidades Vendidas";
                String info = "Cantidad de productos o servicios que se venderán durante el período";
                int img = R.drawable.ventas;

                showInfoDialog(title,info,img);
            }
        });

        txtInfo6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = "Rentabilidad esperada";
                String info = "Es el porcentaje que se espera ganar después de todos los costos";
                int img = R.drawable.rentabilidad;

                showInfoDialog(title,info,img);
            }
        });

        txtInfo7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = "IGV";
                String info = "Es la tasa impositiva que se aplica a todas las ventas que realizas";
                int img = R.drawable.tributos_impuestos;

                showInfoDialog(title,info,img);
            }
        });

        txtInfo8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = "Imapuesto a la renta";
                String info = "Es tasa impositiva que se aplica a la utilidad bruta";
                int img = R.drawable.tributos_impuestos;

                showInfoDialog(title,info,img);
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

    private void showInfoDialog(String title, String info, int img) {
        final AlertDialog dialog = new AlertDialog.Builder(this).create();

        LayoutInflater inflater = LayoutInflater.from(this);
        View finance_method = inflater.inflate(R.layout.info_tool_dialog, null);

        ImageView imgInfo;
        TextView txtTitle, txtInfo;

        imgInfo = finance_method.findViewById(R.id.imgInfo);
        txtTitle = finance_method.findViewById(R.id.txtTitle);
        txtInfo = finance_method.findViewById(R.id.txtInfo);

        imgInfo.setImageResource(img);
        txtTitle.setText(title);
        txtInfo.setText(info);

        dialog.setView(finance_method);
        dialog.show();

    }

}
