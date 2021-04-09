package com.oalonedeveloper.oliver.oliverappandroidapp.FinancialManagement.LendingProduct;

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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.oalonedeveloper.oliver.oliverappandroidapp.FinancialManagement.FinancialInstitutionDetailActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.FinancialManagement.FinancialInstitutionForCompaniesDetailActivityActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.FinancialManagement.FinancialInstitutionModel;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;


public class FinancialInstitutionsForCompaniesFragment extends Fragment {

    DatabaseReference financialInstitutionsRef;
    RecyclerView recyclerView;
    String company_id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_financial_institutions_for_companies, container, false);

        company_id = getActivity().getIntent().getExtras().getString("post_key");

        financialInstitutionsRef = FirebaseDatabase.getInstance().getReference().child("Financial Institutions");

        recyclerView = view.findViewById(R.id.recyclerView);

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setReverseLayout(false);
        linearLayoutManager.setStackFromEnd(false);
        recyclerView.setLayoutManager(linearLayoutManager);

        showFinancialInstitutions();

        return view;
    }

    private void showFinancialInstitutions() {
        Query query = financialInstitutionsRef.orderByChild("financial_institution_name");
        FirebaseRecyclerAdapter<FinancialInstitutionModel, FinancialInstitutionsViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<FinancialInstitutionModel, FinancialInstitutionsViewHolder>
                (FinancialInstitutionModel.class,R.layout.financial_institution_item,FinancialInstitutionsViewHolder.class,query) {
            @Override
            protected void populateViewHolder(FinancialInstitutionsViewHolder viewHolder, FinancialInstitutionModel model, int position) {
                final String postKey = getRef(position).getKey();
                viewHolder.setFinancial_institution_background_image(model.getFinancial_institution_background_image());
                viewHolder.setFinancial_institution_image(model.getFinancial_institution_image());
                viewHolder.setFinancial_institution_name(model.getFinancial_institution_name());

                Picasso.with(getActivity()).load(viewHolder.background).fit().into(viewHolder.imgBackground);
                Picasso.with(getActivity()).load(viewHolder.image).centerCrop().fit().into(viewHolder.imgImage);
                viewHolder.txtFinancialInstitutionName.setText(viewHolder.name.toUpperCase());

                viewHolder.btnVisit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), FinancialInstitutionForCompaniesDetailActivityActivity.class);
                        intent.putExtra("company_id",company_id);
                        intent.putExtra("post_key",postKey);
                        startActivity(intent);
                    }
                });

            }
        };
        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    public static class FinancialInstitutionsViewHolder extends RecyclerView.ViewHolder {
        View mView;
        String name,image,background;
        ImageView imgBackground;
        CircleImageView imgImage;
        TextView txtFinancialInstitutionName;
        Button btnVisit;

        public FinancialInstitutionsViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;

            imgBackground = mView.findViewById(R.id.imgBackground);
            imgImage = mView.findViewById(R.id.imgImage);
            txtFinancialInstitutionName = mView.findViewById(R.id.txtFinancialInstitutionName);
            btnVisit = mView.findViewById(R.id.btnVisit);
        }
        public void setFinancial_institution_background_image(String financial_institution_background_image) {
            background = financial_institution_background_image;
        }

        public void setFinancial_institution_image(String financial_institution_image) {
            image = financial_institution_image;
        }

        public void setFinancial_institution_name(String financial_institution_name) {
            name = financial_institution_name;
        }
    }
}
