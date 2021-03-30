package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;
import com.squareup.picasso.Picasso;
import com.xw.repo.BubbleSeekBar;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;


public class CareManagerSumaryFragment extends Fragment {

    DatabaseReference myCompanyRef;
    String  post_key,total_incomes_st;
    TextView txtCurrentMonth,txtIncomes,txtOutcomes,txtIndex1,txtIndex2,txtIndex3;
    double bills,bills_last,total_salary,purchase_total,igv_taxes,total_outcomes;
    DecimalFormat decimalFormat;
    long pending_dispatch,ready_dispatch,production_ready,production_stop,production_production;
    int sum_dispatch;
    ImageView btnInfo1,btnInfo2,btnInfo3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_care_manager_sumary, container, false);

        txtCurrentMonth = view.findViewById(R.id.txtCurrentMonth);
        txtIncomes = view.findViewById(R.id.txtIncomes);
        txtOutcomes = view.findViewById(R.id.txtOutcomes);
        txtIndex1 = view.findViewById(R.id.txtIndex1);
        txtIndex2 = view.findViewById(R.id.txtIndex2);
        txtIndex3 = view.findViewById(R.id.txtIndex3);

        btnInfo1 = view.findViewById(R.id.btnInfo1);
        btnInfo2 = view.findViewById(R.id.btnInfo2);
        btnInfo3 = view.findViewById(R.id.btnInfo3);

        myCompanyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");

        post_key = getActivity().getIntent().getExtras().getString("post_key");
        decimalFormat = new DecimalFormat("0.00");

        Calendar c = Calendar.getInstance();
        final int year = c.get(Calendar.YEAR);
        final int month = c.get(Calendar.MONTH)+1;

        SimpleDateFormat month_date = new SimpleDateFormat("MMMM");
        String month_name = month_date.format(c.getTime());

        txtCurrentMonth.setText(month_name.toUpperCase()+" "+year);


        myCompanyRef.child(post_key).child("My Bills").orderByChild("issuing_year").equalTo(year).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    int issuing_month = ds.child("issuing_month").getValue(Integer.class);
                    if (issuing_month == month) {
                        Map<String, Object> map = (Map<String, Object>) ds.getValue();
                        Object bill_amount = map.get("bill_amount");
                        double bill_amount_db = Double.parseDouble(String.valueOf(bill_amount));
                        bills += bill_amount_db;
                        total_incomes_st = decimalFormat.format(bills);
                        txtIncomes.setText("S/ "+total_incomes_st);
                    }
                    if (issuing_month == month-1) {
                        Map<String, Object> map = (Map<String, Object>) ds.getValue();
                        Object bill_amount = map.get("bill_amount");
                        double bill_amount_db = Double.parseDouble(String.valueOf(bill_amount));
                        bills_last += bill_amount_db;

                    }

                    double bills_var = ((bills_last/bills)-1)*100;
                    String bills_var_st = decimalFormat.format(bills_var);

                    if (bills_var > 0) {
                        txtIndex1.setTextColor(Color.GREEN);
                    }
                    if (bills_var < 0) {
                        txtIndex1.setTextColor(Color.RED);
                    }

                    txtIndex1.setText(bills_var_st+"%");

                }

                myCompanyRef.child(post_key).child("Worker Bills").orderByChild("operation_year").equalTo(year+"").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        total_salary = 0;
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {

                            String operation_month = ds.child("operation_month").getValue().toString();

                            if (operation_month.equals(month+"")) {
                                Map<String, Object> map = (Map<String, Object>) ds.getValue();
                                Object total_payment = map.get("total_payment");
                                double total_payment_db = Double.parseDouble(String.valueOf(total_payment));
                                total_salary += total_payment_db;

                            }


                        }

                        myCompanyRef.child(post_key).child("Purchased Orders").orderByChild("operation_year").equalTo(year+"").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                purchase_total = 0;
                                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                                    String purchase_payment_state = ds.child("purchase_payment_state").getValue().toString();
                                    String expiration_day = ds.child("expiration_day").getValue().toString();
                                    String expiration_month = ds.child("expiration_month").getValue().toString();
                                    String expiration_year = ds.child("expiration_year").getValue().toString();
                                    String operation_day = ds.child("operation_day").getValue().toString();
                                    String operation_month = ds.child("operation_month").getValue().toString();
                                    String operation_time = ds.child("operation_time").getValue().toString();

                                    String issuing_date = operation_day+"/"+operation_month+"/"+operation_time;
                                    String expiration_date = expiration_day+"/"+expiration_month+"/"+expiration_year;

                                    if (purchase_payment_state.equals("paid")) {

                                        if (expiration_month.equals(month+"")) {
                                            Map<String, Object> map = (Map<String, Object>) ds.getValue();
                                            Object purchase_order_total_amount = map.get("purchase_order_total_amount");
                                            double purchase_order_total_amount_db = Double.parseDouble((String) purchase_order_total_amount);
                                            purchase_total += purchase_order_total_amount_db;

                                        }

                                    }

                                }

                                igv_taxes = (bills*0.18)-(purchase_total*0.18);

                                total_outcomes = total_salary+purchase_total+igv_taxes;
                                String total_outcomes_st = decimalFormat.format(total_outcomes);
                                txtOutcomes.setText("S/ "+total_outcomes_st);

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

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        myCompanyRef.child(post_key).child("Dispatches").orderByChild("dispatch_state").equalTo("pending").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    pending_dispatch = dataSnapshot.getChildrenCount();
                }


                myCompanyRef.child(post_key).child("Dispatches").orderByChild("dispatch_state").equalTo("ready").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            ready_dispatch = dataSnapshot.getChildrenCount();

                            sum_dispatch = (int) (pending_dispatch+ready_dispatch);

                            double ready = (ready_dispatch/sum_dispatch)*100;
                            String ready_st = decimalFormat.format(ready);

                            if (ready > 90) {
                                txtIndex2.setTextColor(Color.GREEN);
                            }
                            if (ready < 90) {
                                txtIndex2.setTextColor(Color.RED);
                            }

                            txtIndex2.setText(ready_st+"%");
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

        myCompanyRef.child(post_key).child("Production Chain").orderByChild("state").equalTo("production").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    production_production = dataSnapshot.getChildrenCount();
                }


                myCompanyRef.child(post_key).child("Production Chain").orderByChild("state").equalTo("stop").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            production_stop = dataSnapshot.getChildrenCount();
                        }


                        myCompanyRef.child(post_key).child("Production Chain").orderByChild("state").equalTo("ready").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                if (dataSnapshot.exists()) {
                                    production_ready = dataSnapshot.getChildrenCount();


                                    double total = (double) (production_production+production_stop+production_ready);


                                    double ready = (production_ready/total)*100;



                                    String ready_st = decimalFormat.format(ready);

                                    if (ready > 90) {
                                        txtIndex3.setTextColor(Color.GREEN);
                                    }
                                    if (ready < 90) {
                                        txtIndex3.setTextColor(Color.RED);
                                    }

                                    txtIndex3.setText(ready_st+"%");
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

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        btnInfo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = "Crecimiento en Ventas";
                String info = "Este indicador te permite analizar qué tanto han crecido o disminuido tus ventas en compración al mes pasado, recuerda que por lo general, un valor positivo en este indicador es bueno";
                int img = R.drawable.proyeccion_ventas;

                showInfoDialog(title,info,img);
            }
        });

        btnInfo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = "Pedidos Despachados";
                String info = "Este indicador el porcentaje de pedidos que has despachado, lo más óptimo es siempre estar cerca del 100%";
                int img = R.drawable.despacho_entregas;

                showInfoDialog(title,info,img);
            }
        });

        btnInfo3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = "Órdenes de Producción Listas";
                String info = "Este indicador representa el porcentaje de órdenes de priducción que han sido terminadas con éxito, mientras este valor sea mas cerano a 100%, será mejor.";
                int img = R.drawable.produccion_lista;

                showInfoDialog(title,info,img);
            }
        });


        return view;
    }

    private void showInfoDialog(String title, String info, int img) {
        final AlertDialog dialog = new AlertDialog.Builder(getActivity()).create();

        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View finance_method = inflater.inflate(R.layout.info_tool_dialog,null);

        ImageView imgInfo;
        TextView txtTitle,txtInfo;

        imgInfo = finance_method.findViewById(R.id.imgInfo);
        txtTitle = finance_method.findViewById(R.id.txtTitle);
        txtInfo = finance_method.findViewById(R.id.txtInfo);

        imgInfo.setImageResource(img);
        txtTitle.setText(title);
        txtInfo.setText(info);

        dialog.setView(finance_method);
        dialog.show();
    }
}
