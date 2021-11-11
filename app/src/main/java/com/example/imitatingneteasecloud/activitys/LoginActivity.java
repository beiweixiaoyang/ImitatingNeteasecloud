package com.example.imitatingneteasecloud.activitys;


import android.os.Bundle;

import com.example.imitatingneteasecloud.R;

public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    //初始化View
    private void initView() {
        initNavBar(false,"登录",false);
    }
}