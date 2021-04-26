package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Commercialization.ProductMarketMatrix;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Commercialization.NewMatrix10.NewMatrix10Activity;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;


public class QuestionsFragment extends Fragment {

    Button btnContinue;
    String post_key;
    RadioButton rd001,rd002,rd003,rd004;
    RelativeLayout rootLayout;
    DatabaseReference companyRef;
    RadioGroup radioGroup;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_questions, container, false);

        post_key = getActivity().getIntent().getExtras().getString("post_key");

        btnContinue = view.findViewById(R.id.btnContinue);
        rd001 = view.findViewById(R.id.rd001);
        rd002 = view.findViewById(R.id.rd002);
        rd003 = view.findViewById(R.id.rd003);
        rd004 = view.findViewById(R.id.rd004);
        rootLayout = view.findViewById(R.id.rootLayout);
        radioGroup = view.findViewById(R.id.radioGroup);

        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");


        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!rd001.isChecked() && !rd002.isChecked() && !rd003.isChecked() && !rd004.isChecked()) {
                    Snackbar.make(rootLayout, "Debes seleccionar una afirmación", Snackbar.LENGTH_LONG).show();
                    return;
                } else {
                    if (rd001.isChecked()) {
                        companyRef.child(post_key).child("Module 6").child("Product Matrix").child("matrix_id").setValue(1);
                    }
                    else if (rd002.isChecked()) {
                        companyRef.child(post_key).child("Module 6").child("Product Matrix").child("matrix_id").setValue(2);
                    }
                    else if (rd003.isChecked()) {
                        companyRef.child(post_key).child("Module 6").child("Product Matrix").child("matrix_id").setValue(3);
                    }
                    else if (rd004.isChecked()) {
                        companyRef.child(post_key).child("Module 6").child("Product Matrix").child("matrix_id").setValue(4);
                    }
                    Intent intent = new Intent(getActivity(), NewMatrix10Activity.class);
                    intent.putExtra("post_key",post_key);
                    intent.putExtra("FRAGMENT_ID",2);
                    startActivity(intent);
                    getActivity().finish();
                }
            }
        });

        return view;
    }
}
