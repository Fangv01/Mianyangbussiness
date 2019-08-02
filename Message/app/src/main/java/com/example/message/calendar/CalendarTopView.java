package com.example.message.calendar;

public interface CalendarTopView {
    int[] getCurrentSelectPositon();

    int getItemHeight();

    void setCalendarTopViewChangeListener(CalendarTopViewChangeListener calendarTopViewChangeListener);
}
