package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.SalesModule;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Commercialization.BillsIssuing.BillsIssuingActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.FinancialManagement.AccountsNoPaid.AccountsNoPaidMonthlyActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.FinancialManagement.CashAndBanks.CashAndBanksMonthlyActivity;

import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.SalesModule.CustomerSchedule.CustomerScheduleActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.SalesModule.MySellers.MySellersActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.SalesModule.SalesArgument.SalesArgumentActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.SalesModule.SalesFunnel.SalesFunnelActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.SalesModule.SalesProjection.SalesProjectionActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;

public class SalesModuleActivity extends AppCompatActivity {

    CardView btnTool0,btnTool1,btnTool2,btnTool3,btnTool4,btnTool00,btnTool22;
    String post_key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_module);


        post_key = getIntent().getExtras().getString("post_key");

        btnTool0 = findViewById(R.id.btnTool0);
        btnTool1 = findViewById(R.id.btnTool1);
        btnTool2 = findViewById(R.id.btnTool2);
        btnTool3 = findViewById(R.id.btnTool3);
        btnTool4 = findViewById(R.id.btnTool4);
        btnTool00 = findViewById(R.id.btnTool00);
        btnTool22 = findViewById(R.id.btnTool22);

        btnTool0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SalesModuleActivity.this, MySellersActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });
        btnTool1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SalesModuleActivity.this, SalesArgumentActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });
        btnTool2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SalesModuleActivity.this, SalesFunnelActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });
        btnTool3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SalesModuleActivity.this, CustomerScheduleActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });
        btnTool4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SalesModuleActivity.this, SalesProjectionActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });
        btnTool00.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SalesModuleActivity.this, SalesModuleTutorialActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });
        btnTool22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SalesModuleActivity.this, BillsIssuingActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });
    }


}
