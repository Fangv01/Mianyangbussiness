package com.example.message.message;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.message.R;

public class Message_Box extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage_box);
        findViewById(R.id.box1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent();
                intent1.setClass(Message_Box.this,Box_Detail.class);
                startActivity(intent1);
            }
        });
        findViewById(R.id.box2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent();
                intent1.setClass(Message_Box.this,Box_Detail2.class);
                startActivity(intent1);
            }
        });
        findViewById(R.id.box5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent();
                intent1.setClass(Message_Box.this,Box_Detail5.class);
                startActivity(intent1);
            }
        });

    }
}
