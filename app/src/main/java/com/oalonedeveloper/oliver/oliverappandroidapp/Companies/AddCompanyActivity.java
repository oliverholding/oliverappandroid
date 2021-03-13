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

    Fragment fragment1,fragment2,fragment3,fragment4,fragment5;

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

        fragment1 = new RegisterCompanyData1Fragment();
        fragment2 = new RegisterCompanyData2Fragment();
        fragment3 = new RegisterCompanyData3Fragment();
        fragment4 = new RegisterCompanyData4Fragment();
        fragment5 = new CompanyDataSumaryFragment();

        getSupportFragmentManager().beginTransaction().add(R.id.contentFragment,fragment1).commit();

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