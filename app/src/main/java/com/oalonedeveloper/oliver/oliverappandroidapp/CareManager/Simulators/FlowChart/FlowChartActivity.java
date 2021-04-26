package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Simulators.FlowChart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.AlertDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;

import es.dmoral.toasty.Toasty;

public class FlowChartActivity extends AppCompatActivity {

    CardView card1,card2,card3,card4,card5,card6,card7,card8,card9,card10,card11,card12,card13,card14,card15,card16,card17,card18,card19,card20,card21,card22,card23,card24,card25;
    LinearLayout layout1,layout2,layout3,layout4,layout5,layout6,layout7,layout8,layout9,layout10,layout11,layout12,layout13,layout14,layout15,layout16,layout17,layout18,layout19,layout20,layout21,layout22,layout23,layout24,layout25;
    TextView txt1,txt2,txt3,txt4,txt5,txt6,txt7,txt8,txt9,txt10,txt11,txt12,txt13,txt14,txt15,txt16,txt17,txt18,txt19,txt20,txt21,txt22,txt23,txt24,txt25;
    ImageView btnArrow1,btnArrow2,btnArrow3,btnArrow4,btnArrow5,btnArrow6,btnArrow7,btnArrow8,btnArrow9,btnArrow10,btnArrow11,btnArrow12,btnArrow13,btnArrow14,btnArrow15,btnArrow16,btnArrow17,btnArrow18,
            btnArrow19,btnArrow20,btnArrow21,btnArrow22,btnArrow23,btnArrow24,btnArrow25,btnArrow26,btnArrow27,btnArrow28,btnArrow29,btnArrow30;

    DatabaseReference companyRef;
    String post_key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flow_chart);

        post_key = getIntent().getExtras().getString("post_key");
        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");

        card1 = findViewById(R.id.card1);
        card2 = findViewById(R.id.card2);
        card3 = findViewById(R.id.card3);
        card4 = findViewById(R.id.card4);
        card5 = findViewById(R.id.card5);
        card6 = findViewById(R.id.card6);
        card7 = findViewById(R.id.card7);
        card8 = findViewById(R.id.card8);
        card9 = findViewById(R.id.card9);
        card10 = findViewById(R.id.card10);
        card11 = findViewById(R.id.card11);
        card12 = findViewById(R.id.card12);
        card13 = findViewById(R.id.card13);
        card14 = findViewById(R.id.card14);
        card15 = findViewById(R.id.card15);
        card16 = findViewById(R.id.card16);
        card17 = findViewById(R.id.card17);
        card18 = findViewById(R.id.card18);
        card19 = findViewById(R.id.card19);
        card20 = findViewById(R.id.card20);
        card21 = findViewById(R.id.card21);
        card22 = findViewById(R.id.card22);
        card23 = findViewById(R.id.card23);
        card24 = findViewById(R.id.card24);
        card25 = findViewById(R.id.card25);

        layout1 = findViewById(R.id.layout1);
        layout2 = findViewById(R.id.layout2);
        layout3 = findViewById(R.id.layout3);
        layout4 = findViewById(R.id.layout4);
        layout5 = findViewById(R.id.layout5);
        layout6 = findViewById(R.id.layout6);
        layout7 = findViewById(R.id.layout7);
        layout8 = findViewById(R.id.layout8);
        layout9 = findViewById(R.id.layout9);
        layout10 = findViewById(R.id.layout10);
        layout11 = findViewById(R.id.layout11);
        layout12 = findViewById(R.id.layout12);
        layout13 = findViewById(R.id.layout13);
        layout14 = findViewById(R.id.layout14);
        layout15 = findViewById(R.id.layout15);
        layout16 = findViewById(R.id.layout16);
        layout17 = findViewById(R.id.layout17);
        layout18 = findViewById(R.id.layout18);
        layout19 = findViewById(R.id.layout19);
        layout20 = findViewById(R.id.layout20);
        layout21 = findViewById(R.id.layout21);
        layout22 = findViewById(R.id.layout22);
        layout23 = findViewById(R.id.layout23);
        layout24 = findViewById(R.id.layout24);
        layout25 = findViewById(R.id.layout25);

        txt1 = findViewById(R.id.txt1);
        txt2 = findViewById(R.id.txt2);
        txt3 = findViewById(R.id.txt3);
        txt4 = findViewById(R.id.txt4);
        txt5 = findViewById(R.id.txt5);
        txt6 = findViewById(R.id.txt6);
        txt7 = findViewById(R.id.txt7);
        txt8 = findViewById(R.id.txt8);
        txt9 = findViewById(R.id.txt9);
        txt10 = findViewById(R.id.txt10);
        txt11 = findViewById(R.id.txt11);
        txt12 = findViewById(R.id.txt12);
        txt13 = findViewById(R.id.txt13);
        txt14 = findViewById(R.id.txt14);
        txt15 = findViewById(R.id.txt15);
        txt16 = findViewById(R.id.txt16);
        txt17 = findViewById(R.id.txt17);
        txt18 = findViewById(R.id.txt18);
        txt19 = findViewById(R.id.txt19);
        txt20 = findViewById(R.id.txt20);
        txt21 = findViewById(R.id.txt21);
        txt22 = findViewById(R.id.txt22);
        txt23 = findViewById(R.id.txt23);
        txt24 = findViewById(R.id.txt24);
        txt25 = findViewById(R.id.txt25);

        btnArrow1 = findViewById(R.id.btnArrow1);
        btnArrow2 = findViewById(R.id.btnArrow2);
        btnArrow3 = findViewById(R.id.btnArrow3);
        btnArrow4 = findViewById(R.id.btnArrow4);
        btnArrow5 = findViewById(R.id.btnArrow5);
        btnArrow6 = findViewById(R.id.btnArrow6);
        btnArrow7 = findViewById(R.id.btnArrow7);
        btnArrow8 = findViewById(R.id.btnArrow8);
        btnArrow9 = findViewById(R.id.btnArrow9);
        btnArrow10 = findViewById(R.id.btnArrow10);
        btnArrow11 = findViewById(R.id.btnArrow11);
        btnArrow12 = findViewById(R.id.btnArrow12);
        btnArrow13 = findViewById(R.id.btnArrow13);
        btnArrow14 = findViewById(R.id.btnArrow14);
        btnArrow15 = findViewById(R.id.btnArrow15);
        btnArrow16 = findViewById(R.id.btnArrow16);
        btnArrow17 = findViewById(R.id.btnArrow17);
        btnArrow18 = findViewById(R.id.btnArrow18);
        btnArrow19 = findViewById(R.id.btnArrow19);
        btnArrow20 = findViewById(R.id.btnArrow20);
        btnArrow21 = findViewById(R.id.btnArrow21);
        btnArrow22 = findViewById(R.id.btnArrow22);
        btnArrow23 = findViewById(R.id.btnArrow23);
        btnArrow24 = findViewById(R.id.btnArrow24);
        btnArrow25 = findViewById(R.id.btnArrow25);
        btnArrow26 = findViewById(R.id.btnArrow26);
        btnArrow27 = findViewById(R.id.btnArrow27);
        btnArrow28 = findViewById(R.id.btnArrow28);
        btnArrow29 = findViewById(R.id.btnArrow29);
        btnArrow30 = findViewById(R.id.btnArrow30);

        companyRef.child(post_key).child("Flow Chart").child("item_1").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild("name")) {
                    String value = dataSnapshot.child("name").getValue().toString();
                    txt1.setText(value);
                }
                if (dataSnapshot.hasChild("shape")) {
                    String value = dataSnapshot.child("shape").getValue().toString();
                    if (value.equals("circle")) {
                        txt1.setBackgroundResource(R.drawable.circulo);
                    }
                    if (value.equals("rectangle")) {
                        txt1.setBackgroundResource(R.drawable.cuadrado);
                    }
                    if (value.equals("diamond")) {
                        txt1.setBackgroundResource(R.drawable.rombo);
                    }
                }

                companyRef.child(post_key).child("Flow Chart").child("item_2").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChild("name")) {
                            String value = dataSnapshot.child("name").getValue().toString();
                            txt2.setText(value);
                        }
                        if (dataSnapshot.hasChild("shape")) {
                            String value = dataSnapshot.child("shape").getValue().toString();
                            if (value.equals("circle")) {
                                txt2.setBackgroundResource(R.drawable.circulo);
                            }
                            if (value.equals("rectangle")) {
                                txt2.setBackgroundResource(R.drawable.cuadrado);
                            }
                            if (value.equals("diamond")) {
                                txt2.setBackgroundResource(R.drawable.rombo);
                            }
                        }

                        companyRef.child(post_key).child("Flow Chart").child("item_3").addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                if (dataSnapshot.hasChild("name")) {
                                    String value = dataSnapshot.child("name").getValue().toString();
                                    txt3.setText(value);
                                }
                                if (dataSnapshot.hasChild("shape")) {
                                    String value = dataSnapshot.child("shape").getValue().toString();
                                    if (value.equals("circle")) {
                                        txt3.setBackgroundResource(R.drawable.circulo);
                                    }
                                    if (value.equals("rectangle")) {
                                        txt3.setBackgroundResource(R.drawable.cuadrado);
                                    }
                                    if (value.equals("diamond")) {
                                        txt3.setBackgroundResource(R.drawable.rombo);
                                    }
                                }

                                companyRef.child(post_key).child("Flow Chart").child("item_4").addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        if (dataSnapshot.hasChild("name")) {
                                            String value = dataSnapshot.child("name").getValue().toString();
                                            txt4.setText(value);
                                        }
                                        if (dataSnapshot.hasChild("shape")) {
                                            String value = dataSnapshot.child("shape").getValue().toString();
                                            if (value.equals("circle")) {
                                                txt4.setBackgroundResource(R.drawable.circulo);
                                            }
                                            if (value.equals("rectangle")) {
                                                txt4.setBackgroundResource(R.drawable.cuadrado);
                                            }
                                            if (value.equals("diamond")) {
                                                txt4.setBackgroundResource(R.drawable.rombo);
                                            }
                                        }

                                        companyRef.child(post_key).child("Flow Chart").child("item_5").addValueEventListener(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                if (dataSnapshot.hasChild("name")) {
                                                    String value = dataSnapshot.child("name").getValue().toString();
                                                    txt5.setText(value);
                                                }
                                                if (dataSnapshot.hasChild("shape")) {
                                                    String value = dataSnapshot.child("shape").getValue().toString();
                                                    if (value.equals("circle")) {
                                                        txt5.setBackgroundResource(R.drawable.circulo);
                                                    }
                                                    if (value.equals("rectangle")) {
                                                        txt5.setBackgroundResource(R.drawable.cuadrado);
                                                    }
                                                    if (value.equals("diamond")) {
                                                        txt5.setBackgroundResource(R.drawable.rombo);
                                                    }
                                                }

                                                companyRef.child(post_key).child("Flow Chart").child("item_6").addValueEventListener(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                                        if (dataSnapshot.hasChild("name")) {
                                                            String value = dataSnapshot.child("name").getValue().toString();
                                                            txt6.setText(value);
                                                        }
                                                        if (dataSnapshot.hasChild("shape")) {
                                                            String value = dataSnapshot.child("shape").getValue().toString();
                                                            if (value.equals("circle")) {
                                                                txt6.setBackgroundResource(R.drawable.circulo);
                                                            }
                                                            if (value.equals("rectangle")) {
                                                                txt6.setBackgroundResource(R.drawable.cuadrado);
                                                            }
                                                            if (value.equals("diamond")) {
                                                                txt6.setBackgroundResource(R.drawable.rombo);
                                                            }
                                                        }

                                                        companyRef.child(post_key).child("Flow Chart").child("item_7").addValueEventListener(new ValueEventListener() {
                                                            @Override
                                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                                if (dataSnapshot.hasChild("name")) {
                                                                    String value = dataSnapshot.child("name").getValue().toString();
                                                                    txt7.setText(value);
                                                                }
                                                                if (dataSnapshot.hasChild("shape")) {
                                                                    String value = dataSnapshot.child("shape").getValue().toString();
                                                                    if (value.equals("circle")) {
                                                                        txt7.setBackgroundResource(R.drawable.circulo);
                                                                    }
                                                                    if (value.equals("rectangle")) {
                                                                        txt7.setBackgroundResource(R.drawable.cuadrado);
                                                                    }
                                                                    if (value.equals("diamond")) {
                                                                        txt7.setBackgroundResource(R.drawable.rombo);
                                                                    }
                                                                }

                                                                companyRef.child(post_key).child("Flow Chart").child("item_8").addValueEventListener(new ValueEventListener() {
                                                                    @Override
                                                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                                                        if (dataSnapshot.hasChild("name")) {
                                                                            String value = dataSnapshot.child("name").getValue().toString();
                                                                            txt8.setText(value);
                                                                        }
                                                                        if (dataSnapshot.hasChild("shape")) {
                                                                            String value = dataSnapshot.child("shape").getValue().toString();
                                                                            if (value.equals("circle")) {
                                                                                txt8.setBackgroundResource(R.drawable.circulo);
                                                                            }
                                                                            if (value.equals("rectangle")) {
                                                                                txt8.setBackgroundResource(R.drawable.cuadrado);
                                                                            }
                                                                            if (value.equals("diamond")) {
                                                                                txt8.setBackgroundResource(R.drawable.rombo);
                                                                            }
                                                                        }

                                                                        companyRef.child(post_key).child("Flow Chart").child("item_9").addValueEventListener(new ValueEventListener() {
                                                                            @Override
                                                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                                                if (dataSnapshot.hasChild("name")) {
                                                                                    String value = dataSnapshot.child("name").getValue().toString();
                                                                                    txt9.setText(value);
                                                                                }
                                                                                if (dataSnapshot.hasChild("shape")) {
                                                                                    String value = dataSnapshot.child("shape").getValue().toString();
                                                                                    if (value.equals("circle")) {
                                                                                        txt9.setBackgroundResource(R.drawable.circulo);
                                                                                    }
                                                                                    if (value.equals("rectangle")) {
                                                                                        txt9.setBackgroundResource(R.drawable.cuadrado);
                                                                                    }
                                                                                    if (value.equals("diamond")) {
                                                                                        txt9.setBackgroundResource(R.drawable.rombo);
                                                                                    }
                                                                                }

                                                                                companyRef.child(post_key).child("Flow Chart").child("item_10").addValueEventListener(new ValueEventListener() {
                                                                                    @Override
                                                                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                                                                        if (dataSnapshot.hasChild("name")) {
                                                                                            String value = dataSnapshot.child("name").getValue().toString();
                                                                                            txt10.setText(value);
                                                                                        }
                                                                                        if (dataSnapshot.hasChild("shape")) {
                                                                                            String value = dataSnapshot.child("shape").getValue().toString();
                                                                                            if (value.equals("circle")) {
                                                                                                txt10.setBackgroundResource(R.drawable.circulo);
                                                                                            }
                                                                                            if (value.equals("rectangle")) {
                                                                                                txt10.setBackgroundResource(R.drawable.cuadrado);
                                                                                            }
                                                                                            if (value.equals("diamond")) {
                                                                                                txt10.setBackgroundResource(R.drawable.rombo);
                                                                                            }
                                                                                        }

                                                                                        companyRef.child(post_key).child("Flow Chart").child("item_11").addValueEventListener(new ValueEventListener() {
                                                                                            @Override
                                                                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                                                                if (dataSnapshot.hasChild("name")) {
                                                                                                    String value = dataSnapshot.child("name").getValue().toString();
                                                                                                    txt11.setText(value);
                                                                                                }
                                                                                                if (dataSnapshot.hasChild("shape")) {
                                                                                                    String value = dataSnapshot.child("shape").getValue().toString();
                                                                                                    if (value.equals("circle")) {
                                                                                                        txt11.setBackgroundResource(R.drawable.circulo);
                                                                                                    }
                                                                                                    if (value.equals("rectangle")) {
                                                                                                        txt11.setBackgroundResource(R.drawable.cuadrado);
                                                                                                    }
                                                                                                    if (value.equals("diamond")) {
                                                                                                        txt11.setBackgroundResource(R.drawable.rombo);
                                                                                                    }
                                                                                                }

                                                                                                companyRef.child(post_key).child("Flow Chart").child("item_12").addValueEventListener(new ValueEventListener() {
                                                                                                    @Override
                                                                                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                                                                                        if (dataSnapshot.hasChild("name")) {
                                                                                                            String value = dataSnapshot.child("name").getValue().toString();
                                                                                                            txt12.setText(value);
                                                                                                        }
                                                                                                        if (dataSnapshot.hasChild("shape")) {
                                                                                                            String value = dataSnapshot.child("shape").getValue().toString();
                                                                                                            if (value.equals("circle")) {
                                                                                                                txt12.setBackgroundResource(R.drawable.circulo);
                                                                                                            }
                                                                                                            if (value.equals("rectangle")) {
                                                                                                                txt12.setBackgroundResource(R.drawable.cuadrado);
                                                                                                            }
                                                                                                            if (value.equals("diamond")) {
                                                                                                                txt12.setBackgroundResource(R.drawable.rombo);
                                                                                                            }
                                                                                                        }

                                                                                                        companyRef.child(post_key).child("Flow Chart").child("item_13").addValueEventListener(new ValueEventListener() {
                                                                                                            @Override
                                                                                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                                                                                if (dataSnapshot.hasChild("name")) {
                                                                                                                    String value = dataSnapshot.child("name").getValue().toString();
                                                                                                                    txt13.setText(value);
                                                                                                                }
                                                                                                                if (dataSnapshot.hasChild("shape")) {
                                                                                                                    String value = dataSnapshot.child("shape").getValue().toString();
                                                                                                                    if (value.equals("circle")) {
                                                                                                                        txt13.setBackgroundResource(R.drawable.circulo);
                                                                                                                    }
                                                                                                                    if (value.equals("rectangle")) {
                                                                                                                        txt13.setBackgroundResource(R.drawable.cuadrado);
                                                                                                                    }
                                                                                                                    if (value.equals("diamond")) {
                                                                                                                        txt13.setBackgroundResource(R.drawable.rombo);
                                                                                                                    }
                                                                                                                }

                                                                                                                companyRef.child(post_key).child("Flow Chart").child("item_14").addValueEventListener(new ValueEventListener() {
                                                                                                                    @Override
                                                                                                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                                                                                                        if (dataSnapshot.hasChild("name")) {
                                                                                                                            String value = dataSnapshot.child("name").getValue().toString();
                                                                                                                            txt14.setText(value);
                                                                                                                        }
                                                                                                                        if (dataSnapshot.hasChild("shape")) {
                                                                                                                            String value = dataSnapshot.child("shape").getValue().toString();
                                                                                                                            if (value.equals("circle")) {
                                                                                                                                txt14.setBackgroundResource(R.drawable.circulo);
                                                                                                                            }
                                                                                                                            if (value.equals("rectangle")) {
                                                                                                                                txt14.setBackgroundResource(R.drawable.cuadrado);
                                                                                                                            }
                                                                                                                            if (value.equals("diamond")) {
                                                                                                                                txt14.setBackgroundResource(R.drawable.rombo);
                                                                                                                            }
                                                                                                                        }

                                                                                                                        companyRef.child(post_key).child("Flow Chart").child("item_15").addValueEventListener(new ValueEventListener() {
                                                                                                                            @Override
                                                                                                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                                                                                                if (dataSnapshot.hasChild("name")) {
                                                                                                                                    String value = dataSnapshot.child("name").getValue().toString();
                                                                                                                                    txt15.setText(value);
                                                                                                                                }
                                                                                                                                if (dataSnapshot.hasChild("shape")) {
                                                                                                                                    String value = dataSnapshot.child("shape").getValue().toString();
                                                                                                                                    if (value.equals("circle")) {
                                                                                                                                        txt15.setBackgroundResource(R.drawable.circulo);
                                                                                                                                    }
                                                                                                                                    if (value.equals("rectangle")) {
                                                                                                                                        txt15.setBackgroundResource(R.drawable.cuadrado);
                                                                                                                                    }
                                                                                                                                    if (value.equals("diamond")) {
                                                                                                                                        txt15.setBackgroundResource(R.drawable.rombo);
                                                                                                                                    }
                                                                                                                                }

                                                                                                                                companyRef.child(post_key).child("Flow Chart").child("item_16").addValueEventListener(new ValueEventListener() {
                                                                                                                                    @Override
                                                                                                                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                                                                                                                        if (dataSnapshot.hasChild("name")) {
                                                                                                                                            String value = dataSnapshot.child("name").getValue().toString();
                                                                                                                                            txt16.setText(value);
                                                                                                                                        }
                                                                                                                                        if (dataSnapshot.hasChild("shape")) {
                                                                                                                                            String value = dataSnapshot.child("shape").getValue().toString();
                                                                                                                                            if (value.equals("circle")) {
                                                                                                                                                txt16.setBackgroundResource(R.drawable.circulo);
                                                                                                                                            }
                                                                                                                                            if (value.equals("rectangle")) {
                                                                                                                                                txt16.setBackgroundResource(R.drawable.cuadrado);
                                                                                                                                            }
                                                                                                                                            if (value.equals("diamond")) {
                                                                                                                                                txt16.setBackgroundResource(R.drawable.rombo);
                                                                                                                                            }
                                                                                                                                        }

                                                                                                                                        companyRef.child(post_key).child("Flow Chart").child("item_17").addValueEventListener(new ValueEventListener() {
                                                                                                                                            @Override
                                                                                                                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                                                                                                                if (dataSnapshot.hasChild("name")) {
                                                                                                                                                    String value = dataSnapshot.child("name").getValue().toString();
                                                                                                                                                    txt17.setText(value);
                                                                                                                                                }
                                                                                                                                                if (dataSnapshot.hasChild("shape")) {
                                                                                                                                                    String value = dataSnapshot.child("shape").getValue().toString();
                                                                                                                                                    if (value.equals("circle")) {
                                                                                                                                                        txt17.setBackgroundResource(R.drawable.circulo);
                                                                                                                                                    }
                                                                                                                                                    if (value.equals("rectangle")) {
                                                                                                                                                        txt17.setBackgroundResource(R.drawable.cuadrado);
                                                                                                                                                    }
                                                                                                                                                    if (value.equals("diamond")) {
                                                                                                                                                        txt17.setBackgroundResource(R.drawable.rombo);
                                                                                                                                                    }
                                                                                                                                                }

                                                                                                                                                companyRef.child(post_key).child("Flow Chart").child("item_18").addValueEventListener(new ValueEventListener() {
                                                                                                                                                    @Override
                                                                                                                                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                                                                                                                                        if (dataSnapshot.hasChild("name")) {
                                                                                                                                                            String value = dataSnapshot.child("name").getValue().toString();
                                                                                                                                                            txt18.setText(value);
                                                                                                                                                        }
                                                                                                                                                        if (dataSnapshot.hasChild("shape")) {
                                                                                                                                                            String value = dataSnapshot.child("shape").getValue().toString();
                                                                                                                                                            if (value.equals("circle")) {
                                                                                                                                                                txt18.setBackgroundResource(R.drawable.circulo);
                                                                                                                                                            }
                                                                                                                                                            if (value.equals("rectangle")) {
                                                                                                                                                                txt18.setBackgroundResource(R.drawable.cuadrado);
                                                                                                                                                            }
                                                                                                                                                            if (value.equals("diamond")) {
                                                                                                                                                                txt18.setBackgroundResource(R.drawable.rombo);
                                                                                                                                                            }
                                                                                                                                                        }

                                                                                                                                                        companyRef.child(post_key).child("Flow Chart").child("item_19").addValueEventListener(new ValueEventListener() {
                                                                                                                                                            @Override
                                                                                                                                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                                                                                                                                if (dataSnapshot.hasChild("name")) {
                                                                                                                                                                    String value = dataSnapshot.child("name").getValue().toString();
                                                                                                                                                                    txt19.setText(value);
                                                                                                                                                                }
                                                                                                                                                                if (dataSnapshot.hasChild("shape")) {
                                                                                                                                                                    String value = dataSnapshot.child("shape").getValue().toString();
                                                                                                                                                                    if (value.equals("circle")) {
                                                                                                                                                                        txt19.setBackgroundResource(R.drawable.circulo);
                                                                                                                                                                    }
                                                                                                                                                                    if (value.equals("rectangle")) {
                                                                                                                                                                        txt19.setBackgroundResource(R.drawable.cuadrado);
                                                                                                                                                                    }
                                                                                                                                                                    if (value.equals("diamond")) {
                                                                                                                                                                        txt19.setBackgroundResource(R.drawable.rombo);
                                                                                                                                                                    }
                                                                                                                                                                }

                                                                                                                                                                companyRef.child(post_key).child("Flow Chart").child("item_20").addValueEventListener(new ValueEventListener() {
                                                                                                                                                                    @Override
                                                                                                                                                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                                                                                                                                                        if (dataSnapshot.hasChild("name")) {
                                                                                                                                                                            String value = dataSnapshot.child("name").getValue().toString();
                                                                                                                                                                            txt20.setText(value);
                                                                                                                                                                        }
                                                                                                                                                                        if (dataSnapshot.hasChild("shape")) {
                                                                                                                                                                            String value = dataSnapshot.child("shape").getValue().toString();
                                                                                                                                                                            if (value.equals("circle")) {
                                                                                                                                                                                txt20.setBackgroundResource(R.drawable.circulo);
                                                                                                                                                                            }
                                                                                                                                                                            if (value.equals("rectangle")) {
                                                                                                                                                                                txt20.setBackgroundResource(R.drawable.cuadrado);
                                                                                                                                                                            }
                                                                                                                                                                            if (value.equals("diamond")) {
                                                                                                                                                                                txt20.setBackgroundResource(R.drawable.rombo);
                                                                                                                                                                            }
                                                                                                                                                                        }

                                                                                                                                                                        companyRef.child(post_key).child("Flow Chart").child("item_21").addValueEventListener(new ValueEventListener() {
                                                                                                                                                                            @Override
                                                                                                                                                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                                                                                                                                                if (dataSnapshot.hasChild("name")) {
                                                                                                                                                                                    String value = dataSnapshot.child("name").getValue().toString();
                                                                                                                                                                                    txt21.setText(value);
                                                                                                                                                                                }
                                                                                                                                                                                if (dataSnapshot.hasChild("shape")) {
                                                                                                                                                                                    String value = dataSnapshot.child("shape").getValue().toString();
                                                                                                                                                                                    if (value.equals("circle")) {
                                                                                                                                                                                        txt21.setBackgroundResource(R.drawable.circulo);
                                                                                                                                                                                    }
                                                                                                                                                                                    if (value.equals("rectangle")) {
                                                                                                                                                                                        txt21.setBackgroundResource(R.drawable.cuadrado);
                                                                                                                                                                                    }
                                                                                                                                                                                    if (value.equals("diamond")) {
                                                                                                                                                                                        txt21.setBackgroundResource(R.drawable.rombo);
                                                                                                                                                                                    }
                                                                                                                                                                                }

                                                                                                                                                                                companyRef.child(post_key).child("Flow Chart").child("item_22").addValueEventListener(new ValueEventListener() {
                                                                                                                                                                                    @Override
                                                                                                                                                                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                                                                                                                                                                        if (dataSnapshot.hasChild("name")) {
                                                                                                                                                                                            String value = dataSnapshot.child("name").getValue().toString();
                                                                                                                                                                                            txt22.setText(value);
                                                                                                                                                                                        }
                                                                                                                                                                                        if (dataSnapshot.hasChild("shape")) {
                                                                                                                                                                                            String value = dataSnapshot.child("shape").getValue().toString();
                                                                                                                                                                                            if (value.equals("circle")) {
                                                                                                                                                                                                txt22.setBackgroundResource(R.drawable.circulo);
                                                                                                                                                                                            }
                                                                                                                                                                                            if (value.equals("rectangle")) {
                                                                                                                                                                                                txt22.setBackgroundResource(R.drawable.cuadrado);
                                                                                                                                                                                            }
                                                                                                                                                                                            if (value.equals("diamond")) {
                                                                                                                                                                                                txt22.setBackgroundResource(R.drawable.rombo);
                                                                                                                                                                                            }
                                                                                                                                                                                        }

                                                                                                                                                                                        companyRef.child(post_key).child("Flow Chart").child("item_23").addValueEventListener(new ValueEventListener() {
                                                                                                                                                                                            @Override
                                                                                                                                                                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                                                                                                                                                                if (dataSnapshot.hasChild("name")) {
                                                                                                                                                                                                    String value = dataSnapshot.child("name").getValue().toString();
                                                                                                                                                                                                    txt23.setText(value);
                                                                                                                                                                                                }
                                                                                                                                                                                                if (dataSnapshot.hasChild("shape")) {
                                                                                                                                                                                                    String value = dataSnapshot.child("shape").getValue().toString();
                                                                                                                                                                                                    if (value.equals("circle")) {
                                                                                                                                                                                                        txt23.setBackgroundResource(R.drawable.circulo);
                                                                                                                                                                                                    }
                                                                                                                                                                                                    if (value.equals("rectangle")) {
                                                                                                                                                                                                        txt23.setBackgroundResource(R.drawable.cuadrado);
                                                                                                                                                                                                    }
                                                                                                                                                                                                    if (value.equals("diamond")) {
                                                                                                                                                                                                        txt23.setBackgroundResource(R.drawable.rombo);
                                                                                                                                                                                                    }
                                                                                                                                                                                                }

                                                                                                                                                                                                companyRef.child(post_key).child("Flow Chart").child("item_24").addValueEventListener(new ValueEventListener() {
                                                                                                                                                                                                    @Override
                                                                                                                                                                                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                                                                                                                                                                                        if (dataSnapshot.hasChild("name")) {
                                                                                                                                                                                                            String value = dataSnapshot.child("name").getValue().toString();
                                                                                                                                                                                                            txt24.setText(value);
                                                                                                                                                                                                        }
                                                                                                                                                                                                        if (dataSnapshot.hasChild("shape")) {
                                                                                                                                                                                                            String value = dataSnapshot.child("shape").getValue().toString();
                                                                                                                                                                                                            if (value.equals("circle")) {
                                                                                                                                                                                                                txt24.setBackgroundResource(R.drawable.circulo);
                                                                                                                                                                                                            }
                                                                                                                                                                                                            if (value.equals("rectangle")) {
                                                                                                                                                                                                                txt24.setBackgroundResource(R.drawable.cuadrado);
                                                                                                                                                                                                            }
                                                                                                                                                                                                            if (value.equals("diamond")) {
                                                                                                                                                                                                                txt24.setBackgroundResource(R.drawable.rombo);
                                                                                                                                                                                                            }
                                                                                                                                                                                                        }

                                                                                                                                                                                                        companyRef.child(post_key).child("Flow Chart").child("item_25").addValueEventListener(new ValueEventListener() {
                                                                                                                                                                                                            @Override
                                                                                                                                                                                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                                                                                                                                                                                if (dataSnapshot.hasChild("name")) {
                                                                                                                                                                                                                    String value = dataSnapshot.child("name").getValue().toString();
                                                                                                                                                                                                                    txt25.setText(value);
                                                                                                                                                                                                                }
                                                                                                                                                                                                                if (dataSnapshot.hasChild("shape")) {
                                                                                                                                                                                                                    String value = dataSnapshot.child("shape").getValue().toString();
                                                                                                                                                                                                                    if (value.equals("circle")) {
                                                                                                                                                                                                                        txt25.setBackgroundResource(R.drawable.circulo);
                                                                                                                                                                                                                    }
                                                                                                                                                                                                                    if (value.equals("rectangle")) {
                                                                                                                                                                                                                        txt25.setBackgroundResource(R.drawable.cuadrado);
                                                                                                                                                                                                                    }
                                                                                                                                                                                                                    if (value.equals("diamond")) {
                                                                                                                                                                                                                        txt25.setBackgroundResource(R.drawable.rombo);
                                                                                                                                                                                                                    }
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
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        companyRef.child(post_key).child("Flow Chart Arrows").child("item_1").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild("direction")) {
                    String value = dataSnapshot.child("direction").getValue().toString();
                    if (value.equals("left")) {
                        btnArrow1.setImageResource(R.drawable.flecha_izquierda);
                    }
                    if (value.equals("right")) {
                        btnArrow1.setImageResource(R.drawable.flecha_derecha);
                    }
                    if (value.equals("top")) {
                        btnArrow1.setImageResource(R.drawable.flecha_arriba);
                    }
                    if (value.equals("down")) {
                        btnArrow1.setImageResource(R.drawable.flecha_abajo);
                    }
                }

                companyRef.child(post_key).child("Flow Chart Arrows").child("item_2").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChild("direction")) {
                            String value = dataSnapshot.child("direction").getValue().toString();
                            if (value.equals("left")) {
                                btnArrow2.setImageResource(R.drawable.flecha_izquierda);
                            }
                            if (value.equals("right")) {
                                btnArrow2.setImageResource(R.drawable.flecha_derecha);
                            }
                            if (value.equals("top")) {
                                btnArrow2.setImageResource(R.drawable.flecha_arriba);
                            }
                            if (value.equals("down")) {
                                btnArrow2.setImageResource(R.drawable.flecha_abajo);
                            }
                        }

                        companyRef.child(post_key).child("Flow Chart Arrows").child("item_3").addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                if (dataSnapshot.hasChild("direction")) {
                                    String value = dataSnapshot.child("direction").getValue().toString();
                                    if (value.equals("left")) {
                                        btnArrow3.setImageResource(R.drawable.flecha_izquierda);
                                    }
                                    if (value.equals("right")) {
                                        btnArrow3.setImageResource(R.drawable.flecha_derecha);
                                    }
                                    if (value.equals("top")) {
                                        btnArrow3.setImageResource(R.drawable.flecha_arriba);
                                    }
                                    if (value.equals("down")) {
                                        btnArrow3.setImageResource(R.drawable.flecha_abajo);
                                    }
                                }

                                companyRef.child(post_key).child("Flow Chart Arrows").child("item_4").addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        if (dataSnapshot.hasChild("direction")) {
                                            String value = dataSnapshot.child("direction").getValue().toString();
                                            if (value.equals("left")) {
                                                btnArrow4.setImageResource(R.drawable.flecha_izquierda);
                                            }
                                            if (value.equals("right")) {
                                                btnArrow4.setImageResource(R.drawable.flecha_derecha);
                                            }
                                            if (value.equals("top")) {
                                                btnArrow4.setImageResource(R.drawable.flecha_arriba);
                                            }
                                            if (value.equals("down")) {
                                                btnArrow4.setImageResource(R.drawable.flecha_abajo);
                                            }
                                        }

                                        companyRef.child(post_key).child("Flow Chart Arrows").child("item_5").addValueEventListener(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                if (dataSnapshot.hasChild("direction")) {
                                                    String value = dataSnapshot.child("direction").getValue().toString();
                                                    if (value.equals("left")) {
                                                        btnArrow5.setImageResource(R.drawable.flecha_izquierda);
                                                    }
                                                    if (value.equals("right")) {
                                                        btnArrow5.setImageResource(R.drawable.flecha_derecha);
                                                    }
                                                    if (value.equals("top")) {
                                                        btnArrow5.setImageResource(R.drawable.flecha_arriba);
                                                    }
                                                    if (value.equals("down")) {
                                                        btnArrow5.setImageResource(R.drawable.flecha_abajo);
                                                    }
                                                }

                                                companyRef.child(post_key).child("Flow Chart Arrows").child("item_6").addValueEventListener(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                                        if (dataSnapshot.hasChild("direction")) {
                                                            String value = dataSnapshot.child("direction").getValue().toString();
                                                            if (value.equals("left")) {
                                                                btnArrow6.setImageResource(R.drawable.flecha_izquierda);
                                                            }
                                                            if (value.equals("right")) {
                                                                btnArrow6.setImageResource(R.drawable.flecha_derecha);
                                                            }
                                                            if (value.equals("top")) {
                                                                btnArrow6.setImageResource(R.drawable.flecha_arriba);
                                                            }
                                                            if (value.equals("down")) {
                                                                btnArrow6.setImageResource(R.drawable.flecha_abajo);
                                                            }
                                                        }

                                                        companyRef.child(post_key).child("Flow Chart Arrows").child("item_7").addValueEventListener(new ValueEventListener() {
                                                            @Override
                                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                                if (dataSnapshot.hasChild("direction")) {
                                                                    String value = dataSnapshot.child("direction").getValue().toString();
                                                                    if (value.equals("left")) {
                                                                        btnArrow7.setImageResource(R.drawable.flecha_izquierda);
                                                                    }
                                                                    if (value.equals("right")) {
                                                                        btnArrow7.setImageResource(R.drawable.flecha_derecha);
                                                                    }
                                                                    if (value.equals("top")) {
                                                                        btnArrow7.setImageResource(R.drawable.flecha_arriba);
                                                                    }
                                                                    if (value.equals("down")) {
                                                                        btnArrow7.setImageResource(R.drawable.flecha_abajo);
                                                                    }
                                                                }

                                                                companyRef.child(post_key).child("Flow Chart Arrows").child("item_8").addValueEventListener(new ValueEventListener() {
                                                                    @Override
                                                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                                                        if (dataSnapshot.hasChild("direction")) {
                                                                            String value = dataSnapshot.child("direction").getValue().toString();
                                                                            if (value.equals("left")) {
                                                                                btnArrow8.setImageResource(R.drawable.flecha_izquierda);
                                                                            }
                                                                            if (value.equals("right")) {
                                                                                btnArrow8.setImageResource(R.drawable.flecha_derecha);
                                                                            }
                                                                            if (value.equals("top")) {
                                                                                btnArrow8.setImageResource(R.drawable.flecha_arriba);
                                                                            }
                                                                            if (value.equals("down")) {
                                                                                btnArrow8.setImageResource(R.drawable.flecha_abajo);
                                                                            }
                                                                        }

                                                                        companyRef.child(post_key).child("Flow Chart Arrows").child("item_9").addValueEventListener(new ValueEventListener() {
                                                                            @Override
                                                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                                                if (dataSnapshot.hasChild("direction")) {
                                                                                    String value = dataSnapshot.child("direction").getValue().toString();
                                                                                    if (value.equals("left")) {
                                                                                        btnArrow9.setImageResource(R.drawable.flecha_izquierda);
                                                                                    }
                                                                                    if (value.equals("right")) {
                                                                                        btnArrow9.setImageResource(R.drawable.flecha_derecha);
                                                                                    }
                                                                                    if (value.equals("top")) {
                                                                                        btnArrow9.setImageResource(R.drawable.flecha_arriba);
                                                                                    }
                                                                                    if (value.equals("down")) {
                                                                                        btnArrow9.setImageResource(R.drawable.flecha_abajo);
                                                                                    }
                                                                                }

                                                                                companyRef.child(post_key).child("Flow Chart Arrows").child("item_10").addValueEventListener(new ValueEventListener() {
                                                                                    @Override
                                                                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                                                                        if (dataSnapshot.hasChild("direction")) {
                                                                                            String value = dataSnapshot.child("direction").getValue().toString();
                                                                                            if (value.equals("left")) {
                                                                                                btnArrow10.setImageResource(R.drawable.flecha_izquierda);
                                                                                            }
                                                                                            if (value.equals("right")) {
                                                                                                btnArrow10.setImageResource(R.drawable.flecha_derecha);
                                                                                            }
                                                                                            if (value.equals("top")) {
                                                                                                btnArrow10.setImageResource(R.drawable.flecha_arriba);
                                                                                            }
                                                                                            if (value.equals("down")) {
                                                                                                btnArrow10.setImageResource(R.drawable.flecha_abajo);
                                                                                            }
                                                                                        }

                                                                                        companyRef.child(post_key).child("Flow Chart Arrows").child("item_11").addValueEventListener(new ValueEventListener() {
                                                                                            @Override
                                                                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                                                                if (dataSnapshot.hasChild("direction")) {
                                                                                                    String value = dataSnapshot.child("direction").getValue().toString();
                                                                                                    if (value.equals("left")) {
                                                                                                        btnArrow11.setImageResource(R.drawable.flecha_izquierda);
                                                                                                    }
                                                                                                    if (value.equals("right")) {
                                                                                                        btnArrow11.setImageResource(R.drawable.flecha_derecha);
                                                                                                    }
                                                                                                    if (value.equals("top")) {
                                                                                                        btnArrow11.setImageResource(R.drawable.flecha_arriba);
                                                                                                    }
                                                                                                    if (value.equals("down")) {
                                                                                                        btnArrow11.setImageResource(R.drawable.flecha_abajo);
                                                                                                    }
                                                                                                }

                                                                                                companyRef.child(post_key).child("Flow Chart Arrows").child("item_12").addValueEventListener(new ValueEventListener() {
                                                                                                    @Override
                                                                                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                                                                                        if (dataSnapshot.hasChild("direction")) {
                                                                                                            String value = dataSnapshot.child("direction").getValue().toString();
                                                                                                            if (value.equals("left")) {
                                                                                                                btnArrow12.setImageResource(R.drawable.flecha_izquierda);
                                                                                                            }
                                                                                                            if (value.equals("right")) {
                                                                                                                btnArrow12.setImageResource(R.drawable.flecha_derecha);
                                                                                                            }
                                                                                                            if (value.equals("top")) {
                                                                                                                btnArrow12.setImageResource(R.drawable.flecha_arriba);
                                                                                                            }
                                                                                                            if (value.equals("down")) {
                                                                                                                btnArrow12.setImageResource(R.drawable.flecha_abajo);
                                                                                                            }
                                                                                                        }

                                                                                                        companyRef.child(post_key).child("Flow Chart Arrows").child("item_13").addValueEventListener(new ValueEventListener() {
                                                                                                            @Override
                                                                                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                                                                                if (dataSnapshot.hasChild("direction")) {
                                                                                                                    String value = dataSnapshot.child("direction").getValue().toString();
                                                                                                                    if (value.equals("left")) {
                                                                                                                        btnArrow13.setImageResource(R.drawable.flecha_izquierda);
                                                                                                                    }
                                                                                                                    if (value.equals("right")) {
                                                                                                                        btnArrow13.setImageResource(R.drawable.flecha_derecha);
                                                                                                                    }
                                                                                                                    if (value.equals("top")) {
                                                                                                                        btnArrow13.setImageResource(R.drawable.flecha_arriba);
                                                                                                                    }
                                                                                                                    if (value.equals("down")) {
                                                                                                                        btnArrow13.setImageResource(R.drawable.flecha_abajo);
                                                                                                                    }
                                                                                                                }

                                                                                                                companyRef.child(post_key).child("Flow Chart Arrows").child("item_14").addValueEventListener(new ValueEventListener() {
                                                                                                                    @Override
                                                                                                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                                                                                                        if (dataSnapshot.hasChild("direction")) {
                                                                                                                            String value = dataSnapshot.child("direction").getValue().toString();
                                                                                                                            if (value.equals("left")) {
                                                                                                                                btnArrow14.setImageResource(R.drawable.flecha_izquierda);
                                                                                                                            }
                                                                                                                            if (value.equals("right")) {
                                                                                                                                btnArrow14.setImageResource(R.drawable.flecha_derecha);
                                                                                                                            }
                                                                                                                            if (value.equals("top")) {
                                                                                                                                btnArrow14.setImageResource(R.drawable.flecha_arriba);
                                                                                                                            }
                                                                                                                            if (value.equals("down")) {
                                                                                                                                btnArrow14.setImageResource(R.drawable.flecha_abajo);
                                                                                                                            }
                                                                                                                        }

                                                                                                                        companyRef.child(post_key).child("Flow Chart Arrows").child("item_15").addValueEventListener(new ValueEventListener() {
                                                                                                                            @Override
                                                                                                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                                                                                                if (dataSnapshot.hasChild("direction")) {
                                                                                                                                    String value = dataSnapshot.child("direction").getValue().toString();
                                                                                                                                    if (value.equals("left")) {
                                                                                                                                        btnArrow15.setImageResource(R.drawable.flecha_izquierda);
                                                                                                                                    }
                                                                                                                                    if (value.equals("right")) {
                                                                                                                                        btnArrow15.setImageResource(R.drawable.flecha_derecha);
                                                                                                                                    }
                                                                                                                                    if (value.equals("top")) {
                                                                                                                                        btnArrow15.setImageResource(R.drawable.flecha_arriba);
                                                                                                                                    }
                                                                                                                                    if (value.equals("down")) {
                                                                                                                                        btnArrow15.setImageResource(R.drawable.flecha_abajo);
                                                                                                                                    }
                                                                                                                                }

                                                                                                                                companyRef.child(post_key).child("Flow Chart Arrows").child("item_16").addValueEventListener(new ValueEventListener() {
                                                                                                                                    @Override
                                                                                                                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                                                                                                                        if (dataSnapshot.hasChild("direction")) {
                                                                                                                                            String value = dataSnapshot.child("direction").getValue().toString();
                                                                                                                                            if (value.equals("left")) {
                                                                                                                                                btnArrow16.setImageResource(R.drawable.flecha_izquierda);
                                                                                                                                            }
                                                                                                                                            if (value.equals("right")) {
                                                                                                                                                btnArrow16.setImageResource(R.drawable.flecha_derecha);
                                                                                                                                            }
                                                                                                                                            if (value.equals("top")) {
                                                                                                                                                btnArrow16.setImageResource(R.drawable.flecha_arriba);
                                                                                                                                            }
                                                                                                                                            if (value.equals("down")) {
                                                                                                                                                btnArrow16.setImageResource(R.drawable.flecha_abajo);
                                                                                                                                            }
                                                                                                                                        }

                                                                                                                                        companyRef.child(post_key).child("Flow Chart Arrows").child("item_17").addValueEventListener(new ValueEventListener() {
                                                                                                                                            @Override
                                                                                                                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                                                                                                                if (dataSnapshot.hasChild("direction")) {
                                                                                                                                                    String value = dataSnapshot.child("direction").getValue().toString();
                                                                                                                                                    if (value.equals("left")) {
                                                                                                                                                        btnArrow17.setImageResource(R.drawable.flecha_izquierda);
                                                                                                                                                    }
                                                                                                                                                    if (value.equals("right")) {
                                                                                                                                                        btnArrow17.setImageResource(R.drawable.flecha_derecha);
                                                                                                                                                    }
                                                                                                                                                    if (value.equals("top")) {
                                                                                                                                                        btnArrow17.setImageResource(R.drawable.flecha_arriba);
                                                                                                                                                    }
                                                                                                                                                    if (value.equals("down")) {
                                                                                                                                                        btnArrow17.setImageResource(R.drawable.flecha_abajo);
                                                                                                                                                    }
                                                                                                                                                }

                                                                                                                                                companyRef.child(post_key).child("Flow Chart Arrows").child("item_18").addValueEventListener(new ValueEventListener() {
                                                                                                                                                    @Override
                                                                                                                                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                                                                                                                                        if (dataSnapshot.hasChild("direction")) {
                                                                                                                                                            String value = dataSnapshot.child("direction").getValue().toString();
                                                                                                                                                            if (value.equals("left")) {
                                                                                                                                                                btnArrow18.setImageResource(R.drawable.flecha_izquierda);
                                                                                                                                                            }
                                                                                                                                                            if (value.equals("right")) {
                                                                                                                                                                btnArrow18.setImageResource(R.drawable.flecha_derecha);
                                                                                                                                                            }
                                                                                                                                                            if (value.equals("top")) {
                                                                                                                                                                btnArrow18.setImageResource(R.drawable.flecha_arriba);
                                                                                                                                                            }
                                                                                                                                                            if (value.equals("down")) {
                                                                                                                                                                btnArrow18.setImageResource(R.drawable.flecha_abajo);
                                                                                                                                                            }
                                                                                                                                                        }

                                                                                                                                                        companyRef.child(post_key).child("Flow Chart Arrows").child("item_19").addValueEventListener(new ValueEventListener() {
                                                                                                                                                            @Override
                                                                                                                                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                                                                                                                                if (dataSnapshot.hasChild("direction")) {
                                                                                                                                                                    String value = dataSnapshot.child("direction").getValue().toString();
                                                                                                                                                                    if (value.equals("left")) {
                                                                                                                                                                        btnArrow19.setImageResource(R.drawable.flecha_izquierda);
                                                                                                                                                                    }
                                                                                                                                                                    if (value.equals("right")) {
                                                                                                                                                                        btnArrow19.setImageResource(R.drawable.flecha_derecha);
                                                                                                                                                                    }
                                                                                                                                                                    if (value.equals("top")) {
                                                                                                                                                                        btnArrow19.setImageResource(R.drawable.flecha_arriba);
                                                                                                                                                                    }
                                                                                                                                                                    if (value.equals("down")) {
                                                                                                                                                                        btnArrow19.setImageResource(R.drawable.flecha_abajo);
                                                                                                                                                                    }
                                                                                                                                                                }

                                                                                                                                                                companyRef.child(post_key).child("Flow Chart Arrows").child("item_20").addValueEventListener(new ValueEventListener() {
                                                                                                                                                                    @Override
                                                                                                                                                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                                                                                                                                                        if (dataSnapshot.hasChild("direction")) {
                                                                                                                                                                            String value = dataSnapshot.child("direction").getValue().toString();
                                                                                                                                                                            if (value.equals("left")) {
                                                                                                                                                                                btnArrow20.setImageResource(R.drawable.flecha_izquierda);
                                                                                                                                                                            }
                                                                                                                                                                            if (value.equals("right")) {
                                                                                                                                                                                btnArrow20.setImageResource(R.drawable.flecha_derecha);
                                                                                                                                                                            }
                                                                                                                                                                            if (value.equals("top")) {
                                                                                                                                                                                btnArrow20.setImageResource(R.drawable.flecha_arriba);
                                                                                                                                                                            }
                                                                                                                                                                            if (value.equals("down")) {
                                                                                                                                                                                btnArrow20.setImageResource(R.drawable.flecha_abajo);
                                                                                                                                                                            }
                                                                                                                                                                        }

                                                                                                                                                                        companyRef.child(post_key).child("Flow Chart Arrows").child("item_21").addValueEventListener(new ValueEventListener() {
                                                                                                                                                                            @Override
                                                                                                                                                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                                                                                                                                                if (dataSnapshot.hasChild("direction")) {
                                                                                                                                                                                    String value = dataSnapshot.child("direction").getValue().toString();
                                                                                                                                                                                    if (value.equals("left")) {
                                                                                                                                                                                        btnArrow21.setImageResource(R.drawable.flecha_izquierda);
                                                                                                                                                                                    }
                                                                                                                                                                                    if (value.equals("right")) {
                                                                                                                                                                                        btnArrow21.setImageResource(R.drawable.flecha_derecha);
                                                                                                                                                                                    }
                                                                                                                                                                                    if (value.equals("top")) {
                                                                                                                                                                                        btnArrow21.setImageResource(R.drawable.flecha_arriba);
                                                                                                                                                                                    }
                                                                                                                                                                                    if (value.equals("down")) {
                                                                                                                                                                                        btnArrow21.setImageResource(R.drawable.flecha_abajo);
                                                                                                                                                                                    }
                                                                                                                                                                                }

                                                                                                                                                                                companyRef.child(post_key).child("Flow Chart Arrows").child("item_22").addValueEventListener(new ValueEventListener() {
                                                                                                                                                                                    @Override
                                                                                                                                                                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                                                                                                                                                                        if (dataSnapshot.hasChild("direction")) {
                                                                                                                                                                                            String value = dataSnapshot.child("direction").getValue().toString();
                                                                                                                                                                                            if (value.equals("left")) {
                                                                                                                                                                                                btnArrow22.setImageResource(R.drawable.flecha_izquierda);
                                                                                                                                                                                            }
                                                                                                                                                                                            if (value.equals("right")) {
                                                                                                                                                                                                btnArrow22.setImageResource(R.drawable.flecha_derecha);
                                                                                                                                                                                            }
                                                                                                                                                                                            if (value.equals("top")) {
                                                                                                                                                                                                btnArrow22.setImageResource(R.drawable.flecha_arriba);
                                                                                                                                                                                            }
                                                                                                                                                                                            if (value.equals("down")) {
                                                                                                                                                                                                btnArrow22.setImageResource(R.drawable.flecha_abajo);
                                                                                                                                                                                            }
                                                                                                                                                                                        }

                                                                                                                                                                                        companyRef.child(post_key).child("Flow Chart Arrows").child("item_23").addValueEventListener(new ValueEventListener() {
                                                                                                                                                                                            @Override
                                                                                                                                                                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                                                                                                                                                                if (dataSnapshot.hasChild("direction")) {
                                                                                                                                                                                                    String value = dataSnapshot.child("direction").getValue().toString();
                                                                                                                                                                                                    if (value.equals("left")) {
                                                                                                                                                                                                        btnArrow23.setImageResource(R.drawable.flecha_izquierda);
                                                                                                                                                                                                    }
                                                                                                                                                                                                    if (value.equals("right")) {
                                                                                                                                                                                                        btnArrow23.setImageResource(R.drawable.flecha_derecha);
                                                                                                                                                                                                    }
                                                                                                                                                                                                    if (value.equals("top")) {
                                                                                                                                                                                                        btnArrow23.setImageResource(R.drawable.flecha_arriba);
                                                                                                                                                                                                    }
                                                                                                                                                                                                    if (value.equals("down")) {
                                                                                                                                                                                                        btnArrow23.setImageResource(R.drawable.flecha_abajo);
                                                                                                                                                                                                    }
                                                                                                                                                                                                }

                                                                                                                                                                                                companyRef.child(post_key).child("Flow Chart Arrows").child("item_24").addValueEventListener(new ValueEventListener() {
                                                                                                                                                                                                    @Override
                                                                                                                                                                                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                                                                                                                                                                                        if (dataSnapshot.hasChild("direction")) {
                                                                                                                                                                                                            String value = dataSnapshot.child("direction").getValue().toString();
                                                                                                                                                                                                            if (value.equals("left")) {
                                                                                                                                                                                                                btnArrow24.setImageResource(R.drawable.flecha_izquierda);
                                                                                                                                                                                                            }
                                                                                                                                                                                                            if (value.equals("right")) {
                                                                                                                                                                                                                btnArrow24.setImageResource(R.drawable.flecha_derecha);
                                                                                                                                                                                                            }
                                                                                                                                                                                                            if (value.equals("top")) {
                                                                                                                                                                                                                btnArrow24.setImageResource(R.drawable.flecha_arriba);
                                                                                                                                                                                                            }
                                                                                                                                                                                                            if (value.equals("down")) {
                                                                                                                                                                                                                btnArrow24.setImageResource(R.drawable.flecha_abajo);
                                                                                                                                                                                                            }
                                                                                                                                                                                                        }

                                                                                                                                                                                                        companyRef.child(post_key).child("Flow Chart Arrows").child("item_25").addValueEventListener(new ValueEventListener() {
                                                                                                                                                                                                            @Override
                                                                                                                                                                                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                                                                                                                                                                                if (dataSnapshot.hasChild("direction")) {
                                                                                                                                                                                                                    String value = dataSnapshot.child("direction").getValue().toString();
                                                                                                                                                                                                                    if (value.equals("left")) {
                                                                                                                                                                                                                        btnArrow25.setImageResource(R.drawable.flecha_izquierda);
                                                                                                                                                                                                                    }
                                                                                                                                                                                                                    if (value.equals("right")) {
                                                                                                                                                                                                                        btnArrow25.setImageResource(R.drawable.flecha_derecha);
                                                                                                                                                                                                                    }
                                                                                                                                                                                                                    if (value.equals("top")) {
                                                                                                                                                                                                                        btnArrow25.setImageResource(R.drawable.flecha_arriba);
                                                                                                                                                                                                                    }
                                                                                                                                                                                                                    if (value.equals("down")) {
                                                                                                                                                                                                                        btnArrow25.setImageResource(R.drawable.flecha_abajo);
                                                                                                                                                                                                                    }
                                                                                                                                                                                                                }

                                                                                                                                                                                                                companyRef.child(post_key).child("Flow Chart Arrows").child("item_26").addValueEventListener(new ValueEventListener() {
                                                                                                                                                                                                                    @Override
                                                                                                                                                                                                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                                                                                                                                                                                                        if (dataSnapshot.hasChild("direction")) {
                                                                                                                                                                                                                            String value = dataSnapshot.child("direction").getValue().toString();
                                                                                                                                                                                                                            if (value.equals("left")) {
                                                                                                                                                                                                                                btnArrow26.setImageResource(R.drawable.flecha_izquierda);
                                                                                                                                                                                                                            }
                                                                                                                                                                                                                            if (value.equals("right")) {
                                                                                                                                                                                                                                btnArrow26.setImageResource(R.drawable.flecha_derecha);
                                                                                                                                                                                                                            }
                                                                                                                                                                                                                            if (value.equals("top")) {
                                                                                                                                                                                                                                btnArrow26.setImageResource(R.drawable.flecha_arriba);
                                                                                                                                                                                                                            }
                                                                                                                                                                                                                            if (value.equals("down")) {
                                                                                                                                                                                                                                btnArrow26.setImageResource(R.drawable.flecha_abajo);
                                                                                                                                                                                                                            }
                                                                                                                                                                                                                        }

                                                                                                                                                                                                                        companyRef.child(post_key).child("Flow Chart Arrows").child("item_27").addValueEventListener(new ValueEventListener() {
                                                                                                                                                                                                                            @Override
                                                                                                                                                                                                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                                                                                                                                                                                                if (dataSnapshot.hasChild("direction")) {
                                                                                                                                                                                                                                    String value = dataSnapshot.child("direction").getValue().toString();
                                                                                                                                                                                                                                    if (value.equals("left")) {
                                                                                                                                                                                                                                        btnArrow27.setImageResource(R.drawable.flecha_izquierda);
                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                    if (value.equals("right")) {
                                                                                                                                                                                                                                        btnArrow27.setImageResource(R.drawable.flecha_derecha);
                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                    if (value.equals("top")) {
                                                                                                                                                                                                                                        btnArrow27.setImageResource(R.drawable.flecha_arriba);
                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                    if (value.equals("down")) {
                                                                                                                                                                                                                                        btnArrow27.setImageResource(R.drawable.flecha_abajo);
                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                }

                                                                                                                                                                                                                                companyRef.child(post_key).child("Flow Chart Arrows").child("item_28").addValueEventListener(new ValueEventListener() {
                                                                                                                                                                                                                                    @Override
                                                                                                                                                                                                                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                                                                                                                                                                                                                        if (dataSnapshot.hasChild("direction")) {
                                                                                                                                                                                                                                            String value = dataSnapshot.child("direction").getValue().toString();
                                                                                                                                                                                                                                            if (value.equals("left")) {
                                                                                                                                                                                                                                                btnArrow28.setImageResource(R.drawable.flecha_izquierda);
                                                                                                                                                                                                                                            }
                                                                                                                                                                                                                                            if (value.equals("right")) {
                                                                                                                                                                                                                                                btnArrow28.setImageResource(R.drawable.flecha_derecha);
                                                                                                                                                                                                                                            }
                                                                                                                                                                                                                                            if (value.equals("top")) {
                                                                                                                                                                                                                                                btnArrow28.setImageResource(R.drawable.flecha_arriba);
                                                                                                                                                                                                                                            }
                                                                                                                                                                                                                                            if (value.equals("down")) {
                                                                                                                                                                                                                                                btnArrow28.setImageResource(R.drawable.flecha_abajo);
                                                                                                                                                                                                                                            }
                                                                                                                                                                                                                                        }

                                                                                                                                                                                                                                        companyRef.child(post_key).child("Flow Chart Arrows").child("item_29").addValueEventListener(new ValueEventListener() {
                                                                                                                                                                                                                                            @Override
                                                                                                                                                                                                                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                                                                                                                                                                                                                if (dataSnapshot.hasChild("direction")) {
                                                                                                                                                                                                                                                    String value = dataSnapshot.child("direction").getValue().toString();
                                                                                                                                                                                                                                                    if (value.equals("left")) {
                                                                                                                                                                                                                                                        btnArrow29.setImageResource(R.drawable.flecha_izquierda);
                                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                                    if (value.equals("right")) {
                                                                                                                                                                                                                                                        btnArrow29.setImageResource(R.drawable.flecha_derecha);
                                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                                    if (value.equals("top")) {
                                                                                                                                                                                                                                                        btnArrow29.setImageResource(R.drawable.flecha_arriba);
                                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                                    if (value.equals("down")) {
                                                                                                                                                                                                                                                        btnArrow29.setImageResource(R.drawable.flecha_abajo);
                                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                                }

                                                                                                                                                                                                                                                companyRef.child(post_key).child("Flow Chart Arrows").child("item_30").addValueEventListener(new ValueEventListener() {
                                                                                                                                                                                                                                                    @Override
                                                                                                                                                                                                                                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                                                                                                                                                                                                                                        if (dataSnapshot.hasChild("direction")) {
                                                                                                                                                                                                                                                            String value = dataSnapshot.child("direction").getValue().toString();
                                                                                                                                                                                                                                                            if (value.equals("left")) {
                                                                                                                                                                                                                                                                btnArrow30.setImageResource(R.drawable.flecha_izquierda);
                                                                                                                                                                                                                                                            }
                                                                                                                                                                                                                                                            if (value.equals("right")) {
                                                                                                                                                                                                                                                                btnArrow30.setImageResource(R.drawable.flecha_derecha);
                                                                                                                                                                                                                                                            }
                                                                                                                                                                                                                                                            if (value.equals("top")) {
                                                                                                                                                                                                                                                                btnArrow30.setImageResource(R.drawable.flecha_arriba);
                                                                                                                                                                                                                                                            }
                                                                                                                                                                                                                                                            if (value.equals("down")) {
                                                                                                                                                                                                                                                                btnArrow30.setImageResource(R.drawable.flecha_abajo);
                                                                                                                                                                                                                                                            }
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

        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path ="item_1";
                showCreateChartFlowDialog(path);
            }
        });
        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path ="item_2";
                showCreateChartFlowDialog(path);
            }
        });
        card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path ="item_3";
                showCreateChartFlowDialog(path);
            }
        });
        card4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path ="item_4";
                showCreateChartFlowDialog(path);
            }
        });
        card5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path ="item_5";
                showCreateChartFlowDialog(path);
            }
        });
        card6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path ="item_6";
                showCreateChartFlowDialog(path);
            }
        });
        card7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path ="item_7";
                showCreateChartFlowDialog(path);
            }
        });
        card8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path ="item_8";
                showCreateChartFlowDialog(path);
            }
        });
        card9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path ="item_9";
                showCreateChartFlowDialog(path);
            }
        });
        card10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path ="item_10";
                showCreateChartFlowDialog(path);
            }
        });
        card11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path ="item_11";
                showCreateChartFlowDialog(path);
            }
        });
        card12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path ="item_12";
                showCreateChartFlowDialog(path);
            }
        });
        card13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path ="item_13";
                showCreateChartFlowDialog(path);
            }
        });
        card14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path ="item_14";
                showCreateChartFlowDialog(path);
            }
        });
        card15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path ="item_15";
                showCreateChartFlowDialog(path);
            }
        });
        card16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path ="item_16";
                showCreateChartFlowDialog(path);
            }
        });
        card17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path ="item_17";
                showCreateChartFlowDialog(path);
            }
        });
        card18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path ="item_18";
                showCreateChartFlowDialog(path);
            }
        });
        card19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path ="item_19";
                showCreateChartFlowDialog(path);
            }
        });
        card20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path ="item_20";
                showCreateChartFlowDialog(path);
            }
        });
        card21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path ="item_21";
                showCreateChartFlowDialog(path);
            }
        });
        card22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path ="item_22";
                showCreateChartFlowDialog(path);
            }
        });
        card23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path ="item_23";
                showCreateChartFlowDialog(path);
            }
        });
        card24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path ="item_24";
                showCreateChartFlowDialog(path);
            }
        });
        card25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path ="item_25";
                showCreateChartFlowDialog(path);
            }
        });



        btnArrow1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path ="item_1";
                showCreateArrowDialog(path);
            }
        });
        btnArrow2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path ="item_2";
                showCreateArrowDialog(path);
            }
        });
        btnArrow3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path ="item_3";
                showCreateArrowDialog(path);
            }
        });
        btnArrow4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path ="item_4";
                showCreateArrowDialog(path);
            }
        });
        btnArrow5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path ="item_5";
                showCreateArrowDialog(path);
            }
        });
        btnArrow6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path ="item_6";
                showCreateArrowDialog(path);
            }
        });
        btnArrow7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path ="item_7";
                showCreateArrowDialog(path);
            }
        });
        btnArrow8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path ="item_8";
                showCreateArrowDialog(path);
            }
        });
        btnArrow9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path ="item_9";
                showCreateArrowDialog(path);
            }
        });
        btnArrow10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path ="item_10";
                showCreateArrowDialog(path);
            }
        });
        btnArrow11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path ="item_11";
                showCreateArrowDialog(path);
            }
        });
        btnArrow12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path ="item_12";
                showCreateArrowDialog(path);
            }
        });
        btnArrow13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path ="item_13";
                showCreateArrowDialog(path);
            }
        });
        btnArrow14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path ="item_14";
                showCreateArrowDialog(path);
            }
        });
        btnArrow15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path ="item_15";
                showCreateArrowDialog(path);
            }
        });
        btnArrow16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path ="item_16";
                showCreateArrowDialog(path);
            }
        });
        btnArrow17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path ="item_17";
                showCreateArrowDialog(path);
            }
        });
        btnArrow18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path ="item_18";
                showCreateArrowDialog(path);
            }
        });
        btnArrow19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path ="item_19";
                showCreateArrowDialog(path);
            }
        });
        btnArrow20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path ="item_20";
                showCreateArrowDialog(path);
            }
        });

        btnArrow21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path ="item_21";
                showCreateArrowDialog(path);
            }
        });
        btnArrow22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path ="item_22";
                showCreateArrowDialog(path);
            }
        });
        btnArrow23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path ="item_23";
                showCreateArrowDialog(path);
            }
        });
        btnArrow24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path ="item_24";
                showCreateArrowDialog(path);
            }
        });
        btnArrow25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path ="item_25";
                showCreateArrowDialog(path);
            }
        });
        btnArrow26.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path ="item_26";
                showCreateArrowDialog(path);
            }
        });
        btnArrow27.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path ="item_27";
                showCreateArrowDialog(path);
            }
        });
        btnArrow28.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path ="item_28";
                showCreateArrowDialog(path);
            }
        });
        btnArrow29.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path ="item_29";
                showCreateArrowDialog(path);
            }
        });
        btnArrow30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path ="item_30";
                showCreateArrowDialog(path);
            }
        });


    }

    private void showCreateArrowDialog(final String path) {
        final AlertDialog dialog = new AlertDialog.Builder(this).create();

        LayoutInflater inflater = LayoutInflater.from(this);
        View finance_method = inflater.inflate(R.layout.change_arrow_dialog,null);

        final RadioButton rd1,rd2,rd3,rd4;
        Button btnFinish;
        final LinearLayout rootLayout;

        rd1 = finance_method.findViewById(R.id.rd1);
        rd2 = finance_method.findViewById(R.id.rd2);
        rd3 = finance_method.findViewById(R.id.rd3);
        rd4 = finance_method.findViewById(R.id.rd4);
        btnFinish = finance_method.findViewById(R.id.btnFinish);
        rootLayout = finance_method.findViewById(R.id.rootLayout);

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!rd1.isChecked() && !rd2.isChecked() && !rd3.isChecked() && !rd4.isChecked()) {
                    Snackbar.make(rootLayout, "Debes selccionar una dirección", Snackbar.LENGTH_SHORT).show();
                } else {

                    if (rd1.isChecked()) {
                        companyRef.child(post_key).child("Flow Chart Arrows").child(path).child("direction").setValue("left");
                    }
                    if (rd2.isChecked()) {
                        companyRef.child(post_key).child("Flow Chart Arrows").child(path).child("direction").setValue("right");
                    }
                    if (rd3.isChecked()) {
                        companyRef.child(post_key).child("Flow Chart Arrows").child(path).child("direction").setValue("top");
                    }
                    if (rd4.isChecked()) {
                        companyRef.child(post_key).child("Flow Chart Arrows").child(path).child("direction").setValue("down");
                    }
                    Toasty.success(FlowChartActivity.this, "Actualizado", Toast.LENGTH_LONG).show();
                    dialog.dismiss();

                }
            }
        });


        dialog.setView(finance_method);
        dialog.show();
    }

    private void showCreateChartFlowDialog(final String path) {
        final AlertDialog dialog = new AlertDialog.Builder(this).create();

        LayoutInflater inflater = LayoutInflater.from(this);
        View finance_method = inflater.inflate(R.layout.create_chart_flow_dialog,null);

        final RadioButton rd1,rd2,rd3;
        final EditText edtName;
        Button btnFinish;
        final LinearLayout rootLayout;

        rd1 = finance_method.findViewById(R.id.rd1);
        rd2 = finance_method.findViewById(R.id.rd2);
        rd3 = finance_method.findViewById(R.id.rd3);
        edtName = finance_method.findViewById(R.id.edtName);
        btnFinish = finance_method.findViewById(R.id.btnFinish);
        rootLayout = finance_method.findViewById(R.id.rootLayout);

        rd1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rd2.setChecked(false);
                rd3.setChecked(false);
            }
        });

        rd2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rd1.setChecked(false);
                rd3.setChecked(false);
            }
        });

        rd3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rd2.setChecked(false);
                rd1.setChecked(false);
            }
        });

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!rd1.isChecked() && !rd2.isChecked() && !rd3.isChecked()) {
                    Snackbar.make(rootLayout,"Debes seleccionar un tipo de flujo",Snackbar.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(edtName.getText().toString())) {
                    Snackbar.make(rootLayout, "Debes ingresar el nombre del flujo", Snackbar.LENGTH_SHORT).show();
                } else {
                    companyRef.child(post_key).child("Flow Chart").child(path).child("name").setValue(edtName.getText().toString());
                    if (rd1.isChecked()) {
                        companyRef.child(post_key).child("Flow Chart").child(path).child("shape").setValue("circle");
                    }
                    if (rd2.isChecked()) {
                        companyRef.child(post_key).child("Flow Chart").child(path).child("shape").setValue("rectangle");
                    }
                    if (rd3.isChecked()) {
                        companyRef.child(post_key).child("Flow Chart").child(path).child("shape").setValue("diamond");
                    }

                    Toasty.success(FlowChartActivity.this, "Actualizado", Toast.LENGTH_LONG).show();
                    dialog.dismiss();
                }
            }
        });

        dialog.setView(finance_method);
        dialog.show();
    }
}
