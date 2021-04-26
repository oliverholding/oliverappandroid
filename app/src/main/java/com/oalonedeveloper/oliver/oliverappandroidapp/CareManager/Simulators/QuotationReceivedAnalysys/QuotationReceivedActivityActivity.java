package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Simulators.QuotationReceivedAnalysys;

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
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Logistic.SupplierEvaluation.SupplierEvaluationPriceFragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Logistic.SupplierEvaluation.SupplierEvaluationSumaryFragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Logistic.SupplierEvaluation.SupplierEvaluationTimeFragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Logistic.SupplierEvaluation.SupplierEvluationQualityFragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Logistic.SupplierEvaluation.SupplierProductEvaluationActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class QuotationReceivedActivityActivity extends AppCompatActivity {

    CardView tab1,tab2,tab3,tab4;
    LinearLayout linearLayout1,linearLayout2,linearLayout3,linearLayout4;
    TextView txtText1,txtText2,txtText3,txtText4;
    Fragment fragment1,fragment2,fragment3,fragment4;
    DatabaseReference companyRef;
    String post_key,product_id;
    CircleImageView imgProduct;
    TextView txtProductName;
    TextView txtPriceWeight,txtQualityWeight,txtTimeWeight;
    int price_weight,quality_weight,time_weight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quotation_received_activity);

        post_key = getIntent().getExtras().getString("post_key");
        product_id = getIntent().getExtras().getString("product_id");
        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");

        txtPriceWeight = findViewById(R.id.txtPriceWeight);
        txtQualityWeight = findViewById(R.id.txtQualityWeight);
        txtTimeWeight = findViewById(R.id.txtTimeWeight);

        imgProduct = findViewById(R.id.imgProduct);
        txtProductName = findViewById(R.id.txtProductName);

        tab1 = findViewById(R.id.tab1);
        tab2 = findViewById(R.id.tab2);
        tab3 = findViewById(R.id.tab3);
        tab4 = findViewById(R.id.tab4);
        linearLayout1 = findViewById(R.id.linearLayout1);
        linearLayout2 = findViewById(R.id.linearLayout2);
        linearLayout3 = findViewById(R.id.linearLayout3);
        linearLayout4 = findViewById(R.id.linearLayout4);
        txtText1 = findViewById(R.id.txtText1);
        txtText2 = findViewById(R.id.txtText2);
        txtText3 = findViewById(R.id.txtText3);
        txtText4 = findViewById(R.id.txtText4);

        fragment1 = new SupplierEvaluationSumaryFragment();
        fragment2 = new SupplierEvaluationPriceFragment();
        fragment3 = new SupplierEvluationQualityFragment();
        fragment4 = new SupplierEvaluationTimeFragment();

        getSupportFragmentManager().beginTransaction().add(R.id.contentFragment,fragment1).commit();
        linearLayout1.setBackgroundResource(R.drawable.orange_button_style_ripple);
        txtText1.setTextColor(Color.WHITE);

        tab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.contentFragment,fragment1).commit();

                linearLayout1.setBackgroundResource(R.drawable.orange_button_style_ripple);
                txtText1.setTextColor(Color.WHITE);

                linearLayout2.setBackgroundResource(R.drawable.orange_strokes_style);
                txtText2.setTextColor(Color.GRAY);

                linearLayout3.setBackgroundResource(R.drawable.orange_strokes_style);
                txtText3.setTextColor(Color.GRAY);

                linearLayout4.setBackgroundResource(R.drawable.orange_strokes_style);
                txtText4.setTextColor(Color.GRAY);


            }
        });

        tab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.contentFragment,fragment2).commit();

                linearLayout2.setBackgroundResource(R.drawable.orange_button_style_ripple);
                txtText2.setTextColor(Color.WHITE);

                linearLayout1.setBackgroundResource(R.drawable.orange_strokes_style);
                txtText1.setTextColor(Color.GRAY);

                linearLayout3.setBackgroundResource(R.drawable.orange_strokes_style);
                txtText3.setTextColor(Color.GRAY);

                linearLayout4.setBackgroundResource(R.drawable.orange_strokes_style);
                txtText4.setTextColor(Color.GRAY);


            }
        });

        tab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.contentFragment,fragment3).commit();

                linearLayout3.setBackgroundResource(R.drawable.orange_button_style_ripple);
                txtText3.setTextColor(Color.WHITE);

                linearLayout1.setBackgroundResource(R.drawable.orange_strokes_style);
                txtText1.setTextColor(Color.GRAY);

                linearLayout2.setBackgroundResource(R.drawable.orange_strokes_style);
                txtText2.setTextColor(Color.GRAY);

                linearLayout4.setBackgroundResource(R.drawable.orange_strokes_style);
                txtText4.setTextColor(Color.GRAY);


            }
        });

        tab4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.contentFragment,fragment4).commit();

                linearLayout4.setBackgroundResource(R.drawable.orange_button_style_ripple);
                txtText4.setTextColor(Color.WHITE);

                linearLayout1.setBackgroundResource(R.drawable.orange_strokes_style);
                txtText1.setTextColor(Color.GRAY);

                linearLayout2.setBackgroundResource(R.drawable.orange_strokes_style);
                txtText2.setTextColor(Color.GRAY);

                linearLayout3.setBackgroundResource(R.drawable.orange_strokes_style);
                txtText3.setTextColor(Color.GRAY);


            }
        });

        companyRef.child(post_key).child("Purchased Items").child(product_id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String product_image = dataSnapshot.child("product_image").getValue().toString();
                String product_name = dataSnapshot.child("product_name").getValue().toString();


                Picasso.with(QuotationReceivedActivityActivity.this).load(product_image).fit().into(imgProduct);
                txtProductName.setText(product_name);

                if (dataSnapshot.child("Evaluation").hasChild("price_weight")) {
                    price_weight = dataSnapshot.child("Evaluation").child("price_weight").getValue(Integer.class);
                    txtPriceWeight.setText(price_weight+"%");
                } else {
                    companyRef.child(post_key).child("Purchased Items").child(product_id).child("Evaluation").child("price_weight").setValue(50);
                }

                if (dataSnapshot.child("Evaluation").hasChild("quality_weight")) {
                    quality_weight = dataSnapshot.child("Evaluation").child("quality_weight").getValue(Integer.class);
                    txtQualityWeight.setText(quality_weight+"%");
                } else {
                    companyRef.child(post_key).child("Purchased Items").child(product_id).child("Evaluation").child("quality_weight").setValue(25);
                }

                if (dataSnapshot.child("Evaluation").hasChild("time_weight")) {
                    time_weight = dataSnapshot.child("Evaluation").child("time_weight").getValue(Integer.class);
                    txtTimeWeight.setText(time_weight+"%");
                } else {
                    companyRef.child(post_key).child("Purchased Items").child(product_id).child("Evaluation").child("time_weight").setValue(25);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

}
