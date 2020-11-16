package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.ValueProposalModule;

import android.graphics.Color;
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


public class DataTestFragment extends Fragment {

    TextView txtMessage;
    DatabaseReference myCompanyRef;
    String post_key;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_data_test, container, false);

        post_key = getActivity().getIntent().getExtras().getString("post_key");

        myCompanyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");

        myCompanyRef.child(post_key).child("Module 3").child("Company Test").orderByChild("A").equalTo(1).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                long answer = dataSnapshot.getChildrenCount();


                if (answer >= 0 && answer <= 4) {
                    txtMessage.setText("¡Preocupate! Necesitas mejorar y cambiar muchas cosas");
                    txtMessage.setTextColor(getResources().getColor(R.color.redColor));
                }
                else if (answer >= 5 && answer <= 7) {
                    txtMessage.setText("¡Despierta! Hay algunas cosas por mejorar");
                    txtMessage.setTextColor(getResources().getColor(R.color.yellowColor));
                }
                else if (answer >= 8) {
                    txtMessage.setText("¡Vamos por buen camino y siempre se puede mejorar!");
                    txtMessage.setTextColor(getResources().getColor(R.color.greenColor));
                }


                Toast.makeText(getActivity(), "CONTADOR: "+answer, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        txtMessage = view.findViewById(R.id.txtMessage);

        return view;
    }
}
