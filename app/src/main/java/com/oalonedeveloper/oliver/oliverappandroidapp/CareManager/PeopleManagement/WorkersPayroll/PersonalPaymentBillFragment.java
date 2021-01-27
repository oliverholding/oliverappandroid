package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.PeopleManagement.WorkersPayroll;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

public class PersonalPaymentBillFragment extends Fragment {

    TextView txtBillPeriod,txtPeriods,txtCompanyName,txtCompanyRuc,txtCompanyAddress,txtWorkerName,txtWorkerDocument,txtCharge,txtBeginDate,txtWorkerArea,txtWorkerSalary,txtTotalIncomes,txtAfpFund,txtAfpFee,txtTotalDiscount,txtEssalud,txtTotalAportation,txtNetPayment;
    int day,month,year,first_day,last_day;
    String post_key,profile_id,company_social_reason,company_ruc,company_address,job_profile_name,job_profile_surname,job_profile_job_name,company_image,document_number,begin_working_day,begin_working_month,begin_working_year,job_profile_area,payment_amount;
    DatabaseReference companyRef;
    CircleImageView imgCompanyProfile;
    DecimalFormat decimalFormat;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_personal_payment_bill, container, false);

        txtBillPeriod = view.findViewById(R.id.txtBillPeriod);
        txtPeriods = view.findViewById(R.id.txtPeriods);
        txtCompanyName = view.findViewById(R.id.txtCompanyName);
        txtCompanyRuc = view.findViewById(R.id.txtCompanyRuc);
        txtCompanyAddress = view.findViewById(R.id.txtCompanyAddress);
        txtWorkerName = view.findViewById(R.id.txtWorkerName);
        txtWorkerDocument = view.findViewById(R.id.txtWorkerDocument);
        txtCharge = view.findViewById(R.id.txtCharge);
        txtBeginDate = view.findViewById(R.id.txtBeginDate);
        imgCompanyProfile = view.findViewById(R.id.imgCompanyProfile);
        txtWorkerArea = view.findViewById(R.id.txtWorkerArea);
        txtWorkerSalary = view.findViewById(R.id.txtWorkerSalary);
        txtTotalIncomes = view.findViewById(R.id.txtTotalIncomes);
        txtAfpFund = view.findViewById(R.id.txtAfpFund);
        txtAfpFee = view.findViewById(R.id.txtAfpFee);
        txtTotalDiscount = view.findViewById(R.id.txtTotalDiscount);
        txtEssalud = view.findViewById(R.id.txtEssalud);
        txtTotalAportation = view.findViewById(R.id.txtTotalAportation);
        txtNetPayment = view.findViewById(R.id.txtNetPayment);

        post_key = getActivity().getIntent().getExtras().getString("post_key");
        profile_id = getActivity().getIntent().getExtras().getString("profile_id");
        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");

        Date date= new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        day = cal.get(Calendar.DAY_OF_MONTH);
        month = cal.get(Calendar.MONTH)+1;
        year = cal.get(Calendar.YEAR);
        first_day = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
        last_day = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

        decimalFormat = new DecimalFormat();


        txtBillPeriod.setText("BOLETA DE PAGO - "+year+" "+month);

        txtPeriods.setText("DEL "+first_day+"/"+month+"/"+year+" AL "+last_day+"/"+month+"/"+year);

        companyRef.child(post_key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                company_social_reason = dataSnapshot.child("company_social_reason").getValue().toString();
                company_ruc = dataSnapshot.child("company_ruc").getValue().toString();
                company_address = dataSnapshot.child("company_address").getValue().toString();
                company_image = dataSnapshot.child("company_image").getValue().toString();

                txtCompanyName.setText(company_social_reason);
                txtCompanyRuc.setText("RUC: "+company_ruc);
                txtCompanyAddress.setText(company_address);
                Picasso.with(getActivity()).load(company_image).fit().into(imgCompanyProfile);

                companyRef.child(post_key).child("Job Profile").child(profile_id).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        job_profile_name = dataSnapshot.child("job_profile_name").getValue().toString();
                        job_profile_surname = dataSnapshot.child("job_profile_surname").getValue().toString();
                        job_profile_job_name = dataSnapshot.child("job_profile_job_name").getValue().toString();
                        job_profile_area = dataSnapshot.child("job_profile_area").getValue().toString();

                        txtWorkerName.setText("Apellidos y Nombres: "+job_profile_surname+" "+job_profile_name);
                        txtCharge.setText("Cargo: "+job_profile_job_name);
                        txtWorkerArea.setText("Área: "+job_profile_area);

                        if (dataSnapshot.hasChild("Birth Data")) {
                            String document_number = dataSnapshot.child("Birth Data").child("document_number").getValue().toString();
                            txtWorkerDocument.setText("Documento: "+document_number);
                        }

                        companyRef.child(post_key).child("Job Profile").child(profile_id).child("Personal File").child("Birth Data").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                if (dataSnapshot.hasChild("document_number")) {
                                    document_number = dataSnapshot.child("document_number").getValue().toString();
                                    txtWorkerDocument.setText("Documento: "+document_number);
                                }


                                companyRef.child(post_key).child("Job Profile").child(profile_id).child("Personal File").child("Company Worker Data").addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {

                                        if (dataSnapshot.hasChild("begin_working_day")) {
                                            begin_working_day = dataSnapshot.child("begin_working_day").getValue().toString();
                                            begin_working_month = dataSnapshot.child("begin_working_month").getValue().toString();
                                            begin_working_year = dataSnapshot.child("begin_working_year").getValue().toString();
                                            txtBeginDate.setText("Fecha de Ingreso: "+begin_working_day+"/"+begin_working_month+"/"+begin_working_year);
                                        }
                                        if (dataSnapshot.hasChild("payment_amount")) {
                                            payment_amount = dataSnapshot.child("payment_amount").getValue().toString();
                                            txtWorkerSalary.setText("Sueldo Computable: S/ "+payment_amount);
                                            txtTotalIncomes.setText("Total Ingresos: S/ "+payment_amount);

                                            double payment = Double.parseDouble(payment_amount);
                                            double afp_fund = payment*0.10;
                                            double afp_fee = payment*0.0160;
                                            double total_discount = afp_fund+afp_fee;
                                            double essalud = payment*0.09;
                                            double net_payment = payment-total_discount;
                                            String afp_fund_st = decimalFormat.format(afp_fund);
                                            String afp_fee_st = decimalFormat.format(afp_fee);
                                            String total_discount_st = decimalFormat.format(total_discount);
                                            String essalud_st = decimalFormat.format(essalud);
                                            String net_payment_st = decimalFormat.format(net_payment);

                                            txtAfpFund.setText("AFP Fondo 10.00%: S/ "+afp_fund_st);
                                            txtAfpFee.setText("AFP Comisión 1.60%: S/ "+afp_fee_st);
                                            txtTotalDiscount.setText("Total Descuentos: S/ "+total_discount_st);
                                            txtEssalud.setText("EsSalud 9%: S/"+essalud_st);
                                            txtTotalAportation.setText("Total Aportes: S/ "+essalud_st);
                                            txtNetPayment.setText("NETO A PAGAR: S/ "+net_payment_st);

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

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return view;
    }
}
