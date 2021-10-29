package com.xxy.test;

import com.xxy.dao.impl.ExpressDaoMysql;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Map;

import static org.junit.Assert.*;

public class ExpressDaoMysqlTest {
    ExpressDaoMysql mysql = new ExpressDaoMysql();
    @Test
    public void getTotalRank() {

        Map<String, ArrayList<String>> data = mysql.getTotalRank(0, 3);
        System.out.println(data.get("nameListTotal"));
    }

    @Test
    public void getYearRank() {
        Map<String, ArrayList<String>> data = mysql.getYearRank(0, 3);
        System.out.println(data.get("nameListYear"));
    }

    @Test
    public void getMonthRank() {
    }
}