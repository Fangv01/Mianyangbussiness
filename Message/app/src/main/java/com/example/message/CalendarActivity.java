package com.example.message;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.message.lib.CalendarAdapter;
import com.example.message.lib.CalendarBean;
import com.example.message.lib.CalendarDateView;
import com.example.message.lib.CalendarUtil;
import com.example.message.lib.CalendarView;

import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CalendarActivity extends AppCompatActivity {

    @BindView(R.id.calendarDateView)
    CalendarDateView mCalendarDateView;
    @BindView(R.id.list)
    ListView mList;
    @BindView(R.id.title)
    TextView mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        ButterKnife.bind(this);

        mCalendarDateView.setAdapter(new CalendarAdapter() {
            @Override
            public View getView(View convertView, ViewGroup parentView, CalendarBean bean) {
                TextView view;
                if (convertView == null) {
                    convertView = LayoutInflater.from(parentView.getContext()).inflate(R.layout.calendar_item, null);
                    ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(px(48), px(48));
                    convertView.setLayoutParams(params);
                }

                view = (TextView) convertView.findViewById(R.id.text);

                view.setText("" + bean.day);
                if (bean.monthFlag != 0) {
                    view.setTextColor(0xff9299a1);
                } else {
                    view.setTextColor(0xffffffff);
                }

                return convertView;
            }


        });

        mCalendarDateView.setOnItemClickListener(new CalendarView.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int postion, CalendarBean bean) {
                mTitle.setText(bean.year + "/" + getDisPlayNumber(bean.month) + "/" + getDisPlayNumber(bean.day));
            }
        });

        int[] data = CalendarUtil.getYMD(new Date());
        mTitle.setText(data[0] + "/" + data[1] + "/" + data[2]);

        mList.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return 100;
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = LayoutInflater.from(CalendarActivity.this).inflate(android.R.layout.simple_list_item_1, null);
                }

                TextView textView = (TextView) convertView;
                textView.setText("item" + position);

                return convertView;
            }
        });
    }

    private int px(int i) {
        return 0;
    }

    private String getDisPlayNumber(int num) {
        return num < 10 ? "0" + num : "" + num;
    }

    @OnClick(R.id.back)
    public void onClick(){
        finish();
    }
    
}
