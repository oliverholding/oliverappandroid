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


public class IndustryAtractiveFragment extends Fragment {

    DatabaseReference companyRef;
    String post_key, item_x,item_y;
    int answer1,answer2,answer3,answer4,answer5,answer6,answer21,answer22,answer23,answer24,answer25,answer26,answer31,answer32,answer33,answer34,answer35,answer36,answer41,answer42,answer43,answer44,answer45,answer46,answer51,answer52,answer53,answer54,answer55,answer56,average_1,average_2,average_3,average_4,average_5;
    LinearLayout layout1,layout2,layout3,layout4,layout5,layout6,layout7,layout8,layout9;
    TextView txtItem1,txtItem2,txtItem3,txtItem4,txtItem5,txtItem6,txtItem7,txtItem8,txtItem9,txtResult;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_industry_atractive, container, false);

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

        companyRef.child(post_key).child("Industry Profile").child("item_1").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                if (dataSnapshot.hasChild("answer_1")) {
                    String value = dataSnapshot.child("answer_1").getValue().toString();
                    if (value.equals("Nada Atractivo")) {
                        answer1 = 1;
                    }
                    if (value.equals("Poco Atractivo")) {
                        answer1 = 2;
                    }
                    if (value.equals("Neutral")) {
                        answer1 = 3;
                    }
                    if (value.equals("Atractivo")) {
                        answer1 = 4;
                    }
                    if (value.equals("Muy Atractivo")) {
                        answer1 = 5;
                    }
                }
                if (dataSnapshot.hasChild("answer_2")) {
                    String value = dataSnapshot.child("answer_2").getValue().toString();
                    if (value.equals("Nada Atractivo")) {
                        answer2 = 1;
                    }
                    if (value.equals("Poco Atractivo")) {
                        answer2 = 2;
                    }
                    if (value.equals("Neutral")) {
                        answer2 = 3;
                    }
                    if (value.equals("Atractivo")) {
                        answer2 = 4;
                    }
                    if (value.equals("Muy Atractivo")) {
                        answer2 = 5;
                    }
                }
                if (dataSnapshot.hasChild("answer_3")) {
                    String value = dataSnapshot.child("answer_3").getValue().toString();
                    if (value.equals("Nada Atractivo")) {
                        answer3 = 1;
                    }
                    if (value.equals("Poco Atractivo")) {
                        answer3 = 2;
                    }
                    if (value.equals("Neutral")) {
                        answer3 = 3;
                    }
                    if (value.equals("Atractivo")) {
                        answer3 = 4;
                    }
                    if (value.equals("Muy Atractivo")) {
                        answer3 = 5;
                    }
                }
                if (dataSnapshot.hasChild("answer_4")) {
                    String value = dataSnapshot.child("answer_4").getValue().toString();
                    if (value.equals("Nada Atractivo")) {
                        answer4 = 1;
                    }
                    if (value.equals("Poco Atractivo")) {
                        answer4 = 2;
                    }
                    if (value.equals("Neutral")) {
                        answer4 = 3;
                    }
                    if (value.equals("Atractivo")) {
                        answer4 = 4;
                    }
                    if (value.equals("Muy Atractivo")) {
                        answer4 = 5;
                    }
                }
                if (dataSnapshot.hasChild("answer_5")) {
                    String value = dataSnapshot.child("answer_5").getValue().toString();
                    if (value.equals("Nada Atractivo")) {
                        answer5 = 1;
                    }
                    if (value.equals("Poco Atractivo")) {
                        answer5 = 2;
                    }
                    if (value.equals("Neutral")) {
                        answer5 = 3;
                    }
                    if (value.equals("Atractivo")) {
                        answer5 = 4;
                    }
                    if (value.equals("Muy Atractivo")) {
                        answer5 = 5;
                    }
                }
                if (dataSnapshot.hasChild("answer_6")) {
                    String value = dataSnapshot.child("answer_6").getValue().toString();
                    if (value.equals("Nada Atractivo")) {
                        answer6 = 1;
                    }
                    if (value.equals("Poco Atractivo")) {
                        answer6 = 2;
                    }
                    if (value.equals("Neutral")) {
                        answer6 = 3;
                    }
                    if (value.equals("Atractivo")) {
                        answer6 = 4;
                    }
                    if (value.equals("Muy Atractivo")) {
                        answer6 = 5;
                    }
                }
                int total = answer1+answer2+answer3+answer4+answer5+answer6;
                average_1 = total/6;

                companyRef.child(post_key).child("Industry Profile").child("item_2").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {


                        if (dataSnapshot.hasChild("answer_1")) {
                            String value = dataSnapshot.child("answer_1").getValue().toString();
                            if (value.equals("Nada Atractivo")) {
                                answer21 = 1;
                            }
                            if (value.equals("Poco Atractivo")) {
                                answer21 = 2;
                            }
                            if (value.equals("Neutral")) {
                                answer21 = 3;
                            }
                            if (value.equals("Atractivo")) {
                                answer21 = 4;
                            }
                            if (value.equals("Muy Atractivo")) {
                                answer21 = 5;
                            }
                        }
                        if (dataSnapshot.hasChild("answer_2")) {
                            String value = dataSnapshot.child("answer_2").getValue().toString();
                            if (value.equals("Nada Atractivo")) {
                                answer22 = 1;
                            }
                            if (value.equals("Poco Atractivo")) {
                                answer22 = 2;
                            }
                            if (value.equals("Neutral")) {
                                answer22 = 3;
                            }
                            if (value.equals("Atractivo")) {
                                answer22 = 4;
                            }
                            if (value.equals("Muy Atractivo")) {
                                answer22 = 5;
                            }
                        }
                        if (dataSnapshot.hasChild("answer_3")) {
                            String value = dataSnapshot.child("answer_3").getValue().toString();
                            if (value.equals("Nada Atractivo")) {
                                answer23 = 1;
                            }
                            if (value.equals("Poco Atractivo")) {
                                answer23 = 2;
                            }
                            if (value.equals("Neutral")) {
                                answer23 = 3;
                            }
                            if (value.equals("Atractivo")) {
                                answer23 = 4;
                            }
                            if (value.equals("Muy Atractivo")) {
                                answer23 = 5;
                            }
                        }
                        if (dataSnapshot.hasChild("answer_4")) {
                            String value = dataSnapshot.child("answer_4").getValue().toString();
                            if (value.equals("Nada Atractivo")) {
                                answer24 = 1;
                            }
                            if (value.equals("Poco Atractivo")) {
                                answer24 = 2;
                            }
                            if (value.equals("Neutral")) {
                                answer24 = 3;
                            }
                            if (value.equals("Atractivo")) {
                                answer24 = 4;
                            }
                            if (value.equals("Muy Atractivo")) {
                                answer24 = 5;
                            }
                        }
                        if (dataSnapshot.hasChild("answer_5")) {
                            String value = dataSnapshot.child("answer_5").getValue().toString();
                            if (value.equals("Nada Atractivo")) {
                                answer25 = 1;
                            }
                            if (value.equals("Poco Atractivo")) {
                                answer25 = 2;
                            }
                            if (value.equals("Neutral")) {
                                answer25 = 3;
                            }
                            if (value.equals("Atractivo")) {
                                answer25 = 4;
                            }
                            if (value.equals("Muy Atractivo")) {
                                answer25 = 5;
                            }
                        }
                        if (dataSnapshot.hasChild("answer_6")) {
                            String value = dataSnapshot.child("answer_6").getValue().toString();
                            if (value.equals("Nada Atractivo")) {
                                answer26 = 1;
                            }
                            if (value.equals("Poco Atractivo")) {
                                answer26 = 2;
                            }
                            if (value.equals("Neutral")) {
                                answer26 = 3;
                            }
                            if (value.equals("Atractivo")) {
                                answer26 = 4;
                            }
                            if (value.equals("Muy Atractivo")) {
                                answer26 = 5;
                            }
                        }
                        int total = answer21+answer22+answer23+answer24+answer25+answer26;
                        average_2 = total/6;

                        companyRef.child(post_key).child("Industry Profile").child("item_3").addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {


                                if (dataSnapshot.hasChild("answer_1")) {
                                    String value = dataSnapshot.child("answer_1").getValue().toString();
                                    if (value.equals("Nada Atractivo")) {
                                        answer31 = 1;
                                    }
                                    if (value.equals("Poco Atractivo")) {
                                        answer31 = 2;
                                    }
                                    if (value.equals("Neutral")) {
                                        answer31 = 3;
                                    }
                                    if (value.equals("Atractivo")) {
                                        answer31 = 4;
                                    }
                                    if (value.equals("Muy Atractivo")) {
                                        answer31 = 5;
                                    }
                                }
                                if (dataSnapshot.hasChild("answer_2")) {
                                    String value = dataSnapshot.child("answer_2").getValue().toString();
                                    if (value.equals("Nada Atractivo")) {
                                        answer32 = 1;
                                    }
                                    if (value.equals("Poco Atractivo")) {
                                        answer32 = 2;
                                    }
                                    if (value.equals("Neutral")) {
                                        answer32 = 3;
                                    }
                                    if (value.equals("Atractivo")) {
                                        answer32 = 4;
                                    }
                                    if (value.equals("Muy Atractivo")) {
                                        answer32 = 5;
                                    }
                                }
                                if (dataSnapshot.hasChild("answer_3")) {
                                    String value = dataSnapshot.child("answer_3").getValue().toString();
                                    if (value.equals("Nada Atractivo")) {
                                        answer33 = 1;
                                    }
                                    if (value.equals("Poco Atractivo")) {
                                        answer33 = 2;
                                    }
                                    if (value.equals("Neutral")) {
                                        answer33 = 3;
                                    }
                                    if (value.equals("Atractivo")) {
                                        answer33 = 4;
                                    }
                                    if (value.equals("Muy Atractivo")) {
                                        answer33 = 5;
                                    }
                                }
                                if (dataSnapshot.hasChild("answer_4")) {
                                    String value = dataSnapshot.child("answer_4").getValue().toString();
                                    if (value.equals("Nada Atractivo")) {
                                        answer34 = 1;
                                    }
                                    if (value.equals("Poco Atractivo")) {
                                        answer34 = 2;
                                    }
                                    if (value.equals("Neutral")) {
                                        answer34 = 3;
                                    }
                                    if (value.equals("Atractivo")) {
                                        answer34 = 4;
                                    }
                                    if (value.equals("Muy Atractivo")) {
                                        answer34 = 5;
                                    }
                                }
                                if (dataSnapshot.hasChild("answer_5")) {
                                    String value = dataSnapshot.child("answer_5").getValue().toString();
                                    if (value.equals("Nada Atractivo")) {
                                        answer35 = 1;
                                    }
                                    if (value.equals("Poco Atractivo")) {
                                        answer35 = 2;
                                    }
                                    if (value.equals("Neutral")) {
                                        answer35 = 3;
                                    }
                                    if (value.equals("Atractivo")) {
                                        answer35 = 4;
                                    }
                                    if (value.equals("Muy Atractivo")) {
                                        answer35 = 5;
                                    }
                                }
                                if (dataSnapshot.hasChild("answer_6")) {
                                    String value = dataSnapshot.child("answer_6").getValue().toString();
                                    if (value.equals("Nada Atractivo")) {
                                        answer36 = 1;
                                    }
                                    if (value.equals("Poco Atractivo")) {
                                        answer36 = 2;
                                    }
                                    if (value.equals("Neutral")) {
                                        answer36 = 3;
                                    }
                                    if (value.equals("Atractivo")) {
                                        answer36 = 4;
                                    }
                                    if (value.equals("Muy Atractivo")) {
                                        answer36 = 5;
                                    }
                                }
                                int total = answer31+answer32+answer33+answer34+answer35+answer36;
                                average_3= total/6;


                                companyRef.child(post_key).child("Industry Profile").child("item_4").addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {


                                        if (dataSnapshot.hasChild("answer_1")) {
                                            String value = dataSnapshot.child("answer_1").getValue().toString();
                                            if (value.equals("Nada Atractivo")) {
                                                answer41 = 1;
                                            }
                                            if (value.equals("Poco Atractivo")) {
                                                answer41 = 2;
                                            }
                                            if (value.equals("Neutral")) {
                                                answer41 = 3;
                                            }
                                            if (value.equals("Atractivo")) {
                                                answer41 = 4;
                                            }
                                            if (value.equals("Muy Atractivo")) {
                                                answer41 = 5;
                                            }
                                        }
                                        if (dataSnapshot.hasChild("answer_2")) {
                                            String value = dataSnapshot.child("answer_2").getValue().toString();
                                            if (value.equals("Nada Atractivo")) {
                                                answer42 = 1;
                                            }
                                            if (value.equals("Poco Atractivo")) {
                                                answer42 = 2;
                                            }
                                            if (value.equals("Neutral")) {
                                                answer42 = 3;
                                            }
                                            if (value.equals("Atractivo")) {
                                                answer42 = 4;
                                            }
                                            if (value.equals("Muy Atractivo")) {
                                                answer42 = 5;
                                            }
                                        }
                                        if (dataSnapshot.hasChild("answer_3")) {
                                            String value = dataSnapshot.child("answer_3").getValue().toString();
                                            if (value.equals("Nada Atractivo")) {
                                                answer43 = 1;
                                            }
                                            if (value.equals("Poco Atractivo")) {
                                                answer43 = 2;
                                            }
                                            if (value.equals("Neutral")) {
                                                answer43 = 3;
                                            }
                                            if (value.equals("Atractivo")) {
                                                answer43 = 4;
                                            }
                                            if (value.equals("Muy Atractivo")) {
                                                answer43 = 5;
                                            }
                                        }
                                        if (dataSnapshot.hasChild("answer_4")) {
                                            String value = dataSnapshot.child("answer_4").getValue().toString();
                                            if (value.equals("Nada Atractivo")) {
                                                answer44 = 1;
                                            }
                                            if (value.equals("Poco Atractivo")) {
                                                answer44 = 2;
                                            }
                                            if (value.equals("Neutral")) {
                                                answer44 = 3;
                                            }
                                            if (value.equals("Atractivo")) {
                                                answer44 = 4;
                                            }
                                            if (value.equals("Muy Atractivo")) {
                                                answer44 = 5;
                                            }
                                        }
                                        if (dataSnapshot.hasChild("answer_5")) {
                                            String value = dataSnapshot.child("answer_5").getValue().toString();
                                            if (value.equals("Nada Atractivo")) {
                                                answer45 = 1;
                                            }
                                            if (value.equals("Poco Atractivo")) {
                                                answer45 = 2;
                                            }
                                            if (value.equals("Neutral")) {
                                                answer45 = 3;
                                            }
                                            if (value.equals("Atractivo")) {
                                                answer45 = 4;
                                            }
                                            if (value.equals("Muy Atractivo")) {
                                                answer45 = 5;
                                            }
                                        }
                                        if (dataSnapshot.hasChild("answer_6")) {
                                            String value = dataSnapshot.child("answer_6").getValue().toString();
                                            if (value.equals("Nada Atractivo")) {
                                                answer46 = 1;
                                            }
                                            if (value.equals("Poco Atractivo")) {
                                                answer46 = 2;
                                            }
                                            if (value.equals("Neutral")) {
                                                answer46 = 3;
                                            }
                                            if (value.equals("Atractivo")) {
                                                answer46 = 4;
                                            }
                                            if (value.equals("Muy Atractivo")) {
                                                answer46 = 5;
                                            }
                                        }
                                        int total = answer41+answer42+answer43+answer44+answer45+answer46;
                                        average_4= total/6;

                                        companyRef.child(post_key).child("Industry Profile").child("item_5").addValueEventListener(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(DataSnapshot dataSnapshot) {


                                                if (dataSnapshot.hasChild("answer_1")) {
                                                    String value = dataSnapshot.child("answer_1").getValue().toString();
                                                    if (value.equals("Nada Atractivo")) {
                                                        answer51 = 1;
                                                    }
                                                    if (value.equals("Poco Atractivo")) {
                                                        answer51 = 2;
                                                    }
                                                    if (value.equals("Neutral")) {
                                                        answer51 = 3;
                                                    }
                                                    if (value.equals("Atractivo")) {
                                                        answer51 = 4;
                                                    }
                                                    if (value.equals("Muy Atractivo")) {
                                                        answer51 = 5;
                                                    }
                                                }
                                                if (dataSnapshot.hasChild("answer_2")) {
                                                    String value = dataSnapshot.child("answer_2").getValue().toString();
                                                    if (value.equals("Nada Atractivo")) {
                                                        answer52 = 1;
                                                    }
                                                    if (value.equals("Poco Atractivo")) {
                                                        answer52 = 2;
                                                    }
                                                    if (value.equals("Neutral")) {
                                                        answer52 = 3;
                                                    }
                                                    if (value.equals("Atractivo")) {
                                                        answer52 = 4;
                                                    }
                                                    if (value.equals("Muy Atractivo")) {
                                                        answer52 = 5;
                                                    }
                                                }
                                                if (dataSnapshot.hasChild("answer_3")) {
                                                    String value = dataSnapshot.child("answer_3").getValue().toString();
                                                    if (value.equals("Nada Atractivo")) {
                                                        answer53 = 1;
                                                    }
                                                    if (value.equals("Poco Atractivo")) {
                                                        answer53 = 2;
                                                    }
                                                    if (value.equals("Neutral")) {
                                                        answer53 = 3;
                                                    }
                                                    if (value.equals("Atractivo")) {
                                                        answer53 = 4;
                                                    }
                                                    if (value.equals("Muy Atractivo")) {
                                                        answer53 = 5;
                                                    }
                                                }
                                                if (dataSnapshot.hasChild("answer_4")) {
                                                    String value = dataSnapshot.child("answer_4").getValue().toString();
                                                    if (value.equals("Nada Atractivo")) {
                                                        answer54 = 1;
                                                    }
                                                    if (value.equals("Poco Atractivo")) {
                                                        answer54 = 2;
                                                    }
                                                    if (value.equals("Neutral")) {
                                                        answer54 = 3;
                                                    }
                                                    if (value.equals("Atractivo")) {
                                                        answer54 = 4;
                                                    }
                                                    if (value.equals("Muy Atractivo")) {
                                                        answer54 = 5;
                                                    }
                                                }
                                                if (dataSnapshot.hasChild("answer_5")) {
                                                    String value = dataSnapshot.child("answer_5").getValue().toString();
                                                    if (value.equals("Nada Atractivo")) {
                                                        answer55 = 1;
                                                    }
                                                    if (value.equals("Poco Atractivo")) {
                                                        answer55 = 2;
                                                    }
                                                    if (value.equals("Neutral")) {
                                                        answer55 = 3;
                                                    }
                                                    if (value.equals("Atractivo")) {
                                                        answer55 = 4;
                                                    }
                                                    if (value.equals("Muy Atractivo")) {
                                                        answer55 = 5;
                                                    }
                                                }
                                                if (dataSnapshot.hasChild("answer_6")) {
                                                    String value = dataSnapshot.child("answer_6").getValue().toString();
                                                    if (value.equals("Nada Atractivo")) {
                                                        answer56 = 1;
                                                    }
                                                    if (value.equals("Poco Atractivo")) {
                                                        answer56 = 2;
                                                    }
                                                    if (value.equals("Neutral")) {
                                                        answer56 = 3;
                                                    }
                                                    if (value.equals("Atractivo")) {
                                                        answer56 = 4;
                                                    }
                                                    if (value.equals("Muy Atractivo")) {
                                                        answer56 = 5;
                                                    }
                                                }
                                                int total = answer51+answer52+answer53+answer54+answer55+answer56;
                                                average_5= total/6;


                                                int total_average= (average_1+average_2+average_3+average_4+average_5)/5;

                                                if (total_average == 1) {

                                                    item_x = "Bajo";
                                                }
                                                if (total_average == 2) {

                                                    item_x = "Bajo";
                                                }
                                                if (total_average == 3) {

                                                    item_x = "Medio";
                                                }
                                                if (total_average == 4) {

                                                    item_x = "Alto";
                                                }
                                                if (total_average == 5) {

                                                    item_x = "Alto";
                                                }

                                                companyRef.child(post_key).child("Strategic Matrix").child("item_4").addValueEventListener(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                                        if (dataSnapshot.hasChild("score")) {
                                                            double value = dataSnapshot.child("score").getValue(Double.class);
                                                            if (value < 1) {
                                                                item_y = "Débil";

                                                            } else if (value >= 1 && value < 3) {
                                                                item_y = "Medio";

                                                            } else if (value >= 3) {
                                                                item_y = "Fuerte";

                                                            }


                                                            if (item_x.equals("Alto") && item_y.equals("Débil")) {
                                                                layout1.setBackgroundResource(R.drawable.blue_button_style);
                                                                txtItem1.setTextColor(Color.WHITE);
                                                                txtResult.setText(txtItem1.getText().toString()+" 1. Ganar. 2. Concentraciòn en segmentos atractivos. 3. Defensa de Fortalezas");
                                                            }
                                                            if (item_x.equals("Alto") && item_y.equals("Medio")) {
                                                                layout2.setBackgroundResource(R.drawable.blue_button_style);
                                                                txtItem2.setTextColor(Color.WHITE);
                                                                txtResult.setText(txtItem2.getText().toString()+"  1. Invertir en segmentos atractivos. 2. Neutralizar la Competencia. 3. Aumentar Rentabilidad Via Productividad");
                                                            }
                                                            if (item_x.equals("Alto") && item_y.equals("Fuerte")) {
                                                                layout3.setBackgroundResource(R.drawable.blue_button_style);
                                                                txtItem3.setTextColor(Color.WHITE);
                                                                txtResult.setText(txtItem3.getText().toString()+"  1. Crecer al máximo posible. 2. Sostener Fortalezas");
                                                            }
                                                            if (item_x.equals("Medio") && item_y.equals("Débil")) {
                                                                layout4.setBackgroundResource(R.drawable.blue_button_style);
                                                                txtItem4.setTextColor(Color.WHITE);
                                                                txtResult.setText(txtItem4.getText().toString()+" 1. Proteger posición en segmentos rentables. 2. Perfeccionar productos. 3. Minimizar inversión");
                                                            }
                                                            if (item_x.equals("Medio") && item_y.equals("Medio")) {
                                                                layout5.setBackgroundResource(R.drawable.blue_button_style);
                                                                txtItem5.setTextColor(Color.WHITE);
                                                                txtResult.setText(txtItem5.getText().toString()+" 1. Invertir en segmentos de buena rentabilidad y bajo precio");
                                                            }
                                                            if (item_x.equals("Medio") && item_y.equals("Fuerte")) {
                                                                layout6.setBackgroundResource(R.drawable.blue_button_style);
                                                                txtItem6.setTextColor(Color.WHITE);
                                                                txtResult.setText(txtItem6.getText().toString()+" 1. Desarrollo selectivo de fortalezas. 2. Refuerzo de áreas vulnerables");
                                                            }
                                                            if (item_x.equals("Bajo") && item_y.equals("Débil")) {
                                                                layout7.setBackgroundResource(R.drawable.blue_button_style);
                                                                txtItem7.setTextColor(Color.WHITE);
                                                                txtResult.setText(txtItem7.getText().toString()+" 1. Vender todo al contado. 2. Reducción de Costos Fijos");
                                                            }
                                                            if (item_x.equals("Bajo") && item_y.equals("Medio")) {
                                                                layout8.setBackgroundResource(R.drawable.blue_button_style);
                                                                txtItem8.setTextColor(Color.WHITE);
                                                                txtResult.setText(txtItem8.getText().toString()+"  1. Expandirse a bajo riesgo ó bajar la inversión y racionalizar");
                                                            }
                                                            if (item_x.equals("Bajo") && item_y.equals("Fuerte")) {
                                                                layout9.setBackgroundResource(R.drawable.blue_button_style);
                                                                txtItem9.setTextColor(Color.WHITE);
                                                                txtResult.setText(txtItem8.getText().toString()+" 1. Especialización alrededor de fortalezas limitadas. 2. Neutralizar debilidades. 3. Retirarse si no hay crecimiento");
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


        return view;
    }
}
