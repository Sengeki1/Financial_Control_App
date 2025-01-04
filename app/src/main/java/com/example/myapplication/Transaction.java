package com.example.myapplication;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Transaction {

    @PrimaryKey(autoGenerate = true)
    public int transaction_id;

    @ColumnInfo(name = "title")
    public String title;

    @ColumnInfo(name = "value")
    public int value;

    @ColumnInfo(name = "category")
    public String category;

    @ColumnInfo(name = "date")
    public String date;

    public Transaction(String title, int value, String category, String date) {
        this.title = title;
        this.value = value;
        this.category = category;
        this.date = date;
    }
}
