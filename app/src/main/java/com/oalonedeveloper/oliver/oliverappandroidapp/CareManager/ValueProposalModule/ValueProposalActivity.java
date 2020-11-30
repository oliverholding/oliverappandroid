package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.ValueProposalModule;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.BusinessOportunitiesModule.BusinessOportunitiesActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.BusinessOportunitiesModule.BusinessOportunitiesFragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.BusinessOportunitiesModule.BusinessOportunityToolFragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.BusinessOportunitiesModule.Training1Fragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.BusinessOportunitiesModule.Video1Module2Fragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.BusinessOportunitiesModule.Video2Module2Fragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.BusinessOportunitiesModule.Video3Module2Fragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.BusinessOportunitiesModule.Video4Module2Fragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.BusinessOportunitiesModule.Video5Module2Fragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.BusinessOportunitiesModule.Video6Module2Fragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.IntructionModule.Files1Fragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.IntructionModule.Video1Fragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.IntructionModule.Video2Fragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.IntructionModule.Video3Fragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.IntructionModule.Video4Fragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.IntructionModule.Video5Fragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.IntructionModule.Video6Fragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;

public class ValueProposalActivity extends AppCompatActivity {

    ViewPager mViewPager;
    SectionsPagerAdapter mSectionsPagerAdapter;
    int fragmentId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_value_proposal);

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
                    return new ValueProposalFragment();
                case 1:
                    return new Training1Fragment();
                case 2:
                    return new Video1Fragment();
                case 3:
                    return new Video2Fragment();
                case 4:
                    return new Video3Fragment();
                case 5:
                    return new Video4Fragment();
                case 6:
                    return new Video5Fragment();
                case 7:
                    return new Video6Fragment();
                case 8:
                    return new Files1Fragment();


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
