package com.fhit.recyclerviewdemo.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.fhit.recyclerviewdemo.R;
import com.fhit.recyclerviewdemo.ui.GridLayoutManager.GridLayoutActivity;
import com.fhit.recyclerviewdemo.ui.LinerLayoutManager.LinerLayoutManagerHorizontalActivity;
import com.fhit.recyclerviewdemo.ui.LinerLayoutManager.LinerLayoutManagerVerticalActivity;
import com.fhit.recyclerviewdemo.ui.RefreshLayout.RefreshRecyclerViewActivity;
import com.fhit.recyclerviewdemo.ui.RefreshLayout.SwipeRecyclerViewActivity;
import com.fhit.recyclerviewdemo.ui.StaggeredGridLayoutManager.StaggeredGridLayoutManagerActivity;

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
    }
}
