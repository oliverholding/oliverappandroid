package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.BusinessPlan.CommercialPlan;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hsalf.smileyrating.SmileyRating;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.BusinessPlan.OperationPlan.OperationPlanActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.StrategicDirection.Vision.VisionActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;

import java.util.Map;

import es.dmoral.toasty.Toasty;

public class CommercialPlanActivity extends AppCompatActivity {

    DatabaseReference companyRef;
    String post_key;
    TextView txtSegment1,txtSegment2,txtSegment3,txtProfileSegment1,txtProfileSegment2,txtProfileSegment3,txtStrategy;
    ImageView btnSegment1,btnSegment2,btnSegment3,btnCompany1,btnCompany2,btnCompany3,btnCompany4,btnStrategy;
    TextView item1_1,item1_2,item1_3,item1_4,item2_1,item2_2,item2_3,item2_4,item3_1,item3_2,item3_3,item3_4,item4_1,item4_2,item4_3,item4_4,item5_1,item5_2,item5_3,item5_4,item6_1,item6_2,item6_3,item6_4,item7_1,item7_2,item7_3,item7_4;
    int sum1,sum2,sum3,sum4,points_db_1,points_db_2,points_db_3,points_db_4;
    Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commercial_plan);

        post_key = getIntent().getExtras().getString("post_key");
        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");

        txtSegment1 = findViewById(R.id.txtSegment1);
        txtSegment2 = findViewById(R.id.txtSegment2);
        txtSegment3 = findViewById(R.id.txtSegment3);
        txtProfileSegment1 = findViewById(R.id.txtProfileSegment1);
        txtProfileSegment2 = findViewById(R.id.txtProfileSegment2);
        txtProfileSegment3 = findViewById(R.id.txtProfileSegment3);
        btnSegment1 = findViewById(R.id.btnSegment1);
        btnSegment2 = findViewById(R.id.btnSegment2);
        btnSegment3 = findViewById(R.id.btnSegment3);

        btnCompany1 = findViewById(R.id.btnCompany1);
        btnCompany2 = findViewById(R.id.btnCompany2);
        btnCompany3 = findViewById(R.id.btnCompany3);
        btnCompany4 = findViewById(R.id.btnCompany4);

        item1_1 = findViewById(R.id.item1_1);
        item1_2 = findViewById(R.id.item1_2);
        item1_3 = findViewById(R.id.item1_3);
        item1_4 = findViewById(R.id.item1_4);

        item2_1 = findViewById(R.id.item2_1);
        item2_2 = findViewById(R.id.item2_2);
        item2_3 = findViewById(R.id.item2_3);
        item2_4 = findViewById(R.id.item2_4);

        item3_1 = findViewById(R.id.item3_1);
        item3_2 = findViewById(R.id.item3_2);
        item3_3 = findViewById(R.id.item3_3);
        item3_4 = findViewById(R.id.item3_4);

        item4_1 = findViewById(R.id.item4_1);
        item4_2 = findViewById(R.id.item4_2);
        item4_3 = findViewById(R.id.item4_3);
        item4_4 = findViewById(R.id.item4_4);

        item5_1 = findViewById(R.id.item5_1);
        item5_2 = findViewById(R.id.item5_2);
        item5_3 = findViewById(R.id.item5_3);
        item5_4 = findViewById(R.id.item5_4);

        item6_1 = findViewById(R.id.item6_1);
        item6_2 = findViewById(R.id.item6_2);
        item6_3 = findViewById(R.id.item6_3);
        item6_4 = findViewById(R.id.item6_4);

        item7_1 = findViewById(R.id.item7_1);
        item7_2 = findViewById(R.id.item7_2);
        item7_3 = findViewById(R.id.item7_3);
        item7_4 = findViewById(R.id.item7_4);

        btnStrategy = findViewById(R.id.btnStrategy);
        txtStrategy = findViewById(R.id.txtStrategy);

        btnNext = findViewById(R.id.btnNext);


        companyRef.child(post_key).child("Business Plan").child("Commercial Plan").child("item_1").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild("segment")) {
                    String value = dataSnapshot.child("segment").getValue().toString();
                    txtSegment1.setText(value);
                }
                if (dataSnapshot.hasChild("profile_segment")) {
                    String value = dataSnapshot.child("profile_segment").getValue().toString();
                    txtProfileSegment1.setText(value);
                }
                companyRef.child(post_key).child("Business Plan").child("Commercial Plan").child("item_2").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChild("segment")) {
                            String value = dataSnapshot.child("segment").getValue().toString();
                            txtSegment2.setText(value);
                        }
                        if (dataSnapshot.hasChild("profile_segment")) {
                            String value = dataSnapshot.child("profile_segment").getValue().toString();
                            txtProfileSegment2.setText(value);
                        }

                        companyRef.child(post_key).child("Business Plan").child("Commercial Plan").child("item_3").addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                if (dataSnapshot.hasChild("segment")) {
                                    String value = dataSnapshot.child("segment").getValue().toString();
                                    txtSegment3.setText(value);
                                }
                                if (dataSnapshot.hasChild("profile_segment")) {
                                    String value = dataSnapshot.child("profile_segment").getValue().toString();
                                    txtProfileSegment3.setText(value);
                                }
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

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        companyRef.child(post_key).child("Business Plan").child("Commercial Plan").child("Evaluation").child("company_1").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    if (dataSnapshot.child("item_1").hasChild("score")) {
                        int value = dataSnapshot.child("item_1").child("score").getValue(Integer.class);
                        if (value == 1) {
                            item1_1.setText("Deficiente");
                        }
                        if (value == 2) {
                            item1_1.setText("Regular");
                        }
                        if (value == 3) {
                            item1_1.setText("Bueno");
                        }
                        if (value == 4) {
                            item1_1.setText("Muy bueno");
                        }
                        if (value == 5) {
                            item1_1.setText("Excelente");
                        }
                    }
                    if (dataSnapshot.child("item_2").hasChild("score")) {
                        int value = dataSnapshot.child("item_2").child("score").getValue(Integer.class);
                        if (value == 1) {
                            item2_1.setText("Deficiente");
                        }
                        if (value == 2) {
                            item2_1.setText("Regular");
                        }
                        if (value == 3) {
                            item2_1.setText("Bueno");
                        }
                        if (value == 4) {
                            item2_1.setText("Muy bueno");
                        }
                        if (value == 5) {
                            item2_1.setText("Excelente");
                        }
                    }
                    if (dataSnapshot.child("item_3").hasChild("score")) {
                        int value = dataSnapshot.child("item_3").child("score").getValue(Integer.class);
                        if (value == 1) {
                            item3_1.setText("Deficiente");
                        }
                        if (value == 2) {
                            item3_1.setText("Regular");
                        }
                        if (value == 3) {
                            item3_1.setText("Bueno");
                        }
                        if (value == 4) {
                            item3_1.setText("Muy bueno");
                        }
                        if (value == 5) {
                            item3_1.setText("Excelente");
                        }
                    }
                    if (dataSnapshot.child("item_4").hasChild("score")) {
                        int value = dataSnapshot.child("item_4").child("score").getValue(Integer.class);
                        if (value == 1) {
                            item4_1.setText("Deficiente");
                        }
                        if (value == 2) {
                            item4_1.setText("Regular");
                        }
                        if (value == 3) {
                            item4_1.setText("Bueno");
                        }
                        if (value == 4) {
                            item4_1.setText("Muy bueno");
                        }
                        if (value == 5) {
                            item4_1.setText("Excelente");
                        }
                    }
                    if (dataSnapshot.child("item_5").hasChild("score")) {
                        int value = dataSnapshot.child("item_5").child("score").getValue(Integer.class);
                        if (value == 1) {
                            item5_1.setText("Deficiente");
                        }
                        if (value == 2) {
                            item5_1.setText("Regular");
                        }
                        if (value == 3) {
                            item5_1.setText("Bueno");
                        }
                        if (value == 4) {
                            item5_1.setText("Muy bueno");
                        }
                        if (value == 5) {
                            item5_1.setText("Excelente");
                        }
                    }
                    if (dataSnapshot.child("item_6").hasChild("score")) {
                        int value = dataSnapshot.child("item_6").child("score").getValue(Integer.class);
                        if (value == 1) {
                            item6_1.setText("Deficiente");
                        }
                        if (value == 2) {
                            item6_1.setText("Regular");
                        }
                        if (value == 3) {
                            item6_1.setText("Bueno");
                        }
                        if (value == 4) {
                            item6_1.setText("Muy bueno");
                        }
                        if (value == 5) {
                            item6_1.setText("Excelente");
                        }
                    }


                }

                sum1 = 0;
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Map<Double, Object> map = (Map<Double, Object>) ds.getValue();
                    Object score = map.get("score");
                    double d = Double.parseDouble(String.valueOf(score));
                    sum1 += d;

                    points_db_1 = sum1/6;


                    if (points_db_1 == 1) {
                       item7_1.setText("Deficiente");
                    }
                    if (points_db_1 == 2) {
                        item7_1.setText("Regular");
                    }
                    if (points_db_1 == 3) {
                        item7_1.setText("Bueno");
                    }
                    if (points_db_1 == 4) {
                        item7_1.setText("Muy Bueno");
                    }
                    if (points_db_1 == 5) {
                        item7_1.setText("Excelente");
                    }

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        companyRef.child(post_key).child("Business Plan").child("Commercial Plan").child("Evaluation").child("company_2").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    if (dataSnapshot.child("item_1").hasChild("score")) {
                        int value = dataSnapshot.child("item_1").child("score").getValue(Integer.class);
                        if (value == 1) {
                            item1_2.setText("Deficiente");
                        }
                        if (value == 2) {
                            item1_2.setText("Regular");
                        }
                        if (value == 3) {
                            item1_2.setText("Bueno");
                        }
                        if (value == 4) {
                            item1_2.setText("Muy bueno");
                        }
                        if (value == 5) {
                            item1_2.setText("Excelente");
                        }
                    }
                    if (dataSnapshot.child("item_2").hasChild("score")) {
                        int value = dataSnapshot.child("item_2").child("score").getValue(Integer.class);
                        if (value == 1) {
                            item2_2.setText("Deficiente");
                        }
                        if (value == 2) {
                            item2_2.setText("Regular");
                        }
                        if (value == 3) {
                            item2_2.setText("Bueno");
                        }
                        if (value == 4) {
                            item2_2.setText("Muy bueno");
                        }
                        if (value == 5) {
                            item2_2.setText("Excelente");
                        }
                    }
                    if (dataSnapshot.child("item_3").hasChild("score")) {
                        int value = dataSnapshot.child("item_3").child("score").getValue(Integer.class);
                        if (value == 1) {
                            item3_2.setText("Deficiente");
                        }
                        if (value == 2) {
                            item3_2.setText("Regular");
                        }
                        if (value == 3) {
                            item3_2.setText("Bueno");
                        }
                        if (value == 4) {
                            item3_2.setText("Muy bueno");
                        }
                        if (value == 5) {
                            item3_2.setText("Excelente");
                        }
                    }
                    if (dataSnapshot.child("item_4").hasChild("score")) {
                        int value = dataSnapshot.child("item_4").child("score").getValue(Integer.class);
                        if (value == 1) {
                            item4_2.setText("Deficiente");
                        }
                        if (value == 2) {
                            item4_2.setText("Regular");
                        }
                        if (value == 3) {
                            item4_2.setText("Bueno");
                        }
                        if (value == 4) {
                            item4_2.setText("Muy bueno");
                        }
                        if (value == 5) {
                            item4_2.setText("Excelente");
                        }
                    }
                    if (dataSnapshot.child("item_5").hasChild("score")) {
                        int value = dataSnapshot.child("item_5").child("score").getValue(Integer.class);
                        if (value == 1) {
                            item5_2.setText("Deficiente");
                        }
                        if (value == 2) {
                            item5_2.setText("Regular");
                        }
                        if (value == 3) {
                            item5_2.setText("Bueno");
                        }
                        if (value == 4) {
                            item5_2.setText("Muy bueno");
                        }
                        if (value == 5) {
                            item5_2.setText("Excelente");
                        }
                    }
                    if (dataSnapshot.child("item_6").hasChild("score")) {
                        int value = dataSnapshot.child("item_6").child("score").getValue(Integer.class);
                        if (value == 1) {
                            item6_2.setText("Deficiente");
                        }
                        if (value == 2) {
                            item6_2.setText("Regular");
                        }
                        if (value == 3) {
                            item6_2.setText("Bueno");
                        }
                        if (value == 4) {
                            item6_2.setText("Muy bueno");
                        }
                        if (value == 5) {
                            item6_2.setText("Excelente");
                        }
                    }


                }

                sum2 = 0;
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Map<Double, Object> map = (Map<Double, Object>) ds.getValue();
                    Object score = map.get("score");
                    double d = Double.parseDouble(String.valueOf(score));
                    sum2 += d;

                    points_db_2 = sum2/6;


                    if (points_db_2 == 1) {
                        item7_2.setText("Deficiente");
                    }
                    if (points_db_2 == 2) {
                        item7_2.setText("Regular");
                    }
                    if (points_db_2 == 3) {
                        item7_2.setText("Bueno");
                    }
                    if (points_db_2 == 4) {
                        item7_2.setText("Muy Bueno");
                    }
                    if (points_db_2 == 5) {
                        item7_2.setText("Excelente");
                    }

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        companyRef.child(post_key).child("Business Plan").child("Commercial Plan").child("Evaluation").child("company_3").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    if (dataSnapshot.child("item_1").hasChild("score")) {
                        int value = dataSnapshot.child("item_1").child("score").getValue(Integer.class);
                        if (value == 1) {
                            item1_3.setText("Deficiente");
                        }
                        if (value == 2) {
                            item1_3.setText("Regular");
                        }
                        if (value == 3) {
                            item1_3.setText("Bueno");
                        }
                        if (value == 4) {
                            item1_3.setText("Muy bueno");
                        }
                        if (value == 5) {
                            item1_3.setText("Excelente");
                        }
                    }
                    if (dataSnapshot.child("item_2").hasChild("score")) {
                        int value = dataSnapshot.child("item_2").child("score").getValue(Integer.class);
                        if (value == 1) {
                            item2_3.setText("Deficiente");
                        }
                        if (value == 2) {
                            item2_3.setText("Regular");
                        }
                        if (value == 3) {
                            item2_3.setText("Bueno");
                        }
                        if (value == 4) {
                            item2_3.setText("Muy bueno");
                        }
                        if (value == 5) {
                            item2_3.setText("Excelente");
                        }
                    }
                    if (dataSnapshot.child("item_3").hasChild("score")) {
                        int value = dataSnapshot.child("item_3").child("score").getValue(Integer.class);
                        if (value == 1) {
                            item3_3.setText("Deficiente");
                        }
                        if (value == 2) {
                            item3_3.setText("Regular");
                        }
                        if (value == 3) {
                            item3_3.setText("Bueno");
                        }
                        if (value == 4) {
                            item3_3.setText("Muy bueno");
                        }
                        if (value == 5) {
                            item3_3.setText("Excelente");
                        }
                    }
                    if (dataSnapshot.child("item_4").hasChild("score")) {
                        int value = dataSnapshot.child("item_4").child("score").getValue(Integer.class);
                        if (value == 1) {
                            item4_3.setText("Deficiente");
                        }
                        if (value == 2) {
                            item4_3.setText("Regular");
                        }
                        if (value == 3) {
                            item4_3.setText("Bueno");
                        }
                        if (value == 4) {
                            item4_3.setText("Muy bueno");
                        }
                        if (value == 5) {
                            item4_3.setText("Excelente");
                        }
                    }
                    if (dataSnapshot.child("item_5").hasChild("score")) {
                        int value = dataSnapshot.child("item_5").child("score").getValue(Integer.class);
                        if (value == 1) {
                            item5_3.setText("Deficiente");
                        }
                        if (value == 2) {
                            item5_3.setText("Regular");
                        }
                        if (value == 3) {
                            item5_3.setText("Bueno");
                        }
                        if (value == 4) {
                            item5_3.setText("Muy bueno");
                        }
                        if (value == 5) {
                            item5_3.setText("Excelente");
                        }
                    }
                    if (dataSnapshot.child("item_6").hasChild("score")) {
                        int value = dataSnapshot.child("item_6").child("score").getValue(Integer.class);
                        if (value == 1) {
                            item6_3.setText("Deficiente");
                        }
                        if (value == 2) {
                            item6_3.setText("Regular");
                        }
                        if (value == 3) {
                            item6_3.setText("Bueno");
                        }
                        if (value == 4) {
                            item6_3.setText("Muy bueno");
                        }
                        if (value == 5) {
                            item6_3.setText("Excelente");
                        }
                    }


                }

                sum3 = 0;
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Map<Double, Object> map = (Map<Double, Object>) ds.getValue();
                    Object score = map.get("score");
                    double d = Double.parseDouble(String.valueOf(score));
                    sum3 += d;

                    points_db_3 = sum3/6;


                    if (points_db_3 == 1) {
                        item7_3.setText("Deficiente");
                    }
                    if (points_db_3 == 2) {
                        item7_3.setText("Regular");
                    }
                    if (points_db_3 == 3) {
                        item7_3.setText("Bueno");
                    }
                    if (points_db_3 == 4) {
                        item7_3.setText("Muy Bueno");
                    }
                    if (points_db_3 == 5) {
                        item7_3.setText("Excelente");
                    }

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        companyRef.child(post_key).child("Business Plan").child("Commercial Plan").child("Evaluation").child("company_4").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    if (dataSnapshot.child("item_1").hasChild("score")) {
                        int value = dataSnapshot.child("item_1").child("score").getValue(Integer.class);
                        if (value == 1) {
                            item1_4.setText("Deficiente");
                        }
                        if (value == 2) {
                            item1_4.setText("Regular");
                        }
                        if (value == 3) {
                            item1_4.setText("Bueno");
                        }
                        if (value == 4) {
                            item1_4.setText("Muy bueno");
                        }
                        if (value == 5) {
                            item1_4.setText("Excelente");
                        }
                    }
                    if (dataSnapshot.child("item_2").hasChild("score")) {
                        int value = dataSnapshot.child("item_2").child("score").getValue(Integer.class);
                        if (value == 1) {
                            item2_4.setText("Deficiente");
                        }
                        if (value == 2) {
                            item2_4.setText("Regular");
                        }
                        if (value == 3) {
                            item2_4.setText("Bueno");
                        }
                        if (value == 4) {
                            item2_4.setText("Muy bueno");
                        }
                        if (value == 5) {
                            item2_4.setText("Excelente");
                        }
                    }
                    if (dataSnapshot.child("item_3").hasChild("score")) {
                        int value = dataSnapshot.child("item_3").child("score").getValue(Integer.class);
                        if (value == 1) {
                            item3_4.setText("Deficiente");
                        }
                        if (value == 2) {
                            item3_4.setText("Regular");
                        }
                        if (value == 3) {
                            item3_4.setText("Bueno");
                        }
                        if (value == 4) {
                            item3_4.setText("Muy bueno");
                        }
                        if (value == 5) {
                            item3_4.setText("Excelente");
                        }
                    }
                    if (dataSnapshot.child("item_4").hasChild("score")) {
                        int value = dataSnapshot.child("item_4").child("score").getValue(Integer.class);
                        if (value == 1) {
                            item4_4.setText("Deficiente");
                        }
                        if (value == 2) {
                            item4_4.setText("Regular");
                        }
                        if (value == 3) {
                            item4_4.setText("Bueno");
                        }
                        if (value == 4) {
                            item4_4.setText("Muy bueno");
                        }
                        if (value == 5) {
                            item4_4.setText("Excelente");
                        }
                    }
                    if (dataSnapshot.child("item_5").hasChild("score")) {
                        int value = dataSnapshot.child("item_5").child("score").getValue(Integer.class);
                        if (value == 1) {
                            item5_4.setText("Deficiente");
                        }
                        if (value == 2) {
                            item5_4.setText("Regular");
                        }
                        if (value == 3) {
                            item5_4.setText("Bueno");
                        }
                        if (value == 4) {
                            item5_4.setText("Muy bueno");
                        }
                        if (value == 5) {
                            item5_4.setText("Excelente");
                        }
                    }
                    if (dataSnapshot.child("item_6").hasChild("score")) {
                        int value = dataSnapshot.child("item_6").child("score").getValue(Integer.class);
                        if (value == 1) {
                            item6_4.setText("Deficiente");
                        }
                        if (value == 2) {
                            item6_4.setText("Regular");
                        }
                        if (value == 3) {
                            item6_4.setText("Bueno");
                        }
                        if (value == 4) {
                            item6_4.setText("Muy bueno");
                        }
                        if (value == 5) {
                            item6_4.setText("Excelente");
                        }
                    }


                }

                sum4 = 0;
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Map<Double, Object> map = (Map<Double, Object>) ds.getValue();
                    Object score = map.get("score");
                    double d = Double.parseDouble(String.valueOf(score));
                    sum4 += d;

                    points_db_4 = sum4/6;


                    if (points_db_4 == 1) {
                        item7_4.setText("Deficiente");
                    }
                    if (points_db_4 == 2) {
                        item7_4.setText("Regular");
                    }
                    if (points_db_4 == 3) {
                        item7_4.setText("Bueno");
                    }
                    if (points_db_4 == 4) {
                        item7_4.setText("Muy Bueno");
                    }
                    if (points_db_4 == 5) {
                        item7_4.setText("Excelente");
                    }

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        companyRef.child(post_key).child("Business Plan").child("Commercial Plan").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild("strategy")) {
                    String strategy = dataSnapshot.child("strategy").getValue().toString();
                    txtStrategy.setText(strategy);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        btnSegment1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path = "item_1";

                showSegmentDialog(path);
            }
        });
        btnSegment2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path = "item_2";

                showSegmentDialog(path);
            }
        });
        btnSegment3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path = "item_3";

                showSegmentDialog(path);
            }
        });


        btnCompany1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path = "company_1";

                showCompanyScoreDialog(path);
                
            }
        });
        btnCompany2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path = "company_2";

                showCompanyScoreDialog(path);

            }
        });
        btnCompany3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path = "company_3";

                showCompanyScoreDialog(path);

            }
        });
        btnCompany4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path = "company_4";

                showCompanyScoreDialog(path);

            }
        });

        btnStrategy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSetStrategyDialog();
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CommercialPlanActivity.this, OperationPlanActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });
    }

    private void showSetStrategyDialog() {
        final AlertDialog dialog = new AlertDialog.Builder(this).create();

        LayoutInflater inflater = LayoutInflater.from(this);
        View finance_method = inflater.inflate(R.layout.strategy_commercial_plan_dialog,null);

        final EditText edtStrategy;
        Button btnFinish;
        final LinearLayout rootLayout;

        edtStrategy = finance_method.findViewById(R.id.edtStrategy);
        btnFinish = finance_method.findViewById(R.id.btnFinish);
        rootLayout = finance_method.findViewById(R.id.rootLayout);

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(edtStrategy.getText().toString())) {
                    Snackbar.make(rootLayout, "Debes ingresar una estrategia", Snackbar.LENGTH_SHORT).show();
                } else {
                    companyRef.child(post_key).child("Business Plan").child("Commercial Plan").child("strategy").setValue(edtStrategy.getText().toString());
                    Toasty.success(CommercialPlanActivity.this, "Registrado", Toast.LENGTH_LONG).show();
                    dialog.dismiss();
                }

            }
        });



        dialog.setView(finance_method);
        dialog.show();
    }

    private void showCompanyScoreDialog(final String path) {
        final AlertDialog dialog = new AlertDialog.Builder(this).create();

        LayoutInflater inflater = LayoutInflater.from(this);
        View finance_method = inflater.inflate(R.layout.company_score_dialog,null);

        final SmileyRating smile_rating1,smile_rating2,smile_rating3,smile_rating4,smile_rating5,smile_rating6;
        Button btnFinish;
        final RelativeLayout rootLayout;

        smile_rating1 = finance_method.findViewById(R.id.smile_rating1);
        smile_rating2 = finance_method.findViewById(R.id.smile_rating2);
        smile_rating3 = finance_method.findViewById(R.id.smile_rating3);
        smile_rating4 = finance_method.findViewById(R.id.smile_rating4);
        smile_rating5 = finance_method.findViewById(R.id.smile_rating5);
        smile_rating6 = finance_method.findViewById(R.id.smile_rating6);
        rootLayout = finance_method.findViewById(R.id.rootLayout);

        btnFinish = finance_method.findViewById(R.id.btnFinish);

        smile_rating1.setTitle(SmileyRating.Type.TERRIBLE,"Deficiente");
        smile_rating1.setTitle(SmileyRating.Type.BAD,"Regular");
        smile_rating1.setTitle(SmileyRating.Type.OKAY,"Bueno");
        smile_rating1.setTitle(SmileyRating.Type.GOOD,"Muy Bueno");
        smile_rating1.setTitle(SmileyRating.Type.GREAT,"Excelente");

        smile_rating2.setTitle(SmileyRating.Type.TERRIBLE,"Deficiente");
        smile_rating2.setTitle(SmileyRating.Type.BAD,"Regular");
        smile_rating2.setTitle(SmileyRating.Type.OKAY,"Bueno");
        smile_rating2.setTitle(SmileyRating.Type.GOOD,"Muy Bueno");
        smile_rating2.setTitle(SmileyRating.Type.GREAT,"Excelente");

        smile_rating3.setTitle(SmileyRating.Type.TERRIBLE,"Deficiente");
        smile_rating3.setTitle(SmileyRating.Type.BAD,"Regular");
        smile_rating3.setTitle(SmileyRating.Type.OKAY,"Bueno");
        smile_rating3.setTitle(SmileyRating.Type.GOOD,"Muy Bueno");
        smile_rating3.setTitle(SmileyRating.Type.GREAT,"Excelente");

        smile_rating4.setTitle(SmileyRating.Type.TERRIBLE,"Deficiente");
        smile_rating4.setTitle(SmileyRating.Type.BAD,"Regular");
        smile_rating4.setTitle(SmileyRating.Type.OKAY,"Bueno");
        smile_rating4.setTitle(SmileyRating.Type.GOOD,"Muy Bueno");
        smile_rating4.setTitle(SmileyRating.Type.GREAT,"Excelente");

        smile_rating5.setTitle(SmileyRating.Type.TERRIBLE,"Deficiente");
        smile_rating5.setTitle(SmileyRating.Type.BAD,"Regular");
        smile_rating5.setTitle(SmileyRating.Type.OKAY,"Bueno");
        smile_rating5.setTitle(SmileyRating.Type.GOOD,"Muy Bueno");
        smile_rating5.setTitle(SmileyRating.Type.GREAT,"Excelente");

        smile_rating6.setTitle(SmileyRating.Type.TERRIBLE,"Deficiente");
        smile_rating6.setTitle(SmileyRating.Type.BAD,"Regular");
        smile_rating6.setTitle(SmileyRating.Type.OKAY,"Bueno");
        smile_rating6.setTitle(SmileyRating.Type.GOOD,"Muy Bueno");
        smile_rating6.setTitle(SmileyRating.Type.GREAT,"Excelente");

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int rating1 = smile_rating1.getSelectedSmiley().getRating();
                int rating2 = smile_rating2.getSelectedSmiley().getRating();
                int rating3 = smile_rating3.getSelectedSmiley().getRating();
                int rating4 = smile_rating4.getSelectedSmiley().getRating();
                int rating5 = smile_rating5.getSelectedSmiley().getRating();
                int rating6 = smile_rating6.getSelectedSmiley().getRating();

                if (rating1 == -1) {
                    Snackbar.make(rootLayout,"Debes completar toda la evaluación",Snackbar.LENGTH_SHORT).show();
                }
                else if (rating2 == -1) {
                    Snackbar.make(rootLayout,"Debes completar toda la evaluación",Snackbar.LENGTH_SHORT).show();
                }
                else if (rating3 == -1) {
                    Snackbar.make(rootLayout,"Debes completar toda la evaluación",Snackbar.LENGTH_SHORT).show();
                }
                else if (rating4 == -1) {
                    Snackbar.make(rootLayout,"Debes completar toda la evaluación",Snackbar.LENGTH_SHORT).show();
                }
                else if (rating5 == -1) {
                    Snackbar.make(rootLayout,"Debes completar toda la evaluación",Snackbar.LENGTH_SHORT).show();
                } else if (rating6 == -1) {
                    Snackbar.make(rootLayout, "Debes completar toda la evaluación", Snackbar.LENGTH_SHORT).show();
                } else {
                    companyRef.child(post_key).child("Business Plan").child("Commercial Plan").child("Evaluation").child(path).child("item_1").child("score").setValue(rating1);
                    companyRef.child(post_key).child("Business Plan").child("Commercial Plan").child("Evaluation").child(path).child("item_2").child("score").setValue(rating2);
                    companyRef.child(post_key).child("Business Plan").child("Commercial Plan").child("Evaluation").child(path).child("item_3").child("score").setValue(rating3);
                    companyRef.child(post_key).child("Business Plan").child("Commercial Plan").child("Evaluation").child(path).child("item_4").child("score").setValue(rating4);
                    companyRef.child(post_key).child("Business Plan").child("Commercial Plan").child("Evaluation").child(path).child("item_5").child("score").setValue(rating5);
                    companyRef.child(post_key).child("Business Plan").child("Commercial Plan").child("Evaluation").child(path).child("item_6").child("score").setValue(rating6);
                    Toasty.success(CommercialPlanActivity.this, "Registrado", Toast.LENGTH_LONG).show();
                    dialog.dismiss();
                }
            }
        });

        dialog.setView(finance_method);
        dialog.show();
    }

    private void showSegmentDialog(final String path) {
        final AlertDialog dialog = new AlertDialog.Builder(this).create();

        LayoutInflater inflater = LayoutInflater.from(this);
        View finance_method = inflater.inflate(R.layout.commercial_plan_segment_dialog,null);

        final EditText edtSegment,edtProfileSegment;
        Button btnFinish;
        final LinearLayout rootLayout;

        edtSegment = finance_method.findViewById(R.id.edtSegment);
        edtProfileSegment = finance_method.findViewById(R.id.edtProfileSegment);
        btnFinish = finance_method.findViewById(R.id.btnFinish);
        rootLayout = finance_method.findViewById(R.id.rootLayout);

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(edtSegment.getText().toString())) {
                    Snackbar.make(rootLayout,"Debes completar el segmento",Snackbar.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(edtProfileSegment.getText().toString())) {
                    Snackbar.make(rootLayout, "Debes completar el perfil del segmento", Snackbar.LENGTH_SHORT).show();
                } else {
                    companyRef.child(post_key).child("Business Plan").child("Commercial Plan").child(path).child("segment").setValue(edtSegment.getText().toString());
                    companyRef.child(post_key).child("Business Plan").child("Commercial Plan").child(path).child("profile_segment").setValue(edtProfileSegment.getText().toString());
                    Toasty.success(CommercialPlanActivity.this, "Registrado", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            }
        });

        dialog.setView(finance_method);
        dialog.show();
    }
}
