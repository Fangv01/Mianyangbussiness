package com.example.message;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
        setFragment(3);
    }

    @Override
    public void onResume(){/*
        //calendar list
        int id = getIntent().getIntExtra("id",4);
        if(id == 4){
            Intent intent = getIntent();
            String event = intent.getStringExtra("content");
            Fragment4 fragment4 = new Fragment4();
            Bundle bundle = new Bundle();
            bundle.putString("str",event);
            fragment4.setArguments(bundle);

            getFragmentManager().beginTransaction()
                    .replace(R.id.content,fragment4)
                    .addToBackStack(null)
                    .commit();
        }*/
        super.onResume();
    }
    private long exitTime = 0;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK&& event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
                } else finish();
                return true;
            }
        return super.onKeyDown(keyCode, event);
     }
    private FragmentTransaction transaction;
    private FragmentManager fragmentManager;
    public void setFragment(int i ){
        fragmentManager=getFragmentManager();
        transaction=fragmentManager.beginTransaction();
        BottomNavigationView navigationView = findViewById(R.id.navigation);
        switch (i){
            case 1:transaction.replace(R.id.content,new Fragment1());navigationView.setSelectedItemId(R.id.nv_message);break;
            case 2:transaction.replace(R.id.content,new Fragment2());navigationView.setSelectedItemId(R.id.nv_address);break;
            case 3:transaction.replace(R.id.content,new Fragment3());navigationView.setSelectedItemId(R.id.nv_work);break;
            case 4:transaction.replace(R.id.content,new Fragment4());navigationView.setSelectedItemId(R.id.nv_calendar);break;
            case 5:transaction.replace(R.id.content,new Fragment5());navigationView.setSelectedItemId(R.id.nv_my);break;
            default:break;
        }
        transaction.commit();
        navigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);
    }





}
