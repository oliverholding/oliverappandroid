package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.FinancialManagement.AccountsToPaid;

import android.app.AlertDialog;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Logistic.PurchaseOrder.PurchaseOrdersModel;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import es.dmoral.toasty.Toasty;


public class LongTermToPaidFragment extends Fragment {

    String post_key,politic_days;
    RecyclerView recyclerView;
    DatabaseReference companyRef;
    AlertDialog warehouse_dialog;
    int day,month,year,politic_days_db;
    long diff,expiration_days_ago;
    DecimalFormat decimalFormat;
    ImageView btnPoliticsDialog;
    TextView txtPolitics,txtTotal;
    double sum;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_long_term_to_paid, container, false);

        post_key = getActivity().getIntent().getExtras().getString("post_key");
        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        txtTotal = view.findViewById(R.id.txtTotal);

        decimalFormat = new DecimalFormat("0.00");

        btnPoliticsDialog = view.findViewById(R.id.btnPoliticsDialog);
        txtPolitics = view.findViewById(R.id.txtPolitics);

        Date date= new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        day = cal.get(Calendar.DAY_OF_MONTH);
        month = cal.get(Calendar.MONTH)+1;
        year = cal.get(Calendar.YEAR);

        sum = 0.00;

        companyRef.child(post_key).child("Politics").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild("Long Terms To Paid")) {
                    politic_days = dataSnapshot.child("Long Terms To Paid").getValue().toString();
                    politic_days_db = Integer.parseInt(politic_days);
                    txtPolitics.setText("Políticas: Mayor a "+politic_days+" días para vencer");
                    showPurchaseOrders();
                } else {
                    politic_days_db = 180;
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        btnPoliticsDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog dialog = new AlertDialog.Builder(getActivity()).create();

                LayoutInflater inflater = LayoutInflater.from(getActivity());
                View finance_method = inflater.inflate(R.layout.long_term_to_paid_dialog,null);

                final EditText edtDays = finance_method.findViewById(R.id.edtDays);
                Button btnRegister = finance_method.findViewById(R.id.btnRegister);
                final LinearLayout rootLayout = finance_method.findViewById(R.id.rootLayout);

                btnRegister.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (TextUtils.isEmpty(edtDays.getText().toString())) {
                            Snackbar.make(rootLayout, "Debes ingresar el número de días", Snackbar.LENGTH_SHORT).show();
                        } else {
                            companyRef.child(post_key).child("Politics").child("Long Terms To Paid").setValue(edtDays.getText().toString());
                            Toasty.success(getActivity(), "Políticas Actualizadas", Toast.LENGTH_LONG).show();
                            dialog.dismiss();
                        }
                    }
                });

                dialog.setView(finance_method);
                dialog.show();
            }
        });


        showPurchaseOrders();

        return view;
    }

    private void showPurchaseOrders() {
        Query query = companyRef.child(post_key).child("Purchased Orders").orderByChild("timestamp");
        FirebaseRecyclerAdapter<PurchaseOrdersModel,PurchaseOrdersViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<PurchaseOrdersModel, PurchaseOrdersViewHolder>
                (PurchaseOrdersModel.class,R.layout.provider_debt_item, PurchaseOrdersViewHolder.class,query) {
            @Override
            protected void populateViewHolder(final PurchaseOrdersViewHolder viewHolder, PurchaseOrdersModel model, int position) {
                final String postKey = getRef(position).getKey();
                viewHolder.setOperation_date(model.getOperation_date());
                viewHolder.setOperation_day(model.getOperation_day());
                viewHolder.setOperation_month(model.getOperation_month());
                viewHolder.setOperation_time(model.getOperation_time());
                viewHolder.setOperation_year(model.getOperation_year());
                viewHolder.setPurchase_order_id(model.getPurchase_order_id());
                viewHolder.setPurchase_order_supplier_id(model.getPurchase_order_supplier_id());
                viewHolder.setPurchase_order_total_amount(model.getPurchase_order_total_amount());
                viewHolder.setPurchase_order_state(model.getPurchase_order_state());

                if (viewHolder.my_purchase_order_state != null) {
                    if (viewHolder.my_purchase_order_state.equals("pending")) {
                        viewHolder.txtCode.setText("Enviado");
                    }
                    if (viewHolder.my_purchase_order_state.equals("ready")) {
                        viewHolder.txtCode.setText("Entregado");
                    }
                    if (viewHolder.my_purchase_order_state.equals("ready_delayed")) {
                        viewHolder.txtCode.setText("Entregado con Retraso");
                    }
                    if (viewHolder.my_purchase_order_state.equals("returned")) {
                        viewHolder.txtCode.setText("Devuelto");
                    }
                    if (viewHolder.my_purchase_order_state.equals("supplier_rejected")) {
                        viewHolder.txtCode.setText("Rechazado por Proveedor");
                    }
                    if (viewHolder.my_purchase_order_state.equals("canceled")) {
                        viewHolder.txtCode.setText("Cancelado");
                    }
                }

                companyRef.child(post_key).child("Purchased Orders").child(postKey).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String expiration_day = dataSnapshot.child("expiration_day").getValue().toString();
                        String expiration_month = dataSnapshot.child("expiration_month").getValue().toString();
                        String expiration_year = dataSnapshot.child("expiration_year").getValue().toString();
                        String state = dataSnapshot.child("purchase_payment_state").getValue().toString();

                        SimpleDateFormat myFormat = new SimpleDateFormat("dd MM yyyy");
                        String inputString1 = day+" "+month+" "+year;
                        String inputString2 = expiration_day+" "+expiration_month+" "+expiration_year;

                        try {
                            Date date1 = myFormat.parse(inputString1);
                            Date date2 = myFormat.parse(inputString2);
                            diff = date2.getTime() - date1.getTime();

                            expiration_days_ago = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);

                            if (diff < 0 && state.equals("no_paid"))  {
                                companyRef.child(post_key).child("Purchased Orders").child(postKey).child("purchase_payment_state").setValue("expired");
                            }

                            if (expiration_days_ago < politic_days_db) {
                                viewHolder.mView.setVisibility(View.GONE);
                            } else {
                                double amount = Double.parseDouble(viewHolder.my_purchase_order_total_amount);
                                sum += amount;
                                String sum_st = decimalFormat.format(sum);
                                txtTotal.setText("TOTAL: S/ "+sum_st);
                            }

                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                viewHolder.txtDate.setText(viewHolder.my_operation_date);

                viewHolder.txtTotal.setText("S/ "+viewHolder.my_purchase_order_total_amount);

                if (viewHolder.my_purchase_order_supplier_id != null) {
                    companyRef.child(post_key).child("My Suppliers").child(viewHolder.my_purchase_order_supplier_id).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            String supplier_name = dataSnapshot.child("supplier_name").getValue().toString();
                            viewHolder.txtSupplier.setText(supplier_name);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }


            }
        };
        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    public static class PurchaseOrdersViewHolder extends RecyclerView.ViewHolder {
        View mView;
        String my_operation_date,my_operation_day,my_operation_month,my_operation_time,my_operation_year,my_purchase_order_id,my_purchase_order_supplier_id,my_purchase_order_total_amount,my_purchase_order_state;
        TextView txtCode,txtSupplier,txtDate,txtTotal;
        ImageView btnActionButton;

        public PurchaseOrdersViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;

            txtCode = mView.findViewById(R.id.txtCode);
            txtSupplier = mView.findViewById(R.id.txtSupplier);
            txtDate = mView.findViewById(R.id.txtDate);
            txtTotal = mView.findViewById(R.id.txtTotal);
            btnActionButton = mView.findViewById(R.id.btnActionButton);
        }

        public void setOperation_date(String operation_date) {
            my_operation_date = operation_date;
        }

        public void setOperation_day(String operation_day) {
            my_operation_day = operation_day;
        }

        public void setOperation_month(String operation_month) {
            my_operation_month = operation_month;
        }

        public void setOperation_time(String operation_time) {
            my_operation_time = operation_time;
        }

        public void setOperation_year(String operation_year) {
            my_operation_year = operation_year;
        }
        public void setPurchase_order_id(String purchase_order_id) {
            my_purchase_order_id = purchase_order_id;
        }
        public void setPurchase_order_supplier_id(String purchase_order_supplier_id) {
            my_purchase_order_supplier_id = purchase_order_supplier_id;
        }

        public void setPurchase_order_total_amount(String purchase_order_total_amount) {
            my_purchase_order_total_amount = purchase_order_total_amount;
        }
        public void setPurchase_order_state(String purchase_order_state) {
            my_purchase_order_state = purchase_order_state;
        }
    }
}
