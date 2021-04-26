package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Logistic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Commercialization.CommercializationActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Commercialization.CommercializationTutorial.CommercializationTutorialActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.FinancialManagement.AccountsNoPaid.AccountsNoPaidMonthlyActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.FinancialManagement.AccountsToPaid.AccountsToPaidMonthlyActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.FinancialManagement.Budget.BudgetActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.FinancialManagement.CashAndBanks.CashAndBanksMonthlyActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.FinancialManagement.CashFlow.CashFlowActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.FinancialManagement.FinanacialManagementActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.FinancialManagement.FinancialStatements.FinancialStatementsActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.FinancialManagement.FixedAssets.FixedAssetsActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.FinancialManagement.RiskManagement.RiskManagementActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Logistic.DemmandPlanning.DemandPlanningActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Logistic.DemmandQuantity.DemmandQuantityActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Logistic.Dispatch.DispatchActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Logistic.InventoryControl.InventoryControlActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Logistic.OrderProcessing.OrderProcessingListActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Logistic.Outsoursing.OutsoursingActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Logistic.PurchaseOrder.PurchaseOrdersListActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Logistic.Storage.StorageActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Logistic.SupplierEvaluation.SupplierEvaluationActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.ModuleQualificationActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;

public class LogisticActivity extends AppCompatActivity {

    CardView btnTool1,btnTool2,btnTool3,btnTool4,btnTool5,btnTool6,btnTool7,btnTool8,btnTool9,btnTool0,btnTool10;
    String post_key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logistic);

        post_key = getIntent().getExtras().getString("post_key");

        btnTool0 = findViewById(R.id.btnTool0);
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

        btnTool0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogisticActivity.this, LogisticTutorialActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });
        btnTool1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogisticActivity.this, DemmandQuantityActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });
        btnTool2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogisticActivity.this, DemandPlanningActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });
        btnTool3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogisticActivity.this, PurchaseOrdersListActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });
        btnTool4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogisticActivity.this, SupplierEvaluationActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });
        btnTool5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogisticActivity.this, OutsoursingActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });
        btnTool6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogisticActivity.this, StorageActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });
        btnTool7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogisticActivity.this, InventoryControlActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });
        btnTool8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogisticActivity.this, OrderProcessingListActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });
        btnTool9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogisticActivity.this, DispatchActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });
        btnTool10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogisticActivity.this, ModuleQualificationActivity.class);
                intent.putExtra("post_key", post_key);
                intent.putExtra("path", "logistic");
                startActivity(intent);
            }
        });

    }


}
