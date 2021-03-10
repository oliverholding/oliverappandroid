package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.BlancedScoreCard.KeyIndex;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;

public class KeyIndexActivity extends AppCompatActivity {

    TextView txtIndexName1,txtIndexName2,txtIndexName3,txtIndexName4,txtIndexName5,txtIndexName6,txtIndexName7,txtIndexName8,txtIndexName9,txtIndexName10,txtIndexName11,txtIndexName12,txtIndexName13,txtIndexName14,txtIndexName15,txtIndexName16,txtIndexName17,txtIndexName18,txtIndexName19,txtIndexName20,txtIndexName21,txtIndexName22,txtIndexName23,txtIndexName24,
            txtIndexFormula1,txtIndexFormula2,txtIndexFormula3,txtIndexFormula4,txtIndexFormula5,txtIndexFormula6,txtIndexFormula7,txtIndexFormula8,txtIndexFormula9,txtIndexFormula10,txtIndexFormula11,txtIndexFormula12,txtIndexFormula13,txtIndexFormula14,txtIndexFormula15,txtIndexFormula16,txtIndexFormula17,txtIndexFormula18,txtIndexFormula19,txtIndexFormula20,txtIndexFormula21,txtIndexFormula22,txtIndexFormula23,txtIndexFormula24;
    DatabaseReference companyRef;
    String post_key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_key_index);

        post_key = getIntent().getExtras().getString("post_key");
        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");

        txtIndexName1 = findViewById(R.id.txtIndexName1);
        txtIndexName2 = findViewById(R.id.txtIndexName2);
        txtIndexName3 = findViewById(R.id.txtIndexName3);
        txtIndexName4 = findViewById(R.id.txtIndexName4);
        txtIndexName5 = findViewById(R.id.txtIndexName5);
        txtIndexName6 = findViewById(R.id.txtIndexName6);
        txtIndexName7 = findViewById(R.id.txtIndexName7);
        txtIndexName8 = findViewById(R.id.txtIndexName8);
        txtIndexName9 = findViewById(R.id.txtIndexName9);
        txtIndexName10 = findViewById(R.id.txtIndexName10);
        txtIndexName11 = findViewById(R.id.txtIndexName11);
        txtIndexName12 = findViewById(R.id.txtIndexName12);
        txtIndexName13 = findViewById(R.id.txtIndexName13);
        txtIndexName14 = findViewById(R.id.txtIndexName14);
        txtIndexName15 = findViewById(R.id.txtIndexName15);
        txtIndexName16 = findViewById(R.id.txtIndexName16);
        txtIndexName17 = findViewById(R.id.txtIndexName17);
        txtIndexName18 = findViewById(R.id.txtIndexName18);
        txtIndexName19 = findViewById(R.id.txtIndexName19);
        txtIndexName20 = findViewById(R.id.txtIndexName20);
        txtIndexName21 = findViewById(R.id.txtIndexName21);
        txtIndexName22 = findViewById(R.id.txtIndexName22);
        txtIndexName23 = findViewById(R.id.txtIndexName23);
        txtIndexName24 = findViewById(R.id.txtIndexName24);

        txtIndexFormula1 = findViewById(R.id.txtIndexFormula1);
        txtIndexFormula2 = findViewById(R.id.txtIndexFormula2);
        txtIndexFormula3 = findViewById(R.id.txtIndexFormula3);
        txtIndexFormula4 = findViewById(R.id.txtIndexFormula4);
        txtIndexFormula5 = findViewById(R.id.txtIndexFormula5);
        txtIndexFormula6 = findViewById(R.id.txtIndexFormula6);
        txtIndexFormula7 = findViewById(R.id.txtIndexFormula7);
        txtIndexFormula8 = findViewById(R.id.txtIndexFormula8);
        txtIndexFormula9 = findViewById(R.id.txtIndexFormula9);
        txtIndexFormula10 = findViewById(R.id.txtIndexFormula10);
        txtIndexFormula11 = findViewById(R.id.txtIndexFormula11);
        txtIndexFormula12 = findViewById(R.id.txtIndexFormula12);
        txtIndexFormula13 = findViewById(R.id.txtIndexFormula13);
        txtIndexFormula14 = findViewById(R.id.txtIndexFormula14);
        txtIndexFormula15 = findViewById(R.id.txtIndexFormula15);
        txtIndexFormula16 = findViewById(R.id.txtIndexFormula16);
        txtIndexFormula17 = findViewById(R.id.txtIndexFormula17);
        txtIndexFormula18 = findViewById(R.id.txtIndexFormula18);
        txtIndexFormula19 = findViewById(R.id.txtIndexFormula19);
        txtIndexFormula20 = findViewById(R.id.txtIndexFormula20);
        txtIndexFormula21 = findViewById(R.id.txtIndexFormula21);
        txtIndexFormula22 = findViewById(R.id.txtIndexFormula22);
        txtIndexFormula23 = findViewById(R.id.txtIndexFormula23);
        txtIndexFormula24 = findViewById(R.id.txtIndexFormula24);

        companyRef.child(post_key).child("Balanced Scorecard").child("item1_1").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String kpi1 = dataSnapshot.child("kpi1").getValue().toString();
                    String kpi2 = dataSnapshot.child("kpi2").getValue().toString();
                    String kpi1_formula = dataSnapshot.child("kpi1_formula").getValue().toString();
                    String kpi2_formula = dataSnapshot.child("kpi2_formula").getValue().toString();

                    txtIndexName1.setText(kpi1);
                    txtIndexName2.setText(kpi2);
                    txtIndexFormula1.setText(kpi1_formula);
                    txtIndexFormula2.setText(kpi2_formula);
                }


                companyRef.child(post_key).child("Balanced Scorecard").child("item1_2").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            String kpi1 = dataSnapshot.child("kpi1").getValue().toString();
                            String kpi2 = dataSnapshot.child("kpi2").getValue().toString();
                            String kpi1_formula = dataSnapshot.child("kpi1_formula").getValue().toString();
                            String kpi2_formula = dataSnapshot.child("kpi2_formula").getValue().toString();

                            txtIndexName3.setText(kpi1);
                            txtIndexName4.setText(kpi2);
                            txtIndexFormula3.setText(kpi1_formula);
                            txtIndexFormula4.setText(kpi2_formula);
                        }


                        companyRef.child(post_key).child("Balanced Scorecard").child("item1_3").addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                if (dataSnapshot.exists()) {
                                    String kpi1 = dataSnapshot.child("kpi1").getValue().toString();
                                    String kpi2 = dataSnapshot.child("kpi2").getValue().toString();
                                    String kpi1_formula = dataSnapshot.child("kpi1_formula").getValue().toString();
                                    String kpi2_formula = dataSnapshot.child("kpi2_formula").getValue().toString();

                                    txtIndexName5.setText(kpi1);
                                    txtIndexName6.setText(kpi2);
                                    txtIndexFormula5.setText(kpi1_formula);
                                    txtIndexFormula6.setText(kpi2_formula);
                                }


                                companyRef.child(post_key).child("Balanced Scorecard").child("item2_1").addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        if (dataSnapshot.exists()) {
                                            String kpi1 = dataSnapshot.child("kpi1").getValue().toString();
                                            String kpi2 = dataSnapshot.child("kpi2").getValue().toString();
                                            String kpi1_formula = dataSnapshot.child("kpi1_formula").getValue().toString();
                                            String kpi2_formula = dataSnapshot.child("kpi2_formula").getValue().toString();

                                            txtIndexName7.setText(kpi1);
                                            txtIndexName8.setText(kpi2);
                                            txtIndexFormula7.setText(kpi1_formula);
                                            txtIndexFormula8.setText(kpi2_formula);
                                        }


                                        companyRef.child(post_key).child("Balanced Scorecard").child("item2_2").addValueEventListener(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                if (dataSnapshot.exists()) {
                                                    String kpi1 = dataSnapshot.child("kpi1").getValue().toString();
                                                    String kpi2 = dataSnapshot.child("kpi2").getValue().toString();
                                                    String kpi1_formula = dataSnapshot.child("kpi1_formula").getValue().toString();
                                                    String kpi2_formula = dataSnapshot.child("kpi2_formula").getValue().toString();

                                                    txtIndexName9.setText(kpi1);
                                                    txtIndexName10.setText(kpi2);
                                                    txtIndexFormula9.setText(kpi1_formula);
                                                    txtIndexFormula10.setText(kpi2_formula);
                                                }


                                                companyRef.child(post_key).child("Balanced Scorecard").child("item2_3").addValueEventListener(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                                        if (dataSnapshot.exists()) {
                                                            String kpi1 = dataSnapshot.child("kpi1").getValue().toString();
                                                            String kpi2 = dataSnapshot.child("kpi2").getValue().toString();
                                                            String kpi1_formula = dataSnapshot.child("kpi1_formula").getValue().toString();
                                                            String kpi2_formula = dataSnapshot.child("kpi2_formula").getValue().toString();

                                                            txtIndexName11.setText(kpi1);
                                                            txtIndexName12.setText(kpi2);
                                                            txtIndexFormula11.setText(kpi1_formula);
                                                            txtIndexFormula12.setText(kpi2_formula);
                                                        }


                                                        companyRef.child(post_key).child("Balanced Scorecard").child("item3_1").addValueEventListener(new ValueEventListener() {
                                                            @Override
                                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                                if (dataSnapshot.exists()) {
                                                                    String kpi1 = dataSnapshot.child("kpi1").getValue().toString();
                                                                    String kpi2 = dataSnapshot.child("kpi2").getValue().toString();
                                                                    String kpi1_formula = dataSnapshot.child("kpi1_formula").getValue().toString();
                                                                    String kpi2_formula = dataSnapshot.child("kpi2_formula").getValue().toString();

                                                                    txtIndexName13.setText(kpi1);
                                                                    txtIndexName14.setText(kpi2);
                                                                    txtIndexFormula13.setText(kpi1_formula);
                                                                    txtIndexFormula14.setText(kpi2_formula);
                                                                }


                                                                companyRef.child(post_key).child("Balanced Scorecard").child("item3_2").addValueEventListener(new ValueEventListener() {
                                                                    @Override
                                                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                                                        if (dataSnapshot.exists()) {
                                                                            String kpi1 = dataSnapshot.child("kpi1").getValue().toString();
                                                                            String kpi2 = dataSnapshot.child("kpi2").getValue().toString();
                                                                            String kpi1_formula = dataSnapshot.child("kpi1_formula").getValue().toString();
                                                                            String kpi2_formula = dataSnapshot.child("kpi2_formula").getValue().toString();

                                                                            txtIndexName15.setText(kpi1);
                                                                            txtIndexName16.setText(kpi2);
                                                                            txtIndexFormula15.setText(kpi1_formula);
                                                                            txtIndexFormula16.setText(kpi2_formula);
                                                                        }


                                                                        companyRef.child(post_key).child("Balanced Scorecard").child("item3_3").addValueEventListener(new ValueEventListener() {
                                                                            @Override
                                                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                                                if (dataSnapshot.exists()) {
                                                                                    String kpi1 = dataSnapshot.child("kpi1").getValue().toString();
                                                                                    String kpi2 = dataSnapshot.child("kpi2").getValue().toString();
                                                                                    String kpi1_formula = dataSnapshot.child("kpi1_formula").getValue().toString();
                                                                                    String kpi2_formula = dataSnapshot.child("kpi2_formula").getValue().toString();

                                                                                    txtIndexName17.setText(kpi1);
                                                                                    txtIndexName18.setText(kpi2);
                                                                                    txtIndexFormula17.setText(kpi1_formula);
                                                                                    txtIndexFormula18.setText(kpi2_formula);
                                                                                }


                                                                                companyRef.child(post_key).child("Balanced Scorecard").child("item4_1").addValueEventListener(new ValueEventListener() {
                                                                                    @Override
                                                                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                                                                        if (dataSnapshot.exists()) {
                                                                                            String kpi1 = dataSnapshot.child("kpi1").getValue().toString();
                                                                                            String kpi2 = dataSnapshot.child("kpi2").getValue().toString();
                                                                                            String kpi1_formula = dataSnapshot.child("kpi1_formula").getValue().toString();
                                                                                            String kpi2_formula = dataSnapshot.child("kpi2_formula").getValue().toString();

                                                                                            txtIndexName19.setText(kpi1);
                                                                                            txtIndexName20.setText(kpi2);
                                                                                            txtIndexFormula19.setText(kpi1_formula);
                                                                                            txtIndexFormula20.setText(kpi2_formula);
                                                                                        }


                                                                                        companyRef.child(post_key).child("Balanced Scorecard").child("item4_2").addValueEventListener(new ValueEventListener() {
                                                                                            @Override
                                                                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                                                                if (dataSnapshot.exists()) {
                                                                                                    String kpi1 = dataSnapshot.child("kpi1").getValue().toString();
                                                                                                    String kpi2 = dataSnapshot.child("kpi2").getValue().toString();
                                                                                                    String kpi1_formula = dataSnapshot.child("kpi1_formula").getValue().toString();
                                                                                                    String kpi2_formula = dataSnapshot.child("kpi2_formula").getValue().toString();

                                                                                                    txtIndexName21.setText(kpi1);
                                                                                                    txtIndexName22.setText(kpi2);
                                                                                                    txtIndexFormula21.setText(kpi1_formula);
                                                                                                    txtIndexFormula22.setText(kpi2_formula);
                                                                                                }


                                                                                                companyRef.child(post_key).child("Balanced Scorecard").child("item4_3").addValueEventListener(new ValueEventListener() {
                                                                                                    @Override
                                                                                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                                                                                        if (dataSnapshot.exists()) {
                                                                                                            String kpi1 = dataSnapshot.child("kpi1").getValue().toString();
                                                                                                            String kpi2 = dataSnapshot.child("kpi2").getValue().toString();
                                                                                                            String kpi1_formula = dataSnapshot.child("kpi1_formula").getValue().toString();
                                                                                                            String kpi2_formula = dataSnapshot.child("kpi2_formula").getValue().toString();

                                                                                                            txtIndexName23.setText(kpi1);
                                                                                                            txtIndexName24.setText(kpi2);
                                                                                                            txtIndexFormula23.setText(kpi1_formula);
                                                                                                            txtIndexFormula24.setText(kpi2_formula);
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
}
