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

    TextView txtCorrect,txtIncorrect;
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

        post_key = getActivity().getIntent().getExtras().getString("post_key");
        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");

        companyRef.child(post_key).child("Lesson Learned Evaluation").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild("question_1")) {
                    question_1 = dataSnapshot.child("question_1").getValue(Integer.class);
                }
                if (dataSnapshot.hasChild("question_2")) {
                    question_2 = dataSnapshot.child("question_2").getValue(Integer.class);
                }
                if (dataSnapshot.hasChild("question_3")) {
                    question_3 = dataSnapshot.child("question_3").getValue(Integer.class);
                }
                if (dataSnapshot.hasChild("question_4")) {
                    question_4 = dataSnapshot.child("question_4").getValue(Integer.class);
                }
                if (dataSnapshot.hasChild("question_5")) {
                    question_5 = dataSnapshot.child("question_5").getValue(Integer.class);
                }
                if (dataSnapshot.hasChild("question_6")) {
                    question_6 = dataSnapshot.child("question_6").getValue(Integer.class);
                }
                if (dataSnapshot.hasChild("question_7")) {
                    question_7 = dataSnapshot.child("question_7").getValue(Integer.class);
                }
                if (dataSnapshot.hasChild("question_8")) {
                    question_8 = dataSnapshot.child("question_8").getValue(Integer.class);
                }
                if (dataSnapshot.hasChild("question_9")) {
                    question_9 = dataSnapshot.child("question_9").getValue(Integer.class);
                }
                if (dataSnapshot.hasChild("question_10")) {
                    question_10 = dataSnapshot.child("question_10").getValue(Integer.class);
                }
                if (dataSnapshot.hasChild("question_11")) {
                    question_11 = dataSnapshot.child("question_11").getValue(Integer.class);
                }
                if (dataSnapshot.hasChild("question_12")) {
                    question_12 = dataSnapshot.child("question_12").getValue(Integer.class);
                }
                if (dataSnapshot.hasChild("question_13")) {
                    question_13 = dataSnapshot.child("question_13").getValue(Integer.class);
                }
                if (dataSnapshot.hasChild("question_14")) {
                    question_14 = dataSnapshot.child("question_14").getValue(Integer.class);
                }
                if (dataSnapshot.hasChild("question_15")) {
                    question_15 = dataSnapshot.child("question_15").getValue(Integer.class);
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
