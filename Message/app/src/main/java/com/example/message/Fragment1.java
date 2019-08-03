package com.example.message;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.message.message.Message_Self;
import com.example.message.message.Message_Phone;

public class Fragment1 extends Fragment {
    private View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.activity_message, container, false);
        rootView.findViewById(R.id.offline_mss).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent();
                intent1.setClass(getActivity(),Message_Phone.class);
                startActivity(intent1);
            }
        });
        rootView.findViewById(R.id.online_mss).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2=new Intent();
                intent2.setClass(getActivity(),Message_Self.class);
                startActivity(intent2);
            }
        });
        return rootView;
    }
}














