package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.ValueProposalModule;

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
import com.oalonedeveloper.oliver.oliverappandroidapp.R;


public class MainDataTestFragment extends Fragment {

    Fragment noDataTestFragment, dataTestFragment;
    DatabaseReference myCompanyRef;
    String post_key;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main_data_test, container, false);

        post_key = getActivity().getIntent().getExtras().getString("post_key");

        myCompanyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");

        noDataTestFragment = new NoDataTestFragment();
        dataTestFragment = new DataTestFragment();

        myCompanyRef.child(post_key).child("Module 3").child("Company Test").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    getActivity().getSupportFragmentManager().beginTransaction().add(R.id.contentFragment,dataTestFragment).commit();
                } else {
                    getActivity().getSupportFragmentManager().beginTransaction().add(R.id.contentFragment,noDataTestFragment).commit();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return view;
    }
}
