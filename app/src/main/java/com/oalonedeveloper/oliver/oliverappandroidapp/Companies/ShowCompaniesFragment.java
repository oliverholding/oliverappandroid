package com.oalonedeveloper.oliver.oliverappandroidapp.Companies;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.CareManagerActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.InformalCompanies.AddInformalCompanyActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;


public class ShowCompaniesFragment extends Fragment {

    Button btnAddCompany;
    FirebaseAuth mAuth;
    String currentUid;
    DatabaseReference companyRef;
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_show_companies, container, false);

        btnAddCompany = view.findViewById(R.id.btnAddCompany);

        mAuth = FirebaseAuth.getInstance();
        currentUid = mAuth.getCurrentUser().getUid();

        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        btnAddCompany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),RegisterCompanyActivity.class);
                startActivity(intent);
            }
        });

        displayMyCompanies();


        return view;
    }

    private void showCompanyTypeDialog() {
        final AlertDialog dialog = new AlertDialog.Builder(getActivity()).create();

        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View finance_method = inflater.inflate(R.layout.company_type_selection_dialog,null);

        CardView btnInformalCompany,btnCompany;

        btnInformalCompany = finance_method.findViewById(R.id.btnInformalCompany);
        btnCompany = finance_method.findViewById(R.id.btnCompany);

        btnInformalCompany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddInformalCompanyActivity.class);
                startActivity(intent);
                dialog.dismiss();
            }
        });

        btnCompany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddCompanyActivity.class);
                startActivity(intent);
                dialog.dismiss();
            }
        });

        dialog.setView(finance_method);
        dialog.show();
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

                Picasso.with(getActivity()).load(viewHolder.my_company_image).fit().into(viewHolder.imgCompanyProfile);

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
                        Intent intent = new Intent(getActivity(), CareManagerActivity.class);
                        intent.putExtra("post_key",postKey);
                        startActivity(intent);
                    }
                });

                viewHolder.btnFinanceCompany.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), OliverAppForBusinessActivity.class);
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
        Button btnCompanyManage,btnFinanceCompany;
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
            btnFinanceCompany = mView.findViewById(R.id.btnFinanceCompany);


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
