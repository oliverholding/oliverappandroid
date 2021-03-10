package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.PeopleManagement.Evaluation360;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hsalf.smilerating.SmileRating;
import com.hsalf.smileyrating.SmileyRating;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import es.dmoral.toasty.Toasty;


public class Evaluation1Fragment extends Fragment {

    String post_key,profile_id,date_last_update,time_last_update;
    DatabaseReference companyRef;
    TextView txtLastEvaluation;
    SmileyRating smile_rating1,smile_rating2,smile_rating3,smile_rating4,smile_rating5,smile_rating6,smile_rating7,smile_rating8,smile_rating9,smile_rating10,
            smile_rating11,smile_rating12,smile_rating13,smile_rating14,smile_rating15,smile_rating16,smile_rating17,smile_rating18,smile_rating19,smile_rating20;
    Button btnFinish;
    RelativeLayout rootLayout;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_evaluation1, container, false);

        post_key = getActivity().getIntent().getExtras().getString("post_key");
        profile_id = getActivity().getIntent().getExtras().getString("profile_id");
        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");

        txtLastEvaluation = view.findViewById(R.id.txtLastEvaluation);
        rootLayout = view.findViewById(R.id.rootLayout);

        companyRef.child(post_key).child("Job Profile").child(profile_id).child("Evaluation").child("Manager Update").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    if (dataSnapshot.hasChild("date_last_update")&& dataSnapshot.hasChild("time_last_update")) {
                        date_last_update = dataSnapshot.child("date_last_update").getValue().toString();
                        time_last_update = dataSnapshot.child("time_last_update").getValue().toString();
                        txtLastEvaluation.setText("Última Evaluación: "+date_last_update+" a las "+time_last_update);
                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        smile_rating1 = view.findViewById(R.id.smile_rating1);
        smile_rating2 = view.findViewById(R.id.smile_rating2);
        smile_rating3 = view.findViewById(R.id.smile_rating3);
        smile_rating4 = view.findViewById(R.id.smile_rating4);
        smile_rating5 = view.findViewById(R.id.smile_rating5);
        smile_rating6 = view.findViewById(R.id.smile_rating6);
        smile_rating7 = view.findViewById(R.id.smile_rating7);
        smile_rating8 = view.findViewById(R.id.smile_rating8);
        smile_rating9 = view.findViewById(R.id.smile_rating9);
        smile_rating10 = view.findViewById(R.id.smile_rating10);
        smile_rating11 = view.findViewById(R.id.smile_rating11);
        smile_rating12 = view.findViewById(R.id.smile_rating12);
        smile_rating13 = view.findViewById(R.id.smile_rating13);
        smile_rating14 = view.findViewById(R.id.smile_rating14);
        smile_rating15 = view.findViewById(R.id.smile_rating15);
        smile_rating16 = view.findViewById(R.id.smile_rating16);
        smile_rating17 = view.findViewById(R.id.smile_rating17);
        smile_rating18 = view.findViewById(R.id.smile_rating18);
        smile_rating19 = view.findViewById(R.id.smile_rating19);
        smile_rating20 = view.findViewById(R.id.smile_rating20);

        btnFinish = view.findViewById(R.id.btnFinish);

        smile_rating1.setTitle(SmileyRating.Type.TERRIBLE,"Deficiente");
        smile_rating1.setTitle(SmileyRating.Type.BAD,"Regular");
        smile_rating1.setTitle(SmileyRating.Type.OKAY,"Bueno");
        smile_rating1.setTitle(SmileyRating.Type.GOOD,"Muy Bueno");
        smile_rating1.setTitle(SmileyRating.Type.GREAT,"Excelente");

        smile_rating2.setTitle(SmileyRating.Type.TERRIBLE,"Deficiente");
        smile_rating2.setTitle(SmileyRating.Type.BAD,"Regular");
        smile_rating2.setTitle(SmileyRating.Type.OKAY,"Bueno");
        smile_rating2.setTitle(SmileyRating.Type.GOOD,"Muy Bueno");
        smile_rating2.setTitle(SmileyRating.Type.GREAT,"Excelente");

        smile_rating3.setTitle(SmileyRating.Type.TERRIBLE,"Deficiente");
        smile_rating3.setTitle(SmileyRating.Type.BAD,"Regular");
        smile_rating3.setTitle(SmileyRating.Type.OKAY,"Bueno");
        smile_rating3.setTitle(SmileyRating.Type.GOOD,"Muy Bueno");
        smile_rating3.setTitle(SmileyRating.Type.GREAT,"Excelente");

        smile_rating4.setTitle(SmileyRating.Type.TERRIBLE,"Deficiente");
        smile_rating4.setTitle(SmileyRating.Type.BAD,"Regular");
        smile_rating4.setTitle(SmileyRating.Type.OKAY,"Bueno");
        smile_rating4.setTitle(SmileyRating.Type.GOOD,"Muy Bueno");
        smile_rating4.setTitle(SmileyRating.Type.GREAT,"Excelente");

        smile_rating5.setTitle(SmileyRating.Type.TERRIBLE,"Deficiente");
        smile_rating5.setTitle(SmileyRating.Type.BAD,"Regular");
        smile_rating5.setTitle(SmileyRating.Type.OKAY,"Bueno");
        smile_rating5.setTitle(SmileyRating.Type.GOOD,"Muy Bueno");
        smile_rating5.setTitle(SmileyRating.Type.GREAT,"Excelente");

        smile_rating6.setTitle(SmileyRating.Type.TERRIBLE,"Deficiente");
        smile_rating6.setTitle(SmileyRating.Type.BAD,"Regular");
        smile_rating6.setTitle(SmileyRating.Type.OKAY,"Bueno");
        smile_rating6.setTitle(SmileyRating.Type.GOOD,"Muy Bueno");
        smile_rating6.setTitle(SmileyRating.Type.GREAT,"Excelente");

        smile_rating7.setTitle(SmileyRating.Type.TERRIBLE,"Deficiente");
        smile_rating7.setTitle(SmileyRating.Type.BAD,"Regular");
        smile_rating7.setTitle(SmileyRating.Type.OKAY,"Bueno");
        smile_rating7.setTitle(SmileyRating.Type.GOOD,"Muy Bueno");
        smile_rating7.setTitle(SmileyRating.Type.GREAT,"Excelente");

        smile_rating8.setTitle(SmileyRating.Type.TERRIBLE,"Deficiente");
        smile_rating8.setTitle(SmileyRating.Type.BAD,"Regular");
        smile_rating8.setTitle(SmileyRating.Type.OKAY,"Bueno");
        smile_rating8.setTitle(SmileyRating.Type.GOOD,"Muy Bueno");
        smile_rating8.setTitle(SmileyRating.Type.GREAT,"Excelente");

        smile_rating9.setTitle(SmileyRating.Type.TERRIBLE,"Deficiente");
        smile_rating9.setTitle(SmileyRating.Type.BAD,"Regular");
        smile_rating9.setTitle(SmileyRating.Type.OKAY,"Bueno");
        smile_rating9.setTitle(SmileyRating.Type.GOOD,"Muy Bueno");
        smile_rating9.setTitle(SmileyRating.Type.GREAT,"Excelente");

        smile_rating10.setTitle(SmileyRating.Type.TERRIBLE,"Deficiente");
        smile_rating10.setTitle(SmileyRating.Type.BAD,"Regular");
        smile_rating10.setTitle(SmileyRating.Type.OKAY,"Bueno");
        smile_rating10.setTitle(SmileyRating.Type.GOOD,"Muy Bueno");
        smile_rating10.setTitle(SmileyRating.Type.GREAT,"Excelente");

        smile_rating11.setTitle(SmileyRating.Type.TERRIBLE,"Deficiente");
        smile_rating11.setTitle(SmileyRating.Type.BAD,"Regular");
        smile_rating11.setTitle(SmileyRating.Type.OKAY,"Bueno");
        smile_rating11.setTitle(SmileyRating.Type.GOOD,"Muy Bueno");
        smile_rating11.setTitle(SmileyRating.Type.GREAT,"Excelente");

        smile_rating12.setTitle(SmileyRating.Type.TERRIBLE,"Deficiente");
        smile_rating12.setTitle(SmileyRating.Type.BAD,"Regular");
        smile_rating12.setTitle(SmileyRating.Type.OKAY,"Bueno");
        smile_rating12.setTitle(SmileyRating.Type.GOOD,"Muy Bueno");
        smile_rating12.setTitle(SmileyRating.Type.GREAT,"Excelente");

        smile_rating13.setTitle(SmileyRating.Type.TERRIBLE,"Deficiente");
        smile_rating13.setTitle(SmileyRating.Type.BAD,"Regular");
        smile_rating13.setTitle(SmileyRating.Type.OKAY,"Bueno");
        smile_rating13.setTitle(SmileyRating.Type.GOOD,"Muy Bueno");
        smile_rating13.setTitle(SmileyRating.Type.GREAT,"Excelente");

        smile_rating14.setTitle(SmileyRating.Type.TERRIBLE,"Deficiente");
        smile_rating14.setTitle(SmileyRating.Type.BAD,"Regular");
        smile_rating14.setTitle(SmileyRating.Type.OKAY,"Bueno");
        smile_rating14.setTitle(SmileyRating.Type.GOOD,"Muy Bueno");
        smile_rating14.setTitle(SmileyRating.Type.GREAT,"Excelente");

        smile_rating15.setTitle(SmileyRating.Type.TERRIBLE,"Deficiente");
        smile_rating15.setTitle(SmileyRating.Type.BAD,"Regular");
        smile_rating15.setTitle(SmileyRating.Type.OKAY,"Bueno");
        smile_rating15.setTitle(SmileyRating.Type.GOOD,"Muy Bueno");
        smile_rating15.setTitle(SmileyRating.Type.GREAT,"Excelente");

        smile_rating16.setTitle(SmileyRating.Type.TERRIBLE,"Deficiente");
        smile_rating16.setTitle(SmileyRating.Type.BAD,"Regular");
        smile_rating16.setTitle(SmileyRating.Type.OKAY,"Bueno");
        smile_rating16.setTitle(SmileyRating.Type.GOOD,"Muy Bueno");
        smile_rating16.setTitle(SmileyRating.Type.GREAT,"Excelente");

        smile_rating17.setTitle(SmileyRating.Type.TERRIBLE,"Deficiente");
        smile_rating17.setTitle(SmileyRating.Type.BAD,"Regular");
        smile_rating17.setTitle(SmileyRating.Type.OKAY,"Bueno");
        smile_rating17.setTitle(SmileyRating.Type.GOOD,"Muy Bueno");
        smile_rating17.setTitle(SmileyRating.Type.GREAT,"Excelente");

        smile_rating18.setTitle(SmileyRating.Type.TERRIBLE,"Deficiente");
        smile_rating18.setTitle(SmileyRating.Type.BAD,"Regular");
        smile_rating18.setTitle(SmileyRating.Type.OKAY,"Bueno");
        smile_rating18.setTitle(SmileyRating.Type.GOOD,"Muy Bueno");
        smile_rating18.setTitle(SmileyRating.Type.GREAT,"Excelente");

        smile_rating19.setTitle(SmileyRating.Type.TERRIBLE,"Deficiente");
        smile_rating19.setTitle(SmileyRating.Type.BAD,"Regular");
        smile_rating19.setTitle(SmileyRating.Type.OKAY,"Bueno");
        smile_rating19.setTitle(SmileyRating.Type.GOOD,"Muy Bueno");
        smile_rating19.setTitle(SmileyRating.Type.GREAT,"Excelente");

        smile_rating20.setTitle(SmileyRating.Type.TERRIBLE,"Deficiente");
        smile_rating20.setTitle(SmileyRating.Type.BAD,"Regular");
        smile_rating20.setTitle(SmileyRating.Type.OKAY,"Bueno");
        smile_rating20.setTitle(SmileyRating.Type.GOOD,"Muy Bueno");
        smile_rating20.setTitle(SmileyRating.Type.GREAT,"Excelente");

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int rating1 = smile_rating1.getSelectedSmiley().getRating();
                int rating2 = smile_rating2.getSelectedSmiley().getRating();
                int rating3 = smile_rating3.getSelectedSmiley().getRating();
                int rating4 = smile_rating4.getSelectedSmiley().getRating();
                int rating5 = smile_rating5.getSelectedSmiley().getRating();
                int rating6 = smile_rating6.getSelectedSmiley().getRating();
                int rating7 = smile_rating7.getSelectedSmiley().getRating();
                int rating8 = smile_rating8.getSelectedSmiley().getRating();
                int rating9 = smile_rating9.getSelectedSmiley().getRating();
                int rating10 = smile_rating10.getSelectedSmiley().getRating();
                int rating11 = smile_rating11.getSelectedSmiley().getRating();
                int rating12 = smile_rating12.getSelectedSmiley().getRating();
                int rating13 = smile_rating13.getSelectedSmiley().getRating();
                int rating14 = smile_rating14.getSelectedSmiley().getRating();
                int rating15 = smile_rating15.getSelectedSmiley().getRating();
                int rating16 = smile_rating16.getSelectedSmiley().getRating();
                int rating17 = smile_rating17.getSelectedSmiley().getRating();
                int rating18 = smile_rating18.getSelectedSmiley().getRating();
                int rating19 = smile_rating19.getSelectedSmiley().getRating();
                int rating20 = smile_rating20.getSelectedSmiley().getRating();

                if (rating1 == -1) {
                    Snackbar.make(rootLayout,"Debes completar toda la evaluación",Snackbar.LENGTH_SHORT).show();
                }
                else if (rating2 == -1) {
                    Snackbar.make(rootLayout,"Debes completar toda la evaluación",Snackbar.LENGTH_SHORT).show();
                }
                else if (rating3 == -1) {
                    Snackbar.make(rootLayout,"Debes completar toda la evaluación",Snackbar.LENGTH_SHORT).show();
                }
                else if (rating4 == -1) {
                    Snackbar.make(rootLayout,"Debes completar toda la evaluación",Snackbar.LENGTH_SHORT).show();
                }
                else if (rating5 == -1) {
                    Snackbar.make(rootLayout,"Debes completar toda la evaluación",Snackbar.LENGTH_SHORT).show();
                }
                else if (rating6 == -1) {
                    Snackbar.make(rootLayout,"Debes completar toda la evaluación",Snackbar.LENGTH_SHORT).show();
                }
                else if (rating7 == -1) {
                    Snackbar.make(rootLayout,"Debes completar toda la evaluación",Snackbar.LENGTH_SHORT).show();
                }
                else if (rating8 == -1) {
                    Snackbar.make(rootLayout,"Debes completar toda la evaluación",Snackbar.LENGTH_SHORT).show();
                }
                else if (rating9 == -1) {
                    Snackbar.make(rootLayout,"Debes completar toda la evaluación",Snackbar.LENGTH_SHORT).show();
                }
                else if (rating10 == -1) {
                    Snackbar.make(rootLayout,"Debes completar toda la evaluación",Snackbar.LENGTH_SHORT).show();
                }
                else if (rating11 == -1) {
                    Snackbar.make(rootLayout,"Debes completar toda la evaluación",Snackbar.LENGTH_SHORT).show();
                }
                else if (rating12 == -1) {
                    Snackbar.make(rootLayout,"Debes completar toda la evaluación",Snackbar.LENGTH_SHORT).show();
                }
                else if (rating13 == -1) {
                    Snackbar.make(rootLayout,"Debes completar toda la evaluación",Snackbar.LENGTH_SHORT).show();
                }
                else if (rating14 == -1) {
                    Snackbar.make(rootLayout,"Debes completar toda la evaluación",Snackbar.LENGTH_SHORT).show();
                }
                else if (rating15 == -1) {
                    Snackbar.make(rootLayout,"Debes completar toda la evaluación",Snackbar.LENGTH_SHORT).show();
                }
                else if (rating16 == -1) {
                    Snackbar.make(rootLayout,"Debes completar toda la evaluación",Snackbar.LENGTH_SHORT).show();
                }
                else if (rating17 == -1) {
                    Snackbar.make(rootLayout,"Debes completar toda la evaluación",Snackbar.LENGTH_SHORT).show();
                }
                else if (rating18 == -1) {
                    Snackbar.make(rootLayout,"Debes completar toda la evaluación",Snackbar.LENGTH_SHORT).show();
                }
                else if (rating19 == -1) {
                    Snackbar.make(rootLayout,"Debes completar toda la evaluación",Snackbar.LENGTH_SHORT).show();
                } else if (rating20 == -1) {
                    Snackbar.make(rootLayout, "Debes completar toda la evaluación", Snackbar.LENGTH_SHORT).show();
                } else {

                    Calendar calForDate = Calendar.getInstance();
                    SimpleDateFormat currentDate = new SimpleDateFormat("dd-MM-yyyy");
                    String saveCurrentDate = currentDate.format(calForDate.getTime());

                    Calendar calForTime = Calendar.getInstance();
                    SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss");
                    String saveCurrentTime = currentTime.format(calForTime.getTime());

                    companyRef.child(post_key).child("Job Profile").child(profile_id).child("Evaluation").child("Manager").child("test_1").child("score").setValue(rating1);
                    companyRef.child(post_key).child("Job Profile").child(profile_id).child("Evaluation").child("Manager").child("test_2").child("score").setValue(rating2);
                    companyRef.child(post_key).child("Job Profile").child(profile_id).child("Evaluation").child("Manager").child("test_3").child("score").setValue(rating3);
                    companyRef.child(post_key).child("Job Profile").child(profile_id).child("Evaluation").child("Manager").child("test_4").child("score").setValue(rating4);
                    companyRef.child(post_key).child("Job Profile").child(profile_id).child("Evaluation").child("Manager").child("test_5").child("score").setValue(rating5);
                    companyRef.child(post_key).child("Job Profile").child(profile_id).child("Evaluation").child("Manager").child("test_6").child("score").setValue(rating6);
                    companyRef.child(post_key).child("Job Profile").child(profile_id).child("Evaluation").child("Manager").child("test_7").child("score").setValue(rating7);
                    companyRef.child(post_key).child("Job Profile").child(profile_id).child("Evaluation").child("Manager").child("test_8").child("score").setValue(rating8);
                    companyRef.child(post_key).child("Job Profile").child(profile_id).child("Evaluation").child("Manager").child("test_9").child("score").setValue(rating9);
                    companyRef.child(post_key).child("Job Profile").child(profile_id).child("Evaluation").child("Manager").child("test_10").child("score").setValue(rating10);
                    companyRef.child(post_key).child("Job Profile").child(profile_id).child("Evaluation").child("Manager").child("test_11").child("score").setValue(rating11);
                    companyRef.child(post_key).child("Job Profile").child(profile_id).child("Evaluation").child("Manager").child("test_12").child("score").setValue(rating12);
                    companyRef.child(post_key).child("Job Profile").child(profile_id).child("Evaluation").child("Manager").child("test_13").child("score").setValue(rating13);
                    companyRef.child(post_key).child("Job Profile").child(profile_id).child("Evaluation").child("Manager").child("test_14").child("score").setValue(rating14);
                    companyRef.child(post_key).child("Job Profile").child(profile_id).child("Evaluation").child("Manager").child("test_15").child("score").setValue(rating15);
                    companyRef.child(post_key).child("Job Profile").child(profile_id).child("Evaluation").child("Manager").child("test_16").child("score").setValue(rating16);
                    companyRef.child(post_key).child("Job Profile").child(profile_id).child("Evaluation").child("Manager").child("test_17").child("score").setValue(rating17);
                    companyRef.child(post_key).child("Job Profile").child(profile_id).child("Evaluation").child("Manager").child("test_18").child("score").setValue(rating18);
                    companyRef.child(post_key).child("Job Profile").child(profile_id).child("Evaluation").child("Manager").child("test_19").child("score").setValue(rating19);
                    companyRef.child(post_key).child("Job Profile").child(profile_id).child("Evaluation").child("Manager").child("test_20").child("score").setValue(rating20);
                    companyRef.child(post_key).child("Job Profile").child(profile_id).child("Evaluation").child("Manager Update").child("date_last_update").setValue(saveCurrentDate);
                    companyRef.child(post_key).child("Job Profile").child(profile_id).child("Evaluation").child("Manager Update").child("time_last_update").setValue(saveCurrentTime);

                    Toasty.success(getActivity(), "Registrado", Toast.LENGTH_LONG).show();

                }


            }
        });



        return view;
    }
}
