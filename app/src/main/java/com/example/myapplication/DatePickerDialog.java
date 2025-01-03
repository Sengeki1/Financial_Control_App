package com.example.myapplication;

import android.app.Dialog;
import android.os.Bundle;
import android.widget.DatePicker;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class DatePickerDialog extends DialogFragment implements android.app.DatePickerDialog.OnDateSetListener {
    private String date;
    private showDate callback;

    DatePickerDialog(showDate callback) {
        this.callback = callback;
    }

    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        return new android.app.DatePickerDialog(getActivity(), this, year, month, day);
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        date = i2 + "/" + (i1 + 1) + "/" + i;
        callback.returnDate(date);
        // since java doesnt know work with points we can do a work arround with callbacks
    }
}
