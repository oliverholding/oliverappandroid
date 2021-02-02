package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.PeopleManagement.Evaluation360;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.oalonedeveloper.oliver.oliverappandroidapp.FinancialManagement.FinancialInstitutionsFragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.FinancialManagement.FinancialProductsFragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.FinancialManagement.MyProductsFragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;

public class Evaluation360Activity extends AppCompatActivity {

    ViewPager mViewPager;
    SectionsPagerAdapter mSectionsPagerAdapter;
    int fragmentId;
    TextView txtWorkerName,txtWorkerCharge;
    String post_key,profile_id, job_profile_name,job_profile_surname,job_profile_job_name;
    DatabaseReference companyRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluation360);

        txtWorkerName = findViewById(R.id.txtWorkerName);
        txtWorkerCharge = findViewById(R.id.txtWorkerCharge);
        post_key = getIntent().getExtras().getString("post_key");
        profile_id = getIntent().getExtras().getString("profile_id");
        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");

        companyRef.child(post_key).child("Job Profile").child(profile_id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                job_profile_name = dataSnapshot.child("job_profile_name").getValue().toString();
                job_profile_surname = dataSnapshot.child("job_profile_surname").getValue().toString();
                job_profile_job_name = dataSnapshot.child("job_profile_job_name").getValue().toString();

                txtWorkerName.setText("Evaluando a: "+job_profile_name+" "+job_profile_surname);
                txtWorkerCharge.setText("Cargo: "+job_profile_job_name);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

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
                    return new Evaluation1Fragment();
                case 1:
                    return new Evaluation2Fragment();
                case 2:
                    return new Evaluation3Fragment();
                case 3:
                    return new ResultsEvaluationFragment();

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
