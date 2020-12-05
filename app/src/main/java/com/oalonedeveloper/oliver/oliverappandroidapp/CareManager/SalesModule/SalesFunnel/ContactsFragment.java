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
import android.widget.RadioButton;
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
import com.oalonedeveloper.oliver.oliverappandroidapp.Companies.CompanyCustomersModel;
import com.oalonedeveloper.oliver.oliverappandroidapp.Companies.MyCustomersActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

import es.dmoral.toasty.Toasty;


public class ContactsFragment extends Fragment {

    Button btnAddCustomer;
    String post_key, customer_define;
    DatabaseReference myCompanyRef;
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contacts, container, false);

        post_key = getActivity().getIntent().getExtras().getString("post_key");

        btnAddCustomer = view.findViewById(R.id.btnAddCustomer);

        myCompanyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
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

        return view;
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

                viewHolder.btnDefine.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showDefineCustomerDialog();
                    }

                    private void showDefineCustomerDialog() {
                        final AlertDialog dialog = new AlertDialog.Builder(getActivity()).create();

                        LayoutInflater inflater = LayoutInflater.from(getActivity());
                        final View finance_method = inflater.inflate(R.layout.define_customer_dialog,null);

                        final RadioButton rd1yes,rd1no,rd2yes,rd2no,rd3yes,rd3no,rd4yes,rd4no,rd5yes,rd5no;
                        final TextView txtMessage2,txtMessage3,txtMessage4,txtMessage5,txtContactData;
                        Button btnFinish;

                        rd1yes = finance_method.findViewById(R.id.rd1yes);
                        rd1no = finance_method.findViewById(R.id.rd1no);
                        rd2yes = finance_method.findViewById(R.id.rd2yes);
                        rd2no = finance_method.findViewById(R.id.rd2no);
                        rd3yes = finance_method.findViewById(R.id.rd3yes);
                        rd3no = finance_method.findViewById(R.id.rd3no);
                        rd4yes = finance_method.findViewById(R.id.rd4yes);
                        rd4no = finance_method.findViewById(R.id.rd4no);
                        rd5yes = finance_method.findViewById(R.id.rd5yes);
                        rd5no = finance_method.findViewById(R.id.rd5no);

                        txtMessage2 = finance_method.findViewById(R.id.txtMessage2);
                        txtMessage3 = finance_method.findViewById(R.id.txtMessage3);
                        txtMessage4 = finance_method.findViewById(R.id.txtMessage4);
                        txtMessage5 = finance_method.findViewById(R.id.txtMessage5);
                        txtContactData = finance_method.findViewById(R.id.txtContactData);

                        btnFinish = finance_method.findViewById(R.id.btnFinish);

                        txtContactData.setText("Define a tu contacto "+viewHolder.name+":");

                        rd2yes.setVisibility(View.GONE);
                        rd2no.setVisibility(View.GONE);
                        rd3yes.setVisibility(View.GONE);
                        rd3no.setVisibility(View.GONE);
                        rd4yes.setVisibility(View.GONE);
                        rd4no.setVisibility(View.GONE);
                        rd5yes.setVisibility(View.GONE);
                        rd5no.setVisibility(View.GONE);

                        txtMessage2.setVisibility(View.GONE);
                        txtMessage3.setVisibility(View.GONE);
                        txtMessage4.setVisibility(View.GONE);
                        txtMessage5.setVisibility(View.GONE);

                        customer_define = "contact";

                        rd1yes.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                customer_define = "interested";
                                txtMessage2.setVisibility(View.VISIBLE);
                                rd2yes.setVisibility(View.VISIBLE);
                                rd2no.setVisibility(View.VISIBLE);
                            }
                        });
                        rd1no.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                customer_define = "contact";
                                rd2yes.setVisibility(View.GONE);
                                rd2no.setVisibility(View.GONE);
                                rd3yes.setVisibility(View.GONE);
                                rd3no.setVisibility(View.GONE);
                                rd4yes.setVisibility(View.GONE);
                                rd4no.setVisibility(View.GONE);
                                rd5yes.setVisibility(View.GONE);
                                rd5no.setVisibility(View.GONE);

                                txtMessage2.setVisibility(View.GONE);
                                txtMessage3.setVisibility(View.GONE);
                                txtMessage4.setVisibility(View.GONE);
                                txtMessage5.setVisibility(View.GONE);
                            }
                        });

                        rd2yes.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                customer_define = "prospect";
                                txtMessage3.setVisibility(View.VISIBLE);
                                rd3yes.setVisibility(View.VISIBLE);
                                rd3no.setVisibility(View.VISIBLE);
                            }
                        });

                        rd2no.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                customer_define = "interested";
                                rd3yes.setVisibility(View.GONE);
                                rd3no.setVisibility(View.GONE);
                                rd4yes.setVisibility(View.GONE);
                                rd4no.setVisibility(View.GONE);
                                rd5yes.setVisibility(View.GONE);
                                rd5no.setVisibility(View.GONE);

                                txtMessage3.setVisibility(View.GONE);
                                txtMessage4.setVisibility(View.GONE);
                                txtMessage5.setVisibility(View.GONE);
                            }
                        });

                        rd3yes.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                customer_define = "prospect";
                                rd4yes.setVisibility(View.GONE);
                                rd4no.setVisibility(View.GONE);
                                rd5yes.setVisibility(View.GONE);
                                rd5no.setVisibility(View.GONE);

                                txtMessage4.setVisibility(View.GONE);
                                txtMessage5.setVisibility(View.GONE);
                            }
                        });

                        rd3no.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                customer_define = "potencial";
                                txtMessage4.setVisibility(View.VISIBLE);
                                rd4yes.setVisibility(View.VISIBLE);
                                rd4no.setVisibility(View.VISIBLE);
                            }
                        });

                        rd4yes.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                customer_define = "final";
                                txtMessage5.setVisibility(View.VISIBLE);
                                rd5yes.setVisibility(View.VISIBLE);
                                rd5no.setVisibility(View.VISIBLE);
                            }
                        });

                        rd4no.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                customer_define = "potencial";
                                rd5yes.setVisibility(View.GONE);
                                rd5no.setVisibility(View.GONE);

                                txtMessage5.setVisibility(View.GONE);
                            }
                        });

                        rd5yes.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                customer_define = "customer";

                            }
                        });

                        rd5no.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                customer_define = "final";

                            }
                        });

                        btnFinish.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                myCompanyRef.child(post_key).child("Customers").child(postKey).child("customer_define").setValue(customer_define);
                                dialog.dismiss();
                                Toasty.success(getActivity(), "Listo", Toast.LENGTH_LONG).show();
                            }
                        });

                        dialog.setView(finance_method);
                        dialog.show();

                    }
                });


            }
        };
        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    public static class CompanyCustomersViewHolder extends RecyclerView.ViewHolder {
        View mView;
        TextView txtName,txtPhone,txtEmail,txtType;
        String email,name,phone,type;
        Button btnDefine;

        public CompanyCustomersViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;

            txtName = mView.findViewById(R.id.txtName);
            txtPhone = mView.findViewById(R.id.txtPhone);
            txtEmail = mView.findViewById(R.id.txtEmail);
            txtType = mView.findViewById(R.id.txtType);
            btnDefine = mView.findViewById(R.id.btnDefine);
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
                hashMap.put("customer_define", "contact");
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
}
