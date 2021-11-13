package com.example.imitatingneteasecloud.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

/**
 * 将用户标记（用户手机号）保存在sp中
 * 登录时判断是否存在用户标记
 * 存在则进入MainActivity，否则进入到登录基界面
 * 用户退出登录时，删除用户标记
 */
public class SpUtils {

    /**
     * 登录时保存用户手机号到sp文件中
     */
    public static boolean saveUser(Context context,String phone){
        SharedPreferences user = context.getSharedPreferences("user", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = user.edit();
        edit.putString("userPhone",phone);
        boolean result = edit.commit();
        return result;
    }
    /**
     * 在WelcomeActivity界面跳转时通过判断用户标记是否为空来跳转对应的界面
     */
    public static boolean isLogin(Context context){
        boolean result=false;
        SharedPreferences user = context.getSharedPreferences("user", Context.MODE_PRIVATE);
        String userPhone = user.getString("userPhone", "");
        if(!TextUtils.isEmpty(userPhone)){
            result =true;
        }
        return  result;
    }
    /**
     * 退出登录时，删除用户标记
     */
    public static boolean removeUser(Context context){
        SharedPreferences user = context.getSharedPreferences("user", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = user.edit();
        edit.remove("userPhone");
        boolean result = edit.commit();
        return result;
    }
}
