package com.example.imitatingneteasecloud.application;

import android.app.Application;

import com.blankj.utilcode.util.Utils;

import io.realm.Realm;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
        Realm.init(this);//初始化realm数据库
    }
}
