package com.fhit.recyclerviewdemo.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;

/**
 * Created by liubaoxing on 2018/3/9 11:25<br/>
 * Email:liubaoxinggo@foxmail.com<br/>
 */

public abstract class QuickAdapter<T> extends RecyclerView.Adapter<QuickAdapter.VH> {

    private List<T> mDatas;

    private OnItemClickListener onItemClickListener;

    public QuickAdapter(List<T> mDatas) {
        this.mDatas = mDatas;
    }

    public List<T> getDatas() {
        return mDatas;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    /**
     * 根据viewType返回布局Id
     * @param viewType
     * @return
     */
    public abstract int getLayoutId(int viewType);

    /**
     *具体的数据绑定
     * @param holder
     * @param data
     * @param position
     */
    public abstract void convert(VH holder,T data ,int position);

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        return VH.get(parent,getLayoutId(viewType));
    }

    @Override
    public void onBindViewHolder(VH holder, final int position) {
        convert(holder,mDatas.get(position),position);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }
    public interface OnItemClickListener{
        void OnItemClick(View view,int position);
        void OnItemLongClick(View view,int position);
    }
    static class VH extends RecyclerView.ViewHolder{

        private SparseArray<View> mViews;

        private View mConvertView;

        public VH(View itemView) {
            super(itemView);
            mConvertView = itemView;
            mViews = new SparseArray<>();
        }

        public static VH get(ViewGroup parent,int layoutId){
            View convertView = LayoutInflater.from(parent.getContext()).inflate(layoutId,parent,false);
            return new VH(convertView);
        }
        public <T extends View> T getView(int id){
            View v = mViews.get(id);
            if(v == null){
                v = mConvertView.findViewById(id);
                mViews.put(id,v);
            }
            return (T)v;
        }
        public void setText(int id,String value){
            TextView view = getView(id);
            view.setText(value);
        }

    }
}
