package com.example.carson_ho.toptabbar.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.TextOptions;
import com.baidu.mapapi.model.LatLng;
import com.example.carson_ho.toptabbar.R;
import com.example.carson_ho.toptabbar.ui.activity.AppointmentActivity;


public class MapActivity extends AppCompatActivity {
    MapView mapView;
    private BaiduMap baiduMap;
    public LocationClient mLocationClient;
    private Boolean isFirstLocate =true;
    int from,to,type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        Log.d("map_activity", "onCreate:success ");
        mLocationClient=new LocationClient(getApplicationContext());
        mLocationClient.registerLocationListener(new myLocationListener());
        mapView=(MapView)findViewById(R.id.bmapView);
        baiduMap=mapView.getMap();
        baiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
        requestLocation();
        Intent intent=getIntent();
//        获取到相应数据






        type =intent.getIntExtra("type",1);
        from=intent.getIntExtra("from",1);
        to=intent.getIntExtra("to",1);
//        通过这些参数筛选房间,并生成标记，记得写点击事件响应，调用generateOrders方法



    }
    public void generateOrders(String roomNum){
        Intent intent1=new Intent(this,AppointmentActivity.class);
        intent1.putExtra("roomNum",roomNum);
        intent1.putExtra("type",type);
        intent1.putExtra("from",from);
        intent1.putExtra("to",to);
        startActivity(intent1);
    }

    public void paint()
    {
        //添加标记
        LatLng point = new LatLng(39.963175, 116.400244);

//构建Marker图标

       /* BitmapDescriptor bitmap = BitmapDescriptorFactory
                .fromResource(R.drawable.icon_marka);*/

//构建MarkerOption，用于在地图上添加Marker

        OverlayOptions textOption = new TextOptions()
                .bgColor(0xAAFFFF00)
                .fontSize(24)
                .fontColor(0xFFFF00FF)
                .text("百度地图SDK")
                .rotate(-30)
                .position(point);

//在地图上添加Marker，并显示

        baiduMap.addOverlay(textOption);
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
            Log.e("mapActivity", bdLocation.getLatitude()+","+bdLocation.getLongitude()+"");

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
