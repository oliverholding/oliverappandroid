package com.oalonedeveloper.oliver.oliverappandroidapp.FinancialManagement.Factoring;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.oalonedeveloper.oliver.oliverappandroidapp.FinancialManagement.CompanyLendingProduct.CompanyLendingConditionsActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.FinancialManagement.CompanyLendingProduct.CompanyLoanConditionsFragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class FactoringConditionsActivity extends AppCompatActivity {

    String post_key,company_id;
    DatabaseReference financialInstitutionsRef;
    String financial_institution_name,financial_institution_image,financial_institution_background_image,financial_institution_slogan,financial_institution_type;
    TextView txtFinancialInstitutionName,txtFinancialInstitutionSlogan,txtFinancialInstitutionType;
    ImageView imgBackground;
    CircleImageView imgImage;
    Fragment fragment1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_factoring_conditions);

        financialInstitutionsRef = FirebaseDatabase.getInstance().getReference().child("Financial Institutions");
        post_key = getIntent().getExtras().getString("post_key");
        company_id = getIntent().getExtras().getString("company_id");

        txtFinancialInstitutionName = findViewById(R.id.txtFinancialInstitutionName);
        imgBackground = findViewById(R.id.imgBackground);
        imgImage = findViewById(R.id.imgImage);
        txtFinancialInstitutionSlogan = findViewById(R.id.txtFinancialInstitutionSlogan);
        txtFinancialInstitutionType = findViewById(R.id.txtFinancialInstitutionType);

        fragment1 = new FactoringConditionsFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.contentFragment,fragment1).commit();

        financialInstitutionsRef.child(post_key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                financial_institution_name = dataSnapshot.child("financial_institution_name").getValue().toString();
                financial_institution_image = dataSnapshot.child("financial_institution_image").getValue().toString();
                financial_institution_background_image = dataSnapshot.child("financial_institution_background_image").getValue().toString();
                financial_institution_slogan = dataSnapshot.child("financial_institution_slogan").getValue().toString();
                financial_institution_type = dataSnapshot.child("financial_institution_type").getValue().toString();
                txtFinancialInstitutionName.setText(financial_institution_name.toUpperCase());
                txtFinancialInstitutionSlogan.setText(financial_institution_slogan);
                txtFinancialInstitutionType.setText(financial_institution_type);
                Picasso.with(FactoringConditionsActivity.this).load(financial_institution_image).fit().into(imgImage);
                Picasso.with(FactoringConditionsActivity.this).load(financial_institution_background_image).fit().into(imgBackground);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
