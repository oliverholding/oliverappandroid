package com.oalonedeveloper.oliver.oliverappandroidapp.Companies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.CareManagerActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.OliverAppActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;

public class OliverAppForBusinessActivity extends AppCompatActivity {

    LinearLayout btMyCompanies;
    String post_key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oliver_app_for_business);

        btMyCompanies= findViewById(R.id.btMyCompanies);

        post_key = getIntent().getExtras().getString("post_key");

        btMyCompanies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OliverAppForBusinessActivity.this, CareManagerActivity.class);
                intent.putExtra("post_key",post_key);
                startActivity(intent);
            }
        });
    }
}
