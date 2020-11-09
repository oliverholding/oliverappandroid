package com.oalonedeveloper.oliver.oliverappandroidapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class SmsActivity extends AppCompatActivity {

    EditText edtSmsCode;
    Button btnVerify;
    String country_code,phone_number,verificationCodeBySystem;
    ProgressBar progressbar;
    RelativeLayout rootLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);

        edtSmsCode = findViewById(R.id.edtSmsCode);
        btnVerify = findViewById(R.id.btnVerify);
        progressbar = findViewById(R.id.progressbar);
        rootLayout = findViewById(R.id.rootLayout);

        country_code = getIntent().getExtras().getString("country_code");
        phone_number = getIntent().getExtras().getString("phone_number");

        //Toast.makeText(this, "CODE: "+country_code, Toast.LENGTH_SHORT).show();

        sendVerificationCodeToUser(phone_number);

        btnVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
                if (TextUtils.isEmpty(edtSmsCode.getText().toString())) {
                    Snackbar.make(rootLayout, "Debes ingresar el código de verificación", Snackbar.LENGTH_LONG).show();

                }
                else if (edtSmsCode.getText().toString().length() != 6) {
                    Snackbar.make(rootLayout, "Debes ingresar un código de verificación válido", Snackbar.LENGTH_LONG).show();

                }
                else if (edtSmsCode.getText().toString().length() != 6) {
                    Snackbar.make(rootLayout, "Debes ingresar un código de verificación válido", Snackbar.LENGTH_LONG).show();
                    return;
                }
                else {
                    progressbar.setVisibility(View.VISIBLE);
                    verifyCode(edtSmsCode.getText().toString());
                }
            }
        });


    }

    private void sendVerificationCodeToUser(String phone) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber("+"+country_code+phone,60, TimeUnit.SECONDS, TaskExecutors.MAIN_THREAD,mCallbacks);

    }

    private  PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);

            verificationCodeBySystem = s;
        }

        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            String code = phoneAuthCredential.getSmsCode();
            if (code != null) {
                progressbar.setVisibility(View.VISIBLE);
                verifyCode(code);
            }
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            Toast.makeText(SmsActivity.this, e.getMessage()+"", Toast.LENGTH_SHORT).show();

        }
    };

    private void verifyCode(String codeByUser) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationCodeBySystem,codeByUser);
        signInTheUserByCredentials(credential);
    }

    private void signInTheUserByCredentials(PhoneAuthCredential credential) {
        FirebaseAuth firebaseAuth =FirebaseAuth.getInstance();
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener(SmsActivity.this,new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Intent intent = new Intent(SmsActivity.this, PinActivity.class);
                    startActivity(intent);
                    finish();

                } else {
                    Snackbar.make(rootLayout, "Código Inválido", Snackbar.LENGTH_LONG).show();
                    progressbar.setVisibility(View.INVISIBLE);
                    //Toast.makeText(SmsVerificationActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}