package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager;

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
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;


public class CareManagerSumaryFragment extends Fragment {

    CircleImageView imgCompanyProfile;
    DatabaseReference myCompanyRef;
    String company_image,company_social_reason, post_key;
    TextView txtCompanyName,txtCurrentMonth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_care_manager_sumary, container, false);

        imgCompanyProfile = view.findViewById(R.id.imgCompanyProfile);
        txtCompanyName = view.findViewById(R.id.txtCompanyName);
        txtCurrentMonth = view.findViewById(R.id.txtCurrentMonth);

        myCompanyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");

        post_key = getActivity().getIntent().getExtras().getString("post_key");

        myCompanyRef.child(post_key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                company_image = dataSnapshot.child("company_image").getValue().toString();
                company_social_reason = dataSnapshot.child("company_social_reason").getValue().toString();
                Picasso.with(getActivity()).load(company_image).fit().into(imgCompanyProfile);
                txtCompanyName.setText(company_social_reason);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return view;
    }
}
