package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Production.QualityControl;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Production.ProductionOrders.ChainModel;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

import de.hdodenhof.circleimageview.CircleImageView;


public class QualityControlSummaryFragment extends Fragment {

    DatabaseReference companyRef;
    String post_key;
    RecyclerView recyclerView;
    DecimalFormat decimalFormat;
    int day,month,year;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_quality_control_summary, container, false);

        post_key = getActivity().getIntent().getExtras().getString("post_key");
        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");
        decimalFormat = new DecimalFormat("0.00");

        recyclerView = view.findViewById(R.id.recyclerView);

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        showChainProducts();

        return view;
    }

    private void showChainProducts() {
        Query query = companyRef.child(post_key).child("Production Chain").orderByChild("state").equalTo("ready");
        FirebaseRecyclerAdapter<ChainModel, OrderChainViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<ChainModel, OrderChainViewHolder>
                (ChainModel.class,R.layout.defects_quality_control_item, OrderChainViewHolder.class, query) {
            @Override
            protected void populateViewHolder(final OrderChainViewHolder viewHolder, ChainModel model, int position) {
                final String postKey = getRef(position).getKey();
                viewHolder.setDate(model.getDate());
                viewHolder.setProduct_id(model.getProduct_id());
                viewHolder.setProduct_image(model.getProduct_image());
                viewHolder.setProduct_name(model.getProduct_name());
                viewHolder.setProduct_quantity_production(model.getProduct_quantity_production());
                viewHolder.setState(model.getState());
                viewHolder.setTime(model.getTime());
                viewHolder.setCode(model.getCode());
                viewHolder.setProduct_currency(model.getProduct_currency());
                viewHolder.setProduct_description(model.getProduct_description());
                viewHolder.setProduct_measure(model.getProduct_measure());
                viewHolder.setProduct_price(model.getProduct_price());

                companyRef.child(post_key).child("Production Chain").child(postKey).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChild("Quality Control")) {

                            double quality_control_1 = dataSnapshot.child("Quality Control").child("quality_control_1").getValue(Double.class);
                            double quality_control_2 = dataSnapshot.child("Quality Control").child("quality_control_2").getValue(Double.class);
                            double quality_control_3 = dataSnapshot.child("Quality Control").child("quality_control_3").getValue(Double.class);
                            double quality_control_4 = dataSnapshot.child("Quality Control").child("quality_control_4").getValue(Double.class);
                            double quality_control_5 = dataSnapshot.child("Quality Control").child("quality_control_5").getValue(Double.class);
                            double quality_control_6 = dataSnapshot.child("Quality Control").child("quality_control_6").getValue(Double.class);

                            double total_product = Double.parseDouble(viewHolder.my_product_quantity_production);

                            double shape = (quality_control_1/total_product)*100;
                            String shape_st = decimalFormat.format(shape);
                            viewHolder.txtShape.setText(shape_st+"%");

                            double color = (quality_control_2/total_product)*100;
                            String color_st = decimalFormat.format(color);
                            viewHolder.txtColor.setText(color_st+"%");

                            double smell = (quality_control_3/total_product)*100;
                            String smell_st = decimalFormat.format(smell);
                            viewHolder.txtSmell.setText(smell_st+"%");

                            double flavor = (quality_control_4/total_product)*100;
                            String flavor_st = decimalFormat.format(flavor);
                            viewHolder.txtFlavor.setText(flavor_st+"%");

                            double pacing = (quality_control_5/total_product)*100;
                            String packing_st = decimalFormat.format(pacing);
                            viewHolder.txtPacking.setText(packing_st+"%");

                            double size = (quality_control_6/total_product)*100;
                            String size_st = decimalFormat.format(size);
                            viewHolder.txtSize.setText(size_st+"%");
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


            }
        };
        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    public static class OrderChainViewHolder extends RecyclerView.ViewHolder {

        View mView;
        TextView txtShape,txtColor,txtSmell,txtFlavor,txtPacking,txtSize;
        String my_product_id,my_product_name,my_product_image,my_product_quantity_production,my_state,my_date,my_time,my_code,my_product_currency,my_product_description,my_product_measure,my_product_price;


        public OrderChainViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;

            txtShape = mView.findViewById(R.id.txtShape);
            txtColor = mView.findViewById(R.id.txtColor);
            txtSmell = mView.findViewById(R.id.txtSmell);
            txtFlavor = mView.findViewById(R.id.txtFlavor);
            txtPacking = mView.findViewById(R.id.txtPacking);
            txtSize = mView.findViewById(R.id.txtSize);

        }
        public void setProduct_id(String product_id) {
            my_product_id = product_id;
        }

        public void setProduct_name(String product_name) {
            my_product_name = product_name;
        }

        public void setProduct_image(String product_image) {
            my_product_image = product_image;
        }

        public void setProduct_quantity_production(String product_quantity_production) {
            my_product_quantity_production = product_quantity_production;
        }

        public void setState(String state) {
            my_state = state;
        }

        public void setDate(String date) {
            my_date = date;
        }

        public void setTime(String time) {
            my_time = time;
        }

        public void setCode(String code) {
            my_code = my_date;
        }

        public void setProduct_currency(String product_currency) {
            my_product_currency = product_currency;
        }

        public void setProduct_description(String product_description) {
            my_product_description = product_description;
        }

        public void setProduct_measure(String product_measure) {
            my_product_measure = product_measure;
        }


        public void setProduct_price(String product_price) {
            my_product_price = product_price;
        }
    }
}
