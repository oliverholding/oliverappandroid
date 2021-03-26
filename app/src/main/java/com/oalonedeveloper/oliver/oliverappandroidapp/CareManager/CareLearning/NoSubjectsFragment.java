package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.CareLearning;

import android.graphics.Color;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.oalonedeveloper.oliver.oliverappandroidapp.R;


public class NoSubjectsFragment extends Fragment {

    Button btnExplore;
    Fragment fragment2;
    CardView tab1,tab2,tab3;
    LinearLayout linearLayout1,linearLayout2,linearLayout3;
    TextView txtText1,txtText2,txtText3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_no_subjects, container, false);

        btnExplore = view.findViewById(R.id.btnExplore);

        fragment2 = new SubjectsFragment();


        btnExplore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.contentFragment,fragment2).commit();
                
            }
        });

        return view;
    }
}
