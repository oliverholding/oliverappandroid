package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Logistic.PurchaseOrder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;

public class PurchaseOrdersListActivity extends AppCompatActivity {

    String post_key;
    RecyclerView recyclerView;
    DatabaseReference companyRef;
    Button btnCreatePurchaseOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_orders_list);

        post_key = getIntent().getExtras().getString("post_key");
        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        btnCreatePurchaseOrder = findViewById(R.id.btnCreatePurchaseOrder);

        btnCreatePurchaseOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PurchaseOrdersListActivity.this, PurchaseOrderActivity.class);
                intent.putExtra("post_key",post_key);
                startActivity(intent);
            }
        });
        
        showPurchaseOrders();
    }

    private void showPurchaseOrders() {
        Query query = companyRef.child(post_key).child("Purchased Orders").orderByChild("timestamp");
        FirebaseRecyclerAdapter<PurchaseOrdersModel,PurchaseOrdersViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<PurchaseOrdersModel, PurchaseOrdersViewHolder>
                (PurchaseOrdersModel.class,R.layout.purchase_order_item,PurchaseOrdersViewHolder.class,query) {
            @Override
            protected void populateViewHolder(final PurchaseOrdersViewHolder viewHolder, PurchaseOrdersModel model, int position) {
                final String postKey = getRef(position).getKey();
                viewHolder.setOperation_date(model.getOperation_date());
                viewHolder.setOperation_day(model.getOperation_day());
                viewHolder.setOperation_month(model.getOperation_month());
                viewHolder.setOperation_time(model.getOperation_time());
                viewHolder.setOperation_year(model.getOperation_year());
                viewHolder.setPurchase_order_id(model.getPurchase_order_id());
                viewHolder.setPurchase_order_supplier_id(model.getPurchase_order_supplier_id());
                viewHolder.setPurchase_order_total_amount(model.getPurchase_order_total_amount());

                viewHolder.txtCode.setText(viewHolder.my_purchase_order_id);
                viewHolder.txtDate.setText(viewHolder.my_operation_date);

                viewHolder.txtTotal.setText("S/ "+viewHolder.my_purchase_order_total_amount);

                if (viewHolder.my_purchase_order_supplier_id != null) {
                    companyRef.child(post_key).child("My Suppliers").child(viewHolder.my_purchase_order_supplier_id).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            String supplier_name = dataSnapshot.child("supplier_name").getValue().toString();
                            viewHolder.txtSupplier.setText(supplier_name);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }


            }
        };
        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    public static class PurchaseOrdersViewHolder extends RecyclerView.ViewHolder {
        View mView;
        String my_operation_date,my_operation_day,my_operation_month,my_operation_time,my_operation_year,my_purchase_order_id,my_purchase_order_supplier_id,my_purchase_order_total_amount;
        TextView txtCode,txtSupplier,txtDate,txtTotal;

        public PurchaseOrdersViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;

            txtCode = mView.findViewById(R.id.txtCode);
            txtSupplier = mView.findViewById(R.id.txtSupplier);
            txtDate = mView.findViewById(R.id.txtDate);
            txtTotal = mView.findViewById(R.id.txtTotal);
        }

        public void setOperation_date(String operation_date) {
            my_operation_date = operation_date;
        }

        public void setOperation_day(String operation_day) {
            my_operation_day = operation_day;
        }

        public void setOperation_month(String operation_month) {
            my_operation_month = operation_month;
        }

        public void setOperation_time(String operation_time) {
            my_operation_time = operation_time;
        }

        public void setOperation_year(String operation_year) {
            my_operation_year = operation_year;
        }
        public void setPurchase_order_id(String purchase_order_id) {
            my_purchase_order_id = purchase_order_id;
        }
        public void setPurchase_order_supplier_id(String purchase_order_supplier_id) {
            my_purchase_order_supplier_id = purchase_order_supplier_id;
        }

        public void setPurchase_order_total_amount(String purchase_order_total_amount) {
            my_purchase_order_total_amount = purchase_order_total_amount;
        }
    }
}
