package com.oalonedeveloper.oliver.oliverappandroidapp.Companies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.CareManagerActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyCompaniesActivity extends AppCompatActivity {

    Button btnAddCompany;
    FirebaseAuth mAuth;
    String currentUid;
    DatabaseReference companyRef;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_companies);

        btnAddCompany = findViewById(R.id.btnAddCompany);

        mAuth = FirebaseAuth.getInstance();
        currentUid = mAuth.getCurrentUser().getUid();

        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        displayMyCompanies();


        btnAddCompany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyCompaniesActivity.this, AddCompanyActivity.class);
                startActivity(intent);
            }
        });
    }

    private void displayMyCompanies() {
        Query myPostQuery = companyRef.orderByChild("uid").startAt(currentUid).endAt(currentUid+"\uf8ff");
        FirebaseRecyclerAdapter<CompaniesModel, myPostViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<CompaniesModel, myPostViewHolder>
                (CompaniesModel.class,R.layout.company_item,myPostViewHolder.class,myPostQuery) {
            @Override
            protected void populateViewHolder(myPostViewHolder viewHolder, CompaniesModel model, final int position) {
                final String postKey = getRef(position).getKey();
                viewHolder.setCompany_name(model.getCompany_name());
                viewHolder.setCompany_ruc(model.getCompany_ruc());
                viewHolder.setCompany_verification(model.getCompany_verification());
                viewHolder.setCompany_image(model.getCompany_image());

                Picasso.with(MyCompaniesActivity.this).load(viewHolder.my_company_image).fit().into(viewHolder.imgCompanyProfile);

                viewHolder.txtCompanyName.setText(viewHolder.my_company_name);
                viewHolder.txtCompanyRuc.setText(viewHolder.my_company_ruc);
                if(viewHolder.my_company_verification.equals("progress")) {
                    viewHolder.txtCompanyVerification.setText("Verificación: En Proceso");
                    viewHolder.imgCompanyVerification.setImageResource(R.drawable.espera);
                }
                else if (viewHolder.my_company_verification.equals("true")) {
                    viewHolder.txtCompanyVerification.setText("Verificación: Verificado con éxito");
                    viewHolder.imgCompanyVerification.setImageResource(R.drawable.check);
                }
                else if (viewHolder.my_company_verification.equals("false")) {
                    viewHolder.txtCompanyVerification.setText("Verificación: No Verificado");
                    viewHolder.imgCompanyVerification.setImageResource(R.drawable.error);
                }

                viewHolder.btnCompanyManage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MyCompaniesActivity.this, CareManagerActivity.class);
                        intent.putExtra("post_key",postKey);
                        startActivity(intent);
                    }
                });
            }
        };
        recyclerView.setAdapter(firebaseRecyclerAdapter);

    }

    public static class myPostViewHolder extends RecyclerView.ViewHolder {

        View mView;
        CircleImageView imgCompanyProfile;
        TextView txtCompanyName,txtCompanyRuc,txtCompanyVerification;
        ImageView imgCompanyVerification;
        Button btnCompanyManage;
        String my_company_name,my_company_ruc,my_company_verification,my_company_image;


        public myPostViewHolder(View itemView) {
            super(itemView);

            mView = itemView;

            imgCompanyProfile = mView.findViewById(R.id.imgCompanyProfile);
            txtCompanyName = mView.findViewById(R.id.txtCompanyName);
            txtCompanyRuc = mView.findViewById(R.id.txtCompanyRuc);
            txtCompanyVerification = mView.findViewById(R.id.txtCompanyVerification);
            imgCompanyVerification = mView.findViewById(R.id.imgCompanyVerification);
            btnCompanyManage = mView.findViewById(R.id.btnCompanyManage);


        }

        public void setCompany_name(String company_name) {
            my_company_name = company_name;
        }


        public void setCompany_ruc(String company_ruc) {
            my_company_ruc = company_ruc;
        }


        public void setCompany_verification(String company_verification) {
            my_company_verification = company_verification;
        }

        public void setCompany_image(String company_image) {
            my_company_image = company_image;
        }
    }
}