package com.example.message;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
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
        setDefaultFragment();
        BottomNavigationView navigationView = findViewById(R.id.navigation);
        navigationView.setSelectedItemId(R.id.nv_work);
        navigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);
    }

    @Override
    public void onStart(){
        super.onStart();
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
    private void setDefaultFragment(){
        fragmentManager=getFragmentManager();
        transaction=fragmentManager.beginTransaction();
        transaction.replace(R.id.content,new Fragment3());
        transaction.commit();
    }





}
