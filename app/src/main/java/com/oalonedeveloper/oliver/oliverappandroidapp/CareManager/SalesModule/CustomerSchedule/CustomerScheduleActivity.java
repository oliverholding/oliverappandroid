package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.SalesModule.CustomerSchedule;

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
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;
import com.hbb20.CountryCodePicker;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Commercialization.BillsIssuing.CreateBillActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.Companies.CompanyCustomersModel;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import es.dmoral.toasty.Toasty;
import in.galaxyofandroid.spinerdialog.OnSpinerItemClick;
import in.galaxyofandroid.spinerdialog.SpinnerDialog;

public class CustomerScheduleActivity extends AppCompatActivity {

    Button btnScheduleDate;
    String post_key;
    DatabaseReference companyRef;
    AlertDialog dialog_customers;
    RecyclerView recyclerView,recyclerView2;
    String customer_name;

    ArrayList<String> bthDay =new ArrayList<>();
    SpinnerDialog bthDayDialog;

    ArrayList<String> bthMonth =new ArrayList<>();
    SpinnerDialog bthMonthDialog;

    ArrayList<String> bthYear =new ArrayList<>();
    SpinnerDialog bthYearDialog;

    ArrayList<String> bthHour =new ArrayList<>();
    SpinnerDialog bthHourDialog;

    ArrayList<String> bthMinute =new ArrayList<>();
    SpinnerDialog bthMinuterDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_schedule);

        btnScheduleDate = findViewById(R.id.btnScheduleDate);

        post_key = getIntent().getExtras().getString("post_key");
        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");

        btnScheduleDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomersDialog();
            }
        });

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        showCustomerSchedule();

    }

    private void showCustomerSchedule() {
        Query query = companyRef.child(post_key).child("Customer Schedules").orderByChild("timestamp");
        FirebaseRecyclerAdapter<CustomerScheduleModel,CustomerScheduleViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<CustomerScheduleModel, CustomerScheduleViewHolder>
                (CustomerScheduleModel.class, R.layout.customer_schedule_item, CustomerScheduleViewHolder.class,query) {
            @Override
            protected void populateViewHolder(CustomerScheduleViewHolder viewHolder, CustomerScheduleModel model, int position) {
                final String postKey = getRef(position).getKey();
                viewHolder.setDate(model.getDate());
                viewHolder.setCustomer_name(model.getCustomer_name());
                viewHolder.setTime(model.getTime());

                viewHolder.txtTime.setText(viewHolder.my_time);
                viewHolder.txtDate.setText(viewHolder.my_date);
                viewHolder.txtCustomerName.setText(viewHolder.my_customer_name);
            }
        };
        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    private void showCustomersDialog() {
        dialog_customers = new AlertDialog.Builder(CustomerScheduleActivity.this).create();

        LayoutInflater inflater = LayoutInflater.from(CustomerScheduleActivity.this);
        View finance_method = inflater.inflate(R.layout.customer_bill_dialog,null);

        Button btnAddCustomer;

        recyclerView2 = finance_method.findViewById(R.id.recyclerView2);
        btnAddCustomer = finance_method.findViewById(R.id.btnAddCustomer);
        recyclerView2.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(false);
        linearLayoutManager.setStackFromEnd(false);
        recyclerView2.setLayoutManager(linearLayoutManager);

        showCompanyCustomers();

        btnAddCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddCustomerDialog();
            }

            private void showAddCustomerDialog() {
                final AlertDialog dialog = new AlertDialog.Builder(CustomerScheduleActivity.this).create();

                LayoutInflater inflater = LayoutInflater.from(CustomerScheduleActivity.this);
                View finance_method = inflater.inflate(R.layout.add_customer_dialog,null);


                Button btnAddCustomer;
                TextView txtCancel;
                final EditText edtName,edtEmail,edtPhoneNumber;
                final CountryCodePicker ccpPhoneCode;
                final RadioButton rdPerson,rdCompany;

                btnAddCustomer = finance_method.findViewById(R.id.btnAddCustomer);
                txtCancel = finance_method.findViewById(R.id.txtCancel);
                edtName = finance_method.findViewById(R.id.edtName);
                edtEmail = finance_method.findViewById(R.id.edtEmail);
                edtPhoneNumber = finance_method.findViewById(R.id.edtPhoneNumber);
                ccpPhoneCode = finance_method.findViewById(R.id.ccpPhoneCode);
                rdPerson = finance_method.findViewById(R.id.rdPerson);
                rdCompany = finance_method.findViewById(R.id.rdCompany);

                btnAddCustomer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Calendar calForDate = Calendar.getInstance();
                        SimpleDateFormat currentDate = new SimpleDateFormat("dd-MM-yyyy");
                        String saveCurrentDate = currentDate.format(calForDate.getTime());

                        Calendar calForTime = Calendar.getInstance();
                        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss");
                        String saveCurrentTime = currentTime.format(calForTime.getTime());

                        String postRandomName = saveCurrentDate + saveCurrentTime;


                        HashMap hashMap = new HashMap();
                        hashMap.put("customer_name", edtName.getText().toString());
                        hashMap.put("customer_email", edtEmail.getText().toString());
                        hashMap.put("customer_phone", ccpPhoneCode.getSelectedCountryCode()+edtPhoneNumber.getText().toString());
                        if (rdPerson.isChecked()) {
                            hashMap.put("customer_type", rdPerson.getText().toString());
                        }
                        else if (rdCompany.isChecked()) {
                            hashMap.put("customer_type", rdCompany.getText().toString());
                        }
                        hashMap.put("register_date", saveCurrentDate);
                        hashMap.put("register_time", saveCurrentTime);
                        companyRef.child(post_key).child("Customers").child(postRandomName).updateChildren(hashMap).addOnCompleteListener(new OnCompleteListener() {
                            @Override
                            public void onComplete(@NonNull Task task) {
                                companyRef.child(post_key).child("Achievements").child("Module2 Add Customer").child("score").setValue("50");
                                companyRef.child(post_key).child("Achievements").child("Module2 Add Customer").child("message").setValue("Has registrado satisfactoriamente a tu primer cliente");
                                companyRef.child(post_key).child("Achievements").child("Module2 Add Customer").child("timestamp").setValue(ServerValue.TIMESTAMP);
                                Toasty.success(CustomerScheduleActivity.this, "Registrado", Toast.LENGTH_LONG).show();
                                dialog.dismiss();
                            }
                        });

                    }
                });

                txtCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.setView(finance_method);
                dialog.show();
            }
        });

        dialog_customers.setView(finance_method);
        dialog_customers.show();

    }

    private void showCompanyCustomers() {
        Query query = companyRef.child(post_key).child("Customers").orderByChild("customer_type");
        FirebaseRecyclerAdapter<CompanyCustomersModel, CompanyCustomersViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<CompanyCustomersModel, CompanyCustomersViewHolder>
                (CompanyCustomersModel.class, R.layout.customer_bill_item, CompanyCustomersViewHolder.class, query) {
            @Override
            protected void populateViewHolder(final CompanyCustomersViewHolder viewHolder, CompanyCustomersModel model, final int position) {
                final String postKey = getRef(position).getKey();
                viewHolder.setCustomer_email(model.getCustomer_email());
                viewHolder.setCustomer_name(model.getCustomer_name());
                viewHolder.setCustomer_phone(model.getCustomer_phone());
                viewHolder.setCustomer_type(model.getCustomer_type());

                viewHolder.txtName.setText(viewHolder.name);
                viewHolder.txtPhone.setText("Teléfono: "+viewHolder.phone);
                viewHolder.txtEmail.setText("Correo: "+viewHolder.email);
                viewHolder.txtType.setText("Tipo: "+viewHolder.type);

                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        companyRef.child(post_key).child("Customers").child(postKey).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                customer_name = dataSnapshot.child("customer_name").getValue().toString();
                                setDateTimeDialog();

                            }

                            private void setDateTimeDialog() {

                                final AlertDialog dialog = new AlertDialog.Builder(CustomerScheduleActivity.this).create();

                                LayoutInflater inflater = LayoutInflater.from(CustomerScheduleActivity.this);
                                View finance_method = inflater.inflate(R.layout.schdule_set_date_time_dialog,null);

                                final Button btnDay,btnMonth,btnYear,btnHour,btnMinute,btnRegister;
                                final RelativeLayout rootLayout;

                                btnDay = finance_method.findViewById(R.id.btnDay);
                                btnMonth = finance_method.findViewById(R.id.btnMonth);
                                btnYear = finance_method.findViewById(R.id.btnYear);
                                btnHour = finance_method.findViewById(R.id.btnHour);
                                btnMinute = finance_method.findViewById(R.id.btnMinute);
                                btnRegister = finance_method.findViewById(R.id.btnRegister);
                                rootLayout = finance_method.findViewById(R.id.rootLayout);

                                bthDay.add("1"); bthDay.add("2"); bthDay.add("3"); bthDay.add("4"); bthDay.add("5"); bthDay.add("6"); bthDay.add("7"); bthDay.add("8"); bthDay.add("9"); bthDay.add("10");
                                bthDay.add("11"); bthDay.add("12"); bthDay.add("13"); bthDay.add("14"); bthDay.add("15"); bthDay.add("16"); bthDay.add("17"); bthDay.add("18"); bthDay.add("19"); bthDay.add("20");
                                bthDay.add("21"); bthDay.add("22"); bthDay.add("23"); bthDay.add("24"); bthDay.add("25"); bthDay.add("26"); bthDay.add("27"); bthDay.add("28"); bthDay.add("29"); bthDay.add("30");
                                bthDay.add("31");

                                btnDay.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        bthDayDialog.showSpinerDialog();
                                    }
                                });

                                bthDayDialog = new SpinnerDialog(CustomerScheduleActivity.this,bthDay, "Selecciona el Día de la cita");
                                bthDayDialog.bindOnSpinerListener(new OnSpinerItemClick() {
                                    @Override
                                    public void onClick(String item2, int position2) {
                                        btnDay.setText(item2);
                                    }
                                });

                                bthMonth.add("1");bthMonth.add("2");bthMonth.add("3");bthMonth.add("4");bthMonth.add("5");bthMonth.add("6");bthMonth.add("7");bthMonth.add("8");bthMonth.add("9");bthMonth.add("10");
                                bthMonth.add("11");bthMonth.add("12");

                                btnMonth.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        bthMonthDialog.showSpinerDialog();
                                    }
                                });

                                bthMonthDialog = new SpinnerDialog(CustomerScheduleActivity.this,bthMonth, "Selecciona el Mes de la Cita");
                                bthMonthDialog.bindOnSpinerListener(new OnSpinerItemClick() {
                                    @Override
                                    public void onClick(String item2, int position2) {
                                        btnMonth.setText(item2);
                                    }
                                });

                                bthYear.add("2020");bthYear.add("2021");bthYear.add("2022");bthYear.add("2023");bthYear.add("2024");bthYear.add("2025");bthYear.add("2026");bthYear.add("2027");bthYear.add("2028");bthYear.add("2029");
                                bthYear.add("2030");

                                btnYear.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        bthYearDialog.showSpinerDialog();
                                    }
                                });

                                bthYearDialog = new SpinnerDialog(CustomerScheduleActivity.this,bthYear, "Selecciona el Año de Vencimiento");
                                bthYearDialog.bindOnSpinerListener(new OnSpinerItemClick() {
                                    @Override
                                    public void onClick(String item2, int position2) {
                                        btnYear.setText(item2);
                                    }
                                });


                                bthHour.add("01");bthHour.add("02");bthHour.add("03");bthHour.add("04");bthHour.add("05");bthHour.add("06");bthHour.add("07");bthHour.add("08");bthHour.add("09");
                                bthHour.add("10");bthHour.add("11");bthHour.add("12");bthHour.add("13");bthHour.add("14");bthHour.add("15");bthHour.add("16");bthHour.add("17");bthHour.add("18");bthHour.add("19");
                                bthHour.add("20");bthHour.add("21");bthHour.add("22");bthHour.add("23");bthHour.add("00");

                                btnHour.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        bthHourDialog.showSpinerDialog();
                                    }
                                });

                                bthHourDialog = new SpinnerDialog(CustomerScheduleActivity.this,bthHour, "Selecciona la hora de la cita");
                                bthHourDialog.bindOnSpinerListener(new OnSpinerItemClick() {
                                    @Override
                                    public void onClick(String item2, int position2) {
                                        btnHour.setText(item2);
                                    }
                                });

                                bthMinute.add("00");bthMinute.add("01");bthMinute.add("02");bthMinute.add("03");bthMinute.add("04");bthMinute.add("05");bthMinute.add("06");bthMinute.add("07");bthMinute.add("08");bthMinute.add("09");
                                bthMinute.add("10");bthMinute.add("11");bthMinute.add("12");bthMinute.add("13");bthMinute.add("14");bthMinute.add("15");bthMinute.add("16");bthMinute.add("17");bthMinute.add("18");bthMinute.add("19");
                                bthMinute.add("20");bthMinute.add("21");bthMinute.add("22");bthMinute.add("23");bthMinute.add("24");bthMinute.add("25");bthMinute.add("26");bthMinute.add("27");bthMinute.add("28");bthMinute.add("29");
                                bthMinute.add("30");bthMinute.add("31");bthMinute.add("32");bthMinute.add("33");bthMinute.add("34");bthMinute.add("35");bthMinute.add("36");bthMinute.add("37");bthMinute.add("38");bthMinute.add("39");
                                bthMinute.add("40");bthMinute.add("41");bthMinute.add("42");bthMinute.add("43");bthMinute.add("44");bthMinute.add("45");bthMinute.add("46");bthMinute.add("47");bthMinute.add("48");bthMinute.add("49");
                                bthMinute.add("50");bthMinute.add("51");bthMinute.add("52");bthMinute.add("53");bthMinute.add("54");bthMinute.add("55");bthMinute.add("56");bthMinute.add("57");bthMinute.add("58");bthMinute.add("59");

                                btnMinute.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        bthMinuterDialog.showSpinerDialog();
                                    }
                                });

                                bthMinuterDialog = new SpinnerDialog(CustomerScheduleActivity.this,bthMinute, "Selecciona el minuto de la cita");
                                bthMinuterDialog.bindOnSpinerListener(new OnSpinerItemClick() {
                                    @Override
                                    public void onClick(String item2, int position2) {
                                        btnMinute.setText(item2);
                                    }
                                });

                                btnRegister.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if (TextUtils.isEmpty(btnDay.getText().toString())) {
                                            Snackbar.make(rootLayout, "Debes ingresar el día de la cita", Snackbar.LENGTH_LONG).show();
                                        }
                                        else if (TextUtils.isEmpty(btnMonth.getText().toString())) {
                                            Snackbar.make(rootLayout, "Debes ingresar el mes de la cita", Snackbar.LENGTH_LONG).show();
                                        }
                                        else if (TextUtils.isEmpty(btnYear.getText().toString())) {
                                            Snackbar.make(rootLayout, "Debes ingresar el año de la cita", Snackbar.LENGTH_LONG).show();
                                        }
                                        else if (TextUtils.isEmpty(btnHour.getText().toString())) {
                                            Snackbar.make(rootLayout, "Debes ingresar la hora de la cita", Snackbar.LENGTH_LONG).show();
                                        } else if (TextUtils.isEmpty(btnMinute.getText().toString())) {
                                            Snackbar.make(rootLayout, "Debes ingresar los minutos de la cita", Snackbar.LENGTH_LONG).show();
                                        } else {
                                            Long tsLong = System.currentTimeMillis()/1000;
                                            String timestamp = tsLong.toString();
                                            companyRef.child(post_key).child("Customer Schedules").child(timestamp).child("date").setValue(btnDay.getText().toString()+"/"+btnMonth.getText().toString()+"/"+btnYear.getText().toString());
                                            companyRef.child(post_key).child("Customer Schedules").child(timestamp).child("time").setValue(btnHour.getText().toString()+":"+btnMinute.getText().toString());
                                            companyRef.child(post_key).child("Customer Schedules").child(timestamp).child("customer_name").setValue(customer_name);
                                            companyRef.child(post_key).child("Customer Schedules").child(timestamp).child("customer_id").setValue(postKey);
                                            companyRef.child(post_key).child("Customer Schedules").child(timestamp).child("schedule_state").setValue("pending");
                                            companyRef.child(post_key).child("Customer Schedules").child(timestamp).child("timestamp").setValue(ServerValue.TIMESTAMP);
                                            Toasty.success(CustomerScheduleActivity.this, "Agendado", Toast.LENGTH_LONG).show();
                                            dialog.dismiss();
                                            dialog_customers.dismiss();
                                        }
                                    }
                                });

                                dialog.setView(finance_method);
                                dialog.show();

                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                    }
                });

            }
        };
        recyclerView2.setAdapter(firebaseRecyclerAdapter);
    }

    public static class CompanyCustomersViewHolder extends RecyclerView.ViewHolder {
        View mView;
        TextView txtName,txtPhone,txtEmail,txtType;
        String email,name,phone,type;

        public CompanyCustomersViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;

            txtName = mView.findViewById(R.id.txtName);
            txtPhone = mView.findViewById(R.id.txtPhone);
            txtEmail = mView.findViewById(R.id.txtEmail);
            txtType = mView.findViewById(R.id.txtType);
        }
        public void setCustomer_email(String customer_email) {
            email = customer_email;
        }

        public void setCustomer_name(String customer_name) {
            name = customer_name;
        }


        public void setCustomer_phone(String customer_phone) {
            phone = customer_phone;
        }


        public void setCustomer_type(String customer_type) {
            type = customer_type;
        }
    }

    public static class CustomerScheduleViewHolder extends RecyclerView.ViewHolder {
        View mView;
        TextView txtDate,txtCustomerName,txtTime;
        String my_date,my_time,my_customer_name;

        public CustomerScheduleViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;

            txtDate = mView.findViewById(R.id.txtDate);
            txtCustomerName = mView.findViewById(R.id.txtCustomerName);
            txtTime = mView.findViewById(R.id.txtTime);
        }
        public void setDate(String date) {
            my_date = date;
        }

        public void setTime(String time) {
            my_time = time;
        }

        public void setCustomer_name(String customer_name) {
            my_customer_name = customer_name;
        }
        public void setCustomer_id(String customer_id) {

        }

        public void setSchedule_state(String schedule_state) {

        }
    }
}
