package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Production.ProductionCost;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Production.ProductionOrders.ProductionOrderProductDetailActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.Companies.ProductsModel;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;


public class ProductionMaterialsFragment extends Fragment {

    DatabaseReference companyRef;
    String post_key;
    RecyclerView recyclerView;
    double sum1;
    DecimalFormat decimalFormat;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_production_materials, container, false);

        post_key = getActivity().getIntent().getExtras().getString("post_key");
        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");

        decimalFormat = new DecimalFormat("0.00");

        recyclerView = view.findViewById(R.id.recyclerView);

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        showCompanyProducts();

        return view;
    }

    private void showCompanyProducts() {
        Query query = companyRef.child(post_key).child("My Products").orderByChild("product_stock");
        FirebaseRecyclerAdapter<ProductsModel, CompanyProductsViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<ProductsModel, CompanyProductsViewHolder>
                (ProductsModel.class,R.layout.production_materials_item, CompanyProductsViewHolder.class,query) {
            @Override
            protected void populateViewHolder(final CompanyProductsViewHolder viewHolder, ProductsModel model, int position) {
                final String postKey = getRef(position).getKey();
                viewHolder.setProduct_image(model.getProduct_image());
                viewHolder.setProduct_currency(model.getProduct_currency());
                viewHolder.setProduct_description(model.getProduct_description());
                viewHolder.setProduct_measure(model.getProduct_measure());
                viewHolder.setUid(model.getUid());
                viewHolder.setCode(model.getCode());
                viewHolder.setProduct_name(model.getProduct_name());
                viewHolder.setProduct_price(model.getProduct_price());
                viewHolder.setProduct_stock(model.getProduct_stock());

                viewHolder.txtProductName.setText(viewHolder.my_product_name);
                Picasso.with(getActivity()).load(viewHolder.my_product_image).fit().into(viewHolder.imgProduct);
                final double product_stock_db = Double.parseDouble(viewHolder.my_product_stock);

                companyRef.child(post_key).child("My Products").child(postKey).child("Production Items").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        sum1 = 0;
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            Map<String, Object> map = (Map<String, Object>) ds.getValue();
                            Object item_price = map.get("item_price");
                            Object item_quantity = map.get("item_quantity");
                            String item_price_st = (String) item_price;
                            String item_quantity_st = (String) item_quantity;

                            double item_price_db = Double.parseDouble(item_price_st);
                            double item_quantity_db = Double.parseDouble(item_quantity_st);
                            double total_item = item_price_db*item_quantity_db;
                            sum1 += total_item;

                        }
                        double total_item_stock = sum1*product_stock_db;
                        String total_item_st = decimalFormat.format(total_item_stock);
                        viewHolder.txtCost.setText("S/ "+total_item_st);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


                viewHolder.btnActionButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });

            }
        };
        recyclerView.setAdapter(firebaseRecyclerAdapter);


    }

    public static class CompanyProductsViewHolder extends RecyclerView.ViewHolder {
        View mView;
        String my_product_image,my_product_name,my_product_stock;
        CircleImageView imgProduct;
        TextView txtProductName,txtCost;
        ImageView btnActionButton;

        public CompanyProductsViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;

            imgProduct = mView.findViewById(R.id.imgProduct);
            txtProductName = mView.findViewById(R.id.txtProductName);
            txtCost = mView.findViewById(R.id.txtCost);
            btnActionButton = mView.findViewById(R.id.btnActionButton);

        }
        public void setProduct_image(String product_image) {
            my_product_image = product_image;
        }
        public void setProduct_currency(String product_currency) {

        }
        public void setProduct_description(String product_description) {

        }
        public void setProduct_measure(String product_measure) {

        }
        public void setUid(String uid) {

        }
        public void setCode(String code) {

        }
        public void setProduct_name(String product_name) {
            my_product_name = product_name;
        }
        public void setProduct_price(String product_price) {

        }
        public void setProduct_stock(String product_stock) {
            my_product_stock = product_stock;
        }
    }
}
