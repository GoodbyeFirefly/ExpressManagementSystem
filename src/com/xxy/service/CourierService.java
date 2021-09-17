package com.xxy.service;

import com.xxy.bean.Courier;
import com.xxy.dao.BaseCourierDao;
import com.xxy.dao.impl.CourierDaoMysql;

import java.util.List;
import java.util.Map;

public class CourierService {
    private static BaseCourierDao dao = new CourierDaoMysql();
    public static List<Courier> findall(boolean limit, int offset, int pageNumber) {
        return dao.findall(limit, offset, pageNumber);
    }

    public static int gettotal() {
        return dao.gettotal();
    }

    public static Boolean insert(Courier courier) {
        return dao.insert(courier);
    }

    public static Courier findByPhone(String courierphone) {
        return dao.findByPhone(courierphone);
    }

    public static Boolean update(Courier courier) {
        return dao.update(courier);
    }

    public static Boolean delete(String number) {
        return dao.delete(number);
    }

    public static Map<String, Integer> console() {
        return dao.console();
    }
}
