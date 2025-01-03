package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.DialogFragment;

import java.util.ArrayList;
import java.util.Calendar;

public class transactions extends AppCompatActivity {
    String date = "";
    String category = "";
    TextView showDateView;

    showDate showdate = new showDate() {
        @Override
        public void returnDate(String value) {
            date = value;
            showDateView.setText(date);
        }
    };

    public void showDatePickerDialog() {
        DialogFragment newFragment = new DatePickerDialog(showdate);
        newFragment.show(getSupportFragmentManager(), "Date Picker");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_transactions);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        int checker = (int) getIntent().getExtras().get("Checker");

        showDateView = findViewById(R.id.tvSelectedDate);
        EditText titulo = findViewById(R.id.etTitle);
        EditText value = findViewById(R.id.etValue);
        Button selectDate = findViewById(R.id.btnSelectDate);
        Button cancel = findViewById(R.id.btnCancel);
        Button save = findViewById(R.id.btnSave);
        Spinner spinner = findViewById(R.id.spCategory);

        Intent intentDashboard = new Intent(this, MainActivity.class);

        selectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String n_titulo = String.valueOf(titulo.getText());
                String n_value = String.valueOf(value.getText());

                if (!n_titulo.isEmpty() && !n_value.isEmpty()) {
                    if (date.isEmpty()) {
                        final Calendar calendar = Calendar.getInstance();
                        int day = calendar.get(Calendar.DAY_OF_MONTH);
                        int year = calendar.get(Calendar.YEAR);
                        int month = calendar.get(Calendar.MONTH) + 1;

                        showDateView.setText(day + "/" + month + "/" + year);
                        date = day + "/" + month + "/" + year;
                    }
                    if (category.isEmpty()) {
                        category = "Alimentação";
                    }
                    startActivity(intentDashboard);
                } else {
                    Toast.makeText(getBaseContext(), "Porfavor prencha os campos", Toast.LENGTH_LONG).show();
                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                titulo.setText("");
                value.setText("");
                startActivity(intentDashboard);
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                category = item;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayList<String> items = new ArrayList<>();
        items.add("Alimentação");
        items.add("Transporte");
        items.add("Salário");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        spinner.setAdapter(adapter);
    }
}