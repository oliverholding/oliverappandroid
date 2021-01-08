package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Commercialization.CreditsAndTreasury;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Commercialization.BillsIssuing.MyBillsModel;
import com.oalonedeveloper.oliver.oliverappandroidapp.JavaMailAPI;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;


public class ExpiredDebtFragment extends Fragment {

    String post_key,company_social_reason,company_image,customer_name,customer_email,customer_phone;
    RecyclerView recyclerView;
    DatabaseReference companyRef;
    Button btnNotify;
    int day,month,year;
    long diff,expiration_days_ago;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_expired_debt, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setReverseLayout(false);
        linearLayoutManager.setStackFromEnd(false);
        recyclerView.setLayoutManager(linearLayoutManager);

        btnNotify = view.findViewById(R.id.btnNotify);

        post_key = getActivity().getIntent().getExtras().getString("post_key");
        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");

        Date date= new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        day = cal.get(Calendar.DAY_OF_MONTH);
        month = cal.get(Calendar.MONTH)+1;
        year = cal.get(Calendar.YEAR);

        companyRef.child(post_key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                company_social_reason = dataSnapshot.child("company_social_reason").getValue().toString();
                company_image = dataSnapshot.child("company_image").getValue().toString();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        showMyBills();

        btnNotify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                companyRef.child(post_key).child("My Bills").orderByChild("state").equalTo("expired").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {


                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            Map<String, Object> map = (Map<String, Object>) ds.getValue();

                            final Object expiration_day = map.get("expiration_day");
                            final Object expiration_month = map.get("expiration_month");
                            final Object expiration_year = map.get("expiration_year");

                            SimpleDateFormat myFormat = new SimpleDateFormat("dd MM yyyy");
                            String inputString1 = day+" "+month+" "+year;
                            String inputString2 = expiration_day+" "+expiration_month+" "+expiration_year;


                            try {
                                Date date1 = myFormat.parse(inputString1);
                                Date date2 = myFormat.parse(inputString2);
                                diff = date2.getTime() - date1.getTime();

                                expiration_days_ago = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
                                expiration_days_ago = Math.abs(expiration_days_ago);

                                if (expiration_days_ago >= 1 && expiration_days_ago <= 8)  {
                                    Object price = map.get("bill_amount");
                                    Object customer_id = map.get("customer_id");
                                    String customer_id_st = customer_id+"";
                                    final String price_st = price+"";


                                    companyRef.child(post_key).child("Customers").child(customer_id_st).addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {
                                            customer_name = dataSnapshot.child("customer_name").getValue().toString();
                                            customer_email = dataSnapshot.child("customer_email").getValue().toString();
                                            customer_phone = dataSnapshot.child("customer_phone").getValue().toString();

                                            sendEmail18();
                                        }

                                        private void sendEmail18() {
                                            String subject = customer_name+", Tienes una notificación de "+company_social_reason;
                                            String message = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional //EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" +
                                                    "\n" +
                                                    "<html xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\" xmlns:v=\"urn:schemas-microsoft-com:vml\">\n" +
                                                    "<head>\n" +
                                                    "\n" +
                                                    "<meta content=\"text/html; charset=utf-8\" http-equiv=\"Content-Type\"/>\n" +
                                                    "<meta content=\"width=device-width\" name=\"viewport\"/>\n" +
                                                    "<meta content=\"IE=edge\" http-equiv=\"X-UA-Compatible\"/>\n" +
                                                    "<title></title>\n" +
                                                    "<link href=\"https://fonts.googleapis.com/css?family=Montserrat\" rel=\"stylesheet\" type=\"text/css\"/>\n" +
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
                                                    "\t\t@media (max-width: 700px) {\n" +
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
                                                    "<body class=\"clean-body\" style=\"margin: 0; padding: 0; -webkit-text-size-adjust: 100%; background-color: #ffffff;\">\n" +
                                                    "<table bgcolor=\"#ffffff\" cellpadding=\"0\" cellspacing=\"0\" class=\"nl-container\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; min-width: 320px; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #ffffff; width: 100%;\" valign=\"top\" width=\"100%\">\n" +
                                                    "<tbody>\n" +
                                                    "<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                                                    "<td style=\"word-break: break-word; vertical-align: top;\" valign=\"top\">\n" +
                                                    "<div style=\"background-color:#000fff;\">\n" +
                                                    "<div class=\"block-grid\" style=\"min-width: 320px; max-width: 680px; overflow-wrap: break-word; word-wrap: break-word; word-break: break-word; Margin: 0 auto; background-color: transparent;\">\n" +
                                                    "<div style=\"border-collapse: collapse;display: table;width: 100%;background-color:transparent;\">\n" +
                                                    "<div class=\"col num12\" style=\"min-width: 320px; max-width: 680px; display: table-cell; vertical-align: top; width: 680px;\">\n" +
                                                    "<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                                                    "<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\">\n" +
                                                    "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\" width=\"100%\">\n" +
                                                    "<tbody>\n" +
                                                    "<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                                                    "<td class=\"divider_inner\" style=\"word-break: break-word; vertical-align: top; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%; padding-top: 5px; padding-right: 5px; padding-bottom: 5px; padding-left: 5px;\" valign=\"top\">\n" +
                                                    "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider_content\" height=\"25\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; border-top: 0px solid transparent; height: 25px; width: 100%;\" valign=\"top\" width=\"100%\">\n" +
                                                    "<tbody>\n" +
                                                    "<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                                                    "<td height=\"25\" style=\"word-break: break-word; vertical-align: top; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\"><span></span></td>\n" +
                                                    "</tr>\n" +
                                                    "</tbody>\n" +
                                                    "</table>\n" +
                                                    "</td>\n" +
                                                    "</tr>\n" +
                                                    "</tbody>\n" +
                                                    "</table>\n" +
                                                    "<div align=\"center\" class=\"img-container center fixedwidth\" style=\"padding-right: 0px;padding-left: 0px;\">\n" +
                                                    "<a href=\"http://www.example.com\" style=\"outline:none\" tabindex=\"-1\" target=\"_blank\"> <img align=\"center\" alt=\"Logo\" border=\"0\" class=\"center fixedwidth\" src=\"http://oliver.com.pe/wp-content/uploads/2020/12/Artboard-3.png\" style=\"text-decoration: none; -ms-interpolation-mode: bicubic; height: auto; border: 0; width: 100%; max-width: 170px; display: block;\" title=\"Logo\" width=\"170\"/></a>\n" +
                                                    "<div style=\"font-size:1px;line-height:15px\"> </div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<div style=\"background-color:#ffffff;\">\n" +
                                                    "<div class=\"block-grid\" style=\"min-width: 320px; max-width: 680px; overflow-wrap: break-word; word-wrap: break-word; word-break: break-word; Margin: 0 auto; background-color: transparent;\">\n" +
                                                    "<div style=\"border-collapse: collapse;display: table;width: 100%;background-color:transparent;\">\n" +
                                                    "<div class=\"col num12\" style=\"min-width: 320px; max-width: 680px; display: table-cell; vertical-align: top; width: 680px;\">\n" +
                                                    "<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                                                    "<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\">\n" +
                                                    "<div align=\"center\" class=\"img-container center fixedwidth\" style=\"padding-right: 0px;padding-left: 0px;\">\n" +
                                                    "<a href=\"www.example.com\" style=\"outline:none\" tabindex=\"-1\" target=\"_blank\"> <img align=\"center\" alt=\"Cool Burger Walking\" border=\"0\" class=\"center fixedwidth\" src=\"http://oliver.com.pe/wp-content/uploads/2020/12/Wallet.gif\" style=\"text-decoration: none; -ms-interpolation-mode: bicubic; height: auto; border: 0; width: 100%; max-width: 306px; display: block;\" title=\"Cool Burger Walking\" width=\"306\"/></a>\n" +
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
                                                    "<div style=\"color:#000fff;font-family:Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif;line-height:1.2;padding-top:10px;padding-right:10px;padding-bottom:15px;padding-left:10px;\">\n" +
                                                    "<div style=\"font-size: 14px; line-height: 1.2; color: #000fff; font-family: Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif; mso-line-height-alt: 17px;\">\n" +
                                                    "<p style=\"font-size: 14px; line-height: 1.2; word-break: break-word; text-align: center; mso-line-height-alt: 17px; margin: 0;\"><strong><span style=\"font-size: 38px;\">NOTIFICACIÓN DE COBRANZA</span></strong></p>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<div style=\"color:#000fff;font-family:'Montserrat', 'Trebuchet MS', 'Lucida Grande', 'Lucida Sans Unicode', 'Lucida Sans', Tahoma, sans-serif;line-height:1.8;padding-top:10px;padding-right:60px;padding-bottom:20px;padding-left:60px;\">\n" +
                                                    "<div style=\"line-height: 1.8; font-size: 12px; font-family: 'Montserrat', 'Trebuchet MS', 'Lucida Grande', 'Lucida Sans Unicode', 'Lucida Sans', Tahoma, sans-serif; color: #000fff; mso-line-height-alt: 22px;\">\n" +
                                                    "<p style=\"font-size: 22px; line-height: 1.8; text-align: center; word-break: break-word; font-family: Montserrat, 'Trebuchet MS', 'Lucida Grande', 'Lucida Sans Unicode', 'Lucida Sans', Tahoma, sans-serif; mso-line-height-alt: 40px; margin: 0;\"><span style=\"font-size: 22px;\">Hola "+customer_name+", te recordamos que tienes un pago pendiente VENCIDO. Porfavor te animamos a que puedas acercarte a regularizarlo para que sigamos atendiéndote y dándote el mejor servicio como siempre. ¡Te esperamos!<br/></span></p>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<div align=\"center\" class=\"button-container\" style=\"padding-top:10px;padding-right:10px;padding-bottom:10px;padding-left:10px;\">\n" +
                                                    "<a href=\"\" style=\"-webkit-text-size-adjust: none; text-decoration: none; display: inline-block; color: #ffffff; background-color: #0000ff; border-radius: 4px; -webkit-border-radius: 4px; -moz-border-radius: 4px; width: auto; width: auto; border-top: 0px solid #8a3b8f; border-right: 0px solid #8a3b8f; border-bottom: 0px solid #8a3b8f; border-left: 0px solid #8a3b8f; padding-top: 5px; padding-bottom: 5px; font-family: Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif; text-align: center; mso-border-alt: none; word-break: keep-all;\" target=\"_blank\"><span style=\"padding-left:40px;padding-right:40px;font-size:16px;display:inline-block;\"><span style=\"font-size: 16px; line-height: 2; word-break: break-word; mso-line-height-alt: 32px;\"><strong>VENCIDO HACE "+expiration_days_ago+" DÍAS"+"</strong></span></span></a>\n" +
                                                    "</div>\n" +
                                                    "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\" width=\"100%\">\n" +
                                                    "<tbody>\n" +
                                                    "<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                                                    "<td class=\"divider_inner\" style=\"word-break: break-word; vertical-align: top; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%; padding-top: 10px; padding-right: 10px; padding-bottom: 10px; padding-left: 10px;\" valign=\"top\">\n" +
                                                    "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider_content\" height=\"25\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; border-top: 0px solid transparent; height: 25px; width: 100%;\" valign=\"top\" width=\"100%\">\n" +
                                                    "<tbody>\n" +
                                                    "<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                                                    "<td height=\"25\" style=\"word-break: break-word; vertical-align: top; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\"><span></span></td>\n" +
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
                                                    "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider_content\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; border-top: 1px solid #BBBBBB; width: 100%;\" valign=\"top\" width=\"100%\">\n" +
                                                    "<tbody>\n" +
                                                    "<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                                                    "<td style=\"word-break: break-word; vertical-align: top; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\"><span></span></td>\n" +
                                                    "</tr>\n" +
                                                    "</tbody>\n" +
                                                    "</table>\n" +
                                                    "</td>\n" +
                                                    "</tr>\n" +
                                                    "</tbody>\n" +
                                                    "</table>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<div style=\"background-color:transparent;\">\n" +
                                                    "<div class=\"block-grid\" style=\"min-width: 320px; max-width: 680px; overflow-wrap: break-word; word-wrap: break-word; word-break: break-word; Margin: 0 auto; background-color: transparent;\">\n" +
                                                    "<div style=\"border-collapse: collapse;display: table;width: 100%;background-color:transparent;\">\n" +
                                                    "<div class=\"col num12\" style=\"min-width: 320px; max-width: 680px; display: table-cell; vertical-align: top; width: 680px;\">\n" +
                                                    "<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                                                    "<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\">\n" +
                                                    "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\" width=\"100%\">\n" +
                                                    "<tbody>\n" +
                                                    "<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                                                    "<td class=\"divider_inner\" style=\"word-break: break-word; vertical-align: top; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%; padding-top: 10px; padding-right: 10px; padding-bottom: 10px; padding-left: 10px;\" valign=\"top\">\n" +
                                                    "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider_content\" height=\"5\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; border-top: 0px solid transparent; height: 5px; width: 100%;\" valign=\"top\" width=\"100%\">\n" +
                                                    "<tbody>\n" +
                                                    "<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                                                    "<td height=\"5\" style=\"word-break: break-word; vertical-align: top; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\"><span></span></td>\n" +
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
                                                    "<div style=\"background-color:transparent;\">\n" +
                                                    "<div class=\"block-grid two-up\" style=\"min-width: 320px; max-width: 680px; overflow-wrap: break-word; word-wrap: break-word; word-break: break-word; Margin: 0 auto; background-color: transparent;\">\n" +
                                                    "<div style=\"border-collapse: collapse;display: table;width: 100%;background-color:transparent;\">\n" +
                                                    "<div class=\"col num6\" style=\"display: table-cell; vertical-align: top; max-width: 320px; min-width: 336px; width: 340px;\">\n" +
                                                    "<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                                                    "<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\">\n" +
                                                    "<div style=\"color:#000000;font-family:'Montserrat', 'Trebuchet MS', 'Lucida Grande', 'Lucida Sans Unicode', 'Lucida Sans', Tahoma, sans-serif;line-height:1.8;padding-top:10px;padding-right:30px;padding-bottom:5px;padding-left:30px;\">\n" +
                                                    "<div style=\"line-height: 1.8; font-size: 12px; font-family: 'Montserrat', 'Trebuchet MS', 'Lucida Grande', 'Lucida Sans Unicode', 'Lucida Sans', Tahoma, sans-serif; color: #000000; mso-line-height-alt: 22px;\">\n" +
                                                    "<p style=\"font-size: 18px; line-height: 1.8; word-break: break-word; text-align: center; font-family: Montserrat, 'Trebuchet MS', 'Lucida Grande', 'Lucida Sans Unicode', 'Lucida Sans', Tahoma, sans-serif; mso-line-height-alt: 32px; margin: 0;\"><span style=\"font-size: 18px;\"><strong><span style=\"\">"+company_social_reason+"</span></strong></span></p>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<div align=\"center\" class=\"img-container center fixedwidth\" style=\"padding-right: 0px;padding-left: 0px;\">\n" +
                                                    "<img align=\"center\" alt=\"Alternate text\" border=\"0\" class=\"center fixedwidth\" src=\""+company_image+"\" style=\"text-decoration: none; -ms-interpolation-mode: bicubic; height: auto; border: 0; width: 100%; max-width: 136px; display: block;\" title=\"Alternate text\" width=\"136\"/>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<div class=\"col num6\" style=\"display: table-cell; vertical-align: top; max-width: 320px; min-width: 336px; width: 340px;\">\n" +
                                                    "<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                                                    "<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\">\n" +
                                                    "<div style=\"color:#000000;font-family:'Montserrat', 'Trebuchet MS', 'Lucida Grande', 'Lucida Sans Unicode', 'Lucida Sans', Tahoma, sans-serif;line-height:1.8;padding-top:10px;padding-right:30px;padding-bottom:5px;padding-left:30px;\">\n" +
                                                    "<div style=\"line-height: 1.8; font-size: 12px; font-family: 'Montserrat', 'Trebuchet MS', 'Lucida Grande', 'Lucida Sans Unicode', 'Lucida Sans', Tahoma, sans-serif; color: #000000; mso-line-height-alt: 22px;\">\n" +
                                                    "<p style=\"font-size: 14px; line-height: 1.8; word-break: break-word; text-align: left; font-family: Montserrat, 'Trebuchet MS', 'Lucida Grande', 'Lucida Sans Unicode', 'Lucida Sans', Tahoma, sans-serif; mso-line-height-alt: 25px; margin: 0;\"><strong><span style=\"font-size: 16px;\">Pago pendiente:</span></strong></p>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<div style=\"color:#000fff;font-family:'Montserrat', 'Trebuchet MS', 'Lucida Grande', 'Lucida Sans Unicode', 'Lucida Sans', Tahoma, sans-serif;line-height:1.8;padding-top:0px;padding-right:30px;padding-bottom:10px;padding-left:30px;\">\n" +
                                                    "<div style=\"line-height: 1.8; font-size: 12px; color: #000fff; font-family: 'Montserrat', 'Trebuchet MS', 'Lucida Grande', 'Lucida Sans Unicode', 'Lucida Sans', Tahoma, sans-serif; mso-line-height-alt: 22px;\">\n" +
                                                    "<p style=\"font-size: 26px; line-height: 1.8; word-break: break-word; text-align: left; mso-line-height-alt: 47px; margin: 0;\"><span style=\"font-size: 26px;\"><strong>S/ "+price_st+"</strong></span></p>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<div style=\"color:#000fff;font-family:'Montserrat', 'Trebuchet MS', 'Lucida Grande', 'Lucida Sans Unicode', 'Lucida Sans', Tahoma, sans-serif;line-height:1.8;padding-top:10px;padding-right:30px;padding-bottom:10px;padding-left:30px;\">\n" +
                                                    "<div style=\"line-height: 1.8; font-size: 12px; font-family: 'Montserrat', 'Trebuchet MS', 'Lucida Grande', 'Lucida Sans Unicode', 'Lucida Sans', Tahoma, sans-serif; color: #000fff; mso-line-height-alt: 22px;\">\n" +
                                                    "<p style=\"font-size: 14px; line-height: 1.8; word-break: break-word; text-align: left; font-family: Montserrat, 'Trebuchet MS', 'Lucida Grande', 'Lucida Sans Unicode', 'Lucida Sans', Tahoma, sans-serif; mso-line-height-alt: 25px; margin: 0;\"><strong><span style=\"font-size: 16px;\">Fecha de vencimiento</span></strong></p>\n" +
                                                    "<p style=\"font-size: 14px; line-height: 1.8; word-break: break-word; text-align: left; font-family: Montserrat, 'Trebuchet MS', 'Lucida Grande', 'Lucida Sans Unicode', 'Lucida Sans', Tahoma, sans-serif; mso-line-height-alt: 25px; margin: 0;\"><strong><span style=\"font-size: 16px;\">"+expiration_day+"/"+expiration_month+"/"+expiration_year+"</span></strong></p>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "\n" +
                                                    "</div>\n" +
                                                    "\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<div style=\"background-color:transparent;\">\n" +
                                                    "<div class=\"block-grid\" style=\"min-width: 320px; max-width: 680px; overflow-wrap: break-word; word-wrap: break-word; word-break: break-word; Margin: 0 auto; background-color: transparent;\">\n" +
                                                    "<div style=\"border-collapse: collapse;display: table;width: 100%;background-color:transparent;\">\n" +
                                                    "<div class=\"col num12\" style=\"min-width: 320px; max-width: 680px; display: table-cell; vertical-align: top; width: 680px;\">\n" +
                                                    "<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                                                    "<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\">\n" +
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
                                                    "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\" width=\"100%\">\n" +
                                                    "<tbody>\n" +
                                                    "<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                                                    "<td class=\"divider_inner\" style=\"word-break: break-word; vertical-align: top; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%; padding-top: 10px; padding-right: 10px; padding-bottom: 10px; padding-left: 10px;\" valign=\"top\">\n" +
                                                    "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider_content\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; border-top: 1px solid #BBBBBB; width: 100%;\" valign=\"top\" width=\"100%\">\n" +
                                                    "<tbody>\n" +
                                                    "<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                                                    "<td style=\"word-break: break-word; vertical-align: top; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\"><span></span></td>\n" +
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
                                                    "<!--[if (!mso)&(!IE)]><!-->\n" +
                                                    "</div>\n" +
                                                    "<!--<![endif]-->\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<div style=\"background-color:transparent;\">\n" +
                                                    "<div class=\"block-grid three-up\" style=\"min-width: 320px; max-width: 680px; overflow-wrap: break-word; word-wrap: break-word; word-break: break-word; Margin: 0 auto; background-color: #ffffff;\">\n" +
                                                    "<div style=\"border-collapse: collapse;display: table;width: 100%;background-color:#ffffff;\">\n" +
                                                    "<div class=\"col num3\" style=\"display: table-cell; vertical-align: top; max-width: 320px; min-width: 168px; width: 170px;\">\n" +
                                                    "<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                                                    "<!--[if (!mso)&(!IE)]><!-->\n" +
                                                    "<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 10px;\">\n" +
                                                    "<!--<![endif]-->\n" +
                                                    "<div class=\"mobile_hide\">\n" +
                                                    "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\" width=\"100%\">\n" +
                                                    "<tbody>\n" +
                                                    "<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                                                    "<td class=\"divider_inner\" style=\"word-break: break-word; vertical-align: top; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%; padding-top: 10px; padding-right: 10px; padding-bottom: 10px; padding-left: 10px;\" valign=\"top\">\n" +
                                                    "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider_content\" height=\"5\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; border-top: 0px solid transparent; height: 5px; width: 100%;\" valign=\"top\" width=\"100%\">\n" +
                                                    "<tbody>\n" +
                                                    "<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                                                    "<td height=\"5\" style=\"word-break: break-word; vertical-align: top; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\"><span></span></td>\n" +
                                                    "</tr>\n" +
                                                    "</tbody>\n" +
                                                    "</table>\n" +
                                                    "</td>\n" +
                                                    "</tr>\n" +
                                                    "</tbody>\n" +
                                                    "</table>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<div class=\"col num6\" style=\"display: table-cell; vertical-align: top; max-width: 320px; min-width: 336px; width: 340px;\">\n" +
                                                    "<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                                                    "<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\">\n" +
                                                    "\n" +
                                                    "<div style=\"color:#232323;font-family:Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif;line-height:1.2;padding-top:10px;padding-right:10px;padding-bottom:0px;padding-left:35px;\">\n" +
                                                    "<div style=\"line-height: 1.2; font-size: 12px; color: #232323; font-family: Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif; mso-line-height-alt: 14px;\">\n" +
                                                    "<p style=\"font-size: 14px; line-height: 1.2; word-break: break-word; mso-line-height-alt: 17px; margin: 0;\"><span style=\"font-size: 14px;\">Subtotal:</span></p>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<!--<![endif]-->\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "\n" +
                                                    "<div class=\"col num3\" style=\"display: table-cell; vertical-align: top; max-width: 320px; min-width: 168px; width: 170px;\">\n" +
                                                    "<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                                                    "<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\">\n" +
                                                    "<div style=\"color:#555555;font-family:Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif;line-height:1.2;padding-top:10px;padding-right:10px;padding-bottom:0px;padding-left:30px;\">\n" +
                                                    "<div style=\"line-height: 1.2; font-size: 12px; color: #555555; font-family: Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif; mso-line-height-alt: 14px;\">\n" +
                                                    "<p style=\"font-size: 14px; line-height: 1.2; word-break: break-word; mso-line-height-alt: 17px; margin: 0;\">S/ "+price_st+"</p>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<!--<![endif]-->\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<div style=\"background-color:transparent;\">\n" +
                                                    "<div class=\"block-grid three-up\" style=\"min-width: 320px; max-width: 680px; overflow-wrap: break-word; word-wrap: break-word; word-break: break-word; Margin: 0 auto; background-color: #ffffff;\">\n" +
                                                    "<div style=\"border-collapse: collapse;display: table;width: 100%;background-color:#ffffff;\">\n" +
                                                    "<div class=\"col num3\" style=\"display: table-cell; vertical-align: top; max-width: 320px; min-width: 168px; width: 170px;\">\n" +
                                                    "<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                                                    "<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 10px;\">\n" +
                                                    "<div class=\"mobile_hide\">\n" +
                                                    "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\" width=\"100%\">\n" +
                                                    "<tbody>\n" +
                                                    "<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                                                    "<td class=\"divider_inner\" style=\"word-break: break-word; vertical-align: top; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%; padding-top: 10px; padding-right: 10px; padding-bottom: 10px; padding-left: 10px;\" valign=\"top\">\n" +
                                                    "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider_content\" height=\"5\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; border-top: 0px solid transparent; height: 5px; width: 100%;\" valign=\"top\" width=\"100%\">\n" +
                                                    "<tbody>\n" +
                                                    "<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                                                    "<td height=\"5\" style=\"word-break: break-word; vertical-align: top; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\"><span></span></td>\n" +
                                                    "</tr>\n" +
                                                    "</tbody>\n" +
                                                    "</table>\n" +
                                                    "</td>\n" +
                                                    "</tr>\n" +
                                                    "</tbody>\n" +
                                                    "</table>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<!--<![endif]-->\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<div class=\"col num6\" style=\"display: table-cell; vertical-align: top; max-width: 320px; min-width: 336px; width: 340px;\">\n" +
                                                    "<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                                                    "<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\">\n" +
                                                    "<div style=\"color:#232323;font-family:Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif;line-height:1.2;padding-top:10px;padding-right:10px;padding-bottom:0px;padding-left:35px;\">\n" +
                                                    "<div style=\"line-height: 1.2; font-size: 12px; color: #232323; font-family: Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif; mso-line-height-alt: 14px;\">\n" +
                                                    "<p style=\"font-size: 14px; line-height: 1.2; word-break: break-word; mso-line-height-alt: 17px; margin: 0;\"><span style=\"font-size: 14px;\">Mora</span></p>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<div class=\"col num3\" style=\"display: table-cell; vertical-align: top; max-width: 320px; min-width: 168px; width: 170px;\">\n" +
                                                    "<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                                                    "<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\">\n" +
                                                    "<div style=\"color:#555555;font-family:Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif;line-height:1.2;padding-top:10px;padding-right:10px;padding-bottom:0px;padding-left:30px;\">\n" +
                                                    "<div style=\"line-height: 1.2; font-size: 12px; color: #555555; font-family: Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif; mso-line-height-alt: 14px;\">\n" +
                                                    "<p style=\"font-size: 14px; line-height: 1.2; word-break: break-word; mso-line-height-alt: 17px; margin: 0;\">$ 0.00</p>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<!--<![endif]-->\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<div style=\"background-color:transparent;\">\n" +
                                                    "<div class=\"block-grid three-up\" style=\"min-width: 320px; max-width: 680px; overflow-wrap: break-word; word-wrap: break-word; word-break: break-word; Margin: 0 auto; background-color: #ffffff;\">\n" +
                                                    "<div style=\"border-collapse: collapse;display: table;width: 100%;background-color:#ffffff;\">\n" +
                                                    "<div class=\"col num3\" style=\"display: table-cell; vertical-align: top; max-width: 320px; min-width: 168px; width: 170px;\">\n" +
                                                    "<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                                                    "<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 10px;\">\n" +
                                                    "<div class=\"mobile_hide\">\n" +
                                                    "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\" width=\"100%\">\n" +
                                                    "<tbody>\n" +
                                                    "<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                                                    "<td class=\"divider_inner\" style=\"word-break: break-word; vertical-align: top; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%; padding-top: 10px; padding-right: 10px; padding-bottom: 10px; padding-left: 10px;\" valign=\"top\">\n" +
                                                    "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider_content\" height=\"5\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; border-top: 0px solid transparent; height: 5px; width: 100%;\" valign=\"top\" width=\"100%\">\n" +
                                                    "<tbody>\n" +
                                                    "<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                                                    "<td height=\"5\" style=\"word-break: break-word; vertical-align: top; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\"><span></span></td>\n" +
                                                    "</tr>\n" +
                                                    "</tbody>\n" +
                                                    "</table>\n" +
                                                    "</td>\n" +
                                                    "</tr>\n" +
                                                    "</tbody>\n" +
                                                    "</table>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<div class=\"col num6\" style=\"display: table-cell; vertical-align: top; max-width: 320px; min-width: 336px; width: 340px;\">\n" +
                                                    "<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                                                    "<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\">\n" +
                                                    "<div style=\"color:#232323;font-family:Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif;line-height:1.2;padding-top:10px;padding-right:10px;padding-bottom:0px;padding-left:35px;\">\n" +
                                                    "<div style=\"line-height: 1.2; font-size: 12px; color: #232323; font-family: Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif; mso-line-height-alt: 14px;\">\n" +
                                                    "<p style=\"font-size: 18px; line-height: 1.2; word-break: break-word; mso-line-height-alt: 22px; margin: 0;\"><span style=\"font-size: 18px;\">Pago Total</span></p>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<!--<![endif]-->\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<div class=\"col num3\" style=\"display: table-cell; vertical-align: top; max-width: 320px; min-width: 168px; width: 170px;\">\n" +
                                                    "<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                                                    "<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\">\n" +
                                                    "<div style=\"color:#000fff;font-family:Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif;line-height:1.2;padding-top:10px;padding-right:10px;padding-bottom:0px;padding-left:30px;\">\n" +
                                                    "<div style=\"line-height: 1.2; font-size: 12px; color: #000fff; font-family: Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif; mso-line-height-alt: 14px;\">\n" +
                                                    "<p style=\"font-size: 18px; line-height: 1.2; word-break: break-word; mso-line-height-alt: 22px; margin: 0;\"><span style=\"font-size: 18px;\">S/ "+price_st+"</span></p>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<!--<![endif]-->\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<div style=\"background-color:transparent;\">\n" +
                                                    "<div class=\"block-grid\" style=\"min-width: 320px; max-width: 680px; overflow-wrap: break-word; word-wrap: break-word; word-break: break-word; Margin: 0 auto; background-color: transparent;\">\n" +
                                                    "<div style=\"border-collapse: collapse;display: table;width: 100%;background-color:transparent;\">\n" +
                                                    "<div class=\"col num12\" style=\"min-width: 320px; max-width: 680px; display: table-cell; vertical-align: top; width: 680px;\">\n" +
                                                    "<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                                                    "<!--[if (!mso)&(!IE)]><!-->\n" +
                                                    "<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\">\n" +
                                                    "<!--<![endif]-->\n" +
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
                                                    "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\" width=\"100%\">\n" +
                                                    "<tbody>\n" +
                                                    "<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                                                    "<td class=\"divider_inner\" style=\"word-break: break-word; vertical-align: top; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%; padding-top: 10px; padding-right: 10px; padding-bottom: 10px; padding-left: 10px;\" valign=\"top\">\n" +
                                                    "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider_content\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; border-top: 1px solid #BBBBBB; width: 100%;\" valign=\"top\" width=\"100%\">\n" +
                                                    "<tbody>\n" +
                                                    "<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                                                    "<td style=\"word-break: break-word; vertical-align: top; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\"><span></span></td>\n" +
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
                                                    "</div>\n" +
                                                    "<!--<![endif]-->\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<div style=\"background-color:#000fff;\">\n" +
                                                    "<div class=\"block-grid\" style=\"min-width: 320px; max-width: 680px; overflow-wrap: break-word; word-wrap: break-word; word-break: break-word; Margin: 0 auto; background-color: transparent;\">\n" +
                                                    "<div style=\"border-collapse: collapse;display: table;width: 100%;background-color:transparent;\">\n" +
                                                    "<div class=\"col num12\" style=\"min-width: 320px; max-width: 680px; display: table-cell; vertical-align: top; width: 680px;\">\n" +
                                                    "<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                                                    "<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\">\n" +
                                                    "<!--<![endif]-->\n" +
                                                    "<div class=\"mobile_hide\">\n" +
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
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<div style=\"background-color:#02025c;\">\n" +
                                                    "<div class=\"block-grid\" style=\"min-width: 320px; max-width: 680px; overflow-wrap: break-word; word-wrap: break-word; word-break: break-word; Margin: 0 auto; background-color: transparent;\">\n" +
                                                    "<div style=\"border-collapse: collapse;display: table;width: 100%;background-color:transparent;\">\n" +
                                                    "<div class=\"col num12\" style=\"min-width: 320px; max-width: 680px; display: table-cell; vertical-align: top; width: 680px;\">\n" +
                                                    "<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                                                    "<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\">\n" +
                                                    "<!--<![endif]-->\n" +
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
                                                    "<div style=\"color:#ffffff;font-family:Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif;line-height:1.5;padding-top:10px;padding-right:10px;padding-bottom:5px;padding-left:10px;\">\n" +
                                                    "<div style=\"line-height: 1.5; font-size: 12px; color: #ffffff; font-family: Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif; mso-line-height-alt: 18px;\">\n" +
                                                    "<p style=\"text-align: center; line-height: 1.5; word-break: break-word; mso-line-height-alt: 18px; margin: 0;\"><strong>This messasge was sent to <a href=\"mailto:email@example.com\" style=\"color: #d9f5fa;\" title=\"email@example.com\">email@example.com</a></strong></p>\n" +
                                                    "<p style=\"text-align: center; line-height: 1.5; word-break: break-word; mso-line-height-alt: 18px; margin: 0;\"><strong>If you would like to change your email, please <a href=\"http://www.example.com/\" rel=\"noopener\" style=\"color: #d9f5fa;\" target=\"_blank\">click here</a></strong></p>\n" +
                                                    "<p style=\"text-align: center; line-height: 1.5; word-break: break-word; mso-line-height-alt: 18px; margin: 0;\"><strong>If you no longer wish to receive e-mails from Company Name, <a href=\"http://www.example.com/\" rel=\"noopener\" style=\"color: #d9f5fa;\" target=\"_blank\">click here</a></strong></p>\n" +
                                                    "<p style=\"text-align: center; line-height: 1.5; word-break: break-word; mso-line-height-alt: 18px; margin: 0;\"><br/><strong>2020 © All Rights Reserved</strong></p>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<div style=\"background-color:transparent;\">\n" +
                                                    "<div class=\"block-grid two-up no-stack\" style=\"min-width: 320px; max-width: 680px; overflow-wrap: break-word; word-wrap: break-word; word-break: break-word; Margin: 0 auto; background-color: transparent;\">\n" +
                                                    "<div style=\"border-collapse: collapse;display: table;width: 100%;background-color:transparent;\">\n" +
                                                    "<div class=\"col num6\" style=\"display: table-cell; vertical-align: top; max-width: 320px; min-width: 336px; width: 340px;\">\n" +
                                                    "<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                                                    "\n" +
                                                    "<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:10px; padding-bottom:10px; padding-right: 0px; padding-left: 0px;\">\n" +
                                                    "\n" +
                                                    "\n" +
                                                    "</div>\n" +
                                                    "<!--<![endif]-->\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</td>\n" +
                                                    "</tr>\n" +
                                                    "</tbody>\n" +
                                                    "</body>\n" +
                                                    "</html>";
                                            JavaMailAPI javaMailAPI = new JavaMailAPI(getActivity(), customer_email,subject,message);
                                            javaMailAPI.execute();
                                        }


                                        @Override
                                        public void onCancelled(DatabaseError databaseError) {

                                        }
                                    });


                                }
                                if (expiration_days_ago >= 9 && expiration_days_ago <= 15)  {
                                    Object price = map.get("bill_amount");
                                    Object customer_id = map.get("customer_id");
                                    String customer_id_st = customer_id+"";
                                    final String price_st = price+"";

                                    companyRef.child(post_key).child("Customers").child(customer_id_st).addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {
                                            customer_name = dataSnapshot.child("customer_name").getValue().toString();
                                            customer_email = dataSnapshot.child("customer_email").getValue().toString();
                                            customer_phone = dataSnapshot.child("customer_phone").getValue().toString();

                                            sendEmail18();
                                        }

                                        private void sendEmail18() {
                                            String subject = customer_name+", Tienes una notificación de "+company_social_reason;
                                            String message = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional //EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" +
                                                    "\n" +
                                                    "<html xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\" xmlns:v=\"urn:schemas-microsoft-com:vml\">\n" +
                                                    "<head>\n" +
                                                    "\n" +
                                                    "<meta content=\"text/html; charset=utf-8\" http-equiv=\"Content-Type\"/>\n" +
                                                    "<meta content=\"width=device-width\" name=\"viewport\"/>\n" +
                                                    "<meta content=\"IE=edge\" http-equiv=\"X-UA-Compatible\"/>\n" +
                                                    "<title></title>\n" +
                                                    "<link href=\"https://fonts.googleapis.com/css?family=Montserrat\" rel=\"stylesheet\" type=\"text/css\"/>\n" +
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
                                                    "\t\t@media (max-width: 700px) {\n" +
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
                                                    "<body class=\"clean-body\" style=\"margin: 0; padding: 0; -webkit-text-size-adjust: 100%; background-color: #ffffff;\">\n" +
                                                    "<table bgcolor=\"#ffffff\" cellpadding=\"0\" cellspacing=\"0\" class=\"nl-container\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; min-width: 320px; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #ffffff; width: 100%;\" valign=\"top\" width=\"100%\">\n" +
                                                    "<tbody>\n" +
                                                    "<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                                                    "<td style=\"word-break: break-word; vertical-align: top;\" valign=\"top\">\n" +
                                                    "<div style=\"background-color:#000fff;\">\n" +
                                                    "<div class=\"block-grid\" style=\"min-width: 320px; max-width: 680px; overflow-wrap: break-word; word-wrap: break-word; word-break: break-word; Margin: 0 auto; background-color: transparent;\">\n" +
                                                    "<div style=\"border-collapse: collapse;display: table;width: 100%;background-color:transparent;\">\n" +
                                                    "<div class=\"col num12\" style=\"min-width: 320px; max-width: 680px; display: table-cell; vertical-align: top; width: 680px;\">\n" +
                                                    "<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                                                    "<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\">\n" +
                                                    "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\" width=\"100%\">\n" +
                                                    "<tbody>\n" +
                                                    "<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                                                    "<td class=\"divider_inner\" style=\"word-break: break-word; vertical-align: top; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%; padding-top: 5px; padding-right: 5px; padding-bottom: 5px; padding-left: 5px;\" valign=\"top\">\n" +
                                                    "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider_content\" height=\"25\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; border-top: 0px solid transparent; height: 25px; width: 100%;\" valign=\"top\" width=\"100%\">\n" +
                                                    "<tbody>\n" +
                                                    "<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                                                    "<td height=\"25\" style=\"word-break: break-word; vertical-align: top; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\"><span></span></td>\n" +
                                                    "</tr>\n" +
                                                    "</tbody>\n" +
                                                    "</table>\n" +
                                                    "</td>\n" +
                                                    "</tr>\n" +
                                                    "</tbody>\n" +
                                                    "</table>\n" +
                                                    "<div align=\"center\" class=\"img-container center fixedwidth\" style=\"padding-right: 0px;padding-left: 0px;\">\n" +
                                                    "<a href=\"http://www.example.com\" style=\"outline:none\" tabindex=\"-1\" target=\"_blank\"> <img align=\"center\" alt=\"Logo\" border=\"0\" class=\"center fixedwidth\" src=\"http://oliver.com.pe/wp-content/uploads/2020/12/Artboard-3.png\" style=\"text-decoration: none; -ms-interpolation-mode: bicubic; height: auto; border: 0; width: 100%; max-width: 170px; display: block;\" title=\"Logo\" width=\"170\"/></a>\n" +
                                                    "<div style=\"font-size:1px;line-height:15px\"> </div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<div style=\"background-color:#ffffff;\">\n" +
                                                    "<div class=\"block-grid\" style=\"min-width: 320px; max-width: 680px; overflow-wrap: break-word; word-wrap: break-word; word-break: break-word; Margin: 0 auto; background-color: transparent;\">\n" +
                                                    "<div style=\"border-collapse: collapse;display: table;width: 100%;background-color:transparent;\">\n" +
                                                    "<div class=\"col num12\" style=\"min-width: 320px; max-width: 680px; display: table-cell; vertical-align: top; width: 680px;\">\n" +
                                                    "<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                                                    "<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\">\n" +
                                                    "<div align=\"center\" class=\"img-container center fixedwidth\" style=\"padding-right: 0px;padding-left: 0px;\">\n" +
                                                    "<a href=\"www.example.com\" style=\"outline:none\" tabindex=\"-1\" target=\"_blank\"> <img align=\"center\" alt=\"Cool Burger Walking\" border=\"0\" class=\"center fixedwidth\" src=\"http://oliver.com.pe/wp-content/uploads/2020/12/Wallet.gif\" style=\"text-decoration: none; -ms-interpolation-mode: bicubic; height: auto; border: 0; width: 100%; max-width: 306px; display: block;\" title=\"Cool Burger Walking\" width=\"306\"/></a>\n" +
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
                                                    "<div style=\"color:#000fff;font-family:Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif;line-height:1.2;padding-top:10px;padding-right:10px;padding-bottom:15px;padding-left:10px;\">\n" +
                                                    "<div style=\"font-size: 14px; line-height: 1.2; color: #000fff; font-family: Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif; mso-line-height-alt: 17px;\">\n" +
                                                    "<p style=\"font-size: 14px; line-height: 1.2; word-break: break-word; text-align: center; mso-line-height-alt: 17px; margin: 0;\"><strong><span style=\"font-size: 38px;\">NOTIFICACIÓN DE COBRANZA</span></strong></p>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<div style=\"color:#000fff;font-family:'Montserrat', 'Trebuchet MS', 'Lucida Grande', 'Lucida Sans Unicode', 'Lucida Sans', Tahoma, sans-serif;line-height:1.8;padding-top:10px;padding-right:60px;padding-bottom:20px;padding-left:60px;\">\n" +
                                                    "<div style=\"line-height: 1.8; font-size: 12px; font-family: 'Montserrat', 'Trebuchet MS', 'Lucida Grande', 'Lucida Sans Unicode', 'Lucida Sans', Tahoma, sans-serif; color: #000fff; mso-line-height-alt: 22px;\">\n" +
                                                    "<p style=\"font-size: 22px; line-height: 1.8; text-align: center; word-break: break-word; font-family: Montserrat, 'Trebuchet MS', 'Lucida Grande', 'Lucida Sans Unicode', 'Lucida Sans', Tahoma, sans-serif; mso-line-height-alt: 40px; margin: 0;\"><span style=\"font-size: 22px;\">Hola "+customer_name+", te recordamos que tienes un pago pendiente VENCIDO. Porfavor te animamos a que puedas acercarte a regularizarlo para que sigamos atendiéndote y dándote el mejor servicio como siempre. ¡Te esperamos!<br/></span></p>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<div align=\"center\" class=\"button-container\" style=\"padding-top:10px;padding-right:10px;padding-bottom:10px;padding-left:10px;\">\n" +
                                                    "<a href=\"\" style=\"-webkit-text-size-adjust: none; text-decoration: none; display: inline-block; color: #ffffff; background-color: #0000ff; border-radius: 4px; -webkit-border-radius: 4px; -moz-border-radius: 4px; width: auto; width: auto; border-top: 0px solid #8a3b8f; border-right: 0px solid #8a3b8f; border-bottom: 0px solid #8a3b8f; border-left: 0px solid #8a3b8f; padding-top: 5px; padding-bottom: 5px; font-family: Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif; text-align: center; mso-border-alt: none; word-break: keep-all;\" target=\"_blank\"><span style=\"padding-left:40px;padding-right:40px;font-size:16px;display:inline-block;\"><span style=\"font-size: 16px; line-height: 2; word-break: break-word; mso-line-height-alt: 32px;\"><strong>VENCIDO HACE "+expiration_days_ago+" DÍAS"+"</strong></span></span></a>\n" +
                                                    "</div>\n" +
                                                    "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\" width=\"100%\">\n" +
                                                    "<tbody>\n" +
                                                    "<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                                                    "<td class=\"divider_inner\" style=\"word-break: break-word; vertical-align: top; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%; padding-top: 10px; padding-right: 10px; padding-bottom: 10px; padding-left: 10px;\" valign=\"top\">\n" +
                                                    "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider_content\" height=\"25\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; border-top: 0px solid transparent; height: 25px; width: 100%;\" valign=\"top\" width=\"100%\">\n" +
                                                    "<tbody>\n" +
                                                    "<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                                                    "<td height=\"25\" style=\"word-break: break-word; vertical-align: top; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\"><span></span></td>\n" +
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
                                                    "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider_content\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; border-top: 1px solid #BBBBBB; width: 100%;\" valign=\"top\" width=\"100%\">\n" +
                                                    "<tbody>\n" +
                                                    "<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                                                    "<td style=\"word-break: break-word; vertical-align: top; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\"><span></span></td>\n" +
                                                    "</tr>\n" +
                                                    "</tbody>\n" +
                                                    "</table>\n" +
                                                    "</td>\n" +
                                                    "</tr>\n" +
                                                    "</tbody>\n" +
                                                    "</table>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<div style=\"background-color:transparent;\">\n" +
                                                    "<div class=\"block-grid\" style=\"min-width: 320px; max-width: 680px; overflow-wrap: break-word; word-wrap: break-word; word-break: break-word; Margin: 0 auto; background-color: transparent;\">\n" +
                                                    "<div style=\"border-collapse: collapse;display: table;width: 100%;background-color:transparent;\">\n" +
                                                    "<div class=\"col num12\" style=\"min-width: 320px; max-width: 680px; display: table-cell; vertical-align: top; width: 680px;\">\n" +
                                                    "<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                                                    "<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\">\n" +
                                                    "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\" width=\"100%\">\n" +
                                                    "<tbody>\n" +
                                                    "<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                                                    "<td class=\"divider_inner\" style=\"word-break: break-word; vertical-align: top; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%; padding-top: 10px; padding-right: 10px; padding-bottom: 10px; padding-left: 10px;\" valign=\"top\">\n" +
                                                    "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider_content\" height=\"5\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; border-top: 0px solid transparent; height: 5px; width: 100%;\" valign=\"top\" width=\"100%\">\n" +
                                                    "<tbody>\n" +
                                                    "<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                                                    "<td height=\"5\" style=\"word-break: break-word; vertical-align: top; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\"><span></span></td>\n" +
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
                                                    "<div style=\"background-color:transparent;\">\n" +
                                                    "<div class=\"block-grid two-up\" style=\"min-width: 320px; max-width: 680px; overflow-wrap: break-word; word-wrap: break-word; word-break: break-word; Margin: 0 auto; background-color: transparent;\">\n" +
                                                    "<div style=\"border-collapse: collapse;display: table;width: 100%;background-color:transparent;\">\n" +
                                                    "<div class=\"col num6\" style=\"display: table-cell; vertical-align: top; max-width: 320px; min-width: 336px; width: 340px;\">\n" +
                                                    "<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                                                    "<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\">\n" +
                                                    "<div style=\"color:#000000;font-family:'Montserrat', 'Trebuchet MS', 'Lucida Grande', 'Lucida Sans Unicode', 'Lucida Sans', Tahoma, sans-serif;line-height:1.8;padding-top:10px;padding-right:30px;padding-bottom:5px;padding-left:30px;\">\n" +
                                                    "<div style=\"line-height: 1.8; font-size: 12px; font-family: 'Montserrat', 'Trebuchet MS', 'Lucida Grande', 'Lucida Sans Unicode', 'Lucida Sans', Tahoma, sans-serif; color: #000000; mso-line-height-alt: 22px;\">\n" +
                                                    "<p style=\"font-size: 18px; line-height: 1.8; word-break: break-word; text-align: center; font-family: Montserrat, 'Trebuchet MS', 'Lucida Grande', 'Lucida Sans Unicode', 'Lucida Sans', Tahoma, sans-serif; mso-line-height-alt: 32px; margin: 0;\"><span style=\"font-size: 18px;\"><strong><span style=\"\">"+company_social_reason+"</span></strong></span></p>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<div align=\"center\" class=\"img-container center fixedwidth\" style=\"padding-right: 0px;padding-left: 0px;\">\n" +
                                                    "<img align=\"center\" alt=\"Alternate text\" border=\"0\" class=\"center fixedwidth\" src=\""+company_image+"\" style=\"text-decoration: none; -ms-interpolation-mode: bicubic; height: auto; border: 0; width: 100%; max-width: 136px; display: block;\" title=\"Alternate text\" width=\"136\"/>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<div class=\"col num6\" style=\"display: table-cell; vertical-align: top; max-width: 320px; min-width: 336px; width: 340px;\">\n" +
                                                    "<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                                                    "<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\">\n" +
                                                    "<div style=\"color:#000000;font-family:'Montserrat', 'Trebuchet MS', 'Lucida Grande', 'Lucida Sans Unicode', 'Lucida Sans', Tahoma, sans-serif;line-height:1.8;padding-top:10px;padding-right:30px;padding-bottom:5px;padding-left:30px;\">\n" +
                                                    "<div style=\"line-height: 1.8; font-size: 12px; font-family: 'Montserrat', 'Trebuchet MS', 'Lucida Grande', 'Lucida Sans Unicode', 'Lucida Sans', Tahoma, sans-serif; color: #000000; mso-line-height-alt: 22px;\">\n" +
                                                    "<p style=\"font-size: 14px; line-height: 1.8; word-break: break-word; text-align: left; font-family: Montserrat, 'Trebuchet MS', 'Lucida Grande', 'Lucida Sans Unicode', 'Lucida Sans', Tahoma, sans-serif; mso-line-height-alt: 25px; margin: 0;\"><strong><span style=\"font-size: 16px;\">Pago pendiente:</span></strong></p>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<div style=\"color:#000fff;font-family:'Montserrat', 'Trebuchet MS', 'Lucida Grande', 'Lucida Sans Unicode', 'Lucida Sans', Tahoma, sans-serif;line-height:1.8;padding-top:0px;padding-right:30px;padding-bottom:10px;padding-left:30px;\">\n" +
                                                    "<div style=\"line-height: 1.8; font-size: 12px; color: #000fff; font-family: 'Montserrat', 'Trebuchet MS', 'Lucida Grande', 'Lucida Sans Unicode', 'Lucida Sans', Tahoma, sans-serif; mso-line-height-alt: 22px;\">\n" +
                                                    "<p style=\"font-size: 26px; line-height: 1.8; word-break: break-word; text-align: left; mso-line-height-alt: 47px; margin: 0;\"><span style=\"font-size: 26px;\"><strong>S/ "+price_st+"</strong></span></p>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<div style=\"color:#000fff;font-family:'Montserrat', 'Trebuchet MS', 'Lucida Grande', 'Lucida Sans Unicode', 'Lucida Sans', Tahoma, sans-serif;line-height:1.8;padding-top:10px;padding-right:30px;padding-bottom:10px;padding-left:30px;\">\n" +
                                                    "<div style=\"line-height: 1.8; font-size: 12px; font-family: 'Montserrat', 'Trebuchet MS', 'Lucida Grande', 'Lucida Sans Unicode', 'Lucida Sans', Tahoma, sans-serif; color: #000fff; mso-line-height-alt: 22px;\">\n" +
                                                    "<p style=\"font-size: 14px; line-height: 1.8; word-break: break-word; text-align: left; font-family: Montserrat, 'Trebuchet MS', 'Lucida Grande', 'Lucida Sans Unicode', 'Lucida Sans', Tahoma, sans-serif; mso-line-height-alt: 25px; margin: 0;\"><strong><span style=\"font-size: 16px;\">Fecha de vencimiento</span></strong></p>\n" +
                                                    "<p style=\"font-size: 14px; line-height: 1.8; word-break: break-word; text-align: left; font-family: Montserrat, 'Trebuchet MS', 'Lucida Grande', 'Lucida Sans Unicode', 'Lucida Sans', Tahoma, sans-serif; mso-line-height-alt: 25px; margin: 0;\"><strong><span style=\"font-size: 16px;\">"+expiration_day+"/"+expiration_month+"/"+expiration_year+"</span></strong></p>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "\n" +
                                                    "</div>\n" +
                                                    "\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<div style=\"background-color:transparent;\">\n" +
                                                    "<div class=\"block-grid\" style=\"min-width: 320px; max-width: 680px; overflow-wrap: break-word; word-wrap: break-word; word-break: break-word; Margin: 0 auto; background-color: transparent;\">\n" +
                                                    "<div style=\"border-collapse: collapse;display: table;width: 100%;background-color:transparent;\">\n" +
                                                    "<div class=\"col num12\" style=\"min-width: 320px; max-width: 680px; display: table-cell; vertical-align: top; width: 680px;\">\n" +
                                                    "<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                                                    "<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\">\n" +
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
                                                    "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\" width=\"100%\">\n" +
                                                    "<tbody>\n" +
                                                    "<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                                                    "<td class=\"divider_inner\" style=\"word-break: break-word; vertical-align: top; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%; padding-top: 10px; padding-right: 10px; padding-bottom: 10px; padding-left: 10px;\" valign=\"top\">\n" +
                                                    "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider_content\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; border-top: 1px solid #BBBBBB; width: 100%;\" valign=\"top\" width=\"100%\">\n" +
                                                    "<tbody>\n" +
                                                    "<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                                                    "<td style=\"word-break: break-word; vertical-align: top; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\"><span></span></td>\n" +
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
                                                    "<!--[if (!mso)&(!IE)]><!-->\n" +
                                                    "</div>\n" +
                                                    "<!--<![endif]-->\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<div style=\"background-color:transparent;\">\n" +
                                                    "<div class=\"block-grid three-up\" style=\"min-width: 320px; max-width: 680px; overflow-wrap: break-word; word-wrap: break-word; word-break: break-word; Margin: 0 auto; background-color: #ffffff;\">\n" +
                                                    "<div style=\"border-collapse: collapse;display: table;width: 100%;background-color:#ffffff;\">\n" +
                                                    "<div class=\"col num3\" style=\"display: table-cell; vertical-align: top; max-width: 320px; min-width: 168px; width: 170px;\">\n" +
                                                    "<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                                                    "<!--[if (!mso)&(!IE)]><!-->\n" +
                                                    "<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 10px;\">\n" +
                                                    "<!--<![endif]-->\n" +
                                                    "<div class=\"mobile_hide\">\n" +
                                                    "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\" width=\"100%\">\n" +
                                                    "<tbody>\n" +
                                                    "<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                                                    "<td class=\"divider_inner\" style=\"word-break: break-word; vertical-align: top; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%; padding-top: 10px; padding-right: 10px; padding-bottom: 10px; padding-left: 10px;\" valign=\"top\">\n" +
                                                    "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider_content\" height=\"5\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; border-top: 0px solid transparent; height: 5px; width: 100%;\" valign=\"top\" width=\"100%\">\n" +
                                                    "<tbody>\n" +
                                                    "<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                                                    "<td height=\"5\" style=\"word-break: break-word; vertical-align: top; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\"><span></span></td>\n" +
                                                    "</tr>\n" +
                                                    "</tbody>\n" +
                                                    "</table>\n" +
                                                    "</td>\n" +
                                                    "</tr>\n" +
                                                    "</tbody>\n" +
                                                    "</table>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<div class=\"col num6\" style=\"display: table-cell; vertical-align: top; max-width: 320px; min-width: 336px; width: 340px;\">\n" +
                                                    "<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                                                    "<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\">\n" +
                                                    "\n" +
                                                    "<div style=\"color:#232323;font-family:Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif;line-height:1.2;padding-top:10px;padding-right:10px;padding-bottom:0px;padding-left:35px;\">\n" +
                                                    "<div style=\"line-height: 1.2; font-size: 12px; color: #232323; font-family: Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif; mso-line-height-alt: 14px;\">\n" +
                                                    "<p style=\"font-size: 14px; line-height: 1.2; word-break: break-word; mso-line-height-alt: 17px; margin: 0;\"><span style=\"font-size: 14px;\">Subtotal:</span></p>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<!--<![endif]-->\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "\n" +
                                                    "<div class=\"col num3\" style=\"display: table-cell; vertical-align: top; max-width: 320px; min-width: 168px; width: 170px;\">\n" +
                                                    "<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                                                    "<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\">\n" +
                                                    "<div style=\"color:#555555;font-family:Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif;line-height:1.2;padding-top:10px;padding-right:10px;padding-bottom:0px;padding-left:30px;\">\n" +
                                                    "<div style=\"line-height: 1.2; font-size: 12px; color: #555555; font-family: Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif; mso-line-height-alt: 14px;\">\n" +
                                                    "<p style=\"font-size: 14px; line-height: 1.2; word-break: break-word; mso-line-height-alt: 17px; margin: 0;\">S/ "+price_st+"</p>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<!--<![endif]-->\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<div style=\"background-color:transparent;\">\n" +
                                                    "<div class=\"block-grid three-up\" style=\"min-width: 320px; max-width: 680px; overflow-wrap: break-word; word-wrap: break-word; word-break: break-word; Margin: 0 auto; background-color: #ffffff;\">\n" +
                                                    "<div style=\"border-collapse: collapse;display: table;width: 100%;background-color:#ffffff;\">\n" +
                                                    "<div class=\"col num3\" style=\"display: table-cell; vertical-align: top; max-width: 320px; min-width: 168px; width: 170px;\">\n" +
                                                    "<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                                                    "<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 10px;\">\n" +
                                                    "<div class=\"mobile_hide\">\n" +
                                                    "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\" width=\"100%\">\n" +
                                                    "<tbody>\n" +
                                                    "<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                                                    "<td class=\"divider_inner\" style=\"word-break: break-word; vertical-align: top; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%; padding-top: 10px; padding-right: 10px; padding-bottom: 10px; padding-left: 10px;\" valign=\"top\">\n" +
                                                    "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider_content\" height=\"5\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; border-top: 0px solid transparent; height: 5px; width: 100%;\" valign=\"top\" width=\"100%\">\n" +
                                                    "<tbody>\n" +
                                                    "<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                                                    "<td height=\"5\" style=\"word-break: break-word; vertical-align: top; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\"><span></span></td>\n" +
                                                    "</tr>\n" +
                                                    "</tbody>\n" +
                                                    "</table>\n" +
                                                    "</td>\n" +
                                                    "</tr>\n" +
                                                    "</tbody>\n" +
                                                    "</table>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<!--<![endif]-->\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<div class=\"col num6\" style=\"display: table-cell; vertical-align: top; max-width: 320px; min-width: 336px; width: 340px;\">\n" +
                                                    "<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                                                    "<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\">\n" +
                                                    "<div style=\"color:#232323;font-family:Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif;line-height:1.2;padding-top:10px;padding-right:10px;padding-bottom:0px;padding-left:35px;\">\n" +
                                                    "<div style=\"line-height: 1.2; font-size: 12px; color: #232323; font-family: Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif; mso-line-height-alt: 14px;\">\n" +
                                                    "<p style=\"font-size: 14px; line-height: 1.2; word-break: break-word; mso-line-height-alt: 17px; margin: 0;\"><span style=\"font-size: 14px;\">Mora</span></p>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<div class=\"col num3\" style=\"display: table-cell; vertical-align: top; max-width: 320px; min-width: 168px; width: 170px;\">\n" +
                                                    "<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                                                    "<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\">\n" +
                                                    "<div style=\"color:#555555;font-family:Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif;line-height:1.2;padding-top:10px;padding-right:10px;padding-bottom:0px;padding-left:30px;\">\n" +
                                                    "<div style=\"line-height: 1.2; font-size: 12px; color: #555555; font-family: Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif; mso-line-height-alt: 14px;\">\n" +
                                                    "<p style=\"font-size: 14px; line-height: 1.2; word-break: break-word; mso-line-height-alt: 17px; margin: 0;\">$ 0.00</p>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<!--<![endif]-->\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<div style=\"background-color:transparent;\">\n" +
                                                    "<div class=\"block-grid three-up\" style=\"min-width: 320px; max-width: 680px; overflow-wrap: break-word; word-wrap: break-word; word-break: break-word; Margin: 0 auto; background-color: #ffffff;\">\n" +
                                                    "<div style=\"border-collapse: collapse;display: table;width: 100%;background-color:#ffffff;\">\n" +
                                                    "<div class=\"col num3\" style=\"display: table-cell; vertical-align: top; max-width: 320px; min-width: 168px; width: 170px;\">\n" +
                                                    "<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                                                    "<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 10px;\">\n" +
                                                    "<div class=\"mobile_hide\">\n" +
                                                    "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\" width=\"100%\">\n" +
                                                    "<tbody>\n" +
                                                    "<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                                                    "<td class=\"divider_inner\" style=\"word-break: break-word; vertical-align: top; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%; padding-top: 10px; padding-right: 10px; padding-bottom: 10px; padding-left: 10px;\" valign=\"top\">\n" +
                                                    "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider_content\" height=\"5\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; border-top: 0px solid transparent; height: 5px; width: 100%;\" valign=\"top\" width=\"100%\">\n" +
                                                    "<tbody>\n" +
                                                    "<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                                                    "<td height=\"5\" style=\"word-break: break-word; vertical-align: top; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\"><span></span></td>\n" +
                                                    "</tr>\n" +
                                                    "</tbody>\n" +
                                                    "</table>\n" +
                                                    "</td>\n" +
                                                    "</tr>\n" +
                                                    "</tbody>\n" +
                                                    "</table>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<div class=\"col num6\" style=\"display: table-cell; vertical-align: top; max-width: 320px; min-width: 336px; width: 340px;\">\n" +
                                                    "<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                                                    "<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\">\n" +
                                                    "<div style=\"color:#232323;font-family:Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif;line-height:1.2;padding-top:10px;padding-right:10px;padding-bottom:0px;padding-left:35px;\">\n" +
                                                    "<div style=\"line-height: 1.2; font-size: 12px; color: #232323; font-family: Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif; mso-line-height-alt: 14px;\">\n" +
                                                    "<p style=\"font-size: 18px; line-height: 1.2; word-break: break-word; mso-line-height-alt: 22px; margin: 0;\"><span style=\"font-size: 18px;\">Pago Total</span></p>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<!--<![endif]-->\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<div class=\"col num3\" style=\"display: table-cell; vertical-align: top; max-width: 320px; min-width: 168px; width: 170px;\">\n" +
                                                    "<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                                                    "<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\">\n" +
                                                    "<div style=\"color:#000fff;font-family:Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif;line-height:1.2;padding-top:10px;padding-right:10px;padding-bottom:0px;padding-left:30px;\">\n" +
                                                    "<div style=\"line-height: 1.2; font-size: 12px; color: #000fff; font-family: Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif; mso-line-height-alt: 14px;\">\n" +
                                                    "<p style=\"font-size: 18px; line-height: 1.2; word-break: break-word; mso-line-height-alt: 22px; margin: 0;\"><span style=\"font-size: 18px;\">S/ "+price_st+"</span></p>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<!--<![endif]-->\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<div style=\"background-color:transparent;\">\n" +
                                                    "<div class=\"block-grid\" style=\"min-width: 320px; max-width: 680px; overflow-wrap: break-word; word-wrap: break-word; word-break: break-word; Margin: 0 auto; background-color: transparent;\">\n" +
                                                    "<div style=\"border-collapse: collapse;display: table;width: 100%;background-color:transparent;\">\n" +
                                                    "<div class=\"col num12\" style=\"min-width: 320px; max-width: 680px; display: table-cell; vertical-align: top; width: 680px;\">\n" +
                                                    "<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                                                    "<!--[if (!mso)&(!IE)]><!-->\n" +
                                                    "<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\">\n" +
                                                    "<!--<![endif]-->\n" +
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
                                                    "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\" width=\"100%\">\n" +
                                                    "<tbody>\n" +
                                                    "<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                                                    "<td class=\"divider_inner\" style=\"word-break: break-word; vertical-align: top; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%; padding-top: 10px; padding-right: 10px; padding-bottom: 10px; padding-left: 10px;\" valign=\"top\">\n" +
                                                    "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider_content\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; border-top: 1px solid #BBBBBB; width: 100%;\" valign=\"top\" width=\"100%\">\n" +
                                                    "<tbody>\n" +
                                                    "<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                                                    "<td style=\"word-break: break-word; vertical-align: top; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\"><span></span></td>\n" +
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
                                                    "</div>\n" +
                                                    "<!--<![endif]-->\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<div style=\"background-color:#000fff;\">\n" +
                                                    "<div class=\"block-grid\" style=\"min-width: 320px; max-width: 680px; overflow-wrap: break-word; word-wrap: break-word; word-break: break-word; Margin: 0 auto; background-color: transparent;\">\n" +
                                                    "<div style=\"border-collapse: collapse;display: table;width: 100%;background-color:transparent;\">\n" +
                                                    "<div class=\"col num12\" style=\"min-width: 320px; max-width: 680px; display: table-cell; vertical-align: top; width: 680px;\">\n" +
                                                    "<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                                                    "<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\">\n" +
                                                    "<!--<![endif]-->\n" +
                                                    "<div class=\"mobile_hide\">\n" +
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
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<div style=\"background-color:#02025c;\">\n" +
                                                    "<div class=\"block-grid\" style=\"min-width: 320px; max-width: 680px; overflow-wrap: break-word; word-wrap: break-word; word-break: break-word; Margin: 0 auto; background-color: transparent;\">\n" +
                                                    "<div style=\"border-collapse: collapse;display: table;width: 100%;background-color:transparent;\">\n" +
                                                    "<div class=\"col num12\" style=\"min-width: 320px; max-width: 680px; display: table-cell; vertical-align: top; width: 680px;\">\n" +
                                                    "<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                                                    "<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\">\n" +
                                                    "<!--<![endif]-->\n" +
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
                                                    "<div style=\"color:#ffffff;font-family:Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif;line-height:1.5;padding-top:10px;padding-right:10px;padding-bottom:5px;padding-left:10px;\">\n" +
                                                    "<div style=\"line-height: 1.5; font-size: 12px; color: #ffffff; font-family: Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif; mso-line-height-alt: 18px;\">\n" +
                                                    "<p style=\"text-align: center; line-height: 1.5; word-break: break-word; mso-line-height-alt: 18px; margin: 0;\"><strong>This messasge was sent to <a href=\"mailto:email@example.com\" style=\"color: #d9f5fa;\" title=\"email@example.com\">email@example.com</a></strong></p>\n" +
                                                    "<p style=\"text-align: center; line-height: 1.5; word-break: break-word; mso-line-height-alt: 18px; margin: 0;\"><strong>If you would like to change your email, please <a href=\"http://www.example.com/\" rel=\"noopener\" style=\"color: #d9f5fa;\" target=\"_blank\">click here</a></strong></p>\n" +
                                                    "<p style=\"text-align: center; line-height: 1.5; word-break: break-word; mso-line-height-alt: 18px; margin: 0;\"><strong>If you no longer wish to receive e-mails from Company Name, <a href=\"http://www.example.com/\" rel=\"noopener\" style=\"color: #d9f5fa;\" target=\"_blank\">click here</a></strong></p>\n" +
                                                    "<p style=\"text-align: center; line-height: 1.5; word-break: break-word; mso-line-height-alt: 18px; margin: 0;\"><br/><strong>2020 © All Rights Reserved</strong></p>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<div style=\"background-color:transparent;\">\n" +
                                                    "<div class=\"block-grid two-up no-stack\" style=\"min-width: 320px; max-width: 680px; overflow-wrap: break-word; word-wrap: break-word; word-break: break-word; Margin: 0 auto; background-color: transparent;\">\n" +
                                                    "<div style=\"border-collapse: collapse;display: table;width: 100%;background-color:transparent;\">\n" +
                                                    "<div class=\"col num6\" style=\"display: table-cell; vertical-align: top; max-width: 320px; min-width: 336px; width: 340px;\">\n" +
                                                    "<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                                                    "\n" +
                                                    "<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:10px; padding-bottom:10px; padding-right: 0px; padding-left: 0px;\">\n" +
                                                    "\n" +
                                                    "\n" +
                                                    "</div>\n" +
                                                    "<!--<![endif]-->\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</td>\n" +
                                                    "</tr>\n" +
                                                    "</tbody>\n" +
                                                    "</body>\n" +
                                                    "</html>";
                                            JavaMailAPI javaMailAPI = new JavaMailAPI(getActivity(), customer_email,subject,message);
                                            Intent intent = new Intent(getActivity(), JavaMailAPI.class);
                                            intent.putExtra("customer_name",customer_name);
                                            javaMailAPI.execute();
                                        }


                                        @Override
                                        public void onCancelled(DatabaseError databaseError) {

                                        }
                                    });

                                }
                                if (expiration_days_ago >= 16 && expiration_days_ago <= 30)  {
                                    Object price = map.get("bill_amount");
                                    Object customer_id = map.get("customer_id");
                                    String customer_id_st = customer_id+"";
                                    final String price_st = price+"";

                                    companyRef.child(post_key).child("Customers").child(customer_id_st).addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {
                                            customer_name = dataSnapshot.child("customer_name").getValue().toString();
                                            customer_email = dataSnapshot.child("customer_email").getValue().toString();
                                            customer_phone = dataSnapshot.child("customer_phone").getValue().toString();

                                            sendEmail18();
                                        }

                                        private void sendEmail18() {
                                            String subject = customer_name+", Tienes una notificación de "+company_social_reason;
                                            String message = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional //EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" +
                                                    "\n" +
                                                    "<html xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\" xmlns:v=\"urn:schemas-microsoft-com:vml\">\n" +
                                                    "<head>\n" +
                                                    "\n" +
                                                    "<meta content=\"text/html; charset=utf-8\" http-equiv=\"Content-Type\"/>\n" +
                                                    "<meta content=\"width=device-width\" name=\"viewport\"/>\n" +
                                                    "<meta content=\"IE=edge\" http-equiv=\"X-UA-Compatible\"/>\n" +
                                                    "<title></title>\n" +
                                                    "<link href=\"https://fonts.googleapis.com/css?family=Montserrat\" rel=\"stylesheet\" type=\"text/css\"/>\n" +
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
                                                    "\t\t@media (max-width: 700px) {\n" +
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
                                                    "<body class=\"clean-body\" style=\"margin: 0; padding: 0; -webkit-text-size-adjust: 100%; background-color: #ffffff;\">\n" +
                                                    "<table bgcolor=\"#ffffff\" cellpadding=\"0\" cellspacing=\"0\" class=\"nl-container\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; min-width: 320px; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #ffffff; width: 100%;\" valign=\"top\" width=\"100%\">\n" +
                                                    "<tbody>\n" +
                                                    "<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                                                    "<td style=\"word-break: break-word; vertical-align: top;\" valign=\"top\">\n" +
                                                    "<div style=\"background-color:#000fff;\">\n" +
                                                    "<div class=\"block-grid\" style=\"min-width: 320px; max-width: 680px; overflow-wrap: break-word; word-wrap: break-word; word-break: break-word; Margin: 0 auto; background-color: transparent;\">\n" +
                                                    "<div style=\"border-collapse: collapse;display: table;width: 100%;background-color:transparent;\">\n" +
                                                    "<div class=\"col num12\" style=\"min-width: 320px; max-width: 680px; display: table-cell; vertical-align: top; width: 680px;\">\n" +
                                                    "<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                                                    "<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\">\n" +
                                                    "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\" width=\"100%\">\n" +
                                                    "<tbody>\n" +
                                                    "<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                                                    "<td class=\"divider_inner\" style=\"word-break: break-word; vertical-align: top; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%; padding-top: 5px; padding-right: 5px; padding-bottom: 5px; padding-left: 5px;\" valign=\"top\">\n" +
                                                    "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider_content\" height=\"25\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; border-top: 0px solid transparent; height: 25px; width: 100%;\" valign=\"top\" width=\"100%\">\n" +
                                                    "<tbody>\n" +
                                                    "<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                                                    "<td height=\"25\" style=\"word-break: break-word; vertical-align: top; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\"><span></span></td>\n" +
                                                    "</tr>\n" +
                                                    "</tbody>\n" +
                                                    "</table>\n" +
                                                    "</td>\n" +
                                                    "</tr>\n" +
                                                    "</tbody>\n" +
                                                    "</table>\n" +
                                                    "<div align=\"center\" class=\"img-container center fixedwidth\" style=\"padding-right: 0px;padding-left: 0px;\">\n" +
                                                    "<a href=\"http://www.example.com\" style=\"outline:none\" tabindex=\"-1\" target=\"_blank\"> <img align=\"center\" alt=\"Logo\" border=\"0\" class=\"center fixedwidth\" src=\"http://oliver.com.pe/wp-content/uploads/2020/12/Artboard-3.png\" style=\"text-decoration: none; -ms-interpolation-mode: bicubic; height: auto; border: 0; width: 100%; max-width: 170px; display: block;\" title=\"Logo\" width=\"170\"/></a>\n" +
                                                    "<div style=\"font-size:1px;line-height:15px\"> </div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<div style=\"background-color:#ffffff;\">\n" +
                                                    "<div class=\"block-grid\" style=\"min-width: 320px; max-width: 680px; overflow-wrap: break-word; word-wrap: break-word; word-break: break-word; Margin: 0 auto; background-color: transparent;\">\n" +
                                                    "<div style=\"border-collapse: collapse;display: table;width: 100%;background-color:transparent;\">\n" +
                                                    "<div class=\"col num12\" style=\"min-width: 320px; max-width: 680px; display: table-cell; vertical-align: top; width: 680px;\">\n" +
                                                    "<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                                                    "<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\">\n" +
                                                    "<div align=\"center\" class=\"img-container center fixedwidth\" style=\"padding-right: 0px;padding-left: 0px;\">\n" +
                                                    "<a href=\"www.example.com\" style=\"outline:none\" tabindex=\"-1\" target=\"_blank\"> <img align=\"center\" alt=\"Cool Burger Walking\" border=\"0\" class=\"center fixedwidth\" src=\"http://oliver.com.pe/wp-content/uploads/2020/12/Wallet.gif\" style=\"text-decoration: none; -ms-interpolation-mode: bicubic; height: auto; border: 0; width: 100%; max-width: 306px; display: block;\" title=\"Cool Burger Walking\" width=\"306\"/></a>\n" +
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
                                                    "<div style=\"color:#000fff;font-family:Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif;line-height:1.2;padding-top:10px;padding-right:10px;padding-bottom:15px;padding-left:10px;\">\n" +
                                                    "<div style=\"font-size: 14px; line-height: 1.2; color: #000fff; font-family: Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif; mso-line-height-alt: 17px;\">\n" +
                                                    "<p style=\"font-size: 14px; line-height: 1.2; word-break: break-word; text-align: center; mso-line-height-alt: 17px; margin: 0;\"><strong><span style=\"font-size: 38px;\">NOTIFICACIÓN DE COBRANZA</span></strong></p>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<div style=\"color:#000fff;font-family:'Montserrat', 'Trebuchet MS', 'Lucida Grande', 'Lucida Sans Unicode', 'Lucida Sans', Tahoma, sans-serif;line-height:1.8;padding-top:10px;padding-right:60px;padding-bottom:20px;padding-left:60px;\">\n" +
                                                    "<div style=\"line-height: 1.8; font-size: 12px; font-family: 'Montserrat', 'Trebuchet MS', 'Lucida Grande', 'Lucida Sans Unicode', 'Lucida Sans', Tahoma, sans-serif; color: #000fff; mso-line-height-alt: 22px;\">\n" +
                                                    "<p style=\"font-size: 22px; line-height: 1.8; text-align: center; word-break: break-word; font-family: Montserrat, 'Trebuchet MS', 'Lucida Grande', 'Lucida Sans Unicode', 'Lucida Sans', Tahoma, sans-serif; mso-line-height-alt: 40px; margin: 0;\"><span style=\"font-size: 22px;\">Hola "+customer_name+", te recordamos que tienes un pago pendiente VENCIDO. Porfavor te animamos a que puedas acercarte a regularizarlo para que sigamos atendiéndote y dándote el mejor servicio como siempre. ¡Te esperamos!<br/></span></p>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<div align=\"center\" class=\"button-container\" style=\"padding-top:10px;padding-right:10px;padding-bottom:10px;padding-left:10px;\">\n" +
                                                    "<a href=\"\" style=\"-webkit-text-size-adjust: none; text-decoration: none; display: inline-block; color: #ffffff; background-color: #0000ff; border-radius: 4px; -webkit-border-radius: 4px; -moz-border-radius: 4px; width: auto; width: auto; border-top: 0px solid #8a3b8f; border-right: 0px solid #8a3b8f; border-bottom: 0px solid #8a3b8f; border-left: 0px solid #8a3b8f; padding-top: 5px; padding-bottom: 5px; font-family: Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif; text-align: center; mso-border-alt: none; word-break: keep-all;\" target=\"_blank\"><span style=\"padding-left:40px;padding-right:40px;font-size:16px;display:inline-block;\"><span style=\"font-size: 16px; line-height: 2; word-break: break-word; mso-line-height-alt: 32px;\"><strong>VENCIDO HACE "+expiration_days_ago+" DÍAS"+"</strong></span></span></a>\n" +
                                                    "</div>\n" +
                                                    "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\" width=\"100%\">\n" +
                                                    "<tbody>\n" +
                                                    "<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                                                    "<td class=\"divider_inner\" style=\"word-break: break-word; vertical-align: top; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%; padding-top: 10px; padding-right: 10px; padding-bottom: 10px; padding-left: 10px;\" valign=\"top\">\n" +
                                                    "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider_content\" height=\"25\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; border-top: 0px solid transparent; height: 25px; width: 100%;\" valign=\"top\" width=\"100%\">\n" +
                                                    "<tbody>\n" +
                                                    "<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                                                    "<td height=\"25\" style=\"word-break: break-word; vertical-align: top; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\"><span></span></td>\n" +
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
                                                    "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider_content\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; border-top: 1px solid #BBBBBB; width: 100%;\" valign=\"top\" width=\"100%\">\n" +
                                                    "<tbody>\n" +
                                                    "<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                                                    "<td style=\"word-break: break-word; vertical-align: top; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\"><span></span></td>\n" +
                                                    "</tr>\n" +
                                                    "</tbody>\n" +
                                                    "</table>\n" +
                                                    "</td>\n" +
                                                    "</tr>\n" +
                                                    "</tbody>\n" +
                                                    "</table>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<div style=\"background-color:transparent;\">\n" +
                                                    "<div class=\"block-grid\" style=\"min-width: 320px; max-width: 680px; overflow-wrap: break-word; word-wrap: break-word; word-break: break-word; Margin: 0 auto; background-color: transparent;\">\n" +
                                                    "<div style=\"border-collapse: collapse;display: table;width: 100%;background-color:transparent;\">\n" +
                                                    "<div class=\"col num12\" style=\"min-width: 320px; max-width: 680px; display: table-cell; vertical-align: top; width: 680px;\">\n" +
                                                    "<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                                                    "<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\">\n" +
                                                    "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\" width=\"100%\">\n" +
                                                    "<tbody>\n" +
                                                    "<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                                                    "<td class=\"divider_inner\" style=\"word-break: break-word; vertical-align: top; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%; padding-top: 10px; padding-right: 10px; padding-bottom: 10px; padding-left: 10px;\" valign=\"top\">\n" +
                                                    "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider_content\" height=\"5\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; border-top: 0px solid transparent; height: 5px; width: 100%;\" valign=\"top\" width=\"100%\">\n" +
                                                    "<tbody>\n" +
                                                    "<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                                                    "<td height=\"5\" style=\"word-break: break-word; vertical-align: top; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\"><span></span></td>\n" +
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
                                                    "<div style=\"background-color:transparent;\">\n" +
                                                    "<div class=\"block-grid two-up\" style=\"min-width: 320px; max-width: 680px; overflow-wrap: break-word; word-wrap: break-word; word-break: break-word; Margin: 0 auto; background-color: transparent;\">\n" +
                                                    "<div style=\"border-collapse: collapse;display: table;width: 100%;background-color:transparent;\">\n" +
                                                    "<div class=\"col num6\" style=\"display: table-cell; vertical-align: top; max-width: 320px; min-width: 336px; width: 340px;\">\n" +
                                                    "<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                                                    "<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\">\n" +
                                                    "<div style=\"color:#000000;font-family:'Montserrat', 'Trebuchet MS', 'Lucida Grande', 'Lucida Sans Unicode', 'Lucida Sans', Tahoma, sans-serif;line-height:1.8;padding-top:10px;padding-right:30px;padding-bottom:5px;padding-left:30px;\">\n" +
                                                    "<div style=\"line-height: 1.8; font-size: 12px; font-family: 'Montserrat', 'Trebuchet MS', 'Lucida Grande', 'Lucida Sans Unicode', 'Lucida Sans', Tahoma, sans-serif; color: #000000; mso-line-height-alt: 22px;\">\n" +
                                                    "<p style=\"font-size: 18px; line-height: 1.8; word-break: break-word; text-align: center; font-family: Montserrat, 'Trebuchet MS', 'Lucida Grande', 'Lucida Sans Unicode', 'Lucida Sans', Tahoma, sans-serif; mso-line-height-alt: 32px; margin: 0;\"><span style=\"font-size: 18px;\"><strong><span style=\"\">"+company_social_reason+"</span></strong></span></p>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<div align=\"center\" class=\"img-container center fixedwidth\" style=\"padding-right: 0px;padding-left: 0px;\">\n" +
                                                    "<img align=\"center\" alt=\"Alternate text\" border=\"0\" class=\"center fixedwidth\" src=\""+company_image+"\" style=\"text-decoration: none; -ms-interpolation-mode: bicubic; height: auto; border: 0; width: 100%; max-width: 136px; display: block;\" title=\"Alternate text\" width=\"136\"/>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<div class=\"col num6\" style=\"display: table-cell; vertical-align: top; max-width: 320px; min-width: 336px; width: 340px;\">\n" +
                                                    "<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                                                    "<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\">\n" +
                                                    "<div style=\"color:#000000;font-family:'Montserrat', 'Trebuchet MS', 'Lucida Grande', 'Lucida Sans Unicode', 'Lucida Sans', Tahoma, sans-serif;line-height:1.8;padding-top:10px;padding-right:30px;padding-bottom:5px;padding-left:30px;\">\n" +
                                                    "<div style=\"line-height: 1.8; font-size: 12px; font-family: 'Montserrat', 'Trebuchet MS', 'Lucida Grande', 'Lucida Sans Unicode', 'Lucida Sans', Tahoma, sans-serif; color: #000000; mso-line-height-alt: 22px;\">\n" +
                                                    "<p style=\"font-size: 14px; line-height: 1.8; word-break: break-word; text-align: left; font-family: Montserrat, 'Trebuchet MS', 'Lucida Grande', 'Lucida Sans Unicode', 'Lucida Sans', Tahoma, sans-serif; mso-line-height-alt: 25px; margin: 0;\"><strong><span style=\"font-size: 16px;\">Pago pendiente:</span></strong></p>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<div style=\"color:#000fff;font-family:'Montserrat', 'Trebuchet MS', 'Lucida Grande', 'Lucida Sans Unicode', 'Lucida Sans', Tahoma, sans-serif;line-height:1.8;padding-top:0px;padding-right:30px;padding-bottom:10px;padding-left:30px;\">\n" +
                                                    "<div style=\"line-height: 1.8; font-size: 12px; color: #000fff; font-family: 'Montserrat', 'Trebuchet MS', 'Lucida Grande', 'Lucida Sans Unicode', 'Lucida Sans', Tahoma, sans-serif; mso-line-height-alt: 22px;\">\n" +
                                                    "<p style=\"font-size: 26px; line-height: 1.8; word-break: break-word; text-align: left; mso-line-height-alt: 47px; margin: 0;\"><span style=\"font-size: 26px;\"><strong>S/ "+price_st+"</strong></span></p>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<div style=\"color:#000fff;font-family:'Montserrat', 'Trebuchet MS', 'Lucida Grande', 'Lucida Sans Unicode', 'Lucida Sans', Tahoma, sans-serif;line-height:1.8;padding-top:10px;padding-right:30px;padding-bottom:10px;padding-left:30px;\">\n" +
                                                    "<div style=\"line-height: 1.8; font-size: 12px; font-family: 'Montserrat', 'Trebuchet MS', 'Lucida Grande', 'Lucida Sans Unicode', 'Lucida Sans', Tahoma, sans-serif; color: #000fff; mso-line-height-alt: 22px;\">\n" +
                                                    "<p style=\"font-size: 14px; line-height: 1.8; word-break: break-word; text-align: left; font-family: Montserrat, 'Trebuchet MS', 'Lucida Grande', 'Lucida Sans Unicode', 'Lucida Sans', Tahoma, sans-serif; mso-line-height-alt: 25px; margin: 0;\"><strong><span style=\"font-size: 16px;\">Fecha de vencimiento</span></strong></p>\n" +
                                                    "<p style=\"font-size: 14px; line-height: 1.8; word-break: break-word; text-align: left; font-family: Montserrat, 'Trebuchet MS', 'Lucida Grande', 'Lucida Sans Unicode', 'Lucida Sans', Tahoma, sans-serif; mso-line-height-alt: 25px; margin: 0;\"><strong><span style=\"font-size: 16px;\">"+expiration_day+"/"+expiration_month+"/"+expiration_year+"</span></strong></p>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "\n" +
                                                    "</div>\n" +
                                                    "\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<div style=\"background-color:transparent;\">\n" +
                                                    "<div class=\"block-grid\" style=\"min-width: 320px; max-width: 680px; overflow-wrap: break-word; word-wrap: break-word; word-break: break-word; Margin: 0 auto; background-color: transparent;\">\n" +
                                                    "<div style=\"border-collapse: collapse;display: table;width: 100%;background-color:transparent;\">\n" +
                                                    "<div class=\"col num12\" style=\"min-width: 320px; max-width: 680px; display: table-cell; vertical-align: top; width: 680px;\">\n" +
                                                    "<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                                                    "<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\">\n" +
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
                                                    "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\" width=\"100%\">\n" +
                                                    "<tbody>\n" +
                                                    "<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                                                    "<td class=\"divider_inner\" style=\"word-break: break-word; vertical-align: top; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%; padding-top: 10px; padding-right: 10px; padding-bottom: 10px; padding-left: 10px;\" valign=\"top\">\n" +
                                                    "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider_content\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; border-top: 1px solid #BBBBBB; width: 100%;\" valign=\"top\" width=\"100%\">\n" +
                                                    "<tbody>\n" +
                                                    "<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                                                    "<td style=\"word-break: break-word; vertical-align: top; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\"><span></span></td>\n" +
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
                                                    "<!--[if (!mso)&(!IE)]><!-->\n" +
                                                    "</div>\n" +
                                                    "<!--<![endif]-->\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<div style=\"background-color:transparent;\">\n" +
                                                    "<div class=\"block-grid three-up\" style=\"min-width: 320px; max-width: 680px; overflow-wrap: break-word; word-wrap: break-word; word-break: break-word; Margin: 0 auto; background-color: #ffffff;\">\n" +
                                                    "<div style=\"border-collapse: collapse;display: table;width: 100%;background-color:#ffffff;\">\n" +
                                                    "<div class=\"col num3\" style=\"display: table-cell; vertical-align: top; max-width: 320px; min-width: 168px; width: 170px;\">\n" +
                                                    "<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                                                    "<!--[if (!mso)&(!IE)]><!-->\n" +
                                                    "<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 10px;\">\n" +
                                                    "<!--<![endif]-->\n" +
                                                    "<div class=\"mobile_hide\">\n" +
                                                    "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\" width=\"100%\">\n" +
                                                    "<tbody>\n" +
                                                    "<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                                                    "<td class=\"divider_inner\" style=\"word-break: break-word; vertical-align: top; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%; padding-top: 10px; padding-right: 10px; padding-bottom: 10px; padding-left: 10px;\" valign=\"top\">\n" +
                                                    "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider_content\" height=\"5\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; border-top: 0px solid transparent; height: 5px; width: 100%;\" valign=\"top\" width=\"100%\">\n" +
                                                    "<tbody>\n" +
                                                    "<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                                                    "<td height=\"5\" style=\"word-break: break-word; vertical-align: top; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\"><span></span></td>\n" +
                                                    "</tr>\n" +
                                                    "</tbody>\n" +
                                                    "</table>\n" +
                                                    "</td>\n" +
                                                    "</tr>\n" +
                                                    "</tbody>\n" +
                                                    "</table>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<div class=\"col num6\" style=\"display: table-cell; vertical-align: top; max-width: 320px; min-width: 336px; width: 340px;\">\n" +
                                                    "<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                                                    "<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\">\n" +
                                                    "\n" +
                                                    "<div style=\"color:#232323;font-family:Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif;line-height:1.2;padding-top:10px;padding-right:10px;padding-bottom:0px;padding-left:35px;\">\n" +
                                                    "<div style=\"line-height: 1.2; font-size: 12px; color: #232323; font-family: Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif; mso-line-height-alt: 14px;\">\n" +
                                                    "<p style=\"font-size: 14px; line-height: 1.2; word-break: break-word; mso-line-height-alt: 17px; margin: 0;\"><span style=\"font-size: 14px;\">Subtotal:</span></p>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<!--<![endif]-->\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "\n" +
                                                    "<div class=\"col num3\" style=\"display: table-cell; vertical-align: top; max-width: 320px; min-width: 168px; width: 170px;\">\n" +
                                                    "<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                                                    "<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\">\n" +
                                                    "<div style=\"color:#555555;font-family:Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif;line-height:1.2;padding-top:10px;padding-right:10px;padding-bottom:0px;padding-left:30px;\">\n" +
                                                    "<div style=\"line-height: 1.2; font-size: 12px; color: #555555; font-family: Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif; mso-line-height-alt: 14px;\">\n" +
                                                    "<p style=\"font-size: 14px; line-height: 1.2; word-break: break-word; mso-line-height-alt: 17px; margin: 0;\">S/ "+price_st+"</p>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<!--<![endif]-->\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<div style=\"background-color:transparent;\">\n" +
                                                    "<div class=\"block-grid three-up\" style=\"min-width: 320px; max-width: 680px; overflow-wrap: break-word; word-wrap: break-word; word-break: break-word; Margin: 0 auto; background-color: #ffffff;\">\n" +
                                                    "<div style=\"border-collapse: collapse;display: table;width: 100%;background-color:#ffffff;\">\n" +
                                                    "<div class=\"col num3\" style=\"display: table-cell; vertical-align: top; max-width: 320px; min-width: 168px; width: 170px;\">\n" +
                                                    "<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                                                    "<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 10px;\">\n" +
                                                    "<div class=\"mobile_hide\">\n" +
                                                    "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\" width=\"100%\">\n" +
                                                    "<tbody>\n" +
                                                    "<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                                                    "<td class=\"divider_inner\" style=\"word-break: break-word; vertical-align: top; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%; padding-top: 10px; padding-right: 10px; padding-bottom: 10px; padding-left: 10px;\" valign=\"top\">\n" +
                                                    "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider_content\" height=\"5\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; border-top: 0px solid transparent; height: 5px; width: 100%;\" valign=\"top\" width=\"100%\">\n" +
                                                    "<tbody>\n" +
                                                    "<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                                                    "<td height=\"5\" style=\"word-break: break-word; vertical-align: top; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\"><span></span></td>\n" +
                                                    "</tr>\n" +
                                                    "</tbody>\n" +
                                                    "</table>\n" +
                                                    "</td>\n" +
                                                    "</tr>\n" +
                                                    "</tbody>\n" +
                                                    "</table>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<!--<![endif]-->\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<div class=\"col num6\" style=\"display: table-cell; vertical-align: top; max-width: 320px; min-width: 336px; width: 340px;\">\n" +
                                                    "<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                                                    "<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\">\n" +
                                                    "<div style=\"color:#232323;font-family:Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif;line-height:1.2;padding-top:10px;padding-right:10px;padding-bottom:0px;padding-left:35px;\">\n" +
                                                    "<div style=\"line-height: 1.2; font-size: 12px; color: #232323; font-family: Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif; mso-line-height-alt: 14px;\">\n" +
                                                    "<p style=\"font-size: 14px; line-height: 1.2; word-break: break-word; mso-line-height-alt: 17px; margin: 0;\"><span style=\"font-size: 14px;\">Mora</span></p>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<div class=\"col num3\" style=\"display: table-cell; vertical-align: top; max-width: 320px; min-width: 168px; width: 170px;\">\n" +
                                                    "<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                                                    "<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\">\n" +
                                                    "<div style=\"color:#555555;font-family:Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif;line-height:1.2;padding-top:10px;padding-right:10px;padding-bottom:0px;padding-left:30px;\">\n" +
                                                    "<div style=\"line-height: 1.2; font-size: 12px; color: #555555; font-family: Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif; mso-line-height-alt: 14px;\">\n" +
                                                    "<p style=\"font-size: 14px; line-height: 1.2; word-break: break-word; mso-line-height-alt: 17px; margin: 0;\">$ 0.00</p>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<!--<![endif]-->\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<div style=\"background-color:transparent;\">\n" +
                                                    "<div class=\"block-grid three-up\" style=\"min-width: 320px; max-width: 680px; overflow-wrap: break-word; word-wrap: break-word; word-break: break-word; Margin: 0 auto; background-color: #ffffff;\">\n" +
                                                    "<div style=\"border-collapse: collapse;display: table;width: 100%;background-color:#ffffff;\">\n" +
                                                    "<div class=\"col num3\" style=\"display: table-cell; vertical-align: top; max-width: 320px; min-width: 168px; width: 170px;\">\n" +
                                                    "<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                                                    "<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 10px;\">\n" +
                                                    "<div class=\"mobile_hide\">\n" +
                                                    "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\" width=\"100%\">\n" +
                                                    "<tbody>\n" +
                                                    "<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                                                    "<td class=\"divider_inner\" style=\"word-break: break-word; vertical-align: top; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%; padding-top: 10px; padding-right: 10px; padding-bottom: 10px; padding-left: 10px;\" valign=\"top\">\n" +
                                                    "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider_content\" height=\"5\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; border-top: 0px solid transparent; height: 5px; width: 100%;\" valign=\"top\" width=\"100%\">\n" +
                                                    "<tbody>\n" +
                                                    "<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                                                    "<td height=\"5\" style=\"word-break: break-word; vertical-align: top; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\"><span></span></td>\n" +
                                                    "</tr>\n" +
                                                    "</tbody>\n" +
                                                    "</table>\n" +
                                                    "</td>\n" +
                                                    "</tr>\n" +
                                                    "</tbody>\n" +
                                                    "</table>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<div class=\"col num6\" style=\"display: table-cell; vertical-align: top; max-width: 320px; min-width: 336px; width: 340px;\">\n" +
                                                    "<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                                                    "<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\">\n" +
                                                    "<div style=\"color:#232323;font-family:Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif;line-height:1.2;padding-top:10px;padding-right:10px;padding-bottom:0px;padding-left:35px;\">\n" +
                                                    "<div style=\"line-height: 1.2; font-size: 12px; color: #232323; font-family: Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif; mso-line-height-alt: 14px;\">\n" +
                                                    "<p style=\"font-size: 18px; line-height: 1.2; word-break: break-word; mso-line-height-alt: 22px; margin: 0;\"><span style=\"font-size: 18px;\">Pago Total</span></p>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<!--<![endif]-->\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<div class=\"col num3\" style=\"display: table-cell; vertical-align: top; max-width: 320px; min-width: 168px; width: 170px;\">\n" +
                                                    "<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                                                    "<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\">\n" +
                                                    "<div style=\"color:#000fff;font-family:Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif;line-height:1.2;padding-top:10px;padding-right:10px;padding-bottom:0px;padding-left:30px;\">\n" +
                                                    "<div style=\"line-height: 1.2; font-size: 12px; color: #000fff; font-family: Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif; mso-line-height-alt: 14px;\">\n" +
                                                    "<p style=\"font-size: 18px; line-height: 1.2; word-break: break-word; mso-line-height-alt: 22px; margin: 0;\"><span style=\"font-size: 18px;\">S/ "+price_st+"</span></p>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<!--<![endif]-->\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<div style=\"background-color:transparent;\">\n" +
                                                    "<div class=\"block-grid\" style=\"min-width: 320px; max-width: 680px; overflow-wrap: break-word; word-wrap: break-word; word-break: break-word; Margin: 0 auto; background-color: transparent;\">\n" +
                                                    "<div style=\"border-collapse: collapse;display: table;width: 100%;background-color:transparent;\">\n" +
                                                    "<div class=\"col num12\" style=\"min-width: 320px; max-width: 680px; display: table-cell; vertical-align: top; width: 680px;\">\n" +
                                                    "<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                                                    "<!--[if (!mso)&(!IE)]><!-->\n" +
                                                    "<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\">\n" +
                                                    "<!--<![endif]-->\n" +
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
                                                    "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\" width=\"100%\">\n" +
                                                    "<tbody>\n" +
                                                    "<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                                                    "<td class=\"divider_inner\" style=\"word-break: break-word; vertical-align: top; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%; padding-top: 10px; padding-right: 10px; padding-bottom: 10px; padding-left: 10px;\" valign=\"top\">\n" +
                                                    "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider_content\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; border-top: 1px solid #BBBBBB; width: 100%;\" valign=\"top\" width=\"100%\">\n" +
                                                    "<tbody>\n" +
                                                    "<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                                                    "<td style=\"word-break: break-word; vertical-align: top; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\"><span></span></td>\n" +
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
                                                    "</div>\n" +
                                                    "<!--<![endif]-->\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<div style=\"background-color:#000fff;\">\n" +
                                                    "<div class=\"block-grid\" style=\"min-width: 320px; max-width: 680px; overflow-wrap: break-word; word-wrap: break-word; word-break: break-word; Margin: 0 auto; background-color: transparent;\">\n" +
                                                    "<div style=\"border-collapse: collapse;display: table;width: 100%;background-color:transparent;\">\n" +
                                                    "<div class=\"col num12\" style=\"min-width: 320px; max-width: 680px; display: table-cell; vertical-align: top; width: 680px;\">\n" +
                                                    "<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                                                    "<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\">\n" +
                                                    "<!--<![endif]-->\n" +
                                                    "<div class=\"mobile_hide\">\n" +
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
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<div style=\"background-color:#02025c;\">\n" +
                                                    "<div class=\"block-grid\" style=\"min-width: 320px; max-width: 680px; overflow-wrap: break-word; word-wrap: break-word; word-break: break-word; Margin: 0 auto; background-color: transparent;\">\n" +
                                                    "<div style=\"border-collapse: collapse;display: table;width: 100%;background-color:transparent;\">\n" +
                                                    "<div class=\"col num12\" style=\"min-width: 320px; max-width: 680px; display: table-cell; vertical-align: top; width: 680px;\">\n" +
                                                    "<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                                                    "<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\">\n" +
                                                    "<!--<![endif]-->\n" +
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
                                                    "<div style=\"color:#ffffff;font-family:Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif;line-height:1.5;padding-top:10px;padding-right:10px;padding-bottom:5px;padding-left:10px;\">\n" +
                                                    "<div style=\"line-height: 1.5; font-size: 12px; color: #ffffff; font-family: Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif; mso-line-height-alt: 18px;\">\n" +
                                                    "<p style=\"text-align: center; line-height: 1.5; word-break: break-word; mso-line-height-alt: 18px; margin: 0;\"><strong>This messasge was sent to <a href=\"mailto:email@example.com\" style=\"color: #d9f5fa;\" title=\"email@example.com\">email@example.com</a></strong></p>\n" +
                                                    "<p style=\"text-align: center; line-height: 1.5; word-break: break-word; mso-line-height-alt: 18px; margin: 0;\"><strong>If you would like to change your email, please <a href=\"http://www.example.com/\" rel=\"noopener\" style=\"color: #d9f5fa;\" target=\"_blank\">click here</a></strong></p>\n" +
                                                    "<p style=\"text-align: center; line-height: 1.5; word-break: break-word; mso-line-height-alt: 18px; margin: 0;\"><strong>If you no longer wish to receive e-mails from Company Name, <a href=\"http://www.example.com/\" rel=\"noopener\" style=\"color: #d9f5fa;\" target=\"_blank\">click here</a></strong></p>\n" +
                                                    "<p style=\"text-align: center; line-height: 1.5; word-break: break-word; mso-line-height-alt: 18px; margin: 0;\"><br/><strong>2020 © All Rights Reserved</strong></p>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<div style=\"background-color:transparent;\">\n" +
                                                    "<div class=\"block-grid two-up no-stack\" style=\"min-width: 320px; max-width: 680px; overflow-wrap: break-word; word-wrap: break-word; word-break: break-word; Margin: 0 auto; background-color: transparent;\">\n" +
                                                    "<div style=\"border-collapse: collapse;display: table;width: 100%;background-color:transparent;\">\n" +
                                                    "<div class=\"col num6\" style=\"display: table-cell; vertical-align: top; max-width: 320px; min-width: 336px; width: 340px;\">\n" +
                                                    "<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                                                    "\n" +
                                                    "<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:10px; padding-bottom:10px; padding-right: 0px; padding-left: 0px;\">\n" +
                                                    "\n" +
                                                    "\n" +
                                                    "</div>\n" +
                                                    "<!--<![endif]-->\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</td>\n" +
                                                    "</tr>\n" +
                                                    "</tbody>\n" +
                                                    "</body>\n" +
                                                    "</html>";
                                            JavaMailAPI javaMailAPI = new JavaMailAPI(getActivity(), customer_email,subject,message);
                                            javaMailAPI.execute();
                                        }


                                        @Override
                                        public void onCancelled(DatabaseError databaseError) {

                                        }
                                    });

                                }
                                if (expiration_days_ago >= 31 && expiration_days_ago <= 45)  {
                                    Object price = map.get("bill_amount");
                                    Object customer_id = map.get("customer_id");
                                    String customer_id_st = customer_id+"";
                                    final String price_st = price+"";

                                    companyRef.child(post_key).child("Customers").child(customer_id_st).addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {
                                            customer_name = dataSnapshot.child("customer_name").getValue().toString();
                                            customer_email = dataSnapshot.child("customer_email").getValue().toString();
                                            customer_phone = dataSnapshot.child("customer_phone").getValue().toString();

                                            sendEmail18();
                                        }

                                        private void sendEmail18() {
                                            String subject = customer_name+", Tienes una notificación de "+company_social_reason;
                                            String message = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional //EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" +
                                                    "\n" +
                                                    "<html xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\" xmlns:v=\"urn:schemas-microsoft-com:vml\">\n" +
                                                    "<head>\n" +
                                                    "\n" +
                                                    "<meta content=\"text/html; charset=utf-8\" http-equiv=\"Content-Type\"/>\n" +
                                                    "<meta content=\"width=device-width\" name=\"viewport\"/>\n" +
                                                    "<meta content=\"IE=edge\" http-equiv=\"X-UA-Compatible\"/>\n" +
                                                    "<title></title>\n" +
                                                    "<link href=\"https://fonts.googleapis.com/css?family=Montserrat\" rel=\"stylesheet\" type=\"text/css\"/>\n" +
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
                                                    "\t\t@media (max-width: 700px) {\n" +
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
                                                    "<body class=\"clean-body\" style=\"margin: 0; padding: 0; -webkit-text-size-adjust: 100%; background-color: #ffffff;\">\n" +
                                                    "<table bgcolor=\"#ffffff\" cellpadding=\"0\" cellspacing=\"0\" class=\"nl-container\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; min-width: 320px; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #ffffff; width: 100%;\" valign=\"top\" width=\"100%\">\n" +
                                                    "<tbody>\n" +
                                                    "<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                                                    "<td style=\"word-break: break-word; vertical-align: top;\" valign=\"top\">\n" +
                                                    "<div style=\"background-color:#000fff;\">\n" +
                                                    "<div class=\"block-grid\" style=\"min-width: 320px; max-width: 680px; overflow-wrap: break-word; word-wrap: break-word; word-break: break-word; Margin: 0 auto; background-color: transparent;\">\n" +
                                                    "<div style=\"border-collapse: collapse;display: table;width: 100%;background-color:transparent;\">\n" +
                                                    "<div class=\"col num12\" style=\"min-width: 320px; max-width: 680px; display: table-cell; vertical-align: top; width: 680px;\">\n" +
                                                    "<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                                                    "<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\">\n" +
                                                    "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\" width=\"100%\">\n" +
                                                    "<tbody>\n" +
                                                    "<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                                                    "<td class=\"divider_inner\" style=\"word-break: break-word; vertical-align: top; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%; padding-top: 5px; padding-right: 5px; padding-bottom: 5px; padding-left: 5px;\" valign=\"top\">\n" +
                                                    "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider_content\" height=\"25\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; border-top: 0px solid transparent; height: 25px; width: 100%;\" valign=\"top\" width=\"100%\">\n" +
                                                    "<tbody>\n" +
                                                    "<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                                                    "<td height=\"25\" style=\"word-break: break-word; vertical-align: top; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\"><span></span></td>\n" +
                                                    "</tr>\n" +
                                                    "</tbody>\n" +
                                                    "</table>\n" +
                                                    "</td>\n" +
                                                    "</tr>\n" +
                                                    "</tbody>\n" +
                                                    "</table>\n" +
                                                    "<div align=\"center\" class=\"img-container center fixedwidth\" style=\"padding-right: 0px;padding-left: 0px;\">\n" +
                                                    "<a href=\"http://www.example.com\" style=\"outline:none\" tabindex=\"-1\" target=\"_blank\"> <img align=\"center\" alt=\"Logo\" border=\"0\" class=\"center fixedwidth\" src=\"http://oliver.com.pe/wp-content/uploads/2020/12/Artboard-3.png\" style=\"text-decoration: none; -ms-interpolation-mode: bicubic; height: auto; border: 0; width: 100%; max-width: 170px; display: block;\" title=\"Logo\" width=\"170\"/></a>\n" +
                                                    "<div style=\"font-size:1px;line-height:15px\"> </div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<div style=\"background-color:#ffffff;\">\n" +
                                                    "<div class=\"block-grid\" style=\"min-width: 320px; max-width: 680px; overflow-wrap: break-word; word-wrap: break-word; word-break: break-word; Margin: 0 auto; background-color: transparent;\">\n" +
                                                    "<div style=\"border-collapse: collapse;display: table;width: 100%;background-color:transparent;\">\n" +
                                                    "<div class=\"col num12\" style=\"min-width: 320px; max-width: 680px; display: table-cell; vertical-align: top; width: 680px;\">\n" +
                                                    "<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                                                    "<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\">\n" +
                                                    "<div align=\"center\" class=\"img-container center fixedwidth\" style=\"padding-right: 0px;padding-left: 0px;\">\n" +
                                                    "<a href=\"www.example.com\" style=\"outline:none\" tabindex=\"-1\" target=\"_blank\"> <img align=\"center\" alt=\"Cool Burger Walking\" border=\"0\" class=\"center fixedwidth\" src=\"http://oliver.com.pe/wp-content/uploads/2020/12/Wallet.gif\" style=\"text-decoration: none; -ms-interpolation-mode: bicubic; height: auto; border: 0; width: 100%; max-width: 306px; display: block;\" title=\"Cool Burger Walking\" width=\"306\"/></a>\n" +
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
                                                    "<div style=\"color:#000fff;font-family:Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif;line-height:1.2;padding-top:10px;padding-right:10px;padding-bottom:15px;padding-left:10px;\">\n" +
                                                    "<div style=\"font-size: 14px; line-height: 1.2; color: #000fff; font-family: Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif; mso-line-height-alt: 17px;\">\n" +
                                                    "<p style=\"font-size: 14px; line-height: 1.2; word-break: break-word; text-align: center; mso-line-height-alt: 17px; margin: 0;\"><strong><span style=\"font-size: 38px;\">NOTIFICACIÓN DE COBRANZA</span></strong></p>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<div style=\"color:#000fff;font-family:'Montserrat', 'Trebuchet MS', 'Lucida Grande', 'Lucida Sans Unicode', 'Lucida Sans', Tahoma, sans-serif;line-height:1.8;padding-top:10px;padding-right:60px;padding-bottom:20px;padding-left:60px;\">\n" +
                                                    "<div style=\"line-height: 1.8; font-size: 12px; font-family: 'Montserrat', 'Trebuchet MS', 'Lucida Grande', 'Lucida Sans Unicode', 'Lucida Sans', Tahoma, sans-serif; color: #000fff; mso-line-height-alt: 22px;\">\n" +
                                                    "<p style=\"font-size: 22px; line-height: 1.8; text-align: center; word-break: break-word; font-family: Montserrat, 'Trebuchet MS', 'Lucida Grande', 'Lucida Sans Unicode', 'Lucida Sans', Tahoma, sans-serif; mso-line-height-alt: 40px; margin: 0;\"><span style=\"font-size: 22px;\">Hola "+customer_name+", te recordamos que tienes un pago pendiente VENCIDO. Porfavor te animamos a que puedas acercarte a regularizarlo para que sigamos atendiéndote y dándote el mejor servicio como siempre. ¡Te esperamos!<br/></span></p>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<div align=\"center\" class=\"button-container\" style=\"padding-top:10px;padding-right:10px;padding-bottom:10px;padding-left:10px;\">\n" +
                                                    "<a href=\"\" style=\"-webkit-text-size-adjust: none; text-decoration: none; display: inline-block; color: #ffffff; background-color: #0000ff; border-radius: 4px; -webkit-border-radius: 4px; -moz-border-radius: 4px; width: auto; width: auto; border-top: 0px solid #8a3b8f; border-right: 0px solid #8a3b8f; border-bottom: 0px solid #8a3b8f; border-left: 0px solid #8a3b8f; padding-top: 5px; padding-bottom: 5px; font-family: Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif; text-align: center; mso-border-alt: none; word-break: keep-all;\" target=\"_blank\"><span style=\"padding-left:40px;padding-right:40px;font-size:16px;display:inline-block;\"><span style=\"font-size: 16px; line-height: 2; word-break: break-word; mso-line-height-alt: 32px;\"><strong>VENCIDO HACE "+expiration_days_ago+" DÍAS"+"</strong></span></span></a>\n" +
                                                    "</div>\n" +
                                                    "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\" width=\"100%\">\n" +
                                                    "<tbody>\n" +
                                                    "<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                                                    "<td class=\"divider_inner\" style=\"word-break: break-word; vertical-align: top; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%; padding-top: 10px; padding-right: 10px; padding-bottom: 10px; padding-left: 10px;\" valign=\"top\">\n" +
                                                    "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider_content\" height=\"25\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; border-top: 0px solid transparent; height: 25px; width: 100%;\" valign=\"top\" width=\"100%\">\n" +
                                                    "<tbody>\n" +
                                                    "<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                                                    "<td height=\"25\" style=\"word-break: break-word; vertical-align: top; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\"><span></span></td>\n" +
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
                                                    "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider_content\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; border-top: 1px solid #BBBBBB; width: 100%;\" valign=\"top\" width=\"100%\">\n" +
                                                    "<tbody>\n" +
                                                    "<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                                                    "<td style=\"word-break: break-word; vertical-align: top; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\"><span></span></td>\n" +
                                                    "</tr>\n" +
                                                    "</tbody>\n" +
                                                    "</table>\n" +
                                                    "</td>\n" +
                                                    "</tr>\n" +
                                                    "</tbody>\n" +
                                                    "</table>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<div style=\"background-color:transparent;\">\n" +
                                                    "<div class=\"block-grid\" style=\"min-width: 320px; max-width: 680px; overflow-wrap: break-word; word-wrap: break-word; word-break: break-word; Margin: 0 auto; background-color: transparent;\">\n" +
                                                    "<div style=\"border-collapse: collapse;display: table;width: 100%;background-color:transparent;\">\n" +
                                                    "<div class=\"col num12\" style=\"min-width: 320px; max-width: 680px; display: table-cell; vertical-align: top; width: 680px;\">\n" +
                                                    "<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                                                    "<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\">\n" +
                                                    "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\" width=\"100%\">\n" +
                                                    "<tbody>\n" +
                                                    "<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                                                    "<td class=\"divider_inner\" style=\"word-break: break-word; vertical-align: top; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%; padding-top: 10px; padding-right: 10px; padding-bottom: 10px; padding-left: 10px;\" valign=\"top\">\n" +
                                                    "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider_content\" height=\"5\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; border-top: 0px solid transparent; height: 5px; width: 100%;\" valign=\"top\" width=\"100%\">\n" +
                                                    "<tbody>\n" +
                                                    "<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                                                    "<td height=\"5\" style=\"word-break: break-word; vertical-align: top; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\"><span></span></td>\n" +
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
                                                    "<div style=\"background-color:transparent;\">\n" +
                                                    "<div class=\"block-grid two-up\" style=\"min-width: 320px; max-width: 680px; overflow-wrap: break-word; word-wrap: break-word; word-break: break-word; Margin: 0 auto; background-color: transparent;\">\n" +
                                                    "<div style=\"border-collapse: collapse;display: table;width: 100%;background-color:transparent;\">\n" +
                                                    "<div class=\"col num6\" style=\"display: table-cell; vertical-align: top; max-width: 320px; min-width: 336px; width: 340px;\">\n" +
                                                    "<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                                                    "<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\">\n" +
                                                    "<div style=\"color:#000000;font-family:'Montserrat', 'Trebuchet MS', 'Lucida Grande', 'Lucida Sans Unicode', 'Lucida Sans', Tahoma, sans-serif;line-height:1.8;padding-top:10px;padding-right:30px;padding-bottom:5px;padding-left:30px;\">\n" +
                                                    "<div style=\"line-height: 1.8; font-size: 12px; font-family: 'Montserrat', 'Trebuchet MS', 'Lucida Grande', 'Lucida Sans Unicode', 'Lucida Sans', Tahoma, sans-serif; color: #000000; mso-line-height-alt: 22px;\">\n" +
                                                    "<p style=\"font-size: 18px; line-height: 1.8; word-break: break-word; text-align: center; font-family: Montserrat, 'Trebuchet MS', 'Lucida Grande', 'Lucida Sans Unicode', 'Lucida Sans', Tahoma, sans-serif; mso-line-height-alt: 32px; margin: 0;\"><span style=\"font-size: 18px;\"><strong><span style=\"\">"+company_social_reason+"</span></strong></span></p>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<div align=\"center\" class=\"img-container center fixedwidth\" style=\"padding-right: 0px;padding-left: 0px;\">\n" +
                                                    "<img align=\"center\" alt=\"Alternate text\" border=\"0\" class=\"center fixedwidth\" src=\""+company_image+"\" style=\"text-decoration: none; -ms-interpolation-mode: bicubic; height: auto; border: 0; width: 100%; max-width: 136px; display: block;\" title=\"Alternate text\" width=\"136\"/>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<div class=\"col num6\" style=\"display: table-cell; vertical-align: top; max-width: 320px; min-width: 336px; width: 340px;\">\n" +
                                                    "<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                                                    "<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\">\n" +
                                                    "<div style=\"color:#000000;font-family:'Montserrat', 'Trebuchet MS', 'Lucida Grande', 'Lucida Sans Unicode', 'Lucida Sans', Tahoma, sans-serif;line-height:1.8;padding-top:10px;padding-right:30px;padding-bottom:5px;padding-left:30px;\">\n" +
                                                    "<div style=\"line-height: 1.8; font-size: 12px; font-family: 'Montserrat', 'Trebuchet MS', 'Lucida Grande', 'Lucida Sans Unicode', 'Lucida Sans', Tahoma, sans-serif; color: #000000; mso-line-height-alt: 22px;\">\n" +
                                                    "<p style=\"font-size: 14px; line-height: 1.8; word-break: break-word; text-align: left; font-family: Montserrat, 'Trebuchet MS', 'Lucida Grande', 'Lucida Sans Unicode', 'Lucida Sans', Tahoma, sans-serif; mso-line-height-alt: 25px; margin: 0;\"><strong><span style=\"font-size: 16px;\">Pago pendiente:</span></strong></p>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<div style=\"color:#000fff;font-family:'Montserrat', 'Trebuchet MS', 'Lucida Grande', 'Lucida Sans Unicode', 'Lucida Sans', Tahoma, sans-serif;line-height:1.8;padding-top:0px;padding-right:30px;padding-bottom:10px;padding-left:30px;\">\n" +
                                                    "<div style=\"line-height: 1.8; font-size: 12px; color: #000fff; font-family: 'Montserrat', 'Trebuchet MS', 'Lucida Grande', 'Lucida Sans Unicode', 'Lucida Sans', Tahoma, sans-serif; mso-line-height-alt: 22px;\">\n" +
                                                    "<p style=\"font-size: 26px; line-height: 1.8; word-break: break-word; text-align: left; mso-line-height-alt: 47px; margin: 0;\"><span style=\"font-size: 26px;\"><strong>S/ "+price_st+"</strong></span></p>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<div style=\"color:#000fff;font-family:'Montserrat', 'Trebuchet MS', 'Lucida Grande', 'Lucida Sans Unicode', 'Lucida Sans', Tahoma, sans-serif;line-height:1.8;padding-top:10px;padding-right:30px;padding-bottom:10px;padding-left:30px;\">\n" +
                                                    "<div style=\"line-height: 1.8; font-size: 12px; font-family: 'Montserrat', 'Trebuchet MS', 'Lucida Grande', 'Lucida Sans Unicode', 'Lucida Sans', Tahoma, sans-serif; color: #000fff; mso-line-height-alt: 22px;\">\n" +
                                                    "<p style=\"font-size: 14px; line-height: 1.8; word-break: break-word; text-align: left; font-family: Montserrat, 'Trebuchet MS', 'Lucida Grande', 'Lucida Sans Unicode', 'Lucida Sans', Tahoma, sans-serif; mso-line-height-alt: 25px; margin: 0;\"><strong><span style=\"font-size: 16px;\">Fecha de vencimiento</span></strong></p>\n" +
                                                    "<p style=\"font-size: 14px; line-height: 1.8; word-break: break-word; text-align: left; font-family: Montserrat, 'Trebuchet MS', 'Lucida Grande', 'Lucida Sans Unicode', 'Lucida Sans', Tahoma, sans-serif; mso-line-height-alt: 25px; margin: 0;\"><strong><span style=\"font-size: 16px;\">"+expiration_day+"/"+expiration_month+"/"+expiration_year+"</span></strong></p>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "\n" +
                                                    "</div>\n" +
                                                    "\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<div style=\"background-color:transparent;\">\n" +
                                                    "<div class=\"block-grid\" style=\"min-width: 320px; max-width: 680px; overflow-wrap: break-word; word-wrap: break-word; word-break: break-word; Margin: 0 auto; background-color: transparent;\">\n" +
                                                    "<div style=\"border-collapse: collapse;display: table;width: 100%;background-color:transparent;\">\n" +
                                                    "<div class=\"col num12\" style=\"min-width: 320px; max-width: 680px; display: table-cell; vertical-align: top; width: 680px;\">\n" +
                                                    "<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                                                    "<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\">\n" +
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
                                                    "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\" width=\"100%\">\n" +
                                                    "<tbody>\n" +
                                                    "<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                                                    "<td class=\"divider_inner\" style=\"word-break: break-word; vertical-align: top; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%; padding-top: 10px; padding-right: 10px; padding-bottom: 10px; padding-left: 10px;\" valign=\"top\">\n" +
                                                    "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider_content\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; border-top: 1px solid #BBBBBB; width: 100%;\" valign=\"top\" width=\"100%\">\n" +
                                                    "<tbody>\n" +
                                                    "<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                                                    "<td style=\"word-break: break-word; vertical-align: top; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\"><span></span></td>\n" +
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
                                                    "<!--[if (!mso)&(!IE)]><!-->\n" +
                                                    "</div>\n" +
                                                    "<!--<![endif]-->\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<div style=\"background-color:transparent;\">\n" +
                                                    "<div class=\"block-grid three-up\" style=\"min-width: 320px; max-width: 680px; overflow-wrap: break-word; word-wrap: break-word; word-break: break-word; Margin: 0 auto; background-color: #ffffff;\">\n" +
                                                    "<div style=\"border-collapse: collapse;display: table;width: 100%;background-color:#ffffff;\">\n" +
                                                    "<div class=\"col num3\" style=\"display: table-cell; vertical-align: top; max-width: 320px; min-width: 168px; width: 170px;\">\n" +
                                                    "<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                                                    "<!--[if (!mso)&(!IE)]><!-->\n" +
                                                    "<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 10px;\">\n" +
                                                    "<!--<![endif]-->\n" +
                                                    "<div class=\"mobile_hide\">\n" +
                                                    "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\" width=\"100%\">\n" +
                                                    "<tbody>\n" +
                                                    "<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                                                    "<td class=\"divider_inner\" style=\"word-break: break-word; vertical-align: top; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%; padding-top: 10px; padding-right: 10px; padding-bottom: 10px; padding-left: 10px;\" valign=\"top\">\n" +
                                                    "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider_content\" height=\"5\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; border-top: 0px solid transparent; height: 5px; width: 100%;\" valign=\"top\" width=\"100%\">\n" +
                                                    "<tbody>\n" +
                                                    "<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                                                    "<td height=\"5\" style=\"word-break: break-word; vertical-align: top; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\"><span></span></td>\n" +
                                                    "</tr>\n" +
                                                    "</tbody>\n" +
                                                    "</table>\n" +
                                                    "</td>\n" +
                                                    "</tr>\n" +
                                                    "</tbody>\n" +
                                                    "</table>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<div class=\"col num6\" style=\"display: table-cell; vertical-align: top; max-width: 320px; min-width: 336px; width: 340px;\">\n" +
                                                    "<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                                                    "<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\">\n" +
                                                    "\n" +
                                                    "<div style=\"color:#232323;font-family:Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif;line-height:1.2;padding-top:10px;padding-right:10px;padding-bottom:0px;padding-left:35px;\">\n" +
                                                    "<div style=\"line-height: 1.2; font-size: 12px; color: #232323; font-family: Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif; mso-line-height-alt: 14px;\">\n" +
                                                    "<p style=\"font-size: 14px; line-height: 1.2; word-break: break-word; mso-line-height-alt: 17px; margin: 0;\"><span style=\"font-size: 14px;\">Subtotal:</span></p>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<!--<![endif]-->\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "\n" +
                                                    "<div class=\"col num3\" style=\"display: table-cell; vertical-align: top; max-width: 320px; min-width: 168px; width: 170px;\">\n" +
                                                    "<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                                                    "<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\">\n" +
                                                    "<div style=\"color:#555555;font-family:Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif;line-height:1.2;padding-top:10px;padding-right:10px;padding-bottom:0px;padding-left:30px;\">\n" +
                                                    "<div style=\"line-height: 1.2; font-size: 12px; color: #555555; font-family: Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif; mso-line-height-alt: 14px;\">\n" +
                                                    "<p style=\"font-size: 14px; line-height: 1.2; word-break: break-word; mso-line-height-alt: 17px; margin: 0;\">S/ "+price_st+"</p>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<!--<![endif]-->\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<div style=\"background-color:transparent;\">\n" +
                                                    "<div class=\"block-grid three-up\" style=\"min-width: 320px; max-width: 680px; overflow-wrap: break-word; word-wrap: break-word; word-break: break-word; Margin: 0 auto; background-color: #ffffff;\">\n" +
                                                    "<div style=\"border-collapse: collapse;display: table;width: 100%;background-color:#ffffff;\">\n" +
                                                    "<div class=\"col num3\" style=\"display: table-cell; vertical-align: top; max-width: 320px; min-width: 168px; width: 170px;\">\n" +
                                                    "<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                                                    "<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 10px;\">\n" +
                                                    "<div class=\"mobile_hide\">\n" +
                                                    "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\" width=\"100%\">\n" +
                                                    "<tbody>\n" +
                                                    "<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                                                    "<td class=\"divider_inner\" style=\"word-break: break-word; vertical-align: top; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%; padding-top: 10px; padding-right: 10px; padding-bottom: 10px; padding-left: 10px;\" valign=\"top\">\n" +
                                                    "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider_content\" height=\"5\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; border-top: 0px solid transparent; height: 5px; width: 100%;\" valign=\"top\" width=\"100%\">\n" +
                                                    "<tbody>\n" +
                                                    "<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                                                    "<td height=\"5\" style=\"word-break: break-word; vertical-align: top; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\"><span></span></td>\n" +
                                                    "</tr>\n" +
                                                    "</tbody>\n" +
                                                    "</table>\n" +
                                                    "</td>\n" +
                                                    "</tr>\n" +
                                                    "</tbody>\n" +
                                                    "</table>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<!--<![endif]-->\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<div class=\"col num6\" style=\"display: table-cell; vertical-align: top; max-width: 320px; min-width: 336px; width: 340px;\">\n" +
                                                    "<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                                                    "<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\">\n" +
                                                    "<div style=\"color:#232323;font-family:Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif;line-height:1.2;padding-top:10px;padding-right:10px;padding-bottom:0px;padding-left:35px;\">\n" +
                                                    "<div style=\"line-height: 1.2; font-size: 12px; color: #232323; font-family: Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif; mso-line-height-alt: 14px;\">\n" +
                                                    "<p style=\"font-size: 14px; line-height: 1.2; word-break: break-word; mso-line-height-alt: 17px; margin: 0;\"><span style=\"font-size: 14px;\">Mora</span></p>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<div class=\"col num3\" style=\"display: table-cell; vertical-align: top; max-width: 320px; min-width: 168px; width: 170px;\">\n" +
                                                    "<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                                                    "<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\">\n" +
                                                    "<div style=\"color:#555555;font-family:Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif;line-height:1.2;padding-top:10px;padding-right:10px;padding-bottom:0px;padding-left:30px;\">\n" +
                                                    "<div style=\"line-height: 1.2; font-size: 12px; color: #555555; font-family: Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif; mso-line-height-alt: 14px;\">\n" +
                                                    "<p style=\"font-size: 14px; line-height: 1.2; word-break: break-word; mso-line-height-alt: 17px; margin: 0;\">$ 0.00</p>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<!--<![endif]-->\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<div style=\"background-color:transparent;\">\n" +
                                                    "<div class=\"block-grid three-up\" style=\"min-width: 320px; max-width: 680px; overflow-wrap: break-word; word-wrap: break-word; word-break: break-word; Margin: 0 auto; background-color: #ffffff;\">\n" +
                                                    "<div style=\"border-collapse: collapse;display: table;width: 100%;background-color:#ffffff;\">\n" +
                                                    "<div class=\"col num3\" style=\"display: table-cell; vertical-align: top; max-width: 320px; min-width: 168px; width: 170px;\">\n" +
                                                    "<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                                                    "<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 10px;\">\n" +
                                                    "<div class=\"mobile_hide\">\n" +
                                                    "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\" width=\"100%\">\n" +
                                                    "<tbody>\n" +
                                                    "<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                                                    "<td class=\"divider_inner\" style=\"word-break: break-word; vertical-align: top; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%; padding-top: 10px; padding-right: 10px; padding-bottom: 10px; padding-left: 10px;\" valign=\"top\">\n" +
                                                    "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider_content\" height=\"5\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; border-top: 0px solid transparent; height: 5px; width: 100%;\" valign=\"top\" width=\"100%\">\n" +
                                                    "<tbody>\n" +
                                                    "<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                                                    "<td height=\"5\" style=\"word-break: break-word; vertical-align: top; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\"><span></span></td>\n" +
                                                    "</tr>\n" +
                                                    "</tbody>\n" +
                                                    "</table>\n" +
                                                    "</td>\n" +
                                                    "</tr>\n" +
                                                    "</tbody>\n" +
                                                    "</table>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<div class=\"col num6\" style=\"display: table-cell; vertical-align: top; max-width: 320px; min-width: 336px; width: 340px;\">\n" +
                                                    "<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                                                    "<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\">\n" +
                                                    "<div style=\"color:#232323;font-family:Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif;line-height:1.2;padding-top:10px;padding-right:10px;padding-bottom:0px;padding-left:35px;\">\n" +
                                                    "<div style=\"line-height: 1.2; font-size: 12px; color: #232323; font-family: Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif; mso-line-height-alt: 14px;\">\n" +
                                                    "<p style=\"font-size: 18px; line-height: 1.2; word-break: break-word; mso-line-height-alt: 22px; margin: 0;\"><span style=\"font-size: 18px;\">Pago Total</span></p>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<!--<![endif]-->\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<div class=\"col num3\" style=\"display: table-cell; vertical-align: top; max-width: 320px; min-width: 168px; width: 170px;\">\n" +
                                                    "<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                                                    "<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\">\n" +
                                                    "<div style=\"color:#000fff;font-family:Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif;line-height:1.2;padding-top:10px;padding-right:10px;padding-bottom:0px;padding-left:30px;\">\n" +
                                                    "<div style=\"line-height: 1.2; font-size: 12px; color: #000fff; font-family: Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif; mso-line-height-alt: 14px;\">\n" +
                                                    "<p style=\"font-size: 18px; line-height: 1.2; word-break: break-word; mso-line-height-alt: 22px; margin: 0;\"><span style=\"font-size: 18px;\">S/ "+price_st+"</span></p>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<!--<![endif]-->\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<div style=\"background-color:transparent;\">\n" +
                                                    "<div class=\"block-grid\" style=\"min-width: 320px; max-width: 680px; overflow-wrap: break-word; word-wrap: break-word; word-break: break-word; Margin: 0 auto; background-color: transparent;\">\n" +
                                                    "<div style=\"border-collapse: collapse;display: table;width: 100%;background-color:transparent;\">\n" +
                                                    "<div class=\"col num12\" style=\"min-width: 320px; max-width: 680px; display: table-cell; vertical-align: top; width: 680px;\">\n" +
                                                    "<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                                                    "<!--[if (!mso)&(!IE)]><!-->\n" +
                                                    "<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\">\n" +
                                                    "<!--<![endif]-->\n" +
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
                                                    "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\" width=\"100%\">\n" +
                                                    "<tbody>\n" +
                                                    "<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                                                    "<td class=\"divider_inner\" style=\"word-break: break-word; vertical-align: top; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%; padding-top: 10px; padding-right: 10px; padding-bottom: 10px; padding-left: 10px;\" valign=\"top\">\n" +
                                                    "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider_content\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; border-top: 1px solid #BBBBBB; width: 100%;\" valign=\"top\" width=\"100%\">\n" +
                                                    "<tbody>\n" +
                                                    "<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                                                    "<td style=\"word-break: break-word; vertical-align: top; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\"><span></span></td>\n" +
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
                                                    "</div>\n" +
                                                    "<!--<![endif]-->\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<div style=\"background-color:#000fff;\">\n" +
                                                    "<div class=\"block-grid\" style=\"min-width: 320px; max-width: 680px; overflow-wrap: break-word; word-wrap: break-word; word-break: break-word; Margin: 0 auto; background-color: transparent;\">\n" +
                                                    "<div style=\"border-collapse: collapse;display: table;width: 100%;background-color:transparent;\">\n" +
                                                    "<div class=\"col num12\" style=\"min-width: 320px; max-width: 680px; display: table-cell; vertical-align: top; width: 680px;\">\n" +
                                                    "<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                                                    "<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\">\n" +
                                                    "<!--<![endif]-->\n" +
                                                    "<div class=\"mobile_hide\">\n" +
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
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<div style=\"background-color:#02025c;\">\n" +
                                                    "<div class=\"block-grid\" style=\"min-width: 320px; max-width: 680px; overflow-wrap: break-word; word-wrap: break-word; word-break: break-word; Margin: 0 auto; background-color: transparent;\">\n" +
                                                    "<div style=\"border-collapse: collapse;display: table;width: 100%;background-color:transparent;\">\n" +
                                                    "<div class=\"col num12\" style=\"min-width: 320px; max-width: 680px; display: table-cell; vertical-align: top; width: 680px;\">\n" +
                                                    "<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                                                    "<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\">\n" +
                                                    "<!--<![endif]-->\n" +
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
                                                    "<div style=\"color:#ffffff;font-family:Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif;line-height:1.5;padding-top:10px;padding-right:10px;padding-bottom:5px;padding-left:10px;\">\n" +
                                                    "<div style=\"line-height: 1.5; font-size: 12px; color: #ffffff; font-family: Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif; mso-line-height-alt: 18px;\">\n" +
                                                    "<p style=\"text-align: center; line-height: 1.5; word-break: break-word; mso-line-height-alt: 18px; margin: 0;\"><strong>This messasge was sent to <a href=\"mailto:email@example.com\" style=\"color: #d9f5fa;\" title=\"email@example.com\">email@example.com</a></strong></p>\n" +
                                                    "<p style=\"text-align: center; line-height: 1.5; word-break: break-word; mso-line-height-alt: 18px; margin: 0;\"><strong>If you would like to change your email, please <a href=\"http://www.example.com/\" rel=\"noopener\" style=\"color: #d9f5fa;\" target=\"_blank\">click here</a></strong></p>\n" +
                                                    "<p style=\"text-align: center; line-height: 1.5; word-break: break-word; mso-line-height-alt: 18px; margin: 0;\"><strong>If you no longer wish to receive e-mails from Company Name, <a href=\"http://www.example.com/\" rel=\"noopener\" style=\"color: #d9f5fa;\" target=\"_blank\">click here</a></strong></p>\n" +
                                                    "<p style=\"text-align: center; line-height: 1.5; word-break: break-word; mso-line-height-alt: 18px; margin: 0;\"><br/><strong>2020 © All Rights Reserved</strong></p>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<div style=\"background-color:transparent;\">\n" +
                                                    "<div class=\"block-grid two-up no-stack\" style=\"min-width: 320px; max-width: 680px; overflow-wrap: break-word; word-wrap: break-word; word-break: break-word; Margin: 0 auto; background-color: transparent;\">\n" +
                                                    "<div style=\"border-collapse: collapse;display: table;width: 100%;background-color:transparent;\">\n" +
                                                    "<div class=\"col num6\" style=\"display: table-cell; vertical-align: top; max-width: 320px; min-width: 336px; width: 340px;\">\n" +
                                                    "<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                                                    "\n" +
                                                    "<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:10px; padding-bottom:10px; padding-right: 0px; padding-left: 0px;\">\n" +
                                                    "\n" +
                                                    "\n" +
                                                    "</div>\n" +
                                                    "<!--<![endif]-->\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</td>\n" +
                                                    "</tr>\n" +
                                                    "</tbody>\n" +
                                                    "</body>\n" +
                                                    "</html>";
                                            JavaMailAPI javaMailAPI = new JavaMailAPI(getActivity(), customer_email,subject,message);
                                            javaMailAPI.execute();
                                        }


                                        @Override
                                        public void onCancelled(DatabaseError databaseError) {

                                        }
                                    });

                                }
                                if (expiration_days_ago >= 46 && expiration_days_ago <= 60)  {
                                    Object price = map.get("bill_amount");
                                    Object customer_id = map.get("customer_id");
                                    String customer_id_st = customer_id+"";
                                    final String price_st = price+"";

                                    companyRef.child(post_key).child("Customers").child(customer_id_st).addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {
                                            customer_name = dataSnapshot.child("customer_name").getValue().toString();
                                            customer_email = dataSnapshot.child("customer_email").getValue().toString();
                                            customer_phone = dataSnapshot.child("customer_phone").getValue().toString();

                                            sendEmail18();
                                        }

                                        private void sendEmail18() {
                                            String subject = customer_name+", Tienes una notificación de "+company_social_reason;
                                            String message = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional //EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" +
                                                    "\n" +
                                                    "<html xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\" xmlns:v=\"urn:schemas-microsoft-com:vml\">\n" +
                                                    "<head>\n" +
                                                    "\n" +
                                                    "<meta content=\"text/html; charset=utf-8\" http-equiv=\"Content-Type\"/>\n" +
                                                    "<meta content=\"width=device-width\" name=\"viewport\"/>\n" +
                                                    "<meta content=\"IE=edge\" http-equiv=\"X-UA-Compatible\"/>\n" +
                                                    "<title></title>\n" +
                                                    "<link href=\"https://fonts.googleapis.com/css?family=Montserrat\" rel=\"stylesheet\" type=\"text/css\"/>\n" +
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
                                                    "\t\t@media (max-width: 700px) {\n" +
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
                                                    "<body class=\"clean-body\" style=\"margin: 0; padding: 0; -webkit-text-size-adjust: 100%; background-color: #ffffff;\">\n" +
                                                    "<table bgcolor=\"#ffffff\" cellpadding=\"0\" cellspacing=\"0\" class=\"nl-container\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; min-width: 320px; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #ffffff; width: 100%;\" valign=\"top\" width=\"100%\">\n" +
                                                    "<tbody>\n" +
                                                    "<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                                                    "<td style=\"word-break: break-word; vertical-align: top;\" valign=\"top\">\n" +
                                                    "<div style=\"background-color:#000fff;\">\n" +
                                                    "<div class=\"block-grid\" style=\"min-width: 320px; max-width: 680px; overflow-wrap: break-word; word-wrap: break-word; word-break: break-word; Margin: 0 auto; background-color: transparent;\">\n" +
                                                    "<div style=\"border-collapse: collapse;display: table;width: 100%;background-color:transparent;\">\n" +
                                                    "<div class=\"col num12\" style=\"min-width: 320px; max-width: 680px; display: table-cell; vertical-align: top; width: 680px;\">\n" +
                                                    "<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                                                    "<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\">\n" +
                                                    "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\" width=\"100%\">\n" +
                                                    "<tbody>\n" +
                                                    "<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                                                    "<td class=\"divider_inner\" style=\"word-break: break-word; vertical-align: top; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%; padding-top: 5px; padding-right: 5px; padding-bottom: 5px; padding-left: 5px;\" valign=\"top\">\n" +
                                                    "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider_content\" height=\"25\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; border-top: 0px solid transparent; height: 25px; width: 100%;\" valign=\"top\" width=\"100%\">\n" +
                                                    "<tbody>\n" +
                                                    "<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                                                    "<td height=\"25\" style=\"word-break: break-word; vertical-align: top; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\"><span></span></td>\n" +
                                                    "</tr>\n" +
                                                    "</tbody>\n" +
                                                    "</table>\n" +
                                                    "</td>\n" +
                                                    "</tr>\n" +
                                                    "</tbody>\n" +
                                                    "</table>\n" +
                                                    "<div align=\"center\" class=\"img-container center fixedwidth\" style=\"padding-right: 0px;padding-left: 0px;\">\n" +
                                                    "<a href=\"http://www.example.com\" style=\"outline:none\" tabindex=\"-1\" target=\"_blank\"> <img align=\"center\" alt=\"Logo\" border=\"0\" class=\"center fixedwidth\" src=\"http://oliver.com.pe/wp-content/uploads/2020/12/Artboard-3.png\" style=\"text-decoration: none; -ms-interpolation-mode: bicubic; height: auto; border: 0; width: 100%; max-width: 170px; display: block;\" title=\"Logo\" width=\"170\"/></a>\n" +
                                                    "<div style=\"font-size:1px;line-height:15px\"> </div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<div style=\"background-color:#ffffff;\">\n" +
                                                    "<div class=\"block-grid\" style=\"min-width: 320px; max-width: 680px; overflow-wrap: break-word; word-wrap: break-word; word-break: break-word; Margin: 0 auto; background-color: transparent;\">\n" +
                                                    "<div style=\"border-collapse: collapse;display: table;width: 100%;background-color:transparent;\">\n" +
                                                    "<div class=\"col num12\" style=\"min-width: 320px; max-width: 680px; display: table-cell; vertical-align: top; width: 680px;\">\n" +
                                                    "<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                                                    "<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\">\n" +
                                                    "<div align=\"center\" class=\"img-container center fixedwidth\" style=\"padding-right: 0px;padding-left: 0px;\">\n" +
                                                    "<a href=\"www.example.com\" style=\"outline:none\" tabindex=\"-1\" target=\"_blank\"> <img align=\"center\" alt=\"Cool Burger Walking\" border=\"0\" class=\"center fixedwidth\" src=\"http://oliver.com.pe/wp-content/uploads/2020/12/Wallet.gif\" style=\"text-decoration: none; -ms-interpolation-mode: bicubic; height: auto; border: 0; width: 100%; max-width: 306px; display: block;\" title=\"Cool Burger Walking\" width=\"306\"/></a>\n" +
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
                                                    "<div style=\"color:#000fff;font-family:Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif;line-height:1.2;padding-top:10px;padding-right:10px;padding-bottom:15px;padding-left:10px;\">\n" +
                                                    "<div style=\"font-size: 14px; line-height: 1.2; color: #000fff; font-family: Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif; mso-line-height-alt: 17px;\">\n" +
                                                    "<p style=\"font-size: 14px; line-height: 1.2; word-break: break-word; text-align: center; mso-line-height-alt: 17px; margin: 0;\"><strong><span style=\"font-size: 38px;\">NOTIFICACIÓN DE COBRANZA</span></strong></p>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<div style=\"color:#000fff;font-family:'Montserrat', 'Trebuchet MS', 'Lucida Grande', 'Lucida Sans Unicode', 'Lucida Sans', Tahoma, sans-serif;line-height:1.8;padding-top:10px;padding-right:60px;padding-bottom:20px;padding-left:60px;\">\n" +
                                                    "<div style=\"line-height: 1.8; font-size: 12px; font-family: 'Montserrat', 'Trebuchet MS', 'Lucida Grande', 'Lucida Sans Unicode', 'Lucida Sans', Tahoma, sans-serif; color: #000fff; mso-line-height-alt: 22px;\">\n" +
                                                    "<p style=\"font-size: 22px; line-height: 1.8; text-align: center; word-break: break-word; font-family: Montserrat, 'Trebuchet MS', 'Lucida Grande', 'Lucida Sans Unicode', 'Lucida Sans', Tahoma, sans-serif; mso-line-height-alt: 40px; margin: 0;\"><span style=\"font-size: 22px;\">Hola "+customer_name+", te recordamos que tienes un pago pendiente VENCIDO. Porfavor te animamos a que puedas acercarte a regularizarlo para que sigamos atendiéndote y dándote el mejor servicio como siempre. ¡Te esperamos!<br/></span></p>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<div align=\"center\" class=\"button-container\" style=\"padding-top:10px;padding-right:10px;padding-bottom:10px;padding-left:10px;\">\n" +
                                                    "<a href=\"\" style=\"-webkit-text-size-adjust: none; text-decoration: none; display: inline-block; color: #ffffff; background-color: #0000ff; border-radius: 4px; -webkit-border-radius: 4px; -moz-border-radius: 4px; width: auto; width: auto; border-top: 0px solid #8a3b8f; border-right: 0px solid #8a3b8f; border-bottom: 0px solid #8a3b8f; border-left: 0px solid #8a3b8f; padding-top: 5px; padding-bottom: 5px; font-family: Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif; text-align: center; mso-border-alt: none; word-break: keep-all;\" target=\"_blank\"><span style=\"padding-left:40px;padding-right:40px;font-size:16px;display:inline-block;\"><span style=\"font-size: 16px; line-height: 2; word-break: break-word; mso-line-height-alt: 32px;\"><strong>VENCIDO HACE "+expiration_days_ago+" DÍAS"+"</strong></span></span></a>\n" +
                                                    "</div>\n" +
                                                    "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\" width=\"100%\">\n" +
                                                    "<tbody>\n" +
                                                    "<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                                                    "<td class=\"divider_inner\" style=\"word-break: break-word; vertical-align: top; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%; padding-top: 10px; padding-right: 10px; padding-bottom: 10px; padding-left: 10px;\" valign=\"top\">\n" +
                                                    "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider_content\" height=\"25\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; border-top: 0px solid transparent; height: 25px; width: 100%;\" valign=\"top\" width=\"100%\">\n" +
                                                    "<tbody>\n" +
                                                    "<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                                                    "<td height=\"25\" style=\"word-break: break-word; vertical-align: top; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\"><span></span></td>\n" +
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
                                                    "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider_content\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; border-top: 1px solid #BBBBBB; width: 100%;\" valign=\"top\" width=\"100%\">\n" +
                                                    "<tbody>\n" +
                                                    "<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                                                    "<td style=\"word-break: break-word; vertical-align: top; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\"><span></span></td>\n" +
                                                    "</tr>\n" +
                                                    "</tbody>\n" +
                                                    "</table>\n" +
                                                    "</td>\n" +
                                                    "</tr>\n" +
                                                    "</tbody>\n" +
                                                    "</table>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<div style=\"background-color:transparent;\">\n" +
                                                    "<div class=\"block-grid\" style=\"min-width: 320px; max-width: 680px; overflow-wrap: break-word; word-wrap: break-word; word-break: break-word; Margin: 0 auto; background-color: transparent;\">\n" +
                                                    "<div style=\"border-collapse: collapse;display: table;width: 100%;background-color:transparent;\">\n" +
                                                    "<div class=\"col num12\" style=\"min-width: 320px; max-width: 680px; display: table-cell; vertical-align: top; width: 680px;\">\n" +
                                                    "<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                                                    "<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\">\n" +
                                                    "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\" width=\"100%\">\n" +
                                                    "<tbody>\n" +
                                                    "<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                                                    "<td class=\"divider_inner\" style=\"word-break: break-word; vertical-align: top; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%; padding-top: 10px; padding-right: 10px; padding-bottom: 10px; padding-left: 10px;\" valign=\"top\">\n" +
                                                    "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider_content\" height=\"5\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; border-top: 0px solid transparent; height: 5px; width: 100%;\" valign=\"top\" width=\"100%\">\n" +
                                                    "<tbody>\n" +
                                                    "<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                                                    "<td height=\"5\" style=\"word-break: break-word; vertical-align: top; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\"><span></span></td>\n" +
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
                                                    "<div style=\"background-color:transparent;\">\n" +
                                                    "<div class=\"block-grid two-up\" style=\"min-width: 320px; max-width: 680px; overflow-wrap: break-word; word-wrap: break-word; word-break: break-word; Margin: 0 auto; background-color: transparent;\">\n" +
                                                    "<div style=\"border-collapse: collapse;display: table;width: 100%;background-color:transparent;\">\n" +
                                                    "<div class=\"col num6\" style=\"display: table-cell; vertical-align: top; max-width: 320px; min-width: 336px; width: 340px;\">\n" +
                                                    "<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                                                    "<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\">\n" +
                                                    "<div style=\"color:#000000;font-family:'Montserrat', 'Trebuchet MS', 'Lucida Grande', 'Lucida Sans Unicode', 'Lucida Sans', Tahoma, sans-serif;line-height:1.8;padding-top:10px;padding-right:30px;padding-bottom:5px;padding-left:30px;\">\n" +
                                                    "<div style=\"line-height: 1.8; font-size: 12px; font-family: 'Montserrat', 'Trebuchet MS', 'Lucida Grande', 'Lucida Sans Unicode', 'Lucida Sans', Tahoma, sans-serif; color: #000000; mso-line-height-alt: 22px;\">\n" +
                                                    "<p style=\"font-size: 18px; line-height: 1.8; word-break: break-word; text-align: center; font-family: Montserrat, 'Trebuchet MS', 'Lucida Grande', 'Lucida Sans Unicode', 'Lucida Sans', Tahoma, sans-serif; mso-line-height-alt: 32px; margin: 0;\"><span style=\"font-size: 18px;\"><strong><span style=\"\">"+company_social_reason+"</span></strong></span></p>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<div align=\"center\" class=\"img-container center fixedwidth\" style=\"padding-right: 0px;padding-left: 0px;\">\n" +
                                                    "<img align=\"center\" alt=\"Alternate text\" border=\"0\" class=\"center fixedwidth\" src=\""+company_image+"\" style=\"text-decoration: none; -ms-interpolation-mode: bicubic; height: auto; border: 0; width: 100%; max-width: 136px; display: block;\" title=\"Alternate text\" width=\"136\"/>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<div class=\"col num6\" style=\"display: table-cell; vertical-align: top; max-width: 320px; min-width: 336px; width: 340px;\">\n" +
                                                    "<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                                                    "<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\">\n" +
                                                    "<div style=\"color:#000000;font-family:'Montserrat', 'Trebuchet MS', 'Lucida Grande', 'Lucida Sans Unicode', 'Lucida Sans', Tahoma, sans-serif;line-height:1.8;padding-top:10px;padding-right:30px;padding-bottom:5px;padding-left:30px;\">\n" +
                                                    "<div style=\"line-height: 1.8; font-size: 12px; font-family: 'Montserrat', 'Trebuchet MS', 'Lucida Grande', 'Lucida Sans Unicode', 'Lucida Sans', Tahoma, sans-serif; color: #000000; mso-line-height-alt: 22px;\">\n" +
                                                    "<p style=\"font-size: 14px; line-height: 1.8; word-break: break-word; text-align: left; font-family: Montserrat, 'Trebuchet MS', 'Lucida Grande', 'Lucida Sans Unicode', 'Lucida Sans', Tahoma, sans-serif; mso-line-height-alt: 25px; margin: 0;\"><strong><span style=\"font-size: 16px;\">Pago pendiente:</span></strong></p>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<div style=\"color:#000fff;font-family:'Montserrat', 'Trebuchet MS', 'Lucida Grande', 'Lucida Sans Unicode', 'Lucida Sans', Tahoma, sans-serif;line-height:1.8;padding-top:0px;padding-right:30px;padding-bottom:10px;padding-left:30px;\">\n" +
                                                    "<div style=\"line-height: 1.8; font-size: 12px; color: #000fff; font-family: 'Montserrat', 'Trebuchet MS', 'Lucida Grande', 'Lucida Sans Unicode', 'Lucida Sans', Tahoma, sans-serif; mso-line-height-alt: 22px;\">\n" +
                                                    "<p style=\"font-size: 26px; line-height: 1.8; word-break: break-word; text-align: left; mso-line-height-alt: 47px; margin: 0;\"><span style=\"font-size: 26px;\"><strong>S/ "+price_st+"</strong></span></p>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<div style=\"color:#000fff;font-family:'Montserrat', 'Trebuchet MS', 'Lucida Grande', 'Lucida Sans Unicode', 'Lucida Sans', Tahoma, sans-serif;line-height:1.8;padding-top:10px;padding-right:30px;padding-bottom:10px;padding-left:30px;\">\n" +
                                                    "<div style=\"line-height: 1.8; font-size: 12px; font-family: 'Montserrat', 'Trebuchet MS', 'Lucida Grande', 'Lucida Sans Unicode', 'Lucida Sans', Tahoma, sans-serif; color: #000fff; mso-line-height-alt: 22px;\">\n" +
                                                    "<p style=\"font-size: 14px; line-height: 1.8; word-break: break-word; text-align: left; font-family: Montserrat, 'Trebuchet MS', 'Lucida Grande', 'Lucida Sans Unicode', 'Lucida Sans', Tahoma, sans-serif; mso-line-height-alt: 25px; margin: 0;\"><strong><span style=\"font-size: 16px;\">Fecha de vencimiento</span></strong></p>\n" +
                                                    "<p style=\"font-size: 14px; line-height: 1.8; word-break: break-word; text-align: left; font-family: Montserrat, 'Trebuchet MS', 'Lucida Grande', 'Lucida Sans Unicode', 'Lucida Sans', Tahoma, sans-serif; mso-line-height-alt: 25px; margin: 0;\"><strong><span style=\"font-size: 16px;\">"+expiration_day+"/"+expiration_month+"/"+expiration_year+"</span></strong></p>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "\n" +
                                                    "</div>\n" +
                                                    "\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<div style=\"background-color:transparent;\">\n" +
                                                    "<div class=\"block-grid\" style=\"min-width: 320px; max-width: 680px; overflow-wrap: break-word; word-wrap: break-word; word-break: break-word; Margin: 0 auto; background-color: transparent;\">\n" +
                                                    "<div style=\"border-collapse: collapse;display: table;width: 100%;background-color:transparent;\">\n" +
                                                    "<div class=\"col num12\" style=\"min-width: 320px; max-width: 680px; display: table-cell; vertical-align: top; width: 680px;\">\n" +
                                                    "<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                                                    "<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\">\n" +
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
                                                    "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\" width=\"100%\">\n" +
                                                    "<tbody>\n" +
                                                    "<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                                                    "<td class=\"divider_inner\" style=\"word-break: break-word; vertical-align: top; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%; padding-top: 10px; padding-right: 10px; padding-bottom: 10px; padding-left: 10px;\" valign=\"top\">\n" +
                                                    "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider_content\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; border-top: 1px solid #BBBBBB; width: 100%;\" valign=\"top\" width=\"100%\">\n" +
                                                    "<tbody>\n" +
                                                    "<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                                                    "<td style=\"word-break: break-word; vertical-align: top; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\"><span></span></td>\n" +
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
                                                    "<!--[if (!mso)&(!IE)]><!-->\n" +
                                                    "</div>\n" +
                                                    "<!--<![endif]-->\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<div style=\"background-color:transparent;\">\n" +
                                                    "<div class=\"block-grid three-up\" style=\"min-width: 320px; max-width: 680px; overflow-wrap: break-word; word-wrap: break-word; word-break: break-word; Margin: 0 auto; background-color: #ffffff;\">\n" +
                                                    "<div style=\"border-collapse: collapse;display: table;width: 100%;background-color:#ffffff;\">\n" +
                                                    "<div class=\"col num3\" style=\"display: table-cell; vertical-align: top; max-width: 320px; min-width: 168px; width: 170px;\">\n" +
                                                    "<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                                                    "<!--[if (!mso)&(!IE)]><!-->\n" +
                                                    "<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 10px;\">\n" +
                                                    "<!--<![endif]-->\n" +
                                                    "<div class=\"mobile_hide\">\n" +
                                                    "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\" width=\"100%\">\n" +
                                                    "<tbody>\n" +
                                                    "<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                                                    "<td class=\"divider_inner\" style=\"word-break: break-word; vertical-align: top; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%; padding-top: 10px; padding-right: 10px; padding-bottom: 10px; padding-left: 10px;\" valign=\"top\">\n" +
                                                    "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider_content\" height=\"5\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; border-top: 0px solid transparent; height: 5px; width: 100%;\" valign=\"top\" width=\"100%\">\n" +
                                                    "<tbody>\n" +
                                                    "<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                                                    "<td height=\"5\" style=\"word-break: break-word; vertical-align: top; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\"><span></span></td>\n" +
                                                    "</tr>\n" +
                                                    "</tbody>\n" +
                                                    "</table>\n" +
                                                    "</td>\n" +
                                                    "</tr>\n" +
                                                    "</tbody>\n" +
                                                    "</table>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<div class=\"col num6\" style=\"display: table-cell; vertical-align: top; max-width: 320px; min-width: 336px; width: 340px;\">\n" +
                                                    "<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                                                    "<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\">\n" +
                                                    "\n" +
                                                    "<div style=\"color:#232323;font-family:Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif;line-height:1.2;padding-top:10px;padding-right:10px;padding-bottom:0px;padding-left:35px;\">\n" +
                                                    "<div style=\"line-height: 1.2; font-size: 12px; color: #232323; font-family: Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif; mso-line-height-alt: 14px;\">\n" +
                                                    "<p style=\"font-size: 14px; line-height: 1.2; word-break: break-word; mso-line-height-alt: 17px; margin: 0;\"><span style=\"font-size: 14px;\">Subtotal:</span></p>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<!--<![endif]-->\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "\n" +
                                                    "<div class=\"col num3\" style=\"display: table-cell; vertical-align: top; max-width: 320px; min-width: 168px; width: 170px;\">\n" +
                                                    "<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                                                    "<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\">\n" +
                                                    "<div style=\"color:#555555;font-family:Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif;line-height:1.2;padding-top:10px;padding-right:10px;padding-bottom:0px;padding-left:30px;\">\n" +
                                                    "<div style=\"line-height: 1.2; font-size: 12px; color: #555555; font-family: Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif; mso-line-height-alt: 14px;\">\n" +
                                                    "<p style=\"font-size: 14px; line-height: 1.2; word-break: break-word; mso-line-height-alt: 17px; margin: 0;\">S/ "+price_st+"</p>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<!--<![endif]-->\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<div style=\"background-color:transparent;\">\n" +
                                                    "<div class=\"block-grid three-up\" style=\"min-width: 320px; max-width: 680px; overflow-wrap: break-word; word-wrap: break-word; word-break: break-word; Margin: 0 auto; background-color: #ffffff;\">\n" +
                                                    "<div style=\"border-collapse: collapse;display: table;width: 100%;background-color:#ffffff;\">\n" +
                                                    "<div class=\"col num3\" style=\"display: table-cell; vertical-align: top; max-width: 320px; min-width: 168px; width: 170px;\">\n" +
                                                    "<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                                                    "<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 10px;\">\n" +
                                                    "<div class=\"mobile_hide\">\n" +
                                                    "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\" width=\"100%\">\n" +
                                                    "<tbody>\n" +
                                                    "<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                                                    "<td class=\"divider_inner\" style=\"word-break: break-word; vertical-align: top; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%; padding-top: 10px; padding-right: 10px; padding-bottom: 10px; padding-left: 10px;\" valign=\"top\">\n" +
                                                    "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider_content\" height=\"5\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; border-top: 0px solid transparent; height: 5px; width: 100%;\" valign=\"top\" width=\"100%\">\n" +
                                                    "<tbody>\n" +
                                                    "<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                                                    "<td height=\"5\" style=\"word-break: break-word; vertical-align: top; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\"><span></span></td>\n" +
                                                    "</tr>\n" +
                                                    "</tbody>\n" +
                                                    "</table>\n" +
                                                    "</td>\n" +
                                                    "</tr>\n" +
                                                    "</tbody>\n" +
                                                    "</table>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<!--<![endif]-->\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<div class=\"col num6\" style=\"display: table-cell; vertical-align: top; max-width: 320px; min-width: 336px; width: 340px;\">\n" +
                                                    "<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                                                    "<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\">\n" +
                                                    "<div style=\"color:#232323;font-family:Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif;line-height:1.2;padding-top:10px;padding-right:10px;padding-bottom:0px;padding-left:35px;\">\n" +
                                                    "<div style=\"line-height: 1.2; font-size: 12px; color: #232323; font-family: Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif; mso-line-height-alt: 14px;\">\n" +
                                                    "<p style=\"font-size: 14px; line-height: 1.2; word-break: break-word; mso-line-height-alt: 17px; margin: 0;\"><span style=\"font-size: 14px;\">Mora</span></p>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<div class=\"col num3\" style=\"display: table-cell; vertical-align: top; max-width: 320px; min-width: 168px; width: 170px;\">\n" +
                                                    "<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                                                    "<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\">\n" +
                                                    "<div style=\"color:#555555;font-family:Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif;line-height:1.2;padding-top:10px;padding-right:10px;padding-bottom:0px;padding-left:30px;\">\n" +
                                                    "<div style=\"line-height: 1.2; font-size: 12px; color: #555555; font-family: Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif; mso-line-height-alt: 14px;\">\n" +
                                                    "<p style=\"font-size: 14px; line-height: 1.2; word-break: break-word; mso-line-height-alt: 17px; margin: 0;\">$ 0.00</p>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<!--<![endif]-->\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<div style=\"background-color:transparent;\">\n" +
                                                    "<div class=\"block-grid three-up\" style=\"min-width: 320px; max-width: 680px; overflow-wrap: break-word; word-wrap: break-word; word-break: break-word; Margin: 0 auto; background-color: #ffffff;\">\n" +
                                                    "<div style=\"border-collapse: collapse;display: table;width: 100%;background-color:#ffffff;\">\n" +
                                                    "<div class=\"col num3\" style=\"display: table-cell; vertical-align: top; max-width: 320px; min-width: 168px; width: 170px;\">\n" +
                                                    "<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                                                    "<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 10px;\">\n" +
                                                    "<div class=\"mobile_hide\">\n" +
                                                    "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\" width=\"100%\">\n" +
                                                    "<tbody>\n" +
                                                    "<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                                                    "<td class=\"divider_inner\" style=\"word-break: break-word; vertical-align: top; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%; padding-top: 10px; padding-right: 10px; padding-bottom: 10px; padding-left: 10px;\" valign=\"top\">\n" +
                                                    "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider_content\" height=\"5\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; border-top: 0px solid transparent; height: 5px; width: 100%;\" valign=\"top\" width=\"100%\">\n" +
                                                    "<tbody>\n" +
                                                    "<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                                                    "<td height=\"5\" style=\"word-break: break-word; vertical-align: top; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\"><span></span></td>\n" +
                                                    "</tr>\n" +
                                                    "</tbody>\n" +
                                                    "</table>\n" +
                                                    "</td>\n" +
                                                    "</tr>\n" +
                                                    "</tbody>\n" +
                                                    "</table>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<div class=\"col num6\" style=\"display: table-cell; vertical-align: top; max-width: 320px; min-width: 336px; width: 340px;\">\n" +
                                                    "<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                                                    "<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\">\n" +
                                                    "<div style=\"color:#232323;font-family:Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif;line-height:1.2;padding-top:10px;padding-right:10px;padding-bottom:0px;padding-left:35px;\">\n" +
                                                    "<div style=\"line-height: 1.2; font-size: 12px; color: #232323; font-family: Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif; mso-line-height-alt: 14px;\">\n" +
                                                    "<p style=\"font-size: 18px; line-height: 1.2; word-break: break-word; mso-line-height-alt: 22px; margin: 0;\"><span style=\"font-size: 18px;\">Pago Total</span></p>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<!--<![endif]-->\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<div class=\"col num3\" style=\"display: table-cell; vertical-align: top; max-width: 320px; min-width: 168px; width: 170px;\">\n" +
                                                    "<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                                                    "<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\">\n" +
                                                    "<div style=\"color:#000fff;font-family:Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif;line-height:1.2;padding-top:10px;padding-right:10px;padding-bottom:0px;padding-left:30px;\">\n" +
                                                    "<div style=\"line-height: 1.2; font-size: 12px; color: #000fff; font-family: Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif; mso-line-height-alt: 14px;\">\n" +
                                                    "<p style=\"font-size: 18px; line-height: 1.2; word-break: break-word; mso-line-height-alt: 22px; margin: 0;\"><span style=\"font-size: 18px;\">S/ "+price_st+"</span></p>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<!--<![endif]-->\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<div style=\"background-color:transparent;\">\n" +
                                                    "<div class=\"block-grid\" style=\"min-width: 320px; max-width: 680px; overflow-wrap: break-word; word-wrap: break-word; word-break: break-word; Margin: 0 auto; background-color: transparent;\">\n" +
                                                    "<div style=\"border-collapse: collapse;display: table;width: 100%;background-color:transparent;\">\n" +
                                                    "<div class=\"col num12\" style=\"min-width: 320px; max-width: 680px; display: table-cell; vertical-align: top; width: 680px;\">\n" +
                                                    "<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                                                    "<!--[if (!mso)&(!IE)]><!-->\n" +
                                                    "<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\">\n" +
                                                    "<!--<![endif]-->\n" +
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
                                                    "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\" width=\"100%\">\n" +
                                                    "<tbody>\n" +
                                                    "<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                                                    "<td class=\"divider_inner\" style=\"word-break: break-word; vertical-align: top; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%; padding-top: 10px; padding-right: 10px; padding-bottom: 10px; padding-left: 10px;\" valign=\"top\">\n" +
                                                    "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider_content\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; border-top: 1px solid #BBBBBB; width: 100%;\" valign=\"top\" width=\"100%\">\n" +
                                                    "<tbody>\n" +
                                                    "<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                                                    "<td style=\"word-break: break-word; vertical-align: top; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\"><span></span></td>\n" +
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
                                                    "</div>\n" +
                                                    "<!--<![endif]-->\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<div style=\"background-color:#000fff;\">\n" +
                                                    "<div class=\"block-grid\" style=\"min-width: 320px; max-width: 680px; overflow-wrap: break-word; word-wrap: break-word; word-break: break-word; Margin: 0 auto; background-color: transparent;\">\n" +
                                                    "<div style=\"border-collapse: collapse;display: table;width: 100%;background-color:transparent;\">\n" +
                                                    "<div class=\"col num12\" style=\"min-width: 320px; max-width: 680px; display: table-cell; vertical-align: top; width: 680px;\">\n" +
                                                    "<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                                                    "<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\">\n" +
                                                    "<!--<![endif]-->\n" +
                                                    "<div class=\"mobile_hide\">\n" +
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
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<div style=\"background-color:#02025c;\">\n" +
                                                    "<div class=\"block-grid\" style=\"min-width: 320px; max-width: 680px; overflow-wrap: break-word; word-wrap: break-word; word-break: break-word; Margin: 0 auto; background-color: transparent;\">\n" +
                                                    "<div style=\"border-collapse: collapse;display: table;width: 100%;background-color:transparent;\">\n" +
                                                    "<div class=\"col num12\" style=\"min-width: 320px; max-width: 680px; display: table-cell; vertical-align: top; width: 680px;\">\n" +
                                                    "<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                                                    "<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\">\n" +
                                                    "<!--<![endif]-->\n" +
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
                                                    "<div style=\"color:#ffffff;font-family:Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif;line-height:1.5;padding-top:10px;padding-right:10px;padding-bottom:5px;padding-left:10px;\">\n" +
                                                    "<div style=\"line-height: 1.5; font-size: 12px; color: #ffffff; font-family: Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif; mso-line-height-alt: 18px;\">\n" +
                                                    "<p style=\"text-align: center; line-height: 1.5; word-break: break-word; mso-line-height-alt: 18px; margin: 0;\"><strong>This messasge was sent to <a href=\"mailto:email@example.com\" style=\"color: #d9f5fa;\" title=\"email@example.com\">email@example.com</a></strong></p>\n" +
                                                    "<p style=\"text-align: center; line-height: 1.5; word-break: break-word; mso-line-height-alt: 18px; margin: 0;\"><strong>If you would like to change your email, please <a href=\"http://www.example.com/\" rel=\"noopener\" style=\"color: #d9f5fa;\" target=\"_blank\">click here</a></strong></p>\n" +
                                                    "<p style=\"text-align: center; line-height: 1.5; word-break: break-word; mso-line-height-alt: 18px; margin: 0;\"><strong>If you no longer wish to receive e-mails from Company Name, <a href=\"http://www.example.com/\" rel=\"noopener\" style=\"color: #d9f5fa;\" target=\"_blank\">click here</a></strong></p>\n" +
                                                    "<p style=\"text-align: center; line-height: 1.5; word-break: break-word; mso-line-height-alt: 18px; margin: 0;\"><br/><strong>2020 © All Rights Reserved</strong></p>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<div style=\"background-color:transparent;\">\n" +
                                                    "<div class=\"block-grid two-up no-stack\" style=\"min-width: 320px; max-width: 680px; overflow-wrap: break-word; word-wrap: break-word; word-break: break-word; Margin: 0 auto; background-color: transparent;\">\n" +
                                                    "<div style=\"border-collapse: collapse;display: table;width: 100%;background-color:transparent;\">\n" +
                                                    "<div class=\"col num6\" style=\"display: table-cell; vertical-align: top; max-width: 320px; min-width: 336px; width: 340px;\">\n" +
                                                    "<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                                                    "\n" +
                                                    "<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:10px; padding-bottom:10px; padding-right: 0px; padding-left: 0px;\">\n" +
                                                    "\n" +
                                                    "\n" +
                                                    "</div>\n" +
                                                    "<!--<![endif]-->\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</td>\n" +
                                                    "</tr>\n" +
                                                    "</tbody>\n" +
                                                    "</body>\n" +
                                                    "</html>";
                                            JavaMailAPI javaMailAPI = new JavaMailAPI(getActivity(), customer_email,subject,message);
                                            javaMailAPI.execute();
                                        }


                                        @Override
                                        public void onCancelled(DatabaseError databaseError) {

                                        }
                                    });

                                }
                                if (expiration_days_ago >= 61)  {
                                    Object price = map.get("bill_amount");
                                    Object customer_id = map.get("customer_id");
                                    String customer_id_st = customer_id+"";
                                    final String price_st = price+"";

                                    companyRef.child(post_key).child("Customers").child(customer_id_st).addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {
                                            customer_name = dataSnapshot.child("customer_name").getValue().toString();
                                            customer_email = dataSnapshot.child("customer_email").getValue().toString();
                                            customer_phone = dataSnapshot.child("customer_phone").getValue().toString();

                                            sendEmail18();
                                        }

                                        private void sendEmail18() {
                                            String subject = customer_name+", Tienes una notificación de "+company_social_reason;
                                            String message = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional //EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" +
                                                    "\n" +
                                                    "<html xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\" xmlns:v=\"urn:schemas-microsoft-com:vml\">\n" +
                                                    "<head>\n" +
                                                    "\n" +
                                                    "<meta content=\"text/html; charset=utf-8\" http-equiv=\"Content-Type\"/>\n" +
                                                    "<meta content=\"width=device-width\" name=\"viewport\"/>\n" +
                                                    "<meta content=\"IE=edge\" http-equiv=\"X-UA-Compatible\"/>\n" +
                                                    "<title></title>\n" +
                                                    "<link href=\"https://fonts.googleapis.com/css?family=Montserrat\" rel=\"stylesheet\" type=\"text/css\"/>\n" +
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
                                                    "\t\t@media (max-width: 700px) {\n" +
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
                                                    "<body class=\"clean-body\" style=\"margin: 0; padding: 0; -webkit-text-size-adjust: 100%; background-color: #ffffff;\">\n" +
                                                    "<table bgcolor=\"#ffffff\" cellpadding=\"0\" cellspacing=\"0\" class=\"nl-container\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; min-width: 320px; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #ffffff; width: 100%;\" valign=\"top\" width=\"100%\">\n" +
                                                    "<tbody>\n" +
                                                    "<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                                                    "<td style=\"word-break: break-word; vertical-align: top;\" valign=\"top\">\n" +
                                                    "<div style=\"background-color:#000fff;\">\n" +
                                                    "<div class=\"block-grid\" style=\"min-width: 320px; max-width: 680px; overflow-wrap: break-word; word-wrap: break-word; word-break: break-word; Margin: 0 auto; background-color: transparent;\">\n" +
                                                    "<div style=\"border-collapse: collapse;display: table;width: 100%;background-color:transparent;\">\n" +
                                                    "<div class=\"col num12\" style=\"min-width: 320px; max-width: 680px; display: table-cell; vertical-align: top; width: 680px;\">\n" +
                                                    "<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                                                    "<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\">\n" +
                                                    "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\" width=\"100%\">\n" +
                                                    "<tbody>\n" +
                                                    "<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                                                    "<td class=\"divider_inner\" style=\"word-break: break-word; vertical-align: top; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%; padding-top: 5px; padding-right: 5px; padding-bottom: 5px; padding-left: 5px;\" valign=\"top\">\n" +
                                                    "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider_content\" height=\"25\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; border-top: 0px solid transparent; height: 25px; width: 100%;\" valign=\"top\" width=\"100%\">\n" +
                                                    "<tbody>\n" +
                                                    "<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                                                    "<td height=\"25\" style=\"word-break: break-word; vertical-align: top; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\"><span></span></td>\n" +
                                                    "</tr>\n" +
                                                    "</tbody>\n" +
                                                    "</table>\n" +
                                                    "</td>\n" +
                                                    "</tr>\n" +
                                                    "</tbody>\n" +
                                                    "</table>\n" +
                                                    "<div align=\"center\" class=\"img-container center fixedwidth\" style=\"padding-right: 0px;padding-left: 0px;\">\n" +
                                                    "<a href=\"http://www.example.com\" style=\"outline:none\" tabindex=\"-1\" target=\"_blank\"> <img align=\"center\" alt=\"Logo\" border=\"0\" class=\"center fixedwidth\" src=\"http://oliver.com.pe/wp-content/uploads/2020/12/Artboard-3.png\" style=\"text-decoration: none; -ms-interpolation-mode: bicubic; height: auto; border: 0; width: 100%; max-width: 170px; display: block;\" title=\"Logo\" width=\"170\"/></a>\n" +
                                                    "<div style=\"font-size:1px;line-height:15px\"> </div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<div style=\"background-color:#ffffff;\">\n" +
                                                    "<div class=\"block-grid\" style=\"min-width: 320px; max-width: 680px; overflow-wrap: break-word; word-wrap: break-word; word-break: break-word; Margin: 0 auto; background-color: transparent;\">\n" +
                                                    "<div style=\"border-collapse: collapse;display: table;width: 100%;background-color:transparent;\">\n" +
                                                    "<div class=\"col num12\" style=\"min-width: 320px; max-width: 680px; display: table-cell; vertical-align: top; width: 680px;\">\n" +
                                                    "<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                                                    "<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\">\n" +
                                                    "<div align=\"center\" class=\"img-container center fixedwidth\" style=\"padding-right: 0px;padding-left: 0px;\">\n" +
                                                    "<a href=\"www.example.com\" style=\"outline:none\" tabindex=\"-1\" target=\"_blank\"> <img align=\"center\" alt=\"Cool Burger Walking\" border=\"0\" class=\"center fixedwidth\" src=\"http://oliver.com.pe/wp-content/uploads/2020/12/Wallet.gif\" style=\"text-decoration: none; -ms-interpolation-mode: bicubic; height: auto; border: 0; width: 100%; max-width: 306px; display: block;\" title=\"Cool Burger Walking\" width=\"306\"/></a>\n" +
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
                                                    "<div style=\"color:#000fff;font-family:Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif;line-height:1.2;padding-top:10px;padding-right:10px;padding-bottom:15px;padding-left:10px;\">\n" +
                                                    "<div style=\"font-size: 14px; line-height: 1.2; color: #000fff; font-family: Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif; mso-line-height-alt: 17px;\">\n" +
                                                    "<p style=\"font-size: 14px; line-height: 1.2; word-break: break-word; text-align: center; mso-line-height-alt: 17px; margin: 0;\"><strong><span style=\"font-size: 38px;\">NOTIFICACIÓN DE COBRANZA</span></strong></p>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<div style=\"color:#000fff;font-family:'Montserrat', 'Trebuchet MS', 'Lucida Grande', 'Lucida Sans Unicode', 'Lucida Sans', Tahoma, sans-serif;line-height:1.8;padding-top:10px;padding-right:60px;padding-bottom:20px;padding-left:60px;\">\n" +
                                                    "<div style=\"line-height: 1.8; font-size: 12px; font-family: 'Montserrat', 'Trebuchet MS', 'Lucida Grande', 'Lucida Sans Unicode', 'Lucida Sans', Tahoma, sans-serif; color: #000fff; mso-line-height-alt: 22px;\">\n" +
                                                    "<p style=\"font-size: 22px; line-height: 1.8; text-align: center; word-break: break-word; font-family: Montserrat, 'Trebuchet MS', 'Lucida Grande', 'Lucida Sans Unicode', 'Lucida Sans', Tahoma, sans-serif; mso-line-height-alt: 40px; margin: 0;\"><span style=\"font-size: 22px;\">Hola "+customer_name+", te recordamos que tienes un pago pendiente VENCIDO. Porfavor te animamos a que puedas acercarte a regularizarlo para que sigamos atendiéndote y dándote el mejor servicio como siempre. ¡Te esperamos!<br/></span></p>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<div align=\"center\" class=\"button-container\" style=\"padding-top:10px;padding-right:10px;padding-bottom:10px;padding-left:10px;\">\n" +
                                                    "<a href=\"\" style=\"-webkit-text-size-adjust: none; text-decoration: none; display: inline-block; color: #ffffff; background-color: #0000ff; border-radius: 4px; -webkit-border-radius: 4px; -moz-border-radius: 4px; width: auto; width: auto; border-top: 0px solid #8a3b8f; border-right: 0px solid #8a3b8f; border-bottom: 0px solid #8a3b8f; border-left: 0px solid #8a3b8f; padding-top: 5px; padding-bottom: 5px; font-family: Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif; text-align: center; mso-border-alt: none; word-break: keep-all;\" target=\"_blank\"><span style=\"padding-left:40px;padding-right:40px;font-size:16px;display:inline-block;\"><span style=\"font-size: 16px; line-height: 2; word-break: break-word; mso-line-height-alt: 32px;\"><strong>VENCIDO HACE "+expiration_days_ago+" DÍAS"+"</strong></span></span></a>\n" +
                                                    "</div>\n" +
                                                    "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\" width=\"100%\">\n" +
                                                    "<tbody>\n" +
                                                    "<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                                                    "<td class=\"divider_inner\" style=\"word-break: break-word; vertical-align: top; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%; padding-top: 10px; padding-right: 10px; padding-bottom: 10px; padding-left: 10px;\" valign=\"top\">\n" +
                                                    "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider_content\" height=\"25\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; border-top: 0px solid transparent; height: 25px; width: 100%;\" valign=\"top\" width=\"100%\">\n" +
                                                    "<tbody>\n" +
                                                    "<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                                                    "<td height=\"25\" style=\"word-break: break-word; vertical-align: top; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\"><span></span></td>\n" +
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
                                                    "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider_content\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; border-top: 1px solid #BBBBBB; width: 100%;\" valign=\"top\" width=\"100%\">\n" +
                                                    "<tbody>\n" +
                                                    "<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                                                    "<td style=\"word-break: break-word; vertical-align: top; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\"><span></span></td>\n" +
                                                    "</tr>\n" +
                                                    "</tbody>\n" +
                                                    "</table>\n" +
                                                    "</td>\n" +
                                                    "</tr>\n" +
                                                    "</tbody>\n" +
                                                    "</table>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<div style=\"background-color:transparent;\">\n" +
                                                    "<div class=\"block-grid\" style=\"min-width: 320px; max-width: 680px; overflow-wrap: break-word; word-wrap: break-word; word-break: break-word; Margin: 0 auto; background-color: transparent;\">\n" +
                                                    "<div style=\"border-collapse: collapse;display: table;width: 100%;background-color:transparent;\">\n" +
                                                    "<div class=\"col num12\" style=\"min-width: 320px; max-width: 680px; display: table-cell; vertical-align: top; width: 680px;\">\n" +
                                                    "<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                                                    "<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\">\n" +
                                                    "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\" width=\"100%\">\n" +
                                                    "<tbody>\n" +
                                                    "<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                                                    "<td class=\"divider_inner\" style=\"word-break: break-word; vertical-align: top; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%; padding-top: 10px; padding-right: 10px; padding-bottom: 10px; padding-left: 10px;\" valign=\"top\">\n" +
                                                    "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider_content\" height=\"5\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; border-top: 0px solid transparent; height: 5px; width: 100%;\" valign=\"top\" width=\"100%\">\n" +
                                                    "<tbody>\n" +
                                                    "<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                                                    "<td height=\"5\" style=\"word-break: break-word; vertical-align: top; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\"><span></span></td>\n" +
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
                                                    "<div style=\"background-color:transparent;\">\n" +
                                                    "<div class=\"block-grid two-up\" style=\"min-width: 320px; max-width: 680px; overflow-wrap: break-word; word-wrap: break-word; word-break: break-word; Margin: 0 auto; background-color: transparent;\">\n" +
                                                    "<div style=\"border-collapse: collapse;display: table;width: 100%;background-color:transparent;\">\n" +
                                                    "<div class=\"col num6\" style=\"display: table-cell; vertical-align: top; max-width: 320px; min-width: 336px; width: 340px;\">\n" +
                                                    "<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                                                    "<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\">\n" +
                                                    "<div style=\"color:#000000;font-family:'Montserrat', 'Trebuchet MS', 'Lucida Grande', 'Lucida Sans Unicode', 'Lucida Sans', Tahoma, sans-serif;line-height:1.8;padding-top:10px;padding-right:30px;padding-bottom:5px;padding-left:30px;\">\n" +
                                                    "<div style=\"line-height: 1.8; font-size: 12px; font-family: 'Montserrat', 'Trebuchet MS', 'Lucida Grande', 'Lucida Sans Unicode', 'Lucida Sans', Tahoma, sans-serif; color: #000000; mso-line-height-alt: 22px;\">\n" +
                                                    "<p style=\"font-size: 18px; line-height: 1.8; word-break: break-word; text-align: center; font-family: Montserrat, 'Trebuchet MS', 'Lucida Grande', 'Lucida Sans Unicode', 'Lucida Sans', Tahoma, sans-serif; mso-line-height-alt: 32px; margin: 0;\"><span style=\"font-size: 18px;\"><strong><span style=\"\">"+company_social_reason+"</span></strong></span></p>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<div align=\"center\" class=\"img-container center fixedwidth\" style=\"padding-right: 0px;padding-left: 0px;\">\n" +
                                                    "<img align=\"center\" alt=\"Alternate text\" border=\"0\" class=\"center fixedwidth\" src=\""+company_image+"\" style=\"text-decoration: none; -ms-interpolation-mode: bicubic; height: auto; border: 0; width: 100%; max-width: 136px; display: block;\" title=\"Alternate text\" width=\"136\"/>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<div class=\"col num6\" style=\"display: table-cell; vertical-align: top; max-width: 320px; min-width: 336px; width: 340px;\">\n" +
                                                    "<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                                                    "<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\">\n" +
                                                    "<div style=\"color:#000000;font-family:'Montserrat', 'Trebuchet MS', 'Lucida Grande', 'Lucida Sans Unicode', 'Lucida Sans', Tahoma, sans-serif;line-height:1.8;padding-top:10px;padding-right:30px;padding-bottom:5px;padding-left:30px;\">\n" +
                                                    "<div style=\"line-height: 1.8; font-size: 12px; font-family: 'Montserrat', 'Trebuchet MS', 'Lucida Grande', 'Lucida Sans Unicode', 'Lucida Sans', Tahoma, sans-serif; color: #000000; mso-line-height-alt: 22px;\">\n" +
                                                    "<p style=\"font-size: 14px; line-height: 1.8; word-break: break-word; text-align: left; font-family: Montserrat, 'Trebuchet MS', 'Lucida Grande', 'Lucida Sans Unicode', 'Lucida Sans', Tahoma, sans-serif; mso-line-height-alt: 25px; margin: 0;\"><strong><span style=\"font-size: 16px;\">Pago pendiente:</span></strong></p>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<div style=\"color:#000fff;font-family:'Montserrat', 'Trebuchet MS', 'Lucida Grande', 'Lucida Sans Unicode', 'Lucida Sans', Tahoma, sans-serif;line-height:1.8;padding-top:0px;padding-right:30px;padding-bottom:10px;padding-left:30px;\">\n" +
                                                    "<div style=\"line-height: 1.8; font-size: 12px; color: #000fff; font-family: 'Montserrat', 'Trebuchet MS', 'Lucida Grande', 'Lucida Sans Unicode', 'Lucida Sans', Tahoma, sans-serif; mso-line-height-alt: 22px;\">\n" +
                                                    "<p style=\"font-size: 26px; line-height: 1.8; word-break: break-word; text-align: left; mso-line-height-alt: 47px; margin: 0;\"><span style=\"font-size: 26px;\"><strong>S/ "+price_st+"</strong></span></p>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<div style=\"color:#000fff;font-family:'Montserrat', 'Trebuchet MS', 'Lucida Grande', 'Lucida Sans Unicode', 'Lucida Sans', Tahoma, sans-serif;line-height:1.8;padding-top:10px;padding-right:30px;padding-bottom:10px;padding-left:30px;\">\n" +
                                                    "<div style=\"line-height: 1.8; font-size: 12px; font-family: 'Montserrat', 'Trebuchet MS', 'Lucida Grande', 'Lucida Sans Unicode', 'Lucida Sans', Tahoma, sans-serif; color: #000fff; mso-line-height-alt: 22px;\">\n" +
                                                    "<p style=\"font-size: 14px; line-height: 1.8; word-break: break-word; text-align: left; font-family: Montserrat, 'Trebuchet MS', 'Lucida Grande', 'Lucida Sans Unicode', 'Lucida Sans', Tahoma, sans-serif; mso-line-height-alt: 25px; margin: 0;\"><strong><span style=\"font-size: 16px;\">Fecha de vencimiento</span></strong></p>\n" +
                                                    "<p style=\"font-size: 14px; line-height: 1.8; word-break: break-word; text-align: left; font-family: Montserrat, 'Trebuchet MS', 'Lucida Grande', 'Lucida Sans Unicode', 'Lucida Sans', Tahoma, sans-serif; mso-line-height-alt: 25px; margin: 0;\"><strong><span style=\"font-size: 16px;\">"+expiration_day+"/"+expiration_month+"/"+expiration_year+"</span></strong></p>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "\n" +
                                                    "</div>\n" +
                                                    "\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<div style=\"background-color:transparent;\">\n" +
                                                    "<div class=\"block-grid\" style=\"min-width: 320px; max-width: 680px; overflow-wrap: break-word; word-wrap: break-word; word-break: break-word; Margin: 0 auto; background-color: transparent;\">\n" +
                                                    "<div style=\"border-collapse: collapse;display: table;width: 100%;background-color:transparent;\">\n" +
                                                    "<div class=\"col num12\" style=\"min-width: 320px; max-width: 680px; display: table-cell; vertical-align: top; width: 680px;\">\n" +
                                                    "<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                                                    "<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\">\n" +
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
                                                    "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\" width=\"100%\">\n" +
                                                    "<tbody>\n" +
                                                    "<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                                                    "<td class=\"divider_inner\" style=\"word-break: break-word; vertical-align: top; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%; padding-top: 10px; padding-right: 10px; padding-bottom: 10px; padding-left: 10px;\" valign=\"top\">\n" +
                                                    "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider_content\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; border-top: 1px solid #BBBBBB; width: 100%;\" valign=\"top\" width=\"100%\">\n" +
                                                    "<tbody>\n" +
                                                    "<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                                                    "<td style=\"word-break: break-word; vertical-align: top; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\"><span></span></td>\n" +
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
                                                    "<!--[if (!mso)&(!IE)]><!-->\n" +
                                                    "</div>\n" +
                                                    "<!--<![endif]-->\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<div style=\"background-color:transparent;\">\n" +
                                                    "<div class=\"block-grid three-up\" style=\"min-width: 320px; max-width: 680px; overflow-wrap: break-word; word-wrap: break-word; word-break: break-word; Margin: 0 auto; background-color: #ffffff;\">\n" +
                                                    "<div style=\"border-collapse: collapse;display: table;width: 100%;background-color:#ffffff;\">\n" +
                                                    "<div class=\"col num3\" style=\"display: table-cell; vertical-align: top; max-width: 320px; min-width: 168px; width: 170px;\">\n" +
                                                    "<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                                                    "<!--[if (!mso)&(!IE)]><!-->\n" +
                                                    "<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 10px;\">\n" +
                                                    "<!--<![endif]-->\n" +
                                                    "<div class=\"mobile_hide\">\n" +
                                                    "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\" width=\"100%\">\n" +
                                                    "<tbody>\n" +
                                                    "<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                                                    "<td class=\"divider_inner\" style=\"word-break: break-word; vertical-align: top; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%; padding-top: 10px; padding-right: 10px; padding-bottom: 10px; padding-left: 10px;\" valign=\"top\">\n" +
                                                    "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider_content\" height=\"5\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; border-top: 0px solid transparent; height: 5px; width: 100%;\" valign=\"top\" width=\"100%\">\n" +
                                                    "<tbody>\n" +
                                                    "<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                                                    "<td height=\"5\" style=\"word-break: break-word; vertical-align: top; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\"><span></span></td>\n" +
                                                    "</tr>\n" +
                                                    "</tbody>\n" +
                                                    "</table>\n" +
                                                    "</td>\n" +
                                                    "</tr>\n" +
                                                    "</tbody>\n" +
                                                    "</table>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<div class=\"col num6\" style=\"display: table-cell; vertical-align: top; max-width: 320px; min-width: 336px; width: 340px;\">\n" +
                                                    "<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                                                    "<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\">\n" +
                                                    "\n" +
                                                    "<div style=\"color:#232323;font-family:Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif;line-height:1.2;padding-top:10px;padding-right:10px;padding-bottom:0px;padding-left:35px;\">\n" +
                                                    "<div style=\"line-height: 1.2; font-size: 12px; color: #232323; font-family: Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif; mso-line-height-alt: 14px;\">\n" +
                                                    "<p style=\"font-size: 14px; line-height: 1.2; word-break: break-word; mso-line-height-alt: 17px; margin: 0;\"><span style=\"font-size: 14px;\">Subtotal:</span></p>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<!--<![endif]-->\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "\n" +
                                                    "<div class=\"col num3\" style=\"display: table-cell; vertical-align: top; max-width: 320px; min-width: 168px; width: 170px;\">\n" +
                                                    "<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                                                    "<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\">\n" +
                                                    "<div style=\"color:#555555;font-family:Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif;line-height:1.2;padding-top:10px;padding-right:10px;padding-bottom:0px;padding-left:30px;\">\n" +
                                                    "<div style=\"line-height: 1.2; font-size: 12px; color: #555555; font-family: Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif; mso-line-height-alt: 14px;\">\n" +
                                                    "<p style=\"font-size: 14px; line-height: 1.2; word-break: break-word; mso-line-height-alt: 17px; margin: 0;\">S/ "+price_st+"</p>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<!--<![endif]-->\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<div style=\"background-color:transparent;\">\n" +
                                                    "<div class=\"block-grid three-up\" style=\"min-width: 320px; max-width: 680px; overflow-wrap: break-word; word-wrap: break-word; word-break: break-word; Margin: 0 auto; background-color: #ffffff;\">\n" +
                                                    "<div style=\"border-collapse: collapse;display: table;width: 100%;background-color:#ffffff;\">\n" +
                                                    "<div class=\"col num3\" style=\"display: table-cell; vertical-align: top; max-width: 320px; min-width: 168px; width: 170px;\">\n" +
                                                    "<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                                                    "<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 10px;\">\n" +
                                                    "<div class=\"mobile_hide\">\n" +
                                                    "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\" width=\"100%\">\n" +
                                                    "<tbody>\n" +
                                                    "<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                                                    "<td class=\"divider_inner\" style=\"word-break: break-word; vertical-align: top; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%; padding-top: 10px; padding-right: 10px; padding-bottom: 10px; padding-left: 10px;\" valign=\"top\">\n" +
                                                    "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider_content\" height=\"5\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; border-top: 0px solid transparent; height: 5px; width: 100%;\" valign=\"top\" width=\"100%\">\n" +
                                                    "<tbody>\n" +
                                                    "<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                                                    "<td height=\"5\" style=\"word-break: break-word; vertical-align: top; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\"><span></span></td>\n" +
                                                    "</tr>\n" +
                                                    "</tbody>\n" +
                                                    "</table>\n" +
                                                    "</td>\n" +
                                                    "</tr>\n" +
                                                    "</tbody>\n" +
                                                    "</table>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<!--<![endif]-->\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<div class=\"col num6\" style=\"display: table-cell; vertical-align: top; max-width: 320px; min-width: 336px; width: 340px;\">\n" +
                                                    "<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                                                    "<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\">\n" +
                                                    "<div style=\"color:#232323;font-family:Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif;line-height:1.2;padding-top:10px;padding-right:10px;padding-bottom:0px;padding-left:35px;\">\n" +
                                                    "<div style=\"line-height: 1.2; font-size: 12px; color: #232323; font-family: Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif; mso-line-height-alt: 14px;\">\n" +
                                                    "<p style=\"font-size: 14px; line-height: 1.2; word-break: break-word; mso-line-height-alt: 17px; margin: 0;\"><span style=\"font-size: 14px;\">Mora</span></p>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<div class=\"col num3\" style=\"display: table-cell; vertical-align: top; max-width: 320px; min-width: 168px; width: 170px;\">\n" +
                                                    "<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                                                    "<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\">\n" +
                                                    "<div style=\"color:#555555;font-family:Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif;line-height:1.2;padding-top:10px;padding-right:10px;padding-bottom:0px;padding-left:30px;\">\n" +
                                                    "<div style=\"line-height: 1.2; font-size: 12px; color: #555555; font-family: Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif; mso-line-height-alt: 14px;\">\n" +
                                                    "<p style=\"font-size: 14px; line-height: 1.2; word-break: break-word; mso-line-height-alt: 17px; margin: 0;\">$ 0.00</p>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<!--<![endif]-->\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<div style=\"background-color:transparent;\">\n" +
                                                    "<div class=\"block-grid three-up\" style=\"min-width: 320px; max-width: 680px; overflow-wrap: break-word; word-wrap: break-word; word-break: break-word; Margin: 0 auto; background-color: #ffffff;\">\n" +
                                                    "<div style=\"border-collapse: collapse;display: table;width: 100%;background-color:#ffffff;\">\n" +
                                                    "<div class=\"col num3\" style=\"display: table-cell; vertical-align: top; max-width: 320px; min-width: 168px; width: 170px;\">\n" +
                                                    "<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                                                    "<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 10px;\">\n" +
                                                    "<div class=\"mobile_hide\">\n" +
                                                    "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\" width=\"100%\">\n" +
                                                    "<tbody>\n" +
                                                    "<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                                                    "<td class=\"divider_inner\" style=\"word-break: break-word; vertical-align: top; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%; padding-top: 10px; padding-right: 10px; padding-bottom: 10px; padding-left: 10px;\" valign=\"top\">\n" +
                                                    "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider_content\" height=\"5\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; border-top: 0px solid transparent; height: 5px; width: 100%;\" valign=\"top\" width=\"100%\">\n" +
                                                    "<tbody>\n" +
                                                    "<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                                                    "<td height=\"5\" style=\"word-break: break-word; vertical-align: top; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\"><span></span></td>\n" +
                                                    "</tr>\n" +
                                                    "</tbody>\n" +
                                                    "</table>\n" +
                                                    "</td>\n" +
                                                    "</tr>\n" +
                                                    "</tbody>\n" +
                                                    "</table>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<div class=\"col num6\" style=\"display: table-cell; vertical-align: top; max-width: 320px; min-width: 336px; width: 340px;\">\n" +
                                                    "<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                                                    "<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\">\n" +
                                                    "<div style=\"color:#232323;font-family:Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif;line-height:1.2;padding-top:10px;padding-right:10px;padding-bottom:0px;padding-left:35px;\">\n" +
                                                    "<div style=\"line-height: 1.2; font-size: 12px; color: #232323; font-family: Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif; mso-line-height-alt: 14px;\">\n" +
                                                    "<p style=\"font-size: 18px; line-height: 1.2; word-break: break-word; mso-line-height-alt: 22px; margin: 0;\"><span style=\"font-size: 18px;\">Pago Total</span></p>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<!--<![endif]-->\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<div class=\"col num3\" style=\"display: table-cell; vertical-align: top; max-width: 320px; min-width: 168px; width: 170px;\">\n" +
                                                    "<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                                                    "<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\">\n" +
                                                    "<div style=\"color:#000fff;font-family:Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif;line-height:1.2;padding-top:10px;padding-right:10px;padding-bottom:0px;padding-left:30px;\">\n" +
                                                    "<div style=\"line-height: 1.2; font-size: 12px; color: #000fff; font-family: Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif; mso-line-height-alt: 14px;\">\n" +
                                                    "<p style=\"font-size: 18px; line-height: 1.2; word-break: break-word; mso-line-height-alt: 22px; margin: 0;\"><span style=\"font-size: 18px;\">S/ "+price_st+"</span></p>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<!--<![endif]-->\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<div style=\"background-color:transparent;\">\n" +
                                                    "<div class=\"block-grid\" style=\"min-width: 320px; max-width: 680px; overflow-wrap: break-word; word-wrap: break-word; word-break: break-word; Margin: 0 auto; background-color: transparent;\">\n" +
                                                    "<div style=\"border-collapse: collapse;display: table;width: 100%;background-color:transparent;\">\n" +
                                                    "<div class=\"col num12\" style=\"min-width: 320px; max-width: 680px; display: table-cell; vertical-align: top; width: 680px;\">\n" +
                                                    "<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                                                    "<!--[if (!mso)&(!IE)]><!-->\n" +
                                                    "<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\">\n" +
                                                    "<!--<![endif]-->\n" +
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
                                                    "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\" width=\"100%\">\n" +
                                                    "<tbody>\n" +
                                                    "<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                                                    "<td class=\"divider_inner\" style=\"word-break: break-word; vertical-align: top; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%; padding-top: 10px; padding-right: 10px; padding-bottom: 10px; padding-left: 10px;\" valign=\"top\">\n" +
                                                    "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider_content\" role=\"presentation\" style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; border-top: 1px solid #BBBBBB; width: 100%;\" valign=\"top\" width=\"100%\">\n" +
                                                    "<tbody>\n" +
                                                    "<tr style=\"vertical-align: top;\" valign=\"top\">\n" +
                                                    "<td style=\"word-break: break-word; vertical-align: top; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\" valign=\"top\"><span></span></td>\n" +
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
                                                    "</div>\n" +
                                                    "<!--<![endif]-->\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<div style=\"background-color:#000fff;\">\n" +
                                                    "<div class=\"block-grid\" style=\"min-width: 320px; max-width: 680px; overflow-wrap: break-word; word-wrap: break-word; word-break: break-word; Margin: 0 auto; background-color: transparent;\">\n" +
                                                    "<div style=\"border-collapse: collapse;display: table;width: 100%;background-color:transparent;\">\n" +
                                                    "<div class=\"col num12\" style=\"min-width: 320px; max-width: 680px; display: table-cell; vertical-align: top; width: 680px;\">\n" +
                                                    "<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                                                    "<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\">\n" +
                                                    "<!--<![endif]-->\n" +
                                                    "<div class=\"mobile_hide\">\n" +
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
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<div style=\"background-color:#02025c;\">\n" +
                                                    "<div class=\"block-grid\" style=\"min-width: 320px; max-width: 680px; overflow-wrap: break-word; word-wrap: break-word; word-break: break-word; Margin: 0 auto; background-color: transparent;\">\n" +
                                                    "<div style=\"border-collapse: collapse;display: table;width: 100%;background-color:transparent;\">\n" +
                                                    "<div class=\"col num12\" style=\"min-width: 320px; max-width: 680px; display: table-cell; vertical-align: top; width: 680px;\">\n" +
                                                    "<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                                                    "<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\">\n" +
                                                    "<!--<![endif]-->\n" +
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
                                                    "<div style=\"color:#ffffff;font-family:Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif;line-height:1.5;padding-top:10px;padding-right:10px;padding-bottom:5px;padding-left:10px;\">\n" +
                                                    "<div style=\"line-height: 1.5; font-size: 12px; color: #ffffff; font-family: Montserrat, Trebuchet MS, Lucida Grande, Lucida Sans Unicode, Lucida Sans, Tahoma, sans-serif; mso-line-height-alt: 18px;\">\n" +
                                                    "<p style=\"text-align: center; line-height: 1.5; word-break: break-word; mso-line-height-alt: 18px; margin: 0;\"><strong>This messasge was sent to <a href=\"mailto:email@example.com\" style=\"color: #d9f5fa;\" title=\"email@example.com\">email@example.com</a></strong></p>\n" +
                                                    "<p style=\"text-align: center; line-height: 1.5; word-break: break-word; mso-line-height-alt: 18px; margin: 0;\"><strong>If you would like to change your email, please <a href=\"http://www.example.com/\" rel=\"noopener\" style=\"color: #d9f5fa;\" target=\"_blank\">click here</a></strong></p>\n" +
                                                    "<p style=\"text-align: center; line-height: 1.5; word-break: break-word; mso-line-height-alt: 18px; margin: 0;\"><strong>If you no longer wish to receive e-mails from Company Name, <a href=\"http://www.example.com/\" rel=\"noopener\" style=\"color: #d9f5fa;\" target=\"_blank\">click here</a></strong></p>\n" +
                                                    "<p style=\"text-align: center; line-height: 1.5; word-break: break-word; mso-line-height-alt: 18px; margin: 0;\"><br/><strong>2020 © All Rights Reserved</strong></p>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "<div style=\"background-color:transparent;\">\n" +
                                                    "<div class=\"block-grid two-up no-stack\" style=\"min-width: 320px; max-width: 680px; overflow-wrap: break-word; word-wrap: break-word; word-break: break-word; Margin: 0 auto; background-color: transparent;\">\n" +
                                                    "<div style=\"border-collapse: collapse;display: table;width: 100%;background-color:transparent;\">\n" +
                                                    "<div class=\"col num6\" style=\"display: table-cell; vertical-align: top; max-width: 320px; min-width: 336px; width: 340px;\">\n" +
                                                    "<div class=\"col_cont\" style=\"width:100% !important;\">\n" +
                                                    "\n" +
                                                    "<div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:10px; padding-bottom:10px; padding-right: 0px; padding-left: 0px;\">\n" +
                                                    "\n" +
                                                    "\n" +
                                                    "</div>\n" +
                                                    "<!--<![endif]-->\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</div>\n" +
                                                    "</td>\n" +
                                                    "</tr>\n" +
                                                    "</tbody>\n" +
                                                    "</body>\n" +
                                                    "</html>";
                                            JavaMailAPI javaMailAPI = new JavaMailAPI(getActivity(), customer_email,subject,message);
                                            javaMailAPI.execute();
                                        }


                                        @Override
                                        public void onCancelled(DatabaseError databaseError) {

                                        }
                                    });

                                }

                            } catch (ParseException e) {
                                e.printStackTrace();
                            }




                            //txtExpiredDebts.setText("S/ "+sum2);
                        }


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });

        return view;
    }

    private void showMyBills() {
        Query query = companyRef.child(post_key).child("My Bills").orderByChild("state").equalTo("expired");
        FirebaseRecyclerAdapter<MyBillsModel, CurrentDebtFragment.CompanyBillsViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<MyBillsModel, CurrentDebtFragment.CompanyBillsViewHolder>
                (MyBillsModel.class, R.layout.my_bill_item, CurrentDebtFragment.CompanyBillsViewHolder.class, query) {
            @Override
            protected void populateViewHolder(CurrentDebtFragment.CompanyBillsViewHolder viewHolder, MyBillsModel model, int position) {
                final String postKey = getRef(position).getKey();
                viewHolder.setBill_amount(model.getBill_amount());
                viewHolder.setBill_id(model.getBill_id());
                viewHolder.setCustomer_name(model.getCustomer_name());
                viewHolder.setState(model.getState());

                viewHolder.txtBillAmount.setText("S/ "+viewHolder.my_bill_amount);
                viewHolder.txtCustomerName.setText(viewHolder.my_customer_name);
                viewHolder.txtBillCode.setText(viewHolder.my_bill_id);

                if (viewHolder.my_state.equals("no_paid")) {
                    viewHolder.txtDebtState.setText("VIGENTE");
                    viewHolder.btnSetPaid.setEnabled(true);
                    viewHolder.btnSetPaid.setVisibility(View.VISIBLE);
                } else if (viewHolder.my_state.equals("paid")){
                    viewHolder.txtDebtState.setText("PAGADO");
                    viewHolder.btnSetPaid.setEnabled(false);
                    viewHolder.btnSetPaid.setVisibility(View.GONE);
                }
                else if (viewHolder.my_state.equals("expired")){
                    viewHolder.txtDebtState.setText("VENCIDO");
                    viewHolder.btnSetPaid.setEnabled(true);
                    viewHolder.btnSetPaid.setVisibility(View.VISIBLE);
                }


                viewHolder.btnSetPaid.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        companyRef.child(post_key).child("My Bills").child(postKey).child("state").setValue("paid");
                    }
                });

            }
        };
        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    public static class CompanyBillsViewHolder extends RecyclerView.ViewHolder {
        View mView;
        String my_bill_amount,my_customer_name,my_bill_id,my_state;
        TextView txtBillCode,txtCustomerName,txtBillAmount,txtDebtState;
        Button btnSetPaid;


        public CompanyBillsViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;

            txtBillCode = mView.findViewById(R.id.txtBillCode);
            txtCustomerName = mView.findViewById(R.id.txtCustomerName);
            txtBillAmount = mView.findViewById(R.id.txtBillAmount);
            txtDebtState = mView.findViewById(R.id.txtDebtState);
            btnSetPaid = mView.findViewById(R.id.btnSetPaid);
        }
        public void setBill_amount(String bill_amount) {
            my_bill_amount = bill_amount;
        }

        public void setCustomer_name(String customer_name) {
            my_customer_name = customer_name;
        }

        public void setBill_id(String bill_id) {
            my_bill_id = bill_id;
        }
        public void setState(String state) {
            my_state = state;
        }
    }
}
