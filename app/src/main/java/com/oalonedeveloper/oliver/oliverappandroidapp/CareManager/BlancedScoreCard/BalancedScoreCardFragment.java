package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.BlancedScoreCard;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.BlancedScoreCard.AnnualOperativePlan.AnnualOperativePlanActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.BlancedScoreCard.KeyIndex.KeyIndexActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.BlancedScoreCard.StrategicMap.StrategicMapActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;


public class BalancedScoreCardFragment extends Fragment {

    String post_key;
    Button btnStrategicMap,btnKeyIndex,btnAnnualOperativePlan;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_balanced_score_card, container, false);

        post_key = getActivity().getIntent().getExtras().getString("post_key");

        btnStrategicMap = view.findViewById(R.id.btnStrategicMap);
        btnKeyIndex = view.findViewById(R.id.btnKeyIndex);
        btnAnnualOperativePlan = view.findViewById(R.id.btnAnnualOperativePlan);

        btnStrategicMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), StrategicMapActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });

        btnKeyIndex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), KeyIndexActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });

        btnAnnualOperativePlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AnnualOperativePlanActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });

        return view;
    }
}
