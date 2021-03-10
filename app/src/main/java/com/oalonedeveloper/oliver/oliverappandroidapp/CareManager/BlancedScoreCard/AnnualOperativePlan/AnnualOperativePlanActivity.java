package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.BlancedScoreCard.AnnualOperativePlan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.PeopleManagement.WorkersPayroll.WorkerPayrollProfilesActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;
import com.xw.repo.BubbleSeekBar;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;
import in.galaxyofandroid.spinerdialog.OnSpinerItemClick;
import in.galaxyofandroid.spinerdialog.SpinnerDialog;

public class AnnualOperativePlanActivity extends AppCompatActivity {

    String post_key;
    DatabaseReference companyRef;
    Button btnRegister;
    ArrayList<String> arr_area =new ArrayList<>();
    SpinnerDialog areaDialog;
    ArrayList<String> arr_area_type =new ArrayList<>();
    SpinnerDialog areaTypeDialog;
    ArrayList<String> arr_linked_objective =new ArrayList<>();
    SpinnerDialog linkedObjectiveDialog;
    ArrayList<String> arr_resources =new ArrayList<>();
    SpinnerDialog resourcesDialog;
    ArrayList<String> bthDay =new ArrayList<>();
    SpinnerDialog bthDayDialog;
    SpinnerDialog bthEndDayDialog;

    ArrayList<String> bthMonth =new ArrayList<>();
    SpinnerDialog bthMonthDialog;
    SpinnerDialog bthEndMonthDialog;

    ArrayList<String> bthYear =new ArrayList<>();
    SpinnerDialog bthYearDialog;
    SpinnerDialog bthEndYearDialog;
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annual_operative_plan);

        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");
        post_key = getIntent().getExtras().getString("post_key");

        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(false);
        linearLayoutManager.setStackFromEnd(false);
        recyclerView.setLayoutManager(linearLayoutManager);

        btnRegister = findViewById(R.id.btnRegister);

        showAnnualPlan();


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddGoal();
            }
        });

    }

    private void showAnnualPlan() {
        Query query = companyRef.child(post_key).child("Annual Plan").orderByChild("end_date");
        FirebaseRecyclerAdapter<AnnualPlanModel,AnnualPlanViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<AnnualPlanModel, AnnualPlanViewHolder>
                (AnnualPlanModel.class,R.layout.annual_plan_item,AnnualPlanViewHolder.class,query) {
            @Override
            protected void populateViewHolder(final AnnualPlanViewHolder viewHolder, AnnualPlanModel model, int position) {
                viewHolder.setEnd_date(model.getEnd_date());
                viewHolder.setExpected_result(model.getExpected_result());
                viewHolder.setInitiative_1(model.getInitiative_1());
                viewHolder.setInitiative_2(model.getInitiative_2());
                viewHolder.setKp1(model.getKp1());
                viewHolder.setKp1_progress(model.getKp1_progress());
                viewHolder.setKp2(model.getKp2());
                viewHolder.setKp2_progress(model.getKp2_progress());
                viewHolder.setLinked_objective(model.getLinked_objective());
                viewHolder.setMain_objective(model.getMain_objective());
                viewHolder.setManagement_area(model.getManagement_area());
                viewHolder.setManagement_area_type(model.getManagement_area_type());
                viewHolder.setManagement_description(model.getManagement_description());
                viewHolder.setResources(model.getResources());
                viewHolder.setResponsable(model.getResponsable());
                viewHolder.setStart_date(model.getStart_date());
                viewHolder.setGoal_ready(model.getGoal_ready());

                viewHolder.txtManagementArea.setText(viewHolder.management_area_st);
                viewHolder.txtDescription.setText("Descripción: "+viewHolder.management_description_st);
                viewHolder.txtManagementType.setText("Tipo de Gestión: "+viewHolder.management_area_type_st);
                viewHolder.txtGoal.setText("Meta Alcanzada: "+viewHolder.goal_ready_st);

                viewHolder.btnDetails.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showDetailsDialog();
                    }

                    private void showDetailsDialog() {
                        final AlertDialog dialog = new AlertDialog.Builder(AnnualOperativePlanActivity.this).create();

                        LayoutInflater inflater = LayoutInflater.from(AnnualOperativePlanActivity.this);
                        View finance_method = inflater.inflate(R.layout.annual_plan_detail_dialog,null);

                        TextView txtArea,txtManagementType,txtDescription,txtMainObjective,txtLinkedObjective,txtInitiative1,txtInitiative2,txtResources,txtResult,txtResponsable,
                                txtStartDate,txtEndDate,kp1,kp1_progress,kp2,kp2_progress,txtGoal;

                        txtArea = finance_method.findViewById(R.id.txtArea);
                        txtManagementType = finance_method.findViewById(R.id.txtManagementType);
                        txtDescription = finance_method.findViewById(R.id.txtDescription);
                        txtMainObjective = finance_method.findViewById(R.id.txtMainObjective);
                        txtLinkedObjective = finance_method.findViewById(R.id.txtLinkedObjective);
                        txtInitiative1 = finance_method.findViewById(R.id.txtInitiative1);
                        txtInitiative2 = finance_method.findViewById(R.id.txtInitiative2);
                        txtResources = finance_method.findViewById(R.id.txtResources);
                        txtResult = finance_method.findViewById(R.id.txtResult);
                        txtResponsable = finance_method.findViewById(R.id.txtResponsable);
                        txtStartDate = finance_method.findViewById(R.id.txtStartDate);
                        txtEndDate = finance_method.findViewById(R.id.txtEndDate);
                        kp1 = finance_method.findViewById(R.id.kp1);
                        kp1_progress = finance_method.findViewById(R.id.kp1_progress);
                        kp2 = finance_method.findViewById(R.id.kp2);
                        kp2_progress = finance_method.findViewById(R.id.kp2_progress);
                        txtGoal = finance_method.findViewById(R.id.txtGoal);

                        txtArea.setText(viewHolder.management_area_st);
                        txtManagementType.setText("Tipo de Gestión: "+viewHolder.management_area_type_st);
                        txtDescription.setText("Descripción: "+viewHolder.management_description_st);
                        txtMainObjective.setText("Objetivo Estratégico e Inspiracional: "+viewHolder.main_objective_st);
                        txtLinkedObjective.setText("Objetivo de Enlace: "+viewHolder.linked_objective_st);
                        txtInitiative1.setText("Actividad o Iniciativa 1: "+viewHolder.initiative_1_st);
                        txtInitiative2.setText("Actividad o Iniciativa 1: "+viewHolder.initiative_2_st);
                        txtResources.setText("Recursos: "+viewHolder.resources_st);
                        txtResult.setText("Resultados Esperados: "+viewHolder.expected_result_st);
                        txtResponsable.setText("Responsable: "+viewHolder.responsable_st);
                        txtStartDate.setText("Inicio de Actividades: "+viewHolder.start_date_st);
                        txtEndDate.setText("Final de Actividades: "+viewHolder.end_date_st);
                        kp1.setText(viewHolder.kp1_st);
                        kp1_progress.setText(viewHolder.kp1_progress_st+"%");
                        kp2.setText(viewHolder.kp2_st);
                        kp2_progress.setText(viewHolder.kp2_progress_st+"%");
                        txtGoal.setText("Meta Alcanzada: "+viewHolder.goal_ready_st);



                        dialog.setView(finance_method);
                        dialog.show();
                    }
                });
            }
        };
        recyclerView.setAdapter(firebaseRecyclerAdapter);

    }

    private void showAddGoal() {
        final AlertDialog dialog = new AlertDialog.Builder(AnnualOperativePlanActivity.this).create();

        LayoutInflater inflater = LayoutInflater.from(AnnualOperativePlanActivity.this);
        View finance_method = inflater.inflate(R.layout.add_annual_operative_plan_dialog,null);

        final Button btnArea,btnAreaType,btnMainObjective,btnLinkedObjectives,btnResources,edtBthDay,edtBthMonth,edtBthYear,edtEndBthDay,edtEndBthMonth,edtEndBthYear,btnFinish;
        final EditText edtManagementDescription,edtResults,edtResponsable;
        final TextView txtInitiative1,txtInitiative2,txtKpi1,txtKpi2;
        final BubbleSeekBar seekBar1,seekBar2;
        final RadioButton rdYes,rdNo;
        final LinearLayout rootLayout;

        btnArea = finance_method.findViewById(R.id.btnArea);
        btnAreaType = finance_method.findViewById(R.id.btnAreaType);
        btnMainObjective = finance_method.findViewById(R.id.btnMainObjective);
        btnLinkedObjectives = finance_method.findViewById(R.id.btnLinkedObjectives);
        btnResources = finance_method.findViewById(R.id.btnResources);
        edtBthDay = finance_method.findViewById(R.id.edtBthDay);
        edtBthMonth = finance_method.findViewById(R.id.edtBthMonth);
        edtBthYear = finance_method.findViewById(R.id.edtBthYear);
        edtEndBthDay = finance_method.findViewById(R.id.edtEndBthDay);
        edtEndBthMonth = finance_method.findViewById(R.id.edtEndBthMonth);
        edtEndBthYear = finance_method.findViewById(R.id.edtEndBthYear);
        edtManagementDescription = finance_method.findViewById(R.id.edtManagementDescription);
        edtResults = finance_method.findViewById(R.id.edtResults);
        edtResponsable = finance_method.findViewById(R.id.edtResponsable);
        txtInitiative1 = finance_method.findViewById(R.id.txtInitiative1);
        txtInitiative2 = finance_method.findViewById(R.id.txtInitiative2);
        txtKpi1 = finance_method.findViewById(R.id.txtKpi1);
        txtKpi2 = finance_method.findViewById(R.id.txtKpi2);
        seekBar1 = finance_method.findViewById(R.id.seekBar1);
        seekBar2 = finance_method.findViewById(R.id.seekBar2);
        rdYes = finance_method.findViewById(R.id.rdYes);
        rdNo = finance_method.findViewById(R.id.rdNo);
        btnFinish = finance_method.findViewById(R.id.btnFinish);
        rootLayout = finance_method.findViewById(R.id.rootLayout);

        arr_area.add("Junta Directiva");arr_area.add("Presidencia");arr_area.add("Dirección General");arr_area.add("Gerencia General");arr_area.add("Financiera y Contabilidad");arr_area.add("Administrativa");arr_area.add("Comercial y Ventas");
        arr_area.add("Recursos Humanos");arr_area.add("Producción y Logística");arr_area.add("Marketing");arr_area.add("Sistemas y Tecnología");arr_area.add("Archivos centrales");arr_area.add("Compras");arr_area.add("Secretaría General");

        btnArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                areaDialog.showSpinerDialog();
            }
        });

        areaDialog = new SpinnerDialog(AnnualOperativePlanActivity.this,arr_area, "Selecciona el área de gestión");
        areaDialog.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String item2, int position2) {
                btnArea.setText(item2);

            }
        });

        arr_area_type.add(" Gestión Empresarial");arr_area_type.add("Gestión ambiental");arr_area_type.add(" Gestión educativa");arr_area_type.add("Gestión humana");
        arr_area_type.add("Gestión social");arr_area_type.add("Gestión de calidad");arr_area_type.add("Gestión de riesgo");arr_area_type.add("Gestión comercial");

        btnAreaType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                areaTypeDialog.showSpinerDialog();
            }
        });

        areaTypeDialog = new SpinnerDialog(AnnualOperativePlanActivity.this,arr_area_type, "Selecciona el tipo de gestión");
        areaTypeDialog.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String item2, int position2) {
                btnAreaType.setText(item2);

            }
        });

        companyRef.child(post_key).child("Balanced Scorecard").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild("main_objective")) {
                    String main_objective = dataSnapshot.child("main_objective").getValue().toString();
                    btnMainObjective.setText(main_objective);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        arr_linked_objective.add("Aprendizaje y Crecimiento");arr_linked_objective.add("Procesos Internos");arr_linked_objective.add("Clientes");arr_linked_objective.add("Finanzas");
        btnLinkedObjectives.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linkedObjectiveDialog.showSpinerDialog();
            }
        });

        linkedObjectiveDialog = new SpinnerDialog(AnnualOperativePlanActivity.this,arr_linked_objective, "Selecciona el objetivo de enlace");
        linkedObjectiveDialog.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String item2, int position2) {
                btnLinkedObjectives.setText(item2);
                if (item2.equals("Aprendizaje y Crecimiento")) {
                    companyRef.child(post_key).child("Balanced Scorecard").child("item1_1").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (dataSnapshot.hasChild("initiative_1")) {
                                String value = dataSnapshot.child("initiative_1").getValue().toString();
                                txtInitiative1.setText(value);
                            }
                            if (dataSnapshot.hasChild("initiative_2")) {
                                String value = dataSnapshot.child("initiative_2").getValue().toString();
                                txtInitiative2.setText(value);
                            }

                            if (dataSnapshot.hasChild("kpi1")) {
                                String value = dataSnapshot.child("kpi1").getValue().toString();
                                txtKpi1.setText(value);
                            }
                            if (dataSnapshot.hasChild("kpi2")) {
                                String value = dataSnapshot.child("kpi2").getValue().toString();
                                txtKpi2.setText(value);
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
                if (item2.equals("Procesos Internos")) {
                    companyRef.child(post_key).child("Balanced Scorecard").child("item1_2").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (dataSnapshot.hasChild("initiative_1")) {
                                String value = dataSnapshot.child("initiative_1").getValue().toString();
                                txtInitiative1.setText(value);
                            }
                            if (dataSnapshot.hasChild("initiative_2")) {
                                String value = dataSnapshot.child("initiative_2").getValue().toString();
                                txtInitiative2.setText(value);
                            }

                            if (dataSnapshot.hasChild("kpi1")) {
                                String value = dataSnapshot.child("kpi1").getValue().toString();
                                txtKpi1.setText(value);
                            }
                            if (dataSnapshot.hasChild("kpi2")) {
                                String value = dataSnapshot.child("kpi2").getValue().toString();
                                txtKpi2.setText(value);
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }
                if (item2.equals("Clientes")) {
                    companyRef.child(post_key).child("Balanced Scorecard").child("item1_3").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (dataSnapshot.hasChild("initiative_1")) {
                                String value = dataSnapshot.child("initiative_1").getValue().toString();
                                txtInitiative1.setText(value);
                            }
                            if (dataSnapshot.hasChild("initiative_2")) {
                                String value = dataSnapshot.child("initiative_2").getValue().toString();
                                txtInitiative2.setText(value);
                            }

                            if (dataSnapshot.hasChild("kpi1")) {
                                String value = dataSnapshot.child("kpi1").getValue().toString();
                                txtKpi1.setText(value);
                            }
                            if (dataSnapshot.hasChild("kpi2")) {
                                String value = dataSnapshot.child("kpi2").getValue().toString();
                                txtKpi2.setText(value);
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }
                if (item2.equals("Finanzas")) {
                    companyRef.child(post_key).child("Balanced Scorecard").child("item1_4").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (dataSnapshot.hasChild("initiative_1")) {
                                String value = dataSnapshot.child("initiative_1").getValue().toString();
                                txtInitiative1.setText(value);
                            }
                            if (dataSnapshot.hasChild("initiative_2")) {
                                String value = dataSnapshot.child("initiative_2").getValue().toString();
                                txtInitiative2.setText(value);
                            }

                            if (dataSnapshot.hasChild("kpi1")) {
                                String value = dataSnapshot.child("kpi1").getValue().toString();
                                txtKpi1.setText(value);
                            }
                            if (dataSnapshot.hasChild("kpi2")) {
                                String value = dataSnapshot.child("kpi2").getValue().toString();
                                txtKpi2.setText(value);
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
            }
        });

        arr_resources.add("Recursos Financieros"); arr_resources.add("Personal"); arr_resources.add("Tecnológico"); arr_resources.add("Otros");
        btnResources.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resourcesDialog.showSpinerDialog();
            }
        });

        resourcesDialog = new SpinnerDialog(AnnualOperativePlanActivity.this,arr_resources, "Selecciona los recursos");
        resourcesDialog.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String item2, int position2) {
                btnResources.setText(item2);

            }
        });

        bthDay.add("1"); bthDay.add("2"); bthDay.add("3"); bthDay.add("4"); bthDay.add("5"); bthDay.add("6"); bthDay.add("7"); bthDay.add("8"); bthDay.add("9"); bthDay.add("10");
        bthDay.add("11"); bthDay.add("12"); bthDay.add("13"); bthDay.add("14"); bthDay.add("15"); bthDay.add("16"); bthDay.add("17"); bthDay.add("18"); bthDay.add("19"); bthDay.add("20");
        bthDay.add("21"); bthDay.add("22"); bthDay.add("23"); bthDay.add("24"); bthDay.add("25"); bthDay.add("26"); bthDay.add("27"); bthDay.add("28"); bthDay.add("29"); bthDay.add("30");
        bthDay.add("31");

        edtBthDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bthDayDialog.showSpinerDialog();
            }
        });

        bthDayDialog = new SpinnerDialog(AnnualOperativePlanActivity.this,bthDay, "Selecciona el Día");
        bthDayDialog.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String item2, int position2) {
                edtBthDay.setText(item2);

            }
        });

        bthMonth.add("1");bthMonth.add("2");bthMonth.add("3");bthMonth.add("4");bthMonth.add("5");bthMonth.add("6");bthMonth.add("7");bthMonth.add("8");bthMonth.add("9");bthMonth.add("10");
        bthMonth.add("11");bthMonth.add("12");

        edtBthMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bthMonthDialog.showSpinerDialog();
            }
        });

        bthMonthDialog = new SpinnerDialog(AnnualOperativePlanActivity.this,bthMonth, "Selecciona el Mes");
        bthMonthDialog.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String item2, int position2) {
                edtBthMonth.setText(item2);

            }
        });

        bthYear.add("2022");bthYear.add("2021");bthYear.add("2020");bthYear.add("2019");bthYear.add("2018");bthYear.add("2017");bthYear.add("2016");bthYear.add("2015");bthYear.add("2014");bthYear.add("2013");bthYear.add("2012");bthYear.add("2011");bthYear.add("2010");
        bthYear.add("2009");bthYear.add("2008");bthYear.add("2007");bthYear.add("2006");bthYear.add("2005");bthYear.add("2004");bthYear.add("2003");bthYear.add("2002");bthYear.add("2001");bthYear.add("2000");

        edtBthYear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bthYearDialog.showSpinerDialog();
            }
        });

        bthYearDialog = new SpinnerDialog(AnnualOperativePlanActivity.this,bthYear, "Selecciona el Año ");
        bthYearDialog.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String item2, int position2) {
                edtBthYear.setText(item2);

            }
        });



        edtEndBthDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bthEndDayDialog.showSpinerDialog();
            }
        });

        bthEndDayDialog = new SpinnerDialog(AnnualOperativePlanActivity.this,bthDay, "Selecciona el Día");
        bthEndDayDialog.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String item2, int position2) {
                edtEndBthDay.setText(item2);

            }
        });


        edtEndBthMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bthEndMonthDialog.showSpinerDialog();
            }
        });

        bthEndMonthDialog = new SpinnerDialog(AnnualOperativePlanActivity.this,bthMonth, "Selecciona el Mes");
        bthEndMonthDialog.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String item2, int position2) {
                edtEndBthMonth.setText(item2);

            }
        });

        edtEndBthYear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bthEndYearDialog.showSpinerDialog();
            }
        });

        bthEndYearDialog = new SpinnerDialog(AnnualOperativePlanActivity.this,bthYear, "Selecciona el Año ");
        bthEndYearDialog.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String item2, int position2) {
                edtEndBthYear.setText(item2);

            }
        });

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(btnArea.getText().toString())) {
                    Snackbar.make(rootLayout, "Debes completar todos los campos", Snackbar.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(btnAreaType.getText().toString())) {
                    Snackbar.make(rootLayout, "Debes completar todos los campos", Snackbar.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(edtManagementDescription.getText().toString())) {
                    Snackbar.make(rootLayout, "Debes completar todos los campos", Snackbar.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(btnMainObjective.getText().toString())) {
                    Snackbar.make(rootLayout, "Debes completar todos los campos", Snackbar.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(btnLinkedObjectives.getText().toString())) {
                    Snackbar.make(rootLayout, "Debes completar todos los campos", Snackbar.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(btnResources.getText().toString())) {
                    Snackbar.make(rootLayout, "Debes completar todos los campos", Snackbar.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(edtResults.getText().toString())) {
                    Snackbar.make(rootLayout, "Debes completar todos los campos", Snackbar.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(edtResponsable.getText().toString())) {
                    Snackbar.make(rootLayout, "Debes completar todos los campos", Snackbar.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(edtBthDay.getText().toString())) {
                    Snackbar.make(rootLayout, "Debes completar todos los campos", Snackbar.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(edtBthMonth.getText().toString())) {
                    Snackbar.make(rootLayout, "Debes completar todos los campos", Snackbar.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(edtBthYear.getText().toString())) {
                    Snackbar.make(rootLayout, "Debes completar todos los campos", Snackbar.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(edtEndBthDay.getText().toString())) {
                    Snackbar.make(rootLayout, "Debes completar todos los campos", Snackbar.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(edtEndBthMonth.getText().toString())) {
                    Snackbar.make(rootLayout, "Debes completar todos los campos", Snackbar.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(edtEndBthYear.getText().toString())) {
                    Snackbar.make(rootLayout, "Debes completar todos los campos", Snackbar.LENGTH_SHORT).show();
                } else if (!rdYes.isChecked() && !rdNo.isChecked()) {
                    Snackbar.make(rootLayout, "Debes completar todos los campos", Snackbar.LENGTH_SHORT).show();
                } else {

                    Long tsLong = System.currentTimeMillis()/1000;
                    String timestamp = tsLong.toString();

                    companyRef.child(post_key).child("Annual Plan").child(timestamp).child("management_area").setValue(btnArea.getText().toString());
                    companyRef.child(post_key).child("Annual Plan").child(timestamp).child("management_area_type").setValue(btnAreaType.getText().toString());
                    companyRef.child(post_key).child("Annual Plan").child(timestamp).child("management_description").setValue(edtManagementDescription.getText().toString());
                    companyRef.child(post_key).child("Annual Plan").child(timestamp).child("main_objective").setValue(btnMainObjective.getText().toString());
                    companyRef.child(post_key).child("Annual Plan").child(timestamp).child("linked_objective").setValue(btnLinkedObjectives.getText().toString());
                    companyRef.child(post_key).child("Annual Plan").child(timestamp).child("initiative_1").setValue(txtInitiative1.getText().toString());
                    companyRef.child(post_key).child("Annual Plan").child(timestamp).child("initiative_2").setValue(txtInitiative2.getText().toString());
                    companyRef.child(post_key).child("Annual Plan").child(timestamp).child("resources").setValue(btnResources.getText().toString());
                    companyRef.child(post_key).child("Annual Plan").child(timestamp).child("expected_result").setValue(edtResults.getText().toString());
                    companyRef.child(post_key).child("Annual Plan").child(timestamp).child("responsable").setValue(edtResponsable.getText().toString());
                    companyRef.child(post_key).child("Annual Plan").child(timestamp).child("start_day").setValue(edtBthDay.getText().toString());
                    companyRef.child(post_key).child("Annual Plan").child(timestamp).child("start_month").setValue(edtBthMonth.getText().toString());
                    companyRef.child(post_key).child("Annual Plan").child(timestamp).child("start_year").setValue(edtBthYear.getText().toString());
                    companyRef.child(post_key).child("Annual Plan").child(timestamp).child("start_date").setValue(edtBthDay.getText().toString()+"/"+edtBthMonth.getText().toString()+"/"+edtBthYear.getText().toString());
                    companyRef.child(post_key).child("Annual Plan").child(timestamp).child("start_month").setValue(edtEndBthMonth.getText().toString());
                    companyRef.child(post_key).child("Annual Plan").child(timestamp).child("start_year").setValue(edtEndBthYear.getText().toString());
                    companyRef.child(post_key).child("Annual Plan").child(timestamp).child("end_date").setValue(edtEndBthDay.getText().toString()+"/"+edtEndBthMonth.getText().toString()+"/"+edtEndBthYear.getText().toString());
                    companyRef.child(post_key).child("Annual Plan").child(timestamp).child("kp1").setValue(txtKpi1.getText().toString());
                    companyRef.child(post_key).child("Annual Plan").child(timestamp).child("kp2").setValue(txtKpi2.getText().toString());
                    companyRef.child(post_key).child("Annual Plan").child(timestamp).child("kp1_progress").setValue(seekBar1.getProgress()+"");
                    companyRef.child(post_key).child("Annual Plan").child(timestamp).child("kp2_progress").setValue(seekBar2.getProgress()+"");
                    if (rdYes.isChecked()) {
                        companyRef.child(post_key).child("Annual Plan").child(timestamp).child("goal_ready").setValue("Sí");
                    }
                    if (rdNo.isChecked()) {
                        companyRef.child(post_key).child("Annual Plan").child(timestamp).child("goal_ready").setValue("No");
                    }
                    Toasty.success(AnnualOperativePlanActivity.this, "Actualizado", Toast.LENGTH_LONG).show();
                    dialog.dismiss();

                }
            }
        });

        dialog.setView(finance_method);
        dialog.show();
    }

    public static class AnnualPlanViewHolder extends RecyclerView.ViewHolder {

        View mView;
        String management_area_st,management_area_type_st,management_description_st,main_objective_st,linked_objective_st,initiative_1_st,initiative_2_st,resources_st,expected_result_st,
                responsable_st,start_date_st,end_date_st,kp1_st,kp2_st,kp1_progress_st,kp2_progress_st,goal_ready_st;

        TextView txtManagementArea,txtManagementType,txtDescription,txtGoal;
        Button btnDetails;

        public AnnualPlanViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;

            txtManagementArea = mView.findViewById(R.id.txtManagementArea);
            txtManagementType = mView.findViewById(R.id.txtManagementType);
            txtDescription = mView.findViewById(R.id.txtDescription);
            txtGoal = mView.findViewById(R.id.txtGoal);
            btnDetails = mView.findViewById(R.id.btnDetails);



        }
        public void setManagement_area(String management_area) {
            management_area_st = management_area;
        }

        public void setManagement_area_type(String management_area_type) {
            management_area_type_st = management_area_type;
        }

        public void setManagement_description(String management_description) {
            management_description_st = management_description;
        }


        public void setMain_objective(String main_objective) {
            main_objective_st = main_objective;
        }


        public void setLinked_objective(String linked_objective) {
            linked_objective_st = linked_objective;
        }


        public void setInitiative_1(String initiative_1) {
            initiative_1_st = initiative_1;
        }

        public void setInitiative_2(String initiative_2) {
            initiative_2_st = initiative_2;
        }

        public void setResources(String resources) {
            resources_st = resources;
        }

        public void setExpected_result(String expected_result) {
            expected_result_st = expected_result;
        }

        public void setResponsable(String responsable) {
            responsable_st = responsable;
        }


        public void setStart_date(String start_date) {
            start_date_st = start_date;
        }

        public void setEnd_date(String end_date) {
            end_date_st = end_date;
        }

        public void setKp1(String kp1) {
            kp1_st = kp1;
        }

        public void setKp2(String kp2) {
            kp2_st = kp2;
        }


        public void setKp1_progress(String kp1_progress) {
            kp1_progress_st = kp1_progress;
        }

        public void setKp2_progress(String kp2_progress) {
            kp2_progress_st = kp2_progress;
        }
        public void setGoal_ready(String goal_ready) {
            goal_ready_st = goal_ready;
        }
    }
}
