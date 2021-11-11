package com.example.imitatingneteasecloud.activitys;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.imitatingneteasecloud.R;

/**
 * 所有Activity的父类
 */
public class BaseActivity extends Activity {
    private ImageView iv_back,iv_person;
    private TextView tv_title;

    /**
     * 初始化导航栏,在其子类中通过调用initNavBar初始化导航栏
     * @param isShowBack 是否显示返回按钮
     * @param title 显示标题内容
     * @param isShowPerson 是否显示个人中心按钮
     */
    protected void initNavBar(boolean isShowBack,String title,boolean isShowPerson){
        iv_person=findViewById(R.id.iv_person);
        iv_back=findViewById(R.id.iv_back);
        tv_title=findViewById(R.id.tv_title);
        iv_back.setVisibility(isShowBack?View.VISIBLE:View.INVISIBLE);
        iv_person.setVisibility(isShowPerson?View.VISIBLE:View.INVISIBLE);
        tv_title.setText(title);

        //返回按钮的点击事件
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        //个人中心按钮的点击事件
        iv_person.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
