package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.BusinessPlan.ExecutiveSumary;

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

public class ExecutiveSumaryActivity extends AppCompatActivity {

    DatabaseReference companyRef;
    String post_key;
    ImageView btnEdit;
    TextView txtInformation1,txtInformation2,txtInformation3,txtInformation4,txtInformation5,txtInformation6,txtInformation7,txtInformation8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_executive_sumary);

        post_key = getIntent().getExtras().getString("post_key");
        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");

        btnEdit = findViewById(R.id.btnEdit);
        txtInformation1 = findViewById(R.id.txtInformation1);
        txtInformation2 = findViewById(R.id.txtInformation2);
        txtInformation3 = findViewById(R.id.txtInformation3);
        txtInformation4 = findViewById(R.id.txtInformation4);
        txtInformation5 = findViewById(R.id.txtInformation5);
        txtInformation6 = findViewById(R.id.txtInformation6);
        txtInformation7 = findViewById(R.id.txtInformation7);
        txtInformation8 = findViewById(R.id.txtInformation8);

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showEditDialog();
            }
        });

        companyRef.child(post_key).child("Business Plan").child("Executive Plan").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild("about")) {
                    String value = dataSnapshot.child("about").getValue().toString();
                    txtInformation1.setText(value);
                }
                if (dataSnapshot.hasChild("customers")) {
                    String value = dataSnapshot.child("customers").getValue().toString();
                    txtInformation2.setText(value);
                }
                if (dataSnapshot.hasChild("value")) {
                    String value = dataSnapshot.child("value").getValue().toString();
                    txtInformation3.setText(value);
                }
                if (dataSnapshot.hasChild("market")) {
                    String value = dataSnapshot.child("market").getValue().toString();
                    txtInformation4.setText(value);
                }
                if (dataSnapshot.hasChild("competitive")) {
                    String value = dataSnapshot.child("competitive").getValue().toString();
                    txtInformation5.setText(value);
                }
                if (dataSnapshot.hasChild("investment")) {
                    String value = dataSnapshot.child("investment").getValue().toString();
                    txtInformation6.setText(value);
                }
                if (dataSnapshot.hasChild("issues")) {
                    String value = dataSnapshot.child("issues").getValue().toString();
                    txtInformation7.setText(value);
                }
                if (dataSnapshot.hasChild("objectives")) {
                    String value = dataSnapshot.child("objectives").getValue().toString();
                    txtInformation8.setText(value);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void showEditDialog() {
        final AlertDialog dialog = new AlertDialog.Builder(this).create();

        LayoutInflater inflater = LayoutInflater.from(this);
        View finance_method = inflater.inflate(R.layout.excutive_sumary_dialog,null);

        final EditText edtAbout,edtCustomers,edtValue,edtMarket,edtCompetitive,edtInvestment,edtIssues,edtObjectives;
        Button btnFinish;

        edtAbout = finance_method.findViewById(R.id.edtAbout);
        edtCustomers = finance_method.findViewById(R.id.edtCustomers);
        edtValue = finance_method.findViewById(R.id.edtValue);
        edtMarket = finance_method.findViewById(R.id.edtMarket);
        edtCompetitive = finance_method.findViewById(R.id.edtCompetitive);
        edtInvestment = finance_method.findViewById(R.id.edtInvestment);
        edtIssues = finance_method.findViewById(R.id.edtIssues);
        edtObjectives = finance_method.findViewById(R.id.edtObjectives);
        btnFinish = finance_method.findViewById(R.id.btnFinish);

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                companyRef.child(post_key).child("Business Plan").child("Executive Plan").child("about").setValue(edtAbout.getText().toString());
                companyRef.child(post_key).child("Business Plan").child("Executive Plan").child("customers").setValue(edtCustomers.getText().toString());
                companyRef.child(post_key).child("Business Plan").child("Executive Plan").child("value").setValue(edtValue.getText().toString());
                companyRef.child(post_key).child("Business Plan").child("Executive Plan").child("market").setValue(edtMarket.getText().toString());
                companyRef.child(post_key).child("Business Plan").child("Executive Plan").child("competitive").setValue(edtCompetitive.getText().toString());
                companyRef.child(post_key).child("Business Plan").child("Executive Plan").child("investment").setValue(edtInvestment.getText().toString());
                companyRef.child(post_key).child("Business Plan").child("Executive Plan").child("issues").setValue(edtIssues.getText().toString());
                companyRef.child(post_key).child("Business Plan").child("Executive Plan").child("objectives").setValue(edtObjectives.getText().toString());
                Toasty.success(ExecutiveSumaryActivity.this, "Registrado", Toast.LENGTH_SHORT).show();
                dialog.dismiss();

            }
        });

        dialog.setView(finance_method);
        dialog.show();
    }
}
