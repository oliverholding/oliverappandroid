package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.PeopleManagement.PersonalFiles;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

import es.dmoral.toasty.Toasty;

public class SwornDeclarationFragment extends Fragment {

    ImageView btnAdd1;
    DatabaseReference companyRef;
    String post_key,profile_id,pension_type,pension_institution,job_profile_name,job_profile_surname,document_number;
    TextView txtPensionType,txtPensionDetail,txtDeclaration;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sworn_declaration, container, false);

        post_key = getActivity().getIntent().getExtras().getString("post_key");
        profile_id = getActivity().getIntent().getExtras().getString("profile_id");
        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");

        txtPensionType = view.findViewById(R.id.txtPensionType);
        txtPensionDetail = view.findViewById(R.id.txtPensionDetail);
        txtDeclaration = view.findViewById(R.id.txtDeclaration);

        btnAdd1 = view.findViewById(R.id.btnAdd1);

        companyRef.child(post_key).child("Job Profile").child(profile_id).child("Personal File").child("Pension Data").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    if (dataSnapshot.hasChild("pension_type")) {
                        pension_type = dataSnapshot.child("pension_type").getValue().toString();
                        txtPensionType.setText("Situación del Régimen Pensionario: "+pension_type);
                    }
                    if (dataSnapshot.hasChild("pension_institution")) {
                        pension_institution = dataSnapshot.child("pension_institution").getValue().toString();
                        txtPensionDetail.setText("Entidad Administradora: "+pension_institution);
                    }
                }

                companyRef.child(post_key).child("Job Profile").child(profile_id).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        job_profile_name = dataSnapshot.child("job_profile_name").getValue().toString();
                        job_profile_surname = dataSnapshot.child("job_profile_surname").getValue().toString();

                        companyRef.child(post_key).child("Job Profile").child(profile_id).child("Personal File").child("Birth Data").addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                if (dataSnapshot.exists()) {
                                    document_number = dataSnapshot.child("document_number").getValue().toString();
                                    txtDeclaration.setText("Yo, "+job_profile_name+" "+job_profile_surname+" identificado con D.N.I "+document_number+" DECLARO BAJO JURAMENTO, que por la presente, elijo o continúo, en el sistema de pensiones, conforme a lo siguiente:");

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

        btnAdd1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog dialog = new AlertDialog.Builder(getActivity()).create();

                LayoutInflater inflater = LayoutInflater.from(getActivity());
                View finance_method = inflater.inflate(R.layout.add_sworn_declaration_dialog,null);

                final RadioButton rdPrivate,rdNational,rdOther,rdPrivateOption1,rdPrivateOption2,rdPrivateOption3,rdPrivateOptionName1,rdPrivateOptionName2,rdPrivateOptionName3,rdPrivateOptionName4,rdPrivateOptionName5,rdNationalOption1,
                        rdNationalOption2,rdNationalOption3,rdOtherOption1,rdOtherOption2;
                final LinearLayout privateLayout,nationalLayout,otherLayout,rootLayout;
                Button btnRegister;

                rdPrivate = finance_method.findViewById(R.id.rdPrivate);
                rdNational = finance_method.findViewById(R.id.rdNational);
                rdOther = finance_method.findViewById(R.id.rdOther);
                rdPrivateOption1 = finance_method.findViewById(R.id.rdPrivateOption1);
                rdPrivateOption2 = finance_method.findViewById(R.id.rdPrivateOption2);
                rdPrivateOption3 = finance_method.findViewById(R.id.rdPrivateOption3);
                rdPrivateOptionName1 = finance_method.findViewById(R.id.rdPrivateOptionName1);
                rdPrivateOptionName2 = finance_method.findViewById(R.id.rdPrivateOptionName2);
                rdPrivateOptionName3 = finance_method.findViewById(R.id.rdPrivateOptionName3);
                rdPrivateOptionName4 = finance_method.findViewById(R.id.rdPrivateOptionName4);
                rdPrivateOptionName5 = finance_method.findViewById(R.id.rdPrivateOptionName5);
                rdNationalOption1 = finance_method.findViewById(R.id.rdNationalOption1);
                rdNationalOption2 = finance_method.findViewById(R.id.rdNationalOption2);
                rdNationalOption3 = finance_method.findViewById(R.id.rdNationalOption3);
                rdOtherOption1 = finance_method.findViewById(R.id.rdOtherOption1);
                rdOtherOption2 = finance_method.findViewById(R.id.rdOtherOption2);
                privateLayout = finance_method.findViewById(R.id.privateLayout);
                nationalLayout = finance_method.findViewById(R.id.nationalLayout);
                otherLayout = finance_method.findViewById(R.id.otherLayout);
                btnRegister = finance_method.findViewById(R.id.btnRegister);
                rootLayout = finance_method.findViewById(R.id.rootLayout);

                rdPrivate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        privateLayout.setVisibility(View.VISIBLE);
                        nationalLayout.setVisibility(View.GONE);
                        otherLayout.setVisibility(View.GONE);
                    }
                });

                rdNational.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        privateLayout.setVisibility(View.GONE);
                        nationalLayout.setVisibility(View.VISIBLE);
                        otherLayout.setVisibility(View.GONE);
                    }
                });

                rdOther.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        privateLayout.setVisibility(View.GONE);
                        nationalLayout.setVisibility(View.GONE);
                        otherLayout.setVisibility(View.VISIBLE);
                    }
                });

                btnRegister.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!rdPrivate.isChecked() && !rdNational.isChecked() && !rdOther.isChecked()) {
                            Snackbar.make(rootLayout, "Debes seleccionar los datos restantes", Snackbar.LENGTH_SHORT).show();
                        } else {
                            if (rdPrivate.isChecked()) {
                                if (rdPrivateOption1.isChecked()) {
                                    companyRef.child(post_key).child("Job Profile").child(profile_id).child("Personal File").child("Pension Data").child("pension_type").setValue(rdPrivateOption1.getText().toString());
                                }
                                if (rdPrivateOption2.isChecked()) {
                                    companyRef.child(post_key).child("Job Profile").child(profile_id).child("Personal File").child("Pension Data").child("pension_type").setValue(rdPrivateOption2.getText().toString());
                                }
                                if (rdPrivateOption3.isChecked()) {
                                    companyRef.child(post_key).child("Job Profile").child(profile_id).child("Personal File").child("Pension Data").child("pension_type").setValue(rdPrivateOption3.getText().toString());
                                }

                                if (rdPrivateOptionName1.isChecked()) {
                                    companyRef.child(post_key).child("Job Profile").child(profile_id).child("Personal File").child("Pension Data").child("pension_institution").setValue(rdPrivateOptionName1.getText().toString());
                                }
                                if (rdPrivateOptionName2.isChecked()) {
                                    companyRef.child(post_key).child("Job Profile").child(profile_id).child("Personal File").child("Pension Data").child("pension_institution").setValue(rdPrivateOptionName2.getText().toString());
                                }
                                if (rdPrivateOptionName3.isChecked()) {
                                    companyRef.child(post_key).child("Job Profile").child(profile_id).child("Personal File").child("Pension Data").child("pension_institution").setValue(rdPrivateOptionName3.getText().toString());
                                }
                                if (rdPrivateOptionName4.isChecked()) {
                                    companyRef.child(post_key).child("Job Profile").child(profile_id).child("Personal File").child("Pension Data").child("pension_institution").setValue(rdPrivateOptionName4.getText().toString());
                                }
                                if (rdPrivateOptionName5.isChecked()) {
                                    companyRef.child(post_key).child("Job Profile").child(profile_id).child("Personal File").child("Pension Data").child("pension_institution").setValue(rdPrivateOptionName5.getText().toString());
                                }

                            }
                            if (rdNational.isChecked()) {
                                if (rdNationalOption1.isChecked()) {
                                    companyRef.child(post_key).child("Job Profile").child(profile_id).child("Personal File").child("Pension Data").child("pension_type").setValue(rdNationalOption1.getText().toString());
                                    companyRef.child(post_key).child("Job Profile").child(profile_id).child("Personal File").child("Pension Data").child("pension_institution").setValue("ONP");
                                }
                                if (rdNationalOption2.isChecked()) {
                                    companyRef.child(post_key).child("Job Profile").child(profile_id).child("Personal File").child("Pension Data").child("pension_type").setValue(rdNationalOption2.getText().toString());
                                    companyRef.child(post_key).child("Job Profile").child(profile_id).child("Personal File").child("Pension Data").child("pension_institution").setValue("ONP");
                                }
                                if (rdNationalOption3.isChecked()) {
                                    companyRef.child(post_key).child("Job Profile").child(profile_id).child("Personal File").child("Pension Data").child("pension_type").setValue(rdNationalOption3.getText().toString());
                                    companyRef.child(post_key).child("Job Profile").child(profile_id).child("Personal File").child("Pension Data").child("pension_institution").setValue("ONP");
                                }
                            }
                            if (rdOther.isChecked()) {
                                if (rdOtherOption1.isChecked()) {
                                    companyRef.child(post_key).child("Job Profile").child(profile_id).child("Personal File").child("Pension Data").child("pension_type").setValue(rdOtherOption1.getText().toString());
                                    companyRef.child(post_key).child("Job Profile").child(profile_id).child("Personal File").child("Pension Data").child("pension_institution").setValue("Otro");
                                }
                                if (rdOtherOption2.isChecked()) {
                                    companyRef.child(post_key).child("Job Profile").child(profile_id).child("Personal File").child("Pension Data").child("pension_type").setValue(rdOtherOption2.getText().toString());
                                    companyRef.child(post_key).child("Job Profile").child(profile_id).child("Personal File").child("Pension Data").child("pension_institution").setValue("Otro");
                                }
                            }
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
}
