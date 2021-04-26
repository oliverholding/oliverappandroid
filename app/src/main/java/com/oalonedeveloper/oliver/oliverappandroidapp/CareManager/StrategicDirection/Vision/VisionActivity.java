package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.StrategicDirection.Vision;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
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

public class VisionActivity extends AppCompatActivity {

    String post_key,company_name,customer_output,company_economic_activity,vision_message,goal_message,company_vision;
    RadioButton rdVision1,rdVision2,rdVision3,rdVision4,rdVision5,rdGoal1,rdGoal2,rdGoal3,rdGoal4,rdGoal5;
    ImageView btnVision1,btnVision2,btnVision3,btnVision4,btnVision5,btnGoal1,btnGoal2,btnGoal3,btnGoal4,btnGoal5,btnIdeology1,btnIdeology2;
    TextView txtIdeology1,txtIdeology2,txtMessage;
    Button btnShowVision;
    LinearLayout rootLayout;
    DatabaseReference companyRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vision);

        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");
        post_key = getIntent().getExtras().getString("post_key");

        rdVision1 = findViewById(R.id.rdVision1);
        rdVision2 = findViewById(R.id.rdVision2);
        rdVision3 = findViewById(R.id.rdVision3);
        rdVision4 = findViewById(R.id.rdVision4);
        rdVision5 = findViewById(R.id.rdVision5);
        rdGoal1 = findViewById(R.id.rdGoal1);
        rdGoal2 = findViewById(R.id.rdGoal2);
        rdGoal3 = findViewById(R.id.rdGoal3);
        rdGoal4 = findViewById(R.id.rdGoal4);
        rdGoal5 = findViewById(R.id.rdGoal5);
        btnGoal1 = findViewById(R.id.btnGoal1);
        btnGoal2 = findViewById(R.id.btnGoal2);
        btnGoal3 = findViewById(R.id.btnGoal3);
        btnGoal4 = findViewById(R.id.btnGoal4);
        btnGoal5 = findViewById(R.id.btnGoal5);
        btnIdeology1 = findViewById(R.id.btnIdeology1);
        btnIdeology2 = findViewById(R.id.btnIdeology2);
        btnVision1 = findViewById(R.id.btnVision1);
        btnVision2 = findViewById(R.id.btnVision2);
        btnVision3 = findViewById(R.id.btnVision3);
        btnVision4 = findViewById(R.id.btnVision4);
        btnVision5 = findViewById(R.id.btnVision5);
        txtIdeology1 = findViewById(R.id.txtIdeology1);
        txtIdeology2 = findViewById(R.id.txtIdeology2);
        btnShowVision = findViewById(R.id.btnShowVision);
        txtMessage = findViewById(R.id.txtMessage);


        rootLayout = findViewById(R.id.rootLayout);

        companyRef.child(post_key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                company_name = dataSnapshot.child("company_name").getValue().toString();
                customer_output = dataSnapshot.child("customer_output").getValue().toString();
                company_economic_activity = dataSnapshot.child("company_economic_activity").getValue().toString();

                if (dataSnapshot.hasChild("company_vision")) {
                    company_vision = dataSnapshot.child("company_vision").getValue().toString();
                    txtMessage.setText("VISIÓN: "+company_vision);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        rdVision1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vision_message = rdVision1.getText().toString();
                rdVision2.setChecked(false);
                rdVision3.setChecked(false);
                rdVision4.setChecked(false);
                rdVision5.setChecked(false);
            }
        });
        rdVision2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vision_message = rdVision2.getText().toString();
                rdVision1.setChecked(false);
                rdVision3.setChecked(false);
                rdVision4.setChecked(false);
                rdVision5.setChecked(false);
            }
        });
        rdVision3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vision_message = rdVision3.getText().toString();
                rdVision2.setChecked(false);
                rdVision1.setChecked(false);
                rdVision4.setChecked(false);
                rdVision5.setChecked(false);
            }
        });
        rdVision4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vision_message = rdVision4.getText().toString();
                rdVision2.setChecked(false);
                rdVision3.setChecked(false);
                rdVision1.setChecked(false);
                rdVision5.setChecked(false);
            }
        });
        rdVision5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vision_message = rdVision5.getText().toString();
                rdVision2.setChecked(false);
                rdVision3.setChecked(false);
                rdVision4.setChecked(false);
                rdVision1.setChecked(false);
            }
        });

        rdGoal1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goal_message = rdGoal1.getText().toString();
                rdGoal2.setChecked(false);
                rdGoal3.setChecked(false);
                rdGoal4.setChecked(false);
                rdGoal5.setChecked(false);
            }
        });
        rdGoal2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goal_message = rdGoal2.getText().toString();
                rdGoal1.setChecked(false);
                rdGoal3.setChecked(false);
                rdGoal4.setChecked(false);
                rdGoal5.setChecked(false);
            }
        });
        rdGoal3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goal_message = rdGoal3.getText().toString();
                rdGoal2.setChecked(false);
                rdGoal1.setChecked(false);
                rdGoal4.setChecked(false);
                rdGoal5.setChecked(false);
            }
        });
        rdGoal4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goal_message = rdGoal4.getText().toString();
                rdGoal2.setChecked(false);
                rdGoal3.setChecked(false);
                rdGoal1.setChecked(false);
                rdGoal5.setChecked(false);
            }
        });
        rdGoal5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goal_message = rdGoal5.getText().toString();
                rdGoal2.setChecked(false);
                rdGoal3.setChecked(false);
                rdGoal4.setChecked(false);
                rdGoal1.setChecked(false);
            }
        });


        btnVision1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = "Ingresa tu Visión Nº 1";
                RadioButton rd_button = rdVision1;



                showEditDialog(message,rd_button);
            }
        });
        btnVision2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = "Ingresa tu Visión Nº 2";
                RadioButton rd_button = rdVision2;


                showEditDialog(message,rd_button);
            }
        });
        btnVision3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = "Ingresa tu Visión Nº 3";
                RadioButton rd_button = rdVision3;


                showEditDialog(message,rd_button);
            }
        });
        btnVision4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = "Ingresa tu Visión Nº 4";
                RadioButton rd_button = rdVision4;

                showEditDialog(message,rd_button);
            }
        });
        btnVision5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = "Ingresa tu Visión Nº 5";
                RadioButton rd_button = rdVision5;

                showEditDialog(message,rd_button);
            }
        });


        btnGoal1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = "Ingresa tu Meta Nº 1";
                RadioButton rd_button = rdGoal1;

                showEditDialog(message,rd_button);
            }
        });
        btnGoal2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = "Ingresa tu Meta Nº 2";
                RadioButton rd_button = rdGoal2;

                showEditDialog(message,rd_button);
            }
        });
        btnGoal3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = "Ingresa tu Meta Nº 3";
                RadioButton rd_button = rdGoal3;

                showEditDialog(message,rd_button);
            }
        });
        btnGoal4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = "Ingresa tu Meta Nº 4";
                RadioButton rd_button = rdGoal4;

                showEditDialog(message,rd_button);
            }
        });
        btnGoal5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = "Ingresa tu Meta Nº 5";
                RadioButton rd_button = rdGoal5;

                showEditDialog(message,rd_button);
            }
        });


        btnIdeology1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = "Ingresa tu Característica principal y única de su negocio/comercio/empresa";
                TextView text_view = txtIdeology1;
                showEditDialog1(message,text_view);
            }
        });

        btnIdeology2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = "Ingresa lo qué hace de diferente su negocio/comercio/empresa";
                TextView text_view = txtIdeology2;
                showEditDialog1(message,text_view);
            }
        });

        btnShowVision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!rdVision1.isChecked() && !rdVision2.isChecked() && !rdVision3.isChecked() && !rdVision4.isChecked() && !rdVision5.isChecked()) {
                    Snackbar.make(rootLayout, "Debes seleccionar la vision", Snackbar.LENGTH_SHORT).show();
                } else if (!rdGoal1.isChecked() && !rdGoal2.isChecked() && !rdGoal3.isChecked() && !rdGoal4.isChecked() && !rdGoal5.isChecked()) {
                    Snackbar.make(rootLayout, "Debes seleccionar la meta", Snackbar.LENGTH_SHORT).show();
                }
                else {
                   showVision();
                }
            }
        });
    }

    private void showVision() {
        final AlertDialog dialog = new AlertDialog.Builder(this).create();

        LayoutInflater inflater = LayoutInflater.from(this);
        View finance_method = inflater.inflate(R.layout.show_vision_dialog,null);

        TextView txtVision;

        txtVision = finance_method.findViewById(R.id.txtVision);


        txtVision.setText("Identificarnos como una empresa de "+customer_output+" líder en "+company_economic_activity+" cuya visión sea "+vision_message+" y que nos lleve al cumplimiento de nuestra meta principal a largo plazo que es la de "+goal_message+" sin perder nuestra esencia en "+txtIdeology1.getText().toString()+" y "+txtIdeology2.getText().toString()+" para nuestro crecimiento sólido y sostenible a lo largo de los años");
        companyRef.child(post_key).child("company_vision").setValue("Identificarnos como una empresa de "+customer_output+" líder en "+company_economic_activity+" cuya visión sea "+vision_message+" y que nos lleve al cumplimiento de nuestra meta principal a largo plazo que es la de "+goal_message+" sin perder nuestra esencia en "+txtIdeology1.getText().toString()+" y "+txtIdeology2.getText().toString()+" para nuestro crecimiento sólido y sostenible a lo largo de los años");

        dialog.setView(finance_method);
        dialog.show();
    }

    private void showEditDialog1(String message, final TextView text_view) {
        final AlertDialog dialog = new AlertDialog.Builder(this).create();

        LayoutInflater inflater = LayoutInflater.from(this);
        View finance_method = inflater.inflate(R.layout.edit_vision_dialog,null);

        TextView txtMessage;
        final EditText edtInput;
        Button btnRegister;
        final RelativeLayout rootLayout;

        txtMessage = finance_method.findViewById(R.id.txtMessage);
        edtInput = finance_method.findViewById(R.id.edtInput);
        btnRegister = finance_method.findViewById(R.id.btnRegister);
        rootLayout = finance_method.findViewById(R.id.rootLayout);

        txtMessage.setText(message);
        edtInput.setHint(message);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(edtInput.getText().toString())) {
                    Snackbar.make(rootLayout, "Debes completar todos los datos", Snackbar.LENGTH_SHORT).show();
                } else {
                    text_view.setText(edtInput.getText().toString());
                    Toasty.success(VisionActivity.this, "Registrado", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            }
        });


        dialog.setView(finance_method);
        dialog.show();
    }

    private void showEditDialog(String message, final RadioButton rd_button) {
        final AlertDialog dialog = new AlertDialog.Builder(this).create();

        LayoutInflater inflater = LayoutInflater.from(this);
        View finance_method = inflater.inflate(R.layout.edit_vision_dialog,null);

        TextView txtMessage;
        final EditText edtInput;
        Button btnRegister;
        final RelativeLayout rootLayout;

        txtMessage = finance_method.findViewById(R.id.txtMessage);
        edtInput = finance_method.findViewById(R.id.edtInput);
        btnRegister = finance_method.findViewById(R.id.btnRegister);
        rootLayout = finance_method.findViewById(R.id.rootLayout);

        txtMessage.setText(message);
        edtInput.setHint(message);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(edtInput.getText().toString())) {
                    Snackbar.make(rootLayout, "Debes completar todos los datos", Snackbar.LENGTH_SHORT).show();
                } else {
                    rd_button.setText(edtInput.getText().toString());
                    Toasty.success(VisionActivity.this, "Registrado", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            }
        });


        dialog.setView(finance_method);
        dialog.show();
    }




}
