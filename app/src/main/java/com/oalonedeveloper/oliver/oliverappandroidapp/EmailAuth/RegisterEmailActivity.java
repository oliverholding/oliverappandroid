package com.oalonedeveloper.oliver.oliverappandroidapp.EmailAuth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.oalonedeveloper.oliver.oliverappandroidapp.PinActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;

public class RegisterEmailActivity extends AppCompatActivity {

    EditText edtEmail,edtPassword,edtConfirmPassword;
    Button btnContinue;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_email);

        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        edtConfirmPassword = findViewById(R.id.edtConfirmPassword);
        btnContinue = findViewById(R.id.btnContinue);
        mAuth = FirebaseAuth.getInstance();

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(edtEmail.getText().toString())) {
                    Toast.makeText(RegisterEmailActivity.this, "Debes ingresar tu correo", Toast.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(edtPassword.getText().toString())) {
                    Toast.makeText(RegisterEmailActivity.this, "Debes ingresar tu contraseña", Toast.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(edtConfirmPassword.getText().toString())) {
                    Toast.makeText(RegisterEmailActivity.this, "Debes confirmar tu contraseña", Toast.LENGTH_SHORT).show();
                } else if (!edtPassword.getText().toString().equals(edtConfirmPassword.getText().toString())) {
                    Toast.makeText(RegisterEmailActivity.this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                }else if (edtPassword.getText().toString().length() < 6) {
                    Toast.makeText(RegisterEmailActivity.this, "Contraseña muy corta", Toast.LENGTH_SHORT).show();
                } else {
                    btnContinue.setText("Registrando...");
                    btnContinue.setEnabled(false);
                    mAuth.createUserWithEmailAndPassword(edtEmail.getText().toString(),edtPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Intent intent = new Intent(RegisterEmailActivity.this, PinActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                // If sign in fails, display a message to the user.
                                btnContinue.setEnabled(true);
                                btnContinue.setText("Registrarse");
                                Toast.makeText(RegisterEmailActivity.this, "Algo salió mal...", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
}