package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.PeopleManagement.RoasterAndPerfomance;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.PeopleManagement.JobProfiles.JobProfileModel;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.PeopleManagement.PersonalFiles.JobProfileFilesActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.PeopleManagement.PersonalFiles.PersonalFilesActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;
import com.xw.repo.BubbleSeekBar;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import es.dmoral.toasty.Toasty;
import in.galaxyofandroid.spinerdialog.OnSpinerItemClick;
import in.galaxyofandroid.spinerdialog.SpinnerDialog;


public class WorkerObjetivesFragment extends Fragment {

    Button btnRegister;
    RecyclerView recyclerView;
    String post_key;
    DatabaseReference companyRef;
    AlertDialog workers_dialog;

    ArrayList<String> arr_objective_type =new ArrayList<>();
    SpinnerDialog objectiveTypeDialog;
    ArrayList<String> arr_frequency =new ArrayList<>();
    SpinnerDialog frequencyTypeDialog;
    ArrayList<String> arr_area =new ArrayList<>();
    SpinnerDialog areaDialog;
    ArrayList<String> arr_qualification =new ArrayList<>();
    SpinnerDialog qualificationDialog;
    SpinnerDialog qualificationDialog2;
    int objective_progress;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_worker_objetives, container, false);

        post_key = getActivity().getIntent().getExtras().getString("post_key");
        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");
        btnRegister = view.findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddObjectiveDialog();
            }
        });

        recyclerView = view.findViewById(R.id.recyclerView);

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setReverseLayout(false);
        linearLayoutManager.setStackFromEnd(false);
        recyclerView.setLayoutManager(linearLayoutManager);

        showObjectives();

        return view;
    }

    private void showObjectives() {
        Query query =  companyRef.child(post_key).child("People Management Objectives").orderByChild("timestamp");
        FirebaseRecyclerAdapter<ObjectiveModel, ObjectiveViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<ObjectiveModel, ObjectiveViewHolder>
                (ObjectiveModel.class,R.layout.objective_item,ObjectiveViewHolder.class,query) {
            @Override
            protected void populateViewHolder(final ObjectiveViewHolder viewHolder, ObjectiveModel model, int position) {
                final String postKey = getRef(position).getKey();
                viewHolder.setObjective(model.getObjective());
                viewHolder.setObjective_destination(model.getObjective_destination());
                viewHolder.setObjective_frequency(model.getObjective_frequency());
                viewHolder.setObjective_priority(model.getObjective_priority());
                viewHolder.setObjective_type(model.getObjective_type());

                viewHolder.txtObjectiveName.setText(viewHolder.my_objective);
                viewHolder.txtObjectiveDestination.setText("Encargado del objetivo: "+viewHolder.my_objective_destination);
                viewHolder.txtPriority.setText("Priodidad: "+viewHolder.my_objective_priority);
                viewHolder.txtFrequency.setText("Seguimiento: "+viewHolder.my_objective_frequency);

                companyRef.child(post_key).child("People Management Objectives").child(postKey).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChild("objective_competence")) {
                            String objective_competence = dataSnapshot.child("objective_competence").getValue().toString();
                            viewHolder.txtCompetence.setText("Competencia: "+objective_competence);
                        }
                        if (dataSnapshot.hasChild("objective_achievement")) {
                            String objective_achievement = dataSnapshot.child("objective_achievement").getValue().toString();
                            viewHolder.txtAchievement.setText("Logro: "+objective_achievement);
                        }
                        if (dataSnapshot.hasChild("objective_progress")) {
                            objective_progress = dataSnapshot.child("objective_progress").getValue(Integer.class);
                            if (objective_progress <= 25) {
                                viewHolder.txtProgress.setText("Progreso: "+objective_progress+"% Necesita tomar medidas de mejora urgente así como acompañamiento frecuente de un superior");
                            }
                            if (objective_progress > 25 && objective_progress < 50) {
                                viewHolder.txtProgress.setText("Progreso: "+objective_progress+"% Necesitas detectar tus debilidades para convertirlas en fortalezas");
                            }
                            if (objective_progress >= 50 && objective_progress < 95) {
                                viewHolder.txtProgress.setText("Progreso: "+objective_progress+"% Próximo a cumplir el objetivo");
                            }
                            if (objective_progress >= 95) {
                                viewHolder.txtProgress.setText("Progreso: "+objective_progress+"% Felicitaciones objetivo cumplido");
                            }
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                viewHolder.btnAddCompetence.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final AlertDialog dialog = new AlertDialog.Builder(getActivity()).create();

                        LayoutInflater inflater = LayoutInflater.from(getActivity());
                        View finance_method = inflater.inflate(R.layout.set_objectuve_competence_dialog,null);

                        final RadioButton rdCompetence1,rdCompetence2,rdCompetence3,rdCompetence4,rdCompetence5,rdCompetence6,rdCompetence7,rdCompetence8,rdCompetence9,rdCompetence10,rdCompetence11,rdCompetence12;
                        Button btnRegisterCompetence;
                        final LinearLayout rootLayout;

                        rdCompetence1 = finance_method.findViewById(R.id.rdCompetence1);
                        rdCompetence2 = finance_method.findViewById(R.id.rdCompetence2);
                        rdCompetence3 = finance_method.findViewById(R.id.rdCompetence3);
                        rdCompetence4 = finance_method.findViewById(R.id.rdCompetence4);
                        rdCompetence5 = finance_method.findViewById(R.id.rdCompetence5);
                        rdCompetence6 = finance_method.findViewById(R.id.rdCompetence6);
                        rdCompetence7 = finance_method.findViewById(R.id.rdCompetence7);
                        rdCompetence8 = finance_method.findViewById(R.id.rdCompetence8);
                        rdCompetence9 = finance_method.findViewById(R.id.rdCompetence9);
                        rdCompetence10 = finance_method.findViewById(R.id.rdCompetence10);
                        rdCompetence11 = finance_method.findViewById(R.id.rdCompetence11);
                        rdCompetence12 = finance_method.findViewById(R.id.rdCompetence12);
                        btnRegisterCompetence = finance_method.findViewById(R.id.btnRegisterCompetence);
                        rootLayout = finance_method.findViewById(R.id.rootLayout);

                        btnRegisterCompetence.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (!rdCompetence1.isChecked() && !rdCompetence2.isChecked() && !rdCompetence3.isChecked() && !rdCompetence4.isChecked() && !rdCompetence5.isChecked() && !rdCompetence6.isChecked() && !rdCompetence7.isChecked() && !rdCompetence8.isChecked()
                                        && !rdCompetence9.isChecked() && !rdCompetence10.isChecked() && !rdCompetence11.isChecked()&& !rdCompetence12.isChecked()) {
                                    Snackbar.make(rootLayout, "Debes seleccionar una competencia", Snackbar.LENGTH_SHORT).show();
                                } else {
                                    if (rdCompetence1.isChecked()) {
                                        companyRef.child(post_key).child("People Management Objectives").child(postKey).child("objective_competence").setValue("Innovación");
                                        companyRef.child(post_key).child("People Management Objectives").child(postKey).child("objective_achievement").setValue("Creación, desarrollo o mejora de productos, procesos, sistemas.");
                                    }
                                    if (rdCompetence2.isChecked()) {
                                        companyRef.child(post_key).child("People Management Objectives").child(postKey).child("objective_competence").setValue("Rigurosidad en el Trabajo");
                                        companyRef.child(post_key).child("People Management Objectives").child(postKey).child("objective_achievement").setValue("Mejoras de calidad.");
                                    }
                                    if (rdCompetence3.isChecked()) {
                                        companyRef.child(post_key).child("People Management Objectives").child(postKey).child("objective_competence").setValue("Análisis");
                                        companyRef.child(post_key).child("People Management Objectives").child(postKey).child("objective_achievement").setValue("Reducción de tiempos o plazos.");
                                    }
                                    if (rdCompetence4.isChecked()) {
                                        companyRef.child(post_key).child("People Management Objectives").child(postKey).child("objective_competence").setValue("Dirección");
                                        companyRef.child(post_key).child("People Management Objectives").child(postKey).child("objective_achievement").setValue("Toma de iniciativas.");
                                    }
                                    if (rdCompetence5.isChecked()) {
                                        companyRef.child(post_key).child("People Management Objectives").child(postKey).child("objective_competence").setValue("Comunicación");
                                        companyRef.child(post_key).child("People Management Objectives").child(postKey).child("objective_achievement").setValue("Resolución de problemas.");
                                    }
                                    if (rdCompetence6.isChecked()) {
                                        companyRef.child(post_key).child("People Management Objectives").child(postKey).child("objective_competence").setValue("Interacción");
                                        companyRef.child(post_key).child("People Management Objectives").child(postKey).child("objective_achievement").setValue("Mejora de relaciones humanas.");
                                    }
                                    if (rdCompetence7.isChecked()) {
                                        companyRef.child(post_key).child("People Management Objectives").child(postKey).child("objective_competence").setValue("Trabajo en equipo");
                                        companyRef.child(post_key).child("People Management Objectives").child(postKey).child("objective_achievement").setValue("Aumento de beneficios.");
                                    }
                                    if (rdCompetence8.isChecked()) {
                                        companyRef.child(post_key).child("People Management Objectives").child(postKey).child("objective_competence").setValue("Gestión del estrés");
                                        companyRef.child(post_key).child("People Management Objectives").child(postKey).child("objective_achievement").setValue("Aumento de ventas.");
                                    }
                                    if (rdCompetence9.isChecked()) {
                                        companyRef.child(post_key).child("People Management Objectives").child(postKey).child("objective_competence").setValue("Adaptación");
                                        companyRef.child(post_key).child("People Management Objectives").child(postKey).child("objective_achievement").setValue("Reducción de costos.");
                                    }
                                    if (rdCompetence10.isChecked()) {
                                        companyRef.child(post_key).child("People Management Objectives").child(postKey).child("objective_competence").setValue("Organización");
                                        companyRef.child(post_key).child("People Management Objectives").child(postKey).child("objective_achievement").setValue("Mejora de productividad.");
                                    }
                                    if (rdCompetence11.isChecked()) {
                                        companyRef.child(post_key).child("People Management Objectives").child(postKey).child("objective_competence").setValue("Orientación al Logro");
                                        companyRef.child(post_key).child("People Management Objectives").child(postKey).child("objective_achievement").setValue("Obtención de felicitaciones o premios.");
                                    }
                                    if (rdCompetence12.isChecked()) {
                                        companyRef.child(post_key).child("People Management Objectives").child(postKey).child("objective_competence").setValue("Especialización");
                                        companyRef.child(post_key).child("People Management Objectives").child(postKey).child("objective_achievement").setValue("Mejora de resultados a igualdad de recursos.");
                                    }
                                    Toasty.success(getActivity(), "Competencia Asignada", Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                }
                            }
                        });

                        dialog.setView(finance_method);
                        dialog.show();

                    }
                });

                viewHolder.btnPerfomance.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final AlertDialog dialog = new AlertDialog.Builder(getActivity()).create();

                        LayoutInflater inflater = LayoutInflater.from(getActivity());
                        View finance_method = inflater.inflate(R.layout.set_objective_perfomance_dialog,null);

                        final BubbleSeekBar seekBarQualification;
                        final Button btnQualification1,btnQualification2,btnRegisterQualification;
                        final LinearLayout rootLayout;

                        seekBarQualification = finance_method.findViewById(R.id.seekBarQualification);
                        btnQualification1 = finance_method.findViewById(R.id.btnQualification1);
                        btnQualification2 = finance_method.findViewById(R.id.btnQualification2);
                        btnRegisterQualification = finance_method.findViewById(R.id.btnRegisterQualification);
                        rootLayout = finance_method.findViewById(R.id.rootLayout);

                        arr_qualification.add("Excelente"); arr_qualification.add("Bueno"); arr_qualification.add("Regular"); arr_qualification.add("Malo");

                        btnQualification1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                qualificationDialog.showSpinerDialog();
                            }
                        });

                        qualificationDialog = new SpinnerDialog(getActivity(),arr_qualification, "Selecciona la Calificación");
                        qualificationDialog.bindOnSpinerListener(new OnSpinerItemClick() {
                            @Override
                            public void onClick(String item2, int position2) {
                                btnQualification1.setText(item2);

                            }
                        });

                        btnQualification2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                qualificationDialog2.showSpinerDialog();
                            }
                        });

                        qualificationDialog2 = new SpinnerDialog(getActivity(),arr_qualification, "Selecciona la Calificación");
                        qualificationDialog2.bindOnSpinerListener(new OnSpinerItemClick() {
                            @Override
                            public void onClick(String item2, int position2) {
                                btnQualification2.setText(item2);

                            }
                        });

                        btnRegisterQualification.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (TextUtils.isEmpty(btnQualification1.getText().toString())) {
                                    Snackbar.make(rootLayout,"Debes completar todos los datos",Snackbar.LENGTH_SHORT).show();
                                }
                                if (TextUtils.isEmpty(btnQualification2.getText().toString())) {
                                    Snackbar.make(rootLayout, "Debes completar todos los datos", Snackbar.LENGTH_SHORT).show();
                                } else {
                                    companyRef.child(post_key).child("People Management Objectives").child(postKey).child("objective_progress").setValue(seekBarQualification.getProgress());
                                    companyRef.child(post_key).child("People Management Objectives").child(postKey).child("objective_qualification1").setValue(btnQualification1.getText().toString());
                                    companyRef.child(post_key).child("People Management Objectives").child(postKey).child("objective_qualification2").setValue(btnQualification2.getText().toString());
                                    Toasty.success(getActivity(), "Registrado", Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                }
                            }
                        });

                        dialog.setView(finance_method);
                        dialog.show();
                    }
                });
            }
        };
        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    private void showAddObjectiveDialog() {
        final AlertDialog dialog = new AlertDialog.Builder(getActivity()).create();

        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View finance_method = inflater.inflate(R.layout.add_objective_dialog,null);

        final Button btnObjectiveType,btnWorker,btnFrequency,btnArea,btnRegisterObjective;
        final EditText edtTeamName,edtObjective;
        final RadioButton rdHigh,rdMiddle,rdLow,rdYes1,rdYes2,rdYes3,rdYes4,rdYes5,rdNo1,rdNo2,rdNo3,rdNo4,rdNo5;
        final LinearLayout workerObjectivesLayout,teamObjectivesLayout,areaObjectivesLayout,rootLayout;

        btnObjectiveType = finance_method.findViewById(R.id.btnObjectiveType);
        btnWorker = finance_method.findViewById(R.id.btnWorker);
        btnFrequency = finance_method.findViewById(R.id.btnFrequency);
        btnArea = finance_method.findViewById(R.id.btnArea);
        edtTeamName = finance_method.findViewById(R.id.edtTeamName);
        edtObjective = finance_method.findViewById(R.id.edtObjective);
        rdHigh = finance_method.findViewById(R.id.rdHigh);
        rdMiddle = finance_method.findViewById(R.id.rdMiddle);
        rdLow = finance_method.findViewById(R.id.rdLow);
        rdYes1 = finance_method.findViewById(R.id.rdYes1);
        rdYes2 = finance_method.findViewById(R.id.rdYes2);
        rdYes3 = finance_method.findViewById(R.id.rdYes3);
        rdYes4 = finance_method.findViewById(R.id.rdYes4);
        rdYes5 = finance_method.findViewById(R.id.rdYes5);
        rdNo1 = finance_method.findViewById(R.id.rdNo1);
        rdNo2 = finance_method.findViewById(R.id.rdNo2);
        rdNo3 = finance_method.findViewById(R.id.rdNo3);
        rdNo4 = finance_method.findViewById(R.id.rdNo4);
        rdNo5 = finance_method.findViewById(R.id.rdNo5);
        workerObjectivesLayout = finance_method.findViewById(R.id.workerObjectivesLayout);
        teamObjectivesLayout = finance_method.findViewById(R.id.teamObjectivesLayout);
        areaObjectivesLayout = finance_method.findViewById(R.id.areaObjectivesLayout);
        btnRegisterObjective = finance_method.findViewById(R.id.btnRegisterObjective);
        rootLayout = finance_method.findViewById(R.id.rootLayout);

        arr_objective_type.add("Objetivo Individual");arr_objective_type.add("Objetivo por Equipo");arr_objective_type.add("Objetivo por Departamento");arr_objective_type.add("Objetivo de la Empresa");

        btnObjectiveType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                objectiveTypeDialog.showSpinerDialog();
            }
        });

        objectiveTypeDialog = new SpinnerDialog(getActivity(),arr_objective_type, "Selecciona el tipo de objetivo");
        objectiveTypeDialog.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String item2, int position2) {
                if (item2.equals("Objetivo Individual")) {
                    workerObjectivesLayout.setVisibility(View.VISIBLE);
                    teamObjectivesLayout.setVisibility(View.GONE);
                    areaObjectivesLayout.setVisibility(View.GONE);
                }
                if (item2.equals("Objetivo por Equipo")) {
                    workerObjectivesLayout.setVisibility(View.GONE);
                    teamObjectivesLayout.setVisibility(View.VISIBLE);
                    areaObjectivesLayout.setVisibility(View.GONE);
                }
                if (item2.equals("Objetivo por Departamento")) {
                    workerObjectivesLayout.setVisibility(View.GONE);
                    teamObjectivesLayout.setVisibility(View.GONE);
                    areaObjectivesLayout.setVisibility(View.VISIBLE);
                }
                if (item2.equals("Objetivo de la Empresa")) {
                    workerObjectivesLayout.setVisibility(View.GONE);
                    teamObjectivesLayout.setVisibility(View.GONE);
                    areaObjectivesLayout.setVisibility(View.GONE);
                }
                btnObjectiveType.setText(item2);

            }
        });

        arr_frequency.add("Diario");arr_frequency.add("Semanal");arr_frequency.add("Quincenal");arr_frequency.add("Mensual");arr_frequency.add("Bimestral");arr_frequency.add("Trimestral");arr_frequency.add("Semestral");arr_frequency.add("Anual");

        btnFrequency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                frequencyTypeDialog.showSpinerDialog();
            }
        });

        frequencyTypeDialog = new SpinnerDialog(getActivity(),arr_frequency, "Selecciona el seguimiento");
        frequencyTypeDialog.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String item2, int position2) {
                btnFrequency.setText(item2);

            }
        });

        arr_area.add("Junta Directiva");arr_area.add("Presidencia");arr_area.add("Dirección General");arr_area.add("Gerencia General");arr_area.add("Financiera y Contabilidad");arr_area.add("Administrativa");arr_area.add("Comercial y Ventas");
        arr_area.add("Recursos Humanos");arr_area.add("Producción y Logística");arr_area.add("Marketing");arr_area.add("Sistemas y Tecnología");arr_area.add("Archivos centrales");arr_area.add("Compras");arr_area.add("Secretaría General");

        btnArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                areaDialog.showSpinerDialog();
            }
        });

        areaDialog = new SpinnerDialog(getActivity(),arr_area, "Selecciona el Area");
        areaDialog.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String item2, int position2) {
                btnArea.setText(item2);

            }
        });

        btnWorker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                workers_dialog = new AlertDialog.Builder(getActivity()).create();

                LayoutInflater inflater = LayoutInflater.from(getActivity());
                View finance_method = inflater.inflate(R.layout.worker_list_dialog,null);

                recyclerView = finance_method.findViewById(R.id.recyclerView);

                recyclerView.setHasFixedSize(true);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                linearLayoutManager.setReverseLayout(false);
                linearLayoutManager.setStackFromEnd(false);
                recyclerView.setLayoutManager(linearLayoutManager);

                showWorkers();

                workers_dialog.setView(finance_method);
                workers_dialog.show();

            }
            private void showWorkers() {
                Query query = companyRef.child(post_key).child("Job Profile").orderByChild("timestamp");
                FirebaseRecyclerAdapter<JobProfileModel, JobProfileViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<JobProfileModel, JobProfileViewHolder>
                        (JobProfileModel.class,R.layout.job_profile_item,JobProfileViewHolder.class,query) {
                    @Override
                    protected void populateViewHolder(final JobProfileViewHolder viewHolder, JobProfileModel model, int position) {
                        final String postKey = getRef(position).getKey();
                        viewHolder.setJob_profile_area(model.getJob_profile_area());
                        viewHolder.setJob_profile_job_name(model.getJob_profile_job_name());
                        viewHolder.setJob_profile_name(model.getJob_profile_name());
                        viewHolder.setJob_profile_surname(model.getJob_profile_surname());

                        viewHolder.txtName.setText(viewHolder.my_job_profile_name+" "+viewHolder.my_job_profile_surname);
                        viewHolder.txtCharge.setText("Cargo: "+viewHolder.my_job_profile_job_name);
                        viewHolder.txtArea.setText("Área: "+viewHolder.my_job_profile_area);

                        viewHolder.btnViewJobProfile.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                btnWorker.setText(viewHolder.my_job_profile_name+" "+viewHolder.my_job_profile_surname);
                                workers_dialog.dismiss();
                            }
                        });

                    }
                };
                recyclerView.setAdapter(firebaseRecyclerAdapter);
            }
        });

        btnRegisterObjective.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(btnObjectiveType.getText().toString())) {
                    Snackbar.make(rootLayout,"Debes completar todos los datos",Snackbar.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(edtObjective.getText().toString())) {
                    Snackbar.make(rootLayout,"Debes completar todos los datos",Snackbar.LENGTH_SHORT).show();
                }
                else if (!rdHigh.isChecked() && !rdMiddle.isChecked() && !rdLow.isChecked()) {
                    Snackbar.make(rootLayout,"Debes completar todos los datos",Snackbar.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(btnFrequency.getText().toString())) {
                    Snackbar.make(rootLayout,"Debes completar todos los datos",Snackbar.LENGTH_SHORT).show();
                }
                else if (!rdYes1.isChecked() && !rdNo1.isChecked()) {
                    Snackbar.make(rootLayout,"Debes completar todos los datos",Snackbar.LENGTH_SHORT).show();
                }
                else if (!rdYes2.isChecked() && !rdNo2.isChecked()) {
                    Snackbar.make(rootLayout,"Debes completar todos los datos",Snackbar.LENGTH_SHORT).show();
                }
                else if (!rdYes3.isChecked() && !rdNo3.isChecked()) {
                    Snackbar.make(rootLayout,"Debes completar todos los datos",Snackbar.LENGTH_SHORT).show();
                }
                else if (!rdYes4.isChecked() && !rdNo4.isChecked()) {
                    Snackbar.make(rootLayout,"Debes completar todos los datos",Snackbar.LENGTH_SHORT).show();
                } else if (!rdYes5.isChecked() && !rdNo5.isChecked()) {
                    Snackbar.make(rootLayout, "Debes completar todos los datos", Snackbar.LENGTH_SHORT).show();
                } else {
                    Long tsLong = System.currentTimeMillis()/1000;
                    String timestamp = tsLong.toString();
                    companyRef.child(post_key).child("People Management Objectives").child(timestamp).child("objective_type").setValue(btnObjectiveType.getText().toString());
                    if (btnObjectiveType.getText().toString().equals("Objetivo Individual")) {
                        companyRef.child(post_key).child("People Management Objectives").child(timestamp).child("objective_destination").setValue(btnWorker.getText().toString());

                    }
                    if (btnObjectiveType.getText().toString().equals("Objetivo por Equipo")) {
                        companyRef.child(post_key).child("People Management Objectives").child(timestamp).child("objective_destination").setValue(edtTeamName.getText().toString());
                    }
                    if (btnObjectiveType.getText().toString().equals("Objetivo por Departamento")) {
                        companyRef.child(post_key).child("People Management Objectives").child(timestamp).child("objective_destination").setValue(btnArea.getText().toString());
                    }
                    if (btnObjectiveType.getText().toString().equals("Objetivo de la Empresa")) {
                        companyRef.child(post_key).child("People Management Objectives").child(timestamp).child("objective_destination").setValue("Empresa");
                    }

                    companyRef.child(post_key).child("People Management Objectives").child(timestamp).child("objective").setValue(edtObjective.getText().toString());
                    if (rdHigh.isChecked()) {
                        companyRef.child(post_key).child("People Management Objectives").child(timestamp).child("objective_priority").setValue(rdHigh.getText().toString());
                    }
                    if (rdMiddle.isChecked()) {
                        companyRef.child(post_key).child("People Management Objectives").child(timestamp).child("objective_priority").setValue(rdMiddle.getText().toString());
                    }
                    if (rdLow.isChecked()) {
                        companyRef.child(post_key).child("People Management Objectives").child(timestamp).child("objective_priority").setValue(rdLow.getText().toString());
                    }
                    companyRef.child(post_key).child("People Management Objectives").child(timestamp).child("objective_frequency").setValue(btnFrequency.getText().toString());
                    if (rdYes1.isChecked()) {
                        companyRef.child(post_key).child("People Management Objectives").child(timestamp).child("smart_1").setValue(rdYes1.getText().toString());
                    }
                    if (rdNo1.isChecked()) {
                        companyRef.child(post_key).child("People Management Objectives").child(timestamp).child("smart_1").setValue(rdNo1.getText().toString());
                    }
                    if (rdYes2.isChecked()) {
                        companyRef.child(post_key).child("People Management Objectives").child(timestamp).child("smart_2").setValue(rdYes2.getText().toString());
                    }
                    if (rdNo2.isChecked()) {
                        companyRef.child(post_key).child("People Management Objectives").child(timestamp).child("smart_2").setValue(rdNo2.getText().toString());
                    }
                    if (rdYes3.isChecked()) {
                        companyRef.child(post_key).child("People Management Objectives").child(timestamp).child("smart_3").setValue(rdYes3.getText().toString());
                    }
                    if (rdNo3.isChecked()) {
                        companyRef.child(post_key).child("People Management Objectives").child(timestamp).child("smart_3").setValue(rdNo3.getText().toString());
                    }
                    if (rdYes4.isChecked()) {
                        companyRef.child(post_key).child("People Management Objectives").child(timestamp).child("smart_4").setValue(rdYes4.getText().toString());
                    }
                    if (rdNo4.isChecked()) {
                        companyRef.child(post_key).child("People Management Objectives").child(timestamp).child("smart_4").setValue(rdNo4.getText().toString());
                    }
                    if (rdYes5.isChecked()) {
                        companyRef.child(post_key).child("People Management Objectives").child(timestamp).child("smart_5").setValue(rdYes5.getText().toString());
                    }
                    if (rdNo5.isChecked()) {
                        companyRef.child(post_key).child("People Management Objectives").child(timestamp).child("smart_5").setValue(rdNo5.getText().toString());
                    }
                    Toasty.success(getActivity(), "Registrado", Toast.LENGTH_LONG).show();
                    dialog.dismiss();

                }

            }
        });

        dialog.setView(finance_method);
        dialog.show();
    }

    public static class JobProfileViewHolder extends RecyclerView.ViewHolder {

        View mView;
        String my_job_profile_name,my_job_profile_surname,my_job_profile_area,my_job_profile_job_name;
        TextView txtName,txtCharge,txtArea;
        ImageView btnViewJobProfile;

        public JobProfileViewHolder(@NonNull View itemView) {
            super(itemView);

            mView = itemView;

            txtName = mView.findViewById(R.id.txtName);
            txtCharge = mView.findViewById(R.id.txtCharge);
            txtArea = mView.findViewById(R.id.txtArea);
            btnViewJobProfile = mView.findViewById(R.id.btnViewJobProfile);

        }
        public void setJob_profile_name(String job_profile_name) {
            my_job_profile_name = job_profile_name;
        }
        public void setJob_profile_surname(String job_profile_surname) {
            my_job_profile_surname = job_profile_surname;
        }
        public void setJob_profile_area(String job_profile_area) {
            my_job_profile_area = job_profile_area;
        }
        public void setJob_profile_job_name(String job_profile_job_name) {
            my_job_profile_job_name = job_profile_job_name;
        }
    }

    public static class ObjectiveViewHolder extends RecyclerView.ViewHolder {
        View mView;
        TextView txtObjectiveName,txtObjectiveDestination,txtPriority,txtFrequency,txtCompetence,txtAchievement,txtProgress;
        ImageView btnAddCompetence;
        Button btnPerfomance;
        String my_objective,my_objective_destination,my_objective_frequency,my_objective_priority,my_objective_type;

        public ObjectiveViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;

            txtObjectiveName = mView.findViewById(R.id.txtObjectiveName);
            txtObjectiveDestination = mView.findViewById(R.id.txtObjectiveDestination);
            txtPriority = mView.findViewById(R.id.txtPriority);
            txtFrequency = mView.findViewById(R.id.txtFrequency);
            txtCompetence = mView.findViewById(R.id.txtCompetence);
            txtAchievement = mView.findViewById(R.id.txtAchievement);
            btnAddCompetence = mView.findViewById(R.id.btnAddCompetence);
            btnPerfomance = mView.findViewById(R.id.btnPerfomance);
            txtProgress = mView.findViewById(R.id.txtProgress);

        }
        public void setObjective(String objective) {
            my_objective = objective;
        }

        public void setObjective_destination(String objective_destination) {
            my_objective_destination = objective_destination;
        }

        public void setObjective_frequency(String objective_frequency) {
            my_objective_frequency = objective_frequency;
        }

        public void setObjective_priority(String objective_priority) {
            my_objective_priority = objective_priority;
        }

        public void setObjective_type(String objective_type) {
            my_objective_type = objective_type;
        }
    }
}
