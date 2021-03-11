package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.BusinessPlan.OperationPlan;

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
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.StrategicDirection.Vision.VisionActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;
import in.galaxyofandroid.spinerdialog.OnSpinerItemClick;
import in.galaxyofandroid.spinerdialog.SpinnerDialog;

public class OperationPlanActivity extends AppCompatActivity {

    DatabaseReference companyRef;
    String post_key;
    ImageView btnAdd1,btnAdd2,btnEdit1,btnKeyProcess1,btnKeyProcess2,btnKeyProcess3;
    RecyclerView recyclerView1,recyclerView2;
    TextView txtTechnology,txtKeyProcess1,txtKeyProcess2,txtKeyProcess3;

    ArrayList<String> measurement =new ArrayList<>();
    SpinnerDialog spinnerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operation_plan);

        post_key = getIntent().getExtras().getString("post_key");
        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");

        btnAdd1 = findViewById(R.id.btnAdd1);
        btnAdd2 = findViewById(R.id.btnAdd2);
        btnEdit1 = findViewById(R.id.btnEdit1);

        btnKeyProcess1 = findViewById(R.id.btnKeyProcess1);
        btnKeyProcess2 = findViewById(R.id.btnKeyProcess2);
        btnKeyProcess3 = findViewById(R.id.btnKeyProcess3);

        recyclerView1 = findViewById(R.id.recyclerView1);
        recyclerView2 = findViewById(R.id.recyclerView2);

        txtTechnology = findViewById(R.id.txtTechnology);
        txtKeyProcess1 = findViewById(R.id.txtKeyProcess1);
        txtKeyProcess2 = findViewById(R.id.txtKeyProcess2);
        txtKeyProcess3 = findViewById(R.id.txtKeyProcess3);

        measurement.add("Cantidad");measurement.add("Kilogramos");measurement.add("Gramos");measurement.add("Libras");measurement.add("Horas");measurement.add("Minutos");


        recyclerView1.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(false);
        linearLayoutManager.setStackFromEnd(false);
        recyclerView1.setLayoutManager(linearLayoutManager);

        recyclerView2.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(this);
        linearLayoutManager2.setReverseLayout(false);
        linearLayoutManager2.setStackFromEnd(false);
        recyclerView2.setLayoutManager(linearLayoutManager2);

        showData();
        showData2();

        btnAdd1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAdd1Dialog();
            }
        });
        btnAdd2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAdd2Dialog();
            }
        });

        btnEdit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTechDialog();
            }
        });


        companyRef.child(post_key).child("Business Plan").child("Operative Plane").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild("technology")) {
                    String value = dataSnapshot.child("technology").getValue().toString();
                    txtTechnology.setText(value);
                }

                companyRef.child(post_key).child("Business Plan").child("Operative Plane").child("Key Process").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChild("item_1")) {
                            String value = dataSnapshot.child("item_1").getValue().toString();
                            txtKeyProcess1.setText(value);
                        }
                        if (dataSnapshot.hasChild("item_2")) {
                            String value = dataSnapshot.child("item_2").getValue().toString();
                            txtKeyProcess2.setText(value);
                        }
                        if (dataSnapshot.hasChild("item_3")) {
                            String value = dataSnapshot.child("item_3").getValue().toString();
                            txtKeyProcess3.setText(value);
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

        btnKeyProcess1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path = "item_1";
                String key_process = "Procesos operativos";

                showKeyProcessDialog(path,key_process);
            }
        });
        btnKeyProcess2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path = "item_2";
                String key_process = "Procesos administrativos";

                showKeyProcessDialog(path,key_process);
            }
        });
        btnKeyProcess3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path = "item_3";
                String key_process = "Procesos comerciales";

                showKeyProcessDialog(path,key_process);
            }
        });



    }

    private void showKeyProcessDialog(final String path, String key_process) {
        final AlertDialog dialog = new AlertDialog.Builder(this).create();

        LayoutInflater inflater = LayoutInflater.from(this);
        View finance_method = inflater.inflate(R.layout.add_key_process_dialog,null);

        final EditText edtName;
        Button btnFinish;
        final LinearLayout rootLayout;
        TextView txtKeyProcess;

        edtName = finance_method.findViewById(R.id.edtName);
        btnFinish = finance_method.findViewById(R.id.btnFinish);
        rootLayout = finance_method.findViewById(R.id.rootLayout);
        txtKeyProcess = finance_method.findViewById(R.id.txtKeyProcess);

        txtKeyProcess.setText(key_process);

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(edtName.getText().toString())) {
                    Snackbar.make(rootLayout,"Debes completar el nombre",Snackbar.LENGTH_SHORT).show();
                }else  {
                    companyRef.child(post_key).child("Business Plan").child("Operative Plane").child("Key Process").child(path).setValue(edtName.getText().toString());
                    Toasty.success(OperationPlanActivity.this, "Registrado", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            }
        });


        dialog.setView(finance_method);
        dialog.show();
    }

    private void showTechDialog() {
        final AlertDialog dialog = new AlertDialog.Builder(this).create();

        LayoutInflater inflater = LayoutInflater.from(this);
        View finance_method = inflater.inflate(R.layout.add_tech_dialog,null);

        final EditText edtName;
        Button btnFinish;
        final LinearLayout rootLayout;

        edtName = finance_method.findViewById(R.id.edtName);
        btnFinish = finance_method.findViewById(R.id.btnFinish);
        rootLayout = finance_method.findViewById(R.id.rootLayout);

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(edtName.getText().toString())) {
                    Snackbar.make(rootLayout,"Debes completar el nombre",Snackbar.LENGTH_SHORT).show();
                }else  {
                    companyRef.child(post_key).child("Business Plan").child("Operative Plane").child("technology").setValue(edtName.getText().toString());
                    Toasty.success(OperationPlanActivity.this, "Registrado", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            }
        });


        dialog.setView(finance_method);
        dialog.show();
    }

    private void showData2() {
        Query query =  companyRef.child(post_key).child("Business Plan").child("Operative Plane").child("Resources").orderByChild("name");
        FirebaseRecyclerAdapter<OperationPlanItemsModel,OperationPlanViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<OperationPlanItemsModel, OperationPlanViewHolder>
                (OperationPlanItemsModel.class,R.layout.operation_plan_item,OperationPlanViewHolder.class,query) {
            @Override
            protected void populateViewHolder(OperationPlanViewHolder viewHolder, OperationPlanItemsModel model, int position) {
                viewHolder.setMeasurement(model.getMeasurement());
                viewHolder.setName(model.getName());
                viewHolder.setQuantity(model.getQuantity());

                viewHolder.txtName.setText(viewHolder.st_name);
                viewHolder.txtMeasurements.setText(viewHolder.st_measurement);
                viewHolder.txtQuantity.setText(viewHolder.st_quantity);
            }
        };
        recyclerView2.setAdapter(firebaseRecyclerAdapter);
    }

    private void showData() {
        Query query =  companyRef.child(post_key).child("Business Plan").child("Operative Plane").child("Products").orderByChild("name");
        FirebaseRecyclerAdapter<OperationPlanItemsModel,OperationPlanViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<OperationPlanItemsModel, OperationPlanViewHolder>
                (OperationPlanItemsModel.class,R.layout.operation_plan_item,OperationPlanViewHolder.class,query) {
            @Override
            protected void populateViewHolder(OperationPlanViewHolder viewHolder, OperationPlanItemsModel model, int position) {
                viewHolder.setMeasurement(model.getMeasurement());
                viewHolder.setName(model.getName());
                viewHolder.setQuantity(model.getQuantity());

                viewHolder.txtName.setText(viewHolder.st_name);
                viewHolder.txtMeasurements.setText(viewHolder.st_measurement);
                viewHolder.txtQuantity.setText(viewHolder.st_quantity);
            }
        };
        recyclerView1.setAdapter(firebaseRecyclerAdapter);
    }

    private void showAdd2Dialog() {
        final AlertDialog dialog = new AlertDialog.Builder(this).create();

        LayoutInflater inflater = LayoutInflater.from(this);
        View finance_method = inflater.inflate(R.layout.add_process_2_dialog,null);

        final EditText edtName,edtQuantity;
        final Button btnUnit,btnFinish;
        final LinearLayout rootLayout;

        edtName = finance_method.findViewById(R.id.edtName);
        edtQuantity = finance_method.findViewById(R.id.edtQuantity);
        btnUnit = finance_method.findViewById(R.id.btnUnit);
        btnFinish = finance_method.findViewById(R.id.btnFinish);
        rootLayout = finance_method.findViewById(R.id.rootLayout);

        btnUnit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinnerDialog.showSpinerDialog();
            }
        });

        spinnerDialog = new SpinnerDialog(this,measurement, "Selecciona la Unidad de Medida");
        spinnerDialog.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String item2, int position2) {
                btnUnit.setText(item2);

            }
        });

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(edtName.getText().toString())) {
                    Snackbar.make(rootLayout,"Debes completar el nombre",Snackbar.LENGTH_SHORT).show();
                }else  if (TextUtils.isEmpty(edtQuantity.getText().toString())) {
                    Snackbar.make(rootLayout,"Debes completar la cantidad",Snackbar.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(btnUnit.getText().toString())) {
                    Snackbar.make(rootLayout, "Debes seleccionar la unidad de medida", Snackbar.LENGTH_SHORT).show();
                } else {
                    Long tsLong = System.currentTimeMillis()/1000;
                    String timestamp = tsLong.toString();
                    companyRef.child(post_key).child("Business Plan").child("Operative Plane").child("Resources").child(timestamp).child("name").setValue(edtName.getText().toString());
                    companyRef.child(post_key).child("Business Plan").child("Operative Plane").child("Resources").child(timestamp).child("measurement").setValue(btnUnit.getText().toString());
                    companyRef.child(post_key).child("Business Plan").child("Operative Plane").child("Resources").child(timestamp).child("quantity").setValue(edtQuantity.getText().toString());
                    Toasty.success(OperationPlanActivity.this, "Registrado", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            }
        });

        dialog.setView(finance_method);
        dialog.show();
    }

    private void showAdd1Dialog() {
        final AlertDialog dialog = new AlertDialog.Builder(this).create();

        LayoutInflater inflater = LayoutInflater.from(this);
        View finance_method = inflater.inflate(R.layout.add_process_1_dialog,null);

        final EditText edtName,edtQuantity;
        final Button btnUnit,btnFinish;
        final LinearLayout rootLayout;

        edtName = finance_method.findViewById(R.id.edtName);
        edtQuantity = finance_method.findViewById(R.id.edtQuantity);
        btnUnit = finance_method.findViewById(R.id.btnUnit);
        btnFinish = finance_method.findViewById(R.id.btnFinish);
        rootLayout = finance_method.findViewById(R.id.rootLayout);

        btnUnit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinnerDialog.showSpinerDialog();
            }
        });

        spinnerDialog = new SpinnerDialog(this,measurement, "Selecciona la Unidad de Medida");
        spinnerDialog.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String item2, int position2) {
                btnUnit.setText(item2);

            }
        });

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(edtName.getText().toString())) {
                    Snackbar.make(rootLayout,"Debes completar el nombre",Snackbar.LENGTH_SHORT).show();
                }else  if (TextUtils.isEmpty(edtQuantity.getText().toString())) {
                    Snackbar.make(rootLayout,"Debes completar la cantidad",Snackbar.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(btnUnit.getText().toString())) {
                    Snackbar.make(rootLayout, "Debes seleccionar la unidad de medida", Snackbar.LENGTH_SHORT).show();
                } else {
                    Long tsLong = System.currentTimeMillis()/1000;
                    String timestamp = tsLong.toString();
                    companyRef.child(post_key).child("Business Plan").child("Operative Plane").child("Products").child(timestamp).child("name").setValue(edtName.getText().toString());
                    companyRef.child(post_key).child("Business Plan").child("Operative Plane").child("Products").child(timestamp).child("measurement").setValue(btnUnit.getText().toString());
                    companyRef.child(post_key).child("Business Plan").child("Operative Plane").child("Products").child(timestamp).child("quantity").setValue(edtQuantity.getText().toString());
                    Toasty.success(OperationPlanActivity.this, "Registrado", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            }
        });

        dialog.setView(finance_method);
        dialog.show();
    }

    public static class OperationPlanViewHolder extends RecyclerView.ViewHolder {
        View mView;
        String st_name, st_measurement,st_quantity ;
        TextView txtName,txtMeasurements,txtQuantity;

        public OperationPlanViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;

            txtName = mView.findViewById(R.id.txtName);
            txtMeasurements = mView.findViewById(R.id.txtMeasurements);
            txtQuantity = mView.findViewById(R.id.txtQuantity);
        }

        public void setName(String name) {
            st_name = name;
        }

        public void setMeasurement(String measurement) {
            st_measurement = measurement;
        }


        public void setQuantity(String quantity) {
            st_quantity = quantity;
        }
    }
}
