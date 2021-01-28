package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.FinancialManagement;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.FinancialManagement.FixedAssets.FixedAssetsActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;

public class FinancialManagementFragment extends Fragment {

    String post_key;
    Button btnFixedAssets;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_financial_management, container, false);

        post_key = getActivity().getIntent().getExtras().getString("post_key");

        btnFixedAssets = view.findViewById(R.id.btnFixedAssets);

        btnFixedAssets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FixedAssetsActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });

        return view;

    }
}
