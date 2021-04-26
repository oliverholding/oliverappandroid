package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.PeopleManagement.RoasterAndPerfomance;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
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
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ServerValue;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.PeopleManagement.JobProfiles.JobProfilesActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import es.dmoral.toasty.Toasty;


public class WorkerRotativeScheduleFragment extends Fragment {

    Button btnRegister;
    int hour_int,minute_int,day_int,month_int,year_int;
    int day,month,year;
    Calendar calendar = Calendar.getInstance();
    Button btnDate,btnDayStart,btnDayEnd;
    DatabaseReference companyRef;
    String post_key,profile_id,date_format;
    long date_timestamp;
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_worker_rotative_schedule, container, false);

        btnRegister = view.findViewById(R.id.btnRegister);

        post_key = getActivity().getIntent().getExtras().getString("post_key");
        profile_id = getActivity().getIntent().getExtras().getString("profile_id");
        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");

        Date date= new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        day = cal.get(Calendar.DAY_OF_MONTH);
        month = cal.get(Calendar.MONTH)+1;
        year = cal.get(Calendar.YEAR);

        recyclerView = view.findViewById(R.id.recyclerView);

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        showDates();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog();
            }
        });
        return view;
    }

    private void showDates() {
        Query query = companyRef.child(post_key).child("Job Profile").child(profile_id).child("Schedules").child("Rotary").orderByChild("date_timestamp");
        FirebaseRecyclerAdapter<RotaryScheduleModel,RotaryScheduleViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<RotaryScheduleModel, RotaryScheduleViewHolder>
                (RotaryScheduleModel.class,R.layout.rotary_schedule_item,RotaryScheduleViewHolder.class,query) {
            @Override
            protected void populateViewHolder(RotaryScheduleViewHolder viewHolder, RotaryScheduleModel model, int i) {
                viewHolder.setDate(model.getDate());
                viewHolder.setText_date(model.getText_date());
                viewHolder.setTime_start(model.getTime_start());
                viewHolder.setTime_end(model.getTime_end());

                viewHolder.txtDayDetail.setText(viewHolder.my_text_date.toUpperCase());
                viewHolder.txtStartTime.setText(viewHolder.my_time_start);
                viewHolder.txtEndTime.setText(viewHolder.my_time_end);
            }
        };
        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    private void showDateDialog() {
        final AlertDialog dialog = new AlertDialog.Builder(getActivity()).create();

        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View finance_method = inflater.inflate(R.layout.set_rorative_schedule_dialog,null);

        final Button btnRegisterDate;

        btnDate = finance_method.findViewById(R.id.btnDate);
        btnDayStart = finance_method.findViewById(R.id.btnDayStart);
        btnDayEnd = finance_method.findViewById(R.id.btnDayEnd);
        btnRegisterDate = finance_method.findViewById(R.id.btnRegisterDate);

        final LinearLayout rootLayout = finance_method.findViewById(R.id.rootLayout);

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

        };

        btnDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(getActivity(), date, calendar
                        .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });



        btnDayStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
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
                TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
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

        btnRegisterDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(btnDate.getText().toString())) {
                    Snackbar.make(rootLayout,"Debes completar toda la información",Snackbar.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(btnDayStart.getText().toString())) {
                    Snackbar.make(rootLayout,"Debes completar toda la información",Snackbar.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(btnDayEnd.getText().toString())) {
                    Snackbar.make(rootLayout, "Debes completar toda la información", Snackbar.LENGTH_SHORT).show();
                } else {
                    Long tsLong = System.currentTimeMillis()/1000;
                    String timestamp = tsLong.toString();

                    DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");


                    companyRef.child(post_key).child("Job Profile").child(profile_id).child("Schedules").child("Rotary").child(timestamp).child("date").setValue(date_format);
                    companyRef.child(post_key).child("Job Profile").child(profile_id).child("Schedules").child("Rotary").child(timestamp).child("text_date").setValue(btnDate.getText().toString());
                    companyRef.child(post_key).child("Job Profile").child(profile_id).child("Schedules").child("Rotary").child(timestamp).child("time_start").setValue(btnDayStart.getText().toString());
                    companyRef.child(post_key).child("Job Profile").child(profile_id).child("Schedules").child("Rotary").child(timestamp).child("time_end").setValue(btnDayEnd.getText().toString());
                    companyRef.child(post_key).child("Job Profile").child(profile_id).child("Schedules").child("Rotary").child(timestamp).child("date_timestamp").setValue(date_timestamp);
                    companyRef.child(post_key).child("Job Profile").child(profile_id).child("Schedules").child("Rotary").child(timestamp).child("timestamp").setValue(ServerValue.TIMESTAMP);
                    Toasty.success(getActivity(), "Registrado", Toast.LENGTH_LONG).show();
                    dialog.dismiss();

                }
            }
        });

        dialog.setView(finance_method);
        dialog.show();

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

    public static class RotaryScheduleViewHolder extends RecyclerView.ViewHolder {
        View mView;
        TextView txtDayDetail,txtStartTime,txtEndTime;
        String my_text_date,my_time_end,my_time_start,my_date;

        public RotaryScheduleViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;

            txtDayDetail = mView.findViewById(R.id.txtDayDetail);
            txtStartTime = mView.findViewById(R.id.txtStartTime);
            txtEndTime = mView.findViewById(R.id.txtEndTime);
        }
        public void setText_date(String text_date) {
            my_text_date = text_date;
        }

        public void setTime_end(String time_end) {
            my_time_end = time_end;
        }


        public void setTime_start(String time_start) {
            my_time_start = time_start;
        }


        public void setDate(String date) {
            my_date = date;
        }
    }

}
