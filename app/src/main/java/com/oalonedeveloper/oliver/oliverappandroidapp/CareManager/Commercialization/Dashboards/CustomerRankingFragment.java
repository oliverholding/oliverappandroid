package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Commercialization.Dashboards;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.oalonedeveloper.oliver.oliverappandroidapp.Companies.CompanyCustomersModel;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;

import java.text.DecimalFormat;
import java.util.Map;


public class CustomerRankingFragment extends Fragment {

    DatabaseReference companyRef;
    String post_key,percent_st,total_amount_st;
    double sum,sum1;
    DecimalFormat decimalFormat;
    RecyclerView recyclerView;
    double percent_v,total_amount_db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_customer_ranking, container, false);

        post_key = getActivity().getIntent().getExtras().getString("post_key");
        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");

        decimalFormat = new DecimalFormat("0.00");

        recyclerView = view.findViewById(R.id.recyclerView);

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        showCustomers();

        return view;
    }

    private void showCustomers() {
        Query query = companyRef.child(post_key).child("Customers").orderByChild("purchased_total_amount").limitToLast(10);
        FirebaseRecyclerAdapter<CompanyCustomersModel, CustomersViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<CompanyCustomersModel, CustomersViewHolder>
                (CompanyCustomersModel.class,R.layout.customer_ranking_item, CustomersViewHolder.class,query) {
            @Override
            protected void populateViewHolder(final CustomersViewHolder viewHolder, CompanyCustomersModel model, int position) {
                final String postKey = getRef(position).getKey();
                viewHolder.setCustomer_name(model.getCustomer_name());

                viewHolder.txtCustomerName.setText(viewHolder.my_customer_name);

                companyRef.child(post_key).child("My Bills").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        sum1 = 0;
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            Map<String, Object> map = (Map<String, Object>) ds.getValue();
                            Object bill_amount = map.get("bill_amount");
                            double total_value = Double.parseDouble(String.valueOf(bill_amount));
                            sum1 += total_value;

                            companyRef.child(post_key).child("Customers").child(postKey).child("Purchased").addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    if (dataSnapshot.exists()) {
                                        sum = 0;

                                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                                            Map<String, Object> map = (Map<String, Object>) ds.getValue();
                                            Object bill_amount = map.get("bill_amount");
                                            double total_value = Double.parseDouble(String.valueOf(bill_amount));
                                            sum += total_value;
                                            total_amount_st = decimalFormat.format(sum);
                                            total_amount_db = Double.parseDouble(total_amount_st);

                                            double percent_vale = (sum/sum1)*100;
                                            percent_st = decimalFormat.format(percent_vale);
                                            percent_v = Double.parseDouble(percent_st);

                                        }
                                        companyRef.child(post_key).child("Customers").child(postKey).child("purchased_total_amount").setValue(total_amount_db);
                                        viewHolder.txtCustomerPercent.setText(percent_st+"%");

                                        companyRef.child(post_key).child("Customers").child(postKey).addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                double purchased_total_amount = dataSnapshot.child("purchased_total_amount").getValue(Double.class);
                                                viewHolder.txtCustomerAmount.setText("S/ "+purchased_total_amount);

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
        };
        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    public static class CustomersViewHolder extends RecyclerView.ViewHolder {
        View mView;
        TextView txtCustomerName,txtCustomerAmount,txtCustomerPercent;
        String my_customer_name;

        public CustomersViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;

            txtCustomerName = mView.findViewById(R.id.txtCustomerName);
            txtCustomerAmount = mView.findViewById(R.id.txtCustomerAmount);
            txtCustomerPercent = mView.findViewById(R.id.txtCustomerPercent);

        }
        public void setCustomer_name(String customer_name) {
            my_customer_name = customer_name;
        }
    }
}
