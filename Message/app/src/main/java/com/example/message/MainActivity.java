package com.example.message;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText et_username;
    private EditText et_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_username =findViewById(R.id.login_name);
        et_password=findViewById(R.id.login_password);
        findViewById(R.id.login).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String username = et_username.getText().toString();
                String password = et_password.getText().toString();
                Intent intent = new Intent(MainActivity.this,SecondActivity.class);
                intent.putExtra("user",username);
                intent.putExtra("pwd",password);
                startActivity(intent);
            }
        });
        findViewById(R.id.new_user).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this,New_User.class);
                startActivity(intent);
            }
        });
    }



    //message


}
