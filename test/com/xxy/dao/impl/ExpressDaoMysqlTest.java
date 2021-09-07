package com.xxy.dao.impl;

import com.xxy.bean.Express;
import com.xxy.exception.DuplicateCodeException;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class ExpressDaoMysqlTest {
    ExpressDaoMysql dao = new ExpressDaoMysql();

    @Test
    public void console() {
        List<Map<String, Integer>> console = dao.console();
        System.out.println(console);
    }

    @Test
    public void findAll() {
//        List<Express> all = dao.findAll(true, 2, 3);// 从2开始查3个(offset从0开始)
        List<Express> all = dao.findAll(false, 0, 0);
        System.out.println(all);
    }

    @Test
    public void findByNumber() {
        Express e = dao.findByNumber("103");
        System.out.println(e);
    }

    @Test
    public void findByCode() {
        Express e = dao.findByCode("123456");
        System.out.println(e);
    }

    @Test
    public void findByUserPhone() {
        List<Express> byUserPhone = dao.findByUserPhone("13838411438");
        System.out.println(byUserPhone);
    }

    @Test
    public void findBySysPhone() {
        List<Express> bySysPhone = dao.findBySysPhone("16666666666");
        System.out.println(bySysPhone);
    }

    @Test
    public void insert() {
        Express e = new Express("123","迪卢克","18516955565","顺丰快递","123456","18888888888");
        boolean result = false;
        try {
            result = dao.insert(e);
        } catch (DuplicateCodeException e1) {
            System.out.println("捕获取件码重复异常");
        }
        System.out.println(result);
    }

    @Test
    public void update() {

    }

    @Test
    public void updateStatus() {
    }

    @Test
    public void delete() {
    }
}