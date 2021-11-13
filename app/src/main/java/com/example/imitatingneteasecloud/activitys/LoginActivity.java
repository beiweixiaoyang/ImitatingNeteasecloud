package com.example.imitatingneteasecloud.activitys;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.imitatingneteasecloud.R;
import com.example.imitatingneteasecloud.utils.UserUtil;
import com.example.imitatingneteasecloud.views.InputView;

public class LoginActivity extends BaseActivity {

    private InputView login_phone,login_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    //初始化View
    private void initView() {
        initNavBar(false,"登录",false);
        login_phone=findViewById(R.id.login_phone);
        login_password=findViewById(R.id.login_password);
    }

    /**
     * 登录按钮的点击事件
     */
    public void onLoginClick(View view) {
        String phone=login_phone.getInputText();
        String password=login_password.getInputText();
        boolean result = UserUtil.verifyLogin(this, phone, password);
        if(!result) return;
        startActivity(new Intent(this,MainActivity.class));
        finish();
    }

    public void onRegisterClick(View view) {
        startActivity(new Intent(this,RegisterActivity.class));
    }
}