package com.keyee.datetime;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import java.util.Calendar;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReadableMap;


public class DatePicker extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    private Callback callback;
    private int year, month, day;

    public DatePicker(ReadableMap options, Callback callback) {
        final Calendar c = Calendar.getInstance();
        this.callback = callback;
        year = options.hasKey("year") ? options.getInt("year") : c.get(Calendar.YEAR);
        month = options.hasKey("month") ? options.getInt("month") : c.get(Calendar.MONTH);
        day = options.hasKey("day") ? options.getInt("day") : c.get(Calendar.DAY_OF_MONTH);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog picker = new RCTDatePickDialog(getActivity(), this, year, month, day);
        return picker;
    }

    @Override
    public void onDateSet(android.widget.DatePicker datePicker, int year, int month, int day) {
        callback.invoke(year, month, day);
    }

    class RCTDatePickDialog extends DatePickerDialog {
        public RCTDatePickDialog(Context context, OnDateSetListener callBack,
                                 int year, int monthOfYear, int dayOfMonth) {
            super(context, callBack, year, monthOfYear, dayOfMonth);
        }

        @Override
        protected void onStop() {
        }
    }
}