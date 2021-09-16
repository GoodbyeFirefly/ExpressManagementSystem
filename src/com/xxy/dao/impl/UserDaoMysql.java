package com.xxy.dao.impl;

import com.xxy.bean.User;
import com.xxy.dao.BaseUserDao;
import com.xxy.util.DruidUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoMysql implements BaseUserDao {
    public static final String SQL_FIND_ALL = "SELECT * FROM EUSER";
    public static final String SQL_FIND_LIMIT = "SELECT * FROM EUSER LIMIT ?,?";
    public static final String SQL_GET_TOTAL = "SELECT COUNT(NUMBER) total FROM EUSER";
//    public static final String SQL_FIND_ALL = "";
//    public static final String SQL_FIND_ALL = "";
//    public static final String SQL_FIND_ALL = "";
//    public static final String SQL_FIND_ALL = "";
//    public static final String SQL_FIND_ALL = "";



    @Override
    public List<User> findAll(boolean limit, int offset, int pageNumber) {
        ArrayList<User> list = new ArrayList<>();
        Connection conn = DruidUtil.getConnection();
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            if (limit) {
                statement = conn.prepareStatement(SQL_FIND_LIMIT);
                statement.setInt(1, offset);
                statement.setInt(2, pageNumber);
            } else {
                statement = conn.prepareStatement(SQL_FIND_ALL);
            }
            rs = statement.executeQuery();
            while (rs.next()) {
                int number = rs.getInt("number");
                String username = rs.getString("username");
                String userphone = rs.getString("userphone");
                String idcard = rs.getString("idcard");
                String password = rs.getString("password");
                Timestamp registertime = rs.getTimestamp("registertime");
                Timestamp logintime = rs.getTimestamp("logintime");
                list.add(new User(number, username, userphone, idcard, password, registertime, logintime));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DruidUtil.close(conn, statement, rs);
        }
        return list;
    }

    @Override
    public int getTotal() {
        Connection conn = DruidUtil.getConnection();
        PreparedStatement statement = null;
        ResultSet rs = null;
        int total = 0;
        try {
            statement = conn.prepareStatement(SQL_GET_TOTAL);
            rs = statement.executeQuery();
            if (rs.next()) {
                total = rs.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DruidUtil.close(conn, statement, rs);
        }
        return total;
    }
}
