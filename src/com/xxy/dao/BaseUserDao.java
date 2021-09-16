package com.xxy.dao;

import com.xxy.bean.User;

import java.util.List;

public interface BaseUserDao {
    List<User> findAll(boolean limit, int offset, int pageNumber);

    int getTotal();
}
