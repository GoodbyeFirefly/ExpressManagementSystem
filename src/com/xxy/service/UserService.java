package com.xxy.service;

import com.xxy.bean.User;
import com.xxy.dao.BaseUserDao;
import com.xxy.dao.impl.UserDaoMysql;

import java.util.List;

public class UserService {
    private static BaseUserDao dao = new UserDaoMysql();
    public static List<User> findAll(boolean limit, int offset, int pageNumber) {
        return dao.findAll(limit, offset, pageNumber);
    }

    public static int getTotal() {
        return dao.getTotal();
    }
}
