package com.oalonedeveloper.oliver.oliverappandroidapp.Companies;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Commercialization.BillsIssuing.CreateInvoiceActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;
import es.dmoral.toasty.Toasty;

public class MyProductsActivity extends AppCompatActivity {

    DatabaseReference companyRef;
    String post_key;
    RecyclerView recyclerView;
    Button btnAddProduct;
    Uri imageUri;
    StorageReference userProfileImageRef;

    ImageView imgProduct;
    ProgressDialog loadingBar;

    String currentPhotoPath,image_verification,current_time,downloadUrl;

    final static int Gallery_Pick = 2;
    static final int REQUEST_TAKE_PHOTO = 1;
    int day,month,year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_products);

        post_key = getIntent().getExtras().getString("post_key");
        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");
        userProfileImageRef = FirebaseStorage.getInstance().getReference().child("Product Images");

        recyclerView = findViewById(R.id.recyclerView);
        loadingBar = new ProgressDialog(this);

        Date date= new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        day = cal.get(Calendar.DAY_OF_MONTH);
        month = cal.get(Calendar.MONTH)+1;
        year = cal.get(Calendar.YEAR);

        current_time = new SimpleDateFormat("HHmmss").format(Calendar.getInstance().getTime());

        recyclerView.setHasFixedSize(true);
        GridLayoutManager linearLayoutManager = new GridLayoutManager(this,2);
        linearLayoutManager.setReverseLayout(false);
        linearLayoutManager.setStackFromEnd(false);
        recyclerView.setLayoutManager(linearLayoutManager);

        btnAddProduct = findViewById(R.id.btnAddProduct);
        image_verification = "";

        btnAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddProductDialog();
            }
        });

        showProducts();
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }


    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File...
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.oalonedeveloper.oliver.oliverappandroidapp.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }

    private void showAddProductDialog() {
        final AlertDialog dialog = new AlertDialog.Builder(MyProductsActivity.this).create();

        LayoutInflater inflater = LayoutInflater.from(MyProductsActivity.this);
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
                            Toasty.success(MyProductsActivity.this, "Producto Registrado", Toast.LENGTH_LONG).show();
                            dialog.dismiss();
                            loadingBar.dismiss();
                        }
                    });
                }
            }
        });

        dialog.setView(finance_method);
        dialog.show();
    }

    private void showProducts() {
        Query query = companyRef.child(post_key).child("My Products").orderByChild("timestamp");
        FirebaseRecyclerAdapter<ProductsModel, CompanyProductsViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<ProductsModel, CompanyProductsViewHolder>
                (ProductsModel.class,R.layout.my_product_item,CompanyProductsViewHolder.class,query) {
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
                viewHolder.txtProductPrice.setText("S/ "+viewHolder.my_product_price);
                Picasso.with(MyProductsActivity.this).load(viewHolder.my_product_image).fit().into(viewHolder.imgProduct);



            }
        };
        recyclerView.setAdapter(firebaseRecyclerAdapter);

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

        String path = MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, "image"+timestamp, null);
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
        sendBroadcast(mediaScanIntent);

        setPic();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
            galleryAddPic();

            /*Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            profileImage.setImageBitmap(imageBitmap);*/
        }
        if (requestCode == Gallery_Pick && resultCode == RESULT_OK && data != null) {

            imageUri = data.getData();
            try {
                Bitmap imageBitmap =MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
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
        ImageView imgProduct;
        TextView txtProductName,txtProductPrice;

        public CompanyProductsViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;

            imgProduct = mView.findViewById(R.id.imgProduct);
            txtProductName = mView.findViewById(R.id.txtProductName);
            txtProductPrice = mView.findViewById(R.id.txtProductPrice);
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
