package com.oalonedeveloper.oliver.oliverappandroidapp.FinancialManagement.LendingProduct;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.oalonedeveloper.oliver.oliverappandroidapp.R;

import java.util.ArrayList;

public class LendingSimulationAdapter  extends RecyclerView.Adapter<LendingSimulationAdapter.ViewHolderItems>{

    ArrayList<LendingSimulationModel> list;

    public LendingSimulationAdapter(ArrayList<LendingSimulationModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolderItems onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.amortization_item,null,false);
        return new ViewHolderItems(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderItems holder, int position) {
        holder.txtQuoteNumber.setText(list.get(position).getQuote_number());
        holder.txtExpirationDate.setText(list.get(position).getExpiration_date());
        holder.txtBalance.setText(list.get(position).getBalance());
        holder.txtAmortization.setText(list.get(position).getAmortization());
        holder.txtInterest.setText(list.get(position).getInterest());
        holder.txtDesgravamen.setText(list.get(position).getDesgravamen_insurance());
        holder.txtFee.setText(list.get(position).getFixed_fee());
        holder.txtQuotePayment.setText(list.get(position).getQuote_payment());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolderItems extends RecyclerView.ViewHolder {

        TextView txtQuoteNumber,txtExpirationDate,txtBalance,txtAmortization,txtInterest,txtDesgravamen,txtFee,txtQuotePayment;

        public ViewHolderItems(@NonNull View itemView) {
            super(itemView);

            txtQuoteNumber = itemView.findViewById(R.id.txtQuoteNumber);
            txtExpirationDate = itemView.findViewById(R.id.txtExpirationDate);
            txtBalance = itemView.findViewById(R.id.txtBalance);
            txtAmortization = itemView.findViewById(R.id.txtAmortization);
            txtInterest = itemView.findViewById(R.id.txtInterest);
            txtDesgravamen = itemView.findViewById(R.id.txtDesgravamen);
            txtFee = itemView.findViewById(R.id.txtFee);
            txtQuotePayment = itemView.findViewById(R.id.txtQuotePayment);
        }
    }
}
