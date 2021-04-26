package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.SalesModule.SalesFunnel;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
import com.oalonedeveloper.oliver.oliverappandroidapp.Companies.CompanyCustomersModel;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

import es.dmoral.toasty.Toasty;


public class CustomerAchievementFragment extends Fragment {

    DatabaseReference myCompanyRef;
    String post_key,customer_def;
    long children_count, contact_count;
    TextView txContactPercent,txContactAmount,txtInterestedPercent,txtInterestedAmount,txtProspectPercent,txtProspectAmount,txtPotencialPercent,txtPotencialAmount,txtFinalPercent,txtFinalAmount,
            txtCustomerPercent,txtCustomerAmount;
    DecimalFormat decimalFormat;
    ImageView btnShowCustomers1,btnShowCustomers2,btnShowCustomers3,btnShowCustomers4,btnShowCustomers5,btnShowCustomers6;
    AlertDialog dialog_customers;
    RecyclerView recyclerView2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_customer_achievement, container, false);

        post_key = getActivity().getIntent().getExtras().getString("post_key");
        myCompanyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");

        txContactPercent = view.findViewById(R.id.txContactPercent);
        txContactAmount = view.findViewById(R.id.txContactAmount);
        txtInterestedPercent = view.findViewById(R.id.txtInterestedPercent);
        txtInterestedAmount = view.findViewById(R.id.txtInterestedAmount);
        txtProspectPercent = view.findViewById(R.id.txtProspectPercent);
        txtProspectAmount = view.findViewById(R.id.txtProspectAmount);
        txtPotencialPercent = view.findViewById(R.id.txtPotencialPercent);
        txtPotencialAmount = view.findViewById(R.id.txtPotencialAmount);
        txtFinalPercent = view.findViewById(R.id.txtFinalPercent);
        txtFinalAmount = view.findViewById(R.id.txtFinalAmount);
        txtCustomerPercent = view.findViewById(R.id.txtCustomerPercent);
        txtCustomerAmount = view.findViewById(R.id.txtCustomerAmount);
        btnShowCustomers1 = view.findViewById(R.id.btnShowCustomers1);
        btnShowCustomers2 = view.findViewById(R.id.btnShowCustomers2);
        btnShowCustomers3 = view.findViewById(R.id.btnShowCustomers3);
        btnShowCustomers4 = view.findViewById(R.id.btnShowCustomers4);
        btnShowCustomers5 = view.findViewById(R.id.btnShowCustomers5);
        btnShowCustomers6 = view.findViewById(R.id.btnShowCustomers6);

        decimalFormat = new DecimalFormat("0.00");

        myCompanyRef.child(post_key).child("Customers").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                children_count = dataSnapshot.getChildrenCount();

                myCompanyRef.child(post_key).child("Customers").orderByChild("customer_define").equalTo("contact").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        contact_count = dataSnapshot.getChildrenCount();

                        //Calculate percent
                        float percent = ((float)contact_count/children_count)*100;
                        String my_percent = decimalFormat.format(percent);

                        txContactPercent.setText(my_percent+"%");
                        txContactAmount.setText(contact_count+"");
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                myCompanyRef.child(post_key).child("Customers").orderByChild("customer_define").equalTo("interested").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        contact_count = dataSnapshot.getChildrenCount();

                        //Calculate percent
                        float percent = ((float)contact_count/children_count)*100;
                        String my_percent = decimalFormat.format(percent);

                        txtInterestedPercent.setText(my_percent+"%");
                        txtInterestedAmount.setText(contact_count+"");
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                myCompanyRef.child(post_key).child("Customers").orderByChild("customer_define").equalTo("prospect").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        contact_count = dataSnapshot.getChildrenCount();

                        //Calculate percent
                        float percent = ((float)contact_count/children_count)*100;
                        String my_percent = decimalFormat.format(percent);

                        txtProspectPercent.setText(my_percent+"%");
                        txtProspectAmount.setText(contact_count+"");
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                myCompanyRef.child(post_key).child("Customers").orderByChild("customer_define").equalTo("potencial").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        contact_count = dataSnapshot.getChildrenCount();

                        //Calculate percent
                        float percent = ((float)contact_count/children_count)*100;
                        String my_percent = decimalFormat.format(percent);

                        txtPotencialPercent.setText(my_percent+"%");
                        txtPotencialAmount.setText(contact_count+"");
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                myCompanyRef.child(post_key).child("Customers").orderByChild("customer_define").equalTo("final").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        contact_count = dataSnapshot.getChildrenCount();

                        //Calculate percent
                        float percent = ((float)contact_count/children_count)*100;
                        String my_percent = decimalFormat.format(percent);

                        txtFinalPercent.setText(my_percent+"%");
                        txtFinalAmount.setText(contact_count+"");
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                myCompanyRef.child(post_key).child("Customers").orderByChild("customer_define").equalTo("customer").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        contact_count = dataSnapshot.getChildrenCount();

                        //Calculate percent
                        float percent = ((float)contact_count/children_count)*100;
                        String my_percent = decimalFormat.format(percent);

                        txtCustomerPercent.setText(my_percent+"%");
                        txtCustomerAmount.setText(contact_count+"");
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

        btnShowCustomers1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customer_def = "contact";
                showCustomersDialog();
            }
        });

        btnShowCustomers2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customer_def = "interested";
                showCustomersDialog();
            }
        });

        btnShowCustomers3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customer_def = "prospect";
                showCustomersDialog();
            }
        });

        btnShowCustomers4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customer_def = "potencial";
                showCustomersDialog();
            }
        });

        btnShowCustomers5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customer_def = "final";
                showCustomersDialog();
            }
        });

        btnShowCustomers6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customer_def = "customer";
                showCustomersDialog();
            }
        });

        return view;
    }

    private void showCustomersDialog() {
        dialog_customers = new AlertDialog.Builder(getActivity()).create();

        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View finance_method = inflater.inflate(R.layout.customer_bill_dialog,null);

        Button btnAddCustomer;

        recyclerView2 = finance_method.findViewById(R.id.recyclerView2);
        btnAddCustomer = finance_method.findViewById(R.id.btnAddCustomer);
        recyclerView2.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setReverseLayout(false);
        linearLayoutManager.setStackFromEnd(false);
        recyclerView2.setLayoutManager(linearLayoutManager);

        showCompanyCustomers();

        btnAddCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddCustomerDialog();
            }

            private void showAddCustomerDialog() {
                final AlertDialog dialog = new AlertDialog.Builder(getActivity()).create();

                LayoutInflater inflater = LayoutInflater.from(getActivity());
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
                        hashMap.put("customer_define","contact");
                        hashMap.put("register_date", saveCurrentDate);
                        hashMap.put("register_time", saveCurrentTime);
                        myCompanyRef.child(post_key).child("Customers").child(postRandomName).updateChildren(hashMap).addOnCompleteListener(new OnCompleteListener() {
                            @Override
                            public void onComplete(@NonNull Task task) {
                                myCompanyRef.child(post_key).child("Achievements").child("Module2 Add Customer").child("score").setValue("50");
                                myCompanyRef.child(post_key).child("Achievements").child("Module2 Add Customer").child("message").setValue("Has registrado satisfactoriamente a tu primer cliente");
                                myCompanyRef.child(post_key).child("Achievements").child("Module2 Add Customer").child("timestamp").setValue(ServerValue.TIMESTAMP);
                                Toasty.success(getActivity(), "Registrado", Toast.LENGTH_LONG).show();
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
        });

        dialog_customers.setView(finance_method);
        dialog_customers.show();

    }

    private void showCompanyCustomers() {
        Query query = myCompanyRef.child(post_key).child("Customers").orderByChild("customer_define").equalTo(customer_def);
        FirebaseRecyclerAdapter<CompanyCustomersModel, CompanyCustomersViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<CompanyCustomersModel, CompanyCustomersViewHolder>
                (CompanyCustomersModel.class, R.layout.customer_bill_item, CompanyCustomersViewHolder.class, query) {
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
        recyclerView2.setAdapter(firebaseRecyclerAdapter);
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
}
