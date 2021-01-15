package com.oalonedeveloper.oliver.oliverappandroidapp.FinancialManagement.LendingProduct;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.oalonedeveloper.oliver.oliverappandroidapp.FinancialManagement.FinancialInstitutionDetailActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.FinancialManagement.FinancialInstitutionsFragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.FinancialManagement.FinancialProductsFragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.FinancialManagement.MyProductsFragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class LendingDetailActivity extends AppCompatActivity {

    String product_key,institution_key, financial_institution_name,financial_institution_image,financial_institution_background_image;;
    DatabaseReference financialInstitutionsRef;
    TextView txtProductName,txtFinancialInstitutionName;
    ImageView imgBackground,imgBackgroundButton;
    CircleImageView imgProductImage;
    LinearLayout btnLoanRequest;
    ViewPager mViewPager;
    SectionsPagerAdapter mSectionsPagerAdapter;
    int fragmentId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lending_detail);

        product_key = getIntent().getExtras().getString("product_key");
        institution_key = getIntent().getExtras().getString("institution_key");
        financialInstitutionsRef = FirebaseDatabase.getInstance().getReference().child("Financial Institutions");

        txtProductName = findViewById(R.id.txtProductName);
        txtFinancialInstitutionName = findViewById(R.id.txtFinancialInstitutionName);
        imgBackground = findViewById(R.id.imgBackground);
        imgProductImage = findViewById(R.id.imgProductImage);
        imgBackgroundButton = findViewById(R.id.imgBackgroundButton);
        btnLoanRequest = findViewById(R.id.btnLoanRequest);


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

        financialInstitutionsRef.child(institution_key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                financial_institution_name = dataSnapshot.child("financial_institution_name").getValue().toString();
                financial_institution_image = dataSnapshot.child("financial_institution_image").getValue().toString();
                financial_institution_background_image = dataSnapshot.child("financial_institution_background_image").getValue().toString();
                txtFinancialInstitutionName.setText("Por "+financial_institution_name);
                Picasso.with(LendingDetailActivity.this).load(financial_institution_background_image).fit().into(imgBackground);
                Picasso.with(LendingDetailActivity.this).load(financial_institution_background_image).fit().into(imgBackgroundButton);

                financialInstitutionsRef.child(institution_key).child("Products").child(product_key).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String product_name = dataSnapshot.child("product_name").getValue().toString();
                        String product_short_description = dataSnapshot.child("product_short_description").getValue().toString();
                        String product_completed_description = dataSnapshot.child("product_completed_description").getValue().toString();
                        String product_img = dataSnapshot.child("product_img").getValue().toString();

                        Picasso.with(LendingDetailActivity.this).load(product_img).fit().into(imgProductImage);
                        txtProductName.setText(product_name);

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

        btnLoanRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LendingDetailActivity.this, LoanRequestActivity.class);
                intent.putExtra("product_key",product_key);
                intent.putExtra("institution_key",institution_key);
                startActivity(intent);
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
                    return new LendingDetailsAndBenefitsFragment();
                case 1:
                    return new LendingDetailsAndBenefitsFragment();
                case 2:
                    return new LendingDetailsAndBenefitsFragment();

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