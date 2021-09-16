package com.xxy.dao;

import com.xxy.bean.User;

import java.util.List;

public interface BaseUserDao {
    List<User> findAll(boolean limit, int offset, int pageNumber);

    int getTotal();

    Boolean insert(User user);

    User findByPhone(String userphone);

    Boolean update(User user);

    Boolean delete(String number);
}
