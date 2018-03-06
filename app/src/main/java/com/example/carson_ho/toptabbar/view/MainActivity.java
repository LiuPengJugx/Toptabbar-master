package com.example.carson_ho.toptabbar.view;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.carson_ho.toptabbar.bean.User;
import com.example.carson_ho.toptabbar.controller.MyFragmentPagerAdapter;
import com.example.carson_ho.toptabbar.R;
import com.example.carson_ho.toptabbar.bean.Order;
import com.example.carson_ho.toptabbar.bean.Room;

import org.litepal.LitePal;

public class MainActivity extends AppCompatActivity {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    private MyFragmentPagerAdapter myFragmentPagerAdapter;

    private TabLayout.Tab one;
    private TabLayout.Tab two;
    private TabLayout.Tab three;
    private TabLayout.Tab four;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SQLiteDatabase db=LitePal.getDatabase();
        //初始化视图
        initViews();
        //初始化房间信息
        initRoomData();
        //初始化订单信息
//        initOrders();
        //初始化用户信息
        initUsers();
    }
    private void initUsers(){
        User user1=new User(R.drawable.init_user,"2220160703","空空如也",3000L,1000L,3000L,100f,"lpj","liuPENGJU@1596","18742520180");
        user1.save();
        User user2=new User(R.drawable.init_user,"2220162599","空空如也",3000L,1000L,3000L,100f,"ljh","liuPENGJU@1596","18742520180");
        user2.save();
        User user3=new User(R.drawable.init_user,"2220163362","空空如也",3000L,1000L,3000L,100f,"llk","liuPENGJU@1596","18742520180");
        user3.save();
    }

    private void initRoomData(){
        Room room1=new Room("A01","理工园小区","高新园区","大连市",1,16.0f,(byte) 0,R.drawable.room_1);
        room1.save();
        Room room2=new Room("A02","文汇小区","高新园区","大连市",2,30.0f,(byte) 0,R.drawable.room_2);
        room2.save();
        Room room3=new Room("A03","西门凌海小区","高新园区","大连市",3,58.0f,(byte) 0,R.drawable.room_3);
        room3.save();
        Room room4=new Room("B01","亿达·软景E居","高新园区","大连市",1,12.0f,(byte) 0,R.drawable.room_4);
        room4.save();
   }
    private void initOrders(){
        Order order1=new Order("A01","2220160703",R.drawable.room_1,4324,4424,80f,(byte) 1,(byte) 1);
        order1.save();
        Order order2=new Order("A02","2220160703",R.drawable.room_2,4324,4424,68f,(byte) 1,(byte) 1);
        order2.save();
        Order order3=new Order("A03","2220160703",R.drawable.room_3,4324,4424,102f,(byte) 1,(byte) 1);
        order3.save();
    }

    private void initViews() {

        //使用适配器将ViewPager与Fragment绑定在一起
        mViewPager= (ViewPager) findViewById(R.id.viewPager);
        myFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(myFragmentPagerAdapter);

        //将TabLayout与ViewPager绑定在一起
        mTabLayout = (TabLayout) findViewById(R.id.tabLayout);
        mTabLayout.setupWithViewPager(mViewPager);

        //指定Tab的位置
                one = mTabLayout.getTabAt(0);
        two = mTabLayout.getTabAt(1);
        three = mTabLayout.getTabAt(2);
        four = mTabLayout.getTabAt(3);

        //设置Tab的图标
        one.setIcon(R.drawable.shouye);
        two.setIcon(R.drawable.yuyue);
        three.setIcon(R.drawable.dingdan);
        four.setIcon(R.drawable.wode);


    }
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.dingdantixing:
                Toast.makeText(this,"you clicked 订单详情",Toast.LENGTH_SHORT).show();
                break;
            case R.id.zaixiankefu:
                Toast.makeText(this,"you clicked 在线客服",Toast.LENGTH_SHORT).show();
                break;
            case R.id.saoyisao:
                Toast.makeText(this,"you clicked 扫一扫",Toast.LENGTH_SHORT).show();
                break;
            default:
        }
        return true;
    }

}