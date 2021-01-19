package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.PeopleManagement.PersonalFiles;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;
import com.hbb20.CountryCodePicker;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.SalesModule.CustomerSchedule.CustomerScheduleActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.Locations.PeruLocationsModel;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;
import in.galaxyofandroid.spinerdialog.OnSpinerItemClick;
import in.galaxyofandroid.spinerdialog.SpinnerDialog;


public class WorkerSheetFragment extends Fragment {

    TextView txtName,txtCharge,txtNationality,txtDepartment,txtProvince,txtDistrict,txtDateOfBirth,txtDocumentType,txtDocumentNumber,txtAddress,txtAddressReference,txtPhone,txtMobilePhone,txtEmail,txtEmergencyContact,txtAcademicDegree,txtInstitutionType,
            txtInstitutionName,txtDegreeName,txtDegreeYear,txtOtherIncome,txtOtherIncomeAmount,txtOtherIncomeCompany,txtOtherIncomeCompanyRuc,txtHealth1,txtHealth2,txtHealth3,txtHealth4,txtHealth5,txtHealth6,txtHealth7;
    DatabaseReference companyRef,peruLocations;
    String post_key,profile_id,job_profile_name,job_profile_surname,job_profile_job_name,province_code;
    ImageView btnAdd1,btnAdd2,btnAdd3,btnAdd4,btnAdd5,btnAdd7;
    AlertDialog departmentDialog;
    RecyclerView recyclerView,recyclerView2;
    EditText edtSearch;
    Button btnDepartment,btnDistrict,btnProvince,btnAdd6;
    LinearLayout rootLayout;
    ArrayList<String> bthDay =new ArrayList<>();
    SpinnerDialog bthDayDialog;

    ArrayList<String> bthMonth =new ArrayList<>();
    SpinnerDialog bthMonthDialog;

    ArrayList<String> bthYear =new ArrayList<>();
    SpinnerDialog bthYearDialog;

    ArrayList<String> documentType =new ArrayList<>();
    SpinnerDialog spinnerDocummentType;

    ArrayList<String> arr_academic_degree =new ArrayList<>();
    SpinnerDialog academicDegreeDialog;

    ArrayList<String> arr_institution_type =new ArrayList<>();
    SpinnerDialog institutionTypeDialog;

    ArrayList<String> arr_familiar =new ArrayList<>();
    SpinnerDialog familiarDialog;

    ArrayList<String> arr_civil_state =new ArrayList<>();
    SpinnerDialog civilStateDialog;

    ArrayList<String> bthHour =new ArrayList<>();
    SpinnerDialog bthHourDialog;
    SpinnerDialog bthHourDialog2;
    SpinnerDialog bthHourDialog3;
    SpinnerDialog bthHourDialog4;

    ArrayList<String> bthMinute =new ArrayList<>();
    SpinnerDialog bthMinuterDialog;
    SpinnerDialog bthMinuterDialog2;
    SpinnerDialog bthMinuterDialog3;
    SpinnerDialog bthMinuterDialog4;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_worker_sheet, container, false);

        post_key = getActivity().getIntent().getExtras().getString("post_key");
        profile_id = getActivity().getIntent().getExtras().getString("profile_id");
        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");
        peruLocations= FirebaseDatabase.getInstance().getReference().child("Peru Locations");

        btnAdd1 = view.findViewById(R.id.btnAdd1);
        btnAdd3 = view.findViewById(R.id.btnAdd3);

        txtName = view.findViewById(R.id.txtName);
        txtCharge = view.findViewById(R.id.txtCharge);

        txtNationality = view.findViewById(R.id.txtNationality);
        txtDepartment = view.findViewById(R.id.txtDepartment);
        txtProvince = view.findViewById(R.id.txtProvince);
        txtDistrict = view.findViewById(R.id.txtDistrict);
        txtDateOfBirth = view.findViewById(R.id.txtDateOfBirth);
        txtDocumentType = view.findViewById(R.id.txtDocumentType);
        txtDocumentNumber = view.findViewById(R.id.txtDocumentNumber);
        btnAdd2 = view.findViewById(R.id.btnAdd2);

        txtAddress = view.findViewById(R.id.txtAddress);
        txtAddressReference = view.findViewById(R.id.txtAddressReference);
        txtPhone = view.findViewById(R.id.txtPhone);
        txtMobilePhone = view.findViewById(R.id.txtMobilePhone);
        txtEmail = view.findViewById(R.id.txtEmail);
        txtEmergencyContact = view.findViewById(R.id.txtEmergencyContact);

        txtAcademicDegree = view.findViewById(R.id.txtAcademicDegree);
        txtInstitutionType = view.findViewById(R.id.txtInstitutionType);
        txtInstitutionName = view.findViewById(R.id.txtInstitutionName);
        txtDegreeName = view.findViewById(R.id.txtDegreeName);
        txtDegreeYear = view.findViewById(R.id.txtDegreeYear);
        btnAdd4 = view.findViewById(R.id.btnAdd4);

        txtOtherIncome = view.findViewById(R.id.txtOtherIncome);
        txtOtherIncomeAmount = view.findViewById(R.id.txtOtherIncomeAmount);
        txtOtherIncomeCompany = view.findViewById(R.id.txtOtherIncomeCompany);
        txtOtherIncomeCompanyRuc = view.findViewById(R.id.txtOtherIncomeCompanyRuc);

        btnAdd5 = view.findViewById(R.id.btnAdd5);
        btnAdd6 = view.findViewById(R.id.btnAdd6);
        btnAdd7 = view.findViewById(R.id.btnAdd7);

        txtHealth1 = view.findViewById(R.id.txtHealth1);
        txtHealth2 = view.findViewById(R.id.txtHealth2);
        txtHealth3 = view.findViewById(R.id.txtHealth3);
        txtHealth4 = view.findViewById(R.id.txtHealth4);
        txtHealth5 = view.findViewById(R.id.txtHealth5);
        txtHealth6 = view.findViewById(R.id.txtHealth6);

        recyclerView2 = view.findViewById(R.id.recyclerView2);

        recyclerView2.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setReverseLayout(false);
        linearLayoutManager.setStackFromEnd(false);
        recyclerView2.setLayoutManager(linearLayoutManager);

        companyRef.child(post_key).child("Job Profile").child(profile_id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                job_profile_name = dataSnapshot.child("job_profile_name").getValue().toString();
                job_profile_surname = dataSnapshot.child("job_profile_surname").getValue().toString();
                job_profile_job_name = dataSnapshot.child("job_profile_job_name").getValue().toString();

                txtName.setText(job_profile_name+" "+job_profile_surname);
                txtCharge.setText(job_profile_job_name);

                companyRef.child(post_key).child("Job Profile").child(profile_id).child("Personal File").child("Birth Data").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            if (dataSnapshot.hasChild("nationality")) {
                                String nationality = dataSnapshot.child("nationality").getValue().toString();
                                txtNationality.setText("Nacionalidad: "+nationality);
                            }
                            if (dataSnapshot.hasChild("department")) {
                                String department = dataSnapshot.child("department").getValue().toString();
                                txtDepartment.setText("Departamento: "+department);
                            }
                            if (dataSnapshot.hasChild("province")) {
                                String province = dataSnapshot.child("province").getValue().toString();
                                txtProvince.setText("Provincia: "+province);
                            }
                            if (dataSnapshot.hasChild("district")) {
                                String district = dataSnapshot.child("district").getValue().toString();
                                txtDistrict.setText("Distrito: "+district);
                            }
                            if (dataSnapshot.hasChild("bth_day") && dataSnapshot.hasChild("bth_month") && dataSnapshot.hasChild("bth_year")) {
                                String bth_day = dataSnapshot.child("bth_day").getValue().toString();
                                String bth_month = dataSnapshot.child("bth_month").getValue().toString();
                                String bth_year = dataSnapshot.child("bth_year").getValue().toString();
                                txtDateOfBirth.setText("Fecha de Nacimiento: "+bth_day+"/"+bth_month+"/"+bth_year);
                            }
                            if (dataSnapshot.hasChild("document_type")) {
                                String document_type = dataSnapshot.child("document_type").getValue().toString();
                                txtDocumentType.setText("Tipo de Documento: "+document_type);
                            }
                            if (dataSnapshot.hasChild("document_number")) {
                                String document_number = dataSnapshot.child("document_number").getValue().toString();
                                txtDocumentNumber.setText("Nº de Documento: "+document_number);
                            }

                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                companyRef.child(post_key).child("Job Profile").child(profile_id).child("Personal File").child("Address Data").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            if (dataSnapshot.hasChild("address")) {
                                String address = dataSnapshot.child("address").getValue().toString();
                                txtAddress.setText("Dirección: "+address);
                            }
                            if (dataSnapshot.hasChild("address_reference")) {
                                String address_reference = dataSnapshot.child("address_reference").getValue().toString();
                                txtAddressReference.setText("Referencia: "+address_reference);
                            }
                            if (dataSnapshot.hasChild("fixed_phone")) {
                                String fixed_phone = dataSnapshot.child("fixed_phone").getValue().toString();
                                txtPhone.setText("Teléfono Fijo: "+fixed_phone);
                            }
                            if (dataSnapshot.hasChild("mobile_phone")) {
                                String mobile_phone = dataSnapshot.child("mobile_phone").getValue().toString();
                                txtMobilePhone.setText("Teléfono Móvil: "+mobile_phone);
                            }
                            if (dataSnapshot.hasChild("email")) {
                                String email = dataSnapshot.child("email").getValue().toString();
                                txtEmail.setText("Correo electrónico: "+email);
                            }
                            if (dataSnapshot.hasChild("emergency_contact_name")  && dataSnapshot.hasChild("emergency_contact_phone")) {
                                String emergency_contact_name = dataSnapshot.child("emergency_contact_name").getValue().toString();
                                String emergency_contact_phone = dataSnapshot.child("emergency_contact_phone").getValue().toString();

                                txtEmergencyContact.setText("Ccontacto de Emergencia: "+emergency_contact_name+" - Nº: "+emergency_contact_phone);
                            }

                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                companyRef.child(post_key).child("Job Profile").child(profile_id).child("Personal File").child("Education Data").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            if (dataSnapshot.hasChild("academic_degree")) {
                                String academic_degree = dataSnapshot.child("academic_degree").getValue().toString();
                                txtAcademicDegree.setText("Grado Académico: "+academic_degree);
                            }
                            if (dataSnapshot.hasChild("institution_type")) {
                                String institution_type = dataSnapshot.child("institution_type").getValue().toString();
                                txtInstitutionType.setText("Tipo de Institución: "+institution_type);
                            }
                            if (dataSnapshot.hasChild("institution_name")) {
                                String institution_name = dataSnapshot.child("institution_name").getValue().toString();
                                txtInstitutionName.setText("Nombre de la Institución: "+institution_name);
                            }
                            if (dataSnapshot.hasChild("degree")) {
                                String degree = dataSnapshot.child("degree").getValue().toString();
                                txtDegreeName.setText("Carrera: "+degree);
                            }
                            if (dataSnapshot.hasChild("out_year")) {
                                String out_year = dataSnapshot.child("out_year").getValue().toString();
                                txtDegreeYear.setText("Año de Egreso: "+out_year);
                            }

                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                companyRef.child(post_key).child("Job Profile").child(profile_id).child("Personal File").child("Other Incomes").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            txtOtherIncome.setText("Ingresos de Rentas de 5ta Categoría: Sí");
                            if (dataSnapshot.hasChild("other_income_amount")) {
                                String other_income_amount = dataSnapshot.child("other_income_amount").getValue().toString();
                                txtOtherIncomeAmount.setText("Importe: S/ "+other_income_amount);
                            }
                            if (dataSnapshot.hasChild("other_income_company")) {
                                String other_income_company = dataSnapshot.child("other_income_company").getValue().toString();
                                txtOtherIncomeCompany.setText("Otro Empleador:: "+other_income_company);
                            }
                            if (dataSnapshot.hasChild("other_income_ruc")) {
                                String other_income_ruc = dataSnapshot.child("other_income_ruc").getValue().toString();
                                txtOtherIncomeCompanyRuc.setText("RUC: "+other_income_ruc);
                            }
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                showFamiliars();
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
                View finance_method = inflater.inflate(R.layout.add_job_file1_dialog,null);

                final CountryCodePicker ccpCNationalityountry;
                final Button edtBthDay,edtBthMonth,edtBthYear,btnDocumentType,btnRegister;
                final EditText edtDocumentNumber;


                ccpCNationalityountry = finance_method.findViewById(R.id.ccpCNationalityountry);
                btnDepartment = finance_method.findViewById(R.id.btnDepartment);
                btnProvince = finance_method.findViewById(R.id.btnProvince);
                btnDistrict = finance_method.findViewById(R.id.btnDistrict);
                edtBthDay = finance_method.findViewById(R.id.edtBthDay);
                edtBthMonth = finance_method.findViewById(R.id.edtBthMonth);
                edtBthYear = finance_method.findViewById(R.id.edtBthYear);
                btnDocumentType = finance_method.findViewById(R.id.btnDocumentType);
                edtDocumentNumber = finance_method.findViewById(R.id.edtDocumentNumber);
                btnRegister = finance_method.findViewById(R.id.btnRegister);
                rootLayout = finance_method.findViewById(R.id.rootLayout);

                btnDepartment.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showDepartmentDilog();
                    }
                });

                btnProvince.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (TextUtils.isEmpty(btnDepartment.getText().toString())) {
                            Snackbar.make(rootLayout, "Debes seleccionar un departamento o región primero", Snackbar.LENGTH_LONG).show();
                            return;

                        } else if (TextUtils.isEmpty(btnDepartment.getText().toString())) {
                            Snackbar.make(rootLayout, "Debes seleccionar un departamento o región primero", Snackbar.LENGTH_LONG).show();
                            return;
                        } else {
                            showProvinceDialog();
                        }
                    }
                });


                btnDistrict.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (TextUtils.isEmpty(btnDepartment.getText().toString())) {
                            Snackbar.make(rootLayout, "Debes seleccionar un departamento o región primero", Snackbar.LENGTH_LONG).show();
                            return;

                        } else if (TextUtils.isEmpty(btnProvince.getText().toString())) {
                            Snackbar.make(rootLayout, "Debes seleccionar una provincia o región primero", Snackbar.LENGTH_LONG).show();
                            return;
                        } else if (TextUtils.isEmpty(btnProvince.getText().toString())) {
                            Snackbar.make(rootLayout, "Debes seleccionar una provincia o región primero", Snackbar.LENGTH_LONG).show();
                            return;
                        } else {
                            showDistrictDialog();
                        }
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

                bthDayDialog = new SpinnerDialog(getActivity(),bthDay, "Selecciona tu Día de Nacimiento");
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

                bthMonthDialog = new SpinnerDialog(getActivity(),bthMonth, "Selecciona tu Mes de Nacimiento");
                bthMonthDialog.bindOnSpinerListener(new OnSpinerItemClick() {
                    @Override
                    public void onClick(String item2, int position2) {
                        edtBthMonth.setText(item2);
                    }
                });

                bthYear.add("2019");bthYear.add("2018");bthYear.add("2017");bthYear.add("2016");bthYear.add("2015");bthYear.add("2014");bthYear.add("2013");bthYear.add("2012");bthYear.add("2011");bthYear.add("2010");
                bthYear.add("2009");bthYear.add("2008");bthYear.add("2007");bthYear.add("2006");bthYear.add("2005");bthYear.add("2004");bthYear.add("2003");bthYear.add("2002");bthYear.add("2001");bthYear.add("2000");
                bthYear.add("1999");bthYear.add("1998");bthYear.add("1997");bthYear.add("1996");bthYear.add("1995");bthYear.add("1994");bthYear.add("1993");bthYear.add("1992");bthYear.add("1991");bthYear.add("1990");
                bthYear.add("1989");bthYear.add("1988");bthYear.add("1987");bthYear.add("1986");bthYear.add("1985");bthYear.add("1984");bthYear.add("1983");bthYear.add("1982");bthYear.add("1981");bthYear.add("1980");
                bthYear.add("1979");bthYear.add("1978");bthYear.add("1977");bthYear.add("1976");bthYear.add("1975");bthYear.add("1974");bthYear.add("1973");bthYear.add("1972");bthYear.add("1971");bthYear.add("1970");
                bthYear.add("1969");bthYear.add("1968");bthYear.add("1967");bthYear.add("1966");bthYear.add("1965");bthYear.add("1964");bthYear.add("1963");bthYear.add("1962");bthYear.add("1961");bthYear.add("1960");
                bthYear.add("1959");bthYear.add("1958");bthYear.add("1957");bthYear.add("1956");bthYear.add("1955");bthYear.add("1954");bthYear.add("1953");bthYear.add("1952");bthYear.add("1951");bthYear.add("1950");
                bthYear.add("1949");bthYear.add("1948");bthYear.add("1947");bthYear.add("1946");bthYear.add("1945");bthYear.add("1944");bthYear.add("1943");bthYear.add("1942");bthYear.add("1941");bthYear.add("1940");
                bthYear.add("1939");bthYear.add("1938");bthYear.add("1937");bthYear.add("1936");bthYear.add("1935");bthYear.add("1934");bthYear.add("1933");bthYear.add("1932");bthYear.add("1931");bthYear.add("1930");

                edtBthYear.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bthYearDialog.showSpinerDialog();
                    }
                });

                bthYearDialog = new SpinnerDialog(getActivity(),bthYear, "Selecciona tu Año de Nacimiento");
                bthYearDialog.bindOnSpinerListener(new OnSpinerItemClick() {
                    @Override
                    public void onClick(String item2, int position2) {
                        edtBthYear.setText(item2);

                    }
                });

                documentType.add("D.N.I");documentType.add("Pasaporte");documentType.add("Carnet de Extranjería");

                btnDocumentType.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        spinnerDocummentType.showSpinerDialog();
                    }
                });

                spinnerDocummentType = new SpinnerDialog(getActivity(),documentType, "Selecciona el Tipo de Documento");
                spinnerDocummentType.bindOnSpinerListener(new OnSpinerItemClick() {
                    @Override
                    public void onClick(String item2, int position2) {
                        btnDocumentType.setText(item2);


                    }
                });

                btnRegister.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (TextUtils.isEmpty(btnDepartment.getText().toString())) {
                            Snackbar.make(rootLayout,"Debes completar todos los campos",Snackbar.LENGTH_SHORT).show();
                        }
                        else  if (TextUtils.isEmpty(btnProvince.getText().toString())) {
                            Snackbar.make(rootLayout,"Debes completar todos los campos",Snackbar.LENGTH_SHORT).show();
                        }
                        else  if (TextUtils.isEmpty(btnDistrict.getText().toString())) {
                            Snackbar.make(rootLayout,"Debes completar todos los campos",Snackbar.LENGTH_SHORT).show();
                        }
                        else  if (TextUtils.isEmpty(edtBthDay.getText().toString())) {
                            Snackbar.make(rootLayout,"Debes completar todos los campos",Snackbar.LENGTH_SHORT).show();
                        }
                        else  if (TextUtils.isEmpty(edtBthMonth.getText().toString())) {
                            Snackbar.make(rootLayout,"Debes completar todos los campos",Snackbar.LENGTH_SHORT).show();
                        }
                        else  if (TextUtils.isEmpty(edtBthYear.getText().toString())) {
                            Snackbar.make(rootLayout,"Debes completar todos los campos",Snackbar.LENGTH_SHORT).show();
                        }
                        else  if (TextUtils.isEmpty(btnDocumentType.getText().toString())) {
                            Snackbar.make(rootLayout,"Debes completar todos los campos",Snackbar.LENGTH_SHORT).show();
                        } else if (TextUtils.isEmpty(edtDocumentNumber.getText().toString())) {
                            Snackbar.make(rootLayout, "Debes completar todos los campos", Snackbar.LENGTH_SHORT).show();
                        } else {
                            companyRef.child(post_key).child("Job Profile").child(profile_id).child("Personal File").child("Birth Data").child("nationality").setValue(ccpCNationalityountry.getSelectedCountryName());
                            companyRef.child(post_key).child("Job Profile").child(profile_id).child("Personal File").child("Birth Data").child("department").setValue(btnDepartment.getText().toString());
                            companyRef.child(post_key).child("Job Profile").child(profile_id).child("Personal File").child("Birth Data").child("province").setValue(btnProvince.getText().toString());
                            companyRef.child(post_key).child("Job Profile").child(profile_id).child("Personal File").child("Birth Data").child("district").setValue(btnDistrict.getText().toString());
                            companyRef.child(post_key).child("Job Profile").child(profile_id).child("Personal File").child("Birth Data").child("bth_day").setValue(edtBthDay.getText().toString());
                            companyRef.child(post_key).child("Job Profile").child(profile_id).child("Personal File").child("Birth Data").child("bth_month").setValue(edtBthMonth.getText().toString());
                            companyRef.child(post_key).child("Job Profile").child(profile_id).child("Personal File").child("Birth Data").child("bth_year").setValue(edtBthYear.getText().toString());
                            companyRef.child(post_key).child("Job Profile").child(profile_id).child("Personal File").child("Birth Data").child("document_type").setValue(btnDocumentType.getText().toString());
                            companyRef.child(post_key).child("Job Profile").child(profile_id).child("Personal File").child("Birth Data").child("document_number").setValue(edtDocumentNumber.getText().toString());
                            Toasty.success(getActivity(), "Registrado", Toast.LENGTH_LONG).show();
                            dialog.dismiss();
                        }
                    }
                });

                dialog.setView(finance_method);
                dialog.show();
            }
        });

        btnAdd2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog dialog = new AlertDialog.Builder(getActivity()).create();

                LayoutInflater inflater = LayoutInflater.from(getActivity());
                View finance_method = inflater.inflate(R.layout.add_job_file2_dialog,null);

                final EditText edtAddress,edtReference,edtFixedPhone,edtMobilePhone,edtEmail,edtEmergencyContactName,edtEmergencyContactPhone;
                Button btnRegister;
                final LinearLayout rootLayout;

                edtAddress = finance_method.findViewById(R.id.edtAddress);
                edtReference = finance_method.findViewById(R.id.edtReference);
                edtFixedPhone = finance_method.findViewById(R.id.edtFixedPhone);
                edtMobilePhone = finance_method.findViewById(R.id.edtMobilePhone);
                edtEmail = finance_method.findViewById(R.id.edtEmail);
                edtEmergencyContactName = finance_method.findViewById(R.id.edtEmergencyContactName);
                edtEmergencyContactPhone = finance_method.findViewById(R.id.edtEmergencyContactPhone);
                btnRegister = finance_method.findViewById(R.id.btnRegister);
                rootLayout = finance_method.findViewById(R.id.rootLayout);

                btnRegister.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (TextUtils.isEmpty(edtAddress.getText().toString())) {
                            Snackbar.make(rootLayout,"Debes completar todos los campos",Snackbar.LENGTH_SHORT).show();
                        }
                        else  if (TextUtils.isEmpty(edtReference.getText().toString())) {
                            Snackbar.make(rootLayout,"Debes completar todos los campos",Snackbar.LENGTH_SHORT).show();
                        }
                        else  if (TextUtils.isEmpty(edtFixedPhone.getText().toString())) {
                            Snackbar.make(rootLayout,"Debes completar todos los campos",Snackbar.LENGTH_SHORT).show();
                        }
                        else  if (TextUtils.isEmpty(edtMobilePhone.getText().toString())) {
                            Snackbar.make(rootLayout,"Debes completar todos los campos",Snackbar.LENGTH_SHORT).show();
                        }
                        else  if (TextUtils.isEmpty(edtEmail.getText().toString())) {
                            Snackbar.make(rootLayout,"Debes completar todos los campos",Snackbar.LENGTH_SHORT).show();
                        }
                        else  if (TextUtils.isEmpty(edtEmergencyContactName.getText().toString())) {
                            Snackbar.make(rootLayout,"Debes completar todos los campos",Snackbar.LENGTH_SHORT).show();
                        } else if (TextUtils.isEmpty(edtEmergencyContactPhone.getText().toString())) {
                            Snackbar.make(rootLayout, "Debes completar todos los campos", Snackbar.LENGTH_SHORT).show();
                        } else {
                            companyRef.child(post_key).child("Job Profile").child(profile_id).child("Personal File").child("Address Data").child("address").setValue(edtAddress.getText().toString());
                            companyRef.child(post_key).child("Job Profile").child(profile_id).child("Personal File").child("Address Data").child("address_reference").setValue(edtReference.getText().toString());
                            companyRef.child(post_key).child("Job Profile").child(profile_id).child("Personal File").child("Address Data").child("fixed_phone").setValue(edtFixedPhone.getText().toString());
                            companyRef.child(post_key).child("Job Profile").child(profile_id).child("Personal File").child("Address Data").child("mobile_phone").setValue(edtMobilePhone.getText().toString());
                            companyRef.child(post_key).child("Job Profile").child(profile_id).child("Personal File").child("Address Data").child("email").setValue(edtEmail.getText().toString());
                            companyRef.child(post_key).child("Job Profile").child(profile_id).child("Personal File").child("Address Data").child("emergency_contact_name").setValue(edtEmergencyContactName.getText().toString());
                            companyRef.child(post_key).child("Job Profile").child(profile_id).child("Personal File").child("Address Data").child("emergency_contact_phone").setValue(edtEmergencyContactPhone.getText().toString());
                            Toasty.success(getActivity(), "Registrado", Toast.LENGTH_LONG).show();
                            dialog.dismiss();
                        }
                    }
                });

                dialog.setView(finance_method);
                dialog.show();
            }
        });

        btnAdd3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog dialog = new AlertDialog.Builder(getActivity()).create();

                LayoutInflater inflater = LayoutInflater.from(getActivity());
                View finance_method = inflater.inflate(R.layout.add_job_file3_dialog,null);

                final Button btnAcademicDegree,btnInstitutionType,btnRegister;
                final EditText edtInstitutionName,edtDegree,edtOutYear;
                final LinearLayout rootLayout;

                btnAcademicDegree =finance_method.findViewById(R.id.btnAcademicDegree);
                btnInstitutionType =finance_method.findViewById(R.id.btnInstitutionType);
                btnRegister =finance_method.findViewById(R.id.btnRegister);
                edtInstitutionName =finance_method.findViewById(R.id.edtInstitutionName);
                edtDegree =finance_method.findViewById(R.id.edtDegree);
                edtOutYear =finance_method.findViewById(R.id.edtOutYear);
                rootLayout = finance_method.findViewById(R.id.rootLayout);

                arr_academic_degree.add("SIN EDCUACIÓN FORMAL"); arr_academic_degree.add("EDUCACIÓN ESPECIAL INCOMPLETA"); arr_academic_degree.add("EDUCACIÓN ESPECIAL COMPLETA"); arr_academic_degree.add("EDUCACIÓN PRIMARIA INCOMPLETA"); arr_academic_degree.add("EDUCACIÓN PRIMARIA COMPLETA"); arr_academic_degree.add("EDUCACIÓN SECUNDARIA INCOMPLETA");
                arr_academic_degree.add("EDUCACIÓN SECUNDARIA COMPLETA"); arr_academic_degree.add("EDUCACIÓN TÉCNICA INCOMPLETA"); arr_academic_degree.add("EDUCACIÓN TÉCNICA COMPLETA"); arr_academic_degree.add("EDUCACIÓN SUP. (INSTITUTO SUPERIOR, ETC. INCOMPLETA)"); arr_academic_degree.add("EDUCACIÓN SUP. (INSTITUTO SUPERIOR, ETC. COMPLETA)"); arr_academic_degree.add("EDUCACIÓN UNIVERSITARIA INCOMPLETA");
                arr_academic_degree.add("EDUCACIÓN UNIVERSITARIA COMPLETA");arr_academic_degree.add("GRADO BACHILLER");arr_academic_degree.add("TITULADO");arr_academic_degree.add("EDUCACIÓN MAESTRÍA INCOMPLETA");arr_academic_degree.add("EDUCACIÓN MAESTRÍA COMPLETA");arr_academic_degree.add("GRADO DE MAESTRÍA");arr_academic_degree.add("ESTUDIO DE DOCTORADO INCOMPLETO");
                arr_academic_degree.add("ESTUDIO DE DOCTORADO COMPLETO");arr_academic_degree.add("GRADO DE DOCTOR");

                btnAcademicDegree.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        academicDegreeDialog.showSpinerDialog();
                    }
                });

                academicDegreeDialog = new SpinnerDialog(getActivity(),arr_academic_degree, "Selecciona el grado académico");
                academicDegreeDialog.bindOnSpinerListener(new OnSpinerItemClick() {
                    @Override
                    public void onClick(String item2, int position2) {
                        btnAcademicDegree.setText(item2);

                    }
                });

                arr_institution_type.add("ESCUELAS E INSTITUTOS DE EDUCACIÓN SUPERIOR, TECNOLOGICOS DE LAS FUERZAS ARMADAS Y POLICIALES"); arr_institution_type.add("EDUCACION SUPERIOR DE FORMACIÓN ARTÍSTICA"); arr_institution_type.add("INSTITUTO SUPERIOR PEDAGÓGICO"); arr_institution_type.add("INSTITUTOS DE EDUCACION SUPERIOR TENOLÓGICA (IEST)"); arr_institution_type.add("UNIVERSIDAD"); arr_institution_type.add("NO ESPECIFICADO");

                btnInstitutionType.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        institutionTypeDialog.showSpinerDialog();
                    }
                });

                institutionTypeDialog = new SpinnerDialog(getActivity(),arr_institution_type, "Selecciona el grado académico");
                institutionTypeDialog.bindOnSpinerListener(new OnSpinerItemClick() {
                    @Override
                    public void onClick(String item2, int position2) {
                        btnInstitutionType.setText(item2);

                    }
                });


                btnRegister.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (TextUtils.isEmpty(btnAcademicDegree.getText().toString())) {
                            Snackbar.make(rootLayout,"Debes completar todos los campos",Snackbar.LENGTH_SHORT).show();
                        }
                        else if (TextUtils.isEmpty(btnInstitutionType.getText().toString())) {
                            Snackbar.make(rootLayout,"Debes completar todos los campos",Snackbar.LENGTH_SHORT).show();
                        }
                        else if (TextUtils.isEmpty(edtInstitutionName.getText().toString())) {
                            Snackbar.make(rootLayout,"Debes completar todos los campos",Snackbar.LENGTH_SHORT).show();
                        }
                        else if (TextUtils.isEmpty(edtDegree.getText().toString())) {
                            Snackbar.make(rootLayout,"Debes completar todos los campos",Snackbar.LENGTH_SHORT).show();
                        } else if (TextUtils.isEmpty(edtOutYear.getText().toString())) {
                            Snackbar.make(rootLayout, "Debes completar todos los campos", Snackbar.LENGTH_SHORT).show();
                        } else {
                            companyRef.child(post_key).child("Job Profile").child(profile_id).child("Personal File").child("Education Data").child("academic_degree").setValue(btnAcademicDegree.getText().toString());
                            companyRef.child(post_key).child("Job Profile").child(profile_id).child("Personal File").child("Education Data").child("institution_type").setValue(btnInstitutionType.getText().toString());
                            companyRef.child(post_key).child("Job Profile").child(profile_id).child("Personal File").child("Education Data").child("institution_name").setValue(edtInstitutionName.getText().toString());
                            companyRef.child(post_key).child("Job Profile").child(profile_id).child("Personal File").child("Education Data").child("degree").setValue(edtDegree.getText().toString());
                            companyRef.child(post_key).child("Job Profile").child(profile_id).child("Personal File").child("Education Data").child("out_year").setValue(edtOutYear.getText().toString());
                            Toasty.success(getActivity(), "Registrado", Toast.LENGTH_LONG).show();
                            dialog.dismiss();
                        }
                    }
                });

                dialog.setView(finance_method);
                dialog.show();
            }
        });

        btnAdd4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog dialog = new AlertDialog.Builder(getActivity()).create();

                LayoutInflater inflater = LayoutInflater.from(getActivity());
                View finance_method = inflater.inflate(R.layout.add_job_file4_dialog,null);

                final Button btnFamiliar,edtBthDay,edtBthMonth,edtBthYear,btnCivilState,btnRegister;
                final EditText edtFamiliarName,edtDocumentNumber;
                final RadioButton rdYes,rdNo;
                final LinearLayout rootLayout;

                btnFamiliar = finance_method.findViewById(R.id.btnFamiliar);
                edtBthDay = finance_method.findViewById(R.id.edtBthDay);
                edtBthMonth = finance_method.findViewById(R.id.edtBthMonth);
                edtBthYear = finance_method.findViewById(R.id.edtBthYear);
                edtFamiliarName = finance_method.findViewById(R.id.edtFamiliarName);
                edtDocumentNumber = finance_method.findViewById(R.id.edtDocumentNumber);
                btnCivilState = finance_method.findViewById(R.id.btnCivilState);
                btnRegister = finance_method.findViewById(R.id.btnRegister);
                rdYes = finance_method.findViewById(R.id.rdYes);
                rdNo = finance_method.findViewById(R.id.rdNo);
                rootLayout = finance_method.findViewById(R.id.rootLayout);

                arr_familiar.add("CÓNYUGE"); arr_familiar.add("CONCUBINA");arr_familiar.add("CONCUBINO"); arr_familiar.add("HIJO"); arr_familiar.add("HIJA");

                btnFamiliar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        familiarDialog.showSpinerDialog();
                    }
                });

                familiarDialog = new SpinnerDialog(getActivity(),arr_familiar, "Selecciona el Familiar");
                familiarDialog.bindOnSpinerListener(new OnSpinerItemClick() {
                    @Override
                    public void onClick(String item2, int position2) {
                        btnFamiliar.setText(item2);

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

                bthDayDialog = new SpinnerDialog(getActivity(),bthDay, "Selecciona tu Día de Nacimiento");
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

                bthMonthDialog = new SpinnerDialog(getActivity(),bthMonth, "Selecciona tu Mes de Nacimiento");
                bthMonthDialog.bindOnSpinerListener(new OnSpinerItemClick() {
                    @Override
                    public void onClick(String item2, int position2) {
                        edtBthMonth.setText(item2);
                    }
                });

                bthYear.add("2019");bthYear.add("2018");bthYear.add("2017");bthYear.add("2016");bthYear.add("2015");bthYear.add("2014");bthYear.add("2013");bthYear.add("2012");bthYear.add("2011");bthYear.add("2010");
                bthYear.add("2009");bthYear.add("2008");bthYear.add("2007");bthYear.add("2006");bthYear.add("2005");bthYear.add("2004");bthYear.add("2003");bthYear.add("2002");bthYear.add("2001");bthYear.add("2000");
                bthYear.add("1999");bthYear.add("1998");bthYear.add("1997");bthYear.add("1996");bthYear.add("1995");bthYear.add("1994");bthYear.add("1993");bthYear.add("1992");bthYear.add("1991");bthYear.add("1990");
                bthYear.add("1989");bthYear.add("1988");bthYear.add("1987");bthYear.add("1986");bthYear.add("1985");bthYear.add("1984");bthYear.add("1983");bthYear.add("1982");bthYear.add("1981");bthYear.add("1980");
                bthYear.add("1979");bthYear.add("1978");bthYear.add("1977");bthYear.add("1976");bthYear.add("1975");bthYear.add("1974");bthYear.add("1973");bthYear.add("1972");bthYear.add("1971");bthYear.add("1970");
                bthYear.add("1969");bthYear.add("1968");bthYear.add("1967");bthYear.add("1966");bthYear.add("1965");bthYear.add("1964");bthYear.add("1963");bthYear.add("1962");bthYear.add("1961");bthYear.add("1960");
                bthYear.add("1959");bthYear.add("1958");bthYear.add("1957");bthYear.add("1956");bthYear.add("1955");bthYear.add("1954");bthYear.add("1953");bthYear.add("1952");bthYear.add("1951");bthYear.add("1950");
                bthYear.add("1949");bthYear.add("1948");bthYear.add("1947");bthYear.add("1946");bthYear.add("1945");bthYear.add("1944");bthYear.add("1943");bthYear.add("1942");bthYear.add("1941");bthYear.add("1940");
                bthYear.add("1939");bthYear.add("1938");bthYear.add("1937");bthYear.add("1936");bthYear.add("1935");bthYear.add("1934");bthYear.add("1933");bthYear.add("1932");bthYear.add("1931");bthYear.add("1930");

                edtBthYear.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bthYearDialog.showSpinerDialog();
                    }
                });

                bthYearDialog = new SpinnerDialog(getActivity(),bthYear, "Selecciona tu Año de Nacimiento");
                bthYearDialog.bindOnSpinerListener(new OnSpinerItemClick() {
                    @Override
                    public void onClick(String item2, int position2) {
                        edtBthYear.setText(item2);

                    }
                });

                arr_civil_state.add("SOLTERO");arr_civil_state.add("CASADO");arr_civil_state.add("VIUDO");arr_civil_state.add("DIVORCIADO");

                btnCivilState.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        civilStateDialog.showSpinerDialog();
                    }
                });

                civilStateDialog = new SpinnerDialog(getActivity(),arr_civil_state, "Selecciona el estado Civil");
                civilStateDialog.bindOnSpinerListener(new OnSpinerItemClick() {
                    @Override
                    public void onClick(String item2, int position2) {
                        btnCivilState.setText(item2);

                    }
                });

                btnRegister.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (TextUtils.isEmpty(btnFamiliar.getText().toString())) {
                            Snackbar.make(rootLayout,"Debes completar todos los datos",Snackbar.LENGTH_SHORT).show();
                        }
                        else  if (TextUtils.isEmpty(edtFamiliarName.getText().toString())) {
                            Snackbar.make(rootLayout,"Debes completar todos los datos",Snackbar.LENGTH_SHORT).show();
                        }
                        else  if (TextUtils.isEmpty(edtBthDay.getText().toString())) {
                            Snackbar.make(rootLayout,"Debes completar todos los datos",Snackbar.LENGTH_SHORT).show();
                        }
                        else  if (TextUtils.isEmpty(edtBthMonth.getText().toString())) {
                            Snackbar.make(rootLayout,"Debes completar todos los datos",Snackbar.LENGTH_SHORT).show();
                        }
                        else  if (TextUtils.isEmpty(edtBthYear.getText().toString())) {
                            Snackbar.make(rootLayout,"Debes completar todos los datos",Snackbar.LENGTH_SHORT).show();
                        }
                        else  if (TextUtils.isEmpty(btnCivilState.getText().toString())) {
                            Snackbar.make(rootLayout,"Debes completar todos los datos",Snackbar.LENGTH_SHORT).show();
                        }
                        else  if (TextUtils.isEmpty(edtDocumentNumber.getText().toString())) {
                            Snackbar.make(rootLayout,"Debes completar todos los datos",Snackbar.LENGTH_SHORT).show();
                        } else if (!rdYes.isChecked() && !rdNo.isChecked()) {
                            Snackbar.make(rootLayout, "Debes completar todos los datos", Snackbar.LENGTH_SHORT).show();
                        } else {
                            Long tsLong = System.currentTimeMillis()/1000;
                            String timestamp = tsLong.toString();
                            companyRef.child(post_key).child("Job Profile").child(profile_id).child("Personal File").child("Familiars").child(timestamp).child("familiar_type").setValue(btnFamiliar.getText().toString());
                            companyRef.child(post_key).child("Job Profile").child(profile_id).child("Personal File").child("Familiars").child(timestamp).child("familiar_name").setValue(edtFamiliarName.getText().toString());
                            companyRef.child(post_key).child("Job Profile").child(profile_id).child("Personal File").child("Familiars").child(timestamp).child("familiar_bth_day").setValue(edtBthDay.getText().toString());
                            companyRef.child(post_key).child("Job Profile").child(profile_id).child("Personal File").child("Familiars").child(timestamp).child("familiar_bth_month").setValue(edtBthMonth.getText().toString());
                            companyRef.child(post_key).child("Job Profile").child(profile_id).child("Personal File").child("Familiars").child(timestamp).child("familiar_bth_year").setValue(edtBthYear.getText().toString());
                            companyRef.child(post_key).child("Job Profile").child(profile_id).child("Personal File").child("Familiars").child(timestamp).child("familiar_bth_civil_state").setValue(btnCivilState.getText().toString());
                            companyRef.child(post_key).child("Job Profile").child(profile_id).child("Personal File").child("Familiars").child(timestamp).child("familiar_document_number").setValue(edtDocumentNumber.getText().toString());
                            companyRef.child(post_key).child("Job Profile").child(profile_id).child("Personal File").child("Familiars").child(timestamp).child("timestamp").setValue(ServerValue.TIMESTAMP);
                            if (rdYes.isChecked()) {
                                companyRef.child(post_key).child("Job Profile").child(profile_id).child("Personal File").child("Familiars").child(timestamp).child("familiar_same_address").setValue(rdYes.getText().toString());
                            }
                            if (rdNo.isChecked()) {
                                companyRef.child(post_key).child("Job Profile").child(profile_id).child("Personal File").child("Familiars").child(timestamp).child("familiar_same_address").setValue(rdNo.getText().toString());
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

        btnAdd5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog dialog = new AlertDialog.Builder(getActivity()).create();

                LayoutInflater inflater = LayoutInflater.from(getActivity());
                View finance_method = inflater.inflate(R.layout.add_job_file5_dialog,null);

                final RadioButton rdYes,rdNo;
                final EditText edtAmount,edtCompanyName,edtRuc;
                Button btnRegister;
                final LinearLayout incomesLayout,rootLayout;

                rdYes = finance_method.findViewById(R.id.rdYes);
                rdNo = finance_method.findViewById(R.id.rdNo);
                edtAmount = finance_method.findViewById(R.id.edtAmount);
                edtCompanyName = finance_method.findViewById(R.id.edtCompanyName);
                edtRuc = finance_method.findViewById(R.id.edtRuc);
                btnRegister = finance_method.findViewById(R.id.btnRegister);
                incomesLayout = finance_method.findViewById(R.id.incomesLayout);
                rootLayout = finance_method.findViewById(R.id.rootLayout);

                rdYes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        incomesLayout.setVisibility(View.VISIBLE);
                    }
                });
                rdNo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        incomesLayout.setVisibility(View.GONE);
                    }
                });

                btnRegister.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!rdNo.isChecked() && !rdYes.isChecked()) {

                        } else {
                            if (rdYes.isChecked()) {

                                if (TextUtils.isEmpty(edtAmount.getText().toString())) {
                                    Snackbar.make(rootLayout, "Debes compeltar toda la información", Snackbar.LENGTH_SHORT).show();
                                } else if (TextUtils.isEmpty(edtCompanyName.getText().toString())) {
                                    Snackbar.make(rootLayout, "Debes compeltar toda la información", Snackbar.LENGTH_SHORT).show();
                                } else if (TextUtils.isEmpty(edtRuc.getText().toString())) {
                                    Snackbar.make(rootLayout, "Debes compeltar toda la información", Snackbar.LENGTH_SHORT).show();
                                } else {
                                    companyRef.child(post_key).child("Job Profile").child(profile_id).child("Personal File").child("Other Incomes").child("other_income_amount").setValue(edtAmount.getText().toString());
                                    companyRef.child(post_key).child("Job Profile").child(profile_id).child("Personal File").child("Other Incomes").child("other_income_company").setValue(edtCompanyName.getText().toString());
                                    companyRef.child(post_key).child("Job Profile").child(profile_id).child("Personal File").child("Other Incomes").child("other_income_ruc").setValue(edtRuc.getText().toString());
                                    Toasty.success(getActivity(), "Registrado", Toast.LENGTH_LONG).show();
                                    dialog.dismiss();
                                }

                            } else {
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

        btnAdd7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog dialog = new AlertDialog.Builder(getActivity()).create();

                LayoutInflater inflater = LayoutInflater.from(getActivity());
                View finance_method = inflater.inflate(R.layout.add_job_file7_dialog,null);

                final Button edtBthDay,edtBthMonth,edtBthYear,btnHourStart,btnMinuteStart,btnHourEnd,btnMinuteEnd,btnHourLunchStart,btnMinuteLunchStart,btnHourLunchEnd,btnMinuteLunchEnd,btnLabourType,btnWorkerType,btnContract,btnPaymentFrequency,
                        btnPaymentType,btnRegister;
                EditText edtPaymentAmount,edtSubvention,edtTotalWeekHours;
                RadioButton rdPen,rdUsd;

                edtBthDay = finance_method.findViewById(R.id.edtBthDay);
                edtBthMonth = finance_method.findViewById(R.id.edtBthMonth);
                edtBthYear = finance_method.findViewById(R.id.edtBthYear);

                btnHourStart = finance_method.findViewById(R.id.btnHourStart);
                btnMinuteStart = finance_method.findViewById(R.id.btnMinuteStart);
                btnHourEnd = finance_method.findViewById(R.id.btnHourEnd);
                btnMinuteEnd = finance_method.findViewById(R.id.btnMinuteEnd);
                btnHourLunchStart = finance_method.findViewById(R.id.btnHourLunchStart);
                btnMinuteLunchStart = finance_method.findViewById(R.id.btnMinuteLunchStart);
                btnHourLunchEnd = finance_method.findViewById(R.id.btnHourLunchEnd);
                btnMinuteLunchEnd = finance_method.findViewById(R.id.btnMinuteLunchEnd);
                btnLabourType = finance_method.findViewById(R.id.btnLabourType);
                btnWorkerType = finance_method.findViewById(R.id.btnWorkerType);
                btnContract = finance_method.findViewById(R.id.btnContract);
                btnPaymentFrequency = finance_method.findViewById(R.id.btnPaymentFrequency);
                btnPaymentType = finance_method.findViewById(R.id.btnPaymentType);
                btnRegister = finance_method.findViewById(R.id.btnRegister);
                edtPaymentAmount = finance_method.findViewById(R.id.edtPaymentAmount);
                edtSubvention = finance_method.findViewById(R.id.edtSubvention);
                edtTotalWeekHours = finance_method.findViewById(R.id.edtTotalWeekHours);
                rdPen = finance_method.findViewById(R.id.rdPen);
                rdUsd = finance_method.findViewById(R.id.rdUsd);


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

                bthDayDialog = new SpinnerDialog(getActivity(),bthDay, "Selecciona tu Día de Nacimiento");
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

                bthMonthDialog = new SpinnerDialog(getActivity(),bthMonth, "Selecciona tu Mes de Nacimiento");
                bthMonthDialog.bindOnSpinerListener(new OnSpinerItemClick() {
                    @Override
                    public void onClick(String item2, int position2) {
                        edtBthMonth.setText(item2);
                    }
                });

                bthYear.add("2019");bthYear.add("2018");bthYear.add("2017");bthYear.add("2016");bthYear.add("2015");bthYear.add("2014");bthYear.add("2013");bthYear.add("2012");bthYear.add("2011");bthYear.add("2010");
                bthYear.add("2009");bthYear.add("2008");bthYear.add("2007");bthYear.add("2006");bthYear.add("2005");bthYear.add("2004");bthYear.add("2003");bthYear.add("2002");bthYear.add("2001");bthYear.add("2000");
                bthYear.add("1999");bthYear.add("1998");bthYear.add("1997");bthYear.add("1996");bthYear.add("1995");bthYear.add("1994");bthYear.add("1993");bthYear.add("1992");bthYear.add("1991");bthYear.add("1990");
                bthYear.add("1989");bthYear.add("1988");bthYear.add("1987");bthYear.add("1986");bthYear.add("1985");bthYear.add("1984");bthYear.add("1983");bthYear.add("1982");bthYear.add("1981");bthYear.add("1980");
                bthYear.add("1979");bthYear.add("1978");bthYear.add("1977");bthYear.add("1976");bthYear.add("1975");bthYear.add("1974");bthYear.add("1973");bthYear.add("1972");bthYear.add("1971");bthYear.add("1970");
                bthYear.add("1969");bthYear.add("1968");bthYear.add("1967");bthYear.add("1966");bthYear.add("1965");bthYear.add("1964");bthYear.add("1963");bthYear.add("1962");bthYear.add("1961");bthYear.add("1960");
                bthYear.add("1959");bthYear.add("1958");bthYear.add("1957");bthYear.add("1956");bthYear.add("1955");bthYear.add("1954");bthYear.add("1953");bthYear.add("1952");bthYear.add("1951");bthYear.add("1950");
                bthYear.add("1949");bthYear.add("1948");bthYear.add("1947");bthYear.add("1946");bthYear.add("1945");bthYear.add("1944");bthYear.add("1943");bthYear.add("1942");bthYear.add("1941");bthYear.add("1940");
                bthYear.add("1939");bthYear.add("1938");bthYear.add("1937");bthYear.add("1936");bthYear.add("1935");bthYear.add("1934");bthYear.add("1933");bthYear.add("1932");bthYear.add("1931");bthYear.add("1930");

                edtBthYear.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bthYearDialog.showSpinerDialog();
                    }
                });

                bthYearDialog = new SpinnerDialog(getActivity(),bthYear, "Selecciona tu Año de Nacimiento");
                bthYearDialog.bindOnSpinerListener(new OnSpinerItemClick() {
                    @Override
                    public void onClick(String item2, int position2) {
                        edtBthYear.setText(item2);

                    }
                });

                bthHour.add("01");bthHour.add("02");bthHour.add("03");bthHour.add("04");bthHour.add("05");bthHour.add("06");bthHour.add("07");bthHour.add("08");bthHour.add("09");
                bthHour.add("10");bthHour.add("11");bthHour.add("12");bthHour.add("13");bthHour.add("14");bthHour.add("15");bthHour.add("16");bthHour.add("17");bthHour.add("18");bthHour.add("19");
                bthHour.add("20");bthHour.add("21");bthHour.add("22");bthHour.add("23");bthHour.add("00");

                btnHourStart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bthHourDialog.showSpinerDialog();
                    }
                });

                bthHourDialog = new SpinnerDialog(getActivity(),bthHour, "Selecciona la hora ");
                bthHourDialog.bindOnSpinerListener(new OnSpinerItemClick() {
                    @Override
                    public void onClick(String item2, int position2) {
                        btnHourStart.setText(item2);
                    }
                });

                bthMinute.add("00");bthMinute.add("01");bthMinute.add("02");bthMinute.add("03");bthMinute.add("04");bthMinute.add("05");bthMinute.add("06");bthMinute.add("07");bthMinute.add("08");bthMinute.add("09");
                bthMinute.add("10");bthMinute.add("11");bthMinute.add("12");bthMinute.add("13");bthMinute.add("14");bthMinute.add("15");bthMinute.add("16");bthMinute.add("17");bthMinute.add("18");bthMinute.add("19");
                bthMinute.add("20");bthMinute.add("21");bthMinute.add("22");bthMinute.add("23");bthMinute.add("24");bthMinute.add("25");bthMinute.add("26");bthMinute.add("27");bthMinute.add("28");bthMinute.add("29");
                bthMinute.add("30");bthMinute.add("31");bthMinute.add("32");bthMinute.add("33");bthMinute.add("34");bthMinute.add("35");bthMinute.add("36");bthMinute.add("37");bthMinute.add("38");bthMinute.add("39");
                bthMinute.add("40");bthMinute.add("41");bthMinute.add("42");bthMinute.add("43");bthMinute.add("44");bthMinute.add("45");bthMinute.add("46");bthMinute.add("47");bthMinute.add("48");bthMinute.add("49");
                bthMinute.add("50");bthMinute.add("51");bthMinute.add("52");bthMinute.add("53");bthMinute.add("54");bthMinute.add("55");bthMinute.add("56");bthMinute.add("57");bthMinute.add("58");bthMinute.add("59");

                btnMinuteStart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bthMinuterDialog.showSpinerDialog();
                    }
                });

                bthMinuterDialog = new SpinnerDialog(getActivity(),bthMinute, "Selecciona el minuto");
                bthMinuterDialog.bindOnSpinerListener(new OnSpinerItemClick() {
                    @Override
                    public void onClick(String item2, int position2) {
                        btnMinuteStart.setText(item2);
                    }
                });

                btnHourEnd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bthHourDialog2.showSpinerDialog();
                    }
                });

                bthHourDialog2 = new SpinnerDialog(getActivity(),bthHour, "Selecciona la hora ");
                bthHourDialog2.bindOnSpinerListener(new OnSpinerItemClick() {
                    @Override
                    public void onClick(String item2, int position2) {
                        btnHourEnd.setText(item2);
                    }
                });

                dialog.setView(finance_method);
                dialog.show();
            }
        });

        return view;
    }

    private void showFamiliars() {
        Query query = companyRef.child(post_key).child("Job Profile").child(profile_id).child("Personal File").child("Familiars").orderByChild("timestamp");
        FirebaseRecyclerAdapter<FamiliarsModel,FamiliarsViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<FamiliarsModel, FamiliarsViewHolder>
                (FamiliarsModel.class,R.layout.job_file_familiar_item,FamiliarsViewHolder.class,query) {
            @Override
            protected void populateViewHolder(FamiliarsViewHolder viewHolder, FamiliarsModel model, int position) {
                viewHolder.setFamiliar_bth_civil_state(model.getFamiliar_bth_civil_state());
                viewHolder.setFamiliar_bth_day(model.getFamiliar_bth_day());
                viewHolder.setFamiliar_bth_month(model.getFamiliar_bth_month());
                viewHolder.setFamiliar_bth_year(model.getFamiliar_bth_year());
                viewHolder.setFamiliar_document_number(model.getFamiliar_document_number());
                viewHolder.setFamiliar_name(model.getFamiliar_name());
                viewHolder.setFamiliar_type(model.getFamiliar_type());

                viewHolder.txtFamiliarName.setText(viewHolder.my_familiar_name);
                viewHolder.txtCivilState.setText("Estado Civil: "+viewHolder.my_familiar_bth_civil_state);
                viewHolder.txtDateOfBirth.setText("Fecha de Nacimiento: "+viewHolder.my_familiar_bth_day+"/"+viewHolder.my_familiar_bth_month+"/"+viewHolder.my_familiar_bth_year);
                viewHolder.txtFamiliarType.setText(viewHolder.my_familiar_type);
                viewHolder.txtDocumentNumber.setText("Nº de Documento de Identidad: "+viewHolder.my_familiar_document_number);
            }
        };
        recyclerView2.setAdapter(firebaseRecyclerAdapter);
    }

    public static class FamiliarsViewHolder extends RecyclerView.ViewHolder {
        View mView;
        String my_familiar_type,my_familiar_name,my_familiar_bth_day,my_familiar_bth_month,my_familiar_bth_year,my_familiar_bth_civil_state,my_familiar_document_number;
        TextView txtFamiliarName,txtFamiliarType,txtDateOfBirth,txtCivilState,txtDocumentNumber;

        public FamiliarsViewHolder(@NonNull View itemView) {
            super(itemView);

            mView = itemView;

            txtFamiliarName = mView.findViewById(R.id.txtFamiliarName);
            txtFamiliarType = mView.findViewById(R.id.txtFamiliarType);
            txtDateOfBirth = mView.findViewById(R.id.txtDateOfBirth);
            txtCivilState = mView.findViewById(R.id.txtCivilState);
            txtDocumentNumber = mView.findViewById(R.id.txtDocumentNumber);
        }
        public void setFamiliar_type(String familiar_type) {
            my_familiar_type = familiar_type;
        }
        public void setFamiliar_name(String familiar_name) {
            my_familiar_name = familiar_name;
        }


        public void setFamiliar_bth_day(String familiar_bth_day) {
            my_familiar_bth_day = familiar_bth_day;
        }

        public void setFamiliar_bth_month(String familiar_bth_month) {
            my_familiar_bth_month = familiar_bth_month;
        }

        public void setFamiliar_bth_year(String familiar_bth_year) {
            my_familiar_bth_year = familiar_bth_year;
        }

        public void setFamiliar_bth_civil_state(String familiar_bth_civil_state) {
            my_familiar_bth_civil_state = familiar_bth_civil_state;
        }

        public void setFamiliar_document_number(String familiar_document_number) {
            my_familiar_document_number = familiar_document_number;
        }
    }


    private void showDistrictDialog() {
        departmentDialog = new AlertDialog.Builder(getActivity()).create();

        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View finance_method = inflater.inflate(R.layout.departments_locations_dialog,null);

        edtSearch = finance_method.findViewById(R.id.edtSearch);
        recyclerView = finance_method.findViewById(R.id.recyclerView);

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setReverseLayout(false);
        linearLayoutManager.setStackFromEnd(false);
        recyclerView.setLayoutManager(linearLayoutManager);

        showDistrictLocations();

        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                searchDistricts();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });




        departmentDialog.setView(finance_method);
        departmentDialog.show();
    }

    private void searchDistricts() {
        Query query = peruLocations.child("Districs").child(province_code).orderByChild("nombre_ubigeo").startAt(edtSearch.getText().toString()).endAt(edtSearch.getText().toString()+"\uf8ff");
        FirebaseRecyclerAdapter<PeruLocationsModel, LocationsViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<PeruLocationsModel, LocationsViewHolder>
                (PeruLocationsModel.class, R.layout.location_item, LocationsViewHolder.class, query) {
            @Override
            protected void populateViewHolder(final LocationsViewHolder viewHolder, PeruLocationsModel model, final int position) {
                viewHolder.setNombre_ubigeo(model.getNombre_ubigeo());
                viewHolder.setId_ubigeo(model.getId_ubigeo());

                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        btnDistrict.setText(viewHolder.location_name);
                        departmentDialog.dismiss();
                    }
                });
            }
        };
        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    private void showDistrictLocations() {
        Query query = peruLocations.child("Districs").child(province_code).orderByChild("nombre_ubigeo");
        FirebaseRecyclerAdapter<PeruLocationsModel, LocationsViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<PeruLocationsModel, LocationsViewHolder>
                (PeruLocationsModel.class, R.layout.location_item, LocationsViewHolder.class, query) {
            @Override
            protected void populateViewHolder(final LocationsViewHolder viewHolder, PeruLocationsModel model, final int position) {
                viewHolder.setNombre_ubigeo(model.getNombre_ubigeo());
                viewHolder.setId_ubigeo(model.getId_ubigeo());

                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        btnDistrict.setText(viewHolder.location_name);
                        departmentDialog.dismiss();
                    }
                });
            }
        };
        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    private void showProvinceDialog() {
        departmentDialog = new AlertDialog.Builder(getActivity()).create();

        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View finance_method = inflater.inflate(R.layout.departments_locations_dialog,null);

        edtSearch = finance_method.findViewById(R.id.edtSearch);
        recyclerView = finance_method.findViewById(R.id.recyclerView);

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setReverseLayout(false);
        linearLayoutManager.setStackFromEnd(false);
        recyclerView.setLayoutManager(linearLayoutManager);

        showProvinceLocations();

        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                searchProvinces();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        departmentDialog.setView(finance_method);
        departmentDialog.show();

    }

    private void searchProvinces() {
        Query query = peruLocations.child("Provinces").child(province_code).orderByChild("nombre_ubigeo").startAt(edtSearch.getText().toString()).endAt(edtSearch.getText().toString()+"\uf8ff");
        FirebaseRecyclerAdapter<PeruLocationsModel, LocationsViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<PeruLocationsModel, LocationsViewHolder>
                (PeruLocationsModel.class, R.layout.location_item, LocationsViewHolder.class, query) {
            @Override
            protected void populateViewHolder(final LocationsViewHolder viewHolder, PeruLocationsModel model, final int position) {
                viewHolder.setNombre_ubigeo(model.getNombre_ubigeo());
                viewHolder.setId_ubigeo(model.getId_ubigeo());

                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        btnProvince.setText(viewHolder.location_name);
                        btnDistrict.setText("");
                        province_code = viewHolder.id_location;
                        departmentDialog.dismiss();
                    }
                });
            }
        };
        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    private void showProvinceLocations() {
        Query query = peruLocations.child("Provinces").child(province_code).orderByChild("nombre_ubigeo");
        FirebaseRecyclerAdapter<PeruLocationsModel, LocationsViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<PeruLocationsModel, LocationsViewHolder>
                (PeruLocationsModel.class, R.layout.location_item, LocationsViewHolder.class, query) {
            @Override
            protected void populateViewHolder(final LocationsViewHolder viewHolder, PeruLocationsModel model, final int position) {
                viewHolder.setNombre_ubigeo(model.getNombre_ubigeo());
                viewHolder.setId_ubigeo(model.getId_ubigeo());

                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        btnProvince.setText(viewHolder.location_name);
                        btnDistrict.setText("");
                        province_code = viewHolder.id_location;
                        departmentDialog.dismiss();
                    }
                });
            }
        };
        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    private void showDepartmentDilog() {
        departmentDialog = new AlertDialog.Builder(getActivity()).create();

        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View finance_method = inflater.inflate(R.layout.departments_locations_dialog,null);

        edtSearch = finance_method.findViewById(R.id.edtSearch);
        recyclerView = finance_method.findViewById(R.id.recyclerView);

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setReverseLayout(false);
        linearLayoutManager.setStackFromEnd(false);
        recyclerView.setLayoutManager(linearLayoutManager);

        showLocations();

        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                searchDepartments();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        departmentDialog.setView(finance_method);
        departmentDialog.show();

    }

    private void searchDepartments() {
        Query query = peruLocations.child("Departments").orderByChild("nombre_ubigeo").startAt(edtSearch.getText().toString()).endAt(edtSearch.getText().toString()+"\uf8ff");
        FirebaseRecyclerAdapter<PeruLocationsModel, LocationsViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<PeruLocationsModel, LocationsViewHolder>
                (PeruLocationsModel.class, R.layout.location_item, LocationsViewHolder.class, query) {
            @Override
            protected void populateViewHolder(final LocationsViewHolder viewHolder, PeruLocationsModel model, int position) {
                viewHolder.setNombre_ubigeo(model.getNombre_ubigeo());
                viewHolder.setId_ubigeo(model.getId_ubigeo());

                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        btnDepartment.setText(viewHolder.location_name);
                        btnProvince.setText("");
                        btnDistrict.setText("");
                        province_code = viewHolder.id_location;
                        departmentDialog.dismiss();
                    }
                });
            }
        };
        recyclerView.setAdapter(firebaseRecyclerAdapter);

    }

    private void showLocations() {
        Query query = peruLocations.child("Departments").orderByChild("nombre_ubigeo");
        FirebaseRecyclerAdapter<PeruLocationsModel, LocationsViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<PeruLocationsModel, LocationsViewHolder>
                (PeruLocationsModel.class, R.layout.location_item, LocationsViewHolder.class, query) {
            @Override
            protected void populateViewHolder(final LocationsViewHolder viewHolder, PeruLocationsModel model, final int position) {
                viewHolder.setNombre_ubigeo(model.getNombre_ubigeo());
                viewHolder.setId_ubigeo(model.getId_ubigeo());

                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        btnDepartment.setText(viewHolder.location_name);
                        btnProvince.setText("");
                        btnDistrict.setText("");
                        province_code = viewHolder.id_location;
                        departmentDialog.dismiss();
                    }
                });
            }
        };
        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    public static class LocationsViewHolder extends RecyclerView.ViewHolder {
        View mView;
        String id_location,location_name;
        FirebaseAuth mAuth;
        DatabaseReference userRef;
        String currentUserID;

        public LocationsViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            mAuth = FirebaseAuth.getInstance();
            currentUserID = mAuth.getCurrentUser().getUid();
            userRef = FirebaseDatabase.getInstance().getReference().child("Users");
        }

        public void setNombre_ubigeo(String nombre_ubigeo) {
            TextView textView = mView.findViewById(R.id.txtName);
            textView.setText(nombre_ubigeo);
            location_name = nombre_ubigeo;
        }

        public void setId_ubigeo(String id_ubigeo) {
            id_location = id_ubigeo;
        }
    }
}
