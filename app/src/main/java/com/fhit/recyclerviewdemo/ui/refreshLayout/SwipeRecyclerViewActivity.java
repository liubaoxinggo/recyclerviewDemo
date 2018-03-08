package com.fhit.recyclerviewdemo.ui.refreshLayout;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.fhit.recyclerviewdemo.R;
import com.fhit.recyclerviewdemo.adapter.RefreshAdapter;
import com.fhit.recyclerviewdemo.bean.Person;
import com.fhit.recyclerviewdemo.custom.MDividerItemDecoration;
import com.fhit.swiperefreshrecyclerview.SwipeRecyclerView;
import com.fhit.swiperefreshrecyclerview.footerView.SimpleFooterView;

import java.util.ArrayList;

/**
 * http://www.jcodecraeer.com/a/anzhuokaifa/2016/1107/6753.html
 */
public class SwipeRecyclerViewActivity extends Activity {

    SwipeRecyclerView swipeRecyclerView;

    RefreshAdapter adapter;

    private ArrayList<Person> persons;

    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.swipe_refresh_recycler_view);
        initData();
        initView();
        initListener();

    }

    private void initData(){
        layoutManager = new LinearLayoutManager(this);

        persons = getData();

        adapter = new RefreshAdapter(persons);
    }
    private void initView(){
        swipeRecyclerView = (SwipeRecyclerView)findViewById(R.id.swipeRecyclerView);

        swipeRecyclerView.getRecyclerView().setLayoutManager(layoutManager);

        swipeRecyclerView.getRecyclerView().addItemDecoration(new MDividerItemDecoration(this,MDividerItemDecoration.VERTICAL_LIST));

        swipeRecyclerView.getSwipeRefreshLayout().setColorSchemeResources(R.color.colorPrimary);

        //设置网络处理
//        swipeRecyclerView.onNetChange(true);

        //设置错误信息
//        swipeRecyclerView.onError("error");

        //禁止下拉刷新
//        swipeRecyclerView.setRefreshEnable(false);
        //禁止加载更多
//        swipeRecyclerView.setLoadMoreEnable(false);

        //设置footerView
        swipeRecyclerView.setFooterView(new SimpleFooterView(this));

        swipeRecyclerView.setAdapter(adapter);

    }
    int age = 100;
    private void refreshData(){
        persons.add(0,new Person("refresh-"+age,age++));
    }
    private void initListener(){
        swipeRecyclerView.setOnLoadListener(new SwipeRecyclerView.OnLoadListener(){
            @Override
            public void onRefresh() {
                swipeRecyclerView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshData();
                        swipeRecyclerView.complete();
                        adapter.notifyItemInserted(0);
                        layoutManager.scrollToPosition(0);
                    }
                },2000);
            }

            @Override
            public void onLoadMore() {
                persons.add(new Person("load more-"+age,age++));
                swipeRecyclerView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRecyclerView.stopLoadingMore();
                        adapter.notifyDataSetChanged();
//                        swipeRecyclerView.onNoMore("----end----");
                    }
                },2000);
            }
        });
        //设置自动下拉刷新，切记要在recyclerView.setOnLoadListener()之后调用
        //因为在没有设置监听接口的情况下，setRefreshing(true),调用不到OnLoadListener
//        swipeRecyclerView.setRefreshing(true);

    }

    private ArrayList<Person> getData(){
        ArrayList<Person> persons = new ArrayList<>();
        for(int i = 0;i < 15 ;i++){
            persons.add(new Person("name-"+i,i+5));
        }
        return persons;
    }
}
