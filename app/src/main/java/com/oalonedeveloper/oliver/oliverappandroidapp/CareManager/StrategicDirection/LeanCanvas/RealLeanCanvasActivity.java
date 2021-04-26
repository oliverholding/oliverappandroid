package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.StrategicDirection.LeanCanvas;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
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

public class RealLeanCanvasActivity extends AppCompatActivity {

    DatabaseReference companyRef;
    String post_key;
    ImageView btnItem1,btnItem2,btnItem3,btnItem4,btnItem5,btnItem6,btnItem7,btnItem8,btnItem9,btnInformation1,btnInformation2,btnInformation3,btnInformation4,btnInformation5,btnInformation6,btnInformation7,btnInformation8,btnInformation9;
    TextView txtAnswer1,txtAnswer2,txtAnswer3,txtAnswer4,txtAnswer5,txtAnswer6,txtAnswer7,txtAnswer8,txtAnswer9;
    RelativeLayout root1,root2,root3,root4,root5,root6,root7,root8,root9;
    Button btnDownloadLeanCanvas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_real_lean_canvas);

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

        btnInformation1 = findViewById(R.id.btnInformation1);
        btnInformation2 = findViewById(R.id.btnInformation2);
        btnInformation3 = findViewById(R.id.btnInformation3);
        btnInformation4 = findViewById(R.id.btnInformation4);
        btnInformation5 = findViewById(R.id.btnInformation5);
        btnInformation6 = findViewById(R.id.btnInformation6);
        btnInformation7 = findViewById(R.id.btnInformation7);
        btnInformation8 = findViewById(R.id.btnInformation8);
        btnInformation9 = findViewById(R.id.btnInformation9);


        txtAnswer1 = findViewById(R.id.txtAnswer1);
        txtAnswer2 = findViewById(R.id.txtAnswer2);
        txtAnswer3 = findViewById(R.id.txtAnswer3);
        txtAnswer4 = findViewById(R.id.txtAnswer4);
        txtAnswer5 = findViewById(R.id.txtAnswer5);
        txtAnswer6 = findViewById(R.id.txtAnswer6);
        txtAnswer7 = findViewById(R.id.txtAnswer7);
        txtAnswer8 = findViewById(R.id.txtAnswer8);
        txtAnswer9 = findViewById(R.id.txtAnswer9);

        root1 = findViewById(R.id.root1);
        root2 = findViewById(R.id.root2);
        root3 = findViewById(R.id.root3);
        root4 = findViewById(R.id.root4);
        root5 = findViewById(R.id.root5);
        root6 = findViewById(R.id.root6);
        root7 = findViewById(R.id.root7);
        root8 = findViewById(R.id.root8);
        root9 = findViewById(R.id.root9);

        btnDownloadLeanCanvas = findViewById(R.id.btnDownloadLeanCanvas);

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

        btnItem4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = "Propuesta de Valor";
                String question = "¿Qué necesitas hacer para diferenciarte de la competencia?";
                String path = "item_1";
                showLeanCanvasDialog(title, question, path);
            }
        });

        btnItem7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = "Segmentos de Cliente";
                String question = "¿Quienes y cómo son tus clientes?";
                String path = "item_2";
                showLeanCanvasDialog(title, question, path);
            }
        });

        btnItem6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = "Canales";
                String question = "¿De qué forma nuestro producto o servicio llega a los clientes?";
                String path = "item_3";
                showLeanCanvasDialog(title, question, path);
            }
        });

        btnItem5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = "Relación con el Cliente";
                String question = "¿Cómo conseguiremos y atreremos a nuevos clientes?";
                String path = "item_4";
                showLeanCanvasDialog(title, question, path);
            }
        });

        btnItem9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = "Flujos de Ingresos";
                String question = "¿Cómo monetizamos nuestro producto o servicio?";
                String path = "item_5";
                showLeanCanvasDialog(title, question, path);
            }
        });

        btnItem3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = "Recursos Claves";
                String question = "¿Qué necesitamos para que nuestro negocio funcione?";
                String path = "item_6";
                showLeanCanvasDialog(title, question, path);
            }
        });

        btnItem2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = "Actividades Clave";
                String question = "¿Qué necesitas hcer para que tu negocio funcione?";
                String path = "item_7";
                showLeanCanvasDialog(title, question, path);
            }
        });

        btnItem1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = "Socios Clave";
                String question = "¿A quienes necesitamos para que el negocio funcione?";
                String path = "item_8";

                showLeanCanvasDialog(title, question, path);
            }
        });

        btnItem8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = "Estructura de Costos";
                String question = "¿Cuales son los cosotos necesarios para poder arrancar con el negocio?";
                String path = "item_9";

                showLeanCanvasDialog(title, question, path);
            }
        });

        companyRef.child(post_key).child("Real Lean Canvas").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (!dataSnapshot.exists()) {
                    root4.setFocusable(true);
                    root4.setFocusableInTouchMode(true);
                    root4.requestFocus();
                    root4.setBackgroundResource(R.drawable.orange_strokes_focus_style);
                }
                if (dataSnapshot.hasChild("item_1")) {
                    String item = dataSnapshot.child("item_1").getValue().toString();
                    txtAnswer4.setText(item);

                    root7.setFocusable(true);
                    root7.setFocusableInTouchMode(true);
                    root7.requestFocus();
                    root7.setBackgroundResource(R.drawable.orange_strokes_focus_style);
                }
                if (dataSnapshot.hasChild("item_2")) {
                    String item = dataSnapshot.child("item_2").getValue().toString();
                    txtAnswer7.setText(item);

                    root7.setFocusable(false);
                    root7.setFocusableInTouchMode(false);
                    root7.requestFocus();
                    root7.setBackgroundResource(0);

                    root6.setFocusable(true);
                    root6.setFocusableInTouchMode(true);
                    root6.requestFocus();
                    root6.setBackgroundResource(R.drawable.orange_strokes_focus_style);
                }
                if (dataSnapshot.hasChild("item_3")) {
                    String item = dataSnapshot.child("item_3").getValue().toString();
                    txtAnswer6.setText(item);

                    root6.setFocusable(false);
                    root6.setFocusableInTouchMode(false);
                    root6.requestFocus();
                    root6.setBackgroundResource(0);

                    root5.setFocusable(true);
                    root5.setFocusableInTouchMode(true);
                    root5.requestFocus();
                    root5.setBackgroundResource(R.drawable.orange_strokes_focus_style);
                }
                if (dataSnapshot.hasChild("item_4")) {
                    String item = dataSnapshot.child("item_4").getValue().toString();
                    txtAnswer5.setText(item);

                    root5.setFocusable(false);
                    root7.setFocusableInTouchMode(false);
                    root5.requestFocus();
                    root5.setBackgroundResource(0);

                    root9.setFocusable(true);
                    root9.setFocusableInTouchMode(true);
                    root9.requestFocus();
                    root9.setBackgroundResource(R.drawable.orange_strokes_focus_style);
                }
                if (dataSnapshot.hasChild("item_5")) {
                    String item = dataSnapshot.child("item_5").getValue().toString();
                    txtAnswer9.setText(item);

                    root9.setFocusable(false);
                    root9.setFocusableInTouchMode(false);
                    root9.requestFocus();
                    root9.setBackgroundResource(0);

                    root3.setFocusable(true);
                    root3.setFocusableInTouchMode(true);
                    root3.requestFocus();
                    root3.setBackgroundResource(R.drawable.orange_strokes_focus_style);
                }
                if (dataSnapshot.hasChild("item_6")) {
                    String item = dataSnapshot.child("item_6").getValue().toString();
                    txtAnswer3.setText(item);

                    root3.setFocusable(false);
                    root3.setFocusableInTouchMode(false);
                    root3.requestFocus();
                    root3.setBackgroundResource(0);

                    root2.setFocusable(true);
                    root2.setFocusableInTouchMode(true);
                    root2.requestFocus();
                    root2.setBackgroundResource(R.drawable.orange_strokes_focus_style);
                }
                if (dataSnapshot.hasChild("item_7")) {
                    String item = dataSnapshot.child("item_7").getValue().toString();
                    txtAnswer2.setText(item);

                    root2.setFocusable(false);
                    root2.setFocusableInTouchMode(false);
                    root2.requestFocus();
                    root2.setBackgroundResource(0);

                    root1.setFocusable(true);
                    root1.setFocusableInTouchMode(true);
                    root1.requestFocus();
                    root1.setBackgroundResource(R.drawable.orange_strokes_focus_style);
                }
                if (dataSnapshot.hasChild("item_8")) {
                    String item = dataSnapshot.child("item_8").getValue().toString();
                    txtAnswer1.setText(item);

                    root1.setFocusable(false);
                    root1.setFocusableInTouchMode(false);
                    root1.requestFocus();
                    root1.setBackgroundResource(0);

                    root8.setFocusable(true);
                    root8.setFocusableInTouchMode(true);
                    root8.requestFocus();
                    root8.setBackgroundResource(R.drawable.orange_strokes_focus_style);
                }
                if (dataSnapshot.hasChild("item_9")) {
                    String item = dataSnapshot.child("item_9").getValue().toString();
                    txtAnswer8.setText(item);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        btnDownloadLeanCanvas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://oliver.com.pe/oliver-manager-login/user/company-reports/"+post_key+"/canvas"));
                startActivity(browserIntent);
            }
        });
    }

    private void showLeanCanvasDialog(String title, String question, final String path) {
        final AlertDialog dialog = new AlertDialog.Builder(this).create();

        LayoutInflater inflater = LayoutInflater.from(this);
        View finance_method = inflater.inflate(R.layout.lean_canvas_answer_dialog,null);

        TextView txtTitle,txtQuestion;
        Button btnFinish;
        final LinearLayout rootLayout;
        ImageView imgCanva;
        final EditText edtAnswer;

        txtTitle = finance_method.findViewById(R.id.txtTitle);
        txtQuestion = finance_method.findViewById(R.id.txtQuestion);
        imgCanva = finance_method.findViewById(R.id.imgCanva);
        btnFinish = finance_method.findViewById(R.id.btnFinish);
        rootLayout = finance_method.findViewById(R.id.rootLayout);
        edtAnswer = finance_method.findViewById(R.id.edtAnswer);

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

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(edtAnswer.getText().toString())) {
                    Snackbar.make(rootLayout, "Debes completar el campo", Snackbar.LENGTH_SHORT).show();
                } else {
                    companyRef.child(post_key).child("Real Lean Canvas").child(path).setValue(edtAnswer.getText().toString());
                    Toasty.success(RealLeanCanvasActivity.this, "Actualizado", Toast.LENGTH_LONG).show();
                    dialog.dismiss();
                }
            }
        });

        txtTitle.setText(title);
        txtQuestion.setText(question);

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
}
