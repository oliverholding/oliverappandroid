package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.ManagementControlAndReports;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.ManagementControlAndReports.DashboardsAndKeyindex.DashboardAndKeyIndexActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Simulators.CostPriceSimulator.CostPriceSimulatorActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Simulators.CostVolumeProfitAnalysys.CostVolumeProfitAnalysysActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Simulators.InterestRateSimulator.InterestRateSimulatorActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Simulators.QuatationSimulation.QuotationSimulationActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Simulators.QuotationReceivedAnalysys.QuotationReceivedAnalysysActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Simulators.SalesAndProfitProyection.SalesAndProfitProjectionActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Simulators.SimulatorsActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;

public class ManagementControlAndReportsActivity extends AppCompatActivity {

    CardView btnTool1,btnTool2,btnTool3,btnTool4,btnTool5,btnTool6;
    String post_key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_management_control_and_reports);

        post_key = getIntent().getExtras().getString("post_key");

        btnTool1 = findViewById(R.id.btnTool1);
        btnTool2 = findViewById(R.id.btnTool2);
        btnTool3 = findViewById(R.id.btnTool3);
        btnTool4 = findViewById(R.id.btnTool4);
        btnTool5 = findViewById(R.id.btnTool5);
        btnTool6 = findViewById(R.id.btnTool6);


        btnTool1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManagementControlAndReportsActivity.this, DashboardAndKeyIndexActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });
        btnTool2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btnTool3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btnTool4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btnTool5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btnTool6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               
            }
        });
    }
}
