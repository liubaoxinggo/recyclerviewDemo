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

public class MyAdapter01 extends RecyclerBaseAdapter<Person> {

    private OnItemClickListener onItemClickListener;

    public MyAdapter01(ArrayList<Person> datas) {
        super(datas);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public MViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
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

    /**
     * 数据绑定
     * @param holder
     * @param position
     */
    public void onBindViewHolder(final MViewHolder holder, int position) {
        holder.tvName.setText(getDatas().get(position).getName());
        holder.tvAge.setText(String.valueOf(getDatas().get(position).getAge()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onItemClickListener != null){
                    int pos = holder.getLayoutPosition();
                    onItemClickListener.OnItemClick(holder.itemView,pos);
                }
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View view) {
                if(onItemClickListener != null){
                    int pos = holder.getLayoutPosition();
                    onItemClickListener.OnItemLongClick(holder.itemView,pos);
                }
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return getDatas() == null ? 0 : getDatas().size();
    }

    /**
     * 添加新的数据
     * @param p
     */
    public void addNewItem(Person p){
        if(getDatas() == null){
            setDatas(new ArrayList<Person>());
        }
        getDatas().add(0,p);
        notifyItemInserted(0);
    }

    /**
     * 删除指定位置的数据
     * @param position
     */
    public void deleteItem(int position){
        if(getDatas() == null || getDatas().isEmpty()){
            return;
        }
        getDatas().remove(position);
        notifyItemRemoved(position);
    }
    public interface OnItemClickListener{
        void OnItemClick(View view,int position);
        void OnItemLongClick(View view,int position);
    }

}
