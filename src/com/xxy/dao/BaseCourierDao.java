package com.xxy.dao;

import com.xxy.bean.Courier;

import java.util.List;

public interface BaseCourierDao {
    List<Courier> findall(boolean limit, int offset, int pageNumber);

    int gettotal();
}
