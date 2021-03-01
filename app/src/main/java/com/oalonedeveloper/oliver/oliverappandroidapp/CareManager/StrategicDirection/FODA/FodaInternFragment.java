package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.StrategicDirection.FODA;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;
import com.xw.repo.BubbleSeekBar;


public class FodaInternFragment extends Fragment {

    ImageView btnAddItem1,btnAddItem2;
    RecyclerView recyclerView1,recyclerView2;
    TextView txtResult;
    DatabaseReference companyRef;
    String post_key;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_foda_intern, container, false);

        post_key = getActivity().getIntent().getExtras().getString("post_key");
        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");

        btnAddItem1 = view.findViewById(R.id.btnAddItem1);
        btnAddItem2 = view.findViewById(R.id.btnAddItem2);
        recyclerView1 = view.findViewById(R.id.recyclerView1);
        recyclerView2 = view.findViewById(R.id.recyclerView2);
        txtResult = view.findViewById(R.id.txtResult);

        btnAddItem1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = "Mis Fortalezas";
                String question_1 = "";
                String question_2 = "";
                String question_3 = "";
                String path = "item_1";

                showAddFodaDataDialog(title,path);
            }
        });

        return view;
    }

    private void showAddFodaDataDialog(String title, String path) {
        final AlertDialog dialog = new AlertDialog.Builder(getActivity()).create();

        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View finance_method = inflater.inflate(R.layout.foda_add_data_dialog,null);

        TextView txtQuestion1,txtQuestion2,txtQuestion3;
        EditText edtInput;
        BubbleSeekBar seekBar;
        Button btnSelection,btnFinish;

        dialog.setView(finance_method);
        dialog.show();
    }
}
