package com.example.carson_ho.toptabbar.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.carson_ho.toptabbar.R;

import java.util.List;

/**
 * Created by pclx on 2018/3/4.
 */

public class LvTAdapter extends ArrayAdapter<ListviewT> {
    private int resourceId;
    public LvTAdapter(Context context, int textViewResourceId, List<ListviewT>objects){
        super(context,textViewResourceId,objects);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        ListviewT lvt = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
        ImageView lvImage = (ImageView)view.findViewById(R.id.lv_image);
        TextView lvText = (TextView)view.findViewById(R.id.lv_text);
        lvImage.setImageResource(lvt.getImageId());
        lvText.setText(lvt.getText());
        return view;
    }
}
