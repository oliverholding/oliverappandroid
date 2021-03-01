package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.StrategicDirection.IndustryInformation;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.StrategicDirection.LeanCanvas.LeanCanvasActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;
import in.galaxyofandroid.spinerdialog.OnSpinerItemClick;
import in.galaxyofandroid.spinerdialog.SpinnerDialog;

public class EnviromentIndustryInformationFragment extends Fragment {

    ImageView btnItem1,btnItem2,btnItem3,btnItem4,btnItem5,btnItem6;
    DatabaseReference companyRef;
    String post_key,answer_selected_1,answer_selected_2;
    TextView   txtItem1Answer1,txtItem1Answer2,txtItem1Answer3, txtItem2Answer1,txtItem2Answer2,txtItem2Answer3, txtItem3Answer1,txtItem3Answer2,txtItem3Answer3, txtItem4Answer1,txtItem4Answer2,txtItem4Answer3, txtItem5Answer1,txtItem5Answer2,txtItem5Answer3, txtItem6Answer1,txtItem6Answer2,txtItem6Answer3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_enviroment_industry_information, container, false);

        post_key = getActivity().getIntent().getExtras().getString("post_key");
        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");

        btnItem1 = view.findViewById(R.id.btnItem1);
        btnItem2 = view.findViewById(R.id.btnItem2);
        btnItem3 = view.findViewById(R.id.btnItem3);
        btnItem4 = view.findViewById(R.id.btnItem4);
        btnItem5 = view.findViewById(R.id.btnItem5);
        btnItem6 = view.findViewById(R.id.btnItem6);

        txtItem1Answer1 = view.findViewById(R.id.txtItem1Answer1);
        txtItem1Answer2 = view.findViewById(R.id.txtItem1Answer2);
        txtItem1Answer3 = view.findViewById(R.id.txtItem1Answer3);
        txtItem2Answer1 = view.findViewById(R.id.txtItem2Answer1);
        txtItem2Answer2 = view.findViewById(R.id.txtItem2Answer2);
        txtItem2Answer3 = view.findViewById(R.id.txtItem2Answer3);
        txtItem3Answer1 = view.findViewById(R.id.txtItem3Answer1);
        txtItem3Answer2 = view.findViewById(R.id.txtItem3Answer2);
        txtItem3Answer3 = view.findViewById(R.id.txtItem3Answer3);
        txtItem4Answer1 = view.findViewById(R.id.txtItem4Answer1);
        txtItem4Answer2 = view.findViewById(R.id.txtItem4Answer2);
        txtItem4Answer3 = view.findViewById(R.id.txtItem4Answer3);

        companyRef.child(post_key).child("Environmental Analysis").child("item_1").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild("answer_1")) {
                    String value = dataSnapshot.child("answer_1").getValue().toString();
                    txtItem1Answer1.setText(value);
                }
                if (dataSnapshot.hasChild("answer_2")) {
                    String value = dataSnapshot.child("answer_2").getValue().toString();
                    txtItem1Answer2.setText(value);
                }
                if (dataSnapshot.hasChild("answer_3")) {
                    String value = dataSnapshot.child("answer_3").getValue().toString();
                    txtItem1Answer3.setText(value);
                }

                companyRef.child(post_key).child("Environmental Analysis").child("item_2").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChild("answer_1")) {
                            String value = dataSnapshot.child("answer_1").getValue().toString();
                            txtItem2Answer1.setText(value);
                        }
                        if (dataSnapshot.hasChild("answer_2")) {
                            String value = dataSnapshot.child("answer_2").getValue().toString();
                            txtItem2Answer2.setText(value);
                        }
                        if (dataSnapshot.hasChild("answer_3")) {
                            String value = dataSnapshot.child("answer_3").getValue().toString();
                            txtItem2Answer3.setText(value);
                        }

                        companyRef.child(post_key).child("Environmental Analysis").child("item_3").addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                if (dataSnapshot.hasChild("answer_1")) {
                                    String value = dataSnapshot.child("answer_1").getValue().toString();
                                    txtItem3Answer1.setText(value);
                                }
                                if (dataSnapshot.hasChild("answer_2")) {
                                    String value = dataSnapshot.child("answer_2").getValue().toString();
                                    txtItem3Answer2.setText(value);
                                }
                                if (dataSnapshot.hasChild("answer_3")) {
                                    String value = dataSnapshot.child("answer_3").getValue().toString();
                                    txtItem3Answer3.setText(value);
                                }

                                companyRef.child(post_key).child("Environmental Analysis").child("item_4").addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        if (dataSnapshot.hasChild("answer_1")) {
                                            String value = dataSnapshot.child("answer_1").getValue().toString();
                                            txtItem4Answer1.setText(value);
                                        }
                                        if (dataSnapshot.hasChild("answer_2")) {
                                            String value = dataSnapshot.child("answer_2").getValue().toString();
                                            txtItem4Answer2.setText(value);
                                        }
                                        if (dataSnapshot.hasChild("answer_3")) {
                                            String value = dataSnapshot.child("answer_3").getValue().toString();
                                            txtItem4Answer3.setText(value);
                                        }

                                        companyRef.child(post_key).child("Environmental Analysis").child("item_5").addValueEventListener(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                if (dataSnapshot.hasChild("answer_1")) {
                                                    String value = dataSnapshot.child("answer_1").getValue().toString();
                                                    txtItem5Answer1.setText(value);
                                                }
                                                if (dataSnapshot.hasChild("answer_2")) {
                                                    String value = dataSnapshot.child("answer_2").getValue().toString();
                                                    txtItem5Answer2.setText(value);
                                                }
                                                if (dataSnapshot.hasChild("answer_3")) {
                                                    String value = dataSnapshot.child("answer_3").getValue().toString();
                                                    txtItem5Answer3.setText(value);
                                                }

                                                companyRef.child(post_key).child("Environmental Analysis").child("item_6").addValueEventListener(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                                        if (dataSnapshot.hasChild("answer_1")) {
                                                            String value = dataSnapshot.child("answer_1").getValue().toString();
                                                            txtItem6Answer1.setText(value);
                                                        }
                                                        if (dataSnapshot.hasChild("answer_2")) {
                                                            String value = dataSnapshot.child("answer_2").getValue().toString();
                                                            txtItem6Answer2.setText(value);
                                                        }
                                                        if (dataSnapshot.hasChild("answer_3")) {
                                                            String value = dataSnapshot.child("answer_3").getValue().toString();
                                                            txtItem6Answer3.setText(value);
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

        btnItem1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = "Análisis Político";
                String question_1 = "a. ¿En la actualidad existe estabilidad política en el país?";
                String answer_1_1 = "Estable";
                String answer_1_2 = "Inestable";
                String question_2 = "b. ¿En los siguientes años crees que mejorará ó empeorará?";
                String answer_2_1 = "Mejorará";
                String answer_2_2 = "Empeorará";
                String question_3 = "c. Del siguiente listado elige una o varias opciones que consideres que se dá en la política peruana con respecto a tu sector.";
                String path = "item_1";
                ArrayList<String> arr =new ArrayList<>();

                arr.add("Eliminación de la burocracia a través de trámites online");arr.add("Impulso a la inclusión financiera");arr.add("Acuerdos internacionales entre países");arr.add("Separación de Poderes (Ejecutivo, Legislativo y Judicial)");arr.add("Competitividad y Productividad");arr.add("Impulso a los concurso públicos (invierte.pe)");arr.add("Inclusión social");

                showSetDataDialog(title,question_1,answer_1_1,answer_1_2,question_2,answer_2_1,answer_2_2,question_3,arr,path);
            }
        });

        btnItem2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = "Análisis Económico";
                String question_1 = "a. ¿ Cómo consideras la economía peruana en la actualidad?";
                String answer_1_1 = "Estable";
                String answer_1_2 = "Inestable";
                String question_2 = "b. ¿En los siguientes años crees que mejorará ó empeorará?";
                String answer_2_1 = "Mejorará";
                String answer_2_2 = "Empeorará";
                String question_3 = "c. Del siguiente listado elige una o varias opciones que consideres que se dá en la economía peruana con respecto a tu sector";
                String path = "item_2";
                ArrayList<String> arr =new ArrayList<>();

                arr.add("Obras por Impuestos"); arr.add("Presupuesto de Inversión Pública"); arr.add("Asociaciones Público-Privadas, unión entre una empresa privada y otra pública para la ejecución de un proyecto"); arr.add("Política tributaria personas naturales y jurídicas");
                arr.add("Atracción de financiación extranjera para el sector"); arr.add("Política de Créditos (facilidades de pago con programas nacionales de garantía y apoyo al sector)"); arr.add("Nivel de Precios : comparativa de precios de producto o servicio con el período anterior"); arr.add("Relación la demanda (lo que se requiere) y la oferta (lo que se produce) para evitar acaparamiento y establecimiento de precios no acordes");

                showSetDataDialog(title,question_1,answer_1_1,answer_1_2,question_2,answer_2_1,answer_2_2,question_3,arr,path);
            }
        });

        btnItem3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = "Análisis Social";
                String question_1 = "a. ¿Conoces las tendencias y las necesidades de tus potenciales clientes?";
                String answer_1_1 = "Existen  nuevas tendencias y necesidades";
                String answer_1_2 = "No suelen cambiar las tendencias y necesidades";
                String question_2 = "b. ¿En los siguientes años crees que mejorará ó empeorará?";
                String answer_2_1 = "Mejorará";
                String answer_2_2 = "Empeorará";
                String question_3 = "c. Del siguiente listado elige una o varias opciones que consideres que se dá en la sociedad peruana con respecto a tu sector";
                String path = "item_3";
                ArrayList<String> arr =new ArrayList<>();

                arr.add("Crecimiento de la Población");arr.add("Nueva clase emergente y trabajadora");arr.add("Programas de desarrollo para poblaciones en riesgo de exclusión");

                showSetDataDialog(title,question_1,answer_1_1,answer_1_2,question_2,answer_2_1,answer_2_2,question_3,arr,path);
            }
        });

        btnItem4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = "Análisis Tecnológico";
                String question_1 = "a. ¿El uso de la tecnología ha revolucionado y condiciona a tu sector? ";
                String answer_1_1 = "Impacta";
                String answer_1_2 = "No impacta";
                String question_2 = "b. ¿Crees que aumentará ó disminuirá su uso?";
                String answer_2_1 = "Aumentará";
                String answer_2_2 = "Disminuirá";
                String question_3 = "c. Del siguiente listado elige una o varias opciones que consideres que se dá en el uso de la tecnología con respecto a tu sector";
                String path = "item_4";
                ArrayList<String> arr =new ArrayList<>();

                arr.add("Transformación digital");arr.add("Nuevos Hardware y Software");arr.add("Inclusión tecnológica (acceso a tecnología mundial y de punta)");

                showSetDataDialog(title,question_1,answer_1_1,answer_1_2,question_2,answer_2_1,answer_2_2,question_3,arr,path);
            }
        });

        btnItem5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = "Análisis Medioambiental";
                String question_1 = "a. ¿Tu negocio es muy sensible a desastres y cambios naturales del cuál te puede dejar sin trabajar? ";
                String answer_1_1 = "Afecta";
                String answer_1_2 = "No afecta";
                String question_2 = "b. ¿Crees que aumentará ó disminuirán los impactos medioambientales?";
                String answer_2_1 = "Aumentarán";
                String answer_2_2 = "Disminuirán";
                String question_3 = "c. Del siguiente listado elige una o varias opciones que consideres que se dá en el impacto medioambietal con respecto a tu sector";
                String path = "item_5";
                ArrayList<String> arr =new ArrayList<>();

                arr.add("Implantación de sistemas de seguridad medioambiental");arr.add("Leyes de Protección Medioambiental");

                showSetDataDialog(title,question_1,answer_1_1,answer_1_2,question_2,answer_2_1,answer_2_2,question_3,arr,path);
            }
        });

        btnItem6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = "Análisis Legal";
                String question_1 = "a. ¿Conoces las leyes y normas relacionado a tu actividad y al sector, que ante un cambio de las mismas, puede condicionar seguir funcionando?";
                String answer_1_1 = "Condiciona";
                String answer_1_2 = "No condiciona";
                String question_2 = "b. ¿Crees que aumentará ó disminuirán la creación y/o amplicación de las leyes relacuionadas a tu sector?";
                String answer_2_1 = "Aumentarán";
                String answer_2_2 = "Disminuirán";
                String question_3 = "c. Del siguiente listado elige una o varias opciones que consideres que se dá un impacto legal con respecto a tu sector";
                String path = "item_6";
                ArrayList<String> arr =new ArrayList<>();

                arr.add("Ley General de sociedades"); arr.add("Licencias Municipales"); arr.add("Ley de Protección al empleador y trabajador"); arr.add("Ley de Pensiones (ONP y AFP´S)");

                showSetDataDialog(title,question_1,answer_1_1,answer_1_2,question_2,answer_2_1,answer_2_2,question_3,arr,path);
            }
        });

        return view;
    }

    private void showSetDataDialog(String title, String question_1, String answer_1_1, String answer_1_2, String question_2, String answer_2_1, String answer_2_2, String question_3, ArrayList<String> arr, final String path) {

        final AlertDialog dialog = new AlertDialog.Builder(getActivity()).create();

        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View finance_method = inflater.inflate(R.layout.enviroment_industry_information_dialog,null);

        TextView txtTitle,txtQuestion1,txtQuestion2,txtQuestion3;
        final RadioButton rdAnswer1_1,rdAnswer1_2,rdAnswer2_1,rdAnswer2_2;
        final Button btnAnswer3,btnFinish;
        final SpinnerDialog spinnerDialog;
        final LinearLayout rootLayout;

        txtTitle = finance_method.findViewById(R.id.txtTitle);
        txtQuestion1 = finance_method.findViewById(R.id.txtQuestion1);
        txtQuestion2 = finance_method.findViewById(R.id.txtQuestion2);
        txtQuestion3 = finance_method.findViewById(R.id.txtQuestion3);
        rdAnswer1_1 = finance_method.findViewById(R.id.rdAnswer1_1);
        rdAnswer1_2 = finance_method.findViewById(R.id.rdAnswer1_2);
        rdAnswer2_1 = finance_method.findViewById(R.id.rdAnswer2_1);
        rdAnswer2_2 = finance_method.findViewById(R.id.rdAnswer2_2);
        btnAnswer3 = finance_method.findViewById(R.id.btnAnswer3);
        btnFinish = finance_method.findViewById(R.id.btnFinish);
        rootLayout = finance_method.findViewById(R.id.rootLayout);

        txtTitle.setText(title);
        txtQuestion1.setText(question_1);
        txtQuestion2.setText(question_2);
        txtQuestion3.setText(question_3);
        rdAnswer1_1.setText(answer_1_1);
        rdAnswer1_2.setText(answer_1_2);
        rdAnswer2_1.setText(answer_2_1);
        rdAnswer2_2.setText(answer_2_2);

        spinnerDialog = new SpinnerDialog(getActivity(),arr, "Selecciona una de las alternativas");
        btnAnswer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinnerDialog.showSpinerDialog();
            }
        });


        spinnerDialog.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String item2, int position2) {
                btnAnswer3.setText(item2);

            }
        });

        rdAnswer1_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answer_selected_1 = rdAnswer1_1.getText().toString();
            }
        });
        rdAnswer1_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answer_selected_1 = rdAnswer1_2.getText().toString();
            }
        });
        rdAnswer2_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answer_selected_2 = rdAnswer2_1.getText().toString();
            }
        });
        rdAnswer2_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answer_selected_2 = rdAnswer2_2.getText().toString();
            }
        });


        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!rdAnswer1_1.isChecked() && !rdAnswer1_2.isChecked()) {
                    Snackbar.make(rootLayout,"Debes seleccionar una respuesta",Snackbar.LENGTH_SHORT).show();
                }
                else if (!rdAnswer2_1.isChecked() && !rdAnswer2_2.isChecked()) {
                    Snackbar.make(rootLayout,"Debes seleccionar una respuesta",Snackbar.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(btnAnswer3.getText().toString())) {
                    Snackbar.make(rootLayout, "Debes seleccionar una respuesta", Snackbar.LENGTH_SHORT).show();
                } else {
                    companyRef.child(post_key).child("Environmental Analysis").child(path).child("answer_1").setValue(answer_selected_1);
                    companyRef.child(post_key).child("Environmental Analysis").child(path).child("answer_2").setValue(answer_selected_2);
                    companyRef.child(post_key).child("Environmental Analysis").child(path).child("answer_3").setValue(btnAnswer3.getText().toString());
                    Toasty.success(getActivity(), "Actualizado", Toast.LENGTH_LONG).show();
                    dialog.dismiss();
                }
            }
        });

        dialog.setView(finance_method);
        dialog.show();
    }
}
