package com.oalonedeveloper.oliver.oliverappandroidapp.Companies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.oalonedeveloper.oliver.oliverappandroidapp.R;

public class MyCompaniesActivity extends AppCompatActivity {

    Button btnAddCompany;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_companies);

        btnAddCompany = findViewById(R.id.btnAddCompany);

        btnAddCompany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyCompaniesActivity.this, AddCompanyActivity.class);
                startActivity(intent);
            }
        });
    }
}