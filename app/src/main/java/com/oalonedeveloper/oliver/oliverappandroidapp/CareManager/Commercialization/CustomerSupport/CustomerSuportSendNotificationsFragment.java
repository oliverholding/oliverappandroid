package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Commercialization.CustomerSupport;

import android.app.AlertDialog;
import android.app.Dialog;
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
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;
import com.hbb20.CountryCodePicker;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Commercialization.BillsIssuing.CreateBillActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.Companies.CompanyCustomersModel;
import com.oalonedeveloper.oliver.oliverappandroidapp.Companies.MyCustomersActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.JavaMailAPI;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

import es.dmoral.toasty.Toasty;


public class CustomerSuportSendNotificationsFragment extends Fragment {

    Button btnAddCustomer;
    String post_key,customer_points,company_name,company_image,company_social_reason,discount1,discount2,discount3,discount4,discount5,discount_value;
    DatabaseReference myCompanyRef;
    RecyclerView recyclerView;
    EditText edtDiscountToSend;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_customer_suport_send_notifications, container, false);

        post_key = getActivity().getIntent().getExtras().getString("post_key");

        btnAddCustomer = view.findViewById(R.id.btnAddCustomer);

        myCompanyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setReverseLayout(false);
        linearLayoutManager.setStackFromEnd(false);
        recyclerView.setLayoutManager(linearLayoutManager);

        myCompanyRef.child(post_key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                company_name = dataSnapshot.child("company_name").getValue().toString();
                company_image = dataSnapshot.child("company_image").getValue().toString();
                company_social_reason = dataSnapshot.child("company_social_reason").getValue().toString();

                myCompanyRef.child(post_key).child("Module 6").child("Customer Points").child("Discounts").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChild("discount1")) {
                            discount1 = dataSnapshot.child("discount1").getValue().toString();

                        }
                        if (dataSnapshot.hasChild("discount2")) {
                            discount2 = dataSnapshot.child("discount2").getValue().toString();

                        }
                        if (dataSnapshot.hasChild("discount3")) {
                            discount3 = dataSnapshot.child("discount3").getValue().toString();

                        }
                        if (dataSnapshot.hasChild("discount4")) {
                            discount4 = dataSnapshot.child("discount4").getValue().toString();

                        }
                        if (dataSnapshot.hasChild("discount5")) {
                            discount5 = dataSnapshot.child("discount5").getValue().toString();

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
                (CompanyCustomersModel.class, R.layout.customer_promo_notification_item, CompanyCustomersViewHolder.class, query) {
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

                myCompanyRef.child(post_key).child("Customers").child(postKey).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChild("customer_points")) {
                            customer_points = dataSnapshot.child("customer_points").getValue().toString();
                            viewHolder.txtPoints.setText("Puntos Acumulados: " + customer_points);
                        } else {
                            customer_points = "0";
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                viewHolder.btnPoints.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showSetPointsDialog();
                    }

                    private void showSetPointsDialog() {
                        final AlertDialog dialog = new AlertDialog.Builder(getActivity()).create();

                        LayoutInflater inflater = LayoutInflater.from(getActivity());
                        View finance_method = inflater.inflate(R.layout.customer_prom_dialog,null);

                        final EditText edtPoints;
                        Button btnRegister;
                        final RelativeLayout rootLayout;
                        TextView txtMessage;

                        edtPoints = finance_method.findViewById(R.id.edtPoints);
                        btnRegister = finance_method.findViewById(R.id.btnRegister);
                        rootLayout = finance_method.findViewById(R.id.rootLayout);
                        txtMessage = finance_method.findViewById(R.id.txtMessage);

                        txtMessage.setText("Asignar puntaje a "+viewHolder.name);

                        btnRegister.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (TextUtils.isEmpty(edtPoints.getText().toString())) {
                                    Snackbar.make(rootLayout,"Debes ingresar el numero de puntos",Snackbar.LENGTH_SHORT);
                                } else {
                                    myCompanyRef.child(post_key).child("Customers").child(postKey).child("customer_points").setValue(edtPoints.getText().toString());
                                    Toasty.success(getActivity(), "Puntaje Asignado", Toast.LENGTH_LONG).show();
                                    dialog.dismiss();
                                }
                            }
                        });

                        dialog.setView(finance_method);
                        dialog.show();
                    }
                });

                viewHolder.btnProm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sendPromEmailDialog();
                    }

                    private void sendPromEmailDialog() {
                        final AlertDialog dialog = new AlertDialog.Builder(getActivity()).create();

                        LayoutInflater inflater = LayoutInflater.from(getActivity());
                        View finance_method = inflater.inflate(R.layout.send_prom_email_dialog,null);

                        Button btnRegister;
                        final RelativeLayout rootLayout;
                        TextView txtMessage;

                        edtDiscountToSend = finance_method.findViewById(R.id.edtDiscountToSend);
                        btnRegister = finance_method.findViewById(R.id.btnRegister);
                        rootLayout = finance_method.findViewById(R.id.rootLayout);
                        txtMessage = finance_method.findViewById(R.id.txtMessage);

                        txtMessage.setText("Enviar descuento a "+viewHolder.name);

                        myCompanyRef.child(post_key).child("Customers").child(postKey).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                discount_value = "0";
                                if (dataSnapshot.hasChild("customer_points")) {
                                    customer_points = dataSnapshot.child("customer_points").getValue().toString();
                                    int customer_points_int = Integer.parseInt(customer_points);

                                    if (customer_points_int >= 0 && customer_points_int <= 5) {
                                        discount_value = discount1;
                                    }
                                    if (customer_points_int >= 6 && customer_points_int <= 10) {
                                        discount_value = discount2;
                                    }
                                    if (customer_points_int >= 11 && customer_points_int <= 50) {
                                        discount_value = discount3;
                                    }
                                    if (customer_points_int >= 51 && customer_points_int <= 100) {
                                        discount_value = discount4;
                                    }
                                    if (customer_points_int > 100) {
                                        discount_value = discount5;
                                    }

                                    edtDiscountToSend.setText(discount_value);
                                } else {
                                    customer_points = "0";
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });


                        btnRegister.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (TextUtils.isEmpty(edtDiscountToSend.getText().toString())) {
                                    Snackbar.make(rootLayout,"Debes ingresar el porcentaje de descuento",Snackbar.LENGTH_SHORT);
                                } else {
                                    String subject = viewHolder.name+", Tienes descuentos en "+company_name;
                                    String message = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional //EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" +
                                            "\n" +
                                            "<html xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\" xmlns:v=\"urn:schemas-microsoft-com:vml\">\n" +
                                            "<head>\n" +
                                            "<!--[if gte mso 9]><xml><o:OfficeDocumentSettings><o:AllowPNG/><o:PixelsPerInch>96</o:PixelsPerInch></o:OfficeDocumentSettings></xml><![endif]-->\n" +
                                            "<meta content=\"text/html; charset=utf-8\" http-equiv=\"Content-Type\"/>\n" +
                                            "<meta content=\"width=device-width\" name=\"viewport\"/>\n" +
                                            "<!--[if !mso]><!-->\n" +
                                            "<meta content=\"IE=edge\" http-equiv=\"X-UA-Compatible\"/>\n" +
                                            "<!--<![endif]-->\n" +
                                            "<title></title>\n" +
                                            "<!--[if !mso]><!-->\n" +
                                            "<link href=\"https://fonts.googleapis.com/css?family=Montserrat\" rel=\"stylesheet\" type=\"text/css\"/>\n" +
                                            "<!--<![endif]-->\n" +
                                            "<style type=\"text/css\">\n" +
                                            "\t\tbody {\n" +
                                            "\t\t\tmargin: 0;\n" +
                                            "\t\t\tpadding: 0;\n" +
                                            "\t\t}\n" +
                                            "\n" +
                                            "\t\ttable,\n" +
                                            "\t\ttd,\n" +
                                            "\t\ttr {\n" +
                                            "\t\t\tvertical-align: top;\n" +
                                            "\t\t\tborder-collapse: collapse;\n" +
                                            "\t\t}\n" +
                                            "\n" +
                                            "\t\t* {\n" +
                                            "\t\t\tline-height: inherit;\n" +
                                            "\t\t}\n" +
                                            "\n" +
                                            "\t\ta[x-apple-data-detectors=true] {\n" +
                                            "\t\t\tcolor: inherit !important;\n" +
                                            "\t\t\ttext-decoration: none !important;\n" +
                                            "\t\t}\n" +
                                            "\t</style>\n" +
                                            "<style id=\"media-query\" type=\"text/css\">\n" +
                                            "\t\t@media (max-width: 690px) {\n" +
                                            "\n" +
                                            "\t\t\t.block-grid,\n" +
                                            "\t\t\t.col {\n" +
                                            "\t\t\t\tmin-width: 320px !important;\n" +
                                            "\t\t\t\tmax-width: 100% !important;\n" +
                                            "\t\t\t\tdisplay: block !important;\n" +
                                            "\t\t\t}\n" +
                                            "\n" +
                                            "\t\t\t.block-grid {\n" +
                                            "\t\t\t\twidth: 100% !important;\n" +
                                            "\t\t\t}\n" +
                                            "\n" +
                                            "\t\t\t.col {\n" +
                                            "\t\t\t\twidth: 100% !important;\n" +
                                            "\t\t\t}\n" +
                                            "\n" +
                                            "\t\t\t.col_cont {\n" +
                                            "\t\t\t\tmargin: 0 auto;\n" +
                                            "\t\t\t}\n" +
                                            "\n" +
                                            "\t\t\timg.fullwidth,\n" +
                                            "\t\t\timg.fullwidthOnMobile {\n" +
                                            "\t\t\t\tmax-width: 100% !important;\n" +
                                            "\t\t\t}\n" +
                                            "\n" +
                                            "\t\t\t.no-stack .col {\n" +
                                            "\t\t\t\tmin-width: 0 !important;\n" +
                                            "\t\t\t\tdisplay: table-cell !important;\n" +
                                            "\t\t\t}\n" +
                                            "\n" +
                                            "\t\t\t.no-stack.two-up .col {\n" +
                                            "\t\t\t\twidth: 50% !important;\n" +
                                            "\t\t\t}\n" +
                                            "\n" +
                                            "\t\t\t.no-stack .col.num2 {\n" +
                                            "\t\t\t\twidth: 16.6% !important;\n" +
                                            "\t\t\t}\n" +
                                            "\n" +
                                            "\t\t\t.no-stack .col.num3 {\n" +
                                            "\t\t\t\twidth: 25% !important;\n" +
                                            "\t\t\t}\n" +
                                            "\n" +
                                            "\t\t\t.no-stack .col.num4 {\n" +
                                            "\t\t\t\twidth: 33% !important;\n" +
                                            "\t\t\t}\n" +
                                            "\n" +
                                            "\t\t\t.no-stack .col.num5 {\n" +
                                            "\t\t\t\twidth: 41.6% !important;\n" +
                                            "\t\t\t}\n" +
                                            "\n" +
                                            "\t\t\t.no-stack .col.num6 {\n" +
                                            "\t\t\t\twidth: 50% !important;\n" +
                                            "\t\t\t}\n" +
                                            "\n" +
                                            "\t\t\t.no-stack .col.num7 {\n" +
                                            "\t\t\t\twidth: 58.3% !important;\n" +
                                            "\t\t\t}\n" +
                                            "\n" +
                                            "\t\t\t.no-stack .col.num8 {\n" +
                                            "\t\t\t\twidth: 66.6% !important;\n" +
                                            "\t\t\t}\n" +
                                            "\n" +
                                            "\t\t\t.no-stack .col.num9 {\n" +
                                            "\t\t\t\twidth: 75% !important;\n" +
                                            "\t\t\t}\n" +
                                            "\n" +
                                            "\t\t\t.no-stack .col.num10 {\n" +
                                            "\t\t\t\twidth: 83.3% !important;\n" +
                                            "\t\t\t}\n" +
                                            "\n" +
                                            "\t\t\t.video-block {\n" +
                                            "\t\t\t\tmax-width: none !important;\n" +
                                            "\t\t\t}\n" +
                                            "\n" +
                                            "\t\t\t.mobile_hide {\n" +
                                            "\t\t\t\tmin-height: 0px;\n" +
                                            "\t\t\t\tmax-height: 0px;\n" +
                                            "\t\t\t\tmax-width: 0px;\n" +
                                            "\t\t\t\tdisplay: none;\n" +
                                            "\t\t\t\toverflow: hidden;\n" +
                                            "\t\t\t\tfont-size: 0px;\n" +
                                            "\t\t\t}\n" +
                                            "\n" +
                                            "\t\t\t.desktop_hide {\n" +
                                            "\t\t\t\tdisplay: block !important;\n" +
                                            "\t\t\t\tmax-height: none !important;\n" +
                                            "\t\t\t}\n" +
                                            "\t\t}\n" +
                                            "\t</style>\n" +
                                            "</head>\n" +
                                            "<body class=\"clean-body\" style=\"margin: 0; padding: 0; -webkit-text-size-adjust: 100%; background-color: #FFFFFF;\">\n" +
                                            "<table bgcolor=\"#FFFFFF\" cellpadding=\"0\" cellspacing=\"0\" class=\"nl-container\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; min-width: 320px; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #FFFFFF; width: 100%;\" valign=\"top\" width=\"100%\">\n" +
                                            "<tbody>\n" +
                                            "<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                                            "<td style=\"word-break: break-word; vertical-align: top;\" valign=\"top\">\n" +
                                            "<div style=\"background-color:#000fff;\">\n" +
                                            "<div class=\"block-grid\" style=\"min-width: 320px; max-width: 670px; overflow-wrap: break-word; word-wrap: break-word; word-break: break-word; Margin: 0 auto; background-color: transparent;\">\n" +
                                            "<div style=\"border-collapse: collapse;display: table;width: 100%;background-color:transparent;\">\n" +
                                            "<div class=\"col num12\" style=\"min-width: 320px; max-width: 670px; display: table-cell; vertical-align: top; width: 670px;\">\n" +
                                            "<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                                            "<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:10px; padding-bottom:10px; padding-right: 0px; padding-left: 0px;\">\n" +
                                            "<div align=\"center\" class=\"img-container center fixedwidth\" style=\"padding-right: 0px;padding-left: 0px;\">\n" +
                                            "<img align=\"center\" alt=\"Company Logo\" border=\"0\" class=\"center fixedwidth\" src=\"http://oliver.com.pe/wp-content/uploads/2020/12/Artboard-3.png\" style=\"text-decoration: none; -ms-interpolation-mode: bicubic; height: auto; border: 0; width: 100%; max-width: 134px; display: block;\" title=\"Company Logo\" width=\"134\"/>\n" +
                                            "<!--[if mso]></td></tr></table><![endif]-->\n" +
                                            "</div>\n" +
                                            "<!--[if (!mso)&(!IE)]><!-->\n" +
                                            "</div>\n" +
                                            "<!--<![endif]-->\n" +
                                            "</div>\n" +
                                            "</div>\n" +
                                            "</div>\n" +
                                            "</div>\n" +
                                            "</div>\n" +
                                            "<div style=\"background-color:#ececec;\">\n" +
                                            "<div class=\"block-grid\" style=\"min-width: 320px; max-width: 670px; overflow-wrap: break-word; word-wrap: break-word; word-break: break-word; Margin: 0 auto; background-color: transparent;\">\n" +
                                            "<div style=\"border-collapse: collapse;display: table;width: 100%;background-color:transparent;\">\n" +
                                            "<div class=\"col num12\" style=\"min-width: 320px; max-width: 670px; display: table-cell; vertical-align: top; width: 670px;\">\n" +
                                            "<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                                            "<!--[if (!mso)&(!IE)]><!-->\n" +
                                            "<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:0px; padding-bottom:0px; padding-right: 0px; padding-left: 0px;\">\n" +
                                            "<!--<![endif]-->\n" +
                                            "<div align=\"center\" class=\"img-container center fixedwidth\" style=\"padding-right: 0px;padding-left: 0px;\">\n" +
                                            "<img align=\"center\" alt=\"Alternate text\" border=\"0\" class=\"center fixedwidth\" src=\"images/morado.jpg\" style=\"text-decoration: none; -ms-interpolation-mode: bicubic; height: auto; border: 0; width: 100%; max-width: 670px; display: block;\" title=\"Alternate text\" width=\"670\"/>\n" +
                                            "</div>\n" +
                                            "<!--[if (!mso)&(!IE)]><!-->\n" +
                                            "</div>\n" +
                                            "<!--<![endif]-->\n" +
                                            "</div>\n" +
                                            "</div>\n" +
                                            "</div>\n" +
                                            "</div>\n" +
                                            "</div>\n" +
                                            "<div style=\"background-color:#ececec;\">\n" +
                                            "<div class=\"block-grid two-up\" style=\"min-width: 320px; max-width: 670px; overflow-wrap: break-word; word-wrap: break-word; word-break: break-word; Margin: 0 auto; background-color: #ffffff;\">\n" +
                                            "<div style=\"border-collapse: collapse;display: table;width: 100%;background-color:#ffffff;\">\n" +
                                            "<div class=\"col num6\" style=\"display: table-cell; vertical-align: top; max-width: 320px; min-width: 330px; width: 335px;\">\n" +
                                            "<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                                            "<!--[if (!mso)&(!IE)]><!-->\n" +
                                            "<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\">\n" +
                                            "<!--<![endif]-->\n" +
                                            "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\" width=\"100%\">\n" +
                                            "<tbody>\n" +
                                            "<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                                            "<td class=\"divider_inner\" style=\"word-break: break-word; vertical-align: top; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%; padding-top: 10px; padding-right: 10px; padding-bottom: 10px; padding-left: 10px;\" valign=\"top\">\n" +
                                            "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider_content\" height=\"15\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; border-top: 0px solid transparent; height: 15px; width: 100%;\" valign=\"top\" width=\"100%\">\n" +
                                            "<tbody>\n" +
                                            "<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                                            "<td height=\"15\" style=\"word-break: break-word; vertical-align: top; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\"><span></span></td>\n" +
                                            "</tr>\n" +
                                            "</tbody>\n" +
                                            "</table>\n" +
                                            "</td>\n" +
                                            "</tr>\n" +
                                            "</tbody>\n" +
                                            "</table>\n" +
                                            "<div style=\"color:#333333;font-family:'Montserrat', 'Trebuchet MS', 'Lucida Grande', 'Lucida Sans Unicode', 'Lucida Sans', Tahoma, sans-serif;line-height:1.2;padding-top:10px;padding-right:10px;padding-bottom:5px;padding-left:20px;\">\n" +
                                            "<div style=\"font-size: 14px; line-height: 1.2; font-family: 'Montserrat', 'Trebuchet MS', 'Lucida Grande', 'Lucida Sans Unicode', 'Lucida Sans', Tahoma, sans-serif; color: #333333; mso-line-height-alt: 17px;\">\n" +
                                            "<p style=\"font-size: 24px; line-height: 1.2; word-break: break-word; text-align: left; font-family: Montserrat, 'Trebuchet MS', 'Lucida Grande', 'Lucida Sans Unicode', 'Lucida Sans', Tahoma, sans-serif; mso-line-height-alt: 29px; margin: 0;\"><span style=\"font-size: 24px;\"><strong>"+company_name+"</strong><br/></span></p>\n" +
                                            "</div>\n" +
                                            "</div>\n" +
                                            "<div style=\"color:#8d8d8d;font-family:Verdana, Geneva, sans-serif;line-height:1.8;padding-top:10px;padding-right:10px;padding-bottom:10px;padding-left:20px;\">\n" +
                                            "<div style=\"line-height: 1.8; font-size: 12px; font-family: Verdana, Geneva, sans-serif; color: #8d8d8d; mso-line-height-alt: 22px;\">\n" +
                                            "<p style=\"font-size: 14px; line-height: 1.8; word-break: break-word; text-align: left; font-family: Verdana, Geneva, sans-serif; mso-line-height-alt: 25px; margin: 0;\">"+company_social_reason+" </p>\n" +
                                            "</div>\n" +
                                            "</div>\n" +
                                            "<div align=\"center\" class=\"button-container\" style=\"padding-top:10px;padding-right:10px;padding-bottom:10px;padding-left:20px;\">\n" +
                                            "\n" +
                                            "</div>\n" +
                                            "<!--[if (!mso)&(!IE)]><!-->\n" +
                                            "</div>\n" +
                                            "<!--<![endif]-->\n" +
                                            "</div>\n" +
                                            "</div>\n" +
                                            "<div class=\"col num6\" style=\"display: table-cell; vertical-align: top; max-width: 320px; min-width: 330px; width: 335px;\">\n" +
                                            "<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                                            "<!--[if (!mso)&(!IE)]><!-->\n" +
                                            "<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\">\n" +
                                            "<!--<![endif]-->\n" +
                                            "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\" width=\"100%\">\n" +
                                            "<tbody>\n" +
                                            "<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                                            "<td class=\"divider_inner\" style=\"word-break: break-word; vertical-align: top; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%; padding-top: 10px; padding-right: 10px; padding-bottom: 10px; padding-left: 10px;\" valign=\"top\">\n" +
                                            "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider_content\" height=\"15\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; border-top: 0px solid transparent; height: 15px; width: 100%;\" valign=\"top\" width=\"100%\">\n" +
                                            "<tbody>\n" +
                                            "<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                                            "<td height=\"15\" style=\"word-break: break-word; vertical-align: top; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\"><span></span></td>\n" +
                                            "</tr>\n" +
                                            "</tbody>\n" +
                                            "</table>\n" +
                                            "</td>\n" +
                                            "</tr>\n" +
                                            "</tbody>\n" +
                                            "</table>\n" +
                                            "<div align=\"center\" class=\"img-container center fixedwidth\" style=\"padding-right: 0px;padding-left: 0px;\">\n" +
                                            "<img align=\"center\" alt=\"new born clothes\" border=\"0\" class=\"center fixedwidth\" src=\""+company_image+"\" style=\"text-decoration: none; -ms-interpolation-mode: bicubic; height: auto; border: 0; width: 100%; max-width: 150px; display: block;\" title=\"new born clothes\" width=\"150\"/>\n" +
                                            "</div>\n" +
                                            "<!--[if (!mso)&(!IE)]><!-->\n" +
                                            "</div>\n" +
                                            "<!--<![endif]-->\n" +
                                            "</div>\n" +
                                            "</div>\n" +
                                            "</div>\n" +
                                            "</div>\n" +
                                            "</div>\n" +
                                            "<div style=\"background-color:#ececec;\">\n" +
                                            "<div class=\"block-grid\" style=\"min-width: 320px; max-width: 670px; overflow-wrap: break-word; word-wrap: break-word; word-break: break-word; Margin: 0 auto; background-color: #ffffff;\">\n" +
                                            "<div style=\"border-collapse: collapse;display: table;width: 100%;background-color:#ffffff;\">\n" +
                                            "\n" +
                                            "<div class=\"col num12\" style=\"min-width: 320px; max-width: 670px; display: table-cell; vertical-align: top; width: 670px;\">\n" +
                                            "<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                                            "<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\">\n" +
                                            "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\" width=\"100%\">\n" +
                                            "<tbody>\n" +
                                            "<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                                            "<td class=\"divider_inner\" style=\"word-break: break-word; vertical-align: top; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%; padding-top: 10px; padding-right: 10px; padding-bottom: 10px; padding-left: 10px;\" valign=\"top\">\n" +
                                            "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider_content\" height=\"15\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; border-top: 0px solid transparent; height: 15px; width: 100%;\" valign=\"top\" width=\"100%\">\n" +
                                            "<tbody>\n" +
                                            "<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                                            "<td height=\"15\" style=\"word-break: break-word; vertical-align: top; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\"><span></span></td>\n" +
                                            "</tr>\n" +
                                            "</tbody>\n" +
                                            "</table>\n" +
                                            "</td>\n" +
                                            "</tr>\n" +
                                            "</tbody>\n" +
                                            "</table>\n" +
                                            "<div style=\"color:#000000;font-family:'Montserrat', 'Trebuchet MS', 'Lucida Grande', 'Lucida Sans Unicode', 'Lucida Sans', Tahoma, sans-serif;line-height:1.2;padding-top:15px;padding-right:10px;padding-bottom:5px;padding-left:10px;\">\n" +
                                            "<div style=\"font-size: 14px; line-height: 1.2; font-family: 'Montserrat', 'Trebuchet MS', 'Lucida Grande', 'Lucida Sans Unicode', 'Lucida Sans', Tahoma, sans-serif; color: #000000; mso-line-height-alt: 17px;\">\n" +
                                            "<p style=\"font-size: 30px; line-height: 1.2; word-break: break-word; text-align: center; font-family: Montserrat, 'Trebuchet MS', 'Lucida Grande', 'Lucida Sans Unicode', 'Lucida Sans', Tahoma, sans-serif; mso-line-height-alt: 36px; margin: 0;\"><span style=\"font-size: 30px;\">"+viewHolder.name+ " tus puntos acumulados </span></p>\n" +
                                            "<p style=\"font-size: 30px; line-height: 1.2; word-break: break-word; text-align: center; font-family: Montserrat, 'Trebuchet MS', 'Lucida Grande', 'Lucida Sans Unicode', 'Lucida Sans', Tahoma, sans-serif; mso-line-height-alt: 36px; margin: 0;\"><span style=\"font-size: 30px;\">al día de hoy son:</span></p>\n" +
                                            "</div>\n" +
                                            "</div>\n" +
                                            "<div style=\"color:#000fff;font-family:'Montserrat', 'Trebuchet MS', 'Lucida Grande', 'Lucida Sans Unicode', 'Lucida Sans', Tahoma, sans-serif;line-height:1.2;padding-top:10px;padding-right:10px;padding-bottom:10px;padding-left:10px;\">\n" +
                                            "<div style=\"line-height: 1.2; font-size: 12px; font-family: 'Montserrat', 'Trebuchet MS', 'Lucida Grande', 'Lucida Sans Unicode', 'Lucida Sans', Tahoma, sans-serif; color: #000fff; mso-line-height-alt: 14px;\">\n" +
                                            "<p style=\"font-size: 34px; line-height: 1.2; word-break: break-word; text-align: center; font-family: Montserrat, 'Trebuchet MS', 'Lucida Grande', 'Lucida Sans Unicode', 'Lucida Sans', Tahoma, sans-serif; mso-line-height-alt: 41px; margin: 0;\"><span style=\"font-size: 34px;\"><strong>"+customer_points+" PUNTOS</strong></span></p>\n" +
                                            "</div>\n" +
                                            "</div>\n" +
                                            "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\" width=\"100%\">\n" +
                                            "<tbody>\n" +
                                            "<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                                            "<td class=\"divider_inner\" style=\"word-break: break-word; vertical-align: top; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%; padding-top: 10px; padding-right: 10px; padding-bottom: 10px; padding-left: 10px;\" valign=\"top\">\n" +
                                            "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider_content\" height=\"0\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; border-top: 0px solid transparent; height: 0px; width: 100%;\" valign=\"top\" width=\"100%\">\n" +
                                            "<tbody>\n" +
                                            "<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                                            "<td height=\"0\" style=\"word-break: break-word; vertical-align: top; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\"><span></span></td>\n" +
                                            "</tr>\n" +
                                            "</tbody>\n" +
                                            "</table>\n" +
                                            "</td>\n" +
                                            "</tr>\n" +
                                            "</tbody>\n" +
                                            "</table>\n" +
                                            "<div style=\"color:#434343;font-family:Verdana, Geneva, sans-serif;line-height:1.8;padding-top:10px;padding-right:60px;padding-bottom:10px;padding-left:60px;\">\n" +
                                            "<div style=\"line-height: 1.8; font-size: 12px; font-family: Verdana, Geneva, sans-serif; color: #434343; mso-line-height-alt: 22px;\">\n" +
                                            "<p style=\"font-size: 14px; line-height: 1.8; text-align: center; word-break: break-word; font-family: Verdana, Geneva, sans-serif; mso-line-height-alt: 25px; margin: 0;\">Hola "+viewHolder.name+", te escribimos de "+company_name+"\"\", para informarte que haz acumulado \""+customer_points+" PUNTOS\" que te dan derecho a un descuento de\""+edtDiscountToSend.getText().toString()+"%\" en tu próxima compra. Muéstranos el mensaje cuando nos visites nuevamente para darte el descuento. Te esperamos !!!</p>\n" +
                                            "</div>\n" +
                                            "</div>\n" +
                                            "<!--[if mso]></td></tr></table><![endif]-->\n" +
                                            "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\" width=\"100%\">\n" +
                                            "<tbody>\n" +
                                            "<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                                            "<td class=\"divider_inner\" style=\"word-break: break-word; vertical-align: top; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%; padding-top: 0px; padding-right: 0px; padding-bottom: 0px; padding-left: 0px;\" valign=\"top\">\n" +
                                            "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider_content\" height=\"20\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; border-top: 0px solid transparent; height: 20px; width: 100%;\" valign=\"top\" width=\"100%\">\n" +
                                            "<tbody>\n" +
                                            "<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                                            "<td height=\"20\" style=\"word-break: break-word; vertical-align: top; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\"><span></span></td>\n" +
                                            "</tr>\n" +
                                            "</tbody>\n" +
                                            "</table>\n" +
                                            "</td>\n" +
                                            "</tr>\n" +
                                            "</tbody>\n" +
                                            "</table>\n" +
                                            "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\" width=\"100%\">\n" +
                                            "<tbody>\n" +
                                            "<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                                            "<td class=\"divider_inner\" style=\"word-break: break-word; vertical-align: top; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%; padding-top: 10px; padding-right: 10px; padding-bottom: 10px; padding-left: 10px;\" valign=\"top\">\n" +
                                            "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider_content\" height=\"20\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; border-top: 0px solid transparent; height: 20px; width: 100%;\" valign=\"top\" width=\"100%\">\n" +
                                            "<tbody>\n" +
                                            "<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                                            "<td height=\"20\" style=\"word-break: break-word; vertical-align: top; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\"><span></span></td>\n" +
                                            "</tr>\n" +
                                            "</tbody>\n" +
                                            "</table>\n" +
                                            "</td>\n" +
                                            "</tr>\n" +
                                            "</tbody>\n" +
                                            "</table>\n" +
                                            "<!--[if (!mso)&(!IE)]><!-->\n" +
                                            "</div>\n" +
                                            "<!--<![endif]-->\n" +
                                            "</div>\n" +
                                            "</div>\n" +
                                            "</div>\n" +
                                            "</div>\n" +
                                            "</div>\n" +
                                            "<div style=\"background-color:#02025c;\">\n" +
                                            "<div class=\"block-grid\" style=\"min-width: 320px; max-width: 670px; overflow-wrap: break-word; word-wrap: break-word; word-break: break-word; Margin: 0 auto; background-color: transparent;\">\n" +
                                            "<div style=\"border-collapse: collapse;display: table;width: 100%;background-color:transparent;\">\n" +
                                            "<div class=\"col num12\" style=\"min-width: 320px; max-width: 670px; display: table-cell; vertical-align: top; width: 670px;\">\n" +
                                            "<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                                            "<!--[if (!mso)&(!IE)]><!-->\n" +
                                            "<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\">\n" +
                                            "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\" width=\"100%\">\n" +
                                            "<tbody>\n" +
                                            "<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                                            "<td class=\"divider_inner\" style=\"word-break: break-word; vertical-align: top; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%; padding-top: 5px; padding-right: 5px; padding-bottom: 5px; padding-left: 5px;\" valign=\"top\">\n" +
                                            "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider_content\" height=\"20\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; border-top: 0px solid transparent; height: 20px; width: 100%;\" valign=\"top\" width=\"100%\">\n" +
                                            "<tbody>\n" +
                                            "<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                                            "<td height=\"20\" style=\"word-break: break-word; vertical-align: top; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\"><span></span></td>\n" +
                                            "</tr>\n" +
                                            "</tbody>\n" +
                                            "</table>\n" +
                                            "</td>\n" +
                                            "</tr>\n" +
                                            "</tbody>\n" +
                                            "</table>\n" +
                                            "<!--[if (!mso)&(!IE)]><!-->\n" +
                                            "</div>\n" +
                                            "<!--<![endif]-->\n" +
                                            "</div>\n" +
                                            "</div>\n" +
                                            "</div>\n" +
                                            "</div>\n" +
                                            "</div>\n" +
                                            "<!--[if (mso)|(IE)]></td></tr></table><![endif]-->\n" +
                                            "</td>\n" +
                                            "</tr>\n" +
                                            "</tbody>\n" +
                                            "</table>\n" +
                                            "<!--[if (IE)]></div><![endif]-->\n" +
                                            "</body>\n" +
                                            "</html>";
                                    JavaMailAPI javaMailAPI = new JavaMailAPI(getActivity(), viewHolder.email,subject,message);
                                    javaMailAPI.execute();

                                    dialog.dismiss();
                                }
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
        TextView txtName,txtPhone,txtEmail,txtType,txtPoints;
        String email,name,phone,type;
        Button btnProm,btnPoints;

        public CompanyCustomersViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;

            txtName = mView.findViewById(R.id.txtName);
            txtPhone = mView.findViewById(R.id.txtPhone);
            txtEmail = mView.findViewById(R.id.txtEmail);
            txtType = mView.findViewById(R.id.txtType);
            btnProm = mView.findViewById(R.id.btnProm);
            txtPoints = mView.findViewById(R.id.txtPoints);
            btnPoints = mView.findViewById(R.id.btnPoints);
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
}
