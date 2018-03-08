package com.fhit.recyclerviewdemo.custom;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.fhit.recyclerviewdemo.adapter.RecyclerBaseAdapter;
import com.fhit.recyclerviewdemo.util.FunctionUtil;
import com.fhit.recyclerviewdemo.util.LogUtils;

import java.util.ArrayList;
import java.util.Collections;

/**
 * RecyclerView的item的拖拽移动的相关实现<br/><br/>
 * Created by liubaoxing on 2018/3/8 09:51<br/>
 * Email:liubaoxinggo@foxmail.com<br/>
 */

public class SimpleItemTouchHelperCallback extends ItemTouchHelper.Callback {

    RecyclerBaseAdapter adapter;

    public SimpleItemTouchHelperCallback(RecyclerBaseAdapter adapter) {
        this.adapter = adapter;
    }

    /**
     *  设置拖拽移动方向
     * @param recyclerView
     * @param viewHolder
     * @return
     */
    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int dragFlags = 0;
        int swipeFlags = 0;
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        //注意这里的判断，一定要先判断layoutManager是否为GridLayoutManager，因为GridLayoutManager是LinearLayoutManager的子类
        if(layoutManager instanceof GridLayoutManager){//网格布局，不能滑动，只能上下左右拖动
            dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
            swipeFlags = 0;
        }else{
            if(((LinearLayoutManager)layoutManager).getOrientation() == LinearLayoutManager.VERTICAL){
                //纵向列表--方向：上下拖动，左右滑动
                dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
                swipeFlags = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
            }else{//横向列表--方法：左右拖动，上下滑动
                dragFlags = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
                swipeFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
            }
        }
        return makeMovementFlags(dragFlags,swipeFlags);
    }

    /**
     * 移动时不断回调的方法 正在移动的item和目标item交换元素，然后通知适配器更新数据
     * @param recyclerView
     * @param viewHolder
     * @param target
     * @return
     */
    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        int fromPosition = viewHolder.getAdapterPosition();
        int toPosition = target.getAdapterPosition();
//        if(fromPosition < toPosition){
//            for (int i = fromPosition ; i < toPosition; i++) {
//                Collections.swap(adapter.getDatas(), i, i+1);
//            }
//        }else{
//            for (int i = fromPosition ; i > toPosition; i--) {
//                Collections.swap(adapter.getDatas(), i, i-1);
//            }
//        }
        Collections.swap(adapter.getDatas(), fromPosition, toPosition);
        adapter.notifyItemMoved(fromPosition,toPosition);
        return true;
    }


    /**
     *  滑动时调用
     * @param viewHolder
     * @param direction
     */
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        if(adapter.getDatas() == null || adapter.getDatas().size() == 0)return;
        int position = viewHolder.getAdapterPosition();
        LogUtils.d("===onSwiped===position = "+position);
        adapter.getDatas().remove(position);
        adapter.notifyItemRemoved(position);
        //adapter.notifyDataSetChanged();//加上这句 解决‘ 删除item后加载刷新item时，有时item布局为空’的问题，具体原因不明
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return super.isLongPressDragEnabled();
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return super.isItemViewSwipeEnabled();
    }

    /**
     * item选中时的操作---设置高亮或放大
     * @param viewHolder
     * @param actionState
     */
    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        if(actionState != ItemTouchHelper.ACTION_STATE_IDLE){
            FunctionUtil.vibrate(viewHolder.itemView.getContext(),10);
//            viewHolder.itemView.setScaleY(1.2f);//放大
        }
    }

    /**
     * 手指松开后 还原
     * @param recyclerView
     * @param viewHolder
     */
    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
//        viewHolder.itemView.setScaleY(1.0f);
    }
}
