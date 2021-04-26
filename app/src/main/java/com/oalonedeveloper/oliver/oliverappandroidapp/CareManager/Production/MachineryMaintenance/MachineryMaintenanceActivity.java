package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Production.MachineryMaintenance;

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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ServerValue;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Production.ProductionOrders.ProductionOrderProductDetailActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import in.galaxyofandroid.spinerdialog.OnSpinerItemClick;
import in.galaxyofandroid.spinerdialog.SpinnerDialog;

public class MachineryMaintenanceActivity extends AppCompatActivity {

    ImageView btnAddMaintenance;
    RecyclerView recyclerView1;
    DatabaseReference companyRef;
    String post_key;

    ArrayList<String> bthDay =new ArrayList<>();
    SpinnerDialog bthDayDialog;

    ArrayList<String> bthMonth =new ArrayList<>();
    SpinnerDialog bthMonthDialog;

    ArrayList<String> bthYear =new ArrayList<>();
    SpinnerDialog bthYearDialog;

    int day,month,year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_machinery_maintenance);

        post_key = getIntent().getExtras().getString("post_key");
        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");

        btnAddMaintenance = findViewById(R.id.btnAddMaintenance);

        recyclerView1 = findViewById(R.id.recyclerView1);

        Date date= new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        day = cal.get(Calendar.DAY_OF_MONTH);
        month = cal.get(Calendar.MONTH)+1;
        year = cal.get(Calendar.YEAR);

        recyclerView1.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView1.setLayoutManager(linearLayoutManager);
        showMaintenance();

        btnAddMaintenance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog dialog = new AlertDialog.Builder(MachineryMaintenanceActivity.this).create();

                LayoutInflater inflater = LayoutInflater.from(MachineryMaintenanceActivity.this);
                View finance_method = inflater.inflate(R.layout.add_machinery_maintenance_dialog,null);

                final EditText edtConcept,edtAsset;
                final Button edtBthDay,edtBthMonth,edtBthYear,btnRegister;
                final LinearLayout rootLayout;

                edtConcept = finance_method.findViewById(R.id.edtConcept);
                edtAsset = finance_method.findViewById(R.id.edtAsset);
                edtBthDay = finance_method.findViewById(R.id.edtBthDay);
                edtBthMonth = finance_method.findViewById(R.id.edtBthMonth);
                edtBthYear = finance_method.findViewById(R.id.edtBthYear);
                btnRegister = finance_method.findViewById(R.id.btnRegister);
                rootLayout = finance_method.findViewById(R.id.rootLayout);

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

                bthDayDialog = new SpinnerDialog(MachineryMaintenanceActivity.this,bthDay, "Selecciona tu Día de Nacimiento");
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

                bthMonthDialog = new SpinnerDialog(MachineryMaintenanceActivity.this,bthMonth, "Selecciona tu Mes de Nacimiento");
                bthMonthDialog.bindOnSpinerListener(new OnSpinerItemClick() {
                    @Override
                    public void onClick(String item2, int position2) {
                        edtBthMonth.setText(item2);

                    }
                });

                bthYear.add("2021");bthYear.add("2022");bthYear.add("2023");

                edtBthYear.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bthYearDialog.showSpinerDialog();
                    }
                });

                bthYearDialog = new SpinnerDialog(MachineryMaintenanceActivity.this,bthYear, "Selecciona tu Año de Nacimiento");
                bthYearDialog.bindOnSpinerListener(new OnSpinerItemClick() {
                    @Override
                    public void onClick(String item2, int position2) {
                        edtBthYear.setText(item2);

                    }
                });

                btnRegister.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (TextUtils.isEmpty(edtConcept.getText().toString())) {
                            Snackbar.make(rootLayout,"Debes inresar el concepto",Snackbar.LENGTH_SHORT).show();
                        }
                        else  if (TextUtils.isEmpty(edtAsset.getText().toString())) {
                            Snackbar.make(rootLayout,"Debes inresar el activo",Snackbar.LENGTH_SHORT).show();
                        }
                        else  if (TextUtils.isEmpty(edtBthDay.getText().toString())) {
                            Snackbar.make(rootLayout,"Debes inresar el dia",Snackbar.LENGTH_SHORT).show();
                        }
                        else  if (TextUtils.isEmpty(edtBthMonth.getText().toString())) {
                            Snackbar.make(rootLayout,"Debes inresar el mes",Snackbar.LENGTH_SHORT).show();
                        } else if (TextUtils.isEmpty(edtBthYear.getText().toString())) {
                            Snackbar.make(rootLayout, "Debes inresar el año", Snackbar.LENGTH_SHORT).show();
                        } else {
                            Long tsLong = System.currentTimeMillis()/1000;
                            final String timestamp = tsLong.toString();

                            companyRef.child(post_key).child("Machinery Maintenance").child(timestamp).child("concept").setValue(edtConcept.getText().toString());
                            companyRef.child(post_key).child("Machinery Maintenance").child(timestamp).child("asset").setValue(edtAsset.getText().toString());
                            companyRef.child(post_key).child("Machinery Maintenance").child(timestamp).child("day").setValue(edtBthDay.getText().toString());
                            companyRef.child(post_key).child("Machinery Maintenance").child(timestamp).child("month").setValue(edtBthMonth.getText().toString());
                            companyRef.child(post_key).child("Machinery Maintenance").child(timestamp).child("year").setValue(edtBthYear.getText().toString());
                            companyRef.child(post_key).child("Machinery Maintenance").child(timestamp).child("timestamp").setValue(ServerValue.TIMESTAMP);

                            dialog.dismiss();
                        }
                    }
                });

                dialog.setView(finance_method);
                dialog.show();
            }
        });

    }

    private void showMaintenance() {
        Query query = companyRef.child(post_key).child("Machinery Maintenance").orderByChild("month").equalTo("1");
        FirebaseRecyclerAdapter<MachineryMaintenanceModel, MachineryMaintenanceViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<MachineryMaintenanceModel, MachineryMaintenanceViewHolder>
                (MachineryMaintenanceModel.class,R.layout.machinery_maintenance_item,MachineryMaintenanceViewHolder.class,query) {
            @Override
            protected void populateViewHolder(MachineryMaintenanceViewHolder viewHolder, MachineryMaintenanceModel model, int position) {
                viewHolder.setAsset(model.getAsset());
                viewHolder.setConcept(model.getConcept());
                viewHolder.setDay(model.getDay());
                viewHolder.setMonth(model.getMonth());
                viewHolder.setYear(model.getYear());

                viewHolder.txtDate.setText(viewHolder.my_day+"/"+viewHolder.my_month+"/"+viewHolder.my_year);
                viewHolder.txtAsset.setText(viewHolder.my_asset);
                viewHolder.txtConcept.setText(viewHolder.my_concept);
            }
        };
        recyclerView1.setAdapter(firebaseRecyclerAdapter);
    }

    public static class MachineryMaintenanceViewHolder extends RecyclerView.ViewHolder {

        View mView;
        String my_concept,my_asset,my_day,my_month,my_year;
        TextView txtDate,txtConcept,txtAsset;

        public MachineryMaintenanceViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;

            txtDate = mView.findViewById(R.id.txtDate);
            txtConcept = mView.findViewById(R.id.txtConcept);
            txtAsset = mView.findViewById(R.id.txtAsset);

        }
        public void setConcept(String concept) {
            my_concept = concept;
        }

        public void setAsset(String asset) {
            my_asset = asset;
        }

        public void setDay(String day) {
            my_day = day;
        }

        public void setMonth(String month) {
            my_month = month;
        }
        public void setYear(String year) {
            my_year = year;
        }
    }
}
