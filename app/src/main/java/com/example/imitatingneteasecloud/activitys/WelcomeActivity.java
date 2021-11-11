package com.example.imitatingneteasecloud.activitys;


import android.content.Intent;
import android.os.Bundle;

import com.example.imitatingneteasecloud.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * APP启动页面，启动页面停留三秒后跳转到下一个页面
 */
public class WelcomeActivity extends BaseActivity {

    private Timer mTimer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        mTimer=new Timer();
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                toLoginActivity();
            }
        },3000);
    }

    /**
     * 跳转到登录界面
     */
    private void toLoginActivity(){
        startActivity(new Intent(this,LoginActivity.class));
        finish();
    }

    /**
     * 跳转到主界面
     */
    private void toMainActivity(){
        startActivity(new Intent(this,MainActivity.class));
        finish();
    }
}