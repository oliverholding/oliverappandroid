package com.oalonedeveloper.oliver.oliverappandroidapp.FinancialManagement.LendingProduct;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

public class LoanRequestActivity extends AppCompatActivity {

    String product_key,institution_key, financial_institution_name,financial_institution_image,financial_institution_background_image,currentUid, request_id, simulation_verification,document_number;
    TextView txtProductName,txtFinancialInstitutionName,txtDocState,txtButton;
    DatabaseReference financialInstitutionsRef,expressLoanRef,toPath,userRef;
    ImageView imgBackground,imgBackgroundButton,btnArrowSimulation;
    CircleImageView imgProductImage,imgFinancialInstitution;
    LinearLayout btnRequestLoan;
    RecyclerView recyclerView;
    FirebaseAuth mAuth;
    LinearLayout simulationLayout;
    RelativeLayout rootLayout;
    String item_verify_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_request);

        mAuth = FirebaseAuth.getInstance();
        currentUid = mAuth.getCurrentUser().getUid();

        product_key = getIntent().getExtras().getString("product_key");
        institution_key = getIntent().getExtras().getString("institution_key");
        financialInstitutionsRef = FirebaseDatabase.getInstance().getReference().child("Financial Institutions");
        expressLoanRef = FirebaseDatabase.getInstance().getReference().child("Users").child(currentUid).child(institution_key).child(product_key);
        toPath = FirebaseDatabase.getInstance().getReference().child("Financial Institutions").child(institution_key).child("Loan Requests");
        userRef = FirebaseDatabase.getInstance().getReference().child("Users");

        txtProductName = findViewById(R.id.txtProductName);
        txtFinancialInstitutionName = findViewById(R.id.txtFinancialInstitutionName);
        imgBackground = findViewById(R.id.imgBackground);
        imgProductImage = findViewById(R.id.imgProductImage);
        imgBackgroundButton = findViewById(R.id.imgBackgroundButton);
        btnRequestLoan = findViewById(R.id.btnRequestLoan);
        btnArrowSimulation = findViewById(R.id.btnArrowSimulation);
        simulationLayout = findViewById(R.id.simulationLayout);

        item_verify_1 = "close";
        simulationLayout.setVisibility(View.GONE);

        btnArrowSimulation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (item_verify_1.equals("close")) {
                    btnArrowSimulation.setImageResource(R.drawable.blue_arrow_up_vector_asset);
                    simulationLayout.setVisibility(View.VISIBLE);
                    item_verify_1 = "open";

                } else if (item_verify_1.equals("open")) {
                    btnArrowSimulation.setImageResource(R.drawable.blue_arrow_down_vector_asset);
                    simulationLayout.setVisibility(View.GONE);
                    item_verify_1 = "close";
                }
            }
        });

        rootLayout = findViewById(R.id.rootLayout);

        financialInstitutionsRef.child(institution_key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                financial_institution_name = dataSnapshot.child("financial_institution_name").getValue().toString();
                financial_institution_image = dataSnapshot.child("financial_institution_image").getValue().toString();
                financial_institution_background_image = dataSnapshot.child("financial_institution_background_image").getValue().toString();
                txtFinancialInstitutionName.setText("Por "+financial_institution_name);
                Picasso.with(LoanRequestActivity.this).load(financial_institution_background_image).fit().into(imgBackground);

                financialInstitutionsRef.child(institution_key).child("Products").child(product_key).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String product_name = dataSnapshot.child("product_name").getValue().toString();
                        String product_img = dataSnapshot.child("product_img").getValue().toString();

                        Picasso.with(LoanRequestActivity.this).load(product_img).fit().into(imgProductImage);
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

    public static class DocumentRequiredViewHolder extends RecyclerView.ViewHolder {
        View mView;
        CircleImageView imgImageState;
        TextView txtDocName,txtDocState,txtButtonAction;
        ImageView imgBackground;
        LinearLayout btnActionButton;
        String my_doc_description,my_doc_tittle,my_step;

        public DocumentRequiredViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;

            imgImageState = mView.findViewById(R.id.imgImageState);
            txtDocName = mView.findViewById(R.id.txtDocName);
            txtDocState = mView.findViewById(R.id.txtDocState);
            btnActionButton = mView.findViewById(R.id.btnActionButton);
            imgBackground = mView.findViewById(R.id.imgBackground);
            txtButtonAction = mView.findViewById(R.id.txtButtonAction);

        }

        public void setDoc_description(String doc_description) {
            my_doc_description = doc_description;
        }


        public void setDoc_tittle(String doc_tittle) {
            my_doc_tittle = doc_tittle;
        }


        public void setStep(String step) {
            my_step = step;
        }
    }
}
