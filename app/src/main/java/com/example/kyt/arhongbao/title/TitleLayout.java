package com.example.kyt.arhongbao.title;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.kyt.arhongbao.R;


/**
 * Created by lalal on 2016/12/19.
 */
public class TitleLayout extends LinearLayout {


    public TitleLayout(Context context, AttributeSet attrs){
        super(context,attrs);
        LayoutInflater.from(context).inflate(R.layout.title,this);
        Button titleshouye = (Button)findViewById(R.id.title_shouye);
        Button titlehoutui = (Button) findViewById(R.id.title_houtui);
        titleshouye.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
//                Uri uri = Uri.parse("http://baike.baidu.com/view/753813.htm");
//                Intent intent = new Intent(Intent.ACTION_VIEW, uri);

                Toast.makeText(getContext(),"You clicked Edit Button",Toast.LENGTH_SHORT).show();
            }
        });
        titlehoutui.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity)getContext()).finish();
            }
        });
    }


}
