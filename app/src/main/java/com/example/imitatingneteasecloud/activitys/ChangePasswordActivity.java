package com.example.imitatingneteasecloud.activitys;

import android.os.Bundle;
import android.view.View;

import com.example.imitatingneteasecloud.R;
import com.example.imitatingneteasecloud.utils.UserUtil;
import com.example.imitatingneteasecloud.views.InputView;

public class ChangePasswordActivity extends BaseActivity {

    private InputView password,new_password,new_passwordConfirm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        initView();
    }

    private void initView() {
        initNavBar(true,"个人中心",false);
        password=findViewById(R.id.oldPassword);
        new_password=findViewById(R.id.newPassword);
        new_passwordConfirm=findViewById(R.id.newPasswordConfirm);

    }

    public void onChangePasswordClick(View view) {
        String oldPassword=password.getInputText();
        String newPassword=new_password.getInputText();
        String newPasswordConfirm=new_passwordConfirm.getInputText();
        boolean result = UserUtil.changePassword(this, oldPassword, newPassword, newPasswordConfirm);
        if(!result) return;
        UserUtil.logout(this);
    }
}