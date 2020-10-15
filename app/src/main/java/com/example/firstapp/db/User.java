package com.example.firstapp.db;

import cn.bmob.v3.BmobUser;

/**
 * author : 刘雨轩
 * e-mail : 1262610086@qq.com
 * date   : 2020/10/14
 * desc   :自建Bmob用户类，用于存储一些用户的信息
 */
public class User extends BmobUser {
    private String name;
    private String address;

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }
}
