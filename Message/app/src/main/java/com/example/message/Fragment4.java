package com.example.message;

import android.app.Fragment;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.example.message.calendar.CalendarAdapter;
import com.example.message.calendar.CalendarBean;
import com.example.message.calendar.CalendarDateView;
import com.example.message.calendar.CalendarEvent;
import com.example.message.calendar.CalendarEventAdapter;
import com.example.message.calendar.CalendarScheduleActivity;
import com.example.message.calendar.CalendarUtil;
import com.example.message.calendar.CalendarView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class Fragment4 extends Fragment {

    private View view;

    @BindView(R.id.calendarDateView)
    CalendarDateView mCalendarDateView;
    @BindView(R.id.cal_title)
    TextView mTitle;
    @BindView(R.id.cal_list)
    ListView mList;
    @BindView(R.id.fab_add)
    FloatingActionButton floatingActionButton;

    private List<CalendarEvent> calendarEvent ;
    private CalendarEventAdapter adapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view=inflater.inflate(R.layout.activity_calendar,container,false);
        if (null != view) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (null != parent) {
                parent.removeView(view);
            }
        }else{
        view = inflater.inflate(R.layout.activity_calendar,container,false); }

        ButterKnife.bind(this, view);

        mCalendarDateView.setAdapter(new CalendarAdapter() {
            @Override
            public View getView(View convertView, ViewGroup parent, CalendarBean bean) {
                TextView view;
                if (convertView == null) {
                    convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.calendar_item, null);
                    ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(px(48), px(48));
                    convertView.setLayoutParams(params);
                }

                view = (TextView) convertView.findViewById(R.id.cal_date);

                view.setText("" + bean.day);
                if (bean.monthFlag != 0) {
                    view.setTextColor(0xff9299a1);
                } else {
                    view.setTextColor(0xff000000);
                }

                return convertView;
            }

            private int px(int i) {
                return 0;
            }
        });

        mCalendarDateView.setOnItemClickListener(new CalendarView.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, CalendarBean bean) {
                mTitle.setText(bean.year + "/" + getDisPlayNumber(bean.month) + "/" + getDisPlayNumber(bean.day));
            }
        });

        int[] data = CalendarUtil.getYMD(new Date());
        mTitle.setText(data[0] + "/" + data[1] + "/" + data[2]);

       // String str = (String) getArguments().getString("str");
        calendarEvent = new ArrayList<CalendarEvent>();
        calendarEvent.add(new CalendarEvent("小组会议",R.drawable.cal_dot));
        calendarEvent.add(new CalendarEvent("部门会议",R.drawable.cal_dot));
        calendarEvent.add(new CalendarEvent("小组会议",R.drawable.cal_dot));
        calendarEvent.add(new CalendarEvent("部门会议",R.drawable.cal_dot));
        //calendarEvent.add(new CalendarEvent("ssss",R.drawable.cal_dot));
        adapter = new CalendarEventAdapter(getActivity(), calendarEvent);
        mList.setAdapter(adapter);

        //跳转到下一活动
        Intent intent = new Intent(getActivity(), CalendarScheduleActivity.class);
        floatingActionButton.setOnClickListener(view1 -> startActivity(intent));

        return view;
    }
    private String getDisPlayNumber(int num) {
        return num < 10 ? "0" + num : "" + num;
    }
}
