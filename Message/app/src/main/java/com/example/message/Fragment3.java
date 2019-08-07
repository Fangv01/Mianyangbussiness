package com.example.message;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.message.work.Notice1;
import com.example.message.work.Notice2;
import com.example.message.work.Notice3;
import com.example.message.work.Notice4;
import com.example.message.work.Notice5;

import java.util.ArrayList;
import java.util.List;

public class Fragment3 extends Fragment {
    private View view;
    private ViewPager viewPager;
    private List<View> viewList;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        view=inflater.inflate(R.layout.activity_work,container,false);
        viewPager=view.findViewById(R.id.news);
        InitViewPager();
        InitTextView();
        return view;
    }

    private void InitViewPager() {
        LayoutInflater layoutInflater=getActivity().getLayoutInflater();
        viewList=new ArrayList<View>();
        viewList.add(layoutInflater.inflate(R.layout.news1,null));
        viewList.add(layoutInflater.inflate(R.layout.news2,null));
        PagerAdapter pagerAdapter=new PagerAdapter() {
            @Override
            public int getCount() {
                return viewList.size();
            }
            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
                return view==viewList.get((int)Integer.parseInt(object.toString()));
            }
            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(viewList.get(position));
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                container.addView(viewList.get(position));
                return position;
            }
        };
        viewPager.setAdapter(pagerAdapter);
    }
    private void InitTextView(){
        view.findViewById(R.id.notice1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setClass(getActivity(), Notice1.class);
                startActivity(intent);
            }
        });
        view.findViewById(R.id.notice2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setClass(getActivity(), Notice2.class);
                startActivity(intent);
            }
        });
        view.findViewById(R.id.notice3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setClass(getActivity(), Notice3.class);
                startActivity(intent);
            }
        });
        view.findViewById(R.id.notice4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setClass(getActivity(), Notice4.class);
                startActivity(intent);
            }
        });
        view.findViewById(R.id.notice5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setClass(getActivity(), Notice5.class);
                startActivity(intent);
            }
        });

    }

}
