package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.FinancialManagement.FinancialStatements.FinancialSituation;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;

import org.joda.time.Period;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;


public class FinancialSituationAssetsFragment extends Fragment {

    TextView txtPeriod,txtCashFlow,txtShortTermNoPaid,txtInventory,txtTotalCurrentAssets,txtTangibleAssets,txtLongTermNoPaid,txtIntangibleAssets,txtTotalNonCurrentAssets,txtTotalAssets;
    int day,month,year,last_year;
    DatabaseReference companyRef;
    DecimalFormat decimalFormat;
    String post_key,cash_flow_st,short_term_accounts_no_paid_st,my_products_st,long_term_accounts_no_paid_st;
    double cash_flow,short_term_accounts_no_paid,my_products,my_warehouse_products,current_inventory,future_bills,purchase_total,production_total,inventory,total_current_asset,tangible_assets,long_term_accounts_no_paid,intangible_assets,total_non_current_asset,total_assets;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_financial_situation_assets, container, false);

        txtPeriod = view.findViewById(R.id.txtPeriod);
        txtCashFlow = view.findViewById(R.id.txtCashFlow);
        txtShortTermNoPaid = view.findViewById(R.id.txtShortTermNoPaid);
        txtInventory = view.findViewById(R.id. txtInventory);
        txtTotalCurrentAssets = view.findViewById(R.id.txtTotalCurrentAssets);
        txtTangibleAssets = view.findViewById(R.id.txtTangibleAssets);
        txtLongTermNoPaid = view.findViewById(R.id.txtLongTermNoPaid);
        txtIntangibleAssets = view.findViewById(R.id.txtIntangibleAssets);
        txtTotalNonCurrentAssets = view.findViewById(R.id.txtTotalNonCurrentAssets);
        txtTotalAssets = view.findViewById(R.id.txtTotalAssets);

        post_key = getActivity().getIntent().getExtras().getString("post_key");
        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");

        decimalFormat = new DecimalFormat("0.00");

        Date date= new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        day = cal.get(Calendar.DAY_OF_MONTH);
        month = cal.get(Calendar.MONTH)+1;
        year = cal.get(Calendar.YEAR);
        last_year = year-1;

        txtPeriod.setText("Al 31 de Diciembre del "+last_year);

        companyRef.child(post_key).child("Cash Flow").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild("cash_flow" + last_year)) {
                    cash_flow_st = dataSnapshot.child("cash_flow" + last_year).getValue().toString();
                    cash_flow = Double.parseDouble(cash_flow_st);
                    txtCashFlow.setText(cash_flow_st);

                } else {
                    cash_flow = 0.00;
                    txtCashFlow.setText("0.00");
                }

                calculateShortTermsAccountsNoPaid();



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        return view;
    }

    private void calculateShortTermsAccountsNoPaid() {

        companyRef.child(post_key).child("My Bills").orderByChild("issuing_year").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                short_term_accounts_no_paid = 0;
                future_bills = 0;
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String state = ds.child("state").getValue().toString();
                    //int issuing_day = ds.child("issuing_day").getValue(Integer.class);
                    //int issuing_month = ds.child("issuing_month").getValue(Integer.class);
                    int issuing_year = ds.child("issuing_year").getValue(Integer.class);
                    String expiration_day = ds.child("expiration_day").getValue().toString();
                    String expiration_month = ds.child("expiration_month").getValue().toString();
                    String expiration_year = ds.child("expiration_year").getValue().toString();
                    int expiration_year_int = Integer.parseInt(expiration_year);

                    //String issuing_date = issuing_day+"/"+issuing_month+"/"+issuing_year;
                    String expiration_date = expiration_day+"/"+expiration_month+"/"+expiration_year;

                    if (state.equals("no_paid")) {

                        if (expiration_year_int == year) {
                            Map<String, Object> map = (Map<String, Object>) ds.getValue();
                            Object bill_amount = map.get("bill_amount");
                            double bill_amount_db = Double.parseDouble(String.valueOf(bill_amount));
                            short_term_accounts_no_paid += bill_amount_db;
                            short_term_accounts_no_paid_st = decimalFormat.format(short_term_accounts_no_paid);
                            txtShortTermNoPaid.setText(short_term_accounts_no_paid_st);
                        }
                        if (expiration_year_int > year) {
                            Map<String, Object> map = (Map<String, Object>) ds.getValue();
                            Object bill_amount = map.get("bill_amount");
                            double bill_amount_db = Double.parseDouble(String.valueOf(bill_amount));
                            long_term_accounts_no_paid += bill_amount_db;
                            long_term_accounts_no_paid_st = decimalFormat.format(long_term_accounts_no_paid);
                            txtLongTermNoPaid.setText(long_term_accounts_no_paid_st);
                        }

                    }
                    if (issuing_year >= year) {
                        Map<String, Object> map = (Map<String, Object>) ds.getValue();
                        Object bill_amount = map.get("bill_amount");
                        double bill_amount_db = Double.parseDouble(String.valueOf(bill_amount));
                        future_bills += bill_amount_db;

                    }

                }

                calculateInventory();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void calculateInventory() {
        companyRef.child(post_key).child("My Products").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                my_products = 0;
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Map<String, Object> map = (Map<String, Object>) ds.getValue();
                    Object product_price = map.get("product_price");
                    Object product_stock = map.get("product_stock");
                    double product_price_db = Double.parseDouble(String.valueOf(product_price));
                    double product_stock_db = Double.parseDouble(String.valueOf(product_stock));
                    double total_products = product_price_db*product_stock_db;
                    my_products += total_products;
                    my_products_st = decimalFormat.format(my_products);


                }

                companyRef.child(post_key).child("Warehouses").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            final String warehouse_id = ds.getKey();

                            companyRef.child(post_key).child("Warehouses").child(warehouse_id).child("Products").addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                                        Map<String, Object> map = (Map<String, Object>) ds.getValue();
                                        Object product_price = map.get("product_price");
                                        Object product_stock = map.get("product_stock");
                                        double product_price_db = Double.parseDouble(String.valueOf(product_price));
                                        double product_stock_db = Double.parseDouble(String.valueOf(product_stock));
                                        double total_warehouse_products = product_price_db*product_stock_db;
                                        my_warehouse_products += total_warehouse_products;

                                    }

                                    current_inventory = my_products+my_warehouse_products;

                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });

                        }

                        companyRef.child(post_key).child("Purchased Orders").addListenerForSingleValueEvent(new ValueEventListener() {
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

                                    int expiration_year_int = Integer.parseInt(expiration_year);

                                    String issuing_date = operation_day+"/"+operation_month+"/"+operation_time;
                                    String expiration_date = expiration_day+"/"+expiration_month+"/"+expiration_year;

                                    if (purchase_payment_state.equals("paid")) {

                                        if (expiration_year_int >= year) {
                                            Map<String, Object> map = (Map<String, Object>) ds.getValue();
                                            Object purchase_order_total_amount = map.get("purchase_order_total_amount");
                                            double purchase_order_total_amount_db = Double.parseDouble(String.valueOf(purchase_order_total_amount));
                                            purchase_total += purchase_order_total_amount_db;

                                        }

                                    }
                                }

                                companyRef.child(post_key).child("Production Chain").orderByChild("state").equalTo("ready").addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        production_total = 0;
                                        for (DataSnapshot ds : dataSnapshot.getChildren()) {

                                            String production_year = ds.child("production_year").getValue().toString();
                                            int production_year_int = Integer.parseInt(production_year);

                                            if (production_year_int >= year) {
                                                Map<String, Object> map = (Map<String, Object>) ds.getValue();
                                                Object product_price = map.get("product_price");
                                                Object product_quantity_production = map.get("product_quantity_production");
                                                double product_price_db = Double.parseDouble(String.valueOf(product_price));
                                                double product_quantity_production_db = Double.parseDouble(String.valueOf(product_quantity_production));
                                                double total_production = product_price_db*product_quantity_production_db;
                                                production_total += total_production;


                                                inventory = (my_products+my_warehouse_products)-(purchase_total+production_total)+future_bills;

                                                if (inventory <= 0.00) {
                                                    txtInventory.setText("0.00");
                                                    inventory = 0.00;
                                                } else {
                                                    String inventory_st =decimalFormat.format(inventory);
                                                    txtInventory.setText(inventory_st);
                                                }

                                                total_current_asset = cash_flow+short_term_accounts_no_paid+inventory;
                                                String total_current_asset_st = decimalFormat.format(total_current_asset);
                                                txtTotalCurrentAssets.setText(total_current_asset_st);

                                                calculateNonCurrentAssets();

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

    private void calculateNonCurrentAssets() {
        companyRef.child(post_key).child("Fixed Assets").child("Tangible").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                tangible_assets = 0;
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Map<String, Object> map = (Map<String, Object>) ds.getValue();
                    Object asset_depreciation_rate = map.get("asset_depreciation_rate");
                    Object asset_price = map.get("asset_price");
                    Object asset_quantity = map.get("asset_quantity");

                    Object asset_purchased_day = map.get("asset_purchased_day");
                    Object asset_purchased_month = map.get("asset_purchased_month");
                    Object asset_purchased_year = map.get("asset_purchased_year");

                    double asset_depreciation_rate_db = Double.parseDouble(String.valueOf(asset_depreciation_rate))/100;
                    double asset_price_db = Double.parseDouble(String.valueOf(asset_price));
                    double asset_quantity_db = Double.parseDouble(String.valueOf(asset_quantity));
                    double total_assets = asset_price_db*asset_quantity_db;


                    double annual_deprecation = asset_depreciation_rate_db*total_assets;
                    double monthly_deprecation = annual_deprecation/12;
                    String start = asset_purchased_day+"/"+asset_purchased_month+"/"+asset_purchased_year;
                    String end = day+"/"+month+"/"+year;
                    DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    try {
                        org.joda.time.LocalDate a = org.joda.time.LocalDate.fromDateFields(sdf.parse(start));
                        org.joda.time.LocalDate b = org.joda.time.LocalDate.fromDateFields(sdf.parse(end));
                        Period p = new Period(a, b);
                        double months = (p.getYears() * 12) + p.getMonths();
                        double net_value = monthly_deprecation*months;
                        String net_value_st = decimalFormat.format(net_value);

                        double real_net_value = total_assets-net_value;
                        tangible_assets += real_net_value;
                        String tangible_assets_st = decimalFormat.format(tangible_assets);

                        txtTangibleAssets.setText(tangible_assets_st);

                    } catch (ParseException e) {
                        e.printStackTrace();
                    }


                }

                companyRef.child(post_key).child("Fixed Assets").child("Intangible").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        intangible_assets = 0;
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            Map<String, Object> map = (Map<String, Object>) ds.getValue();
                            Object asset_price = map.get("asset_price");
                            Object asset_quantity = map.get("asset_quantity");
                            double asset_price_db = Double.parseDouble(String.valueOf(asset_price));
                            double asset_quantity_db = Double.parseDouble(String.valueOf(asset_quantity));
                            double total_assets = asset_price_db*asset_quantity_db;
                            intangible_assets += total_assets;
                            String intangible_assets_st = decimalFormat.format(intangible_assets);
                            txtIntangibleAssets.setText(intangible_assets_st);

                            total_non_current_asset = tangible_assets+intangible_assets+long_term_accounts_no_paid;
                            String total_non_current_asset_st = decimalFormat.format(total_non_current_asset);
                            txtTotalNonCurrentAssets.setText(total_non_current_asset_st);


                            total_assets = total_current_asset+total_non_current_asset;
                            String total_assets_st = decimalFormat.format(total_assets);

                            companyRef.child(post_key).child("Financial Statements").child("Financial Situation").child(last_year+"").child("total_assets").setValue(total_assets_st);

                            txtTotalAssets.setText(total_assets_st);

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
}
