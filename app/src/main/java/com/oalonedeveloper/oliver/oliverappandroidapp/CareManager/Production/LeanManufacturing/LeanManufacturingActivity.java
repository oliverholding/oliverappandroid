package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Production.LeanManufacturing;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Logistic.Outsoursing.OutsoursingActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.StrategicDirection.LeanCanvas.LeanCanvasActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

import de.hdodenhof.circleimageview.CircleImageView;
import es.dmoral.toasty.Toasty;

public class LeanManufacturingActivity extends AppCompatActivity {

    DatabaseReference companyRef;
    String post_key,product_name,product_image;
    ImageView btnActionButton1,btnActionButton2,btnActionButton3,btnActionButton4,btnActionButton5;
    TextView txtItem1,txtItem2,txtItem3,txtItem4,txtItem5,txtItem6;
    DecimalFormat decimalFormat;
    double item_1,item_2,item_3,item_4,item_5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lean_manufacturing);

        post_key = getIntent().getExtras().getString("post_key");
        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");

        btnActionButton1 = findViewById(R.id.btnActionButton1);
        btnActionButton2 = findViewById(R.id.btnActionButton2);
        btnActionButton3 = findViewById(R.id.btnActionButton3);
        btnActionButton4 = findViewById(R.id.btnActionButton4);
        btnActionButton5 = findViewById(R.id.btnActionButton5);

        txtItem1 = findViewById(R.id.txtItem1);
        txtItem2 = findViewById(R.id.txtItem2);
        txtItem3 = findViewById(R.id.txtItem3);
        txtItem4 = findViewById(R.id.txtItem4);
        txtItem5 = findViewById(R.id.txtItem5);
        txtItem6 = findViewById(R.id.txtItem6);

        decimalFormat = new DecimalFormat("0.00");

        companyRef.child(post_key).child("Lean Manufacturing").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild("item_1")) {
                    String value = dataSnapshot.child("item_1").getValue().toString();
                    txtItem1.setText(value+"%");

                    item_1 = Double.parseDouble(value);
                }
                if (dataSnapshot.hasChild("item_2")) {
                    String value = dataSnapshot.child("item_2").getValue().toString();
                    txtItem2.setText(value+"%");

                    item_2 = Double.parseDouble(value);
                }
                if (dataSnapshot.hasChild("item_3")) {
                    String value = dataSnapshot.child("item_3").getValue().toString();
                    txtItem3.setText(value+"%");

                    item_3 = Double.parseDouble(value);
                }
                if (dataSnapshot.hasChild("item_4")) {
                    String value = dataSnapshot.child("item_4").getValue().toString();
                    txtItem4.setText(value+"%");

                    item_4 = Double.parseDouble(value);
                }
                if (dataSnapshot.hasChild("item_5")) {
                    String value = dataSnapshot.child("item_5").getValue().toString();
                    txtItem5.setText(value+"%");

                    item_5 = Double.parseDouble(value);
                }

                double weighted = (item_1*0.20)+(item_2*0.20)+(item_3*0.20)+(item_4*0.20)+(item_5*0.20);
                String weighted_st = decimalFormat.format(weighted);
                txtItem6.setText(weighted_st+"%");

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        btnActionButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = "SOBREPRODUCCIÓN";
                String question = "¿Del 0% al 100% qué tanto peso tiene la Sobreproducción en tu negocio?";
                String path = "item_1";

                showLeanDialog(title,question,path);
            }
        });
        btnActionButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = "ESPERA";
                String question = "¿Del 0% al 100% qué tanto peso tiene la espera en tu negocio?";
                String path = "item_2";

                showLeanDialog(title,question,path);
            }
        });
        btnActionButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = "TRANSPORTE";
                String question = "¿Del 0% al 100% qué tanto peso tiene el transporte en tu negocio?";
                String path = "item_3";

                showLeanDialog(title,question,path);
            }
        });
        btnActionButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = "PROCESO EXTRA";
                String question = "¿Del 0% al 100% qué tanto peso tiene el proceso extra en tu negocio?";
                String path = "item_4";

                showLeanDialog(title,question,path);
            }
        });
        btnActionButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = "INVENTARIO";
                String question = "¿Del 0% al 100% qué tanto peso tiene Inventario extra en tu negocio?";
                String path = "item_5";

                showLeanDialog(title,question,path);
            }
        });

    }

    private void showLeanDialog(String title, String question, final String path) {
        final AlertDialog dialog = new AlertDialog.Builder(this).create();

        LayoutInflater inflater = LayoutInflater.from(this);
        View finance_method = inflater.inflate(R.layout.lean_manufacturing_set_data_dialog,null);

        TextView txtTitle,txtQuestion;
        final EditText edtInput;
        Button btnFinish;
        final RelativeLayout rootLayout;

        txtTitle = finance_method.findViewById(R.id.txtTitle);
        txtQuestion = finance_method.findViewById(R.id.txtQuestion);
        edtInput = finance_method.findViewById(R.id.edtInput);
        btnFinish = finance_method.findViewById(R.id.btnFinish);
        rootLayout = finance_method.findViewById(R.id.rootLayout);

        txtTitle.setText(title);
        txtQuestion.setText(question);

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(edtInput.getText().toString())) {
                    Snackbar.make(rootLayout, "Debes completar el dato", Snackbar.LENGTH_SHORT).show();
                } else {
                    companyRef.child(post_key).child("Lean Manufacturing").child(path).setValue(edtInput.getText().toString());
                    Toasty.success(LeanManufacturingActivity.this, "Registrado", Toast.LENGTH_LONG).show();
                    dialog.dismiss();
                }


            }
        });

        dialog.setView(finance_method);
        dialog.show();
    }
}
