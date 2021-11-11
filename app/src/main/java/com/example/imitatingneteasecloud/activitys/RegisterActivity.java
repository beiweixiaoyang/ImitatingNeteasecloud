package com.example.imitatingneteasecloud.activitys;


import android.os.Bundle;
import android.view.View;

import com.example.imitatingneteasecloud.R;

public class RegisterActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
    }

    private void initView() {
        initNavBar(false,"注册",false);
    }

    /**
     * 注册按钮的点击事件
     * @param view
     */
    public void onRegisterClick(View view) {
    }
}