package com.example.carson_ho.toptabbar.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.example.carson_ho.toptabbar.R;
import com.example.carson_ho.toptabbar.bean.Room;
import com.example.carson_ho.toptabbar.ui.adapter.OrderAdapter;
import com.example.carson_ho.toptabbar.ui.adapter.RoomAdapter;

import org.litepal.crud.DataSupport;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class RoomSelectedActivity extends AppCompatActivity {
    List<Room> roomList;
    int type;
    String from;
    String to;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_selected);


        Intent lastIntent=getIntent();
        type=lastIntent.getIntExtra("type",1);
        from=lastIntent.getStringExtra("from");
        to=lastIntent.getStringExtra("to");
        Log.e("activity_room_selected", "!!!!!from="+from+";to="+to);

        roomList=initRooms();
//        roomList= DataSupport.findAll(Room.class);
        final ImageButton backBtn=(ImageButton)findViewById(R.id.back);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(),MainActivity.class);
                startActivity(intent);
            }
        });
        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.roomsList);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        RoomAdapter roomAdapter=new RoomAdapter(roomList);
        roomAdapter.setClickListener(new RoomAdapter.ItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {
                Room room=roomList.get(position);
                Log.e("ROOM SELECT", "roomNum:"+room.getRoomNum() );
                Intent intent=new Intent(view.getContext(),AppointmentActivity.class);
                intent.putExtra("roomNum",room.getRoomNum());
                intent.putExtra("roomImage",room.getPhoto1());
                intent.putExtra("from",from);
                intent.putExtra("cost",room.getPrice());
                intent.putExtra("to",to);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(roomAdapter);
    }
    private List<Room> initRooms(){
        List<Room> roomList=new ArrayList<Room>();
        Room room1=new Room("A01",1,121.547467f,38.879362f,25.0f,(byte)0,R.drawable.room_a01_1,R.drawable.room_a01_2,R.drawable.room_a01_3);
        room1.setAddress(convertAddress(getApplicationContext(),room1.getLatitude(),room1.getLongitude()));
        roomList.add(room1);
        Room room2=new Room("A02",1,121.551455f,38.891803f,12.0f,(byte)0,R.drawable.room_a02_1,R.drawable.room_a02_2,R.drawable.room_a02_3);
        room2.setAddress(convertAddress(getApplicationContext(),room1.getLatitude(),room1.getLongitude()));
        roomList.add(room2);
        Room room3=new Room("A03",1,121.565649f,38.892702f,35.0f,(byte)0,R.drawable.room_a01_1,R.drawable.room_a01_2,R.drawable.room_a01_3);
        room3.setAddress(convertAddress(getApplicationContext(),room1.getLatitude(),room1.getLongitude()));
        roomList.add(room3);
        Room room4=new Room("A04",1,121.578548f,38.889753f,40.0f,(byte)0,R.drawable.room_a02_1,R.drawable.room_a02_2,R.drawable.room_a02_3);
        room4.setAddress(convertAddress(getApplicationContext(),room1.getLatitude(),room1.getLongitude()));
        roomList.add(room3);
        return roomList;
    }

    private static String convertAddress(Context context, double latitude, double longitude) {
        Geocoder mGeocoder = new Geocoder(context, Locale.getDefault());
        StringBuilder mStringBuilder = new StringBuilder();

        try {
            List<Address> mAddresses = mGeocoder.getFromLocation(latitude, longitude, 1);
            if (!mAddresses.isEmpty()) {
                Address address = mAddresses.get(0);
                mStringBuilder.append(address.getAdminArea()).append(", ").append(address.getLocality()).append(", ").append(address.getCountryName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return mStringBuilder.toString();
    }

}
