package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.ManagementControlAndReports.LessonLearned;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;

import java.util.ArrayList;

import in.galaxyofandroid.spinerdialog.OnSpinerItemClick;
import in.galaxyofandroid.spinerdialog.SpinnerDialog;


public class LessonLearnedEvaluationFragment extends Fragment {

    Button btnAnswer1,btnAnswer2,btnAnswer3,btnAnswer4,btnAnswer5,btnAnswer6,btnAnswer7,btnAnswer8,btnAnswer9,btnAnswer10,btnAnswer11,btnAnswer12,btnAnswer13,btnAnswer14,btnAnswer15,btnFinish;
    ArrayList<String> question1 =new ArrayList<>();
    SpinnerDialog spinnerQuestion1;
    ArrayList<String> question2 =new ArrayList<>();
    SpinnerDialog spinnerQuestion2;
    ArrayList<String> question3 =new ArrayList<>();
    SpinnerDialog spinnerQuestion3;
    ArrayList<String> question4 =new ArrayList<>();
    SpinnerDialog spinnerQuestion4;
    ArrayList<String> question5 =new ArrayList<>();
    SpinnerDialog spinnerQuestion5;
    ArrayList<String> question6 =new ArrayList<>();
    SpinnerDialog spinnerQuestion6;
    ArrayList<String> question7 =new ArrayList<>();
    SpinnerDialog spinnerQuestion7;
    ArrayList<String> question8 =new ArrayList<>();
    SpinnerDialog spinnerQuestion8;
    ArrayList<String> question9 =new ArrayList<>();
    SpinnerDialog spinnerQuestion9;
    ArrayList<String> question10 =new ArrayList<>();
    SpinnerDialog spinnerQuestion10;
    ArrayList<String> question11 =new ArrayList<>();
    SpinnerDialog spinnerQuestion11;
    ArrayList<String> question12 =new ArrayList<>();
    SpinnerDialog spinnerQuestion12;
    ArrayList<String> question13 =new ArrayList<>();
    SpinnerDialog spinnerQuestion13;
    ArrayList<String> question14 =new ArrayList<>();
    SpinnerDialog spinnerQuestion14;
    ArrayList<String> question15 =new ArrayList<>();
    SpinnerDialog spinnerQuestion15;
    RelativeLayout rootLayout;
    String post_key;
    DatabaseReference companyRef;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lesson_learned_evaluation, container, false);

        btnAnswer1 = view.findViewById(R.id.btnAnswer1);
        btnAnswer2 = view.findViewById(R.id.btnAnswer2);
        btnAnswer3 = view.findViewById(R.id.btnAnswer3);
        btnAnswer4 = view.findViewById(R.id.btnAnswer4);
        btnAnswer5 = view.findViewById(R.id.btnAnswer5);
        btnAnswer6 = view.findViewById(R.id.btnAnswer6);
        btnAnswer7 = view.findViewById(R.id.btnAnswer7);
        btnAnswer8 = view.findViewById(R.id.btnAnswer8);
        btnAnswer9 = view.findViewById(R.id.btnAnswer9);
        btnAnswer10 = view.findViewById(R.id.btnAnswer10);
        btnAnswer11 = view.findViewById(R.id.btnAnswer11);
        btnAnswer12 = view.findViewById(R.id.btnAnswer12);
        btnAnswer13 = view.findViewById(R.id.btnAnswer13);
        btnAnswer14 = view.findViewById(R.id.btnAnswer14);
        btnAnswer15 = view.findViewById(R.id.btnAnswer15);
        btnFinish = view.findViewById(R.id.btnFinish);
        rootLayout = view.findViewById(R.id.rootLayout);

        post_key = getActivity().getIntent().getExtras().getString("post_key");
        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");


        question1.add("Es el impuesto a la rentabilidad");question1.add("Es un impuesto aplicado a las ventas");question1.add("Es el porcentaje de ganancia");

        btnAnswer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                spinnerQuestion1.showSpinerDialog();
            }
        });

        spinnerQuestion1 = new SpinnerDialog(getActivity(),question1, "Selecciona la respuesta correcta");
        spinnerQuestion1.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String item2, int position2) {
                btnAnswer1.setText(item2);

            }
        });

        question2.add("Sirve para analizar el monto de ventas de mis productos");question2.add("Sirve para crear productos");question2.add("Sirve para analizar las características de mis clientes");

        btnAnswer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                spinnerQuestion2.showSpinerDialog();
            }
        });

        spinnerQuestion2 = new SpinnerDialog(getActivity(),question2, "Selecciona la respuesta correcta");
        spinnerQuestion2.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String item2, int position2) {
                btnAnswer2.setText(item2);

            }
        });

        question3.add("Deuda vencida se refiere a las boletas o facturas de mayor monto y deuda vigente son las boletas o facturas de menor monto"); question3.add("Deuda vencida se refiere a las boletas o facturas que no te han pagado antes de la fecha de vencimiento y deuda vigente son las boletas o facturas que no te han pagado después de la fecha de vencimiento"); question3.add("Deuda vencida se refiere a las boletas o facturas que no te han pagado después de la fecha de vencimiento y deuda vigente son las boletas o facturas que no te han pagado antes de la fecha de vencimiento");

        btnAnswer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                spinnerQuestion3.showSpinerDialog();
            }
        });

        spinnerQuestion3 = new SpinnerDialog(getActivity(),question3, "Selecciona la respuesta correcta");
        spinnerQuestion3.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String item2, int position2) {
                btnAnswer3.setText(item2);

            }
        });

        question4.add("Porque nos permite identificar el producto que genera más ventas"); question4.add("Porque nos permite identificar al cliente que puede ser una competencia para mi negocio"); question4.add("Porque nos permite identidicar a nuestros clientes que tienen más probabilidades de comprarnos y a los que no tanto");

        btnAnswer4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                spinnerQuestion4.showSpinerDialog();
            }
        });

        spinnerQuestion4 = new SpinnerDialog(getActivity(),question4, "Selecciona la respuesta correcta");
        spinnerQuestion4.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String item2, int position2) {
                btnAnswer4.setText(item2);

            }
        });

        question5.add("Porque nos ayuda a saber cuánto venderemos el siguiente período y a tomar decisiones importantes"); question5.add("Porque nos ayuda a vender más"); question5.add("Porque nos permite predecir qué empresa venderá más el siguiente período");

        btnAnswer5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                spinnerQuestion5.showSpinerDialog();
            }
        });

        spinnerQuestion5 = new SpinnerDialog(getActivity(),question5, "Selecciona la respuesta correcta");
        spinnerQuestion5.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String item2, int position2) {
                btnAnswer5.setText(item2);

            }
        });

        question6.add("Son documentos que registran los bienes y servicios adquiridos por la empresa"); question6.add("Son documentos que sirven para preguntar el precio de productos"); question6.add("Son instrumentos que sirven para hacer una evaluació de proveedores");

        btnAnswer6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                spinnerQuestion6.showSpinerDialog();
            }
        });

        spinnerQuestion6 = new SpinnerDialog(getActivity(),question6, "Selecciona la respuesta correcta");
        spinnerQuestion6.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String item2, int position2) {
                btnAnswer6.setText(item2);

            }
        });

        question7.add("Verificar que el stock registrado en el sistema es igual al stock real en nuestro almacén");question7.add("Verificar que le precio total del stock no sea mayor al anterior período");question7.add("Verificar que el stock actual sea igual al del año pasado");

        btnAnswer7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                spinnerQuestion7.showSpinerDialog();
            }
        });

        spinnerQuestion7 = new SpinnerDialog(getActivity(),question7, "Selecciona la respuesta correcta");
        spinnerQuestion7.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String item2, int position2) {
                btnAnswer7.setText(item2);

            }
        });

        question8.add("Es un documento que permite la comercialización del producto o servicio");question8.add("Es el documento que especifica todas las características técnicas del producto o servicio");question8.add("Es un documento para promocionar mis productos o servicios");

        btnAnswer8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                spinnerQuestion8.showSpinerDialog();
            }
        });

        spinnerQuestion8 = new SpinnerDialog(getActivity(),question8, "Selecciona la respuesta correcta");
        spinnerQuestion8.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String item2, int position2) {
                btnAnswer8.setText(item2);

            }
        });

        question9.add("Funciona como un proceso de transformación de insumos para tener un producto final");question9.add("Funciona como un proceso de adquición de materias primas");question9.add("Funciona como un proceso de venta de productos");

        btnAnswer9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                spinnerQuestion9.showSpinerDialog();
            }
        });

        spinnerQuestion9 = new SpinnerDialog(getActivity(),question9, "Selecciona la respuesta correcta");
        spinnerQuestion9.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String item2, int position2) {
                btnAnswer9.setText(item2);

            }
        });

        question10.add("Sirve para comprar productos a mi proveedor sin pagar impuestos");question10.add("Sirve como comprobante de pago para mis clientes");question10.add("Sirven para declarar un pago por un servicio prestado y declarar el impuesto de 4ta categoría en caso corresponda");

        btnAnswer10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                spinnerQuestion10.showSpinerDialog();
            }
        });

        spinnerQuestion10 = new SpinnerDialog(getActivity(),question10, "Selecciona la respuesta correcta");
        spinnerQuestion10.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String item2, int position2) {
                btnAnswer10.setText(item2);

            }
        });

        question11.add("Porque evita sanciones por parte de las autoridades y mejora nuestra reputación como empresa");question11.add("Porque es uno de los pilares para mejorar la cadena de producción");question11.add("Porque nos certifica como empresa libre emisión de gases contaminantes");

        btnAnswer11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                spinnerQuestion11.showSpinerDialog();
            }
        });

        spinnerQuestion11 = new SpinnerDialog(getActivity(),question11, "Selecciona la respuesta correcta");
        spinnerQuestion11.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String item2, int position2) {
                btnAnswer11.setText(item2);

            }
        });

        question12.add("Sirve para calcular el precio de los activos de la empresa");question12.add("Sirve para analizar los indicadores de rentabilidad de la empresa");question12.add("Sirve para registrar las entradas y salidas de dinero de la empresa");

        btnAnswer12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                spinnerQuestion12.showSpinerDialog();
            }
        });

        spinnerQuestion12 = new SpinnerDialog(getActivity(),question12, "Selecciona la respuesta correcta");
        spinnerQuestion12.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String item2, int position2) {
                btnAnswer12.setText(item2);

            }
        });

        question13.add("Es una herramienta que nos permite construir nuestro modelo de negocio y definir nuestra propuesta de valor");question13.add("Es una herramienta que nos permite proyectar las ventas en unidades");question13.add("Es una herramienta que permite mejorar la cadena de abastecimiento");

        btnAnswer13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                spinnerQuestion13.showSpinerDialog();
            }
        });

        spinnerQuestion13 = new SpinnerDialog(getActivity(),question13, "Selecciona la respuesta correcta");
        spinnerQuestion13.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String item2, int position2) {
                btnAnswer13.setText(item2);

            }
        });

        question14.add("Permite identificar objetivos e indicadores por área estableciendo metas");question14.add("Permite crear un modelo de negocio innovador");question14.add("Permite recolectar información de los productos y servicios de mi empresa");

        btnAnswer14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                spinnerQuestion14.showSpinerDialog();
            }
        });

        spinnerQuestion14 = new SpinnerDialog(getActivity(),question14, "Selecciona la respuesta correcta");
        spinnerQuestion14.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String item2, int position2) {
                btnAnswer14.setText(item2);

            }
        });

        question15.add("Porque es un documento que nos permite formalizar el negocio oficialmente");question15.add("Porque nos permite presentar nuestro negocio de forma detallada a inversionistas, socios u otros interesados para obtener financiamiento o para mostrar los objetivos y estrategias de la empresa");question15.add("Porque es un documento destinado a los clientes con el objetivo de fidelizarlos");

        btnAnswer15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                spinnerQuestion15.showSpinerDialog();
            }
        });

        spinnerQuestion15 = new SpinnerDialog(getActivity(),question15, "Selecciona la respuesta correcta");
        spinnerQuestion15.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String item2, int position2) {
                btnAnswer15.setText(item2);

            }
        });

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(btnAnswer1.getText().toString())) {
                    Snackbar.make(rootLayout,"Debes seleccionar una respuesta",Snackbar.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(btnAnswer2.getText().toString())) {
                    Snackbar.make(rootLayout,"Debes seleccionar una respuesta",Snackbar.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(btnAnswer3.getText().toString())) {
                    Snackbar.make(rootLayout,"Debes seleccionar una respuesta",Snackbar.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(btnAnswer4.getText().toString())) {
                    Snackbar.make(rootLayout,"Debes seleccionar una respuesta",Snackbar.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(btnAnswer5.getText().toString())) {
                    Snackbar.make(rootLayout,"Debes seleccionar una respuesta",Snackbar.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(btnAnswer6.getText().toString())) {
                    Snackbar.make(rootLayout,"Debes seleccionar una respuesta",Snackbar.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(btnAnswer7.getText().toString())) {
                    Snackbar.make(rootLayout,"Debes seleccionar una respuesta",Snackbar.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(btnAnswer8.getText().toString())) {
                    Snackbar.make(rootLayout,"Debes seleccionar una respuesta",Snackbar.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(btnAnswer9.getText().toString())) {
                    Snackbar.make(rootLayout,"Debes seleccionar una respuesta",Snackbar.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(btnAnswer10.getText().toString())) {
                    Snackbar.make(rootLayout,"Debes seleccionar una respuesta",Snackbar.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(btnAnswer11.getText().toString())) {
                    Snackbar.make(rootLayout,"Debes seleccionar una respuesta",Snackbar.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(btnAnswer12.getText().toString())) {
                    Snackbar.make(rootLayout,"Debes seleccionar una respuesta",Snackbar.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(btnAnswer13.getText().toString())) {
                    Snackbar.make(rootLayout,"Debes seleccionar una respuesta",Snackbar.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(btnAnswer14.getText().toString())) {
                    Snackbar.make(rootLayout,"Debes seleccionar una respuesta",Snackbar.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(btnAnswer15.getText().toString())) {
                    Snackbar.make(rootLayout, "Debes seleccionar una respuesta", Snackbar.LENGTH_SHORT).show();
                } else {

                    if (btnAnswer1.getText().toString().equals("Es un impuesto aplicado a las ventas")) {
                        companyRef.child(post_key).child("Lesson Learned Evaluation").child("question_1").setValue(1);
                    } else {
                        companyRef.child(post_key).child("Lesson Learned Evaluation").child("question_1").setValue(0);
                    }

                    if (btnAnswer2.getText().toString().equals("Sirve para analizar las características de mis clientes")) {
                        companyRef.child(post_key).child("Lesson Learned Evaluation").child("question_2").setValue(1);
                    } else {
                        companyRef.child(post_key).child("Lesson Learned Evaluation").child("question_2").setValue(0);
                    }

                    if (btnAnswer3.getText().toString().equals("Deuda vencida se refiere a las boletas o facturas que no te han pagado después de la fecha de vencimiento y deuda vigente son las boletas o facturas que no te han pagado antes de la fecha de vencimiento")) {
                        companyRef.child(post_key).child("Lesson Learned Evaluation").child("question_3").setValue(1);
                    } else {
                        companyRef.child(post_key).child("Lesson Learned Evaluation").child("question_3").setValue(0);
                    }

                    if (btnAnswer4.getText().toString().equals("Porque nos permite identidicar a nuestros clientes que tienen más probabilidades de comprarnos y a los que no tanto")) {
                        companyRef.child(post_key).child("Lesson Learned Evaluation").child("question_4").setValue(1);
                    } else {
                        companyRef.child(post_key).child("Lesson Learned Evaluation").child("question_4").setValue(0);
                    }

                    if (btnAnswer5.getText().toString().equals("Porque nos ayuda a saber cuánto venderemos el siguiente período y a tomar decisiones importantes")) {
                        companyRef.child(post_key).child("Lesson Learned Evaluation").child("question_5").setValue(1);
                    } else {
                        companyRef.child(post_key).child("Lesson Learned Evaluation").child("question_5").setValue(0);
                    }

                    if (btnAnswer6.getText().toString().equals("Son documentos que registran los bienes y servicios adquiridos por la empresa")) {
                        companyRef.child(post_key).child("Lesson Learned Evaluation").child("question_6").setValue(1);
                    } else {
                        companyRef.child(post_key).child("Lesson Learned Evaluation").child("question_6").setValue(0);
                    }

                    if (btnAnswer7.getText().toString().equals("Verificar que el stock registrado en el sistema es igual al stock real en nuestro almacén")) {
                        companyRef.child(post_key).child("Lesson Learned Evaluation").child("question_7").setValue(1);
                    } else {
                        companyRef.child(post_key).child("Lesson Learned Evaluation").child("question_7").setValue(0);
                    }

                    if (btnAnswer8.getText().toString().equals("Es el documento que especifica todas las características técnicas del producto o servicio")) {
                        companyRef.child(post_key).child("Lesson Learned Evaluation").child("question_8").setValue(1);
                    } else {
                        companyRef.child(post_key).child("Lesson Learned Evaluation").child("question_8").setValue(0);
                    }

                    if (btnAnswer9.getText().toString().equals("Funciona como un proceso de transformación de insumos para tener un producto final")) {
                        companyRef.child(post_key).child("Lesson Learned Evaluation").child("question_9").setValue(1);
                    } else {
                        companyRef.child(post_key).child("Lesson Learned Evaluation").child("question_9").setValue(0);
                    }

                    if (btnAnswer10.getText().toString().equals("Sirven para declarar un pago por un servicio prestado y declarar el impuesto de 4ta categoría en caso corresponda")) {
                        companyRef.child(post_key).child("Lesson Learned Evaluation").child("question_10").setValue(1);
                    } else {
                        companyRef.child(post_key).child("Lesson Learned Evaluation").child("question_10").setValue(0);
                    }

                    if (btnAnswer11.getText().toString().equals("Porque evita sanciones por parte de las autoridades y mejora nuestra reputación como empresa")) {
                        companyRef.child(post_key).child("Lesson Learned Evaluation").child("question_11").setValue(1);
                    } else {
                        companyRef.child(post_key).child("Lesson Learned Evaluation").child("question_11").setValue(0);
                    }

                    if (btnAnswer12.getText().toString().equals("Sirve para registrar las entradas y salidas de dinero de la empresa")) {
                        companyRef.child(post_key).child("Lesson Learned Evaluation").child("question_12").setValue(1);
                    } else {
                        companyRef.child(post_key).child("Lesson Learned Evaluation").child("question_12").setValue(0);
                    }

                    if (btnAnswer13.getText().toString().equals("Es una herramienta que nos permite construir nuestro modelo de negocio y definir nuestra propuesta de valor")) {
                        companyRef.child(post_key).child("Lesson Learned Evaluation").child("question_13").setValue(1);
                    } else {
                        companyRef.child(post_key).child("Lesson Learned Evaluation").child("question_13").setValue(0);
                    }

                    if (btnAnswer14.getText().toString().equals("Permite identificar objetivos e indicadores por área estableciendo metas")) {
                        companyRef.child(post_key).child("Lesson Learned Evaluation").child("question_14").setValue(1);
                    } else {
                        companyRef.child(post_key).child("Lesson Learned Evaluation").child("question_14").setValue(0);
                    }

                    if (btnAnswer15.getText().toString().equals("Porque nos permite presentar nuestro negocio de forma detallada a inversionistas, socios u otros interesados para obtener financiamiento o para mostrar los objetivos y estrategias de la empresa")) {
                        companyRef.child(post_key).child("Lesson Learned Evaluation").child("question_15").setValue(1);
                    } else {
                        companyRef.child(post_key).child("Lesson Learned Evaluation").child("question_15").setValue(0);
                    }

                }
            }
        });

        return view;
    }
}
