package com.xxy.bean;

public class BootstrapTableCourier {
    private int number;
    private String couriername;
    private String courierphone;
    private String idcard;
    private String password;
    private int count;
    private String registertime;
    private String logintime;

    public BootstrapTableCourier() {
    }

    public BootstrapTableCourier(int number, String couriername, String courierphone, String idcard, String password, int count, String registertime, String logintime) {
        this.number = number;
        this.couriername = couriername;
        this.courierphone = courierphone;
        this.idcard = idcard;
        this.password = password;
        this.count = count;
        this.registertime = registertime;
        this.logintime = logintime;
    }

    public int getId() {
        return number;
    }

    public void setId(int number) {
        this.number = number;
    }

    public String getCouriername() {
        return couriername;
    }

    public void setCouriername(String couriername) {
        this.couriername = couriername;
    }

    public String getCourierphone() {
        return courierphone;
    }

    public void setCourierphone(String courierphone) {
        this.courierphone = courierphone;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
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
