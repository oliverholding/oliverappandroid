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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ServerValue;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.PeopleManagement.JobProfiles.JobProfileModel;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;
import in.galaxyofandroid.spinerdialog.OnSpinerItemClick;
import in.galaxyofandroid.spinerdialog.SpinnerDialog;

public class WorkerScheduleFragment extends Fragment {

    DatabaseReference companyRef;
    String post_key;
    RecyclerView recyclerView;

    Button btnRegister;

    ArrayList<String> arr_area =new ArrayList<>();
    SpinnerDialog areaDialog;
    ArrayList<String> arr_denomination =new ArrayList<>();
    SpinnerDialog denominationDialog;
    ArrayList<String> arr_boss =new ArrayList<>();
    SpinnerDialog bossDialog;
    ArrayList<String> arr_responsable_area =new ArrayList<>();
    SpinnerDialog responsable_areaDialog;
    ArrayList<String> arr_mission =new ArrayList<>();
    SpinnerDialog missionDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_worker_schedule, container, false);

        btnRegister = view.findViewById(R.id.btnRegister);

        post_key = getActivity().getIntent().getExtras().getString("post_key");
        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");

        recyclerView = view.findViewById(R.id.recyclerView);

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setReverseLayout(false);
        linearLayoutManager.setStackFromEnd(false);
        recyclerView.setLayoutManager(linearLayoutManager);

        showJobProfile();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AlertDialog dialog = new AlertDialog.Builder(getActivity()).create();

                LayoutInflater inflater = LayoutInflater.from(getActivity());
                View finance_method = inflater.inflate(R.layout.add_job_profile_dialog,null);

                final EditText edtName,edtSurname,edtJobName,edtPeopleNumber,edtJobMission;
                final Button btnArea,btnDenomination,btnImmediateBoss,btnResponsableArea,btnJobMission,btnRegister;
                final LinearLayout rootLayout;

                edtName = finance_method.findViewById(R.id.edtName);
                edtSurname = finance_method.findViewById(R.id.edtSurname);
                edtJobName = finance_method.findViewById(R.id.edtJobName);
                edtPeopleNumber = finance_method.findViewById(R.id.edtPeopleNumber);
                edtJobMission = finance_method.findViewById(R.id.edtJobMission);
                btnArea = finance_method.findViewById(R.id.btnArea);
                btnDenomination = finance_method.findViewById(R.id.btnDenomination);
                btnImmediateBoss = finance_method.findViewById(R.id.btnImmediateBoss);
                btnResponsableArea = finance_method.findViewById(R.id.btnResponsableArea);
                btnJobMission = finance_method.findViewById(R.id.btnJobMission);
                btnRegister = finance_method.findViewById(R.id.btnRegister);
                rootLayout = finance_method.findViewById(R.id.rootLayout);

                arr_area.add("Junta Directiva");arr_area.add("Presidencia");arr_area.add("Dirección General");arr_area.add("Gerencia General");arr_area.add("Financiera y Contabilidad");arr_area.add("Administrativa");arr_area.add("Comercial y Ventas");
                arr_area.add("Recursos Humanos");arr_area.add("Producción y Logística");arr_area.add("Marketing");arr_area.add("Sistemas y Tecnología");arr_area.add("Archivos centrales");arr_area.add("Compras");arr_area.add("Secretaría General");

                btnArea.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        areaDialog.showSpinerDialog();
                    }
                });

                areaDialog = new SpinnerDialog(getActivity(),arr_area, "Selecciona la dependencia");
                areaDialog.bindOnSpinerListener(new OnSpinerItemClick() {
                    @Override
                    public void onClick(String item2, int position2) {
                        btnArea.setText(item2);

                    }
                });

                arr_denomination.add("Practicante / Becario"); arr_denomination.add("Operario/a");arr_denomination.add("Asistente administrativo/a");arr_denomination.add("Secretaria/o");arr_denomination.add("Recepcionista");arr_denomination.add("Comercial o Vendedor");arr_denomination.add("Analista");
                arr_denomination.add("Especialista");arr_denomination.add("Jefe de Área");arr_denomination.add("Gerente");arr_denomination.add("Directivo");arr_denomination.add("Presidente");arr_denomination.add("Socio");

                btnDenomination.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        denominationDialog.showSpinerDialog();
                    }
                });

                denominationDialog = new SpinnerDialog(getActivity(),arr_denomination, "Selecciona la denominación");
                denominationDialog.bindOnSpinerListener(new OnSpinerItemClick() {
                    @Override
                    public void onClick(String item2, int position2) {
                        btnDenomination.setText(item2);

                    }
                });

                arr_boss.add("Junta Directiva");arr_boss.add("Presidencia");
                arr_boss.add("Director de Finanzas y Contabilidad");arr_boss.add("Director Administrador");arr_boss.add("Director Comercial y Ventas");arr_boss.add("Director de Recursos Humanos");arr_boss.add("Director de Producción y Logística");arr_boss.add("Director de Marketing");arr_boss.add("Director de Sistemas y Tecnología");arr_boss.add("Director de Archivos centrales");arr_boss.add("Director de Secretaría General");arr_boss.add("Director de Compras");
                arr_boss.add("Gerente de Finanzas y Contabilidad");arr_boss.add("Gerente de Administración");arr_boss.add("Gerente Comercial y Ventas");arr_boss.add("Gerente de Recursos Humanos");arr_boss.add("Gerente de Producción y Logística");arr_boss.add("Gerente de Marketing");arr_boss.add("Gerente de Sistemas y Tecnología");arr_boss.add("Gerente de Archivos centrales");arr_boss.add("Gerente de Secretaría General");arr_boss.add("Gerente de Compras");
                arr_boss.add("Jefe de Finanzas y Contabilidad");arr_boss.add("Jefe de Administración");arr_boss.add("Jefe Comercial y Ventas");arr_boss.add("Jefe de Recursos Humanos");arr_boss.add("Jefe de Producción y Logística");arr_boss.add("Jefe de Marketing");arr_boss.add("Jefe de Sistemas y Tecnología");arr_boss.add("Jefe de Archivos centrales");arr_boss.add("Jefe de Secretaría General");arr_boss.add("Jefe de Compras");
                arr_boss.add("Encargado de Finanzas y Contabilidad");arr_boss.add("Encargado de Administración");arr_boss.add("Encargado Comercial y Ventas");arr_boss.add("Encargado de Recursos Humanos");arr_boss.add("Encargado de Producción y Logística");arr_boss.add("Encargado de Marketing");arr_boss.add("Encargado de Sistemas y Tecnología");arr_boss.add("Encargado de Archivos centrales");arr_boss.add("Encargado de Secretaría General");arr_boss.add("Encargado de Compras");

                btnImmediateBoss.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bossDialog.showSpinerDialog();
                    }
                });

                bossDialog = new SpinnerDialog(getActivity(),arr_boss, "Selecciona la dependencia jerárquica");
                bossDialog.bindOnSpinerListener(new OnSpinerItemClick() {
                    @Override
                    public void onClick(String item2, int position2) {
                        btnImmediateBoss.setText(item2);

                    }
                });

                arr_responsable_area.add("Junta Directiva");arr_responsable_area.add("Presidencia");
                arr_responsable_area.add("Dirección Financiera y Contabilidad");arr_responsable_area.add("Dirección Administrativa");arr_responsable_area.add("Dirección Comercial y Ventas");arr_responsable_area.add("Dirección de Recursos Humanos");arr_responsable_area.add("Dirección de Producción y Logística");arr_responsable_area.add("Dirección de Marketing");arr_responsable_area.add("Dirección de Sistemas y Tecnología");arr_responsable_area.add("Dirección de Archivos centrales");arr_responsable_area.add("Dirección de Secretaría General");arr_responsable_area.add("Dirección de Compras");
                arr_responsable_area.add("Gerencia Financiera y Contabilidad");arr_responsable_area.add("Gerencia Administrativa");arr_responsable_area.add("Gerencia Comercial y Ventas");arr_responsable_area.add("Gerencia de Recursos Humanos");arr_responsable_area.add("Gerencia de Producción y Logística");arr_responsable_area.add("Gerencia de Marketing");arr_responsable_area.add("Gerencia de Sistemas y Tecnología");arr_responsable_area.add("Gerencia de Archivos centrales");arr_responsable_area.add("Gerencia de Secretaría General");arr_responsable_area.add("Gerencia de Compras");
                arr_responsable_area.add("Jefatura Financiera y Contabilidad");arr_responsable_area.add("Jefatura Administrativa");arr_responsable_area.add("Jefatura Comercial y Ventas");arr_responsable_area.add("Jefatura de Recursos Humanos");arr_responsable_area.add("Jefatura de Producción y Logística");arr_responsable_area.add("Jefatura de Marketing");arr_responsable_area.add("Jefatura de Sistemas y Tecnología");arr_responsable_area.add("Jefatura de Archivos centrales");arr_responsable_area.add("Jefatura de Secretaría General");arr_responsable_area.add("Jefatura de Compras");


                btnResponsableArea.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        responsable_areaDialog.showSpinerDialog();
                    }
                });

                responsable_areaDialog = new SpinnerDialog(getActivity(),arr_responsable_area, "Selecciona el Área responsable");
                responsable_areaDialog.bindOnSpinerListener(new OnSpinerItemClick() {
                    @Override
                    public void onClick(String item2, int position2) {
                        btnResponsableArea.setText(item2);

                    }
                });

                arr_mission.add("Realizar tareas y actividades operativas");arr_mission.add("Realizar tareas y actividades de productivas");arr_mission.add("Realizar tareas y actividades comerciales y de ventas");arr_mission.add("Realizar tareas y actividades logísticas");arr_mission.add("Realizar tareas y actividades de desarrollo software y tecnlogía");arr_mission.add("Realizar tareas y actividades de asistencia administrativa");arr_mission.add("Realizar tareas y actividades de secretaría");arr_mission.add("Realizar tareas y actividades de elaboración y diseño creativos");arr_mission.add("Realizar tareas y actividades de análisis");arr_mission.add("Realizar tareas y actividades especializadas");arr_mission.add("Realizar tareas y actividades de la jefatura asignada");
                arr_mission.add("Realizar tareas y actividades de gerencia");arr_mission.add("Realizar tareas y actividades de dirección");arr_mission.add("Realizar tareas y actividades de presidencia");

                btnJobMission.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        missionDialog.showSpinerDialog();
                    }
                });

                missionDialog = new SpinnerDialog(getActivity(),arr_mission, "Selecciona la missión");
                missionDialog.bindOnSpinerListener(new OnSpinerItemClick() {
                    @Override
                    public void onClick(String item2, int position2) {
                        btnJobMission.setText(item2);
                    }
                });

                btnRegister.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (TextUtils.isEmpty(edtName.getText().toString())) {
                            Snackbar.make(rootLayout,"Debes completar todos los datos",Snackbar.LENGTH_SHORT).show();
                        }
                        else  if (TextUtils.isEmpty(edtSurname.getText().toString())) {
                            Snackbar.make(rootLayout,"Debes completar todos los datos",Snackbar.LENGTH_SHORT).show();
                        }
                        else  if (TextUtils.isEmpty(edtJobName.getText().toString())) {
                            Snackbar.make(rootLayout,"Debes completar todos los datos",Snackbar.LENGTH_SHORT).show();
                        }
                        else  if (TextUtils.isEmpty(edtPeopleNumber.getText().toString())) {
                            Snackbar.make(rootLayout,"Debes completar todos los datos",Snackbar.LENGTH_SHORT).show();
                        }
                        else  if (TextUtils.isEmpty(edtJobMission.getText().toString())) {
                            Snackbar.make(rootLayout,"Debes completar todos los datos",Snackbar.LENGTH_SHORT).show();
                        }
                        else  if (TextUtils.isEmpty(btnArea.getText().toString())) {
                            Snackbar.make(rootLayout,"Debes completar todos los datos",Snackbar.LENGTH_SHORT).show();
                        }
                        else  if (TextUtils.isEmpty(btnDenomination.getText().toString())) {
                            Snackbar.make(rootLayout,"Debes completar todos los datos",Snackbar.LENGTH_SHORT).show();
                        }
                        else  if (TextUtils.isEmpty(btnImmediateBoss.getText().toString())) {
                            Snackbar.make(rootLayout,"Debes completar todos los datos",Snackbar.LENGTH_SHORT).show();
                        }
                        else  if (TextUtils.isEmpty(btnResponsableArea.getText().toString())) {
                            Snackbar.make(rootLayout,"Debes completar todos los datos",Snackbar.LENGTH_SHORT).show();
                        }
                        else  if (TextUtils.isEmpty(btnJobMission.getText().toString())) {
                            Snackbar.make(rootLayout,"Debes completar todos los datos",Snackbar.LENGTH_SHORT).show();
                        }
                        else {

                            Long tsLong = System.currentTimeMillis()/1000;
                            String timestamp = tsLong.toString();
                            companyRef.child(post_key).child("Job Profile").child(timestamp).child("job_profile_name").setValue(edtName.getText().toString());
                            companyRef.child(post_key).child("Job Profile").child(timestamp).child("job_profile_surname").setValue(edtSurname.getText().toString());
                            companyRef.child(post_key).child("Job Profile").child(timestamp).child("job_profile_area").setValue(btnArea.getText().toString());
                            companyRef.child(post_key).child("Job Profile").child(timestamp).child("job_profile_denomination").setValue(btnDenomination.getText().toString());
                            companyRef.child(post_key).child("Job Profile").child(timestamp).child("job_profile_job_name").setValue(edtJobName.getText().toString());
                            companyRef.child(post_key).child("Job Profile").child(timestamp).child("job_profile_immediate_boss").setValue(btnImmediateBoss.getText().toString());
                            companyRef.child(post_key).child("Job Profile").child(timestamp).child("job_profile_responsable_area").setValue(btnResponsableArea.getText().toString());
                            companyRef.child(post_key).child("Job Profile").child(timestamp).child("job_profile_people_number").setValue(edtPeopleNumber.getText().toString());
                            companyRef.child(post_key).child("Job Profile").child(timestamp).child("job_profile_job_mission").setValue(btnJobMission.getText().toString());
                            companyRef.child(post_key).child("Job Profile").child(timestamp).child("job_profile_job_mission_description").setValue(edtJobMission.getText().toString());
                            companyRef.child(post_key).child("Job Profile").child(timestamp).child("timestamp").setValue(ServerValue.TIMESTAMP);

                            Toasty.success(getActivity(), "Registrado", Toast.LENGTH_LONG).show();
                            dialog.dismiss();
                        }
                    }
                });

                dialog.setView(finance_method);
                dialog.show();
            }
        });


        return view;
    }

    private void showJobProfile() {
        Query query = companyRef.child(post_key).child("Job Profile").orderByChild("timestamp");
        FirebaseRecyclerAdapter<JobProfileModel, JobProfileViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<JobProfileModel, JobProfileViewHolder>
                (JobProfileModel.class,R.layout.job_profile_item,JobProfileViewHolder.class,query) {
            @Override
            protected void populateViewHolder(JobProfileViewHolder viewHolder, JobProfileModel model, int position) {
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
                        Intent intent = new Intent(getActivity(), RoasterAndPerfomanceActivity.class);
                        intent.putExtra("post_key",post_key);
                        intent.putExtra("profile_id",postKey);
                        startActivity(intent);
                    }
                });

            }
        };
        recyclerView.setAdapter(firebaseRecyclerAdapter);
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
}
