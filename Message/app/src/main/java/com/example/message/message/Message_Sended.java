package com.example.message.message;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.example.message.R;

public class Message_Sended extends Activity {
    private Button popwindow;
    private PopupWindow Pop;
    private Context mcontext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sended_mss);
        mcontext=this;
        popwindow=findViewById(R.id.pop);
        final TextView message1=findViewById(R.id.mss1);
        popwindow.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                View view = getLayoutInflater().inflate(R.layout.message_popwindow, null);
                TextView pop_d =view.findViewById(R.id.pop_delete);
                pop_d.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Pop.dismiss();
                        message1.setText(null);
                        Toast.makeText(mcontext,"已删除",Toast.LENGTH_LONG).show();
                    }
                });
                Pop = new PopupWindow(view, 200, ViewGroup.LayoutParams.WRAP_CONTENT);
                Pop.setOutsideTouchable(true);
                Pop.setFocusable(true);
                Pop.showAsDropDown(popwindow);
            }
        });
    }
}
