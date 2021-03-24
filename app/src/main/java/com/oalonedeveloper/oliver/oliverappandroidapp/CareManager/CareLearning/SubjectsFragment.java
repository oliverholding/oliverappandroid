package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.CareLearning;

import android.graphics.Color;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.CareLearning.BusinessSubjects.BusinessSubjectsFragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.CareLearning.MarketingSubjects.MarketingSubjectsFragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.ManagementControlAndReports.DashboardsAndKeyindex.FinanceIndexragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.ManagementControlAndReports.DashboardsAndKeyindex.GeneralIndexFragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.ManagementControlAndReports.DashboardsAndKeyindex.LogisticIndexFragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.ManagementControlAndReports.DashboardsAndKeyindex.NormativityIndexFragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.ManagementControlAndReports.DashboardsAndKeyindex.PoepleIndexFragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.ManagementControlAndReports.DashboardsAndKeyindex.ProductionIndexFragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;


public class SubjectsFragment extends Fragment {

    CardView tab1,tab2,tab3,tab4;
    LinearLayout linearLayout1,linearLayout2,linearLayout3,linearLayout4;
    TextView txtText1,txtText2,txtText3,txtText4;
    Fragment fragment1,fragment2,fragment3,fragment4;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_subjects, container, false);

        tab1 = view.findViewById(R.id.tab1);
        tab2 = view.findViewById(R.id.tab2);
        tab3 = view.findViewById(R.id.tab3);
        tab4 = view.findViewById(R.id.tab4);

        linearLayout1 = view.findViewById(R.id.linearLayout1);
        linearLayout2 = view.findViewById(R.id.linearLayout2);
        linearLayout3 = view.findViewById(R.id.linearLayout3);
        linearLayout4 = view.findViewById(R.id.linearLayout4);

        txtText1 = view.findViewById(R.id.txtText1);
        txtText2 = view.findViewById(R.id.txtText2);
        txtText3 = view.findViewById(R.id.txtText3);
        txtText4 = view.findViewById(R.id.txtText4);


        fragment1 = new MarketingSubjectsFragment();
        fragment2 = new BusinessSubjectsFragment();
        fragment3 = new MarketingSubjectsFragment();
        fragment4 = new BusinessSubjectsFragment();

        getActivity().getSupportFragmentManager().beginTransaction().add(R.id.contentFragment2,fragment1).commit();
        linearLayout1.setBackgroundResource(R.drawable.orange_button_style_ripple);
        txtText1.setTextColor(Color.WHITE);

        tab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.contentFragment2,fragment1).commit();

                linearLayout1.setBackgroundResource(R.drawable.orange_button_style_ripple);
                txtText1.setTextColor(Color.WHITE);

                linearLayout2.setBackgroundResource(R.drawable.orange_strokes_style);
                txtText2.setTextColor(Color.GRAY);

                linearLayout3.setBackgroundResource(R.drawable.orange_strokes_style);
                txtText3.setTextColor(Color.GRAY);

                linearLayout4.setBackgroundResource(R.drawable.orange_strokes_style);
                txtText4.setTextColor(Color.GRAY);



            }
        });

        tab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.contentFragment2,fragment2).commit();

                linearLayout2.setBackgroundResource(R.drawable.orange_button_style_ripple);
                txtText2.setTextColor(Color.WHITE);

                linearLayout1.setBackgroundResource(R.drawable.orange_strokes_style);
                txtText1.setTextColor(Color.GRAY);

                linearLayout3.setBackgroundResource(R.drawable.orange_strokes_style);
                txtText3.setTextColor(Color.GRAY);

                linearLayout4.setBackgroundResource(R.drawable.orange_strokes_style);
                txtText4.setTextColor(Color.GRAY);


            }
        });

        tab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.contentFragment2,fragment3).commit();

                linearLayout3.setBackgroundResource(R.drawable.orange_button_style_ripple);
                txtText3.setTextColor(Color.WHITE);

                linearLayout1.setBackgroundResource(R.drawable.orange_strokes_style);
                txtText1.setTextColor(Color.GRAY);

                linearLayout2.setBackgroundResource(R.drawable.orange_strokes_style);
                txtText2.setTextColor(Color.GRAY);

                linearLayout4.setBackgroundResource(R.drawable.orange_strokes_style);
                txtText4.setTextColor(Color.GRAY);


            }
        });

        tab4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.contentFragment2,fragment4).commit();

                linearLayout4.setBackgroundResource(R.drawable.orange_button_style_ripple);
                txtText4.setTextColor(Color.WHITE);

                linearLayout1.setBackgroundResource(R.drawable.orange_strokes_style);
                txtText1.setTextColor(Color.GRAY);

                linearLayout2.setBackgroundResource(R.drawable.orange_strokes_style);
                txtText2.setTextColor(Color.GRAY);

                linearLayout3.setBackgroundResource(R.drawable.orange_strokes_style);
                txtText3.setTextColor(Color.GRAY);


            }
        });



        return  view;
    }
}
