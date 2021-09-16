package com.xxy.bean;

public class BootstrapTableUser {
    private int number;
    private String username;
    private String userphone;
    private String idcard;
    private String password;
    private String registertime;
    private String logintime;

    public BootstrapTableUser() {
    }

    public BootstrapTableUser(int number, String username, String userphone, String idcard, String password, String registertime, String logintime) {
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

    public String getRegistertime() {
        return registertime;
    }

    public void setRegistertime(String registertime) {
        this.registertime = registertime;
    }

    public String getLogintime() {
        return logintime;
    }

    public void setLogintime(String logintime) {
        this.logintime = logintime;
    }
}
