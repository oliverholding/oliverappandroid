package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.ManagementControlAndReports;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Commercialization.CommercializationActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.ManagementControlAndReports.ContractManagement.ContractManagementActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.ManagementControlAndReports.DashboardProcess.DashboardProcessActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.ManagementControlAndReports.DashboardsAndKeyindex.DashboardAndKeyIndexActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.ManagementControlAndReports.InternalControl.InternalControlActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.ManagementControlAndReports.LessonLearned.LessonLearnedActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.ManagementControlAndReports.ReportGenerator.ReportGeneratorActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.ModuleQualificationActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Simulators.CostPriceSimulator.CostPriceSimulatorActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Simulators.CostVolumeProfitAnalysys.CostVolumeProfitAnalysysActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Simulators.InterestRateSimulator.InterestRateSimulatorActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Simulators.QuatationSimulation.QuotationSimulationActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Simulators.QuotationReceivedAnalysys.QuotationReceivedAnalysysActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Simulators.SalesAndProfitProyection.SalesAndProfitProjectionActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Simulators.SimulatorsActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;

public class ManagementControlAndReportsActivity extends AppCompatActivity {

    CardView btnTool1,btnTool2,btnTool3,btnTool4,btnTool5,btnTool6,btnTool7;
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
        btnTool7 = findViewById(R.id.btnTool7);


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
                Intent intent = new Intent(ManagementControlAndReportsActivity.this, DashboardProcessActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });
        btnTool3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManagementControlAndReportsActivity.this, InternalControlActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });
        btnTool4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManagementControlAndReportsActivity.this, ContractManagementActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });
        btnTool5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManagementControlAndReportsActivity.this, LessonLearnedActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });
        btnTool6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManagementControlAndReportsActivity.this, ReportGeneratorActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });
        btnTool7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManagementControlAndReportsActivity.this, ModuleQualificationActivity.class);
                intent.putExtra("post_key", post_key);
                intent.putExtra("path", "management_control");
                startActivity(intent);
            }
        });
    }

    public static class myPostViewHolder extends RecyclerView.ViewHolder {
        View mView;
        String my_url, my_file_name;
        TextView txtFileName;
        ImageView btnDownload;

        public myPostViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            txtFileName = mView.findViewById(R.id.txtFileName);
            btnDownload = mView.findViewById(R.id.btnDownload);

        }
        public void setUrl(String url) {
            my_url = url;
        }

        public void setFile_name(String file_name) {
            my_file_name = file_name;
        }
    }
}
