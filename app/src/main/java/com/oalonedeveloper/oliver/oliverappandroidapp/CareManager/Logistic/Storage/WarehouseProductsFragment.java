package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Logistic.Storage;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
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
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Commercialization.BillsIssuing.CreateBillActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.Companies.ProductsModel;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;
import es.dmoral.toasty.Toasty;

import static android.app.Activity.RESULT_OK;


public class WarehouseProductsFragment extends Fragment {

    String post_key,warehouse_id,image_verification,current_time,downloadUrl,currentPhotoPath,warehouse_name,product_price;
    DatabaseReference companyRef;
    RecyclerView recyclerView;
    Button btnAddItems;
    ImageView imgProduct;
    ProgressDialog loadingBar;
    final static int Gallery_Pick = 2;
    int day,month,year;
    Uri imageUri;
    StorageReference userProfileImageRef;
    AlertDialog dialog_options,dialog_list;
    DecimalFormat decimalFormat;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_warehouse_products, container, false);


        post_key = getActivity().getIntent().getExtras().getString("post_key");
        warehouse_id = getActivity().getIntent().getExtras().getString("warehouse_id");
        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");
        userProfileImageRef = FirebaseStorage.getInstance().getReference().child("Product Images");

        decimalFormat = new DecimalFormat("0.00");

        loadingBar = new ProgressDialog(getActivity());

        recyclerView = view.findViewById(R.id.recyclerView);

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        image_verification = "";

        Date date= new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        day = cal.get(Calendar.DAY_OF_MONTH);
        month = cal.get(Calendar.MONTH)+1;
        year = cal.get(Calendar.YEAR);

        btnAddItems = view.findViewById(R.id.btnAddItems);

        btnAddItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddItemsDialog();
            }
        });

        companyRef.child(post_key).child("Warehouses").child(warehouse_id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                warehouse_name = dataSnapshot.child("warehouse_name").getValue().toString();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        showWarehouseProducts();

        return view;
    }

    private void showWarehouseProducts() {
        Query query = companyRef.child(post_key).child("Warehouses").child(warehouse_id).child("Products").orderByChild("warehouse_stock");
        FirebaseRecyclerAdapter<ProductsModel, CompanyProductsViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<ProductsModel, CompanyProductsViewHolder>
                (ProductsModel.class,R.layout.product_warehouse_item,CompanyProductsViewHolder.class,query) {
            @Override
            protected void populateViewHolder(final CompanyProductsViewHolder viewHolder, ProductsModel model, int position) {
                final String postKey = getRef(position).getKey();
                viewHolder.setProduct_image(model.getProduct_image());
                viewHolder.setProduct_currency(model.getProduct_currency());
                viewHolder.setProduct_description(model.getProduct_description());
                viewHolder.setProduct_measure(model.getProduct_measure());
                viewHolder.setUid(model.getUid());
                viewHolder.setCode(model.getCode());
                viewHolder.setProduct_name(model.getProduct_name());
                viewHolder.setProduct_price(model.getProduct_price());
                viewHolder.setProduct_stock(model.getProduct_stock());

                viewHolder.txtProductName.setText(viewHolder.my_product_name);
                Picasso.with(getActivity()).load(viewHolder.my_product_image).fit().into(viewHolder.imgProduct);
                viewHolder.txtWarehouseStock.setText(viewHolder.my_product_stock);
                companyRef.child(post_key).child("My Products").child(postKey).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChild("product_stock")) {
                            String product_stock = dataSnapshot.child("product_stock").getValue().toString();
                            viewHolder.txtStoreStock.setText(product_stock);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                viewHolder.btnActionButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), ProductKardexActivity.class);
                        intent.putExtra("warehouse_id",warehouse_id);
                        intent.putExtra("product_id",postKey);
                        intent.putExtra("company_id",post_key);
                        startActivity(intent);
                    }
                });

            }
        };
        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    private void showAddItemsDialog() {
        dialog_options = new AlertDialog.Builder(getActivity()).create();

        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View finance_method = inflater.inflate(R.layout.add_items_warehouse_dialog,null);

        ImageButton btnRegisterNewProduct,btnStoreProducts;

        btnRegisterNewProduct = finance_method.findViewById(R.id.btnRegisterNewProduct);
        btnStoreProducts = finance_method.findViewById(R.id.btnStoreProducts);

        btnRegisterNewProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewProductDialog();
            }

            private void addNewProductDialog() {
                final AlertDialog dialog = new AlertDialog.Builder(getActivity()).create();

                LayoutInflater inflater = LayoutInflater.from(getActivity());
                View finance_method = inflater.inflate(R.layout.add_new_warehouse_produtc_dialog,null);

                Button btnCamera,btnGallery,btnRegisterNewProduct;
                final EditText edtProductName,edtPrice,edtStock;
                final RelativeLayout rootLayout;

                imgProduct = finance_method.findViewById(R.id.imgProduct);
                btnCamera = finance_method.findViewById(R.id.btnCamera);
                btnGallery = finance_method.findViewById(R.id.btnGallery);
                edtProductName = finance_method.findViewById(R.id.edtProductName);
                edtPrice = finance_method.findViewById(R.id.edtPrice);
                btnRegisterNewProduct = finance_method.findViewById(R.id.btnRegisterNewProduct);
                rootLayout = finance_method.findViewById(R.id.rootLayout);
                edtStock = finance_method.findViewById(R.id.edtStock);

                btnCamera.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dispatchTakePictureIntent();
                    }
                });

                btnGallery.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent galleryIntent = new Intent();
                        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                        galleryIntent.setType("image/*");
                        startActivityForResult(galleryIntent, Gallery_Pick);
                    }
                });

                btnRegisterNewProduct.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!image_verification.equals("true")) {
                            Snackbar.make(rootLayout, "Debes subir una foto de perfil", Snackbar.LENGTH_LONG).show();
                            return;
                        }
                        else if (TextUtils.isEmpty(edtProductName.getText().toString())) {
                            Snackbar.make(rootLayout, "Debes ingresar el nombre de tu producto", Snackbar.LENGTH_LONG).show();
                            return;
                        } else if (TextUtils.isEmpty(edtPrice.getText().toString())) {
                            Snackbar.make(rootLayout, "Debes ingresar el precio de tu producto", Snackbar.LENGTH_LONG).show();
                            return;
                        } else {
                            loadingBar.setTitle("Registrando de tu Producto...");
                            loadingBar.setMessage("Cargando...");
                            loadingBar.show();
                            loadingBar.setCanceledOnTouchOutside(false);
                            loadingBar.setCancelable(false);

                            current_time = new SimpleDateFormat("HHmmss").format(Calendar.getInstance().getTime());

                            HashMap hashMap = new HashMap();
                            hashMap.put("product_image",downloadUrl);
                            hashMap.put("product_currency","PEN");
                            hashMap.put("product_description","");
                            hashMap.put("product_measure","quantity");
                            hashMap.put("company_id",post_key);
                            hashMap.put("code",day+""+month+""+year+""+current_time);
                            hashMap.put("product_name",edtProductName.getText().toString().toUpperCase());
                            hashMap.put("product_price",edtPrice.getText().toString());
                            hashMap.put("product_stock",edtStock.getText().toString());
                            hashMap.put("timestamp", ServerValue.TIMESTAMP);
                            companyRef.child(post_key).child("Warehouses").child(warehouse_id).child("Products").child(post_key+day+""+month+""+year+""+current_time).updateChildren(hashMap).addOnCompleteListener(new OnCompleteListener() {
                                @Override
                                public void onComplete(@NonNull Task task) {

                                    regusterKardexOperation();

                                    //showCompanyProducts();
                                }

                                private void regusterKardexOperation() {
                                    Long tsLong = System.currentTimeMillis()/1000;
                                    String timestamp = tsLong.toString();
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

                                    companyRef.child(post_key).child("Kardex").child(timestamp).child("product_id").setValue(post_key+day+""+month+""+year+""+current_time);
                                    companyRef.child(post_key).child("Kardex").child(timestamp).child("product_price").setValue(edtPrice.getText().toString());
                                    companyRef.child(post_key).child("Kardex").child(timestamp).child("warehouse_origin_id").setValue("new_product");
                                    companyRef.child(post_key).child("Kardex").child(timestamp).child("warehouse_destination_id").setValue(warehouse_id);
                                    companyRef.child(post_key).child("Kardex").child(timestamp).child("stock").setValue(edtStock.getText().toString());
                                    companyRef.child(post_key).child("Kardex").child(timestamp).child("operation_date").setValue(saveCurrentDate);
                                    companyRef.child(post_key).child("Kardex").child(timestamp).child("operation_time").setValue(saveCurrentTime);
                                    companyRef.child(post_key).child("Kardex").child(timestamp).child("operation_day").setValue(day+"");
                                    companyRef.child(post_key).child("Kardex").child(timestamp).child("operation_month").setValue(month+"");
                                    companyRef.child(post_key).child("Kardex").child(timestamp).child("operation_year").setValue(year+"");
                                    companyRef.child(post_key).child("Kardex").child(timestamp).child("operation_type").setValue("purchase");
                                    companyRef.child(post_key).child("Kardex").child(timestamp).child("timestamp").setValue(ServerValue.TIMESTAMP);

                                    Toasty.success(getActivity(), "Existencia Registrada", Toast.LENGTH_LONG).show();
                                    dialog.dismiss();
                                    dialog_options.dismiss();
                                    loadingBar.dismiss();
                                }
                            });
                        }
                    }
                });



                dialog.setView(finance_method);
                dialog.show();
            }
        });

        btnStoreProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showStoreProductsDialog();
            }

            private void showStoreProductsDialog() {
                dialog_list = new AlertDialog.Builder(getActivity()).create();

                LayoutInflater inflater = LayoutInflater.from(getActivity());
                View finance_method = inflater.inflate(R.layout.add_store_products_to_warehouse_dialog,null);

                Button btnRegisterNewProduct;

                btnRegisterNewProduct = finance_method.findViewById(R.id.btnRegisterNewProduct);
                recyclerView = finance_method.findViewById(R.id.recyclerView);

                btnRegisterNewProduct.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showRegisterNewProductDialog();
                    }

                    private void showRegisterNewProductDialog() {
                        final AlertDialog dialog = new AlertDialog.Builder(getActivity()).create();

                        LayoutInflater inflater = LayoutInflater.from(getActivity());
                        View finance_method = inflater.inflate(R.layout.add_new_product_dialog,null);


                        Button btnCamera,btnGallery,btnRegisterNewProduct;
                        final EditText edtProductName,edtPrice,edtStock;
                        final RelativeLayout rootLayout;

                        imgProduct = finance_method.findViewById(R.id.imgProduct);
                        btnCamera = finance_method.findViewById(R.id.btnCamera);
                        btnGallery = finance_method.findViewById(R.id.btnGallery);
                        edtProductName = finance_method.findViewById(R.id.edtProductName);
                        edtPrice = finance_method.findViewById(R.id.edtPrice);
                        btnRegisterNewProduct = finance_method.findViewById(R.id.btnRegisterNewProduct);
                        rootLayout = finance_method.findViewById(R.id.rootLayout);
                        edtStock = finance_method.findViewById(R.id.edtStock);

                        btnCamera.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dispatchTakePictureIntent();
                            }
                        });

                        btnGallery.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent galleryIntent = new Intent();
                                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                                galleryIntent.setType("image/*");
                                startActivityForResult(galleryIntent, Gallery_Pick);
                            }
                        });

                        btnRegisterNewProduct.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (!image_verification.equals("true")) {
                                    Snackbar.make(rootLayout, "Debes subir una foto de perfil", Snackbar.LENGTH_LONG).show();
                                    return;
                                }
                                else if (TextUtils.isEmpty(edtProductName.getText().toString())) {
                                    Snackbar.make(rootLayout, "Debes ingresar el nombre de tu producto", Snackbar.LENGTH_LONG).show();
                                    return;
                                } else if (TextUtils.isEmpty(edtPrice.getText().toString())) {
                                    Snackbar.make(rootLayout, "Debes ingresar el precio de tu producto", Snackbar.LENGTH_LONG).show();
                                    return;
                                } else {
                                    loadingBar.setTitle("Registrando de tu Producto...");
                                    loadingBar.setMessage("Cargando...");
                                    loadingBar.show();
                                    loadingBar.setCanceledOnTouchOutside(false);
                                    loadingBar.setCancelable(false);

                                    current_time = new SimpleDateFormat("HHmmss").format(Calendar.getInstance().getTime());

                                    HashMap hashMap = new HashMap();
                                    hashMap.put("product_image",downloadUrl);
                                    hashMap.put("product_currency","PEN");
                                    hashMap.put("product_description","");
                                    hashMap.put("product_measure","quantity");
                                    hashMap.put("company_id",post_key);
                                    hashMap.put("code",day+""+month+""+year+""+current_time);
                                    hashMap.put("product_name",edtProductName.getText().toString().toUpperCase());
                                    hashMap.put("product_price",edtPrice.getText().toString());
                                    hashMap.put("product_stock",edtStock.getText().toString());
                                    hashMap.put("timestamp", ServerValue.TIMESTAMP);
                                    companyRef.child(post_key).child("My Products").child(post_key+day+""+month+""+year+""+current_time).updateChildren(hashMap).addOnCompleteListener(new OnCompleteListener() {
                                        @Override
                                        public void onComplete(@NonNull Task task) {
                                            Toasty.success(getActivity(), "Producto Registrado", Toast.LENGTH_LONG).show();
                                            dialog.dismiss();
                                            loadingBar.dismiss();
                                            showCompanyProducts();
                                        }
                                    });
                                }
                            }
                        });

                        dialog.setView(finance_method);
                        dialog.show();
                    }
                });

                recyclerView.setHasFixedSize(true);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                linearLayoutManager.setReverseLayout(false);
                linearLayoutManager.setStackFromEnd(false);
                recyclerView.setLayoutManager(linearLayoutManager);

                showCompanyProducts();

                dialog_list.setView(finance_method);
                dialog_list.show();
            }
        });

        dialog_options.setView(finance_method);
        dialog_options.show();


    }

    private void showCompanyProducts() {
        Query query = companyRef.child(post_key).child("My Products").orderByChild("timestamp");
        FirebaseRecyclerAdapter<ProductsModel, CompanyProductsViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<ProductsModel, CompanyProductsViewHolder>
                (ProductsModel.class,R.layout.product_bill_item, CompanyProductsViewHolder.class,query) {
            @Override
            protected void populateViewHolder(final CompanyProductsViewHolder viewHolder, ProductsModel model, int position) {
                final String postKey = getRef(position).getKey();
                viewHolder.setProduct_image(model.getProduct_image());
                viewHolder.setProduct_currency(model.getProduct_currency());
                viewHolder.setProduct_description(model.getProduct_description());
                viewHolder.setProduct_measure(model.getProduct_measure());
                viewHolder.setUid(model.getUid());
                viewHolder.setCode(model.getCode());
                viewHolder.setProduct_name(model.getProduct_name());
                viewHolder.setProduct_price(model.getProduct_price());
                viewHolder.setProduct_stock(model.getProduct_stock());

                viewHolder.txtProductName.setText(viewHolder.my_product_name);
                viewHolder.txtProductPrice.setText(viewHolder.my_product_stock);
                Picasso.with(getActivity()).load(viewHolder.my_product_image).fit().into(viewHolder.imgProduct);

                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showTransferItemsDialog();
                    }

                    private void showTransferItemsDialog() {
                        final AlertDialog dialog = new AlertDialog.Builder(getActivity()).create();

                        LayoutInflater inflater = LayoutInflater.from(getActivity());
                        View finance_method = inflater.inflate(R.layout.transfer_items_dialog,null);

                        CircleImageView imgProductDialog;
                        TextView txtProductName;
                        final EditText edtQuantity;
                        Button btnRegister;
                        final RelativeLayout rootLayout;

                        imgProductDialog = finance_method.findViewById(R.id.imgProduct);
                        txtProductName = finance_method.findViewById(R.id.txtProductName);
                        edtQuantity = finance_method.findViewById(R.id.edtQuantity);
                        btnRegister = finance_method.findViewById(R.id.btnRegister);
                        rootLayout = finance_method.findViewById(R.id.rootLayout);

                        Picasso.with(getActivity()).load(viewHolder.my_product_image).fit().into(imgProductDialog);
                        txtProductName.setText("Stock actual en Tienda de "+viewHolder.my_product_name+": "+viewHolder.my_product_stock);

                        btnRegister.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                final double current_stock = Double.parseDouble(viewHolder.my_product_stock);
                                final double stock_to_transfer = Double.parseDouble(edtQuantity.getText().toString());

                                if (current_stock < stock_to_transfer) {
                                    Snackbar.make(rootLayout, "Este monto sobrepasa tu stock actual", Snackbar.LENGTH_LONG).show();
                                } else {
                                    companyRef.child(post_key).child("Warehouses").child(warehouse_id).child("Products").child(postKey).addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {
                                            double warehouse_product_stock;
                                            if (dataSnapshot.hasChild("product_stock")) {

                                                String product_stock = dataSnapshot.child("product_stock").getValue().toString();
                                                product_price = dataSnapshot.child("product_price").getValue().toString();
                                                warehouse_product_stock = Double.parseDouble(product_stock);

                                                double stock_on_store = current_stock - stock_to_transfer;
                                                String stock_on_store_st = decimalFormat.format(stock_on_store);
                                                companyRef.child(post_key).child("My Products").child(postKey).child("product_stock").setValue(stock_on_store_st);
                                                double stock_on_warehouse = warehouse_product_stock + stock_to_transfer;
                                                String stock_on_warehouse_st = decimalFormat.format(stock_on_warehouse);
                                                companyRef.child(post_key).child("Warehouses").child(warehouse_id).child("Products").child(postKey).child("product_stock").setValue(stock_on_warehouse_st);

                                                registerKardexOperation();

                                            } else {

                                                companyRef.child(post_key).child("My Products").child(postKey).addListenerForSingleValueEvent(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                                        String product_image = dataSnapshot.child("product_image").getValue().toString();
                                                        String product_currency = dataSnapshot.child("product_currency").getValue().toString();
                                                        String product_description = dataSnapshot.child("product_description").getValue().toString();
                                                        String product_measure = dataSnapshot.child("product_measure").getValue().toString();
                                                        String company_id = dataSnapshot.child("company_id").getValue().toString();
                                                        String code = dataSnapshot.child("code").getValue().toString();
                                                        String product_name = dataSnapshot.child("product_name").getValue().toString();
                                                        product_price = dataSnapshot.child("product_price").getValue().toString();
                                                        String product_stock = dataSnapshot.child("product_stock").getValue().toString();
                                                        long timestamp = dataSnapshot.child("timestamp").getValue(Long.class);



                                                        double stock_on_store = current_stock - stock_to_transfer;
                                                        String stock_on_store_st = decimalFormat.format(stock_on_store);
                                                        companyRef.child(post_key).child("My Products").child(postKey).child("product_stock").setValue(stock_on_store_st);

                                                        companyRef.child(post_key).child("Warehouses").child(warehouse_id).child("Products").child(postKey).child("product_stock").setValue(edtQuantity.getText().toString());
                                                        companyRef.child(post_key).child("Warehouses").child(warehouse_id).child("Products").child(postKey).child("product_image").setValue(product_image);
                                                        companyRef.child(post_key).child("Warehouses").child(warehouse_id).child("Products").child(postKey).child("product_currency").setValue(product_currency);
                                                        companyRef.child(post_key).child("Warehouses").child(warehouse_id).child("Products").child(postKey).child("product_description").setValue(product_description);
                                                        companyRef.child(post_key).child("Warehouses").child(warehouse_id).child("Products").child(postKey).child("product_measure").setValue(product_measure);
                                                        companyRef.child(post_key).child("Warehouses").child(warehouse_id).child("Products").child(postKey).child("company_id").setValue(company_id);
                                                        companyRef.child(post_key).child("Warehouses").child(warehouse_id).child("Products").child(postKey).child("code").setValue(code);
                                                        companyRef.child(post_key).child("Warehouses").child(warehouse_id).child("Products").child(postKey).child("product_name").setValue(product_name);
                                                        companyRef.child(post_key).child("Warehouses").child(warehouse_id).child("Products").child(postKey).child("product_price").setValue(product_price);
                                                        companyRef.child(post_key).child("Warehouses").child(warehouse_id).child("Products").child(postKey).child("timestamp").setValue(timestamp);

                                                        registerKardexOperation();



                                                    }

                                                    @Override
                                                    public void onCancelled(DatabaseError databaseError) {

                                                    }
                                                });


                                            }
                                        }

                                        private void registerKardexOperation() {
                                            Long tsLong = System.currentTimeMillis()/1000;
                                            String timestamp = tsLong.toString();
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

                                            companyRef.child(post_key).child("Kardex").child(timestamp).child("product_id").setValue(postKey);
                                            companyRef.child(post_key).child("Kardex").child(timestamp).child("product_price").setValue(product_price);
                                            companyRef.child(post_key).child("Kardex").child(timestamp).child("warehouse_origin_id").setValue("store");
                                            companyRef.child(post_key).child("Kardex").child(timestamp).child("warehouse_destination_id").setValue(warehouse_id);
                                            companyRef.child(post_key).child("Kardex").child(timestamp).child("stock").setValue(edtQuantity.getText().toString());
                                            companyRef.child(post_key).child("Kardex").child(timestamp).child("operation_date").setValue(saveCurrentDate);
                                            companyRef.child(post_key).child("Kardex").child(timestamp).child("operation_time").setValue(saveCurrentTime);
                                            companyRef.child(post_key).child("Kardex").child(timestamp).child("operation_day").setValue(day+"");
                                            companyRef.child(post_key).child("Kardex").child(timestamp).child("operation_month").setValue(month+"");
                                            companyRef.child(post_key).child("Kardex").child(timestamp).child("operation_year").setValue(year+"");
                                            companyRef.child(post_key).child("Kardex").child(timestamp).child("operation_type").setValue("from_store_transfer");
                                            companyRef.child(post_key).child("Kardex").child(timestamp).child("timestamp").setValue(ServerValue.TIMESTAMP);

                                            Toasty.success(getActivity(), "Transferencia realizada con éxito", Toast.LENGTH_LONG).show();
                                            dialog.dismiss();

                                        }

                                        @Override
                                        public void onCancelled(DatabaseError databaseError) {

                                        }
                                    });

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

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }

    static final int REQUEST_TAKE_PHOTO = 1;

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File...
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(getActivity(),
                        "com.oalonedeveloper.oliver.oliverappandroidapp.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }

    private void setPic() {
        // Get the dimensions of the View
        int targetW = imgProduct.getWidth();
        int targetH = imgProduct.getHeight();

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;

        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = Math.min(photoW/targetW, photoH/targetH);

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        Bitmap bitmap = BitmapFactory.decodeFile(currentPhotoPath, bmOptions);
        imgProduct.setImageBitmap(bitmap);

        Long tsLong = System.currentTimeMillis()/1000;
        String timestamp = tsLong.toString();

        String path = MediaStore.Images.Media.insertImage(getActivity().getContentResolver(), bitmap, "Title"+timestamp, null);
        imageUri = imageUri.parse(path);


        savePhotoOnDatabase();

    }

    private void savePhotoOnDatabase() {
        loadingBar.setTitle("Subiendo la imágen de tu Producto...");
        loadingBar.setMessage("Cargando...");
        loadingBar.show();
        loadingBar.setCanceledOnTouchOutside(false);
        loadingBar.setCancelable(false);

        StorageReference filePath = userProfileImageRef.child(imageUri.getLastPathSegment()+post_key+".jpg");
        filePath.putFile(imageUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                if (task.isSuccessful()) {
                    downloadUrl = task.getResult().getDownloadUrl().toString();
                    image_verification = "true";
                    loadingBar.dismiss();
                }
            }
        });

    }

    private void galleryAddPic() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(currentPhotoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        getActivity().sendBroadcast(mediaScanIntent);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
            galleryAddPic();
            setPic();

            /*Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            profileImage.setImageBitmap(imageBitmap);*/
        }
        if (requestCode == Gallery_Pick && resultCode == RESULT_OK && data != null) {

            imageUri = data.getData();
            try {
                Bitmap imageBitmap =MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), imageUri);
                imgProduct.setImageBitmap(imageBitmap);
                // Get the dimensions of the View
                int targetW = imgProduct.getWidth();
                int targetH = imgProduct.getHeight();

                // Get the dimensions of the bitmap
                BitmapFactory.Options bmOptions = new BitmapFactory.Options();
                bmOptions.inJustDecodeBounds = true;

                int photoW = bmOptions.outWidth;
                int photoH = bmOptions.outHeight;

                // Determine how much to scale down the image
                int scaleFactor = Math.min(photoW/targetW, photoH/targetH);

                // Decode the image file into a Bitmap sized to fill the View
                bmOptions.inJustDecodeBounds = false;
                bmOptions.inSampleSize = scaleFactor;
                bmOptions.inPurgeable = true;

                //imageBitmap = BitmapFactory.decodeFile(currentPhotoPath, bmOptions);


                imgProduct.setImageBitmap(imageBitmap);

                /*ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, bytes);
                String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), imageBitmap, "Title", null);
                imageUri = imageUri.parse(path);*/
                //txtImageVerification.setText("Imágen cargada con éxito");
                savePhotoOnDatabase();


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static class CompanyProductsViewHolder extends RecyclerView.ViewHolder {
        View mView;
        String my_product_image,my_product_currency,my_product_description,my_product_measure,my_uid, my_code,my_product_name,my_product_price,my_product_stock;
        CircleImageView imgProduct;
        TextView txtProductName,txtWarehouseStock,txtStoreStock,txtProductPrice;
        ImageView btnActionButton;

        public CompanyProductsViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;

            imgProduct = mView.findViewById(R.id.imgProduct);
            txtProductName = mView.findViewById(R.id.txtProductName);
            txtWarehouseStock = mView.findViewById(R.id.txtWarehouseStock);
            txtStoreStock = mView.findViewById(R.id.txtStoreStock);
            txtProductPrice = mView.findViewById(R.id.txtProductPrice);
            btnActionButton = mView.findViewById(R.id.btnActionButton);
        }
        public void setProduct_image(String product_image) {
            my_product_image = product_image;
        }
        public void setProduct_currency(String product_currency) {
            my_product_currency = product_currency;
        }
        public void setProduct_description(String product_description) {
            my_product_description = product_description;
        }
        public void setProduct_measure(String product_measure) {
            my_product_measure = product_measure;
        }
        public void setUid(String uid) {
            my_uid = uid;
        }
        public void setCode(String code) {
            my_code = code;
        }
        public void setProduct_name(String product_name) {
            my_product_name = product_name;
        }
        public void setProduct_price(String product_price) {
            my_product_price = product_price;
        }
        public void setProduct_stock(String product_stock) {
            my_product_stock = product_stock;
        }

    }

}
