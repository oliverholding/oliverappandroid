package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.CareLearning;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;
import com.squareup.picasso.Picasso;

public class VideoListActivity extends AppCompatActivity {

    ImageView imgVideo;
    TextView txtSubjectName;
    DatabaseReference careRef;
    String postkey;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_list);

        imgVideo = findViewById(R.id.imgVideo);
        txtSubjectName = findViewById(R.id.txtSubjectName);
        postkey = getIntent().getExtras().getString("postkey");
        careRef = FirebaseDatabase.getInstance().getReference().child("Care Learning").child("Subjects");

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(false);
        linearLayoutManager.setStackFromEnd(false);
        recyclerView.setLayoutManager(linearLayoutManager);

        careRef.child(postkey).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String subject_name = dataSnapshot.child("subject_name").getValue().toString();
                String subject_image = dataSnapshot.child("subject_image").getValue().toString();

                Picasso.with(VideoListActivity.this).load(subject_image).fit().into(imgVideo);
                txtSubjectName.setText(subject_name);

                showVideos();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void showVideos() {
        Query query = careRef.child(postkey).child("Videos");
        FirebaseRecyclerAdapter<VideoModel,VideoViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<VideoModel, VideoViewHolder>
                (VideoModel.class,R.layout.video_item,VideoViewHolder.class,query) {
            @Override
            protected void populateViewHolder(VideoViewHolder viewHolder, VideoModel model, int position) {
                final String postKey = getRef(position).getKey();
                viewHolder.setPosition(model.getPosition());
                viewHolder.setVideo_image(model.getVideo_image());
                viewHolder.setVideo_name(model.getVideo_name());
                viewHolder.setVideo_url(model.getVideo_url());

                viewHolder.txtVideoName.setText(viewHolder.my_video_name);

                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(VideoListActivity.this,VideoSubjectActivity.class);
                        intent.putExtra("video_id",postKey);
                        intent.putExtra("subject_id",postkey);
                        startActivity(intent);
                    }
                });

            }
        };
        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    public static class VideoViewHolder extends RecyclerView.ViewHolder {

        View mView;
        String my_position,my_video_image,my_video_name,my_video_url;
        TextView txtVideoName;

        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);

            mView = itemView;
            txtVideoName = mView.findViewById(R.id.txtVideoName);

        }
        public void setPosition(String position) {
            my_position = position;
        }

        public void setVideo_image(String video_image) {
            my_video_image = video_image;
        }

        public void setVideo_name(String video_name) {
            my_video_name = video_name;
        }


        public void setVideo_url(String video_url) {
            my_video_url = video_url;
        }
    }
}
