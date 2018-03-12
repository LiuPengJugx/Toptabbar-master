package com.example.carson_ho.toptabbar.ui.activity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.baidu.mapapi.SDKInitializer;
import com.example.carson_ho.toptabbar.bean.Article;
import com.example.carson_ho.toptabbar.bean.Orders;
import com.example.carson_ho.toptabbar.bean.User;
import com.example.carson_ho.toptabbar.ui.adapter.MyFragmentPagerAdapter;
import com.example.carson_ho.toptabbar.R;
import com.example.carson_ho.toptabbar.bean.Room;

import org.litepal.LitePal;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

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
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        SQLiteDatabase db=LitePal.getDatabase();
        //初始化视图
        initViews();

            //初始化房间信息
            initRoomData();
            //初始化订单信息
            initOrders();
            //初始化用户信息
            initUsers();
          /*  //初始化文章信息
            initAticles();*/


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
   /* private void initAticles(){
        Article article1=new Article("artcle1","lpj","empty!","my article.");
        article1.save();
        Article article2=new Article("artcle2","lpj","empty!","my article.");
        article2.save();
        Article article3=new Article("artcle3","lpj","empty!","my article.");
        article3.save();
        Article article4=new Article("artcle3","lpj","empty!","my article.");
        article4.save();
    }*/
    private void initUsers(){
        User user1=new User(R.drawable.init_user,"2220160703","空空如也",3000L,1000L,3000L,100f,"lpj","110","18742520180");
        user1.save();
        User user2=new User(R.drawable.init_user,"2220162599","空空如也",3000L,1000L,3000L,100f,"ljh","110","18742520180");
        user2.save();
        User user3=new User(R.drawable.init_user,"2220163362","空空如也",3000L,1000L,3000L,100f,"llk","110","18742520180");
        user3.save();
    }

    private void initRoomData(){
        Room room1=new Room("A01",1,121.547467f,38.879362f,25.0f,(byte)0,R.drawable.room_a01_1,R.drawable.room_a01_2,R.drawable.room_a01_3);
       room1.setAddress(convertAddress(getApplicationContext(),room1.getLatitude(),room1.getLongitude()));
        room1.save();
        Room room2=new Room("A02",1,121.551455f,38.891803f,12.0f,(byte)0,R.drawable.room_a02_1,R.drawable.room_a02_2,R.drawable.room_a02_3);
        room2.setAddress(convertAddress(getApplicationContext(),room1.getLatitude(),room1.getLongitude()));
        room2.save();
        Room room3=new Room("A03",1,121.565649f,38.892702f,35.0f,(byte)0,R.drawable.room_a01_1,R.drawable.room_a01_2,R.drawable.room_a01_3);
        room3.setAddress(convertAddress(getApplicationContext(),room1.getLatitude(),room1.getLongitude()));
        room3.save();
        Room room4=new Room("A04",1,121.578548f,38.889753f,40.0f,(byte)0,R.drawable.room_a02_1,R.drawable.room_a02_2,R.drawable.room_a02_3);
        room4.setAddress(convertAddress(getApplicationContext(),room1.getLatitude(),room1.getLongitude()));
        room4.save();
   }

    private void initOrders(){
        Orders orders1=new Orders("A01","2220160703",R.drawable.room_a01_1,null,null,80f,(byte) 1,(byte) 1);
        orders1.save();
        Orders order2=new Orders("A02","2220160703",R.drawable.room_a02_1,null,null,68f,(byte) 1,(byte) 1);
        order2.save();
        Orders order3=new Orders("A03","2220160703",R.drawable.room_a03_1,null,null,102f,(byte) 1,(byte) 1);
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