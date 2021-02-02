package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.PeopleManagement.WorkersPayroll;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;

import es.dmoral.toasty.Toasty;

public class ProfitFourthRentFragment extends Fragment {

    DatabaseReference ratesRef,companyRef;
    double tax_unit_value,four_category_max,payment;
    TextView txtWorkerName,txtRuc,txtWorkerAddress,txtCompanySocialReason,txtCompanyRuc,txtCompanyAddress,txtDate,txtConcept,txtObservation,txtPayment,txtRetention,txtTotalPayment;
    String post_key,profile_id,job_profile_name,job_profile_surname,company_social_reason,company_ruc,company_address;
    DecimalFormat decimalFormat;
    EditText edtSalary;
    int day,month,year;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profit_fourth_rent, container, false);

        post_key = getActivity().getIntent().getExtras().getString("post_key");
        profile_id = getActivity().getIntent().getExtras().getString("profile_id");
        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");
        ratesRef = FirebaseDatabase.getInstance().getReference().child("Rates");

        decimalFormat = new DecimalFormat("0.00");

        edtSalary = view.findViewById(R.id.edtSalary);

        txtWorkerName = view.findViewById(R.id.txtWorkerName);
        txtRuc = view.findViewById(R.id.txtRuc);
        txtWorkerAddress = view.findViewById(R.id.txtWorkerAddress);
        txtCompanySocialReason = view.findViewById(R.id.txtCompanySocialReason);
        txtCompanyRuc = view.findViewById(R.id.txtCompanyRuc);
        txtCompanyAddress = view.findViewById(R.id.txtCompanyAddress);
        txtDate = view.findViewById(R.id.txtDate);
        txtConcept = view.findViewById(R.id.txtConcept);
        txtObservation = view.findViewById(R.id.txtObservation);
        txtPayment = view.findViewById(R.id.txtPayment);
        txtRetention = view.findViewById(R.id.txtRetention);
        txtTotalPayment = view.findViewById(R.id.txtTotalPayment);

        Date date= new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        day = cal.get(Calendar.DAY_OF_MONTH);
        month = cal.get(Calendar.MONTH)+1;
        year = cal.get(Calendar.YEAR);

        txtDate.setText("Fecha de Emisión: "+day+"/"+month+"/"+year);

        ratesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                tax_unit_value = dataSnapshot.child("tax_unit_value").getValue(Double.class);
                four_category_max = dataSnapshot.child("four_category_max").getValue(Double.class);

                companyRef.child(post_key).child("Job Profile").child(profile_id).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        job_profile_name = dataSnapshot.child("job_profile_name").getValue().toString();
                        job_profile_surname = dataSnapshot.child("job_profile_surname").getValue().toString();

                        txtWorkerName.setText(job_profile_surname.toUpperCase()+" "+job_profile_name.toUpperCase());

                        if (dataSnapshot.child("Personal File").child("Birth Data").hasChild("ruc_document_number")) {
                            String ruc_document_number = dataSnapshot.child("Personal File").child("Birth Data").child("ruc_document_number").getValue().toString();
                            txtRuc.setText("RUC: "+ruc_document_number);
                        }

                        if (dataSnapshot.child("Personal File").child("Address Data").hasChild("address")) {
                            String address = dataSnapshot.child("Personal File").child("Address Data").child("address").getValue().toString();
                            txtWorkerAddress.setText(address.toUpperCase());
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

        companyRef.child(post_key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                company_social_reason = dataSnapshot.child("company_social_reason").getValue().toString();
                company_ruc = dataSnapshot.child("company_ruc").getValue().toString();
                company_address = dataSnapshot.child("company_address").getValue().toString();
                txtCompanySocialReason.setText("Recibí de: "+company_social_reason);
                txtCompanyRuc.setText("Identificado con RUC número: "+company_ruc);
                txtCompanyAddress.setText("Domiciliado en: "+company_address);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        txtConcept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog dialog = new AlertDialog.Builder(getActivity()).create();

                LayoutInflater inflater = LayoutInflater.from(getActivity());
                View finance_method = inflater.inflate(R.layout.add_concept_receipt_dialog,null);

                final EditText edtConcept;
                Button btnRegister;
                final RelativeLayout rootLayout;

                edtConcept = finance_method.findViewById(R.id.edtConcept);
                btnRegister = finance_method.findViewById(R.id.btnRegister);
                rootLayout = finance_method.findViewById(R.id.rootLayout);

                btnRegister.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (TextUtils.isEmpty(edtConcept.getText().toString())) {
                            Snackbar.make(rootLayout, "Debes completar toda la información", Snackbar.LENGTH_SHORT).show();
                        } else {
                            txtConcept.setText("Concepto: "+edtConcept.getText().toString());
                            Toasty.success(getActivity(), "Registrado", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                    }
                });



                dialog.setView(finance_method);
                dialog.show();
            }
        });

        txtObservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog dialog = new AlertDialog.Builder(getActivity()).create();

                LayoutInflater inflater = LayoutInflater.from(getActivity());
                View finance_method = inflater.inflate(R.layout.add_observation_receipt_dialog,null);

                final EditText edtConcept;
                Button btnRegister;
                final RelativeLayout rootLayout;

                edtConcept = finance_method.findViewById(R.id.edtConcept);
                btnRegister = finance_method.findViewById(R.id.btnRegister);
                rootLayout = finance_method.findViewById(R.id.rootLayout);

                btnRegister.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (TextUtils.isEmpty(edtConcept.getText().toString())) {
                            Snackbar.make(rootLayout, "Debes completar toda la información", Snackbar.LENGTH_SHORT).show();
                        } else {
                            txtObservation.setText("Observación: "+edtConcept.getText().toString());
                            Toasty.success(getActivity(), "Registrado", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                    }
                });



                dialog.setView(finance_method);
                dialog.show();
            }
        });

        txtRuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog dialog = new AlertDialog.Builder(getActivity()).create();

                LayoutInflater inflater = LayoutInflater.from(getActivity());
                View finance_method = inflater.inflate(R.layout.set_ruc_receipt_dialog,null);

                TextView txtMessage;
                final EditText edtConcept;
                Button btnRegister;
                final RelativeLayout rootLayout;

                edtConcept = finance_method.findViewById(R.id.edtConcept);
                btnRegister = finance_method.findViewById(R.id.btnRegister);
                rootLayout = finance_method.findViewById(R.id.rootLayout);
                txtMessage = finance_method.findViewById(R.id.txtMessage);

                txtMessage.setText("Ingresa el RUC de "+job_profile_name+" "+job_profile_surname);

                btnRegister.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (TextUtils.isEmpty(edtConcept.getText().toString())) {
                            Snackbar.make(rootLayout, "Debes completar toda la información", Snackbar.LENGTH_SHORT).show();
                        } else {

                            txtRuc.setText("RUC: "+edtConcept.getText().toString());
                            companyRef.child(post_key).child("Job Profile").child(profile_id).child("Personal File").child("Birth Data").child("ruc_document_number").setValue(edtConcept.getText().toString());
                            Toasty.success(getActivity(), "Registrado", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();

                        }
                    }
                });



                dialog.setView(finance_method);
                dialog.show();
            }
        });

        txtPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog dialog = new AlertDialog.Builder(getActivity()).create();

                LayoutInflater inflater = LayoutInflater.from(getActivity());
                View finance_method = inflater.inflate(R.layout.set_payment_receipt_dialog,null);

                final EditText edtConcept;
                Button btnRegister;
                final RelativeLayout rootLayout;

                edtConcept = finance_method.findViewById(R.id.edtConcept);
                btnRegister = finance_method.findViewById(R.id.btnRegister);
                rootLayout = finance_method.findViewById(R.id.rootLayout);

                btnRegister.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (TextUtils.isEmpty(edtConcept.getText().toString())) {
                            Snackbar.make(rootLayout, "Debes completar toda la información", Snackbar.LENGTH_SHORT).show();
                        } else {

                            txtPayment.setText("TOTAL POR HONORARIOS S/ : "+edtConcept.getText().toString());
                            payment = Double.parseDouble(edtConcept.getText().toString());
                            Toasty.success(getActivity(), "Registrado", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();

                        }
                    }
                });



                dialog.setView(finance_method);
                dialog.show();
            }
        });

        txtRetention.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog dialog = new AlertDialog.Builder(getActivity()).create();

                LayoutInflater inflater = LayoutInflater.from(getActivity());
                View finance_method = inflater.inflate(R.layout.set_retention_receipt_dialog,null);

                final RadioButton rdYes,rdNo;
                TextView txtMessage;
                Button btnRegister;
                final RelativeLayout rootLayout;

                btnRegister = finance_method.findViewById(R.id.btnRegister);
                rootLayout = finance_method.findViewById(R.id.rootLayout);
                rdYes = finance_method.findViewById(R.id.rdYes);
                rdNo = finance_method.findViewById(R.id.rdNo);
                txtMessage = finance_method.findViewById(R.id.txtMessage);

                txtMessage.setText("¿"+job_profile_name+" "+job_profile_surname+" tendrá unos ingresos proyectados mayores a S/ "+four_category_max+" en el año "+year+"?");

                btnRegister.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!rdYes.isChecked() && !rdNo.isChecked()) {
                            Snackbar.make(rootLayout, "Debes completar toda la información", Snackbar.LENGTH_SHORT).show();
                        } else {

                            if (rdNo.isChecked()) {
                                txtRetention.setText("RETENCIÓN (8%) IR: S/ 0.00");
                                txtTotalPayment.setText("TOTAL NETO RECIBIDO: S/ "+payment);
                            }
                            if (rdYes.isChecked()) {
                                double retention = payment*0.08;
                                String retention_st = decimalFormat.format(retention);
                                txtRetention.setText("RETENCIÓN (8%) IR: S/ "+retention_st);

                                double total_payment = payment-retention;
                                String total_payment_st = decimalFormat.format(total_payment);
                                txtTotalPayment.setText("TOTAL NETO RECIBIDO: S/ "+total_payment_st);

                            }

                            Toasty.success(getActivity(), "Registrado", Toast.LENGTH_SHORT).show();
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


}
