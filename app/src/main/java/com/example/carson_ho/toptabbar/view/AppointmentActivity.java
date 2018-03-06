package com.example.carson_ho.toptabbar.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.carson_ho.toptabbar.R;
import com.example.carson_ho.toptabbar.bean.Order;
import com.example.carson_ho.toptabbar.bean.Room;

import org.litepal.crud.DataSupport;

public class AppointmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);
        //以下变量从上一活动中传入；
        Intent intent=getIntent();
        final String roomNum=intent.getStringExtra("roomNum");
        final int from=intent.getIntExtra("from",1);
        final int to=intent.getIntExtra("to",1);
        Log.e("Appointment", "roomNum: "+roomNum );
        final Room room= DataSupport.where("roomNum=?",roomNum).findFirst(Room.class);
        final float cost=room.getPrice();
        final int roomImage=room.getPhoto();
        SharedPreferences preferences=getSharedPreferences("data",MODE_PRIVATE);
        final String userNo=preferences.getString("userNo","");
        Button okBtn=(Button)findViewById(R.id.ok);
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                创建订单
                Order order=new Order();
                order.setRoomNum(roomNum);
                order.setUserNo(userNo);
                order.setRoomImage(roomImage);
                order.setCost(cost);
                order.setFrom(from);
                order.setTo(to);
                order.save();
            }
        });



    }
}
