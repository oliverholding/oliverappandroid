package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.StrategicDirection.BusinessModel;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import org.w3c.dom.Text;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;
import in.galaxyofandroid.spinerdialog.OnSpinerItemClick;
import in.galaxyofandroid.spinerdialog.SpinnerDialog;

public class BusinessModelActivity extends AppCompatActivity {

    ImageView btnQuestion1,btnQuestion2,btnQuestion3,btnQuestion4,btnQuestion5,btnQuestion6,btnQuestion7,btnQuestion8,btnQuestion9;
    TextView txtAnswer1,txtAnswer2,txtAnswer3,txtAnswer4,txtAnswer5,txtAnswer6,txtAnswer7,txtAnswer8,txtAnswer9,txtAnswer10,txtAnswer11,txtAnswer12,txtAnswer62;
    String post_key;
    DatabaseReference companyRef;
    String question_1,question_2,question_3,question_4;

    SpinnerDialog spinnerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_model2);

        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");
        post_key = getIntent().getExtras().getString("post_key");

        btnQuestion1 = findViewById(R.id.btnQuestion1);
        btnQuestion2 = findViewById(R.id.btnQuestion2);
        btnQuestion3 = findViewById(R.id.btnQuestion3);
        btnQuestion4 = findViewById(R.id.btnQuestion4);
        btnQuestion5 = findViewById(R.id.btnQuestion5);
        btnQuestion6 = findViewById(R.id.btnQuestion6);
        btnQuestion7 = findViewById(R.id.btnQuestion7);
        btnQuestion8 = findViewById(R.id.btnQuestion8);
        btnQuestion9 = findViewById(R.id.btnQuestion9);

        txtAnswer1 = findViewById(R.id.txtAnswer1);
        txtAnswer2 = findViewById(R.id.txtAnswer2);
        txtAnswer3 = findViewById(R.id.txtAnswer3);
        txtAnswer4 = findViewById(R.id.txtAnswer4);
        txtAnswer5 = findViewById(R.id.txtAnswer5);
        txtAnswer6 = findViewById(R.id.txtAnswer6);
        txtAnswer7 = findViewById(R.id.txtAnswer7);
        txtAnswer8 = findViewById(R.id.txtAnswer8);
        txtAnswer9 = findViewById(R.id.txtAnswer9);
        txtAnswer10 = findViewById(R.id.txtAnswer10);
        txtAnswer11 = findViewById(R.id.txtAnswer11);
        txtAnswer12 = findViewById(R.id.txtAnswer12);
        txtAnswer62 = findViewById(R.id.txtAnswer62);

        companyRef.child(post_key).child("Business Model").child("item_1").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild("question_1")) {
                    String question_1 = dataSnapshot.child("question_1").getValue().toString();
                    txtAnswer1.setText(question_1);
                }

                companyRef.child(post_key).child("Business Model").child("item_2").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChild("question_1")) {
                            String question_1 = dataSnapshot.child("question_1").getValue().toString();
                            txtAnswer2.setText(question_1);
                        }

                        companyRef.child(post_key).child("Business Model").child("item_4").addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                if (dataSnapshot.hasChild("question_1")) {
                                    String question_1 = dataSnapshot.child("question_1").getValue().toString();
                                    txtAnswer7.setText(question_1);
                                }

                                companyRef.child(post_key).child("Business Model").child("item_5").addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        if (dataSnapshot.hasChild("question_1")) {
                                            String question_1 = dataSnapshot.child("question_1").getValue().toString();
                                            txtAnswer8.setText(question_1);
                                        }

                                        companyRef.child(post_key).child("Business Model").child("item_6").addValueEventListener(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                if (dataSnapshot.hasChild("question_1")) {
                                                    String question_1 = dataSnapshot.child("question_1").getValue().toString();
                                                    txtAnswer9.setText(question_1);
                                                }

                                                companyRef.child(post_key).child("Business Model").child("item_7").addValueEventListener(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                                        if (dataSnapshot.hasChild("question_1")) {
                                                            String question_1 = dataSnapshot.child("question_1").getValue().toString();
                                                            txtAnswer10.setText(question_1);
                                                        }

                                                        companyRef.child(post_key).child("Business Model").child("item_8").addValueEventListener(new ValueEventListener() {
                                                            @Override
                                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                                if (dataSnapshot.hasChild("question_1")) {
                                                                    String question_1 = dataSnapshot.child("question_1").getValue().toString();
                                                                    txtAnswer11.setText(question_1);
                                                                }

                                                                companyRef.child(post_key).child("Business Model").child("item_9").addValueEventListener(new ValueEventListener() {
                                                                    @Override
                                                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                                                        if (dataSnapshot.hasChild("question_1")) {
                                                                            String question_1 = dataSnapshot.child("question_1").getValue().toString();
                                                                            txtAnswer12.setText(question_1);
                                                                        }
                                                                        companyRef.child(post_key).child("Business Model").child("item_3").addValueEventListener(new ValueEventListener() {
                                                                            @Override
                                                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                                                if (dataSnapshot.hasChild("question_1")) {
                                                                                    String question_1 = dataSnapshot.child("question_1").getValue().toString();
                                                                                    txtAnswer3.setText(question_1);
                                                                                }
                                                                                if (dataSnapshot.hasChild("question_2")) {
                                                                                    String question_1 = dataSnapshot.child("question_2").getValue().toString();
                                                                                    txtAnswer4.setText(question_1);
                                                                                }
                                                                                if (dataSnapshot.hasChild("question_3")) {
                                                                                    String question_1 = dataSnapshot.child("question_3").getValue().toString();
                                                                                    txtAnswer5.setText(question_1);
                                                                                }
                                                                                if (dataSnapshot.hasChild("question_4")) {
                                                                                    String question_1 = dataSnapshot.child("question_4").getValue().toString();
                                                                                    txtAnswer6.setText(question_1);
                                                                                }
                                                                                if (dataSnapshot.hasChild("question_5")) {
                                                                                    String question_1 = dataSnapshot.child("question_5").getValue().toString();
                                                                                    txtAnswer62.setText(question_1);
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

        btnQuestion1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ArrayList<String> arr =new ArrayList<>();
                String path = "item_1";

                arr.add("Producción de bienes terminados");arr.add("Comercialización de bienes");arr.add("Servicios");

                showOptionsDialog(arr,path);
            }
        });

        btnQuestion2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ArrayList<String> arr =new ArrayList<>();
                String path = "item_2";

                arr.add("Precio");arr.add("Calidad");arr.add("Servicio post venta");arr.add("Valor agregado: producto diferenciado y único");

                showOptionsDialog(arr,path);
            }
        });

        btnQuestion3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showOptionsDialogCompleted();
            }
        });

        btnQuestion4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ArrayList<String> arr =new ArrayList<>();
                String path = "item_4";

                arr.add("Publicidad en medios de comunicación (prensa escrita / prensa hablada)");arr.add("Publicidad en redes sociales. ");
                arr.add("Publicidad física (volantes).");arr.add("Descuentos.");arr.add("Promociones 2x1.");

                showOptionsDialog(arr,path);
            }
        });

        btnQuestion5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ArrayList<String> arr =new ArrayList<>();
                String path = "item_5";

                arr.add("Tomar en cuenta cada sugerencia, comentario ó opinión, para ponerlo en constante práctica");arr.add("Inculcar valores a tus empleados o en ti para que para hacer de tu negocio la elección predilecta de tus clientes.\n");
                arr.add("Comunicaciòn constante de cambios y mejoras a tus clientes.");arr.add("Soluciones a sus reclamos, por lo mínimo que fuera.");arr.add("Política de cambios/mejoras en los productos/servicios.");

                showOptionsDialog(arr,path);
            }
        });

        btnQuestion6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ArrayList<String> arr =new ArrayList<>();
                String path = "item_6";

                arr.add("Negocio novedoso o emprendedor"); arr.add("Negocio retador");
                arr.add("Negocio tradicional"); arr.add("Negocio seguidor");

                showOptionsDialog(arr,path);
            }
        });

        btnQuestion7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ArrayList<String> arr =new ArrayList<>();
                String path = "item_7";

                arr.add("Estrategia de Precio bajo / Precio diferenciado o alto");arr.add("Estrategia de Distribución: red de distribución propia, red de distribución comisionista, red de distribución contratada");
                arr.add("Estrategia de Producto/Servicio");arr.add("Estrategia de Promoción: 2x1");

                showOptionsDialog(arr,path);
            }
        });

        btnQuestion8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ArrayList<String> arr =new ArrayList<>();
                String path = "item_8";

                arr.add("Objetivo mínimo de ventas mensuales y anual (ni gana ni pierde) vs lo deseado"); arr.add("Utilidad mínima por cada producto/servicio.");

                showOptionsDialog(arr,path);
            }
        });

        btnQuestion9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ArrayList<String> arr =new ArrayList<>();
                String path = "item_9";

                arr.add("Impacto en tu zona de actuación"); arr.add("Cuidado del medio ambiente");
                arr.add("Participación ciudadana");arr.add("Licencias y certificaciones, Laborales");

                showOptionsDialog(arr,path);
            }
        });


    }

    private void showOptionsDialogCompleted() {
        final AlertDialog dialog = new AlertDialog.Builder(this).create();

        LayoutInflater inflater = LayoutInflater.from(this);
        View finance_method = inflater.inflate(R.layout.business_model_customer_dialog,null);

        final RadioButton rdMan,rdWoman,rdRange1,rdRange2,rdRange3,rdRange4,rdRange5,rdRange6,rdHigh,rdMiddle,rdLow,rdProduction,rdDistribution,rdConsuming;
        final EditText edtLocation;
        Button btnFinish;
        final LinearLayout rootLayout;

        rdMan = finance_method.findViewById(R.id.rdMan);
        rdWoman = finance_method.findViewById(R.id.rdWoman);
        rdRange1 = finance_method.findViewById(R.id.rdRange1);
        rdRange2 = finance_method.findViewById(R.id.rdRange2);
        rdRange3 = finance_method.findViewById(R.id.rdRange3);
        rdRange4 = finance_method.findViewById(R.id.rdRange4);
        rdRange5 = finance_method.findViewById(R.id.rdRange5);
        rdRange6 = finance_method.findViewById(R.id.rdRange6);
        rdHigh = finance_method.findViewById(R.id.rdHigh);
        rdMiddle = finance_method.findViewById(R.id.rdMiddle);
        rdLow = finance_method.findViewById(R.id.rdLow);
        rdProduction = finance_method.findViewById(R.id.rdProduction);
        rdDistribution = finance_method.findViewById(R.id.rdDistribution);
        rdConsuming = finance_method.findViewById(R.id.rdConsuming);
        edtLocation = finance_method.findViewById(R.id.edtLocation);
        btnFinish = finance_method.findViewById(R.id.btnFinish);
        rootLayout = finance_method.findViewById(R.id.rootLayout);

        rdMan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                question_1 = rdMan.getText().toString();
            }
        });
        rdWoman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                question_1 = rdWoman.getText().toString();
            }
        });

        rdRange1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                question_2 = rdRange1.getText().toString();
            }
        });
        rdRange2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                question_2 = rdRange2.getText().toString();
            }
        });
        rdRange3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                question_2 = rdRange3.getText().toString();
            }
        });
        rdRange4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                question_2 = rdRange4.getText().toString();
            }
        });
        rdRange5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                question_2 = rdRange5.getText().toString();
            }
        });
        rdRange6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                question_2 = rdRange6.getText().toString();
            }
        });

        rdHigh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                question_3 = rdHigh.getText().toString();
            }
        });
        rdMiddle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                question_3 = rdMiddle.getText().toString();
            }
        });
        rdLow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                question_3 = rdLow.getText().toString();
            }
        });

        rdProduction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                question_4 = rdProduction.getText().toString();
            }
        });
        rdDistribution.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                question_4= rdDistribution.getText().toString();
            }
        });
        rdConsuming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                question_4 = rdConsuming.getText().toString();
            }
        });

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!rdMan.isChecked() && !rdWoman.isChecked()) {
                    Snackbar.make(rootLayout,"Debes seleccionar una opción",Snackbar.LENGTH_SHORT).show();
                }
                else  if (!rdRange1.isChecked() && !rdRange2.isChecked() && !rdRange3.isChecked() && !rdRange4.isChecked() && !rdRange5.isChecked() && !rdRange6.isChecked()) {
                    Snackbar.make(rootLayout,"Debes seleccionar una opción",Snackbar.LENGTH_SHORT).show();
                }
                else  if (!rdHigh.isChecked() && !rdMiddle.isChecked() && !rdLow.isChecked()) {
                    Snackbar.make(rootLayout,"Debes seleccionar una opción",Snackbar.LENGTH_SHORT).show();
                } else if (!rdProduction.isChecked() && !rdDistribution.isChecked() && !rdConsuming.isChecked()) {
                    Snackbar.make(rootLayout, "Debes seleccionar una opción", Snackbar.LENGTH_SHORT).show();
                } else {
                    companyRef.child(post_key).child("Business Model").child("item_3").child("question_1").setValue(question_1);
                    companyRef.child(post_key).child("Business Model").child("item_3").child("question_2").setValue(question_2);
                    companyRef.child(post_key).child("Business Model").child("item_3").child("question_3").setValue(question_3);
                    companyRef.child(post_key).child("Business Model").child("item_3").child("question_4").setValue(question_4);
                    companyRef.child(post_key).child("Business Model").child("item_3").child("question_5").setValue(edtLocation.getText().toString());
                    Toasty.success(BusinessModelActivity.this, "Registrado", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            }
        });

        dialog.setView(finance_method);
        dialog.show();
    }

    private void showOptionsDialog(ArrayList<String> arr, final String path) {
        spinnerDialog = new SpinnerDialog(BusinessModelActivity.this,arr, "Selecciona una alternativa");
        spinnerDialog.showSpinerDialog();
        spinnerDialog.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String item2, int position2) {

                companyRef.child(post_key).child("Business Model").child(path).child("question_1").setValue(item2);
            }
        });
    }


}
