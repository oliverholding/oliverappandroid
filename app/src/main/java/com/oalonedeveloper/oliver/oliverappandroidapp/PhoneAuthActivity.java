package com.oalonedeveloper.oliver.oliverappandroidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.google.android.material.snackbar.Snackbar;
import com.hbb20.CountryCodePicker;
import com.oalonedeveloper.oliver.oliverappandroidapp.EmailAuth.RegisterEmailActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.EmailAuth.SignInEmailActivity;

public class PhoneAuthActivity extends AppCompatActivity {

    CountryCodePicker countryPhoneCodePicker;
    EditText edtPhoneNumber;
    Button btnContinue,btnEmailSignIn,btnRegisterSignIn;
    RelativeLayout rootLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_auth);

        countryPhoneCodePicker= findViewById(R.id.countryPhoneCodePicker);
        edtPhoneNumber = findViewById(R.id.edtPhoneNumber);
        btnContinue = findViewById(R.id.btnContinue);
        rootLayout = findViewById(R.id.rootLayout);
        btnEmailSignIn = findViewById(R.id.btnEmailSignIn);
        btnRegisterSignIn = findViewById(R.id.btnRegisterSignIn);


        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(edtPhoneNumber.getText().toString())) {
                    Snackbar.make(rootLayout, "Debes ingresar tu número de teléfono", Snackbar.LENGTH_LONG).show();
                } else if (!TextUtils.isEmpty(edtPhoneNumber.getText().toString())){
                    Intent intent = new Intent(PhoneAuthActivity.this, SmsActivity.class);
                    intent.putExtra("country_code",countryPhoneCodePicker.getSelectedCountryCode());
                    intent.putExtra("phone_number",edtPhoneNumber.getText().toString());
                    startActivity(intent);
                }
            }
        });

        btnEmailSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PhoneAuthActivity.this, SignInEmailActivity.class);
                startActivity(intent);
            }
        });

        btnRegisterSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PhoneAuthActivity.this, RegisterEmailActivity.class);
                startActivity(intent);
            }
        });
    }
}