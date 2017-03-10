package com.example.kyt.arhongbao.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.poi.PoiNearbySearchOption;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.baidu.mapapi.search.poi.PoiSortType;
import com.baidu.mapapi.utils.CoordinateConverter;
import com.example.kyt.arhongbao.R;

public class NearInfoActivity extends AppCompatActivity {
    public LocationClient mLocationClient = null;
    private BDLocation lastLocation;
    private double mCurrentLantitude,mCurrentLongitude;
    public BDLocationListener myListener = new MyLocationListener();
    PoiSearch mPoiSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_near_info);
        mPoiSearch = PoiSearch.newInstance();
    }
    /**
     * 搜索周边地理位置
     */
    private void searchNeayBy() {
        PoiNearbySearchOption option = new PoiNearbySearchOption();
        option.keyword("写字楼");
        option.sortType(PoiSortType.distance_from_near_to_far);
        option.location(new LatLng(mCurrentLantitude, mCurrentLongitude));
//        if (radius != 0) {
//            option.radius(radius);
//        } else {
//            option.radius(1000);
//        }

        option.pageCapacity(20);
        mPoiSearch.searchNearby(option);

    }

/*
* 接受周边地理位置结果
* @param poiResult
*/
public class MyLocationListener implements BDLocationListener {
    @Override
    public void onReceiveLocation(BDLocation location) {
        if (location == null) {
            return;
        }
        Log.d("map", "On location change received:" + location);
        Log.d("map", "addr:" + location.getAddrStr());

        if (lastLocation != null) {
            if (lastLocation.getLatitude() == location.getLatitude() && lastLocation.getLongitude() == location.getLongitude()) {
                Log.d("map", "same location, skip refresh");
                // mMapView.refresh(); //need this refresh?
                return;
            }
        }
        lastLocation = location;
        mCurrentLantitude = lastLocation.getLatitude();
        mCurrentLongitude = lastLocation.getLongitude();
        Log.e(">>>>>>>", mCurrentLantitude + "," + mCurrentLongitude);
        LatLng llA = new LatLng(lastLocation.getLatitude(), lastLocation.getLongitude());
        CoordinateConverter converter = new CoordinateConverter();
        converter.coord(llA);
        converter.from(CoordinateConverter.CoordType.COMMON);
        LatLng convertLatLng = converter.convert();
//        OverlayOptions ooA = new MarkerOptions().position(convertLatLng).icon(BitmapDescriptorFactory
//                .fromResource(R.drawable.icon_marka))
//                .zIndex(4).draggable(true);
//        mCurrentMarker = (Marker) mBaiduMap.addOverlay(ooA);
//        MapStatusUpdate u = MapStatusUpdateFactory.newLatLngZoom(convertLatLng, 16.0f);
//        mBaiduMap.animateMapStatus(u);
        new Thread(new Runnable() {
            @Override
            public void run() {
                searchNeayBy();
            }
        }).start();
    }

    @Override
    public void onConnectHotSpotMessage(String s, int i) {

    }

    public void onReceivePoi(BDLocation poiLocation) {
        if (poiLocation == null) {
            return;
        }
    }

}


}
