package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.PeopleManagement.Evaluation360;

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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Commercialization.CreditsAndTreasury.CreditTableFragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Commercialization.CreditsAndTreasury.CurrentDebtFragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Commercialization.CreditsAndTreasury.CustomerCreditFragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Commercialization.CreditsAndTreasury.ExpiredDebtFragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.FinancialManagement.FinancialInstitutionsFragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.FinancialManagement.FinancialProductsFragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.FinancialManagement.MyProductsFragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;

public class Evaluation360Activity extends AppCompatActivity {

    CardView tab1,tab2,tab3,tab4;
    LinearLayout linearLayout1,linearLayout2,linearLayout3,linearLayout4;
    TextView txtText1,txtText2,txtText3,txtText4;
    Fragment fragment1,fragment2,fragment3,fragment4;
    TextView txtWorkerName,txtWorkerCharge;
    String post_key,profile_id, job_profile_name,job_profile_surname,job_profile_job_name;
    DatabaseReference companyRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluation360);

        txtWorkerName = findViewById(R.id.txtWorkerName);
        txtWorkerCharge = findViewById(R.id.txtWorkerCharge);
        post_key = getIntent().getExtras().getString("post_key");
        profile_id = getIntent().getExtras().getString("profile_id");
        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");

        companyRef.child(post_key).child("Job Profile").child(profile_id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                job_profile_name = dataSnapshot.child("job_profile_name").getValue().toString();
                job_profile_surname = dataSnapshot.child("job_profile_surname").getValue().toString();
                job_profile_job_name = dataSnapshot.child("job_profile_job_name").getValue().toString();

                txtWorkerName.setText("Evaluando a: "+job_profile_name+" "+job_profile_surname);
                txtWorkerCharge.setText("Cargo: "+job_profile_job_name);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        tab1 = findViewById(R.id.tab1);
        tab2 = findViewById(R.id.tab2);
        tab3 = findViewById(R.id.tab3);
        tab4 = findViewById(R.id.tab4);
        linearLayout1 = findViewById(R.id.linearLayout1);
        linearLayout2 = findViewById(R.id.linearLayout2);
        linearLayout3 = findViewById(R.id.linearLayout3);
        linearLayout4 = findViewById(R.id.linearLayout4);
        txtText1 = findViewById(R.id.txtText1);
        txtText2 = findViewById(R.id.txtText2);
        txtText3 = findViewById(R.id.txtText3);
        txtText4 = findViewById(R.id.txtText4);

        fragment1 = new Evaluation1Fragment();
        fragment2 = new Evaluation2Fragment();
        fragment3 = new Evaluation3Fragment();
        fragment4 = new ResultsEvaluationFragment();

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

                linearLayout3.setBackgroundResource(R.drawable.orange_strokes_style);
                txtText3.setTextColor(Color.GRAY);

                linearLayout4.setBackgroundResource(R.drawable.orange_strokes_style);
                txtText4.setTextColor(Color.GRAY);
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

                linearLayout4.setBackgroundResource(R.drawable.orange_strokes_style);
                txtText4.setTextColor(Color.GRAY);
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

                linearLayout4.setBackgroundResource(R.drawable.orange_strokes_style);
                txtText4.setTextColor(Color.GRAY);
            }
        });

        tab4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.contentFragment,fragment4).commit();

                linearLayout4.setBackgroundResource(R.drawable.orange_button_style_ripple);
                txtText4.setTextColor(Color.WHITE);

                linearLayout1.setBackgroundResource(R.drawable.orange_strokes_style);
                txtText1.setTextColor(Color.GRAY);

                linearLayout2.setBackgroundResource(R.drawable.orange_strokes_style);
                txtText2.setTextColor(Color.GRAY);

                linearLayout3.setBackgroundResource(R.drawable.orange_strokes_style);
                txtText3.setTextColor(Color.GRAY);
            }
        });
    }

}
