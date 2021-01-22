package com.oalonedeveloper.oliver.oliverappandroidapp.FinancialManagement.LendingProduct;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;
import com.squareup.picasso.Picasso;

import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;
import es.dmoral.toasty.Toasty;

public class UploadDocumentationActivity extends AppCompatActivity {

    CircleImageView btnUploadFiles;
    static final int RESULT_LOAD_IMAGE = 1;
    StorageReference mStorage;
    String downloadUrl, currentUid,product_key,institution_key,doc_key,financial_institution_background_image,doc_tittle;
    FirebaseAuth mAuth;
    DatabaseReference expressLoanRef,financialInstitutionsRef;
    ProgressDialog loadingBar;
    RecyclerView recyclerView;
    TextView txtDocName,txtDocDescription;
    ImageView imgBackgroundButton;
    LinearLayout btnActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_documentation);

        btnUploadFiles = findViewById(R.id.btnUploadFiles);
        mStorage = FirebaseStorage.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        loadingBar = new ProgressDialog(this);

        txtDocName = findViewById(R.id.txtDocName);
        txtDocDescription = findViewById(R.id.txtDocDescription);
        imgBackgroundButton = findViewById(R.id.imgBackgroundButton);
        btnActionButton = findViewById(R.id.btnActionButton);

        currentUid = mAuth.getCurrentUser().getUid();

        product_key = getIntent().getExtras().getString("product_key");
        institution_key = getIntent().getExtras().getString("institution_key");
        doc_key = getIntent().getExtras().getString("doc_key");

        expressLoanRef = FirebaseDatabase.getInstance().getReference().child("Users").child(currentUid).child(institution_key).child(product_key).child(doc_key);
        financialInstitutionsRef = FirebaseDatabase.getInstance().getReference().child("Financial Institutions");

        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(false);
        linearLayoutManager.setStackFromEnd(false);
        recyclerView.setLayoutManager(linearLayoutManager);
        showFiles();

        financialInstitutionsRef.child(institution_key).child("Products").child(product_key).child("Required Documentation").child(doc_key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                    doc_tittle = dataSnapshot.child("doc_tittle").getValue().toString();
                String doc_description = dataSnapshot.child("doc_description").getValue().toString();

                txtDocName.setText(doc_tittle);
                txtDocDescription.setText(doc_description);

                financialInstitutionsRef.child(institution_key).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        financial_institution_background_image = dataSnapshot.child("financial_institution_background_image").getValue().toString();
                        Picasso.with(UploadDocumentationActivity.this).load(financial_institution_background_image).fit().into(imgBackgroundButton);
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

        btnActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UploadDocumentationActivity.this, LoanRequestActivity.class);
                intent.putExtra("product_key",product_key);
                intent.putExtra("institution_key",institution_key);
                startActivity(intent);
            }
        });

        btnUploadFiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] mimeTypes =
                        {"image/*",
                                "application/pdf"};

                Intent intent = new Intent();
                intent.addCategory(Intent.CATEGORY_OPENABLE);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    intent.setType(mimeTypes.length == 1 ? mimeTypes[0] : "*/*");
                    if (mimeTypes.length > 0) {
                        intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
                    }
                } else {
                    String mimeTypesStr = "";
                    for (String mimeType : mimeTypes) {
                        mimeTypesStr += mimeType + "|";
                    }
                    intent.setType(mimeTypesStr.substring(0,mimeTypesStr.length() - 1));
                }
                //intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Selecciona los archivos"),RESULT_LOAD_IMAGE);
            }
        });
    }

    private void showFiles() {
        Query query = expressLoanRef.orderByChild("file_name");
        FirebaseRecyclerAdapter<FileUploadedModel, myPostViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<FileUploadedModel, myPostViewHolder>
                (FileUploadedModel.class,R.layout.upload_file_item, myPostViewHolder.class,query) {
            @Override
            protected void populateViewHolder(myPostViewHolder viewHolder, FileUploadedModel model, int position) {
                final String postKey = getRef(position).getKey();
                viewHolder.setFile_name(model.getFile_name());
                viewHolder.setUrl(model.getUrl());

                viewHolder.txtFileName.setText(viewHolder.my_file_name);

                viewHolder.btnDeleteFile.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        expressLoanRef.child(postKey).removeValue();
                    }
                });
            }
        };
        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    public String getFileName(Uri uri) {
        String result = null;
        if (uri.getScheme().equals("content")) {
            Cursor cursor = getContentResolver().query(uri, null,null,null,null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            } finally {
                cursor.close();
            }
        }
        if (result == null) {
            result = uri.getPath();
            int cut = result.lastIndexOf('/');
            if (cut != -1) {
                result = result.substring(cut + 1);
            }
        }
        return result;

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK) {
            if (data.getClipData() != null) {
                int totalItemSelected = data.getClipData().getItemCount();

                for (int i = 0; i < totalItemSelected; i++) {
                    loadingBar.setTitle("Subiendo archivos...");
                    loadingBar.setMessage("Cargando...");
                    loadingBar.show();
                    loadingBar.setCanceledOnTouchOutside(false);
                    loadingBar.setCancelable(false);

                    Uri fileUri = data.getClipData().getItemAt(i).getUri();

                    final String filename = getFileName(fileUri);
                    final String refName = String.valueOf(i);

                    Random rand = new Random();
                    final int value = rand.nextInt(999999999);

                    final int finalI = i;
                    final int countSelection = totalItemSelected;
                    StorageReference fileToUpload = mStorage.child("Document Required").child(filename);
                    fileToUpload.putFile(fileUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                            if (task.isSuccessful()) {
                                downloadUrl = task.getResult().getDownloadUrl().toString();

                                expressLoanRef.child("file"+value).child("url").setValue(downloadUrl);
                                expressLoanRef.child("file"+value).child("file_name").setValue(filename);
                                expressLoanRef.child("file"+value).child("doc_tittle").setValue(doc_tittle);
                                Toasty.success(UploadDocumentationActivity.this, "Archivo "+filename+" cargado con éxito", Toast.LENGTH_LONG).show();

                                if (finalI == (countSelection-1)) {
                                    loadingBar.dismiss();
                                }

                            }
                        }
                    });

                }
            } else if (data.getData() != null) {
                int totalItemSelected = 1;
                for (int i = 0; i < totalItemSelected; i++) {
                    loadingBar.setTitle("Subiendo archivo...");
                    loadingBar.setMessage("Cargando...");
                    loadingBar.show();
                    loadingBar.setCanceledOnTouchOutside(false);
                    loadingBar.setCancelable(false);
                    Uri fileUri = data.getData();

                    final String filename = getFileName(fileUri);
                    final String refName = String.valueOf(i);

                    Random rand = new Random();
                    final int value = rand.nextInt(999999999);

                    StorageReference fileToUpload = mStorage.child("Document Required").child(filename);
                    fileToUpload.putFile(fileUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                            if (task.isSuccessful()) {
                                downloadUrl = task.getResult().getDownloadUrl().toString();

                                expressLoanRef.child("file"+value).child("url").setValue(downloadUrl);
                                expressLoanRef.child("file"+value).child("file_name").setValue(filename);
                                expressLoanRef.child("file"+value).child("doc_tittle").setValue(doc_tittle);
                                Toasty.success(UploadDocumentationActivity.this, "Archivo "+filename+" cargado con éxito", Toast.LENGTH_LONG).show();
                                loadingBar.dismiss();
                            }
                        }
                    });

                }

            }
        }
    }

    public static class myPostViewHolder extends RecyclerView.ViewHolder {
        View mView;
        String my_url, my_file_name;
        TextView txtFileName;
        ImageView btnDeleteFile;

        public myPostViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            txtFileName = mView.findViewById(R.id.txtFileName);
            btnDeleteFile = mView.findViewById(R.id.btnDeleteFile);

        }
        public void setUrl(String url) {
            my_url = url;
        }

        public void setFile_name(String file_name) {
            my_file_name = file_name;
        }
    }
}
