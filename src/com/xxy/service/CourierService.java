package com.xxy.service;

import com.xxy.bean.Courier;
import com.xxy.dao.BaseCourierDao;
import com.xxy.dao.impl.CourierDaoMysql;

import java.util.List;

public class CourierService {
    private static BaseCourierDao dao = new CourierDaoMysql();
    public static List<Courier> findall(boolean limit, int offset, int pageNumber) {
        return dao.findall(limit, offset, pageNumber);
    }

    public static int gettotal() {
        return dao.gettotal();
    }
}
