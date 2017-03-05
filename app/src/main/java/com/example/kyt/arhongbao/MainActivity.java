package com.example.kyt.arhongbao;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kyt.arhongbao.ui.ZhuymActivity;
import com.google.gson.JsonObject;
import com.squareup.okhttp.Request;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private Button login;
    private TextView register,forgetpass;
    private EditText account,password;
    private Map<String,String> map;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.denglu);
        super.onCreate(savedInstanceState);
        initView();
        setListener();
    }



    private void setListener() {
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str_account=account.getText().toString();
                String str_password=password.getText().toString();
                map=new HashMap<String, String>();
                map.put("phone",str_account);
                map.put("password",str_password);
//                OkHttpClientManager.postAsyn("http://maneng.uicp.io/api/users/", new OkHttpClientManager.ResultCallback() {
//                    @Override
//                    public void onError(Request request, Exception e) {
//
//                    }
//
//                    @Override
//                    public void onResponse(Object response) {
//
//                    }
//                },map);
                Intent intent=new Intent(MainActivity.this, ZhuymActivity.class);
                startActivity(intent);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,ResgisterActivity.class);
                startActivity(intent);
                finish();
            }
        });


        forgetpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,ForgetpassActivity.class);
                startActivity(intent);
            }
        });



    }

    private void initView() {
        login= (Button) findViewById(R.id.login);
        register= (TextView) findViewById(R.id.register);
        forgetpass= (TextView) findViewById(R.id.forgetpass);
        account= (EditText) findViewById(R.id.account);
        password= (EditText) findViewById(R.id.password);
    }
}
