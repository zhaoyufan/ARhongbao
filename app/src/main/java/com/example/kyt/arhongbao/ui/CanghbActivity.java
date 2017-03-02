package com.example.kyt.arhongbao.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kyt.arhongbao.R;

/**
 * Created by kyt on 2017/2/22.
 */

public class CanghbActivity extends AppCompatActivity {
    private ImageView imageView;
    private TextView back,title;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canghb);
        back = (TextView) findViewById(R.id.common_title_bar_left);
        title = (TextView) findViewById(R.id.common_title);
        title.setText("藏红包");
        findViewById(R.id.czz).setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return false;
            }
        });
        imageView= (ImageView) findViewById(R.id.imageview);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();
            }
        });
    }

}
