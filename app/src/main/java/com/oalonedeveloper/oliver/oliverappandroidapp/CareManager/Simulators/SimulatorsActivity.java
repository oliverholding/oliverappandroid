package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Simulators;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Commercialization.CommercializationActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.FinancialManagement.AccountsNoPaid.AccountsNoPaidMonthlyActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.FinancialManagement.AccountsToPaid.AccountsToPaidMonthlyActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.FinancialManagement.Budget.BudgetActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.FinancialManagement.CashAndBanks.CashAndBanksMonthlyActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.FinancialManagement.CashFlow.CashFlowActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.FinancialManagement.FinanacialManagementActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.FinancialManagement.FinancialStatements.FinancialStatementsActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.FinancialManagement.FixedAssets.FixedAssetsActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.FinancialManagement.RiskManagement.RiskManagementActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.ModuleQualificationActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Simulators.CostPriceSimulator.CostPriceSimulatorActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Simulators.CostVolumeProfitAnalysys.CostVolumeProfitAnalysysActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Simulators.FinancialAppeceamentSimulator.FinancialAppeceamentSimulatorActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Simulators.FlowChart.FlowChartActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Simulators.GanttChart.WorkChargeAnnalysisActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Simulators.InterestRateSimulator.InterestRateSimulatorActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Simulators.ProjectSimulator.ProjectSimulatorActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Simulators.QuatationSimulation.QuotationSimulationActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Simulators.QuotationReceivedAnalysys.QuotationReceivedAnalysysActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Simulators.SalesAndProfitProyection.SalesAndProfitProjectionActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;

public class SimulatorsActivity extends AppCompatActivity {

    CardView btnTool1,btnTool2,btnTool3,btnTool4,btnTool5,btnTool6,btnTool7,btnTool8,btnTool9,btnTool10,btnTool11;
    String post_key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simulators);

        post_key = getIntent().getExtras().getString("post_key");

        btnTool1 = findViewById(R.id.btnTool1);
        btnTool2 = findViewById(R.id.btnTool2);
        btnTool3 = findViewById(R.id.btnTool3);
        btnTool4 = findViewById(R.id.btnTool4);
        btnTool5 = findViewById(R.id.btnTool5);
        btnTool6 = findViewById(R.id.btnTool6);
        btnTool7 = findViewById(R.id.btnTool7);
        btnTool8 = findViewById(R.id.btnTool8);
        btnTool9 = findViewById(R.id.btnTool9);
        btnTool10 = findViewById(R.id.btnTool10);
        btnTool11 = findViewById(R.id.btnTool11);

        btnTool1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SimulatorsActivity.this, CostPriceSimulatorActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });
        btnTool2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SimulatorsActivity.this, CostVolumeProfitAnalysysActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });
        btnTool3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SimulatorsActivity.this, SalesAndProfitProjectionActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });
        btnTool4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SimulatorsActivity.this, QuotationSimulationActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });
        btnTool5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SimulatorsActivity.this, QuotationReceivedAnalysysActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });
        btnTool6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SimulatorsActivity.this, InterestRateSimulatorActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });
        btnTool7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SimulatorsActivity.this, FinancialAppeceamentSimulatorActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });
        btnTool8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SimulatorsActivity.this, WorkChargeAnnalysisActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });
        btnTool9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SimulatorsActivity.this, FlowChartActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });
        btnTool10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SimulatorsActivity.this, ProjectSimulatorActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });
        btnTool11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SimulatorsActivity.this, ModuleQualificationActivity.class);
                intent.putExtra("post_key", post_key);
                intent.putExtra("path", "simulators");
                startActivity(intent);
            }
        });
    }
}
