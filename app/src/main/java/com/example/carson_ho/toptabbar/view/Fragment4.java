package com.example.carson_ho.toptabbar.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.carson_ho.toptabbar.controller.FruitAdapter;
import com.example.carson_ho.toptabbar.R;
import com.example.carson_ho.toptabbar.bean.Fruit;
import com.example.carson_ho.toptabbar.bean.User;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Carson_Ho on 16/7/22.
 */
public class Fragment4 extends Fragment {

    View view;
    MainActivity mainActivity;

    private Button btnCountManger;

    private List<Fruit> fruitList = new ArrayList<>();
    TextView name;
    TextView signature;
    TextView  deposit;
    TextView  balance;
    TextView  integralValue;
    TextView  creditValue;
    ImageView head;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment4,container,false);
        mainActivity = (MainActivity)getActivity();


        initDatas();
        FruitAdapter adapter = new FruitAdapter(mainActivity, R.layout.fruit_item,fruitList);

        ListView listView = (ListView)view.findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Fruit fruit = fruitList.get(position);
                Toast.makeText(mainActivity,fruit.getName(),Toast.LENGTH_SHORT).show();
            }
        });

        Toolbar toolbar = (Toolbar)view.findViewById(R.id.toolbar);
        mainActivity.setSupportActionBar(toolbar);


        //账户管理按钮
        btnCountManger = (Button)view.findViewById(R.id.btn_countManager);
        btnCountManger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mainActivity,CountMangerActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        getActivity().getMenuInflater().inflate(R.menu.toolbar,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.dingdantixing:
                Toast.makeText(getContext(),"you clicked 订单详情",Toast.LENGTH_SHORT).show();
                break;
            case R.id.zaixiankefu:
                Toast.makeText(getContext(),"you clicked 在线客服",Toast.LENGTH_SHORT).show();
                break;
            case R.id.saoyisao:
                Toast.makeText(getContext(),"you clicked 扫一扫",Toast.LENGTH_SHORT).show();
                break;
            default:
        }
        return true;
    }

    private void initDatas()
    {
        SharedPreferences preferences=getActivity().getSharedPreferences("userData", Context.MODE_PRIVATE);
        String no=preferences.getString("userNo","");
        no="2220160703";
        User user= DataSupport.where("no=?",no).findFirst(User.class);
        Log.e("Fragment4", "initDatas: "+user.toString() );
        name=(TextView)view.findViewById(R.id.name);
        name.setText(user.getNickName());
        signature=(TextView)view.findViewById(R.id.signature);
        signature.setText(user.getSignature());
        head=(ImageView)view.findViewById(R.id.head);
        head.setImageResource(user.getHead());
        deposit=(TextView)view.findViewById(R.id.deposit);
        deposit.setText(String.valueOf(user.getCash()));
        balance=(TextView)view.findViewById(R.id.balance);
        balance.setText(String.valueOf(user.getBalance()));
        integralValue=(TextView)view.findViewById(R.id.integralValue);
        integralValue.setText(String.valueOf(user.getsIntegral()));
        creditValue=(TextView)view.findViewById(R.id.creditValue);
        creditValue.setText(String.valueOf(user.getsCredit()));



        Fruit fruit1 = new Fruit("兑换积分", R.mipmap.money, R.mipmap.arrow);
        fruitList.add(fruit1);
        Fruit fruit2 = new Fruit("信用值历史记录", R.mipmap.history, R.mipmap.arrow);
        fruitList.add(fruit2);
        Fruit fruit3 = new Fruit("系统设置", R.mipmap.gear, R.mipmap.arrow);
        fruitList.add(fruit3);

    }
}
