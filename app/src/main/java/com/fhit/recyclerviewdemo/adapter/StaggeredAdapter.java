package com.fhit.recyclerviewdemo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fhit.recyclerviewdemo.R;
import com.fhit.recyclerviewdemo.adapter.ViewHolder.SViewHolder;
import com.fhit.recyclerviewdemo.bean.Person;

import java.util.ArrayList;

/**
 * Created by liubaoxing on 2018/3/7 16:58<br/>
 * Email:liubaoxinggo@foxmail.com<br/>
 */

public class StaggeredAdapter extends RecyclerBaseAdapter<Person> {


    public StaggeredAdapter(ArrayList<Person> datas) {
        super(datas);
    }

    @Override
    public SViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v ;
        if(viewType == 1){
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.staggered_item_1,parent,false);
        }else if(viewType == 2){
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.staggered_item_2,parent,false);
        }else{
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.staggered_item_3,parent,false);
        }

        return new SViewHolder(v);
    }

    @Override
    public int getItemViewType(int position) {
        return position % 3;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder mHolder, int position) {
        SViewHolder holder = (SViewHolder)mHolder;
        holder.tvIndex.setText(String.valueOf(getDatas().get(position).getAge()));
    }


}
