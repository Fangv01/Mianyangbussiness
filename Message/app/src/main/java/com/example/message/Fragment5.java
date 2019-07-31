package com.example.message;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.message.my.My_Document;
import com.example.message.my.Setting;

public class Fragment5 extends Fragment {

    private View view;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        view=inflater.inflate(R.layout.activity_my,container,false);
        Button md=view.findViewById(R.id.my_document);
        Button ms=view.findViewById(R.id.setting);
        md.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1=new Intent(getActivity(),My_Document.class);
                startActivity(i1);
            }
        });
        ms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i2=new Intent(getActivity(), Setting.class);
                startActivity(i2);
            }
        });
        return view;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
    }

}
