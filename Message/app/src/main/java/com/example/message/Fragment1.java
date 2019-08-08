package com.example.message;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.message.message.Message_Box;
import com.example.message.message.Message_Send;
import com.example.message.message.Received_Detail;
import com.example.message.message.Received_Detail2;
import com.example.message.message.Received_Detail9;

import java.util.ArrayList;
import java.util.List;

public class Fragment1 extends Fragment {
    private View rootView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.activity_message, container, false);
        rootView.findViewById(R.id.received1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent();
                intent1.setClass(getActivity(), Received_Detail.class);
                startActivity(intent1);
            }
        });
        rootView.findViewById(R.id.received2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent();
                intent1.setClass(getActivity(), Received_Detail2.class);
                startActivity(intent1);
            }
        });
        rootView.findViewById(R.id.received9).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent();
                intent1.setClass(getActivity(), Received_Detail9.class);
                startActivity(intent1);
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














