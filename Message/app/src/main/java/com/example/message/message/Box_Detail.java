package com.example.message.message;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.example.message.R;

public class Box_Detail extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.box_detial);
        findViewById(R.id.send_mss).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        findViewById(R.id.saveintobox).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
