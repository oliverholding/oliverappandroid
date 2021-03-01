package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.StrategicDirection.IndustryInformation;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;


public class SumaryIndustryProfileFragment extends Fragment {

    TextView txtAns1,txtAns2,txtAns3,txtAns4,txtAns5,txtAns6;
    DatabaseReference companyRef;
    String post_key,answer_selected_1,answer_selected_2,answer_selected_3,answer_selected_4,answer_selected_5,answer_selected_6;
    int answer1,answer2,answer3,answer4,answer5,answer6,answer21,answer22,answer23,answer24,answer25,answer26,answer31,answer32,answer33,answer34,answer35,answer36,answer41,answer42,answer43,answer44,answer45,answer46,answer51,answer52,answer53,answer54,answer55,answer56,average_1,average_2,average_3,average_4,average_5;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sumary_industry_profile, container, false);

        post_key = getActivity().getIntent().getExtras().getString("post_key");
        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");

        txtAns1 = view.findViewById(R.id.txtAns1);
        txtAns2 = view.findViewById(R.id.txtAns2);
        txtAns3 = view.findViewById(R.id.txtAns3);
        txtAns4 = view.findViewById(R.id.txtAns4);
        txtAns5 = view.findViewById(R.id.txtAns5);
        txtAns6 = view.findViewById(R.id.txtAns6);

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
                    txtAns1.setText("Nada Atractivo");

                }
                if (average_1 == 2) {
                    txtAns1.setText("Poco Atractivo");

                }
                if (average_1 == 3) {
                    txtAns1.setText("Neutral");

                }
                if (average_1 == 4) {
                    txtAns1.setText("Atractivo");

                }
                if (average_1 == 5) {
                    txtAns1.setText("Muy Atractivo");

                }

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
                            txtAns2.setText("Nada Atractivo");

                        }
                        if (average_2 == 2) {
                            txtAns2.setText("Poco Atractivo");

                        }
                        if (average_2 == 3) {
                            txtAns1.setText("Neutral");

                        }
                        if (average_2 == 4) {
                            txtAns2.setText("Atractivo");

                        }
                        if (average_2 == 5) {
                            txtAns2.setText("Muy Atractivo");

                        }

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
                                    txtAns3.setText("Nada Atractivo");

                                }
                                if (average_3 == 2) {
                                    txtAns3.setText("Poco Atractivo");

                                }
                                if (average_3 == 3) {
                                    txtAns3.setText("Neutral");

                                }
                                if (average_3 == 4) {
                                    txtAns3.setText("Atractivo");

                                }
                                if (average_3 == 5) {
                                    txtAns3.setText("Muy Atractivo");

                                }

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
                                            txtAns4.setText("Nada Atractivo");

                                        }
                                        if (average_4 == 2) {
                                            txtAns4.setText("Poco Atractivo");

                                        }
                                        if (average_4 == 3) {
                                            txtAns4.setText("Neutral");

                                        }
                                        if (average_4 == 4) {
                                            txtAns4.setText("Atractivo");

                                        }
                                        if (average_4 == 5) {
                                            txtAns4.setText("Muy Atractivo");

                                        }

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
                                                    txtAns5.setText("Nada Atractivo");

                                                }
                                                if (average_5 == 2) {
                                                    txtAns5.setText("Poco Atractivo");

                                                }
                                                if (average_5 == 3) {
                                                    txtAns5.setText("Neutral");

                                                }
                                                if (average_5 == 4) {
                                                    txtAns5.setText("Atractivo");

                                                }
                                                if (average_5 == 5) {
                                                    txtAns5.setText("Muy Atractivo");

                                                }

                                                int total_average= (average_1+average_2+average_3+average_4+average_5)/5;

                                                if (total_average == 1) {
                                                    txtAns6.setText("Nada Atractivo");

                                                }
                                                if (total_average == 2) {
                                                    txtAns6.setText("Poco Atractivo");

                                                }
                                                if (total_average == 3) {
                                                    txtAns6.setText("Neutral");

                                                }
                                                if (total_average == 4) {
                                                    txtAns6.setText("Atractivo");

                                                }
                                                if (total_average == 5) {
                                                    txtAns6.setText("Muy Atractivo");

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



        return view;
    }
}
