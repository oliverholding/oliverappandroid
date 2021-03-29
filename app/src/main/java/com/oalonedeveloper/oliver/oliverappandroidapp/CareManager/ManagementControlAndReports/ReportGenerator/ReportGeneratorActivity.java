package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.ManagementControlAndReports.ReportGenerator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Logistic.OrderProcessing.OrderProcessingActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;
import com.oalonedeveloper.oliver.oliverappandroidapp.WebViewActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

import es.dmoral.toasty.Toasty;

public class ReportGeneratorActivity extends AppCompatActivity {

    ImageButton btnExcel1,btnPdf1,btnExcel2,btnPdf2,btnExcel3,btnPdf3,btnExcel4,btnPdf4,btnExcel5,btnPdf5,btnExcel6,btnPdf6,btnExcel7,btnPdf7,btnExcel8,btnPdf8;
    DatabaseReference companyRef;
    String post_key;
    String state = "";
    String payment = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_generator);

        btnExcel1 = findViewById(R.id.btnExcel1);
        btnPdf1 = findViewById(R.id.btnPdf1);
        btnExcel2 = findViewById(R.id.btnExcel2);
        btnPdf2 = findViewById(R.id.btnPdf2);
        btnExcel3 = findViewById(R.id.btnExcel3);
        btnPdf3 = findViewById(R.id.btnPdf3);
        btnExcel4 = findViewById(R.id.btnExcel4);
        btnPdf4 = findViewById(R.id.btnPdf4);
        btnExcel5 = findViewById(R.id.btnExcel5);
        btnPdf5 = findViewById(R.id.btnPdf5);
        btnExcel6 = findViewById(R.id.btnExcel6);
        btnPdf6 = findViewById(R.id.btnPdf6);
        btnExcel7 = findViewById(R.id.btnExcel7);
        btnPdf7 = findViewById(R.id.btnPdf7);
        btnExcel8 = findViewById(R.id.btnExcel8);
        btnPdf8 = findViewById(R.id.btnPdf8);

        post_key = getIntent().getExtras().getString("post_key");
        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");

        btnExcel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exportExcel1();
            }
        });

        btnExcel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exportExcel2();
            }
        });

        btnExcel3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exportExcel3();
            }
        });

        btnExcel4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exportExcel4();
            }
        });

        btnExcel5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exportExcel5();
            }
        });

        btnExcel6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exportExcel6();
            }
        });

        btnExcel7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exportExcel7();
            }
        });

        btnExcel8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exportExcel8();
            }
        });

        btnPdf1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://oliver.com.pe/oliver-manager-login/user/company-reports/"+post_key+"/products"));
                startActivity(browserIntent);
            }
        });
        btnPdf2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://oliver.com.pe/oliver-manager-login/user/company-reports/"+post_key+"/bills"));
                startActivity(browserIntent);
            }
        });
        btnPdf3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://oliver.com.pe/oliver-manager-login/user/company-reports/"+post_key+"/sellers"));
                startActivity(browserIntent);
            }
        });
        btnPdf4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://oliver.com.pe/oliver-manager-login/user/company-reports/"+post_key+"/purchased_order"));
                startActivity(browserIntent);

            }
        });
        btnPdf5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://oliver.com.pe/oliver-manager-login/user/company-reports/"+post_key+"/suppliers"));
                startActivity(browserIntent);
            }
        });
        btnPdf6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://oliver.com.pe/oliver-manager-login/user/company-reports/"+post_key+"/warehouses"));
                startActivity(browserIntent);

            }
        });
        btnPdf7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://oliver.com.pe/oliver-manager-login/user/company-reports/"+post_key+"/production_chain"));
                startActivity(browserIntent);

            }
        });
        btnPdf8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://oliver.com.pe/oliver-manager-login/user/company-reports/"+post_key+"/job_profiles"));
                startActivity(browserIntent);

            }
        });

    }

    private void exportExcel8() {
        final StringBuilder data = new StringBuilder();
        data.append("Nombres,Apellidos,Cargo,Área,Denominación,Misión,Jefe Inmediato");
        companyRef.child(post_key).child("Job Profile").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String job_profile_name = ds.child("job_profile_name").getValue().toString();
                    String job_profile_surname = ds.child("job_profile_surname").getValue().toString();
                    String job_profile_job_name = ds.child("job_profile_job_name").getValue().toString();
                    String job_profile_area = ds.child("job_profile_area").getValue().toString();
                    String job_profile_denomination = ds.child("job_profile_denomination").getValue().toString();
                    String job_profile_job_mission = ds.child("job_profile_job_mission").getValue().toString();
                    String job_profile_immediate_boss = ds.child("job_profile_immediate_boss").getValue().toString();

                    data.append("\n"+job_profile_name+","+job_profile_surname+","+job_profile_job_name+","+job_profile_area+","+job_profile_denomination+","+job_profile_job_mission+","+job_profile_immediate_boss);

                }
                try{
                    //saving the file into device
                    FileOutputStream out = openFileOutput("personal.csv", Context.MODE_PRIVATE);
                    out.write((data.toString()).getBytes());
                    out.close();

                    //exporting
                    Context context = getApplicationContext();
                    File filelocation = new File(getFilesDir(), "personal.csv");
                    Uri path = FileProvider.getUriForFile(context, "com.oalonedeveloper.oliver.oliverappandroidapp.fileprovider", filelocation);
                    Intent fileIntent = new Intent(Intent.ACTION_SEND);
                    fileIntent.setType("text/csv");
                    fileIntent.putExtra(Intent.EXTRA_SUBJECT, "Personal");
                    fileIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    fileIntent.putExtra(Intent.EXTRA_STREAM, path);
                    startActivity(Intent.createChooser(fileIntent, "Send mail"));
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void exportExcel7() {
        final StringBuilder data = new StringBuilder();
        data.append("Código,Producto,Valor,Cantidad Producida,Estado,Fecha");
        companyRef.child(post_key).child("Production Chain").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String code = ds.child("code").getValue().toString();
                    String product_name = ds.child("product_name").getValue().toString();
                    String product_price = ds.child("product_price").getValue().toString();
                    String product_quantity_production = ds.child("product_quantity_production").getValue().toString();
                    String state = ds.child("state").getValue().toString();
                    String production_day = ds.child("production_day").getValue().toString();
                    String production_month = ds.child("production_month").getValue().toString();
                    String production_year = ds.child("production_year").getValue().toString();

                    String date = production_day+"/"+production_month+"/"+production_year;
                    String production_state = "";
                    if (state.equals("production")) {
                        production_state = "En Producción";
                    }
                    if (state.equals("stop")) {
                        production_state = "Detenido";
                    }
                    if (state.equals("ready")) {
                        production_state = "Listo";
                    }

                    data.append("\n"+code+","+product_name+","+product_price+","+product_quantity_production+","+production_state+","+date);

                    try{
                        //saving the file into device
                        FileOutputStream out = openFileOutput("ordenes_produccion.csv", Context.MODE_PRIVATE);
                        out.write((data.toString()).getBytes());
                        out.close();

                        //exporting
                        Context context = getApplicationContext();
                        File filelocation = new File(getFilesDir(), "ordenes_produccion.csv");
                        Uri path = FileProvider.getUriForFile(context, "com.oalonedeveloper.oliver.oliverappandroidapp.fileprovider", filelocation);
                        Intent fileIntent = new Intent(Intent.ACTION_SEND);
                        fileIntent.setType("text/csv");
                        fileIntent.putExtra(Intent.EXTRA_SUBJECT, "Órdenes de Producción");
                        fileIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                        fileIntent.putExtra(Intent.EXTRA_STREAM, path);
                        startActivity(Intent.createChooser(fileIntent, "Send mail"));
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void exportExcel6() {
        final StringBuilder data = new StringBuilder();
        data.append("ID de Almacén, Nombre de Almacén, Destino,Fecha de Registro");
        companyRef.child(post_key).child("Warehouses").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String warehouse_destination = ds.child("warehouse_destination").getValue().toString();
                    String warehouse_name = ds.child("warehouse_name").getValue().toString();
                    String warehouse_register_date = ds.child("warehouse_register_date").getValue().toString();
                    long warehouse_id = ds.child("timestamp").getValue(Long.class);

                    String warehouse_dest = "";

                    if (warehouse_destination.equals("products")) {
                        warehouse_dest = "Productos Terminados";
                    }
                    if (warehouse_destination.equals("materials")) {
                        warehouse_dest = "Materiales o Insumos";
                    }

                    data.append("\n"+warehouse_id+","+warehouse_name+","+warehouse_dest+","+warehouse_register_date);
                }
                try{
                    //saving the file into device
                    FileOutputStream out = openFileOutput("almacenes.csv", Context.MODE_PRIVATE);
                    out.write((data.toString()).getBytes());
                    out.close();

                    //exporting
                    Context context = getApplicationContext();
                    File filelocation = new File(getFilesDir(), "almacenes.csv");
                    Uri path = FileProvider.getUriForFile(context, "com.oalonedeveloper.oliver.oliverappandroidapp.fileprovider", filelocation);
                    Intent fileIntent = new Intent(Intent.ACTION_SEND);
                    fileIntent.setType("text/csv");
                    fileIntent.putExtra(Intent.EXTRA_SUBJECT, "Almacenes");
                    fileIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    fileIntent.putExtra(Intent.EXTRA_STREAM, path);
                    startActivity(Intent.createChooser(fileIntent, "Send mail"));
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void exportExcel5() {
        final StringBuilder data = new StringBuilder();
        data.append("Nombre,Documento de Identidad,Teléfono,Correo,Contacto");
        companyRef.child(post_key).child("My Suppliers").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String supplier_name = ds.child("supplier_name").getValue().toString();
                    String supplier_document_number = ds.child("supplier_document_number").getValue().toString();
                    String supplier_phone = ds.child("supplier_phone").getValue().toString();
                    String supplier_email = ds.child("supplier_email").getValue().toString();
                    String supplier_contact = ds.child("supplier_contact").getValue().toString();

                    data.append("\n"+supplier_name+","+supplier_document_number+","+supplier_phone+","+supplier_email+","+supplier_contact);
                }
                try{
                    //saving the file into device
                    FileOutputStream out = openFileOutput("proveedores.csv", Context.MODE_PRIVATE);
                    out.write((data.toString()).getBytes());
                    out.close();

                    //exporting
                    Context context = getApplicationContext();
                    File filelocation = new File(getFilesDir(), "proveedores.csv");
                    Uri path = FileProvider.getUriForFile(context, "com.oalonedeveloper.oliver.oliverappandroidapp.fileprovider", filelocation);
                    Intent fileIntent = new Intent(Intent.ACTION_SEND);
                    fileIntent.setType("text/csv");
                    fileIntent.putExtra(Intent.EXTRA_SUBJECT, "Proveedores");
                    fileIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    fileIntent.putExtra(Intent.EXTRA_STREAM, path);
                    startActivity(Intent.createChooser(fileIntent, "Send mail"));
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void exportExcel4() {
        final StringBuilder data = new StringBuilder();
        data.append("Código,Proveedor,Monto,Estado,Estado de Pago, Fecha");
        companyRef.child(post_key).child("Purchased Orders").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    final String purchase_order_id = ds.child("purchase_order_id").getValue().toString();
                    final String purchase_order_supplier_id = ds.child("purchase_order_supplier_id").getValue().toString();
                    final String purchase_order_total_amount = ds.child("purchase_order_total_amount").getValue().toString();
                    String purchase_order_state = ds.child("purchase_order_state").getValue().toString();
                    String purchase_payment_state = ds.child("purchase_payment_state").getValue().toString();
                    final String operation_date = ds.child("operation_date").getValue().toString();

                    if (purchase_order_state.equals("pending")) {
                        state = "Enviado";
                    }
                    if (purchase_order_state.equals("ready")) {
                        state = "Entregado";
                    }
                    if (purchase_order_state.equals("ready_delayed")) {
                        state = "Entregado con Retraso";
                    }
                    if (purchase_order_state.equals("returned")) {
                        state = "Devuelto";
                    }
                    if (purchase_order_state.equals("supplier_rejected")) {
                        state = "Rechazado por Proveedor";
                    }
                    if (purchase_order_state.equals("canceled")) {
                        state = "Cancelado";
                    }

                    if (purchase_payment_state.equals("expired")) {
                        payment = "Vencida";
                    }
                    if (purchase_payment_state.equals("paid")) {
                        payment = "Pagado";
                    }
                    if (purchase_payment_state.equals("no_paid")) {
                        payment = "Por Pagar";
                    }

                    companyRef.child(post_key).child("My Suppliers").child(purchase_order_supplier_id).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            String supplier_name = dataSnapshot.child("supplier_name").getValue().toString();

                            data.append("\n"+purchase_order_id+","+supplier_name+","+purchase_order_total_amount+","+state+","+payment+","+operation_date);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }

                try{
                    //saving the file into device
                    FileOutputStream out = openFileOutput("compras.csv", Context.MODE_PRIVATE);
                    out.write((data.toString()).getBytes());
                    out.close();

                    //exporting
                    Context context = getApplicationContext();
                    File filelocation = new File(getFilesDir(), "compras.csv");
                    Uri path = FileProvider.getUriForFile(context, "com.oalonedeveloper.oliver.oliverappandroidapp.fileprovider", filelocation);
                    Intent fileIntent = new Intent(Intent.ACTION_SEND);
                    fileIntent.setType("text/csv");
                    fileIntent.putExtra(Intent.EXTRA_SUBJECT, "Compras");
                    fileIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    fileIntent.putExtra(Intent.EXTRA_STREAM, path);
                    startActivity(Intent.createChooser(fileIntent, "Send mail"));
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void exportExcel3() {
        final StringBuilder data = new StringBuilder();
        data.append("Nombre,Teléfono,Documento,Fecha de Registro");
        companyRef.child(post_key).child("Sellers").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String seller_name = ds.child("seller_name").getValue().toString();
                    String seller_phone = ds.child("seller_phone").getValue().toString();
                    String seller_document_number = ds.child("seller_document_number").getValue().toString();
                    String register_date = ds.child("register_date").getValue().toString();

                    data.append("\n"+seller_name+","+seller_phone+","+seller_document_number+","+register_date);

                }
                try{
                    //saving the file into device
                    FileOutputStream out = openFileOutput("vendedores.csv", Context.MODE_PRIVATE);
                    out.write((data.toString()).getBytes());
                    out.close();

                    //exporting
                    Context context = getApplicationContext();
                    File filelocation = new File(getFilesDir(), "vendedores.csv");
                    Uri path = FileProvider.getUriForFile(context, "com.oalonedeveloper.oliver.oliverappandroidapp.fileprovider", filelocation);
                    Intent fileIntent = new Intent(Intent.ACTION_SEND);
                    fileIntent.setType("text/csv");
                    fileIntent.putExtra(Intent.EXTRA_SUBJECT, "Vendedores");
                    fileIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    fileIntent.putExtra(Intent.EXTRA_STREAM, path);
                    startActivity(Intent.createChooser(fileIntent, "Send mail"));
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void exportExcel2() {
        final StringBuilder data = new StringBuilder();
        data.append("Código,Cliente,Monto,IGV,Emisión,Vencimiento,Estado");
        companyRef.child(post_key).child("My Products").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                companyRef.child(post_key).child("My Bills").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            String bill_id = ds.child("bill_id").getValue().toString();
                            String customer_name = ds.child("customer_name").getValue().toString();
                            String bill_amount = ds.child("bill_amount").getValue().toString();
                            String bill_igv_taxes = ds.child("bill_igv_taxes").getValue().toString();
                            String issuing_day = ds.child("issuing_day").getValue().toString();
                            String issuing_month = ds.child("issuing_month").getValue().toString();
                            String issuing_year = ds.child("issuing_year").getValue().toString();
                            String expiration_day = ds.child("expiration_day").getValue().toString();
                            String expiration_month = ds.child("expiration_month").getValue().toString();
                            String expiration_year = ds.child("expiration_year").getValue().toString();
                            String state = ds.child("state").getValue().toString();
                            String bill_state = "";
                            String issuing_date = issuing_day+"/"+issuing_month+"/"+issuing_year;
                            String expiration_date = expiration_day+"/"+expiration_month+"/"+expiration_year;
                            if (state.equals("expired")) {
                                bill_state = "Vencida";
                            }
                            if (state.equals("paid")) {
                                bill_state = "Pagado";
                            }
                            if (state.equals("no_paid")) {
                                bill_state = "Por Pagar";
                            }
                            data.append("\n"+bill_id+","+customer_name+","+bill_amount+","+bill_igv_taxes+","+issuing_date+","+expiration_date+","+bill_state);


                        }

                        try{
                            //saving the file into device
                            FileOutputStream out = openFileOutput("boletas_facturas.csv", Context.MODE_PRIVATE);
                            out.write((data.toString()).getBytes());
                            out.close();

                            //exporting
                            Context context = getApplicationContext();
                            File filelocation = new File(getFilesDir(), "boletas_facturas.csv");
                            Uri path = FileProvider.getUriForFile(context, "com.oalonedeveloper.oliver.oliverappandroidapp.fileprovider", filelocation);
                            Intent fileIntent = new Intent(Intent.ACTION_SEND);
                            fileIntent.setType("text/csv");
                            fileIntent.putExtra(Intent.EXTRA_SUBJECT, "Boletas y Facturas");
                            fileIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                            fileIntent.putExtra(Intent.EXTRA_STREAM, path);
                            startActivity(Intent.createChooser(fileIntent, "Send mail"));
                        }
                        catch(Exception e){
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void exportExcel1() {
        final StringBuilder data = new StringBuilder();
        data.append("Código,Nombre,Precio,Stock actual");

        companyRef.child(post_key).child("My Products").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Map<String, Object> map = (Map<String, Object>) ds.getValue();
                    Object code = map.get("code");
                    Object product_name = map.get("product_name");
                    Object product_price = map.get("product_price");
                    Object product_stock = map.get("product_stock");
                    data.append("\n"+code+","+String.valueOf(product_name)+","+String.valueOf(product_price)+","+String.valueOf(product_stock));

                }
                try{
                    //saving the file into device
                    FileOutputStream out = openFileOutput("data.csv", Context.MODE_PRIVATE);
                    out.write((data.toString()).getBytes());
                    out.close();

                    //exporting
                    Context context = getApplicationContext();
                    File filelocation = new File(getFilesDir(), "data.csv");
                    Uri path = FileProvider.getUriForFile(context, "com.oalonedeveloper.oliver.oliverappandroidapp.fileprovider", filelocation);
                    Intent fileIntent = new Intent(Intent.ACTION_SEND);
                    fileIntent.setType("text/csv");
                    fileIntent.putExtra(Intent.EXTRA_SUBJECT, "Data");
                    fileIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    fileIntent.putExtra(Intent.EXTRA_STREAM, path);
                    startActivity(Intent.createChooser(fileIntent, "Send mail"));
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}
