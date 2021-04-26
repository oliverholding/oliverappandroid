package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Commercialization.CustomerSupport;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;


public class ResultCustomerSupportFragment extends Fragment {

    TextView txtAns001,txtAns002,txtAns003,txtAns004,txtAns005,txtAns006,txtAns007,txtAns008,txtAns009,txtAns010,txtAns011,txtAns012,txtAns013,txtAns014,txtAns015,txtAns016,txtAns017,txtAns018,txtAns019,txtAns020;
    String post_key,answer1,answer2,answer3,answer4,answer5,answer6,answer7,answer8,answer9,answer10,answer11,answer12,answer13,answer14,answer15A,answer15B,answer15C,answer15D,answer15E,answer15F,answer16,answer17,
            answer18,answer19A,answer19B,answer19C,answer19D,answer19E,answer19F,answer19G,answer19H,answer20;
    DatabaseReference companyRef;
    Button btnTryAgain;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_result_customer_support, container, false);

        post_key = getActivity().getIntent().getExtras().getString("post_key");
        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");

        answer15A = "";
        answer15B = "";
        answer15C = "";
        answer15D = "";
        answer15E = "";
        answer15F = "";

        answer19A = "";
        answer19B = "";
        answer19C = "";
        answer19D = "";
        answer19E = "";
        answer19F = "";
        answer19G = "";
        answer19H = "";

        txtAns001 = view.findViewById(R.id.txtAns001);
        txtAns002 = view.findViewById(R.id.txtAns002);
        txtAns003 = view.findViewById(R.id.txtAns003);
        txtAns004 = view.findViewById(R.id.txtAns004);
        txtAns005 = view.findViewById(R.id.txtAns005);
        txtAns006 = view.findViewById(R.id.txtAns006);
        txtAns007 = view.findViewById(R.id.txtAns007);
        txtAns008 = view.findViewById(R.id.txtAns008);
        txtAns009 = view.findViewById(R.id.txtAns009);
        txtAns010 = view.findViewById(R.id.txtAns010);
        txtAns011 = view.findViewById(R.id.txtAns011);
        txtAns012 = view.findViewById(R.id.txtAns012);
        txtAns013 = view.findViewById(R.id.txtAns013);
        txtAns014 = view.findViewById(R.id.txtAns014);
        txtAns015 = view.findViewById(R.id.txtAns015);
        txtAns016 = view.findViewById(R.id.txtAns016);
        txtAns017 = view.findViewById(R.id.txtAns017);
        txtAns018 = view.findViewById(R.id.txtAns018);
        txtAns019 = view.findViewById(R.id.txtAns019);
        txtAns020 = view.findViewById(R.id.txtAns020);

        btnTryAgain = view.findViewById(R.id.btnTryAgain);

        companyRef.child(post_key).child("Module 6").child("Questions Customer Support").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                answer1 = dataSnapshot.child("answer1").getValue().toString();
                txtAns001.setText(answer1);
                answer2 = dataSnapshot.child("answer2").getValue().toString();
                txtAns002.setText(answer2);
                answer3 = dataSnapshot.child("answer3").getValue().toString();
                txtAns003.setText(answer3);
                answer4 = dataSnapshot.child("answer4").getValue().toString();
                txtAns004.setText(answer4);
                answer5 = dataSnapshot.child("answer5").getValue().toString();
                txtAns005.setText(answer5);
                answer6 = dataSnapshot.child("answer6").getValue().toString();
                txtAns006.setText(answer6);
                answer7 = dataSnapshot.child("answer7").getValue().toString();
                txtAns007.setText(answer7);
                answer8 = dataSnapshot.child("answer8").getValue().toString();
                txtAns008.setText(answer8);
                answer9 = dataSnapshot.child("answer9").getValue().toString();
                txtAns009.setText(answer9);
                answer10 = dataSnapshot.child("answer10").getValue().toString();
                txtAns010.setText(answer10);
                answer11 = dataSnapshot.child("answer11").getValue().toString();
                txtAns011.setText(answer11);
                answer12 = dataSnapshot.child("answer12").getValue().toString();
                txtAns012.setText(answer12);
                answer13 = dataSnapshot.child("answer13").getValue().toString();
                txtAns013.setText(answer13);
                answer14 = dataSnapshot.child("answer14").getValue().toString();
                txtAns014.setText(answer14);
                if (dataSnapshot.hasChild("answer15A")) {
                    answer15A = dataSnapshot.child("answer15A").getValue().toString();
                }
                if (dataSnapshot.hasChild("answer15B")) {
                    answer15B = dataSnapshot.child("answer15B").getValue().toString();
                }
                if (dataSnapshot.hasChild("answer15C")) {
                    answer15C = dataSnapshot.child("answer15C").getValue().toString();
                }
                if (dataSnapshot.hasChild("answer15D")) {
                    answer15D = dataSnapshot.child("answer15D").getValue().toString();
                }
                if (dataSnapshot.hasChild("answer15E")) {
                    answer15E = dataSnapshot.child("answer15E").getValue().toString();
                }
                if (dataSnapshot.hasChild("answer15F")) {
                    answer15F = dataSnapshot.child("answer15F").getValue().toString();
                }
                txtAns015.setText(answer15A+" "+answer15B+" "+answer15C+" "+answer15D+" "+answer15E+" "+answer15F);
                answer16 = dataSnapshot.child("answer16").getValue().toString();
                txtAns016.setText(answer16);
                answer17 = dataSnapshot.child("answer17").getValue().toString();
                txtAns017.setText(answer17);
                answer18 = dataSnapshot.child("answer18").getValue().toString();
                txtAns018.setText(answer18);
                if (dataSnapshot.hasChild("answer19A")) {
                    answer19A = dataSnapshot.child("answer19A").getValue().toString();
                }
                if (dataSnapshot.hasChild("answer19B")) {
                    answer19B = dataSnapshot.child("answer19B").getValue().toString();
                }
                if (dataSnapshot.hasChild("answer19C")) {
                    answer19C = dataSnapshot.child("answer19C").getValue().toString();
                }
                if (dataSnapshot.hasChild("answer19D")) {
                    answer19D = dataSnapshot.child("answer19D").getValue().toString();
                }
                if (dataSnapshot.hasChild("answer19E")) {
                    answer19E = dataSnapshot.child("answer19E").getValue().toString();
                }
                if (dataSnapshot.hasChild("answer19F")) {
                    answer19F = dataSnapshot.child("answer19F").getValue().toString();
                }
                if (dataSnapshot.hasChild("answer19G")) {
                    answer19G = dataSnapshot.child("answer19G").getValue().toString();
                }
                if (dataSnapshot.hasChild("answer19H")) {
                    answer19H = dataSnapshot.child("answer19H").getValue().toString();
                }
                txtAns019.setText(answer19A+" "+answer19B+" "+answer19C+" "+answer19D+" "+answer19E+" "+answer19F+" "+answer19G+" "+answer19H);
                answer20 = dataSnapshot.child("answer20").getValue().toString();
                txtAns020.setText(answer20);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        btnTryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                companyRef.child(post_key).child("Module 6").child("Questions Customer Support").removeValue();
                Intent intent = new Intent(getActivity(), CustomerSuportActivity.class);
                intent.putExtra("post_key",post_key);
                startActivity(intent);
            }
        });


        return view;
    }
}
