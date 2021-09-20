package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Production.ProductDatasheet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

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
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Logistic.PurchaseOrder.PurchaseOrdersListActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;
import com.squareup.picasso.Picasso;

import es.dmoral.toasty.Toasty;

public class ProductDatasheetDetailActivity extends AppCompatActivity {

    DatabaseReference companyRef;
    String post_key,product_id,product_image,code,product_name,product_description,product_specifications,product_measurements,product_security_usage,product_price;
    TextView txtProductName,txtProductCode,txtDescription,txtSpecifications,txtMeasurements,txtSecurityUsage,txtProductPrice;
    ImageView imgProduct,btnEditDescription,btnSpecifications,btnMeasurements,btnSecurityUsage,btnEditName,btnEditPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_datasheet_detail);

        post_key = getIntent().getExtras().getString("post_key");
        product_id = getIntent().getExtras().getString("product_id");
        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");

        txtProductName = findViewById(R.id.txtProductName);
        txtProductCode = findViewById(R.id.txtProductCode);
        imgProduct = findViewById(R.id.imgProduct);
        btnEditDescription = findViewById(R.id.btnEditDescription);
        txtDescription = findViewById(R.id.txtDescription);
        btnSpecifications = findViewById(R.id.btnSpecifications);
        txtSpecifications = findViewById(R.id.txtSpecifications);
        txtMeasurements = findViewById(R.id.txtMeasurements);
        btnSecurityUsage = findViewById(R.id.btnSecurityUsage);
        btnMeasurements = findViewById(R.id.btnMeasurements);
        txtMeasurements = findViewById(R.id.txtMeasurements);
        txtSecurityUsage = findViewById(R.id.txtSecurityUsage);
        btnEditName = findViewById(R.id.btnEditName);
        btnEditPrice = findViewById(R.id.btnEditPrice);
        txtProductPrice = findViewById(R.id.txtProductPrice);

        companyRef.child(post_key).child("My Products").child(product_id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                product_image = dataSnapshot.child("product_image").getValue().toString();
                code = dataSnapshot.child("code").getValue().toString();
                product_name = dataSnapshot.child("product_name").getValue().toString();
                product_price = dataSnapshot.child("product_price").getValue().toString();

                if (dataSnapshot.hasChild("product_description")) {
                    product_description = dataSnapshot.child("product_description").getValue().toString();
                    txtDescription.setText(product_description);
                }

                if (dataSnapshot.hasChild("product_specifications")) {
                    product_specifications = dataSnapshot.child("product_specifications").getValue().toString();
                    txtSpecifications.setText(product_specifications);
                } else {
                    txtSpecifications.setText("Sin información");
                }


                if (dataSnapshot.hasChild("product_measurements")) {
                    product_measurements = dataSnapshot.child("product_measurements").getValue().toString();
                    txtMeasurements.setText(product_measurements);
                } else {
                    txtMeasurements.setText("Sin información");
                }

                if (dataSnapshot.hasChild("product_security_usage")) {
                    product_security_usage = dataSnapshot.child("product_security_usage").getValue().toString();
                    txtSecurityUsage.setText(product_security_usage);
                } else {
                    txtSecurityUsage.setText("Sin información");
                }


                Picasso.with(ProductDatasheetDetailActivity.this).load(product_image).fit().into(imgProduct);

                txtProductName.setText(product_name);
                txtProductPrice.setText("S/ "+product_price);
                txtProductCode.setText("Código: "+code);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        btnEditDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = "Descripción del Producto";
                String path = "product_description";
                showDataDialog(message,path);

            }
        });

        btnSpecifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = "Propiedades y Especificaciones";
                String path = "product_specifications";
                showDataDialog(message,path);

            }
        });

        btnMeasurements.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = "Medidas";
                String path = "product_measurements";
                showDataDialog(message,path);

            }
        });

        btnSecurityUsage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = "Normas de Seguridad y Uso";
                String path = "product_security_usage";
                showDataDialog(message,path);

            }
        });

        btnEditName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = "Nombre";
                String path = "product_name";
                showDataDialog(message,path);
            }
        });

        btnEditPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = "Precio";
                String path = "product_price";
                showDataDialog(message,path);
            }
        });
    }

    private void showDataDialog(String message, final String path) {

        final AlertDialog dialog = new AlertDialog.Builder(ProductDatasheetDetailActivity.this).create();

        LayoutInflater inflater = LayoutInflater.from(ProductDatasheetDetailActivity.this);
        View finance_method = inflater.inflate(R.layout.data_sheet_information_dialog,null);

        TextView txtMessage;
        final EditText edtInput;
        Button btnRegister;
        final RelativeLayout rootLayout;

        txtMessage = finance_method.findViewById(R.id.txtMessage);
        edtInput = finance_method.findViewById(R.id.edtInput);
        btnRegister = finance_method.findViewById(R.id.btnRegister);
        rootLayout = finance_method.findViewById(R.id.rootLayout);

        companyRef.child(post_key).child("My Products").child(product_id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String path_ds = dataSnapshot.child(path).getValue().toString();

                edtInput.setText(path_ds);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        txtMessage.setText(message);
        edtInput.setHint(message);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(edtInput.getText().toString())) {
                    Snackbar.make(rootLayout, "Debes completar la información", Snackbar.LENGTH_SHORT).show();
                } else {
                    companyRef.child(post_key).child("My Products").child(product_id).child(path).setValue(edtInput.getText().toString());
                    Toasty.success(ProductDatasheetDetailActivity.this, "Actualizado", Toast.LENGTH_LONG).show();
                    dialog.dismiss();
                }

            }
        });

        dialog.setView(finance_method);
        dialog.show();
    }
}
