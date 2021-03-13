package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.FinancialManagement.AccountsNoPaid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.FinancialManagement.AccountsToPaid.LongTermToPaidFragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;

public class AccountsNoPaidActivity extends AppCompatActivity {


    Fragment fragment1,fragment2,fragment3;
    CardView tab1,tab2,tab3;
    TextView txtText1,txtText2,txtText3;
    LinearLayout linearLayout1,linearLayout2,linearLayout3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accounts_no_paid);

        fragment1 = new ShortTermDebtsFragment();
        fragment2 = new LongTermToPaidFragment();
        fragment3 = new ExpiredDebtsFragment();

        tab1 = findViewById(R.id.tab1);
        tab2 = findViewById(R.id.tab2);
        tab3 = findViewById(R.id.tab3);

        txtText1 = findViewById(R.id.txtText1);
        txtText2 = findViewById(R.id.txtText2);
        txtText3 = findViewById(R.id.txtText3);

        linearLayout1 = findViewById(R.id.linearLayout1);
        linearLayout2 = findViewById(R.id.linearLayout2);
        linearLayout3 = findViewById(R.id.linearLayout3);

        getSupportFragmentManager().beginTransaction().add(R.id.contentFragment,fragment1).commit();

        tab1.setBackgroundResource(R.drawable.orange_button_style_ripple);
        txtText1.setTextColor(Color.WHITE);

        tab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().add(R.id.contentFragment,fragment1).commit();
                txtText1.setTextColor(Color.WHITE);
                linearLayout1.setBackgroundResource(R.drawable.orange_button_style_ripple);

                txtText2.setTextColor(Color.GRAY);
                linearLayout2.setBackgroundResource(R.drawable.orange_strokes_style);

                txtText3.setTextColor(Color.GRAY);
                linearLayout3.setBackgroundResource(R.drawable.orange_strokes_style);

            }
        });

        tab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().add(R.id.contentFragment,fragment2).commit();
                txtText2.setTextColor(Color.WHITE);
                linearLayout2.setBackgroundResource(R.drawable.orange_button_style_ripple);

                txtText1.setTextColor(Color.GRAY);
                linearLayout1.setBackgroundResource(R.drawable.orange_strokes_style);

                txtText3.setTextColor(Color.GRAY);
                linearLayout3.setBackgroundResource(R.drawable.orange_strokes_style);
            }
        });

        tab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().add(R.id.contentFragment,fragment3).commit();
                txtText3.setTextColor(Color.WHITE);
                linearLayout3.setBackgroundResource(R.drawable.orange_button_style_ripple);

                txtText1.setTextColor(Color.GRAY);
                linearLayout1.setBackgroundResource(R.drawable.orange_strokes_style);

                txtText2.setTextColor(Color.GRAY);
                linearLayout2.setBackgroundResource(R.drawable.orange_strokes_style);
            }
        });

    }

}
