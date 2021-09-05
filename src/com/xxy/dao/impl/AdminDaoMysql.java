package com.xxy.dao.impl;

import com.xxy.dao.BaseAdminDao;
import com.xxy.util.DruidUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class AdminDaoMysql implements BaseAdminDao {

    private static final String SQL_UPDATE_LOGIN_TIME = "UPDATE EADMIN SET LOGINTIME=?,LOGINIP=? WHERE USERNAME=?";
    private static final String SQL_LOGIN = "SELECT ID FROM EADMIN WHERE USERNAME=? AND PASSWORD=?";

    /**
     * 根据用户名更新登陆时间和登录ip
     *
     * @param username
     * @param date
     * @param ip
     */
    @Override
    public void updateLoginTime(String username, Date date, String ip) {
        Connection conn = DruidUtil.getConnection();
        PreparedStatement state = null;
        try {
            state = conn.prepareStatement(SQL_UPDATE_LOGIN_TIME);
            // 这里的date是util包中的类，作为参数传递方便处理。传入时需要转换为sql包中的Date类
            state.setDate(1, new java.sql.Date(date.getTime()));
            state.setString(2, ip);
            state.setString(3, username);
            state.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DruidUtil.close(conn, state, null);// 这里不需要返回结果，所以传入null
        }
    }

    /**
     * 判断登录是否成功
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    public boolean login(String username, String password) {
        Connection conn = DruidUtil.getConnection();
        PreparedStatement state = null;
        ResultSet rs = null;
        try {
            state = conn.prepareStatement(SQL_LOGIN);
            // 这里的date是util包中的类，作为参数传递方便处理。传入时需要转换为sql包中的Date类
            state.setString(1, username);
            state.setString(2, password);
            rs = state.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DruidUtil.close(conn, state, rs);// 这里不需要返回结果，所以传入null
        }
        return false;
    }
}
