package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.CareLearning;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Commercialization.Dashboards.CustomerRankingFragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Commercialization.Dashboards.GraphicsFragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Commercialization.Dashboards.ProductRankingFragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;

public class CareLearningActivity extends AppCompatActivity {

    CardView tab1,tab2,tab3;
    LinearLayout linearLayout1,linearLayout2,linearLayout3;
    TextView txtText1,txtText2,txtText3;
    Fragment fragment1,fragment2,fragment3;
    DatabaseReference userRef;
    FirebaseAuth mAuth;
    String currentUid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_care_learning);

        tab1 = findViewById(R.id.tab1);
        tab2 = findViewById(R.id.tab2);
        tab3 = findViewById(R.id.tab3);
        linearLayout1 = findViewById(R.id.linearLayout1);
        linearLayout2 = findViewById(R.id.linearLayout2);
        linearLayout3 = findViewById(R.id.linearLayout3);
        txtText1 = findViewById(R.id.txtText1);
        txtText2 = findViewById(R.id.txtText2);
        txtText3 = findViewById(R.id.txtText3);

        mAuth = FirebaseAuth.getInstance();
        currentUid = mAuth.getCurrentUser().getUid();
        userRef = FirebaseDatabase.getInstance().getReference().child("Users");

        userRef.child(currentUid).child("Care Learning").child("My Subjects").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    fragment1 = new MySubjectsFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.contentFragment,fragment1).commit();
                    linearLayout1.setBackgroundResource(R.drawable.orange_button_rectangle_ripple);
                    txtText1.setTextColor(Color.WHITE);
                } else {
                    fragment1 = new NoSubjectsFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.contentFragment,fragment1).commit();
                    linearLayout1.setBackgroundResource(R.drawable.orange_button_rectangle_ripple);
                    txtText1.setTextColor(Color.WHITE);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        fragment2 = new SubjectsFragment();
        fragment3 = new NoSubjectsFragment();



        tab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.contentFragment,fragment1).commit();

                linearLayout1.setBackgroundResource(R.drawable.orange_button_rectangle_ripple);
                txtText1.setTextColor(Color.WHITE);

                linearLayout2.setBackgroundResource(0);
                txtText2.setTextColor(Color.GRAY);

                linearLayout3.setBackgroundResource(0);
                txtText3.setTextColor(Color.GRAY);


            }
        });

        tab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.contentFragment,fragment2).commit();

                linearLayout2.setBackgroundResource(R.drawable.orange_button_rectangle_ripple);
                txtText2.setTextColor(Color.WHITE);

                linearLayout1.setBackgroundResource(0);
                txtText1.setTextColor(Color.GRAY);

                linearLayout3.setBackgroundResource(0);
                txtText3.setTextColor(Color.GRAY);


            }
        });

        tab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.contentFragment,fragment3).commit();

                linearLayout3.setBackgroundResource(R.drawable.orange_button_rectangle_ripple);
                txtText3.setTextColor(Color.WHITE);

                linearLayout1.setBackgroundResource(0);
                txtText1.setTextColor(Color.GRAY);

                linearLayout2.setBackgroundResource(0);
                txtText2.setTextColor(Color.GRAY);


            }
        });

    }

}
