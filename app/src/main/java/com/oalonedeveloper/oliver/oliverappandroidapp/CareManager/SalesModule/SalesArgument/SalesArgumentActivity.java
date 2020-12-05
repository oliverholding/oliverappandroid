package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.SalesModule.SalesArgument;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;

import java.util.ArrayList;

import in.galaxyofandroid.spinerdialog.OnSpinerItemClick;
import in.galaxyofandroid.spinerdialog.SpinnerDialog;

public class SalesArgumentActivity extends AppCompatActivity {

    Button btnGreeting,btnCharacteristic,btnCompanySchedule,btnShowPhrase;
    EditText edtSellerName,edtProductName,edtCompanyNumber;
    CheckBox cb1,cb2,cb3,cb4;
    DatabaseReference companyRef;
    String post_key,company_name,payment_method1,payment_method2,payment_method3,payment_method4;
    RelativeLayout rootLayout;

    ArrayList<String> greetingsArray =new ArrayList<>();
    SpinnerDialog spinnerGreetings;

    ArrayList<String> characteristicsArray =new ArrayList<>();
    SpinnerDialog spinnerCharacteristics;

    ArrayList<String> scheduleArray =new ArrayList<>();
    SpinnerDialog spinnerSchedule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_argument);

        btnGreeting = findViewById(R.id.btnGreeting);
        btnCharacteristic = findViewById(R.id.btnCharacteristic);
        btnCompanySchedule = findViewById(R.id.btnCompanySchedule);
        edtSellerName = findViewById(R.id.edtSellerName);
        edtProductName = findViewById(R.id.edtProductName);
        edtCompanyNumber = findViewById(R.id.edtCompanyNumber);
        cb1 = findViewById(R.id.cb1);
        cb2 = findViewById(R.id.cb2);
        cb3 = findViewById(R.id.cb3);
        cb4 = findViewById(R.id.cb4);
        btnShowPhrase = findViewById(R.id.btnShowPhrase);
        rootLayout = findViewById(R.id.rootLayout);

        payment_method1 = "";
        payment_method2 = "";
        payment_method3 = "";
        payment_method4 = "";

        post_key = getIntent().getExtras().getString("post_key");
        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");

        companyRef.child(post_key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                company_name = dataSnapshot.child("company_name").getValue().toString();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        greetingsArray.add("Buenos días");greetingsArray.add("Buenas Tardes");greetingsArray.add("Buenas Noches");

        btnGreeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinnerGreetings.showSpinerDialog();
            }
        });

        spinnerGreetings = new SpinnerDialog(this,greetingsArray, "Selecciona el Saludo");
        spinnerGreetings.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String item2, int position2) {
                btnGreeting.setText(item2);

            }
        });

        characteristicsArray.add("Precio");characteristicsArray.add("Calidad");characteristicsArray.add("Variedad");

        btnCharacteristic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinnerCharacteristics.showSpinerDialog();
            }
        });

        spinnerCharacteristics = new SpinnerDialog(this,characteristicsArray, "Selecciona la Diferenciación");
        spinnerCharacteristics.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String item2, int position2) {
                btnCharacteristic.setText(item2);

            }
        });

        scheduleArray.add("L-V 8am a 6pm");scheduleArray.add("L-V 8am a 8pm; S 8am-1pm");scheduleArray.add("L-S 8am a 8pm");

        btnCompanySchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinnerSchedule.showSpinerDialog();
            }
        });

        spinnerSchedule = new SpinnerDialog(this,scheduleArray, "Selecciona el Horario de Atención");
        spinnerSchedule.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String item2, int position2) {
                btnCompanySchedule.setText(item2);

            }
        });

        btnShowPhrase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(btnGreeting.getText().toString())) {
                    Snackbar.make(rootLayout, "Debes seleccionar el saludo", Snackbar.LENGTH_LONG).show();
                    return;
                } else if (TextUtils.isEmpty(edtSellerName.getText().toString())) {
                    Snackbar.make(rootLayout, "Debes ingresar el nombre del vendedor", Snackbar.LENGTH_LONG).show();
                    return;
                } else if (TextUtils.isEmpty(edtProductName.getText().toString())) {
                    Snackbar.make(rootLayout, "Debes ingresar el nombre producto o servicio", Snackbar.LENGTH_LONG).show();
                    return;
                } else if (TextUtils.isEmpty(btnCharacteristic.getText().toString())) {
                    Snackbar.make(rootLayout, "Debes seleccionar la diferenciación", Snackbar.LENGTH_LONG).show();
                    return;
                } else if (!cb1.isChecked() && !cb2.isChecked() && !cb3.isChecked() && !cb4.isChecked()) {
                    Snackbar.make(rootLayout, "Debes seleccionar una forma de pago", Snackbar.LENGTH_LONG).show();
                    return;
                } else if (TextUtils.isEmpty(edtCompanyNumber.getText().toString())) {
                    Snackbar.make(rootLayout, "Debes ingresar el teléfono de contacto", Snackbar.LENGTH_LONG).show();

                } else if (TextUtils.isEmpty(btnCompanySchedule.getText().toString())) {
                    Snackbar.make(rootLayout, "Debes seleccionar un horario", Snackbar.LENGTH_LONG).show();

                } else {
                    showPhraseDialog();
                }

            }
        });

    }

    private void showPhraseDialog() {

        final AlertDialog dialog = new AlertDialog.Builder(this).create();

        LayoutInflater inflater = LayoutInflater.from(this);
        View finance_method = inflater.inflate(R.layout.sales_argument_phrase_dialog,null);

        if (cb1.isChecked()) {
            payment_method1 = "Efectivo";
        }
        if (cb2.isChecked()) {
            payment_method2 = "Tarjetas de Débito y Crédito";
        }
        if (cb3.isChecked()) {
            payment_method3 = "Yape";
        }
        if (cb4.isChecked()) {
            payment_method4 = "Transferencias Bancarias";
        }

        TextView txtPhrase,txtPhrase2;

        txtPhrase = finance_method.findViewById(R.id.txtPhrase);
        txtPhrase2 = finance_method.findViewById(R.id.txtPhrase2);

        txtPhrase.setText(btnGreeting.getText().toString()+", mi nombre es "+edtSellerName.getText().toString()+" de "+company_name+", y le contactamos porque quisiera presentarle nuestro "+edtProductName.getText().toString()+" que estamos seguros que les va a interesar.");
        txtPhrase2.setText("Nuestro "+edtProductName.getText().toString()+" se diferencia por "+btnCharacteristic.getText().toString()+" que no lo tiene nuestra competencia. Sus pagos lo pueden realizar de lassiguientes formas: "+payment_method1+" "+payment_method2+" "+payment_method3+" "+payment_method4+". Si lo requiere pregunte por nuestros múltiples servicios. Pueden hacer sus pedidos por whatsapp al teléfono: "+edtCompanyNumber.getText().toString()+" en el siguiente horario: "+btnCompanySchedule.getText().toString());

        dialog.setView(finance_method);
        dialog.show();

    }
}
