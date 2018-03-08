package com.fhit.recyclerviewdemo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fhit.recyclerviewdemo.R;
import com.fhit.recyclerviewdemo.adapter.ViewHolder.MViewHolder;
import com.fhit.recyclerviewdemo.bean.Person;

import java.util.ArrayList;

/**
 * Created by liubaoxing on 2018/3/8 14:30<br/>
 * Email:liubaoxinggo@foxmail.com<br/>
 */

public class RefreshAdapter extends RecyclerBaseAdapter<Person> {

    public RefreshAdapter(ArrayList<Person> datas) {
        super(datas);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //实例化展示的view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout_1,parent,false);
        //实例化viewholder
        MViewHolder viewHolder = new MViewHolder(v);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        onBindViewHolder((MViewHolder)holder,position);
    }
    public void onBindViewHolder(final MViewHolder holder, int position) {
        holder.tvName.setText(getDatas().get(position).getName());
        holder.tvAge.setText(String.valueOf(getDatas().get(position).getAge()));
    }
}
