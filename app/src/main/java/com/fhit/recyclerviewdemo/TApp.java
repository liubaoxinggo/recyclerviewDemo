package com.fhit.recyclerviewdemo;

import android.app.Application;

import com.fhit.recyclerviewdemo.util.Utils;

/**
 * Created by liubaoxing on 2018/3/7 11:28<br/>
 * Email:liubaoxinggo@foxmail.com<br/>
 */

public class TApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
    }
}
