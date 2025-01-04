package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView balanceAmount = findViewById(R.id.tvBalanceAmount);
        TextView revenueAmount = findViewById(R.id.tvRevenueAmount);
        TextView expenseAmount = findViewById(R.id.tvExpenseAmount);

        Button btnAddRevenue = findViewById(R.id.btnAddRevenue);
        Button btnAddExpense = findViewById(R.id.btnAddExpense);
        Button btnViewTransactions = findViewById(R.id.btnViewTransactions);

        Intent transactionIntent = new Intent(this, transactions.class);

        // --------------------- DATABASE LOGIC ------------------- //
        TransactionDatabase db = Room.databaseBuilder(
                getBaseContext(), TransactionDatabase.class, "transaction-database"
        ).allowMainThreadQueries().build();

        btnAddRevenue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                transactionIntent.putExtra("Checker", 0); // if 0 meaning the transaction amount should be positive;
                startActivity(transactionIntent);
            }
        });

        btnAddExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                transactionIntent.putExtra("Checker", 1); // if 0 meaning the transaction amount should be negative;
                startActivity(transactionIntent);
            }
        });

        btnViewTransactions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Transaction> transactions = db.transactionDao().getAllTransactions();

                for (Transaction transaction : transactions) {
                    Log.d("transaction", transaction.title + " " +
                            String.valueOf(transaction.value) + " " +
                            transaction.category + " " +
                            transaction.date);
                }
            }
        });
    }
}