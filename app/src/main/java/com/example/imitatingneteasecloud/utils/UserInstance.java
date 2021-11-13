package com.example.imitatingneteasecloud.utils;

/**
 * 用户单例类
 */
public class UserInstance {
    private String phone;
    public static UserInstance instance;
    public UserInstance() {

    }
    public static UserInstance getInstance(){
        if(instance == null){
            synchronized (UserInstance.class){
                if (instance == null){
                    instance=new UserInstance();
                }
            }
        }
        return  instance;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
