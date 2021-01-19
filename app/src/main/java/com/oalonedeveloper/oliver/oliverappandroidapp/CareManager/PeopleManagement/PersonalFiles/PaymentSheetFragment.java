package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.PeopleManagement.PersonalFiles;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;
import in.galaxyofandroid.spinerdialog.OnSpinerItemClick;
import in.galaxyofandroid.spinerdialog.SpinnerDialog;


public class PaymentSheetFragment extends Fragment {

    TextView txtCompanyName,txtFinancialInstitutionName,txtAccountType,txtCurrency,txtAccountNumber,txtInterbankAccount;
    DatabaseReference companyRef;
    String post_key,profile_id,company_social_reason;
    ImageView btnAdd1;
    ArrayList<String> arr_account_type =new ArrayList<>();
    SpinnerDialog accountTypeDialog;
    ArrayList<String> arr_currency =new ArrayList<>();
    SpinnerDialog currencyDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_payment_sheet, container, false);

        txtCompanyName = view.findViewById(R.id.txtCompanyName);
        post_key = getActivity().getIntent().getExtras().getString("post_key");
        profile_id = getActivity().getIntent().getExtras().getString("profile_id");
        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");

        txtFinancialInstitutionName = view.findViewById(R.id.txtFinancialInstitutionName);
        txtAccountType = view.findViewById(R.id.txtAccountType);
        txtCurrency = view.findViewById(R.id.txtCurrency);
        txtAccountNumber = view.findViewById(R.id.txtAccountNumber);
        txtInterbankAccount = view.findViewById(R.id.txtInterbankAccount);
        btnAdd1 = view.findViewById(R.id.btnAdd1);

        companyRef.child(post_key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                company_social_reason = dataSnapshot.child("company_social_reason").getValue().toString();

                txtCompanyName.setText("Empresa: "+company_social_reason);

                companyRef.child(post_key).child("Job Profile").child(profile_id).child("Personal File").child("Payment Data").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            if (dataSnapshot.hasChild("financial_institution_name")) {
                                String financial_institution_name = dataSnapshot.child("financial_institution_name").getValue().toString();
                                txtFinancialInstitutionName.setText("Entidad Bancaria: "+financial_institution_name);
                            }
                            if (dataSnapshot.hasChild("account_number")) {
                                String account_number = dataSnapshot.child("account_number").getValue().toString();
                                txtAccountNumber.setText("Número de Cuenta:"+account_number);
                            }
                            if (dataSnapshot.hasChild("account_type")) {
                                String account_type = dataSnapshot.child("account_type").getValue().toString();
                                txtAccountType.setText("Tipo de Cuenta: "+account_type);
                            }
                            if (dataSnapshot.hasChild("currency")) {
                                String currency = dataSnapshot.child("currency").getValue().toString();
                                txtCurrency.setText("Moneda: "+currency);
                            }
                            if (dataSnapshot.hasChild("interbank_account_number")) {
                                String interbank_account_number = dataSnapshot.child("interbank_account_number").getValue().toString();
                                txtInterbankAccount.setText("Número de Cuenta Interbancario: "+interbank_account_number);
                            }
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

        btnAdd1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog dialog = new AlertDialog.Builder(getActivity()).create();

                LayoutInflater inflater = LayoutInflater.from(getActivity());
                View finance_method = inflater.inflate(R.layout.add_job_file_financial_data_dialog,null);

                final EditText edtFinancialInstitutionName,edtAccountNumber,edtInterbankAccount;
                final Button btnAccountType,btnCurrency,btnRegister;
                final LinearLayout rootLayout;

                edtFinancialInstitutionName = finance_method.findViewById(R.id.edtFinancialInstitutionName);
                edtAccountNumber = finance_method.findViewById(R.id.edtAccountNumber);
                btnAccountType = finance_method.findViewById(R.id.btnAccountType);
                btnCurrency = finance_method.findViewById(R.id.btnCurrency);
                edtInterbankAccount = finance_method.findViewById(R.id.edtInterbankAccount);
                rootLayout = finance_method.findViewById(R.id.rootLayout);
                btnRegister = finance_method.findViewById(R.id.btnRegister);

                arr_account_type.add("Cuenta de Ahorros");arr_account_type.add("Cuenta Corriente");arr_account_type.add("Cuenta Sueldo");arr_account_type.add("Otro");

                btnAccountType.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        accountTypeDialog.showSpinerDialog();
                    }
                });

                accountTypeDialog = new SpinnerDialog(getActivity(),arr_account_type, "Selecciona el tipo de Cuenta");
                accountTypeDialog.bindOnSpinerListener(new OnSpinerItemClick() {
                    @Override
                    public void onClick(String item2, int position2) {
                        btnAccountType.setText(item2);
                    }
                });

                arr_currency.add("Soles");

                btnCurrency.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        currencyDialog.showSpinerDialog();
                    }
                });

                currencyDialog = new SpinnerDialog(getActivity(),arr_currency, "Selecciona el tipo de Cuenta");
                currencyDialog.bindOnSpinerListener(new OnSpinerItemClick() {
                    @Override
                    public void onClick(String item2, int position2) {
                        btnCurrency.setText(item2);
                    }
                });

                btnRegister.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (TextUtils.isEmpty(edtFinancialInstitutionName.getText().toString())) {
                            Snackbar.make(rootLayout,"Debes completar toda la información",Snackbar.LENGTH_SHORT).show();
                        }
                        else if (TextUtils.isEmpty(edtAccountNumber.getText().toString())) {
                            Snackbar.make(rootLayout,"Debes completar toda la información",Snackbar.LENGTH_SHORT).show();
                        }
                        else if (TextUtils.isEmpty(btnAccountType.getText().toString())) {
                            Snackbar.make(rootLayout,"Debes completar toda la información",Snackbar.LENGTH_SHORT).show();
                        }
                        else if (TextUtils.isEmpty(btnCurrency.getText().toString())) {
                            Snackbar.make(rootLayout,"Debes completar toda la información",Snackbar.LENGTH_SHORT).show();
                        } else if (TextUtils.isEmpty(edtInterbankAccount.getText().toString())) {
                            Snackbar.make(rootLayout, "Debes completar toda la información", Snackbar.LENGTH_SHORT).show();
                        } else {
                            companyRef.child(post_key).child("Job Profile").child(profile_id).child("Personal File").child("Payment Data").child("financial_institution_name").setValue(edtFinancialInstitutionName.getText().toString());
                            companyRef.child(post_key).child("Job Profile").child(profile_id).child("Personal File").child("Payment Data").child("account_number").setValue(edtAccountNumber.getText().toString());
                            companyRef.child(post_key).child("Job Profile").child(profile_id).child("Personal File").child("Payment Data").child("account_type").setValue(btnAccountType.getText().toString());
                            companyRef.child(post_key).child("Job Profile").child(profile_id).child("Personal File").child("Payment Data").child("currency").setValue(btnCurrency.getText().toString());
                            companyRef.child(post_key).child("Job Profile").child(profile_id).child("Personal File").child("Payment Data").child("interbank_account_number").setValue(edtInterbankAccount.getText().toString());
                            Toasty.success(getActivity(), "Registrado", Toast.LENGTH_LONG).show();
                            dialog.dismiss();
                        }
                    }
                });

                dialog.setView(finance_method);
                dialog.show();
            }
        });

        return view;
    }
}
