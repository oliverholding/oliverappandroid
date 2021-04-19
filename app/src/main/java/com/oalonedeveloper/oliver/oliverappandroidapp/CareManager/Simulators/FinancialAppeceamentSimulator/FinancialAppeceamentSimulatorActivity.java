package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Simulators.FinancialAppeceamentSimulator;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.oalonedeveloper.oliver.oliverappandroidapp.R;

import java.text.DecimalFormat;

public class FinancialAppeceamentSimulatorActivity extends AppCompatActivity {

    EditText edtCapital,edtMonths,edtRate;
    TextView txtResult;
    DecimalFormat decimalFormat;
    ImageView txtInfo1,txtInfo2,txtInfo3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_financial_appeceament_simulator);

        edtCapital = findViewById(R.id.edtCapital);
        edtMonths = findViewById(R.id.edtMonths);
        edtRate = findViewById(R.id.edtRate);
        txtResult = findViewById(R.id.txtResult);

        txtInfo1 = findViewById(R.id.txtInfo1);
        txtInfo2 = findViewById(R.id.txtInfo2);
        txtInfo3 = findViewById(R.id.txtInfo3);


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

        txtInfo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = "Capital";
                String info = "Es el dinero que una entidad o persona te entrega para financiar algo";
                int img = R.drawable.prestamos_empresas;

                showInfoDialog(title,info,img);
            }
        });

        txtInfo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = "Plazo en meses";
                String info = "Es el número de meses que dura el préstamo";
                int img = R.drawable.prestamos_empresas;

                showInfoDialog(title,info,img);
            }
        });

        txtInfo3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = "Tasa de interés efectivo anual";
                String info = "Es el precio del dinero, esta tasa principalmente es dada por las instituciones financieras para representar el interés a cobrar por el capital prestado";
                int img = R.drawable.prestamos_empresas;

                showInfoDialog(title,info,img);
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
