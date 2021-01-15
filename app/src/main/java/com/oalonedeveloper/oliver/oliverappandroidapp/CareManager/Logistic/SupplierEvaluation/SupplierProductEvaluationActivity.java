package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Logistic.SupplierEvaluation;

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
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class SupplierProductEvaluationActivity extends AppCompatActivity {

    ViewPager mViewPager;
    SectionsPagerAdapter mSectionsPagerAdapter;
    int fragmentId;
    DatabaseReference companyRef;
    String post_key,product_id;
    CircleImageView imgProduct;
    TextView txtProductName;
    TextView txtPriceWeight,txtQualityWeight,txtTimeWeight;
    int price_weight,quality_weight,time_weight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplier_product_evaluation);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        TabItem tabItem = findViewById(R.id.tabItem);
        TabItem tabItem2 = findViewById(R.id.tabItem2);

        post_key = getIntent().getExtras().getString("post_key");
        product_id = getIntent().getExtras().getString("product_id");
        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");

        txtPriceWeight = findViewById(R.id.txtPriceWeight);
        txtQualityWeight = findViewById(R.id.txtQualityWeight);
        txtTimeWeight = findViewById(R.id.txtTimeWeight);

        fragmentId = getIntent().getIntExtra("FRAGMENT_ID",0);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(),tabLayout.getTabCount());

        imgProduct = findViewById(R.id.imgProduct);
        txtProductName = findViewById(R.id.txtProductName);

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        companyRef.child(post_key).child("Purchased Items").child(product_id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String product_image = dataSnapshot.child("product_image").getValue().toString();
                String product_name = dataSnapshot.child("product_name").getValue().toString();

                Picasso.with(SupplierProductEvaluationActivity.this).load(product_image).fit().into(imgProduct);
                txtProductName.setText(product_name);

                if (dataSnapshot.child("Evaluation").hasChild("price_weight")) {
                    price_weight = dataSnapshot.child("Evaluation").child("price_weight").getValue(Integer.class);
                    txtPriceWeight.setText(price_weight+"%");
                } else {
                    companyRef.child(post_key).child("Purchased Items").child(product_id).child("Evaluation").child("price_weight").setValue(50);
                }

                if (dataSnapshot.child("Evaluation").hasChild("quality_weight")) {
                    quality_weight = dataSnapshot.child("Evaluation").child("quality_weight").getValue(Integer.class);
                    txtQualityWeight.setText(quality_weight+"%");
                } else {
                    companyRef.child(post_key).child("Purchased Items").child(product_id).child("Evaluation").child("quality_weight").setValue(25);
                }

                if (dataSnapshot.child("Evaluation").hasChild("time_weight")) {
                    time_weight = dataSnapshot.child("Evaluation").child("time_weight").getValue(Integer.class);
                    txtTimeWeight.setText(time_weight+"%");
                } else {
                    companyRef.child(post_key).child("Purchased Items").child(product_id).child("Evaluation").child("time_weight").setValue(25);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

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
                    return new SupplierEvaluationSumaryFragment();
                case 1:
                    return new SupplierEvaluationPriceFragment();
                case 2:
                    return new SupplierEvluationQualityFragment();
                case 3:
                    return new SupplierEvaluationTimeFragment();

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
