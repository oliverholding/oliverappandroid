package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Logistic.Dispatch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;

public class DispatchActivity extends AppCompatActivity {

    String post_key;
    RecyclerView recyclerView;
    DatabaseReference companyRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispatch);

        post_key = getIntent().getExtras().getString("post_key");
        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        showDispatches();
    }

    private void showDispatches() {
        Query query = companyRef.child(post_key).child("Dispatches").orderByChild("timestamp");
        FirebaseRecyclerAdapter<DispatchModel,DispatchesViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<DispatchModel, DispatchesViewHolder>
                (DispatchModel.class,R.layout.purchase_order_item,DispatchesViewHolder.class,query) {
            @Override
            protected void populateViewHolder(final DispatchesViewHolder viewHolder, DispatchModel model, int position) {
                viewHolder.setDispatch_address(model.getDispatch_address());
                viewHolder.setDispatch_bill_number(model.getDispatch_bill_number());
                viewHolder.setDispatch_customer_id(model.getDispatch_customer_id());
                viewHolder.setDispatch_department(model.getDispatch_department());
                viewHolder.setDispatch_district(model.getDispatch_district());
                viewHolder.setDispatch_province(model.getDispatch_province());
                viewHolder.setDispatch_shift(model.getDispatch_shift());
                viewHolder.setDispatch_state(model.getDispatch_state());

                if (viewHolder.my_dispatch_state.equals("pending")) {
                    viewHolder.txtCode.setText("Despecho");
                }

                companyRef.child(post_key).child("Customers").child(viewHolder.my_dispatch_customer_id).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String customer_name = dataSnapshot.child("customer_name").getValue().toString();
                        viewHolder.txtSupplier.setText(customer_name);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                viewHolder.txtDate.setText(viewHolder.my_dispatch_district);
                viewHolder.txtTotal.setText(viewHolder.my_dispatch_shift);

            }
        };
        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    public static class DispatchesViewHolder extends RecyclerView.ViewHolder {
        View mView;
        TextView txtCode,txtSupplier,txtDate,txtTotal;
        ImageView btnActionButton;
        String my_dispatch_address,my_dispatch_bill_number,my_dispatch_customer_id,my_dispatch_department,my_dispatch_district,my_dispatch_province,my_dispatch_shift,my_dispatch_state;

        public DispatchesViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;

            txtCode = mView.findViewById(R.id.txtCode);
            txtSupplier = mView.findViewById(R.id.txtSupplier);
            txtDate = mView.findViewById(R.id.txtDate);
            txtTotal = mView.findViewById(R.id.txtTotal);
            btnActionButton = mView.findViewById(R.id.btnActionButton);
        }

        public void setDispatch_address(String dispatch_address) {
            my_dispatch_address = dispatch_address;
        }

        public void setDispatch_bill_number(String dispatch_bill_number) {
            my_dispatch_bill_number = dispatch_bill_number;
        }

        public void setDispatch_customer_id(String dispatch_customer_id) {
            my_dispatch_customer_id = dispatch_customer_id;
        }

        public void setDispatch_department(String dispatch_department) {
            my_dispatch_department = dispatch_department;
        }

        public void setDispatch_district(String dispatch_district) {
            my_dispatch_district = dispatch_district;
        }

        public void setDispatch_province(String dispatch_province) {
            my_dispatch_province = dispatch_province;
        }

        public void setDispatch_shift(String dispatch_shift) {
            my_dispatch_shift = dispatch_shift;
        }

        public void setDispatch_state(String dispatch_state) {
            my_dispatch_state = dispatch_state;
        }
    }
}
