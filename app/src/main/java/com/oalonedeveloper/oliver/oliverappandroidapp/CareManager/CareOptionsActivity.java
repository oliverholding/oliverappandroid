package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.CareLearning.CareLearningActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.Companies.MyCompaniesActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.PlatformSelectionActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;

public class CareOptionsActivity extends AppCompatActivity {

    CardView btnCompanies,btnLearning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_care_options);

        btnCompanies = findViewById(R.id.btnCompanies);
        btnLearning = findViewById(R.id.btnLearning);

        btnCompanies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CareOptionsActivity.this, MyCompaniesActivity.class);
                startActivity(intent);
            }
        });

        btnLearning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CareOptionsActivity.this, CareLearningActivity.class);
                startActivity(intent);
            }
        });

    }
}
