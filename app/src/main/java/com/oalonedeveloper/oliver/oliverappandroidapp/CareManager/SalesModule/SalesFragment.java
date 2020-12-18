package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.SalesModule;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Commercialization.CommercializationActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Commercialization.CommercializationToolActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Commercialization.ProductMarketMatrix.ProductMarketMatrixActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.SalesModule.CustomerSchedule.CustomerScheduleActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.SalesModule.MySellers.MySellersActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.SalesModule.SalesArgument.SalesArgumentActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.SalesModule.SalesFunnel.SalesFunnelActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;


public class SalesFragment extends Fragment {

    ImageButton btnTest1,btnCap1,btnVideo1,btnVideo2,btnVideo3,btnVideo4,btnVideo5,btnVideo6,btnFile1,btnTool1,btnTool2,btnTool3,btnTool4,btnTool5,btnTool6,btnTest2;
    Button btnSalesArgument,btnSalesFunnel,btnTutorial,btnScheduleDate,btnSellers;
    String post_key;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sales, container, false);

        btnTest1 = view.findViewById(R.id.btnTest1);
        btnCap1 = view.findViewById(R.id.btnCap1);
        btnVideo1 = view.findViewById(R.id.btnVideo1);
        btnVideo2 = view.findViewById(R.id.btnVideo2);
        btnVideo3 = view.findViewById(R.id.btnVideo3);
        btnVideo4 = view.findViewById(R.id.btnVideo4);
        btnVideo5 = view.findViewById(R.id.btnVideo5);
        btnVideo6 = view.findViewById(R.id.btnVideo6);
        btnFile1 = view.findViewById(R.id.btnFile1);
        btnTool1 = view.findViewById(R.id.btnTool1);
        btnTool2 = view.findViewById(R.id.btnTool2);
        btnTool3 = view.findViewById(R.id.btnTool3);
        btnTool4 = view.findViewById(R.id.btnTool4);
        btnTool5 = view.findViewById(R.id.btnTool5);
        btnTool6 = view.findViewById(R.id.btnTool6);
        btnTest2 = view.findViewById(R.id.btnTest2);
        btnSalesArgument = view.findViewById(R.id.btnSalesArgument);
        btnSalesFunnel = view.findViewById(R.id.btnSalesFunnel);
        btnTutorial = view.findViewById(R.id.btnTutorial);
        btnScheduleDate = view.findViewById(R.id.btnScheduleDate);
        btnSellers = view.findViewById(R.id.btnSellers);

        post_key = getActivity().getIntent().getExtras().getString("post_key");

        btnSalesArgument.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SalesArgumentActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });
        btnSalesFunnel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SalesFunnelActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });
        btnScheduleDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CustomerScheduleActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });
        btnSellers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MySellersActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });




        btnTutorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SalesTutorialActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });

        btnTest1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SalesToolsActivity.class);
                intent.putExtra("FRAGMENT_ID", 0);
                startActivity(intent);
            }
        });
        btnCap1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SalesModuleActivity.class);
                intent.putExtra("FRAGMENT_ID", 1);
                startActivity(intent);
            }
        });
        btnVideo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SalesModuleActivity.class);
                intent.putExtra("FRAGMENT_ID", 2);
                startActivity(intent);
            }
        });
        btnVideo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SalesModuleActivity.class);
                intent.putExtra("FRAGMENT_ID", 3);
                startActivity(intent);
            }
        });
        btnVideo3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SalesModuleActivity.class);
                intent.putExtra("FRAGMENT_ID", 4);
                startActivity(intent);
            }
        });
        btnVideo4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SalesModuleActivity.class);
                intent.putExtra("FRAGMENT_ID", 5);
                startActivity(intent);
            }
        });
        btnVideo5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SalesModuleActivity.class);
                intent.putExtra("FRAGMENT_ID", 6);
                startActivity(intent);
            }
        });
        btnVideo6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SalesModuleActivity.class);
                intent.putExtra("FRAGMENT_ID", 7);
                startActivity(intent);
            }
        });
        btnFile1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SalesModuleActivity.class);
                intent.putExtra("FRAGMENT_ID", 8);
                startActivity(intent);
            }
        });

        btnTool1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SalesToolsActivity.class);
                intent.putExtra("FRAGMENT_ID", 1);
                startActivity(intent);
            }
        });
        btnTool2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SalesToolsActivity.class);
                intent.putExtra("FRAGMENT_ID", 2);
                startActivity(intent);
            }
        });
        btnTool3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SalesToolsActivity.class);
                intent.putExtra("FRAGMENT_ID", 3);
                startActivity(intent);
            }
        });
        btnTool4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SalesToolsActivity.class);
                intent.putExtra("FRAGMENT_ID", 4);
                startActivity(intent);
            }
        });
        btnTool5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SalesToolsActivity.class);
                intent.putExtra("FRAGMENT_ID", 5);
                startActivity(intent);
            }
        });
        btnTool6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SalesToolsActivity.class);
                intent.putExtra("FRAGMENT_ID", 6);
                startActivity(intent);
            }
        });

        btnTest2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SalesToolsActivity.class);
                intent.putExtra("FRAGMENT_ID", 7);
                startActivity(intent);
            }
        });

        return view;
    }
}
