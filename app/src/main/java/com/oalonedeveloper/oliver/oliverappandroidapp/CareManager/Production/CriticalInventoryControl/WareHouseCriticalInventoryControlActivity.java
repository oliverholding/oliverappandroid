package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Production.CriticalInventoryControl;

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
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Logistic.InventoryControl.WarehouseInventoryControlActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Logistic.InventoryControl.WarehouseInventoryControlDiffFragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Logistic.InventoryControl.WarehouseInventoryControlFragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Logistic.InventoryControl.WarehouseInventoryControlStockFragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;

public class WareHouseCriticalInventoryControlActivity extends AppCompatActivity {

    TextView txtWarehouseName;
    String post_key,warehouse_id,warehouse_name;
    DatabaseReference companyRef;
    ViewPager mViewPager;
    SectionsPagerAdapter mSectionsPagerAdapter;
    int fragmentId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ware_house_critical_inventory_control);

        post_key = getIntent().getExtras().getString("post_key");
        warehouse_id = getIntent().getExtras().getString("warehouse_id");
        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");

        txtWarehouseName = findViewById(R.id.txtWarehouseName);

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

        companyRef.child(post_key).child("Warehouses").child(warehouse_id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                warehouse_name = dataSnapshot.child("warehouse_name").getValue().toString();
                txtWarehouseName.setText(warehouse_name);

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
                    return new WarehouseInventoryControlFragment();
                case 1:
                    return new WarehouseInventoryControlStockFragment();
                case 2:
                    return new CriticalInventoryCategoryFragment();


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
