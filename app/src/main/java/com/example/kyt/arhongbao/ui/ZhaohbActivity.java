package com.example.kyt.arhongbao.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.UiSettings;
import com.baidu.mapapi.model.LatLng;
import com.example.kyt.arhongbao.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kyt on 2017/2/22.
 */

public class ZhaohbActivity extends AppCompatActivity {
    private ImageView sousuo;
    private Button cxdw, rw;
    private TextView back, title;

    //以下是地图用到的
    private MapView mapView;
    private BaiduMap mBaiduMap;
    boolean isFirstLoc = true;// 是否首次定位
    public LocationClient locationClient = null;
    public MapStatus mapStatus;
    public MapStatusUpdate msu;
    public MapStatusUpdateFactory msuFactory = null;
    double latitude, longitude;
    private MarkerOptions options;
    private BitmapDescriptor bitmap, bitmap2;
    private Marker marker1;//自己标记
    private LatLng point;
    private LatLng point1;
    private List<LatLng> points = new ArrayList<LatLng>();
    private LinearLayout hongbaoinfo_window;
    private InfoWindow mInfowIndow;
    private MarkerOnInfoWindowClickListener markerListener;
    private TextView distence;
    private View view;

    private final class MarkerOnInfoWindowClickListener implements InfoWindow.OnInfoWindowClickListener {

        @Override
        public void onInfoWindowClick() {
            //隐藏InfoWindow
            mBaiduMap.hideInfoWindow();
        }

    }

    public BDLocationListener myListener = new BDLocationListener() {

        @Override
        public void onReceiveLocation(BDLocation location) {
            if (location == null || mapView == null)
                return;
//            MyLocationData locationData = new MyLocationData.Builder()
//                    .accuracy(100)
//                    .direction(100)
//                    .latitude(location.getLatitude())
//                    .longitude(location.getLongitude())
//                    .build();
//            latitude = location.getLatitude();
//            longitude = location.getLatitude();
            //29.917063   29.916063  29.918055
            //121.521415  121.520415 121.522315
            //29.892273   121.486969
            point = new LatLng(29.891273, 121.485969);
            points.add(point);
            point = new LatLng(29.893273, 121.487969);
            points.add(point);

            Log.i("lat", "a" + location.getLatitude());
            Log.i("log", "a" + location.getLongitude());
//            mBaiduMap.setMyLocationData(locationData);    //设置定位数据
            if (isFirstLoc) {
                isFirstLoc = false;
                point1 = new LatLng(location.getLatitude(), location.getLongitude());
                Toast.makeText(ZhaohbActivity.this, point1.toString(), Toast.LENGTH_SHORT).show();
                options = new MarkerOptions()
                        .position(point1)
                        .icon(bitmap)
                        .zIndex(9)
                        .title("我的位置");
                marker1 = (Marker) mBaiduMap.addOverlay(options);
                for (int i = 0; i < points.size(); i++) {
                    double a = points.get(i).latitude;
                    Log.i("aaaa", a + "a");
                    options = new MarkerOptions()
                            .position(points.get(i))
                            .icon(bitmap2)
                            .zIndex(9)
                            .title("红包");
                    marker1 = (Marker) mBaiduMap.addOverlay(options);
                }
                MapStatusUpdate u = MapStatusUpdateFactory.newLatLngZoom(point1, 19);    //设置地图中心点以及缩放级别
                mBaiduMap.animateMapStatus(u);
            }
        }

        @Override
        public void onConnectHotSpotMessage(String s, int i) {

        }
    };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        MapView.setMapCustomEnable(true);
        MapView.setCustomMapStylePath("/sdcard/mystyle");
        setContentView(R.layout.activity_zhaohb);
        init();
        mapSetting();
        uiSetting();
        locationClient = new LocationClient(getApplicationContext()); // 实例化LocationClient类
        locationClient.registerLocationListener(myListener); // 注册监听函数
        this.setLocationOption();    //设置定位参数
        locationClient.start(); // 开始定位
        clickListener();
    }

    private void mapSetting() {
        mapView.getMap().showMapPoi(false);
        mapView.showScaleControl(false);
        mapView.showZoomControls(false);//不显示地图缩放控件（按钮控制栏）
        mapView.removeViewAt(1);
        mBaiduMap = mapView.getMap();
        mapStatus = new MapStatus.Builder(mBaiduMap.getMapStatus()).overlook(-55).build();//设置地图3D效果
        msu = msuFactory.newMapStatus(mapStatus);
        mBaiduMap.setMyLocationEnabled(true);
        mBaiduMap.setTrafficEnabled(false);
        mBaiduMap.animateMapStatus(msu);
    }

    //初始化各种布局
    private void init() {
        sousuo = (ImageView) findViewById(R.id.sousuo);
        cxdw = (Button) findViewById(R.id.cxdw);
        rw = (Button) findViewById(R.id.rw);
        back = (TextView) findViewById(R.id.common_title_bar_left);
        title = (TextView) findViewById(R.id.common_title);
        mapView = (MapView) findViewById(R.id.bmapView);
        bitmap = BitmapDescriptorFactory.fromResource(R.drawable.userlocation);
        bitmap2 = BitmapDescriptorFactory.fromResource(R.drawable.packagelocation);
        title.setText("找红包");
        view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.hongbaoinfo_window, null);
        distence = (TextView) view.findViewById(R.id.distence);
    }

    private void clickListener() {
        rw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ZhaohbActivity.this, RenwuActivity.class);
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
        mBaiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                LatLng ll = marker.getPosition();
                Log.i("lanlat", ll.toString() + "a");
                Log.i("lanlat", points.get(0) + "b" + points.size());
                for (int i = 0; i < points.size(); i++) {
                    if (ll == points.get(i)) {
                        distence.setText("111");
                        Toast.makeText(ZhaohbActivity.this, "marker+" + i + "被点击了", Toast.LENGTH_SHORT).show();

                        mInfowIndow = new InfoWindow(view, ll, -47);
                        view.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                mBaiduMap.hideInfoWindow();
                            }
                        });
                        mBaiduMap.showInfoWindow(mInfowIndow);
                    }
                }
                return false;
            }
        });
    }

    //百度地图UI设置
    private void uiSetting() {
        UiSettings uiSettings = mBaiduMap.getUiSettings();
        uiSettings.setRotateGesturesEnabled(true);//设置是否允许旋转手势
        uiSettings.setOverlookingGesturesEnabled(false);
        uiSettings.isOverlookingGesturesEnabled();
        uiSettings.setZoomGesturesEnabled(false);//设置缩放手势状态
        uiSettings.setScrollGesturesEnabled(false);
        uiSettings.setCompassEnabled(false);

    }

    /**
     * 设置定位参数
     */
    private void setLocationOption() {
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 打开GPS
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);// 设置定位模式
        option.setCoorType("bd09ll"); // 返回的定位结果是百度经纬度,默认值gcj02
        option.setScanSpan(1000); // 设置发起定位请求的间隔时间为1000ms
        option.setIsNeedAddress(true); // 返回的定位结果包含地址信息
        option.setNeedDeviceDirect(true); // 返回的定位结果包含手机机头的方向
        locationClient.setLocOption(option);
    }
}
