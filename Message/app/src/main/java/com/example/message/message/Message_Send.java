package com.example.message.message;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.message.R;

public class Message_Send extends Activity {
    private TextView send,save;
    private Context mcontext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.snd_mss);
        mcontext=this;
        findViewById(R.id.snd_mss).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mcontext,"已发送",Toast.LENGTH_LONG).show();
            }
        });
        findViewById(R.id.saveintobox).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mcontext,"已存至草稿箱",Toast.LENGTH_LONG).show();
            }
        });
    }

}
