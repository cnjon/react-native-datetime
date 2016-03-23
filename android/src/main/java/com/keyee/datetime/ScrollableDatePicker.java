package com.keyee.datetime;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewParent;
import android.widget.DatePicker;

public class ScrollableDatePicker extends DatePicker {

    public ScrollableDatePicker(Context context) {
        super(context);
    }

    public ScrollableDatePicker(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScrollableDatePicker(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        ViewParent parentView = getParent();
        switch (ev.getActionMasked()) {
        case MotionEvent.ACTION_DOWN:
            parentView.requestDisallowInterceptTouchEvent(true);
            break;

        case MotionEvent.ACTION_UP:
            parentView.requestDisallowInterceptTouchEvent(false);
            break;
        }

        return false;
    }
}