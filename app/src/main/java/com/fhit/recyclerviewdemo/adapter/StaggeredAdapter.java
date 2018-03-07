package com.fhit.recyclerviewdemo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fhit.recyclerviewdemo.R;
import com.fhit.recyclerviewdemo.adapter.ViewHolder.SViewHolder;

/**
 * Created by liubaoxing on 2018/3/7 16:58<br/>
 * Email:liubaoxinggo@foxmail.com<br/>
 */

public class StaggeredAdapter extends RecyclerView.Adapter<SViewHolder> {

    public StaggeredAdapter() {
    }

    @Override
    public SViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = null;
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
    public void onBindViewHolder(SViewHolder holder, int position) {
        holder.tvIndex.setText(String.valueOf(position));
    }

    @Override
    public int getItemCount() {
        return 25;
    }
}
