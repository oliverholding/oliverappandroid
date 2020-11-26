package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.ValueProposalModule;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;

import pl.droidsonroids.gif.GifImageView;


public class DataTestFragment extends Fragment {

    TextView txtMessage;
    DatabaseReference myCompanyRef;
    String post_key;
    GifImageView imgGif;
    Button btnTryAgain;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_data_test, container, false);

        post_key = getActivity().getIntent().getExtras().getString("post_key");

        myCompanyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");
        imgGif = view.findViewById(R.id.imgGif);
        txtMessage = view.findViewById(R.id.txtMessage);
        btnTryAgain = view.findViewById(R.id.btnTryAgain);

        btnTryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myCompanyRef.child(post_key).child("Module 3").child("Company Test").removeValue();
                Intent intent = new Intent(getActivity(), ValueProposalActivity.class);
                intent.putExtra("post_key",post_key);
                startActivity(intent);
                getActivity().finish();
            }
        });

        myCompanyRef.child(post_key).child("Module 3").child("Company Test").orderByChild("A").equalTo(1).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                long answer = dataSnapshot.getChildrenCount();


                if (answer >= 0 && answer <= 4) {
                    txtMessage.setText("¡Preocupate! Necesitas mejorar y cambiar muchas cosas");
                    txtMessage.setTextColor(getResources().getColor(R.color.redColor));
                    imgGif.setImageResource(R.drawable.company_on_fire);
                }
                else if (answer >= 5 && answer <= 7) {
                    txtMessage.setText("¡Despierta! Hay algunas cosas por mejorar");
                    txtMessage.setTextColor(getResources().getColor(R.color.yellowColor));
                    imgGif.setImageResource(R.drawable.company_alert);
                }
                else if (answer >= 8) {
                    txtMessage.setText("¡Vamos por buen camino y siempre se puede mejorar!");
                    txtMessage.setTextColor(getResources().getColor(R.color.greenColor));
                    imgGif.setImageResource(R.drawable.company_good);
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        return view;
    }
}
