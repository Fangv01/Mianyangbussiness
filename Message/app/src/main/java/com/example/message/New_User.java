package com.example.message;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class New_User extends Activity {
    private EditText name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newuser);
        name=findViewById(R.id.newlogin_name);
        findViewById(R.id.ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(New_User.this,MainActivity.class);
                intent.putExtra("username",name.getText());
                startActivity(intent);
            }
        });
    }
}
