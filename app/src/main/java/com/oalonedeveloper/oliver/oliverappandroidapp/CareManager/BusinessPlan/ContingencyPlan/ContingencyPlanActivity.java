package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.BusinessPlan.ContingencyPlan;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.BusinessPlan.OperationPlan.OperationPlanActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;

import es.dmoral.toasty.Toasty;

public class ContingencyPlanActivity extends AppCompatActivity {

    DatabaseReference companyRef;
    String post_key;
    ImageView btnEdit1,btnEdit2,btnEdit3,btnEdit4,btnEdit5,btnEdit6;
    TextView txtInformation1,txtInformation2,txtInformation3,txtInformation4,txtInformation5,txtInformation6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contingency_plan);

        post_key = getIntent().getExtras().getString("post_key");
        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");

        btnEdit1 = findViewById(R.id.btnEdit1);
        btnEdit2 = findViewById(R.id.btnEdit2);
        btnEdit3 = findViewById(R.id.btnEdit3);
        btnEdit4 = findViewById(R.id.btnEdit4);
        btnEdit5 = findViewById(R.id.btnEdit5);
        btnEdit6 = findViewById(R.id.btnEdit6);

        txtInformation1 = findViewById(R.id.txtInformation1);
        txtInformation2 = findViewById(R.id.txtInformation2);
        txtInformation3 = findViewById(R.id.txtInformation3);
        txtInformation4 = findViewById(R.id.txtInformation4);
        txtInformation5 = findViewById(R.id.txtInformation5);
        txtInformation6 = findViewById(R.id.txtInformation6);

        btnEdit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path = "item_1";
                String question = "¿Qué estrategia plantearías si el entorno político juega en tu contra?";
                String title = "";
                showEditInfoDialog(path,question,title);
            }
        });
        btnEdit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path = "item_2";
                String question = "¿Qué estrategia plantearías si el entorno económico juega en tu contra?";
                String title = "";
                showEditInfoDialog(path,question,title);
            }
        });
        btnEdit3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path = "item_3";
                String question = "¿Qué estrategia plantearías si el entorno social juega en tu contra?";
                String title = "";
                showEditInfoDialog(path,question,title);
            }
        });
        btnEdit4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path = "item_4";
                String question = "¿Qué estrategia plantearías si el entorno tecnológico juega en tu contra?";
                String title = "";
                showEditInfoDialog(path,question,title);
            }
        });
        btnEdit5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path = "item_5";
                String question = "¿Qué estrategia plantearías si el entorno ambiental juega en tu contra?";
                String title = "";
                showEditInfoDialog(path,question,title);
            }
        });
        btnEdit6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path = "item_6";
                String question = "¿Qué estrategia plantearías si el entorno legal juega en tu contra?";
                String title = "";
                showEditInfoDialog(path,question,title);
            }
        });

        companyRef.child(post_key).child("Business Plan").child("Contingency Plan").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild("item_1")) {
                    String value = dataSnapshot.child("item_1").getValue().toString();
                    txtInformation1.setText(value);
                }
                if (dataSnapshot.hasChild("item_2")) {
                    String value = dataSnapshot.child("item_2").getValue().toString();
                    txtInformation2.setText(value);
                }
                if (dataSnapshot.hasChild("item_3")) {
                    String value = dataSnapshot.child("item_3").getValue().toString();
                    txtInformation3.setText(value);
                }
                if (dataSnapshot.hasChild("item_4")) {
                    String value = dataSnapshot.child("item_4").getValue().toString();
                    txtInformation4.setText(value);
                }
                if (dataSnapshot.hasChild("item_5")) {
                    String value = dataSnapshot.child("item_5").getValue().toString();
                    txtInformation5.setText(value);
                }
                if (dataSnapshot.hasChild("item_6")) {
                    String value = dataSnapshot.child("item_6").getValue().toString();
                    txtInformation6.setText(value);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void showEditInfoDialog(final String path, String question, String title) {
        final AlertDialog dialog = new AlertDialog.Builder(this).create();

        LayoutInflater inflater = LayoutInflater.from(this);
        View finance_method = inflater.inflate(R.layout.contingency_plan_edit_dialog,null);

        TextView txtQuestion;
        final EditText edtInput;
        Button btnFinish;

        txtQuestion = finance_method.findViewById(R.id.txtQuestion);
        edtInput = finance_method.findViewById(R.id.edtInput);
        btnFinish = finance_method.findViewById(R.id.btnFinish);

        txtQuestion.setText(question);

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                companyRef.child(post_key).child("Business Plan").child("Contingency Plan").child(path).setValue(edtInput.getText().toString());
                Toasty.success(ContingencyPlanActivity.this, "Registrado", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });


        dialog.setView(finance_method);
        dialog.show();
    }
}
