package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Logistic.SupplierEvaluation;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;


public class SupplierEvaluationTimeFragment extends Fragment {

    DatabaseReference companyRef;
    String post_key,product_id;
    RecyclerView recyclerView;
    int day,month,year,last_year;
    double sum,sum1;
    DecimalFormat decimalFormat;
    TextView txtLastPeriod,txtCurrentPeriod;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_supplier_evaluation_time, container, false);

        post_key = getActivity().getIntent().getExtras().getString("post_key");
        product_id = getActivity().getIntent().getExtras().getString("product_id");
        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");

        txtLastPeriod = view.findViewById(R.id.txtLastPeriod);
        txtCurrentPeriod = view.findViewById(R.id.txtCurrentPeriod);

        decimalFormat = new DecimalFormat("0.00");

        Date date= new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        day = cal.get(Calendar.DAY_OF_MONTH);
        month = cal.get(Calendar.MONTH)+1;
        year = cal.get(Calendar.YEAR);
        last_year = year-1;

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);


        showSuppliers();

        return view;
    }

    private void showSuppliers() {
        Query query = companyRef.child(post_key).child("Purchased Items").child(product_id).child("Suppliers").orderByChild("supplier_id");
        FirebaseRecyclerAdapter<SuppliersModel, SuppliersViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<SuppliersModel, SuppliersViewHolder>
                (SuppliersModel.class, R.layout.supplier_evaluation_time_item,SuppliersViewHolder.class,query) {
            @Override
            protected void populateViewHolder(final SuppliersViewHolder viewHolder, SuppliersModel model, int position) {
                final String postKey = getRef(position).getKey();
                viewHolder.setSupplier_id(model.getSupplier_id());

                companyRef.child(post_key).child("Purchased Items").child(product_id).child("Suppliers").child(postKey).child("Delivery Delayed").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        long count = dataSnapshot.getChildrenCount();
                        sum = 0;
                        viewHolder.txtLastPeriodPrice.setText(count+"");


                        companyRef.child(post_key).child("Purchased Items").child(product_id).child("Suppliers").child(postKey).child("Delivery on Time").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                long count = dataSnapshot.getChildrenCount();
                                sum1 = 0;
                                viewHolder.txtCurrentPeriodPrice.setText(count+"");

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






                companyRef.child(post_key).child("My Suppliers").child(postKey).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String supplier_name = dataSnapshot.child("supplier_name").getValue().toString();
                        viewHolder.txtSupplierName.setText(supplier_name);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        };
        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }


    public static class SuppliersViewHolder extends RecyclerView.ViewHolder {

        View mView;
        String my_supplier_id;
        TextView txtSupplierName,txtLastPeriodPrice,txtCurrentPeriodPrice;

        public SuppliersViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;

            txtSupplierName = mView.findViewById(R.id.txtSupplierName);
            txtLastPeriodPrice = mView.findViewById(R.id.txtLastPeriodPrice);
            txtCurrentPeriodPrice = mView.findViewById(R.id.txtCurrentPeriodPrice);
        }
        public void setSupplier_id(String supplier_id) {
            my_supplier_id = supplier_id;
        }
    }
}
