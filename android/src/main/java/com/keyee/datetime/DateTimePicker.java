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
import android.widget.LinearLayout;
import android.widget.ScrollView;

import java.util.Calendar;
import android.text.format.DateFormat;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReadableMap;


public class DateTimePicker extends DialogFragment implements OnDateChangedListener,OnTimeChangedListener,DialogInterface.OnClickListener
{
    static final String TAG = DateTimePicker.class.getSimpleName();
    private Callback callback;
    private int year, month, day;
    private int hour, minute;

    private LinearLayout dateTimeLayout;
    private ScrollView dateTimeScrollView;
    private DatePicker datePicker;
    private TimePicker timePicker;
    private AlertDialog ad;

    private String currentDateTime;

    public DateTimePicker(ReadableMap options, Callback callback) {
        final Calendar c = Calendar.getInstance();
        this.callback = callback;
        year = options.hasKey("year") ? options.getInt("year") : c.get(Calendar.YEAR);
        month = options.hasKey("month") ? options.getInt("month") : c.get(Calendar.MONTH);
        day = options.hasKey("day") ? options.getInt("day") : c.get(Calendar.DAY_OF_MONTH);
        hour = options.hasKey("hour") ? options.getInt("hour") : c.get(Calendar.HOUR_OF_DAY);
        minute = options.hasKey("minute") ? options.getInt("minute") : c.get(Calendar.MINUTE);
    }

    private void setCurrentDatetime(){
        currentDateTime = year +"/"+(month+1)+"/"+day+" "+hour+":"+minute;
        datePicker.init(year, month, day, this);
        datePicker.setCalendarViewShown(false);
        timePicker.setCurrentHour(hour);
        timePicker.setCurrentMinute(minute);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        if (datePicker == null){
            datePicker = new DatePicker(this.getActivity());
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
        setCurrentDatetime();
        ad = new AlertDialog.Builder(this.getActivity())
            .setTitle(currentDateTime)
            .setView(dateTimeScrollView)
            .setPositiveButton("设置", this)
            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {}
            }).show();
        return ad;
    }

    public void onClick(DialogInterface dialog, int whichButton) {
        this.callback.invoke(this.year, this.month, this.day, this.hour, this.minute);
    }

    private void setTitle(){
        if (ad != null){
            currentDateTime = year +"/"+(month+1)+"/"+day+" "+hour+":"+minute;
            ad.setTitle(currentDateTime);
        }
    }

    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        this.year = year;
        this.month = monthOfYear;
        this.day = dayOfMonth;
        setTitle();
    }

    public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
        this.hour = hourOfDay;
        this.minute = minute;
        setTitle();
    }
}