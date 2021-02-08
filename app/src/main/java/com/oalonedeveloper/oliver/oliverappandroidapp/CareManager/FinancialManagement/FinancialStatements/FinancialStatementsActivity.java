package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.FinancialManagement.FinancialStatements;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.FinancialManagement.FinancialStatements.FinancialSituation.FinancialSituationActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.FinancialManagement.FinancialStatements.StatementOfIncomes.StatementOfIncomesActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;

public class FinancialStatementsActivity extends AppCompatActivity {

    Button btnFinancialSituation,btnStatementOfIncomes;
    String post_key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_financial_statements);

        btnFinancialSituation = findViewById(R.id.btnFinancialSituation);
        btnStatementOfIncomes = findViewById(R.id.btnStatementOfIncomes);
        post_key = getIntent().getExtras().getString("post_key");

        btnFinancialSituation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FinancialStatementsActivity.this, FinancialSituationActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });

        btnStatementOfIncomes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FinancialStatementsActivity.this, StatementOfIncomesActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });
    }
}
