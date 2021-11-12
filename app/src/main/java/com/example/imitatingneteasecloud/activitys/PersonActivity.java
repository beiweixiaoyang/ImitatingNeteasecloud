package com.example.imitatingneteasecloud.activitys;

import android.os.Bundle;
import android.view.View;

import com.example.imitatingneteasecloud.R;

/**
 * 个人中心界面
 * 1.查看用户名
 * 2.修改密码
 * 3.退出当前用户
 */
public class PersonActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);
        initView();
    }

    private void initView() {
        initNavBar(true,"个人中心",false);
    }

    /**
     * 退出登录按钮点击事件
     * @param view
     */
    public void onLogoutClick(View view) {
    }

    public void onChangePasswordClick(View view) {
    }
}