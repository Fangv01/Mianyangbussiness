package com.example.message.calendar;

import android.view.View;
import android.view.ViewGroup;

public interface CalendarAdapter {
    View getView(View convertView, ViewGroup parentView, CalendarBean bean);
}
