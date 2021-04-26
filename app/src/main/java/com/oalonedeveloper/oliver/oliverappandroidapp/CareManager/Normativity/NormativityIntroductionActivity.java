package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Normativity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.oalonedeveloper.oliver.oliverappandroidapp.R;

public class NormativityIntroductionActivity extends AppCompatActivity {

    String post_key;
    Button btnTaxesNormativity,btnLabourNormativity,btnSanitaryNormativity,btnEnviromentalNormativity,btnCivilResponsability,btnNormativityResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normativity_introduction);

        post_key = getIntent().getExtras().getString("post_key");
        btnTaxesNormativity = findViewById(R.id.btnTaxesNormativity);
        btnLabourNormativity = findViewById(R.id.btnLabourNormativity);
        btnSanitaryNormativity = findViewById(R.id.btnSanitaryNormativity);
        btnEnviromentalNormativity = findViewById(R.id.btnEnviromentalNormativity);
        btnCivilResponsability = findViewById(R.id.btnCivilResponsability);
        btnNormativityResults = findViewById(R.id.btnNormativityResults);

        btnTaxesNormativity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NormativityIntroductionActivity.this, TaxesNormativityActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });

        btnLabourNormativity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NormativityIntroductionActivity.this, LabourNormativityActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });

        btnSanitaryNormativity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NormativityIntroductionActivity.this, SanitaryNormativityActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });

        btnEnviromentalNormativity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NormativityIntroductionActivity.this, EnviromentalNormativityActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });

        btnCivilResponsability.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NormativityIntroductionActivity.this, CivilResponsabilityActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });

        btnNormativityResults.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NormativityIntroductionActivity.this, NormativitySumaryActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });
    }
}
