package com.oalonedeveloper.oliver.oliverappandroidapp.Companies;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.Bundle;

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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.oalonedeveloper.oliver.oliverappandroidapp.Locations.PeruLocationsModel;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;

import java.util.ArrayList;

import in.galaxyofandroid.spinerdialog.OnSpinerItemClick;
import in.galaxyofandroid.spinerdialog.SpinnerDialog;

public class RegisterCompanyData2Fragment extends Fragment {

    EditText edtCommercialName,edtDocumentNumber;
    Button edtBthDay,edtBthMonth,edtBthYear;
    FirebaseAuth mAuth;
    DatabaseReference userRef,ratesRef,peruLocations,economic_activities;
    String currentUserID,province_code;
    RelativeLayout rootLayout;
    ProgressDialog loadingBar;

    ArrayList<String> bthDay =new ArrayList<>();
    SpinnerDialog bthDayDialog;

    ArrayList<String> bthMonth =new ArrayList<>();
    SpinnerDialog bthMonthDialog;

    ArrayList<String> bthYear =new ArrayList<>();
    SpinnerDialog bthYearDialog;

    RecyclerView recyclerView;
    EditText edtSearch;
    AlertDialog departmentDialog;
    Button btnDepartment,btnDistrict,btnProvince,btnEconomicActivities;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register_company_data2, container, false);

        edtCommercialName = view.findViewById(R.id.edtCommercialName);
        edtDocumentNumber = view.findViewById(R.id.edtDocumentNumber);
        edtBthDay = view.findViewById(R.id.edtBthDay);
        edtBthMonth = view.findViewById(R.id.edtBthMonth);
        edtBthYear = view.findViewById(R.id.edtBthYear);
        btnDepartment = view.findViewById(R.id.btnDepartment);
        btnProvince = view.findViewById(R.id.btnProvince);
        btnDistrict = view.findViewById(R.id.btnDistrict);
        btnEconomicActivities = view.findViewById(R.id.btnEconomicActivities);

        loadingBar = new ProgressDialog(getActivity());

        mAuth = FirebaseAuth.getInstance();
        currentUserID = mAuth.getCurrentUser().getUid();
        userRef = FirebaseDatabase.getInstance().getReference().child("Users").child(currentUserID).child("Company Registration");
        ratesRef = FirebaseDatabase.getInstance().getReference().child("Rates");
        peruLocations= FirebaseDatabase.getInstance().getReference().child("Peru Locations");
        economic_activities = FirebaseDatabase.getInstance().getReference().child("Economic Activities");

        loadingBar.setTitle("Preparando todo...");
        loadingBar.setMessage("Cargando...");
        loadingBar.show();
        loadingBar.setCanceledOnTouchOutside(false);
        loadingBar.setCancelable(false);

        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild("commercial_name")) {
                    String commercial_name = dataSnapshot.child("commercial_name").getValue().toString();
                    edtCommercialName.setText(commercial_name);
                }
                if (dataSnapshot.hasChild("company_document_number")) {
                    String company_document_number = dataSnapshot.child("company_document_number").getValue().toString();
                    edtDocumentNumber.setText(company_document_number);
                }
                if (dataSnapshot.hasChild("company_bth_day")) {
                    String company_bth_day = dataSnapshot.child("company_bth_day").getValue().toString();
                    edtBthDay.setText(company_bth_day);
                }
                if (dataSnapshot.hasChild("company_bth_month")) {
                    String company_bth_month = dataSnapshot.child("company_bth_month").getValue().toString();
                    edtBthMonth.setText(company_bth_month);
                }
                if (dataSnapshot.hasChild("company_bth_year")) {
                    String company_bth_year = dataSnapshot.child("company_bth_year").getValue().toString();
                    edtBthYear.setText(company_bth_year);
                }
                if (dataSnapshot.hasChild("department")) {
                    String department = dataSnapshot.child("department").getValue().toString();
                    btnDepartment.setText(department);
                }
                if (dataSnapshot.hasChild("province")) {
                    String province = dataSnapshot.child("province").getValue().toString();
                    btnProvince.setText(province);
                }
                if (dataSnapshot.hasChild("district")) {
                    String district = dataSnapshot.child("district").getValue().toString();
                    btnDistrict.setText(district);
                }
                if (dataSnapshot.hasChild("economic_activity")) {
                    String economic_activity = dataSnapshot.child("economic_activity").getValue().toString();
                    btnEconomicActivities.setText(economic_activity);
                }
                loadingBar.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        edtCommercialName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!TextUtils.isEmpty(edtCommercialName.getText().toString())) {
                    userRef.child("commercial_name").setValue(edtCommercialName.getText().toString());
                } else {
                    userRef.child("commercial_name").removeValue();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        edtDocumentNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!TextUtils.isEmpty(edtDocumentNumber.getText().toString())) {
                    userRef.child("company_document_number").setValue(edtDocumentNumber.getText().toString());
                } else {
                    userRef.child("company_document_number").removeValue();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        bthDay.add("01"); bthDay.add("02"); bthDay.add("03"); bthDay.add("04"); bthDay.add("05"); bthDay.add("06"); bthDay.add("07"); bthDay.add("08"); bthDay.add("09"); bthDay.add("10");
        bthDay.add("11"); bthDay.add("12"); bthDay.add("13"); bthDay.add("14"); bthDay.add("15"); bthDay.add("16"); bthDay.add("17"); bthDay.add("18"); bthDay.add("19"); bthDay.add("20");
        bthDay.add("21"); bthDay.add("22"); bthDay.add("23"); bthDay.add("24"); bthDay.add("25"); bthDay.add("26"); bthDay.add("27"); bthDay.add("28"); bthDay.add("29"); bthDay.add("30");
        bthDay.add("31");

        edtBthDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bthDayDialog.showSpinerDialog();
            }
        });

        bthDayDialog = new SpinnerDialog(getActivity(),bthDay, "Selecciona el Día de Inscripción");
        bthDayDialog.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String item2, int position2) {
                edtBthDay.setText(item2);
                userRef.child("company_bth_day").setValue(item2);
            }
        });

        bthMonth.add("01");bthMonth.add("02");bthMonth.add("03");bthMonth.add("04");bthMonth.add("05");bthMonth.add("06");bthMonth.add("07");bthMonth.add("08");bthMonth.add("09");bthMonth.add("10");
        bthMonth.add("11");bthMonth.add("12");

        edtBthMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bthMonthDialog.showSpinerDialog();
            }
        });

        bthMonthDialog = new SpinnerDialog(getActivity(),bthMonth, "Selecciona el Mes de Inscripción");
        bthMonthDialog.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String item2, int position2) {
                edtBthMonth.setText(item2);
                userRef.child("company_bth_month").setValue(item2);
            }
        });

        bthYear.add("2020");bthYear.add("2019");bthYear.add("2018");bthYear.add("2017");bthYear.add("2016");bthYear.add("2015");bthYear.add("2014");bthYear.add("2013");bthYear.add("2012");bthYear.add("2011");bthYear.add("2010");
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

        bthYearDialog = new SpinnerDialog(getActivity(),bthYear, "Selecciona el Año de Inscripción");
        bthYearDialog.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String item2, int position2) {
                edtBthYear.setText(item2);
                userRef.child("company_bth_year").setValue(item2);
            }
        });

        btnDepartment.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                userRef.child("department").setValue(btnDepartment.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        btnProvince.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                userRef.child("province").setValue(btnProvince.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        btnDistrict.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                userRef.child("district").setValue(btnDistrict.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

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

        btnEconomicActivities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showEconomicActtivitiesDialog();
            }
        });


        return view;
    }

    private void showEconomicActtivitiesDialog() {
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

        showEconomicActivities();

        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                searchEconomicActivities();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });




        departmentDialog.setView(finance_method);
        departmentDialog.show();
    }

    private void searchEconomicActivities() {
        Query query = economic_activities.orderByChild("name").startAt(edtSearch.getText().toString().toUpperCase()).endAt(edtSearch.getText().toString().toUpperCase()+"\uf8ff");
        FirebaseRecyclerAdapter<EconomicActivitiesModel, EconomicActivitiesViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<EconomicActivitiesModel, EconomicActivitiesViewHolder>
                (EconomicActivitiesModel.class, R.layout.location_item, EconomicActivitiesViewHolder.class, query) {
            @Override
            protected void populateViewHolder(final EconomicActivitiesViewHolder viewHolder, EconomicActivitiesModel model, final int position) {
                viewHolder.setName(model.getName());

                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        btnEconomicActivities.setText(viewHolder.act_name);
                        userRef.child("economic_activity").setValue(viewHolder.act_name);
                        departmentDialog.dismiss();
                    }
                });
            }
        };
        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    private void showEconomicActivities() {
        Query query = economic_activities.orderByValue();
        FirebaseRecyclerAdapter<EconomicActivitiesModel, EconomicActivitiesViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<EconomicActivitiesModel, EconomicActivitiesViewHolder>
                (EconomicActivitiesModel.class, R.layout.location_item, EconomicActivitiesViewHolder.class, query) {
            @Override
            protected void populateViewHolder(final EconomicActivitiesViewHolder viewHolder, EconomicActivitiesModel model, final int position) {
                final String postKey = getRef(position).getKey();
                viewHolder.setName(model.getName());

                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        btnEconomicActivities.setText(viewHolder.act_name);
                        userRef.child("economic_activity").setValue(viewHolder.act_name);
                        departmentDialog.dismiss();
                    }
                });
            }
        };
        recyclerView.setAdapter(firebaseRecyclerAdapter);
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
                        userRef.child(currentUserID).child("district").setValue(viewHolder.location_name);
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
                        userRef.child(currentUserID).child("province").setValue(viewHolder.location_name);
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

    public static class EconomicActivitiesViewHolder extends RecyclerView.ViewHolder {
        View mView;
        TextView txtName;
        String act_name;

        public EconomicActivitiesViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            txtName = mView.findViewById(R.id.txtName);
        }
        public void setName(String name) {
            TextView textView = mView.findViewById(R.id.txtName);
            textView.setText(name);
            act_name = name;
        }


    }
}