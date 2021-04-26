package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.ManagementControlAndReports.LessonLearned;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Commercialization.Dashboards.CustomerRankingFragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Commercialization.Dashboards.GraphicsFragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Commercialization.Dashboards.ProductRankingFragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;

public class LessonLearnedActivity extends AppCompatActivity {

    CardView tab1;
    LinearLayout linearLayout1;
    TextView txtText1;
    Fragment fragment1,fragment2;
    String post_key;
    DatabaseReference companyRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_learned);

        post_key = getIntent().getExtras().getString("post_key");
        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");

        tab1 = findViewById(R.id.tab1);

        linearLayout1 = findViewById(R.id.linearLayout1);


        txtText1 = findViewById(R.id.txtText1);

        fragment1 = new LessonLearnedEvaluationFragment();
        fragment2 = new LessonLearnedResultsFragment();

        companyRef.child(post_key).child("Lesson Learned Evaluation").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.contentFragment, fragment2).commit();
                    linearLayout1.setBackgroundResource(R.drawable.orange_button_style_ripple);
                    txtText1.setTextColor(Color.WHITE);
                    txtText1.setText("Resultados");
                } else {
                    getSupportFragmentManager().beginTransaction().replace(R.id.contentFragment, fragment1).commit();
                    linearLayout1.setBackgroundResource(R.drawable.orange_button_style_ripple);
                    txtText1.setTextColor(Color.WHITE);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}
