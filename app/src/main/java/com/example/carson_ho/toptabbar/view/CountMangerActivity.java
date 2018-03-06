package com.example.carson_ho.toptabbar.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.carson_ho.toptabbar.controller.HeadPortrait;
import com.example.carson_ho.toptabbar.R;
import com.example.carson_ho.toptabbar.bean.User;

import org.litepal.crud.DataSupport;

public class CountMangerActivity extends AppCompatActivity {

    private RelativeLayout rlHeadPortrait;
    private RelativeLayout rlPhoneNumber;
    private Button btnOutOfLog;
    private ImageView userHead;
    private TextView userName;
    private TextView userNo;
    private TextView userPhone;
    private TextView userSignature;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_manger);
//        初始化数据
        SharedPreferences preferences=getSharedPreferences("data",MODE_PRIVATE);
        String no=preferences.getString("userNo","");
        no="2220160703";
        User user= DataSupport.where("no=?",no).findFirst(User.class);
        userHead=(ImageView)findViewById(R.id.userHead);
        userHead.setImageResource(user.getHead());
        userName=(TextView) findViewById(R.id.userName);
        userName.setText(user.getNickName());
        userNo=(TextView )findViewById(R.id.userNo);
        userNo=(TextView) findViewById(R.id.userNo);
        userNo.setText(user.getNo());
        userPhone=(TextView) findViewById(R.id.userPhone);
        userPhone.setText(user.getPhone());
        userSignature=(TextView) findViewById(R.id.userSignature);
        userSignature.setText(user.getSignature());

        findViewId();

        setListener();
    }

    public void findViewId()
    {
        rlHeadPortrait = (RelativeLayout)findViewById(R.id.rl_HeadPortrait);
        rlPhoneNumber = (RelativeLayout)findViewById(R.id.rl_PhoneNumber);
        btnOutOfLog = (Button)findViewById(R.id.btn_outOfLog);
    }
    public void setListener()
    {
        rlHeadPortrait.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CountMangerActivity.this,HeadPortrait.class);
                startActivity(intent);
            }
        });

        rlPhoneNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CountMangerActivity.this,PhoneNumberActivity.class);
                startActivity(intent);
            }
        });

        btnOutOfLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CountMangerActivity.this,Login.class);
                startActivity(intent);
            }
        });
    }
}
