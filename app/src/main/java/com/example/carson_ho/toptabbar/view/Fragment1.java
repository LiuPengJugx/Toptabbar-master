package com.example.carson_ho.toptabbar.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.example.carson_ho.toptabbar.controller.ListviewT;
import com.example.carson_ho.toptabbar.controller.LocalImageHolderView;
import com.example.carson_ho.toptabbar.controller.LvTAdapter;
import com.example.carson_ho.toptabbar.R;
import com.example.carson_ho.toptabbar.controller.ResourceGet;


import java.util.ArrayList;
import java.util.List;

public class Fragment1 extends Fragment{
    MainActivity mainActivity;
    private Button login_b;
    private ListView listView;
    private LvTAdapter adapter;
    private List<ListviewT> dataList = new ArrayList<>();
    private ArrayList<Integer> localImages = new ArrayList<Integer>();
    View view;
    public static final String BASE_URL = "http://news-at.zhihu.com/";
    ConvenientBanner convenientBanner;
    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment1,container,false);
        mainActivity = (MainActivity)getActivity();
        login_b = (Button)  view.findViewById(R.id.login_button);
//              轮播图的设置
        convenientBanner=(ConvenientBanner)view.findViewById(R.id.convenientBanner);
        //获取本地的图片
        for (int position = 1; position <=4; position++) {
            localImages.add(new ResourceGet().getImageID("room_"+position,getContext()));
        }
        convenientBanner.setPages(new CBViewHolderCreator() {
            @Override
            public Object createHolder() {
                return new LocalImageHolderView();
            }
        },localImages)
                //设置指示器是否可见
                .setPointViewVisible(true)
                //设置自动切换（同时设置了切换时间间隔）
                .startTurning(2000)
                //设置指示器的方向（左、中、右）
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT)
                //设置手动影响（设置了该项无法手动切换）
                .setManualPageable(true);


        listView = (ListView) view.findViewById(R.id.list_view);
        initText();
        adapter = new LvTAdapter(getContext(), R.layout.lv_item,dataList);
        listView.setAdapter(adapter);


        login_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mainActivity,Login.class);
                startActivity(intent);
            }
        });



        return view;
    }
    private void initText(){
        //ListView的素材
        for(int i=0;i<3;i++)
        {
            ListviewT lv1 = new ListviewT("一个人的成就不在于智商，而是在于自制力", R.drawable.sucai);
            dataList.add(lv1);
        }

    }



}
