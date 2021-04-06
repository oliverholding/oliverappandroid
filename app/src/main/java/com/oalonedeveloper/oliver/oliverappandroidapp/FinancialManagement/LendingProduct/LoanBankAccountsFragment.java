package com.oalonedeveloper.oliver.oliverappandroidapp.FinancialManagement.LendingProduct;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;
import in.galaxyofandroid.spinerdialog.OnSpinerItemClick;
import in.galaxyofandroid.spinerdialog.SpinnerDialog;

public class LoanBankAccountsFragment extends Fragment {

    RecyclerView recyclerView;
    DatabaseReference userRef,financial_institutions_account,lendingRef,financialInstitutionsRef;
    FirebaseAuth mAuth;
    String currentUid,operation_id,post_key,request_id,product_key;
    LinearLayout accountSelectedLayout;
    TextView txtFinancialInstitutionName,txtCc,txtCci,txtCurrency;
    Button btnAddBankAccounts;
    ArrayList<String> names =new ArrayList<>();
    SpinnerDialog spinnerDialog;
    long institutions_count;
    CheckBox checkBox;
    Button btnContinue;
    RelativeLayout rootLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_loan_bank_accounts, container, false);


        userRef = FirebaseDatabase.getInstance().getReference().child("Users");
        financial_institutions_account = FirebaseDatabase.getInstance().getReference().child("Financial Institutions Accounts");
        lendingRef = FirebaseDatabase.getInstance().getReference().child("Lendings");
        financialInstitutionsRef = FirebaseDatabase.getInstance().getReference().child("Financial Institutions");
        mAuth = FirebaseAuth.getInstance();
        currentUid = mAuth.getCurrentUser().getUid();
        operation_id = getActivity().getIntent().getExtras().getString("operation_id");
        post_key = getActivity().getIntent().getExtras().getString("post_key");
        request_id = getActivity().getIntent().getExtras().getString("request_id");
        product_key = getActivity().getIntent().getExtras().getString("product_key");

        recyclerView = view.findViewById(R.id.recyclerView);
        accountSelectedLayout = view.findViewById(R.id.accountSelectedLayout);
        txtFinancialInstitutionName = view.findViewById(R.id.txtFinancialInstitutionName);
        txtCc = view.findViewById(R.id.txtCc);
        txtCci = view.findViewById(R.id.txtCci);
        txtCurrency = view.findViewById(R.id.txtCurrency);
        btnAddBankAccounts = view.findViewById(R.id.btnAddBankAccounts);
        checkBox = view.findViewById(R.id.checkBox);
        btnContinue = view.findViewById(R.id.btnContinue);

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setReverseLayout(false);
        linearLayoutManager.setStackFromEnd(false);
        recyclerView.setLayoutManager(linearLayoutManager);

        accountSelectedLayout.setVisibility(View.GONE);

        btnContinue.setVisibility(View.GONE);

        btnAddBankAccounts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddBankAccountDialog();
            }
        });

        financial_institutions_account.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                institutions_count = dataSnapshot.getChildrenCount();


                for (int i = 1; i <= institutions_count; i++) {

                    String name = dataSnapshot.child(i+"").child("financial_institution_name").getValue().toString();

                    names.add(name);
                }

                userRef.child(currentUid).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String document_number = dataSnapshot.child("document_number").getValue().toString();
                        String document_type = dataSnapshot.child("document_type").getValue().toString();
                        String fullname = dataSnapshot.child("fullname").getValue().toString();

                        checkBox.setText("Yo, "+fullname+" con "+document_type+": "+document_number+" confirmo que soy titular de la cuenta registrada.");

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

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox.isChecked()) {
                    btnContinue.setVisibility(View.VISIBLE);

                } else {
                    btnContinue.setVisibility(View.GONE);
                }
            }
        });

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox.isChecked()) {
                    lendingRef.child(operation_id).child("lending_state").setValue("approved");
                    financialInstitutionsRef.child(post_key).child("Loan Requests").child(request_id).child("request_state").setValue("ready");
                    Intent intent = new Intent(getActivity(), LoanInProcessToGetActivity.class);
                    intent.putExtra("institution_key",post_key);
                    intent.putExtra("product_key",product_key);
                    intent.putExtra("operation_id",operation_id);
                    startActivity(intent);
                    getActivity().finish();


                } else {
                    Snackbar.make(rootLayout,"Debes aceptar las condiciones",Snackbar.LENGTH_SHORT).show();
                }
            }
        });

        showBankAccounts();

        return view;
    }

    private void showAddBankAccountDialog() {
        final AlertDialog dialog = new AlertDialog.Builder(getActivity()).create();

        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View finance_method = inflater.inflate(R.layout.add_user_bank_account_dialog,null);

        final Button btnFinancialInstitutionName;
        final EditText edtCc,edtCci;
        final RadioButton rdPen,rdUsd;
        Button btnRegister;
        final LinearLayout rootLayout;

        btnFinancialInstitutionName = finance_method.findViewById(R.id.btnFinancialInstitutionName);
        edtCc = finance_method.findViewById(R.id.edtCc);
        edtCci = finance_method.findViewById(R.id.edtCci);
        rdPen = finance_method.findViewById(R.id.rdPen);
        rdUsd = finance_method.findViewById(R.id.rdUsd);
        btnRegister = finance_method.findViewById(R.id.btnRegister);
        rootLayout = finance_method.findViewById(R.id.rootLayout);

        btnFinancialInstitutionName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinnerDialog.showSpinerDialog();
            }
        });

        spinnerDialog = new SpinnerDialog(getActivity(),names,"Selecciona la institución Financiera");
        spinnerDialog.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String item, int i) {

                btnFinancialInstitutionName.setText(item);

            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(btnFinancialInstitutionName.getText().toString())) {
                    Snackbar.make(rootLayout,"Debes seleccionar una institucion financiera",Snackbar.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(edtCc.getText().toString())) {
                    Snackbar.make(rootLayout,"Debes ingresar tu número de cuenta bancario",Snackbar.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(edtCci.getText().toString())) {
                    Snackbar.make(rootLayout,"Debes ingresar tu número de cuenta interbancario",Snackbar.LENGTH_SHORT).show();

                } else if (!rdPen.isChecked() && !rdUsd.isChecked()) {
                    Snackbar.make(rootLayout,"Debes seleccionar la moneda",Snackbar.LENGTH_SHORT).show();
                } else {
                    Long tsLong = System.currentTimeMillis()/1000;
                    String timestamp = tsLong.toString();
                    userRef.child(currentUid).child("Bank Accounts").child(timestamp).child("account_cc").setValue(edtCc.getText().toString());
                    userRef.child(currentUid).child("Bank Accounts").child(timestamp).child("account_cci").setValue(edtCci.getText().toString());
                    if (rdPen.isChecked()) {
                        userRef.child(currentUid).child("Bank Accounts").child(timestamp).child("account_currency").setValue("PEN");
                    }
                    if (rdUsd.isChecked()) {
                        userRef.child(currentUid).child("Bank Accounts").child(timestamp).child("account_currency").setValue("USD");
                    }
                    userRef.child(currentUid).child("Bank Accounts").child(timestamp).child("financial_institution_name").setValue(btnFinancialInstitutionName.getText().toString());
                    Toasty.success(getActivity(), "Registrado", Toast.LENGTH_LONG).show();
                    dialog.dismiss();

                }
            }
        });

        dialog.setView(finance_method);
        dialog.show();
    }

    private void showBankAccounts() {
        Query query = userRef.child(currentUid).child("Bank Accounts").orderByChild("financial_institution_name");
        FirebaseRecyclerAdapter<LoanBankAccountModel, LoanBankAccountViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<LoanBankAccountModel, LoanBankAccountViewHolder>
                (LoanBankAccountModel.class,R.layout.loan_bank_account_item,LoanBankAccountViewHolder.class,query) {
            @Override
            protected void populateViewHolder(final LoanBankAccountViewHolder viewHolder, LoanBankAccountModel model, int position) {
                viewHolder.setAccount_cc(model.getAccount_cc());
                viewHolder.setAccount_cci(model.getAccount_cci());
                viewHolder.setAccount_currency(model.getAccount_currency());
                viewHolder.setFinancial_institution_name(model.getFinancial_institution_name());

                viewHolder.txtFinancialInstitutionName.setText(viewHolder.my_financial_institution_name);
                viewHolder.txtCc.setText("CC: "+viewHolder.my_account_cc);
                viewHolder.txtCci.setText("CCI: "+viewHolder.my_account_cci);
                viewHolder.txtCurrency.setText("Moneda: "+viewHolder.my_account_currency);

                viewHolder.btnSelectBankAccount.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        txtFinancialInstitutionName.setText(viewHolder.my_financial_institution_name);
                        txtCc.setText("CC: "+viewHolder.my_account_cc);
                        txtCci.setText("CCI: "+viewHolder.my_account_cci);
                        txtCurrency.setText("Moneda: "+viewHolder.my_account_currency);
                        recyclerView.setVisibility(View.GONE);
                        accountSelectedLayout.setVisibility(View.VISIBLE);
                    }
                });
            }
        };
        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    public static class LoanBankAccountViewHolder extends RecyclerView.ViewHolder {

        View mView;
        String my_account_cc,my_account_cci,my_account_currency,my_financial_institution_name;
        TextView txtFinancialInstitutionName,txtCc,txtCci,txtCurrency;
        Button btnSelectBankAccount;

        public LoanBankAccountViewHolder(@NonNull View itemView) {
            super(itemView);

            mView = itemView;

            txtFinancialInstitutionName = mView.findViewById(R.id.txtFinancialInstitutionName);
            txtCc = mView.findViewById(R.id.txtCc);
            txtCci = mView.findViewById(R.id.txtCci);
            txtCurrency = mView.findViewById(R.id.txtCurrency);
            btnSelectBankAccount = mView.findViewById(R.id.btnSelectBankAccount);


        }

        public void setAccount_cc(String account_cc) {
            my_account_cc = account_cc;
        }

        public void setAccount_cci(String account_cci) {
            my_account_cci = account_cci;
        }


        public void setAccount_currency(String account_currency) {
            my_account_currency = account_currency;
        }

        public void setFinancial_institution_name(String financial_institution_name) {
            my_financial_institution_name = financial_institution_name;
        }
    }
}
