package com.example.carson_ho.toptabbar.view;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.example.carson_ho.toptabbar.R;
import com.example.carson_ho.toptabbar.controller.ResourceGet;

import java.util.ArrayList;
import java.util.List;

public class Fragment2 extends Fragment {
    View view;
    public LocationClient mLocationClient;
    private TextView positionView;
    int[] buttons=new int[15];
    int from;
    int to;
    int i;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment2, container, false);
        ResourceGet resourceGet=new ResourceGet();

        MainActivity mainActivity=(MainActivity) getActivity();
        Toolbar toolbar = (Toolbar)view.findViewById(R.id.toolbar);
        mainActivity.setSupportActionBar(toolbar);

        from=to=0;
        for (i=0;i<buttons.length;i++) {
            buttons[i] = resourceGet.getWidgetID( "b"+(i + 1), getContext());
        }


      for (i=0;i<buttons.length;i++) {
               final Button btn=(Button)view.findViewById(buttons[i]);
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(from!=0)to=i*30*60+9*3600;
                        else from=i*30*60+9*3600;
                        btn.setActivated(true);
                        btn.setText("已选中");
                    }
                });
            }

//        地图按钮,进入新的地图页面
        ImageButton enterMap=(ImageButton)view.findViewById(R.id.mapLocation);
        enterMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(getActivity(),MapActivity.class);
                startActivity(intent1);
            }
        });
//        预订成功,进入订单信息页
        Button okBtn=(Button)view.findViewById(R.id.ok);
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2=new Intent(getActivity(),AppointmentActivity.class);
                intent2.putExtra("roomNum","A01");
                intent2.putExtra("from",from);
                intent2.putExtra("to",to);
                startActivity(intent2);
            }
        });

//        位置定位功能模板
        mLocationClient=new LocationClient(getActivity().getApplicationContext());
        mLocationClient.registerLocationListener(new myLocationListener());
        positionView=(TextView)view.findViewById(R.id.mLocation);
        List<String> permissionList=new ArrayList<>();
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED)
        {permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);}
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_PHONE_STATE)!= PackageManager.PERMISSION_GRANTED)
        {permissionList.add(Manifest.permission.READ_PHONE_STATE);}
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED)
        {permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE  );}
        if (!permissionList.isEmpty()){
            String [] permissions=permissionList.toArray(new String[permissionList.size()]);
            ActivityCompat.requestPermissions(getActivity(),permissions,1);
        }else {
            requestLocation();
        }



        return view;
    }
    private  void requestLocation(){
        initLocation();
        mLocationClient.start();
    }

    private void initLocation(){
        LocationClientOption option=new LocationClientOption();
        option.setScanSpan(5000);
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        option.setIsNeedAddress(true);
        mLocationClient.setLocOption(option);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mLocationClient.stop();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 1:
                if (grantResults.length>0)
                {
                    for (int result:grantResults){
                            if (result!=PackageManager.PERMISSION_GRANTED){
                                Toast.makeText(getActivity(),"必须同意所有权限才能使用本程序",Toast.LENGTH_SHORT).show();
                                getActivity().finish();
                                return;
                            }
                    }
                }else
                {
                    Toast.makeText(getActivity(),"发生未知错误",Toast.LENGTH_SHORT).show();
                    getActivity().finish();
                }

                break;
                default:


        }

    }

    public class myLocationListener implements BDLocationListener{

        public myLocationListener() {
            super();
        }

        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            StringBuilder currentPosition=new StringBuilder();
            currentPosition.append("市:").append(bdLocation.getCity());
            currentPosition.append("区:").append(bdLocation.getDistrict());
            currentPosition.append("街道:").append(bdLocation.getStreet());
            positionView.setText(currentPosition);

        }
    }
}
