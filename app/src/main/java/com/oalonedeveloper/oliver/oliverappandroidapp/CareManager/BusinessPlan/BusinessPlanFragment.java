package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.BusinessPlan;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.BusinessPlan.CommercialPlan.CommercialPlanActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.StrategicDirection.Vision.VisionActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;


public class BusinessPlanFragment extends Fragment {

    Button btnCommercialPlan;
    String post_key;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_business_plan, container, false);

        post_key = getActivity().getIntent().getExtras().getString("post_key");


        btnCommercialPlan = view.findViewById(R.id.btnCommercialPlan);
        btnCommercialPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CommercialPlanActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });

        return view;
    }
}
