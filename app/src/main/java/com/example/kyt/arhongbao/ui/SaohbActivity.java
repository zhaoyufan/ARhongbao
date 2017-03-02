package com.example.kyt.arhongbao.ui;

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

public class SaohbActivity extends AppCompatActivity {
    private ImageView imageView;
    private Button shb;
    private TextView back,title;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saohb);
        shb= (Button) findViewById(R.id.shb);
        back = (TextView) findViewById(R.id.common_title_bar_left);
        title = (TextView) findViewById(R.id.common_title);
        title.setText("扫红包");
        shb.setOnLongClickListener(new View.OnLongClickListener() {
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
