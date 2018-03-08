package com.fhit.recyclerviewdemo.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.fhit.recyclerviewdemo.R;
import com.fhit.recyclerviewdemo.ui.gridLayoutManager.GridLayoutActivity;
import com.fhit.recyclerviewdemo.ui.gridLayoutManager.IrregularGridLayoutActivity;
import com.fhit.recyclerviewdemo.ui.linerLayoutManager.LinerLayoutManagerHorizontalActivity;
import com.fhit.recyclerviewdemo.ui.linerLayoutManager.LinerLayoutManagerVerticalActivity;
import com.fhit.recyclerviewdemo.ui.refreshLayout.RefreshRecyclerViewActivity;
import com.fhit.recyclerviewdemo.ui.refreshLayout.SwipeRecyclerViewActivity;
import com.fhit.recyclerviewdemo.ui.staggeredGridLayoutManager.StaggeredGridLayoutManagerActivity;

public class MainActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.linerLayout_manager1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, LinerLayoutManagerVerticalActivity.class));
            }
        });
        findViewById(R.id.linerLayout_manager2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, LinerLayoutManagerHorizontalActivity.class));
            }
        });
        findViewById(R.id.gridLayout_manager).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, GridLayoutActivity.class));
            }
        });
        findViewById(R.id.staggeredGridLayout_manager).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, StaggeredGridLayoutManagerActivity.class));
            }
        });
        findViewById(R.id.swipe_refresh_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, RefreshRecyclerViewActivity.class));
            }
        });
        findViewById(R.id.swipe_refresh_recycler_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SwipeRecyclerViewActivity.class));
            }
        });
        findViewById(R.id.irregular_gridLayout_manager).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, IrregularGridLayoutActivity.class));
            }
        });
    }
}
