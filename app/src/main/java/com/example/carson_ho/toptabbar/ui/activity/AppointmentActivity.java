package com.example.carson_ho.toptabbar.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.carson_ho.toptabbar.R;
import com.example.carson_ho.toptabbar.bean.Orders;
import com.example.carson_ho.toptabbar.bean.Room;
import com.example.carson_ho.toptabbar.bean.User;
import com.example.carson_ho.toptabbar.ui.fragment.Fragment3;

import org.litepal.crud.DataSupport;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AppointmentActivity extends AppCompatActivity {
    TextView order_time;
    TextView order_user;
    TextView order_phone;
    TextView order_cost;
    ImageView order_room_image;
     String roomNum;
     String userNo;
     String userName;
     String phone;
     String from;
     String to;
     long tfrom;
     long tto;
     float cost;
     int roomImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        SharedPreferences preferences=getSharedPreferences("userData",MODE_PRIVATE);
        userNo=preferences.getString("userNo","2220160703");
        userName=preferences.getString("name","lpj");
        phone=preferences.getString("phone","18742520180");

        //以下变量从上一活动中传入；roomNum to from
        Intent intent=getIntent();
        roomNum=intent.getStringExtra("roomNum");
        from=intent.getStringExtra("from");
        to=intent.getStringExtra("to");
        Log.e("activity_appointment", "!!!!!from="+from+";to="+to);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = null;
        try {
            date = simpleDateFormat.parse(from);
            tfrom = date.getTime();
            date=simpleDateFormat.parse(to);
            tto=date.getTime();

        } catch (ParseException e) {
            e.printStackTrace();
        }


        cost=intent.getIntExtra("cost",1)*(tto-tfrom)/3600;
        roomImage=intent.getIntExtra("roomImage",1);
//        Log.e("Appointment", "roomNum: "+roomNum );
//        根据号码生成订单信息；
//        Room room= DataSupport.where("roomNum=?",roomNum).findFirst(Room.class);
//        User user=DataSupport.where("no=?",userNo).findFirst(User.class);
//        填充数据
        order_time=(TextView)findViewById(R.id.order_time);
        order_user=(TextView)findViewById(R.id.order_user);
        order_phone=(TextView)findViewById(R.id.order_phone);
        order_room_image=(ImageView)findViewById(R.id.order_room_image);
        order_cost=(TextView)findViewById(R.id.cost);
        order_cost.setText(cost+"");
        order_time.setText(from+"-"+to);
        order_user.setText(userName);
        order_phone.setText(phone);
        order_room_image.setImageResource(roomImage);
        Intent intent1=new Intent();

        Button okBtn=(Button)findViewById(R.id.ok);
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                创建订单
                Orders order=new Orders();
                order.setRoomNum(roomNum);
                order.setUserNo(userNo);
                order.setRoomImage(roomImage);
                order.setCost(cost);
                order.setFrom(from);
                order.setTo(to);
                order.save();
                Toast.makeText(AppointmentActivity.this, "订单创建成功！", Toast.LENGTH_SHORT).show();
                Intent intent1=new Intent(getBaseContext(),MainActivity.class);

                startActivity(intent1);
            }
        });



    }
}
