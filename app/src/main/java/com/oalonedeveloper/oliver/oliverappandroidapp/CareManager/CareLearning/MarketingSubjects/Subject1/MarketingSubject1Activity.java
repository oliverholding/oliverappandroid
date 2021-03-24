package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.CareLearning.MarketingSubjects.Subject1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.CareAchievementsFragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.CareManagerSumaryFragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.CareToolsFragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;

public class MarketingSubject1Activity extends AppCompatActivity {

    CardView tab1,tab2;
    Fragment fragment1,fragment2;
    View view1,view2;
    TextView txt1,txt2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marketing_subject1);

        tab1 = findViewById(R.id.tab1);
        tab2 = findViewById(R.id.tab2);

        txt1 = findViewById(R.id.txt1);
        txt2 = findViewById(R.id.txt2);

        view1 = findViewById(R.id.view1);
        view2 = findViewById(R.id.view2);

        fragment1 = new MarketingSubject1SyllabusFragment();
        fragment2 = new MarketingSubject1SyllabusFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.contentFragment,fragment1).commit();


        tab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.contentFragment,fragment1).commit();
                txt1.setTextColor(getResources().getColor(R.color.orange1));
                txt2.setTextColor(Color.GRAY);


                view1.setBackgroundResource(R.color.orange1);
                view2.setBackgroundResource(R.color.gray2);

            }
        });

        tab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.contentFragment,fragment2).commit();
                txt2.setTextColor(getResources().getColor(R.color.orange1));
                txt1.setTextColor(Color.GRAY);

                view2.setBackgroundResource(R.color.orange1);
                view1.setBackgroundResource(R.color.gray2);

            }
        });
    }
}
