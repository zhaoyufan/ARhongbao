package com.example.kyt.arhongbao;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.squareup.okhttp.Request;

import java.util.HashMap;
import java.util.Map;

public class ResgisterActivity extends AppCompatActivity {
    private EditText et_account,et_pass;
    private Button btn_next;
    private CheckBox checkBox;
    private Map<String,String> map;

    private boolean ischeck=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resgister);
        initView();
        setListener();
    }

    private void setListener() {
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check();
                String str_account=et_account.getText().toString();
                String str_pass=et_pass.getText().toString();
                map=new HashMap<String, String>();
                map.put("login",str_account);
                map.put("password",str_pass);
                if(ischeck){
                    Intent intent=new Intent(ResgisterActivity.this,MsgforbusinessRegisterAcitivity.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    OkHttpClientManager.postAsyn("", new OkHttpClientManager.ResultCallback() {
                        @Override
                        public void onError(Request request, Exception e) {

                        }

                        @Override
                        public void onResponse(Object response) {

                        }
                    },map);
                }

            }
        });
    }

    private void check() {
        if (checkBox.isChecked())//被选中则应该是商户
        {
            ischeck=true;
        }
        ischeck=false;
    }

    private void initView() {
        et_account= (EditText) findViewById(R.id.register_account);
        et_pass= (EditText) findViewById(R.id.resgiter_pass);
        btn_next= (Button) findViewById(R.id.register_nextstep);
        checkBox= (CheckBox) findViewById(R.id.register_check);
    }

}
