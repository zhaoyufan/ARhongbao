package com.example.kyt.arhongbao.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.example.kyt.arhongbao.R;
import com.example.kyt.arhongbao.util.BaiDuHelp;

import org.w3c.dom.Text;

/**
 * Created by kyt on 2017/2/22.
 */

public class ZhuymActivity extends AppCompatActivity {
    private Button chb,shb,zhb,dingwei;
    private TextView back,title,setting;
    private LocationClient mLocationClient;//定位的核心类
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLocationClient = new LocationClient(getApplicationContext());
        //注册监听事件
        mLocationClient.registerLocationListener(new MyCityLocationListener());
        //设置定位参数
        BaiDuHelp.setLocationOption(mLocationClient);

        dingwei = (Button) findViewById(R.id.dingwei);
        setting = (TextView) findViewById(R.id.setting);
        chb= (Button) findViewById(R.id.chb);
        shb= (Button) findViewById(R.id.shb);
        zhb= (Button) findViewById(R.id.zhb);
        back = (TextView) findViewById(R.id.common_title_bar_left);
        title = (TextView) findViewById(R.id.common_title);
        dingwei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLocationClient.start();
            }
        });
        setting.setVisibility(View.VISIBLE);
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ZhuymActivity.this,"设置",Toast.LENGTH_SHORT).show();
            }
        });
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
    class MyCityLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            String city = location.getAddrStr();
            if(null==city){
                //dingwei.setText("定位失败");
                mLocationClient.stop();
            }
            //dingwei.setText("当前所在地点："+city);
            Log.i("aaa",city);
        }

        @Override
        public void onConnectHotSpotMessage(String s, int i) {

        }
    }
}
