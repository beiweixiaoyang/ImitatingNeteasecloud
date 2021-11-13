package com.example.imitatingneteasecloud.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.Toast;

import com.blankj.utilcode.util.RegexUtils;
import com.example.imitatingneteasecloud.R;
import com.example.imitatingneteasecloud.activitys.LoginActivity;
import com.example.imitatingneteasecloud.models.UserModel;

import java.util.List;

/**
 * 用户信息相关的工具类
 */
public class UserUtil {

    /**
     * 登录合法性验证
     * 1.对手机号进行合法性验证
     * 2.手机号是否已经注册
     * 3.密码不能为空，判断密码和手机号是否匹配
     * @param phone 用户手机号
     * @param password 密码
     */
    public static boolean verifyLogin(Context context,String phone,String password){
        if(!RegexUtils.isMobileExact(phone)){
            Toast.makeText(context,"请输入正确的手机号",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!userIsExists(phone)){
            Toast.makeText(context,"该手机号未注册",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(context,"请输入密码",Toast.LENGTH_SHORT).show();
            return false;
        }
        RealmUtils realmUtils=new RealmUtils();
        boolean result = realmUtils.verifyUser(phone, password);
        if(!result){
            Toast.makeText(context,"手机号或密码不正确",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!SpUtils.saveUser(context,phone)){
            Toast.makeText(context,"系统错误，请稍后再试",Toast.LENGTH_SHORT).show();
            return false;
        }
        //保存用户标记到用户单例类中
        UserInstance.getInstance().setPhone(phone);
        realmUtils.setMusicSource(context);
        realmUtils.close();
        return true;
    }

    /**
     * 跳转到登录界面
     * 清空当前任务栈（否则点击返回回回到上一个activity）
     * 重新设置activity的切换动画
     */
    public static void logout(Context context){
        boolean result = SpUtils.removeUser(context);
        if(!result){
            Toast.makeText(context,"系统错误，请稍后再试",Toast.LENGTH_SHORT).show();
            return ;
        }
        RealmUtils realmUtils=new RealmUtils();
        realmUtils.removeMusicSource();
        realmUtils.close();
        context.startActivity(new Intent(context, LoginActivity.class).
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
        ((Activity)context).overridePendingTransition(R.anim.close_enter,R.anim.close_exit);
    }

    /**
     * 注册账号合法性验证
     * 1.对手机号进行合法性验证
     * 2.两次输入密码不能为空并且需要相等
     * 3.判断当前用户是否已经注册
     * 4.添加用户数据到数据库中
     */

    public static boolean register(Context context,String phone,String password,String passwordConfirm){
        if(!RegexUtils.isMobileExact(phone)){
            Toast.makeText(context,"请输入正确的手机号",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(TextUtils.isEmpty(password) || !password.equals(passwordConfirm)){
            Toast.makeText(context,"请确认密码",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(userIsExists(phone)){
            Toast.makeText(context,"该手机号已经注册",Toast.LENGTH_SHORT).show();
            return false;
        }
        UserModel userModel=new UserModel();
        userModel.setPhone(phone);
        userModel.setPassword(password);
        saveUser(userModel);
        return true;
    }

    /**
     * 修改密码
     */
    public static boolean changePassword(Context context,String oldPassword,String newPassword,String newPasswordConfirm){
        RealmUtils realmUtils=new RealmUtils();
        if(TextUtils.isEmpty(oldPassword)){
            Toast.makeText(context,"请输入原密码",Toast.LENGTH_SHORT).show();
            return false;
        }
        UserModel currentUser = realmUtils.getCurrentUser();
        if(!currentUser.getPassword().equals(oldPassword)){
            Toast.makeText(context,"原密码输入错误",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(TextUtils.isEmpty(newPassword) || !newPassword.equals(newPasswordConfirm)){
            Toast.makeText(context,"请确认密码",Toast.LENGTH_SHORT).show();
            return false;
        }
        //修改数据库中的用户密码
        realmUtils.changePassword(newPassword);
        realmUtils.close();
        return true;
    }

    /**
     * 保存用户信息到数据库
     */

    private static void saveUser(UserModel userModel){
        RealmUtils realmUtils=new RealmUtils();
        realmUtils.saveUser(userModel);
        realmUtils.close();
    }
    /**
     * 判断当前手机号是否已经注册
     * 1.已经存在：返回true
     * 2.不存在：返回false
     */
    private static boolean userIsExists(String phone){
        boolean result =false;
        RealmUtils realmUtils=new RealmUtils();
        List<UserModel> allUser = realmUtils.getAllUser();
        for(UserModel userModel:allUser){
            if(userModel.getPhone().equals(phone)){
                result =true;
            }
        }
        realmUtils.close();
        return result;
    }
}
