package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Commercialization.CreditsAndTreasury;

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
import com.oalonedeveloper.oliver.oliverappandroidapp.R;

import java.util.Map;

public class CreditAndTreasuryActivity extends AppCompatActivity {


    String post_key;
    DatabaseReference companyRef;
    TextView txtCurrentDebt,txtExpiredDebts,txtTotalDebt;
    double sum1,sum2;

    CardView tab1,tab2,tab3,tab4;
    LinearLayout linearLayout1,linearLayout2,linearLayout3,linearLayout4;
    TextView txtText1,txtText2,txtText3,txtText4;
    Fragment fragment1,fragment2,fragment3,fragment4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit_and_treasury);

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

        fragment1 = new CurrentDebtFragment();
        fragment2 = new ExpiredDebtFragment();
        fragment3 = new CreditTableFragment();
        fragment4 = new CustomerCreditFragment();

        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");

        txtCurrentDebt = findViewById(R.id.txtCurrentDebt);
        txtExpiredDebts = findViewById(R.id.txtExpiredDebts);
        txtTotalDebt = findViewById(R.id.txtTotalDebt);

        companyRef.child(post_key).child("My Bills").orderByChild("state").equalTo("no_paid").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                sum1 = 0;
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Map<String, Object> map = (Map<String, Object>) ds.getValue();
                    Object price = map.get("bill_amount");
                    double total_value = Double.parseDouble(String.valueOf(price));
                    sum1 += total_value;


                    txtCurrentDebt.setText("S/ "+sum1);
                }

                companyRef.child(post_key).child("My Bills").orderByChild("state").equalTo("expired").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        sum2 = 0;
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            Map<String, Object> map = (Map<String, Object>) ds.getValue();
                            Object price = map.get("bill_amount");
                            double total_value = Double.parseDouble(String.valueOf(price));
                            sum2 += total_value;


                            txtExpiredDebts.setText("S/ "+sum2);
                        }

                        double total_debt = sum1+sum2;

                        txtTotalDebt.setText("S/ "+total_debt);

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

    }

}
