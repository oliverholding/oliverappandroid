package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Logistic.DemmandPlanning;

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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.oalonedeveloper.oliver.oliverappandroidapp.Companies.ProductsModel;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;
import in.galaxyofandroid.spinerdialog.OnSpinerItemClick;
import in.galaxyofandroid.spinerdialog.SpinnerDialog;


public class SecurityStockFragment extends Fragment {

    DatabaseReference companyRef;
    String post_key;
    RecyclerView recyclerView;
    int day,month,year;

    ArrayList<String> frequency_arr =new ArrayList<>();
    SpinnerDialog frequencyDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_security_stock, container, false);

        post_key = getActivity().getIntent().getExtras().getString("post_key");
        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");

        Date date= new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        day = cal.get(Calendar.DAY_OF_MONTH);
        month = cal.get(Calendar.MONTH)+1;
        year = cal.get(Calendar.YEAR);

        frequency_arr.add("Mensual");frequency_arr.add("Quincenal");frequency_arr.add("Semanal");frequency_arr.add("Diario");
        

        recyclerView = view.findViewById(R.id.recyclerView);

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        showCompanyProducts();

        return view;
    }

    String realPostKey;

    private void showCompanyProducts() {
        Query query = companyRef.child(post_key).child("My Products").orderByChild(year+""+month+"quantity");
        FirebaseRecyclerAdapter<ProductsModel, CompanyProductsViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<ProductsModel, CompanyProductsViewHolder>
                (ProductsModel.class,R.layout.product_month_stock_item, CompanyProductsViewHolder.class,query) {
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

                viewHolder.btnSecurityStock.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        frequencyDialog.showSpinerDialog();
                        realPostKey = postKey;
                    }
                });

                companyRef.child(post_key).child("My Products").child(postKey).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChild("security_stock_frequency")) {
                            String security_stock_frequency = dataSnapshot.child("security_stock_frequency").getValue().toString();
                            viewHolder.txtSecurityStockFrequency.setText(security_stock_frequency);

                        } else {
                            companyRef.child(post_key).child("My Products").child(postKey).child("security_stock_frequency").setValue("Mensual");

                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                frequencyDialog = new SpinnerDialog(getActivity(),frequency_arr, "Selecciona la Frecuencia");
                frequencyDialog.bindOnSpinerListener(new OnSpinerItemClick() {
                    @Override
                    public void onClick(String item2, int position2) {
                        if (item2.equals("Mensual")) {
                            companyRef.child(post_key).child("My Products").child(realPostKey).child("security_stock_frequency").setValue("Mensual");
                        }
                        if (item2.equals("Quincenal")) {
                            companyRef.child(post_key).child("My Products").child(realPostKey).child("security_stock_frequency").setValue("Quincenal");
                        }
                        if (item2.equals("Semanal")) {
                            companyRef.child(post_key).child("My Products").child(realPostKey).child("security_stock_frequency").setValue("Semanal");
                        }
                        if (item2.equals("Diario")) {
                            companyRef.child(post_key).child("My Products").child(realPostKey).child("security_stock_frequency").setValue("Diario");
                        }
                    }
                });
            }
        };
        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    public static class CompanyProductsViewHolder extends RecyclerView.ViewHolder {
        View mView;
        String my_product_image,my_product_name,my_product_stock;
        CircleImageView imgProduct,btnSecurityStock;
        TextView txtProductName,txtSecurityStockFrequency;

        public CompanyProductsViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;

            imgProduct = mView.findViewById(R.id.imgProduct);
            txtProductName = mView.findViewById(R.id.txtProductName);
            txtSecurityStockFrequency = mView.findViewById(R.id.txtSecurityStockFrequency);
            btnSecurityStock = mView.findViewById(R.id.btnSecurityStock);

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
