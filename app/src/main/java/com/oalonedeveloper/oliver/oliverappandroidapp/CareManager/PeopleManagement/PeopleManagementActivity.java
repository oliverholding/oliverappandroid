package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.PeopleManagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.PeopleManagement.Evaluation360.JobProfilesEvaluationsActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.PeopleManagement.JobProfiles.JobProfilesActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.PeopleManagement.PersonalFiles.JobProfileFilesActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.PeopleManagement.RoasterAndPerfomance.JobProfileRoasterActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.PeopleManagement.WorkersPayroll.WorkerPayrollProfilesActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;

public class PeopleManagementActivity extends AppCompatActivity {

    CardView btnTool1,btnTool2,btnTool3,btnTool4,btnTool5;
    String post_key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people_management);

        post_key = getIntent().getExtras().getString("post_key");

        btnTool1 = findViewById(R.id.btnTool1);
        btnTool2 = findViewById(R.id.btnTool2);
        btnTool3 = findViewById(R.id.btnTool3);
        btnTool4 = findViewById(R.id.btnTool4);
        btnTool5 = findViewById(R.id.btnTool5);

        btnTool1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PeopleManagementActivity.this, JobProfilesActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });
        btnTool2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PeopleManagementActivity.this, JobProfileFilesActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });
        btnTool3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PeopleManagementActivity.this, WorkerPayrollProfilesActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });
        btnTool4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PeopleManagementActivity.this, JobProfileRoasterActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });
        btnTool5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PeopleManagementActivity.this,  JobProfilesEvaluationsActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });
    }

}
