package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.FinancialManagement.AccountsNoPaid;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Commercialization.BillsIssuing.MyBillsModel;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import es.dmoral.toasty.Toasty;


public class ShortTermDebtsFragment extends Fragment {

    String post_key,politic_days;
    RecyclerView recyclerView;
    DatabaseReference companyRef;
    int day,month,year,politic_days_db;
    long diff,expiration_days_ago;
    ImageView btnPoliticsDialog;
    TextView txtPolitics,txtTotal;
    double sum;
    DecimalFormat decimalFormat;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_short_term_debts, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setReverseLayout(false);
        linearLayoutManager.setStackFromEnd(false);
        recyclerView.setLayoutManager(linearLayoutManager);
        btnPoliticsDialog = view.findViewById(R.id.btnPoliticsDialog);
        txtPolitics = view.findViewById(R.id.txtPolitics);
        txtTotal = view.findViewById(R.id.txtTotal);

        post_key = getActivity().getIntent().getExtras().getString("post_key");
        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");

        decimalFormat = new DecimalFormat("0.00");

        Date date= new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        day = cal.get(Calendar.DAY_OF_MONTH);
        month = cal.get(Calendar.MONTH)+1;
        year = cal.get(Calendar.YEAR);

        sum = 0.00;

        companyRef.child(post_key).child("Politics").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild("Short Terms Charges")) {
                    politic_days = dataSnapshot.child("Short Terms Charges").getValue().toString();
                    politic_days_db = Integer.parseInt(politic_days);
                    txtPolitics.setText("Políticas: Hasta "+politic_days+" días para vencer");
                    showMyBills();
                } else {
                    politic_days_db = 180;
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        btnPoliticsDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog dialog = new AlertDialog.Builder(getActivity()).create();

                LayoutInflater inflater = LayoutInflater.from(getActivity());
                View finance_method = inflater.inflate(R.layout.short_term_politics_dialog,null);

                final EditText edtDays = finance_method.findViewById(R.id.edtDays);
                Button btnRegister = finance_method.findViewById(R.id.btnRegister);
                final LinearLayout rootLayout = finance_method.findViewById(R.id.rootLayout);

                btnRegister.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (TextUtils.isEmpty(edtDays.getText().toString())) {
                            Snackbar.make(rootLayout, "Debes ingresar el número de días", Snackbar.LENGTH_SHORT).show();
                        } else {
                            companyRef.child(post_key).child("Politics").child("Short Terms Charges").setValue(edtDays.getText().toString());
                            Toasty.success(getActivity(), "Políticas Actualizadas", Toast.LENGTH_LONG).show();
                            dialog.dismiss();
                        }
                    }
                });

                dialog.setView(finance_method);
                dialog.show();
            }
        });

        showMyBills();

        return view;
    }

    private void showMyBills() {
        Query query = companyRef.child(post_key).child("My Bills").orderByChild("state").equalTo("no_paid");
        FirebaseRecyclerAdapter<MyBillsModel, CompanyBillsViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<MyBillsModel, CompanyBillsViewHolder>
                (MyBillsModel.class, R.layout.my_bill_item, CompanyBillsViewHolder.class, query) {
            @Override
            protected void populateViewHolder(final CompanyBillsViewHolder viewHolder, MyBillsModel model, int position) {
                final String postKey = getRef(position).getKey();
                viewHolder.setBill_amount(model.getBill_amount());
                viewHolder.setBill_id(model.getBill_id());
                viewHolder.setCustomer_name(model.getCustomer_name());
                viewHolder.setState(model.getState());

                viewHolder.txtBillAmount.setText("S/ "+viewHolder.my_bill_amount);
                viewHolder.txtCustomerName.setText(viewHolder.my_customer_name);
                viewHolder.txtBillCode.setText(viewHolder.my_bill_id);

                companyRef.child(post_key).child("My Bills").child(postKey).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String expiration_day = dataSnapshot.child("expiration_day").getValue().toString();
                        String expiration_month = dataSnapshot.child("expiration_month").getValue().toString();
                        String expiration_year = dataSnapshot.child("expiration_year").getValue().toString();
                        String state = dataSnapshot.child("state").getValue().toString();

                        SimpleDateFormat myFormat = new SimpleDateFormat("dd MM yyyy");
                        String inputString1 = day+" "+month+" "+year;
                        String inputString2 = expiration_day+" "+expiration_month+" "+expiration_year;

                        try {
                            Date date1 = myFormat.parse(inputString1);
                            Date date2 = myFormat.parse(inputString2);
                            diff = date2.getTime() - date1.getTime();

                            expiration_days_ago = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
                            //Toast.makeText(BillsIssuingActivity.this, "EXPIRED "+expiration_days_ago, Toast.LENGTH_SHORT).show();

                            if (diff < 0 && state.equals("no_paid"))  {
                                companyRef.child(post_key).child("My Bills").child(postKey).child("state").setValue("expired");
                            }

                            if (expiration_days_ago > politic_days_db) {
                                viewHolder.mView.setVisibility(View.GONE);
                            } else {
                                double amount = Double.parseDouble(viewHolder.my_bill_amount);
                                sum += amount;
                                String sum_st = decimalFormat.format(sum);
                                txtTotal.setText("TOTAL: S/ "+sum_st);
                            }

                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

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
