package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private void refreshData() {
        TransactionDatabase db = Room.databaseBuilder(
                getBaseContext(), TransactionDatabase.class, "transaction-database"
        ).allowMainThreadQueries().build();

        List<Transaction> transactions = db.transactionDao().getAllTransactions();

        int total_balance = 0;
        int total_revenue = 0;
        int total_expense = 0;

        // Calculate total revenue, total expense, and total balance
        for (Transaction transaction : transactions) {
            if (transaction.color.equals("#4CAF50")) { // positive revenue
                total_revenue += transaction.value;
            } else {
                total_expense += transaction.value;
            }
        }

        total_balance = total_expense + total_revenue;;

        // Update the UI with the new values
        TextView balanceAmount = findViewById(R.id.tvBalanceAmount);
        TextView revenueAmount = findViewById(R.id.tvRevenueAmount);
        TextView expenseAmount = findViewById(R.id.tvExpenseAmount);

        balanceAmount.setText(String.valueOf(total_balance));
        revenueAmount.setText(String.valueOf(total_revenue));
        expenseAmount.setText(String.valueOf(total_expense));
    };

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

        Button btnAddRevenue = findViewById(R.id.btnAddRevenue);
        Button btnAddExpense = findViewById(R.id.btnAddExpense);
        Button btnViewTransactions = findViewById(R.id.btnViewTransactions);

        Intent transactionIntent = new Intent(this, transactions.class);
        Intent showTransactionsIntent = new Intent(this, transactions_list.class);
        Intent graphIntent = new Intent(this, GraphActivity.class);

        // ------------------ DASHBOARD LOGIC ---------------- //
        refreshData();

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
                startActivity(showTransactionsIntent);
            }
        });

        // -------------- GRAPH ---------------- //
        LinearLayout layoutBalance = findViewById(R.id.cardBalance);
        LinearLayout layoutRevenue = findViewById(R.id.cardRevenue);
        LinearLayout layoutExpense = findViewById(R.id.cardExpense);

        layoutBalance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(graphIntent);
            }
        });

        layoutRevenue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(graphIntent);
            }
        });

        layoutExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(graphIntent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshData();
    }
}