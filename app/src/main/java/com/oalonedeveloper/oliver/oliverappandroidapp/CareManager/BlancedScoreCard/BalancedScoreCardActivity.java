package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.BlancedScoreCard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.BlancedScoreCard.AnnualOperativePlan.AnnualOperativePlanActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.BlancedScoreCard.KeyIndex.KeyIndexActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.BlancedScoreCard.StrategicMap.StrategicMapActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;

public class BalancedScoreCardActivity extends AppCompatActivity {

    CardView btnTool1,btnTool2,btnTool3;
    String post_key;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balanced_score_card);

        post_key = getIntent().getExtras().getString("post_key");

        btnTool1 = findViewById(R.id.btnTool1);
        btnTool2 = findViewById(R.id.btnTool2);
        btnTool3 = findViewById(R.id.btnTool3);

        btnTool1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BalancedScoreCardActivity.this, StrategicMapActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });
        btnTool2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BalancedScoreCardActivity.this, KeyIndexActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });
        btnTool3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BalancedScoreCardActivity.this, AnnualOperativePlanActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });
    }

}
