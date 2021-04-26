package com.oalonedeveloper.oliver.oliverappandroidapp.Companies;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.oalonedeveloper.oliver.oliverappandroidapp.InformalCompanies.AddInformalCompanyActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;


public class NonCompaniesFragment extends Fragment {

    Button btnAddCompany;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_non_companies, container, false);

        btnAddCompany = view.findViewById(R.id.btnAddCompany);

        btnAddCompany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),RegisterCompanyActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    private void showCompanyTypeDialog() {
        final AlertDialog dialog = new AlertDialog.Builder(getActivity()).create();

        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View finance_method = inflater.inflate(R.layout.company_type_selection_dialog,null);

        CardView btnInformalCompany,btnCompany;

        btnInformalCompany = finance_method.findViewById(R.id.btnInformalCompany);
        btnCompany = finance_method.findViewById(R.id.btnCompany);

        btnInformalCompany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddInformalCompanyActivity.class);
                startActivity(intent);
                dialog.dismiss();
            }
        });

        btnCompany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddCompanyActivity.class);
                startActivity(intent);
                dialog.dismiss();
            }
        });

        dialog.setView(finance_method);
        dialog.show();
    }
}
