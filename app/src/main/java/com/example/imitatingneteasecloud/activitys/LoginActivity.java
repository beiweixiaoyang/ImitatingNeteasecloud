package com.example.imitatingneteasecloud.activitys;


import android.os.Bundle;
import android.view.View;

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

    /**
     * 登录按钮的点击事件
     */
    public void onLoginClick(View view) {
    }

    public void onRegisterClick(View view) {
    }
}