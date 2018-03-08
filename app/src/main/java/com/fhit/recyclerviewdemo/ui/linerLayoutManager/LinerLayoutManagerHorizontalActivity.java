package com.fhit.recyclerviewdemo.ui.linerLayoutManager;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.fhit.recyclerviewdemo.R;
import com.fhit.recyclerviewdemo.adapter.MyAdapter02;
import com.fhit.recyclerviewdemo.bean.Person;
import com.fhit.recyclerviewdemo.custom.MDividerItemDecoration;
import com.fhit.recyclerviewdemo.util.LogUtils;

import java.util.ArrayList;

public class LinerLayoutManagerHorizontalActivity extends Activity {

    private RecyclerView mRecyclerView;

    private RecyclerView.Adapter adapter;

    private RecyclerView.LayoutManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview);
        initData();
        initView();
    }
    private void initData(){
        manager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);

        adapter  = new MyAdapter02(getData());
    }
    private void initView(){
        mRecyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        //设置布局管理器
        mRecyclerView.setLayoutManager(manager);
        //设置分割线
        mRecyclerView.addItemDecoration(new MDividerItemDecoration(this,MDividerItemDecoration.HORIZONTAL_LIST));
        //设置适配器
        mRecyclerView.setAdapter(adapter);

    }
    private ArrayList<Person> getData(){
        ArrayList<Person> persons = new ArrayList<>();
        for(int i = 0;i < 10 ;i++){
            persons.add(new Person("name-"+i,i+5));
        }
        LogUtils.d("数据初始化:",persons.size());
        return persons;
    }

}




















