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

public class MyAdapter01 extends RecyclerView.Adapter<MViewHolder> {

    private ArrayList<Person> persons;

    private OnItemClickListener onItemClickListener;

    public MyAdapter01(ArrayList<Person> persons) {
        this.persons = persons;
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

    /**
     * 数据绑定
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(final MViewHolder holder, int position) {
        holder.tvName.setText(persons.get(position).getName());
        holder.tvAge.setText(String.valueOf(persons.get(position).getAge()));
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
        return persons == null ? 0 : persons.size();
    }

    /**
     * 添加新的数据
     * @param p
     */
    public void addNewItem(Person p){
        if(persons == null){
            persons = new ArrayList<>();
        }
        persons.add(0,p);
        notifyItemInserted(0);
    }

    /**
     * 删除指定位置的数据
     * @param position
     */
    public void deleteItem(int position){
        if(persons == null || persons.isEmpty()){
            return;
        }
        persons.remove(position);
        notifyItemRemoved(position);
    }
    public interface OnItemClickListener{
        void OnItemClick(View view,int position);
        void OnItemLongClick(View view,int position);
    }

}
