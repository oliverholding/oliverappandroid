package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Commercialization;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.BusinessOportunitiesModule.Tool1Fragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.BusinessOportunitiesModule.Tool2Fragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.BusinessOportunitiesModule.Tool3Fragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.BusinessOportunitiesModule.Tool4Fragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.BusinessOportunitiesModule.Tool5Fragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.BusinessOportunitiesModule.Tool6Fragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.NewProductsOrServices.NewProductsOrServicesToolsActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.UserInformationModule.FinalTest1Fragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.UserInformationModule.WelcomeTest1Fragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;

public class CommercializationToolActivity extends AppCompatActivity {

    ViewPager mViewPager;
    SectionsPagerAdapter mSectionsPagerAdapter;
    int fragmentId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commercialization_tool);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        TabItem tabItem = findViewById(R.id.tabItem);
        TabItem tabItem2 = findViewById(R.id.tabItem2);

        fragmentId = getIntent().getIntExtra("FRAGMENT_ID",0);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(),tabLayout.getTabCount());

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        mViewPager.setCurrentItem(fragmentId);
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        private int numOfTabs;

        public SectionsPagerAdapter(FragmentManager fm, int numOfTabs) {
            super(fm);
            this.numOfTabs = numOfTabs;
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            switch (position) {
                case 0:
                    return new WelcomeTest1Fragment();
                case 1:
                    return new Tool1Fragment();
                case 2:
                    return new Tool2Fragment();
                case 3:
                    return new Tool3Fragment();
                case 4:
                    return new Tool4Fragment();
                case 5:
                    return new Tool5Fragment();
                case 6:
                    return new Tool6Fragment();
                case 7:
                    return new FinalTest1Fragment();


                default:
                    return null;
            }

        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return numOfTabs;
        }
    }
}
