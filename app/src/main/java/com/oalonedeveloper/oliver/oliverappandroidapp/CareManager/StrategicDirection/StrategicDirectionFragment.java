package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.StrategicDirection;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Production.ProductionOrders.ProductionOrdersManagementActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.StrategicDirection.FODA.FodaActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.StrategicDirection.IndustryInformation.IndustryInformationActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.StrategicDirection.LeanCanvas.LeanCanvasActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.StrategicDirection.MarketInformation.MarketInformationActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.StrategicDirection.Mission.MissionActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.StrategicDirection.Values.OrganizationalValuesActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.StrategicDirection.Vision.VisionActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;


public class StrategicDirectionFragment extends Fragment {

    Button btnVision,btnMission,btnValues,btnLeanCanvas,btnIndustryInformation,btnMarketInformation,btnFoda;
    String post_key;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_strategic_direction, container, false);

        post_key = getActivity().getIntent().getExtras().getString("post_key");

        btnVision = view.findViewById(R.id.btnVision);
        btnMission = view.findViewById(R.id.btnMission);
        btnValues = view.findViewById(R.id.btnValues);
        btnLeanCanvas = view.findViewById(R.id.btnLeanCanvas);
        btnIndustryInformation = view.findViewById(R.id.btnIndustryInformation);
        btnMarketInformation = view.findViewById(R.id.btnMarketInformation);
        btnFoda = view.findViewById(R.id.btnFoda);

        btnVision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), VisionActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });

        btnMission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MissionActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });
        btnValues.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), OrganizationalValuesActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });

        btnLeanCanvas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LeanCanvasActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });

        btnIndustryInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), IndustryInformationActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });

        btnMarketInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MarketInformationActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });

        btnFoda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FodaActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });

        return view;
    }
}
