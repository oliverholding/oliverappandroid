package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Logistic.Storage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ServerValue;
import com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Commercialization.BillsIssuing.CreateBillActivity;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class StorageActivity extends AppCompatActivity {
    
    Button btnRegisterWarehouse;
    String post_key;
    DatabaseReference companyRef;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);

        post_key = getIntent().getExtras().getString("post_key");
        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");

        btnRegisterWarehouse = findViewById(R.id.btnRegisterWarehouse);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        showWareHouses();

        btnRegisterWarehouse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRegisterWarehouseDialog();
            }
        });


    }

    private void showWareHouses() {
        Query query = companyRef.child(post_key).child("Warehouses").orderByChild("timestamp");
        FirebaseRecyclerAdapter<WarehousesModel, WarehousesViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<WarehousesModel, WarehousesViewHolder>
                (WarehousesModel.class,R.layout.warehouse_item,WarehousesViewHolder.class,query) {
            @Override
            protected void populateViewHolder(WarehousesViewHolder viewHolder, WarehousesModel model, int position) {
                final String postKey = getRef(position).getKey();
                viewHolder.setWarehouse_destination(model.getWarehouse_destination());
                viewHolder.setWarehouse_name(model.getWarehouse_name());

                viewHolder.txtWarehouseName.setText(viewHolder.ware_name);

                if (viewHolder.ware_destiny.equals("products")) {
                    viewHolder.txtWarehouseDestination.setText("Destino: Productos Terminados");
                }
                if (viewHolder.ware_destiny.equals("materials")) {
                    viewHolder.txtWarehouseDestination.setText("Destino: Materiales o Insumos");
                }


                viewHolder.btnManageWarehouse.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(StorageActivity.this, WarehouseActivity.class);
                        intent.putExtra("warehouse_id",postKey);
                        intent.putExtra("post_key",post_key);
                        startActivity(intent);
                    }
                });

            }
        };
        recyclerView.setAdapter(firebaseRecyclerAdapter);

    }

    private void showRegisterWarehouseDialog() {
        final AlertDialog dialog = new AlertDialog.Builder(StorageActivity.this).create();

        LayoutInflater inflater = LayoutInflater.from(StorageActivity.this);
        View finance_method = inflater.inflate(R.layout.register_warehouse_dialog,null);

        final EditText edtWareHouseName;
        final RadioButton rdProducts,rdMaterials;
        Button btnRegister;
        final RelativeLayout rootLayout;

        edtWareHouseName = finance_method.findViewById(R.id.edtWareHouseName);
        rdProducts = finance_method.findViewById(R.id.rdProducts);
        rdMaterials = finance_method.findViewById(R.id.rdMaterials);
        btnRegister = finance_method.findViewById(R.id.btnRegister);
        rootLayout = finance_method.findViewById(R.id.rootLayout);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(edtWareHouseName.getText().toString())) {
                    Snackbar.make(rootLayout,"Debes ingresar el nombre del almacén",Snackbar.LENGTH_LONG);

                } else if (!rdMaterials.isChecked() && !rdProducts.isChecked()) {
                    Snackbar.make(rootLayout,"Debes seleccionar el destino del almacén",Snackbar.LENGTH_LONG);
                } else {
                    registerWareHouse();
                }
            }

            private void registerWareHouse() {
                Calendar calForDate = Calendar.getInstance();
                SimpleDateFormat currentDate = new SimpleDateFormat("dd-MM-yyyy");
                String saveCurrentDate = currentDate.format(calForDate.getTime());

                Calendar calForTime = Calendar.getInstance();
                SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss");
                String saveCurrentTime = currentTime.format(calForTime.getTime());
                Long tsLong = System.currentTimeMillis()/1000;
                String timestamp = tsLong.toString();

                if (rdProducts.isChecked()) {
                    companyRef.child(post_key).child("Warehouses").child(timestamp).child("warehouse_destination").setValue("products");
                }
                if (rdMaterials.isChecked()) {
                    companyRef.child(post_key).child("Warehouses").child(timestamp).child("warehouse_destination").setValue("materials");
                }
                companyRef.child(post_key).child("Warehouses").child(timestamp).child("warehouse_name").setValue(edtWareHouseName.getText().toString());
                companyRef.child(post_key).child("Warehouses").child(timestamp).child("warehouse_register_date").setValue(saveCurrentDate);
                companyRef.child(post_key).child("Warehouses").child(timestamp).child("warehouse_register_time").setValue(saveCurrentTime);
                companyRef.child(post_key).child("Warehouses").child(timestamp).child("timestamp").setValue(ServerValue.TIMESTAMP);
                dialog.dismiss();

            }
        });
        
        dialog.setView(finance_method);
        dialog.show();
    }

    public static class WarehousesViewHolder extends RecyclerView.ViewHolder {

        View mView;
        String ware_name,ware_destiny;
        TextView txtWarehouseName,txtWarehouseDestination,btnManageWarehouse;

        public WarehousesViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;

            txtWarehouseName = mView.findViewById(R.id.txtWarehouseName);
            txtWarehouseDestination = mView.findViewById(R.id.txtWarehouseDestination);
            btnManageWarehouse = mView.findViewById(R.id.btnManageWarehouse);
        }

        public void setWarehouse_name(String warehouse_name) {
            ware_name = warehouse_name;
        }

        public void setWarehouse_destination(String warehouse_destination) {
            ware_destiny = warehouse_destination;
        }
    }
}
