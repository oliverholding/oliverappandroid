package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Commercialization.ProductMarketMatrix;

import android.graphics.Color;
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


public class MatrixFragment extends Fragment {

    TextView txtMatrix1,txtMatrix2,txtMatrix3,txtMatrix4,txtMessage;
    DatabaseReference companyRef;
    String post_key;
    int matrix_id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_matrix, container, false);

        post_key = getActivity().getIntent().getExtras().getString("post_key");

        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");

        txtMatrix1 = view.findViewById(R.id.txtMatrix1);
        txtMatrix2 = view.findViewById(R.id.txtMatrix2);
        txtMatrix3 = view.findViewById(R.id.txtMatrix3);
        txtMatrix4 = view.findViewById(R.id.txtMatrix4);
        txtMessage = view.findViewById(R.id. txtMessage);

        companyRef.child(post_key).child("Module 6").child("Product Matrix").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    matrix_id = dataSnapshot.child("matrix_id").getValue(Integer.class);

                    if (matrix_id == 1) {
                        txtMatrix1.setTextColor(Color.WHITE);
                        txtMatrix1.setBackgroundResource(R.drawable.blue_button_style);
                        txtMessage.setText("Estás en el cuadrante: PENETRACIÓN DE MERCADO (BAJO RIESGO)");
                    }
                    if (matrix_id == 2) {
                        txtMatrix2.setTextColor(Color.WHITE);
                        txtMatrix2.setBackgroundResource(R.drawable.blue_button_style);
                        txtMessage.setText("Estás en el cuadrante: DESARROLLO DE NUEVOS PRODUCTOS (RIESGO MEDIO)");
                    }
                    if (matrix_id == 3) {
                        txtMatrix3.setTextColor(Color.WHITE);
                        txtMatrix3.setBackgroundResource(R.drawable.blue_button_style);
                        txtMessage.setText("Estás en el cuadrante: DESARROLLO DE NUEVOS MERCIADOS (RIESGO MEDIO)");
                    }
                    if (matrix_id == 4) {
                        txtMatrix4.setTextColor(Color.WHITE);
                        txtMatrix4.setBackgroundResource(R.drawable.blue_button_style);
                        txtMessage.setText("Estás en el cuadrante: \"DIVERSIFICACIÓN (ALTO RIESGO)");
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return view;
    }
}
