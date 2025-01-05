package com.example.myapplication;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;

import java.util.ArrayList;
import java.util.List;

public class GraphActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_graph);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        AnyChartView anyChartView = findViewById(R.id.balanceGraph_id);

        Pie pie = AnyChart.pie(); // create new object of chart

        TransactionDatabase db = Room.databaseBuilder(
                getBaseContext(), TransactionDatabase.class, "transaction-database"
        ).allowMainThreadQueries().build();

        // Convert the List into a dataEntries;
        List<Transaction> transactions = db.transactionDao().getAllTransactions();
        List<DataEntry> dataEntries = new ArrayList<>();

        for (Transaction transaction : transactions) {
            if (transaction.value < 0) {
                dataEntries.add(new ValueDataEntry(transaction.title, -(transaction.value)));
            } else {
                dataEntries.add(new ValueDataEntry(transaction.title, transaction.value));
            }
        }

        pie.data(dataEntries);
        anyChartView.setChart(pie);
    }
}