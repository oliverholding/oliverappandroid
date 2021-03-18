package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Simulators.GanttChart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Commercialization.Dashboards.CustomerRankingFragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Commercialization.Dashboards.GraphicsFragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Commercialization.Dashboards.ProductRankingFragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class GantChartActivity extends AppCompatActivity {

    DatabaseReference companyRef;
    String post_key,product_id;
    TextView txtProductName;
    String product_image,product_name;
    CircleImageView imgProduct;

    CardView tab1;
    LinearLayout linearLayout1;
    TextView txtText1;
    Fragment fragment1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gant_chart);

        post_key = getIntent().getExtras().getString("post_key");
        product_id = getIntent().getExtras().getString("product_id");
        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");

        txtProductName = findViewById(R.id.txtProductName);
        imgProduct = findViewById(R.id.imgProduct);


        tab1 = findViewById(R.id.tab1);


        linearLayout1 = findViewById(R.id.linearLayout1);


        txtText1 = findViewById(R.id.txtText1);


        fragment1 = new WorkChargeActivitiesFragment();

        getSupportFragmentManager().beginTransaction().add(R.id.contentFragment,fragment1).commit();
        linearLayout1.setBackgroundResource(R.drawable.orange_button_style_ripple);
        txtText1.setTextColor(Color.WHITE);

        tab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.contentFragment,fragment1).commit();

                linearLayout1.setBackgroundResource(R.drawable.orange_button_style_ripple);
                txtText1.setTextColor(Color.WHITE);



            }
        });


        companyRef.child(post_key).child("My Products").child(product_id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                product_image = dataSnapshot.child("product_image").getValue().toString();
                product_name = dataSnapshot.child("product_name").getValue().toString();

                Picasso.with(GantChartActivity.this).load(product_image).fit().into(imgProduct);
                txtProductName.setText(product_name);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}
