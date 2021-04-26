package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.CareLearning;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;
import com.squareup.picasso.Picasso;


public class MySubjectsFragment extends Fragment {

    DatabaseReference careRef,userRef;
    FirebaseAuth mAuth;
    String currentUid;
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_subjects, container, false);

        mAuth = FirebaseAuth.getInstance();
        currentUid = mAuth.getCurrentUser().getUid();

        careRef = FirebaseDatabase.getInstance().getReference().child("Care Learning").child("Subjects");
        userRef = FirebaseDatabase.getInstance().getReference().child("Users");

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setReverseLayout(false);
        linearLayoutManager.setStackFromEnd(false);
        recyclerView.setLayoutManager(linearLayoutManager);

        showMySubjects();

        return view;
    }

    private void showMySubjects() {
        Query query = userRef.child(currentUid).child("Care Learning").child("My Subjects");
        FirebaseRecyclerAdapter<MySubjectsModel,MySubjectsViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<MySubjectsModel, MySubjectsViewHolder>
                (MySubjectsModel.class,R.layout.my_subject_item,MySubjectsViewHolder.class,query) {
            @Override
            protected void populateViewHolder(final MySubjectsViewHolder viewHolder, MySubjectsModel model, int position) {
                final String postKey = getRef(position).getKey();
                viewHolder.setSubject_id(model.getSubject_id());
                viewHolder.setProgress(model.getProgress());

                careRef.child(postKey).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String subject_name = dataSnapshot.child("subject_name").getValue().toString();
                        String subject_image = dataSnapshot.child("subject_image").getValue().toString();

                        viewHolder.txtSubjectName.setText(subject_name);
                        Picasso.with(getActivity()).load(subject_image).fit().into(viewHolder.imgSubject);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                viewHolder.btnSubject.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), VideoListActivity.class);
                        intent.putExtra("postkey",postKey);
                        startActivity(intent);
                    }
                });

            }
        };
        recyclerView.setAdapter(firebaseRecyclerAdapter);

    }

    public static class MySubjectsViewHolder extends RecyclerView.ViewHolder {

        View mView;
        String my_subject_id;
        int my_progress;
        ImageView imgSubject;
        TextView txtSubjectName;
        Button btnSubject;

        public MySubjectsViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;

            txtSubjectName = mView.findViewById(R.id.txtSubjectName);
            imgSubject = mView.findViewById(R.id.imgSubject);
            btnSubject = mView.findViewById(R.id.btnSubject);


        }
        public void setSubject_id(String subject_id) {
            my_subject_id = subject_id;
        }

        public void setProgress(int progress) {
            my_progress = progress;
        }
    }
}
