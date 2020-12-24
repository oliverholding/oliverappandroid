package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Commercialization.ProductMarketMatrix;

import android.content.Intent;
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
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Commercialization.NewMatrix10.NewMatrix10Activity;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;

public class MatrizAnswerFragment extends Fragment {

    DatabaseReference companyRef;
    String post_key;
    int matrix_id;
    TextView txtMessage;
    Button btnTryAgain,btnMatrix;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_matriz_answer, container, false);

        post_key = getActivity().getIntent().getExtras().getString("post_key");

        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");

        txtMessage = view.findViewById(R.id.txtMessage);
        btnTryAgain = view.findViewById(R.id.btnTryAgain);
        btnMatrix = view.findViewById(R.id.btnMatrix);

        btnMatrix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NewMatrix10Activity.class);
                intent.putExtra("post_key",post_key);
                intent.putExtra("FRAGMENT_ID", 3);
                startActivity(intent);
                getActivity().finish();
            }
        });

        companyRef.child(post_key).child("Module 6").child("Product Matrix").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    matrix_id = dataSnapshot.child("matrix_id").getValue(Integer.class);

                    if (matrix_id == 1) {
                        txtMessage.setText("La estrategia a utilizar es la de Penetración de Mercado con bajo riesgo al fracaso");
                    }
                    if (matrix_id == 2) {
                        txtMessage.setText("La estrategia a utilizar es la de Desarrollo de Nuevo Producto con riesgo medio al fracaso.");
                    }
                    if (matrix_id == 3) {
                        txtMessage.setText("La estrategia a utilizar es la de Desarrollo de Nuevos Mercados con riesgo medio al fracaso.");
                    }
                    if (matrix_id == 4) {
                        txtMessage.setText("La estrategia a utilizar es la de Diversificación con riesgo alto al fracaso si no hay respaldo financiero para invertir.");
                    }

                }



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        btnTryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                companyRef.child(post_key).child("Module 6").child("Product Matrix").removeValue();
                Intent intent = new Intent(getActivity(), NewMatrix10Activity.class);
                intent.putExtra("post_key",post_key);
                intent.putExtra("FRAGMENT_ID",2);
                startActivity(intent);
                getActivity().finish();
            }
        });

        return view;
    }
}
