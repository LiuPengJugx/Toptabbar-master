package com.example.carson_ho.toptabbar.ui.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.carson_ho.toptabbar.R;
import com.example.carson_ho.toptabbar.bean.User;
import com.example.carson_ho.toptabbar.ui.activity.LoginActivity;
import com.example.carson_ho.toptabbar.ui.activity.MainActivity;

import org.litepal.crud.DataSupport;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class Fragment02 extends Fragment {
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        final View view = inflater.inflate(R.layout.fragment02, container, false);
        Button button4 = (Button) view.findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login2(view);
            }
        });
        return view;
    }

    public void login2(View v){
        Intent intent2 = new Intent(v.getContext(),LoginActivity.class);
        EditText editText4 = (EditText)v.findViewById(R.id.editText4);
        EditText editText5 = (EditText)v.findViewById(R.id.editText5);
        EditText editText6 = (EditText)v.findViewById(R.id.editText6);
        EditText editText7 = (EditText)v.findViewById(R.id.editText7);
        String newname = editText4.getText().toString();
        String newid = editText5.getText().toString();
        String newtel = editText6.getText().toString();
        String newpassword = editText7.getText().toString();

        if(CheckData(newtel,newid)){
            Toast.makeText(v.getContext(),"该学号/号码已被注册，注册失败",Toast.LENGTH_SHORT).show();
        }
        else {
            if(register(newname,newid,newtel,newpassword)){
                Toast.makeText(v.getContext(),"注册成功",Toast.LENGTH_SHORT).show();
                startActivity(intent2);
            }else{
                Toast.makeText(v.getContext(),"昵称长度或电话长度不正确!",Toast.LENGTH_SHORT).show();
            }
        }
    }

    public boolean register(String name,String no,String tel,String password){
        if((tel.length()!=11)&&name.length()>=3&&name.length()<=12)return false;
       /*User user=new User();
       user.setHead(R.mipmap.ic_launcher);
       user.setSignature("你的签名空空如也。");
       user.setNickName(name);
       user.setPhone(tel);
       user.setPassWord(password);
       user.setsCredit(5000);
       user.setsIntegral(100);
       user.setCash(800);
       user.setBalance(8000);
       user.setNo(no);
        user.save();*/
        SharedPreferences.Editor editor=getActivity().getSharedPreferences("userData", MODE_PRIVATE).edit();
       editor.putString("userNo",no);
       editor.putString("phone",tel);
       editor.putString("password",password);
       editor.putString("name",name);
       editor.apply();
       return true;
    }
    public boolean CheckData(String tel,String id)
    {
        /*List<User> users=DataSupport.where("no=?",id).find(User.class);
        if (users.size()>0)return true;
        return false;*/
        return false;
    }

}



