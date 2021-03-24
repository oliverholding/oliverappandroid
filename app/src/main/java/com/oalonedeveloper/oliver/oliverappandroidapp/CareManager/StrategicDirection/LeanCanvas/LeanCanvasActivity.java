package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.StrategicDirection.LeanCanvas;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import es.dmoral.toasty.Toasty;

public class LeanCanvasActivity extends AppCompatActivity {

    DatabaseReference companyRef;
    String post_key, question_message_1,question_message_2,question_message_3,question_message_4;
    ImageView btnItem1,btnItem2,btnItem3,btnItem4,btnItem5,btnItem6,btnItem7,btnItem8,btnItem9,btnInformation1,btnInformation2,btnInformation3,btnInformation4,btnInformation5,btnInformation6,btnInformation7,btnInformation8,btnInformation9,
            btnGraph1,btnGraph2,btnGraph3,btnGraph4,btnGraph5,btnGraph6,btnGraph7,btnGraph8,btnGraph9;
    TextView txtTitle1Answer1,txtTitle1Answer2,txtTitle1Answer3,txtTitle1Answer4,txtTitle2Answer1,txtTitle2Answer2,txtTitle2Answer3,txtTitle2Answer4,txtTitle3Answer1,txtTitle3Answer2,txtTitle3Answer3,txtTitle3Answer4,
            txtTitle4Answer1,txtTitle4Answer2,txtTitle4Answer3,txtTitle4Answer4,txtTitle5Answer1,txtTitle5Answer2,txtTitle5Answer3,txtTitle5Answer4,txtTitle6Answer1,txtTitle6Answer2,txtTitle6Answer3,txtTitle6Answer4,
            txtTitle7Answer1,txtTitle7Answer2,txtTitle7Answer3,txtTitle7Answer4, txtTitle8Answer1,txtTitle8Answer2,txtTitle8Answer3,txtTitle8Answer4, txtTitle9Answer1,txtTitle9Answer2,txtTitle9Answer3,txtTitle9Answer4;
    double value1,value2,value3,value4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lean_canvas);

        post_key = getIntent().getExtras().getString("post_key");
        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");

        btnItem1 = findViewById(R.id.btnItem1);
        btnItem2 = findViewById(R.id.btnItem2);
        btnItem3 = findViewById(R.id.btnItem3);
        btnItem4 = findViewById(R.id.btnItem4);
        btnItem5 = findViewById(R.id.btnItem5);
        btnItem6 = findViewById(R.id.btnItem6);
        btnItem7 = findViewById(R.id.btnItem7);
        btnItem8 = findViewById(R.id.btnItem8);
        btnItem9 = findViewById(R.id.btnItem9);
        txtTitle1Answer1 = findViewById(R.id.txtTitle1Answer1);
        txtTitle1Answer2 = findViewById(R.id.txtTitle1Answer2);
        txtTitle1Answer3 = findViewById(R.id.txtTitle1Answer3);
        txtTitle1Answer4 = findViewById(R.id.txtTitle1Answer4);

        txtTitle2Answer1 = findViewById(R.id.txtTitle2Answer1);
        txtTitle2Answer2 = findViewById(R.id.txtTitle2Answer2);
        txtTitle2Answer3 = findViewById(R.id.txtTitle2Answer3);
        txtTitle2Answer4 = findViewById(R.id.txtTitle2Answer4);

        txtTitle3Answer1 = findViewById(R.id.txtTitle3Answer1);
        txtTitle3Answer2 = findViewById(R.id.txtTitle3Answer2);
        txtTitle3Answer3 = findViewById(R.id.txtTitle3Answer3);
        txtTitle3Answer4 = findViewById(R.id.txtTitle3Answer4);

        txtTitle4Answer1 = findViewById(R.id.txtTitle4Answer1);
        txtTitle4Answer2 = findViewById(R.id.txtTitle4Answer2);
        txtTitle4Answer3 = findViewById(R.id.txtTitle4Answer3);
        txtTitle4Answer4 = findViewById(R.id.txtTitle4Answer4);

        txtTitle5Answer1 = findViewById(R.id.txtTitle5Answer1);
        txtTitle5Answer2 = findViewById(R.id.txtTitle5Answer2);
        txtTitle5Answer3 = findViewById(R.id.txtTitle5Answer3);
        txtTitle5Answer4 = findViewById(R.id.txtTitle5Answer4);

        txtTitle6Answer1 = findViewById(R.id.txtTitle6Answer1);
        txtTitle6Answer2 = findViewById(R.id.txtTitle6Answer2);
        txtTitle6Answer3 = findViewById(R.id.txtTitle6Answer3);
        txtTitle6Answer4 = findViewById(R.id.txtTitle6Answer4);

        txtTitle7Answer1 = findViewById(R.id.txtTitle7Answer1);
        txtTitle7Answer2 = findViewById(R.id.txtTitle7Answer2);
        txtTitle7Answer3 = findViewById(R.id.txtTitle7Answer3);
        txtTitle7Answer4 = findViewById(R.id.txtTitle7Answer4);

        txtTitle8Answer1 = findViewById(R.id.txtTitle8Answer1);
        txtTitle8Answer2 = findViewById(R.id.txtTitle8Answer2);
        txtTitle8Answer3 = findViewById(R.id.txtTitle8Answer3);
        txtTitle8Answer4 = findViewById(R.id.txtTitle8Answer4);

        txtTitle9Answer1 = findViewById(R.id.txtTitle9Answer1);
        txtTitle9Answer2 = findViewById(R.id.txtTitle9Answer2);
        txtTitle9Answer3 = findViewById(R.id.txtTitle9Answer3);
        txtTitle9Answer4 = findViewById(R.id.txtTitle9Answer4);

        btnInformation1 = findViewById(R.id.btnInformation1);
        btnInformation2 = findViewById(R.id.btnInformation2);
        btnInformation3 = findViewById(R.id.btnInformation3);
        btnInformation4 = findViewById(R.id.btnInformation4);
        btnInformation5 = findViewById(R.id.btnInformation5);
        btnInformation6 = findViewById(R.id.btnInformation6);
        btnInformation7 = findViewById(R.id.btnInformation7);
        btnInformation8 = findViewById(R.id.btnInformation8);
        btnInformation9 = findViewById(R.id.btnInformation9);

        btnGraph1 = findViewById(R.id.btnGraph1);
        btnGraph2 = findViewById(R.id.btnGraph2);
        btnGraph3 = findViewById(R.id.btnGraph3);
        btnGraph4 = findViewById(R.id.btnGraph4);
        btnGraph5 = findViewById(R.id.btnGraph5);
        btnGraph6 = findViewById(R.id.btnGraph6);
        btnGraph7 = findViewById(R.id.btnGraph7);
        btnGraph8 = findViewById(R.id.btnGraph8);
        btnGraph9 = findViewById(R.id.btnGraph9);

        btnInformation1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = "Socios Clave";
                String message = "¿Quiénes serán tus socios, comerciales y proveedores? ¿Qué alianzas estratégicas llevarás a cabo? Este punto es una pieza fundamental para hacer networking (presentación ante nuevos clientes potenciales), ya que cuantos más contactos de calidad tengas dentro de tu propio mercado, mejores resultados podrás conseguir.";

                showInformationDialog(title,message);
            }
        });
        btnInformation2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = "Actividades Clave";
                String message = "Aquí deberás definir las actividades clave internas que te van a permitir entregar la propuesta de valor a tus clientes:procesos de producción, publicidad y marketing etc. De esta manera conocerás las actividades clave que darán valor a tu marca, y sabrás que estrategias llevarás a cabo para potenciarlas.";

                showInformationDialog(title,message);
            }
        });
        btnInformation3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = "Recursos Claves";
                String message = "En este punto deberás identificar la infraestructura necesaria para operar tu Modelo de Negocio, ¿cuáles son los activos indispensables para llevar a cabo todo el proceso? Se refiere tanto a recursos físicos, como intelectuales, financieros y humanos, que no pueden faltar para que tu producto sea un éxito en el mercado.";

                showInformationDialog(title,message);
            }
        });
        btnInformation4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = "Propuesta de Valor";
                String message = "Tu propuesta de valor es aquello que esperas hacer mejor o de manera diferente que tu competencia. Una empresa puede tener una o varias propuestas de valor, dependiendo si se dirige a uno o varios grupos de público objetivo.\n" +
                        "En este paso debes describir la propuesta que ofrecerás a tus clientes, que se basará en los siguientes puntos:\n" +
                        "– Los problemas que se le resuelven al cliente al usar tu producto.\n" +
                        "– Las necesidades que vas a cubrir con tu producto.\n" +
                        "– Las características del producto que se resaltarán en el mercado.\n" +
                        "Debes ofrecerle a tus clientes algo único que no ofrecen las demás marcas, ¿tienes claro ya lo que es? Si es así, ¡ya tienes casi todo el trabajo hecho!";

                showInformationDialog(title,message);
            }
        });
        btnInformation5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = "Relación con el Cliente";
                String message = "En este punto debes analizar el tipo de relaciones que se establecerán con tu cliente en el momento que adquieran tu producto. Se trata de identificar cuál es la forma más agresiva y creativa para establecer relaciones a largo plazo. ¿Cómo integrarás las relaciones con tus clientes para que funcionen de forma armoniosa con tu Modelo de Negocio? ¿Les hablarás de tú o de usted? ¿Qué buscan y esperan de ti?";

                showInformationDialog(title,message);
            }
        });
        btnInformation6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = "Canales";
                String message = "Como su nombre indica, deberás saber cómo vas a distribuir tu producto para que llegue al cliente final de la forma más efectiva y segura. Debes identificar los canales de distribución que funcionarán mejor para tu producto y para hacer eficientes tus costes. En este apartado también entraría la estrategia de comunicación que llevarás a cabo para promocionar tu producto, ¿cómo vas a darte a conocer a nuevos clientes?";

                showInformationDialog(title,message);
            }
        });
        btnInformation7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = "Segmentos de Cliente";
                String message = "Los clientes son la base principal de tu Modelo de Negocio. Debes saber perfectamente a quién quieres vender tu propuesta de valor y qué necesidades tiene. Analiza en este punto el grupo de clientes que alcanzarás y reflexiona sobre aquellas necesidades que les vas a poder cubrir con la compra de tu producto.";

                showInformationDialog(title,message);
            }
        });
        btnInformation8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = "Estructura de Costos";
                String message = "Y llegamos al último punto. Ahora que ya sabes cuáles serán los recursos necesarios para llevar a cabo tu idea y cuánto van a pagar los clientes por tu producto, podrás definir la estrategia que deberá seguir tu Modelo de Negocio para mantener la estructura de costes, calculando los gastos de inversión y la rentabilidad de tu propuesta, y tratando de aprovechar al máximo los beneficios. Deberás decidir entre mantener los costes bajos, o ser un negocio que se enfoque en crear valor a un precio más alto. Para ello debes evaluar los siguientes puntos: ¿Cuáles son los recursos clave más caros y cómo podrás reducirlos? ¿Qué actividades clave son las más caras? ¿Cuáles son los costes más importantes para la realización de tu modelo de negocio?";

                showInformationDialog(title,message);
            }
        });
        btnInformation9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = "Flujos de Ingresos";
                String message = "En esta sección tendrás que evaluar qué cantidad de dinero estará dispuesto a pagar tu cliente por la compra del producto,y resolverás los tres principales interrogantes sobre cómo obtendrás ingresos con tu idea: ¿Cuánto pagarán por mi producto? ¿Cuánto pagan actualmente por un producto similar? ¿Cuánto tienes que ingresar para obtener los márgenes de beneficios deseados?";

                showInformationDialog(title,message);
            }
        });

        companyRef.child(post_key).child("Lean Canvas").child("item_1").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild("value_1")) {
                    String value = dataSnapshot.child("value_1").getValue().toString();
                    txtTitle1Answer1.setText("Empresas Privadas: "+value);
                }
                if (dataSnapshot.hasChild("value_2")) {
                    String value = dataSnapshot.child("value_2").getValue().toString();
                    txtTitle1Answer2.setText("Entidades Estatales: "+value);
                }
                if (dataSnapshot.hasChild("value_3")) {
                    String value = dataSnapshot.child("value_3").getValue().toString();
                    txtTitle1Answer3.setText("ONG´s y Fundaciones: "+value);
                }
                if (dataSnapshot.hasChild("value_4")) {
                    String value = dataSnapshot.child("value_4").getValue().toString();
                    txtTitle1Answer4.setText("Personas naturales o personas naturales con negocio: "+value);
                }

                companyRef.child(post_key).child("Lean Canvas").child("item_2").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChild("value_1")) {
                            String value = dataSnapshot.child("value_1").getValue().toString();
                            txtTitle2Answer1.setText("Atención rápida y eficiente: "+value);
                        }
                        if (dataSnapshot.hasChild("value_2")) {
                            String value = dataSnapshot.child("value_2").getValue().toString();
                            txtTitle2Answer2.setText("Personal capacitado: "+value);
                        }
                        if (dataSnapshot.hasChild("value_3")) {
                            String value = dataSnapshot.child("value_3").getValue().toString();
                            txtTitle2Answer3.setText("Ofertas y Promociones: "+value);
                        }
                        if (dataSnapshot.hasChild("value_4")) {
                            String value = dataSnapshot.child("value_4").getValue().toString();
                            txtTitle2Answer4.setText("Ubicación del Negocio: "+value);
                        }

                        companyRef.child(post_key).child("Lean Canvas").child("item_3").addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                if (dataSnapshot.hasChild("value_1")) {
                                    String value = dataSnapshot.child("value_1").getValue().toString();
                                    txtTitle3Answer1.setText("Vendedorese: "+value);
                                }
                                if (dataSnapshot.hasChild("value_2")) {
                                    String value = dataSnapshot.child("value_2").getValue().toString();
                                    txtTitle3Answer2.setText("Capital de Trabajo: "+value);
                                }
                                if (dataSnapshot.hasChild("value_3")) {
                                    String value = dataSnapshot.child("value_3").getValue().toString();
                                    txtTitle3Answer3.setText("Condiciones del local/puesto del negocio: "+value);
                                }
                                if (dataSnapshot.hasChild("value_4")) {
                                    String value = dataSnapshot.child("value_4").getValue().toString();
                                    txtTitle3Answer4.setText("Permisos y Licencias: "+value);
                                }

                                companyRef.child(post_key).child("Lean Canvas").child("item_4").addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        if (dataSnapshot.hasChild("value_1")) {
                                            String value = dataSnapshot.child("value_1").getValue().toString();
                                            txtTitle4Answer1.setText("Garantía del producto ó servicio: "+value);
                                        }
                                        if (dataSnapshot.hasChild("value_2")) {
                                            String value = dataSnapshot.child("value_2").getValue().toString();
                                            txtTitle4Answer2.setText("Puestos de trabajos limpios: "+value);
                                        }
                                        if (dataSnapshot.hasChild("value_3")) {
                                            String value = dataSnapshot.child("value_3").getValue().toString();
                                            txtTitle4Answer3.setText("Producto novedoso y 100% peruano: "+value);
                                        }
                                        if (dataSnapshot.hasChild("value_4")) {
                                            String value = dataSnapshot.child("value_4").getValue().toString();
                                            txtTitle4Answer4.setText("Entrega rápida: "+value);
                                        }

                                        companyRef.child(post_key).child("Lean Canvas").child("item_5").addValueEventListener(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                if (dataSnapshot.hasChild("value_1")) {
                                                    String value = dataSnapshot.child("value_1").getValue().toString();
                                                    txtTitle5Answer1.setText("Trato amable y de calidad: "+value);
                                                }
                                                if (dataSnapshot.hasChild("value_2")) {
                                                    String value = dataSnapshot.child("value_2").getValue().toString();
                                                    txtTitle5Answer2.setText("Información detallada de tu producto/servicio: "+value);
                                                }
                                                if (dataSnapshot.hasChild("value_3")) {
                                                    String value = dataSnapshot.child("value_3").getValue().toString();
                                                    txtTitle5Answer3.setText("Descuentos del producto/servicio: "+value);
                                                }
                                                if (dataSnapshot.hasChild("value_4")) {
                                                    String value = dataSnapshot.child("value_4").getValue().toString();
                                                    txtTitle5Answer4.setText("Entrega de complementos ó adicionales: "+value);
                                                }

                                                companyRef.child(post_key).child("Lean Canvas").child("item_6").addValueEventListener(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                                        if (dataSnapshot.hasChild("value_1")) {
                                                            String value = dataSnapshot.child("value_1").getValue().toString();
                                                            txtTitle6Answer1.setText("Redes sociales: "+value);
                                                        }
                                                        if (dataSnapshot.hasChild("value_2")) {
                                                            String value = dataSnapshot.child("value_2").getValue().toString();
                                                            txtTitle6Answer2.setText("Página web propia: "+value);
                                                        }
                                                        if (dataSnapshot.hasChild("value_3")) {
                                                            String value = dataSnapshot.child("value_3").getValue().toString();
                                                            txtTitle6Answer3.setText("Distribuidores: "+value);
                                                        }
                                                        if (dataSnapshot.hasChild("value_4")) {
                                                            String value = dataSnapshot.child("value_4").getValue().toString();
                                                            txtTitle6Answer4.setText("Local o Puesto del Negocio: "+value);
                                                        }

                                                        companyRef.child(post_key).child("Lean Canvas").child("item_7").addValueEventListener(new ValueEventListener() {
                                                            @Override
                                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                                if (dataSnapshot.hasChild("value_1")) {
                                                                    String value = dataSnapshot.child("value_1").getValue().toString();
                                                                    txtTitle7Answer1.setText("Trabajadores: "+value);
                                                                }
                                                                if (dataSnapshot.hasChild("value_2")) {
                                                                    String value = dataSnapshot.child("value_2").getValue().toString();
                                                                    txtTitle7Answer2.setText("Funcionarios: "+value);
                                                                }
                                                                if (dataSnapshot.hasChild("value_3")) {
                                                                    String value = dataSnapshot.child("value_3").getValue().toString();
                                                                    txtTitle7Answer3.setText("Comerciantes: "+value);
                                                                }
                                                                if (dataSnapshot.hasChild("value_4")) {
                                                                    String value = dataSnapshot.child("value_4").getValue().toString();
                                                                    txtTitle7Answer4.setText("Extranjeros: "+value);
                                                                }

                                                                companyRef.child(post_key).child("Lean Canvas").child("item_8").addValueEventListener(new ValueEventListener() {
                                                                    @Override
                                                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                                                        if (dataSnapshot.hasChild("value_1")) {
                                                                            String value = dataSnapshot.child("value_1").getValue().toString();
                                                                            txtTitle8Answer1.setText("Servicios Públicos: "+value);
                                                                        }
                                                                        if (dataSnapshot.hasChild("value_2")) {
                                                                            String value = dataSnapshot.child("value_2").getValue().toString();
                                                                            txtTitle8Answer2.setText("Sueldos y Salarios: "+value);
                                                                        }
                                                                        if (dataSnapshot.hasChild("value_3")) {
                                                                            String value = dataSnapshot.child("value_3").getValue().toString();
                                                                            txtTitle8Answer3.setText("Promoción y Publicidad: "+value);
                                                                        }
                                                                        if (dataSnapshot.hasChild("value_4")) {
                                                                            String value = dataSnapshot.child("value_4").getValue().toString();
                                                                            txtTitle8Answer4.setText("Máquinas y Equipos: "+value);
                                                                        }

                                                                        companyRef.child(post_key).child("Lean Canvas").child("item_9").addValueEventListener(new ValueEventListener() {
                                                                            @Override
                                                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                                                if (dataSnapshot.hasChild("value_1")) {
                                                                                    String value = dataSnapshot.child("value_1").getValue().toString();
                                                                                    txtTitle9Answer1.setText("Venta de Productos: "+value);
                                                                                }
                                                                                if (dataSnapshot.hasChild("value_2")) {
                                                                                    String value = dataSnapshot.child("value_2").getValue().toString();
                                                                                    txtTitle9Answer2.setText("Servicios: "+value);
                                                                                }
                                                                                if (dataSnapshot.hasChild("value_3")) {
                                                                                    String value = dataSnapshot.child("value_3").getValue().toString();
                                                                                    txtTitle9Answer3.setText("Intermediación: "+value);
                                                                                }
                                                                                if (dataSnapshot.hasChild("value_4")) {
                                                                                    String value = dataSnapshot.child("value_4").getValue().toString();
                                                                                    txtTitle9Answer4.setText("Rentas y Alquileres: "+value);
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



        btnItem1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = "Socios Clave";
                String question = "¿Quiénes son tus aliados clave en tu modelo de negocio?";
                String item_1 = "Empresas Privadas";
                String item_2 = "Entidades Estatales";
                String item_3 = "ONG´s y Fundaciones";
                String item_4 = "Personas naturales o personas naturales con negocio";
                String path = "item_1";

                showLeanCanvasDialog(title,question,item_1,item_2,item_3,item_4,path);
            }
        });

        btnGraph1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = "Socios Clave";
                String path = "item_1";
                String item_1 = "Empresas Privadas";
                String item_2 = "Entidades Estatales";
                String item_3 = "ONG´s y Fundaciones";
                String item_4 = "Personas naturales o personas naturales con negocio";
                showGraphDialog(title,path,item_1,item_2,item_3,item_4);
            }
        });

        btnItem2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = "Actividades Clave";
                String question = "¿Qué actividades son clave para que tu modelo de negocio funciones?";
                String item_1 = "Atención rápida y eficiente";
                String item_2 = "Personal capacitado";
                String item_3 = "Ofertas y Promociones";
                String item_4 = "Ubicación del Negocio";
                String path = "item_2";

                showLeanCanvasDialog(title,question,item_1,item_2,item_3,item_4,path);
            }
        });

        btnGraph2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = "Actividades Clave";
                String item_1 = "Atención rápida y eficiente";
                String item_2 = "Personal capacitado";
                String item_3 = "Ofertas y Promociones";
                String item_4 = "Ubicación del Negocio";
                String path = "item_2";
                showGraphDialog(title,path,item_1,item_2,item_3,item_4);
            }
        });

        btnItem3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = "Recursos Claves";
                String question = "¿Qué recursos clave requiere tu modelo de negocio?";
                String item_1 = "Vendedores";
                String item_2 = "Capital de Trabajo";
                String item_3 = "Condiciones del local/puesto del negocio";
                String item_4 = "Permisos y Licencias";
                String path = "item_3";

                showLeanCanvasDialog(title,question,item_1,item_2,item_3,item_4,path);
            }
        });

        btnGraph3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = "Recursos Claves";
                String item_1 = "Vendedores";
                String item_2 = "Capital de Trabajo";
                String item_3 = "Condiciones del local/puesto del negocio";
                String item_4 = "Permisos y Licencias";
                String path = "item_3";
                showGraphDialog(title,path,item_1,item_2,item_3,item_4);
            }
        });

        btnItem4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = "Propuesta de Valor";
                String question = "¿Qué probema/necesidad solucionas/satisfaces?";
                String item_1 = "Garantía del producto ó servicio";
                String item_2 = "Puestos de trabajos limpios";
                String item_3 = "Producto novedoso y 100% peruano";
                String item_4 = "Entrega rápida";
                String path = "item_4";

                showLeanCanvasDialog(title,question,item_1,item_2,item_3,item_4,path);
            }
        });

        btnGraph4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = "Propuesta de Valor";
                String item_1 = "Garantía del producto ó servicio";
                String item_2 = "Puestos de trabajos limpios";
                String item_3 = "Producto novedoso y 100% peruano";
                String item_4 = "Entrega rápida";
                String path = "item_4";
                showGraphDialog(title,path,item_1,item_2,item_3,item_4);
            }
        });

        btnItem5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = "Relación con el Cliente";
                String question = "¿Qué esperan tus clientes de tu negocio?";
                String item_1 = "Trato amable y de calidad";
                String item_2 = "Información detallada de tu producto/servicio";
                String item_3 = "Descuentos del producto/servicio";
                String item_4 = "Entrega de complementos o adicionales";
                String path = "item_5";

                showLeanCanvasDialog(title,question,item_1,item_2,item_3,item_4,path);
            }
        });

        btnGraph5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = "Relación con el Cliente";
                String item_1 = "Trato amable y de calidad";
                String item_2 = "Información detallada de tu producto/servicio";
                String item_3 = "Descuentos del producto/servicio";
                String item_4 = "Entrega de complementos o adicionales";
                String path = "item_5";
                showGraphDialog(title,path,item_1,item_2,item_3,item_4);
            }
        });

        btnItem6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = "Canales";
                String question = "¿Qué canales utilizas para contactar y atender a tus clientes?";
                String item_1 = "Redes sociales";
                String item_2 = "Página web propia";
                String item_3 = "Distribuidores";
                String item_4 = "Local o Puesto del Negocio";
                String path = "item_6";

                showLeanCanvasDialog(title,question,item_1,item_2,item_3,item_4,path);
            }
        });

        btnGraph6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = "Canales";
                String item_1 = "Redes sociales";
                String item_2 = "Página web propia";
                String item_3 = "Distribuidores";
                String item_4 = "Local o Puesto del Negocio";
                String path = "item_6";

                showGraphDialog(title,path,item_1,item_2,item_3,item_4);
            }
        });

        btnItem7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = "Segmentos de Cliente";
                String question = "¿A quiénes te diriges?";
                String item_1 = "Trabajadores";
                String item_2 = "Funcionarios";
                String item_3 = "Comerciantes";
                String item_4 = "Extranjeros";
                String path = "item_7";

                showLeanCanvasDialog(title,question,item_1,item_2,item_3,item_4,path);
            }
        });

        btnGraph7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = "Segmentos de Cliente";
                String item_1 = "Trabajadores";
                String item_2 = "Funcionarios";
                String item_3 = "Comerciantes";
                String item_4 = "Extranjeros";
                String path = "item_7";

                showGraphDialog(title,path,item_1,item_2,item_3,item_4);
            }
        });

        btnItem8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = "Estructura de Costos";
                String question = "¿Cuál es tu estrctura de costos para que funcione tu negocio?";
                String item_1 = "Servicios Públicos";
                String item_2 = "Sueldos y Salarios";
                String item_3 = "Promoción y Publicidad";
                String item_4 = "Máquinas y Equipos";
                String path = "item_8";

                showLeanCanvasDialog(title,question,item_1,item_2,item_3,item_4,path);
            }
        });

        btnGraph8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = "Estructura de Costos";
                String item_1 = "Servicios Públicos";
                String item_2 = "Sueldos y Salarios";
                String item_3 = "Promoción y Publicidad";
                String item_4 = "Máquinas y Equipos";
                String path = "item_8";

                showGraphDialog(title,path,item_1,item_2,item_3,item_4);
            }
        });

        btnItem9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = "Flujos de Ingresos";
                String question = "¿Qué representa los flujos de ingreso de tu empresa?";
                String item_1 = "Venta de Productos";
                String item_2 = "Servicios";
                String item_3 = "Intermediación";
                String item_4 = "Rentas y Alquileres";
                String path = "item_9";

                showLeanCanvasDialog(title,question,item_1,item_2,item_3,item_4,path);
            }
        });

        btnGraph9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = "Flujos de Ingresos";
                String item_1 = "Venta de Productos";
                String item_2 = "Servicios";
                String item_3 = "Intermediación";
                String item_4 = "Rentas y Alquileres";
                String path = "item_9";

                showGraphDialog(title,path,item_1,item_2,item_3,item_4);
            }
        });


    }

    private void showGraphDialog(String title, String path, final String item_1, final String item_2, final String item_3, final String item_4) {
        final AlertDialog dialog = new AlertDialog.Builder(this).create();

        LayoutInflater inflater = LayoutInflater.from(this);
        View finance_method = inflater.inflate(R.layout.lean_canvas_graph_dialog,null);

        TextView txtTitle;
        final PieChart pieChart;

        txtTitle = finance_method.findViewById(R.id.txtTitle);
        pieChart = finance_method.findViewById(R.id.pieChart);

        txtTitle.setText(title);


        companyRef.child(post_key).child("Lean Canvas").child(path).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild("value_1")) {
                    String value = dataSnapshot.child("value_1").getValue().toString();
                    if (value.equals("No es Importante")) {
                        value1 = 1;
                    }
                    if (value.equals("Relativamente Importante")) {
                        value1 = 2;
                    }
                    if (value.equals("Muy Importante")) {
                        value1 = 3;
                    }
                }
                if (dataSnapshot.hasChild("value_2")) {
                    String value = dataSnapshot.child("value_2").getValue().toString();
                    if (value.equals("No es Importante")) {
                        value2 = 1;
                    }
                    if (value.equals("Relativamente Importante")) {
                        value2 = 2;
                    }
                    if (value.equals("Muy Importante")) {
                        value2 = 3;
                    }
                }
                if (dataSnapshot.hasChild("value_3")) {
                    String value = dataSnapshot.child("value_3").getValue().toString();
                    if (value.equals("No es Importante")) {
                        value3 = 1;
                    }
                    if (value.equals("Relativamente Importante")) {
                        value3 = 2;
                    }
                    if (value.equals("Muy Importante")) {
                        value3 = 3;
                    }
                }
                if (dataSnapshot.hasChild("value_4")) {
                    String value = dataSnapshot.child("value_4").getValue().toString();
                    if (value.equals("No es Importante")) {
                        value4 = 1;
                    }
                    if (value.equals("Relativamente Importante")) {
                        value4 = 2;
                    }
                    if (value.equals("Muy Importante")) {
                        value4 = 3;
                    }
                }

                double total = value1+value2+value3+value4;
                value1 = (value1/total)*100;
                value2 = (value2/total)*100;
                value3 = (value3/total)*100;
                value4 = (value4/total)*100;

                int percent1 = (int) value1;
                int percent2 = (int) value2;
                int percent3 = (int) value3;
                int percent4 = (int) value4;

                ArrayList<PieEntry> pieEntries = new ArrayList<>();

                //initializing data
                Map<String, Integer> typeAmountMap = new HashMap<>();
                typeAmountMap.put(item_1,percent1);
                typeAmountMap.put(item_2,percent2);
                typeAmountMap.put(item_3,percent3);
                typeAmountMap.put(item_4,percent4);

                //initializing colors for the entries
                ArrayList<Integer> colors = new ArrayList<>();
                colors.add(Color.parseColor("#304567"));
                colors.add(Color.parseColor("#309967"));
                colors.add(Color.parseColor("#309952"));
                colors.add(Color.parseColor("#309986"));

                //input data and fit data into pie chart entry
                for(String type: typeAmountMap.keySet()){
                    pieEntries.add(new PieEntry(typeAmountMap.get(type).floatValue(), type));
                }

                //collecting the entries with label name
                PieDataSet pieDataSet = new PieDataSet(pieEntries,"");
                //setting text size of the value
                pieDataSet.setValueTextSize(12f);
                //providing color list for coloring different entries
                pieDataSet.setColors(colors);
                pieDataSet.setValueTextColor(Color.WHITE);
                //grouping the data set from entry to chart
                PieData pieData = new PieData(pieDataSet);
                //showing the value of the entries, default true if not set
                pieData.setDrawValues(true);

                pieChart.setData(pieData);
                pieChart.invalidate();

                pieData.setValueFormatter(new PercentFormatter());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        dialog.setView(finance_method);
        dialog.show();
    }


    private void showInformationDialog(String title, String message) {
        final AlertDialog dialog = new AlertDialog.Builder(this).create();

        LayoutInflater inflater = LayoutInflater.from(this);
        View finance_method = inflater.inflate(R.layout.lean_canvas_information_dialog,null);

        TextView txtTitle,txtMessage;

        txtTitle = finance_method.findViewById(R.id.txtTitle);
        txtMessage = finance_method.findViewById(R.id.txtMessage);


        txtTitle.setText(title);
        txtMessage.setText(message);

        dialog.setView(finance_method);
        dialog.show();
    }

    private void showLeanCanvasDialog(String title, String question, final String item_1, final String item_2, final String item_3,final String item_4, final String path) {
        final AlertDialog dialog = new AlertDialog.Builder(this).create();

        LayoutInflater inflater = LayoutInflater.from(this);
        View finance_method = inflater.inflate(R.layout.lean_canvas_set_data_dialog,null);

        TextView txtTitle,txtQuestion,txtItem1,txtItem2,txtItem3,txtItem4;
        final RadioButton rdNoImportant1,rdNoImportant2,rdNoImportant3,rdNoImportant4,rdRelativeImportant1,rdRelativeImportant2,rdRelativeImportant3,rdRelativeImportant4,rdVeryImportant1,rdVeryImportant2,rdVeryImportant3,rdVeryImportant4;
        Button btnFinish;
        final LinearLayout rootLayout;
        ImageView imgCanva,imgItem1,imgItem2,imgItem3,imgItem4;

        txtTitle = finance_method.findViewById(R.id.txtTitle);
        txtQuestion = finance_method.findViewById(R.id.txtQuestion);
        txtItem1 = finance_method.findViewById(R.id.txtItem1);
        txtItem2 = finance_method.findViewById(R.id.txtItem2);
        txtItem3 = finance_method.findViewById(R.id.txtItem3);
        txtItem4 = finance_method.findViewById(R.id.txtItem4);
        rdNoImportant1 = finance_method.findViewById(R.id.rdNoImportant1);
        rdNoImportant2 = finance_method.findViewById(R.id.rdNoImportant2);
        rdNoImportant3 = finance_method.findViewById(R.id.rdNoImportant3);
        rdNoImportant4 = finance_method.findViewById(R.id.rdNoImportant4);
        rdRelativeImportant1 = finance_method.findViewById(R.id.rdRelativeImportant1);
        rdRelativeImportant2 = finance_method.findViewById(R.id.rdRelativeImportant2);
        rdRelativeImportant3 = finance_method.findViewById(R.id.rdRelativeImportant3);
        rdRelativeImportant4 = finance_method.findViewById(R.id.rdRelativeImportant4);
        rdVeryImportant1 = finance_method.findViewById(R.id.rdVeryImportant1);
        rdVeryImportant2 = finance_method.findViewById(R.id.rdVeryImportant2);
        rdVeryImportant3 = finance_method.findViewById(R.id.rdVeryImportant3);
        rdVeryImportant4 = finance_method.findViewById(R.id.rdVeryImportant4);
        btnFinish = finance_method.findViewById(R.id.btnFinish);
        rootLayout = finance_method.findViewById(R.id.rootLayout);
        imgCanva = finance_method.findViewById(R.id.imgCanva);
        imgItem1 = finance_method.findViewById(R.id.imgItem1);
        imgItem2 = finance_method.findViewById(R.id.imgItem2);
        imgItem3 = finance_method.findViewById(R.id.imgItem3);
        imgItem4 = finance_method.findViewById(R.id.imgItem4);

        if (title.equals("Socios Clave")) {
            imgCanva.setImageResource(R.drawable.socios_clave);
        }
        if (title.equals("Actividades Clave")) {
            imgCanva.setImageResource(R.drawable.actividades_clave);
        }
        if (title.equals("Recursos Claves")) {
            imgCanva.setImageResource(R.drawable.recursos_clave);
        }
        if (title.equals("Propuesta de Valor")) {
            imgCanva.setImageResource(R.drawable.propuesta_valor);
        }
        if (title.equals("Relación con el Cliente")) {
            imgCanva.setImageResource(R.drawable.relaciones_los_clientes);
        }
        if (title.equals("Canales")) {
            imgCanva.setImageResource(R.drawable.canales_de_distribucion);
        }
        if (title.equals("Segmentos de Cliente")) {
            imgCanva.setImageResource(R.drawable.segmento_de_clientes);
        }
        if (title.equals("Estructura de Costos")) {
            imgCanva.setImageResource(R.drawable.estructura_costes);
        }
        if (title.equals("Flujos de Ingresos")) {
            imgCanva.setImageResource(R.drawable.ingresos);
        }


        if (item_1.equals("Empresas Privadas")) {
            imgItem1.setImageResource(R.drawable.empresas_privadas);
        }
        if (item_2.equals("Entidades Estatales")) {
            imgItem2.setImageResource(R.drawable.entidades_estatales);
        }
        if (item_3.equals("ONG´s y Fundaciones")) {
            imgItem3.setImageResource(R.drawable.ongs_fundaciones);
        }
        if (item_4.equals("Personas naturales o personas naturales con negocio")) {
            imgItem4.setImageResource(R.drawable.personas_naturales);
        }


        if (item_1.equals("Atención rápida y eficiente")) {
            imgItem1.setImageResource(R.drawable.atencion_rapida_eficiente);
        }
        if (item_2.equals("Personal capacitado")) {
            imgItem2.setImageResource(R.drawable.personal_capacitado);
        }
        if (item_3.equals("Ofertas y Promociones")) {
            imgItem3.setImageResource(R.drawable.ofertas_promociones);
        }
        if (item_4.equals("Ubicación del Negocio")) {
            imgItem4.setImageResource(R.drawable.ubicacion_del_negocio);
        }

        if (item_1.equals("Vendedores")) {
            imgItem1.setImageResource(R.drawable.vendedor);
        }
        if (item_2.equals("Capital de Trabajo")) {
            imgItem2.setImageResource(R.drawable.capital_trabajo);
        }
        if (item_3.equals("Condiciones del local/puesto del negocio")) {
            imgItem3.setImageResource(R.drawable.condiciones_del_local);
        }
        if (item_4.equals("Permisos y Licencias")) {
            imgItem4.setImageResource(R.drawable.permisos_licencias);
        }

        if (item_1.equals("Garantía del producto ó servicio")) {
            imgItem1.setImageResource(R.drawable.garantia_del_producto_servicio);
        }
        if (item_2.equals("Puestos de trabajos limpios")) {
            imgItem2.setImageResource(R.drawable.puestos_de_trabajos_limpios);
        }
        if (item_3.equals("Producto novedoso y 100% peruano")) {
            imgItem3.setImageResource(R.drawable.producto_novedoso);
        }
        if (item_4.equals("Entrega rápida")) {
            imgItem4.setImageResource(R.drawable.entrega_rapida);
        }


        if (item_1.equals("Trato amable y de calidad")) {
            imgItem1.setImageResource(R.drawable.trabajadores_amables);
        }
        if (item_2.equals("Información detallada de tu producto/servicio")) {
            imgItem2.setImageResource(R.drawable.informacion_detalla_del_producto_servicio);
        }
        if (item_3.equals("Descuentos del producto/servicio")) {
            imgItem3.setImageResource(R.drawable.descuentos_producto);
        }
        if (item_4.equals("Entrega de complementos o adicionales")) {
            imgItem4.setImageResource(R.drawable.entrega_complementos);
        }


        if (item_1.equals("Redes sociales")) {
            imgItem1.setImageResource(R.drawable.redes_sociales);
        }
        if (item_2.equals("Página web propia")) {
            imgItem2.setImageResource(R.drawable.pagina_web);
        }
        if (item_3.equals("Distribuidores")) {
            imgItem3.setImageResource(R.drawable.canales_de_distribucion);
        }
        if (item_4.equals("Local o Puesto del Negocio")) {
            imgItem4.setImageResource(R.drawable.local_puesto_del_negocio);
        }


        if (item_1.equals("Trabajadores")) {
            imgItem1.setImageResource(R.drawable.trabajadores);
        }
        if (item_2.equals("Funcionarios")) {
            imgItem2.setImageResource(R.drawable.funcionarios);
        }
        if (item_3.equals("Comerciantes")) {
            imgItem3.setImageResource(R.drawable.comerciantes);
        }
        if (item_4.equals("Extranjeros")) {
            imgItem4.setImageResource(R.drawable.extranjeros);
        }


        if (item_1.equals("Servicios Públicos")) {
            imgItem1.setImageResource(R.drawable.servicios_publicos);
        }
        if (item_2.equals("Sueldos y Salarios")) {
            imgItem2.setImageResource(R.drawable.sueldos_salarios);
        }
        if (item_3.equals("Promoción y Publicidad")) {
            imgItem3.setImageResource(R.drawable.promocion_publicidad);
        }
        if (item_4.equals("Máquinas y Equipos")) {
            imgItem4.setImageResource(R.drawable.maquinas_equipos);
        }


        if (item_1.equals("Venta de Productos")) {
            imgItem1.setImageResource(R.drawable.nuevo_producto_servicio);
        }
        if (item_2.equals("Servicios")) {
            imgItem2.setImageResource(R.drawable.servicios_principales);
        }
        if (item_3.equals("Intermediación")) {
            imgItem3.setImageResource(R.drawable.intermediarios);
        }
        if (item_4.equals("Rentas y Alquileres")) {
            imgItem4.setImageResource(R.drawable.rentabilidad);
        }

        rdNoImportant1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                question_message_1 = rdNoImportant1.getText().toString();
            }
        });
        rdNoImportant2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                question_message_2 = rdNoImportant2.getText().toString();
            }
        });
        rdNoImportant3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                question_message_3 = rdNoImportant3.getText().toString();
            }
        });
        rdNoImportant4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                question_message_4 = rdNoImportant4.getText().toString();
            }
        });

        rdRelativeImportant1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                question_message_1 = rdRelativeImportant1.getText().toString();
            }
        });
        rdRelativeImportant2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                question_message_2 = rdRelativeImportant2.getText().toString();
            }
        });
        rdRelativeImportant3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                question_message_3 = rdRelativeImportant3.getText().toString();
            }
        });
        rdRelativeImportant4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                question_message_4 = rdRelativeImportant4.getText().toString();
            }
        });

        rdVeryImportant1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                question_message_1 = rdVeryImportant1.getText().toString();
            }
        });
        rdVeryImportant2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                question_message_2 = rdVeryImportant2.getText().toString();
            }
        });
        rdVeryImportant3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                question_message_3 = rdVeryImportant3.getText().toString();
            }
        });
        rdVeryImportant4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                question_message_4 = rdVeryImportant4.getText().toString();
            }
        });

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!rdNoImportant1.isChecked() && !rdRelativeImportant1.isChecked() && !rdVeryImportant1.isChecked()) {
                    Snackbar.make(rootLayout,"Debes seleccionar una alternativa en "+item_1,Snackbar.LENGTH_SHORT).show();
                }
                else  if (!rdNoImportant2.isChecked() && !rdRelativeImportant2.isChecked() && !rdVeryImportant2.isChecked()) {
                    Snackbar.make(rootLayout,"Debes seleccionar una alternativa en "+item_2,Snackbar.LENGTH_SHORT).show();
                } else if (!rdNoImportant3.isChecked() && !rdRelativeImportant3.isChecked() && !rdVeryImportant3.isChecked()) {
                    Snackbar.make(rootLayout, "Debes seleccionar una alternativa en " + item_3, Snackbar.LENGTH_SHORT).show();
                } else if (!rdNoImportant4.isChecked() && !rdRelativeImportant4.isChecked() && !rdVeryImportant4.isChecked()) {
                    Snackbar.make(rootLayout, "Debes seleccionar una alternativa en " + item_4, Snackbar.LENGTH_SHORT).show();
                }

                else {
                    companyRef.child(post_key).child("Lean Canvas").child(path).child("value_1").setValue(question_message_1);
                    companyRef.child(post_key).child("Lean Canvas").child(path).child("value_2").setValue(question_message_2);
                    companyRef.child(post_key).child("Lean Canvas").child(path).child("value_3").setValue(question_message_3);
                    companyRef.child(post_key).child("Lean Canvas").child(path).child("value_4").setValue(question_message_4);
                    Toasty.success(LeanCanvasActivity.this, "Actualizado", Toast.LENGTH_LONG).show();
                    dialog.dismiss();
                }

            }
        });

        txtTitle.setText(title);
        txtQuestion.setText(question);
        txtItem1.setText(item_1);
        txtItem2.setText(item_2);
        txtItem3.setText(item_3);
        txtItem4.setText(item_4);

        dialog.setView(finance_method);
        dialog.show();
    }

}
