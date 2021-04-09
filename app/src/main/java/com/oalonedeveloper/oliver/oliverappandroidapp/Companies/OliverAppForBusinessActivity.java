package com.oalonedeveloper.oliver.oliverappandroidapp.Companies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.content.Intent;
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
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.CareManagerActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.FinancialManagement.FinancialInstitutionsFragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.FinancialManagement.FinancialProductsFragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.FinancialManagement.LendingProduct.FinancialInstitutionsForCompaniesFragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.FinancialManagement.MyProductsFragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.FinancialManagement.NonProductsFragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.OliverAppActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class OliverAppForBusinessActivity extends AppCompatActivity {

    String post_key,currentUid;
    DatabaseReference companyRef;
    CardView tab1,tab2,tab3;
    LinearLayout linearLayout1,linearLayout2,linearLayout3;
    TextView txtText1,txtText2,txtText3;
    Fragment fragment1,fragment2,fragment3,fragment4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oliver_app_for_business);

        post_key = getIntent().getExtras().getString("post_key");
        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");

        tab1 = findViewById(R.id.tab1);
        tab2 = findViewById(R.id.tab2);
        tab3 = findViewById(R.id.tab3);
        linearLayout1 = findViewById(R.id.linearLayout1);
        linearLayout2 = findViewById(R.id.linearLayout2);
        linearLayout3 = findViewById(R.id.linearLayout3);
        txtText1 = findViewById(R.id.txtText1);
        txtText2 = findViewById(R.id.txtText2);
        txtText3 = findViewById(R.id.txtText3);

        currentUid = post_key;

        fragment1 = new NonProductsFragment();
        //fragment2 = new MyProductsFragment();
        fragment3 = new FinancialInstitutionsForCompaniesFragment();
        //fragment4 = new FinancialProductsFragment();

        companyRef.child(currentUid).child("Financial Products").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    //getSupportFragmentManager().beginTransaction().replace(R.id.contentFragment,fragment2).commit();
                } else {
                    //getSupportFragmentManager().beginTransaction().replace(R.id.contentFragment,fragment1).commit();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        linearLayout1.setBackgroundResource(R.drawable.blue_button_rectangle);
        txtText1.setTextColor(Color.WHITE);

        tab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                companyRef.child(currentUid).child("Financial Products").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            getSupportFragmentManager().beginTransaction().replace(R.id.contentFragment,fragment2).commit();
                        } else {
                            getSupportFragmentManager().beginTransaction().replace(R.id.contentFragment,fragment1).commit();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                linearLayout1.setBackgroundResource(R.drawable.blue_button_rectangle);
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
                getSupportFragmentManager().beginTransaction().replace(R.id.contentFragment,fragment4).commit();

                linearLayout2.setBackgroundResource(R.drawable.blue_button_rectangle);
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

                linearLayout3.setBackgroundResource(R.drawable.blue_button_rectangle);
                txtText3.setTextColor(Color.WHITE);

                linearLayout1.setBackgroundResource(0);
                txtText1.setTextColor(Color.GRAY);

                linearLayout2.setBackgroundResource(0);
                txtText2.setTextColor(Color.GRAY);


            }
        });



    }
}
