package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Commercialization.CustomerSupport;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.BusinessOportunitiesModule.Training1Fragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Commercialization.CommercializationFragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.IntructionModule.Files1Fragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.IntructionModule.Video1Fragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.IntructionModule.Video2Fragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.IntructionModule.Video3Fragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.IntructionModule.Video4Fragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.IntructionModule.Video5Fragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.IntructionModule.Video6Fragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;

public class CustomerSuportActivity extends AppCompatActivity {

    ViewPager mViewPager;
    SectionsPagerAdapter mSectionsPagerAdapter;
    int fragmentId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_suport);

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
                    return new ShowCustomerSupportFragment();
                case 1:
                    return new CustomerSuportTableFragment();
                case 2:
                    return new CustomerSuportSendNotificationsFragment();

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
