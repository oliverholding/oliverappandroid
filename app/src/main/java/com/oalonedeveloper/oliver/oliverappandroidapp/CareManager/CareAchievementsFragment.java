package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager;

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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.oalonedeveloper.oliver.oliverappandroidapp.Companies.CompanyCustomersModel;
import com.oalonedeveloper.oliver.oliverappandroidapp.Companies.MyCustomersActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;


public class CareAchievementsFragment extends Fragment {

    String post_key;
    DatabaseReference myCompanyRef;
    RecyclerView recyclerView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_care_achievements, container, false);

        post_key = getActivity().getIntent().getExtras().getString("post_key");

        myCompanyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setReverseLayout(false);
        linearLayoutManager.setStackFromEnd(false);
        recyclerView.setLayoutManager(linearLayoutManager);

        showCompanyAchievements();

        return view;
    }

    private void showCompanyAchievements() {
        Query query = myCompanyRef.child(post_key).child("Achievements").orderByChild("timestamp");
        FirebaseRecyclerAdapter<AchievementsModel, CompanyAchievementsViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<AchievementsModel, CompanyAchievementsViewHolder>
                (AchievementsModel.class, R.layout.company_achievement_item, CompanyAchievementsViewHolder.class, query) {
            @Override
            protected void populateViewHolder(final CompanyAchievementsViewHolder viewHolder, AchievementsModel model, final int position) {
                final String postKey = getRef(position).getKey();
                viewHolder.setMessage(model.getMessage());
                viewHolder.setScore(model.getScore());

                viewHolder.txtMessage.setText(viewHolder.my_message);
                viewHolder.txtExp.setText("Experiencia obtenida: +"+viewHolder.my_exp+" EXP");


            }
        };
        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    public static class CompanyAchievementsViewHolder extends RecyclerView.ViewHolder {
        View mView;
        TextView txtMessage,txtExp;
        String my_message,my_exp;

        public CompanyAchievementsViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;

            txtMessage = mView.findViewById(R.id.txtMessage);
            txtExp = mView.findViewById(R.id.txtExp);

        }

        public void setScore(String score) {

            my_exp = score;
        }

        public void setMessage(String message) {
           my_message = message;
        }

    }
}
