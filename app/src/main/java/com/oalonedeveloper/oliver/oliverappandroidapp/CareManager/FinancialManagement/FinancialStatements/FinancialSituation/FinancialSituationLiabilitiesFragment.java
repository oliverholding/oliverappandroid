package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.FinancialManagement.FinancialStatements.FinancialSituation;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;


public class FinancialSituationLiabilitiesFragment extends Fragment {

    TextView txtPeriod;
    int day,month,year,last_year;
    TextView txtShortTermToPaid,txtTotalCurrentLiabilities,txtLongTermToPaid,txtTotalNonCurrentLiabilities,txtTotalLiabilities;
    DatabaseReference companyRef;
    DecimalFormat decimalFormat;
    String post_key;
    double short_term_purchase_total,long_term_purchase_total,total_liabilities;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_financial_situation_liabilities, container, false);

        txtPeriod = view.findViewById(R.id.txtPeriod);
        txtShortTermToPaid = view.findViewById(R.id.txtShortTermToPaid);
        txtTotalCurrentLiabilities = view.findViewById(R.id.txtTotalCurrentLiabilities);
        txtLongTermToPaid = view.findViewById(R.id.txtLongTermToPaid);
        txtTotalNonCurrentLiabilities = view.findViewById(R.id.txtTotalNonCurrentLiabilities);
        txtTotalLiabilities = view.findViewById(R.id.txtTotalLiabilities);

        Date date= new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        day = cal.get(Calendar.DAY_OF_MONTH);
        month = cal.get(Calendar.MONTH)+1;
        year = cal.get(Calendar.YEAR);
        last_year = year-1;

        post_key = getActivity().getIntent().getExtras().getString("post_key");
        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");

        decimalFormat = new DecimalFormat("0.00");

        txtPeriod.setText("Al 31 de Diciembre del "+last_year);

        calculateShortTermsToPaid();

        return view;
    }

    private void calculateShortTermsToPaid() {

        companyRef.child(post_key).child("Purchased Orders").orderByChild("purchase_payment_state").equalTo("no_paid").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                short_term_purchase_total = 0;
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String purchase_payment_state = ds.child("purchase_payment_state").getValue().toString();
                    String expiration_day = ds.child("expiration_day").getValue().toString();
                    String expiration_month = ds.child("expiration_month").getValue().toString();
                    String expiration_year = ds.child("expiration_year").getValue().toString();

                    int expiration_year_int = Integer.parseInt(expiration_year);

                    if (expiration_year_int == year) {
                        Map<String, Object> map = (Map<String, Object>) ds.getValue();
                        Object purchase_order_total_amount = map.get("purchase_order_total_amount");
                        double purchase_order_total_amount_db = Double.parseDouble((String) purchase_order_total_amount);
                        short_term_purchase_total += purchase_order_total_amount_db;

                        String short_term_purchase_total_st = decimalFormat.format(short_term_purchase_total);
                        txtShortTermToPaid.setText(short_term_purchase_total_st);

                        txtTotalCurrentLiabilities.setText(short_term_purchase_total_st);



                    }

                    if (expiration_year_int > year) {
                        Map<String, Object> map = (Map<String, Object>) ds.getValue();
                        Object purchase_order_total_amount = map.get("purchase_order_total_amount");
                        double purchase_order_total_amount_db = Double.parseDouble((String) purchase_order_total_amount);
                        long_term_purchase_total += purchase_order_total_amount_db;

                        String long_term_purchase_total_st = decimalFormat.format(long_term_purchase_total);
                        txtLongTermToPaid.setText(long_term_purchase_total_st);


                        txtTotalNonCurrentLiabilities.setText(long_term_purchase_total_st);

                        total_liabilities = short_term_purchase_total+long_term_purchase_total;

                        String total_liabilities_st = decimalFormat.format(total_liabilities);

                        txtTotalLiabilities.setText(total_liabilities_st);

                        companyRef.child(post_key).child("Financial Statements").child("Financial Situation").child(last_year+"").child("total_liabilities").setValue(total_liabilities_st);

                    }

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
