package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.SalesModule.SalesFunnel;

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
import com.oalonedeveloper.oliver.oliverappandroidapp.R;

import java.text.DecimalFormat;


public class CustomerAchievementFragment extends Fragment {

    DatabaseReference myCompanyRef;
    String post_key;
    long children_count, contact_count;
    TextView txContactPercent,txContactAmount,txtInterestedPercent,txtInterestedAmount,txtProspectPercent,txtProspectAmount,txtPotencialPercent,txtPotencialAmount,txtFinalPercent,txtFinalAmount,
            txtCustomerPercent,txtCustomerAmount;
    DecimalFormat decimalFormat;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_customer_achievement, container, false);

        post_key = getActivity().getIntent().getExtras().getString("post_key");
        myCompanyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");

        txContactPercent = view.findViewById(R.id.txContactPercent);
        txContactAmount = view.findViewById(R.id.txContactAmount);
        txtInterestedPercent = view.findViewById(R.id.txtInterestedPercent);
        txtInterestedAmount = view.findViewById(R.id.txtInterestedAmount);
        txtProspectPercent = view.findViewById(R.id.txtProspectPercent);
        txtProspectAmount = view.findViewById(R.id.txtProspectAmount);
        txtPotencialPercent = view.findViewById(R.id.txtPotencialPercent);
        txtPotencialAmount = view.findViewById(R.id.txtPotencialAmount);
        txtFinalPercent = view.findViewById(R.id.txtFinalPercent);
        txtFinalAmount = view.findViewById(R.id.txtFinalAmount);
        txtCustomerPercent = view.findViewById(R.id.txtCustomerPercent);
        txtCustomerAmount = view.findViewById(R.id.txtCustomerAmount);

        decimalFormat = new DecimalFormat("0.00");

        myCompanyRef.child(post_key).child("Customers").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                children_count = dataSnapshot.getChildrenCount();

                myCompanyRef.child(post_key).child("Customers").orderByChild("customer_define").equalTo("contact").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        contact_count = dataSnapshot.getChildrenCount();

                        //Calculate percent
                        float percent = ((float)contact_count/children_count)*100;
                        String my_percent = decimalFormat.format(percent);

                        txContactPercent.setText(my_percent+"%");
                        txContactAmount.setText(contact_count+"");
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                myCompanyRef.child(post_key).child("Customers").orderByChild("customer_define").equalTo("interested").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        contact_count = dataSnapshot.getChildrenCount();

                        //Calculate percent
                        float percent = ((float)contact_count/children_count)*100;
                        String my_percent = decimalFormat.format(percent);

                        txtInterestedPercent.setText(my_percent+"%");
                        txtInterestedAmount.setText(contact_count+"");
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                myCompanyRef.child(post_key).child("Customers").orderByChild("customer_define").equalTo("prospect").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        contact_count = dataSnapshot.getChildrenCount();

                        //Calculate percent
                        float percent = ((float)contact_count/children_count)*100;
                        String my_percent = decimalFormat.format(percent);

                        txtProspectPercent.setText(my_percent+"%");
                        txtProspectAmount.setText(contact_count+"");
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                myCompanyRef.child(post_key).child("Customers").orderByChild("customer_define").equalTo("potencial").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        contact_count = dataSnapshot.getChildrenCount();

                        //Calculate percent
                        float percent = ((float)contact_count/children_count)*100;
                        String my_percent = decimalFormat.format(percent);

                        txtPotencialPercent.setText(my_percent+"%");
                        txtPotencialAmount.setText(contact_count+"");
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                myCompanyRef.child(post_key).child("Customers").orderByChild("customer_define").equalTo("final").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        contact_count = dataSnapshot.getChildrenCount();

                        //Calculate percent
                        float percent = ((float)contact_count/children_count)*100;
                        String my_percent = decimalFormat.format(percent);

                        txtFinalPercent.setText(my_percent+"%");
                        txtFinalAmount.setText(contact_count+"");
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                myCompanyRef.child(post_key).child("Customers").orderByChild("customer_define").equalTo("customer").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        contact_count = dataSnapshot.getChildrenCount();

                        //Calculate percent
                        float percent = ((float)contact_count/children_count)*100;
                        String my_percent = decimalFormat.format(percent);

                        txtCustomerPercent.setText(my_percent+"%");
                        txtCustomerAmount.setText(contact_count+"");
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
