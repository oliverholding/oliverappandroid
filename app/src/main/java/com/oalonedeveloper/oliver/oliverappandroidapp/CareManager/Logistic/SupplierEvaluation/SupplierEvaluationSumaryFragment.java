package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Logistic.SupplierEvaluation;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;


public class SupplierEvaluationSumaryFragment extends Fragment {

    DatabaseReference companyRef;
    String post_key,product_id;
    RecyclerView recyclerView;
    double price_weight,quality_weight,time_weight,total_weight;
    int day,month,year,last_year;
    double sum,sum1,total_deliver,deliver_delay,deliver_on_time,deliver_returned,price_var_weight,time_var_weight,quality_var_weight;
    DecimalFormat decimalFormat;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_supplier_evaluation_sumary, container, false);

        post_key = getActivity().getIntent().getExtras().getString("post_key");
        product_id = getActivity().getIntent().getExtras().getString("product_id");
        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");

        decimalFormat = new DecimalFormat("0.00");

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        Date date= new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        day = cal.get(Calendar.DAY_OF_MONTH);
        month = cal.get(Calendar.MONTH)+1;
        year = cal.get(Calendar.YEAR);
        last_year = year-1;

        companyRef.child(post_key).child("Purchased Items").child(product_id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (dataSnapshot.child("Evaluation").hasChild("price_weight")) {
                    price_weight = dataSnapshot.child("Evaluation").child("price_weight").getValue(Double.class);

                } else {
                    companyRef.child(post_key).child("Purchased Items").child(product_id).child("Evaluation").child("price_weight").setValue(50);
                }

                if (dataSnapshot.child("Evaluation").hasChild("quality_weight")) {
                    quality_weight = dataSnapshot.child("Evaluation").child("quality_weight").getValue(Double.class);

                } else {
                    companyRef.child(post_key).child("Purchased Items").child(product_id).child("Evaluation").child("quality_weight").setValue(25);
                }

                if (dataSnapshot.child("Evaluation").hasChild("time_weight")) {
                    time_weight = dataSnapshot.child("Evaluation").child("time_weight").getValue(Double.class);

                } else {
                    companyRef.child(post_key).child("Purchased Items").child(product_id).child("Evaluation").child("time_weight").setValue(25);
                }

                if (dataSnapshot.child("Evaluation").hasChild("total_weight")) {
                    total_weight = dataSnapshot.child("Evaluation").child("total_weight").getValue(Double.class);

                } else {
                    companyRef.child(post_key).child("Purchased Items").child(product_id).child("Evaluation").child("total_weight").setValue(15);
                }

                showSuppliers();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        return view;
    }

    private void showSuppliers() {
        Query query = companyRef.child(post_key).child("Purchased Items").child(product_id).child("Suppliers").orderByChild("supplier_id");
        FirebaseRecyclerAdapter<SuppliersModel, SuppliersViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<SuppliersModel, SuppliersViewHolder>
                (SuppliersModel.class, R.layout.supplier_evaluation_item,SuppliersViewHolder.class,query) {
            @Override
            protected void populateViewHolder(final SuppliersViewHolder viewHolder, SuppliersModel model, int position) {
                final String postKey = getRef(position).getKey();
                viewHolder.setSupplier_id(model.getSupplier_id());

                companyRef.child(post_key).child("Purchased Items").child(product_id).child("Suppliers").child(postKey).child("Prices").child(year+"").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        long count = dataSnapshot.getChildrenCount();
                        sum = 0;
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            Map<String, Object> map = (Map<String, Object>) ds.getValue();
                            Object price = map.get("price");
                            double price_db = Double.parseDouble(String.valueOf(price));
                            sum += price_db;
                            double average = sum/count;
                            String final_price = decimalFormat.format(average);

                        }

                        companyRef.child(post_key).child("Purchased Items").child(product_id).child("Suppliers").child(postKey).child("Prices").child(last_year+"").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                long count = dataSnapshot.getChildrenCount();
                                sum1 = 0;
                                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                                    Map<String, Object> map = (Map<String, Object>) ds.getValue();
                                    Object price = map.get("price");
                                    double price_db = Double.parseDouble(String.valueOf(price));
                                    sum1 += price_db;
                                    double average = sum1/count;
                                    String final_price = decimalFormat.format(average);

                                }

                                double var = ((sum/sum1)-1)*100;
                                String var_st = decimalFormat.format(var);
                                viewHolder.txtPriceVar.setText(var_st+"%");

                                price_var_weight = (price_weight/100)*(var/100);

                                companyRef.child(post_key).child("Purchased Items").child(product_id).child("Suppliers").child(postKey).child("Delivery Delayed").addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        long count = dataSnapshot.getChildrenCount();
                                        deliver_delay = count;

                                        companyRef.child(post_key).child("Purchased Items").child(product_id).child("Suppliers").child(postKey).child("Delivery on Time").addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                long count = dataSnapshot.getChildrenCount();
                                                deliver_on_time = count;

                                                total_deliver = deliver_delay+deliver_on_time;
                                                String total_st = decimalFormat.format(total_deliver);

                                                double time_var = (deliver_delay/total_deliver)*100;
                                                String time_var_st = decimalFormat.format(time_var);
                                                viewHolder.txtTimeVar.setText(time_var_st+"%");

                                                time_var_weight = (time_weight/100)*(time_var/100);

                                                companyRef.child(post_key).child("Purchased Items").child(product_id).child("Suppliers").child(postKey).child("Returned").addListenerForSingleValueEvent(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                                        long count = dataSnapshot.getChildrenCount();
                                                        deliver_returned = count;

                                                        double quality_var = (deliver_returned/total_deliver)*100;
                                                        String quality_var_st = decimalFormat.format(quality_var);

                                                        viewHolder.txtQualityVar.setText(quality_var_st+"%");

                                                        quality_var_weight = (quality_weight/100)*(quality_var/100);

                                                        double weighted = (price_var_weight+time_var_weight+quality_var_weight)*100;
                                                        String weighted_st = decimalFormat.format(weighted);

                                                        if (weighted > total_weight) {
                                                            viewHolder.secondLayout.setBackgroundColor(Color.RED);
                                                            viewHolder.txtPriceVar.setTextColor(Color.WHITE);
                                                            viewHolder.txtSupplierName.setTextColor(Color.WHITE);
                                                            viewHolder.txtQualityVar.setTextColor(Color.WHITE);
                                                            viewHolder.txtTimeVar.setTextColor(Color.WHITE);
                                                            viewHolder.txtWeighted.setTextColor(Color.WHITE);
                                                        }

                                                        viewHolder.txtWeighted.setText(weighted_st+"%");
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
        TextView txtSupplierName,txtPriceVar,txtQualityVar,txtTimeVar,txtWeighted;
        LinearLayout secondLayout;

        public SuppliersViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;

            txtSupplierName = mView.findViewById(R.id.txtSupplierName);
            txtPriceVar = mView.findViewById(R.id.txtPriceVar);
            txtQualityVar = mView.findViewById(R.id.txtQualityVar);
            txtTimeVar = mView.findViewById(R.id.txtTimeVar);
            txtWeighted = mView.findViewById(R.id.txtWeighted);
            secondLayout = mView.findViewById(R.id.secondLayout);
        }
        public void setSupplier_id(String supplier_id) {
            my_supplier_id = supplier_id;
        }
    }
}
