package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Production.ProductionCapacity;

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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ServerValue;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;

import java.text.DecimalFormat;

import es.dmoral.toasty.Toasty;

public class ProductionCapacityActivity extends AppCompatActivity {

    Button btnRegister;
    DatabaseReference companyRef;
    String post_key;
    DecimalFormat decimalFormat,decimalFormat1;
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_production_capacity);

        btnRegister = findViewById(R.id.btnRegister);

        post_key = getIntent().getExtras().getString("post_key");
        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");
        decimalFormat = new DecimalFormat("0.00");
        decimalFormat1 = new DecimalFormat("0");

        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        showProductLines();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog dialog = new AlertDialog.Builder(ProductionCapacityActivity.this).create();

                LayoutInflater inflater = LayoutInflater.from(ProductionCapacityActivity.this);
                View finance_method = inflater.inflate(R.layout.add_production_line_dialog,null);

                final EditText edtProductionLineName,edtTheoreticalCapacity,edtManTimes,edtRealProduction;
                Button btnRegister;
                final LinearLayout rootLayout;

                edtProductionLineName = finance_method.findViewById(R.id.edtProductionLineName);
                edtTheoreticalCapacity = finance_method.findViewById(R.id.edtTheoreticalCapacity);
                edtManTimes = finance_method.findViewById(R.id.edtManTimes);
                edtRealProduction = finance_method.findViewById(R.id.edtRealProduction);
                btnRegister = finance_method.findViewById(R.id.btnRegister);
                rootLayout = finance_method.findViewById(R.id.rootLayout);

                btnRegister.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (TextUtils.isEmpty(edtProductionLineName.getText().toString())) {
                            Snackbar.make(rootLayout,"Debes ingresar el nombre de la linea de producción",Snackbar.LENGTH_LONG).show();
                        }
                        else if (TextUtils.isEmpty(edtTheoreticalCapacity.getText().toString())) {
                            Snackbar.make(rootLayout,"Debes ingresar la capacidad teórica",Snackbar.LENGTH_LONG).show();
                        }
                        else if (TextUtils.isEmpty(edtManTimes.getText().toString())) {
                            Snackbar.make(rootLayout,"Debes ingresar las horas hombre",Snackbar.LENGTH_LONG).show();
                        } else if (TextUtils.isEmpty(edtRealProduction.getText().toString())) {
                            Snackbar.make(rootLayout, "Debes ingresar la producción real", Snackbar.LENGTH_LONG).show();
                        } else {

                            Long tsLong = System.currentTimeMillis()/1000;
                            String timestamp = tsLong.toString();

                            companyRef.child(post_key).child("Production Lines").child(timestamp).child("production_line_name").setValue(edtProductionLineName.getText().toString());
                            companyRef.child(post_key).child("Production Lines").child(timestamp).child("production_theoretical_capacity").setValue(edtTheoreticalCapacity.getText().toString());
                            companyRef.child(post_key).child("Production Lines").child(timestamp).child("production_man_time").setValue(edtManTimes.getText().toString());
                            companyRef.child(post_key).child("Production Lines").child(timestamp).child("production_real_production").setValue(edtRealProduction.getText().toString());
                            companyRef.child(post_key).child("Production Lines").child(timestamp).child("timestamp").setValue(ServerValue.TIMESTAMP);

                            dialog.dismiss();
                            Toasty.success(ProductionCapacityActivity.this, "Registrado", Toast.LENGTH_LONG).show();
                        }
                    }
                });

                dialog.setView(finance_method);
                dialog.show();
            }
        });
    }

    private void showProductLines() {
        Query query = companyRef.child(post_key).child("Production Lines").orderByChild("timestamp");
        FirebaseRecyclerAdapter<ProductionLinesModel, ProductionCapacityViewHolder> firebaseRecyclerAdapter= new FirebaseRecyclerAdapter<ProductionLinesModel, ProductionCapacityViewHolder>
                (ProductionLinesModel.class,R.layout.production_line_item,ProductionCapacityViewHolder.class,query) {
            @Override
            protected void populateViewHolder(ProductionCapacityViewHolder viewHolder, ProductionLinesModel model, int position) {
                viewHolder.setProduction_line_name(model.getProduction_line_name());
                viewHolder.setProduction_man_time(model.getProduction_man_time());
                viewHolder.setProduction_real_production(model.getProduction_real_production());
                viewHolder.setProduction_theoretical_capacity(model.getProduction_theoretical_capacity());

                viewHolder.txtProductionLineName.setText(viewHolder.my_production_line_name);
                viewHolder.txtTheoreticalCapacity.setText("Capacidad Teórica o Máxima"+viewHolder.my_production_theoretical_capacity);
                viewHolder.txtManTime.setText("Horas hombre necesarias: "+viewHolder.my_production_man_time+" horas mensuales");

                double man_time = Double.parseDouble(viewHolder.my_production_man_time);
                double people = man_time/192;
                String people_st = decimalFormat1.format(people);
                viewHolder.txtPeople.setText("Personas: "+people_st);

                double total_capacity = Double.parseDouble(viewHolder.my_production_theoretical_capacity);
                double real_production = Double.parseDouble(viewHolder.my_production_real_production);
                double real_percent = (real_production/total_capacity)*100;
                String real_percent_st = decimalFormat.format(real_percent);
                viewHolder.txtRealProduction.setText("Producción Real: "+viewHolder.my_production_real_production);
                viewHolder.txtRealCapacity.setText("Capacidad Utilizada: "+real_percent_st+"%");

            }
        };
        recyclerView.setAdapter(firebaseRecyclerAdapter);

    }

    public static class ProductionCapacityViewHolder extends RecyclerView.ViewHolder {
        View mView;
        String my_production_line_name,my_production_man_time,my_production_real_production,my_production_theoretical_capacity;
        TextView txtProductionLineName,txtTheoreticalCapacity,txtManTime,txtPeople,txtRealProduction,txtRealCapacity;
        Button btnManage;

        public ProductionCapacityViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;

            txtProductionLineName = mView.findViewById(R.id.txtProductionLineName);
            txtTheoreticalCapacity = mView.findViewById(R.id.txtTheoreticalCapacity);
            txtManTime = mView.findViewById(R.id.txtManTime);
            txtPeople = mView.findViewById(R.id.txtPeople);
            txtRealProduction = mView.findViewById(R.id.txtRealProduction);
            txtRealCapacity = mView.findViewById(R.id.txtRealCapacity);
            btnManage = mView.findViewById(R.id.btnManage);
        }
        public void setProduction_line_name(String production_line_name) {
            my_production_line_name = production_line_name;
        }

        public void setProduction_man_time(String production_man_time) {
            my_production_man_time = production_man_time;
        }

        public void setProduction_real_production(String production_real_production) {
            my_production_real_production = production_real_production;
        }

        public void setProduction_theoretical_capacity(String production_theoretical_capacity) {
            my_production_theoretical_capacity = production_theoretical_capacity;
        }
    }
}
