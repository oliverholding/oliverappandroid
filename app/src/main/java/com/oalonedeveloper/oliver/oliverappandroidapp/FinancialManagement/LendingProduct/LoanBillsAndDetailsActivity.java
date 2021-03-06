package com.oalonedeveloper.oliver.oliverappandroidapp.FinancialManagement.LendingProduct;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
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
import com.oalonedeveloper.oliver.oliverappandroidapp.R;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class LoanBillsAndDetailsActivity extends AppCompatActivity {

    String product_key,institution_key, financial_institution_name,financial_institution_image,financial_institution_background_image;;
    DatabaseReference financialInstitutionsRef;
    TextView txtProductName,txtFinancialInstitutionName;
    ImageView imgBackground;
    CircleImageView imgProductImage;
    Fragment fragment1,fragment2;
    CardView tab1,tab2;
    TextView txt1,txt2;
    View view1,view2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_bills_and_details);

        product_key = getIntent().getExtras().getString("product_key");
        institution_key = getIntent().getExtras().getString("institution_key");
        financialInstitutionsRef = FirebaseDatabase.getInstance().getReference().child("Financial Institutions");

        txtProductName = findViewById(R.id.txtProductName);
        txtFinancialInstitutionName = findViewById(R.id.txtFinancialInstitutionName);
        imgBackground = findViewById(R.id.imgBackground);
        imgProductImage = findViewById(R.id.imgProductImage);

        tab1 = findViewById(R.id.tab1);
        tab2 = findViewById(R.id.tab2);
        txt1 = findViewById(R.id.txt1);
        txt2 = findViewById(R.id.txt2);
        view1 = findViewById(R.id.view1);
        view2 = findViewById(R.id.view2);

        fragment1 = new LoanBillsFragment();
        fragment2 = new LoanReadyDetailsFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.contentFragment,fragment1).commit();

        tab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                txt1.setTextColor(getResources().getColor(R.color.blue1));
                txt2.setTextColor(getResources().getColor(R.color.gray2));
                view1.setBackgroundColor(getResources().getColor(R.color.blue1));
                view2.setBackgroundColor(getResources().getColor(R.color.gray2));

                getSupportFragmentManager().beginTransaction().replace(R.id.contentFragment,fragment1).commit();
            }
        });

        tab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                txt2.setTextColor(getResources().getColor(R.color.blue1));
                txt1.setTextColor(getResources().getColor(R.color.gray2));
                view2.setBackgroundColor(getResources().getColor(R.color.blue1));
                view1.setBackgroundColor(getResources().getColor(R.color.gray2));

                getSupportFragmentManager().beginTransaction().replace(R.id.contentFragment,fragment2).commit();
            }
        });

        financialInstitutionsRef.child(institution_key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                financial_institution_name = dataSnapshot.child("financial_institution_name").getValue().toString();
                financial_institution_image = dataSnapshot.child("financial_institution_image").getValue().toString();
                financial_institution_background_image = dataSnapshot.child("financial_institution_background_image").getValue().toString();
                txtFinancialInstitutionName.setText("Por "+financial_institution_name);
                Picasso.with(LoanBillsAndDetailsActivity.this).load(financial_institution_background_image).fit().into(imgBackground);

                financialInstitutionsRef.child(institution_key).child("Products").child(product_key).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String product_name = dataSnapshot.child("product_name").getValue().toString();
                        String product_short_description = dataSnapshot.child("product_short_description").getValue().toString();
                        String product_completed_description = dataSnapshot.child("product_completed_description").getValue().toString();
                        String product_img = dataSnapshot.child("product_img").getValue().toString();

                        Picasso.with(LoanBillsAndDetailsActivity.this).load(product_img).fit().into(imgProductImage);
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

    }
}
