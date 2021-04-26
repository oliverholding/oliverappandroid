package com.oalonedeveloper.oliver.oliverappandroidapp.FinancialManagement.Factoring;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RelativeLayout;

import com.github.barteksc.pdfviewer.PDFView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.oalonedeveloper.oliver.oliverappandroidapp.FinancialManagement.CompanyLendingProduct.CompanyLoanBankAccountsFragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.FinancialManagement.CompanyLendingProduct.CompanyLoanContractFragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class FactoringContractFragment extends Fragment {

    PDFView pdfView;
    StorageReference mStorage;
    StorageReference ref;
    String operation_id,post_key,company_id;
    DatabaseReference lendingRef,financialInstitutionsRef,userRef,userRealRef;
    CheckBox checkBox;
    Button btnContinue;
    RelativeLayout rootLayout;
    Fragment fragment3;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_factoring_contract, container, false);

        lendingRef = FirebaseDatabase.getInstance().getReference().child("Factoring");
        financialInstitutionsRef = FirebaseDatabase.getInstance().getReference().child("Financial Institutions");
        userRealRef = FirebaseDatabase.getInstance().getReference().child("Users");
        pdfView = view.findViewById(R.id.pdfView);
        operation_id = getActivity().getIntent().getExtras().getString("operation_id");
        post_key = getActivity().getIntent().getExtras().getString("post_key");
        company_id = getActivity().getIntent().getExtras().getString("company_id");
        mStorage = FirebaseStorage.getInstance().getReference();
        userRef = FirebaseDatabase.getInstance().getReference().child("My Companies");
        checkBox = view.findViewById(R.id.checkBox);
        btnContinue = view.findViewById(R.id.btnContinue);

        fragment3 = new FactoringBankAccountsFragment();

        btnContinue.setVisibility(View.GONE);

        lendingRef.child(operation_id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String issuing_product_id = dataSnapshot.child("issuing_product_id").getValue().toString();
                String customer_id = dataSnapshot.child("customer_id").getValue().toString();

                userRef.child(customer_id).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        String uid = dataSnapshot.child("uid").getValue().toString();
                        final String company_social_reason = dataSnapshot.child("company_social_reason").getValue().toString();
                        userRealRef.child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                String document_number = dataSnapshot.child("document_number").getValue().toString();
                                String document_type = dataSnapshot.child("document_type").getValue().toString();
                                String fullname = dataSnapshot.child("fullname").getValue().toString();

                                checkBox.setText("Yo, "+fullname+" con "+document_type+": "+document_number+"representante legal de la empresa "+company_social_reason+" he leído y acepto las condiciones del presente contrato.");
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

                financialInstitutionsRef.child(post_key).child("Company Products").child(issuing_product_id).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        final String product_file1 = dataSnapshot.child("product_file1").getValue().toString();

                        new RetrivePdfStream().execute(product_file1);

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

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox.isChecked()) {
                    btnContinue.setVisibility(View.VISIBLE);

                } else {
                    btnContinue.setVisibility(View.GONE);
                }
            }
        });

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox.isChecked()) {
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.contentFragment,fragment3).commit();

                } else {
                    Snackbar.make(rootLayout,"Debes aceptar las condiciones",Snackbar.LENGTH_SHORT).show();
                }
            }
        });


        return view;
    }

    class RetrivePdfStream extends AsyncTask<String, Void, InputStream> {


        @Override
        protected InputStream doInBackground(String... strings) {
            InputStream inputStream = null;
            try {
                URL url = new URL(strings[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                if (urlConnection.getResponseCode() == 200) {
                    inputStream = new BufferedInputStream(urlConnection.getInputStream());
                }
            } catch (IOException e) {
                return null;
            }
            return inputStream;
        }

        @Override
        protected void onPostExecute(InputStream inputStream) {
            pdfView.fromStream(inputStream).load();
        }
    }
}
