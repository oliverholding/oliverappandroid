package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.IntructionModule;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.oalonedeveloper.oliver.oliverappandroidapp.R;


public class IntroductionModuleFragment extends Fragment {

    ImageButton btnVideo1,btnVideo2,btnVideo3,btnVideo4,btnVideo5,btnVideo6,btnFile1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_introduction_module, container, false);

        btnVideo1 = view.findViewById(R.id.btnVideo1);
        btnVideo2 = view.findViewById(R.id.btnVideo2);
        btnVideo3= view.findViewById(R.id.btnVideo3);
        btnVideo4 = view.findViewById(R.id.btnVideo4);
        btnVideo5 = view.findViewById(R.id.btnVideo5);
        btnVideo6 = view.findViewById(R.id.btnVideo6);
        btnFile1 = view.findViewById(R.id.btnFile1);

        btnVideo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), IntroductionModuleActivity.class);
                intent.putExtra("FRAGMENT_ID", 1);
                startActivity(intent);
            }
        });
        btnVideo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), IntroductionModuleActivity.class);
                intent.putExtra("FRAGMENT_ID", 2);
                startActivity(intent);
            }
        });
        btnVideo3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), IntroductionModuleActivity.class);
                intent.putExtra("FRAGMENT_ID", 3);
                startActivity(intent);
            }
        });
        btnVideo4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), IntroductionModuleActivity.class);
                intent.putExtra("FRAGMENT_ID", 4);
                startActivity(intent);
            }
        });
        btnVideo5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), IntroductionModuleActivity.class);
                intent.putExtra("FRAGMENT_ID", 5);
                startActivity(intent);
            }
        });
        btnVideo6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), IntroductionModuleActivity.class);
                intent.putExtra("FRAGMENT_ID", 6);
                startActivity(intent);
            }
        });
        btnFile1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), IntroductionModuleActivity.class);
                intent.putExtra("FRAGMENT_ID", 7);
                startActivity(intent);
            }
        });



        return view;
    }
}
