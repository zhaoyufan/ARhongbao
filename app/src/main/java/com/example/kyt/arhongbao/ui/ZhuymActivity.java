package com.example.kyt.arhongbao.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kyt.arhongbao.R;

import org.w3c.dom.Text;

/**
 * Created by kyt on 2017/2/22.
 */

public class ZhuymActivity extends AppCompatActivity {
    private Button chb,shb,zhb;
    private TextView back,title;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        chb= (Button) findViewById(R.id.chb);
        shb= (Button) findViewById(R.id.shb);
        zhb= (Button) findViewById(R.id.zhb);
        back = (TextView) findViewById(R.id.common_title_bar_left);
        title = (TextView) findViewById(R.id.common_title);
        title.setText("主页面");
        chb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(ZhuymActivity.this,CanghbActivity.class);
                startActivity(intent);
            }
        });
        shb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(ZhuymActivity.this,SaohbActivity.class);
                startActivity(intent);
            }
        });
        zhb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(ZhuymActivity.this,ZhaohbActivity.class);
                startActivity(intent);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();
            }
        });
    }
}
