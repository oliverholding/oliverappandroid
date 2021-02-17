package com.oalonedeveloper.oliver.oliverappandroidapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.BusinessOportunitiesModule.Tool1Fragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.BusinessOportunitiesModule.Tool2Fragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.BusinessOportunitiesModule.Tool3Fragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.BusinessOportunitiesModule.Tool4Fragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.BusinessOportunitiesModule.Tool5Fragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.BusinessOportunitiesModule.Tool6Fragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.SalesModule.SalesToolsActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.UserInformationModule.FinalTest1Fragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.UserInformationModule.WelcomeTest1Fragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.Companies.MyCompaniesActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.Companies.OliverAppForBusinessActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.FinancialManagement.FinancialInstitutionsFragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.FinancialManagement.FinancialProductsFragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.FinancialManagement.MyProductsFragment;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class OliverAppActivity extends AppCompatActivity {

    LinearLayout btMyCompanies;
    CircleImageView profileImage;
    DatabaseReference userRef;
    FirebaseAuth mAuth;
    String currentUid,profileimage;
    ViewPager mViewPager;
    SectionsPagerAdapter mSectionsPagerAdapter;
    int fragmentId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oliver_app);

        mAuth = FirebaseAuth.getInstance();
        currentUid = mAuth.getCurrentUser().getUid();
        userRef = FirebaseDatabase.getInstance().getReference().child("Users");

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

        btMyCompanies= findViewById(R.id.btMyCompanies);
        profileImage = findViewById(R.id.profileImage);

        btMyCompanies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OliverAppActivity.this, MyCompaniesActivity.class);
                startActivity(intent);
            }
        });

        userRef.child(currentUid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                profileimage = dataSnapshot.child("profileimage").getValue().toString();
                Picasso.with(OliverAppActivity.this).load(profileimage).fit().into(profileImage);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        showWelcomeMessage();


    }

    private void showWelcomeMessage() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);

        LayoutInflater inflater = LayoutInflater.from(this);
        final View add_bank_account = inflater.inflate(R.layout.welcome_dialog,null);

        dialog.setPositiveButton("Entendido", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        dialog.setView(add_bank_account);
        dialog.show();
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
                    return new MyProductsFragment();
                case 1:
                    return new FinancialProductsFragment();
                case 2:
                    return new FinancialInstitutionsFragment();

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