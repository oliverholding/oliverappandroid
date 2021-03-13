package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.FinancialManagement;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.FinancialManagement.AccountsNoPaid.AccountsNoPaidMonthlyActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.FinancialManagement.AccountsToPaid.AccountsToPaidMonthlyActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.FinancialManagement.Budget.BudgetActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.FinancialManagement.CashAndBanks.CashAndBanksMonthlyActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.FinancialManagement.CashFlow.CashFlowActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.FinancialManagement.FinancialStatements.FinancialStatementsActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.FinancialManagement.FixedAssets.FixedAssetsActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.FinancialManagement.RiskManagement.RiskManagementActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;

public class FinancialManagementFragment extends Fragment {

    String post_key;
    Button btnFixedAssets,btnCashAndBanks,btnAccountsNoPaid,btnAccountsToPaid,btnBudget,btnFinancialStatements,btnCashFlow,btnRiskManagement;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_financial_management, container, false);

        post_key = getActivity().getIntent().getExtras().getString("post_key");

        btnFixedAssets = view.findViewById(R.id.btnFixedAssets);
        btnCashAndBanks = view.findViewById(R.id.btnCashAndBanks);
        btnAccountsNoPaid = view.findViewById(R.id.btnAccountsNoPaid);
        btnAccountsToPaid = view.findViewById(R.id.btnAccountsToPaid);
        btnBudget = view.findViewById(R.id.btnBudget);
        btnFinancialStatements = view.findViewById(R.id.btnFinancialStatements);
        btnCashFlow = view.findViewById(R.id.btnCashFlow);
        btnRiskManagement = view.findViewById(R.id.btnRiskManagement);

        btnFixedAssets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FixedAssetsActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });

        btnCashAndBanks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CashAndBanksMonthlyActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });

        btnAccountsNoPaid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AccountsNoPaidMonthlyActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });

        btnAccountsToPaid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AccountsToPaidMonthlyActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });

        btnBudget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), BudgetActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });

        btnFinancialStatements.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FinancialStatementsActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });

        btnCashFlow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CashFlowActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });

        btnRiskManagement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), RiskManagementActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });

        return view;

    }
}
