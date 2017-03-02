package com.example.kyt.arhongbao.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kyt.arhongbao.R;

/**
 * Created by kyt on 2017/2/22.
 */

public class ZhaohbActivity extends AppCompatActivity {
    private ImageView sousuo;
    private Button cxdw,rw;
    private TextView back,title;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhaohb);
        sousuo= (ImageView) findViewById(R.id.sousuo);
        cxdw= (Button) findViewById(R.id.cxdw);
        rw= (Button) findViewById(R.id.rw);
        back = (TextView) findViewById(R.id.common_title_bar_left);
        title = (TextView) findViewById(R.id.common_title);
        title.setText("找红包");
        rw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  intent=new Intent(ZhaohbActivity.this,RenwuActivity.class);
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
