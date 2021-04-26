package com.oalonedeveloper.oliver.oliverappandroidapp.FinancialManagement.LendingProduct;

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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class LoanBillsFragment extends Fragment {

    RecyclerView recyclerView;
    DatabaseReference lendingRef;
    String operation_id;
    int day,month,year;
    long diff,expiration_days_ago;
    DecimalFormat decimalFormat;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_loan_bills, container, false);

        lendingRef = FirebaseDatabase.getInstance().getReference().child("Lendings");
        operation_id = getActivity().getIntent().getExtras().getString("operation_id");

        decimalFormat = new DecimalFormat("0.00");

        Date date= new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        day = cal.get(Calendar.DAY_OF_MONTH);
        month = cal.get(Calendar.MONTH)+1;
        year = cal.get(Calendar.YEAR);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setReverseLayout(false);
        linearLayoutManager.setStackFromEnd(false);
        recyclerView.setLayoutManager(linearLayoutManager);

        showBills();

        return view;
    }

    private void showBills() {
        Query query = lendingRef.child(operation_id).child("Bills");
        FirebaseRecyclerAdapter<LoanBillsModel, LoanBillsViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<LoanBillsModel, LoanBillsViewHolder>
                (LoanBillsModel.class,R.layout.loan_bill_item,LoanBillsViewHolder.class,query) {
            @Override
            protected void populateViewHolder(LoanBillsViewHolder viewHolder, LoanBillsModel model, int position) {
                viewHolder.setBill_amount(model.getBill_amount());
                viewHolder.setBill_currency(model.getBill_currency());
                viewHolder.setBill_expiration_day(model.getBill_expiration_day());
                viewHolder.setBill_expiration_month(model.getBill_expiration_month());
                viewHolder.setBill_expiration_year(model.getBill_expiration_year());
                viewHolder.setBill_state(model.getBill_state());

                double amount_db = Double.parseDouble(viewHolder.my_bill_amount);
                String amount = decimalFormat.format(amount_db);

                if (viewHolder.my_bill_currency.equals("PEN")) {
                    viewHolder.txtAmount.setText("Monto: S/ "+amount);
                }
                if (viewHolder.my_bill_currency.equals("USD")) {
                    viewHolder.txtAmount.setText("Monto: $ "+amount);
                }

                viewHolder.txtExpirationDate.setText("Fecha de Vencimiento: "+viewHolder.my_bill_expiration_day+"/"+viewHolder.my_bill_expiration_month+"/"+viewHolder.my_bill_expiration_year);

                SimpleDateFormat myFormat = new SimpleDateFormat("dd MM yyyy");
                String inputString1 = day+" "+month+" "+year;
                String inputString2 = viewHolder.my_bill_expiration_day+" "+viewHolder.my_bill_expiration_month+" "+viewHolder.my_bill_expiration_year;


                try {
                    Date date1  = myFormat.parse(inputString1);
                    Date date2 = myFormat.parse(inputString2);
                    diff = date2.getTime() - date1.getTime();

                    expiration_days_ago = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
                    expiration_days_ago = Math.abs(expiration_days_ago);

                    if (expiration_days_ago >= 0) {
                        viewHolder.txtDaysToExpire.setText("Vence en: "+expiration_days_ago+ " días");
                    } else {
                        viewHolder.txtDaysToExpire.setText("Vencío hace: "+expiration_days_ago+ " días");
                    }

                    if (viewHolder.my_bill_state.equals("no_paid")){
                        viewHolder.txtBillState.setText("Por Pagar");
                    }
                    if (viewHolder.my_bill_state.equals("paid")){
                        viewHolder.txtBillState.setText("Pagado");
                    }


                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }
        };
        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    public static class LoanBillsViewHolder extends RecyclerView.ViewHolder {
        View mView;
        String my_bill_amount,my_bill_currency,my_bill_expiration_day,my_bill_expiration_month,my_bill_expiration_year,my_bill_state,my_bill_capital;
        TextView txtAmount,txtExpirationDate,txtDaysToExpire,txtBillState;

        public LoanBillsViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;

            txtAmount = mView.findViewById(R.id.txtAmount);
            txtExpirationDate = mView.findViewById(R.id.txtExpirationDate);
            txtDaysToExpire = mView.findViewById(R.id.txtDaysToExpire);
            txtBillState = mView.findViewById(R.id.txtBillState);

        }

        public void setBill_amount(String bill_amount) {
            my_bill_amount = bill_amount;
        }

        public void setBill_capital(String bill_capital) {
            my_bill_capital = bill_capital;
        }
        public void setBill_currency(String bill_currency) {
            my_bill_currency = bill_currency;
        }

        public void setBill_expiration_day(String bill_expiration_day) {
            my_bill_expiration_day = bill_expiration_day;
        }

        public void setBill_expiration_month(String bill_expiration_month) {
            my_bill_expiration_month = bill_expiration_month;
        }

        public void setBill_expiration_year(String bill_expiration_year) {
            my_bill_expiration_year = bill_expiration_year;
        }

        public void setBill_state(String bill_state) {
            my_bill_state = bill_state;
        }
    }
}
