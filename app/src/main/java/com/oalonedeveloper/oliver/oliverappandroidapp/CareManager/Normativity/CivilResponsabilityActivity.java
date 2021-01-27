package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Normativity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;

import java.text.DecimalFormat;

public class CivilResponsabilityActivity extends AppCompatActivity {

    String post_key;
    DatabaseReference companyRef;
    int norm_1,norm_2,norm_3,norm_4,norm_5,norm_6,norm_7,norm_8;
    RadioButton rdYes1,rdNo1,rdYes2,rdNo2,rdYes3,rdNo3,rdYes4,rdNo4,rdYes5,rdNo5,rdYes6,rdNo6,rdYes7,rdNo7,rdYes8,rdNo8;
    TextView txtTotal;
    DecimalFormat decimalFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_civil_responsability);

        post_key = getIntent().getExtras().getString("post_key");
        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");

        rdYes1 = findViewById(R.id.rdYes1);
        rdNo1 = findViewById(R.id.rdNo1);
        rdYes2 = findViewById(R.id.rdYes2);
        rdNo2 = findViewById(R.id.rdNo2);
        rdYes3 = findViewById(R.id.rdYes3);
        rdNo3 = findViewById(R.id.rdNo3);
        rdYes4 = findViewById(R.id.rdYes4);
        rdNo4 = findViewById(R.id.rdNo4);
        rdYes5 = findViewById(R.id.rdYes5);
        rdNo5 = findViewById(R.id.rdNo5);
        rdYes6 = findViewById(R.id.rdYes6);
        rdNo6 = findViewById(R.id.rdNo6);
        rdYes7 = findViewById(R.id.rdYes7);
        rdNo7 = findViewById(R.id.rdNo7);
        rdYes8 = findViewById(R.id.rdYes8);
        rdNo8 = findViewById(R.id.rdNo8);
        txtTotal = findViewById(R.id.txtTotal);

        decimalFormat = new DecimalFormat("0.00");

        rdYes1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                companyRef.child(post_key).child("Normativity").child("Civil").child("norm_1").setValue(1);
            }
        });

        rdNo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                companyRef.child(post_key).child("Normativity").child("Civil").child("norm_1").setValue(0);
            }
        });

        rdYes2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                companyRef.child(post_key).child("Normativity").child("Civil").child("norm_2").setValue(1);
            }
        });

        rdNo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                companyRef.child(post_key).child("Normativity").child("Civil").child("norm_2").setValue(0);
            }
        });

        rdYes3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                companyRef.child(post_key).child("Normativity").child("Civil").child("norm_3").setValue(1);
            }
        });

        rdNo3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                companyRef.child(post_key).child("Normativity").child("Civil").child("norm_3").setValue(0);
            }
        });

        rdYes4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                companyRef.child(post_key).child("Normativity").child("Civil").child("norm_4").setValue(1);
            }
        });

        rdNo4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                companyRef.child(post_key).child("Normativity").child("Civil").child("norm_4").setValue(0);
            }
        });

        rdYes5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                companyRef.child(post_key).child("Normativity").child("Civil").child("norm_5").setValue(1);
            }
        });

        rdNo5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                companyRef.child(post_key).child("Normativity").child("Civil").child("norm_5").setValue(0);
            }
        });

        rdYes6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                companyRef.child(post_key).child("Normativity").child("Civil").child("norm_6").setValue(1);
            }
        });

        rdNo6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                companyRef.child(post_key).child("Normativity").child("Civil").child("norm_6").setValue(0);
            }
        });

        rdYes7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                companyRef.child(post_key).child("Normativity").child("Civil").child("norm_7").setValue(1);
            }
        });

        rdNo7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                companyRef.child(post_key).child("Normativity").child("Civil").child("norm_7").setValue(0);
            }
        });

        rdYes8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                companyRef.child(post_key).child("Normativity").child("Civil").child("norm_8").setValue(1);
            }
        });

        rdNo8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                companyRef.child(post_key).child("Normativity").child("Civil").child("norm_8").setValue(0);
            }
        });


        companyRef.child(post_key).child("Normativity").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild("Civil")) {
                    if (dataSnapshot.child("Civil").hasChild("norm_1")) {
                        norm_1 = dataSnapshot.child("Civil").child("norm_1").getValue(Integer.class);
                    }
                    if (dataSnapshot.child("Civil").hasChild("norm_2")) {
                        norm_2 = dataSnapshot.child("Civil").child("norm_2").getValue(Integer.class);
                    }
                    if (dataSnapshot.child("Civil").hasChild("norm_3")) {
                        norm_3 = dataSnapshot.child("Civil").child("norm_3").getValue(Integer.class);
                    }
                    if (dataSnapshot.child("Civil").hasChild("norm_4")) {
                        norm_4 = dataSnapshot.child("Civil").child("norm_4").getValue(Integer.class);
                    }
                    if (dataSnapshot.child("Civil").hasChild("norm_5")) {
                        norm_5 = dataSnapshot.child("Civil").child("norm_5").getValue(Integer.class);
                    }
                    if (dataSnapshot.child("Civil").hasChild("norm_6")) {
                        norm_6 = dataSnapshot.child("Civil").child("norm_6").getValue(Integer.class);
                    }
                    if (dataSnapshot.child("Civil").hasChild("norm_7")) {
                        norm_7 = dataSnapshot.child("Civil").child("norm_7").getValue(Integer.class);
                    }
                    if (dataSnapshot.child("Civil").hasChild("norm_8")) {
                        norm_8 = dataSnapshot.child("Civil").child("norm_8").getValue(Integer.class);
                    }

                    double total_percent = (norm_1+norm_2+norm_3+norm_4+norm_5+norm_6+norm_7+norm_8);
                    double total_percent_real = (total_percent/8)*100;
                    String total_percent_st = decimalFormat.format(total_percent_real);
                    txtTotal.setText("TOTAL CUMPLIMIENTO: "+total_percent_st+"%");

                } else {
                    companyRef.child(post_key).child("Normativity").child("Civil").child("norm_1").setValue(0);
                    companyRef.child(post_key).child("Normativity").child("Civil").child("norm_2").setValue(0);
                    companyRef.child(post_key).child("Normativity").child("Civil").child("norm_3").setValue(0);
                    companyRef.child(post_key).child("Normativity").child("Civil").child("norm_4").setValue(0);
                    companyRef.child(post_key).child("Normativity").child("Civil").child("norm_5").setValue(0);
                    companyRef.child(post_key).child("Normativity").child("Civil").child("norm_6").setValue(0);
                    companyRef.child(post_key).child("Normativity").child("Civil").child("norm_7").setValue(0);
                    companyRef.child(post_key).child("Normativity").child("Civil").child("norm_8").setValue(0);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
