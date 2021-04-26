package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.StrategicDirection.Mission;

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
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.StrategicDirection.Vision.VisionActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;
import in.galaxyofandroid.spinerdialog.OnSpinerItemClick;
import in.galaxyofandroid.spinerdialog.SpinnerDialog;

public class MissionActivity extends AppCompatActivity {

    String post_key,company_name,customer_output,company_economic_activity,vision_message,goal_message,company_mission;
    RadioButton rdVision1,rdVision2,rdVision3,rdVision4,rdVision5,rdGoal1,rdGoal2,rdGoal3,rdGoal4,rdGoal5;
    ImageView btnVision1,btnVision2,btnVision3,btnVision4,btnVision5,btnGoal1,btnGoal2,btnGoal3,btnGoal4,btnGoal5,btnIdeology1,btnWorkersRelation;
    TextView txtIdeology1,txtWorkersRelation,txtMessage;
    Button btnShowVision;
    LinearLayout rootLayout;
    DatabaseReference companyRef;
    String worker_relation_message;

    ArrayList<String> companySector =new ArrayList<>();
    SpinnerDialog spinnerSector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mission);

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
        btnVision1 = findViewById(R.id.btnVision1);
        btnVision2 = findViewById(R.id.btnVision2);
        btnVision3 = findViewById(R.id.btnVision3);
        btnVision4 = findViewById(R.id.btnVision4);
        btnVision5 = findViewById(R.id.btnVision5);
        txtIdeology1 = findViewById(R.id.txtIdeology1);
        btnShowVision = findViewById(R.id.btnShowVision);
        txtWorkersRelation = findViewById(R.id.txtWorkersRelation);
        btnWorkersRelation = findViewById(R.id.btnWorkersRelation);
        txtMessage = findViewById(R.id.txtMessage);

        rootLayout = findViewById(R.id.rootLayout);

        companyRef.child(post_key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                company_name = dataSnapshot.child("company_name").getValue().toString();
                customer_output = dataSnapshot.child("customer_output").getValue().toString();
                company_economic_activity = dataSnapshot.child("company_economic_activity").getValue().toString();

                if (dataSnapshot.hasChild("company_mission")) {
                    company_mission = dataSnapshot.child("company_mission").getValue().toString();
                    txtMessage.setText("MISIÓN: "+company_mission);
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
                String message = "Ingresa tu Actividad Nº 1";
                RadioButton rd_button = rdVision1;



                showEditDialog(message,rd_button);
            }
        });
        btnVision2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = "Ingresa tu Actividad Nº 2";
                RadioButton rd_button = rdVision2;


                showEditDialog(message,rd_button);
            }
        });
        btnVision3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = "Ingresa tu Actividad Nº 3";
                RadioButton rd_button = rdVision3;


                showEditDialog(message,rd_button);
            }
        });
        btnVision4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = "Ingresa tu Actividad Nº 4";
                RadioButton rd_button = rdVision4;

                showEditDialog(message,rd_button);
            }
        });
        btnVision5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = "Ingresa tu Actividad Nº 5";
                RadioButton rd_button = rdVision5;

                showEditDialog(message,rd_button);
            }
        });


        btnGoal1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = "Ingresa tu Cliente Nº 1";
                RadioButton rd_button = rdGoal1;

                showEditDialog(message,rd_button);
            }
        });
        btnGoal2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = "Ingresa tu Cliente Nº 2";
                RadioButton rd_button = rdGoal2;

                showEditDialog(message,rd_button);
            }
        });
        btnGoal3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = "Ingresa tu Cliente Nº 3";
                RadioButton rd_button = rdGoal3;

                showEditDialog(message,rd_button);
            }
        });
        btnGoal4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = "Ingresa tu Cliente Nº 4";
                RadioButton rd_button = rdGoal4;

                showEditDialog(message,rd_button);
            }
        });
        btnGoal5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = "Ingresa tu Cliente Nº 5";
                RadioButton rd_button = rdGoal5;

                showEditDialog(message,rd_button);
            }
        });

        companySector.add("Sector Agropecuario");companySector.add("Sector Pesca");companySector.add("Sector Minería e Hidrocarburos");companySector.add("Sector Manufactura");companySector.add("Sector Electricidad, Luz y Agua");companySector.add("Sector Construcción");
        companySector.add("Sector Comercio");companySector.add("Sector Transporte, Almacenamiento, Correo y Mensajería");companySector.add("Sector Telecomunicaciones y otros servicios de información");companySector.add("Sector Turismo");companySector.add("Sector Financiero");

        btnIdeology1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinnerSector.showSpinerDialog();
            }
        });

        spinnerSector = new SpinnerDialog(this,companySector, "Selecciona el Sector");
        spinnerSector.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String item2, int position2) {
                txtIdeology1.setText(item2);

            }
        });

        btnWorkersRelation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showWorkerRelationDialog();
            }
        });

        btnShowVision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!rdVision1.isChecked() && !rdVision2.isChecked() && !rdVision3.isChecked() && !rdVision4.isChecked() && !rdVision5.isChecked()) {
                    Snackbar.make(rootLayout, "Debes seleccionar la actividad", Snackbar.LENGTH_SHORT).show();
                } else if (!rdGoal1.isChecked() && !rdGoal2.isChecked() && !rdGoal3.isChecked() && !rdGoal4.isChecked() && !rdGoal5.isChecked()) {
                    Snackbar.make(rootLayout, "Debes seleccionar al cliente", Snackbar.LENGTH_SHORT).show();
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
        View finance_method = inflater.inflate(R.layout.show_mission_dialog,null);

        TextView txtVision;

        txtVision = finance_method.findViewById(R.id.txtVision);


        txtVision.setText("Ser una empresa que se enfoca en el "+txtIdeology1.getText().toString()+" brindando servicios de "+vision_message+" para "+goal_message+" con el fin de "+worker_relation_message+" siguiendo las sinergias comprendidas con el soporte de sus colaboradores y aliados");

        companyRef.child(post_key).child("company_mission").setValue("Ser una empresa que se enfoca en el "+txtIdeology1.getText().toString()+" brindando servicios de "+vision_message+" para "+goal_message+" con el fin de "+worker_relation_message+" siguiendo las sinergias comprendidas con el soporte de sus colaboradores y aliados");

        dialog.setView(finance_method);
        dialog.show();
    }


    private void showWorkerRelationDialog() {
        final AlertDialog dialog = new AlertDialog.Builder(this).create();

        LayoutInflater inflater = LayoutInflater.from(this);
        View finance_method = inflater.inflate(R.layout.mission_workers_relation_dialog,null);

        final RadioButton rdWorkerRelation1,rdWorkerRelation2,rdWorkerRelation3,rdWorkerRelation4,rdWorkerRelation5,rdWorkerRelation6,rdWorkerRelation7,rdWorkerRelation8,rdWorkerRelation9,rdWorkerRelation10;
        ImageView btnWorkerRelationInfo1,btnWorkerRelationInfo2,btnWorkerRelationInfo3,btnWorkerRelationInfo4,btnWorkerRelationInfo5,btnWorkerRelationInfo6,btnWorkerRelationInfo7,btnWorkerRelationInfo8,btnWorkerRelationInfo9,btnWorkerRelationInfo10;
        Button btnRegister;
        final RelativeLayout rootLayout;

        rdWorkerRelation1 = finance_method.findViewById(R.id.rdWorkerRelation1);
        rdWorkerRelation2 = finance_method.findViewById(R.id.rdWorkerRelation2);
        rdWorkerRelation3 = finance_method.findViewById(R.id.rdWorkerRelation3);
        rdWorkerRelation4 = finance_method.findViewById(R.id.rdWorkerRelation4);
        rdWorkerRelation5 = finance_method.findViewById(R.id.rdWorkerRelation5);
        rdWorkerRelation6 = finance_method.findViewById(R.id.rdWorkerRelation6);
        rdWorkerRelation7 = finance_method.findViewById(R.id.rdWorkerRelation7);
        rdWorkerRelation8 = finance_method.findViewById(R.id.rdWorkerRelation8);
        rdWorkerRelation9 = finance_method.findViewById(R.id.rdWorkerRelation9);
        rdWorkerRelation10 = finance_method.findViewById(R.id.rdWorkerRelation10);

        btnWorkerRelationInfo1 = finance_method.findViewById(R.id.btnWorkerRelationInfo1);
        btnWorkerRelationInfo2 = finance_method.findViewById(R.id.btnWorkerRelationInfo2);
        btnWorkerRelationInfo3 = finance_method.findViewById(R.id.btnWorkerRelationInfo3);
        btnWorkerRelationInfo4 = finance_method.findViewById(R.id.btnWorkerRelationInfo4);
        btnWorkerRelationInfo5 = finance_method.findViewById(R.id.btnWorkerRelationInfo5);
        btnWorkerRelationInfo6 = finance_method.findViewById(R.id.btnWorkerRelationInfo6);
        btnWorkerRelationInfo7 = finance_method.findViewById(R.id.btnWorkerRelationInfo7);
        btnWorkerRelationInfo8 = finance_method.findViewById(R.id.btnWorkerRelationInfo8);
        btnWorkerRelationInfo9 = finance_method.findViewById(R.id.btnWorkerRelationInfo9);
        btnWorkerRelationInfo10 = finance_method.findViewById(R.id.btnWorkerRelationInfo10);

        btnRegister = finance_method.findViewById(R.id.btnRegister);
        rootLayout = finance_method.findViewById(R.id.rootLayout);

        rdWorkerRelation1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                worker_relation_message = rdWorkerRelation1.getText().toString();
                rdWorkerRelation2.setChecked(false);
                rdWorkerRelation3.setChecked(false);
                rdWorkerRelation4.setChecked(false);
                rdWorkerRelation5.setChecked(false);
                rdWorkerRelation6.setChecked(false);
                rdWorkerRelation7.setChecked(false);
                rdWorkerRelation8.setChecked(false);
                rdWorkerRelation9.setChecked(false);
                rdWorkerRelation10.setChecked(false);
            }
        });

        rdWorkerRelation2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                worker_relation_message = rdWorkerRelation2.getText().toString();
                rdWorkerRelation1.setChecked(false);
                rdWorkerRelation3.setChecked(false);
                rdWorkerRelation4.setChecked(false);
                rdWorkerRelation5.setChecked(false);
                rdWorkerRelation6.setChecked(false);
                rdWorkerRelation7.setChecked(false);
                rdWorkerRelation8.setChecked(false);
                rdWorkerRelation9.setChecked(false);
                rdWorkerRelation10.setChecked(false);
            }
        });

        rdWorkerRelation3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                worker_relation_message = rdWorkerRelation3.getText().toString();
                rdWorkerRelation2.setChecked(false);
                rdWorkerRelation1.setChecked(false);
                rdWorkerRelation4.setChecked(false);
                rdWorkerRelation5.setChecked(false);
                rdWorkerRelation6.setChecked(false);
                rdWorkerRelation7.setChecked(false);
                rdWorkerRelation8.setChecked(false);
                rdWorkerRelation9.setChecked(false);
                rdWorkerRelation10.setChecked(false);
            }
        });

        rdWorkerRelation4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                worker_relation_message = rdWorkerRelation4.getText().toString();
                rdWorkerRelation2.setChecked(false);
                rdWorkerRelation3.setChecked(false);
                rdWorkerRelation1.setChecked(false);
                rdWorkerRelation5.setChecked(false);
                rdWorkerRelation6.setChecked(false);
                rdWorkerRelation7.setChecked(false);
                rdWorkerRelation8.setChecked(false);
                rdWorkerRelation9.setChecked(false);
                rdWorkerRelation10.setChecked(false);
            }
        });

        rdWorkerRelation5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                worker_relation_message = rdWorkerRelation5.getText().toString();
                rdWorkerRelation2.setChecked(false);
                rdWorkerRelation3.setChecked(false);
                rdWorkerRelation4.setChecked(false);
                rdWorkerRelation1.setChecked(false);
                rdWorkerRelation6.setChecked(false);
                rdWorkerRelation7.setChecked(false);
                rdWorkerRelation8.setChecked(false);
                rdWorkerRelation9.setChecked(false);
                rdWorkerRelation10.setChecked(false);
            }
        });

        rdWorkerRelation6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                worker_relation_message = rdWorkerRelation6.getText().toString();
                rdWorkerRelation2.setChecked(false);
                rdWorkerRelation3.setChecked(false);
                rdWorkerRelation4.setChecked(false);
                rdWorkerRelation5.setChecked(false);
                rdWorkerRelation1.setChecked(false);
                rdWorkerRelation7.setChecked(false);
                rdWorkerRelation8.setChecked(false);
                rdWorkerRelation9.setChecked(false);
                rdWorkerRelation10.setChecked(false);
            }
        });

        rdWorkerRelation7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                worker_relation_message = rdWorkerRelation7.getText().toString();
                rdWorkerRelation2.setChecked(false);
                rdWorkerRelation3.setChecked(false);
                rdWorkerRelation4.setChecked(false);
                rdWorkerRelation5.setChecked(false);
                rdWorkerRelation6.setChecked(false);
                rdWorkerRelation1.setChecked(false);
                rdWorkerRelation8.setChecked(false);
                rdWorkerRelation9.setChecked(false);
                rdWorkerRelation10.setChecked(false);
            }
        });

        rdWorkerRelation8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                worker_relation_message = rdWorkerRelation8.getText().toString();
                rdWorkerRelation2.setChecked(false);
                rdWorkerRelation3.setChecked(false);
                rdWorkerRelation4.setChecked(false);
                rdWorkerRelation5.setChecked(false);
                rdWorkerRelation6.setChecked(false);
                rdWorkerRelation7.setChecked(false);
                rdWorkerRelation1.setChecked(false);
                rdWorkerRelation9.setChecked(false);
                rdWorkerRelation10.setChecked(false);
            }
        });

        rdWorkerRelation9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                worker_relation_message = rdWorkerRelation9.getText().toString();
                rdWorkerRelation2.setChecked(false);
                rdWorkerRelation3.setChecked(false);
                rdWorkerRelation4.setChecked(false);
                rdWorkerRelation5.setChecked(false);
                rdWorkerRelation6.setChecked(false);
                rdWorkerRelation7.setChecked(false);
                rdWorkerRelation8.setChecked(false);
                rdWorkerRelation1.setChecked(false);
                rdWorkerRelation10.setChecked(false);
            }
        });

        rdWorkerRelation10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                worker_relation_message = rdWorkerRelation10.getText().toString();
                rdWorkerRelation2.setChecked(false);
                rdWorkerRelation3.setChecked(false);
                rdWorkerRelation4.setChecked(false);
                rdWorkerRelation5.setChecked(false);
                rdWorkerRelation6.setChecked(false);
                rdWorkerRelation7.setChecked(false);
                rdWorkerRelation8.setChecked(false);
                rdWorkerRelation9.setChecked(false);
                rdWorkerRelation1.setChecked(false);
            }
        });

        btnWorkerRelationInfo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String concept = rdWorkerRelation1.getText().toString();
                String definition = "Instaurar medidas de conciliación para mantener el equilibrio entra la vida entre el trabajo y la vida personal, flexibilizando horarios, otorgando permisos para atender necesidades personales o familiares cuando sean necesarios y teniendo en cuanta la situación socio-familiar de cada persona es algo fundamental para mejorar la satisfacción del personal de una empresa.";

                showWorkerRelationDefinitionDialog(concept,definition);
            }
        });

        btnWorkerRelationInfo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String concept = rdWorkerRelation2.getText().toString();
                String definition = "Animar al personal, alabar su trabajo bien hecho y, en general, reforzar todos los aspectos que puedan mejorar su satisfacción, entendiendo que ésta no se basa sólo en hechos materiales como el sueldo o la categoría laboral, es algo básico para el éxito del personal y de la empresa en la que trabaja.";
                showWorkerRelationDefinitionDialog(concept,definition);
            }
        });

        btnWorkerRelationInfo3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String concept = rdWorkerRelation3.getText().toString();
                String definition = "Las empresas deben valorar la experiencia del personal más veterano y aprovecharla para que este personal se implique con el más reciente en la empresa, ayudándole a integrarse en la misma y a ser partícipe de la cultura empresarial. Nunca desoír las aportaciones de los recién llegados simplemente por serlo.";
                showWorkerRelationDefinitionDialog(concept,definition);
            }
        });

        btnWorkerRelationInfo4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String concept = rdWorkerRelation4.getText().toString();
                String definition = "Hay que aprovechar el talento de cada persona para ponerla en el puesto de trabajo que mejor le permita desarrollar ese talento, potenciar la creatividad y, asimismo, fomentar el clima necesario de confianza para que las personas que trabajan en una empresa se sientan impulsadas a comunicar todas aquellas ideas o propuestas de mejora que les puedan surgir en un momento dado.";
                showWorkerRelationDefinitionDialog(concept,definition);
            }
        });

        btnWorkerRelationInfo5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String concept = rdWorkerRelation5.getText().toString();
                String definition = "Cuidar la ergonomía de los puestos de trabajo y fomentar hábitos saludables en el trabajo (descansos, cuidar la salud visual, prevenir el estrés, fomentar buenos hábitos posturales) es básico ya que está demostrado que el estado físico está muy relacionado con el estado emocional de la persona.";
                showWorkerRelationDefinitionDialog(concept,definition);
            }
        });

        btnWorkerRelationInfo6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String concept = rdWorkerRelation6.getText().toString();
                String definition = "Impulsar es espíritu de compañerismo y evitar los “agravios comparativos” mediante una sencilla fórmula: siendo imparcial y, sobre todo, transparente a la hora de tomar decisiones que puedan afectar a las condiciones laborales del personal. Escuchar a todo el mundo por igual para que nadie se pueda sentir minusvalorado. Todas las opiniones son importantes, todas las opiniones cuentan.E40:M43";
                showWorkerRelationDefinitionDialog(concept,definition);
            }
        });

        btnWorkerRelationInfo7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String concept = rdWorkerRelation7.getText().toString();
                String definition = "Estar pendiente de las necesidades de formación del personal de la empresa y facilitar que ésta pueda hacer los cursos necesarios para reciclarse profesionalmente sin que ello le suponga una carga de trabajo adicional.";
                showWorkerRelationDefinitionDialog(concept,definition);
            }
        });

        btnWorkerRelationInfo8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String concept = rdWorkerRelation8.getText().toString();
                String definition = "Conseguir que el personal de la empresa vea siempre la aplicación de nuevas tecnologías y metodologías como algo que va a suponer una mejora en su desempeño y en su rendimiento y, por tanto en su “carga de trabajo” hay que tener en cuenta que las personas siempre son reticentes a los cambios y por tanto hay que demostrar que esas nuevas metodologías lo que pretenden no es sólo aumentar los beneficios de la empresa sino “hacerle la vida más fácil”.";
                showWorkerRelationDefinitionDialog(concept,definition);
            }
        });

        btnWorkerRelationInfo9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String concept = rdWorkerRelation9.getText().toString();
                String definition = "Hacer que las pausas y los tiempos de descanso también sean “productivos” haciendo que varios compañeros puedan compartir ese tiempo de descanso para relajarse y comunicarse, compartir impresiones e ideas alrededor de un café sin que se sientan “vigilados” o “controlados”.";
                showWorkerRelationDefinitionDialog(concept,definition);
            }
        });

        btnWorkerRelationInfo10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String concept = rdWorkerRelation10.getText().toString();
                String definition = "Que la tan comentada “Cena de Navidad” no sea la única ocasión en que la plantilla de una empresa puede estar junta para compartir un rato de ocio y esparcimiento. Realizar actividades fuera del horario y espacio de trabajo en las que todos puedan participar (excursiones, visitas culturales) ayudará a fomentar el espíritu de equipo, de compañerismo y, por tanto, de empresa.";
                showWorkerRelationDefinitionDialog(concept,definition);
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!rdWorkerRelation1.isChecked() && !rdWorkerRelation2.isChecked() && !rdWorkerRelation3.isChecked() && !rdWorkerRelation4.isChecked() && !rdWorkerRelation5.isChecked()
                        && !rdWorkerRelation6.isChecked() && !rdWorkerRelation7.isChecked() && !rdWorkerRelation8.isChecked() && !rdWorkerRelation9.isChecked() && !rdWorkerRelation10.isChecked()) {
                    Snackbar.make(rootLayout, "Debes seleccionar una de las características", Snackbar.LENGTH_SHORT).show();
                } else {
                    txtWorkersRelation.setText(worker_relation_message);
                    dialog.dismiss();
                }
            }
        });


        dialog.setView(finance_method);
        dialog.show();
    }

    private void showWorkerRelationDefinitionDialog(String concept, String definition) {
        final AlertDialog dialog = new AlertDialog.Builder(this).create();

        LayoutInflater inflater = LayoutInflater.from(this);
        View finance_method = inflater.inflate(R.layout.worker_relation_mission_definition_dialog,null);

        TextView txtConcept,txtDefinition;

        txtConcept = finance_method.findViewById(R.id.txtConcept);
        txtDefinition = finance_method.findViewById(R.id.txtDefinition);

        txtConcept.setText(concept);
        txtDefinition.setText(definition);

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
                    Toasty.success(MissionActivity.this, "Registrado", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            }
        });


        dialog.setView(finance_method);
        dialog.show();
    }

}
