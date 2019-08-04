package com.example.message;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.message.message.Message_Box;
import com.example.message.message.Message_Received;
import com.example.message.message.Message_Send;
import com.example.message.message.Message_Sended;

public class Fragment1 extends Fragment {
    private View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.activity_message, container, false);
        rootView.findViewById(R.id.received).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent();
                intent1.setClass(getActivity(), Message_Received.class);
                startActivity(intent1);
            }
        });
        rootView.findViewById(R.id.sended).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2=new Intent();
                intent2.setClass(getActivity(), Message_Sended.class);
                startActivity(intent2);
            }
        });
        rootView.findViewById(R.id.box).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3=new Intent();
                intent3.setClass(getActivity(), Message_Box.class);
                startActivity(intent3);
            }
        });
        rootView.findViewById(R.id.send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4=new Intent();
                intent4.setClass(getActivity(), Message_Send.class);
                startActivity(intent4);
            }
        });
        return rootView;
    }
}














