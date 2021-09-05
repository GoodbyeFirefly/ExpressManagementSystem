package com.xxy.dao;

import java.util.Date;

public interface BaseAdminDao {
    /**
     * 根据用户名更新登陆时间和登录ip
     * @param username
     * @param date
     * @param ip
     */
    void updateLoginTime(String username, Date date, String ip);

    /**
     * 判断登录是否成功
     * @param username
     * @param password
     * @return
     */
    boolean login(String username, String password);

}
