package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Production;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Production.ProductionOrders.ProductionOrdersManagementActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;

public class ProductionFragment extends Fragment {

    Button btnProductionOrders;
    String post_key;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_production, container, false);

        post_key = getActivity().getIntent().getExtras().getString("post_key");

        btnProductionOrders = view.findViewById(R.id.btnProductionOrders);

        btnProductionOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ProductionOrdersManagementActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });

        return view;
    }
}
