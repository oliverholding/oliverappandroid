package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.ManagementControlAndReports.ContractManagement;

import android.app.AlertDialog;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.OpenableColumns;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.ManagementControlAndReports.InternalControl.InternalControlActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.FinancialManagement.LendingProduct.FileUploadedModel;
import com.oalonedeveloper.oliver.oliverappandroidapp.FinancialManagement.LendingProduct.UploadDocumentationActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;

import java.util.Random;

import es.dmoral.toasty.Toasty;

import static android.app.Activity.RESULT_OK;
import static android.os.Environment.DIRECTORY_DOWNLOADS;


public class MyContractsFragment extends Fragment {

    DatabaseReference companyRef;
    String post_key,downloadUrl;
    Button btnAddContract;
    static final int RESULT_LOAD_IMAGE = 1;
    ProgressDialog loadingBar;
    StorageReference mStorage;
    EditText edtName;
    AlertDialog dialog;
    RecyclerView recyclerView;
    StorageReference ref;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_contracts, container, false);

        post_key = getActivity().getIntent().getExtras().getString("post_key");
        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");

        loadingBar = new ProgressDialog(getActivity());

        mStorage = FirebaseStorage.getInstance().getReference();

        btnAddContract = view.findViewById(R.id.btnAddContract);

        recyclerView = view.findViewById(R.id.recyclerView);


        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setReverseLayout(false);
        linearLayoutManager.setStackFromEnd(false);
        recyclerView.setLayoutManager(linearLayoutManager);
        showFiles();

        btnAddContract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddContractDialog();
            }
        });

        return view;
    }

    private void showFiles() {
        Query query =  companyRef.child(post_key).child("Contracts").orderByChild("file_name");
        FirebaseRecyclerAdapter<FileUploadedModel, myPostViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<FileUploadedModel, myPostViewHolder>
                (FileUploadedModel.class,R.layout.contract_item, myPostViewHolder.class,query) {
            @Override
            protected void populateViewHolder(final myPostViewHolder viewHolder, FileUploadedModel model, int position) {
                final String postKey = getRef(position).getKey();
                viewHolder.setFile_name(model.getFile_name());
                viewHolder.setUrl(model.getUrl());
                viewHolder.setDoc_tittle(model.getDoc_tittle());

                viewHolder.txtFileName.setText(viewHolder.my_doc_tittle);

                viewHolder.btnDownload.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ref = mStorage.child("Companies Contracts").child(viewHolder.my_file_name);
                        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                String url = uri.toString();
                                downloadFile(getActivity(),viewHolder.my_file_name,".pdf",DIRECTORY_DOWNLOADS,url);
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getActivity(), ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
            }
        };
        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    private void downloadFile(FragmentActivity activity, String my_url, String s, String directoryDownloads, String url) {
        DownloadManager downloadManager =  (DownloadManager) activity.getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse(url);
        DownloadManager.Request request = new DownloadManager.Request(uri);

        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalFilesDir(activity,directoryDownloads,my_url+s);
        downloadManager.enqueue(request);

        Toasty.success(getActivity(), "Descargando... ", Toast.LENGTH_LONG).show();
       
    }

    private void showAddContractDialog() {
        dialog = new AlertDialog.Builder(getActivity()).create();

        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View finance_method = inflater.inflate(R.layout.add_contract_dialog,null);

        Button btnAddContract;
        final RelativeLayout rootLayout;

        edtName = finance_method.findViewById(R.id.edtName);
        btnAddContract = finance_method.findViewById(R.id.btnAddContract);
        rootLayout = finance_method.findViewById(R.id.rootLayout);

        btnAddContract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(edtName.getText().toString())) {
                    Snackbar.make(rootLayout, "Debes ingresar el nombre del contrato", Snackbar.LENGTH_SHORT).show();

                } else {
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

            }
        });

        dialog.setView(finance_method);
        dialog.show();
    }

    public String getFileName(Uri uri) {
        String result = null;
        if (uri.getScheme().equals("content")) {
            Cursor cursor = getActivity().getContentResolver().query(uri, null,null,null,null);
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
             if (data.getData() != null) {
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

                    StorageReference fileToUpload = mStorage.child("Companies Contracts").child(filename);
                    fileToUpload.putFile(fileUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                            if (task.isSuccessful()) {
                                downloadUrl = task.getResult().getDownloadUrl().toString();

                                companyRef.child(post_key).child("Contracts").child("file"+value).child("url").setValue(downloadUrl);
                                companyRef.child(post_key).child("Contracts").child("file"+value).child("file_name").setValue(filename);
                                companyRef.child(post_key).child("Contracts").child("file"+value).child("doc_tittle").setValue(edtName.getText().toString());
                                Toasty.success(getActivity(), "Archivo "+filename+" cargado con éxito", Toast.LENGTH_LONG).show();
                                dialog.dismiss();
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
        String my_url, my_file_name, my_doc_tittle;
        TextView txtFileName;
        ImageView btnDownload;

        public myPostViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            txtFileName = mView.findViewById(R.id.txtFileName);
            btnDownload = mView.findViewById(R.id.btnDownload);

        }
        public void setUrl(String url) {
            my_url = url;
        }

        public void setFile_name(String file_name) {
            my_file_name = file_name;
        }
        public void setDoc_tittle(String doc_tittle) {
            my_doc_tittle = doc_tittle;
        }
    }
}
