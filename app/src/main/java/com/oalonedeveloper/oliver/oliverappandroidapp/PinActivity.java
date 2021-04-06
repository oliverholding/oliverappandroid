package com.oalonedeveloper.oliver.oliverappandroidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.oalonedeveloper.oliver.oliverappandroidapp.RegistrationData.RegistrationDataActivity;

public class PinActivity extends AppCompatActivity {

    RelativeLayout rootLayout;
    EditText edtPin;
    Button btnConfirmPin;
    TextView txtMessage,txtCurrentVersion;;
    DatabaseReference userRef, permissionRef;
    FirebaseAuth mAuth;
    String currentUserID, pin,username;
    int pin_attempts;
    ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin);

        loadingBar = new ProgressDialog(this);
        loadingBar.setTitle("Preparando todo...");
        loadingBar.setMessage("Cargando...");
        loadingBar.show();
        loadingBar.setCanceledOnTouchOutside(false);
        loadingBar.setCancelable(false);

        rootLayout = findViewById(R.id.rootLayout);
        edtPin = findViewById(R.id.edtPin);
        btnConfirmPin = findViewById(R.id.btnConfirmPin);
        txtMessage = findViewById(R.id.txtMessage);
        txtCurrentVersion = findViewById(R.id.txtCurrentVersion);

        mAuth = FirebaseAuth.getInstance();
        currentUserID = mAuth.getCurrentUser().getUid();
        userRef = FirebaseDatabase.getInstance().getReference().child("Users");


        int date_time_checker = android.provider.Settings.Global.getInt(getContentResolver(), android.provider.Settings.Global.AUTO_TIME,0);

        if (date_time_checker == 0) {
            showDateErrorDialog();
        }

        userRef.child(currentUserID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("user_type")) {
                    pin = dataSnapshot.child("pin").getValue().toString();
                    username = dataSnapshot.child("username").getValue().toString();
                    pin_attempts = dataSnapshot.child("pin_attempts").getValue(Integer.class);

                    txtMessage.setText("Hola, " + username + "!");


                    loadingBar.dismiss();
                } else {
                    Intent intent = new Intent(PinActivity.this, RegistrationDataActivity.class);
                    startActivity(intent);
                    finish();
                    loadingBar.dismiss();
                }

                if (pin_attempts >= 3) {
                    showRestrictionDialog();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        btnConfirmPin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(edtPin.getText().toString()))
                {
                    Snackbar.make(rootLayout, "Debes ingresar tu PIN de seguridad...", Snackbar.LENGTH_LONG).show();
                    return;
                }
                if (!edtPin.getText().toString().equals(pin))
                {
                    int pin_inrtents_updated = pin_attempts+1;

                    int intents_allowed = 3-pin_inrtents_updated;

                    userRef.child(currentUserID).child("pin_attempts").setValue(pin_inrtents_updated);
                    Snackbar.make(rootLayout, "PIN INCORRECTO, TE QUEDAN "+intents_allowed+" INTENTOS", Snackbar.LENGTH_LONG).show();
                    return;
                }
                if (edtPin.getText().toString().equals(pin))
                {
                    btnConfirmPin.setEnabled(false);
                    //Intent intent = new Intent(PinActivity.this, OliverAppActivity.class);
                    Intent intent = new Intent(PinActivity.this, PlatformSelectionActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

    }

    private void showRestrictionDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setCancelable(false);

        LayoutInflater inflater = LayoutInflater.from(this);
        View finance_method = inflater.inflate(R.layout.pin_restriction_dialog,null);


        dialog.setView(finance_method);
        dialog.show();
    }

    private void showDateErrorDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setCancelable(false);

        LayoutInflater inflater = LayoutInflater.from(this);
        View finance_method = inflater.inflate(R.layout.date_timte_auto_no_active_dialog,null);


        dialog.setView(finance_method);
        dialog.show();
    }
}