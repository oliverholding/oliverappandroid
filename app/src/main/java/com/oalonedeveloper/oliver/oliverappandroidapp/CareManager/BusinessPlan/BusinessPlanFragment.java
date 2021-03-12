package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.BusinessPlan;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.BusinessPlan.CommercialPlan.CommercialPlanActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.BusinessPlan.ContingencyPlan.ContingencyPlanActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.BusinessPlan.ExecutiveSumary.ExecutiveSumaryActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.BusinessPlan.FinancialPlan.FinancialPlanActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.BusinessPlan.HumanResourcesPlan.HumanResourcesPlanActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.BusinessPlan.OperationPlan.OperationPlanActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.StrategicDirection.Vision.VisionActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;


public class BusinessPlanFragment extends Fragment {

    Button btnCommercialPlan,btnOperationPlan,btnHumanResourcesPlan,btnFinancialPlan,btnExecutivePlan,btnContingencyPlan;
    String post_key;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_business_plan, container, false);

        post_key = getActivity().getIntent().getExtras().getString("post_key");


        btnCommercialPlan = view.findViewById(R.id.btnCommercialPlan);
        btnOperationPlan = view.findViewById(R.id.btnOperationPlan);
        btnHumanResourcesPlan = view.findViewById(R.id.btnHumanResourcesPlan);
        btnFinancialPlan = view.findViewById(R.id.btnFinancialPlan);
        btnExecutivePlan = view.findViewById(R.id.btnExecutivePlan);
        btnContingencyPlan = view.findViewById(R.id.btnContingencyPlan);
        btnCommercialPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CommercialPlanActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });
        btnOperationPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), OperationPlanActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });
        btnHumanResourcesPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), HumanResourcesPlanActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });
        btnFinancialPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FinancialPlanActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });
        btnExecutivePlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ExecutiveSumaryActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });
        btnContingencyPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ContingencyPlanActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });

        return view;
    }
}
