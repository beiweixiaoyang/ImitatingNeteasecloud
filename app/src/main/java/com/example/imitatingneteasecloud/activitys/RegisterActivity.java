package com.example.imitatingneteasecloud.activitys;


import android.os.Bundle;
import android.view.View;

import com.example.imitatingneteasecloud.R;
import com.example.imitatingneteasecloud.utils.UserUtil;
import com.example.imitatingneteasecloud.views.InputView;

public class RegisterActivity extends BaseActivity {


    private InputView register_phone;
    private InputView register_password;
    private InputView register_passwordConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
    }

    private void initView() {
        initNavBar(true,"注册",false);
        register_phone=findViewById(R.id.register_phone);
        register_password=findViewById(R.id.register_password);
        register_passwordConfirm=findViewById(R.id.register_password_confirm);
    }

    /**
     * 注册按钮的点击事件
     * @param view
     */
    public void onRegisterClick(View view) {
        String phone=register_phone.getInputText();
        String password=register_password.getInputText();
        String passwordConfirm=register_passwordConfirm.getInputText();
        boolean result = UserUtil.register(this, phone, password, passwordConfirm);
        if(!result) return;
        UserUtil.logout(this);
    }
}