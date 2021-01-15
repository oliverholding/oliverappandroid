package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Production;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Production.CriticalInventoryControl.CirticalInventoryControlActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Production.LeanManufacturing.LeanManufacturingActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Production.MachineryMaintenance.MachineryMaintenanceActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Production.ProductDatasheet.ProductDatasheetActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Production.ProductionCapacity.ProductionCapacityActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Production.ProductionCost.ProductionCostActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Production.ProductionOrders.ProductionOrdersManagementActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Production.QualityControl.QualityControlActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;

public class ProductionFragment extends Fragment {

    Button btnProductionOrders,btnProductionCost,btnDataSheets,btnQualityControl,btnProductionCapacity,btnCriticalInventory,btnMachineryMaintenance,btnLeanManufacturing;
    String post_key;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_production, container, false);

        post_key = getActivity().getIntent().getExtras().getString("post_key");

        btnProductionOrders = view.findViewById(R.id.btnProductionOrders);
        btnProductionCost = view.findViewById(R.id.btnProductionCost);
        btnDataSheets = view.findViewById(R.id.btnDataSheets);
        btnQualityControl = view.findViewById(R.id.btnQualityControl);
        btnProductionCapacity = view.findViewById(R.id.btnProductionCapacity);
        btnCriticalInventory = view.findViewById(R.id.btnCriticalInventory);
        btnMachineryMaintenance = view.findViewById(R.id.btnMachineryMaintenance);
        btnLeanManufacturing = view.findViewById(R.id.btnLeanManufacturing);

        btnProductionOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ProductionOrdersManagementActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });

        btnProductionCost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ProductionCostActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });
        btnDataSheets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ProductDatasheetActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });
        btnQualityControl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), QualityControlActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });
        btnProductionCapacity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ProductionCapacityActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });
        btnCriticalInventory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CirticalInventoryControlActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });
        btnMachineryMaintenance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MachineryMaintenanceActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });
        btnLeanManufacturing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LeanManufacturingActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });

        return view;
    }
}
