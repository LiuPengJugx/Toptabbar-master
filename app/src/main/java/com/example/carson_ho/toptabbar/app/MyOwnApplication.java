package com.example.carson_ho.toptabbar.app;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;

import org.litepal.LitePal;


/**
 * Created by Test on 2018/3/7.
 */

public class MyOwnApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SDKInitializer.initialize(getApplicationContext());
        LitePal.initialize(this);
    }
}
