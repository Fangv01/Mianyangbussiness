package com.example.message.message;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.message.Fragment1;
import com.example.message.R;

public class Message_Self extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.online_mss);
        findViewById(R.id.snd_mss).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent();
                intent1.setClass(Message_Self.this,Message_Send.class);
                startActivity(intent1);
            }
        });
        findViewById(R.id.gotten_mss).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2=new Intent();
                intent2.setClass(Message_Self.this,Message_Received.class);
                startActivity(intent2);
            }
        });
        findViewById(R.id.search_mss).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3=new Intent();
                intent3.setClass(Message_Self.this,Message_Sended.class);
                startActivity(intent3);
            }
        });
        findViewById(R.id.ready_send_mss).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4=new Intent();
                intent4.setClass(Message_Self.this,Message_Box.class);
                startActivity(intent4);
            }
        });
    }

}
