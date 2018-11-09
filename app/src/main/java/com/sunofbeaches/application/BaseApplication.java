package com.sunofbeaches.application;



import android.app.Application;

import com.sunofbeaches.Utils.StaticClass;

import cn.bmob.v3.Bmob;

public class BaseApplication extends Application {

    //创建
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化Bmob
        Bmob.initialize(this, StaticClass.BMOB_APP_ID);

    }
}
