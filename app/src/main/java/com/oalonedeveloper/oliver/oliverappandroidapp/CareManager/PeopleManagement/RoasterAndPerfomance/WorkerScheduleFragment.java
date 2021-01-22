package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.PeopleManagement.RoasterAndPerfomance;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.oalonedeveloper.oliver.oliverappandroidapp.R;

public class WorkerScheduleFragment extends Fragment {

    Fragment fixed_schedule;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_worker_schedule, container, false);

        fixed_schedule = new WorkerFixedScheduleFragment();

        getActivity().getSupportFragmentManager().beginTransaction().add(R.id.contentFragment,fixed_schedule).commit();

        return view;
    }
}
