package com.oalonedeveloper.oliver.oliverappandroidapp.FinancialManagement.Factoring;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.oalonedeveloper.oliver.oliverappandroidapp.FinancialManagement.CompanyLendingProduct.CompanyLendingDetailActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.FinancialManagement.CompanyLendingProduct.CompanyLendingDetailsAndBenefitsFragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.FinancialManagement.CompanyLendingProduct.CompanyLoanRequestActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class FactoringDetailActivity extends AppCompatActivity {

    String product_key,company_id,institution_key, financial_institution_name,financial_institution_image,financial_institution_background_image;;
    DatabaseReference financialInstitutionsRef;
    TextView txtProductName,txtFinancialInstitutionName;
    ImageView imgBackground;
    CircleImageView imgProductImage;
    Button btnLoanRequest;
    Fragment fragment1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_factoring_detail);

        product_key = getIntent().getExtras().getString("product_key");
        company_id = getIntent().getExtras().getString("company_id");
        institution_key = getIntent().getExtras().getString("institution_key");
        financialInstitutionsRef = FirebaseDatabase.getInstance().getReference().child("Financial Institutions");

        txtProductName = findViewById(R.id.txtProductName);
        txtFinancialInstitutionName = findViewById(R.id.txtFinancialInstitutionName);
        imgBackground = findViewById(R.id.imgBackground);
        imgProductImage = findViewById(R.id.imgProductImage);
        btnLoanRequest = findViewById(R.id.btnLoanRequest);

        fragment1 = new CompanyLendingDetailsAndBenefitsFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.contentFragment,fragment1).commit();

        financialInstitutionsRef.child(institution_key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                financial_institution_name = dataSnapshot.child("financial_institution_name").getValue().toString();
                financial_institution_image = dataSnapshot.child("financial_institution_image").getValue().toString();
                financial_institution_background_image = dataSnapshot.child("financial_institution_background_image").getValue().toString();
                txtFinancialInstitutionName.setText("Por "+financial_institution_name);
                Picasso.with(FactoringDetailActivity.this).load(financial_institution_background_image).fit().into(imgBackground);

                financialInstitutionsRef.child(institution_key).child("Company Products").child(product_key).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String product_name = dataSnapshot.child("product_name").getValue().toString();
                        String product_short_description = dataSnapshot.child("product_short_description").getValue().toString();
                        String product_completed_description = dataSnapshot.child("product_completed_description").getValue().toString();
                        String product_img = dataSnapshot.child("product_img").getValue().toString();

                        Picasso.with(FactoringDetailActivity.this).load(product_img).fit().into(imgProductImage);
                        txtProductName.setText(product_name);

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        btnLoanRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FactoringDetailActivity.this, FactoringRequestActivity.class);
                intent.putExtra("company_id",company_id);
                intent.putExtra("product_key",product_key);
                intent.putExtra("institution_key",institution_key);
                startActivity(intent);
            }
        });
    }
}
