package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.PeopleManagement.RoasterAndPerfomance;

import android.app.TimePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TimePicker;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;

import java.util.Calendar;


public class WorkerFixedScheduleFragment extends Fragment{

    Button btnMondayStart,btnMondayEnd,btnTuesdayStart,btnTuesdayEnd,btnWednesdayStart,btnWednesdayEnd,btnThursdayStart,btnThursdayEnd,btnFridayStart,btnFridayEnd,btnSaturdayStart,btnSaturdayEnd,btnSundayStart,btnSundayEnd;
    int hour_int,minute_int;
    DatabaseReference companyRef;
    String post_key,profile_id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_worker_fixed_schedule, container, false);

        post_key = getActivity().getIntent().getExtras().getString("post_key");
        profile_id = getActivity().getIntent().getExtras().getString("profile_id");
        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");

        btnMondayStart = view.findViewById(R.id.btnMondayStart);
        btnMondayEnd = view.findViewById(R.id.btnMondayEnd);
        btnTuesdayStart = view.findViewById(R.id.btnTuesdayStart);
        btnTuesdayEnd = view.findViewById(R.id.btnTuesdayEnd);
        btnWednesdayStart = view.findViewById(R.id.btnWednesdayStart);
        btnWednesdayEnd = view.findViewById(R.id.btnWednesdayEnd);
        btnThursdayStart = view.findViewById(R.id.btnThursdayStart);
        btnThursdayEnd = view.findViewById(R.id.btnThursdayEnd);
        btnFridayStart = view.findViewById(R.id.btnFridayStart);
        btnFridayEnd = view.findViewById(R.id.btnFridayEnd);
        btnSaturdayStart = view.findViewById(R.id.btnSaturdayStart);
        btnSaturdayEnd = view.findViewById(R.id.btnSaturdayEnd);
        btnSundayStart = view.findViewById(R.id.btnSundayStart);
        btnSundayEnd = view.findViewById(R.id.btnSundayEnd);

        companyRef.child(post_key).child("Job Profile").child(profile_id).child("Schedules").child("Fixed").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    if (dataSnapshot.hasChild("monday_start")) {
                        String day_time = dataSnapshot.child("monday_start").getValue().toString();
                        btnMondayStart.setText(day_time);
                    }
                    if (dataSnapshot.hasChild("monday_end")) {
                        String day_time = dataSnapshot.child("monday_end").getValue().toString();
                        btnMondayEnd.setText(day_time);
                    }
                    if (dataSnapshot.hasChild("tuesday_start")) {
                        String day_time = dataSnapshot.child("tuesday_start").getValue().toString();
                        btnTuesdayStart.setText(day_time);
                    }
                    if (dataSnapshot.hasChild("tuesday_end")) {
                        String day_time = dataSnapshot.child("tuesday_end").getValue().toString();
                        btnTuesdayEnd.setText(day_time);
                    }
                    if (dataSnapshot.hasChild("wednesday_start")) {
                        String day_time = dataSnapshot.child("wednesday_start").getValue().toString();
                        btnWednesdayStart.setText(day_time);
                    }
                    if (dataSnapshot.hasChild("wednesday_end")) {
                        String day_time = dataSnapshot.child("wednesday_end").getValue().toString();
                        btnWednesdayEnd.setText(day_time);
                    }
                    if (dataSnapshot.hasChild("thursday_start")) {
                        String day_time = dataSnapshot.child("thursday_start").getValue().toString();
                        btnThursdayStart.setText(day_time);
                    }
                    if (dataSnapshot.hasChild("thursday_end")) {
                        String day_time = dataSnapshot.child("thursday_end").getValue().toString();
                        btnThursdayEnd.setText(day_time);
                    }
                    if (dataSnapshot.hasChild("friday_start")) {
                        String day_time = dataSnapshot.child("friday_start").getValue().toString();
                        btnFridayStart.setText(day_time);
                    }
                    if (dataSnapshot.hasChild("friday_end")) {
                        String day_time = dataSnapshot.child("friday_end").getValue().toString();
                        btnFridayEnd.setText(day_time);
                    }
                    if (dataSnapshot.hasChild("saturday_start")) {
                        String day_time = dataSnapshot.child("saturday_start").getValue().toString();
                        btnSaturdayStart.setText(day_time);
                    }
                    if (dataSnapshot.hasChild("saturday_end")) {
                        String day_time = dataSnapshot.child("saturday_end").getValue().toString();
                        btnSaturdayEnd.setText(day_time);
                    }
                    if (dataSnapshot.hasChild("sunday_start")) {
                        String day_time = dataSnapshot.child("sunday_start").getValue().toString();
                        btnSundayStart.setText(day_time);
                    }
                    if (dataSnapshot.hasChild("sunday_end")) {
                        String day_time = dataSnapshot.child("sunday_end").getValue().toString();
                        btnSundayEnd.setText(day_time);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        btnMondayStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        hour_int = hourOfDay;
                        minute_int = minute;

                        Calendar calendar = Calendar.getInstance();

                        calendar.set(0,0,0);

                        btnMondayStart.setText(hour_int+":"+minute_int);
                        companyRef.child(post_key).child("Job Profile").child(profile_id).child("Schedules").child("Fixed").child("monday_start").setValue(hour_int+":"+minute_int);
                    }
                },12,0,true);
                timePickerDialog.updateTime(hour_int,minute_int);
                timePickerDialog.show();
            }
        });

        btnMondayEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        hour_int = hourOfDay;
                        minute_int = minute;

                        Calendar calendar = Calendar.getInstance();

                        calendar.set(0,0,0);

                        btnMondayEnd.setText(hour_int+":"+minute_int);
                        companyRef.child(post_key).child("Job Profile").child(profile_id).child("Schedules").child("Fixed").child("monday_end").setValue(hour_int+":"+minute_int);
                    }
                },12,0,true);
                timePickerDialog.updateTime(hour_int,minute_int);
                timePickerDialog.show();
            }
        });

        btnTuesdayStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        hour_int = hourOfDay;
                        minute_int = minute;

                        Calendar calendar = Calendar.getInstance();

                        calendar.set(0,0,0);

                        btnTuesdayStart.setText(hour_int+":"+minute_int);
                        companyRef.child(post_key).child("Job Profile").child(profile_id).child("Schedules").child("Fixed").child("tuesday_start").setValue(hour_int+":"+minute_int);
                    }
                },12,0,true);
                timePickerDialog.updateTime(hour_int,minute_int);
                timePickerDialog.show();
            }
        });

        btnTuesdayEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        hour_int = hourOfDay;
                        minute_int = minute;

                        Calendar calendar = Calendar.getInstance();

                        calendar.set(0,0,0);

                        btnTuesdayEnd.setText(hour_int+":"+minute_int);
                        companyRef.child(post_key).child("Job Profile").child(profile_id).child("Schedules").child("Fixed").child("tuesday_end").setValue(hour_int+":"+minute_int);
                    }
                },12,0,true);
                timePickerDialog.updateTime(hour_int,minute_int);
                timePickerDialog.show();
            }
        });

        btnWednesdayStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        hour_int = hourOfDay;
                        minute_int = minute;

                        Calendar calendar = Calendar.getInstance();

                        calendar.set(0,0,0);

                        btnWednesdayStart.setText(hour_int+":"+minute_int);
                        companyRef.child(post_key).child("Job Profile").child(profile_id).child("Schedules").child("Fixed").child("wednesday_start").setValue(hour_int+":"+minute_int);
                    }
                },12,0,true);
                timePickerDialog.updateTime(hour_int,minute_int);
                timePickerDialog.show();
            }
        });

        btnWednesdayEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        hour_int = hourOfDay;
                        minute_int = minute;

                        Calendar calendar = Calendar.getInstance();

                        calendar.set(0,0,0);

                        btnWednesdayEnd.setText(hour_int+":"+minute_int);
                        companyRef.child(post_key).child("Job Profile").child(profile_id).child("Schedules").child("Fixed").child("wednesday_end").setValue(hour_int+":"+minute_int);
                    }
                },12,0,true);
                timePickerDialog.updateTime(hour_int,minute_int);
                timePickerDialog.show();
            }
        });

        btnThursdayStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        hour_int = hourOfDay;
                        minute_int = minute;

                        Calendar calendar = Calendar.getInstance();

                        calendar.set(0,0,0);

                        btnThursdayStart.setText(hour_int+":"+minute_int);
                        companyRef.child(post_key).child("Job Profile").child(profile_id).child("Schedules").child("Fixed").child("thursday_start").setValue(hour_int+":"+minute_int);
                    }
                },12,0,true);
                timePickerDialog.updateTime(hour_int,minute_int);
                timePickerDialog.show();
            }
        });

        btnThursdayEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        hour_int = hourOfDay;
                        minute_int = minute;

                        Calendar calendar = Calendar.getInstance();

                        calendar.set(0,0,0);

                        btnThursdayEnd.setText(hour_int+":"+minute_int);
                        companyRef.child(post_key).child("Job Profile").child(profile_id).child("Schedules").child("Fixed").child("thursday_end").setValue(hour_int+":"+minute_int);
                    }
                },12,0,true);
                timePickerDialog.updateTime(hour_int,minute_int);
                timePickerDialog.show();
            }
        });

        btnFridayStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        hour_int = hourOfDay;
                        minute_int = minute;

                        Calendar calendar = Calendar.getInstance();

                        calendar.set(0,0,0);

                        btnFridayStart.setText(hour_int+":"+minute_int);
                        companyRef.child(post_key).child("Job Profile").child(profile_id).child("Schedules").child("Fixed").child("friday_start").setValue(hour_int+":"+minute_int);
                    }
                },12,0,true);
                timePickerDialog.updateTime(hour_int,minute_int);
                timePickerDialog.show();
            }
        });

        btnFridayEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        hour_int = hourOfDay;
                        minute_int = minute;

                        Calendar calendar = Calendar.getInstance();

                        calendar.set(0,0,0);

                        btnFridayEnd.setText(hour_int+":"+minute_int);
                        companyRef.child(post_key).child("Job Profile").child(profile_id).child("Schedules").child("Fixed").child("friday_end").setValue(hour_int+":"+minute_int);
                    }
                },12,0,true);
                timePickerDialog.updateTime(hour_int,minute_int);
                timePickerDialog.show();
            }
        });

        btnSaturdayStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        hour_int = hourOfDay;
                        minute_int = minute;

                        Calendar calendar = Calendar.getInstance();

                        calendar.set(0,0,0);

                        btnSaturdayStart.setText(hour_int+":"+minute_int);
                        companyRef.child(post_key).child("Job Profile").child(profile_id).child("Schedules").child("Fixed").child("saturday_start").setValue(hour_int+":"+minute_int);
                    }
                },12,0,true);
                timePickerDialog.updateTime(hour_int,minute_int);
                timePickerDialog.show();
            }
        });

        btnSaturdayEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        hour_int = hourOfDay;
                        minute_int = minute;

                        Calendar calendar = Calendar.getInstance();

                        calendar.set(0,0,0);

                        btnSaturdayEnd.setText(hour_int+":"+minute_int);
                        companyRef.child(post_key).child("Job Profile").child(profile_id).child("Schedules").child("Fixed").child("saturday_end").setValue(hour_int+":"+minute_int);
                    }
                },12,0,true);
                timePickerDialog.updateTime(hour_int,minute_int);
                timePickerDialog.show();
            }
        });

        btnSundayStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        hour_int = hourOfDay;
                        minute_int = minute;

                        Calendar calendar = Calendar.getInstance();

                        calendar.set(0,0,0);

                        btnSundayStart.setText(hour_int+":"+minute_int);
                        companyRef.child(post_key).child("Job Profile").child(profile_id).child("Schedules").child("Fixed").child("sunday_start").setValue(hour_int+":"+minute_int);
                    }
                },12,0,true);
                timePickerDialog.updateTime(hour_int,minute_int);
                timePickerDialog.show();
            }
        });

        btnSundayEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        hour_int = hourOfDay;
                        minute_int = minute;

                        Calendar calendar = Calendar.getInstance();

                        calendar.set(0,0,0);

                        btnSundayEnd.setText(hour_int+":"+minute_int);
                        companyRef.child(post_key).child("Job Profile").child(profile_id).child("Schedules").child("Fixed").child("sunday_end").setValue(hour_int+":"+minute_int);
                    }
                },12,0,true);
                timePickerDialog.updateTime(hour_int,minute_int);
                timePickerDialog.show();
            }
        });

        return view;
    }

}
