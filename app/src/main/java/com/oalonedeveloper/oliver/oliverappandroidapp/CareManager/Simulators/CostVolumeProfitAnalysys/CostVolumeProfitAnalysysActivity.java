package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Simulators.CostVolumeProfitAnalysys;

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

import com.oalonedeveloper.oliver.oliverappandroidapp.R;

import java.text.DecimalFormat;

public class CostVolumeProfitAnalysysActivity extends AppCompatActivity {

    EditText edtCost1,edtCost2,edtPrice,edtProfit;
    TextView txtQuantity1,txtQuantity2;
    DecimalFormat decimalFormat;
    ImageView txtInfo1,txtInfo2,txtInfo3,txtInfo4;


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

        txtInfo1 = findViewById(R.id.txtInfo1);
        txtInfo2 = findViewById(R.id.txtInfo2);
        txtInfo3 = findViewById(R.id.txtInfo3);
        txtInfo4 = findViewById(R.id.txtInfo4);

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
                String title = "Precio";
                String info = "Es el valor al que venderás tu producto o servicio";
                int img = R.drawable.precio;

                showInfoDialog(title,info,img);
            }
        });

        txtInfo4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = "Utilidad Esperada";
                String info = "Es el monto de rentabilidad que esperas obtener después de todos tus costos";
                int img = R.drawable.rentabilidad;

                showInfoDialog(title,info,img);
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

    private void showInfoDialog(String title, String info, int img) {
        final AlertDialog dialog = new AlertDialog.Builder(this).create();

        LayoutInflater inflater = LayoutInflater.from(this);
        View finance_method = inflater.inflate(R.layout.info_tool_dialog,null);

        ImageView imgInfo;
        TextView txtTitle,txtInfo;

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
