package com.oalonedeveloper.oliver.oliverappandroidapp.FinancialManagement.CompanyLendingProduct;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.oalonedeveloper.oliver.oliverappandroidapp.FinancialManagement.Factoring.FactoringBillsAndDetailsActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.FinancialManagement.Factoring.FactoringInProcessToGetActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.FinancialManagement.LendingProduct.LoanBillsAndDetailsActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.FinancialManagement.LendingProduct.LoanInProcessToGetActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.FinancialManagement.MyProductsModel;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyCompanyProductsFragment extends Fragment {

    RecyclerView recyclerView;
    DatabaseReference userRef,lendingRef,financialInstitutionsRef,factoringRef;

    String currentUid,post_key;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_company_products, container, false);

        post_key = getActivity().getIntent().getExtras().getString("post_key");

        currentUid = post_key;

        recyclerView = view.findViewById(R.id.recyclerView);
        userRef = FirebaseDatabase.getInstance().getReference().child("My Companies");
        lendingRef = FirebaseDatabase.getInstance().getReference().child("Company Lendings");
        factoringRef = FirebaseDatabase.getInstance().getReference().child("Factoring");
        financialInstitutionsRef = FirebaseDatabase.getInstance().getReference().child("Financial Institutions");

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setReverseLayout(false);
        linearLayoutManager.setStackFromEnd(false);
        recyclerView.setLayoutManager(linearLayoutManager);

        showMyProducts();

        return view;
    }

    private void showMyProducts() {
        Query query = userRef.child(currentUid).child("Financial Products").orderByChild("timestamp");
        FirebaseRecyclerAdapter<MyProductsModel,MyProductsViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<MyProductsModel, MyProductsViewHolder>
                (MyProductsModel.class,R.layout.my_financial_product_item,MyProductsViewHolder.class,query) {
            @Override
            protected void populateViewHolder(final MyProductsViewHolder viewHolder, MyProductsModel model, int position) {
                final String postKey = getRef(position).getKey();
                viewHolder.setAmount(model.getAmount());
                viewHolder.setFinancial_institution_id(model.getFinancial_institution_id());
                viewHolder.setOperation_id(model.getOperation_id());
                viewHolder.setProduct_type(model.getProduct_type());

                lendingRef.child(viewHolder.my_operation_id).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            String issuing_product_id = dataSnapshot.child("issuing_product_id").getValue().toString();

                            financialInstitutionsRef.child(viewHolder.my_financial_institution_id).child("Company Products").child(issuing_product_id).addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    String product_name = dataSnapshot.child("product_name").getValue().toString();
                                    String product_img = dataSnapshot.child("product_img").getValue().toString();
                                    viewHolder.txtProductName.setText("Producto: " + product_name);
                                    Picasso.with(getActivity()).load(product_img).fit().into(viewHolder.imgProduct);

                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });
                        } else {
                            factoringRef.child(viewHolder.my_operation_id).addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    if (dataSnapshot.exists()) {
                                        String issuing_product_id = dataSnapshot.child("issuing_product_id").getValue().toString();

                                        financialInstitutionsRef.child(viewHolder.my_financial_institution_id).child("Company Products").child(issuing_product_id).addValueEventListener(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                String product_name = dataSnapshot.child("product_name").getValue().toString();
                                                String product_img = dataSnapshot.child("product_img").getValue().toString();
                                                viewHolder.txtProductName.setText("Producto: "+product_name);
                                                Picasso.with(getActivity()).load(product_img).fit().into(viewHolder.imgProduct);

                                            }

                                            @Override
                                            public void onCancelled(DatabaseError databaseError) {

                                            }
                                        });
                                    }


                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });
                        }


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                financialInstitutionsRef.child(viewHolder.my_financial_institution_id).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String financial_institution_name = dataSnapshot.child("financial_institution_name").getValue().toString();
                        viewHolder.txtFinancialInstitutionName.setText("Inst. Financiera: "+financial_institution_name);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                viewHolder.btnDetails.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        lendingRef.child(viewHolder.my_operation_id).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                if (dataSnapshot.exists()) {
                                    String lending_state = dataSnapshot.child("lending_state").getValue().toString();

                                    if (lending_state.equals("approved")) {
                                        Intent intent = new Intent(getActivity(), CompanyLoanBillsAndDetailsActivity.class);
                                        intent.putExtra("operation_id", viewHolder.my_operation_id);
                                        intent.putExtra("company_id", post_key);
                                        intent.putExtra("institution_key", viewHolder.my_financial_institution_id);
                                        startActivity(intent);
                                    }
                                    if (lending_state.equals("ready")) {

                                        lendingRef.child(viewHolder.my_operation_id).addValueEventListener(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                String issuing_product_id = dataSnapshot.child("issuing_product_id").getValue().toString();
                                                Intent intent = new Intent(getActivity(), CompanyLoanBillsAndDetailsActivity.class);
                                                intent.putExtra("product_key", issuing_product_id);
                                                intent.putExtra("company_id", post_key);
                                                intent.putExtra("institution_key", viewHolder.my_financial_institution_id);
                                                intent.putExtra("operation_id", viewHolder.my_operation_id);
                                                startActivity(intent);
                                            }

                                            @Override
                                            public void onCancelled(DatabaseError databaseError) {

                                            }
                                        });
                                    }
                                } else {
                                    factoringRef.child(viewHolder.my_operation_id).addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {
                                            String lending_state = dataSnapshot.child("factoring_state").getValue().toString();

                                            if (lending_state.equals("approved")) {
                                                Intent intent = new Intent(getActivity(), FactoringInProcessToGetActivity.class);
                                                intent.putExtra("operation_id", viewHolder.my_operation_id);
                                                intent.putExtra("company_id", post_key);
                                                intent.putExtra("institution_key", viewHolder.my_financial_institution_id);
                                                startActivity(intent);
                                            }
                                            if (lending_state.equals("ready")) {

                                                factoringRef.child(viewHolder.my_operation_id).addValueEventListener(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                                        String issuing_product_id = dataSnapshot.child("issuing_product_id").getValue().toString();
                                                        Intent intent = new Intent(getActivity(), FactoringBillsAndDetailsActivity.class);
                                                        intent.putExtra("product_key", issuing_product_id);
                                                        intent.putExtra("company_id", post_key);
                                                        intent.putExtra("institution_key", viewHolder.my_financial_institution_id);
                                                        intent.putExtra("operation_id", viewHolder.my_operation_id);
                                                        startActivity(intent);
                                                    }

                                                    @Override
                                                    public void onCancelled(DatabaseError databaseError) {

                                                    }
                                                });
                                            }
                                        }

                                        @Override
                                        public void onCancelled(DatabaseError databaseError) {

                                        }
                                    });
                                }


                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                    }
                });

            }
        };
        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    public static class MyProductsViewHolder extends RecyclerView.ViewHolder {

        View mView;
        String my_amount,my_financial_institution_id,my_operation_id,my_product_type;
        CircleImageView imgProduct;
        TextView txtProductName,txtFinancialInstitutionName;
        Button btnDetails;

        public MyProductsViewHolder(@NonNull View itemView) {
            super(itemView);

            mView = itemView;

            imgProduct = mView.findViewById(R.id.imgProduct);
            txtProductName = mView.findViewById(R.id.txtProductName);
            txtFinancialInstitutionName = mView.findViewById(R.id.txtFinancialInstitutionName);
            btnDetails = mView.findViewById(R.id.btnDetails);
        }

        public void setAmount(String amount) {
            my_amount = amount;
        }

        public void setFinancial_institution_id(String financial_institution_id) {
            my_financial_institution_id = financial_institution_id;
        }


        public void setOperation_id(String operation_id) {
            my_operation_id = operation_id;
        }

        public void setProduct_type(String product_type) {
            my_product_type = product_type;
        }
    }
}
