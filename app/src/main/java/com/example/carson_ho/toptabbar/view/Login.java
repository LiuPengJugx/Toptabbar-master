package com.example.carson_ho.toptabbar.view;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.carson_ho.toptabbar.R;

public class Login extends AppCompatActivity implements View.OnClickListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button button1 = (Button) findViewById(R.id.button1);
        Button button2 = (Button) findViewById(R.id.button2);


        button1.setOnClickListener(this);
        button2.setOnClickListener(this);

        replaceFragment(new Fragment01());//改


    }
    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.button1:
                replaceFragment(new Fragment01());//改
                break;
            case R.id.button2:
                replaceFragment(new Fragment02());//改
                break;
            default:
                break;
        }
    }

    private void replaceFragment(Fragment fragment){    //改
        FragmentManager fragmentManager1 = getFragmentManager();
        FragmentTransaction transaction1 = fragmentManager1.beginTransaction();
        transaction1.replace(R.id.fragment_content,fragment);//改
        transaction1.commit();//如果还是像之前一样重叠把这句删了试试
    }
  /*  private void replaceFragment2(Fragment fragment){
        FragmentManager fragmentManager2 = getFragmentManager();
        FragmentTransaction transaction2 = fragmentManager2.beginTransaction();
        transaction2.replace(R.id.fragment01,fragment);
        transaction2.commit();
    }*/
}
