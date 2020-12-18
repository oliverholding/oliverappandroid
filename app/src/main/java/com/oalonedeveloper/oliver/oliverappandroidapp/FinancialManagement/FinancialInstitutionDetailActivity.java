package com.oalonedeveloper.oliver.oliverappandroidapp.FinancialManagement;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class FinancialInstitutionDetailActivity extends AppCompatActivity {

    String post_key;
    DatabaseReference financialInstitutionsRef;
    String financial_institution_name,financial_institution_image,financial_institution_background_image;
    TextView txtFinancialInstitutionName;
    ImageView imgBackground;
    CircleImageView imgImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_financial_institution_detail);

        financialInstitutionsRef = FirebaseDatabase.getInstance().getReference().child("Financial Institutions");
        post_key = getIntent().getExtras().getString("post_key");

        txtFinancialInstitutionName = findViewById(R.id.txtFinancialInstitutionName);
        imgBackground = findViewById(R.id.imgBackground);
        imgImage = findViewById(R.id.imgImage);

        financialInstitutionsRef.child(post_key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                financial_institution_name = dataSnapshot.child("financial_institution_name").getValue().toString();
                financial_institution_image = dataSnapshot.child("financial_institution_image").getValue().toString();
                financial_institution_background_image = dataSnapshot.child("financial_institution_background_image").getValue().toString();
                txtFinancialInstitutionName.setText(financial_institution_name);
                Picasso.with(FinancialInstitutionDetailActivity.this).load(financial_institution_image).fit().into(imgImage);
                Picasso.with(FinancialInstitutionDetailActivity.this).load(financial_institution_background_image).fit().into(imgBackground);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
