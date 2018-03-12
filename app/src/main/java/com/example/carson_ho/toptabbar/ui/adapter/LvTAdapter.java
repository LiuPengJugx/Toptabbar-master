package com.example.carson_ho.toptabbar.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.carson_ho.toptabbar.R;
import com.example.carson_ho.toptabbar.bean.New;

import java.util.List;

/**
 * Created by pclx on 2018/3/4.
 */

public class LvTAdapter extends ArrayAdapter<New> {
    private int resourceId;
    public LvTAdapter(Context context, int textViewResourceId, List<New>objects){
        super(context,textViewResourceId,objects);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        New lvt = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
        ImageView lvImage = (ImageView)view.findViewById(R.id.lv_image);
        TextView title = (TextView)view.findViewById(R.id.lv_text);
        TextView author=(TextView)view.findViewById(R.id.author);
        TextView time=(TextView)view.findViewById(R.id.currentTime);
        title.setText(lvt.getTitle());
        author.setText(lvt.getAuthor());
        time.setText(lvt.getTime());
        lvImage.setImageResource(lvt.getImageId());
        return view;
    }
}
