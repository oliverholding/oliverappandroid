package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Normativity;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.PeopleManagement.JobProfiles.JobProfilesActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;


public class NormativityFragment extends Fragment {

    String post_key;
    Button btnNormativityIntroduction,btnTaxesNormativity,btnLabourNormativity,btnSanitaryrNormativity,btnEnviromentalNormativity,btnCivilResponsability,btnNormativityResults;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_normativity, container, false);

        post_key = getActivity().getIntent().getExtras().getString("post_key");
        btnNormativityIntroduction = view.findViewById(R.id.btnNormativityIntroduction);
        btnTaxesNormativity = view.findViewById(R.id.btnTaxesNormativity);
        btnLabourNormativity = view.findViewById(R.id.btnLabourNormativity);
        btnSanitaryrNormativity = view.findViewById(R.id.btnSanitaryrNormativity);
        btnEnviromentalNormativity = view.findViewById(R.id.btnEnviromentalNormativity);
        btnCivilResponsability = view.findViewById(R.id.btnCivilResponsability);
        btnNormativityResults = view.findViewById(R.id.btnNormativityResults);

        btnNormativityIntroduction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NormativityIntroductionActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });

        btnTaxesNormativity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TaxesNormativityActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });

        btnLabourNormativity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LabourNormativityActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });

        btnSanitaryrNormativity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SanitaryNormativityActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });

        btnEnviromentalNormativity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), EnviromentalNormativityActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });

        btnCivilResponsability.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CivilResponsabilityActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });

        btnNormativityResults.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NormativitySumaryActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });

        return view;
    }
}
