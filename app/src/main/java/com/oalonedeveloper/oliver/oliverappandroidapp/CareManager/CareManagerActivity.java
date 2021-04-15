package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Commercialization.Dashboards.CustomerRankingFragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Commercialization.Dashboards.GraphicsFragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Commercialization.Dashboards.ProductRankingFragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.PhoneAuthActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.PlatformSelectionActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;
import com.oalonedeveloper.oliver.oliverappandroidapp.RegistrationData.DataSumaryFragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.RegistrationData.RegisterData1Fragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.RegistrationData.RegisterData2Fragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.RegistrationData.RegisterData3Fragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.RegistrationData.RegisterData4Fragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.RegistrationData.RegisterData5Fragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.RegistrationData.RegistrationDataActivity;
import com.squareup.picasso.Picasso;
import com.xw.repo.BubbleSeekBar;

import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class CareManagerActivity extends AppCompatActivity {

    CardView tab1,tab2,tab3;
    Fragment fragment1,fragment2,fragment3;
    CircleImageView imgCompanyProfile;
    DatabaseReference myCompanyRef;
    String company_image,company_social_reason, post_key,company_ruc;
    TextView txtCompanyName,txtExp,txtRuc,txt1,txt2,txt3;
    View view1,view2,view3;
    BubbleSeekBar expBar;
    ImageView btnSignOut;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_care_manager);

        tab1 = findViewById(R.id.tab1);
        tab2 = findViewById(R.id.tab2);
        tab3 = findViewById(R.id.tab3);

        txtExp = findViewById(R.id.txtExp);
        expBar = findViewById(R.id.expBar);

        txt1 = findViewById(R.id.txt1);
        txt2 = findViewById(R.id.txt2);
        txt3 = findViewById(R.id.txt3);

        view1 = findViewById(R.id.view1);
        view2 = findViewById(R.id.view2);
        view3 = findViewById(R.id.view3);
        btnSignOut = findViewById(R.id.btnSignOut);
        mAuth = FirebaseAuth.getInstance();

        fragment1 = new CareManagerSumaryFragment();
        fragment2 = new CareToolsFragment();
        fragment3 = new CareAchievementsFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.contentFragment,fragment1).commit();


        tab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.contentFragment,fragment1).commit();
                txt1.setTextColor(getResources().getColor(R.color.orange1));
                txt2.setTextColor(Color.GRAY);
                txt3.setTextColor(Color.GRAY);

                view1.setBackgroundResource(R.color.orange1);
                view2.setBackgroundResource(R.color.gray2);
                view3.setBackgroundResource(R.color.gray2);
            }
        });

        tab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.contentFragment,fragment2).commit();
                txt2.setTextColor(getResources().getColor(R.color.orange1));
                txt1.setTextColor(Color.GRAY);
                txt3.setTextColor(Color.GRAY);

                view2.setBackgroundResource(R.color.orange1);
                view1.setBackgroundResource(R.color.gray2);
                view3.setBackgroundResource(R.color.gray2);
            }
        });

        tab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.contentFragment,fragment3).commit();
                txt3.setTextColor(getResources().getColor(R.color.orange1));
                txt2.setTextColor(Color.GRAY);
                txt1.setTextColor(Color.GRAY);

                view3.setBackgroundResource(R.color.orange1);
                view2.setBackgroundResource(R.color.gray2);
                view1.setBackgroundResource(R.color.gray2);

            }
        });

        myCompanyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");

        post_key = getIntent().getExtras().getString("post_key");

        imgCompanyProfile = findViewById(R.id.imgCompanyProfile);
        txtCompanyName = findViewById(R.id.txtCompanyName);
        txtRuc = findViewById(R.id.txtRuc);

        myCompanyRef.child(post_key).addValueEventListener(new ValueEventListener() {
            public void onDataChange(DataSnapshot dataSnapshot) {
                company_image = dataSnapshot.child("company_image").getValue().toString();
                company_social_reason = dataSnapshot.child("company_social_reason").getValue().toString();
                company_ruc = dataSnapshot.child("company_ruc").getValue().toString();
                Picasso.with(CareManagerActivity.this).load(company_image).fit().into(imgCompanyProfile);
                txtCompanyName.setText(company_social_reason);
                txtRuc.setText("RUC: "+company_ruc);

                if (dataSnapshot.hasChild("Achievements")) {
                    myCompanyRef.child(post_key).child("Achievements").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            int sum = 0;
                            for (DataSnapshot ds : dataSnapshot.getChildren()) {
                                Map<String,Object> map = (Map<String,Object>) ds.getValue();
                                Object score = map.get("score");
                                int exp = Integer.parseInt(String.valueOf(score));
                                sum += exp;

                                txtExp.setText("EXP: "+sum);
                                expBar.setProgress(sum);

                                if (sum <= 199) {
                                    myCompanyRef.child(post_key).child("company_level").setValue("1");
                                    expBar.getConfigBuilder().min(0).max(199).build();
                                    expBar.setProgress(sum);
                                } else if (sum >= 200 && sum <= 399) {
                                    myCompanyRef.child(post_key).child("company_level").setValue("2");
                                    expBar.getConfigBuilder().min(200).max(399).build();
                                    expBar.setProgress(sum);
                                } else if (sum >= 400 && sum <= 600) {
                                    myCompanyRef.child(post_key).child("company_level").setValue("3");
                                    expBar.getConfigBuilder().min(400).max(600).build();
                                    expBar.setProgress(sum);
                                }



                            }

                        }

                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
            }

            public void onCancelled(DatabaseError databaseError) {

            }
        });

        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                Intent intent = new Intent(CareManagerActivity.this, PhoneAuthActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

}
