package com.example.message;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Calendar;

import butterknife.ButterKnife;


public class Fragment4 extends Fragment {
    private View view;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.activity_calendar,container,false);

        ButterKnife.bind(this, view);
        Intent intent = new Intent(getActivity(), CalendarActivity.class);
        startActivity(intent);

        return view;
    }
}
