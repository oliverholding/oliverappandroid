package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.PeopleManagement.RoasterAndPerfomance;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
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


public class IncentivesFragment extends Fragment {

    Button btnRegister;
    RecyclerView recyclerView;
    String post_key;
    DatabaseReference companyRef;
    AlertDialog workers_dialog;

    ArrayList<String> arr_objective_type =new ArrayList<>();
    SpinnerDialog objectiveTypeDialog;
    ArrayList<String> arr_area =new ArrayList<>();
    SpinnerDialog areaDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_incentives, container, false);

        post_key = getActivity().getIntent().getExtras().getString("post_key");
        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");
        btnRegister = view.findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddIncentivesDialog();
            }
        });

        recyclerView = view.findViewById(R.id.recyclerView);

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setReverseLayout(false);
        linearLayoutManager.setStackFromEnd(false);
        recyclerView.setLayoutManager(linearLayoutManager);

        showIncentives();

        return view;
    }

    private void showIncentives() {
        Query query = companyRef.child(post_key).child("People Management Incentives").orderByChild("timestamp");
        FirebaseRecyclerAdapter<IncentivesModel, IncentivesViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<IncentivesModel, IncentivesViewHolder>
                (IncentivesModel.class,R.layout.incentive_item,IncentivesViewHolder.class,query) {
            @Override
            protected void populateViewHolder(final IncentivesViewHolder viewHolder, IncentivesModel model, int position) {
                final String postKey = getRef(position).getKey();
                viewHolder.setIncentive1(model.getIncentive1());
                viewHolder.setIncentive2(model.getIncentive2());
                viewHolder.setIncentive3(model.getIncentive3());
                viewHolder.setIncentive4(model.getIncentive4());
                viewHolder.setIncentive5(model.getIncentive5());
                viewHolder.setIncentive6(model.getIncentive6());
                viewHolder.setIncentive7(model.getIncentive7());
                viewHolder.setIncentive8(model.getIncentive8());
                viewHolder.setIncentive_destination(model.getIncentive_destination());
                viewHolder.setIncentive_type(model.getIncentive_type());

                viewHolder.txtDestinationName.setText(viewHolder.my_incentive_destination);
                viewHolder.txtIncentiveType.setText("Tipo de Incentivo: "+viewHolder.my_incentive_type);
                viewHolder.txtIncentives.setText("Incentivos: "+viewHolder.my_incentive1+" "+viewHolder.my_incentive2+" "+viewHolder.my_incentive3+" "+viewHolder.my_incentive4+" "+viewHolder.my_incentive5+" "+viewHolder.my_incentive6+" "+viewHolder.my_incentive7+" "+viewHolder.my_incentive8+" ");

            }
        };
        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    private void showAddIncentivesDialog() {
        final AlertDialog dialog = new AlertDialog.Builder(getActivity()).create();

        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View finance_method = inflater.inflate(R.layout.add_incentive_dialog,null);

        final Button btnObjectiveType,btnWorker,btnArea,btnRegisterIncentive;
        final EditText edtTeamName;
        final LinearLayout workerObjectivesLayout,teamObjectivesLayout,areaObjectivesLayout,rootLayout,monetaryLayout,noMonetaryLayout;
        final RadioButton rdMonetary,rdNoMonetary,rdMixed;
        final CheckBox cb1,cb2,cb3,cb4,cb5,cb6,cb7,cb8;

        btnObjectiveType = finance_method.findViewById(R.id.btnObjectiveType);
        btnWorker = finance_method.findViewById(R.id.btnWorker);
        btnArea = finance_method.findViewById(R.id.btnArea);
        edtTeamName = finance_method.findViewById(R.id.edtTeamName);
        workerObjectivesLayout = finance_method.findViewById(R.id.workerObjectivesLayout);
        teamObjectivesLayout = finance_method.findViewById(R.id.teamObjectivesLayout);
        areaObjectivesLayout = finance_method.findViewById(R.id.areaObjectivesLayout);
        rootLayout = finance_method.findViewById(R.id.rootLayout);
        monetaryLayout = finance_method.findViewById(R.id.monetaryLayout);
        noMonetaryLayout = finance_method.findViewById(R.id.noMonetaryLayout);
        rdMonetary = finance_method.findViewById(R.id.rdMonetary);
        rdNoMonetary = finance_method.findViewById(R.id.rdNoMonetary);
        rdMixed = finance_method.findViewById(R.id.rdMixed);
        cb1 = finance_method.findViewById(R.id.cb1);
        cb2 = finance_method.findViewById(R.id.cb2);
        cb3 = finance_method.findViewById(R.id.cb3);
        cb4 = finance_method.findViewById(R.id.cb4);
        cb5 = finance_method.findViewById(R.id.cb5);
        cb6 = finance_method.findViewById(R.id.cb6);
        cb7 = finance_method.findViewById(R.id.cb7);
        cb8 = finance_method.findViewById(R.id.cb8);
        btnRegisterIncentive = finance_method.findViewById(R.id.btnRegisterIncentive);

        arr_objective_type.add("Incentivo Individual");arr_objective_type.add("Incentivo por Equipo");arr_objective_type.add("Incentivo por Departamento");arr_objective_type.add("Incentivo de la Empresa");

        btnObjectiveType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                objectiveTypeDialog.showSpinerDialog();
            }
        });

        objectiveTypeDialog = new SpinnerDialog(getActivity(),arr_objective_type, "Selecciona el tipo de Incentivo");
        objectiveTypeDialog.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String item2, int position2) {
                if (item2.equals("Incentivo Individual")) {
                    workerObjectivesLayout.setVisibility(View.VISIBLE);
                    teamObjectivesLayout.setVisibility(View.GONE);
                    areaObjectivesLayout.setVisibility(View.GONE);
                }
                if (item2.equals("Incentivo por Equipo")) {
                    workerObjectivesLayout.setVisibility(View.GONE);
                    teamObjectivesLayout.setVisibility(View.VISIBLE);
                    areaObjectivesLayout.setVisibility(View.GONE);
                }
                if (item2.equals("Incentivo por Departamento")) {
                    workerObjectivesLayout.setVisibility(View.GONE);
                    teamObjectivesLayout.setVisibility(View.GONE);
                    areaObjectivesLayout.setVisibility(View.VISIBLE);
                }
                if (item2.equals("Incentivo de la Empresa")) {
                    workerObjectivesLayout.setVisibility(View.GONE);
                    teamObjectivesLayout.setVisibility(View.GONE);
                    areaObjectivesLayout.setVisibility(View.GONE);
                }
                btnObjectiveType.setText(item2);

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

        rdMonetary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                monetaryLayout.setVisibility(View.VISIBLE);
                noMonetaryLayout.setVisibility(View.GONE);
            }
        });

        rdNoMonetary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                monetaryLayout.setVisibility(View.GONE);
                noMonetaryLayout.setVisibility(View.VISIBLE);
            }
        });
        rdMixed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                monetaryLayout.setVisibility(View.VISIBLE);
                noMonetaryLayout.setVisibility(View.VISIBLE);
            }
        });

        btnRegisterIncentive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Long tsLong = System.currentTimeMillis()/1000;
                String timestamp = tsLong.toString();
                companyRef.child(post_key).child("People Management Incentives").child(timestamp).child("incentive_destination_type").setValue(btnObjectiveType.getText().toString());
                if (btnObjectiveType.getText().toString().equals("Incentivo Individual")) {
                    companyRef.child(post_key).child("People Management Incentives").child(timestamp).child("incentive_destination").setValue(btnWorker.getText().toString());

                }
                if (btnObjectiveType.getText().toString().equals("Incentivo por Equipo")) {
                    companyRef.child(post_key).child("People Management Incentives").child(timestamp).child("incentive_destination").setValue(edtTeamName.getText().toString());
                }
                if (btnObjectiveType.getText().toString().equals("Incentivo por Departamento")) {
                    companyRef.child(post_key).child("People Management Incentives").child(timestamp).child("incentive_destination").setValue(btnArea.getText().toString());
                }
                if (btnObjectiveType.getText().toString().equals("Incentivo de la Empresa")) {
                    companyRef.child(post_key).child("People Management Incentives").child(timestamp).child("incentive_destination").setValue("Empresa");
                }

                if (rdMonetary.isChecked()) {
                    companyRef.child(post_key).child("People Management Incentives").child(timestamp).child("incentive_type").setValue(rdMonetary.getText().toString());
                }
                if (rdNoMonetary.isChecked()) {
                    companyRef.child(post_key).child("People Management Incentives").child(timestamp).child("incentive_type").setValue(rdNoMonetary.getText().toString());
                }
                if (rdMixed.isChecked()) {
                    companyRef.child(post_key).child("People Management Incentives").child(timestamp).child("incentive_type").setValue(rdMixed.getText().toString());
                }

                if (cb1.isChecked()) {
                    companyRef.child(post_key).child("People Management Incentives").child(timestamp).child("incentive1").setValue(cb1.getText().toString());
                } else {
                    companyRef.child(post_key).child("People Management Incentives").child(timestamp).child("incentive1").setValue("");
                }

                if (cb2.isChecked()) {
                    companyRef.child(post_key).child("People Management Incentives").child(timestamp).child("incentive2").setValue(cb2.getText().toString());
                } else {
                    companyRef.child(post_key).child("People Management Incentives").child(timestamp).child("incentive2").setValue("");
                }

                if (cb3.isChecked()) {
                    companyRef.child(post_key).child("People Management Incentives").child(timestamp).child("incentive3").setValue(cb3.getText().toString());
                } else {
                    companyRef.child(post_key).child("People Management Incentives").child(timestamp).child("incentive3").setValue("");
                }

                if (cb4.isChecked()) {
                    companyRef.child(post_key).child("People Management Incentives").child(timestamp).child("incentive4").setValue(cb4.getText().toString());
                } else {
                    companyRef.child(post_key).child("People Management Incentives").child(timestamp).child("incentive4").setValue("");
                }

                if (cb5.isChecked()) {
                    companyRef.child(post_key).child("People Management Incentives").child(timestamp).child("incentive5").setValue(cb5.getText().toString());
                } else {
                    companyRef.child(post_key).child("People Management Incentives").child(timestamp).child("incentive5").setValue("");
                }

                if (cb6.isChecked()) {
                    companyRef.child(post_key).child("People Management Incentives").child(timestamp).child("incentive6").setValue(cb6.getText().toString());
                } else {
                    companyRef.child(post_key).child("People Management Incentives").child(timestamp).child("incentive6").setValue("");
                }

                if (cb7.isChecked()) {
                    companyRef.child(post_key).child("People Management Incentives").child(timestamp).child("incentive7").setValue(cb7.getText().toString());
                } else {
                    companyRef.child(post_key).child("People Management Incentives").child(timestamp).child("incentive7").setValue("");
                }

                if (cb8.isChecked()) {
                    companyRef.child(post_key).child("People Management Incentives").child(timestamp).child("incentive8").setValue(cb8.getText().toString());
                } else {
                    companyRef.child(post_key).child("People Management Incentives").child(timestamp).child("incentive8").setValue("");
                }

                companyRef.child(post_key).child("People Management Incentives").child(timestamp).child("timestamp").setValue(ServerValue.TIMESTAMP);

                Toasty.success(getActivity(), "Incentivo Registrado", Toast.LENGTH_LONG).show();
                dialog.dismiss();

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

    public static class IncentivesViewHolder extends RecyclerView.ViewHolder {
        View mView;
        String my_incentive1,my_incentive2,my_incentive3,my_incentive4,my_incentive5,my_incentive6,my_incentive7,my_incentive8,my_incentive_destination,my_incentive_destination_type,my_incentive_type;
        TextView txtDestinationName,txtIncentiveType,txtIncentives;

        public IncentivesViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
            txtDestinationName = mView.findViewById(R.id.txtDestinationName);
            txtIncentiveType = mView.findViewById(R.id.txtIncentiveType);
            txtIncentives = mView.findViewById(R.id.txtIncentives);
        }
        public void setIncentive1(String incentive1) {
            my_incentive1 = incentive1;
        }

        public void setIncentive2(String incentive2) {
            my_incentive2 = incentive2;
        }

        public void setIncentive3(String incentive3) {
            my_incentive3 = incentive3;
        }

        public void setIncentive4(String incentive4) {
            my_incentive4 = incentive4;
        }

        public void setIncentive5(String incentive5) {
            my_incentive5 = incentive5;
        }

        public void setIncentive6(String incentive6) {
            my_incentive6 = incentive6;
        }

        public void setIncentive7(String incentive7) {
            my_incentive7 = incentive7;
        }
        public void setIncentive8(String incentive8) {
            my_incentive8 = incentive8;
        }

        public void setIncentive_destination(String incentive_destination) {
            my_incentive_destination = incentive_destination;
        }

        public void setIncentive_destination_type(String incentive_destination_type) {
            my_incentive_destination_type = incentive_destination_type;
        }

        public void setIncentive_type(String incentive_type) {
            my_incentive_type = incentive_type;
        }
    }
}
