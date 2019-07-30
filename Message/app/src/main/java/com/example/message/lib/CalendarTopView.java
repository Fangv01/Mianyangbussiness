package com.example.message.lib;

public interface CalendarTopView {
    int[] getCurrentSelectPositon();

    int getItemHeight();

    void setCalendarTopViewChangeListener(CalendarTopViewChangeListener calendarTopViewChangeListener);
}
