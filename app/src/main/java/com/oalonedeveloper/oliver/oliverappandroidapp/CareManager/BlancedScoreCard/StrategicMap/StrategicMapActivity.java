package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.BlancedScoreCard.StrategicMap;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;
import in.galaxyofandroid.spinerdialog.OnSpinerItemClick;
import in.galaxyofandroid.spinerdialog.SpinnerDialog;

public class StrategicMapActivity extends AppCompatActivity {

    TextView txtObjectiveLearning1,txtKpiLearning1_1,txtKpiLearning1_2,txtInitiativeLearning1_1,txtInitiativeLearning1_2,txtObjectiveLearning2,txtKpiLearning2_1,txtKpiLearning2_2,txtInitiativeLearning2_1,txtInitiativeLearning2_2,txtObjectiveLearning3,txtKpiLearning3_1,txtKpiLearning3_2,txtInitiativeLearning3_1,txtInitiativeLearning3_2;
    ImageView btnObjective1,btnObjective2,btnObjective3;
    LinearLayout layout1_1,layout1_2,layout1_3;
    DatabaseReference companyRef;
    String post_key;

    TextView txtObjectiveProcess1,txtKpiProcess1_1,txtKpiProcess1_2,txtInitiativeProcess1_1,txtInitiativeProcess1_2,txtObjectiveProcess2,txtKpiProcess2_1,txtKpiProcess2_2,txtInitiativeProcess2_1,txtInitiativeProcess2_2,txtObjectiveProcess3,txtKpiProcess3_1,txtKpiProcess3_2,txtInitiativeProcess3_1,txtInitiativeProcess3_2;
    ImageView btnProcess1,btnProcess2,btnProcess3;
    LinearLayout layout2_1,layout2_2,layout2_3;

    TextView txtObjectiveCustomer1,txtKpiCustomer1_1,txtKpiCustomer1_2,txtInitiativeCustomer1_1,txtInitiativeCustomer1_2,txtObjectiveCustomer2,txtKpiCustomer2_1,txtKpiCustomer2_2,txtInitiativeCustomer2_1,txtInitiativeCustomer2_2,txtObjectiveCustomer3,txtKpiCustomer3_1,txtKpiCustomer3_2,txtInitiativeCustomer3_1,txtInitiativeCustomer3_2;
    ImageView btnCustomer1,btnCustomer2,btnCustomer3;
    LinearLayout layout3_1,layout3_2,layout3_3;

    TextView txtObjectiveFinance1,txtKpiFinance1_1,txtKpiFinance1_2,txtInitiativeFinance1_1,txtInitiativeFinance1_2,txtObjectiveFinance2,txtKpiFinance2_1,txtKpiFinance2_2,txtInitiativeFinance2_1,txtInitiativeFinance2_2,txtObjectiveFinance3,txtKpiFinance3_1,txtKpiFinance3_2,txtInitiativeFinance3_1,txtInitiativeFinance3_2;
    ImageView btnFinance1,btnFinance2,btnFinance3;
    LinearLayout layout4_1,layout4_2,layout4_3;

    String last_objective2_1,last_objective2_2,last_objective2_3,last_objective3_1,last_objective3_2,last_objective3_3;

    ImageView btnMainObjective;
    TextView txtMainObjective;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_strategic_map);

        post_key = getIntent().getExtras().getString("post_key");
        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");

        txtObjectiveLearning1 = findViewById(R.id.txtObjectiveLearning1);
        txtKpiLearning1_1 = findViewById(R.id.txtKpiLearning1_1);
        txtKpiLearning1_2 = findViewById(R.id.txtKpiLearning1_2);
        txtInitiativeLearning1_1 = findViewById(R.id.txtInitiativeLearning1_1);
        txtInitiativeLearning1_2 = findViewById(R.id.txtInitiativeLearning1_2);

        txtObjectiveLearning2 = findViewById(R.id.txtObjectiveLearning2);
        txtKpiLearning2_1 = findViewById(R.id.txtKpiLearning2_1);
        txtKpiLearning2_2 = findViewById(R.id.txtKpiLearning2_2);
        txtInitiativeLearning2_1 = findViewById(R.id.txtInitiativeLearning2_1);
        txtInitiativeLearning2_2 = findViewById(R.id.txtInitiativeLearning2_2);

        txtObjectiveLearning3 = findViewById(R.id.txtObjectiveLearning3);
        txtKpiLearning3_1 = findViewById(R.id.txtKpiLearning3_1);
        txtKpiLearning3_2 = findViewById(R.id.txtKpiLearning3_2);
        txtInitiativeLearning3_1 = findViewById(R.id.txtInitiativeLearning3_1);
        txtInitiativeLearning3_2 = findViewById(R.id.txtInitiativeLearning3_2);

        btnObjective1 = findViewById(R.id.btnObjective1);
        btnObjective2 = findViewById(R.id.btnObjective2);
        btnObjective3 = findViewById(R.id.btnObjective3);

        layout1_1 = findViewById(R.id.layout1_1);
        layout1_2 = findViewById(R.id.layout1_2);
        layout1_3 = findViewById(R.id.layout1_3);

        txtObjectiveProcess1 = findViewById(R.id.txtObjectiveProcess1);
        txtKpiProcess1_1 = findViewById(R.id.txtKpiProcess1_1);
        txtKpiProcess1_2 = findViewById(R.id.txtKpiProcess1_2);
        txtInitiativeProcess1_1 = findViewById(R.id.txtInitiativeProcess1_1);
        txtInitiativeProcess1_2 = findViewById(R.id.txtInitiativeProcess1_2);
        txtObjectiveProcess2 = findViewById(R.id.txtObjectiveProcess2);
        txtKpiProcess2_1 = findViewById(R.id.txtKpiProcess2_1);
        txtKpiProcess2_2 = findViewById(R.id.txtKpiProcess2_2);
        txtInitiativeProcess2_1 = findViewById(R.id.txtInitiativeProcess2_1);
        txtInitiativeProcess2_2 = findViewById(R.id.txtInitiativeProcess2_2);
        txtObjectiveProcess3 = findViewById(R.id.txtObjectiveProcess3);
        txtKpiProcess3_1 = findViewById(R.id.txtKpiProcess3_1);
        txtKpiProcess3_2 = findViewById(R.id.txtKpiProcess3_2);
        txtInitiativeProcess3_1 = findViewById(R.id.txtInitiativeProcess3_1);
        txtInitiativeProcess3_2 = findViewById(R.id.txtInitiativeProcess3_2);
        btnProcess1 = findViewById(R.id.btnProcess1);
        btnProcess2 = findViewById(R.id.btnProcess2);
        btnProcess3 = findViewById(R.id.btnProcess3);
        layout2_1 = findViewById(R.id.layout2_1);
        layout2_2 = findViewById(R.id.layout2_2);
        layout2_3 = findViewById(R.id.layout2_3);

        txtObjectiveCustomer1 = findViewById(R.id.txtObjectiveCustomers1);
        txtKpiCustomer1_1 = findViewById(R.id.txtKpiCustomers1_1);
        txtKpiCustomer1_2 = findViewById(R.id.txtKpiCustomers1_2);
        txtInitiativeCustomer1_1 = findViewById(R.id.txtInitiativeCustomers1_1);
        txtInitiativeCustomer1_2 = findViewById(R.id.txtInitiativeCustomers1_2);
        txtObjectiveCustomer2 = findViewById(R.id.txtObjectiveCustomers2);
        txtKpiCustomer2_1 = findViewById(R.id.txtKpiCustomers2_1);
        txtKpiCustomer2_2 = findViewById(R.id.txtKpiCustomers2_2);
        txtInitiativeCustomer2_1 = findViewById(R.id.txtInitiativeCustomers2_1);
        txtInitiativeCustomer2_2 = findViewById(R.id.txtInitiativeCustomers2_2);
        txtObjectiveCustomer3 = findViewById(R.id.txtObjectiveCustomers3);
        txtKpiCustomer3_1 = findViewById(R.id.txtKpiCustomers3_1);
        txtKpiCustomer3_2 = findViewById(R.id.txtKpiCustomers3_2);
        txtInitiativeCustomer3_1 = findViewById(R.id.txtInitiativeCustomers3_1);
        txtInitiativeCustomer3_2 = findViewById(R.id.txtInitiativeCustomers3_2);
        btnCustomer1 = findViewById(R.id.btnCustomers1);
        btnCustomer2 = findViewById(R.id.btnCustomers2);
        btnCustomer3 = findViewById(R.id.btnCustomers3);
        layout3_1 = findViewById(R.id.layout3_1);
        layout3_2 = findViewById(R.id.layout3_2);
        layout3_3 = findViewById(R.id.layout3_3);

        txtObjectiveFinance1 = findViewById(R.id.txtObjectiveFinance1);
        txtKpiFinance1_1 = findViewById(R.id.txtKpiFinance1_1);
        txtKpiFinance1_2 = findViewById(R.id.txtKpiFinance1_2);
        txtInitiativeFinance1_1 = findViewById(R.id.txtInitiativeFinance1_1);
        txtInitiativeFinance1_2 = findViewById(R.id.txtInitiativeFinance1_2);
        txtObjectiveFinance2 = findViewById(R.id.txtObjectiveFinance2);
        txtKpiFinance2_1 = findViewById(R.id.txtKpiFinance2_1);
        txtKpiFinance2_2 = findViewById(R.id.txtKpiFinance2_2);
        txtInitiativeFinance2_1 = findViewById(R.id.txtInitiativeFinance2_1);
        txtInitiativeFinance2_2 = findViewById(R.id.txtInitiativeFinance2_2);
        txtObjectiveFinance3 = findViewById(R.id.txtObjectiveFinance3);
        txtKpiFinance3_1 = findViewById(R.id.txtKpiFinance3_1);
        txtKpiFinance3_2 = findViewById(R.id.txtKpiFinance3_2);
        txtInitiativeFinance3_1 = findViewById(R.id.txtInitiativeFinance3_1);
        txtInitiativeFinance3_2 = findViewById(R.id.txtInitiativeFinance3_2);
        btnFinance1 = findViewById(R.id.btnFinance1);
        btnFinance2 = findViewById(R.id.btnFinance2);
        btnFinance3 = findViewById(R.id.btnFinance3);
        layout4_1 = findViewById(R.id.layout4_1);
        layout4_2 = findViewById(R.id.layout4_2);
        layout4_3 = findViewById(R.id.layout4_3);

        btnMainObjective = findViewById(R.id.btnMainObjective);
        txtMainObjective = findViewById(R.id.txtMainObjective);

        companyRef.child(post_key).child("Balanced Scorecard").child("item1_1").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild("objective")) {
                    String objective = dataSnapshot.child("objective").getValue().toString();
                    txtObjectiveLearning1.setText("Objetivo: " + objective);
                }
                if (dataSnapshot.hasChild("area")) {
                    String area = dataSnapshot.child("area").getValue().toString();

                }
                if (dataSnapshot.hasChild("reason")) {
                    String reason = dataSnapshot.child("reason").getValue().toString();

                }
                if (dataSnapshot.hasChild("kpi1") && dataSnapshot.hasChild("kpi1_formula")) {
                    String kpi1 = dataSnapshot.child("kpi1").getValue().toString();
                    String kpi1_formula = dataSnapshot.child("kpi1_formula").getValue().toString();
                    txtKpiLearning1_1.setText("KPI 1: " + kpi1 + "= " + kpi1_formula);
                }
                if (dataSnapshot.hasChild("kpi2") && dataSnapshot.hasChild("kpi2_formula")) {
                    String kpi2 = dataSnapshot.child("kpi2").getValue().toString();
                    String kpi2_formula = dataSnapshot.child("kpi2_formula").getValue().toString();
                    txtKpiLearning1_2.setText("KPI 2: " + kpi2 + "= " + kpi2_formula);
                }
                if (dataSnapshot.hasChild("initiative_1")) {
                    String initiative_1 = dataSnapshot.child("initiative_1").getValue().toString();
                    txtInitiativeLearning1_1.setText("Iniciativa 1: " + initiative_1);
                }
                if (dataSnapshot.hasChild("initiative_2")) {
                    String initiative_2 = dataSnapshot.child("initiative_2").getValue().toString();
                    txtInitiativeLearning1_2.setText("Iniciativa 2: " + initiative_2);
                }

                companyRef.child(post_key).child("Balanced Scorecard").child("item1_2").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChild("objective")) {
                            String objective = dataSnapshot.child("objective").getValue().toString();
                            txtObjectiveLearning2.setText("Objetivo: " + objective);
                        }
                        if (dataSnapshot.hasChild("area")) {
                            String area = dataSnapshot.child("area").getValue().toString();

                        }
                        if (dataSnapshot.hasChild("reason")) {
                            String reason = dataSnapshot.child("reason").getValue().toString();

                        }
                        if (dataSnapshot.hasChild("kpi1") && dataSnapshot.hasChild("kpi1_formula")) {
                            String kpi1 = dataSnapshot.child("kpi1").getValue().toString();
                            String kpi1_formula = dataSnapshot.child("kpi1_formula").getValue().toString();
                            txtKpiLearning2_1.setText("KPI 1: " + kpi1 + "= " + kpi1_formula);
                        }
                        if (dataSnapshot.hasChild("kpi2") && dataSnapshot.hasChild("kpi2_formula")) {
                            String kpi2 = dataSnapshot.child("kpi2").getValue().toString();
                            String kpi2_formula = dataSnapshot.child("kpi2_formula").getValue().toString();
                            txtKpiLearning2_2.setText("KPI 2: " + kpi2 + "= " + kpi2_formula);
                        }
                        if (dataSnapshot.hasChild("initiative_1")) {
                            String initiative_1 = dataSnapshot.child("initiative_1").getValue().toString();
                            txtInitiativeLearning2_1.setText("Iniciativa 1: " + initiative_1);
                        }
                        if (dataSnapshot.hasChild("initiative_2")) {
                            String initiative_2 = dataSnapshot.child("initiative_2").getValue().toString();
                            txtInitiativeLearning2_2.setText("Iniciativa 2: " + initiative_2);
                        }

                        companyRef.child(post_key).child("Balanced Scorecard").child("item1_3").addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                if (dataSnapshot.hasChild("objective")) {
                                    String objective = dataSnapshot.child("objective").getValue().toString();
                                    txtObjectiveLearning3.setText("Objetivo: " + objective);
                                }
                                if (dataSnapshot.hasChild("area")) {
                                    String area = dataSnapshot.child("area").getValue().toString();

                                }
                                if (dataSnapshot.hasChild("reason")) {
                                    String reason = dataSnapshot.child("reason").getValue().toString();

                                }
                                if (dataSnapshot.hasChild("kpi1") && dataSnapshot.hasChild("kpi1_formula")) {
                                    String kpi1 = dataSnapshot.child("kpi1").getValue().toString();
                                    String kpi1_formula = dataSnapshot.child("kpi1_formula").getValue().toString();
                                    txtKpiLearning3_1.setText("KPI 1: " + kpi1 + "= " + kpi1_formula);
                                }
                                if (dataSnapshot.hasChild("kpi2") && dataSnapshot.hasChild("kpi2_formula")) {
                                    String kpi2 = dataSnapshot.child("kpi2").getValue().toString();
                                    String kpi2_formula = dataSnapshot.child("kpi2_formula").getValue().toString();
                                    txtKpiLearning3_2.setText("KPI 2: " + kpi2 + "= " + kpi2_formula);
                                }
                                if (dataSnapshot.hasChild("initiative_1")) {
                                    String initiative_1 = dataSnapshot.child("initiative_1").getValue().toString();
                                    txtInitiativeLearning3_1.setText("Iniciativa 1: " + initiative_1);
                                }
                                if (dataSnapshot.hasChild("initiative_2")) {
                                    String initiative_2 = dataSnapshot.child("initiative_2").getValue().toString();
                                    txtInitiativeLearning3_2.setText("Iniciativa 2: " + initiative_2);
                                }

                                companyRef.child(post_key).child("Balanced Scorecard").child("item2_1").addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        if (dataSnapshot.hasChild("objective")) {
                                            String objective = dataSnapshot.child("objective").getValue().toString();
                                            txtObjectiveProcess1.setText("Objetivo: " + objective);
                                        }
                                        if (dataSnapshot.hasChild("area")) {
                                            String area = dataSnapshot.child("area").getValue().toString();

                                        }
                                        if (dataSnapshot.hasChild("reason")) {
                                            String reason = dataSnapshot.child("reason").getValue().toString();

                                        }
                                        if (dataSnapshot.hasChild("kpi1") && dataSnapshot.hasChild("kpi1_formula")) {
                                            String kpi1 = dataSnapshot.child("kpi1").getValue().toString();
                                            String kpi1_formula = dataSnapshot.child("kpi1_formula").getValue().toString();
                                            txtKpiProcess1_1.setText("KPI 1: " + kpi1 + "= " + kpi1_formula);
                                        }
                                        if (dataSnapshot.hasChild("kpi2") && dataSnapshot.hasChild("kpi2_formula")) {
                                            String kpi2 = dataSnapshot.child("kpi2").getValue().toString();
                                            String kpi2_formula = dataSnapshot.child("kpi2_formula").getValue().toString();
                                            txtKpiProcess1_2.setText("KPI 2: " + kpi2 + "= " + kpi2_formula);
                                        }
                                        if (dataSnapshot.hasChild("initiative_1")) {
                                            String initiative_1 = dataSnapshot.child("initiative_1").getValue().toString();
                                            txtInitiativeProcess1_1.setText("Iniciativa 1: " + initiative_1);
                                        }
                                        if (dataSnapshot.hasChild("initiative_2")) {
                                            String initiative_2 = dataSnapshot.child("initiative_2").getValue().toString();
                                            txtInitiativeProcess1_2.setText("Iniciativa 2: " + initiative_2);
                                        }
                                        if (dataSnapshot.hasChild("last_objective")) {
                                            String last_objective = dataSnapshot.child("last_objective").getValue().toString();
                                            last_objective2_1 = last_objective;
                                            if (last_objective.equals("objective_1")) {
                                                layout2_1.setBackgroundResource(R.color.balancedScoreCard1);

                                            }
                                            if (last_objective.equals("objective_2")) {
                                                layout2_1.setBackgroundResource(R.color.balancedScoreCard2);

                                            }
                                            if (last_objective.equals("objective_3")) {
                                                layout2_1.setBackgroundResource(R.color.balancedScoreCard3);

                                            }
                                            txtObjectiveProcess1.setTextColor(Color.WHITE);
                                            txtKpiProcess1_1.setTextColor(Color.WHITE);
                                            txtKpiProcess1_2.setTextColor(Color.WHITE);
                                            txtInitiativeProcess1_1.setTextColor(Color.WHITE);
                                            txtInitiativeProcess1_2.setTextColor(Color.WHITE);

                                        }

                                        companyRef.child(post_key).child("Balanced Scorecard").child("item2_2").addValueEventListener(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                if (dataSnapshot.hasChild("objective")) {
                                                    String objective = dataSnapshot.child("objective").getValue().toString();
                                                    txtObjectiveProcess2.setText("Objetivo: " + objective);
                                                }
                                                if (dataSnapshot.hasChild("area")) {
                                                    String area = dataSnapshot.child("area").getValue().toString();

                                                }
                                                if (dataSnapshot.hasChild("reason")) {
                                                    String reason = dataSnapshot.child("reason").getValue().toString();

                                                }
                                                if (dataSnapshot.hasChild("kpi1") && dataSnapshot.hasChild("kpi1_formula")) {
                                                    String kpi1 = dataSnapshot.child("kpi1").getValue().toString();
                                                    String kpi1_formula = dataSnapshot.child("kpi1_formula").getValue().toString();
                                                    txtKpiProcess2_1.setText("KPI 1: " + kpi1 + "= " + kpi1_formula);
                                                }
                                                if (dataSnapshot.hasChild("kpi2") && dataSnapshot.hasChild("kpi2_formula")) {
                                                    String kpi2 = dataSnapshot.child("kpi2").getValue().toString();
                                                    String kpi2_formula = dataSnapshot.child("kpi2_formula").getValue().toString();
                                                    txtKpiProcess2_2.setText("KPI 2: " + kpi2 + "= " + kpi2_formula);
                                                }
                                                if (dataSnapshot.hasChild("initiative_1")) {
                                                    String initiative_1 = dataSnapshot.child("initiative_1").getValue().toString();
                                                    txtInitiativeProcess2_1.setText("Iniciativa 1: " + initiative_1);
                                                }
                                                if (dataSnapshot.hasChild("initiative_2")) {
                                                    String initiative_2 = dataSnapshot.child("initiative_2").getValue().toString();
                                                    txtInitiativeProcess2_2.setText("Iniciativa 2: " + initiative_2);
                                                }
                                                if (dataSnapshot.hasChild("last_objective")) {
                                                    String last_objective = dataSnapshot.child("last_objective").getValue().toString();
                                                    last_objective2_2 = last_objective;
                                                    if (last_objective.equals("objective_1")) {
                                                        layout2_2.setBackgroundResource(R.color.balancedScoreCard1);

                                                    }
                                                    if (last_objective.equals("objective_2")) {
                                                        layout2_2.setBackgroundResource(R.color.balancedScoreCard2);

                                                    }
                                                    if (last_objective.equals("objective_3")) {
                                                        layout2_2.setBackgroundResource(R.color.balancedScoreCard3);
                                                        txtObjectiveProcess3.setTextColor(Color.WHITE);
                                                        txtKpiProcess3_1.setTextColor(Color.WHITE);
                                                        txtKpiProcess3_2.setTextColor(Color.WHITE);
                                                        txtInitiativeProcess3_1.setTextColor(Color.WHITE);
                                                        txtInitiativeProcess3_2.setTextColor(Color.WHITE);
                                                    }

                                                    txtObjectiveProcess2.setTextColor(Color.WHITE);
                                                    txtKpiProcess2_1.setTextColor(Color.WHITE);
                                                    txtKpiProcess2_2.setTextColor(Color.WHITE);
                                                    txtInitiativeProcess2_1.setTextColor(Color.WHITE);
                                                    txtInitiativeProcess2_2.setTextColor(Color.WHITE);

                                                }

                                                companyRef.child(post_key).child("Balanced Scorecard").child("item2_3").addValueEventListener(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                                        if (dataSnapshot.hasChild("objective")) {
                                                            String objective = dataSnapshot.child("objective").getValue().toString();
                                                            txtObjectiveProcess3.setText("Objetivo: " + objective);
                                                        }
                                                        if (dataSnapshot.hasChild("area")) {
                                                            String area = dataSnapshot.child("area").getValue().toString();

                                                        }
                                                        if (dataSnapshot.hasChild("reason")) {
                                                            String reason = dataSnapshot.child("reason").getValue().toString();

                                                        }
                                                        if (dataSnapshot.hasChild("kpi1") && dataSnapshot.hasChild("kpi1_formula")) {
                                                            String kpi1 = dataSnapshot.child("kpi1").getValue().toString();
                                                            String kpi1_formula = dataSnapshot.child("kpi1_formula").getValue().toString();
                                                            txtKpiProcess3_1.setText("KPI 1: " + kpi1 + "= " + kpi1_formula);
                                                        }
                                                        if (dataSnapshot.hasChild("kpi2") && dataSnapshot.hasChild("kpi2_formula")) {
                                                            String kpi2 = dataSnapshot.child("kpi2").getValue().toString();
                                                            String kpi2_formula = dataSnapshot.child("kpi2_formula").getValue().toString();
                                                            txtKpiProcess3_2.setText("KPI 2: " + kpi2 + "= " + kpi2_formula);
                                                        }
                                                        if (dataSnapshot.hasChild("initiative_1")) {
                                                            String initiative_1 = dataSnapshot.child("initiative_1").getValue().toString();
                                                            txtInitiativeProcess3_1.setText("Iniciativa 1: " + initiative_1);
                                                        }
                                                        if (dataSnapshot.hasChild("initiative_2")) {
                                                            String initiative_2 = dataSnapshot.child("initiative_2").getValue().toString();
                                                            txtInitiativeProcess3_2.setText("Iniciativa 2: " + initiative_2);
                                                        }
                                                        if (dataSnapshot.hasChild("last_objective")) {
                                                            String last_objective = dataSnapshot.child("last_objective").getValue().toString();
                                                            last_objective2_3 = last_objective;
                                                            if (last_objective.equals("objective_1")) {
                                                                layout2_3.setBackgroundResource(R.color.balancedScoreCard1);

                                                            }
                                                            if (last_objective.equals("objective_2")) {
                                                                layout2_3.setBackgroundResource(R.color.balancedScoreCard2);

                                                            }
                                                            if (last_objective.equals("objective_3")) {
                                                                layout2_3.setBackgroundResource(R.color.balancedScoreCard3);

                                                            }
                                                            txtObjectiveProcess3.setTextColor(Color.WHITE);
                                                            txtKpiProcess3_1.setTextColor(Color.WHITE);
                                                            txtKpiProcess3_2.setTextColor(Color.WHITE);
                                                            txtInitiativeProcess3_1.setTextColor(Color.WHITE);
                                                            txtInitiativeProcess3_2.setTextColor(Color.WHITE);

                                                        }

                                                        companyRef.child(post_key).child("Balanced Scorecard").child("item3_1").addValueEventListener(new ValueEventListener() {
                                                            @Override
                                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                                if (dataSnapshot.hasChild("objective")) {
                                                                    String objective = dataSnapshot.child("objective").getValue().toString();
                                                                    txtObjectiveCustomer1.setText("Objetivo: " + objective);
                                                                }
                                                                if (dataSnapshot.hasChild("area")) {
                                                                    String area = dataSnapshot.child("area").getValue().toString();

                                                                }
                                                                if (dataSnapshot.hasChild("reason")) {
                                                                    String reason = dataSnapshot.child("reason").getValue().toString();

                                                                }
                                                                if (dataSnapshot.hasChild("kpi1") && dataSnapshot.hasChild("kpi1_formula")) {
                                                                    String kpi1 = dataSnapshot.child("kpi1").getValue().toString();
                                                                    String kpi1_formula = dataSnapshot.child("kpi1_formula").getValue().toString();
                                                                    txtKpiCustomer1_1.setText("KPI 1: " + kpi1 + "= " + kpi1_formula);
                                                                }
                                                                if (dataSnapshot.hasChild("kpi2") && dataSnapshot.hasChild("kpi2_formula")) {
                                                                    String kpi2 = dataSnapshot.child("kpi2").getValue().toString();
                                                                    String kpi2_formula = dataSnapshot.child("kpi2_formula").getValue().toString();
                                                                    txtKpiCustomer1_2.setText("KPI 2: " + kpi2 + "= " + kpi2_formula);
                                                                }
                                                                if (dataSnapshot.hasChild("initiative_1")) {
                                                                    String initiative_1 = dataSnapshot.child("initiative_1").getValue().toString();
                                                                    txtInitiativeCustomer1_1.setText("Iniciativa 1: " + initiative_1);
                                                                }
                                                                if (dataSnapshot.hasChild("initiative_2")) {
                                                                    String initiative_2 = dataSnapshot.child("initiative_2").getValue().toString();
                                                                    txtInitiativeCustomer1_2.setText("Iniciativa 2: " + initiative_2);
                                                                }
                                                                if (dataSnapshot.hasChild("last_objective")) {
                                                                    String last_objective = dataSnapshot.child("last_objective").getValue().toString();
                                                                    last_objective3_1 = last_objective;
                                                                    if (last_objective.equals("objective_1")) {
                                                                        if (last_objective2_1.equals("objective_1")) {

                                                                            layout3_1.setBackgroundResource(R.color.balancedScoreCard1);

                                                                        }
                                                                        if (last_objective2_1.equals("objective_2")) {
                                                                            layout3_1.setBackgroundResource(R.color.balancedScoreCard2);

                                                                        }
                                                                        if (last_objective2_1.equals("objective_3")) {
                                                                            layout3_1.setBackgroundResource(R.color.balancedScoreCard3);

                                                                        }
                                                                    }
                                                                    if (last_objective.equals("objective_2")) {
                                                                        if (last_objective2_2.equals("objective_1")) {

                                                                            layout3_1.setBackgroundResource(R.color.balancedScoreCard1);

                                                                        }
                                                                        if (last_objective2_2.equals("objective_2")) {
                                                                            layout3_1.setBackgroundResource(R.color.balancedScoreCard2);

                                                                        }
                                                                        if (last_objective2_2.equals("objective_3")) {
                                                                            layout3_1.setBackgroundResource(R.color.balancedScoreCard3);

                                                                        }
                                                                    }
                                                                    if (last_objective.equals("objective_3")) {
                                                                        if (last_objective2_3.equals("objective_1")) {

                                                                            layout3_1.setBackgroundResource(R.color.balancedScoreCard1);

                                                                        }
                                                                        if (last_objective2_3.equals("objective_2")) {
                                                                            layout3_1.setBackgroundResource(R.color.balancedScoreCard2);

                                                                        }
                                                                        if (last_objective2_3.equals("objective_3")) {
                                                                            layout3_1.setBackgroundResource(R.color.balancedScoreCard3);

                                                                        }
                                                                    }

                                                                    txtObjectiveCustomer1.setTextColor(Color.WHITE);
                                                                    txtKpiCustomer1_1.setTextColor(Color.WHITE);
                                                                    txtKpiCustomer1_2.setTextColor(Color.WHITE);
                                                                    txtInitiativeCustomer1_1.setTextColor(Color.WHITE);
                                                                    txtInitiativeCustomer1_2.setTextColor(Color.WHITE);

                                                                }

                                                                companyRef.child(post_key).child("Balanced Scorecard").child("item3_2").addValueEventListener(new ValueEventListener() {
                                                                    @Override
                                                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                                                        if (dataSnapshot.hasChild("objective")) {
                                                                            String objective = dataSnapshot.child("objective").getValue().toString();
                                                                            txtObjectiveCustomer2.setText("Objetivo: " + objective);
                                                                        }
                                                                        if (dataSnapshot.hasChild("area")) {
                                                                            String area = dataSnapshot.child("area").getValue().toString();

                                                                        }
                                                                        if (dataSnapshot.hasChild("reason")) {
                                                                            String reason = dataSnapshot.child("reason").getValue().toString();

                                                                        }
                                                                        if (dataSnapshot.hasChild("kpi1") && dataSnapshot.hasChild("kpi1_formula")) {
                                                                            String kpi1 = dataSnapshot.child("kpi1").getValue().toString();
                                                                            String kpi1_formula = dataSnapshot.child("kpi1_formula").getValue().toString();
                                                                            txtKpiCustomer2_1.setText("KPI 1: " + kpi1 + "= " + kpi1_formula);
                                                                        }
                                                                        if (dataSnapshot.hasChild("kpi2") && dataSnapshot.hasChild("kpi2_formula")) {
                                                                            String kpi2 = dataSnapshot.child("kpi2").getValue().toString();
                                                                            String kpi2_formula = dataSnapshot.child("kpi2_formula").getValue().toString();
                                                                            txtKpiCustomer2_2.setText("KPI 2: " + kpi2 + "= " + kpi2_formula);
                                                                        }
                                                                        if (dataSnapshot.hasChild("initiative_1")) {
                                                                            String initiative_1 = dataSnapshot.child("initiative_1").getValue().toString();
                                                                            txtInitiativeCustomer2_1.setText("Iniciativa 1: " + initiative_1);
                                                                        }
                                                                        if (dataSnapshot.hasChild("initiative_2")) {
                                                                            String initiative_2 = dataSnapshot.child("initiative_2").getValue().toString();
                                                                            txtInitiativeCustomer2_2.setText("Iniciativa 2: " + initiative_2);
                                                                        }
                                                                        if (dataSnapshot.hasChild("last_objective")) {
                                                                            String last_objective = dataSnapshot.child("last_objective").getValue().toString();
                                                                            last_objective3_2 = last_objective;
                                                                            if (last_objective.equals("objective_1")) {
                                                                                if (last_objective2_1.equals("objective_1")) {

                                                                                    layout3_2.setBackgroundResource(R.color.balancedScoreCard1);

                                                                                }
                                                                                if (last_objective2_1.equals("objective_2")) {
                                                                                    layout3_2.setBackgroundResource(R.color.balancedScoreCard2);

                                                                                }
                                                                                if (last_objective2_1.equals("objective_3")) {
                                                                                    layout3_2.setBackgroundResource(R.color.balancedScoreCard3);

                                                                                }
                                                                            }
                                                                            if (last_objective.equals("objective_2")) {
                                                                                if (last_objective2_2.equals("objective_1")) {

                                                                                    layout3_2.setBackgroundResource(R.color.balancedScoreCard1);

                                                                                }
                                                                                if (last_objective2_2.equals("objective_2")) {
                                                                                    layout3_2.setBackgroundResource(R.color.balancedScoreCard2);

                                                                                }
                                                                                if (last_objective2_2.equals("objective_3")) {
                                                                                    layout3_2.setBackgroundResource(R.color.balancedScoreCard3);

                                                                                }
                                                                            }
                                                                            if (last_objective.equals("objective_3")) {
                                                                                if (last_objective2_3.equals("objective_1")) {

                                                                                    layout3_2.setBackgroundResource(R.color.balancedScoreCard1);

                                                                                }
                                                                                if (last_objective2_3.equals("objective_2")) {
                                                                                    layout3_2.setBackgroundResource(R.color.balancedScoreCard2);

                                                                                }
                                                                                if (last_objective2_3.equals("objective_3")) {
                                                                                    layout3_2.setBackgroundResource(R.color.balancedScoreCard3);

                                                                                }
                                                                            }

                                                                            txtObjectiveCustomer2.setTextColor(Color.WHITE);
                                                                            txtKpiCustomer2_1.setTextColor(Color.WHITE);
                                                                            txtKpiCustomer2_2.setTextColor(Color.WHITE);
                                                                            txtInitiativeCustomer2_1.setTextColor(Color.WHITE);
                                                                            txtInitiativeCustomer2_2.setTextColor(Color.WHITE);

                                                                        }

                                                                        companyRef.child(post_key).child("Balanced Scorecard").child("item3_3").addValueEventListener(new ValueEventListener() {
                                                                            @Override
                                                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                                                if (dataSnapshot.hasChild("objective")) {
                                                                                    String objective = dataSnapshot.child("objective").getValue().toString();
                                                                                    txtObjectiveCustomer3.setText("Objetivo: " + objective);
                                                                                }
                                                                                if (dataSnapshot.hasChild("area")) {
                                                                                    String area = dataSnapshot.child("area").getValue().toString();

                                                                                }
                                                                                if (dataSnapshot.hasChild("reason")) {
                                                                                    String reason = dataSnapshot.child("reason").getValue().toString();

                                                                                }
                                                                                if (dataSnapshot.hasChild("kpi1") && dataSnapshot.hasChild("kpi1_formula")) {
                                                                                    String kpi1 = dataSnapshot.child("kpi1").getValue().toString();
                                                                                    String kpi1_formula = dataSnapshot.child("kpi1_formula").getValue().toString();
                                                                                    txtKpiCustomer3_1.setText("KPI 1: " + kpi1 + "= " + kpi1_formula);
                                                                                }
                                                                                if (dataSnapshot.hasChild("kpi2") && dataSnapshot.hasChild("kpi2_formula")) {
                                                                                    String kpi2 = dataSnapshot.child("kpi2").getValue().toString();
                                                                                    String kpi2_formula = dataSnapshot.child("kpi2_formula").getValue().toString();
                                                                                    txtKpiCustomer3_2.setText("KPI 2: " + kpi2 + "= " + kpi2_formula);
                                                                                }
                                                                                if (dataSnapshot.hasChild("initiative_1")) {
                                                                                    String initiative_1 = dataSnapshot.child("initiative_1").getValue().toString();
                                                                                    txtInitiativeCustomer3_1.setText("Iniciativa 1: " + initiative_1);
                                                                                }
                                                                                if (dataSnapshot.hasChild("initiative_2")) {
                                                                                    String initiative_2 = dataSnapshot.child("initiative_2").getValue().toString();
                                                                                    txtInitiativeCustomer3_2.setText("Iniciativa 2: " + initiative_2);
                                                                                }
                                                                                if (dataSnapshot.hasChild("last_objective")) {
                                                                                    String last_objective = dataSnapshot.child("last_objective").getValue().toString();
                                                                                    last_objective3_3 = last_objective;
                                                                                    if (last_objective.equals("objective_1")) {
                                                                                        if (last_objective2_1.equals("objective_1")) {

                                                                                            layout3_3.setBackgroundResource(R.color.balancedScoreCard1);

                                                                                        }
                                                                                        if (last_objective2_1.equals("objective_2")) {
                                                                                            layout3_3.setBackgroundResource(R.color.balancedScoreCard2);

                                                                                        }
                                                                                        if (last_objective2_1.equals("objective_3")) {
                                                                                            layout3_3.setBackgroundResource(R.color.balancedScoreCard3);

                                                                                        }
                                                                                    }
                                                                                    if (last_objective.equals("objective_2")) {
                                                                                        if (last_objective2_2.equals("objective_1")) {

                                                                                            layout3_3.setBackgroundResource(R.color.balancedScoreCard1);

                                                                                        }
                                                                                        if (last_objective2_2.equals("objective_2")) {
                                                                                            layout3_3.setBackgroundResource(R.color.balancedScoreCard2);

                                                                                        }
                                                                                        if (last_objective2_2.equals("objective_3")) {
                                                                                            layout3_3.setBackgroundResource(R.color.balancedScoreCard3);

                                                                                        }
                                                                                    }
                                                                                    if (last_objective.equals("objective_3")) {
                                                                                        if (last_objective2_3.equals("objective_1")) {

                                                                                            layout3_3.setBackgroundResource(R.color.balancedScoreCard1);

                                                                                        }
                                                                                        if (last_objective2_3.equals("objective_2")) {
                                                                                            layout3_3.setBackgroundResource(R.color.balancedScoreCard2);

                                                                                        }
                                                                                        if (last_objective2_3.equals("objective_3")) {
                                                                                            layout3_3.setBackgroundResource(R.color.balancedScoreCard3);

                                                                                        }
                                                                                    }
                                                                                    txtObjectiveCustomer3.setTextColor(Color.WHITE);
                                                                                    txtKpiCustomer3_1.setTextColor(Color.WHITE);
                                                                                    txtKpiCustomer3_2.setTextColor(Color.WHITE);
                                                                                    txtInitiativeCustomer3_1.setTextColor(Color.WHITE);
                                                                                    txtInitiativeCustomer3_2.setTextColor(Color.WHITE);

                                                                                }

                                                                                companyRef.child(post_key).child("Balanced Scorecard").child("item4_1").addValueEventListener(new ValueEventListener() {
                                                                                    @Override
                                                                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                                                                        if (dataSnapshot.hasChild("objective")) {
                                                                                            String objective = dataSnapshot.child("objective").getValue().toString();
                                                                                            txtObjectiveFinance1.setText("Objetivo: " + objective);
                                                                                        }
                                                                                        if (dataSnapshot.hasChild("area")) {
                                                                                            String area = dataSnapshot.child("area").getValue().toString();

                                                                                        }
                                                                                        if (dataSnapshot.hasChild("reason")) {
                                                                                            String reason = dataSnapshot.child("reason").getValue().toString();

                                                                                        }
                                                                                        if (dataSnapshot.hasChild("kpi1") && dataSnapshot.hasChild("kpi1_formula")) {
                                                                                            String kpi1 = dataSnapshot.child("kpi1").getValue().toString();
                                                                                            String kpi1_formula = dataSnapshot.child("kpi1_formula").getValue().toString();
                                                                                            txtKpiFinance1_1.setText("KPI 1: " + kpi1 + "= " + kpi1_formula);
                                                                                        }
                                                                                        if (dataSnapshot.hasChild("kpi2") && dataSnapshot.hasChild("kpi2_formula")) {
                                                                                            String kpi2 = dataSnapshot.child("kpi2").getValue().toString();
                                                                                            String kpi2_formula = dataSnapshot.child("kpi2_formula").getValue().toString();
                                                                                            txtKpiFinance1_2.setText("KPI 2: " + kpi2 + "= " + kpi2_formula);
                                                                                        }
                                                                                        if (dataSnapshot.hasChild("initiative_1")) {
                                                                                            String initiative_1 = dataSnapshot.child("initiative_1").getValue().toString();
                                                                                            txtInitiativeFinance1_1.setText("Iniciativa 1: " + initiative_1);
                                                                                        }
                                                                                        if (dataSnapshot.hasChild("initiative_2")) {
                                                                                            String initiative_2 = dataSnapshot.child("initiative_2").getValue().toString();
                                                                                            txtInitiativeFinance1_2.setText("Iniciativa 2: " + initiative_2);
                                                                                        }
                                                                                        if (dataSnapshot.hasChild("last_objective")) {
                                                                                            String last_objective = dataSnapshot.child("last_objective").getValue().toString();

                                                                                            if (last_objective.equals("objective_1")) {
                                                                                                if (last_objective3_1.equals("objective_1")) {

                                                                                                    if (last_objective2_1.equals("objective_1")) {

                                                                                                        layout4_1.setBackgroundResource(R.color.balancedScoreCard1);

                                                                                                    }
                                                                                                    if (last_objective2_1.equals("objective_2")) {
                                                                                                        layout4_1.setBackgroundResource(R.color.balancedScoreCard2);

                                                                                                    }
                                                                                                    if (last_objective2_1.equals("objective_3")) {
                                                                                                        layout4_1.setBackgroundResource(R.color.balancedScoreCard3);

                                                                                                    }

                                                                                                    //layout4_3.setBackgroundResource(R.color.balancedScoreCard1);

                                                                                                }
                                                                                                if (last_objective3_1.equals("objective_2")) {
                                                                                                    if (last_objective2_2.equals("objective_1")) {

                                                                                                        layout4_1.setBackgroundResource(R.color.balancedScoreCard1);

                                                                                                    }
                                                                                                    if (last_objective2_2.equals("objective_2")) {
                                                                                                        layout4_1.setBackgroundResource(R.color.balancedScoreCard2);

                                                                                                    }
                                                                                                    if (last_objective2_2.equals("objective_3")) {
                                                                                                        layout4_1.setBackgroundResource(R.color.balancedScoreCard3);

                                                                                                    }
                                                                                                    //layout4_3.setBackgroundResource(R.color.balancedScoreCard2);

                                                                                                }
                                                                                                if (last_objective3_1.equals("objective_3")) {
                                                                                                    if (last_objective2_3.equals("objective_1")) {

                                                                                                        layout4_1.setBackgroundResource(R.color.balancedScoreCard1);

                                                                                                    }
                                                                                                    if (last_objective2_3.equals("objective_2")) {
                                                                                                        layout4_1.setBackgroundResource(R.color.balancedScoreCard2);

                                                                                                    }
                                                                                                    if (last_objective2_3.equals("objective_3")) {
                                                                                                        layout4_1.setBackgroundResource(R.color.balancedScoreCard3);

                                                                                                    }
                                                                                                    //layout4_3.setBackgroundResource(R.color.balancedScoreCard3);

                                                                                                }
                                                                                            }
                                                                                            if (last_objective.equals("objective_2")) {
                                                                                                if (last_objective3_2.equals("objective_1")) {

                                                                                                    if (last_objective2_1.equals("objective_1")) {

                                                                                                        layout4_1.setBackgroundResource(R.color.balancedScoreCard1);

                                                                                                    }
                                                                                                    if (last_objective2_1.equals("objective_2")) {
                                                                                                        layout4_1.setBackgroundResource(R.color.balancedScoreCard2);

                                                                                                    }
                                                                                                    if (last_objective2_1.equals("objective_3")) {
                                                                                                        layout4_1.setBackgroundResource(R.color.balancedScoreCard3);

                                                                                                    }

                                                                                                }
                                                                                                if (last_objective3_2.equals("objective_2")) {
                                                                                                    if (last_objective2_2.equals("objective_1")) {

                                                                                                        layout4_1.setBackgroundResource(R.color.balancedScoreCard1);

                                                                                                    }
                                                                                                    if (last_objective2_2.equals("objective_2")) {
                                                                                                        layout4_1.setBackgroundResource(R.color.balancedScoreCard2);

                                                                                                    }
                                                                                                    if (last_objective2_2.equals("objective_3")) {
                                                                                                        layout4_1.setBackgroundResource(R.color.balancedScoreCard3);

                                                                                                    }

                                                                                                }
                                                                                                if (last_objective3_2.equals("objective_3")) {
                                                                                                    if (last_objective2_3.equals("objective_1")) {

                                                                                                        layout4_1.setBackgroundResource(R.color.balancedScoreCard1);

                                                                                                    }
                                                                                                    if (last_objective2_3.equals("objective_2")) {
                                                                                                        layout4_1.setBackgroundResource(R.color.balancedScoreCard2);

                                                                                                    }
                                                                                                    if (last_objective2_3.equals("objective_3")) {
                                                                                                        layout4_1.setBackgroundResource(R.color.balancedScoreCard3);

                                                                                                    }

                                                                                                }
                                                                                            }
                                                                                            if (last_objective.equals("objective_3")) {
                                                                                                if (last_objective3_3.equals("objective_1")) {

                                                                                                    if (last_objective2_1.equals("objective_1")) {

                                                                                                        layout4_1.setBackgroundResource(R.color.balancedScoreCard1);

                                                                                                    }
                                                                                                    if (last_objective2_1.equals("objective_2")) {
                                                                                                        layout4_1.setBackgroundResource(R.color.balancedScoreCard2);

                                                                                                    }
                                                                                                    if (last_objective2_1.equals("objective_3")) {
                                                                                                        layout4_1.setBackgroundResource(R.color.balancedScoreCard3);

                                                                                                    }

                                                                                                }
                                                                                                if (last_objective3_3.equals("objective_2")) {
                                                                                                    if (last_objective2_2.equals("objective_1")) {

                                                                                                        layout4_1.setBackgroundResource(R.color.balancedScoreCard1);

                                                                                                    }
                                                                                                    if (last_objective2_2.equals("objective_2")) {
                                                                                                        layout4_1.setBackgroundResource(R.color.balancedScoreCard2);

                                                                                                    }
                                                                                                    if (last_objective2_2.equals("objective_3")) {
                                                                                                        layout4_1.setBackgroundResource(R.color.balancedScoreCard3);

                                                                                                    }

                                                                                                }
                                                                                                if (last_objective3_3.equals("objective_3")) {
                                                                                                    if (last_objective2_3.equals("objective_1")) {

                                                                                                        layout4_1.setBackgroundResource(R.color.balancedScoreCard1);

                                                                                                    }
                                                                                                    if (last_objective2_3.equals("objective_2")) {
                                                                                                        layout4_1.setBackgroundResource(R.color.balancedScoreCard2);

                                                                                                    }
                                                                                                    if (last_objective2_3.equals("objective_3")) {
                                                                                                        layout4_1.setBackgroundResource(R.color.balancedScoreCard3);

                                                                                                    }

                                                                                                }
                                                                                            }

                                                                                            txtObjectiveFinance1.setTextColor(Color.WHITE);
                                                                                            txtKpiFinance1_1.setTextColor(Color.WHITE);
                                                                                            txtKpiFinance1_2.setTextColor(Color.WHITE);
                                                                                            txtInitiativeFinance1_1.setTextColor(Color.WHITE);
                                                                                            txtInitiativeFinance1_2.setTextColor(Color.WHITE);

                                                                                        }

                                                                                        companyRef.child(post_key).child("Balanced Scorecard").child("item4_2").addValueEventListener(new ValueEventListener() {
                                                                                            @Override
                                                                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                                                                if (dataSnapshot.hasChild("objective")) {
                                                                                                    String objective = dataSnapshot.child("objective").getValue().toString();
                                                                                                    txtObjectiveFinance2.setText("Objetivo: " + objective);
                                                                                                }
                                                                                                if (dataSnapshot.hasChild("area")) {
                                                                                                    String area = dataSnapshot.child("area").getValue().toString();

                                                                                                }
                                                                                                if (dataSnapshot.hasChild("reason")) {
                                                                                                    String reason = dataSnapshot.child("reason").getValue().toString();

                                                                                                }
                                                                                                if (dataSnapshot.hasChild("kpi1") && dataSnapshot.hasChild("kpi1_formula")) {
                                                                                                    String kpi1 = dataSnapshot.child("kpi1").getValue().toString();
                                                                                                    String kpi1_formula = dataSnapshot.child("kpi1_formula").getValue().toString();
                                                                                                    txtKpiFinance2_1.setText("KPI 1: " + kpi1 + "= " + kpi1_formula);
                                                                                                }
                                                                                                if (dataSnapshot.hasChild("kpi2") && dataSnapshot.hasChild("kpi2_formula")) {
                                                                                                    String kpi2 = dataSnapshot.child("kpi2").getValue().toString();
                                                                                                    String kpi2_formula = dataSnapshot.child("kpi2_formula").getValue().toString();
                                                                                                    txtKpiFinance2_2.setText("KPI 2: " + kpi2 + "= " + kpi2_formula);
                                                                                                }
                                                                                                if (dataSnapshot.hasChild("initiative_1")) {
                                                                                                    String initiative_1 = dataSnapshot.child("initiative_1").getValue().toString();
                                                                                                    txtInitiativeFinance2_1.setText("Iniciativa 1: " + initiative_1);
                                                                                                }
                                                                                                if (dataSnapshot.hasChild("initiative_2")) {
                                                                                                    String initiative_2 = dataSnapshot.child("initiative_2").getValue().toString();
                                                                                                    txtInitiativeFinance2_2.setText("Iniciativa 2: " + initiative_2);
                                                                                                }
                                                                                                if (dataSnapshot.hasChild("last_objective")) {
                                                                                                    String last_objective = dataSnapshot.child("last_objective").getValue().toString();
                                                                                                    if (last_objective.equals("objective_1")) {
                                                                                                        if (last_objective3_1.equals("objective_1")) {

                                                                                                            if (last_objective2_1.equals("objective_1")) {

                                                                                                                layout4_2.setBackgroundResource(R.color.balancedScoreCard1);

                                                                                                            }
                                                                                                            if (last_objective2_1.equals("objective_2")) {
                                                                                                                layout4_2.setBackgroundResource(R.color.balancedScoreCard2);

                                                                                                            }
                                                                                                            if (last_objective2_1.equals("objective_3")) {
                                                                                                                layout4_2.setBackgroundResource(R.color.balancedScoreCard3);

                                                                                                            }

                                                                                                            //layout4_3.setBackgroundResource(R.color.balancedScoreCard1);

                                                                                                        }
                                                                                                        if (last_objective3_1.equals("objective_2")) {
                                                                                                            if (last_objective2_2.equals("objective_1")) {

                                                                                                                layout4_2.setBackgroundResource(R.color.balancedScoreCard1);

                                                                                                            }
                                                                                                            if (last_objective2_2.equals("objective_2")) {
                                                                                                                layout4_2.setBackgroundResource(R.color.balancedScoreCard2);

                                                                                                            }
                                                                                                            if (last_objective2_2.equals("objective_3")) {
                                                                                                                layout4_2.setBackgroundResource(R.color.balancedScoreCard3);

                                                                                                            }
                                                                                                            //layout4_3.setBackgroundResource(R.color.balancedScoreCard2);

                                                                                                        }
                                                                                                        if (last_objective3_1.equals("objective_3")) {
                                                                                                            if (last_objective2_3.equals("objective_1")) {

                                                                                                                layout4_2.setBackgroundResource(R.color.balancedScoreCard1);

                                                                                                            }
                                                                                                            if (last_objective2_3.equals("objective_2")) {
                                                                                                                layout4_2.setBackgroundResource(R.color.balancedScoreCard2);

                                                                                                            }
                                                                                                            if (last_objective2_3.equals("objective_3")) {
                                                                                                                layout4_2.setBackgroundResource(R.color.balancedScoreCard3);

                                                                                                            }
                                                                                                            //layout4_3.setBackgroundResource(R.color.balancedScoreCard3);

                                                                                                        }
                                                                                                    }
                                                                                                    if (last_objective.equals("objective_2")) {
                                                                                                        if (last_objective3_2.equals("objective_1")) {

                                                                                                            if (last_objective2_1.equals("objective_1")) {

                                                                                                                layout4_2.setBackgroundResource(R.color.balancedScoreCard1);

                                                                                                            }
                                                                                                            if (last_objective2_1.equals("objective_2")) {
                                                                                                                layout4_2.setBackgroundResource(R.color.balancedScoreCard2);

                                                                                                            }
                                                                                                            if (last_objective2_1.equals("objective_3")) {
                                                                                                                layout4_2.setBackgroundResource(R.color.balancedScoreCard3);

                                                                                                            }

                                                                                                        }
                                                                                                        if (last_objective3_2.equals("objective_2")) {
                                                                                                            if (last_objective2_2.equals("objective_1")) {

                                                                                                                layout4_2.setBackgroundResource(R.color.balancedScoreCard1);

                                                                                                            }
                                                                                                            if (last_objective2_2.equals("objective_2")) {
                                                                                                                layout4_2.setBackgroundResource(R.color.balancedScoreCard2);

                                                                                                            }
                                                                                                            if (last_objective2_2.equals("objective_3")) {
                                                                                                                layout4_2.setBackgroundResource(R.color.balancedScoreCard3);

                                                                                                            }

                                                                                                        }
                                                                                                        if (last_objective3_2.equals("objective_3")) {
                                                                                                            if (last_objective2_3.equals("objective_1")) {

                                                                                                                layout4_2.setBackgroundResource(R.color.balancedScoreCard1);

                                                                                                            }
                                                                                                            if (last_objective2_3.equals("objective_2")) {
                                                                                                                layout4_2.setBackgroundResource(R.color.balancedScoreCard2);

                                                                                                            }
                                                                                                            if (last_objective2_3.equals("objective_3")) {
                                                                                                                layout4_2.setBackgroundResource(R.color.balancedScoreCard3);

                                                                                                            }

                                                                                                        }
                                                                                                    }
                                                                                                    if (last_objective.equals("objective_3")) {
                                                                                                        if (last_objective3_3.equals("objective_1")) {

                                                                                                            if (last_objective2_1.equals("objective_1")) {

                                                                                                                layout4_2.setBackgroundResource(R.color.balancedScoreCard1);

                                                                                                            }
                                                                                                            if (last_objective2_1.equals("objective_2")) {
                                                                                                                layout4_2.setBackgroundResource(R.color.balancedScoreCard2);

                                                                                                            }
                                                                                                            if (last_objective2_1.equals("objective_3")) {
                                                                                                                layout4_2.setBackgroundResource(R.color.balancedScoreCard3);

                                                                                                            }

                                                                                                        }
                                                                                                        if (last_objective3_3.equals("objective_2")) {
                                                                                                            if (last_objective2_2.equals("objective_1")) {

                                                                                                                layout4_2.setBackgroundResource(R.color.balancedScoreCard1);

                                                                                                            }
                                                                                                            if (last_objective2_2.equals("objective_2")) {
                                                                                                                layout4_2.setBackgroundResource(R.color.balancedScoreCard2);

                                                                                                            }
                                                                                                            if (last_objective2_2.equals("objective_3")) {
                                                                                                                layout4_2.setBackgroundResource(R.color.balancedScoreCard3);

                                                                                                            }

                                                                                                        }
                                                                                                        if (last_objective3_3.equals("objective_3")) {
                                                                                                            if (last_objective2_3.equals("objective_1")) {

                                                                                                                layout4_2.setBackgroundResource(R.color.balancedScoreCard1);

                                                                                                            }
                                                                                                            if (last_objective2_3.equals("objective_2")) {
                                                                                                                layout4_2.setBackgroundResource(R.color.balancedScoreCard2);

                                                                                                            }
                                                                                                            if (last_objective2_3.equals("objective_3")) {
                                                                                                                layout4_2.setBackgroundResource(R.color.balancedScoreCard3);

                                                                                                            }

                                                                                                        }
                                                                                                    }

                                                                                                    txtObjectiveFinance2.setTextColor(Color.WHITE);
                                                                                                    txtKpiFinance2_1.setTextColor(Color.WHITE);
                                                                                                    txtKpiFinance2_2.setTextColor(Color.WHITE);
                                                                                                    txtInitiativeFinance2_1.setTextColor(Color.WHITE);
                                                                                                    txtInitiativeFinance2_2.setTextColor(Color.WHITE);

                                                                                                }

                                                                                                companyRef.child(post_key).child("Balanced Scorecard").child("item4_3").addValueEventListener(new ValueEventListener() {
                                                                                                    @Override
                                                                                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                                                                                        if (dataSnapshot.hasChild("objective")) {
                                                                                                            String objective = dataSnapshot.child("objective").getValue().toString();
                                                                                                            txtObjectiveFinance3.setText("Objetivo: " + objective);
                                                                                                        }
                                                                                                        if (dataSnapshot.hasChild("area")) {
                                                                                                            String area = dataSnapshot.child("area").getValue().toString();

                                                                                                        }
                                                                                                        if (dataSnapshot.hasChild("reason")) {
                                                                                                            String reason = dataSnapshot.child("reason").getValue().toString();

                                                                                                        }
                                                                                                        if (dataSnapshot.hasChild("kpi1") && dataSnapshot.hasChild("kpi1_formula")) {
                                                                                                            String kpi1 = dataSnapshot.child("kpi1").getValue().toString();
                                                                                                            String kpi1_formula = dataSnapshot.child("kpi1_formula").getValue().toString();
                                                                                                            txtKpiFinance3_1.setText("KPI 1: " + kpi1 + "= " + kpi1_formula);
                                                                                                        }
                                                                                                        if (dataSnapshot.hasChild("kpi2") && dataSnapshot.hasChild("kpi2_formula")) {
                                                                                                            String kpi2 = dataSnapshot.child("kpi2").getValue().toString();
                                                                                                            String kpi2_formula = dataSnapshot.child("kpi2_formula").getValue().toString();
                                                                                                            txtKpiFinance3_2.setText("KPI 2: " + kpi2 + "= " + kpi2_formula);
                                                                                                        }
                                                                                                        if (dataSnapshot.hasChild("initiative_1")) {
                                                                                                            String initiative_1 = dataSnapshot.child("initiative_1").getValue().toString();
                                                                                                            txtInitiativeFinance3_1.setText("Iniciativa 1: " + initiative_1);
                                                                                                        }
                                                                                                        if (dataSnapshot.hasChild("initiative_2")) {
                                                                                                            String initiative_2 = dataSnapshot.child("initiative_2").getValue().toString();
                                                                                                            txtInitiativeFinance3_2.setText("Iniciativa 2: " + initiative_2);
                                                                                                        }
                                                                                                        if (dataSnapshot.hasChild("last_objective")) {
                                                                                                            String last_objective = dataSnapshot.child("last_objective").getValue().toString();
                                                                                                            if (last_objective.equals("objective_1")) {
                                                                                                                if (last_objective3_1.equals("objective_1")) {

                                                                                                                    if (last_objective2_1.equals("objective_1")) {

                                                                                                                        layout4_3.setBackgroundResource(R.color.balancedScoreCard1);

                                                                                                                    }
                                                                                                                    if (last_objective2_1.equals("objective_2")) {
                                                                                                                        layout4_3.setBackgroundResource(R.color.balancedScoreCard2);

                                                                                                                    }
                                                                                                                    if (last_objective2_1.equals("objective_3")) {
                                                                                                                        layout4_3.setBackgroundResource(R.color.balancedScoreCard3);

                                                                                                                    }

                                                                                                                    //layout4_3.setBackgroundResource(R.color.balancedScoreCard1);

                                                                                                                }
                                                                                                                if (last_objective3_1.equals("objective_2")) {
                                                                                                                    if (last_objective2_2.equals("objective_1")) {

                                                                                                                        layout4_3.setBackgroundResource(R.color.balancedScoreCard1);

                                                                                                                    }
                                                                                                                    if (last_objective2_2.equals("objective_2")) {
                                                                                                                        layout4_3.setBackgroundResource(R.color.balancedScoreCard2);

                                                                                                                    }
                                                                                                                    if (last_objective2_2.equals("objective_3")) {
                                                                                                                        layout4_3.setBackgroundResource(R.color.balancedScoreCard3);

                                                                                                                    }
                                                                                                                    //layout4_3.setBackgroundResource(R.color.balancedScoreCard2);

                                                                                                                }
                                                                                                                if (last_objective3_1.equals("objective_3")) {
                                                                                                                    if (last_objective2_3.equals("objective_1")) {

                                                                                                                        layout4_3.setBackgroundResource(R.color.balancedScoreCard1);

                                                                                                                    }
                                                                                                                    if (last_objective2_3.equals("objective_2")) {
                                                                                                                        layout4_3.setBackgroundResource(R.color.balancedScoreCard2);

                                                                                                                    }
                                                                                                                    if (last_objective2_3.equals("objective_3")) {
                                                                                                                        layout4_3.setBackgroundResource(R.color.balancedScoreCard3);

                                                                                                                    }
                                                                                                                    //layout4_3.setBackgroundResource(R.color.balancedScoreCard3);

                                                                                                                }
                                                                                                            }
                                                                                                            if (last_objective.equals("objective_2")) {
                                                                                                                if (last_objective3_2.equals("objective_1")) {

                                                                                                                    if (last_objective2_1.equals("objective_1")) {

                                                                                                                        layout4_3.setBackgroundResource(R.color.balancedScoreCard1);

                                                                                                                    }
                                                                                                                    if (last_objective2_1.equals("objective_2")) {
                                                                                                                        layout4_3.setBackgroundResource(R.color.balancedScoreCard2);

                                                                                                                    }
                                                                                                                    if (last_objective2_1.equals("objective_3")) {
                                                                                                                        layout4_3.setBackgroundResource(R.color.balancedScoreCard3);

                                                                                                                    }

                                                                                                                }
                                                                                                                if (last_objective3_2.equals("objective_2")) {
                                                                                                                    if (last_objective2_2.equals("objective_1")) {

                                                                                                                        layout4_3.setBackgroundResource(R.color.balancedScoreCard1);

                                                                                                                    }
                                                                                                                    if (last_objective2_2.equals("objective_2")) {
                                                                                                                        layout4_3.setBackgroundResource(R.color.balancedScoreCard2);

                                                                                                                    }
                                                                                                                    if (last_objective2_2.equals("objective_3")) {
                                                                                                                        layout4_3.setBackgroundResource(R.color.balancedScoreCard3);

                                                                                                                    }

                                                                                                                }
                                                                                                                if (last_objective3_2.equals("objective_3")) {
                                                                                                                    if (last_objective2_3.equals("objective_1")) {

                                                                                                                        layout4_3.setBackgroundResource(R.color.balancedScoreCard1);

                                                                                                                    }
                                                                                                                    if (last_objective2_3.equals("objective_2")) {
                                                                                                                        layout4_3.setBackgroundResource(R.color.balancedScoreCard2);

                                                                                                                    }
                                                                                                                    if (last_objective2_3.equals("objective_3")) {
                                                                                                                        layout4_3.setBackgroundResource(R.color.balancedScoreCard3);

                                                                                                                    }

                                                                                                                }
                                                                                                            }
                                                                                                            if (last_objective.equals("objective_3")) {
                                                                                                                if (last_objective3_3.equals("objective_1")) {

                                                                                                                    if (last_objective2_1.equals("objective_1")) {

                                                                                                                        layout4_3.setBackgroundResource(R.color.balancedScoreCard1);

                                                                                                                    }
                                                                                                                    if (last_objective2_1.equals("objective_2")) {
                                                                                                                        layout4_3.setBackgroundResource(R.color.balancedScoreCard2);

                                                                                                                    }
                                                                                                                    if (last_objective2_1.equals("objective_3")) {
                                                                                                                        layout4_3.setBackgroundResource(R.color.balancedScoreCard3);

                                                                                                                    }

                                                                                                                }
                                                                                                                if (last_objective3_3.equals("objective_2")) {
                                                                                                                    if (last_objective2_2.equals("objective_1")) {

                                                                                                                        layout4_3.setBackgroundResource(R.color.balancedScoreCard1);

                                                                                                                    }
                                                                                                                    if (last_objective2_2.equals("objective_2")) {
                                                                                                                        layout4_3.setBackgroundResource(R.color.balancedScoreCard2);

                                                                                                                    }
                                                                                                                    if (last_objective2_2.equals("objective_3")) {
                                                                                                                        layout4_3.setBackgroundResource(R.color.balancedScoreCard3);

                                                                                                                    }

                                                                                                                }
                                                                                                                if (last_objective3_3.equals("objective_3")) {
                                                                                                                    if (last_objective2_3.equals("objective_1")) {

                                                                                                                        layout4_3.setBackgroundResource(R.color.balancedScoreCard1);

                                                                                                                    }
                                                                                                                    if (last_objective2_3.equals("objective_2")) {
                                                                                                                        layout4_3.setBackgroundResource(R.color.balancedScoreCard2);

                                                                                                                    }
                                                                                                                    if (last_objective2_3.equals("objective_3")) {
                                                                                                                        layout4_3.setBackgroundResource(R.color.balancedScoreCard3);

                                                                                                                    }

                                                                                                                }
                                                                                                            }
                                                                                                            txtObjectiveFinance3.setTextColor(Color.WHITE);
                                                                                                            txtKpiFinance3_1.setTextColor(Color.WHITE);
                                                                                                            txtKpiFinance3_2.setTextColor(Color.WHITE);
                                                                                                            txtInitiativeFinance3_1.setTextColor(Color.WHITE);
                                                                                                            txtInitiativeFinance3_2.setTextColor(Color.WHITE);

                                                                                                        }

                                                                                                        companyRef.child(post_key).child("Balanced Scorecard").addValueEventListener(new ValueEventListener() {
                                                                                                            @Override
                                                                                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                                                                                if (dataSnapshot.hasChild("main_objective")) {
                                                                                                                    String main_objective = dataSnapshot.child("main_objective").getValue().toString();
                                                                                                                    txtMainObjective.setText("OBJETIVO ESTRATÉGICO E INSPIRACIONAL: "+main_objective.toUpperCase());
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
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        btnObjective1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path = "item1_1";

                showObjectiveDialog(path);
            }
        });

        btnObjective2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path = "item1_2";

                showObjectiveDialog(path);
            }
        });

        btnObjective3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path = "item1_3";

                showObjectiveDialog(path);
            }
        });

        btnProcess1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path = "item2_1";

                showObjectiveDialog(path);
            }
        });
        btnProcess2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path = "item2_2";

                showObjectiveDialog(path);
            }
        });
        btnProcess3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path = "item2_3";

                showObjectiveDialog(path);
            }
        });

        btnCustomer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path = "item3_1";

                showObjectiveDialog(path);
            }
        });
        btnCustomer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path = "item3_2";

                showObjectiveDialog(path);
            }
        });
        btnCustomer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path = "item3_3";

                showObjectiveDialog(path);
            }
        });


        btnFinance1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path = "item4_1";

                showObjectiveDialog(path);
            }
        });
        btnFinance2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path = "item4_2";

                showObjectiveDialog(path);
            }
        });
        btnFinance3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path = "item4_3";

                showObjectiveDialog(path);
            }
        });

        btnMainObjective.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setMainObjectiveDialog();
            }
        });


    }

    private void setMainObjectiveDialog() {
        final AlertDialog dialog = new AlertDialog.Builder(this).create();

        LayoutInflater inflater = LayoutInflater.from(this);
        View finance_method = inflater.inflate(R.layout.set_main_objective_dialog, null);

        Button  btnMainObjective1,btnMainObjective2,btnMainObjective3;

        btnMainObjective1 = finance_method.findViewById(R.id.btnMainObjective1);
        btnMainObjective2 = finance_method.findViewById(R.id.btnMainObjective2);
        btnMainObjective3 = finance_method.findViewById(R.id.btnMainObjective3);

        btnMainObjective1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                companyRef.child(post_key).child("Balanced Scorecard").child("main_objective").setValue("Servir mejor a los clientes");
                Toasty.success(StrategicMapActivity.this, "Actualizado", Toast.LENGTH_LONG).show();
                dialog.dismiss();
            }
        });

        btnMainObjective2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                companyRef.child(post_key).child("Balanced Scorecard").child("main_objective").setValue("Mejorar las operaciones");
                Toasty.success(StrategicMapActivity.this, "Actualizado", Toast.LENGTH_LONG).show();
                dialog.dismiss();
            }
        });

        btnMainObjective3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                companyRef.child(post_key).child("Balanced Scorecard").child("main_objective").setValue("Mejorar el producto");
                Toasty.success(StrategicMapActivity.this, "Actualizado", Toast.LENGTH_LONG).show();
                dialog.dismiss();
            }
        });

        dialog.setView(finance_method);
        dialog.show();
    }

    private void showObjectiveDialog(final String path) {
        final AlertDialog dialog = new AlertDialog.Builder(this).create();

        LayoutInflater inflater = LayoutInflater.from(this);
        View finance_method = inflater.inflate(R.layout.balanced_score_card_objectives_dialog, null);

        TextView txtTitle, txtSubtitle, txtMessage;
        final EditText edtObjectiveName, edtReason1, edtKpi1_1, edtKpiFormula1_1, edtKpi1_2, edtKpiFormula1_2, edtReason2, edtKpi2_1, edtKpiFormula2_1, edtKpi2_2, edtKpiFormula2_2, edtReason3, edtKpi3_1, edtKpiFormula3_1, edtKpi3_2, edtKpiFormula3_2,
                edtInitiative1, edtInitiative2;
        final Button btnArea, btnFinish;
        ImageView btnKpiInfo1_1, btnKpiInfo1_2, btnKpiInfo2_1, btnKpiInfo2_2, btnKpiInfo3_1, btnKpiInfo3_2;
        final RadioButton rdReason1, rdReason2, rdReason3, rdLastObjective1, rdLastObjective2, rdLastObjective3;
        final LinearLayout rootLayout;
        ArrayList<String> arr = new ArrayList<>();
        final SpinnerDialog spinnerDialog;
        CardView cardViewLastObjective;

        txtTitle = finance_method.findViewById(R.id.txtTitle);
        txtSubtitle = finance_method.findViewById(R.id.txtSubtitle);
        txtMessage = finance_method.findViewById(R.id.txtMessage);
        edtObjectiveName = finance_method.findViewById(R.id.edtObjectiveName);
        edtReason1 = finance_method.findViewById(R.id.edtReason1);
        edtKpi1_1 = finance_method.findViewById(R.id.edtKpi1_1);
        edtKpiFormula1_1 = finance_method.findViewById(R.id.edtKpiFormula1_1);
        edtKpi1_2 = finance_method.findViewById(R.id.edtKpi1_2);
        edtKpiFormula1_2 = finance_method.findViewById(R.id.edtKpiFormula1_2);
        edtReason2 = finance_method.findViewById(R.id.edtReason2);
        edtKpi2_1 = finance_method.findViewById(R.id.edtKpi2_1);
        edtKpiFormula2_1 = finance_method.findViewById(R.id.edtKpiFormula2_1);
        edtKpi2_2 = finance_method.findViewById(R.id.edtKpi2_2);
        edtKpiFormula2_2 = finance_method.findViewById(R.id.edtKpiFormula2_2);
        edtReason3 = finance_method.findViewById(R.id.edtReason3);
        edtKpi3_1 = finance_method.findViewById(R.id.edtKpi3_1);
        edtKpiFormula3_1 = finance_method.findViewById(R.id.edtKpiFormula3_1);
        edtKpi3_2 = finance_method.findViewById(R.id.edtKpi3_2);
        edtKpiFormula3_2 = finance_method.findViewById(R.id.edtKpiFormula3_2);
        edtInitiative1 = finance_method.findViewById(R.id.edtInitiative1);
        edtInitiative2 = finance_method.findViewById(R.id.edtInitiative2);
        btnArea = finance_method.findViewById(R.id.btnArea);
        btnFinish = finance_method.findViewById(R.id.btnFinish);
        btnKpiInfo1_1 = finance_method.findViewById(R.id.btnKpiInfo1_1);
        btnKpiInfo1_2 = finance_method.findViewById(R.id.btnKpiInfo1_2);
        btnKpiInfo2_1 = finance_method.findViewById(R.id.btnKpiInfo2_1);
        btnKpiInfo2_2 = finance_method.findViewById(R.id.btnKpiInfo2_2);
        btnKpiInfo3_1 = finance_method.findViewById(R.id.btnKpiInfo3_1);
        btnKpiInfo3_2 = finance_method.findViewById(R.id.btnKpiInfo3_2);
        rdReason1 = finance_method.findViewById(R.id.rdReason1);
        rdReason2 = finance_method.findViewById(R.id.rdReason2);
        rdReason3 = finance_method.findViewById(R.id.rdReason3);
        rootLayout = finance_method.findViewById(R.id.rootLayout);
        rdLastObjective1 = finance_method.findViewById(R.id.rdLastObjective1);
        rdLastObjective2 = finance_method.findViewById(R.id.rdLastObjective2);
        rdLastObjective3 = finance_method.findViewById(R.id.rdLastObjective3);
        cardViewLastObjective = finance_method.findViewById(R.id.cardViewLastObjective);

        arr.add("Excelencia del producto/servicio");
        arr.add("Relación con el cliente");
        arr.add("Excelencia Operacional");
        arr.add("General (todos los temas)");
        spinnerDialog = new SpinnerDialog(this, arr, "Selecciona una de las alternativas");


        if (path.equals("item1_1")) {
            cardViewLastObjective.setVisibility(View.GONE);
        }
        if (path.equals("item1_2")) {
            cardViewLastObjective.setVisibility(View.GONE);
        }
        if (path.equals("item1_3")) {
            cardViewLastObjective.setVisibility(View.GONE);
        }

        if (path.equals("item2_1") || path.equals("item2_2") || path.equals("item2_3")) {
            companyRef.child(post_key).child("Balanced Scorecard").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.child("item1_1").hasChild("objective")) {
                        String objective1 = dataSnapshot.child("item1_1").child("objective").getValue().toString();
                        rdLastObjective1.setText(objective1);
                    }
                    if (dataSnapshot.child("item1_2").hasChild("objective")) {
                        String objective2 = dataSnapshot.child("item1_2").child("objective").getValue().toString();
                        rdLastObjective2.setText(objective2);
                    }
                    if (dataSnapshot.child("item1_3").hasChild("objective")) {
                        String objective3 = dataSnapshot.child("item1_3").child("objective").getValue().toString();
                        rdLastObjective3.setText(objective3);
                    }

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }

        if (path.equals("item3_1") || path.equals("item3_2") || path.equals("item3_3")) {
            companyRef.child(post_key).child("Balanced Scorecard").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.child("item2_1").hasChild("objective")) {
                        String objective1 = dataSnapshot.child("item2_1").child("objective").getValue().toString();
                        rdLastObjective1.setText(objective1);
                    }
                    if (dataSnapshot.child("item2_2").hasChild("objective")) {
                        String objective2 = dataSnapshot.child("item2_2").child("objective").getValue().toString();
                        rdLastObjective2.setText(objective2);
                    }
                    if (dataSnapshot.child("item2_3").hasChild("objective")) {
                        String objective3 = dataSnapshot.child("item2_3").child("objective").getValue().toString();
                        rdLastObjective3.setText(objective3);
                    }

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }

        if (path.equals("item4_1") || path.equals("item4_2") || path.equals("item4_3")) {
            companyRef.child(post_key).child("Balanced Scorecard").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.child("item3_1").hasChild("objective")) {
                        String objective1 = dataSnapshot.child("item3_1").child("objective").getValue().toString();
                        rdLastObjective1.setText(objective1);
                    }
                    if (dataSnapshot.child("item3_2").hasChild("objective")) {
                        String objective2 = dataSnapshot.child("item3_2").child("objective").getValue().toString();
                        rdLastObjective2.setText(objective2);
                    }
                    if (dataSnapshot.child("item3_3").hasChild("objective")) {
                        String objective3 = dataSnapshot.child("item3_3").child("objective").getValue().toString();
                        rdLastObjective3.setText(objective3);
                    }

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }

        btnArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinnerDialog.showSpinerDialog();
            }
        });

        spinnerDialog.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String item2, int position2) {
                btnArea.setText(item2);

            }
        });

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(edtObjectiveName.getText().toString())) {
                    Snackbar.make(rootLayout, "Debes ingresar el objetivo", Snackbar.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(btnArea.getText().toString())) {
                    Snackbar.make(rootLayout, "Debes seleccionar una opción", Snackbar.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(edtReason1.getText().toString())) {
                    Snackbar.make(rootLayout, "Debes registrar la razón", Snackbar.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(edtKpi1_1.getText().toString())) {
                    Snackbar.make(rootLayout, "Debes registrar el KPI", Snackbar.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(edtKpiFormula1_1.getText().toString())) {
                    Snackbar.make(rootLayout, "Debes registrar la fórmula del KPI", Snackbar.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(edtKpi1_2.getText().toString())) {
                    Snackbar.make(rootLayout, "Debes registrar el KPI", Snackbar.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(edtKpiFormula1_2.getText().toString())) {
                    Snackbar.make(rootLayout, "Debes registrar la fórmula del KPI", Snackbar.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(edtReason2.getText().toString())) {
                    Snackbar.make(rootLayout, "Debes registrar la razón", Snackbar.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(edtKpi2_1.getText().toString())) {
                    Snackbar.make(rootLayout, "Debes registrar el KPI", Snackbar.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(edtKpiFormula2_1.getText().toString())) {
                    Snackbar.make(rootLayout, "Debes registrar la fórmula del KPI", Snackbar.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(edtKpi2_2.getText().toString())) {
                    Snackbar.make(rootLayout, "Debes registrar el KPI", Snackbar.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(edtKpiFormula2_2.getText().toString())) {
                    Snackbar.make(rootLayout, "Debes registrar la fórmula del KPI", Snackbar.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(edtReason3.getText().toString())) {
                    Snackbar.make(rootLayout, "Debes registrar la razón", Snackbar.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(edtKpi3_1.getText().toString())) {
                    Snackbar.make(rootLayout, "Debes registrar el KPI", Snackbar.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(edtKpiFormula3_1.getText().toString())) {
                    Snackbar.make(rootLayout, "Debes registrar la fórmula del KPI", Snackbar.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(edtKpi3_2.getText().toString())) {
                    Snackbar.make(rootLayout, "Debes registrar el KPI", Snackbar.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(edtKpiFormula3_2.getText().toString())) {
                    Snackbar.make(rootLayout, "Debes registrar la fórmula del KPI", Snackbar.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(edtInitiative1.getText().toString())) {
                    Snackbar.make(rootLayout, "Debes registrar la iniciativa", Snackbar.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(edtInitiative2.getText().toString())) {
                    Snackbar.make(rootLayout, "Debes registrar la iniciativa", Snackbar.LENGTH_SHORT).show();
                } else if (!rdReason1.isChecked() && !rdReason2.isChecked() && !rdReason3.isChecked()) {
                    Snackbar.make(rootLayout, "Debes seleccionar una razón", Snackbar.LENGTH_SHORT).show();
                } else {
                    companyRef.child(post_key).child("Balanced Scorecard").child(path).child("objective").setValue(edtObjectiveName.getText().toString());
                    companyRef.child(post_key).child("Balanced Scorecard").child(path).child("area").setValue(btnArea.getText().toString());
                    if (rdReason1.isChecked()) {
                        companyRef.child(post_key).child("Balanced Scorecard").child(path).child("reason").setValue(edtReason1.getText().toString());
                        companyRef.child(post_key).child("Balanced Scorecard").child(path).child("kpi1").setValue(edtKpi1_1.getText().toString());
                        companyRef.child(post_key).child("Balanced Scorecard").child(path).child("kpi1_formula").setValue(edtKpiFormula1_1.getText().toString());
                        companyRef.child(post_key).child("Balanced Scorecard").child(path).child("kpi2").setValue(edtKpi1_2.getText().toString());
                        companyRef.child(post_key).child("Balanced Scorecard").child(path).child("kpi2_formula").setValue(edtKpiFormula1_2.getText().toString());
                    }
                    if (rdReason2.isChecked()) {
                        companyRef.child(post_key).child("Balanced Scorecard").child(path).child("reason").setValue(edtReason2.getText().toString());
                        companyRef.child(post_key).child("Balanced Scorecard").child(path).child("kpi1").setValue(edtKpi2_1.getText().toString());
                        companyRef.child(post_key).child("Balanced Scorecard").child(path).child("kpi1_formula").setValue(edtKpiFormula2_1.getText().toString());
                        companyRef.child(post_key).child("Balanced Scorecard").child(path).child("kpi2").setValue(edtKpi2_2.getText().toString());
                        companyRef.child(post_key).child("Balanced Scorecard").child(path).child("kpi2_formula").setValue(edtKpiFormula2_2.getText().toString());
                    }
                    if (rdReason3.isChecked()) {
                        companyRef.child(post_key).child("Balanced Scorecard").child(path).child("reason").setValue(edtReason3.getText().toString());
                        companyRef.child(post_key).child("Balanced Scorecard").child(path).child("kpi1").setValue(edtKpi3_1.getText().toString());
                        companyRef.child(post_key).child("Balanced Scorecard").child(path).child("kpi1_formula").setValue(edtKpiFormula3_1.getText().toString());
                        companyRef.child(post_key).child("Balanced Scorecard").child(path).child("kpi2").setValue(edtKpi3_2.getText().toString());
                        companyRef.child(post_key).child("Balanced Scorecard").child(path).child("kpi2_formula").setValue(edtKpiFormula3_2.getText().toString());
                    }

                    if (rdLastObjective1.isChecked()) {
                        companyRef.child(post_key).child("Balanced Scorecard").child(path).child("last_objective").setValue("objective_1");
                    }
                    if (rdLastObjective2.isChecked()) {
                        companyRef.child(post_key).child("Balanced Scorecard").child(path).child("last_objective").setValue("objective_2");
                    }
                    if (rdLastObjective3.isChecked()) {
                        companyRef.child(post_key).child("Balanced Scorecard").child(path).child("last_objective").setValue("objective_3");
                    }

                    companyRef.child(post_key).child("Balanced Scorecard").child(path).child("initiative_1").setValue(edtInitiative1.getText().toString());
                    companyRef.child(post_key).child("Balanced Scorecard").child(path).child("initiative_2").setValue(edtInitiative2.getText().toString());
                    Toasty.success(StrategicMapActivity.this, "Actualizado", Toast.LENGTH_LONG).show();
                    dialog.dismiss();
                }
            }
        });


        dialog.setView(finance_method);
        dialog.show();

    }

}

