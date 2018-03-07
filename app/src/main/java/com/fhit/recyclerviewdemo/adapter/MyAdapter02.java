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
 * Created by liubaoxing on 2018/3/7 10:45<br/>
 * Email:liubaoxinggo@foxmail.com<br/>
 */

public class MyAdapter02 extends RecyclerView.Adapter<MViewHolder> {

    private ArrayList<Person> persons;

    public MyAdapter02(ArrayList<Person> persons) {
        this.persons = persons;
    }

    @Override
    public MViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //实例化展示的view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout_2,parent,false);
        //实例化viewholder
        MViewHolder viewHolder = new MViewHolder(v);
        return viewHolder;
    }

    /**
     * 数据绑定
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(MViewHolder holder, int position) {
        holder.tvName.setText(persons.get(position).getName());
        holder.tvAge.setText(String.valueOf(persons.get(position).getAge()));
    }

    @Override
    public int getItemCount() {
        return persons == null ? 0 : persons.size();
    }
}
