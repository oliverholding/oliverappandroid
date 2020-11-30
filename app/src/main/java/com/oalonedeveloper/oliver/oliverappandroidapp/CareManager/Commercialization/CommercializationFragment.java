package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Commercialization;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.NewProductsOrServices.NewProductsOrServicesActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.NewProductsOrServices.NewProductsOrServicesToolsActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;

public class CommercializationFragment extends Fragment {

    ImageButton btnTest1,btnCap1,btnVideo1,btnVideo2,btnVideo3,btnVideo4,btnVideo5,btnVideo6,btnFile1,btnTool1,btnTool2,btnTool3,btnTool4,btnTool5,btnTool6,btnTest2;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_commercialization, container, false);

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

        btnTest1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CommercializationToolActivity.class);
                intent.putExtra("FRAGMENT_ID", 0);
                startActivity(intent);
            }
        });
        btnCap1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CommercializationActivity.class);
                intent.putExtra("FRAGMENT_ID", 1);
                startActivity(intent);
            }
        });
        btnVideo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CommercializationActivity.class);
                intent.putExtra("FRAGMENT_ID", 2);
                startActivity(intent);
            }
        });
        btnVideo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CommercializationActivity.class);
                intent.putExtra("FRAGMENT_ID", 3);
                startActivity(intent);
            }
        });
        btnVideo3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CommercializationActivity.class);
                intent.putExtra("FRAGMENT_ID", 4);
                startActivity(intent);
            }
        });
        btnVideo4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CommercializationActivity.class);
                intent.putExtra("FRAGMENT_ID", 5);
                startActivity(intent);
            }
        });
        btnVideo5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CommercializationActivity.class);
                intent.putExtra("FRAGMENT_ID", 6);
                startActivity(intent);
            }
        });
        btnVideo6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CommercializationActivity.class);
                intent.putExtra("FRAGMENT_ID", 7);
                startActivity(intent);
            }
        });
        btnFile1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CommercializationActivity.class);
                intent.putExtra("FRAGMENT_ID", 8);
                startActivity(intent);
            }
        });

        btnTool1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CommercializationToolActivity.class);
                intent.putExtra("FRAGMENT_ID", 1);
                startActivity(intent);
            }
        });
        btnTool2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CommercializationToolActivity.class);
                intent.putExtra("FRAGMENT_ID", 2);
                startActivity(intent);
            }
        });
        btnTool3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CommercializationToolActivity.class);
                intent.putExtra("FRAGMENT_ID", 3);
                startActivity(intent);
            }
        });
        btnTool4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CommercializationToolActivity.class);
                intent.putExtra("FRAGMENT_ID", 4);
                startActivity(intent);
            }
        });
        btnTool5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CommercializationToolActivity.class);
                intent.putExtra("FRAGMENT_ID", 5);
                startActivity(intent);
            }
        });
        btnTool6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CommercializationToolActivity.class);
                intent.putExtra("FRAGMENT_ID", 6);
                startActivity(intent);
            }
        });

        btnTest2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CommercializationToolActivity.class);
                intent.putExtra("FRAGMENT_ID", 7);
                startActivity(intent);
            }
        });

        return view;
    }
}
