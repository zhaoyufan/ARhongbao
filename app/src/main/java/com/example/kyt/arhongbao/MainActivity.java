package com.example.kyt.arhongbao;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.kyt.arhongbao.ui.ZhuymActivity;

public class MainActivity extends AppCompatActivity {
    private Button Login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.denglu);
        super.onCreate(savedInstanceState);
        Login= (Button) findViewById(R.id.login);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, ZhuymActivity.class);
                startActivity(intent);
            }
    });
    }
}
