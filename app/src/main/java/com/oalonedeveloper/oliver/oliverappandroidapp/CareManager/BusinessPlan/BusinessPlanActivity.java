package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.BusinessPlan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.BusinessPlan.CommercialPlan.CommercialPlanActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.BusinessPlan.ContingencyPlan.ContingencyPlanActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.BusinessPlan.ExecutiveSumary.ExecutiveSumaryActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.BusinessPlan.FinancialPlan.FinancialPlanActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.BusinessPlan.HumanResourcesPlan.HumanResourcesPlanActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.BusinessPlan.OperationPlan.OperationPlanActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Commercialization.CommercializationActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.ModuleQualificationActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;

public class BusinessPlanActivity extends AppCompatActivity {

    CardView btnTool1,btnTool2,btnTool3,btnTool4,btnTool5,btnTool6,btnTool7;
    String post_key;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_plan);

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
                Intent intent = new Intent(BusinessPlanActivity.this, CommercialPlanActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });
        btnTool2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BusinessPlanActivity.this, OperationPlanActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });
        btnTool3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BusinessPlanActivity.this, HumanResourcesPlanActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });
        btnTool4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BusinessPlanActivity.this, FinancialPlanActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });
        btnTool5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BusinessPlanActivity.this, ExecutiveSumaryActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });
        btnTool6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BusinessPlanActivity.this, ContingencyPlanActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });
        btnTool7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BusinessPlanActivity.this, ModuleQualificationActivity.class);
                intent.putExtra("post_key", post_key);
                intent.putExtra("path", "business_plan");
                startActivity(intent);
            }
        });
    }


}
