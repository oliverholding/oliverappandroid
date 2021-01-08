package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Logistic;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.storage.StorageReference;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Logistic.DemmandPlanning.DemandPlanningActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Logistic.DemmandQuantity.DemmandQuantityActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Logistic.InventoryControl.InventoryControlActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Logistic.PurchaseOrder.PurchaseOrderActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Logistic.PurchaseOrder.PurchaseOrdersListActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Logistic.Storage.StorageActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;

public class LogisticFragment extends Fragment {

    Button btnDemandPlanning,btnSupplyPlanning,btnStorage,btnInventoryControl,btnAcquisitions;
    String post_key;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_logistic, container, false);

        btnDemandPlanning = view.findViewById(R.id.btnDemandPlanning);
        btnStorage = view.findViewById(R.id.btnStorage);
        btnInventoryControl = view.findViewById(R.id.btnInventoryControl);
        btnSupplyPlanning = view.findViewById(R.id.btnSupplyPlanning);
        btnAcquisitions = view.findViewById(R.id.btnAcquisitions);
        post_key = getActivity().getIntent().getExtras().getString("post_key");

        btnSupplyPlanning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DemandPlanningActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });
        btnStorage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), StorageActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });
        btnInventoryControl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), InventoryControlActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });
        btnAcquisitions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PurchaseOrdersListActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });
        btnDemandPlanning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DemmandQuantityActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });

        return view;
    }
}
