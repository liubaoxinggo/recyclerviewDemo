package com.fhit.recyclerviewdemo.ui.RefreshLayout;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.fhit.recyclerviewdemo.R;
import com.fhit.recyclerviewdemo.adapter.RefreshAdapter;
import com.fhit.recyclerviewdemo.bean.Person;
import com.fhit.recyclerviewdemo.custom.MDividerItemDecoration;
import com.fhit.recyclerviewdemo.custom.SimpleItemTouchHelperCallback;
import com.fhit.recyclerviewdemo.util.LogUtils;

import java.util.ArrayList;

public class RefreshRecyclerViewActivity extends Activity {

    private SwipeRefreshLayout swipeRefreshLayout;

    private RecyclerView recyclerView;

    private RefreshAdapter adapter;

    private RecyclerView.LayoutManager layoutManager;

    private ArrayList<Person> persons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refresh_recycler_view);
        initData();
        initView();
        initListener();
    }
    private void initData(){
        layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);

        persons = getData();

        adapter  = new RefreshAdapter(persons);
    }

    private void initView(){
        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipe_refresh_layout);

        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent,R.color.colorPrimary,R.color.colorPrimaryDark);

        swipeRefreshLayout.setProgressViewOffset(true,10,21);



        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.addItemDecoration(new MDividerItemDecoration(this,MDividerItemDecoration.VERTICAL_LIST));

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new SimpleItemTouchHelperCallback(adapter));

        itemTouchHelper.attachToRecyclerView(recyclerView);

        recyclerView.setAdapter(adapter);

    }
    int age = 100;
    private void refreshData(){
        persons.add(0,new Person("refresh-"+age,age++));
    }
    private void initListener(){
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshData();
//                        adapter.notifyDataSetChanged();
                        adapter.notifyItemInserted(0);
                        swipeRefreshLayout.setRefreshing(false);
                        layoutManager.scrollToPosition(0);
                        LogUtils.d("当前线程为："+Thread.currentThread());//主线程
                    }
                },3000);
            }
        });
        setOnScrollChangeListener();
    }
    int lastVisibleItem;

    private  void setOnScrollChangeListener(){
         recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener(){
             @Override
             public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                 super.onScrollStateChanged(recyclerView, newState);
                 if(newState == RecyclerView.SCROLL_STATE_IDLE
                         && lastVisibleItem + 1 == adapter.getItemCount()){
                     swipeRefreshLayout.setRefreshing(true);
                     persons.add(new Person("load more-"+age,age++));
                     swipeRefreshLayout.postDelayed(new Runnable() {
                         @Override
                         public void run() {
                             swipeRefreshLayout.setRefreshing(false);
                             adapter.notifyDataSetChanged();
                         }
                     },3000);
                 }
             }

             @Override
             public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                 super.onScrolled(recyclerView, dx, dy);
                 lastVisibleItem = ((LinearLayoutManager)layoutManager).findLastVisibleItemPosition();
             }
         });
    }
    private ArrayList<Person> getData(){
        ArrayList<Person> persons = new ArrayList<>();
        for(int i = 0;i < 15 ;i++){
            persons.add(new Person("name-"+i,i+5));
        }
        LogUtils.d("数据初始化:",persons.size());
        return persons;
    }

}
