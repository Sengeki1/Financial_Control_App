package com.example.myapplication;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Transaction.class}, version = 1)
public abstract class TransactionDatabase extends RoomDatabase {
    public abstract transactionDao transactionDao();
}
