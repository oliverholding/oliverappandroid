package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hsalf.smileyrating.SmileyRating;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Commercialization.BillsIssuing.CreateInvoiceActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;

import es.dmoral.toasty.Toasty;

public class ModuleQualificationActivity extends AppCompatActivity {

    SmileyRating smile_rating;
    Button btnSendRating;
    DatabaseReference userRef, moduleRatingRef;
    FirebaseAuth mAuth;
    String currentUid, path,post_key;
    RelativeLayout rootLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module_qualification);

        smile_rating = findViewById(R.id.smile_rating);
        btnSendRating = findViewById(R.id.btnSendRating);
        rootLayout = findViewById(R.id.rootLayout);
        post_key = getIntent().getExtras().getString("post_key");

        mAuth = FirebaseAuth.getInstance();
        currentUid = mAuth.getCurrentUser().getUid();

        path = getIntent().getExtras().getString("path");

        userRef = FirebaseDatabase.getInstance().getReference().child("Users");
        moduleRatingRef = FirebaseDatabase.getInstance().getReference().child("Module Ratings");

        smile_rating.setTitle(SmileyRating.Type.TERRIBLE,"Malo");
        smile_rating.setTitle(SmileyRating.Type.BAD,"Regular");
        smile_rating.setTitle(SmileyRating.Type.OKAY,"Bueno");
        smile_rating.setTitle(SmileyRating.Type.GOOD,"Muy Bueno");
        smile_rating.setTitle(SmileyRating.Type.GREAT,"Excelente");

        btnSendRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int rating = smile_rating.getSelectedSmiley().getRating();
                if (rating == -1) {
                    Snackbar.make(rootLayout, "Debes seleccionar una calificación", Snackbar.LENGTH_SHORT).show();
                } else {
                    moduleRatingRef.child(path).child(currentUid).setValue(rating);
                    Intent intent = new Intent(ModuleQualificationActivity.this, CareManagerActivity.class);
                    intent.putExtra("post_key", post_key);
                    Toasty.success(ModuleQualificationActivity.this, "Calificación Enviada", Toast.LENGTH_LONG).show();
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}
