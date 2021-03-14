package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Logistic.Storage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Commercialization.Dashboards.CustomerRankingFragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Commercialization.Dashboards.GraphicsFragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Commercialization.Dashboards.ProductRankingFragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.FinancialManagement.FinancialInstitutionsFragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.FinancialManagement.FinancialProductsFragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.FinancialManagement.MyProductsFragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProductKardexActivity extends AppCompatActivity {

    CircleImageView imgProduct;
    TextView txtProductName,txtWarehouseName,txtCurrentStock,txtTotalValue;
    String company_id,product_id,warehouse_id,product_image,product_name,warehouse_name,product_stock,product_price;
    DatabaseReference companyRef;
    CardView tab1,tab2,tab3;
    LinearLayout linearLayout1,linearLayout2,linearLayout3;
    TextView txtText1,txtText2,txtText3;
    Fragment fragment1,fragment2,fragment3;
    DecimalFormat decimalFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_kardex);

        imgProduct = findViewById(R.id.imgProduct);
        txtProductName = findViewById(R.id.txtProductName);
        txtWarehouseName = findViewById(R.id.txtWarehouseName);
        txtCurrentStock = findViewById(R.id.txtCurrentStock);
        txtTotalValue = findViewById(R.id.txtTotalValue);

        company_id = getIntent().getExtras().getString("company_id");
        product_id = getIntent().getExtras().getString("product_id");
        warehouse_id = getIntent().getExtras().getString("warehouse_id");

        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");

        decimalFormat = new DecimalFormat("0.00");

        tab1 = findViewById(R.id.tab1);
        tab2 = findViewById(R.id.tab2);
        tab3 = findViewById(R.id.tab3);
        linearLayout1 = findViewById(R.id.linearLayout1);
        linearLayout2 = findViewById(R.id.linearLayout2);
        linearLayout3 = findViewById(R.id.linearLayout3);
        txtText1 = findViewById(R.id.txtText1);
        txtText2 = findViewById(R.id.txtText2);
        txtText3 = findViewById(R.id.txtText3);

        fragment1 = new ProductKardexSumaryFragment();
        fragment2 = new ProductKardexIncomeAndOutcomesFragment();
        fragment3 = new ProductKardexValuationFragment();

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


            }
        });

        companyRef.child(company_id).child("Warehouses").child(warehouse_id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                warehouse_name = dataSnapshot.child("warehouse_name").getValue().toString();
                txtWarehouseName.setText("ALMACÉN: "+warehouse_name.toUpperCase());

                companyRef.child(company_id).child("Warehouses").child(warehouse_id).child("Products").child(product_id).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        product_image = dataSnapshot.child("product_image").getValue().toString();
                        product_name = dataSnapshot.child("product_name").getValue().toString();
                        product_stock = dataSnapshot.child("product_stock").getValue().toString();
                        product_price = dataSnapshot.child("product_price").getValue().toString();

                        Picasso.with(ProductKardexActivity.this).load(product_image).fit().into(imgProduct);
                        txtProductName.setText(product_name);

                        txtCurrentStock.setText("STOCK TOTAL: "+product_stock);

                        double price = Double.parseDouble(product_price);
                        double stock = Double.parseDouble(product_stock);
                        double valuation = price*stock;
                        String valuation_st = decimalFormat.format(valuation);
                        txtTotalValue.setText("VALOR TOTAL: S/ "+valuation_st);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

}
