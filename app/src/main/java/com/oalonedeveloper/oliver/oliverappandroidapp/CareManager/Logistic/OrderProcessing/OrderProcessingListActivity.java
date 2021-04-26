package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Logistic.OrderProcessing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Commercialization.BillsIssuing.CreateInvoiceActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Logistic.PurchaseOrder.PurchaseOrderActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Logistic.PurchaseOrder.PurchaseOrdersListActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import es.dmoral.toasty.Toasty;
import in.galaxyofandroid.spinerdialog.OnSpinerItemClick;
import in.galaxyofandroid.spinerdialog.SpinnerDialog;

public class OrderProcessingListActivity extends AppCompatActivity {

    String post_key,item_id,purchase_order_id,sale_type,customer_document_verification,current_time,customer_document_number,supplier_name;
    RecyclerView recyclerView,recyclerView2,recyclerView1;
    DatabaseReference companyRef,fromPath,toPath;
    Button btnCreatePurchaseOrder;
    AlertDialog warehouse_dialog;
    int day,month,year;
    DecimalFormat decimalFormat;

    ArrayList<String> bthDay =new ArrayList<>();
    SpinnerDialog bthDayDialog;

    ArrayList<String> bthMonth =new ArrayList<>();
    SpinnerDialog bthMonthDialog;

    ArrayList<String> bthYear =new ArrayList<>();
    SpinnerDialog bthYearDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_processing_list);

        post_key = getIntent().getExtras().getString("post_key");
        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");
        decimalFormat = new DecimalFormat("0.00");

        customer_document_verification = "";

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        decimalFormat = new DecimalFormat("0.00");

        btnCreatePurchaseOrder = findViewById(R.id.btnCreatePurchaseOrder);

        btnCreatePurchaseOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderProcessingListActivity.this, OrderProcessingActivity.class);
                intent.putExtra("post_key",post_key);
                startActivity(intent);
            }
        });

        showPurchaseOrders();
    }

    private void showPurchaseOrders() {
        Query query = companyRef.child(post_key).child("Orders Processing").orderByChild("timestamp");
        FirebaseRecyclerAdapter<OrderNoteModel,OrderProcessingViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<OrderNoteModel, OrderProcessingViewHolder>
                (OrderNoteModel.class, R.layout.purchase_order_item,OrderProcessingViewHolder.class,query) {
            @Override
            protected void populateViewHolder(final OrderProcessingViewHolder viewHolder, final OrderNoteModel model, int position) {
                final String postKey = getRef(position).getKey();
                viewHolder.setOperation_date(model.getOperation_date());
                viewHolder.setOrder_processing_customer_id(model.getOrder_processing_customer_id());
                viewHolder.setOrder_processing_state(model.getOrder_processing_state());
                viewHolder.setOrder_processing_total_amount(model.getOrder_processing_total_amount());

                if (viewHolder.my_order_processing_state != null) {
                    if (viewHolder.my_order_processing_state.equals("pending")) {
                        viewHolder.txtCode.setText("Enviado");
                    }
                    if (viewHolder.my_order_processing_state.equals("ready")) {
                        viewHolder.txtCode.setText("Entregado");
                    }
                    if (viewHolder.my_order_processing_state.equals("ready_delayed")) {
                        viewHolder.txtCode.setText("Entregado con Retraso");
                    }
                    if (viewHolder.my_order_processing_state.equals("returned")) {
                        viewHolder.txtCode.setText("Devuelto");
                    }
                    if (viewHolder.my_order_processing_state.equals("dispatch")) {
                        viewHolder.txtCode.setText("Despacho");
                    }
                    if (viewHolder.my_order_processing_state.equals("canceled")) {
                        viewHolder.txtCode.setText("Cancelado");
                    }
                }

                viewHolder.txtDate.setText(viewHolder.my_operation_date);

                viewHolder.txtTotal.setText("S/ "+viewHolder.my_order_processing_total_amount);

                if (viewHolder.my_order_processing_customer_id != null) {
                    companyRef.child(post_key).child("Customers").child(viewHolder.my_order_processing_customer_id).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            String supplier_name = dataSnapshot.child("customer_name").getValue().toString();
                            viewHolder.txtSupplier.setText(supplier_name);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }

                viewHolder.btnActionButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showOptionsDialog();
                    }

                    private void showOptionsDialog() {
                        final AlertDialog dialog = new AlertDialog.Builder(OrderProcessingListActivity.this).create();

                        LayoutInflater inflater = LayoutInflater.from(OrderProcessingListActivity.this);
                        View finance_method = inflater.inflate(R.layout.order_processing_options,null);

                        ImageButton btnReady,btnReturned;

                        btnReady = finance_method.findViewById(R.id.btnReady);
                        btnReturned = finance_method.findViewById(R.id.btnReturned);

                        btnReady.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                final AlertDialog dialog = new AlertDialog.Builder(OrderProcessingListActivity.this).create();

                                LayoutInflater inflater = LayoutInflater.from(OrderProcessingListActivity.this);
                                View finance_method = inflater.inflate(R.layout.schedule_dispatch_dialog,null);

                                final TextView txtCustomerName,txtDocumentNumber;
                                final RadioButton rdCash,rdCredit,rdMorning,rdAfternoon,rdNight;
                                final Button edtBthDay,edtBthMonth,edtBthYear,btnDepartment,btnProvince,btnDistrict,btnRegister;
                                final EditText edtAddress;
                                final LinearLayout creditLayout;
                                final RelativeLayout rootLayout;

                                recyclerView2 = finance_method.findViewById(R.id.recyclerView2);

                                recyclerView2 = finance_method.findViewById(R.id.recyclerView2);
                                txtCustomerName = finance_method.findViewById(R.id.txtCustomerName);
                                txtDocumentNumber = finance_method.findViewById(R.id.txtDocumentNumber);
                                rdCash = finance_method.findViewById(R.id.rdCash);
                                rdCredit = finance_method.findViewById(R.id.rdCredit);
                                rdMorning = finance_method.findViewById(R.id.rdMorning);
                                rdAfternoon = finance_method.findViewById(R.id.rdAfternoon);
                                rdNight = finance_method.findViewById(R.id.rdNight);
                                edtBthDay = finance_method.findViewById(R.id.edtBthDay);
                                edtBthMonth = finance_method.findViewById(R.id.edtBthMonth);
                                edtBthYear = finance_method.findViewById(R.id.edtBthYear);
                                btnDepartment = finance_method.findViewById(R.id.btnDepartment);
                                btnProvince = finance_method.findViewById(R.id.btnProvince);
                                btnDistrict = finance_method.findViewById(R.id.btnDistrict);
                                edtAddress = finance_method.findViewById(R.id.edtAddress);
                                creditLayout = finance_method.findViewById(R.id.creditLayout);
                                btnRegister = finance_method.findViewById(R.id.btnRegister);
                                rootLayout = finance_method.findViewById(R.id.rootLayout);

                                recyclerView2.setHasFixedSize(true);
                                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(OrderProcessingListActivity.this);
                                linearLayoutManager.setReverseLayout(true);
                                linearLayoutManager.setStackFromEnd(true);
                                recyclerView2.setLayoutManager(linearLayoutManager);

                                bthDay.add("1"); bthDay.add("2"); bthDay.add("3"); bthDay.add("4"); bthDay.add("5"); bthDay.add("6"); bthDay.add("7"); bthDay.add("8"); bthDay.add("9"); bthDay.add("10");
                                bthDay.add("11"); bthDay.add("12"); bthDay.add("13"); bthDay.add("14"); bthDay.add("15"); bthDay.add("16"); bthDay.add("17"); bthDay.add("18"); bthDay.add("19"); bthDay.add("20");
                                bthDay.add("21"); bthDay.add("22"); bthDay.add("23"); bthDay.add("24"); bthDay.add("25"); bthDay.add("26"); bthDay.add("27"); bthDay.add("28"); bthDay.add("29"); bthDay.add("30");
                                bthDay.add("31");

                                edtBthDay.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        bthDayDialog.showSpinerDialog();
                                    }
                                });

                                bthDayDialog = new SpinnerDialog(OrderProcessingListActivity.this,bthDay, "Selecciona tu Día de Nacimiento");
                                bthDayDialog.bindOnSpinerListener(new OnSpinerItemClick() {
                                    @Override
                                    public void onClick(String item2, int position2) {
                                        edtBthDay.setText(item2);
                                    }
                                });

                                bthMonth.add("1");bthMonth.add("2");bthMonth.add("3");bthMonth.add("4");bthMonth.add("5");bthMonth.add("6");bthMonth.add("7");bthMonth.add("8");bthMonth.add("9");bthMonth.add("10");
                                bthMonth.add("11");bthMonth.add("12");

                                edtBthMonth.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        bthMonthDialog.showSpinerDialog();
                                    }
                                });

                                bthMonthDialog = new SpinnerDialog(OrderProcessingListActivity.this,bthMonth, "Selecciona tu Mes de Nacimiento");
                                bthMonthDialog.bindOnSpinerListener(new OnSpinerItemClick() {
                                    @Override
                                    public void onClick(String item2, int position2) {
                                        edtBthMonth.setText(item2);

                                    }
                                });

                                bthYear.add("2020");bthYear.add("2021");bthYear.add("2022");bthYear.add("2023");bthYear.add("2024");bthYear.add("2025");bthYear.add("2026");bthYear.add("2027");bthYear.add("2028");bthYear.add("2029");
                                bthYear.add("2030");

                                edtBthYear.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        bthYearDialog.showSpinerDialog();
                                    }
                                });

                                bthYearDialog = new SpinnerDialog(OrderProcessingListActivity.this,bthYear, "Selecciona tu Año de Nacimiento");
                                bthYearDialog.bindOnSpinerListener(new OnSpinerItemClick() {
                                    @Override
                                    public void onClick(String item2, int position2) {
                                        edtBthYear.setText(item2);
                                    }
                                });

                                companyRef.child(post_key).child("Customers").child(viewHolder.my_order_processing_customer_id).addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        supplier_name = dataSnapshot.child("customer_name").getValue().toString();
                                        String customer_type = dataSnapshot.child("customer_type").getValue().toString();

                                        if (dataSnapshot.hasChild("customer_document_number")) {
                                            customer_document_number = dataSnapshot.child("customer_document_number").getValue().toString();

                                            if (customer_type.equals("Empresa")) {
                                                txtDocumentNumber.setText("RUC: "+customer_document_number);
                                                customer_document_verification = "true";
                                            }
                                            if (customer_type.equals("Persona")) {
                                                txtDocumentNumber.setText("DNI: "+customer_document_number);
                                                customer_document_verification = "true";
                                            }
                                        } else {
                                            txtDocumentNumber.setText("Nº DOCUMENTO: COMPLETAR");
                                        }
                                        if (dataSnapshot.hasChild("customer_department")) {
                                            String customer_department = dataSnapshot.child("customer_department").getValue().toString();
                                            btnDepartment.setText(customer_department);
                                        }
                                        if (dataSnapshot.hasChild("customer_province")) {
                                            String customer_province = dataSnapshot.child("customer_province").getValue().toString();
                                            btnProvince.setText(customer_province);
                                        }
                                        if (dataSnapshot.hasChild("customer_district")) {
                                            String customer_district = dataSnapshot.child("customer_district").getValue().toString();
                                            btnDistrict.setText(customer_district);
                                        }
                                        if (dataSnapshot.hasChild("customer_address")) {
                                            String customer_address = dataSnapshot.child("customer_address").getValue().toString();
                                            edtAddress.setText(customer_address);
                                        }

                                        rdCash.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                creditLayout.setVisibility(View.GONE);
                                                sale_type = "cash";
                                            }
                                        });

                                        rdCredit.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                creditLayout.setVisibility(View.VISIBLE);
                                                sale_type = "credit";
                                            }
                                        });

                                        txtCustomerName.setText(supplier_name);



                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {

                                    }
                                });

                                showItems();

                                btnRegister.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if (customer_document_verification.equals("")) {
                                            Snackbar.make(rootLayout,"Debes ingresar el número de documento del cliente",Snackbar.LENGTH_LONG).show();
                                        }
                                        else if (!rdCash.isChecked() && !rdCredit.isChecked()) {
                                            Snackbar.make(rootLayout,"Debes seleccionar el tipo de venta",Snackbar.LENGTH_LONG).show();
                                        }
                                        else if (TextUtils.isEmpty(btnDepartment.getText().toString())) {
                                            Snackbar.make(rootLayout,"Debes seleccionar el departamento de entrega",Snackbar.LENGTH_LONG).show();
                                        }
                                        else if (TextUtils.isEmpty(btnProvince.getText().toString())) {
                                            Snackbar.make(rootLayout,"Debes seleccionar la provincia de entrega",Snackbar.LENGTH_LONG).show();
                                        }
                                        else if (TextUtils.isEmpty(btnDistrict.getText().toString())) {
                                            Snackbar.make(rootLayout,"Debes seleccionar el distrito de entrega",Snackbar.LENGTH_LONG).show();
                                        }
                                        else if (TextUtils.isEmpty(edtAddress.getText().toString())) {
                                            Snackbar.make(rootLayout,"Debes ingresar la dirección exacta de entrega",Snackbar.LENGTH_LONG).show();
                                        } else if (!rdMorning.isChecked() && !rdAfternoon.isChecked() && !rdNight.isChecked()) {
                                            Snackbar.make(rootLayout, "Debes seleccionar el turno de entrega", Snackbar.LENGTH_LONG).show();
                                        } else {
                                            registerBill();
                                        }
                                    }

                                    private void registerBill() {
                                        Date date= new Date();
                                        Calendar cal = Calendar.getInstance();
                                        cal.setTime(date);
                                        day = cal.get(Calendar.DAY_OF_MONTH);
                                        month = cal.get(Calendar.MONTH)+1;
                                        year = cal.get(Calendar.YEAR);

                                        current_time = new SimpleDateFormat("HHmmss").format(Calendar.getInstance().getTime());
                                        final String billId = day+""+month+""+year+""+current_time;
                                        fromPath = companyRef.child(post_key).child("Orders Processing").child(postKey).child("Items");
                                        toPath = FirebaseDatabase.getInstance().getReference().child("My Companies").child(post_key).child("My Bills").child(billId).child("Product List");

                                        fromPath.addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(DataSnapshot dataSnapshot) {

                                                for (final DataSnapshot ds : dataSnapshot.getChildren()) {
                                                    Map<String, Object> map = (Map<String, Object>) ds.getValue();
                                                    Object quantity = map.get("quantity");
                                                    final String item_quantity_st = (String) quantity;

                                                    companyRef.child(post_key).child("My Products").child(ds.getKey()).addListenerForSingleValueEvent(new ValueEventListener() {
                                                        @Override
                                                        public void onDataChange(DataSnapshot dataSnapshot) {
                                                            String product_stock = dataSnapshot.child("product_stock").getValue().toString();

                                                            double current_stock = Double.parseDouble(product_stock);
                                                            double item_quantity_db = Double.parseDouble(item_quantity_st);

                                                            double total_stock = current_stock-item_quantity_db;
                                                            String total_stock_st = decimalFormat.format(total_stock);

                                                            companyRef.child(post_key).child("My Products").child(ds.getKey()).child("product_stock").setValue(total_stock_st);
                                                        }

                                                        @Override
                                                        public void onCancelled(DatabaseError databaseError) {

                                                        }
                                                    });
                                                }


                                                toPath.setValue(dataSnapshot.getValue(), new DatabaseReference.CompletionListener() {
                                                    @Override
                                                    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                                                        if (databaseError != null) {
                                                            Toast.makeText(OrderProcessingListActivity.this, "Hubo un Error", Toast.LENGTH_SHORT).show();
                                                        } else {
                                                            if (rdCash.isChecked()) {
                                                                companyRef.child(post_key).child("My Bills").child(billId).child("expiration_day").setValue(day+"");
                                                                companyRef.child(post_key).child("My Bills").child(billId).child("expiration_month").setValue(month+"");
                                                                companyRef.child(post_key).child("My Bills").child(billId).child("expiration_year").setValue(year+"");
                                                                companyRef.child(post_key).child("My Bills").child(billId).child("state").setValue("paid");

                                                            } else if (rdCredit.isChecked()) {
                                                                companyRef.child(post_key).child("My Bills").child(billId).child("expiration_day").setValue(edtBthDay.getText().toString());
                                                                companyRef.child(post_key).child("My Bills").child(billId).child("expiration_month").setValue(edtBthMonth.getText().toString());
                                                                companyRef.child(post_key).child("My Bills").child(billId).child("expiration_year").setValue(edtBthYear.getText().toString());
                                                                companyRef.child(post_key).child("My Bills").child(billId).child("state").setValue("no_paid");
                                                            }

                                                            companyRef.child(post_key).child("My Bills").child(billId).child("type").setValue("invoice");
                                                            companyRef.child(post_key).child("My Bills").child(billId).child("company_id").setValue(post_key);
                                                            companyRef.child(post_key).child("My Bills").child(billId).child("bill_currency").setValue("PEN");
                                                            companyRef.child(post_key).child("My Bills").child(billId).child("bill_id").setValue(billId);

                                                            double total = Double.parseDouble(viewHolder.my_order_processing_total_amount);
                                                            double taxes = total*0.18;
                                                            String taxes_st = decimalFormat.format(taxes);

                                                            companyRef.child(post_key).child("My Bills").child(billId).child("issuing_day").setValue(day);
                                                            companyRef.child(post_key).child("My Bills").child(billId).child("issuing_month").setValue(month);
                                                            companyRef.child(post_key).child("My Bills").child(billId).child("issuing_year").setValue(year);
                                                            companyRef.child(post_key).child("My Bills").child(billId).child("customer_id").setValue(viewHolder.my_order_processing_customer_id);
                                                            companyRef.child(post_key).child("My Bills").child(billId).child("customer_name").setValue(supplier_name);
                                                            companyRef.child(post_key).child("My Bills").child(billId).child("customer_document_number").setValue(customer_document_number);
                                                            companyRef.child(post_key).child("My Bills").child(billId).child("bill_observations").setValue("");
                                                            companyRef.child(post_key).child("My Bills").child(billId).child("bill_amount").setValue(viewHolder.my_order_processing_total_amount);
                                                            companyRef.child(post_key).child("My Bills").child(billId).child("bill_igv_taxes").setValue(taxes_st);
                                                            companyRef.child(post_key).child("My Bills").child(billId).child("seller_id").setValue("unknown");

                                                            companyRef.child(post_key).child("Customers").child(viewHolder.my_order_processing_customer_id).child("Purchased").child(billId).child("bill_amount").setValue(viewHolder.my_order_processing_total_amount);

                                                            companyRef.child(post_key).child("My Bills").child(billId).child("timestamp").setValue(ServerValue.TIMESTAMP);

                                                            //Register dispatch
                                                            Long tsLong = (System.currentTimeMillis()/1000);
                                                            String timestamp = tsLong.toString();

                                                            companyRef.child(post_key).child("Dispatches").child(timestamp).child("dispatch_customer_id").setValue(viewHolder.my_order_processing_customer_id);
                                                            companyRef.child(post_key).child("Dispatches").child(timestamp).child("dispatch_department").setValue(btnDepartment.getText().toString());
                                                            companyRef.child(post_key).child("Dispatches").child(timestamp).child("dispatch_province").setValue(btnProvince.getText().toString());
                                                            companyRef.child(post_key).child("Dispatches").child(timestamp).child("dispatch_district").setValue(btnDistrict.getText().toString());
                                                            companyRef.child(post_key).child("Dispatches").child(timestamp).child("dispatch_address").setValue(edtAddress.getText().toString());
                                                            companyRef.child(post_key).child("Dispatches").child(timestamp).child("dispatch_bill_number").setValue(billId);
                                                            companyRef.child(post_key).child("Dispatches").child(timestamp).child("dispatch_state").setValue("pending");
                                                            companyRef.child(post_key).child("Dispatches").child(timestamp).child("timestamp").setValue(ServerValue.TIMESTAMP);
                                                            if (rdMorning.isChecked()) {
                                                                companyRef.child(post_key).child("Dispatches").child(timestamp).child("dispatch_shift").setValue("Mañana");
                                                            }
                                                            if (rdAfternoon.isChecked()) {
                                                                companyRef.child(post_key).child("Dispatches").child(timestamp).child("dispatch_shift").setValue("Tarde");
                                                            }
                                                            if (rdNight.isChecked()) {
                                                                companyRef.child(post_key).child("Dispatches").child(timestamp).child("dispatch_shift").setValue("Noche");
                                                            }

                                                            //Update order processing state
                                                            companyRef.child(post_key).child("Orders Processing").child(postKey).child("order_processing_state").setValue("dispatch");
                                                            dialog.dismiss();

                                                        }
                                                    }
                                                });
                                            }


                                            @Override
                                            public void onCancelled(DatabaseError databaseError) {

                                            }
                                        });



                                    }
                                });

                                dialog.setView(finance_method);
                                dialog.show();
                            }

                            private void showItems() {
                                Query query = companyRef.child(post_key).child("Orders Processing").child(postKey).child("Items").orderByChild("timestamp");
                                FirebaseRecyclerAdapter<OrderNoteItemsModel, OrderProcessingItemsViewHolder> firebaseRecyclerAdapter1 = new FirebaseRecyclerAdapter<OrderNoteItemsModel, OrderProcessingItemsViewHolder>
                                        (OrderNoteItemsModel.class, R.layout.oder_note_ready_item,OrderProcessingItemsViewHolder.class,query) {
                                    @Override
                                    protected void populateViewHolder(OrderProcessingItemsViewHolder viewHolder1, OrderNoteItemsModel model1, int i) {
                                        viewHolder1.setName(model1.getName());
                                        viewHolder1.setPrice(model1.getPrice());
                                        viewHolder1.setProduct_id(model1.getProduct_id());
                                        viewHolder1.setQuantity(model1.getQuantity());
                                        viewHolder1.setTotal(model1.getTotal());

                                        viewHolder1.txtItemName.setText(viewHolder1.my_name);
                                        viewHolder1.txtQuantity.setText(viewHolder1.my_quantity);
                                        viewHolder1.txtPrice.setText(viewHolder1.my_price);
                                        viewHolder1.txtTotal.setText(viewHolder1.my_total);
                                    }
                                };
                                recyclerView2.setAdapter(firebaseRecyclerAdapter1);

                            }
                        });

                        btnReturned.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                companyRef.child(post_key).child("Orders Processing").child(postKey).child("order_processing_state").setValue("canceled");
                                Toasty.success(OrderProcessingListActivity.this, "Nota de Pedido Cancelada", Toast.LENGTH_LONG).show();
                                dialog.dismiss();
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

    public static class OrderProcessingItemsViewHolder extends RecyclerView.ViewHolder {
        View mView;
        String my_name,my_price,my_product_id,my_quantity,my_total;
        TextView txtQuantity,txtItemName,txtPrice,txtTotal;

        public OrderProcessingItemsViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;

            txtQuantity = mView.findViewById(R.id.txtQuantity);
            txtItemName = mView.findViewById(R.id.txtItemName);
            txtPrice = mView.findViewById(R.id.txtPrice);
            txtTotal = mView.findViewById(R.id.txtTotal);
        }
        public void setName(String name) {
            my_name = name;
        }

        public void setPrice(String price) {
            my_price = price;
        }

        public void setProduct_id(String product_id) {
            my_product_id = product_id;
        }

        public void setQuantity(String quantity) {
            my_quantity = quantity;
        }

        public void setTotal(String total) {
            my_total = total;
        }
    }

    public static class OrderProcessingViewHolder extends RecyclerView.ViewHolder {

        View mView;
        String my_order_processing_state,my_order_processing_customer_id,my_operation_date,my_order_processing_total_amount;
        TextView txtCode,txtSupplier,txtDate,txtTotal;
        ImageView btnActionButton;

        public OrderProcessingViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;

            txtCode = mView.findViewById(R.id.txtCode);
            txtSupplier = mView.findViewById(R.id.txtSupplier);
            txtDate = mView.findViewById(R.id.txtDate);
            txtTotal = mView.findViewById(R.id.txtTotal);
            btnActionButton = mView.findViewById(R.id.btnActionButton);

        }

        public void setOrder_processing_state(String order_processing_state) {
            my_order_processing_state = order_processing_state;
        }

        public void setOrder_processing_customer_id(String order_processing_customer_id) {
            my_order_processing_customer_id = order_processing_customer_id;
        }


        public void setOperation_date(String operation_date) {
            my_operation_date = operation_date;
        }


        public void setOrder_processing_total_amount(String order_processing_total_amount) {
            my_order_processing_total_amount = order_processing_total_amount;
        }
    }
}
