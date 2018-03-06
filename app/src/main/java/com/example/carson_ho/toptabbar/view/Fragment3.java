package com.example.carson_ho.toptabbar.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.carson_ho.toptabbar.controller.OrderAdapter;
import com.example.carson_ho.toptabbar.R;
import com.example.carson_ho.toptabbar.bean.Order;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

public class Fragment3 extends Fragment {
    private List<Order> orderList=new ArrayList<Order>();

    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment3, container, false);

        MainActivity mainActivity=(MainActivity) getActivity();
        Toolbar toolbar = (Toolbar)view.findViewById(R.id.toolbar);
        mainActivity.setSupportActionBar(toolbar);

//        从数据库中取出房间信息，按照时间排列，取出前3个。
//        orderList=DataSupport.order("from desc").limit(3).offset(0).find(Order.class);//表中第1条、2条、3条
        Order order1=new Order("A01","2220160703",R.drawable.room_1,4324,4424,80f,(byte) 1,(byte) 1);
        orderList.add(order1);
        Order order2=new Order("A02","2220160703",R.drawable.room_2,4324,4424,68f,(byte) 1,(byte) 1);
        orderList.add(order2);
        Order order3=new Order("A03","2220160703",R.drawable.room_3,4324,4424,102f,(byte) 1,(byte) 1);
        orderList.add(order3);
        RecyclerView recyclerView=(RecyclerView)view.findViewById(R.id.orderList);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        OrderAdapter orderAdapter=new OrderAdapter(orderList);
        recyclerView.setAdapter(orderAdapter);
        return view;
    }



}
