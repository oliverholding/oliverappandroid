package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.CareLearning.VideoResourses;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.CareLearning.VideoListActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.CareLearning.VideoModel;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.CareLearning.VideoSubjectActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;
import com.squareup.picasso.Picasso;


public class VideoListFragment extends Fragment {

    DatabaseReference careRef;
    String postkey;
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_video_list, container, false);

        postkey = getActivity().getIntent().getExtras().getString("subject_id");
        careRef = FirebaseDatabase.getInstance().getReference().child("Care Learning").child("Subjects");

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setReverseLayout(false);
        linearLayoutManager.setStackFromEnd(false);
        recyclerView.setLayoutManager(linearLayoutManager);

        showVideos();

        return view;
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
                        Intent intent = new Intent(getActivity(), VideoSubjectActivity.class);
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
