package com.fhit.recyclerviewdemo.adapter.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.fhit.recyclerviewdemo.R;

/**
 * Created by liubaoxing on 2018/3/7 10:47<br/>
 * Email:liubaoxinggo@foxmail.com<br/>
 */

public class MViewHolder extends RecyclerView.ViewHolder {
    public TextView tvName;
    public TextView tvAge;
    public MViewHolder(View itemView) {
        super(itemView);
        tvName = (TextView) itemView.findViewById(R.id.name);
        tvAge = (TextView) itemView.findViewById(R.id.age);
    }
}
