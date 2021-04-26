package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.ManagementControlAndReports.LessonLearned;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;


public class LessonLearnedResultsFragment extends Fragment {

    TextView txtCorrect,txtIncorrect,txtQuestion1,txtQuestion2,txtQuestion3,txtQuestion4,txtQuestion5,txtQuestion6,txtQuestion7,txtQuestion8,txtQuestion9,txtQuestion10,txtQuestion11,txtQuestion12,txtQuestion13,txtQuestion14,txtQuestion15;
    Button btnFinish;
    String post_key;
    DatabaseReference companyRef;
    int question_1,question_2,question_3,question_4,question_5,question_6,question_7,question_8,question_9,question_10,question_11,question_12,question_13,question_14,question_15,correct,incorrect;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lesson_learned_results, container, false);

        txtCorrect = view.findViewById(R.id.txtCorrect);
        txtIncorrect = view.findViewById(R.id.txtIncorrect);
        btnFinish = view.findViewById(R.id.btnFinish);

        txtQuestion1 = view.findViewById(R.id.txtQuestion1);
        txtQuestion2 = view.findViewById(R.id.txtQuestion2);
        txtQuestion3 = view.findViewById(R.id.txtQuestion3);
        txtQuestion4 = view.findViewById(R.id.txtQuestion4);
        txtQuestion5 = view.findViewById(R.id.txtQuestion5);
        txtQuestion6 = view.findViewById(R.id.txtQuestion6);
        txtQuestion7 = view.findViewById(R.id.txtQuestion7);
        txtQuestion8 = view.findViewById(R.id.txtQuestion8);
        txtQuestion9 = view.findViewById(R.id.txtQuestion9);
        txtQuestion10 = view.findViewById(R.id.txtQuestion10);
        txtQuestion11 = view.findViewById(R.id.txtQuestion11);
        txtQuestion12 = view.findViewById(R.id.txtQuestion12);
        txtQuestion13 = view.findViewById(R.id.txtQuestion13);
        txtQuestion14 = view.findViewById(R.id.txtQuestion14);
        txtQuestion15 = view.findViewById(R.id.txtQuestion15);

        post_key = getActivity().getIntent().getExtras().getString("post_key");
        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");

        companyRef.child(post_key).child("Lesson Learned Evaluation").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild("question_1")) {
                    question_1 = dataSnapshot.child("question_1").getValue(Integer.class);
                    if (question_1 == 0) {
                        txtQuestion1.setTextColor(getResources().getColor(R.color.redColor));
                        txtQuestion1.setText("Respuesta Incorrecta");
                    } else if (question_1 == 1) {
                        txtQuestion1.setTextColor(getResources().getColor(R.color.greenColor));
                        txtQuestion1.setText("Respuesta Correcta");
                    }
                }
                if (dataSnapshot.hasChild("question_2")) {
                    question_2 = dataSnapshot.child("question_2").getValue(Integer.class);
                    if (question_2 == 0) {
                        txtQuestion2.setTextColor(getResources().getColor(R.color.redColor));
                        txtQuestion2.setText("Respuesta Incorrecta");
                    } else if (question_2 == 1) {
                        txtQuestion2.setTextColor(getResources().getColor(R.color.greenColor));
                        txtQuestion2.setText("Respuesta Correcta");
                    }
                }
                if (dataSnapshot.hasChild("question_3")) {
                    question_3 = dataSnapshot.child("question_3").getValue(Integer.class);
                    if (question_3 == 0) {
                        txtQuestion3.setTextColor(getResources().getColor(R.color.redColor));
                        txtQuestion3.setText("Respuesta Incorrecta");
                    } else if (question_3 == 1) {
                        txtQuestion3.setTextColor(getResources().getColor(R.color.greenColor));
                        txtQuestion3.setText("Respuesta Correcta");
                    }
                }
                if (dataSnapshot.hasChild("question_4")) {
                    question_4 = dataSnapshot.child("question_4").getValue(Integer.class);
                    if (question_4 == 0) {
                        txtQuestion4.setTextColor(getResources().getColor(R.color.redColor));
                        txtQuestion4.setText("Respuesta Incorrecta");
                    } else if (question_4 == 1) {
                        txtQuestion4.setTextColor(getResources().getColor(R.color.greenColor));
                        txtQuestion4.setText("Respuesta Correcta");
                    }
                }
                if (dataSnapshot.hasChild("question_5")) {
                    question_5 = dataSnapshot.child("question_5").getValue(Integer.class);
                    if (question_5 == 0) {
                        txtQuestion5.setTextColor(getResources().getColor(R.color.redColor));
                        txtQuestion5.setText("Respuesta Incorrecta");
                    } else if (question_5 == 1) {
                        txtQuestion5.setTextColor(getResources().getColor(R.color.greenColor));
                        txtQuestion5.setText("Respuesta Correcta");
                    }
                }
                if (dataSnapshot.hasChild("question_6")) {
                    question_6 = dataSnapshot.child("question_6").getValue(Integer.class);
                    if (question_6 == 0) {
                        txtQuestion6.setTextColor(getResources().getColor(R.color.redColor));
                        txtQuestion6.setText("Respuesta Incorrecta");
                    } else if (question_6 == 1) {
                        txtQuestion6.setTextColor(getResources().getColor(R.color.greenColor));
                        txtQuestion6.setText("Respuesta Correcta");
                    }
                }
                if (dataSnapshot.hasChild("question_7")) {
                    question_7 = dataSnapshot.child("question_7").getValue(Integer.class);
                    if (question_7 == 0) {
                        txtQuestion7.setTextColor(getResources().getColor(R.color.redColor));
                        txtQuestion7.setText("Respuesta Incorrecta");
                    } else if (question_7 == 1) {
                        txtQuestion7.setTextColor(getResources().getColor(R.color.greenColor));
                        txtQuestion7.setText("Respuesta Correcta");
                    }
                }
                if (dataSnapshot.hasChild("question_8")) {
                    question_8 = dataSnapshot.child("question_8").getValue(Integer.class);
                    if (question_8 == 0) {
                        txtQuestion8.setTextColor(getResources().getColor(R.color.redColor));
                        txtQuestion8.setText("Respuesta Incorrecta");
                    } else if (question_8 == 1) {
                        txtQuestion8.setTextColor(getResources().getColor(R.color.greenColor));
                        txtQuestion8.setText("Respuesta Correcta");
                    }
                }
                if (dataSnapshot.hasChild("question_9")) {
                    question_9 = dataSnapshot.child("question_9").getValue(Integer.class);
                    if (question_9 == 0) {
                        txtQuestion9.setTextColor(getResources().getColor(R.color.redColor));
                        txtQuestion9.setText("Respuesta Incorrecta");
                    } else if (question_9 == 1) {
                        txtQuestion9.setTextColor(getResources().getColor(R.color.greenColor));
                        txtQuestion9.setText("Respuesta Correcta");
                    }
                }
                if (dataSnapshot.hasChild("question_10")) {
                    question_10 = dataSnapshot.child("question_10").getValue(Integer.class);
                    if (question_10 == 0) {
                        txtQuestion10.setTextColor(getResources().getColor(R.color.redColor));
                        txtQuestion10.setText("Respuesta Incorrecta");
                    } else if (question_10 == 1) {
                        txtQuestion10.setTextColor(getResources().getColor(R.color.greenColor));
                        txtQuestion10.setText("Respuesta Correcta");
                    }
                }
                if (dataSnapshot.hasChild("question_11")) {
                    question_11 = dataSnapshot.child("question_11").getValue(Integer.class);
                    if (question_11 == 0) {
                        txtQuestion11.setTextColor(getResources().getColor(R.color.redColor));
                        txtQuestion11.setText("Respuesta Incorrecta");
                    } else if (question_11 == 1) {
                        txtQuestion11.setTextColor(getResources().getColor(R.color.greenColor));
                        txtQuestion11.setText("Respuesta Correcta");
                    }
                }
                if (dataSnapshot.hasChild("question_12")) {
                    question_12 = dataSnapshot.child("question_12").getValue(Integer.class);
                    if (question_12 == 0) {
                        txtQuestion12.setTextColor(getResources().getColor(R.color.redColor));
                        txtQuestion12.setText("Respuesta Incorrecta");
                    } else if (question_12 == 1) {
                        txtQuestion12.setTextColor(getResources().getColor(R.color.greenColor));
                        txtQuestion12.setText("Respuesta Correcta");
                    }
                }
                if (dataSnapshot.hasChild("question_13")) {
                    question_13 = dataSnapshot.child("question_13").getValue(Integer.class);
                    if (question_13 == 0) {
                        txtQuestion13.setTextColor(getResources().getColor(R.color.redColor));
                        txtQuestion13.setText("Respuesta Incorrecta");
                    } else if (question_13 == 1) {
                        txtQuestion13.setTextColor(getResources().getColor(R.color.greenColor));
                        txtQuestion13.setText("Respuesta Correcta");
                    }
                }
                if (dataSnapshot.hasChild("question_14")) {
                    question_14 = dataSnapshot.child("question_14").getValue(Integer.class);
                    if (question_14 == 0) {
                        txtQuestion14.setTextColor(getResources().getColor(R.color.redColor));
                        txtQuestion14.setText("Respuesta Incorrecta");
                    } else if (question_14 == 1) {
                        txtQuestion14.setTextColor(getResources().getColor(R.color.greenColor));
                        txtQuestion14.setText("Respuesta Correcta");
                    }
                }
                if (dataSnapshot.hasChild("question_15")) {
                    question_15 = dataSnapshot.child("question_15").getValue(Integer.class);
                    if (question_15 == 0) {
                        txtQuestion15.setTextColor(getResources().getColor(R.color.redColor));
                        txtQuestion15.setText("Respuesta Incorrecta");
                    } else if (question_15 == 1) {
                        txtQuestion15.setTextColor(getResources().getColor(R.color.greenColor));
                        txtQuestion15.setText("Respuesta Correcta");
                    }
                }

                correct = question_1+question_2+question_3+question_4+question_5+question_6+question_7+question_8+question_9+question_10+question_11+question_12+question_13+question_14+question_15;
                incorrect = 15-correct;

                txtCorrect.setText("Respuestas Correctas: "+correct);
                txtIncorrect.setText("Respuestas Incorrectas: "+incorrect);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                companyRef.child(post_key).child("Lesson Learned Evaluation").removeValue();
            }
        });

        return view;
    }
}
