package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.CareLearning.VideoResourses;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hsalf.smileyrating.SmileyRating;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;


public class VideoRatingFragment extends Fragment {

    SmileyRating smile_rating;
    TextView txtMessage;
    Button btnWhatsApp,btnSendRating;
    String video_id,subject_id,fullname;
    DatabaseReference careRef,userRef;
    FirebaseAuth mAuth;
    String currentUid,subject_name;
    RelativeLayout rootLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_video_rating, container, false);

        smile_rating = view.findViewById(R.id.smile_rating);
        txtMessage = view.findViewById(R.id.txtMessage);
        btnWhatsApp = view.findViewById(R.id.btnWhatsApp);
        btnSendRating = view.findViewById(R.id.btnSendRating);
        rootLayout = view.findViewById(R.id.rootLayout);

        smile_rating.setVisibility(View.GONE);
        txtMessage.setVisibility(View.GONE);
        btnSendRating.setVisibility(View.GONE);

        mAuth = FirebaseAuth.getInstance();
        currentUid = mAuth.getCurrentUser().getUid();

        video_id = getActivity().getIntent().getExtras().getString("video_id");
        subject_id =  getActivity().getIntent().getExtras().getString("subject_id");

        careRef = FirebaseDatabase.getInstance().getReference().child("Care Learning").child("Subjects");
        userRef = FirebaseDatabase.getInstance().getReference().child("Users");

        userRef.child(currentUid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                fullname = dataSnapshot.child("fullname").getValue().toString();

                careRef.child(subject_id).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        subject_name = dataSnapshot.child("subject_name").getValue().toString();
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

        btnSendRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int rating = smile_rating.getSelectedSmiley().getRating();
                if (rating == -1) {
                    Snackbar.make(rootLayout, "Debes seleccionar una calificación", Snackbar.LENGTH_SHORT).show();
                } else {
                    careRef.child(subject_id).child("Videos").child(video_id).child("Ratings").child(currentUid).child("score").setValue(rating);
                }
            }
        });

        careRef.child(subject_id).child("Videos").child(video_id).child("Ratings").child(currentUid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    smile_rating.setVisibility(View.GONE);
                    txtMessage.setVisibility(View.VISIBLE);
                    btnSendRating.setVisibility(View.GONE);
                } else {
                    txtMessage.setVisibility(View.GONE);
                    smile_rating.setVisibility(View.VISIBLE);
                    btnSendRating.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        smile_rating.setTitle(SmileyRating.Type.TERRIBLE,"Malo");
        smile_rating.setTitle(SmileyRating.Type.BAD,"Regular");
        smile_rating.setTitle(SmileyRating.Type.OKAY,"Bueno");
        smile_rating.setTitle(SmileyRating.Type.GOOD,"Muy Bueno");
        smile_rating.setTitle(SmileyRating.Type.GREAT,"Excelente");

        btnWhatsApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWhatsappTosendMessage();
            }
        });

        return view;
    }

    private void openWhatsappTosendMessage() {
        boolean installed = appInstalledOrNot("com.whatsapp");
        if (installed) {
            String message = "Hola, soy "+fullname+" de Empresara, tengo unas preguntas sobre el curso "+subject_name;
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("http://api.whatsapp.com/send?phone=+51934495761" + "&text=" + message));
            startActivity(intent);
        } else {
            Toast.makeText(getActivity(), "No tienes instalado WhatsApp", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean appInstalledOrNot(String url) {
        PackageManager packageManager = getActivity().getPackageManager();
        boolean app_installed;

        try {
            packageManager.getPackageInfo(url, PackageManager.GET_ACTIVITIES);
            app_installed = true;
        } catch (PackageManager.NameNotFoundException e) {
            app_installed = false;
        }

        return app_installed;
    }
}
