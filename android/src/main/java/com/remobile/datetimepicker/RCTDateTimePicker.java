package com.keyee.datetime;

import android.app.Activity;
import android.app.DialogFragment;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReadableMap;

public class RCTDateTimePicker extends ReactContextBaseJavaModule {
    private Activity activity;

    public RCTDateTimePicker(ReactApplicationContext reactContext, Activity activity) {
        super(reactContext);
        this.activity = activity;
    }

    @Override
    public String getName() {
        return "DateTimePicker";
    }

    @ReactMethod
    public void showDatePicker(ReadableMap options, Callback callback) {
        DialogFragment datePicker = new DatePicker(options, callback);
        datePicker.show(activity.getFragmentManager(), "datePicker");
    }

    @ReactMethod
    public void showTimePicker(ReadableMap options, Callback callback) {
        DialogFragment timePicker = new TimePicker(options, callback);
        timePicker.show(activity.getFragmentManager(), "timePicker");
    }

    @ReactMethod
    public void showDateTimePicker(ReadableMap options, Callback callback) {
        DialogFragment datetimePicker = new DateTimePicker(options, callback);
        datetimePicker.show(activity.getFragmentManager(), "datetimePicker");
    }
}