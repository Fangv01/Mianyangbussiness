package com.example.message;

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

public class Message_Sended extends Message_My {
    private View view;
    private Button popwindow;
    private PopupWindow Pop;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        view=inflater.inflate(R.layout.sended_mss,container,false);
        popwindow=view.findViewById(R.id.pop);
        final TextView message1=view.findViewById(R.id.mss1);
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
                        //
                        message1.setText(null);
                        Toast.makeText(getActivity(),"已删除",Toast.LENGTH_LONG).show();
                    }
                });
                Pop = new PopupWindow(view, 200, ViewGroup.LayoutParams.WRAP_CONTENT);
                Pop.setOutsideTouchable(true);
                Pop.setFocusable(true);
                Pop.showAsDropDown(popwindow);
            }
        });

        return view;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }
}
