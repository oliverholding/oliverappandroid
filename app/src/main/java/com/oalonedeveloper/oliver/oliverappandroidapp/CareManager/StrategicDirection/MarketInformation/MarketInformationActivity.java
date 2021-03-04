package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.StrategicDirection.MarketInformation;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;
import in.galaxyofandroid.spinerdialog.OnSpinerItemClick;
import in.galaxyofandroid.spinerdialog.SpinnerDialog;

public class MarketInformationActivity extends AppCompatActivity {

    ImageView btnItem1,btnItem2,btnItem3,btnItem4,btnItem5;
    TextView txtItem1,txtItem2,txtItem3,txtItem4,txtItem5,txtResult;
    DatabaseReference companyRef;
    String post_key,answer_message;
    int factor_1,factor_2,factor_3,factor_4,factor_5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market_information);

        post_key = getIntent().getExtras().getString("post_key");
        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");

        btnItem1 = findViewById(R.id.btnItem1);
        btnItem2 = findViewById(R.id.btnItem2);
        btnItem3 = findViewById(R.id.btnItem3);
        btnItem4 = findViewById(R.id.btnItem4);
        btnItem5 = findViewById(R.id.btnItem5);

        txtItem1 = findViewById(R.id.txtItem1);
        txtItem2 = findViewById(R.id.txtItem2);
        txtItem3 = findViewById(R.id.txtItem3);
        txtItem4 = findViewById(R.id.txtItem4);
        txtItem5 = findViewById(R.id.txtItem5);
        txtResult = findViewById(R.id.txtResult);

        companyRef.child(post_key).child("Market Information").child("item_1").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild("question_2")) {
                    String value = dataSnapshot.child("question_2").getValue().toString();
                    txtItem1.setText(value);
                    if (value.equals("Alta")) {
                        factor_1 = 3;
                    }
                    if (value.equals("Media")) {
                        factor_1 = 2;
                    }
                    if (value.equals("Débil")) {
                        factor_1 = 1;
                    }

                }

                companyRef.child(post_key).child("Market Information").child("item_2").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChild("question_2")) {
                            String value = dataSnapshot.child("question_2").getValue().toString();
                            txtItem2.setText(value);
                            if (value.equals("Alta")) {
                                factor_2 = 3;
                            }
                            if (value.equals("Media")) {
                                factor_2 = 2;
                            }
                            if (value.equals("Débil")) {
                                factor_2 = 1;
                            }
                        }
                        companyRef.child(post_key).child("Market Information").child("item_3").addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                if (dataSnapshot.hasChild("question_2")) {
                                    String value = dataSnapshot.child("question_2").getValue().toString();
                                    txtItem3.setText(value);
                                    if (value.equals("Alta")) {
                                        factor_3 = 3;
                                    }
                                    if (value.equals("Media")) {
                                        factor_3 = 2;
                                    }
                                    if (value.equals("Débil")) {
                                        factor_3 = 1;
                                    }
                                }


                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                        companyRef.child(post_key).child("Market Information").child("item_4").addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                if (dataSnapshot.hasChild("question_2")) {
                                    String value = dataSnapshot.child("question_2").getValue().toString();
                                    txtItem4.setText(value);

                                    if (value.equals("Alta")) {
                                        factor_4 = 3;
                                    }
                                    if (value.equals("Media")) {
                                        factor_4 = 2;
                                    }
                                    if (value.equals("Débil")) {
                                        factor_4 = 1;
                                    }
                                }


                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });

                        companyRef.child(post_key).child("Market Information").child("item_5").addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                if (dataSnapshot.hasChild("question_2")) {
                                    String value = dataSnapshot.child("question_2").getValue().toString();
                                    txtItem5.setText(value);
                                    if (value.equals("Alta")) {
                                        factor_5 = 3;
                                    }
                                    if (value.equals("Media")) {
                                        factor_5 = 2;
                                    }
                                    if (value.equals("Débil")) {
                                        factor_5 = 1;
                                    }

                                    int average = (factor_1+factor_2+factor_3+factor_4+factor_5)/5;
                                    if (average == 1) {
                                        txtResult.setText("Resultado: Débil");
                                    }
                                    if (average == 2) {
                                        txtResult.setText("Resultado: Media");
                                    }
                                    if (average == 3) {
                                        txtResult.setText("Resultado: Alta");
                                    }

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

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        btnItem1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = "Competidores Potenciales";
                String question_1 = "¿Quiénes serían nuestros competidores potenciales? ";
                String question_2 = "¿Qué impacto crees que tendría tu empresa frente al ingreso de competidores potenciales?";
                ArrayList<String> arr =new ArrayList<>();
                String path = "item_1";

                arr.add("Proveedores de nuestros insumos");arr.add("Distribuidores de nuestros productos/servicios");
                arr.add("Intermediarios comerciales");arr.add("Competencia indirecta que ofece un producto/servicio complementario al nuestro");

                showMarketinInformationDialog(title,question_1,question_2,arr,path);
            }
        });
        btnItem2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = "Proveedores";
                String question_1 = "¿Quiénes son y cómo influyen en nuestro mercado? (";
                String question_2 = "¿Qué influencia tienen tus proveedores en tu empresa ?";
                ArrayList<String> arr =new ArrayList<>();
                String path = "item_2";

                arr.add("Proveedores de mi actividad principal"); arr.add("Proveedores complementarios a mi actividad principal");
                arr.add("Controlan el mercado por precio y/o producto/servicio"); arr.add("Organizados"); arr.add("Desorganizados");

                showMarketinInformationDialog(title,question_1,question_2,arr,path);
            }
        });

        btnItem3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = "Competidores del Mercado";
                String question_1 = "¿Cómo es la rivalidad entre tu empresa y tus competidores? ";
                String question_2 = "¿Qué grado de Rivalidad crees que tine tu empresa frente a tus competidores?";
                ArrayList<String> arr =new ArrayList<>();
                String path = "item_3";

                arr.add("Precios sensibles (subidas y bajadas)"); arr.add("Campañas continuas de publicidad"); arr.add("Intensidad de tus comeptidores por ganar mayor presencia en el mercado");
                arr.add("Concentración de competidores (pocas empresas pero especializadas) que dominan el mercado"); arr.add("Barreras de entrada (inversión alta para entrar a competir)"); arr.add("Barreras de salida (por los altos costos complementarios a la liquidación o salida del producto)");

                showMarketinInformationDialog(title,question_1,question_2,arr,path);
            }
        });

        btnItem4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = "Compradores";
                String question_1 = "¿Qué influencia tiene tus clientes en tu mercado? ";
                String question_2 = "¿Qué impacto tendría tu empresa frente a nuevas necesidades y requerimientos de tus clientes?";
                ArrayList<String> arr =new ArrayList<>();
                String path = "item_4";

                arr.add("Capacidad de ponernos bajo presión para bajar nuestros precios"); arr.add("Preferencias variables o cambiantes, que hacen rediseñar tu producto/servicio");

                showMarketinInformationDialog(title,question_1,question_2,arr,path);
            }
        });

        btnItem5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = "Productos Sustitutos";
                String question_1 = "¿Nuestros productos/servicios pueden ser sustituídos por otros diferentes al de mi mercado?";
                String question_2 = "¿Cómo afecta la predilección de otro producto/servicio que sustituye al tuyo?";
                ArrayList<String> arr =new ArrayList<>();
                String path = "item_5";

                arr.add("Muy alta probabilidad de sustitución");   arr.add("Alta probabilidad de sustitución");   arr.add("Sustitución probable");   arr.add("Poca probabilidad de sustitución");   arr.add("Muy baja probabilidad de sustitución");

                showMarketinInformationDialog(title,question_1,question_2,arr,path);
            }
        });

    }

    private void showMarketinInformationDialog(String title, String question_1, String question_2, ArrayList<String> arr, final String path) {
        final AlertDialog dialog = new AlertDialog.Builder(this).create();

        LayoutInflater inflater = LayoutInflater.from(this);
        View finance_method = inflater.inflate(R.layout.market_information_data_dialog,null);

        TextView txtTitle,txtQuestion1,txtQuestion2;
        final Button btnAnswer,btnFinish;
        final RadioButton rdAnswer1,rdAnswer2,rdAnswer3;
        final SpinnerDialog spinnerDialog;
        final LinearLayout rootLayout;

        txtTitle = finance_method.findViewById(R.id.txtTitle);
        txtQuestion1 = finance_method.findViewById(R.id.txtQuestion1);
        txtQuestion2 = finance_method.findViewById(R.id.txtQuestion2);
        btnAnswer = finance_method.findViewById(R.id.btnAnswer);
        rdAnswer1 = finance_method.findViewById(R.id.rdAnswer1);
        rdAnswer2 = finance_method.findViewById(R.id.rdAnswer2);
        rdAnswer3 = finance_method.findViewById(R.id.rdAnswer3);
        btnFinish =finance_method.findViewById(R.id.btnFinish);
        rootLayout = finance_method.findViewById(R.id.rootLayout);

        txtTitle.setText(title);
        txtQuestion1.setText(question_1);
        txtQuestion2.setText(question_2);

        spinnerDialog = new SpinnerDialog(this,arr, "Selecciona una de las alternativas");
        btnAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinnerDialog.showSpinerDialog();
            }
        });

        spinnerDialog.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String item2, int position2) {
                btnAnswer.setText(item2);

            }
        });

        rdAnswer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answer_message = rdAnswer1.getText().toString();
            }
        });
        rdAnswer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answer_message = rdAnswer2.getText().toString();
            }
        });
        rdAnswer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answer_message = rdAnswer3.getText().toString();
            }
        });

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!rdAnswer1.isChecked() && !rdAnswer2.isChecked() && !rdAnswer3.isChecked()) {
                    Snackbar.make(rootLayout,"Debes seleccionar una alterantiva",Snackbar.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(btnAnswer.getText().toString())) {
                    Snackbar.make(rootLayout,"Debes seleccionar una alterantiva",Snackbar.LENGTH_SHORT).show();
                } else {
                    companyRef.child(post_key).child("Market Information").child(path).child("question_1").setValue(btnAnswer.getText().toString());
                    companyRef.child(post_key).child("Market Information").child(path).child("question_2").setValue(answer_message);
                    Toasty.success(MarketInformationActivity.this, "Actualizado", Toast.LENGTH_LONG).show();
                    dialog.dismiss();
                }
            }
        });

        dialog.setView(finance_method);
        dialog.show();
    }
}
