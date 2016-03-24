package com.keyee.datetime;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.TimePicker;
import android.widget.TimePicker.OnTimeChangedListener;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import android.app.AlertDialog;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import java.util.Calendar;
import android.text.format.DateFormat;
import java.text.SimpleDateFormat;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReadableMap;

public class DateTimePicker extends DialogFragment implements OnDateChangedListener,OnTimeChangedListener,DialogInterface.OnClickListener
{
    static final String TAG = DateTimePicker.class.getSimpleName();
    private Callback callback;

    private LinearLayout dateTimeLayout;
    private ScrollView dateTimeScrollView;
    private DatePicker datePicker;
    private TimePicker timePicker;
    private AlertDialog ad;

    private String cancelText;
    private String okText;

    private Calendar calendar;

    public DateTimePicker(ReadableMap options, Callback callback) {
        this.callback = callback;
        calendar = Calendar.getInstance();
        cancelText = options.getString("cancelText");
        okText = options.getString("okText");
        if (options.hasKey("year")) {
            calendar.set(Calendar.YEAR, options.getInt("year"));
        }
        if (options.hasKey("month")) {
            calendar.set(Calendar.MONTH, options.getInt("month"));
        }
        if (options.hasKey("day")) {
            calendar.set(Calendar.DAY_OF_MONTH, options.getInt("day"));
        }
        if (options.hasKey("hour")) {
            calendar.set(Calendar.HOUR_OF_DAY, options.getInt("hour"));
        }
        if (options.hasKey("minute")) {
            calendar.set(Calendar.MINUTE, options.getInt("minute"));
        }
    }

    private void initializePickers(){
        datePicker.init(
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH),
            this
        );
        datePicker.setCalendarViewShown(false);
        timePicker.setCurrentHour(calendar.get(Calendar.HOUR_OF_DAY));
        timePicker.setCurrentMinute(calendar.get(Calendar.MINUTE));
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        if (datePicker == null){
            datePicker = new ScrollableDatePicker(this.getActivity());
            datePicker.setCalendarViewShown(false);
        }
        if (timePicker == null){
            timePicker = new TimePicker(this.getActivity());
            timePicker.setIs24HourView(DateFormat.is24HourFormat(getActivity()));
            timePicker.setOnTimeChangedListener(this);
        }
        if (dateTimeLayout == null){
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.FILL_PARENT,
                LinearLayout.LayoutParams.FILL_PARENT
            );
            dateTimeLayout = new LinearLayout(this.getActivity());
            dateTimeLayout.setLayoutParams(params);
            dateTimeLayout.setOrientation(LinearLayout.VERTICAL);
            dateTimeLayout.addView(datePicker);
            dateTimeLayout.addView(timePicker);
        }
        if (dateTimeScrollView == null){
            dateTimeScrollView = new ScrollView(this.getActivity());
            dateTimeScrollView.addView(dateTimeLayout);
        }
        initializePickers();
        ad = new AlertDialog.Builder(this.getActivity())
            .setTitle(getFormattedDateTime())
            .setView(dateTimeScrollView)
            .setPositiveButton(okText, this)
            .setNegativeButton(cancelText, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {}
            }).show();
        return ad;
    }

    public void onClick(DialogInterface dialog, int whichButton) {
        this.callback.invoke(
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH),
            calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE)
        );
    }

    private String getFormattedDateTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        return simpleDateFormat.format(calendar.getTime());
    }

    private void setTitle(){
        if (ad != null){
            ad.setTitle(getFormattedDateTime());
        }
    }

    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, monthOfYear);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        setTitle();
    }

    public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        calendar.set(Calendar.MINUTE, minute);
        setTitle();
    }
}