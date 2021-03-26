package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Commercialization;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.BlancedScoreCard.AnnualOperativePlan.AnnualOperativePlanActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.BlancedScoreCard.BalancedScoreCardActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.BlancedScoreCard.KeyIndex.KeyIndexActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.BlancedScoreCard.StrategicMap.StrategicMapActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Commercialization.BillsIssuing.BillsIssuingActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Commercialization.CommercializationTutorial.CommercializationTutorialActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Commercialization.CreditsAndTreasury.CreditAndTreasuryActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Commercialization.CustomerSupport.CustomerSuportActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Commercialization.NewMatrix10.NewMatrix10Activity;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;

public class CommercializationActivity extends AppCompatActivity {

    CardView btnTool1,btnTool2,btnTool3,btnTool4,btnTool0;
    String post_key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commercialization);

        post_key = getIntent().getExtras().getString("post_key");

        btnTool0 = findViewById(R.id.btnTool0);
        btnTool1 = findViewById(R.id.btnTool1);
        btnTool2 = findViewById(R.id.btnTool2);
        btnTool3 = findViewById(R.id.btnTool3);
        btnTool4 = findViewById(R.id.btnTool4);

        btnTool0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CommercializationActivity.this, CommercializationTutorialActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });
        btnTool1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CommercializationActivity.this, NewMatrix10Activity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });
        btnTool2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CommercializationActivity.this, BillsIssuingActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });
        btnTool3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CommercializationActivity.this, CreditAndTreasuryActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });
        btnTool4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CommercializationActivity.this, CustomerSuportActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });
    }

}
