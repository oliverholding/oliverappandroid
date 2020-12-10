package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.IntructionModule;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;

import static android.os.Environment.DIRECTORY_DOWNLOADS;


public class Files1Fragment extends Fragment {

    LinearLayout btnDownloadFile1;
    StorageReference storageReference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_files1, container, false);

        btnDownloadFile1 = view.findViewById(R.id.btnDownloadFile1);

        storageReference = FirebaseStorage.getInstance().getReference().child("Documents").child("2da Convocatoria_TDRs Aplicación Móvil para Gestión Empresarial V.04.pdf");

        btnDownloadFile1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        String url = uri.toString();
                        downloadFiles(getActivity(),"TDR Prueba",".pdf", DIRECTORY_DOWNLOADS,url);
                        Toast.makeText(getActivity(), "DESCARGANDO...", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });

            }
        });

        return view;
    }

    public void downloadFiles(Context context, String filename, String fileExtension, String destinationDirectory, String url) {
        DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse(url);
        DownloadManager.Request request = new DownloadManager.Request(uri);

        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalFilesDir(context, destinationDirectory, filename+fileExtension);
        downloadManager.enqueue(request);
    }
}
