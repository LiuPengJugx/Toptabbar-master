package com.example.carson_ho.toptabbar.utils;

import android.content.Context;


public class ResourceGet {
    public int getImageID(String name, Context mContext) {
        int id = mContext.getResources().getIdentifier(name, "drawable",
                mContext.getPackageName());
        return id;
    }
    public int getWidgetID(String name, Context mContext) {
        int id = mContext.getResources().getIdentifier(name, "id",
                mContext.getPackageName());
        return id;
    }

}
