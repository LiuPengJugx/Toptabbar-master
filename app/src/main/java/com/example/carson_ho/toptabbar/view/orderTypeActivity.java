package com.example.carson_ho.toptabbar.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.carson_ho.toptabbar.controller.OrderAdapter;
import com.example.carson_ho.toptabbar.R;
import com.example.carson_ho.toptabbar.bean.Order;
import com.example.carson_ho.toptabbar.bean.Room;

import org.litepal.crud.DataSupport;

import java.util.List;

public class orderTypeActivity extends AppCompatActivity {
List<Order> orderList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int roomType=0;
        setContentView(R.layout.activity_order_type);
//        根据房间类型查找房间号，根据房间号查询
        List<Room> roomList= DataSupport.select("roomNum").where("type= ?", roomType+"").find(Room.class);
        for (Room room:roomList) {
            String roomNum=room.getRoomNum();
            List<Order> orders=DataSupport.where("roomNum=?",roomNum).find(Order.class);
            for (Order order:orders) {
                orderList.add(order);
            }
        }
    RecyclerView recyclerView=(RecyclerView)findViewById(R.id.orderTypeList);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        OrderAdapter orderAdapter=new OrderAdapter(orderList);
        recyclerView.setAdapter(orderAdapter);









    }
}
