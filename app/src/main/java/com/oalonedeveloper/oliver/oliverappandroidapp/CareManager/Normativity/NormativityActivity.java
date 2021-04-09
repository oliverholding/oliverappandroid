package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Normativity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.FinancialManagement.AccountsNoPaid.AccountsNoPaidMonthlyActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.FinancialManagement.AccountsToPaid.AccountsToPaidMonthlyActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.FinancialManagement.Budget.BudgetActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.FinancialManagement.CashAndBanks.CashAndBanksMonthlyActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.FinancialManagement.CashFlow.CashFlowActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.FinancialManagement.FinanacialManagementActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;

import es.dmoral.toasty.Toasty;

import static android.os.Environment.DIRECTORY_DOWNLOADS;

public class NormativityActivity extends AppCompatActivity {

    CardView btnTool0,btnTool1,btnTool2,btnTool3,btnTool4,btnTool5,btnTool6,btnTool00,btnTool7;
    String post_key;
    StorageReference mStorage;
    StorageReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normativity);

        post_key = getIntent().getExtras().getString("post_key");

        btnTool0 = findViewById(R.id.btnTool0);
        btnTool1 = findViewById(R.id.btnTool1);
        btnTool2 = findViewById(R.id.btnTool2);
        btnTool3 = findViewById(R.id.btnTool3);
        btnTool4 = findViewById(R.id.btnTool4);
        btnTool5 = findViewById(R.id.btnTool5);
        btnTool6= findViewById(R.id.btnTool6);
        btnTool00 = findViewById(R.id.btnTool00);
        btnTool7 = findViewById(R.id.btnTool7);
        mStorage = FirebaseStorage.getInstance().getReference();

        btnTool00.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NormativityActivity.this, NormativityTutorialActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });
        btnTool0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NormativityActivity.this, NormativityIntroductionActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });
        btnTool1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NormativityActivity.this, TaxesNormativityActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });
        btnTool2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NormativityActivity.this, LabourNormativityActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });
        btnTool3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NormativityActivity.this, SanitaryNormativityActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });
        btnTool4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NormativityActivity.this, EnviromentalNormativityActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });
        btnTool5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NormativityActivity.this, CivilResponsabilityActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });
        btnTool6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NormativityActivity.this, NormativitySumaryActivity.class);
                intent.putExtra("post_key", post_key);
                startActivity(intent);
            }
        });

        btnTool7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ref = mStorage.child("Contracts Templates").child("Ficha Sanitaria - Normatividad.xlsx");
                ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        String url = uri.toString();
                        downloadFile(NormativityActivity.this,"Ficha Sanitaria - Normatividad",".xlsx",DIRECTORY_DOWNLOADS,url);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(NormativityActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }

    private void downloadFile(FragmentActivity activity, String my_url, String s, String directoryDownloads, String url) {
        DownloadManager downloadManager =  (DownloadManager) activity.getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse(url);
        DownloadManager.Request request = new DownloadManager.Request(uri);

        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalFilesDir(activity,directoryDownloads,my_url+s);
        downloadManager.enqueue(request);

        Toasty.success(NormativityActivity.this, "Descargando... ", Toast.LENGTH_LONG).show();

    }

}
