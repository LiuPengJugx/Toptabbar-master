package com.example.carson_ho.toptabbar.ui.fragment;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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
import com.example.carson_ho.toptabbar.ui.activity.RoomSelectedActivity;
import com.example.carson_ho.toptabbar.utils.ResourceGet;
import com.example.carson_ho.toptabbar.ui.activity.MainActivity;
import com.example.carson_ho.toptabbar.ui.activity.MapActivity;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Fragment2 extends Fragment {
    View view;
    public LocationClient mLocationClient;
    private ImageButton oneBtn;
    private ImageButton twoBtn;
    private ImageButton threeBtn;
    private TextView positionView;
    int[] buttons=new int[15];
    int type;
    String from;
    String to;
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
        oneBtn=(ImageButton) view.findViewById(R.id.single_room);
        twoBtn=(ImageButton) view.findViewById(R.id.two_room);
        threeBtn=(ImageButton) view.findViewById(R.id.three_room);

//        确定用户房间类型
        oneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type=1;
                oneBtn.setBackgroundColor(Color.parseColor("#ff33b5e5"));
                twoBtn.setBackgroundColor(Color.parseColor("#FFFFFF"));
                threeBtn.setBackgroundColor(Color.parseColor("#FFFFFF"));
            }
        });
        twoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type=2;
                oneBtn.setBackgroundColor(Color.parseColor("#FFFFFF"));
                twoBtn.setBackgroundColor(Color.parseColor("#ff33b5e5"));
                threeBtn.setBackgroundColor(Color.parseColor("#FFFFFF"));
            }
        });
        threeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type=3;
                oneBtn.setBackgroundColor(Color.parseColor("#FFFFFF"));
                twoBtn.setBackgroundColor(Color.parseColor("#FFFFFF"));
                threeBtn.setBackgroundColor(Color.parseColor("#ff33b5e5"));
            }
        });


        for (i=0;i<buttons.length;i++) {
            buttons[i] = resourceGet.getWidgetID( "b"+(i + 1), getContext());
        }

        long dayTime = System.currentTimeMillis();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd ");
        Date date=new Date(dayTime);
        final String beginTime=simpleDateFormat.format(date);
      for (i=0;i<buttons.length;i++) {
               final Button btn=(Button)view.findViewById(buttons[i]);
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(from!=null)to=btn.getText()+"";
                        else from=beginTime+btn.getText();
                        Log.e("Fragment2", "from="+from+";to="+to);
                        btn.setActivated(true);
                        btn.setText("已选中");
                    }
                });
            }

//        预订成功,进入订单信息页
        Button okBtn=(Button)view.findViewById(R.id.ok);
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2=new Intent(getActivity(),RoomSelectedActivity.class);
                intent2.putExtra("type",type);
                intent2.putExtra("from",from);
                Log.e("Fragment2", "!!!!!from="+from+";to="+to);
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
