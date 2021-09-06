package com.xxy.bean;


import java.sql.Timestamp;
import java.util.Objects;

public class Express {

  private int id;
  private String number;
  private String username;
  private String userphone;
  private String company;
  private String code;
  private Timestamp intime;
  private Timestamp outtime;
  private int status;
  private String sysPhone;

  public Express(String number, String username, String userphone, String company, String code, String sysPhone) {
    this.number = number;
    this.username = username;
    this.userphone = userphone;
    this.company = company;
    this.code = code;
    this.sysPhone = sysPhone;
  }

  public Express(int id, String number, String username, String userphone, String company, Timestamp intime, Timestamp outtime, int status, String sysPhone) {
    this.id = id;
    this.number = number;
    this.username = username;
    this.userphone = userphone;
    this.company = company;
    this.intime = intime;
    this.outtime = outtime;
    this.status = status;
    this.sysPhone = sysPhone;
  }

  public Express() {
  }

  @Override
  public String toString() {
    return "Express{" +
            "id=" + id +
            ", number='" + number + '\'' +
            ", username='" + username + '\'' +
            ", userphone='" + userphone + '\'' +
            ", company='" + company + '\'' +
            ", code='" + code + '\'' +
            ", intime=" + intime +
            ", outtime=" + outtime +
            ", status=" + status +
            ", sysPhone='" + sysPhone + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Express express = (Express) o;
    return id == express.id &&
            status == express.status &&
            Objects.equals(number, express.number) &&
            Objects.equals(username, express.username) &&
            Objects.equals(userphone, express.userphone) &&
            Objects.equals(company, express.company) &&
            Objects.equals(code, express.code) &&
            Objects.equals(intime, express.intime) &&
            Objects.equals(outtime, express.outtime) &&
            Objects.equals(sysPhone, express.sysPhone);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, number, username, userphone, company, code, intime, outtime, status, sysPhone);
  }

  public long getId() {
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


  public java.sql.Timestamp getIntime() {
    return intime;
  }

  public void setIntime(java.sql.Timestamp intime) {
    this.intime = intime;
  }


  public java.sql.Timestamp getOuttime() {
    return outtime;
  }

  public void setOuttime(java.sql.Timestamp outtime) {
    this.outtime = outtime;
  }


  public long getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }


  public String getSysPhone() {
    return sysPhone;
  }

  public void setSysPhone(String sysPhone) {
    this.sysPhone = sysPhone;
  }

}
