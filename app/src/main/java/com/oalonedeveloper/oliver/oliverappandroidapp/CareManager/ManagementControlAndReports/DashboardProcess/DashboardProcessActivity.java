package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.ManagementControlAndReports.DashboardProcess;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;
import com.xw.repo.BubbleSeekBar;

import java.text.DecimalFormat;

public class DashboardProcessActivity extends AppCompatActivity {

    TextView txtProgress1,txtProgress2,txtProgress3,txtProgress4,txtProgress5,txtProgress6;
    BubbleSeekBar barProgress1,barProgress2,barProgress3,barProgress4,barProgress5,barProgress6;
    ImageView btnDetails1,img1_1,img1_2,img1_3,img1_4,btnDetails2,img2_1,img2_2,img2_3,img2_4, btnDetails3,img3_1,img3_2,img3_3,img3_4,img3_5,btnDetails4,img4_1,img4_2,img4_3,img4_4,btnDetails5,img5_1,img5_2,img5_3,img5_4,
            btnDetails6,img6_1,img6_2,img6_3,img6_4,img6_5;
    double tool1,tool2,tool3,tool4,total1,tool21,tool22,tool23,tool24,total2,tool31,tool32,tool33,tool34,tool35,total3,tool41,tool42,tool43,tool44,total4,tool51,tool52,tool53,tool54,total5,tool61,tool62,tool63,tool64,tool65,total6;
    String total1_st,verify1,total2_st,verify2,total3_st,verify3,total4_st,verify4,total5_st,verify5,total6_st,verify6;
    LinearLayout layout1,layout2,layout3,layout4,layout5,layout6;

    String post_key;
    DatabaseReference companyRef;

    DecimalFormat decimalFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_process);

        txtProgress1 = findViewById(R.id.txtProgress1);
        barProgress1 = findViewById(R.id.barProgress1);
        btnDetails1 = findViewById(R.id.btnDetails1);
        img1_1 = findViewById(R.id.img1_1);
        img1_2 = findViewById(R.id.img1_2);
        img1_3 = findViewById(R.id.img1_3);
        img1_4 = findViewById(R.id.img1_4);
        layout1 = findViewById(R.id.layout1);

        txtProgress2 = findViewById(R.id.txtProgress2);
        barProgress2 = findViewById(R.id.barProgress2);
        btnDetails2 = findViewById(R.id.btnDetails2);
        img2_1 = findViewById(R.id.img2_1);
        img2_2 = findViewById(R.id.img2_2);
        img2_3 = findViewById(R.id.img2_3);
        img2_4 = findViewById(R.id.img2_4);
        layout2 = findViewById(R.id.layout2);

        txtProgress3 = findViewById(R.id.txtProgress3);
        barProgress3 = findViewById(R.id.barProgress3);
        btnDetails3 = findViewById(R.id.btnDetails3);
        img3_1 = findViewById(R.id.img3_1);
        img3_2 = findViewById(R.id.img3_2);
        img3_3 = findViewById(R.id.img3_3);
        img3_4 = findViewById(R.id.img3_4);
        img3_5 = findViewById(R.id.img3_5);
        layout3 = findViewById(R.id.layout3);

        txtProgress4 = findViewById(R.id.txtProgress4);
        barProgress4 = findViewById(R.id.barProgress4);
        btnDetails4 = findViewById(R.id.btnDetails4);
        img4_1 = findViewById(R.id.img4_1);
        img4_2 = findViewById(R.id.img4_2);
        img4_3 = findViewById(R.id.img4_3);
        img4_4 = findViewById(R.id.img4_4);
        layout4 = findViewById(R.id.layout4);

        txtProgress5 = findViewById(R.id.txtProgress5);
        barProgress5 = findViewById(R.id.barProgress5);
        btnDetails5 = findViewById(R.id.btnDetails5);
        img5_1 = findViewById(R.id.img5_1);
        img5_2 = findViewById(R.id.img5_2);
        img5_3 = findViewById(R.id.img5_3);
        img5_4 = findViewById(R.id.img5_4);
        layout5 = findViewById(R.id.layout5);

        txtProgress6 = findViewById(R.id.txtProgress6);
        barProgress6 = findViewById(R.id.barProgress6);
        btnDetails6 = findViewById(R.id.btnDetails6);
        img6_1 = findViewById(R.id.img6_1);
        img6_2 = findViewById(R.id.img6_2);
        img6_3 = findViewById(R.id.img6_3);
        img6_4 = findViewById(R.id.img6_4);
        img6_5 = findViewById(R.id.img6_5);
        layout6 = findViewById(R.id.layout6);


        decimalFormat = new DecimalFormat("0.00");

        post_key = getIntent().getExtras().getString("post_key");
        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");

        layout1.setVisibility(View.GONE);
        verify1 = "close";
        layout2.setVisibility(View.GONE);
        verify2 = "close";
        layout3.setVisibility(View.GONE);
        verify3 = "close";
        layout4.setVisibility(View.GONE);
        verify4 = "close";
        layout5.setVisibility(View.GONE);
        verify5 = "close";
        layout6.setVisibility(View.GONE);
        verify6 = "close";

        btnDetails1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (verify1.equals("close")) {
                    btnDetails1.setImageResource(R.drawable.arrow_up_vector_asset);
                    layout1.setVisibility(View.VISIBLE);
                    verify1 = "open";

                } else if (verify1.equals("open")) {
                    btnDetails1.setImageResource(R.drawable.arrow_down_vector_asset);
                    layout1.setVisibility(View.GONE);
                    verify1 = "close";
                }

            }
        });

        companyRef.child(post_key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.child("Module 6").hasChild("Matrix 10") && dataSnapshot.child("Module 6").hasChild("Matrix 10 Companies")) {
                    tool1 = 25.0;
                    img1_1.setImageResource(R.drawable.check);
                }
                if (dataSnapshot.hasChild("My Bills")) {
                    tool2 = 25.0;
                    tool3 = 25.0;
                    img1_2.setImageResource(R.drawable.check);
                    img1_3.setImageResource(R.drawable.check);
                }
                if (dataSnapshot.child("Module 6").hasChild("Customer Points")) {
                    tool4 = 25.0;
                    img1_4.setImageResource(R.drawable.check);
                }

                total1 = tool1+tool2+tool3+tool4;
                total1_st = decimalFormat.format(total1);

                txtProgress1.setText("Progreso: "+total1_st+"%");
                barProgress1.setProgress((float) total1);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        btnDetails2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (verify2.equals("close")) {
                    btnDetails2.setImageResource(R.drawable.arrow_up_vector_asset);
                    layout2.setVisibility(View.VISIBLE);
                    verify2 = "open";

                } else if (verify2.equals("open")) {
                    btnDetails2.setImageResource(R.drawable.arrow_down_vector_asset);
                    layout2.setVisibility(View.GONE);
                    verify2 = "close";
                }

            }
        });

        companyRef.child(post_key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild("Sellers")) {
                    tool21 = 25.0;
                    tool22 = 25.0;
                    img2_1.setImageResource(R.drawable.check);
                    img2_2.setImageResource(R.drawable.check);
                }
                if (dataSnapshot.hasChild("Customer Schedules")) {
                    tool23 = 25.0;
                    img2_3.setImageResource(R.drawable.check);
                }
                if (dataSnapshot.child("Module 7").hasChild("Sales Projection")) {
                    tool24 = 25.0;
                    img2_4.setImageResource(R.drawable.check);
                }

                total2 = tool21+tool22+tool23+tool24;
                total2_st = decimalFormat.format(total2);

                txtProgress2.setText("Progreso: "+total2_st+"%");
                barProgress2.setProgress((float) total2);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        btnDetails3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (verify3.equals("close")) {
                    btnDetails3.setImageResource(R.drawable.arrow_up_vector_asset);
                    layout3.setVisibility(View.VISIBLE);
                    verify3 = "open";

                } else if (verify3.equals("open")) {
                    btnDetails3.setImageResource(R.drawable.arrow_down_vector_asset);
                    layout3.setVisibility(View.GONE);
                    verify3 = "close";
                }

            }
        });

        companyRef.child(post_key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild("Purchased Orders")) {
                    tool31 = 20.0;
                    tool32 = 20.0;
                    img3_1.setImageResource(R.drawable.check);
                    img3_2.setImageResource(R.drawable.check);
                }
                if (dataSnapshot.hasChild("Warehouses")) {
                    tool33 = 20.0;
                    img3_3.setImageResource(R.drawable.check);
                }
                if (dataSnapshot.hasChild("Orders Processing")) {
                    tool34 = 20.0;
                    img3_4.setImageResource(R.drawable.check);
                }
                if (dataSnapshot.hasChild("Dispatches")) {
                    tool35 = 20.0;
                    img3_5.setImageResource(R.drawable.check);
                }

                total3 = tool31+tool32+tool33+tool34+tool35;
                total3_st = decimalFormat.format(total3);

                txtProgress3.setText("Progreso: "+total3_st+"%");
                barProgress3.setProgress((float) total3);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        btnDetails4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (verify4.equals("close")) {
                    btnDetails4.setImageResource(R.drawable.arrow_up_vector_asset);
                    layout4.setVisibility(View.VISIBLE);
                    verify4 = "open";

                } else if (verify4.equals("open")) {
                    btnDetails4.setImageResource(R.drawable.arrow_down_vector_asset);
                    layout4.setVisibility(View.GONE);
                    verify4 = "close";
                }

            }
        });

        companyRef.child(post_key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild("Production Lines")) {
                    tool41 = 25.0;
                    img4_1.setImageResource(R.drawable.check);
                }
                if (dataSnapshot.hasChild("Production Chain")) {
                    tool42 = 25.0;
                    img4_2.setImageResource(R.drawable.check);
                }
                if (dataSnapshot.hasChild("Machinery Maintenance")) {
                    tool43 = 25.0;
                    img4_3.setImageResource(R.drawable.check);
                }
                if (dataSnapshot.hasChild("Lean Manufacturing")) {
                    tool44 = 25.0;
                    img4_4.setImageResource(R.drawable.check);
                }

                total4 = tool41+tool42+tool43+tool44;
                total4_st = decimalFormat.format(total4);

                txtProgress4.setText("Progreso: "+total4_st+"%");
                barProgress4.setProgress((float) total4);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        btnDetails5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (verify5.equals("close")) {
                    btnDetails5.setImageResource(R.drawable.arrow_up_vector_asset);
                    layout5.setVisibility(View.VISIBLE);
                    verify5 = "open";

                } else if (verify5.equals("open")) {
                    btnDetails5.setImageResource(R.drawable.arrow_down_vector_asset);
                    layout5.setVisibility(View.GONE);
                    verify5 = "close";
                }

            }
        });

        companyRef.child(post_key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild("Job Profile")) {
                    tool51 = 25.0;
                    img5_1.setImageResource(R.drawable.check);
                    tool52 = 25.0;
                    img5_2.setImageResource(R.drawable.check);
                }
                if (dataSnapshot.hasChild("People Management Objectives")) {
                    tool53 = 25.0;
                    img5_3.setImageResource(R.drawable.check);
                    tool54 = 25.0;
                    img5_4.setImageResource(R.drawable.check);
                }

                total5 = tool51+tool52+tool53+tool54;
                total5_st = decimalFormat.format(total5);

                txtProgress5.setText("Progreso: "+total5_st+"%");
                barProgress5.setProgress((float) total5);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        btnDetails6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (verify6.equals("close")) {
                    btnDetails6.setImageResource(R.drawable.arrow_up_vector_asset);
                    layout6.setVisibility(View.VISIBLE);
                    verify6 = "open";

                } else if (verify6.equals("open")) {
                    btnDetails6.setImageResource(R.drawable.arrow_down_vector_asset);
                    layout6.setVisibility(View.GONE);
                    verify6 = "close";
                }

            }
        });

        companyRef.child(post_key).child("Normativity").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild("Taxes")) {
                    tool61 = 20.0;
                    img6_1.setImageResource(R.drawable.check);
                }
                if (dataSnapshot.hasChild("Labour")) {
                    tool62 = 20.0;
                    img6_2.setImageResource(R.drawable.check);
                }
                if (dataSnapshot.hasChild("Sanitary")) {
                    tool63 = 20.0;
                    img6_3.setImageResource(R.drawable.check);
                }
                if (dataSnapshot.hasChild("Environmental")) {
                    tool64 = 20.0;
                    img6_4.setImageResource(R.drawable.check);
                }
                if (dataSnapshot.hasChild("Civil")) {
                    tool65 = 20.0;
                    img6_5.setImageResource(R.drawable.check);
                }

                total6 = tool61+tool62+tool63+tool64+tool65;
                total6_st = decimalFormat.format(total6);

                txtProgress6.setText("Progreso: "+total6_st+"%");
                barProgress6.setProgress((float) total6);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
