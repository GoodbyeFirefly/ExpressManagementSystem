package com.xxy.service;

import com.xxy.bean.User;
import com.xxy.dao.BaseUserDao;
import com.xxy.dao.impl.UserDaoMysql;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserService {
    private static BaseUserDao dao = new UserDaoMysql();
    public static List<User> findAll(boolean limit, int offset, int pageNumber) {
        return dao.findAll(limit, offset, pageNumber);
    }

    public static int getTotal() {
        return dao.getTotal();
    }

    public static Boolean insert(User user) {
        return dao.insert(user);
    }

    public static User findByPhone(String userphone) {
        return dao.findByPhone(userphone);
    }

    public static Boolean update(User user) {
        return dao.update(user);
    }

    public static Boolean delete(String number) {
        return dao.delete(number);
    }

    public static Map<String, Integer> console() {
        return dao.console();
    }

}
