package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.SalesModule.MySellers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ServerValue;
import com.hbb20.CountryCodePicker;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.SalesModule.CustomerSchedule.CustomerScheduleActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.SalesModule.CustomerSchedule.CustomerScheduleModel;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

import es.dmoral.toasty.Toasty;

public class MySellersActivity extends AppCompatActivity {

    Button btnRegisterSeller;
    DatabaseReference companyRef;
    String post_key;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_sellers);

        btnRegisterSeller = findViewById(R.id.btnRegisterSeller);

        post_key = getIntent().getExtras().getString("post_key");
        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        btnRegisterSeller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRegisterSellerDialog();
            }
        });

        showMySellers();
    }

    private void showMySellers() {
        Query query = companyRef.child(post_key).child("Sellers").orderByChild("timestamp");
        FirebaseRecyclerAdapter<MySellersModel,MySellersViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<MySellersModel, MySellersViewHolder>
                (MySellersModel.class, R.layout.seller_item, MySellersViewHolder.class,query) {
            @Override
            protected void populateViewHolder(MySellersViewHolder viewHolder, MySellersModel model, int position) {
                final String postKey = getRef(position).getKey();
                viewHolder.setSeller_document_number(model.getSeller_document_number());
                viewHolder.setSeller_email(model.getSeller_email());
                viewHolder.setSeller_name(model.getSeller_name());
                viewHolder.setSeller_phone(model.getSeller_phone());


                viewHolder.txtName.setText(viewHolder.name);
                viewHolder.txtPhone.setText(viewHolder.phone);
                viewHolder.txtEmail.setText(viewHolder.email);
                viewHolder.txtDocumentNumber.setText(viewHolder.document_number);

            }
        };
        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    private void showRegisterSellerDialog() {
        final AlertDialog dialog = new AlertDialog.Builder(MySellersActivity.this).create();

        LayoutInflater inflater = LayoutInflater.from(MySellersActivity.this);
        View finance_method = inflater.inflate(R.layout.register_seller_dialog,null);

        final EditText edtName,edtDocumentNumber,edtEmail,edtPhoneNumber;
        final CountryCodePicker ccpPhoneCode;
        Button btnAddSeller;

        edtName = finance_method.findViewById(R.id.edtName);
        edtDocumentNumber = finance_method.findViewById(R.id.edtDocumentNumber);
        edtEmail = finance_method.findViewById(R.id.edtEmail);
        edtPhoneNumber = finance_method.findViewById(R.id.edtPhoneNumber);
        ccpPhoneCode = finance_method.findViewById(R.id.ccpPhoneCode);
        btnAddSeller = finance_method.findViewById(R.id.btnAddSeller);

        btnAddSeller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar calForDate = Calendar.getInstance();
                SimpleDateFormat currentDate = new SimpleDateFormat("dd-MM-yyyy");
                String saveCurrentDate = currentDate.format(calForDate.getTime());

                Calendar calForTime = Calendar.getInstance();
                SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss");
                String saveCurrentTime = currentTime.format(calForTime.getTime());

                HashMap hashMap = new HashMap();
                hashMap.put("seller_name", edtName.getText().toString());
                hashMap.put("seller_email", edtEmail.getText().toString());
                hashMap.put("seller_document_number", edtDocumentNumber.getText().toString());
                hashMap.put("seller_phone", ccpPhoneCode.getSelectedCountryCode()+edtPhoneNumber.getText().toString());
                hashMap.put("register_date", saveCurrentDate);
                hashMap.put("register_time", saveCurrentTime);
                hashMap.put("timestamp", ServerValue.TIMESTAMP);

                companyRef.child(post_key).child("Sellers").child(edtDocumentNumber.getText().toString()).updateChildren(hashMap).addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        Toasty.success(MySellersActivity.this, "Registrado", Toast.LENGTH_LONG).show();
                        dialog.dismiss();
                    }
                });

            }
        });

        dialog.setView(finance_method);
        dialog.show();
    }

    public static class MySellersViewHolder extends RecyclerView.ViewHolder {

        View mView;
        TextView txtName,txtPhone,txtEmail,txtDocumentNumber;
        String email,name,phone,document_number;

        public MySellersViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;

            txtName = mView.findViewById(R.id.txtName);
            txtPhone = mView.findViewById(R.id.txtPhone);
            txtEmail = mView.findViewById(R.id.txtEmail);
            txtDocumentNumber = mView.findViewById(R.id.txtDocumentNumber);
        }

        public void setSeller_name(String seller_name) {
            name = seller_name;
        }

        public void setSeller_email(String seller_email) {
            email = seller_email;
        }


        public void setSeller_document_number(String seller_document_number) {
            document_number = seller_document_number;
        }


        public void setSeller_phone(String seller_phone) {
            phone = seller_phone;
        }
    }
}
