package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.PeopleManagement;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.PeopleManagement.Evaluation360.JobProfilesEvaluationsActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.PeopleManagement.JobProfiles.JobProfilesActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.PeopleManagement.PersonalFiles.JobProfileFilesActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.PeopleManagement.PersonalFiles.PersonalFilesActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.PeopleManagement.RoasterAndPerfomance.JobProfileRoasterActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.PeopleManagement.RoasterAndPerfomance.RoasterAndPerfomanceActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.PeopleManagement.WorkersPayroll.WorkerPayrollProfilesActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.PeopleManagement.WorkersPayroll.WorkersPayrollActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;

public class PeopleManagementFragment extends Fragment {

    Button btnJobProfile,btnPersonalFiles,btnPayRoll,btnRoasterAndPerfomance,btnEvaluations;
    String post_key;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_people_management, container, false);

        post_key = getActivity().getIntent().getExtras().getString("post_key");

        btnJobProfile = view.findViewById(R.id.btnJobProfile);
        btnPersonalFiles = view.findViewById(R.id.btnPersonalFiles);
        btnPayRoll = view.findViewById(R.id.btnPayRoll);
        btnRoasterAndPerfomance = view.findViewById(R.id.btnRoasterAndPerfomance);
        btnEvaluations = view.findViewById(R.id.btnEvaluations);

        btnJobProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), JobProfilesActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });
        btnPersonalFiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), JobProfileFilesActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });
        btnPayRoll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), WorkerPayrollProfilesActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });
        btnRoasterAndPerfomance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), JobProfileRoasterActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });
        btnEvaluations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), JobProfilesEvaluationsActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });

        return view;
    }
}
