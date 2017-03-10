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

import com.example.kyt.arhongbao.application.App;
import com.example.kyt.arhongbao.model.User;
import com.example.kyt.arhongbao.model.view.LoginListenter;
import com.example.kyt.arhongbao.persenter.UserPersenter;
import com.example.kyt.arhongbao.ui.ZhuymActivity;
import com.example.kyt.arhongbao.util.MyProgressDialog;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements LoginListenter {
    private Button login;
    private TextView register,forgetpass;
    private EditText account,password;
    private Map<String,String> map;
    UserPersenter persenter;
    MyProgressDialog dialog;

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
                map.put("userName",str_account);
                map.put("password",str_password);
                if("".equals(str_account)){
                    Toast.makeText(MainActivity.this,"用户名不能为空!",Toast.LENGTH_SHORT).show();
                    return;
                }
                if("".equals(str_password)){
                    Toast.makeText(MainActivity.this,"密码不能为空!",Toast.LENGTH_SHORT).show();
                    return;
                }
                dialog.show();

                persenter.login(map,MainActivity.this);
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
        persenter = new UserPersenter();
        dialog = new MyProgressDialog(this);
    }

    @Override
    public void success(User user) {
        if(user.getUserName() == null){
            Toast.makeText(this,"用户名/密码错误！",Toast.LENGTH_SHORT).show();
            dialog.dismiss();
            return;
        }
        App.APP_USER = user;
        Log.i("aaa","name="+user.getUserName());
        dialog.dismiss();
        Intent intent=new Intent(MainActivity.this, ZhuymActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void fail(String message) {
        dialog.dismiss();
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
}
