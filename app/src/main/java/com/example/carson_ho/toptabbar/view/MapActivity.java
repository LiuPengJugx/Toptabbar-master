package com.example.carson_ho.toptabbar.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.example.carson_ho.toptabbar.R;


public class MapActivity extends AppCompatActivity {
    MapView mapView;
    private BaiduMap baiduMap;
    public LocationClient mLocationClient;
    private Boolean isFirstLocate =true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        Log.d("map_activity", "onCreate:success ");
        mLocationClient=new LocationClient(getApplicationContext());
        mLocationClient.registerLocationListener(new myLocationListener());
        SDKInitializer.initialize(getApplicationContext());
        mapView=(MapView)findViewById(R.id.bmapView);
        baiduMap=mapView.getMap();
        requestLocation();
    }
    private  void requestLocation(){
        initLocation();
        mLocationClient.start();
    }
    private void initLocation(){
        LocationClientOption option=new LocationClientOption();
        option.setScanSpan(5000);
        option.setIsNeedAddress(true);
        mLocationClient.setLocOption(option);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLocationClient.stop();
        mapView.onDestroy();
        baiduMap.setMyLocationEnabled(false);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }
    private void navigateTo(BDLocation bdLocation)
    {
        if(isFirstLocate){
            LatLng ll=new LatLng(bdLocation.getLatitude(),bdLocation.getLongitude());
            MapStatusUpdate update= MapStatusUpdateFactory.newLatLng(ll);
            baiduMap.animateMapStatus(update);
            update=MapStatusUpdateFactory.zoomTo(16f);
            baiduMap.animateMapStatus(update);
            isFirstLocate=false;
        }else{
            MyLocationData.Builder locationBuilder=new MyLocationData.Builder();
            locationBuilder.latitude(bdLocation.getLatitude());
            locationBuilder.longitude(bdLocation.getLongitude());
            MyLocationData myLocationData=locationBuilder.build();
            baiduMap.setMyLocationData(myLocationData);
        }
    }
    public class myLocationListener implements BDLocationListener {
        public myLocationListener() {
            super();
        }
        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
          if(bdLocation.getLocType()==bdLocation.TypeGpsLocation||bdLocation.getLocType()==bdLocation.TypeNetWorkLocation){
              navigateTo(bdLocation);
          }
        }
    }
}
