package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.ValueProposalModule;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RelativeLayout;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;


public class NoDataTestFragment extends Fragment {

    RadioButton rd001A,rd001B,rd002A,rd002B,rd003A,rd003B,rd004A,rd004B,rd005A,rd005B,rd006A,rd006B,rd007A,rd007B,rd008A,rd008B,rd009A,rd009B,rd010A,rd010B,rd011A,rd011B;
    Button btnSendTest;
    RelativeLayout rootLayout;
    DatabaseReference myCompanyRef;
    String post_key;
    ProgressDialog loadingBar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_no_data_test, container, false);

        rd001A = view.findViewById(R.id.rd001A);
        rd001B = view.findViewById(R.id.rd001B);
        rd002A = view.findViewById(R.id.rd002A);
        rd002B = view.findViewById(R.id.rd002B);
        rd003A = view.findViewById(R.id.rd003A);
        rd003B = view.findViewById(R.id.rd003B);
        rd004A = view.findViewById(R.id.rd004A);
        rd004B = view.findViewById(R.id.rd004B);
        rd005A = view.findViewById(R.id.rd005A);
        rd005B = view.findViewById(R.id.rd005B);
        rd006A = view.findViewById(R.id.rd006A);
        rd006B = view.findViewById(R.id.rd006B);
        rd007A = view.findViewById(R.id.rd007A);
        rd007B = view.findViewById(R.id.rd007B);
        rd008A = view.findViewById(R.id.rd008A);
        rd008B = view.findViewById(R.id.rd008B);
        rd009A = view.findViewById(R.id.rd009A);
        rd009B = view.findViewById(R.id.rd009B);
        rd010A = view.findViewById(R.id.rd010A);
        rd010B = view.findViewById(R.id.rd010B);
        rd011A = view.findViewById(R.id.rd011A);
        rd011B = view.findViewById(R.id.rd011B);
        btnSendTest = view.findViewById(R.id.btnSendTest);
        rootLayout = view.findViewById(R.id.rootLayout);
        loadingBar = new ProgressDialog(getActivity());

        post_key = getActivity().getIntent().getExtras().getString("post_key");

        myCompanyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");

        btnSendTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!rd001A.isChecked() && !rd001B.isChecked()) {
                    Snackbar.make(rootLayout, "Debes completar la pregunta 1", Snackbar.LENGTH_LONG).show();
                    return;
                }
                else  if (!rd002A.isChecked() && !rd002B.isChecked()) {
                    Snackbar.make(rootLayout, "Debes completar la pregunta 2", Snackbar.LENGTH_LONG).show();
                    return;
                }
                else  if (!rd003A.isChecked() && !rd003B.isChecked()) {
                    Snackbar.make(rootLayout, "Debes completar la pregunta 3", Snackbar.LENGTH_LONG).show();
                    return;
                }
                else  if (!rd004A.isChecked() && !rd004B.isChecked()) {
                    Snackbar.make(rootLayout, "Debes completar la pregunta 4", Snackbar.LENGTH_LONG).show();
                    return;
                }
                else  if (!rd005A.isChecked() && !rd005B.isChecked()) {
                    Snackbar.make(rootLayout, "Debes completar la pregunta 5", Snackbar.LENGTH_LONG).show();
                    return;
                }
                else  if (!rd006A.isChecked() && !rd006B.isChecked()) {
                    Snackbar.make(rootLayout, "Debes completar la pregunta 6", Snackbar.LENGTH_LONG).show();
                    return;
                }
                else  if (!rd007A.isChecked() && !rd007B.isChecked()) {
                    Snackbar.make(rootLayout, "Debes completar la pregunta 7", Snackbar.LENGTH_LONG).show();
                    return;
                }
                else  if (!rd008A.isChecked() && !rd008B.isChecked()) {
                    Snackbar.make(rootLayout, "Debes completar la pregunta 8", Snackbar.LENGTH_LONG).show();
                    return;
                }
                else  if (!rd009A.isChecked() && !rd009B.isChecked()) {
                    Snackbar.make(rootLayout, "Debes completar la pregunta 9", Snackbar.LENGTH_LONG).show();
                    return;
                }
                else  if (!rd010A.isChecked() && !rd010B.isChecked()) {
                    Snackbar.make(rootLayout, "Debes completar la pregunta 10", Snackbar.LENGTH_LONG).show();
                    return;
                } else if (!rd011A.isChecked() && !rd011B.isChecked()) {
                    Snackbar.make(rootLayout, "Debes completar la pregunta 11", Snackbar.LENGTH_LONG).show();
                    return;
                } else {
                    registerInfoOnDatabase();
                }
            }
        });



        return view;
    }

    private void registerInfoOnDatabase() {
        loadingBar.setTitle("Generando el Test...");
        loadingBar.setMessage("Cargando...");
        loadingBar.show();
        loadingBar.setCanceledOnTouchOutside(false);
        loadingBar.setCancelable(false);

        if (rd001A.isChecked()) {
            myCompanyRef.child(post_key).child("Module 3").child("Company Test").child("001").child("A").setValue(1);
            myCompanyRef.child(post_key).child("Module 3").child("Company Test").child("001").child("B").setValue(0);
        }
        if (rd001B.isChecked()) {
            myCompanyRef.child(post_key).child("Module 3").child("Company Test").child("001").child("A").setValue(0);
            myCompanyRef.child(post_key).child("Module 3").child("Company Test").child("001").child("B").setValue(1);
        }

        if (rd002A.isChecked()) {
            myCompanyRef.child(post_key).child("Module 3").child("Company Test").child("002").child("A").setValue(1);
            myCompanyRef.child(post_key).child("Module 3").child("Company Test").child("002").child("B").setValue(0);
        }
        if (rd002B.isChecked()) {
            myCompanyRef.child(post_key).child("Module 3").child("Company Test").child("002").child("A").setValue(0);
            myCompanyRef.child(post_key).child("Module 3").child("Company Test").child("002").child("B").setValue(1);
        }

        if (rd003A.isChecked()) {
            myCompanyRef.child(post_key).child("Module 3").child("Company Test").child("003").child("A").setValue(1);
            myCompanyRef.child(post_key).child("Module 3").child("Company Test").child("003").child("B").setValue(0);
        }
        if (rd003B.isChecked()) {
            myCompanyRef.child(post_key).child("Module 3").child("Company Test").child("003").child("A").setValue(0);
            myCompanyRef.child(post_key).child("Module 3").child("Company Test").child("003").child("B").setValue(1);
        }

        if (rd004A.isChecked()) {
            myCompanyRef.child(post_key).child("Module 3").child("Company Test").child("004").child("A").setValue(1);
            myCompanyRef.child(post_key).child("Module 3").child("Company Test").child("004").child("B").setValue(0);
        }
        if (rd004B.isChecked()) {
            myCompanyRef.child(post_key).child("Module 3").child("Company Test").child("004").child("A").setValue(0);
            myCompanyRef.child(post_key).child("Module 3").child("Company Test").child("004").child("B").setValue(1);
        }

        if (rd005A.isChecked()) {
            myCompanyRef.child(post_key).child("Module 3").child("Company Test").child("005").child("A").setValue(1);
            myCompanyRef.child(post_key).child("Module 3").child("Company Test").child("005").child("B").setValue(0);
        }
        if (rd005B.isChecked()) {
            myCompanyRef.child(post_key).child("Module 3").child("Company Test").child("005").child("A").setValue(0);
            myCompanyRef.child(post_key).child("Module 3").child("Company Test").child("005").child("B").setValue(1);
        }

        if (rd006A.isChecked()) {
            myCompanyRef.child(post_key).child("Module 3").child("Company Test").child("006").child("A").setValue(1);
            myCompanyRef.child(post_key).child("Module 3").child("Company Test").child("006").child("B").setValue(0);
        }
        if (rd006B.isChecked()) {
            myCompanyRef.child(post_key).child("Module 3").child("Company Test").child("006").child("A").setValue(0);
            myCompanyRef.child(post_key).child("Module 3").child("Company Test").child("006").child("B").setValue(1);
        }

        if (rd007A.isChecked()) {
            myCompanyRef.child(post_key).child("Module 3").child("Company Test").child("007").child("A").setValue(1);
            myCompanyRef.child(post_key).child("Module 3").child("Company Test").child("007").child("B").setValue(0);
        }
        if (rd007B.isChecked()) {
            myCompanyRef.child(post_key).child("Module 3").child("Company Test").child("007").child("A").setValue(0);
            myCompanyRef.child(post_key).child("Module 3").child("Company Test").child("007").child("B").setValue(1);
        }

        if (rd008A.isChecked()) {
            myCompanyRef.child(post_key).child("Module 3").child("Company Test").child("008").child("A").setValue(1);
            myCompanyRef.child(post_key).child("Module 3").child("Company Test").child("008").child("B").setValue(0);
        }
        if (rd008B.isChecked()) {
            myCompanyRef.child(post_key).child("Module 3").child("Company Test").child("008").child("A").setValue(0);
            myCompanyRef.child(post_key).child("Module 3").child("Company Test").child("008").child("B").setValue(1);
        }

        if (rd009A.isChecked()) {
            myCompanyRef.child(post_key).child("Module 3").child("Company Test").child("009").child("A").setValue(1);
            myCompanyRef.child(post_key).child("Module 3").child("Company Test").child("009").child("B").setValue(0);
        }
        if (rd009B.isChecked()) {
            myCompanyRef.child(post_key).child("Module 3").child("Company Test").child("009").child("A").setValue(0);
            myCompanyRef.child(post_key).child("Module 3").child("Company Test").child("009").child("B").setValue(1);
        }

        if (rd010A.isChecked()) {
            myCompanyRef.child(post_key).child("Module 3").child("Company Test").child("010").child("A").setValue(1);
            myCompanyRef.child(post_key).child("Module 3").child("Company Test").child("010").child("B").setValue(0);
        }
        if (rd010B.isChecked()) {
            myCompanyRef.child(post_key).child("Module 3").child("Company Test").child("010").child("A").setValue(0);
            myCompanyRef.child(post_key).child("Module 3").child("Company Test").child("010").child("B").setValue(1);
        }

        if (rd011A.isChecked()) {
            myCompanyRef.child(post_key).child("Module 3").child("Company Test").child("011").child("A").setValue(1);
            myCompanyRef.child(post_key).child("Module 3").child("Company Test").child("011").child("B").setValue(0);
        }
        if (rd011B.isChecked()) {
            myCompanyRef.child(post_key).child("Module 3").child("Company Test").child("011").child("A").setValue(0);
            myCompanyRef.child(post_key).child("Module 3").child("Company Test").child("011").child("B").setValue(1);
        }

        Intent intent = new Intent(getActivity(), ValueProposalActivity.class);
        intent.putExtra("post_key",post_key);
        startActivity(intent);
        loadingBar.dismiss();
        getActivity().finish();


    }
}
