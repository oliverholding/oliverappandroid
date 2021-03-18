package com.oalonedeveloper.oliver.oliverappandroidapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private final int REQUEST_LOGIN = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);
        setContentView(R.layout.activity_main);

        FirebaseAuth auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null) {
            if (!FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber().isEmpty()) {
                startActivity(new Intent(this, PinActivity.class)
                        .putExtra("phone", FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber().isEmpty()));
                finish();
            }
        }
        else
        {
            Intent intent = new Intent(MainActivity.this, PhoneAuthActivity.class);
            startActivity(intent);
            finish();
            /*startActivityForResult(AuthUI.getInstance()
                    .createSignInIntentBuilder().setAvailableProviders(Arrays.asList(new AuthUI.IdpConfig.Builder(AuthUI.PHONE_VERIFICATION_PROVIDER).build()
                    )).build(),REQUEST_LOGIN);*/
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_LOGIN)
        {
            IdpResponse response = IdpResponse.fromResultIntent(data);
            if (resultCode == RESULT_OK)
            {
                if (!FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber().isEmpty())
                {
                    //Here was the MainActivity
                    startActivity(new Intent(this, PinActivity.class).putExtra("phone",FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber()));
                    finish();
                    return;
                }
                else
                {
                    if (response == null)
                    {
                        Toast.makeText(this, "Cancelado", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (response.getErrorCode()== ErrorCodes.NO_NETWORK)
                    {
                        Toast.makeText(this, "Debes conectar a INTERNET", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (response.getErrorCode() == ErrorCodes.UNKNOWN_ERROR)
                    {
                        Toast.makeText(this, "Error desconocido", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    Toast.makeText(this, "Error de ingreso desconocido", Toast.LENGTH_SHORT).show();
                }
            }
        }

    }
}