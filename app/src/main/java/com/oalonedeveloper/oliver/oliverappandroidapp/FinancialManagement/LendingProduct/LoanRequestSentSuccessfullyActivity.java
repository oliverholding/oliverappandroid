package com.oalonedeveloper.oliver.oliverappandroidapp.FinancialManagement.LendingProduct;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;
import com.squareup.picasso.Picasso;

public class LoanRequestSentSuccessfullyActivity extends AppCompatActivity {

    ImageView imgImage,imgBackgroundButton,imgBackground;
    TextView txtMessage,txtButtonAction;
    LinearLayout btnActionButton;
    DatabaseReference financialInstitutionsRef;
    String product_key,institution_key,financial_institution_name,financial_institution_image,financial_institution_background_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_request_sent_successfully);

        imgImage = findViewById(R.id.imgImage);
        imgBackgroundButton = findViewById(R.id.imgBackgroundButton);
        txtMessage = findViewById(R.id.txtMessage);
        txtButtonAction = findViewById(R.id.txtButtonAction);
        btnActionButton = findViewById(R.id.btnActionButton);
        imgBackground = findViewById(R.id.imgBackground);
        financialInstitutionsRef = FirebaseDatabase.getInstance().getReference().child("Financial Institutions");
        institution_key = getIntent().getExtras().getString("institution_key");

        financialInstitutionsRef.child(institution_key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                financial_institution_name = dataSnapshot.child("financial_institution_name").getValue().toString();
                financial_institution_image = dataSnapshot.child("financial_institution_image").getValue().toString();
                financial_institution_background_image = dataSnapshot.child("financial_institution_background_image").getValue().toString();

                Picasso.with(LoanRequestSentSuccessfullyActivity.this).load(financial_institution_image).fit().centerCrop().into(imgImage);
                Picasso.with(LoanRequestSentSuccessfullyActivity.this).load(financial_institution_background_image).fit().into(imgBackgroundButton);
                Picasso.with(LoanRequestSentSuccessfullyActivity.this).load(financial_institution_background_image).fit().into(imgBackground);
                txtMessage.setText("Solicitud enviada con éxito a "+financial_institution_name);
                txtButtonAction.setText("VER TODAS MIS SOLICITUDES EN "+financial_institution_name.toUpperCase());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
