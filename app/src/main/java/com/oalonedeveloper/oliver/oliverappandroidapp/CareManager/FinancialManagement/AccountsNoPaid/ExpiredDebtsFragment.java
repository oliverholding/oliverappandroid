package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.FinancialManagement.AccountsNoPaid;

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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Commercialization.BillsIssuing.MyBillsModel;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;

import java.util.Calendar;
import java.util.Date;


public class ExpiredDebtsFragment extends Fragment {

    String post_key,company_social_reason,company_image,customer_name,customer_email,customer_phone;
    RecyclerView recyclerView;
    DatabaseReference companyRef;
    Button btnNotify;
    int day,month,year;
    long diff,expiration_days_ago;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_expired_debts, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setReverseLayout(false);
        linearLayoutManager.setStackFromEnd(false);
        recyclerView.setLayoutManager(linearLayoutManager);

        btnNotify = view.findViewById(R.id.btnNotify);

        post_key = getActivity().getIntent().getExtras().getString("post_key");
        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");

        Date date= new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        day = cal.get(Calendar.DAY_OF_MONTH);
        month = cal.get(Calendar.MONTH)+1;
        year = cal.get(Calendar.YEAR);

        companyRef.child(post_key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                company_social_reason = dataSnapshot.child("company_social_reason").getValue().toString();
                company_image = dataSnapshot.child("company_image").getValue().toString();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        showMyBills();

        return view;
    }

    private void showMyBills() {
        Query query = companyRef.child(post_key).child("My Bills").orderByChild("state").equalTo("expired");
        FirebaseRecyclerAdapter<MyBillsModel, CompanyBillsViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<MyBillsModel, CompanyBillsViewHolder>
                (MyBillsModel.class, R.layout.my_bill_item, CompanyBillsViewHolder.class, query) {
            @Override
            protected void populateViewHolder(CompanyBillsViewHolder viewHolder, MyBillsModel model, int position) {
                final String postKey = getRef(position).getKey();
                viewHolder.setBill_amount(model.getBill_amount());
                viewHolder.setBill_id(model.getBill_id());
                viewHolder.setCustomer_name(model.getCustomer_name());
                viewHolder.setState(model.getState());

                viewHolder.txtBillAmount.setText("S/ "+viewHolder.my_bill_amount);
                viewHolder.txtCustomerName.setText(viewHolder.my_customer_name);
                viewHolder.txtBillCode.setText(viewHolder.my_bill_id);

                if (viewHolder.my_state.equals("no_paid")) {
                    viewHolder.txtDebtState.setText("VIGENTE");
                    viewHolder.btnSetPaid.setEnabled(true);
                    viewHolder.btnSetPaid.setVisibility(View.VISIBLE);
                } else if (viewHolder.my_state.equals("paid")){
                    viewHolder.txtDebtState.setText("PAGADO");
                    viewHolder.btnSetPaid.setEnabled(false);
                    viewHolder.btnSetPaid.setVisibility(View.GONE);
                }
                else if (viewHolder.my_state.equals("expired")){
                    viewHolder.txtDebtState.setText("VENCIDO");
                    viewHolder.btnSetPaid.setEnabled(true);
                    viewHolder.btnSetPaid.setVisibility(View.VISIBLE);
                }


                viewHolder.btnSetPaid.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        companyRef.child(post_key).child("My Bills").child(postKey).child("state").setValue("paid");
                    }
                });

            }
        };
        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    public static class CompanyBillsViewHolder extends RecyclerView.ViewHolder {
        View mView;
        String my_bill_amount,my_customer_name,my_bill_id,my_state;
        TextView txtBillCode,txtCustomerName,txtBillAmount,txtDebtState;
        Button btnSetPaid;


        public CompanyBillsViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;

            txtBillCode = mView.findViewById(R.id.txtBillCode);
            txtCustomerName = mView.findViewById(R.id.txtCustomerName);
            txtBillAmount = mView.findViewById(R.id.txtBillAmount);
            txtDebtState = mView.findViewById(R.id.txtDebtState);
            btnSetPaid = mView.findViewById(R.id.btnSetPaid);
        }
        public void setBill_amount(String bill_amount) {
            my_bill_amount = bill_amount;
        }

        public void setCustomer_name(String customer_name) {
            my_customer_name = customer_name;
        }

        public void setBill_id(String bill_id) {
            my_bill_id = bill_id;
        }
        public void setState(String state) {
            my_state = state;
        }
    }
}
