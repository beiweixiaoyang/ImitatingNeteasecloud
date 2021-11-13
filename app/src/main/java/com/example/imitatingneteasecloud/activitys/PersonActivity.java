package com.example.imitatingneteasecloud.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.imitatingneteasecloud.R;
import com.example.imitatingneteasecloud.utils.UserInstance;
import com.example.imitatingneteasecloud.utils.UserUtil;

/**
 * 个人中心界面
 * 1.查看用户名
 * 2.修改密码
 * 3.退出当前用户
 */
public class PersonActivity extends BaseActivity {

    private TextView userName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);
        initView();
    }

    private void initView() {
        initNavBar(true,"个人中心",false);
        userName=findViewById(R.id.userName);
        userName.setText("用户名："+ UserInstance.getInstance().getPhone());
    }

    /**
     * 退出登录按钮点击事件
     * @param view
     */
    public void onLogoutClick(View view) {
        UserUtil.logout(this);
    }

    public void onChangePasswordClick(View view) {
        startActivity(new Intent(this,ChangePasswordActivity.class));
    }
}