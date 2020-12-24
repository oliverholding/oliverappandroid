package com.oalonedeveloper.oliver.oliverappandroidapp.FinancialManagement.LendingProduct;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
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

import de.hdodenhof.circleimageview.CircleImageView;

public class LoanRequestActivity extends AppCompatActivity {

    String product_key,institution_key, financial_institution_name,financial_institution_image,financial_institution_background_image,currentUid, request_id;
    TextView txtProductName,txtFinancialInstitutionName;
    DatabaseReference financialInstitutionsRef,expressLoanRef,toPath;
    ImageView imgBackground,imgBackgroundButton,imgBackgroundSimulation;
    CircleImageView imgProductImage,imgFinancialInstitution;
    LinearLayout btnRequestLoan;
    RecyclerView recyclerView;
    FirebaseAuth mAuth;
    LinearLayout btnSimulation;

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
        toPath = FirebaseDatabase.getInstance().getReference().child("Financial Institutions").child(institution_key).child("Loan Requests").child(product_key);

        txtProductName = findViewById(R.id.txtProductName);
        txtFinancialInstitutionName = findViewById(R.id.txtFinancialInstitutionName);
        imgBackground = findViewById(R.id.imgBackground);
        imgProductImage = findViewById(R.id.imgProductImage);
        imgBackgroundButton = findViewById(R.id.imgBackgroundButton);
        btnRequestLoan = findViewById(R.id.btnRequestLoan);
        imgFinancialInstitution = findViewById(R.id.imgFinancialInstitution);
        imgBackgroundSimulation = findViewById(R.id.imgBackgroundSimulation);
        btnSimulation = findViewById(R.id.btnSimulation);

        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(false);
        linearLayoutManager.setStackFromEnd(false);
        recyclerView.setLayoutManager(linearLayoutManager);

        showDocumentRequired();

        financialInstitutionsRef.child(institution_key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                financial_institution_name = dataSnapshot.child("financial_institution_name").getValue().toString();
                financial_institution_image = dataSnapshot.child("financial_institution_image").getValue().toString();
                financial_institution_background_image = dataSnapshot.child("financial_institution_background_image").getValue().toString();
                txtFinancialInstitutionName.setText("Por "+financial_institution_name);
                Picasso.with(LoanRequestActivity.this).load(financial_institution_background_image).fit().into(imgBackground);
                Picasso.with(LoanRequestActivity.this).load(financial_institution_background_image).fit().into(imgBackgroundButton);
                Picasso.with(LoanRequestActivity.this).load(financial_institution_background_image).fit().into(imgBackgroundSimulation);
                Picasso.with(LoanRequestActivity.this).load(financial_institution_image).fit().into(imgFinancialInstitution);

                financialInstitutionsRef.child(institution_key).child("Products").child(product_key).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String product_name = dataSnapshot.child("product_name").getValue().toString();
                        String product_short_description = dataSnapshot.child("product_short_description").getValue().toString();
                        String product_completed_description = dataSnapshot.child("product_completed_description").getValue().toString();
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

        btnRequestLoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerLoanRequest();
            }
        });

        btnSimulation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoanRequestActivity.this, LoanSimulationActivity.class);
                intent.putExtra("product_key",product_key);
                intent.putExtra("institution_key",institution_key);
                startActivity(intent);
            }
        });
    }

    private void registerLoanRequest() {

        Calendar calForDate = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("ddMMyyyy");
        String saveCurrentDate = currentDate.format(calForDate.getTime());

        Calendar calForTime = Calendar.getInstance();
        SimpleDateFormat currentTime = new SimpleDateFormat("HHmmss");
        String saveCurrentTime = currentTime.format(calForTime.getTime());

        request_id = saveCurrentDate+saveCurrentTime;

        expressLoanRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                toPath.child(request_id+currentUid).setValue(dataSnapshot.getValue(), new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                        if (databaseError != null) {
                            Toast.makeText(LoanRequestActivity.this, "Hubo un Error", Toast.LENGTH_SHORT).show();
                        } else {

                            toPath.child(request_id+currentUid).child("customer_id").setValue(currentUid);

                            expressLoanRef.removeValue();

                        }
                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void showDocumentRequired() {
        Query query = financialInstitutionsRef.child(institution_key).child("Products").child(product_key).child("Required Documentation").orderByChild("step");
        FirebaseRecyclerAdapter<DocumentRequiredModel, DocumentRequiredViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<DocumentRequiredModel, DocumentRequiredViewHolder>
                (DocumentRequiredModel.class, R.layout.financial_institution_documentation_customer_product_required_item,DocumentRequiredViewHolder.class,query) {
            @Override
            protected void populateViewHolder(DocumentRequiredViewHolder viewHolder, DocumentRequiredModel model, int position) {
                final String postKey = getRef(position).getKey();
                viewHolder.setDoc_description(model.getDoc_description());
                viewHolder.setDoc_tittle(model.getDoc_tittle());
                viewHolder.setStep(model.getStep());

                viewHolder.txtDocName.setText(viewHolder.my_step+". "+viewHolder.my_doc_tittle);
                Picasso.with(LoanRequestActivity.this).load(financial_institution_background_image).fit().into(viewHolder.imgBackground);

                viewHolder.btnActionButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(LoanRequestActivity.this, UploadDocumentationActivity.class);
                        intent.putExtra("product_key",product_key);
                        intent.putExtra("institution_key",institution_key);
                        intent.putExtra("doc_key",postKey);
                        startActivity(intent);
                    }
                });

            }
        };
        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    public static class DocumentRequiredViewHolder extends RecyclerView.ViewHolder {
        View mView;
        CircleImageView imgImageState;
        TextView txtDocName,txtDocState;
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
