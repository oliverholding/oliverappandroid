package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Production.ProductDatasheet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;
import com.squareup.picasso.Picasso;

public class ProductDatasheetDetailActivity extends AppCompatActivity {

    DatabaseReference companyRef;
    String post_key,product_id,product_image,code,product_name;
    TextView txtProductName,txtProductCode;
    ImageView imgProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_datasheet_detail);


        post_key = getIntent().getExtras().getString("post_key");
        product_id = getIntent().getExtras().getString("product_id");
        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");

        txtProductName = findViewById(R.id.txtProductName);
        txtProductCode = findViewById(R.id.txtProductCode);
        imgProduct = findViewById(R.id.imgProduct);

        companyRef.child(post_key).child("My Products").child(product_id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                product_image = dataSnapshot.child("product_image").getValue().toString();
                code = dataSnapshot.child("code").getValue().toString();
                product_name = dataSnapshot.child("product_name").getValue().toString();

                Picasso.with(ProductDatasheetDetailActivity.this).load(product_image).fit().into(imgProduct);

                txtProductName.setText(product_name);
                txtProductCode.setText("Código: "+code);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
