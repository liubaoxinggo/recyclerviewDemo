package com.fhit.recyclerviewdemo.ui.StaggeredGridLayoutManager;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.fhit.recyclerviewdemo.R;
import com.fhit.recyclerviewdemo.adapter.StaggeredAdapter;
import com.fhit.recyclerviewdemo.custom.MDGridRvDividerDecoration;
import com.fhit.recyclerviewdemo.custom.MDStaggeredGridRvDividerDecoration;

public class StaggeredGridLayoutManagerActivity extends Activity {

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
        manager = new StaggeredGridLayoutManager(3,OrientationHelper.VERTICAL);

        adapter  = new StaggeredAdapter();

    }
    private void initView(){
        mRecyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        //设置布局管理器
        mRecyclerView.setLayoutManager(manager);
        //设置分割线
        mRecyclerView.addItemDecoration(new MDStaggeredGridRvDividerDecoration(this));
        //设置item添加和删除动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //设置适配器
        mRecyclerView.setAdapter(adapter);

    }



}
