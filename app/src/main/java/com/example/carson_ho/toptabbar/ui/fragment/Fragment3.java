package com.example.carson_ho.toptabbar.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.carson_ho.toptabbar.bean.Room;
import com.example.carson_ho.toptabbar.ui.adapter.OrderAdapter;
import com.example.carson_ho.toptabbar.R;
import com.example.carson_ho.toptabbar.bean.Orders;
import com.example.carson_ho.toptabbar.ui.activity.MainActivity;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

public class Fragment3 extends Fragment {
    private List<Orders> ordersList =new ArrayList<Orders>();

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
//        ordersList= DataSupport.findAll(Orders.class);
        ordersList=initOrders();
        RecyclerView recyclerView=(RecyclerView)view.findViewById(R.id.ordersList);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        OrderAdapter orderAdapter=new OrderAdapter(ordersList);
        recyclerView.setAdapter(orderAdapter);
        return view;
    }
    private List<Orders> initOrders(){
        List<Orders> orderList=new ArrayList<Orders>();
        Orders orders1=new Orders("A01","2220160703",R.drawable.room_a01_1,null,null,80f,(byte) 1,(byte) 1);
        orderList.add(orders1);
        Orders order2=new Orders("A02","2220160703",R.drawable.room_a02_1,null,null,68f,(byte) 1,(byte) 1);
        orderList.add(order2);
        Orders order3=new Orders("A03","2220160703",R.drawable.room_a03_1,null,null,102f,(byte) 1,(byte) 1);
        orderList.add(order3);
        return orderList;
    }


}
