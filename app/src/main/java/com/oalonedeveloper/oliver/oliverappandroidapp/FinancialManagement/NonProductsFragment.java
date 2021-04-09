package com.oalonedeveloper.oliver.oliverappandroidapp.FinancialManagement;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.oalonedeveloper.oliver.oliverappandroidapp.R;


public class NonProductsFragment extends Fragment {

    Button btnExplore;
    Fragment fragment1;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_non_products, container, false);

        fragment1 = new FinancialProductsFragment();
        btnExplore = view.findViewById(R.id.btnExplore);

        btnExplore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.contentFragment,fragment1).commit();
            }
        });

        return view;
    }
}
