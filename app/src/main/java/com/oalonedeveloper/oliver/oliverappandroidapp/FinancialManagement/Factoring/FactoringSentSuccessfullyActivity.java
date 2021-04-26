package com.oalonedeveloper.oliver.oliverappandroidapp.FinancialManagement.Factoring;

import androidx.appcompat.app.AppCompatActivity;

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
import com.oalonedeveloper.oliver.oliverappandroidapp.FinancialManagement.CompanyLendingProduct.CompanyLoanRequestSentSuccessfullyActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.FinancialManagement.CompanyLendingProduct.CompanyLoanRequestsListActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class FactoringSentSuccessfullyActivity extends AppCompatActivity {

    String product_key,company_id,institution_key,financial_institution_name,financial_institution_image,financial_institution_background_image;
    TextView txtFinancialInstitutionName;
    TextView txtMessage,txtProductName;
    DatabaseReference financialInstitutionsRef;
    ImageView imgBackground;
    CircleImageView imgProductImage;
    Button btnRequests;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_factoring_sent_successfully);

        txtMessage = findViewById(R.id.txtMessage);
        txtFinancialInstitutionName = findViewById(R.id.txtFinancialInstitutionName);
        txtProductName = findViewById(R.id.txtProductName);
        imgBackground = findViewById(R.id.imgBackground);
        imgProductImage = findViewById(R.id.imgProductImage);
        btnRequests = findViewById(R.id.btnRequests);


        product_key = getIntent().getExtras().getString("product_key");
        company_id = getIntent().getExtras().getString("company_id");
        institution_key = getIntent().getExtras().getString("institution_key");

        financialInstitutionsRef = FirebaseDatabase.getInstance().getReference().child("Financial Institutions");

        financialInstitutionsRef.child(institution_key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                financial_institution_name = dataSnapshot.child("financial_institution_name").getValue().toString();
                financial_institution_image = dataSnapshot.child("financial_institution_image").getValue().toString();
                financial_institution_background_image = dataSnapshot.child("financial_institution_background_image").getValue().toString();
                txtFinancialInstitutionName.setText("Por "+financial_institution_name);
                Picasso.with(FactoringSentSuccessfullyActivity.this).load(financial_institution_background_image).fit().into(imgBackground);

                financialInstitutionsRef.child(institution_key).child("Company Products").child(product_key).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String product_name = dataSnapshot.child("product_name").getValue().toString();
                        String product_img = dataSnapshot.child("product_img").getValue().toString();

                        Picasso.with(FactoringSentSuccessfullyActivity.this).load(product_img).fit().into(imgProductImage);
                        txtProductName.setText(product_name);

                        txtMessage.setText("Solicitud enviada con éxito a "+financial_institution_name);

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

        btnRequests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FactoringSentSuccessfullyActivity.this, FactoringRequestsListActivity.class);
                intent.putExtra("post_key",institution_key);
                intent.putExtra("company_id",company_id);
                startActivity(intent);
                finish();
            }
        });
    }
}
