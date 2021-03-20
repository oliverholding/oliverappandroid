package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.ManagementControlAndReports.LessonLearned;

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

public class LessonLearnedActivity extends AppCompatActivity {

    CardView tab1,tab2;
    LinearLayout linearLayout1,linearLayout2;
    TextView txtText1,txtText2;
    Fragment fragment1,fragment2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_learned);

        tab1 = findViewById(R.id.tab1);
        tab2 = findViewById(R.id.tab2);

        linearLayout1 = findViewById(R.id.linearLayout1);
        linearLayout2 = findViewById(R.id.linearLayout2);

        txtText1 = findViewById(R.id.txtText1);
        txtText2 = findViewById(R.id.txtText2);


        fragment1 = new GraphicsFragment();
        fragment2 = new ProductRankingFragment();


        getSupportFragmentManager().beginTransaction().add(R.id.contentFragment,fragment1).commit();
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

            }
        });

    }
}
