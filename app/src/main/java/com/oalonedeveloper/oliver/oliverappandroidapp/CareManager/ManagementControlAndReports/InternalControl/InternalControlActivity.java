package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.ManagementControlAndReports.InternalControl;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.BlancedScoreCard.AnnualOperativePlan.AnnualOperativePlanActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import es.dmoral.toasty.Toasty;
import in.galaxyofandroid.spinerdialog.OnSpinerItemClick;
import in.galaxyofandroid.spinerdialog.SpinnerDialog;

public class InternalControlActivity extends AppCompatActivity {

    String post_key,date_format;
    DatabaseReference companyRef;
    ArrayList<String> arr_area =new ArrayList<>();
    SpinnerDialog areaDialog;
    RecyclerView recyclerView;
    Button btnAdd;
    int hour_int,minute_int,day_int,month_int,year_int;
    int day,month,year;
    Calendar calendar = Calendar.getInstance();
    long date_timestamp;
    RecyclerView recyclerView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internal_control);

        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");
        post_key = getIntent().getExtras().getString("post_key");

        recyclerView = findViewById(R.id.recyclerView);

        Date date= new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        day = cal.get(Calendar.DAY_OF_MONTH);
        month = cal.get(Calendar.MONTH)+1;
        year = cal.get(Calendar.YEAR);

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(false);
        linearLayoutManager.setStackFromEnd(false);
        recyclerView.setLayoutManager(linearLayoutManager);

        btnAdd = findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddDialog();
            }
        });

        showInternalControls();
    }

    private void showInternalControls() {
        Query query = companyRef.child(post_key).child("Internal Control").orderByChild("timestamp");
        FirebaseRecyclerAdapter<InternalControlModel, InternalControlViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<InternalControlModel, InternalControlViewHolder>
                (InternalControlModel.class,R.layout.internal_control_item,InternalControlViewHolder.class,query) {
            @Override
            protected void populateViewHolder(final InternalControlViewHolder viewHolder, final InternalControlModel model, int position) {
                final String postKey = getRef(position).getKey();
                viewHolder.setInternal_control_area(model.getInternal_control_area());
                viewHolder.setInternal_control_name(model.getInternal_control_name());
                viewHolder.setInternal_control_objective(model.getInternal_control_objective());

                viewHolder.txtName.setText(viewHolder.internal_control_name_st);
                viewHolder.txtArea.setText(viewHolder.internal_control_area_st);
                viewHolder.btnActionButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showActitivitesDialog();
                    }

                    private void showActitivitesDialog() {
                        final AlertDialog dialog = new AlertDialog.Builder(InternalControlActivity.this).create();

                        LayoutInflater inflater = LayoutInflater.from(InternalControlActivity.this);
                        View finance_method = inflater.inflate(R.layout.add_interal_control_activities_dialog,null);

                        TextView txtName;

                        Button btnAdd;

                        txtName = finance_method.findViewById(R.id.txtName);
                        recyclerView1 = finance_method.findViewById(R.id.recyclerView);
                        btnAdd = finance_method.findViewById(R.id.btnAdd);

                        txtName.setText(viewHolder.internal_control_name_st);
                        recyclerView1.setHasFixedSize(true);
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(InternalControlActivity.this);
                        linearLayoutManager.setReverseLayout(false);
                        linearLayoutManager.setStackFromEnd(false);
                        recyclerView1.setLayoutManager(linearLayoutManager);

                        showActivites();


                        btnAdd.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                showAddActivityDialog();
                            }

                            private void showAddActivityDialog() {
                                final AlertDialog dialog = new AlertDialog.Builder(InternalControlActivity.this).create();

                                LayoutInflater inflater = LayoutInflater.from(InternalControlActivity.this);
                                View finance_method = inflater.inflate(R.layout.add_activity_internal_control_dialog,null);

                                final EditText edtName;
                                final Button btnDate,btnDayStart,btnDayEnd,btnRegister;

                                edtName = finance_method.findViewById(R.id.edtName);
                                btnDate = finance_method.findViewById(R.id.btnDate);
                                btnDayStart = finance_method.findViewById(R.id.btnDayStart);
                                btnDayEnd = finance_method.findViewById(R.id.btnDayEnd);
                                btnRegister = finance_method.findViewById(R.id.btnRegister);

                                final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

                                    @Override
                                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                                                          int dayOfMonth) {
                                        // TODO Auto-generated method stub
                                        calendar.set(Calendar.YEAR, year);
                                        calendar.set(Calendar.MONTH, monthOfYear);
                                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                                        updateLabel();
                                    }

                                    private void updateLabel() {
                                        String myFormat = "dd/MM/yy"; //In which you need put here
                                        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.getDefault());

                                        String dateString = String.format("%d-%d-%d", calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                                        try {
                                            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
                                            String dayOfWeek = new SimpleDateFormat("EEEE", Locale.getDefault()).format(date);
                                            date_format = sdf.format(calendar.getTime());
                                            String month_name = new SimpleDateFormat("MMMM").format(calendar.getTime());
                                            btnDate.setText(dayOfWeek+" "+calendar.get(Calendar.DAY_OF_MONTH)+" de "+month_name+" de "+calendar.get(Calendar.YEAR));

                                            long output=date.getTime()/1000L;
                                            String str=Long.toString(output);
                                            date_timestamp = Long.parseLong(str) * 1000;

                                        } catch (ParseException e) {
                                            e.printStackTrace();
                                        }
                                    }

                                };

                                btnDate.setOnClickListener(new View.OnClickListener() {

                                    @Override
                                    public void onClick(View v) {
                                        // TODO Auto-generated method stub
                                        new DatePickerDialog(InternalControlActivity.this, date, calendar
                                                .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                                                calendar.get(Calendar.DAY_OF_MONTH)).show();
                                    }
                                });



                                btnDayStart.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        TimePickerDialog timePickerDialog = new TimePickerDialog(InternalControlActivity.this, new TimePickerDialog.OnTimeSetListener() {
                                            @Override
                                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                                hour_int = hourOfDay;
                                                minute_int = minute;

                                                Calendar calendar = Calendar.getInstance();

                                                calendar.set(0,0,0);

                                                btnDayStart.setText(hour_int+":"+minute_int);
                                            }
                                        },12,0,true);
                                        timePickerDialog.updateTime(hour_int,minute_int);
                                        timePickerDialog.show();
                                    }
                                });

                                btnDayEnd.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        TimePickerDialog timePickerDialog = new TimePickerDialog(InternalControlActivity.this, new TimePickerDialog.OnTimeSetListener() {
                                            @Override
                                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                                hour_int = hourOfDay;
                                                minute_int = minute;

                                                Calendar calendar = Calendar.getInstance();

                                                calendar.set(0,0,0);

                                                btnDayEnd.setText(hour_int+":"+minute_int);
                                            }
                                        },12,0,true);
                                        timePickerDialog.updateTime(hour_int,minute_int);
                                        timePickerDialog.show();
                                    }
                                });

                                btnRegister.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Long tsLong = System.currentTimeMillis()/1000;
                                        String timestamp = tsLong.toString();

                                        companyRef.child(post_key).child("Internal Control").child(postKey).child("Activities").child(timestamp).child("activity_name").setValue(edtName.getText().toString());
                                        companyRef.child(post_key).child("Internal Control").child(postKey).child("Activities").child(timestamp).child("text_date").setValue(btnDate.getText().toString());
                                        companyRef.child(post_key).child("Internal Control").child(postKey).child("Activities").child(timestamp).child("time_start").setValue(btnDayStart.getText().toString());
                                        companyRef.child(post_key).child("Internal Control").child(postKey).child("Activities").child(timestamp).child("time_end").setValue(btnDayEnd.getText().toString());
                                        companyRef.child(post_key).child("Internal Control").child(postKey).child("Activities").child(timestamp).child("date_timestamp").setValue(date_timestamp+"");
                                        companyRef.child(post_key).child("Internal Control").child(postKey).child("Activities").child(timestamp).child("timestamp").setValue(ServerValue.TIMESTAMP);
                                        Toasty.success(InternalControlActivity.this, "Registrado", Toast.LENGTH_LONG).show();
                                        dialog.dismiss();
                                    }
                                });

                                dialog.setView(finance_method);
                                dialog.show();

                            }
                        });

                        dialog.setView(finance_method);
                        dialog.show();

                    }

                    private void showActivites() {
                        Query query =  companyRef.child(post_key).child("Internal Control").child(postKey).child("Activities").orderByChild("timestamp");
                       FirebaseRecyclerAdapter<ActivitiesInternalControlModel, ActivitiesInternalControlViewHolder> firebaseRecyclerAdapter1 = new FirebaseRecyclerAdapter<ActivitiesInternalControlModel, ActivitiesInternalControlViewHolder>
                               (ActivitiesInternalControlModel.class, R.layout.internal_control_activity_item,ActivitiesInternalControlViewHolder.class,query) {
                           @Override
                           protected void populateViewHolder(ActivitiesInternalControlViewHolder viewHolder1, ActivitiesInternalControlModel model1, int i) {
                               viewHolder1.setActivity_name(model1.getActivity_name());
                               viewHolder1.setDate_timestamp(model1.getDate_timestamp());
                               viewHolder1.setText_date(model1.getText_date());
                               viewHolder1.setTime_end(model1.getTime_end());
                               viewHolder1.setTime_start(model1.getTime_start());

                               viewHolder1.txtDate.setText(viewHolder1.text_date_st);
                               viewHolder1.txtSchedule.setText(viewHolder1.time_start_st+" - "+viewHolder1.time_end_st);
                               viewHolder1.txtActivities.setText(viewHolder1.activity_name_st);

                           }
                       };
                       recyclerView1.setAdapter(firebaseRecyclerAdapter1);


                    }
                });

            }
        };
        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    private void showAddDialog() {
        final AlertDialog dialog = new AlertDialog.Builder(InternalControlActivity.this).create();

        LayoutInflater inflater = LayoutInflater.from(InternalControlActivity.this);
        View finance_method = inflater.inflate(R.layout.add_internal_control_dialog,null);

        final Button btnArea,btnFinish;
        final EditText edtName,edtObjectives;
        final LinearLayout rootLayout;

        btnArea = finance_method.findViewById(R.id.btnArea);
        edtName = finance_method.findViewById(R.id.edtName);
        edtObjectives = finance_method.findViewById(R.id.edtObjectives);
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

        areaDialog = new SpinnerDialog(InternalControlActivity.this,arr_area, "Selecciona el área de control");
        areaDialog.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String item2, int position2) {
                btnArea.setText(item2);

            }
        });

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(btnArea.getText().toString())) {
                    Snackbar.make(rootLayout, "Debes seleccionar el área", Snackbar.LENGTH_SHORT).show();
                } else  if (TextUtils.isEmpty(edtName.getText().toString())) {
                    Snackbar.make(rootLayout, "Debes completar el nombre", Snackbar.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(edtObjectives.getText().toString())) {
                    Snackbar.make(rootLayout, "Debes completar el objetivo", Snackbar.LENGTH_SHORT).show();
                } else {
                    Long tsLong = System.currentTimeMillis()/1000;
                    String timestamp = tsLong.toString();
                    companyRef.child(post_key).child("Internal Control").child(timestamp).child("internal_control_name").setValue(edtName.getText().toString());
                    companyRef.child(post_key).child("Internal Control").child(timestamp).child("internal_control_area").setValue(btnArea.getText().toString());
                    companyRef.child(post_key).child("Internal Control").child(timestamp).child("internal_control_objective").setValue(edtObjectives.getText().toString());
                    companyRef.child(post_key).child("Internal Control").child(timestamp).child("timestamp").setValue(ServerValue.TIMESTAMP);
                    Toasty.success(InternalControlActivity.this, "Registrado", Toast.LENGTH_LONG).show();
                    dialog.dismiss();
                }
            }
        });

        dialog.setView(finance_method);
        dialog.show();
    }

    public static class InternalControlViewHolder extends RecyclerView.ViewHolder {

        View mView;
        TextView txtName,txtArea;
        ImageView btnActionButton;

        String internal_control_name_st,internal_control_area_st,internal_control_objective_st;

        public InternalControlViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;

            txtName = mView.findViewById(R.id.txtName);
            txtArea = mView.findViewById(R.id.txtArea);
            btnActionButton = mView.findViewById(R.id.btnActionButton);
        }

        public void setInternal_control_name(String internal_control_name) {
            internal_control_name_st = internal_control_name;
        }

        public void setInternal_control_area(String internal_control_area) {
            internal_control_area_st = internal_control_area;
        }

        public void setInternal_control_objective(String internal_control_objective) {
            internal_control_objective_st = internal_control_objective;
        }
    }

    public static class ActivitiesInternalControlViewHolder extends RecyclerView.ViewHolder {

        View mView;
        String activity_name_st,text_date_st,time_start_st,time_end_st,date_timestamp_st;
        TextView txtDate,txtSchedule,txtActivities;

        public ActivitiesInternalControlViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;

            txtDate =mView.findViewById(R.id.txtDate);
            txtSchedule =mView.findViewById(R.id.txtSchedule);
            txtActivities =mView.findViewById(R.id.txtActivities);

        }
        public void setActivity_name(String activity_name) {
            activity_name_st =activity_name;
        }

        public void setText_date(String text_date) {
            text_date_st = text_date;
        }

        public void setTime_start(String time_start) {
            time_start_st = time_start;
        }

        public void setTime_end(String time_end) {
            time_end_st = time_end;
        }

        public void setDate_timestamp(String date_timestamp) {
            date_timestamp_st = date_timestamp;
        }
    }
}
