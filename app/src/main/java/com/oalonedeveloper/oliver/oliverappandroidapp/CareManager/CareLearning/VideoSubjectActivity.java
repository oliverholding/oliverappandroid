package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.CareLearning;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import com.oalonedeveloper.oliver.oliverappandroidapp.R;

public class VideoSubjectActivity extends AppCompatActivity {

    VideoView video_view;
    String video_path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_subject);

        video_view = findViewById(R.id.video_view);
        video_path = "android.resource://"+getPackageName()+"/"+R.raw.marketing_video_muestra;
        Uri uri = Uri.parse(video_path);
        video_view.setVideoURI(uri);

        MediaController mediaController = new MediaController(this);
        video_view.setMediaController(mediaController);
        mediaController.setAnchorView(video_view);

    }
}
