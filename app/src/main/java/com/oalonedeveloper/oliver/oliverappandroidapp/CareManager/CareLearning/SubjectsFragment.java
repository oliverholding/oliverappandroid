package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.CareLearning;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;
import com.squareup.picasso.Picasso;
import com.xw.repo.BubbleSeekBar;


public class SubjectsFragment extends Fragment {

    CardView tab1,tab2,tab3,tab4;
    LinearLayout linearLayout1,linearLayout2,linearLayout3,linearLayout4;
    TextView txtText1,txtText2,txtText3,txtText4;
    Fragment fragment1,fragment2,fragment3,fragment4;
    RecyclerView recyclerView;
    DatabaseReference careRef,userRef;
    FirebaseAuth mAuth;
    String currentUid,subject_category;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_subjects, container, false);

        tab1 = view.findViewById(R.id.tab1);
        tab2 = view.findViewById(R.id.tab2);
        tab3 = view.findViewById(R.id.tab3);
        tab4 = view.findViewById(R.id.tab4);

        linearLayout1 = view.findViewById(R.id.linearLayout1);
        linearLayout2 = view.findViewById(R.id.linearLayout2);
        linearLayout3 = view.findViewById(R.id.linearLayout3);
        linearLayout4 = view.findViewById(R.id.linearLayout4);

        txtText1 = view.findViewById(R.id.txtText1);
        txtText2 = view.findViewById(R.id.txtText2);
        txtText3 = view.findViewById(R.id.txtText3);
        txtText4 = view.findViewById(R.id.txtText4);

        recyclerView = view.findViewById(R.id.recyclerView);

        mAuth = FirebaseAuth.getInstance();
        currentUid = mAuth.getCurrentUser().getUid();

        careRef = FirebaseDatabase.getInstance().getReference().child("Care Learning").child("Subjects");
        userRef = FirebaseDatabase.getInstance().getReference().child("Users");

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setReverseLayout(false);
        linearLayoutManager.setStackFromEnd(false);
        recyclerView.setLayoutManager(linearLayoutManager);

        linearLayout1.setBackgroundResource(R.drawable.orange_button_style_ripple);
        txtText1.setTextColor(Color.WHITE);

        subject_category = "marketing";

        tab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                linearLayout1.setBackgroundResource(R.drawable.orange_button_style_ripple);
                txtText1.setTextColor(Color.WHITE);

                linearLayout2.setBackgroundResource(R.drawable.orange_strokes_style);
                txtText2.setTextColor(Color.GRAY);

                linearLayout3.setBackgroundResource(R.drawable.orange_strokes_style);
                txtText3.setTextColor(Color.GRAY);

                linearLayout4.setBackgroundResource(R.drawable.orange_strokes_style);
                txtText4.setTextColor(Color.GRAY);

                subject_category = "marketing";
                showSubjects();


            }
        });

        tab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                linearLayout2.setBackgroundResource(R.drawable.orange_button_style_ripple);
                txtText2.setTextColor(Color.WHITE);

                linearLayout1.setBackgroundResource(R.drawable.orange_strokes_style);
                txtText1.setTextColor(Color.GRAY);

                linearLayout3.setBackgroundResource(R.drawable.orange_strokes_style);
                txtText3.setTextColor(Color.GRAY);

                linearLayout4.setBackgroundResource(R.drawable.orange_strokes_style);
                txtText4.setTextColor(Color.GRAY);

                subject_category = "sales";
                showSubjects();
            }
        });

        tab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                linearLayout3.setBackgroundResource(R.drawable.orange_button_style_ripple);
                txtText3.setTextColor(Color.WHITE);

                linearLayout1.setBackgroundResource(R.drawable.orange_strokes_style);
                txtText1.setTextColor(Color.GRAY);

                linearLayout2.setBackgroundResource(R.drawable.orange_strokes_style);
                txtText2.setTextColor(Color.GRAY);

                linearLayout4.setBackgroundResource(R.drawable.orange_strokes_style);
                txtText4.setTextColor(Color.GRAY);

                subject_category = "business";
                showSubjects();

            }
        });

        tab4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                linearLayout4.setBackgroundResource(R.drawable.orange_button_style_ripple);
                txtText4.setTextColor(Color.WHITE);

                linearLayout1.setBackgroundResource(R.drawable.orange_strokes_style);
                txtText1.setTextColor(Color.GRAY);

                linearLayout2.setBackgroundResource(R.drawable.orange_strokes_style);
                txtText2.setTextColor(Color.GRAY);

                linearLayout3.setBackgroundResource(R.drawable.orange_strokes_style);
                txtText3.setTextColor(Color.GRAY);

                subject_category = "startup";
                showSubjects();

            }
        });

        showSubjects();

        return  view;
    }

    private void showSubjects() {
        Query query = careRef.orderByChild("subject_category").equalTo(subject_category);
        FirebaseRecyclerAdapter<SubjectModel,SubjectViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<SubjectModel, SubjectViewHolder>
                (SubjectModel.class,R.layout.care_subject_item,SubjectViewHolder.class,query) {
            @Override
            protected void populateViewHolder(SubjectViewHolder viewHolder, SubjectModel model, int position) {
                final String postKey = getRef(position).getKey();
                viewHolder.setPosition(model.getPosition());
                viewHolder.setSubject_category(model.getSubject_category());
                viewHolder.setSubject_image(model.getSubject_image());
                viewHolder.setSubject_name(model.getSubject_name());

                viewHolder.txtSubjectName.setText(viewHolder.my_subject_name);
                Picasso.with(getActivity()).load(viewHolder.my_subject_image).fit().into(viewHolder.imgSubject);

                viewHolder.btnSubject.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), VideoListActivity.class);
                        intent.putExtra("postkey",postKey);
                        userRef.child(currentUid).child("Care Learning").child("My Subjects").child(postKey).child("subject_id").setValue(postKey);
                        userRef.child(currentUid).child("Care Learning").child("My Subjects").child(postKey).child("progress").setValue(0);
                        startActivity(intent);
                    }
                });

            }
        };
        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    public static class SubjectViewHolder extends RecyclerView.ViewHolder {

        View mView;
        String my_position,my_subject_category,my_subject_image,my_subject_name;
        ImageView imgSubject;
        TextView txtSubjectName;
        Button btnSubject;
        BubbleSeekBar progressBar;

        public SubjectViewHolder(@NonNull View itemView) {
            super(itemView);

            mView = itemView;
            txtSubjectName = mView.findViewById(R.id.txtSubjectName);
            imgSubject = mView.findViewById(R.id.imgSubject);
            btnSubject = mView.findViewById(R.id.btnSubject);
            progressBar = mView.findViewById(R.id.progressBar);
        }
        public void setPosition(String position) {
            my_position = position;
        }

        public void setSubject_category(String subject_category) {
            my_subject_category = subject_category;
        }

        public void setSubject_image(String subject_image) {
            my_subject_image = subject_image;
        }

        public void setSubject_name(String subject_name) {
            my_subject_name = subject_name;
        }
    }
}
