package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.BusinessOportunitiesModule;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;
import com.oalonedeveloper.oliver.oliverappandroidapp.Companies.MyCustomersActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;


public class BusinessOportunityToolFragment extends Fragment {

    Button btnMyCustomers,btnSendSurvey;
    String post_key,customers_exist,company_social_reason,customer_name,customer_phone;
    DatabaseReference myCompanyRef;
    RelativeLayout rootLayout;
    AlertDialog dialog;
    int id001A,id001B,id001C,id001D,id002A,id002B,id003A,id003B,id004A,id004B,id004C,id004D,id004E,id004F,id004G,id004H,id004I,id004J,id004K,id004L,id004M,id004N,id004O,id004P,id004Q,id005A,id005B,id006A,id006B,id006C,id006D,id007A,id007B,id008A,id008B,id008C,id008D,id008E,id008F;
    TextView txt001A,txt001B,txt001C,txt001D,txt002A,txt002B,txt003A,txt003B,txt004A,txt004B,txt004C,txt004D,txt004E,txt004F,txt004G,txt004H,txt004I,txt004J,txt004K,txt004L,txt004M,txt004N,txt004O,txt004P,txt004Q,txt005A,txt005B,txt006A,
            txt006B,txt006C,txt006D,txt007A,txt007B,txt008A,txt008B,txt008C,txt008D,txt008E,txt008F;


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_business_oportunity_tool, container, false);

        post_key = getActivity().getIntent().getExtras().getString("post_key");

        btnMyCustomers = view.findViewById(R.id.btnMyCustomers);
        btnSendSurvey = view.findViewById(R.id.btnSendSurvey);
        rootLayout = view.findViewById(R.id.rootLayout);

        myCompanyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");

        customers_exist = "false";

        txt001A = view.findViewById(R.id.txt001A);
        txt001B = view.findViewById(R.id.txt001B);
        txt001C = view.findViewById(R.id.txt001C);
        txt001D = view.findViewById(R.id.txt001D);
        txt002A = view.findViewById(R.id.txt002A);
        txt002B = view.findViewById(R.id.txt002B);
        txt003A = view.findViewById(R.id.txt003A);
        txt003B = view.findViewById(R.id.txt003B);
        txt004A = view.findViewById(R.id.txt004A);
        txt004B = view.findViewById(R.id.txt004B);
        txt004C = view.findViewById(R.id.txt004C);
        txt004D = view.findViewById(R.id.txt004D);
        txt004E = view.findViewById(R.id.txt004E);
        txt004F = view.findViewById(R.id.txt004F);
        txt004G = view.findViewById(R.id.txt004G);
        txt004H = view.findViewById(R.id.txt004H);
        txt004I = view.findViewById(R.id.txt004I);
        txt004J = view.findViewById(R.id.txt004J);
        txt004K = view.findViewById(R.id.txt004K);
        txt004L = view.findViewById(R.id.txt004L);
        txt004M = view.findViewById(R.id.txt004M);
        txt004N = view.findViewById(R.id.txt004N);
        txt004O = view.findViewById(R.id.txt004O);
        txt004P = view.findViewById(R.id.txt004P);
        txt004Q = view.findViewById(R.id.txt004Q);
        txt005A = view.findViewById(R.id.txt005A);
        txt005B = view.findViewById(R.id.txt005B);
        txt006A = view.findViewById(R.id.txt006A);
        txt006B = view.findViewById(R.id.txt006B);
        txt006C = view.findViewById(R.id.txt006C);
        txt006D = view.findViewById(R.id.txt006D);
        txt007A = view.findViewById(R.id.txt007A);
        txt007B = view.findViewById(R.id.txt007B);
        txt008A = view.findViewById(R.id.txt008A);
        txt008B = view.findViewById(R.id.txt008B);
        txt008C = view.findViewById(R.id.txt008C);
        txt008D = view.findViewById(R.id.txt008D);
        txt008E = view.findViewById(R.id.txt008E);
        txt008F = view.findViewById(R.id.txt008F);


        myCompanyRef.child(post_key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild("Customers")) {
                    customers_exist = "true";
                }
                if (dataSnapshot.hasChild("Module 2")) {
                    myCompanyRef.child(post_key).child("Module 2").child("Company Survey").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            id001A = dataSnapshot.child("001").child("001A").getValue(Integer.class);
                            id001B = dataSnapshot.child("001").child("001B").getValue(Integer.class);
                            id001C = dataSnapshot.child("001").child("001C").getValue(Integer.class);
                            id001D = dataSnapshot.child("001").child("001D").getValue(Integer.class);
                            txt001A.setText(id001A+ " Personas piensan que tu servicio es MUY BUENO");
                            txt001B.setText(id001B+ " Personas piensan que tu servicio es BUENO");
                            txt001C.setText(id001C+ " Personas piensan que tu servicio es REGULAR");
                            txt001D.setText(id001D+ " Personas piensan que tu servicio es MALO");

                            id002A = dataSnapshot.child("002").child("002A").getValue(Integer.class);
                            id002B = dataSnapshot.child("002").child("002B").getValue(Integer.class);
                            txt002A.setText(id002A+" Personas están satisfechas con tu producto o servicio");
                            txt002B.setText(id002B+" Personas NO están satisfechas con tu producto o servicio");

                            id003A = dataSnapshot.child("003").child("003A").getValue(Integer.class);
                            id003B = dataSnapshot.child("003").child("003B").getValue(Integer.class);
                            txt003A.setText(id003A+" Personas recomendarían tu marca");
                            txt003B.setText(id003B+" Personas NO recomendarían tu marca");

                            id004A = dataSnapshot.child("004").child("004A").getValue(Integer.class);
                            id004B = dataSnapshot.child("004").child("004B").getValue(Integer.class);
                            id004C = dataSnapshot.child("004").child("004C").getValue(Integer.class);
                            id004D = dataSnapshot.child("004").child("004D").getValue(Integer.class);
                            id004E = dataSnapshot.child("004").child("004E").getValue(Integer.class);
                            id004F = dataSnapshot.child("004").child("004F").getValue(Integer.class);
                            id004G = dataSnapshot.child("004").child("004G").getValue(Integer.class);
                            id004H = dataSnapshot.child("004").child("004H").getValue(Integer.class);
                            id004I = dataSnapshot.child("004").child("004I").getValue(Integer.class);
                            id004J = dataSnapshot.child("004").child("004J").getValue(Integer.class);
                            id004K = dataSnapshot.child("004").child("004K").getValue(Integer.class);
                            id004L = dataSnapshot.child("004").child("004L").getValue(Integer.class);
                            id004M = dataSnapshot.child("004").child("004M").getValue(Integer.class);
                            id004N = dataSnapshot.child("004").child("004N").getValue(Integer.class);
                            id004O = dataSnapshot.child("004").child("004O").getValue(Integer.class);
                            id004P = dataSnapshot.child("004").child("004P").getValue(Integer.class);
                            id004Q = dataSnapshot.child("004").child("004Q").getValue(Integer.class);
                            txt004A.setText(id004A+ " Personas piensan que debes mejorar el Emapaque");
                            txt004B.setText(id004B+ " Personas piensan que debes mejorar el Precio");
                            txt004C.setText(id004C+ " Personas piensan que debes mejorar el Servicio de Venta");
                            txt004D.setText(id004D+ " Personas piensan que debes mejorar el Servicio de Postventa");
                            txt004E.setText(id004E+ " Personas piensan que debes tener productos más novedosos");
                            txt004F.setText(id004F+ " Personas piensan que debes tener más puntos de venta");
                            txt004G.setText(id004G+ " Personas piensan que debes mejorar el tamaño del producto");
                            txt004H.setText(id004H+ " Personas piensan que debes mejorar el tiempo de entrega del producto");
                            txt004I.setText(id004I+ " Personas piensan que debes tener en cuenta los productos ecológicos");
                            txt004J.setText(id004J+ " Personas piensan que debes mejorar la variedad de productos");
                            txt004K.setText(id004K+ " Personas piensan que debes tener en cuenta los empaques ecológicos");
                            txt004L.setText(id004L+ " Personas piensan que debes incrementar las zonas de reparto");
                            txt004M.setText(id004M+ " Personas piensan que debes mejorar las tiendas físicas");
                            txt004N.setText(id004N+ " Personas piensan que debes mejorar los canales de venta");
                            txt004O.setText(id004O+ " Personas piensan que debes mejorar la comunicación de la marca");
                            txt004P.setText(id004P+ " Personas piensan que debes mejorar la página web");
                            txt004Q.setText(id004Q+ " Personas piensan que debes mejorar el número de atención al cliente");

                            id005A = dataSnapshot.child("005").child("005A").getValue(Integer.class);
                            id005B = dataSnapshot.child("005").child("005B").getValue(Integer.class);
                            txt005A.setText(id005A+ " Personas piensan que el precio está relacionado con el producto o servicio brindado");
                            txt005B.setText(id005B+ " Personas piensan que el precio NO está relacionado con el producto o servicio brindado");

                            id006A = dataSnapshot.child("006").child("006A").getValue(Integer.class);
                            id006B = dataSnapshot.child("006").child("006B").getValue(Integer.class);
                            id006C = dataSnapshot.child("006").child("006C").getValue(Integer.class);
                            id006D = dataSnapshot.child("006").child("006D").getValue(Integer.class);
                            txt006A.setText(id006A+" Personas te compran aproximadamente 1 vez al mes");
                            txt006B.setText(id006B+" Personas te compran entre 2 a 3 veces al mes");
                            txt006C.setText(id006C+" Personas te compran entre 4 a 5 veces al mes");
                            txt006D.setText(id006D+" Personas te compran 6 veces al mes o más");

                            id007A = dataSnapshot.child("007").child("007A").getValue(Integer.class);
                            id007B = dataSnapshot.child("007").child("007B").getValue(Integer.class);
                            txt007A.setText(id007A+ " Personas piensan que la marca está acorde a las tendencias actuale");
                            txt007B.setText(id007B+ " Personas piensan que la marca NO está acorde a las tendencias actuales");

                            id008A = dataSnapshot.child("008").child("008A").getValue(Integer.class);
                            id008B = dataSnapshot.child("008").child("008B").getValue(Integer.class);
                            id008C = dataSnapshot.child("008").child("008C").getValue(Integer.class);
                            id008D = dataSnapshot.child("008").child("008D").getValue(Integer.class);
                            id008E = dataSnapshot.child("008").child("008E").getValue(Integer.class);
                            id008F = dataSnapshot.child("008").child("008F").getValue(Integer.class);
                            txt008A.setText(id008A+" Personas Perciben la marca como una Mujer Joven");
                            txt008B.setText(id008B+" Personas Perciben la marca como un Hombre Joven");
                            txt008C.setText(id008C+" Personas Perciben la marca como una Mujer Adulta");
                            txt008D.setText(id008D+" Personas Perciben la marca como un Hombre Adulto");
                            txt008E.setText(id008E+" Personas Perciben la marca como una Mujer Anciana");
                            txt008F.setText(id008F+" Personas Perciben la marca como un Hombre Anciana");

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
                company_social_reason = dataSnapshot.child("company_social_reason").getValue().toString();



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        btnMyCustomers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MyCustomersActivity.class);
                intent.putExtra("post_key",post_key);
                startActivity(intent);
            }
        });

        btnSendSurvey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (customers_exist.equals("true")) {
                    showSendCustomersSmsDialog();

                } else {
                    Snackbar.make(rootLayout, "Debes tener al menos un cliente registrado", Snackbar.LENGTH_LONG).show();
                    return;
                }
            }
        });

        return view;
    }

    private void showSendCustomersSmsDialog() {
        dialog = new AlertDialog.Builder(getActivity()).create();

        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View finance_method = inflater.inflate(R.layout.send_survey_confirmation_dialog,null);

        final Button btnSendSurvey;
        TextView txtCancel;

        btnSendSurvey = finance_method.findViewById(R.id.btnSendSurvey);
        txtCancel = finance_method.findViewById(R.id.txtCancel);

        btnSendSurvey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnSendSurvey.setEnabled(false);
                btnSendSurvey.setText("Enviado encuestas...");
                sendSmsMethod();
                registerCompanyAchievement();
            }
        });

        txtCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.setView(finance_method);
        dialog.show();
    }

    private void registerCompanyAchievement() {
        myCompanyRef.child(post_key).child("Achievements").child("Module2 Add Survey").child("score").setValue("70");
        myCompanyRef.child(post_key).child("Achievements").child("Module2 Add Survey").child("message").setValue("Has enviado las encuestas a tus clientes de forma exitosa");
        myCompanyRef.child(post_key).child("Achievements").child("Module2 Add Survey").child("timestamp").setValue(ServerValue.TIMESTAMP);
        Toasty.success(getActivity(), "Encuestas Enviadas", Toast.LENGTH_LONG).show();
        dialog.dismiss();
    }

    private void sendSmsMethod() {
        myCompanyRef.child(post_key).child("Customers").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot myDatasnapshot : dataSnapshot.getChildren()) {
                    customer_name = myDatasnapshot.child("customer_name").getValue().toString();
                    customer_phone = myDatasnapshot.child("customer_phone").getValue().toString();


                    String message = "Hola "+customer_name+", en Oliver te invitamos a responder una encuesta de "+company_social_reason+". Enlace de la encuesta: https://oliver.com.pe/company-survey/"+post_key;

                    SmsManager smsManager = SmsManager.getDefault();

                    ArrayList<String> parts =smsManager.divideMessage(message);

                    smsManager.sendMultipartTextMessage(customer_phone,null,parts,null,null);

                }
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
