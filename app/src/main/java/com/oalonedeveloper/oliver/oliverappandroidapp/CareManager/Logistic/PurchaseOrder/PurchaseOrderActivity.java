package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Logistic.PurchaseOrder;

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
import android.widget.LinearLayout;
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
import com.hbb20.CountryCodePicker;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Commercialization.BillsIssuing.CreateBillActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Production.ProductionOrders.ProductionOrderProductDetailActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;
import es.dmoral.toasty.Toasty;

import static com.oalonedeveloper.oliver.oliverappandroidapp.R.layout.activity_purchase_order;

public class PurchaseOrderActivity extends AppCompatActivity {

    CircleImageView imgCompanyProfile;
    TextView txtCompanyName,txtCompanyAddress,txtCurrentDate,txtOrderCode,txtSupplier,txtContact,txtPhone,txtEmail,txtAddress,txtSalesConditions,txtTaxes,txtTotalAmount;
    DatabaseReference companyRef;
    String post_key,company_image,company_social_reason,company_address,timestamp,supplier_id,address_value,sales_conditions_value,total_amount_st,total_taxes_st;
    DecimalFormat decimalFormat;
    int day,month,year;
    RecyclerView recyclerView,recyclerView2;
    AlertDialog dialog;
    Button btnAddProduct,btnRegisterPurchaseOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_purchase_order);

        imgCompanyProfile = findViewById(R.id.imgCompanyProfile);
        txtCompanyName = findViewById(R.id.txtCompanyName);
        txtCompanyAddress = findViewById(R.id.txtCompanyAddress);
        txtCurrentDate = findViewById(R.id.txtCurrentDate);
        txtOrderCode = findViewById(R.id.txtOrderCode);
        txtSupplier = findViewById(R.id.txtSupplier);
        txtContact = findViewById(R.id.txtContact);
        txtPhone = findViewById(R.id.txtPhone);
        txtEmail = findViewById(R.id.txtEmail);
        txtAddress = findViewById(R.id.txtAddress);
        txtSalesConditions = findViewById(R.id.txtSalesConditions);
        btnAddProduct = findViewById(R.id.btnAddProduct);
        txtTaxes = findViewById(R.id.txtTaxes);
        txtTotalAmount = findViewById(R.id.txtTotalAmount);
        btnRegisterPurchaseOrder = findViewById(R.id.btnRegisterPurchaseOrder);

        post_key = getIntent().getExtras().getString("post_key");
        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");
        decimalFormat = new DecimalFormat("0.00");

        recyclerView2 = findViewById(R.id.recyclerView2);
        recyclerView2.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView2.setLayoutManager(linearLayoutManager);

        Date date= new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        day = cal.get(Calendar.DAY_OF_MONTH);
        month = cal.get(Calendar.MONTH)+1;
        year = cal.get(Calendar.YEAR);

        Long tsLong = System.currentTimeMillis()/1000;
        timestamp = tsLong.toString();

        txtSupplier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSupplierDialog();
            }
        });

        txtCurrentDate.setText("Fecha de emisión: "+day+"/"+month+"/"+year);
        txtOrderCode.setText("Nº: "+timestamp);

        companyRef.child(post_key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                company_image = dataSnapshot.child("company_image").getValue().toString();
                company_social_reason = dataSnapshot.child("company_social_reason").getValue().toString();
                company_address = dataSnapshot.child("company_address").getValue().toString();

                Picasso.with(PurchaseOrderActivity.this).load(company_image).fit().into(imgCompanyProfile);
                txtCompanyName.setText(company_social_reason);
                txtCompanyAddress.setText(company_address);

                showOrderPurchaseProducts();
                showTotalAmount();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        txtAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddAddressDialog();
            }
        });
        txtSalesConditions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog dialog = new AlertDialog.Builder(PurchaseOrderActivity.this).create();

                LayoutInflater inflater = LayoutInflater.from(PurchaseOrderActivity.this);
                View finance_method = inflater.inflate(R.layout.add_purchase_order_conditions,null);

                final EditText edtInput;
                Button btnRegister;

                edtInput = finance_method.findViewById(R.id.edtInput);
                btnRegister = finance_method.findViewById(R.id.btnRegister);

                btnRegister.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        txtSalesConditions.setText("DIRECCIÓN DE ENTREGA: "+edtInput.getText().toString());
                        sales_conditions_value = edtInput.getText().toString();
                        dialog.dismiss();
                    }
                });

                dialog.setView(finance_method);
                dialog.show();
            }
        });

        btnAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddProductDialog();
            }
        });

        btnRegisterPurchaseOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Long tsLong = System.currentTimeMillis()/1000;
                final String timestamp = tsLong.toString();
                Calendar calForDate = Calendar.getInstance();
                SimpleDateFormat currentDate = new SimpleDateFormat("dd-MM-yyyy");
                String saveCurrentDate = currentDate.format(calForDate.getTime());

                Calendar calForTime = Calendar.getInstance();
                SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss");
                String saveCurrentTime = currentTime.format(calForTime.getTime());

                Date date= new Date();
                Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                day = cal.get(Calendar.DAY_OF_MONTH);
                month = cal.get(Calendar.MONTH)+1;
                year = cal.get(Calendar.YEAR);

                companyRef.child(post_key).child("Purchased Orders").child(timestamp).child("purchase_order_id").setValue(timestamp);
                companyRef.child(post_key).child("Purchased Orders").child(timestamp).child("purchase_order_supplier_id").setValue(supplier_id);
                companyRef.child(post_key).child("Purchased Orders").child(timestamp).child("purchase_order_destination_address").setValue(address_value);
                companyRef.child(post_key).child("Purchased Orders").child(timestamp).child("purchase_order_sales_conditions").setValue(sales_conditions_value);
                companyRef.child(post_key).child("Purchased Orders").child(timestamp).child("purchase_order_total_amount").setValue(total_amount_st);
                companyRef.child(post_key).child("Purchased Orders").child(timestamp).child("timestamp").setValue(ServerValue.TIMESTAMP);
                companyRef.child(post_key).child("Purchased Orders").child(timestamp).child("operation_date").setValue(saveCurrentDate);
                companyRef.child(post_key).child("Purchased Orders").child(timestamp).child("operation_time").setValue(saveCurrentTime);
                companyRef.child(post_key).child("Purchased Orders").child(timestamp).child("operation_day").setValue(day+"");
                companyRef.child(post_key).child("Purchased Orders").child(timestamp).child("operation_month").setValue(month+"");
                companyRef.child(post_key).child("Purchased Orders").child(timestamp).child("operation_year").setValue(year+"");
                companyRef.child(post_key).child("Purchased Orders").child(timestamp).child("purchase_order_currency").setValue("PEN");

                companyRef.child(post_key).child("Purchased Order Items").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        companyRef.child(post_key).child("Purchased Orders").child(timestamp).child("Items").setValue(dataSnapshot.getValue(), new DatabaseReference.CompletionListener() {
                            @Override
                            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                                if (databaseError != null) {
                                    Toast.makeText(PurchaseOrderActivity.this, "Hubo un Error", Toast.LENGTH_SHORT).show();
                                } else {
                                    companyRef.child(post_key).child("Purchased Order Items").removeValue();
                                    Intent intent = new Intent(PurchaseOrderActivity.this, PurchaseOrdersListActivity.class);
                                    intent.putExtra("post_key",post_key);
                                    startActivity(intent);
                                    finish();
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
    }

    private void showTotalAmount() {
        companyRef.child(post_key).child("Purchased Order Items").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                double sum = 0;

                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Map<String, Object> map = (Map<String, Object>) ds.getValue();
                    Object price = map.get("item_total");
                    double total_value = Double.parseDouble(String.valueOf(price));
                    sum += total_value;

                    total_amount_st = decimalFormat.format(sum);

                    double taxes_db = sum*0.18;
                    double taxes_round = Math.round(taxes_db*100.0)/100.0;
                    total_taxes_st = decimalFormat.format(taxes_round);

                    txtTaxes.setText("IGV: S/ "+total_taxes_st);
                    txtTotalAmount.setText("TOTAL: S/ "+total_amount_st);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void showOrderPurchaseProducts() {
        Query query = companyRef.child(post_key).child("Purchased Order Items").orderByChild("timestamp");
        FirebaseRecyclerAdapter<SupplierProductsModel, SupplierProductsViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<SupplierProductsModel, SupplierProductsViewHolder>
                (SupplierProductsModel.class,R.layout.purchase_order_product_item,SupplierProductsViewHolder.class,query) {
            @Override
            protected void populateViewHolder(SupplierProductsViewHolder viewHolder, SupplierProductsModel model, int position) {
                viewHolder.setItem_description(model.getItem_description());
                viewHolder.setItem_name(model.getItem_name());
                viewHolder.setItem_price(model.getItem_price());
                viewHolder.setItem_quantity(model.getItem_quantity());
                viewHolder.setItem_total(model.getItem_total());

                viewHolder.txtDescription.setText(viewHolder.my_item_description);
                viewHolder.txtItemName.setText(viewHolder.my_item_name);
                viewHolder.txtPrice.setText("S/ "+viewHolder.my_item_price);
                viewHolder.txtQuantity.setText(viewHolder.my_item_quantity);
                viewHolder.txtTotal.setText("S/ "+viewHolder.my_item_total);
            }
        };
        recyclerView2.setAdapter(firebaseRecyclerAdapter);
    }

    private void showAddProductDialog() {
        final AlertDialog dialog = new AlertDialog.Builder(PurchaseOrderActivity.this).create();

        LayoutInflater inflater = LayoutInflater.from(PurchaseOrderActivity.this);
        View finance_method = inflater.inflate(R.layout.add_purchase_product_dialog,null);

        final EditText edtProductName,edtDescription,edtPrice,edtQuantity;
        Button btnRegisterNewProduct;
        final RelativeLayout rootLayout;

        edtProductName = finance_method.findViewById(R.id.edtProductName);
        edtDescription = finance_method.findViewById(R.id.edtDescription);
        edtPrice = finance_method.findViewById(R.id.edtPrice);
        edtQuantity = finance_method.findViewById(R.id.edtQuantity);
        btnRegisterNewProduct = finance_method.findViewById(R.id.btnRegisterNewProduct);
        rootLayout = finance_method.findViewById(R.id.rootLayout);

        btnRegisterNewProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(edtProductName.getText().toString())) {
                    Snackbar.make(rootLayout,"Debes completar todos los datos",Snackbar.LENGTH_SHORT);
                }
                else if (TextUtils.isEmpty(edtDescription.getText().toString())) {
                    Snackbar.make(rootLayout,"Debes completar todos los datos",Snackbar.LENGTH_SHORT);
                }
                else if (TextUtils.isEmpty(edtPrice.getText().toString())) {
                    Snackbar.make(rootLayout,"Debes completar todos los datos",Snackbar.LENGTH_SHORT);
                } else if (TextUtils.isEmpty(edtQuantity.getText().toString())) {
                    Snackbar.make(rootLayout, "Debes completar todos los datos", Snackbar.LENGTH_SHORT);
                } else {
                    double price = Double.parseDouble(edtPrice.getText().toString());
                    double quantity = Double.parseDouble(edtQuantity.getText().toString());
                    double total = price*quantity;
                    String total_st = decimalFormat.format(total);

                    Long tsLong = System.currentTimeMillis()/1000;
                    final String timestamp = tsLong.toString();

                    companyRef.child(post_key).child("Purchased Order Items").child(timestamp).child("item_name").setValue(edtProductName.getText().toString());
                    companyRef.child(post_key).child("Purchased Order Items").child(timestamp).child("item_description").setValue(edtDescription.getText().toString());
                    companyRef.child(post_key).child("Purchased Order Items").child(timestamp).child("item_price").setValue(edtPrice.getText().toString());
                    companyRef.child(post_key).child("Purchased Order Items").child(timestamp).child("item_quantity").setValue(edtQuantity.getText().toString());
                    companyRef.child(post_key).child("Purchased Order Items").child(timestamp).child("item_total").setValue(total_st);
                    companyRef.child(post_key).child("Purchased Order Items").child(timestamp).child("timestamp").setValue(ServerValue.TIMESTAMP);
                    Toasty.success(PurchaseOrderActivity.this, edtProductName.getText().toString()+" Registrado con éxito", Toast.LENGTH_LONG).show();
                    dialog.dismiss();

                }
            }
        });

        dialog.setView(finance_method);
        dialog.show();
    }

    private void showAddAddressDialog() {
        final AlertDialog dialog = new AlertDialog.Builder(PurchaseOrderActivity.this).create();

        LayoutInflater inflater = LayoutInflater.from(PurchaseOrderActivity.this);
        View finance_method = inflater.inflate(R.layout.add_purchase_order_address,null);

        final EditText edtInput;
        Button btnRegister;

        edtInput = finance_method.findViewById(R.id.edtInput);
        btnRegister = finance_method.findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtAddress.setText("DIRECCIÓN DE ENTREGA: "+edtInput.getText().toString());
                address_value = edtInput.getText().toString();
                dialog.dismiss();
            }
        });

        dialog.setView(finance_method);
        dialog.show();
    }


    private void showSupplierDialog() {
        dialog = new AlertDialog.Builder(PurchaseOrderActivity.this).create();

        LayoutInflater inflater = LayoutInflater.from(PurchaseOrderActivity.this);
        View finance_method = inflater.inflate(R.layout.supplier_list_dialog,null);

        Button btnAddSupplier;

        btnAddSupplier = finance_method.findViewById(R.id.btnAddSupplier);
        recyclerView = finance_method.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(PurchaseOrderActivity.this);
        linearLayoutManager.setReverseLayout(false);
        linearLayoutManager.setStackFromEnd(false);
        recyclerView.setLayoutManager(linearLayoutManager);

        showSuppliers();

        btnAddSupplier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddSupplierDialog();
            }
        });

        dialog.setView(finance_method);
        dialog.show();
    }

    private void showSuppliers() {
        Query query = companyRef.child(post_key).child("My Suppliers").orderByChild("timestamp");
        FirebaseRecyclerAdapter<SupplierModel, SuppliersViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<SupplierModel, SuppliersViewHolder>
                (SupplierModel.class, R.layout.supplier_item,SuppliersViewHolder.class,query) {
            @Override
            protected void populateViewHolder(final SuppliersViewHolder viewHolder, SupplierModel model, int position) {
                viewHolder.setSupplier_contact(model.getSupplier_contact());
                viewHolder.setSupplier_document_number(model.getSupplier_document_number());
                viewHolder.setSupplier_email(model.getSupplier_email());
                viewHolder.setSupplier_id(model.getSupplier_id());
                viewHolder.setSupplier_name(model.getSupplier_name());
                viewHolder.setSupplier_phone(model.getSupplier_phone());

                viewHolder.txtName.setText(viewHolder.my_supplier_name);
                viewHolder.txtPhone.setText("Teléfono: "+viewHolder.my_supplier_phone);
                viewHolder.txtEmail.setText("Correo electrónico: "+viewHolder.my_supplier_email);
                viewHolder.txtContact.setText("Contacto: "+viewHolder.my_supplier_contact);

                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        txtSupplier.setText("PROVEEDOR: "+viewHolder.my_supplier_name);
                        txtContact.setText("CONTACTO: "+viewHolder.my_supplier_contact);
                        txtPhone.setText("TELÉFONO: "+viewHolder.my_supplier_phone);
                        txtEmail.setText("CORREO: "+viewHolder.my_supplier_email);
                        supplier_id = viewHolder.my_supplier_id;
                        dialog.dismiss();
                    }
                });
            }
        };
        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    private void showAddSupplierDialog() {
        final AlertDialog dialog = new AlertDialog.Builder(PurchaseOrderActivity.this).create();

        LayoutInflater inflater = LayoutInflater.from(PurchaseOrderActivity.this);
        View finance_method = inflater.inflate(R.layout.add_supplier_dialog,null);

        final EditText edtName,edtDocumentNumber,edtEmail,edtPhoneNumber,edtContact;
        final CountryCodePicker ccpPhoneCode;
        Button btnAddCustomer;
        TextView txtCancel;
        final LinearLayout rootLayout;

        edtName = finance_method.findViewById(R.id.edtName);
        edtDocumentNumber = finance_method.findViewById(R.id.edtDocumentNumber);
        edtEmail = finance_method.findViewById(R.id.edtEmail);
        edtPhoneNumber = finance_method.findViewById(R.id.edtPhoneNumber);
        edtContact = finance_method.findViewById(R.id.edtContact);
        ccpPhoneCode = finance_method.findViewById(R.id.ccpPhoneCode);
        btnAddCustomer = finance_method.findViewById(R.id.btnAddCustomer);
        txtCancel = finance_method.findViewById(R.id.txtCancel);
        rootLayout = finance_method.findViewById(R.id.rootLayout);

        btnAddCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(edtName.getText().toString())) {
                    Snackbar.make(rootLayout,"Debes completar todos los datos",Snackbar.LENGTH_LONG);
                }
                else  if (TextUtils.isEmpty(edtDocumentNumber.getText().toString())) {
                    Snackbar.make(rootLayout,"Debes completar todos los datos",Snackbar.LENGTH_LONG);
                }
                else  if (TextUtils.isEmpty(edtEmail.getText().toString())) {
                    Snackbar.make(rootLayout,"Debes completar todos los datos",Snackbar.LENGTH_LONG);
                }
                else  if (TextUtils.isEmpty(edtPhoneNumber.getText().toString())) {
                    Snackbar.make(rootLayout,"Debes completar todos los datos",Snackbar.LENGTH_LONG);
                }
                else  if (TextUtils.isEmpty(edtContact.getText().toString())) {
                    Snackbar.make(rootLayout,"Debes completar todos los datos",Snackbar.LENGTH_LONG);
                } else {
                    Long tsLong = System.currentTimeMillis()/1000;
                    final String timestamp = tsLong.toString();

                    companyRef.child(post_key).child("My Suppliers").child(timestamp).child("supplier_name").setValue(edtName.getText().toString());
                    companyRef.child(post_key).child("My Suppliers").child(timestamp).child("supplier_document_number").setValue(edtDocumentNumber.getText().toString());
                    companyRef.child(post_key).child("My Suppliers").child(timestamp).child("supplier_email").setValue(edtEmail.getText().toString());
                    companyRef.child(post_key).child("My Suppliers").child(timestamp).child("supplier_phone").setValue(ccpPhoneCode.getSelectedCountryCode()+""+edtPhoneNumber.getText().toString());
                    companyRef.child(post_key).child("My Suppliers").child(timestamp).child("supplier_contact").setValue(edtContact.getText().toString());
                    companyRef.child(post_key).child("My Suppliers").child(timestamp).child("supplier_id").setValue(timestamp);
                    Toasty.success(PurchaseOrderActivity.this, "Registrado", Toast.LENGTH_LONG).show();
                    dialog.dismiss();

                }
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

    public static class SuppliersViewHolder extends RecyclerView.ViewHolder {
        View mView;
        String my_supplier_name,my_supplier_document_number,my_supplier_email,my_supplier_phone,my_supplier_contact,my_supplier_id;
        TextView txtName,txtPhone,txtEmail,txtContact;

        public SuppliersViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;

            txtName = mView.findViewById(R.id.txtName);
            txtPhone = mView.findViewById(R.id.txtPhone);
            txtEmail = mView.findViewById(R.id.txtEmail);
            txtContact = mView.findViewById(R.id.txtContact);

        }
        public void setSupplier_name(String supplier_name) {
            my_supplier_name = supplier_name;
        }


        public void setSupplier_document_number(String supplier_document_number) {
            my_supplier_document_number = supplier_document_number;
        }

        public void setSupplier_email(String supplier_email) {
            my_supplier_email = supplier_email;
        }


        public void setSupplier_phone(String supplier_phone) {
            my_supplier_phone = supplier_phone;
        }

        public void setSupplier_contact(String supplier_contact) {
            my_supplier_contact = supplier_contact;
        }

        public void setSupplier_id(String supplier_id) {
            my_supplier_id = supplier_id;
        }
    }

    public static class SupplierProductsViewHolder extends RecyclerView.ViewHolder {

        View mView;
        String my_item_description,my_item_name,my_item_price,my_item_quantity,my_item_total;
        TextView txtItemName,txtDescription,txtPrice,txtQuantity,txtTotal;

        public SupplierProductsViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;

            txtItemName = mView.findViewById(R.id.txtItemName);
            txtDescription = mView.findViewById(R.id.txtDescription);
            txtPrice = mView.findViewById(R.id.txtPrice);
            txtQuantity = mView.findViewById(R.id.txtQuantity);
            txtTotal = mView.findViewById(R.id.txtTotal);
        }
        public void setItem_description(String item_description) {
            my_item_description = item_description;
        }

        public void setItem_name(String item_name) {
            my_item_name = item_name;
        }
        public void setItem_price(String item_price) {
            my_item_price = item_price;
        }

        public void setItem_quantity(String item_quantity) {
            my_item_quantity = item_quantity;
        }

        public void setItem_total(String item_total) {
            my_item_total = item_total;
        }
    }
}
