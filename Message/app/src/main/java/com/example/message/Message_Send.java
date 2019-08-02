package com.example.message;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class Message_Send extends message_phone {
    private View view;
    private Button send,save;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        view=inflater.inflate(R.layout.snd_mss,container,false);
        send=view.findViewById(R.id.snd_mss);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"已发送",Toast.LENGTH_LONG).show();
            }
        });
        save=view.findViewById(R.id.saveintobox);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"已存至草稿箱",Toast.LENGTH_LONG).show();
            }
        });
        return view;

    }
}
