package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.ManagementControlAndReports.ContractManagement;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;

import es.dmoral.toasty.Toasty;

import static android.os.Environment.DIRECTORY_DOWNLOADS;


public class ContractsTemplatesFragment extends Fragment {

    ImageView btnDownload1,btnDownload2,btnDownload3;
    StorageReference mStorage;
    StorageReference ref;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contracts_templates, container, false);

        btnDownload1 = view.findViewById(R.id.btnDownload1);
        btnDownload2 = view.findViewById(R.id.btnDownload2);
        btnDownload3 = view.findViewById(R.id.btnDownload3);
        mStorage = FirebaseStorage.getInstance().getReference();

        btnDownload1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ref = mStorage.child("Contracts Templates").child("contrato_trabajo_indefinido.docx");
                ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        String url = uri.toString();
                        downloadFile(getActivity(),"contrato_trabajo_indefinido",".docx",DIRECTORY_DOWNLOADS,url);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getActivity(), ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        btnDownload2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ref = mStorage.child("Contracts Templates").child("contrato_trabajo_temporal.docx");
                ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        String url = uri.toString();
                        downloadFile(getActivity(),"contrato_trabajo_temporal",".docx",DIRECTORY_DOWNLOADS,url);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getActivity(), ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        btnDownload3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ref = mStorage.child("Contracts Templates").child("contrato_de_compraventa.docx");
                ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        String url = uri.toString();
                        downloadFile(getActivity(),"contrato_de_compraventa",".docx",DIRECTORY_DOWNLOADS,url);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getActivity(), ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


        return view;
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
}
