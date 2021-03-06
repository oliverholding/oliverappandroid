package com.oalonedeveloper.oliver.oliverappandroidapp.CareManager.Logistic.Storage;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.oalonedeveloper.oliver.oliverappandroidapp.R;


public class ProductKardexSumaryFragment extends Fragment {

    RecyclerView recyclerView;
    DatabaseReference companyRef;
    String company_id,product_id,warehouse_id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_product_kardex_sumary, container, false);

        company_id = getActivity().getIntent().getExtras().getString("company_id");
        product_id = getActivity().getIntent().getExtras().getString("product_id");
        warehouse_id = getActivity().getIntent().getExtras().getString("warehouse_id");

        companyRef = FirebaseDatabase.getInstance().getReference().child("My Companies");

        recyclerView = view.findViewById(R.id.recyclerView);

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        showKardex();

        return view;
    }

    private void showKardex() {
        Query query = companyRef.child(company_id).child("Kardex").orderByChild("timestamp");
        FirebaseRecyclerAdapter<KardexModel, KardexItemsViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<KardexModel, KardexItemsViewHolder>
                (KardexModel.class,R.layout.kardex_date_item,KardexItemsViewHolder.class,query) {
            @Override
            protected void populateViewHolder(KardexItemsViewHolder viewHolder, KardexModel model, int position) {
                final String postKey = getRef(position).getKey();
                viewHolder.setOperation_date(model.getOperation_date());
                viewHolder.setOperation_day(model.getOperation_day());
                viewHolder.setOperation_month(model.getOperation_month());
                viewHolder.setOperation_time(model.getOperation_time());
                viewHolder.setOperation_type(model.getOperation_type());
                viewHolder.setOperation_year(model.getOperation_year());
                viewHolder.setProduct_id(model.getProduct_id());
                viewHolder.setProduct_price(model.getProduct_price());
                viewHolder.setStock(model.getStock());
                viewHolder.setWarehouse_destination_id(model.getWarehouse_destination_id());
                viewHolder.setWarehouse_origin_id(model.getWarehouse_origin_id());

                viewHolder.txtKardexDate.setText(viewHolder.my_operation_date);
                if (viewHolder.my_operation_type.equals("purchase")) {
                    viewHolder.txtKardexConcept.setText("Compra o Fabricación");
                }
                if (viewHolder.my_operation_type.equals("from_store_transfer")) {
                    viewHolder.txtKardexConcept.setText("Transferencia desde Tienda");
                }
                if (viewHolder.my_operation_type.equals("warehouse_transfer")) {
                    viewHolder.txtKardexConcept.setText("Transferencia entre almacenes");
                }
                if (viewHolder.my_operation_type.equals("to_store_transfer")) {
                    viewHolder.txtKardexConcept.setText("Transferencia a Tienda");
                }
                if (viewHolder.my_operation_type.equals("inventory_control")) {
                    viewHolder.txtKardexConcept.setText("Ajuste de Inventario");
                }
                if (viewHolder.my_operation_type.equals("production")) {
                    viewHolder.txtKardexConcept.setText("Para Producción");
                }

                if (!viewHolder.my_product_id.equals(product_id)) {
                    viewHolder.mView.setVisibility(View.GONE);
                    viewHolder.mView.getLayoutParams().height = 0;
                }
                if (!viewHolder.my_warehouse_destination_id.equals(warehouse_id) && !viewHolder.my_warehouse_origin_id.equals(warehouse_id)) {
                    viewHolder.mView.setVisibility(View.GONE);
                    viewHolder.mView.getLayoutParams().height = 0;
                }
            }
        };
        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    public static class KardexItemsViewHolder extends RecyclerView.ViewHolder {
        View mView;
        TextView txtKardexDate,txtKardexConcept;
        String my_operation_date,my_operation_day,my_operation_month,my_operation_time,my_operation_type,my_operation_year,my_product_id,my_product_price,my_stock,my_warehouse_destination_id,my_warehouse_origin_id;

        public KardexItemsViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;

            txtKardexDate = mView.findViewById(R.id.txtKardexDate);
            txtKardexConcept = mView.findViewById(R.id.txtKardexConcept);

        }

        public void setOperation_date(String operation_date) {
            my_operation_date = operation_date;
        }

        public void setOperation_day(String operation_day) {
            my_operation_day =operation_day;
        }
        public void setOperation_month(String operation_month) {
            my_operation_month = operation_month;
        }
        public void setOperation_time(String operation_time) {
            my_operation_time = operation_time;
        }
        public void setOperation_type(String operation_type) {
            my_operation_type = operation_type;
        }

        public void setOperation_year(String operation_year) {
            my_operation_year = operation_year;
        }

        public void setProduct_id(String product_id) {
            my_product_id = product_id;
        }

        public void setProduct_price(String product_price) {
            my_product_price = product_price;
        }

        public void setStock(String stock) {
            my_stock = stock;
        }

        public void setWarehouse_destination_id(String warehouse_destination_id) {
            my_warehouse_destination_id = warehouse_destination_id;
        }

        public void setWarehouse_origin_id(String warehouse_origin_id) {
            my_warehouse_origin_id = warehouse_origin_id;
        }
    }
}
