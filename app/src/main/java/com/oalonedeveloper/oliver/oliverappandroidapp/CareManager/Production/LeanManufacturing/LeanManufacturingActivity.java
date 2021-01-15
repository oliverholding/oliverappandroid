package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Production.LeanManufacturing;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class LeanManufacturingActivity extends AppCompatActivity {

    DatabaseReference companyRef;
    String post_key,product_name,product_image;
    CircleImageView imgProduct;
    TextView txtProductName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lean_manufacturing);

        post_key = getIntent().getExtras().getString("post_key");
        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");
        imgProduct = findViewById(R.id.imgProduct);
        txtProductName = findViewById(R.id.txtProductName);

        companyRef.child(post_key).child("My Products").child("NjpFcCADpcNpv7B60wrPQfX0vQ0316063478981112021153204").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                product_name = dataSnapshot.child("product_name").getValue().toString();
                product_image = dataSnapshot.child("product_image").getValue().toString();

                txtProductName.setText(product_name);
                Picasso.with(LeanManufacturingActivity.this).load(product_image).fit().into(imgProduct);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
