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
    public static final String SQL_INSERT = "INSERT INTO EUSER (USERNAME,USERPHONE,IDCARD,PASSWORD,REGISTERTIME,LOGINTIME) VALUES(?,?,?,?,NOW(),NOW())";
    public static final String SQL_FIND_BY_PHONE = "SELECT * FROM EUSER WHERE USERPHONE=?";
    public static final String SQL_UPDATE = "UPDATE EUSER SET USERNAME=?,USERPHONE=?,IDCARD=?,PASSWORD=? WHERE NUMBER=?";
    public static final String SQL_DELETE = "DELETE FROM EUSER WHERE NUMBER=?";
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

    @Override
    public Boolean insert(User user) {
        Connection conn = DruidUtil.getConnection();
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            statement = conn.prepareStatement(SQL_INSERT);
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getUserphone());
            statement.setString(3, user.getIdcard());
            statement.setString(4, user.getPassword());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DruidUtil.close(conn, statement, rs);
        }
        return false;
    }

    @Override
    public User findByPhone(String userphone) {
        Connection conn = DruidUtil.getConnection();
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            statement = conn.prepareStatement(SQL_FIND_BY_PHONE);
            statement.setString(1, userphone);
            rs = statement.executeQuery();
            User user = null;
            if (rs.next()) {
                int number = rs.getInt("number");
                String username = rs.getString("username");
                String idcard = rs.getString("idcard");
                String password = rs.getString("password");
                Timestamp registertime = rs.getTimestamp("registertime");
                Timestamp logintime = rs.getTimestamp("logintime");
                user = new User(number, username, userphone, idcard, password, registertime, logintime);
            }
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DruidUtil.close(conn, statement, rs);
        }
        return null;
    }

    @Override
    public Boolean update(User user) {
        Connection conn = DruidUtil.getConnection();
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            statement = conn.prepareStatement(SQL_UPDATE);
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getUserphone());
            statement.setString(3, user.getIdcard());
            statement.setString(4, user.getPassword());
            statement.setInt(5, user.getNumber());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DruidUtil.close(conn, statement, rs);
        }
        return false;
    }

    @Override
    public Boolean delete(String number) {
        Connection conn = DruidUtil.getConnection();
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            statement = conn.prepareStatement(SQL_DELETE);
            statement.setInt(1, Integer.parseInt(number));
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DruidUtil.close(conn, statement, rs);
        }
        return false;
    }
}
