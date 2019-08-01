package com.example.message;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class ScheduleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_schedule);


        Button button = (Button) findViewById(R.id.btn_back);
        button.setOnClickListener(view -> finish());
    }

}
