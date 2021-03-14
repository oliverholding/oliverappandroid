package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Commercialization.Dashboards.CustomerRankingFragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Commercialization.Dashboards.GraphicsFragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Commercialization.Dashboards.ProductRankingFragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;
import com.oalonedeveloper.oliver.oliverappandroidapp.RegistrationData.DataSumaryFragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.RegistrationData.RegisterData1Fragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.RegistrationData.RegisterData2Fragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.RegistrationData.RegisterData3Fragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.RegistrationData.RegisterData4Fragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.RegistrationData.RegisterData5Fragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.RegistrationData.RegistrationDataActivity;

public class CareManagerActivity extends AppCompatActivity {

    CardView tab1,tab2,tab3;
    Fragment fragment1,fragment2,fragment3;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_care_manager);

        tab1 = findViewById(R.id.tab1);
        tab2 = findViewById(R.id.tab2);
        tab3 = findViewById(R.id.tab3);

        fragment1 = new CareManagerSumaryFragment();
        fragment2 = new CareToolsFragment();
        fragment3 = new CareAchievementsFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.contentFragment,fragment1).commit();


        tab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.contentFragment,fragment1).commit();

            }
        });

        tab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.contentFragment,fragment2).commit();




            }
        });

        tab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.contentFragment,fragment3).commit();



            }
        });
    }

}
