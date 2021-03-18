package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Simulators.GanttChart;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;
import com.xw.repo.BubbleSeekBar;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

import javax.xml.datatype.Duration;

import es.dmoral.toasty.Toasty;


public class WorkChargeActivitiesFragment extends Fragment {

    DatabaseReference companyRef;
    String post_key,product_id;
    Button btnAdd;
    RecyclerView recyclerView;
    DecimalFormat decimalFormat;
    int sum,max;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_work_charge_activities, container, false);

        post_key = getActivity().getIntent().getExtras().getString("post_key");
        product_id = getActivity().getIntent().getExtras().getString("product_id");
        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");

        decimalFormat = new DecimalFormat("0");

        btnAdd = view.findViewById(R.id.btnAdd);
        recyclerView = view.findViewById(R.id.recyclerView);

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setReverseLayout(false);
        linearLayoutManager.setStackFromEnd(false);
        recyclerView.setLayoutManager(linearLayoutManager);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddActivityDialog();
            }
        });

        companyRef.child(post_key).child("My Products").child(product_id).child("Production Activities").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                sum = 0;
                max = 0;
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    if (dataSnapshot.hasChild("activity_total_minutes")) {
                        Map<String, Object> map = (Map<String, Object>) ds.getValue();
                        Object activity_total_minutes = map.get("activity_total_minutes");

                        int total = Integer.parseInt(String.valueOf(activity_total_minutes));

                        sum += total;



                    }

                }

                showActivities();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        return view;
    }

    private void showActivities() {
        Query query = companyRef.child(post_key).child("My Products").child(product_id).child("Production Activities").orderByChild("timestamp");
        FirebaseRecyclerAdapter<ProductionActivitiesModel, ProductionActivitiesViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<ProductionActivitiesModel, ProductionActivitiesViewHolder>
                (ProductionActivitiesModel.class, R.layout.activity_process_item, ProductionActivitiesViewHolder.class,query) {
            @Override
            protected void populateViewHolder(final ProductionActivitiesViewHolder viewHolder, ProductionActivitiesModel model, int position) {
                final String postKey = getRef(position).getKey();
               viewHolder.setActivity_name(model.getActivity_name());
               viewHolder.setActivity_time(model.getActivity_time());

               viewHolder.txtName.setText(model.activity_name);
               viewHolder.txtTime.setText(model.activity_time);

                companyRef.child(post_key).child("My Products").child(product_id).child("Production Activities").child(postKey).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String activity_total_minutes = dataSnapshot.child("activity_total_minutes").getValue().toString();
                         int activity_total_minutes_int = Integer.parseInt(activity_total_minutes);

                        viewHolder.seekBar.setProgress(activity_total_minutes_int);
                        viewHolder.seekBar.setEnabled(false);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


            }
        };
        recyclerView.setAdapter(firebaseRecyclerAdapter);

    }

    private void showAddActivityDialog() {
        final AlertDialog dialog = new AlertDialog.Builder(getActivity()).create();

        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View finance_method = inflater.inflate(R.layout.add_prodution_activity_dialog,null);

        final EditText edtName,edtHour,edtMinutes;
        Button btnAdd;

        edtName = finance_method.findViewById(R.id.edtName);
        edtHour = finance_method.findViewById(R.id.edtHour);
        edtMinutes = finance_method.findViewById(R.id.edtMinutes);
        btnAdd = finance_method.findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Long tsLong = System.currentTimeMillis()/1000;
                String timestamp = tsLong.toString();

                double minutes = Double.parseDouble(edtMinutes.getText().toString());
                double hour_minutes = Double.parseDouble(edtHour.getText().toString())*60;
                double total_minutes = minutes+hour_minutes;
                String total_minutes_st = decimalFormat.format(total_minutes);

                companyRef.child(post_key).child("My Products").child(product_id).child("Production Activities").child(timestamp).child("activity_name").setValue(edtName.getText().toString());
                companyRef.child(post_key).child("My Products").child(product_id).child("Production Activities").child(timestamp).child("activity_total_minutes").setValue(total_minutes_st);
                companyRef.child(post_key).child("My Products").child(product_id).child("Production Activities").child(timestamp).child("activity_hour").setValue(edtHour.getText().toString());
                companyRef.child(post_key).child("My Products").child(product_id).child("Production Activities").child(timestamp).child("activity_minute").setValue(edtMinutes.getText().toString());
                companyRef.child(post_key).child("My Products").child(product_id).child("Production Activities").child(timestamp).child("activity_time").setValue(edtHour.getText().toString()+":"+edtMinutes.getText().toString());
                companyRef.child(post_key).child("My Products").child(product_id).child("Production Activities").child(timestamp).child("timestamp").setValue(ServerValue.TIMESTAMP);
                Toasty.success(getActivity(), "Registrado", Toast.LENGTH_LONG).show();
                dialog.dismiss();
            }
        });



        dialog.setView(finance_method);
        dialog.show();
    }

    public static class ProductionActivitiesViewHolder extends RecyclerView.ViewHolder {

        View mView;
        String activity_name_st,activity_time_st;
        TextView txtName,txtTime;
        BubbleSeekBar seekBar;

        public ProductionActivitiesViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;

            txtName = mView.findViewById(R.id.txtName);
            txtTime = mView.findViewById(R.id.txtTime);
            seekBar = mView.findViewById(R.id.seekBar);

        }
        public void setActivity_name(String activity_name) {
            activity_name_st = activity_name;
        }

        public void setActivity_time(String activity_time) {
            activity_time_st = activity_time;
        }
    }
}
