package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Commercialization.BillsIssuing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Commercialization.BillList.BillListActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Commercialization.Dashboards.DashboardsActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class BillsIssuingActivity extends AppCompatActivity {

    Button btnCreateBill,btnCreateInvoice,btnDashboards,btnBillList;
    String post_key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bills_issuing);

        btnCreateBill = findViewById(R.id.btnCreateBill);
        btnCreateInvoice = findViewById(R.id.btnCreateInvoice);
        btnDashboards = findViewById(R.id.btnDashboards);
        btnBillList = findViewById(R.id.btnBillList);
        post_key = getIntent().getExtras().getString("post_key");


        btnCreateBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showOptionDialog();
            }
        });
        btnCreateInvoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showOptionDialog2();
            }
        });
        btnDashboards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BillsIssuingActivity.this, DashboardsActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);

            }
        });
        btnBillList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BillsIssuingActivity.this, BillListActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);

            }
        });


    }

    private void showOptionDialog2() {
        final AlertDialog dialog = new AlertDialog.Builder(BillsIssuingActivity.this).create();

        LayoutInflater inflater = LayoutInflater.from(BillsIssuingActivity.this);
        View finance_method = inflater.inflate(R.layout.sale_bill_type_dialog,null);

        ImageButton btnCashNow,btnCredit;
        btnCashNow = finance_method.findViewById(R.id.btnCashNow);
        btnCredit = finance_method.findViewById(R.id.btnCredit);

        btnCashNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BillsIssuingActivity.this, CreateInvoiceActivity.class);
                intent.putExtra("post_key", post_key);
                intent.putExtra("bill_sale_type", "cash_now");
                dialog.dismiss();
                startActivity(intent);

            }
        });
        btnCredit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BillsIssuingActivity.this, CreateInvoiceActivity.class);
                intent.putExtra("post_key", post_key);
                intent.putExtra("bill_sale_type", "credit");
                dialog.dismiss();
                startActivity(intent);
            }
        });

        dialog.setView(finance_method);
        dialog.show();
    }

    private void showOptionDialog() {
        final AlertDialog dialog = new AlertDialog.Builder(BillsIssuingActivity.this).create();

        LayoutInflater inflater = LayoutInflater.from(BillsIssuingActivity.this);
        View finance_method = inflater.inflate(R.layout.sale_bill_type_dialog,null);

        ImageButton btnCashNow,btnCredit;
        btnCashNow = finance_method.findViewById(R.id.btnCashNow);
        btnCredit = finance_method.findViewById(R.id.btnCredit);

        btnCashNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BillsIssuingActivity.this, CreateBillActivity.class);
                intent.putExtra("post_key", post_key);
                intent.putExtra("bill_sale_type", "cash_now");
                dialog.dismiss();
                startActivity(intent);
                finish();
            }
        });
        btnCredit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BillsIssuingActivity.this, CreateBillActivity.class);
                intent.putExtra("post_key", post_key);
                intent.putExtra("bill_sale_type", "credit");
                dialog.dismiss();
                startActivity(intent);
                finish();
            }
        });

        dialog.setView(finance_method);
        dialog.show();
    }


}
