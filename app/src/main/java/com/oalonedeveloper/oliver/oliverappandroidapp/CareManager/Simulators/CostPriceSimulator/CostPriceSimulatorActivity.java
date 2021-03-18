package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Simulators.CostPriceSimulator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Commercialization.Dashboards.CustomerRankingFragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Commercialization.Dashboards.GraphicsFragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Commercialization.Dashboards.ProductRankingFragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;

public class CostPriceSimulatorActivity extends AppCompatActivity {

    CardView tab1;
    LinearLayout linearLayout1;
    TextView txtText1;
    Fragment fragment1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cost_price_simulator);

        tab1 = findViewById(R.id.tab1);


        linearLayout1 = findViewById(R.id.linearLayout1);


        txtText1 = findViewById(R.id.txtText1);


        fragment1 = new ProductCostSimulatorFragment();


        getSupportFragmentManager().beginTransaction().add(R.id.contentFragment,fragment1).commit();
        linearLayout1.setBackgroundResource(R.drawable.orange_button_style_ripple);
        txtText1.setTextColor(Color.WHITE);

        tab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.contentFragment,fragment1).commit();

                linearLayout1.setBackgroundResource(R.drawable.orange_button_style_ripple);
                txtText1.setTextColor(Color.WHITE);


            }
        });

    }
}
