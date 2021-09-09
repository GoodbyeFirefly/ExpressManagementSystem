package com.xxy.bean;

import java.sql.Timestamp;

public class BootsrapTableExpress {
    private int id;
    private String number;
    private String username;
    private String userphone;
    private String company;
    private String code;
    private String intime;
    private String outtime;
    private String status;

    public BootsrapTableExpress() {
    }

    private String sysPhone;

    public BootsrapTableExpress(int id, String number, String username, String userphone, String company, String code, String intime, String outtime, String status, String sysPhone) {
        this.id = id;
        this.number = number;
        this.username = username;
        this.userphone = userphone;
        this.company = company;
        this.code = code;
        this.intime = intime;
        this.outtime = outtime;
        this.status = status;
        this.sysPhone = sysPhone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
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

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getIntime() {
        return intime;
    }

    public void setIntime(String intime) {
        this.intime = intime;
    }

    public String getOuttime() {
        return outtime;
    }

    public void setOuttime(String outtime) {
        this.outtime = outtime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSysPhone() {
        return sysPhone;
    }

    public void setSysPhone(String sysPhone) {
        this.sysPhone = sysPhone;
    }
}
