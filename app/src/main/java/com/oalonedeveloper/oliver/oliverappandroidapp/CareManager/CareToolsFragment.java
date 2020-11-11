package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.IntructionModule.IntroductionModuleActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;


public class CareToolsFragment extends Fragment {

    CardView cardViewModule1;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_care_tools, container, false);

        cardViewModule1 = view.findViewById(R.id.cardViewModule1);

        cardViewModule1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), IntroductionModuleActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}
