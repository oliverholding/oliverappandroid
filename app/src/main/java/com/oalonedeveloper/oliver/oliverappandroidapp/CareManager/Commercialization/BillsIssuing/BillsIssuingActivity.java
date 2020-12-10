package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Commercialization.BillsIssuing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Commercialization.Dashboards.DashboardsActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;

import java.util.Map;

public class BillsIssuingActivity extends AppCompatActivity {

    Button btnCreateBill,btnCreateInvoice,btnDashboards;
    String post_key;
    RecyclerView recyclerView;
    DatabaseReference companyRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bills_issuing);

        btnCreateBill = findViewById(R.id.btnCreateBill);
        btnCreateInvoice = findViewById(R.id.btnCreateInvoice);
        btnDashboards = findViewById(R.id.btnDashboards);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        post_key = getIntent().getExtras().getString("post_key");
        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");

        btnCreateBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showOptionDialog();
            }
        });
        btnCreateInvoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showOptionDialog2();
            }
        });
        btnDashboards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BillsIssuingActivity.this, DashboardsActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
                finish();
            }
        });

        showMyBills();
    }

    private void showOptionDialog2() {
        final AlertDialog dialog = new AlertDialog.Builder(BillsIssuingActivity.this).create();

        LayoutInflater inflater = LayoutInflater.from(BillsIssuingActivity.this);
        View finance_method = inflater.inflate(R.layout.sale_bill_type_dialog,null);

        ImageButton btnCashNow,btnCredit;
        btnCashNow = finance_method.findViewById(R.id.btnCashNow);
        btnCredit = finance_method.findViewById(R.id.btnCredit);

        btnCashNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BillsIssuingActivity.this, CreateInvoiceActivity.class);
                intent.putExtra("post_key", post_key);
                intent.putExtra("bill_sale_type", "cash_now");
                dialog.dismiss();
                startActivity(intent);
                finish();
            }
        });
        btnCredit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BillsIssuingActivity.this, CreateInvoiceActivity.class);
                intent.putExtra("post_key", post_key);
                intent.putExtra("bill_sale_type", "credit");
                dialog.dismiss();
                startActivity(intent);
                finish();
            }
        });

        dialog.setView(finance_method);
        dialog.show();
    }

    private void showOptionDialog() {
        final AlertDialog dialog = new AlertDialog.Builder(BillsIssuingActivity.this).create();

        LayoutInflater inflater = LayoutInflater.from(BillsIssuingActivity.this);
        View finance_method = inflater.inflate(R.layout.sale_bill_type_dialog,null);

        ImageButton btnCashNow,btnCredit;
        btnCashNow = finance_method.findViewById(R.id.btnCashNow);
        btnCredit = finance_method.findViewById(R.id.btnCredit);

        btnCashNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BillsIssuingActivity.this, CreateBillActivity.class);
                intent.putExtra("post_key", post_key);
                intent.putExtra("bill_sale_type", "cash_now");
                dialog.dismiss();
                startActivity(intent);
                finish();
            }
        });
        btnCredit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BillsIssuingActivity.this, CreateBillActivity.class);
                intent.putExtra("post_key", post_key);
                intent.putExtra("bill_sale_type", "credit");
                dialog.dismiss();
                startActivity(intent);
                finish();
            }
        });

        dialog.setView(finance_method);
        dialog.show();
    }

    private void showMyBills() {
        Query query = companyRef.child(post_key).child("My Bills").orderByChild("timestamp");
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
