package com.example.myapplication;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;


@Dao // Data Access Object
public interface transactionDao {
    @Insert
    void insert(Transaction... transactions);

    @Query("SELECT * FROM `transaction`")
    List<Transaction> getAllTransactions();

    @Query("DELETE FROM `transaction`")
    void deleteAll();

    @Update
    void update(Transaction transaction);

    @Query("DELETE FROM `transaction` WHERE `transaction_id` = :transactionId")
    void deleteById(int transactionId);
}
