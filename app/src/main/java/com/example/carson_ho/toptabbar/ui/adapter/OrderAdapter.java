package com.example.carson_ho.toptabbar.ui.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.carson_ho.toptabbar.R;
import com.example.carson_ho.toptabbar.bean.Orders;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {
    private List<Orders> mOrdersList;
    static class ViewHolder extends RecyclerView.ViewHolder{
        View orderView;
        ImageView orderImage;
        TextView roomName;
        TextView orderTime;
        TextView orderCost;
        TextView isUsed;
        Button assess;
        public ViewHolder(View itemView) {
            super(itemView);
            orderView=itemView;
            orderImage=(ImageView) itemView.findViewById(R.id.order_room);
            roomName=(TextView)itemView.findViewById(R.id.room_name);
            orderTime=(TextView)itemView.findViewById(R.id.order_time);
            orderCost=(TextView)itemView.findViewById(R.id.order_cost);
            isUsed=(TextView)itemView.findViewById(R.id.status_use);
            assess=(Button)itemView.findViewById(R.id.assess);
        }
    }


    public OrderAdapter(List<Orders> ordersList)
    {
        mOrdersList = ordersList;

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.order_item,parent,false);
        final ViewHolder holder=new ViewHolder(view);
        holder.assess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position=holder.getAdapterPosition();
                Orders orders = mOrdersList.get(position);
                Toast.makeText(v.getContext(),"you will access "+ orders.getRoomNum(),Toast.LENGTH_LONG);
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Orders orders = mOrdersList.get(position);

        holder.roomName.setText(orders.getRoomNum());

       /* holder.orderImage.setImageResource(orders.getRoomImage());
//        利用时间戳计算时间
        int hour=(int)((orders.getTo()-orders.getFrom())/3600);

        holder.orderTime.setText(hour+"");
        holder.orderCost.setText(orders.getCost()+"");
        if (orders.getStatusUse()==0)holder.isUsed.setText("未使用");
        else holder.isUsed.setText("已使用");*/


      /*  //获取当前时间戳
        long time=System.currentTimeMillis();*/

    }



    @Override
    public int getItemCount() {
        return mOrdersList.size();
    }
}
