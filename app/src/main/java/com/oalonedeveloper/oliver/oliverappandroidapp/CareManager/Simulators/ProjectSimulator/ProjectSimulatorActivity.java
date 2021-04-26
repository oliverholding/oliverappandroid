package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Simulators.ProjectSimulator;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;

import java.text.DecimalFormat;

public class ProjectSimulatorActivity extends AppCompatActivity {

    EditText edtUnits1,edtUnits2,edtUnits3,edtUnits4,edtUnits5,edtPrice1,edtPrice2,edtPrice3,edtPrice4,edtPrice5,edtCost1,edtCost2,edtCost3,edtCost4,edtCost5,edtCostVar1,edtCostVar2,edtCostVar3,
            edtCostVar4,edtCostVar5,edtTaxes1,edtTaxes2,edtTaxes3,edtTaxes4,edtTaxes5,edtCapital,edtRateOfReturn;
    Button btnSimulation;
    TextView txtIncome1,txtIncome2,txtIncome3,txtIncome4,txtIncome5,txtOutcome1,txtOutcome2,txtOutcome3,txtOutcome4,txtOutcome5,txtEarningsBeforeTaxes1,txtEarningsBeforeTaxes2,txtEarningsBeforeTaxes3,
            txtEarningsBeforeTaxes4,txtEarningsBeforeTaxes5,txtProfit1,txtProfit2,txtProfit3,txtProfit4,txtProfit5,txtViability;
    RelativeLayout rootLayout;
    DecimalFormat decimalFormat;
    ImageView txtInfo1,txtInfo2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_simulator);

        edtUnits1 = findViewById(R.id.edtUnits1);
        edtUnits2 = findViewById(R.id.edtUnits2);
        edtUnits3 = findViewById(R.id.edtUnits3);
        edtUnits4 = findViewById(R.id.edtUnits4);
        edtUnits5 = findViewById(R.id.edtUnits5);

        edtPrice1 = findViewById(R.id.edtPrice1);
        edtPrice2 = findViewById(R.id.edtPrice2);
        edtPrice3 = findViewById(R.id.edtPrice3);
        edtPrice4 = findViewById(R.id.edtPrice4);
        edtPrice5 = findViewById(R.id.edtPrice5);

        edtCost1 = findViewById(R.id.edtCost1);
        edtCost2 = findViewById(R.id.edtCost2);
        edtCost3 = findViewById(R.id.edtCost3);
        edtCost4 = findViewById(R.id.edtCost4);
        edtCost5 = findViewById(R.id.edtCost5);

        edtCostVar1 = findViewById(R.id.edtCostVar1);
        edtCostVar2 = findViewById(R.id.edtCostVar2);
        edtCostVar3 = findViewById(R.id.edtCostVar3);
        edtCostVar4 = findViewById(R.id.edtCostVar4);
        edtCostVar5 = findViewById(R.id.edtCostVar5);

        edtTaxes1 = findViewById(R.id.edtTaxes1);
        edtTaxes2 = findViewById(R.id.edtTaxes2);
        edtTaxes3 = findViewById(R.id.edtTaxes3);
        edtTaxes4 = findViewById(R.id.edtTaxes4);
        edtTaxes5 = findViewById(R.id.edtTaxes5);

        btnSimulation = findViewById(R.id.btnSimulation);

        txtIncome1 = findViewById(R.id.txtIncome1);
        txtIncome2 = findViewById(R.id.txtIncome2);
        txtIncome3 = findViewById(R.id.txtIncome3);
        txtIncome4 = findViewById(R.id.txtIncome4);
        txtIncome5 = findViewById(R.id.txtIncome5);

        txtOutcome1 = findViewById(R.id.txtOutcome1);
        txtOutcome2 = findViewById(R.id.txtOutcome2);
        txtOutcome3 = findViewById(R.id.txtOutcome3);
        txtOutcome4 = findViewById(R.id.txtOutcome4);
        txtOutcome5 = findViewById(R.id.txtOutcome5);

        txtEarningsBeforeTaxes1 = findViewById(R.id.txtEarningsBeforeTaxes1);
        txtEarningsBeforeTaxes2 = findViewById(R.id.txtEarningsBeforeTaxes2);
        txtEarningsBeforeTaxes3 = findViewById(R.id.txtEarningsBeforeTaxes3);
        txtEarningsBeforeTaxes4 = findViewById(R.id.txtEarningsBeforeTaxes4);
        txtEarningsBeforeTaxes5 = findViewById(R.id.txtEarningsBeforeTaxes5);

        txtProfit1 = findViewById(R.id.txtProfit1);
        txtProfit2 = findViewById(R.id.txtProfit2);
        txtProfit3 = findViewById(R.id.txtProfit3);
        txtProfit4 = findViewById(R.id.txtProfit4);
        txtProfit5 = findViewById(R.id.txtProfit5);

        edtCapital = findViewById(R.id.edtCapital);
        edtRateOfReturn = findViewById(R.id.edtRateOfReturn);

        txtViability = findViewById(R.id.txtViability);
        txtInfo1 = findViewById(R.id.txtInfo1);
        txtInfo2 = findViewById(R.id.txtInfo2);

        rootLayout = findViewById(R.id.rootLayout);

        decimalFormat = new DecimalFormat("0.00");

        btnSimulation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(edtUnits1.getText().toString())) {
                    Snackbar.make(rootLayout,"Debes completar todos los campos",Snackbar.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(edtUnits2.getText().toString())) {
                    Snackbar.make(rootLayout,"Debes completar todos los campos",Snackbar.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(edtUnits3.getText().toString())) {
                    Snackbar.make(rootLayout,"Debes completar todos los campos",Snackbar.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(edtUnits4.getText().toString())) {
                    Snackbar.make(rootLayout,"Debes completar todos los campos",Snackbar.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(edtUnits5.getText().toString())) {
                    Snackbar.make(rootLayout,"Debes completar todos los campos",Snackbar.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(edtPrice1.getText().toString())) {
                    Snackbar.make(rootLayout,"Debes completar todos los campos",Snackbar.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(edtPrice2.getText().toString())) {
                    Snackbar.make(rootLayout,"Debes completar todos los campos",Snackbar.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(edtPrice3.getText().toString())) {
                    Snackbar.make(rootLayout,"Debes completar todos los campos",Snackbar.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(edtPrice4.getText().toString())) {
                    Snackbar.make(rootLayout,"Debes completar todos los campos",Snackbar.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(edtPrice5.getText().toString())) {
                    Snackbar.make(rootLayout,"Debes completar todos los campos",Snackbar.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(edtCost1.getText().toString())) {
                    Snackbar.make(rootLayout,"Debes completar todos los campos",Snackbar.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(edtCost2.getText().toString())) {
                    Snackbar.make(rootLayout,"Debes completar todos los campos",Snackbar.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(edtCost3.getText().toString())) {
                    Snackbar.make(rootLayout,"Debes completar todos los campos",Snackbar.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(edtCost4.getText().toString())) {
                    Snackbar.make(rootLayout,"Debes completar todos los campos",Snackbar.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(edtCost5.getText().toString())) {
                    Snackbar.make(rootLayout,"Debes completar todos los campos",Snackbar.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(edtCostVar1.getText().toString())) {
                    Snackbar.make(rootLayout,"Debes completar todos los campos",Snackbar.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(edtCostVar2.getText().toString())) {
                    Snackbar.make(rootLayout,"Debes completar todos los campos",Snackbar.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(edtCostVar3.getText().toString())) {
                    Snackbar.make(rootLayout,"Debes completar todos los campos",Snackbar.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(edtCostVar4.getText().toString())) {
                    Snackbar.make(rootLayout,"Debes completar todos los campos",Snackbar.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(edtCostVar5.getText().toString())) {
                    Snackbar.make(rootLayout,"Debes completar todos los campos",Snackbar.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(edtTaxes1.getText().toString())) {
                    Snackbar.make(rootLayout,"Debes completar todos los campos",Snackbar.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(edtTaxes2.getText().toString())) {
                    Snackbar.make(rootLayout,"Debes completar todos los campos",Snackbar.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(edtTaxes3.getText().toString())) {
                    Snackbar.make(rootLayout,"Debes completar todos los campos",Snackbar.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(edtTaxes4.getText().toString())) {
                    Snackbar.make(rootLayout,"Debes completar todos los campos",Snackbar.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(edtTaxes5.getText().toString())) {
                    Snackbar.make(rootLayout, "Debes completar todos los campos", Snackbar.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(edtCapital.getText().toString())) {
                    Snackbar.make(rootLayout, "Debes completar todos los campos", Snackbar.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(edtRateOfReturn.getText().toString())) {
                    Snackbar.make(rootLayout, "Debes completar todos los campos", Snackbar.LENGTH_SHORT).show();
                }
                else {
                    calculate();
                }
            }
        });

        txtInfo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = "Capital Inicial";
                String info = "Dinero que se usará para iniciar el proyecto";
                int img = R.drawable.capital_trabajo;

                showInfoDialog(title,info,img);
            }
        });

        txtInfo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = "Rentabilidad Mínima Exigida";
                String info = "Porncentaje de rentabilidad que como inversionista exige para ganar más que otras alternativas";
                int img = R.drawable.rentabilidad;

                showInfoDialog(title,info,img);
            }
        });

    }

    private void calculate() {

        double units1 = Double.parseDouble(edtUnits1.getText().toString());
        double units2 = Double.parseDouble(edtUnits2.getText().toString());
        double units3 = Double.parseDouble(edtUnits3.getText().toString());
        double units4 = Double.parseDouble(edtUnits4.getText().toString());
        double units5 = Double.parseDouble(edtUnits5.getText().toString());

        double price1 = Double.parseDouble(edtPrice1.getText().toString());
        double price2 = Double.parseDouble(edtPrice2.getText().toString());
        double price3 = Double.parseDouble(edtPrice3.getText().toString());
        double price4 = Double.parseDouble(edtPrice4.getText().toString());
        double price5 = Double.parseDouble(edtPrice5.getText().toString());

        double fixed_cost1 = Double.parseDouble(edtCost1.getText().toString());
        double fixed_cost2 = Double.parseDouble(edtCost2.getText().toString());
        double fixed_cost3 = Double.parseDouble(edtCost3.getText().toString());
        double fixed_cost4 = Double.parseDouble(edtCost4.getText().toString());
        double fixed_cost5 = Double.parseDouble(edtCost5.getText().toString());

        double var_cost1 = Double.parseDouble(edtCostVar1.getText().toString());
        double var_cost2 = Double.parseDouble(edtCostVar2.getText().toString());
        double var_cost3 = Double.parseDouble(edtCostVar3.getText().toString());
        double var_cost4 = Double.parseDouble(edtCostVar4.getText().toString());
        double var_cost5 = Double.parseDouble(edtCostVar5.getText().toString());

        double taxes1 = Double.parseDouble(edtTaxes1.getText().toString());
        double taxes2 = Double.parseDouble(edtTaxes2.getText().toString());
        double taxes3 = Double.parseDouble(edtTaxes3.getText().toString());
        double taxes4 = Double.parseDouble(edtTaxes4.getText().toString());
        double taxes5 = Double.parseDouble(edtTaxes5.getText().toString());

        double total_incomes1 = (units1*price1);
        double total_incomes2 = (units2*price2);
        double total_incomes3 = (units3*price3);
        double total_incomes4 = (units4*price4);
        double total_incomes5 = (units5*price5);

        double total_cost1 = (units1*var_cost1)+fixed_cost1;
        double total_cost2 = (units2*var_cost2)+fixed_cost2;
        double total_cost3 = (units3*var_cost3)+fixed_cost3;
        double total_cost4 = (units4*var_cost4)+fixed_cost4;
        double total_cost5 = (units5*var_cost5)+fixed_cost5;

        double earnings_before_taxes1 = (total_incomes1-total_cost1);
        double earnings_before_taxes2 = (total_incomes2-total_cost2);
        double earnings_before_taxes3 = (total_incomes3-total_cost3);
        double earnings_before_taxes4 = (total_incomes4-total_cost4);
        double earnings_before_taxes5 = (total_incomes5-total_cost5);

        double profit1 = earnings_before_taxes1-taxes1;
        double profit2 = earnings_before_taxes2-taxes2;
        double profit3 = earnings_before_taxes3-taxes3;
        double profit4 = earnings_before_taxes4-taxes4;
        double profit5 = earnings_before_taxes5-taxes5;

        String total_incomes1_st = decimalFormat.format(total_incomes1);
        String total_incomes2_st = decimalFormat.format(total_incomes2);
        String total_incomes3_st = decimalFormat.format(total_incomes3);
        String total_incomes4_st = decimalFormat.format(total_incomes4);
        String total_incomes5_st = decimalFormat.format(total_incomes5);

        txtIncome1.setText("S/ "+total_incomes1_st);
        txtIncome2.setText("S/ "+total_incomes2_st);
        txtIncome3.setText("S/ "+total_incomes3_st);
        txtIncome4.setText("S/ "+total_incomes4_st);
        txtIncome5.setText("S/ "+total_incomes5_st);

        String total_cost1_st = decimalFormat.format(total_cost1);
        String total_cost2_st = decimalFormat.format(total_cost2);
        String total_cost3_st = decimalFormat.format(total_cost3);
        String total_cost4_st = decimalFormat.format(total_cost4);
        String total_cost5_st = decimalFormat.format(total_cost5);

        txtOutcome1.setText("S/ "+total_cost1_st);
        txtOutcome2.setText("S/ "+total_cost2_st);
        txtOutcome3.setText("S/ "+total_cost3_st);
        txtOutcome4.setText("S/ "+total_cost4_st);
        txtOutcome5.setText("S/ "+total_cost5_st);

        String earnings_before_taxes1_st = decimalFormat.format(earnings_before_taxes1);
        String earnings_before_taxes2_st = decimalFormat.format(earnings_before_taxes2);
        String earnings_before_taxes3_st = decimalFormat.format(earnings_before_taxes3);
        String earnings_before_taxes4_st = decimalFormat.format(earnings_before_taxes4);
        String earnings_before_taxes5_st = decimalFormat.format(earnings_before_taxes5);

        txtEarningsBeforeTaxes1.setText("S/ "+earnings_before_taxes1_st);
        txtEarningsBeforeTaxes2.setText("S/ "+earnings_before_taxes2_st);
        txtEarningsBeforeTaxes3.setText("S/ "+earnings_before_taxes3_st);
        txtEarningsBeforeTaxes4.setText("S/ "+earnings_before_taxes4_st);
        txtEarningsBeforeTaxes5.setText("S/ "+earnings_before_taxes5_st);

        String profit1_st = decimalFormat.format(profit1);
        String profit2_st = decimalFormat.format(profit2);
        String profit3_st = decimalFormat.format(profit3);
        String profit4_st = decimalFormat.format(profit4);
        String profit5_st = decimalFormat.format(profit5);

        txtProfit1.setText("S/ "+profit1_st);
        txtProfit2.setText("S/ "+profit2_st);
        txtProfit3.setText("S/ "+profit3_st);
        txtProfit4.setText("S/ "+profit4_st);
        txtProfit5.setText("S/ "+profit5_st);

        double rate_of_return = Double.parseDouble(edtRateOfReturn.getText().toString())/100;
        double capital = Double.parseDouble(edtCapital.getText().toString());

        double flow1 = (profit1/Math.pow((1+rate_of_return),1));
        double flow2 = (profit2/Math.pow((1+rate_of_return),2));
        double flow3 = (profit3/Math.pow((1+rate_of_return),3));
        double flow4 = (profit4/Math.pow((1+rate_of_return),4));
        double flow5 = (profit5/Math.pow((1+rate_of_return),5));

        double total_flow = flow1+flow2+flow3+flow4+flow5;
        double pnv = total_flow-capital;

        String pnv_st = decimalFormat.format(pnv);

        if (pnv == 0.00) {
            txtViability.setText("Es probable que tu inversión no genere un retorno y tampoco pérdidas");
        }
        else if (pnv > 0.00) {
            txtViability.setText("Es recomendable invertir en el proyecto ya que tiene un valor esperado de S/ "+pnv_st);
        }
        else if (pnv < 0.00) {
            txtViability.setText("No se recomienda invertir en este proyecto ya que tiene un valor esperado de S/ "+pnv_st+" y podría generar péridas");
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
