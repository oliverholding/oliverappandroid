package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Simulators.CostPriceSimulator;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.oalonedeveloper.oliver.oliverappandroidapp.R;

import java.text.DecimalFormat;


public class ProductCostSimulatorFragment extends Fragment {

    EditText edtCost1,edtCost2,edtCost3,edtProfit,edtTaxes;
    TextView txtPrice;
    DecimalFormat decimalFormat;
    ImageView txtInfo1,txtInfo2,txtInfo3,txtInfo4,txtInfo5;

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

        txtInfo1 = view.findViewById(R.id.txtInfo1);
        txtInfo2 = view.findViewById(R.id.txtInfo2);
        txtInfo3 = view.findViewById(R.id.txtInfo3);
        txtInfo4 = view.findViewById(R.id.txtInfo4);
        txtInfo5 = view.findViewById(R.id.txtInfo5);

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

        txtInfo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = "Costo fijo Unitario";
                String info = "Se refiere a los egresos de forma recurrente que no dependen de la producción o cantidad comericializada";
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
                String title = "Otros Costos Unitarios";
                String info = "Se refiere a otros costos que puedan estar incurridos en la producción o comercialización por cada uno de los productos";
                int img = R.drawable.costos;

                showInfoDialog(title,info,img);
            }
        });

        txtInfo4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = "Rentabilidad Esperada";
                String info = "Es el porcentaje de rentabilidad que esperas obtener después de todos tus costos";
                int img = R.drawable.rentabilidad;

                showInfoDialog(title,info,img);
            }
        });

        txtInfo5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = "IGV";
                String info = "Es la tasa impositiva que sueler ser del 18% que se aplica a cada una de las ventas";
                int img = R.drawable.tributos_impuestos;

                showInfoDialog(title,info,img);
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

    private void showInfoDialog(String title, String info, int img) {
        final AlertDialog dialog = new AlertDialog.Builder(getActivity()).create();

        LayoutInflater inflater = LayoutInflater.from(getActivity());
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
