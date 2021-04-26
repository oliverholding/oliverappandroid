package com.oalonedeveloper.oliver.oliverappandroidapp.FinancialManagement.CompanyLendingProduct;

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
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.oalonedeveloper.oliver.oliverappandroidapp.FinancialManagement.LendingProduct.FinancialProductModel;
import com.oalonedeveloper.oliver.oliverappandroidapp.FinancialManagement.LendingProduct.LendingDetailActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.FinancialManagement.LendingProduct.LoanRequestsListActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;


public class CompanyFinancialProductLendingsListFragment extends Fragment {

    String post_key,company_id;
    DatabaseReference financialInstitutionsRef;
    RecyclerView recyclerView;
    Button btnMyRequest;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_company_financial_product_lendings_list, container, false);

        financialInstitutionsRef = FirebaseDatabase.getInstance().getReference().child("Financial Institutions");
        post_key = getActivity().getIntent().getExtras().getString("post_key");
        company_id = getActivity().getIntent().getExtras().getString("company_id");

        recyclerView = view.findViewById(R.id.recyclerView);

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setReverseLayout(false);
        linearLayoutManager.setStackFromEnd(false);
        recyclerView.setLayoutManager(linearLayoutManager);

        btnMyRequest = view.findViewById(R.id.btnMyRequest);

        btnMyRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getActivity(), CompanyLoanRequestsListActivity.class);
                intent.putExtra("post_key",post_key);
                intent.putExtra("company_id",company_id);
                startActivity(intent);
            }
        });

        showFinancialInstitutionLendings();

        return view;
    }

    private void showFinancialInstitutionLendings() {
        Query query = financialInstitutionsRef.child(post_key).child("Company Products").orderByChild("product").equalTo("lending");
        FirebaseRecyclerAdapter<FinancialProductModel, FinancialProductViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<FinancialProductModel, FinancialProductViewHolder>
                (FinancialProductModel.class,R.layout.financial_institution_lending_product_item,FinancialProductViewHolder.class,query) {
            @Override
            protected void populateViewHolder(FinancialProductViewHolder viewHolder, FinancialProductModel model, int position) {
                final String postKey = getRef(position).getKey();
                viewHolder.setProduct(model.getProduct());
                viewHolder.setProduct_img(model.getProduct_img());
                viewHolder.setProduct_name(model.getProduct_name());
                viewHolder.setProduct_short_description(model.getProduct_short_description());

                viewHolder.txtProductName.setText(viewHolder.my_product_name);
                viewHolder.txtProductDescription.setText(viewHolder.my_product_short_description);
                Picasso.with(getActivity()).load(viewHolder.my_product_img).fit().into(viewHolder.imgImage);

                viewHolder.imgBackground.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), CompanyLendingDetailActivity.class);
                        intent.putExtra("company_id",company_id);
                        intent.putExtra("product_key",postKey);
                        intent.putExtra("institution_key",post_key);
                        startActivity(intent);
                    }
                });

            }
        };
        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    public static class FinancialProductViewHolder extends RecyclerView.ViewHolder {
        View mView;
        CircleImageView imgImage;
        TextView txtProductName,txtProductDescription;
        Button imgBackground;
        String my_product,my_product_short_description,my_product_name,my_product_img;

        public FinancialProductViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;

            imgImage = itemView.findViewById(R.id.imgImage);
            txtProductName = itemView.findViewById(R.id.txtProductName);
            txtProductDescription = itemView.findViewById(R.id.txtProductDescription);
            imgBackground = itemView.findViewById(R.id.imgBackground);


        }
        public void setProduct(String product) {
            my_product = product;
        }

        public void setProduct_short_description(String product_short_description) {
            my_product_short_description = product_short_description;
        }

        public void setProduct_name(String product_name) {
            my_product_name = product_name;
        }

        public void setProduct_img(String product_img) {
            my_product_img = product_img;
        }
    }
}
