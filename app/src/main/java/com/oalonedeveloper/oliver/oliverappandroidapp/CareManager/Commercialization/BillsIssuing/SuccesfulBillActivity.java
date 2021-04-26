package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Commercialization.BillsIssuing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.oalonedeveloper.oliver.oliverappandroidapp.R;

public class SuccesfulBillActivity extends AppCompatActivity {

    String post_key;
    Button btnGoToMyBills;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_succesful_bill);

        post_key = getIntent().getExtras().getString("post_key");
        btnGoToMyBills = findViewById(R.id.btnGoToMyBills);

        btnGoToMyBills.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SuccesfulBillActivity.this, BillsIssuingActivity.class);
                intent.putExtra("post_key",post_key);
                startActivity(intent);
                finish();
            }
        });
    }
}
