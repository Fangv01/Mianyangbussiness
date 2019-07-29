package com.example.message;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Fragment1 extends Fragment {


    private View view;

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//
//        view = inflater.inflate(R.layout.activity_message, container, false);
//        return view;
//
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_message, container, false);

            rootView.findViewById(R.id.offline_mss).setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    getFragmentManager()
                            .beginTransaction()
                            .addToBackStack(null)  //将当前fragment加入到返回栈中
                            .replace(R.id.container, new message_phone()).commit();
                }

            });
        rootView.findViewById(R.id.online_mss).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                getFragmentManager()
                        .beginTransaction()
                        .addToBackStack(null)  //将当前fragment加入到返回栈中
                        .replace(R.id.container, new Message_Self()).commit();
            }

        });
            return rootView;
        }
    }














