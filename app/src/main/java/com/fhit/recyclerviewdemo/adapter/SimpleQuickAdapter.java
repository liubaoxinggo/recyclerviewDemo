package com.fhit.recyclerviewdemo.adapter;

import android.view.View;
import android.widget.Toast;

import com.fhit.recyclerviewdemo.R;
import com.fhit.recyclerviewdemo.bean.Person;
import com.fhit.recyclerviewdemo.custom.ItemWithMenuLayout;
import com.fhit.recyclerviewdemo.util.LogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liubaoxing on 2018/3/9 11:45<br/>
 * Email:liubaoxinggo@foxmail.com<br/>
 */

public class SimpleQuickAdapter extends QuickAdapter<Person> {

    private ArrayList<ItemWithMenuLayout> opendItemWithMenuLayouts;//记录所有打开menu的item

    public SimpleQuickAdapter(List<Person> mDatas) {
        super(mDatas);
    }

    public void holdOpenMenu(ItemWithMenuLayout itemWithMenuLayout){
        if(opendItemWithMenuLayouts == null){
            opendItemWithMenuLayouts = new ArrayList<>();
        }
        opendItemWithMenuLayouts.add(itemWithMenuLayout);
    }

    public void closeOpenMenu(){
        if(opendItemWithMenuLayouts != null && opendItemWithMenuLayouts.size() > 0){
            for (int i = 0; i < opendItemWithMenuLayouts.size(); i++) {
                ItemWithMenuLayout im = opendItemWithMenuLayouts.get(i);
                if(im.isOpen()){
                    im.closeMenu();
                }
            }
            opendItemWithMenuLayouts.clear();
        }
    }
    @Override
    public int getLayoutId(int viewType) {
        return R.layout.item_with_menu_layout;
    }

    @Override
    public void convert(final QuickAdapter.VH holder, Person data, final int position) {
        holder.setText(R.id.name,data.getName());
        holder.setText(R.id.age,String.valueOf(data.getAge()));
//        holder.itemView.setOnClickListener(null);//此处可以添加点击事件
        holder.getView(R.id.delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeOpenMenu();

                Toast.makeText(holder.itemView.getContext(),"delete-"+position,Toast.LENGTH_LONG).show();
            }
        });
        ItemWithMenuLayout im = holder.getView(R.id.item_with_menu_layout);
        im.setCustomOnClickListener(new ItemWithMenuLayout.CustomOnClickListener() {
            @Override
            public void onClick() {
                Toast.makeText(holder.itemView.getContext(),"onClick-2-"+position,Toast.LENGTH_LONG).show();
            }
        });
//        holder.getView(R.id.content).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                closeOpenMenu();
//                Toast.makeText(holder.itemView.getContext(),"onClick-1-"+position,Toast.LENGTH_LONG).show();
//            }
//        });
    }
}
