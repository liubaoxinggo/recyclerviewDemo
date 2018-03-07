package com.fhit.recyclerviewdemo.adapter.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.fhit.recyclerviewdemo.R;

/**
 * Created by liubaoxing on 2018/3/7 10:47<br/>
 * Email:liubaoxinggo@foxmail.com<br/>
 */

public class SViewHolder extends RecyclerView.ViewHolder {
    public TextView tvIndex;
    public SViewHolder(View itemView) {
        super(itemView);
        tvIndex = (TextView) itemView.findViewById(R.id.tv);
    }

}
