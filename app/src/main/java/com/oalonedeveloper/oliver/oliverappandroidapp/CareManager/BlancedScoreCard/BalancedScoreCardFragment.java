package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.BlancedScoreCard;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.StrategicDirection.Vision.VisionActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;


public class BalancedScoreCardFragment extends Fragment {

    String post_key;
    Button btnStrategicMap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_balanced_score_card, container, false);

        post_key = getActivity().getIntent().getExtras().getString("post_key");

        btnStrategicMap = view.findViewById(R.id.btnStrategicMap);

        btnStrategicMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), StrategicMapActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });

        return view;
    }
}
