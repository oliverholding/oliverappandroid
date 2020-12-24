package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Commercialization.CreditsAndTreasury;

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

import java.util.Map;

public class CreditAndTreasuryActivity extends AppCompatActivity {

    ViewPager mViewPager;
    SectionsPagerAdapter mSectionsPagerAdapter;
    int fragmentId;
    String post_key;
    DatabaseReference companyRef;
    TextView txtCurrentDebt,txtExpiredDebts,txtTotalDebt;
    double sum1,sum2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit_and_treasury);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        TabItem tabItem = findViewById(R.id.tabItem);
        TabItem tabItem2 = findViewById(R.id.tabItem2);

        fragmentId = getIntent().getIntExtra("FRAGMENT_ID",0);

        post_key = getIntent().getExtras().getString("post_key");

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(),tabLayout.getTabCount());

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        mViewPager.setCurrentItem(fragmentId);

        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");

        txtCurrentDebt = findViewById(R.id.txtCurrentDebt);
        txtExpiredDebts = findViewById(R.id.txtExpiredDebts);
        txtTotalDebt = findViewById(R.id.txtTotalDebt);

        companyRef.child(post_key).child("My Bills").orderByChild("state").equalTo("no_paid").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                sum1 = 0;
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Map<String, Object> map = (Map<String, Object>) ds.getValue();
                    Object price = map.get("bill_amount");
                    double total_value = Double.parseDouble(String.valueOf(price));
                    sum1 += total_value;


                    txtCurrentDebt.setText("S/ "+sum1);
                }

                companyRef.child(post_key).child("My Bills").orderByChild("state").equalTo("expired").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        sum2 = 0;
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            Map<String, Object> map = (Map<String, Object>) ds.getValue();
                            Object price = map.get("bill_amount");
                            double total_value = Double.parseDouble(String.valueOf(price));
                            sum2 += total_value;


                            txtExpiredDebts.setText("S/ "+sum2);
                        }

                        double total_debt = sum1+sum2;

                        txtTotalDebt.setText("S/ "+total_debt);

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


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
                    return new CurrentDebtFragment();
                case 1:
                    return new ExpiredDebtFragment();
                case 2:
                    return new CreditTableFragment();
                case 3:
                    return new CustomerCreditFragment();

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
