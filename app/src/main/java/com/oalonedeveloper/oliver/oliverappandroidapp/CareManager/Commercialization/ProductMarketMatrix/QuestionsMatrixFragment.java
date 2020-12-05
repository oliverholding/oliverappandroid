package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Commercialization.ProductMarketMatrix;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.ValueProposalModule.DataTestFragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.ValueProposalModule.NoDataTestFragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;


public class QuestionsMatrixFragment extends Fragment {

    Fragment questionFragment,answerFragment;
    DatabaseReference myCompanyRef;
    String post_key;
    DatabaseReference companyRef;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_questions_matrix, container, false);

        post_key = getActivity().getIntent().getExtras().getString("post_key");

        myCompanyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");

        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");

        questionFragment = new QuestionsFragment();
        answerFragment = new MatrizAnswerFragment();

        companyRef.child(post_key).child("Module 6").child("Product Matrix").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    getActivity().getSupportFragmentManager().beginTransaction().add(R.id.contentFragment,answerFragment).commit();
                } else {
                    getActivity().getSupportFragmentManager().beginTransaction().add(R.id.contentFragment,questionFragment).commit();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return view;
    }
}
