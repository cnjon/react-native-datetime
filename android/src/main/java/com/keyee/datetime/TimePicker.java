package com.keyee.datetime;

import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import java.util.Calendar;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReadableMap;

import android.text.format.DateFormat;

public class TimePicker extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

    private Callback callback;
    private int hour, minute;

    public TimePicker(ReadableMap options, Callback callback) {
        final Calendar c = Calendar.getInstance();
        this.callback = callback;
        hour = options.hasKey("hour") ? options.getInt("hour") : c.get(Calendar.HOUR_OF_DAY);
        minute = options.hasKey("minute") ? options.getInt("minute") : c.get(Calendar.MINUTE);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new RCTTimePickerDialog(getActivity(), this, hour, minute, DateFormat.is24HourFormat(getActivity()));
    }

    @Override
    public void onTimeSet(android.widget.TimePicker datePicker, int hour, int minute) {
        callback.invoke(hour, minute);
    }

    class RCTTimePickerDialog extends TimePickerDialog {
        public RCTTimePickerDialog(Context context, OnTimeSetListener listener, int hourOfDay, int minute,
                                   boolean is24HourView) {
            super(context, listener, hourOfDay, minute, is24HourView);
        }

        @Override
        protected void onStop() {
        }
    }
}