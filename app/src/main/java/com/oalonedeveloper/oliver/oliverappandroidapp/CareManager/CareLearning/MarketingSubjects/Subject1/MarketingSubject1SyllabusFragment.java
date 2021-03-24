package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.CareLearning.MarketingSubjects.Subject1;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.CareLearning.VideoSubjectActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;


public class MarketingSubject1SyllabusFragment extends Fragment {

    CardView btnClass1;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_marketing_subject1_syllabus, container, false);

        btnClass1 = view.findViewById(R.id.btnClass1);

        btnClass1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), VideoSubjectActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}
