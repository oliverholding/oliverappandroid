package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Commercialization.CreditsAndTreasury;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Commercialization.BillsIssuing.BillsIssuingActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;


public class CreditTableFragment extends Fragment {

    TextView txtNoPaidAmount,txtExpiredAmount1,txtExpiredAmount2,txtExpiredAmount3,txtExpiredAmount4,txtExpiredAmount5,txtExpiredAmount6,txtNoPaid,txtExpired1,txtExpired2,txtExpired3,txtExpired4,txtExpired5,txtExpired6;
    String post_key;
    DatabaseReference companyRef;
    double sum1,sum2,sum3,sum4,sum5,sum6,sum7,sum0,total_debt;
    int day,month,year;
    long diff,expiration_days_ago;
    DecimalFormat decimalFormat;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_credit_table, container, false);

        post_key = getActivity().getIntent().getExtras().getString("post_key");
        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");

        Date date= new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        day = cal.get(Calendar.DAY_OF_MONTH);
        month = cal.get(Calendar.MONTH)+1;
        year = cal.get(Calendar.YEAR);

        txtNoPaidAmount = view.findViewById(R.id.txtNoPaidAmount);
        txtExpiredAmount1 = view.findViewById(R.id.txtExpiredAmount1);
        txtExpiredAmount2 = view.findViewById(R.id.txtExpiredAmount2);
        txtExpiredAmount3 = view.findViewById(R.id.txtExpiredAmount3);
        txtExpiredAmount4 = view.findViewById(R.id.txtExpiredAmount4);
        txtExpiredAmount5 = view.findViewById(R.id.txtExpiredAmount5);
        txtExpiredAmount6 = view.findViewById(R.id.txtExpiredAmount6);
        txtNoPaid = view.findViewById(R.id.txtNoPaid);
        txtExpired1 = view.findViewById(R.id.txtExpired1);
        txtExpired2 = view.findViewById(R.id.txtExpired2);
        txtExpired3 = view.findViewById(R.id.txtExpired3);
        txtExpired4 = view.findViewById(R.id.txtExpired4);
        txtExpired5 = view.findViewById(R.id.txtExpired5);
        txtExpired6 = view.findViewById(R.id.txtExpired6);

        decimalFormat = new DecimalFormat("0.00");

        companyRef.child(post_key).child("My Bills").orderByChild("state").equalTo("no_paid").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                sum1 = 0;
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Map<String, Object> map = (Map<String, Object>) ds.getValue();
                    Object price = map.get("bill_amount");
                    double total_value = Double.parseDouble(String.valueOf(price));
                    sum1 += total_value;


                    txtNoPaidAmount.setText("S/ "+sum1);
                }

                companyRef.child(post_key).child("My Bills").orderByChild("state").equalTo("expired").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        sum0 = 0;
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            Map<String, Object> map = (Map<String, Object>) ds.getValue();
                            Object price = map.get("bill_amount");
                            double total_value = Double.parseDouble(String.valueOf(price));
                            sum0 += total_value;

                        }

                        total_debt = sum1+sum0;

                        double percent = (sum1/total_debt)*100;
                        String percent_st = decimalFormat.format(percent);
                        txtNoPaid.setText(percent_st+"%");


                        companyRef.child(post_key).child("My Bills").orderByChild("state").equalTo("expired").addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {

                                sum2 = 0;
                                sum3 = 0;
                                sum4 = 0;
                                sum5 = 0;
                                sum6 = 0;
                                sum7 = 0;
                                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                                    Map<String, Object> map = (Map<String, Object>) ds.getValue();

                                    Object expiration_day = map.get("expiration_day");
                                    Object expiration_month = map.get("expiration_month");
                                    Object expiration_year = map.get("expiration_year");

                                    SimpleDateFormat myFormat = new SimpleDateFormat("dd MM yyyy");
                                    String inputString1 = day+" "+month+" "+year;
                                    String inputString2 = expiration_day+" "+expiration_month+" "+expiration_year;

                                    try {
                                        Date date1 = myFormat.parse(inputString1);
                                        Date date2 = myFormat.parse(inputString2);
                                        diff = date2.getTime() - date1.getTime();

                                        expiration_days_ago = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
                                        expiration_days_ago = Math.abs(expiration_days_ago);

                                        if (expiration_days_ago >= 1 && expiration_days_ago <= 8)  {
                                            Object price = map.get("bill_amount");
                                            double total_value = Double.parseDouble(String.valueOf(price));
                                            sum2 += total_value;

                                            double percent = (sum2/total_debt)*100;
                                            String percent_st = decimalFormat.format(percent);
                                            txtExpired1.setText(percent_st+"%");

                                            txtExpiredAmount1.setText("S/ "+sum2);
                                        }
                                        if (expiration_days_ago >= 9 && expiration_days_ago <= 15)  {
                                            Object price = map.get("bill_amount");
                                            double total_value = Double.parseDouble(String.valueOf(price));
                                            sum3 += total_value;

                                            double percent = (sum3/total_debt)*100;
                                            String percent_st = decimalFormat.format(percent);
                                            txtExpired2.setText(percent_st+"%");

                                            txtExpiredAmount2.setText("S/ "+sum3);
                                        }
                                        if (expiration_days_ago >= 16 && expiration_days_ago <= 30)  {
                                            Object price = map.get("bill_amount");
                                            double total_value = Double.parseDouble(String.valueOf(price));
                                            sum4 += total_value;

                                            double percent = (sum4/total_debt)*100;
                                            String percent_st = decimalFormat.format(percent);
                                            txtExpired3.setText(percent_st+"%");

                                            txtExpiredAmount3.setText("S/ "+sum4);
                                        }
                                        if (expiration_days_ago >= 31 && expiration_days_ago <= 45)  {
                                            Object price = map.get("bill_amount");
                                            double total_value = Double.parseDouble(String.valueOf(price));
                                            sum5 += total_value;

                                            double percent = (sum5/total_debt)*100;
                                            String percent_st = decimalFormat.format(percent);
                                            txtExpired4.setText(percent_st+"%");

                                            txtExpiredAmount4.setText("S/ "+sum5);
                                        }
                                        if (expiration_days_ago >= 46 && expiration_days_ago <= 60)  {
                                            Object price = map.get("bill_amount");
                                            double total_value = Double.parseDouble(String.valueOf(price));
                                            sum6 += total_value;

                                            double percent = (sum6/total_debt)*100;
                                            String percent_st = decimalFormat.format(percent);
                                            txtExpired5.setText(percent_st+"%");

                                            txtExpiredAmount5.setText("S/ "+sum6);
                                        }
                                        if (expiration_days_ago >= 61)  {
                                            Object price = map.get("bill_amount");
                                            double total_value = Double.parseDouble(String.valueOf(price));
                                            sum7 += total_value;

                                            double percent = (sum7/total_debt)*100;
                                            String percent_st = decimalFormat.format(percent);
                                            txtExpired6.setText(percent_st+"%");

                                            txtExpiredAmount6.setText("S/ "+sum7);
                                        }

                                    } catch (ParseException e) {
                                        e.printStackTrace();
                                    }




                                    //txtExpiredDebts.setText("S/ "+sum2);
                                }


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



        return view;
    }
}
