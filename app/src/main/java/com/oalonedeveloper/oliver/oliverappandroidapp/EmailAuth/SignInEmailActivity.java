package com.oalonedeveloper.oliver.oliverappandroidapp.EmailAuth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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

public class SignInEmailActivity extends AppCompatActivity {

    EditText edtEmail,edtPassword;
    Button btnContinue;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_email);

        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        btnContinue = findViewById(R.id.btnContinue);
        mAuth = FirebaseAuth.getInstance();

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(edtEmail.getText().toString())) {
                    Toast.makeText(SignInEmailActivity.this, "Debes ingresar tu correo", Toast.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(edtPassword.getText().toString())) {
                    Toast.makeText(SignInEmailActivity.this, "Debes ingresar tu contraseña", Toast.LENGTH_SHORT).show();
                }
                else {
                    btnContinue.setText("Ingresando...");
                    btnContinue.setEnabled(false);
                    mAuth.signInWithEmailAndPassword(edtEmail.getText().toString(), edtPassword.getText().toString())
                            .addOnCompleteListener(SignInEmailActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Intent intent = new Intent(SignInEmailActivity.this, PinActivity.class);
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        btnContinue.setEnabled(true);
                                        btnContinue.setText("Ingresar");
                                        Toast.makeText(SignInEmailActivity.this, "Algo salió mal...", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                }
            }
        });
    }
}