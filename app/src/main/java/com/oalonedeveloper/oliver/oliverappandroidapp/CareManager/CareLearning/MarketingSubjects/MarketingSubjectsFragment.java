package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.CareLearning.MarketingSubjects;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.CareLearning.MarketingSubjects.Subject1.MarketingSubject1Activity;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;

public class MarketingSubjectsFragment extends Fragment {

    Button btnSubjects1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_marketing_subjects, container, false);

        btnSubjects1 = view.findViewById(R.id.btnSubjects1);

        btnSubjects1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MarketingSubject1Activity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}
