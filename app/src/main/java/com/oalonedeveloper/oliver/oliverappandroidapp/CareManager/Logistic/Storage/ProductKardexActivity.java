package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Logistic.Storage;

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
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Commercialization.Dashboards.ProductRankingFragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.FinancialManagement.FinancialInstitutionsFragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.FinancialManagement.FinancialProductsFragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.FinancialManagement.MyProductsFragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProductKardexActivity extends AppCompatActivity {

    CircleImageView imgProduct;
    TextView txtProductName,txtWarehouseName,txtCurrentStock,txtTotalValue;
    String company_id,product_id,warehouse_id,product_image,product_name,warehouse_name,product_stock,product_price;
    DatabaseReference companyRef;
    ViewPager mViewPager;
    SectionsPagerAdapter mSectionsPagerAdapter;
    int fragmentId;
    DecimalFormat decimalFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_kardex);

        imgProduct = findViewById(R.id.imgProduct);
        txtProductName = findViewById(R.id.txtProductName);
        txtWarehouseName = findViewById(R.id.txtWarehouseName);
        txtCurrentStock = findViewById(R.id.txtCurrentStock);
        txtTotalValue = findViewById(R.id.txtTotalValue);

        company_id = getIntent().getExtras().getString("company_id");
        product_id = getIntent().getExtras().getString("product_id");
        warehouse_id = getIntent().getExtras().getString("warehouse_id");

        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");

        decimalFormat = new DecimalFormat("0.00");

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

        companyRef.child(company_id).child("Warehouses").child(warehouse_id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                warehouse_name = dataSnapshot.child("warehouse_name").getValue().toString();
                txtWarehouseName.setText("ALMACÉN: "+warehouse_name.toUpperCase());

                companyRef.child(company_id).child("Warehouses").child(warehouse_id).child("Products").child(product_id).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        product_image = dataSnapshot.child("product_image").getValue().toString();
                        product_name = dataSnapshot.child("product_name").getValue().toString();
                        product_stock = dataSnapshot.child("product_stock").getValue().toString();
                        product_price = dataSnapshot.child("product_price").getValue().toString();

                        Picasso.with(ProductKardexActivity.this).load(product_image).fit().into(imgProduct);
                        txtProductName.setText(product_name);

                        txtCurrentStock.setText("STOCK TOTAL: "+product_stock);

                        double price = Double.parseDouble(product_price);
                        double stock = Double.parseDouble(product_stock);
                        double valuation = price*stock;
                        String valuation_st = decimalFormat.format(valuation);
                        txtTotalValue.setText("VALOR TOTAL: S/ "+valuation_st);
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
                    return new ProductKardexSumaryFragment();
                case 1:
                    return new ProductKardexIncomeAndOutcomesFragment();
                case 2:
                    return new ProductKardexValuationFragment();

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
