package com.oalonedeveloper.oliver.oliverappandroidapp.FinancialManagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.oalonedeveloper.oliver.oliverappandroidapp.FinancialManagement.CompanyLendingProduct.CompanyFinancialProductLendingsListFragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.FinancialManagement.Factoring.ProductFactoringListFragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class FinancialInstitutionForCompaniesDetailActivityActivity extends AppCompatActivity {

    String post_key,company_id;
    DatabaseReference financialInstitutionsRef;
    String financial_institution_name,financial_institution_image,financial_institution_background_image,financial_institution_slogan,financial_institution_type;
    TextView txtFinancialInstitutionName,txtFinancialInstitutionSlogan,txtFinancialInstitutionType;
    ImageView imgBackground;
    CircleImageView imgImage;
    Fragment fragment1,fragment2;
    CardView tab1,tab2;
    TextView txt1,txt2;
    View view1,view2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_financial_institution_for_companies_detail_activity);

        financialInstitutionsRef = FirebaseDatabase.getInstance().getReference().child("Financial Institutions");
        post_key = getIntent().getExtras().getString("post_key");
        company_id = getIntent().getExtras().getString("company_id");

        txtFinancialInstitutionName = findViewById(R.id.txtFinancialInstitutionName);
        imgBackground = findViewById(R.id.imgBackground);
        imgImage = findViewById(R.id.imgImage);
        txtFinancialInstitutionSlogan = findViewById(R.id.txtFinancialInstitutionSlogan);
        txtFinancialInstitutionType = findViewById(R.id.txtFinancialInstitutionType);

        tab1 = findViewById(R.id.tab1);
        tab2 = findViewById(R.id.tab2);
        txt1 = findViewById(R.id.txt1);
        txt2 = findViewById(R.id.txt2);
        view1 = findViewById(R.id.view1);
        view2 = findViewById(R.id.view2);

        fragment1 = new CompanyFinancialProductLendingsListFragment();
        fragment2 = new ProductFactoringListFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.contentFragment,fragment1).commit();

        tab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt1.setTextColor(getResources().getColor(R.color.blue1));
                view1.setBackgroundResource(R.color.blue1);

                txt2.setTextColor(getResources().getColor(R.color.gray2));
                view2.setBackgroundResource(R.color.gray2);

                getSupportFragmentManager().beginTransaction().replace(R.id.contentFragment,fragment1).commit();
            }
        });

        tab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt2.setTextColor(getResources().getColor(R.color.blue1));
                view2.setBackgroundResource(R.color.blue1);

                txt1.setTextColor(getResources().getColor(R.color.gray2));
                view1.setBackgroundResource(R.color.gray2);

                getSupportFragmentManager().beginTransaction().replace(R.id.contentFragment,fragment2).commit();
            }
        });

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
                Picasso.with(FinancialInstitutionForCompaniesDetailActivityActivity.this).load(financial_institution_image).fit().into(imgImage);
                Picasso.with(FinancialInstitutionForCompaniesDetailActivityActivity.this).load(financial_institution_background_image).fit().into(imgBackground);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
