package com.oalonedeveloper.oliver.oliverappandroidapp.Companies;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;
import com.oalonedeveloper.oliver.oliverappandroidapp.RegistrationData.DataSumaryFragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.RegistrationData.RegisterData1Fragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.RegistrationData.RegisterData2Fragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.RegistrationData.RegisterData3Fragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.RegistrationData.RegisterData4Fragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.RegistrationData.RegisterData5Fragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.RegistrationData.RegistrationDataActivity;

public class AddCompanyActivity extends AppCompatActivity {

    ViewPager mViewPager;
    SectionsPagerAdapter mSectionsPagerAdapter;
    int fragmentId;

    private static final int MY_CAMERA_REQUEST_CODE = 100;
    private static final int MY_STORAGE_REQUEST_CODE = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_company);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_REQUEST_CODE);
            }
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_STORAGE_REQUEST_CODE);
            }
        }

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
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
                    return new RegisterCompanyData1Fragment();
                case 1:
                    return new RegisterCompanyData2Fragment();
                case 2:
                    return new RegisterCompanyData3Fragment();
                case 3:
                    return new RegisterCompanyData4Fragment();
                case 4:
                    return new CompanyDataSumaryFragment();
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_CAMERA_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "AL RECHAZAR LOS PERMISOS ALGUNAS FUNCIONES NO ESTARÁN DISPONIBLES", Toast.LENGTH_LONG).show();
            }
        }
        if (requestCode == MY_STORAGE_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "AL RECHAZAR LOS PERMISOS ALGUNAS FUNCIONES NO ESTARÁN DISPONIBLES", Toast.LENGTH_LONG).show();
            }
        }
    }
}