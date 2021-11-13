package com.example.imitatingneteasecloud.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * 用户模型类继承自RealmObject
 * 一个模型相当于数据库中一张表
 * 模型中的属性相当于表中的一列key
 */
public class UserModel extends RealmObject {

    @PrimaryKey
    private String phone;

    @Required
    private String password;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
