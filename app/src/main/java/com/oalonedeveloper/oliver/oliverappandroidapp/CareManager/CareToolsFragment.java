package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.BusinessModelModule.BusinessModelActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.BusinessOportunitiesModule.BusinessOportunitiesActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Commercialization.CommercializationActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.FinancialManagement.FinanacialManagementActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.FinancialManagement.FinancialManagementFragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.IntructionModule.IntroductionModuleActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Logistic.LogisticActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.NewProductsOrServices.NewProductsOrServicesActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Normativity.NormativityActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.PeopleManagement.JobProfiles.JobProfilesActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.PeopleManagement.PeopleManagementActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Production.ProductionActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.SalesModule.SalesModuleActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.StrategicDirection.StrategicDirectionActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.UserInformationModule.UserInformationModuleActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.ValueProposalModule.ValueProposalActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;


public class CareToolsFragment extends Fragment {

    CardView cardViewModule1,cardViewModule2,cardViewModule3,cardViewModule4,cardViewModule5,cardViewModule6,cardViewModule7,cardViewModule8,cardViewModule9,cardViewModule10,cardViewModule11,cardViewModule12,cardViewModule13,cardViewModule14;
    String post_key;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_care_tools, container, false);

        post_key = getActivity().getIntent().getExtras().getString("post_key");

        cardViewModule1 = view.findViewById(R.id.cardViewModule1);
        cardViewModule2 = view.findViewById(R.id.cardViewModule2);
        cardViewModule3 = view.findViewById(R.id.cardViewModule3);
        cardViewModule4 = view.findViewById(R.id.cardViewModule4);
        cardViewModule5 = view.findViewById(R.id.cardViewModule5);
        cardViewModule6 = view.findViewById(R.id.cardViewModule6);
        cardViewModule7 = view.findViewById(R.id.cardViewModule7);
        cardViewModule8 = view.findViewById(R.id.cardViewModule8);
        cardViewModule9 = view.findViewById(R.id.cardViewModule9);
        cardViewModule10 = view.findViewById(R.id.cardViewModule10);
        cardViewModule11 = view.findViewById(R.id.cardViewModule11);
        cardViewModule12 = view.findViewById(R.id.cardViewModule12);
        cardViewModule13 = view.findViewById(R.id.cardViewModule13);
        cardViewModule14 = view.findViewById(R.id.cardViewModule14);

        cardViewModule1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), IntroductionModuleActivity.class);
                startActivity(intent);
            }
        });

        cardViewModule2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), BusinessOportunitiesActivity.class);
                intent.putExtra("post_key",post_key);
                startActivity(intent);
            }
        });

        cardViewModule3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ValueProposalActivity.class);
                intent.putExtra("post_key",post_key);
                startActivity(intent);
            }
        });

        cardViewModule4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), BusinessModelActivity.class);
                intent.putExtra("post_key",post_key);
                startActivity(intent);
            }
        });

        cardViewModule5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), UserInformationModuleActivity.class);
                intent.putExtra("post_key",post_key);
                startActivity(intent);
            }
        });

        cardViewModule6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NewProductsOrServicesActivity.class);
                intent.putExtra("post_key",post_key);
                startActivity(intent);
            }
        });

        cardViewModule7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CommercializationActivity.class);
                intent.putExtra("post_key",post_key);
                startActivity(intent);
            }
        });

        cardViewModule8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SalesModuleActivity.class);
                intent.putExtra("post_key",post_key);
                startActivity(intent);
            }
        });
        cardViewModule9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LogisticActivity.class);
                intent.putExtra("post_key",post_key);
                startActivity(intent);
            }
        });
        cardViewModule10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ProductionActivity.class);
                intent.putExtra("post_key",post_key);
                startActivity(intent);
            }
        });

        cardViewModule11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PeopleManagementActivity.class);
                intent.putExtra("post_key",post_key);
                startActivity(intent);
            }
        });

        cardViewModule12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NormativityActivity.class);
                intent.putExtra("post_key",post_key);
                startActivity(intent);
            }
        });

        cardViewModule13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FinanacialManagementActivity.class);
                intent.putExtra("post_key",post_key);
                startActivity(intent);
            }
        });

        cardViewModule14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), StrategicDirectionActivity.class);
                intent.putExtra("post_key",post_key);
                startActivity(intent);
            }
        });

        return view;
    }
}
