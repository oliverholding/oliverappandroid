package com.oalonedeveloper.oliver.oliverappandroidapp.FinancialManagement.LendingProduct;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.oalonedeveloper.oliver.oliverappandroidapp.FinancialManagement.FinancialInstitutionDetailActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.FinancialManagement.FinancialProductLendingsListFragment;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import de.hdodenhof.circleimageview.CircleImageView;
import pl.droidsonroids.gif.GifImageView;

public class LoanRequestsListActivity extends AppCompatActivity {

    String post_key;
    DatabaseReference financialInstitutionsRef, lendingRef;
    String financial_institution_name,financial_institution_image,financial_institution_background_image,financial_institution_slogan,financial_institution_type,currentUid;
    TextView txtFinancialInstitutionName,txtFinancialInstitutionSlogan,txtFinancialInstitutionType;
    ImageView imgBackground;
    CircleImageView imgImage;
    RecyclerView recyclerView;
    FirebaseAuth mAuth;
    int day,month,year;
    long diff,expiration_days_ago;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_requests_list);

        financialInstitutionsRef = FirebaseDatabase.getInstance().getReference().child("Financial Institutions");
        lendingRef = FirebaseDatabase.getInstance().getReference().child("Lendings");
        post_key = getIntent().getExtras().getString("post_key");

        txtFinancialInstitutionName = findViewById(R.id.txtFinancialInstitutionName);
        imgBackground = findViewById(R.id.imgBackground);
        imgImage = findViewById(R.id.imgImage);
        txtFinancialInstitutionSlogan = findViewById(R.id.txtFinancialInstitutionSlogan);
        txtFinancialInstitutionType = findViewById(R.id.txtFinancialInstitutionType);

        mAuth = FirebaseAuth.getInstance();
        currentUid = mAuth.getCurrentUser().getUid();

        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        Date date= new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        day = cal.get(Calendar.DAY_OF_MONTH);
        month = cal.get(Calendar.MONTH)+1;
        year = cal.get(Calendar.YEAR);


        financialInstitutionsRef.child(post_key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                financial_institution_name = dataSnapshot.child("financial_institution_name").getValue().toString();
                financial_institution_image = dataSnapshot.child("financial_institution_image").getValue().toString();
                financial_institution_background_image = dataSnapshot.child("financial_institution_background_image").getValue().toString();
                financial_institution_slogan = dataSnapshot.child("financial_institution_slogan").getValue().toString();
                financial_institution_type = dataSnapshot.child("financial_institution_type").getValue().toString();
                txtFinancialInstitutionName.setText(financial_institution_name.toUpperCase());
                txtFinancialInstitutionSlogan.setText(financial_institution_slogan);
                txtFinancialInstitutionType.setText(financial_institution_type);
                Picasso.with(LoanRequestsListActivity.this).load(financial_institution_image).fit().into(imgImage);
                Picasso.with(LoanRequestsListActivity.this).load(financial_institution_background_image).fit().into(imgBackground);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        showRequestLoan();
    }

    String request_id;
    private void showRequestLoan() {
        Query query = financialInstitutionsRef.child(post_key).child("Loan Requests").orderByChild("timestamp");
        FirebaseRecyclerAdapter<RequestLoanModel, RequestsLoanViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<RequestLoanModel, RequestsLoanViewHolder>
                (RequestLoanModel.class,R.layout.request_financial_product_item,RequestsLoanViewHolder.class,query) {
            @Override
            protected void populateViewHolder(final RequestsLoanViewHolder viewHolder, RequestLoanModel model, int position) {
                final String postKey = getRef(position).getKey();
                request_id = postKey;
                viewHolder.setCustomer_id(model.getCustomer_id());
                viewHolder.setDesgravamen_rate(model.getDesgravamen_rate());
                viewHolder.setDocument_number(model.getDocument_number());
                viewHolder.setProduct_fixed_fee(model.getProduct_fixed_fee());
                viewHolder.setProduct_id(model.getProduct_id());
                viewHolder.setProduct_img(model.getProduct_img());
                viewHolder.setProduct_name(model.getProduct_name());
                viewHolder.setProduct_tea(model.getProduct_tea());
                viewHolder.setRequest_day(model.getRequest_day());
                viewHolder.setRequest_month(model.getRequest_month());
                viewHolder.setRequest_state(model.getRequest_state());
                viewHolder.setRequest_year(model.getRequest_year());
                viewHolder.setRequested_date(model.getRequested_date());
                viewHolder.setRequested_time(model.getRequested_time());

                if (!viewHolder.my_customer_id.equals(currentUid)){
                    viewHolder.mView.setVisibility(View.GONE);
                    viewHolder.mView.getLayoutParams().height = 0;
                }

                Picasso.with(LoanRequestsListActivity.this).load(viewHolder.my_product_img).fit().into(viewHolder.imgProduct);
                viewHolder.txtProductName.setText("Producto: "+viewHolder.my_product_name);
                viewHolder.txtDate.setText(viewHolder.my_requested_date);

                viewHolder.btnRequestDetail.setVisibility(View.GONE);
                viewHolder.txtMessage.setVisibility(View.GONE);

                if (viewHolder.my_request_state.equals("sent")) {
                    viewHolder.imgStep1.setImageResource(R.drawable.solicitud_enviada_gif);
                    viewHolder.txtStep1.setText("Solicitud Enviada");
                    viewHolder.txtStep1.setTextColor(Color.BLACK);
                    viewHolder.txtStep1.setTypeface(null, Typeface.BOLD);

                    viewHolder.imgStep2.setImageResource(R.drawable.prestamo_revision_blanco_negro);
                    viewHolder.txtStep2.setText("En Revisión");

                    viewHolder.imgStep3.setImageResource(R.drawable.prestamo_aprovado_blanco_negro);
                    viewHolder.txtStep3.setText("Préstamo Aprobado");
                    viewHolder.btnRequestDetail.setVisibility(View.GONE);
                    viewHolder.txtMessage.setVisibility(View.GONE);

                }
                if (viewHolder.my_request_state.equals("checking")) {

                    viewHolder.imgStep1.setImageResource(R.drawable.check_azul);
                    viewHolder.txtStep1.setText("Solicitud Enviada");
                    viewHolder.txtStep1.setTextColor(Color.BLACK);
                    viewHolder.txtStep1.setTypeface(null, Typeface.BOLD);

                    viewHolder.imgStep2.setImageResource(R.drawable.check_azul);
                    viewHolder.txtStep2.setText("En Revisión");
                    viewHolder.txtStep2.setTextColor(Color.BLACK);
                    viewHolder.txtStep2.setTypeface(null, Typeface.BOLD);

                    viewHolder.imgStep3.setImageResource(R.drawable.prestamo_aprovado_blanco_negro);
                    viewHolder.txtStep3.setText("Préstamo Aprobado");
                    viewHolder.btnRequestDetail.setVisibility(View.GONE);
                    viewHolder.txtMessage.setVisibility(View.GONE);
                }
                if (viewHolder.my_request_state.equals("approved")) {
                    viewHolder.imgStep1.setImageResource(R.drawable.check_azul);
                    viewHolder.txtStep1.setText("Solicitud Enviada");
                    viewHolder.txtStep1.setTextColor(Color.BLACK);
                    viewHolder.txtStep1.setTypeface(null, Typeface.BOLD);

                    viewHolder.imgStep2.setImageResource(R.drawable.check_azul);
                    viewHolder.txtStep2.setText("En Revisión");
                    viewHolder.txtStep2.setTextColor(Color.BLACK);
                    viewHolder.txtStep2.setTypeface(null, Typeface.BOLD);

                    viewHolder.imgStep3.setImageResource(R.drawable.prestamo_aprovado_gif);
                    viewHolder.txtStep3.setText("Préstamo Aprobado");
                    viewHolder.txtStep3.setTextColor(Color.BLACK);
                    viewHolder.txtStep3.setTypeface(null, Typeface.BOLD);
                    viewHolder.btnRequestDetail.setVisibility(View.VISIBLE);
                    viewHolder.txtMessage.setVisibility(View.VISIBLE);

                    financialInstitutionsRef.child(post_key).child("Loan Requests").child(postKey).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (dataSnapshot.hasChild("operation_id")) {
                                String operation_id = dataSnapshot.child("operation_id").getValue().toString();

                                lendingRef.child(operation_id).addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        if (dataSnapshot.hasChild("issuing_expiration_day") && dataSnapshot.hasChild("issuing_expiration_month") && dataSnapshot.hasChild("issuing_expiration_year")) {
                                            String issuing_expiration_day = dataSnapshot.child("issuing_expiration_day").getValue().toString();
                                            String issuing_expiration_month = dataSnapshot.child("issuing_expiration_month").getValue().toString();
                                            String issuing_expiration_year = dataSnapshot.child("issuing_expiration_year").getValue().toString();

                                            SimpleDateFormat myFormat = new SimpleDateFormat("dd MM yyyy");
                                            String inputString1 = day+" "+month+" "+year;
                                            String inputString2 = issuing_expiration_day+" "+issuing_expiration_month+" "+issuing_expiration_year;


                                            try {
                                                Date date1  = myFormat.parse(inputString1);
                                                Date date2 = myFormat.parse(inputString2);
                                                diff = date2.getTime() - date1.getTime();

                                                expiration_days_ago = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
                                                expiration_days_ago = Math.abs(expiration_days_ago);

                                                if (expiration_days_ago < 0) {
                                                    financialInstitutionsRef.child(post_key).child("Loan Requests").child(postKey).child("request_state").setValue("canceled");
                                                }

                                                viewHolder.txtMessage.setText("Tienes "+expiration_days_ago+" días para aceptar el préstamo");

                                            } catch (ParseException e) {
                                                e.printStackTrace();
                                            }


                                        }

                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {

                                    }
                                });

                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });



                }
                if (viewHolder.my_request_state.equals("rejected")) {
                    viewHolder.imgStep1.setImageResource(R.drawable.check_azul);
                    viewHolder.txtStep1.setText("Solicitud Enviada");
                    viewHolder.txtStep1.setTextColor(Color.BLACK);
                    viewHolder.txtStep1.setTypeface(null, Typeface.BOLD);

                    viewHolder.imgStep2.setImageResource(R.drawable.check_azul);
                    viewHolder.txtStep2.setText("En Revisión");
                    viewHolder.txtStep2.setTextColor(Color.BLACK);
                    viewHolder.txtStep2.setTypeface(null, Typeface.BOLD);

                    viewHolder.imgStep3.setImageResource(R.drawable.prestamo_rechazado_gif);
                    viewHolder.txtStep3.setText("Préstamo Rechazado");
                    viewHolder.txtStep3.setTextColor(Color.BLACK);
                    viewHolder.txtStep3.setTypeface(null, Typeface.BOLD);
                    viewHolder.btnRequestDetail.setVisibility(View.GONE);
                    viewHolder.txtMessage.setVisibility(View.GONE);
                }
                if (viewHolder.my_request_state.equals("canceled")) {
                    viewHolder.imgStep1.setImageResource(R.drawable.check_azul);
                    viewHolder.txtStep1.setText("Solicitud Enviada");
                    viewHolder.txtStep1.setTextColor(Color.BLACK);
                    viewHolder.txtStep1.setTypeface(null, Typeface.BOLD);

                    viewHolder.imgStep2.setImageResource(R.drawable.check_azul);
                    viewHolder.txtStep2.setText("En Revisión");
                    viewHolder.txtStep2.setTextColor(Color.BLACK);
                    viewHolder.txtStep2.setTypeface(null, Typeface.BOLD);

                    viewHolder.imgStep3.setImageResource(R.drawable.prestamo_cancelado_gif);
                    viewHolder.txtStep3.setText("Préstamo Cancelado");
                    viewHolder.txtStep3.setTextColor(Color.BLACK);
                    viewHolder.txtStep3.setTypeface(null, Typeface.BOLD);
                    viewHolder.btnRequestDetail.setVisibility(View.GONE);
                    viewHolder.txtMessage.setVisibility(View.GONE);
                }
                if (viewHolder.my_request_state.equals("ready")) {
                    viewHolder.imgStep1.setImageResource(R.drawable.check_azul);
                    viewHolder.txtStep1.setText("Solicitud Enviada");
                    viewHolder.txtStep1.setTextColor(Color.BLACK);
                    viewHolder.txtStep1.setTypeface(null, Typeface.BOLD);

                    viewHolder.imgStep2.setImageResource(R.drawable.check_azul);
                    viewHolder.txtStep2.setText("En Revisión");
                    viewHolder.txtStep2.setTextColor(Color.BLACK);
                    viewHolder.txtStep2.setTypeface(null, Typeface.BOLD);

                    viewHolder.imgStep3.setImageResource(R.drawable.check_azul);
                    viewHolder.txtStep3.setText("Por Desembolsar");
                    viewHolder.txtStep3.setTextColor(Color.BLACK);
                    viewHolder.btnRequestDetail.setVisibility(View.VISIBLE);
                    viewHolder.txtMessage.setVisibility(View.GONE);
                    viewHolder.txtStep3.setTypeface(null, Typeface.BOLD);

                    viewHolder.btnRequestDetail.setText("VER DETALLES DEL PRÉSTAMO");
                }

                financialInstitutionsRef.child(post_key).child("Loan Requests").child(postKey).child("Simulation").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String requested_currency = dataSnapshot.child("requested_currency").getValue().toString();
                        String requested_amount = dataSnapshot.child("requested_amount").getValue().toString();

                        if (requested_currency.equals("PEN")) {
                            viewHolder.txtRequestAmount.setText("Monto solicitado: S/ "+requested_amount);
                        }
                        if (requested_currency.equals("USD")) {
                            viewHolder.txtRequestAmount.setText("Monto solicitado: $ "+requested_amount);
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                viewHolder.btnRequestDetail.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (viewHolder.my_request_state.equals("approved")) {

                            financialInstitutionsRef.child(post_key).child("Loan Requests").child(postKey).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    if (dataSnapshot.hasChild("operation_id")) {
                                        String operation_id = dataSnapshot.child("operation_id").getValue().toString();

                                        Intent intent = new Intent(LoanRequestsListActivity.this,LendingConditionsActivity.class);
                                        intent.putExtra("post_key",post_key);
                                        intent.putExtra("request_id",postKey);
                                        intent.putExtra("operation_id",operation_id);
                                        startActivity(intent);

                                    }
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });

                        }
                        if (viewHolder.my_request_state.equals("ready")) {

                            financialInstitutionsRef.child(post_key).child("Loan Requests").child(postKey).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    if (dataSnapshot.hasChild("operation_id")) {
                                        final String operation_id = dataSnapshot.child("operation_id").getValue().toString();

                                        lendingRef.child(operation_id).addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                String lending_state = dataSnapshot.child("lending_state").getValue().toString();

                                                if (lending_state.equals("approved")) {
                                                    Intent intent = new Intent(LoanRequestsListActivity.this, LoanInProcessToGetActivity.class);
                                                    intent.putExtra("operation_id",operation_id);
                                                    intent.putExtra("institution_key", post_key);
                                                    startActivity(intent);
                                                }
                                                if (lending_state.equals("ready")) {
                                                    Intent intent = new Intent(LoanRequestsListActivity.this, LoanBillsAndDetailsActivity.class);
                                                    intent.putExtra("product_key",viewHolder.my_product_id);
                                                    intent.putExtra("institution_key", post_key);
                                                    intent.putExtra("operation_id",operation_id);
                                                    startActivity(intent);
                                                }

                                            }

                                            @Override
                                            public void onCancelled(DatabaseError databaseError) {

                                            }
                                        });

                                    }
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });
                        }
                    }
                });

            }
        };
        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    public static class RequestsLoanViewHolder extends RecyclerView.ViewHolder {
        View mView;
        String my_customer_id,my_document_number,my_product_id,my_product_img,my_product_name,my_request_day,my_request_month,my_request_state,my_requested_date,my_requested_time,my_product_tea;

        int my_desgravamen_rate,my_product_fixed_fee,my_request_year;

        CircleImageView imgProduct;
        TextView txtProductName,txtStep1,txtStep2,txtStep3,txtRequestAmount,txtDate,txtMessage;
        GifImageView imgStep1,imgStep2,imgStep3;
        Button btnRequestDetail;

        public RequestsLoanViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;

            imgProduct = mView.findViewById(R.id.imgProduct);
            txtProductName = mView.findViewById(R.id.txtProductName);
            imgStep1 = mView.findViewById(R.id.imgStep1);
            imgStep2 = mView.findViewById(R.id.imgStep2);
            imgStep3 = mView.findViewById(R.id.imgStep3);
            txtStep1 = mView.findViewById(R.id.txtStep1);
            txtStep2 = mView.findViewById(R.id.txtStep2);
            txtStep3 = mView.findViewById(R.id.txtStep3);
            txtRequestAmount = mView.findViewById(R.id.txtRequestAmount);
            txtDate = mView.findViewById(R.id.txtDate);
            btnRequestDetail = mView.findViewById(R.id.btnRequestDetail);
            txtMessage = mView.findViewById(R.id.txtMessage);
        }

        public void setCustomer_id(String customer_id) {
            my_customer_id = customer_id;
        }

        public void setDocument_number(String document_number) {
            my_document_number = document_number;
        }

        public void setProduct_id(String product_id) {
            my_product_id = product_id;
        }

        public void setProduct_img(String product_img) {
            my_product_img = product_img;
        }

        public void setProduct_name(String product_name) {
            my_product_name = product_name;
        }

        public void setRequest_day(String request_day) {
            my_request_day = request_day;
        }

        public void setRequest_month(String request_month) {
            my_request_month = request_month;
        }


        public void setRequest_state(String request_state) {
            my_request_state = request_state;
        }


        public void setRequested_date(String requested_date) {
            my_requested_date = requested_date;
        }


        public void setRequested_time(String requested_time) {
            my_requested_time = my_requested_time;
        }

        public void setDesgravamen_rate(int desgravamen_rate) {
            my_desgravamen_rate = desgravamen_rate;
        }


        public void setProduct_fixed_fee(int product_fixed_fee) {
            my_product_fixed_fee = product_fixed_fee;
        }


        public void setProduct_tea(String  product_tea) {
            my_product_tea = product_tea;
        }


        public void setRequest_year(int request_year) {
            my_request_year = request_year;
        }
    }
}
