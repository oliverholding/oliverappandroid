package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.CareLearning.VideoResourses;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;

import es.dmoral.toasty.Toasty;

import static android.os.Environment.DIRECTORY_DOWNLOADS;


public class VideoResourcesFragment extends Fragment {

    String video_id,subject_id;
    DatabaseReference careRef,userRef;
    RecyclerView recyclerView;
    FirebaseAuth mAuth;
    String currentUid;
    StorageReference mStorage;
    StorageReference ref;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_video_resources, container, false);

        video_id = getActivity().getIntent().getExtras().getString("video_id");
        subject_id =  getActivity().getIntent().getExtras().getString("subject_id");

        careRef = FirebaseDatabase.getInstance().getReference().child("Care Learning").child("Subjects");
        userRef = FirebaseDatabase.getInstance().getReference().child("Users");
        mStorage = FirebaseStorage.getInstance().getReference();

        mAuth = FirebaseAuth.getInstance();
        currentUid = mAuth.getCurrentUser().getUid();

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setReverseLayout(false);
        linearLayoutManager.setStackFromEnd(false);
        recyclerView.setLayoutManager(linearLayoutManager);

        showResources();

        return view;
    }

    private void showResources() {
        Query query = careRef.child(subject_id).child("Videos").child(video_id).child("Resources");
        FirebaseRecyclerAdapter<ResourceModel,ResourcesViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<ResourceModel, ResourcesViewHolder>
                (ResourceModel.class,R.layout.video_resource_item,ResourcesViewHolder.class,query) {
            @Override
            protected void populateViewHolder(final ResourcesViewHolder viewHolder, ResourceModel model, int position) {
                final String postKey = getRef(position).getKey();
                viewHolder.setResource_doc_name(model.getResource_doc_name());
                viewHolder.setResource_name(model.getResource_name());
                viewHolder.setResource_url(model.getResource_url());
                viewHolder.setResource_extension(model.getResource_extension());

                viewHolder.txtResourceName.setText(viewHolder.my_resource_name);

                viewHolder.btnDownload.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ref = mStorage.child("Video Resources").child(viewHolder.my_resource_doc_name);
                        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                String url = uri.toString();
                                downloadFile(getActivity(),viewHolder.my_resource_url,viewHolder.my_resource_extension,DIRECTORY_DOWNLOADS,url);
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

    public static class ResourcesViewHolder extends RecyclerView.ViewHolder {

        View mView;
        TextView txtResourceName;
        ImageView btnDownload;
        String my_resource_doc_name,my_resource_name,my_resource_url,my_resource_extension;

        public ResourcesViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;

            txtResourceName = mView.findViewById(R.id.txtResourceName);
            btnDownload = mView.findViewById(R.id.btnDownload);
        }
        public void setResource_doc_name(String resource_doc_name) {
            my_resource_doc_name = resource_doc_name;
        }

        public void setResource_name(String resource_name) {
            my_resource_name = resource_name;
        }


        public void setResource_url(String resource_url) {
            my_resource_url = resource_url;
        }

        public void setResource_extension(String resource_extension) {
            my_resource_extension = resource_extension;
        }
    }
}
