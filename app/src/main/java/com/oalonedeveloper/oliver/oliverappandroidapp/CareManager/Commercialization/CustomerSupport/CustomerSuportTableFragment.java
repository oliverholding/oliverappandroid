package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Commercialization.CustomerSupport;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;


public class CustomerSuportTableFragment extends Fragment {

    String post_key;
    DatabaseReference myCompanyRef;
    String discount1,discount2,discount3,discount4,discount5;
    TextView  txtDiscount1,txtDiscount2,txtDiscount3,txtDiscount4,txtDiscount5;
    ImageView btnChangeDiscount1,btnChangeDiscount2,btnChangeDiscount3,btnChangeDiscount4,btnChangeDiscount5;
    String discountRef;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_customer_suport_table, container, false);

        post_key = getActivity().getIntent().getExtras().getString("post_key");

        myCompanyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");

        txtDiscount1 = view.findViewById(R.id.txtDiscount1);
        txtDiscount2 = view.findViewById(R.id.txtDiscount2);
        txtDiscount3 = view.findViewById(R.id.txtDiscount3);
        txtDiscount4 = view.findViewById(R.id.txtDiscount4);
        txtDiscount5 = view.findViewById(R.id.txtDiscount5);

        btnChangeDiscount1 = view.findViewById(R.id.btnChangeDiscount1);
        btnChangeDiscount2 = view.findViewById(R.id.btnChangeDiscount2);
        btnChangeDiscount3 = view.findViewById(R.id.btnChangeDiscount3);
        btnChangeDiscount4 = view.findViewById(R.id.btnChangeDiscount4);
        btnChangeDiscount5 = view.findViewById(R.id.btnChangeDiscount5);

        btnChangeDiscount1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                discountRef = "discount1";
                showChangeValuesDialog();
            }
        });
        btnChangeDiscount2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                discountRef = "discount2";
                showChangeValuesDialog();
            }
        });
        btnChangeDiscount3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                discountRef = "discount3";
                showChangeValuesDialog();
            }
        });
        btnChangeDiscount4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                discountRef = "discount4";
                showChangeValuesDialog();
            }
        });
        btnChangeDiscount5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                discountRef = "discount5";
                showChangeValuesDialog();
            }
        });


        myCompanyRef.child(post_key).child("Module 6").child("Customer Points").child("Discounts").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    discount1 = dataSnapshot.child("discount1").getValue().toString();
                    discount2 = dataSnapshot.child("discount2").getValue().toString();
                    discount3 = dataSnapshot.child("discount3").getValue().toString();
                    discount4 = dataSnapshot.child("discount4").getValue().toString();
                    discount5 = dataSnapshot.child("discount5").getValue().toString();
                    txtDiscount1.setText(discount1+"%");
                    txtDiscount2.setText(discount2+"%");
                    txtDiscount3.setText(discount3+"%");
                    txtDiscount4.setText(discount4+"%");
                    txtDiscount5.setText(discount5+"%");

                } else if (!dataSnapshot.exists()) {
                    myCompanyRef.child(post_key).child("Module 6").child("Customer Points").child("Discounts").child("discount1").setValue("5");
                    myCompanyRef.child(post_key).child("Module 6").child("Customer Points").child("Discounts").child("discount2").setValue("10");
                    myCompanyRef.child(post_key).child("Module 6").child("Customer Points").child("Discounts").child("discount3").setValue("15");
                    myCompanyRef.child(post_key).child("Module 6").child("Customer Points").child("Discounts").child("discount4").setValue("20");
                    myCompanyRef.child(post_key).child("Module 6").child("Customer Points").child("Discounts").child("discount5").setValue("25");

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return view;
    }

    private void showChangeValuesDialog() {
        final AlertDialog dialog = new AlertDialog.Builder(getActivity()).create();

        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View finance_method = inflater.inflate(R.layout.change_range_and_points_dialog,null);

        final EditText edtDiscount;
        Button btnRegister;
        final RelativeLayout rootLayout;

        edtDiscount = finance_method.findViewById(R.id.edtPoints);
        btnRegister = finance_method.findViewById(R.id.btnRegister);
        rootLayout = finance_method.findViewById(R.id.rootLayout);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(edtDiscount.getText().toString())) {
                    Snackbar.make(rootLayout,"Debes ingresar el descuento",Snackbar.LENGTH_SHORT);
                } else {
                    myCompanyRef.child(post_key).child("Module 6").child("Customer Points").child("Discounts").child(discountRef).setValue(edtDiscount.getText().toString());
                    dialog.dismiss();
                }
            }
        });

        dialog.setView(finance_method);
        dialog.show();

    }
}
