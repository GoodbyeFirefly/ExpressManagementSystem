package com.xxy.dao.impl;

import com.xxy.bean.Courier;
import com.xxy.dao.BaseCourierDao;
import com.xxy.util.DruidUtil;

import javax.print.attribute.standard.RequestingUserName;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourierDaoMysql implements BaseCourierDao {
    public static final String SQL_FIND_ALL = "SELECT * FROM ECOURIER";
    public static final String SQL_FIND_LIMIT = "SELECT * FROM ECOURIER LIMIT ?,?";
    public static final String SQL_GET_TOTAL = "SELECT COUNT(NUMBER) total FROM ECOURIER";
    public static final String SQL_INSERT = "INSERT INTO ECOURIER (COURIERNAME,COURIERPHONE,IDCARD,PASSWORD,COUNT,REGISTERTIME,LOGINTIME) VALUES(?,?,?,?,0,NOW(),NOW())";
    public static final String SQL_FIND_BY_PHONE = "SELECT * FROM ECOURIER WHERE COURIERPHONE=?";
    public static final String SQL_UPDATE = "UPDATE ECOURIER SET COURIERNAME=?,COURIERPHONE=?,IDCARD=?,PASSWORD=? WHERE NUMBER=?";
    public static final String SQL_DELETE = "DELETE FROM ECOURIER WHERE NUMBER=?";
    public static final String SQL_CONSOLE = "SELECT COUNT(NUMBER) courier_size, COUNT((TO_DAYS(REGISTERTIME)=TO_DAYS(NOW())) OR NULL) courier_day FROM ECOURIER";

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
                int number = rs.getInt("number");
                String couriername = rs.getString("couriername");
                String courierphone = rs.getString("courierphone");
                String idcard = rs.getString("idcard");
                String password = rs.getString("password");
                int count = rs.getInt("count");
                Timestamp registertime = rs.getTimestamp("registertime");
                Timestamp logintime = rs.getTimestamp("logintime");
                list.add(new Courier(number, couriername, courierphone, idcard, password, count, registertime, logintime));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DruidUtil.close(conn, statement, rs);
        }
//        System.out.println(list);
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
//        System.out.println(total);
        return total;
    }

    @Override
    public Boolean insert(Courier courier) {
        Connection conn = DruidUtil.getConnection();
        PreparedStatement statement = null;
        ResultSet rs = null;
        int total = 0;
        try {
            statement = conn.prepareStatement(SQL_INSERT);
            statement.setString(1, courier.getCouriername());
            statement.setString(2, courier.getCourierphone());
            statement.setString(3, courier.getIdcard());
            statement.setString(4, courier.getPassword());
            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DruidUtil.close(conn, statement, rs);
        }
        return false;
    }

    @Override
    public Courier findByPhone(String courierphone) {
        Connection conn = DruidUtil.getConnection();
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            statement = conn.prepareStatement(SQL_FIND_BY_PHONE);
            statement.setString(1, courierphone);
            rs = statement.executeQuery();
            if (rs.next()) {
                int number = rs.getInt("number");
                String couriername = rs.getString("couriername");
                String courierphone1 = rs.getString("courierphone");
                String idcard = rs.getString("idcard");
                String password = rs.getString("password");
                return new Courier(number, couriername, courierphone1, idcard, password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DruidUtil.close(conn, statement, rs);
        }
        return null;
    }

    @Override
    public Boolean update(Courier courier) {
        Connection conn = DruidUtil.getConnection();
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            statement = conn.prepareStatement(SQL_UPDATE);
            statement.setString(1, courier.getCouriername());
            statement.setString(2, courier.getCourierphone());
            statement.setString(3, courier.getIdcard());
            statement.setString(4, courier.getPassword());
            statement.setString(5, Integer.toString(courier.getNumber()));
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
        try {
            statement = conn.prepareStatement(SQL_DELETE);
            statement.setString(1, number);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            DruidUtil.close(conn, statement, null);
        }
        return false;
    }

    @Override
    public Map<String, Integer> console() {
        Map<String, Integer> data = new HashMap<>();
        Connection conn = DruidUtil.getConnection();
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            statement = conn.prepareStatement(SQL_CONSOLE);
            rs = statement.executeQuery();
            if (rs.next()) {
                int courier_size = rs.getInt("courier_size");
                int courier_day = rs.getInt("courier_day");
                data.put("courier_size", courier_size);
                data.put("courier_day", courier_day);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DruidUtil.close(conn, statement, rs);
        }
        return data;
    }
}
