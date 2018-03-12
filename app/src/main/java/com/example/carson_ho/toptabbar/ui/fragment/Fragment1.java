package com.example.carson_ho.toptabbar.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.example.carson_ho.toptabbar.bean.Article;
import com.example.carson_ho.toptabbar.bean.New;
import com.example.carson_ho.toptabbar.ui.adapter.LocalImageHolderView;
import com.example.carson_ho.toptabbar.ui.adapter.LvTAdapter;
import com.example.carson_ho.toptabbar.R;
import com.example.carson_ho.toptabbar.utils.ResourceGet;
import com.example.carson_ho.toptabbar.ui.activity.LoginActivity;
import com.example.carson_ho.toptabbar.ui.activity.MainActivity;


import org.litepal.crud.DataSupport;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Fragment1 extends Fragment{
    MainActivity mainActivity;
    private Button login_b;
    private ListView listView;
    private LvTAdapter adapter;
    private List<New> dataList = new ArrayList<>();
    private List<Article> articleList=new ArrayList<Article>();
    private ArrayList<Integer> localImages = new ArrayList<Integer>();
    View view;
    public static final String BASE_URL = "http://news-at.zhihu.com/";
    ConvenientBanner convenientBanner;
    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment1,container,false);
        mainActivity = (MainActivity)getActivity();
        login_b = (Button)  view.findViewById(R.id.login_button);
//              轮播图的设置
        convenientBanner=(ConvenientBanner)view.findViewById(R.id.convenientBanner);
        //获取本地的图片
        for (int position = 1; position <=4; position++) {
            localImages.add(new ResourceGet().getImageID("room_a0"+position+"_1",getContext()));
        }
        convenientBanner.setPages(new CBViewHolderCreator() {
            @Override
            public Object createHolder() {
                return new LocalImageHolderView();
            }
        },localImages)
                //设置指示器是否可见
                .setPointViewVisible(true)
                //设置自动切换（同时设置了切换时间间隔）
                .startTurning(2000)
                .setPageIndicator(new int[]{R.drawable.ic_page_indicator, R.drawable.ic_page_indicator_focused})
                //设置指示器的方向（左、中、右）
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT)
                //设置手动影响（设置了该项无法手动切换）
                .setManualPageable(true);


        listView = (ListView) view.findViewById(R.id.list_view);
        initText();
        adapter = new LvTAdapter(getContext(), R.layout.lv_item,dataList);
        listView.setAdapter(adapter);


        login_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mainActivity,LoginActivity.class);
                startActivity(intent);
            }
        });



        return view;
    }
    private void initText(){

//       articleList= DataSupport.findAll(Article.class);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String current=df.format(new Date());

        for(int i=1;i<=10;i++)
        {
            Article article=new Article("artcle"+i,"lpj","This is empty!","my article"+i,current);
            articleList.add(article);
        }
        for (Article article:articleList) {
            dataList.add(new New(article.getTitle(),R.drawable.sucai,article.getAuthor(),article.getTime()));
        }
        New end = new New("我是有底线的~~",0,"","");
        dataList.add(end);
    }



}
