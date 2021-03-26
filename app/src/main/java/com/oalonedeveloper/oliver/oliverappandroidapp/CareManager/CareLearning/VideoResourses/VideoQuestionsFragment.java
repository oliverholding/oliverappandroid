package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.CareLearning.VideoResourses;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.oalonedeveloper.oliver.oliverappandroidapp.R;


public class VideoQuestionsFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_video_questions, container, false);
        return view;
    }
}
