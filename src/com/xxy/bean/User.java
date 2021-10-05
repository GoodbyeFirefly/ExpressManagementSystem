package com.xxy.bean;

import java.sql.Timestamp;

public class User {
    private int number;
    private String username;
    private String userphone;
    private String idcard;
    private String password;
    private Timestamp registertime;
    private Timestamp logintime;
    private Boolean isUser;// 判断是否为user

    public User(String username, String userphone, String idcard, String password) {
        this.username = username;
        this.userphone = userphone;
        this.idcard = idcard;
        this.password = password;
    }

    public User(int number, String username, String userphone, String idcard, String password) {
        this.number = number;
        this.username = username;
        this.userphone = userphone;
        this.idcard = idcard;
        this.password = password;
    }

    public User() {
    }

    public User(int number, String username, String userphone, String idcard, String password, Timestamp registertime, Timestamp logintime) {
        this.number = number;
        this.username = username;
        this.userphone = userphone;
        this.idcard = idcard;
        this.password = password;
        this.registertime = registertime;
        this.logintime = logintime;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserphone() {
        return userphone;
    }

    public void setUserphone(String userphone) {
        this.userphone = userphone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getRegistertime() {
        return registertime;
    }

    public void setRegistertime(Timestamp registertime) {
        this.registertime = registertime;
    }

    public Timestamp getLogintime() {
        return logintime;
    }

    public void setLogintime(Timestamp logintime) {
        this.logintime = logintime;
    }

    public Boolean getUser() {
        return isUser;
    }

    public void setUser(Boolean user) {
        isUser = user;
    }

}
