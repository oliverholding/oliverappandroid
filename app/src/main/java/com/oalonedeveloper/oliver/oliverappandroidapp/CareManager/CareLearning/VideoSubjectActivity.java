package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.CareLearning;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.app.DownloadManager;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.CareAchievementsFragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.CareLearning.VideoResourses.VideoListFragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.CareManagerSumaryFragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.CareToolsFragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;

import es.dmoral.toasty.Toasty;

import static android.os.Environment.DIRECTORY_DOWNLOADS;

public class VideoSubjectActivity extends AppCompatActivity {

    VideoView video_view;
    StorageReference mStorage;
    StorageReference ref;
    Button btnDownload;
    String video_id,subject_id,video_doc_name,video_name,video_url;
    DatabaseReference careRef;

    CardView tab1,tab2,tab3,tab4;
    Fragment fragment1,fragment2,fragment3,fragment4;
    View view1,view2,view3,view4;
    TextView txt1,txt2,txt3,txt4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_subject);

        btnDownload = findViewById(R.id.btnDownload);

        video_view = findViewById(R.id.video_view);
        MediaController mediaController = new MediaController(this);
        video_view.setMediaController(mediaController);
        mediaController.setAnchorView(video_view);
        mStorage = FirebaseStorage.getInstance().getReference();

        video_id = getIntent().getExtras().getString("video_id");
        subject_id = getIntent().getExtras().getString("subject_id");

        careRef = FirebaseDatabase.getInstance().getReference().child("Care Learning").child("Subjects");


        tab1 = findViewById(R.id.tab1);
        tab2 = findViewById(R.id.tab2);
        tab3 = findViewById(R.id.tab3);
        tab4 = findViewById(R.id.tab4);

        txt1 = findViewById(R.id.txt1);
        txt2 = findViewById(R.id.txt2);
        txt3 = findViewById(R.id.txt3);
        txt4 = findViewById(R.id.txt4);

        view1 = findViewById(R.id.view1);
        view2 = findViewById(R.id.view2);
        view3 = findViewById(R.id.view3);
        view4 = findViewById(R.id.view4);

        fragment1 = new VideoListFragment();
        fragment2 = new VideoListFragment();
        fragment3 = new VideoListFragment();
        fragment4 = new VideoListFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.contentFragment,fragment1).commit();

        tab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.contentFragment,fragment1).commit();
                txt1.setTextColor(getResources().getColor(R.color.orange1));
                txt2.setTextColor(Color.GRAY);
                txt3.setTextColor(Color.GRAY);
                txt4.setTextColor(Color.GRAY);

                view1.setBackgroundResource(R.color.orange1);
                view2.setBackgroundResource(R.color.gray2);
                view3.setBackgroundResource(R.color.gray2);
                view4.setBackgroundResource(R.color.gray2);
            }
        });

        tab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.contentFragment,fragment2).commit();
                txt2.setTextColor(getResources().getColor(R.color.orange1));
                txt1.setTextColor(Color.GRAY);
                txt3.setTextColor(Color.GRAY);
                txt4.setTextColor(Color.GRAY);

                view2.setBackgroundResource(R.color.orange1);
                view1.setBackgroundResource(R.color.gray2);
                view3.setBackgroundResource(R.color.gray2);
                view4.setBackgroundResource(R.color.gray2);
            }
        });

        tab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.contentFragment,fragment3).commit();
                txt3.setTextColor(getResources().getColor(R.color.orange1));
                txt2.setTextColor(Color.GRAY);
                txt1.setTextColor(Color.GRAY);
                txt4.setTextColor(Color.GRAY);

                view3.setBackgroundResource(R.color.orange1);
                view2.setBackgroundResource(R.color.gray2);
                view1.setBackgroundResource(R.color.gray2);
                view4.setBackgroundResource(R.color.gray2);

            }
        });

        tab4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.contentFragment,fragment4).commit();
                txt4.setTextColor(getResources().getColor(R.color.orange1));
                txt2.setTextColor(Color.GRAY);
                txt1.setTextColor(Color.GRAY);
                txt3.setTextColor(Color.GRAY);

                view4.setBackgroundResource(R.color.orange1);
                view2.setBackgroundResource(R.color.gray2);
                view1.setBackgroundResource(R.color.gray2);
                view3.setBackgroundResource(R.color.gray2);

            }
        });


        careRef.child(subject_id).child("Videos").child(video_id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                video_doc_name = dataSnapshot.child("video_doc_name").getValue().toString();
                video_name = dataSnapshot.child("video_name").getValue().toString();
                video_url = dataSnapshot.child("video_url").getValue().toString();

                Uri uri = Uri.parse(video_url);
                video_view.setVideoURI(uri);
                video_view.start();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ref = mStorage.child("Care Videos").child(video_doc_name+".mp4");
                ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        String url = uri.toString();
                        downloadFile(VideoSubjectActivity.this,video_doc_name,".mp4",DIRECTORY_DOWNLOADS,url);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(VideoSubjectActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });



    }

    private void downloadFile(FragmentActivity activity, String my_url, String s, String directoryDownloads, String url) {
        DownloadManager downloadManager =  (DownloadManager) activity.getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse(url);
        DownloadManager.Request request = new DownloadManager.Request(uri);

        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalFilesDir(activity,directoryDownloads,my_url+s);
        downloadManager.enqueue(request);

        Toasty.success(VideoSubjectActivity.this, "Descargando... ", Toast.LENGTH_LONG).show();

    }
}
