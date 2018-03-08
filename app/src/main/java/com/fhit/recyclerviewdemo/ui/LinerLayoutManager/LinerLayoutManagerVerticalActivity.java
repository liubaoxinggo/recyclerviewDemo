package com.fhit.recyclerviewdemo.ui.LinerLayoutManager;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Toast;

import com.fhit.recyclerviewdemo.R;
import com.fhit.recyclerviewdemo.adapter.MyAdapter01;
import com.fhit.recyclerviewdemo.bean.Person;
import com.fhit.recyclerviewdemo.custom.MDividerItemDecoration;
import com.fhit.recyclerviewdemo.custom.SimpleItemTouchHelperCallback;
import com.fhit.recyclerviewdemo.util.LogUtils;

import java.util.ArrayList;

public class LinerLayoutManagerVerticalActivity extends Activity implements MyAdapter01.OnItemClickListener{

    private RecyclerView mRecyclerView;

    private MyAdapter01 adapter;

    private RecyclerView.LayoutManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview);
        initData();
        initView();
        initListener();
    }
    private void initData(){
        manager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);

        adapter  = new MyAdapter01(getData());

        adapter.setOnItemClickListener(this);
    }
    private void initView(){
        mRecyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        //设置布局管理器
        mRecyclerView.setLayoutManager(manager);
        //设置分割线
        mRecyclerView.addItemDecoration(new MDividerItemDecoration(this,MDividerItemDecoration.VERTICAL_LIST));
        //设置item添加和删除动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //设置适配器
        mRecyclerView.setAdapter(adapter);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new SimpleItemTouchHelperCallback(adapter));

        itemTouchHelper.attachToRecyclerView(mRecyclerView);


    }
    boolean flag = true;
    private void initListener(){
        findViewById(R.id.op).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(flag){
                    adapter.addNewItem(new Person("name-add",109));
                }else{
                    adapter.deleteItem(0);
                }
                manager.scrollToPosition(0);
                flag = !flag;
            }
        });
    }
    private ArrayList<Person> getData(){
        ArrayList<Person> persons = new ArrayList<>();
        for(int i = 0;i < 10 ;i++){
            persons.add(new Person("name-"+i,i+5));
        }
        LogUtils.d("数据初始化:",persons.size());
        return persons;
    }

    @Override
    public void OnItemClick(View view, int position) {
        LogUtils.d("OnItemClick position = "+position);
        Toast.makeText(this,"OnItemClick position = "+position,Toast.LENGTH_LONG).show();
    }

    @Override
    public void OnItemLongClick(View view, int position) {
        LogUtils.d("OnItemLongClick position = "+position);
        Toast.makeText(this,"OnItemLongClick position = "+position,Toast.LENGTH_LONG).show();
    }
}




















