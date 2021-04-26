package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.StrategicDirection.StrategicMatrix;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;

public class CompetitiveMatrixFragment extends Fragment {

    DatabaseReference companyRef;
    String post_key,market_average_st,company_st;
    int factor_1,factor_2,factor_3,factor_4,factor_5,market_average;
    LinearLayout layout1,layout2,layout3,layout4,layout5,layout6,layout7,layout8,layout9;
    TextView txtItem1,txtItem2,txtItem3,txtItem4,txtItem5,txtItem6,txtItem7,txtItem8,txtItem9,txtResult;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_competitive_matrix, container, false);

        post_key = getActivity().getIntent().getExtras().getString("post_key");
        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");

        layout1 = view.findViewById(R.id.layout1);
        layout2 = view.findViewById(R.id.layout2);
        layout3 = view.findViewById(R.id.layout3);
        layout4 = view.findViewById(R.id.layout4);
        layout5 = view.findViewById(R.id.layout5);
        layout6 = view.findViewById(R.id.layout6);
        layout7 = view.findViewById(R.id.layout7);
        layout8 = view.findViewById(R.id.layout8);
        layout9 = view.findViewById(R.id.layout9);

        txtItem1 = view.findViewById(R.id.txtItem1);
        txtItem2 = view.findViewById(R.id.txtItem2);
        txtItem3 = view.findViewById(R.id.txtItem3);
        txtItem4 = view.findViewById(R.id.txtItem4);
        txtItem5 = view.findViewById(R.id.txtItem5);
        txtItem6 = view.findViewById(R.id.txtItem6);
        txtItem7 = view.findViewById(R.id.txtItem7);
        txtItem8 = view.findViewById(R.id.txtItem8);
        txtItem9 = view.findViewById(R.id.txtItem9);

        txtResult = view.findViewById(R.id.txtResult);

        companyRef.child(post_key).child("Market Information").child("item_1").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild("question_2")) {
                    String value = dataSnapshot.child("question_2").getValue().toString();

                    if (value.equals("Alta")) {
                        factor_1 = 3;
                    }
                    if (value.equals("Media")) {
                        factor_1 = 2;
                    }
                    if (value.equals("Débil")) {
                        factor_1 = 1;
                    }

                }

                companyRef.child(post_key).child("Market Information").child("item_2").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChild("question_2")) {
                            String value = dataSnapshot.child("question_2").getValue().toString();

                            if (value.equals("Alta")) {
                                factor_2 = 3;
                            }
                            if (value.equals("Media")) {
                                factor_2 = 2;
                            }
                            if (value.equals("Débil")) {
                                factor_2 = 1;
                            }
                        }
                        companyRef.child(post_key).child("Market Information").child("item_3").addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                if (dataSnapshot.hasChild("question_2")) {
                                    String value = dataSnapshot.child("question_2").getValue().toString();

                                    if (value.equals("Alta")) {
                                        factor_3 = 3;
                                    }
                                    if (value.equals("Media")) {
                                        factor_3 = 2;
                                    }
                                    if (value.equals("Débil")) {
                                        factor_3 = 1;
                                    }
                                }


                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                        companyRef.child(post_key).child("Market Information").child("item_4").addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                if (dataSnapshot.hasChild("question_2")) {
                                    String value = dataSnapshot.child("question_2").getValue().toString();


                                    if (value.equals("Alta")) {
                                        factor_4 = 3;
                                    }
                                    if (value.equals("Media")) {
                                        factor_4 = 2;
                                    }
                                    if (value.equals("Débil")) {
                                        factor_4 = 1;
                                    }
                                }


                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });

                        companyRef.child(post_key).child("Market Information").child("item_5").addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                if (dataSnapshot.hasChild("question_2")) {
                                    String value = dataSnapshot.child("question_2").getValue().toString();

                                    if (value.equals("Alta")) {
                                        factor_5 = 3;
                                    }
                                    if (value.equals("Media")) {
                                        factor_5 = 2;
                                    }
                                    if (value.equals("Débil")) {
                                        factor_5 = 1;
                                    }

                                    market_average = (factor_1+factor_2+factor_3+factor_4+factor_5)/5;
                                    if (market_average == 1) {
                                        market_average_st = "Débil";
                                    }
                                    if (market_average == 2) {
                                        market_average_st = "Medio";
                                    }
                                    if (market_average == 3) {
                                        market_average_st = "Alto";
                                    }

                                    companyRef.child(post_key).child("Strategic Matrix").child("item_4").addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {
                                            if (dataSnapshot.hasChild("score")) {
                                                double value = dataSnapshot.child("score").getValue(Double.class);
                                                if (value < 1) {
                                                    company_st = "Débil";

                                                } else if (value >= 1 && value < 3) {
                                                    company_st = "Medio";

                                                } else if (value >= 3) {
                                                    company_st = "Fuerte";

                                                }


                                                if (market_average_st.equals("Alto") && company_st.equals("Débil")) {
                                                    layout1.setBackgroundResource(R.drawable.blue_button_style);
                                                    txtItem1.setTextColor(Color.WHITE);
                                                    txtResult.setText("Resultado final: "+txtItem1.getText().toString());
                                                }
                                                if (market_average_st.equals("Alto") && company_st.equals("Medio")) {
                                                    layout2.setBackgroundResource(R.drawable.blue_button_style);
                                                    txtItem2.setTextColor(Color.WHITE);
                                                    txtResult.setText("No se realiza alguna acción");
                                                }
                                                if (market_average_st.equals("Alto") && company_st.equals("Fuerte")) {
                                                    layout3.setBackgroundResource(R.drawable.blue_button_style);
                                                    txtItem3.setTextColor(Color.WHITE);
                                                    txtResult.setText(txtItem3.getText().toString());
                                                }
                                                if (market_average_st.equals("Medio") && company_st.equals("Débil")) {
                                                    layout4.setBackgroundResource(R.drawable.blue_button_style);
                                                    txtItem4.setTextColor(Color.WHITE);
                                                    txtResult.setText("No se realiza alguna acción");
                                                }
                                                if (market_average_st.equals("Medio") && company_st.equals("Medio")) {
                                                    layout5.setBackgroundResource(R.drawable.blue_button_style);
                                                    txtItem5.setTextColor(Color.WHITE);
                                                    txtResult.setText("No se realiza alguna acción");
                                                }
                                                if (market_average_st.equals("Medio") && company_st.equals("Fuerte")) {
                                                    layout6.setBackgroundResource(R.drawable.blue_button_style);
                                                    txtItem6.setTextColor(Color.WHITE);
                                                    txtResult.setText("No se realiza alguna acción");
                                                }
                                                if (market_average_st.equals("Débil") && company_st.equals("Débil")) {
                                                    layout7.setBackgroundResource(R.drawable.blue_button_style);
                                                    txtItem7.setTextColor(Color.WHITE);
                                                    txtResult.setText("Resultado final: "+txtItem7.getText().toString());
                                                }
                                                if (market_average_st.equals("Débil") && company_st.equals("Medio")) {
                                                    layout8.setBackgroundResource(R.drawable.blue_button_style);
                                                    txtItem8.setTextColor(Color.WHITE);
                                                    txtResult.setText("No se realiza alguna acción");
                                                }
                                                if (market_average_st.equals("Débil") && company_st.equals("Fuerte")) {
                                                    layout9.setBackgroundResource(R.drawable.blue_button_style);
                                                    txtItem9.setTextColor(Color.WHITE);
                                                    txtResult.setText("Resultado final: "+txtItem9.getText().toString());
                                                }



                                            }

                                        }

                                        @Override
                                        public void onCancelled(DatabaseError databaseError) {

                                        }
                                    });


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
