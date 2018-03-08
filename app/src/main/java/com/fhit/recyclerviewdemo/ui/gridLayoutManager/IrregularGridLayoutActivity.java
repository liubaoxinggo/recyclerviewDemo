package com.fhit.recyclerviewdemo.ui.gridLayoutManager;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;

import com.fhit.recyclerviewdemo.R;
import com.fhit.recyclerviewdemo.adapter.MyAdapter01;
import com.fhit.recyclerviewdemo.adapter.RecyclerBaseAdapter;
import com.fhit.recyclerviewdemo.bean.Person;
import com.fhit.recyclerviewdemo.custom.MDGridRvDividerDecoration;
import com.fhit.recyclerviewdemo.util.LogUtils;

import java.util.ArrayList;

/**
 * 不规则的表格
 */
public class IrregularGridLayoutActivity extends Activity {

    private RecyclerView mRecyclerView;

    private RecyclerBaseAdapter adapter;

    private GridLayoutManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview);
        initData();
        initView();
    }
    private void initData(){
        manager = new GridLayoutManager(this,4, OrientationHelper.VERTICAL,false);
        //不规则列表设置--指定每个item所占的列数
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                //这里的返回值表示下表为position的item所占的列数
                //这里的列表一共4列
                if(position < 11){
                    return 1;//
                }else if(position < 14){
                    return 2;
                }else if(position < 16){
                    return 3;
                }
                return 4;
            }
        });

        adapter  = new MyAdapter01(getData());


    }
    private void initView(){
        mRecyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        //设置布局管理器
        mRecyclerView.setLayoutManager(manager);
        //设置分割线
        mRecyclerView.addItemDecoration(new MDGridRvDividerDecoration(this));
        //设置item添加和删除动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //设置适配器
        mRecyclerView.setAdapter(adapter);

        //拖拽设置
//        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new SimpleItemTouchHelperCallback(adapter));

//        itemTouchHelper.attachToRecyclerView(mRecyclerView);

    }

    private ArrayList<Person> getData(){
        ArrayList<Person> persons = new ArrayList<>();
        for(int i = 0;i < 23 ;i++){
            persons.add(new Person("name-"+i,i));
        }
        LogUtils.d("数据初始化:",persons.size());
        return persons;
    }
}
