package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.PeopleManagement.Evaluation360;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;
import com.xw.repo.BubbleSeekBar;

import java.text.DecimalFormat;
import java.util.Map;

public class ResultsEvaluationFragment extends Fragment {

    BubbleSeekBar evaluation1SeekBar,evaluation2SeekBar,evaluation3SeekBar,evaluation4SeekBar;
    TextView txtQualification1,txtQualification2,txtQualification3,txtQualification4,txtScore1,txtScore2,txtScore3,txtScore4;
    String post_key,profile_id;
    DatabaseReference companyRef;
    double sum1,sum2,sum3,points_db_1,points_db_2,points_db_3;
    DecimalFormat decimalFormat;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_results_evaluation, container, false);

        evaluation1SeekBar = view.findViewById(R.id.evaluation1SeekBar);
        evaluation2SeekBar = view.findViewById(R.id.evaluation2SeekBar);
        evaluation3SeekBar = view.findViewById(R.id.evaluation3SeekBar);
        evaluation4SeekBar = view.findViewById(R.id.evaluation4SeekBar);
        txtQualification1 = view.findViewById(R.id.txtQualification1);
        txtQualification2 = view.findViewById(R.id.txtQualification2);
        txtQualification3 = view.findViewById(R.id.txtQualification3);
        txtQualification4 = view.findViewById(R.id.txtQualification4);
        txtScore1 = view.findViewById(R.id.txtScore1);
        txtScore2 = view.findViewById(R.id.txtScore2);
        txtScore3 = view.findViewById(R.id.txtScore3);
        txtScore4 = view.findViewById(R.id.txtScore4);

        evaluation1SeekBar.setEnabled(false);
        evaluation2SeekBar.setEnabled(false);
        evaluation3SeekBar.setEnabled(false);
        evaluation4SeekBar.setEnabled(false);

        decimalFormat = new DecimalFormat("0.00");

        post_key = getActivity().getIntent().getExtras().getString("post_key");
        profile_id = getActivity().getIntent().getExtras().getString("profile_id");
        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");

        companyRef.child(post_key).child("Job Profile").child(profile_id).child("Evaluation").child("Partner").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    sum1 = 0.00;
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        Map<Double, Object> map = (Map<Double, Object>) ds.getValue();
                        Object score = map.get("score");
                        double d = Double.parseDouble(String.valueOf(score));
                        sum1 += d;

                        points_db_1 = sum1/20;
                        String points = decimalFormat.format(points_db_1);
                        txtScore3.setText(points);

                        evaluation3SeekBar.setProgress((float) points_db_1);

                        if (points_db_1 >= 0 && points_db_1 <= 0.9) {
                            txtQualification3.setText("Deficiente");
                        }
                        if (points_db_1 >= 1 && points_db_1 <= 1.9) {
                            txtQualification3.setText("Regular");
                        }
                        if (points_db_1 >= 2 && points_db_1 <= 2.9) {
                            txtQualification3.setText("Bueno");
                        }
                        if (points_db_1 >= 3 && points_db_1 <= 3.9) {
                            txtQualification3.setText("Muy Bueno");
                        }
                        if (points_db_1 >= 4) {
                            txtQualification3.setText("Excelente");
                        }

                    }
                }

                companyRef.child(post_key).child("Job Profile").child(profile_id).child("Evaluation").child("Leader").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            sum2 = 0.00;
                            for (DataSnapshot ds : dataSnapshot.getChildren()) {
                                Map<Double, Object> map = (Map<Double, Object>) ds.getValue();
                                Object score = map.get("score");
                                double d = Double.parseDouble(String.valueOf(score));
                                sum2 += d;

                                points_db_2 = sum2/20;
                                String points = decimalFormat.format(points_db_2);
                                txtScore2.setText(points);

                                evaluation2SeekBar.setProgress((float) points_db_2);

                                if (points_db_2 >= 0 && points_db_2 <= 0.9) {
                                    txtQualification2.setText("Deficiente");
                                }
                                if (points_db_2 >= 1 && points_db_2 <= 1.9) {
                                    txtQualification2.setText("Regular");
                                }
                                if (points_db_2 >= 2 && points_db_2 <= 2.9) {
                                    txtQualification2.setText("Bueno");
                                }
                                if (points_db_2 >= 3 && points_db_2 <= 3.9) {
                                    txtQualification2.setText("Muy Bueno");
                                }
                                if (points_db_2 >= 4) {
                                    txtQualification2.setText("Excelente");
                                }
                            }
                        }

                        companyRef.child(post_key).child("Job Profile").child(profile_id).child("Evaluation").child("Manager").addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                if (dataSnapshot.exists()) {
                                    sum3 = 0.00;
                                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                                        Map<Double, Object> map = (Map<Double, Object>) ds.getValue();
                                        Object score = map.get("score");
                                        double d = Double.parseDouble(String.valueOf(score));
                                        sum3 += d;

                                        points_db_3 = sum3/20;
                                        String points = decimalFormat.format(points_db_3);
                                        txtScore1.setText(points);

                                        evaluation1SeekBar.setProgress((float) points_db_3);

                                        if (points_db_3 >= 0 && points_db_3 <= 0.9) {
                                            txtQualification1.setText("Deficiente");
                                        }
                                        if (points_db_3 >= 1 && points_db_3 <= 1.9) {
                                            txtQualification1.setText("Regular");
                                        }
                                        if (points_db_3 >= 2 && points_db_3 <= 2.9) {
                                            txtQualification1.setText("Bueno");
                                        }
                                        if (points_db_3 >= 3 && points_db_3 <= 3.9) {
                                            txtQualification1.setText("Muy Bueno");
                                        }
                                        if (points_db_3 >= 4) {
                                            txtQualification1.setText("Excelente");
                                        }
                                    }

                                    double average = (points_db_1+points_db_2+points_db_3)/3;
                                    String average_st = decimalFormat.format(average);
                                    txtScore4.setText(average_st);
                                    evaluation4SeekBar.setProgress((float) average);

                                    if (average >= 0 && average <= 0.9) {
                                        txtQualification4.setText("Deficiente");
                                    }
                                    if (average >= 1 && average <= 1.9) {
                                        txtQualification4.setText("Regular");
                                    }
                                    if (average >= 2 && average <= 2.9) {
                                        txtQualification4.setText("Bueno");
                                    }
                                    if (average >= 3 && average <= 3.9) {
                                        txtQualification4.setText("Muy Bueno");
                                    }
                                    if (average >= 4) {
                                        txtQualification4.setText("Excelente");
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

        return view;
    }
}
