package com.oalonedeveloper.oliver.oliverappandroidapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.oalonedeveloper.oliver.oliverappandroidapp.Companies.MyCompaniesActivity;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class PlatformSelectionActivity extends AppCompatActivity {

    CardView btnOliver,btnCare;
    CircleImageView profileImage;
    TextView txtName,txtUsername;
    DatabaseReference userRef;
    FirebaseAuth mAuth;
    String currentUserId,fullname,profileimage,username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_platform_selection);

        btnOliver = findViewById(R.id.btnOliver);
        btnCare = findViewById(R.id.btnCare);
        profileImage = findViewById(R.id.profileImage);
        txtName = findViewById(R.id.txtName);
        txtUsername = findViewById(R.id.txtUsername);

        mAuth = FirebaseAuth.getInstance();
        currentUserId = mAuth.getCurrentUser().getUid();
        userRef = FirebaseDatabase.getInstance().getReference().child("Users");

        userRef.child(currentUserId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                fullname = dataSnapshot.child("fullname").getValue().toString();
                profileimage = dataSnapshot.child("profileimage").getValue().toString();
                username = dataSnapshot.child("username").getValue().toString();

                Picasso.with(PlatformSelectionActivity.this).load(profileimage).fit().into(profileImage);
                txtName.setText(fullname);
                txtUsername.setText(username);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        btnOliver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnCare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PlatformSelectionActivity.this, MyCompaniesActivity.class);
                startActivity(intent);
            }
        });
    }
}
