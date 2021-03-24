package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Commercialization.Dashboards;

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

import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Commercialization.CreditsAndTreasury.CreditTableFragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Commercialization.CreditsAndTreasury.CurrentDebtFragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Commercialization.CreditsAndTreasury.CustomerCreditFragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Commercialization.CreditsAndTreasury.ExpiredDebtFragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;

public class DashboardsActivity extends AppCompatActivity {

    CardView tab1,tab2,tab3;
    LinearLayout linearLayout1,linearLayout2,linearLayout3;
    TextView txtText1,txtText2,txtText3;
    Fragment fragment1,fragment2,fragment3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboards);

        tab1 = findViewById(R.id.tab1);
        tab2 = findViewById(R.id.tab2);
        tab3 = findViewById(R.id.tab3);
        linearLayout1 = findViewById(R.id.linearLayout1);
        linearLayout2 = findViewById(R.id.linearLayout2);
        linearLayout3 = findViewById(R.id.linearLayout3);
        txtText1 = findViewById(R.id.txtText1);
        txtText2 = findViewById(R.id.txtText2);
        txtText3 = findViewById(R.id.txtText3);

        fragment1 = new GraphicsFragment();
        fragment2 = new ProductRankingFragment();
        fragment3 = new CustomerRankingFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.contentFragment,fragment1).commit();
        linearLayout1.setBackgroundResource(R.drawable.orange_button_style_ripple);
        txtText1.setTextColor(Color.WHITE);

        tab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.contentFragment,fragment1).commit();

                linearLayout1.setBackgroundResource(R.drawable.orange_button_style_ripple);
                txtText1.setTextColor(Color.WHITE);

                linearLayout2.setBackgroundResource(R.drawable.orange_strokes_style);
                txtText2.setTextColor(Color.GRAY);

                linearLayout3.setBackgroundResource(R.drawable.orange_strokes_style);
                txtText3.setTextColor(Color.GRAY);


            }
        });

        tab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.contentFragment,fragment2).commit();

                linearLayout2.setBackgroundResource(R.drawable.orange_button_style_ripple);
                txtText2.setTextColor(Color.WHITE);

                linearLayout1.setBackgroundResource(R.drawable.orange_strokes_style);
                txtText1.setTextColor(Color.GRAY);

                linearLayout3.setBackgroundResource(R.drawable.orange_strokes_style);
                txtText3.setTextColor(Color.GRAY);


            }
        });

        tab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.contentFragment,fragment3).commit();

                linearLayout3.setBackgroundResource(R.drawable.orange_button_style_ripple);
                txtText3.setTextColor(Color.WHITE);

                linearLayout1.setBackgroundResource(R.drawable.orange_strokes_style);
                txtText1.setTextColor(Color.GRAY);

                linearLayout2.setBackgroundResource(R.drawable.orange_strokes_style);
                txtText2.setTextColor(Color.GRAY);


            }
        });

    }


}
