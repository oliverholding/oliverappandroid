package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.StrategicDirection.IndustryInformation;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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


public class IndustryProfileFragment extends Fragment {

    ImageView btnItem1,btnItem2,btnItem3,btnItem4,btnItem5;
    TextView txtItem1Answer1,txtItem1Answer2,txtItem1Answer3,txtItem1Answer4,txtItem1Answer5,txtItem2Answer1,txtItem2Answer2,txtItem2Answer3,txtItem2Answer4,txtItem2Answer5,
            txtItem3Answer1,txtItem3Answer2,txtItem3Answer3,txtItem3Answer4,txtItem3Answer5,txtItem4Answer1,txtItem4Answer2,txtItem4Answer3,txtItem4Answer4,txtItem4Answer5,
            txtItem5Answer1,txtItem5Answer2,txtItem5Answer3,txtItem5Answer4,txtItem5Answer5,txtItem6Answer1,txtItem6Answer2,txtItem6Answer3,txtItem6Answer4,txtItem6Answer5;
    DatabaseReference companyRef;
    String post_key,answer_selected_1,answer_selected_2,answer_selected_3,answer_selected_4,answer_selected_5,answer_selected_6;
    int answer1,answer2,answer3,answer4,answer5,answer6,answer21,answer22,answer23,answer24,answer25,answer26,answer31,answer32,answer33,answer34,answer35,answer36,answer41,answer42,answer43,answer44,answer45,answer46,answer51,answer52,answer53,answer54,answer55,answer56,average_1,average_2,average_3,average_4,average_5;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_industry_profile, container, false);

        post_key = getActivity().getIntent().getExtras().getString("post_key");
        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");

        btnItem1 = view.findViewById(R.id.btnItem1);
        btnItem2 = view.findViewById(R.id.btnItem2);
        btnItem3 = view.findViewById(R.id.btnItem3);
        btnItem4 = view.findViewById(R.id.btnItem4);
        btnItem5 = view.findViewById(R.id.btnItem5);

        txtItem1Answer1 = view.findViewById(R.id.txtItem1Answer1);
        txtItem1Answer2 = view.findViewById(R.id.txtItem1Answer2);
        txtItem1Answer3 = view.findViewById(R.id.txtItem1Answer3);
        txtItem1Answer4 = view.findViewById(R.id.txtItem1Answer4);
        txtItem1Answer5 = view.findViewById(R.id.txtItem1Answer5);

        txtItem2Answer1 = view.findViewById(R.id.txtItem2Answer1);
        txtItem2Answer2 = view.findViewById(R.id.txtItem2Answer2);
        txtItem2Answer3 = view.findViewById(R.id.txtItem2Answer3);
        txtItem2Answer4 = view.findViewById(R.id.txtItem2Answer4);
        txtItem2Answer5 = view.findViewById(R.id.txtItem2Answer5);

        txtItem3Answer1 = view.findViewById(R.id.txtItem3Answer1);
        txtItem3Answer2 = view.findViewById(R.id.txtItem3Answer2);
        txtItem3Answer3 = view.findViewById(R.id.txtItem3Answer3);
        txtItem3Answer4 = view.findViewById(R.id.txtItem3Answer4);
        txtItem3Answer5 = view.findViewById(R.id.txtItem3Answer5);

        txtItem4Answer1 = view.findViewById(R.id.txtItem4Answer1);
        txtItem4Answer2 = view.findViewById(R.id.txtItem4Answer2);
        txtItem4Answer3 = view.findViewById(R.id.txtItem4Answer3);
        txtItem4Answer4 = view.findViewById(R.id.txtItem4Answer4);
        txtItem4Answer5 = view.findViewById(R.id.txtItem4Answer5);

        txtItem5Answer1 = view.findViewById(R.id.txtItem5Answer1);
        txtItem5Answer2 = view.findViewById(R.id.txtItem5Answer2);
        txtItem5Answer3 = view.findViewById(R.id.txtItem5Answer3);
        txtItem5Answer4 = view.findViewById(R.id.txtItem5Answer4);
        txtItem5Answer5 = view.findViewById(R.id.txtItem5Answer5);



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
                if (average_1 == 1) {
                    txtItem1Answer1.setText("X");
                    txtItem1Answer2.setText("");
                    txtItem1Answer3.setText("");
                    txtItem1Answer4.setText("");
                    txtItem1Answer5.setText("");

                }
                if (average_1 == 2) {
                    txtItem1Answer2.setText("X");
                    txtItem1Answer1.setText("");
                    txtItem1Answer3.setText("");
                    txtItem1Answer4.setText("");
                    txtItem1Answer5.setText("");

                }
                if (average_1 == 3) {
                    txtItem1Answer3.setText("X");
                    txtItem1Answer2.setText("");
                    txtItem1Answer1.setText("");
                    txtItem1Answer4.setText("");
                    txtItem1Answer5.setText("");

                }
                if (average_1 == 4) {
                    txtItem1Answer4.setText("X");
                    txtItem1Answer2.setText("");
                    txtItem1Answer3.setText("");
                    txtItem1Answer1.setText("");
                    txtItem1Answer5.setText("");

                }
                if (average_1 == 5) {
                    txtItem1Answer5.setText("X");
                    txtItem1Answer2.setText("");
                    txtItem1Answer3.setText("");
                    txtItem1Answer4.setText("");
                    txtItem1Answer1.setText("");

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

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
                if (average_2 == 1) {
                    txtItem2Answer1.setText("X");
                    txtItem2Answer2.setText("");
                    txtItem2Answer3.setText("");
                    txtItem2Answer4.setText("");
                    txtItem2Answer5.setText("");

                }
                if (average_2 == 2) {
                    txtItem2Answer2.setText("X");
                    txtItem2Answer1.setText("");
                    txtItem2Answer3.setText("");
                    txtItem2Answer4.setText("");
                    txtItem2Answer5.setText("");

                }
                if (average_2 == 3) {
                    txtItem2Answer3.setText("X");
                    txtItem2Answer2.setText("");
                    txtItem2Answer1.setText("");
                    txtItem2Answer4.setText("");
                    txtItem2Answer5.setText("");

                }
                if (average_2 == 4) {
                    txtItem2Answer4.setText("X");
                    txtItem2Answer2.setText("");
                    txtItem2Answer3.setText("");
                    txtItem2Answer1.setText("");
                    txtItem2Answer5.setText("");

                }
                if (average_2 == 5) {
                    txtItem2Answer5.setText("X");
                    txtItem2Answer2.setText("");
                    txtItem2Answer3.setText("");
                    txtItem2Answer4.setText("");
                    txtItem2Answer1.setText("");

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

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
                if (average_3 == 1) {
                    txtItem3Answer1.setText("X");
                    txtItem3Answer2.setText("");
                    txtItem3Answer3.setText("");
                    txtItem3Answer4.setText("");
                    txtItem3Answer5.setText("");

                }
                if (average_3 == 2) {
                    txtItem3Answer2.setText("X");
                    txtItem3Answer1.setText("");
                    txtItem3Answer3.setText("");
                    txtItem3Answer4.setText("");
                    txtItem3Answer5.setText("");

                }
                if (average_3 == 3) {
                    txtItem3Answer3.setText("X");
                    txtItem3Answer2.setText("");
                    txtItem3Answer1.setText("");
                    txtItem3Answer4.setText("");
                    txtItem3Answer5.setText("");

                }
                if (average_3 == 4) {
                    txtItem3Answer4.setText("X");
                    txtItem3Answer2.setText("");
                    txtItem3Answer3.setText("");
                    txtItem3Answer1.setText("");
                    txtItem3Answer5.setText("");

                }
                if (average_3 == 5) {
                    txtItem3Answer5.setText("X");
                    txtItem3Answer2.setText("");
                    txtItem3Answer3.setText("");
                    txtItem3Answer4.setText("");
                    txtItem3Answer1.setText("");

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

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
                if (average_4 == 1) {
                    txtItem4Answer1.setText("X");
                    txtItem4Answer2.setText("");
                    txtItem4Answer3.setText("");
                    txtItem4Answer4.setText("");
                    txtItem4Answer5.setText("");

                }
                if (average_4 == 2) {
                    txtItem4Answer2.setText("X");
                    txtItem4Answer1.setText("");
                    txtItem4Answer3.setText("");
                    txtItem4Answer4.setText("");
                    txtItem4Answer5.setText("");

                }
                if (average_4 == 3) {
                    txtItem4Answer3.setText("X");
                    txtItem4Answer2.setText("");
                    txtItem4Answer1.setText("");
                    txtItem4Answer4.setText("");
                    txtItem4Answer5.setText("");

                }
                if (average_4 == 4) {
                    txtItem4Answer4.setText("X");
                    txtItem4Answer2.setText("");
                    txtItem4Answer3.setText("");
                    txtItem4Answer1.setText("");
                    txtItem4Answer5.setText("");

                }
                if (average_4 == 5) {
                    txtItem4Answer5.setText("X");
                    txtItem4Answer2.setText("");
                    txtItem4Answer3.setText("");
                    txtItem4Answer4.setText("");
                    txtItem4Answer1.setText("");

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

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
                if (average_5 == 1) {
                    txtItem5Answer1.setText("X");
                    txtItem5Answer2.setText("");
                    txtItem5Answer3.setText("");
                    txtItem5Answer4.setText("");
                    txtItem5Answer5.setText("");

                }
                if (average_5 == 2) {
                    txtItem5Answer2.setText("X");
                    txtItem5Answer1.setText("");
                    txtItem5Answer3.setText("");
                    txtItem5Answer4.setText("");
                    txtItem5Answer5.setText("");

                }
                if (average_5 == 3) {
                    txtItem5Answer3.setText("X");
                    txtItem5Answer2.setText("");
                    txtItem5Answer1.setText("");
                    txtItem5Answer4.setText("");
                    txtItem5Answer5.setText("");

                }
                if (average_5 == 4) {
                    txtItem5Answer4.setText("X");
                    txtItem5Answer2.setText("");
                    txtItem5Answer3.setText("");
                    txtItem5Answer1.setText("");
                    txtItem5Answer5.setText("");

                }
                if (average_5 == 5) {
                    txtItem5Answer5.setText("X");
                    txtItem5Answer2.setText("");
                    txtItem5Answer3.setText("");
                    txtItem5Answer4.setText("");
                    txtItem5Answer1.setText("");

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        btnItem1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = "Factores de Mercado";
                String question_1 = "Crecimiento y Rentabilidad de la Empresa";
                String question_2 = "Diferenciación";
                String question_3 = "Tamaño de Mercado";
                String question_4 = "Estacionalidad";
                String question_5 = "Constante";
                String question_6 = "Mcdos Potenciales";
                String path = "item_1";

                showIndustryProfileDialog(title,question_1,question_2,question_3,question_4,question_5,question_6,path);
            }
        });

        btnItem2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = "Factores Competitivos";
                String question_1 = "Intensidad de la Competencia";
                String question_2 = "Grado de Concentración";
                String question_3 = "Barreras de Entrada";
                String question_4 = "Barreras de Salida";
                String question_5 = "Grado de integración";
                String question_6 = "Disponibilidad de Sustitutos";
                String path = "item_2";

                showIndustryProfileDialog(title,question_1,question_2,question_3,question_4,question_5,question_6,path);
            }
        });

        btnItem3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = "Factores Económicos y Gubernamentales";
                String question_1 = "Inflación y Tipo de Cambio";
                String question_2 = "Nivel Salarial";
                String question_3 = "Sum. De Mano de Obra";
                String question_4 = "Leyes Laborales";
                String question_5 = "Regulación";
                String question_6 = "Impuestos y Fiscalidad";
                String path = "item_3";

                showIndustryProfileDialog(title,question_1,question_2,question_3,question_4,question_5,question_6,path);
            }
        });

        btnItem4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = "Factores Tecnológicos";
                String question_1 = "Estructura";
                String question_2 = "Complejidad";
                String question_3 = "Globalización";
                String question_4 = "Apoyo Gubernamental";
                String question_5 = "Económico";
                String question_6 = "Informalidad";
                String path = "item_4";

                showIndustryProfileDialog(title,question_1,question_2,question_3,question_4,question_5,question_6,path);
            }
        });

        btnItem5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = "Factores Sociales";
                String question_1 = "Impactos Ecológicos";
                String question_2 = "Ética Laboral";
                String question_3 = "Protección del Consumidor";
                String question_4 = "Cambio demográfico";
                String question_5 = "Grado de Sindicalización";
                String question_6 = "Envejecimiento Poblacional";
                String path = "item_5";

                showIndustryProfileDialog(title,question_1,question_2,question_3,question_4,question_5,question_6,path);
            }
        });



        return view;
    }

    private void showIndustryProfileDialog(String title, String question_1, String question_2, String question_3, String question_4, String question_5, String question_6, final String path) {
        final AlertDialog dialog = new AlertDialog.Builder(getActivity()).create();

        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View finance_method = inflater.inflate(R.layout.industry_profile_set_data_dialog,null);

        TextView txtTitle,txtQuestion1,txtQuestion2,txtQuestion3,txtQuestion4,txtQuestion5,txtQuestion6;
        final RadioButton rdAnswer1_1,rdAnswer1_2,rdAnswer1_3,rdAnswer1_4,rdAnswer1_5,rdAnswer2_1,rdAnswer2_2,rdAnswer2_3,rdAnswer2_4,rdAnswer2_5,rdAnswer3_1,rdAnswer3_2,rdAnswer3_3,rdAnswer3_4,rdAnswer3_5,rdAnswer4_1,rdAnswer4_2,rdAnswer4_3,rdAnswer4_4,rdAnswer4_5,
                rdAnswer5_1,rdAnswer5_2,rdAnswer5_3,rdAnswer5_4,rdAnswer5_5,rdAnswer6_1,rdAnswer6_2,rdAnswer6_3,rdAnswer6_4,rdAnswer6_5;
        Button btnFinish;
        final LinearLayout rootLayout;

        txtTitle = finance_method.findViewById(R.id.txtTitle);
        txtQuestion1 = finance_method.findViewById(R.id.txtQuestion1);
        txtQuestion2 = finance_method.findViewById(R.id.txtQuestion2);
        txtQuestion3 = finance_method.findViewById(R.id.txtQuestion3);
        txtQuestion4 = finance_method.findViewById(R.id.txtQuestion4);
        txtQuestion5 = finance_method.findViewById(R.id.txtQuestion5);
        txtQuestion6 = finance_method.findViewById(R.id.txtQuestion6);

        rdAnswer1_1 = finance_method.findViewById(R.id.rdAnswer1_1);
        rdAnswer1_2 = finance_method.findViewById(R.id.rdAnswer1_2);
        rdAnswer1_3 = finance_method.findViewById(R.id.rdAnswer1_3);
        rdAnswer1_4 = finance_method.findViewById(R.id.rdAnswer1_4);
        rdAnswer1_5 = finance_method.findViewById(R.id.rdAnswer1_5);

        rdAnswer2_1 = finance_method.findViewById(R.id.rdAnswer2_1);
        rdAnswer2_2 = finance_method.findViewById(R.id.rdAnswer2_2);
        rdAnswer2_3 = finance_method.findViewById(R.id.rdAnswer2_3);
        rdAnswer2_4 = finance_method.findViewById(R.id.rdAnswer2_4);
        rdAnswer2_5 = finance_method.findViewById(R.id.rdAnswer2_5);

        rdAnswer3_1 = finance_method.findViewById(R.id.rdAnswer3_1);
        rdAnswer3_2 = finance_method.findViewById(R.id.rdAnswer3_2);
        rdAnswer3_3 = finance_method.findViewById(R.id.rdAnswer3_3);
        rdAnswer3_4 = finance_method.findViewById(R.id.rdAnswer3_4);
        rdAnswer3_5 = finance_method.findViewById(R.id.rdAnswer3_5);

        rdAnswer4_1 = finance_method.findViewById(R.id.rdAnswer4_1);
        rdAnswer4_2 = finance_method.findViewById(R.id.rdAnswer4_2);
        rdAnswer4_3 = finance_method.findViewById(R.id.rdAnswer4_3);
        rdAnswer4_4 = finance_method.findViewById(R.id.rdAnswer4_4);
        rdAnswer4_5 = finance_method.findViewById(R.id.rdAnswer4_5);

        rdAnswer5_1 = finance_method.findViewById(R.id.rdAnswer5_1);
        rdAnswer5_2 = finance_method.findViewById(R.id.rdAnswer5_2);
        rdAnswer5_3 = finance_method.findViewById(R.id.rdAnswer5_3);
        rdAnswer5_4 = finance_method.findViewById(R.id.rdAnswer5_4);
        rdAnswer5_5 = finance_method.findViewById(R.id.rdAnswer5_5);

        rdAnswer6_1 = finance_method.findViewById(R.id.rdAnswer6_1);
        rdAnswer6_2 = finance_method.findViewById(R.id.rdAnswer6_2);
        rdAnswer6_3 = finance_method.findViewById(R.id.rdAnswer6_3);
        rdAnswer6_4 = finance_method.findViewById(R.id.rdAnswer6_4);
        rdAnswer6_5 = finance_method.findViewById(R.id.rdAnswer6_5);

        btnFinish = finance_method.findViewById(R.id.btnFinish);

        rootLayout = finance_method.findViewById(R.id.rootLayout);

        txtTitle.setText(title);
        txtQuestion1.setText(question_1);
        txtQuestion2.setText(question_2);
        txtQuestion3.setText(question_3);
        txtQuestion4.setText(question_4);
        txtQuestion5.setText(question_5);
        txtQuestion6.setText(question_6);

        rdAnswer1_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answer_selected_1 = rdAnswer1_1.getText().toString();
            }
        });
        rdAnswer1_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answer_selected_1 = rdAnswer1_2.getText().toString();
            }
        });
        rdAnswer1_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answer_selected_1 = rdAnswer1_3.getText().toString();
            }
        });
        rdAnswer1_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answer_selected_1 = rdAnswer1_4.getText().toString();
            }
        });
        rdAnswer1_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answer_selected_1 = rdAnswer1_5.getText().toString();
            }
        });

        rdAnswer2_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answer_selected_2 = rdAnswer2_1.getText().toString();
            }
        });
        rdAnswer2_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answer_selected_2 = rdAnswer2_2.getText().toString();
            }
        });
        rdAnswer2_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answer_selected_2 = rdAnswer2_3.getText().toString();
            }
        });
        rdAnswer2_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answer_selected_2 = rdAnswer2_4.getText().toString();
            }
        });
        rdAnswer2_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answer_selected_2 = rdAnswer2_5.getText().toString();
            }
        });

        rdAnswer3_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answer_selected_3 = rdAnswer3_1.getText().toString();
            }
        });
        rdAnswer3_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answer_selected_3 = rdAnswer3_2.getText().toString();
            }
        });
        rdAnswer3_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answer_selected_3 = rdAnswer3_3.getText().toString();
            }
        });
        rdAnswer3_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answer_selected_3 = rdAnswer3_4.getText().toString();
            }
        });
        rdAnswer3_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answer_selected_3 = rdAnswer3_5.getText().toString();
            }
        });

        rdAnswer4_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answer_selected_4 = rdAnswer4_1.getText().toString();
            }
        });
        rdAnswer4_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answer_selected_4 = rdAnswer4_2.getText().toString();
            }
        });
        rdAnswer4_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answer_selected_4 = rdAnswer4_3.getText().toString();
            }
        });
        rdAnswer4_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answer_selected_4 = rdAnswer4_4.getText().toString();
            }
        });
        rdAnswer4_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answer_selected_4 = rdAnswer4_5.getText().toString();
            }
        });

        rdAnswer5_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answer_selected_5 = rdAnswer5_1.getText().toString();
            }
        });
        rdAnswer5_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answer_selected_5 = rdAnswer5_2.getText().toString();
            }
        });
        rdAnswer5_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answer_selected_5 = rdAnswer5_3.getText().toString();
            }
        });
        rdAnswer5_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answer_selected_5 = rdAnswer5_4.getText().toString();
            }
        });
        rdAnswer5_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answer_selected_5 = rdAnswer5_5.getText().toString();
            }
        });

        rdAnswer6_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answer_selected_6 = rdAnswer6_1.getText().toString();
            }
        });
        rdAnswer6_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answer_selected_6 = rdAnswer6_2.getText().toString();
            }
        });
        rdAnswer6_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answer_selected_6 = rdAnswer6_3.getText().toString();
            }
        });
        rdAnswer6_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answer_selected_6 = rdAnswer6_4.getText().toString();
            }
        });
        rdAnswer6_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answer_selected_6 = rdAnswer6_5.getText().toString();
            }
        });

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!rdAnswer1_1.isChecked() && !rdAnswer1_2.isChecked() && !rdAnswer1_3.isChecked() && !rdAnswer1_4.isChecked() && !rdAnswer1_5.isChecked()) {
                    Snackbar.make(rootLayout,"Debes seleccionar una alternativa", Snackbar.LENGTH_SHORT).show();
                }
                else  if (!rdAnswer2_1.isChecked() && !rdAnswer2_2.isChecked() && !rdAnswer2_3.isChecked() && !rdAnswer2_4.isChecked() && !rdAnswer2_5.isChecked()) {
                    Snackbar.make(rootLayout,"Debes seleccionar una alternativa", Snackbar.LENGTH_SHORT).show();
                }
                else  if (!rdAnswer3_1.isChecked() && !rdAnswer3_2.isChecked() && !rdAnswer3_3.isChecked() && !rdAnswer3_4.isChecked() && !rdAnswer3_5.isChecked()) {
                    Snackbar.make(rootLayout,"Debes seleccionar una alternativa", Snackbar.LENGTH_SHORT).show();
                }
                else  if (!rdAnswer4_1.isChecked() && !rdAnswer4_2.isChecked() && !rdAnswer4_3.isChecked() && !rdAnswer4_4.isChecked() && !rdAnswer4_5.isChecked()) {
                    Snackbar.make(rootLayout,"Debes seleccionar una alternativa", Snackbar.LENGTH_SHORT).show();
                }
                else  if (!rdAnswer5_1.isChecked() && !rdAnswer5_2.isChecked() && !rdAnswer5_3.isChecked() && !rdAnswer5_4.isChecked() && !rdAnswer5_5.isChecked()) {
                    Snackbar.make(rootLayout,"Debes seleccionar una alternativa", Snackbar.LENGTH_SHORT).show();
                } else if (!rdAnswer6_1.isChecked() && !rdAnswer6_2.isChecked() && !rdAnswer6_3.isChecked() && !rdAnswer6_4.isChecked() && !rdAnswer6_5.isChecked()) {
                    Snackbar.make(rootLayout, "Debes seleccionar una alternativa", Snackbar.LENGTH_SHORT).show();
                } else {
                    companyRef.child(post_key).child("Industry Profile").child(path).child("answer_1").setValue(answer_selected_1);
                    companyRef.child(post_key).child("Industry Profile").child(path).child("answer_2").setValue(answer_selected_2);
                    companyRef.child(post_key).child("Industry Profile").child(path).child("answer_3").setValue(answer_selected_3);
                    companyRef.child(post_key).child("Industry Profile").child(path).child("answer_4").setValue(answer_selected_4);
                    companyRef.child(post_key).child("Industry Profile").child(path).child("answer_5").setValue(answer_selected_5);
                    companyRef.child(post_key).child("Industry Profile").child(path).child("answer_6").setValue(answer_selected_6);
                    Toasty.success(getActivity(), "Actualizado", Toast.LENGTH_LONG).show();
                    dialog.dismiss();

                }
            }
        });

        dialog.setView(finance_method);
        dialog.show();
    }
}
