package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.CareLearning.VideoResourses;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;
import com.squareup.picasso.Picasso;

import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;
import es.dmoral.toasty.Toasty;


public class VideoQuestionsFragment extends Fragment {

    EditText edtInputQuestion;
    ImageButton btnSendQuestion;
    RelativeLayout rootLayout;
    String video_id,subject_id;
    DatabaseReference careRef,userRef;
    RecyclerView recyclerView;

    FirebaseAuth mAuth;
    String currentUid;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_video_questions, container, false);

        edtInputQuestion = view.findViewById(R.id.edtInputQuestion);
        btnSendQuestion = view.findViewById(R.id.btnSendQuestion);
        rootLayout = view.findViewById(R.id.rootLayout);

        video_id = getActivity().getIntent().getExtras().getString("video_id");
        subject_id =  getActivity().getIntent().getExtras().getString("subject_id");

        careRef = FirebaseDatabase.getInstance().getReference().child("Care Learning").child("Subjects");
        userRef = FirebaseDatabase.getInstance().getReference().child("Users");

        mAuth = FirebaseAuth.getInstance();
        currentUid = mAuth.getCurrentUser().getUid();

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setReverseLayout(false);
        linearLayoutManager.setStackFromEnd(false);
        recyclerView.setLayoutManager(linearLayoutManager);


        showQuestions();


        btnSendQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(edtInputQuestion.getText().toString())) {
                    Snackbar.make(rootLayout,"Debes ingresar tu pregunta o comentario ",Snackbar.LENGTH_SHORT).show();
                } else {
                    Long tsLong = System.currentTimeMillis()/1000;
                    String timestamp = tsLong.toString();
                    careRef.child(subject_id).child("Videos").child(video_id).child("Questions").child(timestamp).child("question").setValue(edtInputQuestion.getText().toString());
                    careRef.child(subject_id).child("Videos").child(video_id).child("Questions").child(timestamp).child("uid").setValue(currentUid);
                    careRef.child(subject_id).child("Videos").child(video_id).child("Questions").child(timestamp).child("timestamp").setValue(timestamp);
                    Toasty.success(getActivity(), "Enviado", Toast.LENGTH_LONG).show();
                    edtInputQuestion.setText("");
                }
            }
        });


        return view;
    }

    private void showQuestions() {
        Query query = careRef.child(subject_id).child("Videos").child(video_id).child("Questions").orderByChild("timestamp");
        FirebaseRecyclerAdapter<QuestionsModel,QuestionsViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<QuestionsModel, QuestionsViewHolder>
                (QuestionsModel.class,R.layout.question_item,QuestionsViewHolder.class,query) {
            @Override
            protected void populateViewHolder(final QuestionsViewHolder viewHolder, QuestionsModel model, int position) {
                final String postKey = getRef(position).getKey();
                viewHolder.setQuestion(model.getQuestion());
                viewHolder.setUid(model.getUid());


                Calendar cal = Calendar.getInstance();
                long timestamp = Long.parseLong(postKey);
                cal.setTimeInMillis(timestamp * 1000);
                String date = DateFormat.format("dd-MM-yyyy", cal).toString();

                viewHolder.txtText.setText(viewHolder.my_question);
                viewHolder.txtDate.setText(date);

               userRef.child(viewHolder.my_uid).addListenerForSingleValueEvent(new ValueEventListener() {
                   @Override
                   public void onDataChange(DataSnapshot dataSnapshot) {
                       String profileimage = dataSnapshot.child("profileimage").getValue().toString();
                       String name = dataSnapshot.child("name").getValue().toString();

                       Picasso.with(getActivity()).load(profileimage).fit().into(viewHolder.imgProfile);
                       viewHolder.txtName.setText(name);
                   }

                   @Override
                   public void onCancelled(DatabaseError databaseError) {

                   }
               });

            }
        };
        recyclerView.setAdapter(firebaseRecyclerAdapter);

    }

    public static class QuestionsViewHolder extends RecyclerView.ViewHolder {

        View mView;

        String my_question,my_uid;

        CircleImageView imgProfile;
        TextView txtName,txtText,txtDate;

        public QuestionsViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
            imgProfile = mView.findViewById(R.id.imgProfile);
            txtName = mView.findViewById(R.id.txtName);
            txtText = mView.findViewById(R.id.txtText);
            txtDate = mView.findViewById(R.id.txtDate);

        }
        public void setQuestion(String question) {
            my_question = question;
        }

        public void setUid(String uid) {
            my_uid = uid;
        }
    }
}
