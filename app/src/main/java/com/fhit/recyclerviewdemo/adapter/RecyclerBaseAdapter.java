package com.fhit.recyclerviewdemo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by liubaoxing on 2018/3/8 10:35<br/>
 * Email:liubaoxinggo@foxmail.com<br/>
 */

public class  RecyclerBaseAdapter<T> extends RecyclerView.Adapter {

    private ArrayList<T> datas;

    public ArrayList<T> getDatas() {
        return datas;
    }

    public void setDatas(ArrayList<T> datas) {
        this.datas = datas;
    }

    public RecyclerBaseAdapter(ArrayList<T> datas) {
        this.datas = datas;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }
}
