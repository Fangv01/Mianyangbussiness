package com.example.message.lib;

import android.view.View;
import android.view.ViewGroup;

public interface CalendarAdapter {
    View getView(View convertView, ViewGroup parentView, CalendarBean bean);
}
