package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.PeopleManagement;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.PeopleManagement.JobProfiles.JobProfilesActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;

public class PeopleManagementFragment extends Fragment {

    Button btnJobProfile;
    String post_key;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_people_management, container, false);

        post_key = getActivity().getIntent().getExtras().getString("post_key");

        btnJobProfile = view.findViewById(R.id.btnJobProfile);

        btnJobProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), JobProfilesActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });

        return view;
    }
}
