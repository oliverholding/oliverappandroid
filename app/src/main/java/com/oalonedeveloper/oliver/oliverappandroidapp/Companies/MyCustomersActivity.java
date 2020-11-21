package com.oalonedeveloper.oliver.oliverappandroidapp.Companies;

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
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;
import com.hbb20.CountryCodePicker;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;
import es.dmoral.toasty.Toasty;

public class MyCustomersActivity extends AppCompatActivity {

    Button btnAddCustomer;
    String post_key;
    DatabaseReference myCompanyRef;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_customers);

        post_key = getIntent().getExtras().getString("post_key");

        btnAddCustomer = findViewById(R.id.btnAddCustomer);

        myCompanyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(false);
        linearLayoutManager.setStackFromEnd(false);
        recyclerView.setLayoutManager(linearLayoutManager);

        showCompanyCustomers();

        btnAddCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddCustomerDialog();
            }
        });


    }

    private void showCompanyCustomers() {
        Query query = myCompanyRef.child(post_key).child("Customers").orderByChild("name");
        FirebaseRecyclerAdapter<CompanyCustomersModel, CompanyCustomersViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<CompanyCustomersModel, CompanyCustomersViewHolder>
                (CompanyCustomersModel.class, R.layout.company_customer_item, CompanyCustomersViewHolder.class, query) {
            @Override
            protected void populateViewHolder(final CompanyCustomersViewHolder viewHolder, CompanyCustomersModel model, final int position) {
                final String postKey = getRef(position).getKey();
                viewHolder.setCustomer_email(model.getCustomer_email());
                viewHolder.setCustomer_name(model.getCustomer_name());
                viewHolder.setCustomer_phone(model.getCustomer_phone());
                viewHolder.setCustomer_type(model.getCustomer_type());

                viewHolder.txtName.setText(viewHolder.name);
                viewHolder.txtPhone.setText("Teléfono: "+viewHolder.phone);
                viewHolder.txtEmail.setText("Correo: "+viewHolder.email);
                viewHolder.txtType.setText("Tipo: "+viewHolder.type);


            }
        };
        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    public static class CompanyCustomersViewHolder extends RecyclerView.ViewHolder {
        View mView;
        TextView txtName,txtPhone,txtEmail,txtType;
        String email,name,phone,type;

        public CompanyCustomersViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;

            txtName = mView.findViewById(R.id.txtName);
            txtPhone = mView.findViewById(R.id.txtPhone);
            txtEmail = mView.findViewById(R.id.txtEmail);
            txtType = mView.findViewById(R.id.txtType);
        }
        public void setCustomer_email(String customer_email) {
            email = customer_email;
        }

        public void setCustomer_name(String customer_name) {
            name = customer_name;
        }


        public void setCustomer_phone(String customer_phone) {
            phone = customer_phone;
        }


        public void setCustomer_type(String customer_type) {
            type = customer_type;
        }
    }

    private void showAddCustomerDialog() {
        final AlertDialog dialog = new AlertDialog.Builder(this).create();

        LayoutInflater inflater = LayoutInflater.from(this);
        View finance_method = inflater.inflate(R.layout.add_customer_dialog,null);


        Button btnAddCustomer;
        TextView txtCancel;
        final EditText edtName,edtEmail,edtPhoneNumber;
        final CountryCodePicker ccpPhoneCode;
        final RadioButton rdPerson,rdCompany;

        btnAddCustomer = finance_method.findViewById(R.id.btnAddCustomer);
        txtCancel = finance_method.findViewById(R.id.txtCancel);
        edtName = finance_method.findViewById(R.id.edtName);
        edtEmail = finance_method.findViewById(R.id.edtEmail);
        edtPhoneNumber = finance_method.findViewById(R.id.edtPhoneNumber);
        ccpPhoneCode = finance_method.findViewById(R.id.ccpPhoneCode);
        rdPerson = finance_method.findViewById(R.id.rdPerson);
        rdCompany = finance_method.findViewById(R.id.rdCompany);

        btnAddCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calForDate = Calendar.getInstance();
                SimpleDateFormat currentDate = new SimpleDateFormat("dd-MM-yyyy");
                String saveCurrentDate = currentDate.format(calForDate.getTime());

                Calendar calForTime = Calendar.getInstance();
                SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss");
                String saveCurrentTime = currentTime.format(calForTime.getTime());

                String postRandomName = saveCurrentDate + saveCurrentTime;


                HashMap hashMap = new HashMap();
                hashMap.put("customer_name", edtName.getText().toString());
                hashMap.put("customer_email", edtEmail.getText().toString());
                hashMap.put("customer_phone", ccpPhoneCode.getSelectedCountryCode()+edtPhoneNumber.getText().toString());
                if (rdPerson.isChecked()) {
                    hashMap.put("customer_type", rdPerson.getText().toString());
                }
                else if (rdCompany.isChecked()) {
                    hashMap.put("customer_type", rdCompany.getText().toString());
                }
                hashMap.put("register_date", saveCurrentDate);
                hashMap.put("register_time", saveCurrentTime);
                myCompanyRef.child(post_key).child("Customers").child(postRandomName).updateChildren(hashMap).addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        myCompanyRef.child(post_key).child("Achievements").child("Module2 Add Customer").child("score").setValue("50");
                        myCompanyRef.child(post_key).child("Achievements").child("Module2 Add Customer").child("message").setValue("Has registrado satisfactoriamente a tu primer cliente");
                        myCompanyRef.child(post_key).child("Achievements").child("Module2 Add Customer").child("timestamp").setValue(ServerValue.TIMESTAMP);
                        Toasty.success(MyCustomersActivity.this, "Registrado", Toast.LENGTH_LONG).show();
                        dialog.dismiss();
                    }
                });

            }
        });

        txtCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.setView(finance_method);
        dialog.show();
    }
}
