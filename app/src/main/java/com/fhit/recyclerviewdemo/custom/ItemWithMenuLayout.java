package com.fhit.recyclerviewdemo.custom;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import com.fhit.recyclerviewdemo.adapter.SimpleQuickAdapter;
import com.fhit.recyclerviewdemo.util.LogUtils;

/**
 * http://blog.csdn.net/yhaolpz/article/details/77366154<br/>
 * Created by liubaoxing on 2018/3/9 15:11<br/>
 * Email:liubaoxinggo@foxmail.com<br/>
 */

public class ItemWithMenuLayout extends HorizontalScrollView {

    private static final float radio = 0.3f;//菜单占屏幕宽度

    private  int mScreenWidth;//屏幕宽度

    private int mMenuWidth;//菜单宽度

    private boolean isOpen;//是否打开

    private boolean once = true;//测量一次

    private long downTime = 0;

    public ItemWithMenuLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mScreenWidth = context.getResources().getDisplayMetrics().widthPixels;
        mMenuWidth = (int)(mScreenWidth * radio);
        setOverScrollMode(View.OVER_SCROLL_NEVER);
        setHorizontalScrollBarEnabled(false);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if(once){
            LinearLayout wrapper = (LinearLayout)getChildAt(0);
            wrapper.getChildAt(0).getLayoutParams().width = mScreenWidth;
            wrapper.getChildAt(1).getLayoutParams().width = mMenuWidth;
            once = false;
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN://content 设置了 OnClickListener，这里就不会执行了
                downTime = System.currentTimeMillis();
                LogUtils.d("====ACTION_DOWN======");
                closeOpenMenu();//关闭上一次打开的menu
                break;
            case MotionEvent.ACTION_UP:
                int scrollX = getScrollX();
                if(System.currentTimeMillis() - downTime <= 100 && scrollX == 0){
                    if(mCustomOnClickListener != null){
                        mCustomOnClickListener.onClick();
                    }
                    return false;
                }
                if(Math.abs(scrollX) > mMenuWidth / 2){
                    this.smoothScrollTo(mMenuWidth,0);
                    onOpenMenu();//当打开菜单时记录此view，方便下次关闭
                }else{
                    this.smoothScrollTo(0,0);
                }
                return false;
        }
        return super.onTouchEvent(ev);
    }

    /**
     * 关闭菜单
     */
    public void closeMenu(){
        this.smoothScrollTo(0,0);
        isOpen = false;
    }

    public boolean isOpen() {
        return isOpen;
    }

    /**
     * 获取adapter
     * @return SimpleQuickAdapter
     */
    private SimpleQuickAdapter getAdapter(){
        View view = this;
        while (true){
            view = (View)view.getParent();
            if(view instanceof RecyclerView){
                break;
            }
        }
        return (SimpleQuickAdapter)((RecyclerView)view).getAdapter() ;
    }
    /**
     * 当打开菜单时记录此view，方便下次关闭
     */
    private void onOpenMenu(){
        isOpen = true;
        getAdapter().holdOpenMenu(this);
    }

    /**
     * 当触摸此item时，关闭上一次打开的item
     */
    private void closeOpenMenu(){
        if(!isOpen){
            getAdapter().closeOpenMenu();
        }
    }

    public interface CustomOnClickListener {
        void onClick();
    }

    private CustomOnClickListener mCustomOnClickListener;

    public void setCustomOnClickListener(CustomOnClickListener listener) {
        this.mCustomOnClickListener = listener;
    }
}
