package com.xxy.dao.impl;

import com.xxy.bean.Courier;
import com.xxy.dao.BaseCourierDao;
import com.xxy.util.DruidUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourierDaoMysql implements BaseCourierDao {
    public static final String SQL_FIND_ALL = "SELECT * FROM ECOURIER";
    public static final String SQL_FIND_LIMIT = "SELECT * FROM ECOURIER LIMIT ?,?";
    public static final String SQL_GET_TOTAL = "SELECT COUNT(ID) total FROM ECOURIER";
//    public static final String SQL_FINDALL = "";
//    public static final String SQL_FINDALL = "";
//    public static final String SQL_FINDALL = "";
//    public static final String SQL_FINDALL = "";


    @Override
    public List<Courier> findall(boolean limit, int offset, int pageNumber) {
        ArrayList<Courier> list = new ArrayList<>();
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
                int id = rs.getInt("id");
                String number = rs.getString("number");
                String couriername = rs.getString("couriername");
                String courierphone = rs.getString("courierphone");
                String idcard = rs.getString("idcard");
                String password = rs.getString("password");
                int count = rs.getInt("count");
                Timestamp registertime = rs.getTimestamp("registertime");
                Timestamp logintime = rs.getTimestamp("logintime");
                list.add(new Courier(id, number, couriername, courierphone, idcard, password, count, registertime, logintime));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DruidUtil.close(conn, statement, rs);
        }
        System.out.println(list);
        return list;
    }

    @Override
    public int gettotal() {
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
        System.out.println(total);
        return total;
    }
}
