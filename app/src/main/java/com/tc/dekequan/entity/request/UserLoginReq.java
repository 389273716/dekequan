package com.tc.dekequan.entity.request;

/**
 * author：   tc
 * date：     2016/9/5 & 20:45
 * version    1.0
 * description 登录请求实体
 * modify by
 */
public class UserLoginReq {
    private String userName;
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
