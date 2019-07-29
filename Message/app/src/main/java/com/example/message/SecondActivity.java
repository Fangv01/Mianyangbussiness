package com.example.message;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SecondActivity extends Activity {
    public TextView tv_user;
    public Button bt_back;
    public static String username;
    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener=new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            fragmentManager = getFragmentManager();
            transaction=fragmentManager.beginTransaction();
            switch (menuItem.getItemId()){
                case R.id.nv_message:
                    transaction.replace(R.id.content,new Fragment1());
                    transaction.commit();
                    return true;
                case R.id.nv_address:
                    transaction.replace(R.id.content,new Fragment2());
                    transaction.commit();
                    return true;
                case R.id.nv_work:
                    transaction.replace(R.id.content,new Fragment3());
                    transaction.commit();
                    return true;
                case R.id.nv_calendar:
                    transaction.replace(R.id.content,new Fragment4());
                    transaction.commit();
                    return true;
                case R.id.nv_my:
                    transaction.replace(R.id.content,new Fragment5());
                    transaction.commit();
                    return true;

            }
            return false;
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        tv_user=findViewById(R.id.user);
        bt_back=findViewById(R.id.back);
        bt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=getIntent();
                setResult(100,intent);
                SecondActivity.this.finish();
            }
        });
        Intent intent = getIntent();
        username=intent.getStringExtra("user");
        tv_user.setText(username);
        setDefaultFragment();
        BottomNavigationView navigationView = findViewById(R.id.navigation);
        navigationView.setSelectedItemId(R.id.nv_work);
        navigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);
    }
    @Override
    public void onStart(){
        super.onStart();
    }
    private FragmentTransaction transaction;
    private FragmentManager fragmentManager;
    private void setDefaultFragment(){
        fragmentManager=getFragmentManager();
        transaction=fragmentManager.beginTransaction();
        transaction.replace(R.id.content,new Fragment3());
        transaction.commit();
    }





}
