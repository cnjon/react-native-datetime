package com.keyee.datetime;

import android.app.Activity;
import android.app.DialogFragment;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReadableMap;

public class RCTDateTimePicker extends ReactContextBaseJavaModule {

    public RCTDateTimePicker(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return "DateTimePicker";
    }

    @ReactMethod
    public void showDatePicker(ReadableMap options, Callback callback) {
        DialogFragment datePicker = new DatePicker(options, callback);
        datePicker.show(getCurrentActivity().getFragmentManager(), "datePicker");
    }

    @ReactMethod
    public void showTimePicker(ReadableMap options, Callback callback) {
        DialogFragment timePicker = new TimePicker(options, callback);
        timePicker.show(getCurrentActivity().getFragmentManager(), "timePicker");
    }

    @ReactMethod
    public void showDateTimePicker(ReadableMap options, Callback callback) {
        DialogFragment datetimePicker = new DateTimePicker(options, callback);
        datetimePicker.show(getCurrentActivity().getFragmentManager(), "datetimePicker");
    }
}
