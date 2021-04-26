package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.StrategicDirection;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Commercialization.CommercializationActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.ModuleQualificationActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.StrategicDirection.BusinessModel.BusinessModelActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.StrategicDirection.FODA.FodaActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.StrategicDirection.IndustryInformation.IndustryInformationActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.StrategicDirection.LeanCanvas.LeanCanvasActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.StrategicDirection.MarketInformation.MarketInformationActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.StrategicDirection.Mission.MissionActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.StrategicDirection.OrganizationalLearning.OrganizationLearningActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.StrategicDirection.StrategicMatrix.StrategicMatrixActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.StrategicDirection.Values.OrganizationalValuesActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.StrategicDirection.Vision.VisionActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;

public class StrategicDirectionActivity extends AppCompatActivity {

    CardView btnTool1,btnTool2,btnTool3,btnTool4,btnTool5,btnTool6,btnTool7,btnTool8,btnTool9,btnTool10,btnTool11,btnTool12;
    String post_key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_strategic_direction);

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
        btnTool12 = findViewById(R.id.btnTool12);

        btnTool1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StrategicDirectionActivity.this, VisionActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });
        btnTool2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StrategicDirectionActivity.this, MissionActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });
        btnTool3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StrategicDirectionActivity.this, OrganizationalValuesActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });
        btnTool4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StrategicDirectionActivity.this, LeanCanvasActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });
        btnTool5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StrategicDirectionActivity.this, BusinessModelActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });
        btnTool6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StrategicDirectionActivity.this, IndustryInformationActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });
        btnTool7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StrategicDirectionActivity.this, MarketInformationActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });
        btnTool8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StrategicDirectionActivity.this, FodaActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });
        btnTool9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StrategicDirectionActivity.this, StrategicMatrixActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });
        btnTool10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StrategicDirectionActivity.this, OrganizationLearningActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });
        btnTool11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://oliver.com.pe/oliver-manager-login/user/company-reports/"+post_key+"/company"));
                startActivity(browserIntent);
            }
        });
        btnTool12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StrategicDirectionActivity.this, ModuleQualificationActivity.class);
                intent.putExtra("post_key", post_key);
                intent.putExtra("path", "strategic");
                startActivity(intent);
            }
        });
    }


}
