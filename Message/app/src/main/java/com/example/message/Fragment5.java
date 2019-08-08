package com.example.message;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.message.my.My_Document;
import com.example.message.my.Setting;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Fragment5 extends Fragment {

    private View view;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        view=inflater.inflate(R.layout.activity_my,container,false);
        view.findViewById(R.id.my_document).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1=new Intent(getActivity(),My_Document.class);
                startActivity(i1);
            }
        });
        view.findViewById(R.id.my_message).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setFragment(1);
            }
        });
        view.findViewById(R.id.my_address).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setFragment(2);
            }
        });
        view.findViewById(R.id.my_work).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setFragment(3);
            }
        });
        view.findViewById(R.id.my_calendar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setFragment(4);
            }
        });
        return view;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
    }
    private FragmentTransaction transaction;
    private FragmentManager fragmentManager;
    public void setFragment(int i ){
        fragmentManager=getFragmentManager();
        transaction=fragmentManager.beginTransaction();
        switch (i){
            case 1:transaction.replace(R.id.content,new Fragment1());break;
            case 2:transaction.replace(R.id.content,new Fragment2());break;
            case 3:transaction.replace(R.id.content,new Fragment3());break;
            case 4:transaction.replace(R.id.content,new Fragment4());break;
            case 5:transaction.replace(R.id.content,new Fragment5());break;
            default:break;
        }
        transaction.commit();
    }
}
