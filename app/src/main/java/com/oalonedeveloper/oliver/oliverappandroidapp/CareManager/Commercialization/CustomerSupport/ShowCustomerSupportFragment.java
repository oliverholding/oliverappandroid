package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Commercialization.CustomerSupport;

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
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Commercialization.ProductMarketMatrix.MatrizAnswerFragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Commercialization.ProductMarketMatrix.QuestionsFragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;


public class ShowCustomerSupportFragment extends Fragment {

    Fragment questionFragment,answerFragment;
    DatabaseReference myCompanyRef;
    String post_key;
    DatabaseReference companyRef;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_show_customer_support, container, false);

        post_key = getActivity().getIntent().getExtras().getString("post_key");

        myCompanyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");

        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");

        questionFragment = new QuestionCustomerSuportFragment();
        answerFragment = new ResultCustomerSupportFragment();

        companyRef.child(post_key).child("Module 6").child("Questions Customer Support").addListenerForSingleValueEvent(new ValueEventListener() {
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
