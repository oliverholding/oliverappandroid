package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.PeopleManagement.JobProfiles;

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

public class JobProfileInformationFragment extends Fragment {

    TextView txtName,txtCharge,txtArea,txtDenomination,txtImmediateBoss,txtResponsableArea,txtPeopleNumber,txtMission,txtMissionDescription;
    DatabaseReference companyRef;
    String post_key,profile_id,job_profile_name,job_profile_job_name,job_profile_area,job_profile_denomination,job_profile_immediate_boss,job_profile_responsable_area,job_profile_people_number,job_profile_job_mission,job_profile_job_mission_description;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_job_profile_information, container, false);

        post_key = getActivity().getIntent().getExtras().getString("post_key");
        profile_id = getActivity().getIntent().getExtras().getString("profile_id");
        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");

        txtName = view.findViewById(R.id.txtName);
        txtCharge = view.findViewById(R.id.txtCharge);
        txtArea = view.findViewById(R.id.txtArea);
        txtDenomination = view.findViewById(R.id.txtDenomination);
        txtImmediateBoss = view.findViewById(R.id.txtImmediateBoss);
        txtResponsableArea = view.findViewById(R.id.txtResponsableArea);
        txtPeopleNumber = view.findViewById(R.id.txtPeopleNumber);
        txtMission = view.findViewById(R.id.txtMission);
        txtMissionDescription = view.findViewById(R.id.txtMissionDescription);

        companyRef.child(post_key).child("Job Profile").child(profile_id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                job_profile_name = dataSnapshot.child("job_profile_name").getValue().toString();
                job_profile_job_name = dataSnapshot.child("job_profile_job_name").getValue().toString();
                job_profile_area = dataSnapshot.child("job_profile_area").getValue().toString();
                job_profile_denomination = dataSnapshot.child("job_profile_denomination").getValue().toString();
                job_profile_immediate_boss = dataSnapshot.child("job_profile_immediate_boss").getValue().toString();
                job_profile_responsable_area = dataSnapshot.child("job_profile_responsable_area").getValue().toString();
                job_profile_people_number = dataSnapshot.child("job_profile_people_number").getValue().toString();
                job_profile_job_mission = dataSnapshot.child("job_profile_job_mission").getValue().toString();
                job_profile_job_mission_description = dataSnapshot.child("job_profile_job_mission_description").getValue().toString();

                txtName.setText(job_profile_name);
                txtCharge.setText(job_profile_job_name);
                txtArea.setText(job_profile_area);
                txtDenomination.setText(job_profile_denomination);
                txtImmediateBoss.setText(job_profile_immediate_boss);
                txtResponsableArea.setText(job_profile_responsable_area);
                txtPeopleNumber.setText(job_profile_people_number+" Personas");
                txtMission.setText(job_profile_job_mission);
                txtMissionDescription.setText(job_profile_job_mission_description);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return view;
    }
}
