package com.example.message.message;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import android.app.Fragment;

import com.example.message.R;

public class Message_Phone extends Fragment {
    private Button btn;
    private View view;

//    @Override
//      public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//                 View rootView = inflater.inflate(R.layout.fragment_other, container, false);
//                 rootView.findViewById(R.id.btn_back).setOnClickListener(new View.OnClickListener() {
//
//                     @Override
//             public void onClick(View arg0) {
//                                 //从栈中将当前fragment推出
//                                 getFragmentManager().popBackStack();
//                             }
//         });
//                 return rootView;
//             }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.offline_mss, container, false);
        rootView.findViewById(R.id.snd_mss).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                getFragmentManager()
                        .beginTransaction()
                        .addToBackStack(null)  //将当前fragment加入到返回栈中
                        .replace(R.id.container, new Message_Send()).commit();
            }

        });
        rootView.findViewById(R.id.my_mss).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                getFragmentManager()
                        .beginTransaction()
                        .addToBackStack(null)  //将当前fragment加入到返回栈中
                        .replace(R.id.container, new Message_My()).commit();
            }

        });
        return rootView;
    }
    }


