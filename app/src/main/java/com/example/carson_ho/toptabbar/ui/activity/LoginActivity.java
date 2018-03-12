package com.example.carson_ho.toptabbar.ui.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.carson_ho.toptabbar.R;
import com.example.carson_ho.toptabbar.ui.fragment.Fragment01;
import com.example.carson_ho.toptabbar.ui.fragment.Fragment02;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button button1 = (Button) findViewById(R.id.button1);
        Button button2 = (Button) findViewById(R.id.button2);


        button1.setOnClickListener(this);
        button2.setOnClickListener(this);

        replaceFragment(new Fragment01());
    }
    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.button1:
                replaceFragment(new Fragment01());
                break;
            case R.id.button2:
                replaceFragment(new Fragment02());
                break;

            default:
                break;
        }
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager1 = getFragmentManager();
        FragmentTransaction transaction1 = fragmentManager1.beginTransaction();
        transaction1.replace(R.id.fragment_content,fragment);
        transaction1.commit();
    }

}
