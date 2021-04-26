package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Production;

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
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.ModuleQualificationActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Production.CriticalInventoryControl.CirticalInventoryControlActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Production.LeanManufacturing.LeanManufacturingActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Production.MachineryMaintenance.MachineryMaintenanceActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Production.ProductDatasheet.ProductDatasheetActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Production.ProductionCapacity.ProductionCapacityActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Production.ProductionCost.ProductionCostActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Production.ProductionOrders.ProductionOrdersManagementActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Production.QualityControl.QualityControlActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;

public class ProductionActivity extends AppCompatActivity {

    CardView btnTool1,btnTool2,btnTool3,btnTool4,btnTool5,btnTool6,btnTool7,btnTool8,btnTool0,btnTool9;
    String post_key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_production);

        post_key = getIntent().getExtras().getString("post_key");

        btnTool1 = findViewById(R.id.btnTool1);
        btnTool2 = findViewById(R.id.btnTool2);
        btnTool3 = findViewById(R.id.btnTool3);
        btnTool4 = findViewById(R.id.btnTool4);
        btnTool5 = findViewById(R.id.btnTool5);
        btnTool6 = findViewById(R.id.btnTool6);
        btnTool7 = findViewById(R.id.btnTool7);
        btnTool8 = findViewById(R.id.btnTool8);
        btnTool0 = findViewById(R.id.btnTool0);
        btnTool9 = findViewById(R.id.btnTool9);

        btnTool0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductionActivity.this, ProductionTutorialActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });
        btnTool1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductionActivity.this, ProductDatasheetActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });
        btnTool2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductionActivity.this, ProductionCostActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });
        btnTool3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductionActivity.this, ProductionCapacityActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });
        btnTool4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductionActivity.this, ProductionOrdersManagementActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });
        btnTool5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductionActivity.this, CirticalInventoryControlActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });
        btnTool6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductionActivity.this, QualityControlActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });
        btnTool7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductionActivity.this, MachineryMaintenanceActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });
        btnTool8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductionActivity.this, LeanManufacturingActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });
        btnTool9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductionActivity.this, ModuleQualificationActivity.class);
                intent.putExtra("post_key", post_key);
                intent.putExtra("path", "production");
                startActivity(intent);
            }
        });
    }

}
