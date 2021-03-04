package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.PeopleManagement.WorkersPayroll;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Commercialization.BillsIssuing.CreateBillActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Production.ProductionCapacity.WorkerBillSuccessfullActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;
import es.dmoral.toasty.Toasty;

public class PersonalPaymentBillFragment extends Fragment {

    TextView txtBillPeriod,txtPeriods,txtCompanyName,txtCompanyRuc,txtCompanyAddress,txtWorkerName,txtWorkerDocument,txtCharge,txtBeginDate,txtWorkerArea,txtWorkerSalary,txtTotalIncomes,txtAfpFund,txtAfpFee,txtTotalDiscount,txtEssalud,txtTotalAportation,txtNetPayment,
            txtExtraTime,txtFamiliarBonus,txtHolidays,txtTransport,txtCts,txtOnp,txtFaults,txtRetentions4,txtRetentions5,txtJudicialRetentions,txtAdvancedPayments;
    int day,month,year,first_day,last_day;
    String post_key,profile_id,company_social_reason,company_ruc,company_address,job_profile_name,job_profile_surname,job_profile_job_name,company_image,document_number,begin_working_day,begin_working_month,begin_working_year,job_profile_area,payment_amount,
            rmv,net_payment_st;
    DatabaseReference companyRef,ratesRef;
    CircleImageView imgCompanyProfile;
    DecimalFormat decimalFormat;
    ImageView btnAddIncomes,btnAddDiscounts;
    double total_incomes,total_discount,salary,rmv_db,sis_db;
    RadioButton rdGeneral,rdLittle,rdMicro;
    RelativeLayout rootLayout;
    Button btnRegisterWorkerBill;

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
        btnAddIncomes = view.findViewById(R.id.btnAddIncomes);
        txtExtraTime = view.findViewById(R.id.txtExtraTime);
        txtFamiliarBonus = view.findViewById(R.id.txtFamiliarBonus);
        txtHolidays = view.findViewById(R.id.txtHolidays);
        txtTransport = view.findViewById(R.id.txtTransport);
        txtCts = view.findViewById(R.id.txtCts);
        btnAddDiscounts = view.findViewById(R.id.btnAddDiscounts);
        txtOnp = view.findViewById(R.id.txtOnp);
        txtFaults = view.findViewById(R.id.txtFaults);
        txtRetentions4 = view.findViewById(R.id.txtRetentions4);
        txtRetentions5 = view.findViewById(R.id.txtRetentions5);
        txtJudicialRetentions = view.findViewById(R.id.txtJudicialRetentions);
        txtAdvancedPayments = view.findViewById(R.id.txtAdvancedPayments);
        rdGeneral = view.findViewById(R.id.rdGeneral);
        rdLittle = view.findViewById(R.id.rdLittle);
        rdMicro = view.findViewById(R.id.rdMicro);
        rootLayout = view.findViewById(R.id.rootLayout);
        btnRegisterWorkerBill = view.findViewById(R.id.btnRegisterWorkerBill);


        post_key = getActivity().getIntent().getExtras().getString("post_key");
        profile_id = getActivity().getIntent().getExtras().getString("profile_id");
        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");
        ratesRef = FirebaseDatabase.getInstance().getReference().child("Rates");

        Date date= new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        day = cal.get(Calendar.DAY_OF_MONTH);
        month = cal.get(Calendar.MONTH)+1;
        year = cal.get(Calendar.YEAR);
        first_day = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
        last_day = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

        decimalFormat = new DecimalFormat("0.0");


        txtBillPeriod.setText("BOLETA DE PAGO - "+year+" "+month);

        txtPeriods.setText("DEL "+first_day+"/"+month+"/"+year+" AL "+last_day+"/"+month+"/"+year);

        btnAddIncomes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!rdGeneral.isChecked() && !rdLittle.isChecked() && !rdMicro.isChecked()) {
                    Snackbar.make(rootLayout,"Debes seleccionar un regimen",Snackbar.LENGTH_SHORT).show();
                } else {
                    showIncomesDialog();
                }

            }
        });

        btnAddDiscounts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!rdGeneral.isChecked() && !rdLittle.isChecked() && !rdMicro.isChecked()) {
                    Snackbar.make(rootLayout, "Debes seleccionar un regimen", Snackbar.LENGTH_SHORT).show();

                } else {
                    discountsDialog();
                }

            }
        });

        rdGeneral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rdLittle.setChecked(false);
                rdMicro.setChecked(false);
            }
        });

        rdLittle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rdGeneral.setChecked(false);
                rdMicro.setChecked(false);
            }
        });

        rdMicro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rdLittle.setChecked(false);
                rdGeneral.setChecked(false);
            }
        });

        ratesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                rmv = dataSnapshot.child("rmv").getValue().toString();
                rmv_db = Double.parseDouble(rmv);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

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
                                            salary = Double.parseDouble(payment_amount);
                                            total_incomes = salary;

                                            txtWorkerSalary.setText("Sueldo: S/ " + payment_amount);
                                            txtTotalIncomes.setText("Total Ingresos: S/ " + payment_amount);

                                            double payment = Double.parseDouble(payment_amount);
                                            double afp_fund = payment * 0.10;
                                            double afp_fee = payment * 0.0160;
                                            total_discount = afp_fund + afp_fee;
                                            double essalud = payment * 0.09;
                                            double net_payment = payment - total_discount;
                                            String afp_fund_st = decimalFormat.format(afp_fund);
                                            String afp_fee_st = decimalFormat.format(afp_fee);
                                            String total_discount_st = decimalFormat.format(total_discount);
                                            String essalud_st = decimalFormat.format(essalud);
                                            net_payment_st = decimalFormat.format(net_payment);

                                            txtAfpFund.setText("AFP Fondo 10.00%: S/ " + afp_fund_st);
                                            txtAfpFee.setText("AFP Comisión 1.60%: S/ " + afp_fee_st);
                                            txtTotalDiscount.setText("Total Descuentos: S/ " + total_discount_st);
                                            txtEssalud.setText("EsSalud 9%: S/" + essalud_st);
                                            txtTotalAportation.setText("Total Aportes: S/ " + essalud_st);
                                            txtNetPayment.setText("NETO A PAGAR: S/ " + net_payment_st);

                                        } else {

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

        btnRegisterWorkerBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Long tsLong = System.currentTimeMillis()/1000;
                String timestamp = tsLong.toString();
                companyRef.child(post_key).child("Worker Bills").child(timestamp).child("total_payment").setValue(net_payment_st);
                companyRef.child(post_key).child("Worker Bills").child(timestamp).child("bill_id").setValue(timestamp);
                companyRef.child(post_key).child("Worker Bills").child(timestamp).child("worker_id").setValue(profile_id);
                companyRef.child(post_key).child("Worker Bills").child(timestamp).child("bill_type").setValue("work_sheet");
                companyRef.child(post_key).child("Worker Bills").child(timestamp).child("operation_day").setValue(day+"");
                companyRef.child(post_key).child("Worker Bills").child(timestamp).child("operation_month").setValue(month+"");
                companyRef.child(post_key).child("Worker Bills").child(timestamp).child("operation_year").setValue(year+"");
                companyRef.child(post_key).child("Worker Bills").child(timestamp).child("timestamp").setValue(ServerValue.TIMESTAMP);

                Intent intent = new Intent(getActivity(), WorkerBillSuccessfullActivity.class);
                startActivity(intent);
                getActivity().finish();

            }
        });

        return view;
    }

    private void discountsDialog() {
        final AlertDialog dialog = new AlertDialog.Builder(getActivity()).create();

        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View finance_method = inflater.inflate(R.layout.add_discounts_payment_roll_dialog,null);

        final EditText edtAfpFund,edtAfpFee,edtOnp,edtFaults,edt4Retentions,edt5Retentions,edtJudicialRetentions,edtAdvance;
        Button btnRegister;

        edtAfpFund = finance_method.findViewById(R.id.edtAfpFund);
        edtAfpFee = finance_method.findViewById(R.id.edtAfpFee);
        edtOnp = finance_method.findViewById(R.id.edtOnp);
        edtFaults = finance_method.findViewById(R.id.edtFaults);
        edt4Retentions = finance_method.findViewById(R.id.edt4Retentions);
        edt5Retentions = finance_method.findViewById(R.id.edt5Retentions);
        edtJudicialRetentions = finance_method.findViewById(R.id.edtJudicialRetentions);
        edtAdvance = finance_method.findViewById(R.id.edtAdvance);
        btnRegister = finance_method.findViewById(R.id.btnRegister);

        if (rdMicro.isChecked()) {
            edtAfpFund.setText("0.00");
            edtAfpFund.setEnabled(false);
            edtAfpFee.setText("0.00");
            edtAfpFee.setEnabled(false);
            edtOnp.setText("0.00");
            edtOnp.setEnabled(false);
            edtFaults.setText("0.00");
            edt4Retentions.setText("0.00");
            edt5Retentions.setText("0.00");
            edtJudicialRetentions.setText("0.00");
            edtAdvance.setText("0.00");

        } else {
            edtAfpFund.setText("10.00");
            edtAfpFee.setText("1.60");
            edtOnp.setText("0.00");
            edtFaults.setText("0.00");
            edt4Retentions.setText("0.00");
            edt5Retentions.setText("0.00");
            edtJudicialRetentions.setText("0.00");
            edtAdvance.setText("0.00");
        }



        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rdMicro.isChecked()) {
                    double afp_fund_percent = Double.parseDouble(edtAfpFund.getText().toString())/100;
                    double afp_fund = salary*afp_fund_percent;
                    double afp_fee_percent = Double.parseDouble(edtAfpFee.getText().toString())/100;
                    double afp_fee = salary*afp_fee_percent;
                    double onp_fee_percent = Double.parseDouble(edtOnp.getText().toString())/100;
                    double onp_fee = salary*onp_fee_percent;
                    double faults = Double.parseDouble(edtFaults.getText().toString());
                    double retentions4 = Double.parseDouble(edt4Retentions.getText().toString());
                    double retentions5 = Double.parseDouble(edt5Retentions.getText().toString());
                    double retentions_judicial = Double.parseDouble(edtJudicialRetentions.getText().toString());
                    double advance = Double.parseDouble(edtAdvance.getText().toString());
                    if (salary > rmv_db) {
                        sis_db = rmv_db*0.04;
                    } else {
                        sis_db = salary*0.04;
                    }

                    String sis_st = decimalFormat.format(sis_db);

                    String afp_fund_st = decimalFormat.format(afp_fund);
                    txtAfpFund.setText("Sist. Pensiones social : S/ "+sis_st);

                    String afp_fee_st = decimalFormat.format(afp_fee);
                    txtAfpFee.setText("AFP Comisión "+edtAfpFee.getText().toString()+"%: S/ "+afp_fee_st);

                    txtOnp.setText("ONP: S/ "+edtOnp.getText().toString());
                    txtFaults.setText("Faltas: S/"+edtFaults.getText().toString());
                    txtRetentions4.setText("Retenciones 4ta Categoría: S/ "+edt4Retentions.getText().toString());
                    txtRetentions5.setText("Retenciones 5ta Categoría: S/ "+edt5Retentions.getText().toString());
                    txtJudicialRetentions.setText("Retenciones Judiciales: S/ "+edtJudicialRetentions.getText().toString());
                    txtAdvancedPayments.setText("Adelantos: S/ "+edtAdvance.getText().toString());

                    total_discount = afp_fund+afp_fee+onp_fee+faults+retentions4+retentions5+retentions_judicial+advance+sis_db;
                    String total_discount_st = decimalFormat.format(total_discount);
                    txtTotalDiscount.setText("Total Descuentos: S/ "+total_discount_st);

                    double payment = total_incomes-total_discount;
                    String payment_st = decimalFormat.format(payment);
                    txtNetPayment.setText("NETO A PAGAR: S/ "+payment_st);

                    Toasty.success(getActivity(), "Registrado", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                } else {
                    double afp_fund_percent = Double.parseDouble(edtAfpFund.getText().toString())/100;
                    double afp_fund = salary*afp_fund_percent;
                    double afp_fee_percent = Double.parseDouble(edtAfpFee.getText().toString())/100;
                    double afp_fee = salary*afp_fee_percent;
                    double onp_fee_percent = Double.parseDouble(edtOnp.getText().toString())/100;
                    double onp_fee = salary*onp_fee_percent;
                    double faults = Double.parseDouble(edtFaults.getText().toString());
                    double retentions4 = Double.parseDouble(edt4Retentions.getText().toString());
                    double retentions5 = Double.parseDouble(edt5Retentions.getText().toString());
                    double retentions_judicial = Double.parseDouble(edtJudicialRetentions.getText().toString());
                    double advance = Double.parseDouble(edtAdvance.getText().toString());

                    String afp_fund_st = decimalFormat.format(afp_fund);
                    txtAfpFund.setText("AFP Fondo "+edtAfpFund.getText().toString()+"%: S/ "+afp_fund_st);

                    String afp_fee_st = decimalFormat.format(afp_fee);
                    txtAfpFee.setText("AFP Comisión "+edtAfpFee.getText().toString()+"%: S/ "+afp_fee_st);

                    txtOnp.setText("ONP: S/ "+edtOnp.getText().toString());
                    txtFaults.setText("Faltas: S/"+edtFaults.getText().toString());
                    txtRetentions4.setText("Retenciones 4ta Categoría: S/ "+edt4Retentions.getText().toString());
                    txtRetentions5.setText("Retenciones 5ta Categoría: S/ "+edt5Retentions.getText().toString());
                    txtJudicialRetentions.setText("Retenciones Judiciales: S/ "+edtJudicialRetentions.getText().toString());
                    txtAdvancedPayments.setText("Adelantos: S/ "+edtAdvance.getText().toString());

                    total_discount = afp_fund+afp_fee+onp_fee+faults+retentions4+retentions5+retentions_judicial+advance;
                    String total_discount_st = decimalFormat.format(total_discount);
                    txtTotalDiscount.setText("Total Descuentos: S/ "+total_discount_st);

                    double payment = total_incomes-total_discount;
                    String payment_st = decimalFormat.format(payment);
                    txtNetPayment.setText("NETO A PAGAR: S/ "+payment_st);

                    Toasty.success(getActivity(), "Registrado", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }

            }
        });

        dialog.setView(finance_method);
        dialog.show();
    }

    private void showIncomesDialog() {
        final AlertDialog dialog = new AlertDialog.Builder(getActivity()).create();

        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View finance_method = inflater.inflate(R.layout.add_incomes_payment_roll_dialog,null);

        final EditText edtSalary,edtExtraTime,edtFamiliarBonus,edtHolidays,edtTransport,edtCts;
        Button btnRegister;

        edtSalary = finance_method.findViewById(R.id.edtSalary);
        edtExtraTime = finance_method.findViewById(R.id.edtExtraTime);
        edtFamiliarBonus = finance_method.findViewById(R.id.edtFamiliarBonus);
        edtHolidays = finance_method.findViewById(R.id.edtHolidays);
        edtTransport = finance_method.findViewById(R.id.edtTransport);
        edtCts = finance_method.findViewById(R.id.edtCts);
        btnRegister = finance_method.findViewById(R.id.btnRegister);

        edtSalary.setText(payment_amount);
        edtExtraTime.setText("0.00");
        edtFamiliarBonus.setText("0.00");
        edtHolidays.setText("0.00");
        edtTransport.setText("0.00");
        edtCts.setText("0.00");

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salary = Double.parseDouble(edtSalary.getText().toString());
                double extra_time = Double.parseDouble(edtExtraTime.getText().toString());
                double familiar_bonus = Double.parseDouble(edtFamiliarBonus.getText().toString());
                double holidays = Double.parseDouble(edtHolidays.getText().toString());
                double transport = Double.parseDouble(edtTransport.getText().toString());
                double cts = Double.parseDouble(edtCts.getText().toString());

                txtWorkerSalary.setText("Sueldo: S/ "+edtSalary.getText().toString());
                txtExtraTime.setText("Horas Extra: S/ "+edtExtraTime.getText().toString());
                txtFamiliarBonus.setText("Asignación Familiar: S/ "+edtFamiliarBonus.getText().toString());
                txtHolidays.setText("Remuneración Vacacional: S/ "+edtHolidays.getText().toString());
                txtTransport.setText("Mov. Sub. Asistencia: S/ "+edtTransport.getText().toString());
                txtCts.setText("C.T.S: S/ "+edtCts.getText().toString());

                total_incomes = salary+extra_time+holidays+familiar_bonus+transport+cts;
                String total_incomes_st = decimalFormat.format(total_incomes);
                txtTotalIncomes.setText("Total Ingresos: S/ "+total_incomes_st);

                String total_discount_st = decimalFormat.format(total_discount);
                txtTotalDiscount.setText("Total Descuentos: S/ "+total_discount_st);

                double payment = total_incomes-total_discount;
                String payment_st = decimalFormat.format(payment);
                txtNetPayment.setText("NETO A PAGAR: S/ "+payment_st);

                Toasty.success(getActivity(), "Registrado", Toast.LENGTH_SHORT).show();
                dialog.dismiss();

            }
        });

        dialog.setView(finance_method);
        dialog.show();
    }
}
