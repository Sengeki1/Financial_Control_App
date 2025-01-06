package com.example.myapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterTransaction extends RecyclerView.Adapter<AdapterTransaction.TransactionViewHolder> {
    private LayoutInflater mInflater;
    private Context context;
    private final List<Transaction> transactionList;
    private EditTransaction editTransaction;

    public AdapterTransaction(Context context, List<Transaction> transactionList, EditTransaction editTransaction) {
        this.mInflater = LayoutInflater.from(context);
        this.transactionList = transactionList;
        this.editTransaction = editTransaction;
        this.context = context;
    }
    @NonNull
    @Override
    public AdapterTransaction.TransactionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.item_recycler, parent, false);
        return new TransactionViewHolder(itemView, editTransaction, transactionList, context);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterTransaction.TransactionViewHolder holder, int position) {
        Transaction currentTransaction = transactionList.get(position);
        holder.transactionTitle.setText(currentTransaction.title);
        holder.transactionValue.setText(String.format("USD$ %d", currentTransaction.value));
        holder.transactionValue.setTextColor(Color.parseColor(currentTransaction.color));
        holder.transactionCategory.setText(currentTransaction.category);
        holder.transactionDate.setText(currentTransaction.date);
    }

    @Override
    public int getItemCount() {
        return transactionList.size();
    }

    public class TransactionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        public final TextView transactionTitle;
        public final TextView transactionValue;
        public final TextView transactionCategory;
        public final TextView transactionDate;
        private EditTransaction editTransaction;
        private List<Transaction> transactionList;
        private Context context;
        public TransactionViewHolder(@NonNull View itemView, EditTransaction editTransaction, List<Transaction> transactionList, Context context) {
            super(itemView);
            transactionTitle = itemView.findViewById(R.id.transaction_title_id);
            transactionValue = itemView.findViewById(R.id.transaction_value_id);
            transactionCategory = itemView.findViewById(R.id.transaction_category_id);
            transactionDate = itemView.findViewById(R.id.transaction_date_id);
            this.editTransaction = editTransaction;
            this.transactionList = transactionList;
            this.context = context;
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View view) { // edit Transaction
            int position = getAdapterPosition();
            String color = transactionList.get(position).color;
            editTransaction.editTransaction(transactionList.get(position).transaction_id, color);
        }

        @Override
        public boolean onLongClick(View view) { // Alert if yes delete
            int position = getAdapterPosition();
            AlertDialog dialog = createDialog(position);
            dialog.show();
            return true;
        }
    }

    AlertDialog createDialog(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("Do you want to delete?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                editTransaction.deleteTransaction(transactionList.get(position).transaction_id);
                transactionList.remove(position);
                notifyItemRemoved(position);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        return builder.create();
    }

}
