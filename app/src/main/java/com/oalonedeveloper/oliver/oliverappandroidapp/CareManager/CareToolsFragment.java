package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.BusinessOportunitiesModule.BusinessOportunitiesActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.IntructionModule.IntroductionModuleActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.ValueProposalModule.ValueProposalActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;


public class CareToolsFragment extends Fragment {

    CardView cardViewModule1,cardViewModule2,cardViewModule3;
    String post_key;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_care_tools, container, false);

        post_key = getActivity().getIntent().getExtras().getString("post_key");

        cardViewModule1 = view.findViewById(R.id.cardViewModule1);
        cardViewModule2 = view.findViewById(R.id.cardViewModule2);
        cardViewModule3 = view.findViewById(R.id.cardViewModule3);

        cardViewModule1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), IntroductionModuleActivity.class);
                startActivity(intent);
            }
        });

        cardViewModule2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), BusinessOportunitiesActivity.class);
                intent.putExtra("post_key",post_key);
                startActivity(intent);
            }
        });

        cardViewModule3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ValueProposalActivity.class);
                intent.putExtra("post_key",post_key);
                startActivity(intent);
            }
        });

        return view;
    }
}
