package com.oalonedeveloper.oliver.oliverappandroidapp.Companies;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.provider.MediaStore;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.face.Face;
import com.google.android.gms.vision.face.FaceDetector;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;
import com.oalonedeveloper.oliver.oliverappandroidapp.RegistrationData.RegistrationDataActivity;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.app.Activity.RESULT_OK;


public class RegisterCompanyData1Fragment extends Fragment {

    ImageView profileImage,profileImageState;
    LinearLayout btnCamera,btnGallery;
    String currentPhotoPath,image_verification;
    FirebaseAuth mAuth;
    DatabaseReference userRef;
    String currentUserID,downloadUrl,profileimage;
    StorageReference userProfileImageRef;
    Uri imageUri;
    TextView txtImageVerification;
    final static int Gallery_Pick = 2;
    ProgressDialog loadingBar;
    Button btnContinue;
    RelativeLayout rootLayout;

    Fragment fragment2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register_company_data1, container, false);

        fragment2 = new RegisterCompanyData2Fragment();

        profileImage = view.findViewById(R.id.profileImage);
        btnCamera = view.findViewById(R.id.btnCamera);
        btnGallery = view.findViewById(R.id.btnGallery);
        txtImageVerification = view.findViewById(R.id.txtImageVerification);
        profileImageState = view.findViewById(R.id.profileImageState);
        rootLayout = view.findViewById(R.id.rootLayout);
        btnContinue = view.findViewById(R.id.btnContinue);
        loadingBar = new ProgressDialog(getActivity());

        mAuth = FirebaseAuth.getInstance();
        currentUserID = mAuth.getCurrentUser().getUid();
        userRef = FirebaseDatabase.getInstance().getReference().child("Users").child(currentUserID).child("Company Registration");
        userProfileImageRef = FirebaseStorage.getInstance().getReference().child("Company Profile Images");

        image_verification = "false";

        loadingBar.setTitle("Preparando todo...");
        loadingBar.setMessage("Cargando...");
        loadingBar.show();
        loadingBar.setCanceledOnTouchOutside(false);
        loadingBar.setCancelable(false);

        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild("company_profileimage")) {
                    profileimage = dataSnapshot.child("company_profileimage").getValue().toString();
                    Picasso.with(getActivity()).load(profileimage).fit().into(profileImage);
                    image_verification = "true";
                    txtImageVerification.setText("Foto subida con éxito");
                    txtImageVerification.setTextColor(Color.GREEN);
                    profileImageState.setImageResource(R.drawable.check);
                    loadingBar.dismiss();
                } else {
                    loadingBar.dismiss();
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //Take picture button
        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dispatchTakePictureIntent();
            }
        });

        btnGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent galleryIntent = new Intent();
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, Gallery_Pick);
            }
        });

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (image_verification.equals("false")) {
                    showSkipPhotoDialog();

                }
                else if (image_verification.equals("true")) {
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.contentFragment,fragment2).commit();

                }
            }
        });

        return view;
    }

    private void showSkipPhotoDialog() {
        final AlertDialog dialog = new AlertDialog.Builder(getActivity()).create();
        dialog.setCancelable(false);

        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View finance_method = inflater.inflate(R.layout.skip_photo_dialog,null);

        TextView txtYes;
        Button btnNo;

        txtYes = finance_method.findViewById(R.id.txtYes);
        btnNo = finance_method.findViewById(R.id.btnNo);

        txtYes.setTextColor(getResources().getColor(R.color.orange1));
        btnNo.setBackgroundResource(R.drawable.orange_button_style_ripple);

        txtYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userRef.child("company_profileimage").setValue("https://oliver.com.pe/wp-content/uploads/2021/04/agregar_negocio.png");
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.contentFragment,fragment2).commit();
                dialog.dismiss();
            }
        });
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.setView(finance_method);
        dialog.show();
    }

    private void setPic() {
        // Get the dimensions of the View
        int targetW = profileImage.getWidth();
        int targetH = profileImage.getHeight();

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
        profileImage.setImageBitmap(bitmap);

        String path = MediaStore.Images.Media.insertImage(getActivity().getContentResolver(), bitmap, "Title", null);
        imageUri = imageUri.parse(path);

        txtImageVerification.setText("Foto subida con éxito");
        savePhotoOnDatabase();

    }

    private void savePhotoOnDatabase() {

        loadingBar.setTitle("Subiendo la imágen de tu Negocio...");
        loadingBar.setMessage("Cargando...");
        loadingBar.show();
        loadingBar.setCanceledOnTouchOutside(false);
        loadingBar.setCancelable(false);

        StorageReference filePath = userProfileImageRef.child(imageUri.getLastPathSegment()+currentUserID+".jpg");
        filePath.putFile(imageUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                if (task.isSuccessful()) {
                    downloadUrl = task.getResult().getDownloadUrl().toString();
                    userRef.child("company_profileimage").setValue(downloadUrl);
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


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
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
                profileImage.setImageBitmap(imageBitmap);
                // Get the dimensions of the View
                int targetW = profileImage.getWidth();
                int targetH = profileImage.getHeight();

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


                profileImage.setImageBitmap(imageBitmap);

                /*ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, bytes);
                String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), imageBitmap, "Title", null);
                imageUri = imageUri.parse(path);*/
                //txtImageVerification.setText("Imágen cargada con éxito");
                txtImageVerification.setText("Foto subida con éxito");
                savePhotoOnDatabase();


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}