package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.PeopleManagement.JobProfiles;

import android.app.AlertDialog;
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
import com.oalonedeveloper.oliver.oliverappandroidapp.R;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;
import in.galaxyofandroid.spinerdialog.OnSpinerItemClick;
import in.galaxyofandroid.spinerdialog.SpinnerDialog;


public class CoordinationsFragment extends Fragment {

    DatabaseReference companyRef;
    String post_key,profile_id;
    Button btnRegister;
    RecyclerView recyclerView,recyclerView2;

    ArrayList<String> arr_area =new ArrayList<>();
    SpinnerDialog areaDialog;
    ArrayList<String> arr_denomination =new ArrayList<>();
    SpinnerDialog denominationDialog;
    SpinnerDialog denominationDialog2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_coordinations, container, false);

        post_key = getActivity().getIntent().getExtras().getString("post_key");
        profile_id = getActivity().getIntent().getExtras().getString("profile_id");
        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");

        btnRegister = view.findViewById(R.id.btnRegister);

        recyclerView = view.findViewById(R.id.recyclerView);

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setReverseLayout(false);
        linearLayoutManager.setStackFromEnd(false);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView2 = view.findViewById(R.id.recyclerView2);

        recyclerView2.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getActivity());
        linearLayoutManager2.setReverseLayout(false);
        linearLayoutManager2.setStackFromEnd(false);
        recyclerView2.setLayoutManager(linearLayoutManager2);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog dialog = new AlertDialog.Builder(getActivity()).create();

                LayoutInflater inflater = LayoutInflater.from(getActivity());
                View finance_method = inflater.inflate(R.layout.add_coordinations_job_dialog,null);

                final RadioButton rdInternal,rdExternal;
                final LinearLayout internalLayout,externalLayout,rootLayout;
                final EditText edtName,edtName2,edtCompanyName,edtEmail,edtPhone;
                final Button btnDenomination,btnArea,btnRegister,btnDenomination2;

                rdInternal = finance_method.findViewById(R.id.rdInternal);
                rdExternal = finance_method.findViewById(R.id.rdExternal);
                internalLayout = finance_method.findViewById(R.id.internalLayout);
                externalLayout = finance_method.findViewById(R.id.externalLayout);
                edtName = finance_method.findViewById(R.id.edtName);
                btnDenomination = finance_method.findViewById(R.id.btnDenomination);
                btnArea = finance_method.findViewById(R.id.btnArea);
                btnRegister = finance_method.findViewById(R.id.btnRegister);
                rootLayout = finance_method.findViewById(R.id.rootLayout);

                edtName2 = finance_method.findViewById(R.id.edtName2);
                edtCompanyName = finance_method.findViewById(R.id.edtCompanyName);
                edtEmail = finance_method.findViewById(R.id.edtEmail);
                edtPhone = finance_method.findViewById(R.id.edtPhone);
                btnDenomination2 = finance_method.findViewById(R.id.btnDenomination2);

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

                btnDenomination2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        denominationDialog2.showSpinerDialog();
                    }
                });

                denominationDialog2 = new SpinnerDialog(getActivity(),arr_denomination, "Selecciona la denominación");
                denominationDialog2.bindOnSpinerListener(new OnSpinerItemClick() {
                    @Override
                    public void onClick(String item2, int position2) {
                        btnDenomination2.setText(item2);

                    }
                });

                rdInternal.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        internalLayout.setVisibility(View.VISIBLE);
                        externalLayout.setVisibility(View.GONE);
                    }
                });

                rdExternal.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        externalLayout.setVisibility(View.VISIBLE);
                        internalLayout.setVisibility(View.GONE);
                    }
                });

                btnRegister.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!rdInternal.isChecked() && !rdExternal.isChecked()) {
                            Snackbar.make(rootLayout,"Debes seleccionar el tipo de coordinación",Snackbar.LENGTH_SHORT).show();
                        }
                        else if (rdInternal.isChecked()) {
                            if (TextUtils.isEmpty(edtName.getText().toString())) {
                                Snackbar.make(rootLayout, "Debes ingresar el nombre del jefe inmediato", Snackbar.LENGTH_SHORT).show();
                            }
                            else if (TextUtils.isEmpty(btnDenomination.getText().toString())) {
                                Snackbar.make(rootLayout, "Debes ingresar la denominación", Snackbar.LENGTH_SHORT).show();
                            } else if (TextUtils.isEmpty(btnArea.getText().toString())) {
                                Snackbar.make(rootLayout, "Debes ingresar la área", Snackbar.LENGTH_SHORT).show();
                            } else {
                                Long tsLong = System.currentTimeMillis()/1000;
                                String timestamp = tsLong.toString();
                                companyRef.child(post_key).child("Job Profile").child(profile_id).child("Internal Coordination").child(timestamp).child("coordination_immediate_boss_name").setValue(edtName.getText().toString());
                                companyRef.child(post_key).child("Job Profile").child(profile_id).child("Internal Coordination").child(timestamp).child("coordination_denomination").setValue(btnDenomination.getText().toString());
                                companyRef.child(post_key).child("Job Profile").child(profile_id).child("Internal Coordination").child(timestamp).child("coordination_area").setValue(btnArea.getText().toString());
                                companyRef.child(post_key).child("Job Profile").child(profile_id).child("Internal Coordination").child(timestamp).child("timestamps").setValue(ServerValue.TIMESTAMP);

                                Toasty.success(getActivity(), "Registrado", Toast.LENGTH_LONG).show();
                                dialog.dismiss();
                            }
                        } else if (rdExternal.isChecked()) {
                            if (TextUtils.isEmpty(edtName2.getText().toString())) {
                                Snackbar.make(rootLayout, "Debes ingresar el nombre del jefe inmediato de la empresa", Snackbar.LENGTH_SHORT).show();
                            }
                            else if (TextUtils.isEmpty(btnDenomination2.getText().toString())) {
                                Snackbar.make(rootLayout, "Debes ingresar la denominación de la empresa", Snackbar.LENGTH_SHORT).show();
                            }
                            else if (TextUtils.isEmpty(edtCompanyName.getText().toString())) {
                                Snackbar.make(rootLayout, "Debes ingresar el nombre de la empresa", Snackbar.LENGTH_SHORT).show();
                            }
                            else if (TextUtils.isEmpty(edtEmail.getText().toString())) {
                                Snackbar.make(rootLayout, "Debes ingresar el correo", Snackbar.LENGTH_SHORT).show();
                            } else if (TextUtils.isEmpty(edtPhone.getText().toString())) {
                                Snackbar.make(rootLayout, "Debes ingresar el teléfono", Snackbar.LENGTH_SHORT).show();
                            } else {
                                Long tsLong = System.currentTimeMillis()/1000;
                                String timestamp = tsLong.toString();
                                companyRef.child(post_key).child("Job Profile").child(profile_id).child("External Coordination").child(timestamp).child("coordination_immediate_boss_name").setValue(edtName2.getText().toString());
                                companyRef.child(post_key).child("Job Profile").child(profile_id).child("External Coordination").child(timestamp).child("coordination_denomination").setValue(btnDenomination2.getText().toString());
                                companyRef.child(post_key).child("Job Profile").child(profile_id).child("External Coordination").child(timestamp).child("coordination_company").setValue(edtCompanyName.getText().toString());
                                companyRef.child(post_key).child("Job Profile").child(profile_id).child("External Coordination").child(timestamp).child("coordination_email").setValue(edtEmail.getText().toString());
                                companyRef.child(post_key).child("Job Profile").child(profile_id).child("External Coordination").child(timestamp).child("coordination_phone").setValue(edtPhone.getText().toString());
                                companyRef.child(post_key).child("Job Profile").child(profile_id).child("External Coordination").child(timestamp).child("timestamps").setValue(ServerValue.TIMESTAMP);
                                Toasty.success(getActivity(), "Registrado", Toast.LENGTH_LONG).show();
                                dialog.dismiss();
                            }
                        }
                    }
                });

                dialog.setView(finance_method);
                dialog.show();
            }
        });

        showInternalCoordination();
        showExternalCoordination();

        return view;
    }

    private void showExternalCoordination() {
        Query query = companyRef.child(post_key).child("Job Profile").child(profile_id).child("External Coordination").orderByChild("timestamp");
        FirebaseRecyclerAdapter<ExternalCoordinationModel,ExternalCoordinationViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<ExternalCoordinationModel, ExternalCoordinationViewHolder>
                (ExternalCoordinationModel.class,R.layout.external_coordination_item,ExternalCoordinationViewHolder.class,query) {
            @Override
            protected void populateViewHolder(ExternalCoordinationViewHolder viewHolder, ExternalCoordinationModel model, int position) {
                viewHolder.setCoordination_company(model.getCoordination_company());
                viewHolder.setCoordination_denomination(model.getCoordination_denomination());
                viewHolder.setCoordination_email(model.getCoordination_email());
                viewHolder.setCoordination_immediate_boss_name(model.getCoordination_immediate_boss_name());
                viewHolder.setCoordination_phone(model.getCoordination_phone());

                viewHolder.txtImmediateBoss.setText("Jefe Inmediato: "+viewHolder.my_coordination_immediate_boss_name);
                viewHolder.txtCharge.setText("Cargo: "+viewHolder.my_coordination_denomination);
                viewHolder.txtCompany.setText("Empresa: "+viewHolder.my_coordination_company);
                viewHolder.txtEmail.setText("Correo: "+viewHolder.my_coordination_email);
                viewHolder.txtPhone.setText("Teléfono: "+viewHolder.my_coordination_phone);

            }
        };
        recyclerView2.setAdapter(firebaseRecyclerAdapter);
    }


    private void showInternalCoordination() {
        Query query = companyRef.child(post_key).child("Job Profile").child(profile_id).child("Internal Coordination").orderByChild("timestamp");
        FirebaseRecyclerAdapter<InternalCoordinationModel,InternalCoordinationViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<InternalCoordinationModel, InternalCoordinationViewHolder>
                (InternalCoordinationModel.class,R.layout.internal_coordination_item,InternalCoordinationViewHolder.class,query) {
            @Override
            protected void populateViewHolder(InternalCoordinationViewHolder viewHolder, InternalCoordinationModel model, int position) {
                viewHolder.setCoordination_area(model.getCoordination_area());
                viewHolder.setCoordination_denomination(model.getCoordination_denomination());
                viewHolder.setCoordination_immediate_boss_name(model.getCoordination_immediate_boss_name());

                viewHolder.txtImmediateBoss.setText("Jefe Inmediato: "+viewHolder.my_coordination_immediate_boss_name);
                viewHolder.txtCharge.setText("Cargo: "+viewHolder.my_coordination_denomination);
                viewHolder.txtArea.setText("Área: "+viewHolder.my_coordination_area);

            }
        };
        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    public static class InternalCoordinationViewHolder extends RecyclerView.ViewHolder {

        View mView;
        String my_coordination_area,my_coordination_denomination,my_coordination_immediate_boss_name;
        TextView txtImmediateBoss,txtCharge,txtArea;

        public InternalCoordinationViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;

            txtImmediateBoss = mView.findViewById(R.id.txtImmediateBoss);
            txtCharge = mView.findViewById(R.id.txtCharge);
            txtArea = mView.findViewById(R.id.txtArea);

        }
        public void setCoordination_area(String coordination_area) {
            my_coordination_area = coordination_area;
        }

        public void setCoordination_denomination(String coordination_denomination) {
            my_coordination_denomination = coordination_denomination;
        }


        public void setCoordination_immediate_boss_name(String coordination_immediate_boss_name) {
            my_coordination_immediate_boss_name = coordination_immediate_boss_name;
        }
    }

    public static class ExternalCoordinationViewHolder extends RecyclerView.ViewHolder {

        View mView;
        String my_coordination_company,my_coordination_denomination,my_coordination_email,my_coordination_immediate_boss_name,my_coordination_phone;
        TextView txtImmediateBoss,txtCharge,txtCompany,txtEmail,txtPhone;

        public ExternalCoordinationViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;

            txtImmediateBoss = mView.findViewById(R.id.txtImmediateBoss);
            txtCharge = mView.findViewById(R.id.txtCharge);
            txtCompany = mView.findViewById(R.id.txtCompany);
            txtEmail = mView.findViewById(R.id.txtEmail);
            txtPhone = mView.findViewById(R.id.txtPhone);

        }
        public void setCoordination_company(String coordination_company) {
            my_coordination_company  = coordination_company;
        }

        public void setCoordination_denomination(String coordination_denomination) {
            my_coordination_denomination = coordination_denomination;
        }


        public void setCoordination_email(String coordination_email) {
            my_coordination_email = coordination_email;
        }

        public void setCoordination_immediate_boss_name(String coordination_immediate_boss_name) {
            my_coordination_immediate_boss_name = coordination_immediate_boss_name;
        }


        public void setCoordination_phone(String coordination_phone) {
            my_coordination_phone = coordination_phone;
        }
    }
}
