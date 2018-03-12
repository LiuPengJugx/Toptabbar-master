package com.example.carson_ho.toptabbar.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.carson_ho.toptabbar.R;
import com.example.carson_ho.toptabbar.bean.Room;
import java.util.List;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.ViewHolder> {
    private List<Room> mRoomsList;
    private ItemClickListener ClickListener;
    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView roomNum;
        TextView price;
        TextView address;
        ImageView photo1;
        ImageView photo2;
        RelativeLayout roomLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            roomNum=(TextView)itemView.findViewById(R.id.roomNumber);
            price=(TextView)itemView.findViewById(R.id.price);
            address=(TextView)itemView.findViewById(R.id.address);
            photo1=(ImageView)itemView.findViewById(R.id.photo);
            photo2=(ImageView)itemView.findViewById(R.id.photo2);
            roomLayout=(RelativeLayout)itemView.findViewById(R.id.room_layout);
        }
    }
    public RoomAdapter(List<Room> roomsList)
    {
        mRoomsList=roomsList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.room_item,parent,false);
        final ViewHolder holder=new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Room room=mRoomsList.get(position);
        holder.roomNum.setText(room.getRoomNum());
        holder.price.setText(room.getPrice()+"");
        holder.address.setText(room.getAddress());
       holder.photo1.setImageResource(room.getPhoto1());
       holder.photo2.setImageResource(room.getPhoto2());
       holder.roomNum.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               ClickListener.OnItemClick(v,position);
           }
       });
        holder.address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClickListener.OnItemClick(v,position);
            }
        });
        holder.price.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClickListener.OnItemClick(v,position);
            }
        });
        holder.photo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClickListener.OnItemClick(v,position);
            }
        });
        holder.photo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClickListener.OnItemClick(v,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mRoomsList.size();
    }

    public RoomAdapter setClickListener(ItemClickListener ClickListener)
    {
        this.ClickListener = ClickListener;
        return this;
    }
    public interface ItemClickListener{
        void OnItemClick(View view,int position);
    }

}
