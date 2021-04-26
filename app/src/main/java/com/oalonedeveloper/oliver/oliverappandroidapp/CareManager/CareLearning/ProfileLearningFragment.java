package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.CareLearning;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;


public class ProfileLearningFragment extends Fragment {

    TextView txtName,txtDate,txtEmail,txtDocumentNumber,txtGender,txtAddress;
    CircleImageView imgProfile;
    DatabaseReference userRef;
    FirebaseAuth mAuth;
    String currentUid,fullname,bth_day,bth_month,bth_year,email,document_number,gender,address,profileimage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile_learning, container, false);

        txtName = view.findViewById(R.id.txtName);
        txtDate = view.findViewById(R.id.txtDate);
        txtEmail = view.findViewById(R.id.txtEmail);
        txtDocumentNumber = view.findViewById(R.id.txtDocumentNumber);
        txtGender = view.findViewById(R.id.txtGender);
        txtAddress = view.findViewById(R.id.txtAddress);
        imgProfile = view.findViewById(R.id.imgProfile);

        userRef = FirebaseDatabase.getInstance().getReference().child("Users");

        mAuth = FirebaseAuth.getInstance();
        currentUid = mAuth.getCurrentUser().getUid();

        userRef.child(currentUid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                fullname = dataSnapshot.child("fullname").getValue().toString();
                bth_day = dataSnapshot.child("bth_day").getValue().toString();
                bth_month = dataSnapshot.child("bth_month").getValue().toString();
                bth_year = dataSnapshot.child("bth_year").getValue().toString();
                email = dataSnapshot.child("email").getValue().toString();
                document_number = dataSnapshot.child("document_number").getValue().toString();
                gender = dataSnapshot.child("gender").getValue().toString();
                address = dataSnapshot.child("address").getValue().toString();
                profileimage = dataSnapshot.child("profileimage").getValue().toString();

                Picasso.with(getActivity()).load(profileimage).fit().into(imgProfile);
                txtName.setText("NOMBRES: "+fullname.toUpperCase());
                txtDate.setText("FECHA DE NACIMIENTO: "+bth_day+"/"+bth_month+"/"+bth_year);
                txtEmail.setText("CORREO: "+email.toUpperCase());
                txtDocumentNumber.setText("Nº DE DOCUMENTO: "+document_number);
                txtGender.setText("SEXO: "+gender.toUpperCase());
                txtAddress.setText("DOMICILIO: "+address.toUpperCase());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return view;
    }
}
