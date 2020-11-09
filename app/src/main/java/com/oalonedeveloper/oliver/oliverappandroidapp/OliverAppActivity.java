package com.oalonedeveloper.oliver.oliverappandroidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.oalonedeveloper.oliver.oliverappandroidapp.Companies.MyCompaniesActivity;

public class OliverAppActivity extends AppCompatActivity {

    LinearLayout btMyCompanies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oliver_app);

        btMyCompanies= findViewById(R.id.btMyCompanies);

        btMyCompanies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OliverAppActivity.this, MyCompaniesActivity.class);
                startActivity(intent);
            }
        });


    }
}