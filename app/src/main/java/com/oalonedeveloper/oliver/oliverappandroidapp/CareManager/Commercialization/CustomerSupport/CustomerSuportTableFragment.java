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
    String discount1,discount2,discount3,discount4,discount5,points1,points2,points3,points4,points5,points6;
    TextView  txtDiscount1,txtDiscount2,txtDiscount3,txtDiscount4,txtDiscount5,txtPoints1,txtPoints2,txtPoints3,txtPoints4,txtPoints5,txtPoints6;
    ImageView btnChangeDiscount1,btnChangeDiscount2,btnChangeDiscount3,btnChangeDiscount4,btnChangeDiscount5,btnPoints1,btnPoints2,btnPoints3,btnPoints4,btnPoints5,btnPoints6;
    String discountRef,pointsRef;

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
        txtPoints1 = view.findViewById(R.id.txtPoints1);
        txtPoints2 = view.findViewById(R.id.txtPoints2);
        txtPoints3 = view.findViewById(R.id.txtPoints3);
        txtPoints4 = view.findViewById(R.id.txtPoints4);
        txtPoints5 = view.findViewById(R.id.txtPoints5);
        txtPoints6 = view.findViewById(R.id.txtPoints6);
        btnPoints1 = view.findViewById(R.id.btnPoints1);
        btnPoints2 = view.findViewById(R.id.btnPoints2);
        btnPoints3 = view.findViewById(R.id.btnPoints3);
        btnPoints4 = view.findViewById(R.id.btnPoints4);
        btnPoints5 = view.findViewById(R.id.btnPoints5);
        btnPoints6 = view.findViewById(R.id.btnPoints6);

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

        btnPoints1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pointsRef = "points1";
                showPointsChangeDialog();
            }
        });
        btnPoints2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pointsRef = "points2";
                showPointsChangeDialog();
            }
        });
        btnPoints3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pointsRef = "points3";
                showPointsChangeDialog();
            }
        });
        btnPoints4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pointsRef = "points4";
                showPointsChangeDialog();
            }
        });
        btnPoints5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pointsRef = "points5";
                showPointsChangeDialog();
            }
        });
        btnPoints6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pointsRef = "points6";
                showPointsChangeDialog();
            }
        });


        myCompanyRef.child(post_key).child("Module 6").child("Customer Points").child("Discounts").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild("discount1")) {
                    discount1 = dataSnapshot.child("discount1").getValue().toString();
                    txtDiscount1.setText(discount1+"%");
                }
                if (dataSnapshot.hasChild("discount2")) {
                    discount2 = dataSnapshot.child("discount2").getValue().toString();
                    txtDiscount2.setText(discount2+"%");
                }
                if (dataSnapshot.hasChild("discount3")) {
                    discount3 = dataSnapshot.child("discount3").getValue().toString();
                    txtDiscount3.setText(discount3+"%");
                }
                if (dataSnapshot.hasChild("discount4")) {
                    discount4 = dataSnapshot.child("discount4").getValue().toString();
                    txtDiscount4.setText(discount4+"%");
                }
                if (dataSnapshot.hasChild("discount5")) {
                    discount5 = dataSnapshot.child("discount5").getValue().toString();
                    txtDiscount5.setText(discount5+"%");
                }

                myCompanyRef.child(post_key).child("Module 6").child("Customer Points").child("Points").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChild("points1")) {
                            points1 = dataSnapshot.child("points1").getValue().toString();
                            txtPoints1.setText(points1);
                        }
                        if (dataSnapshot.hasChild("points2")) {
                            points2 = dataSnapshot.child("points2").getValue().toString();
                            txtPoints2.setText(points2);
                        }
                        if (dataSnapshot.hasChild("points3")) {
                            points3 = dataSnapshot.child("points3").getValue().toString();
                            txtPoints3.setText(points3);
                        }
                        if (dataSnapshot.hasChild("points4")) {
                            points4 = dataSnapshot.child("points4").getValue().toString();
                            txtPoints4.setText(points4);
                        }
                        if (dataSnapshot.hasChild("points5")) {
                            points5 = dataSnapshot.child("points5").getValue().toString();
                            txtPoints5.setText(points5);
                        }
                        if (dataSnapshot.hasChild("points6")) {
                            points6 = dataSnapshot.child("points6").getValue().toString();
                            txtPoints6.setText(points6);
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

        return view;
    }

    private void showPointsChangeDialog() {
        final AlertDialog dialog = new AlertDialog.Builder(getActivity()).create();

        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View finance_method = inflater.inflate(R.layout.change_period_points,null);

        final EditText edtDiscount;
        Button btnRegister;
        final RelativeLayout rootLayout;

        edtDiscount = finance_method.findViewById(R.id.edtDiscount);
        btnRegister = finance_method.findViewById(R.id.btnRegister);
        rootLayout = finance_method.findViewById(R.id.rootLayout);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(edtDiscount.getText().toString())) {
                    Snackbar.make(rootLayout,"Debes ingresar los puntos",Snackbar.LENGTH_SHORT);
                } else {
                    myCompanyRef.child(post_key).child("Module 6").child("Customer Points").child("Points").child(pointsRef).setValue(edtDiscount.getText().toString());
                    dialog.dismiss();
                }
            }
        });

        dialog.setView(finance_method);
        dialog.show();

    }

    private void showChangeValuesDialog() {
        final AlertDialog dialog = new AlertDialog.Builder(getActivity()).create();

        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View finance_method = inflater.inflate(R.layout.change_range_and_points_dialog,null);

        final EditText edtDiscount;
        Button btnRegister;
        final RelativeLayout rootLayout;

        edtDiscount = finance_method.findViewById(R.id.edtDiscount);
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
