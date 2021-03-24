package com.oalonedeveloper.oliver.oliverappandroidapp.FinancialManagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class FinancialInstitutionDetailActivity extends AppCompatActivity {

    String post_key;
    DatabaseReference financialInstitutionsRef;
    String financial_institution_name,financial_institution_image,financial_institution_background_image;
    TextView txtFinancialInstitutionName;
    ImageView imgBackground;
    CircleImageView imgImage;
    ViewPager mViewPager;
    SectionsPagerAdapter mSectionsPagerAdapter;
    int fragmentId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_financial_institution_detail);

        financialInstitutionsRef = FirebaseDatabase.getInstance().getReference().child("Financial Institutions");
        post_key = getIntent().getExtras().getString("post_key");

        txtFinancialInstitutionName = findViewById(R.id.txtFinancialInstitutionName);
        imgBackground = findViewById(R.id.imgBackground);
        imgImage = findViewById(R.id.imgImage);


        mViewPager.setCurrentItem(fragmentId);

        financialInstitutionsRef.child(post_key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                financial_institution_name = dataSnapshot.child("financial_institution_name").getValue().toString();
                financial_institution_image = dataSnapshot.child("financial_institution_image").getValue().toString();
                financial_institution_background_image = dataSnapshot.child("financial_institution_background_image").getValue().toString();
                txtFinancialInstitutionName.setText(financial_institution_name);
                Picasso.with(FinancialInstitutionDetailActivity.this).load(financial_institution_image).fit().into(imgImage);
                Picasso.with(FinancialInstitutionDetailActivity.this).load(financial_institution_background_image).fit().into(imgBackground);

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
                    return new FinancialProductLendingsListFragment();
                case 1:
                    return new FinancialProductLendingsListFragment();
                case 2:
                    return new FinancialProductLendingsListFragment();

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
