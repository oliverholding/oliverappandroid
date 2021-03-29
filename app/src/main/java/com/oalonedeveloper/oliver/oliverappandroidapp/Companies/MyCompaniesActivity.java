package com.oalonedeveloper.oliver.oliverappandroidapp.Companies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.CareManagerActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Commercialization.Dashboards.GraphicsFragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Commercialization.Dashboards.ProductRankingFragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.InformalCompanies.AddInformalCompanyActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyCompaniesActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    String currentUid;
    DatabaseReference companyRef;
    long company_count;
    Fragment fragment1,fragment2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_companies);

        mAuth = FirebaseAuth.getInstance();
        currentUid = mAuth.getCurrentUser().getUid();

        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");

        fragment1 = new NonCompaniesFragment();
        fragment2 = new ShowCompaniesFragment();

        companyRef.orderByChild("uid").startAt(currentUid).endAt(currentUid+"\uf8ff").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                company_count = dataSnapshot.getChildrenCount();

                if (company_count == 0) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.contentFragment,fragment1).commit();
                } else {
                    getSupportFragmentManager().beginTransaction().replace(R.id.contentFragment,fragment2).commit();
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




    }


}