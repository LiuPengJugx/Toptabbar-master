package com.example.carson_ho.toptabbar.view;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.carson_ho.toptabbar.R;
import com.example.carson_ho.toptabbar.bean.User;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

import java.util.List;

public class Fragment01 extends Fragment {
//    private MyDBHelper dbHelper;

    private EditText username;
    private EditText id;
    private EditText tel;
    private EditText password;
    private View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInsranceState){

        view = inflater.inflate(R.layout.fragment01, container,false);
        LitePal.getDatabase();



        Button button3 = (Button) view.findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = (EditText)view.findViewById(R.id.editText1);
                tel = (EditText)view.findViewById(R.id.editText2);
                password = (EditText)view.findViewById(R.id.editText3);
                String id1 = id.getText().toString();
                String tel1 = tel.getText().toString();
                String password1 = password.getText().toString();
                if (login1(id1,tel1,password1)){
                    Toast.makeText(view.getContext(),"登录成功",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(view.getContext(),MainActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(view.getContext(),"登录失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }
    public boolean login1(String id, String tel, String password){
        List<User> users=DataSupport.where("id=?,tel=?,password=?",id,tel,password).find(User.class);
        if(users.size()>0)return true;
            else return false;
    }



}
