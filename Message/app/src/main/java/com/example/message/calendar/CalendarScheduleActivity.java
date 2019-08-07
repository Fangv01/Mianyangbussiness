package com.example.message.calendar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.message.R;
import com.example.message.SecondActivity;
import com.example.message.calendar.CalendarCustomDatePicker;
import com.example.message.calendar.CalendarDateFormatUtils;

import butterknife.BindView;

public class CalendarScheduleActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView  mTvSelectedTime1,mTvSelectedTime2;
    private CalendarCustomDatePicker mTimerPicker1,mTimerPicker2;
    @BindView(R.id.et_content)
    EditText et_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_schedule);

        findViewById(R.id.tv_selected_time1).setOnClickListener(this);
        mTvSelectedTime1 = findViewById(R.id.tv_selected_time1);
        initTimerPicker1();
        findViewById(R.id.tv_selected_time2).setOnClickListener(this);
        mTvSelectedTime2 = findViewById(R.id.tv_selected_time2);
        initTimerPicker2();

        Button button = (Button) findViewById(R.id.btn_back);
        button.setOnClickListener(view -> finish());

        Button button2 = (Button) findViewById(R.id.btn_complete);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    //String event = et_content.getText().toString();
                    Intent intent = new Intent(CalendarScheduleActivity.this, SecondActivity.class);
                    intent.putExtra("id", 4);
                    intent.putExtra("content", "schedule");
                    startActivityForResult(intent, 10);
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_selected_time1:
                // 日期格式为yyyy-MM-dd HH:mm
                mTimerPicker1.show(mTvSelectedTime1.getText().toString());
                break;

            case R.id.tv_selected_time2:
                // 日期格式为yyyy-MM-dd HH:mm
                mTimerPicker2.show(mTvSelectedTime1.getText().toString());
                break;
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }



    private void initTimerPicker1() {
        String beginTime = CalendarDateFormatUtils.long2Str(System.currentTimeMillis(), true);
        String endTime = "2020-10-10 10:00";

        mTvSelectedTime1.setText(endTime);

        // 通过日期字符串初始化日期，格式请用：yyyy-MM-dd HH:mm
        mTimerPicker1 = new CalendarCustomDatePicker(this, new CalendarCustomDatePicker.Callback() {
            @Override
            public void onTimeSelected(long timestamp) {
                mTvSelectedTime1.setText(CalendarDateFormatUtils.long2Str(timestamp, true));
            }
        }, beginTime, endTime);
        // 允许点击屏幕或物理返回键关闭
        mTimerPicker1.setCancelable(true);
        // 显示时和分
        mTimerPicker1.setCanShowPreciseTime(true);
        // 允许循环滚动
        mTimerPicker1.setScrollLoop(true);
        // 允许滚动动画
        mTimerPicker1.setCanShowAnim(true);
    }

    private void initTimerPicker2() {
        String beginTime = CalendarDateFormatUtils.long2Str(System.currentTimeMillis(), true);
        String endTime = "2020-10-10 10:00";

        mTvSelectedTime2.setText(endTime);

        // 通过日期字符串初始化日期，格式请用：yyyy-MM-dd HH:mm
        mTimerPicker2 = new CalendarCustomDatePicker(this, new CalendarCustomDatePicker.Callback() {
            @Override
            public void onTimeSelected(long timestamp) {
                mTvSelectedTime2.setText(CalendarDateFormatUtils.long2Str(timestamp, true));
            }
        }, beginTime, endTime);
        // 允许点击屏幕或物理返回键关闭
        mTimerPicker2.setCancelable(true);
        // 显示时和分
        mTimerPicker2.setCanShowPreciseTime(true);
        // 允许循环滚动
        mTimerPicker2.setScrollLoop(true);
        // 允许滚动动画
        mTimerPicker2.setCanShowAnim(true);
    }
}
