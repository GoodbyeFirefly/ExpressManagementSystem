package com.xxy.dao;

import com.xxy.bean.Courier;

import java.util.List;
import java.util.Map;

public interface BaseCourierDao {
    List<Courier> findall(boolean limit, int offset, int pageNumber);

    int gettotal();

    Boolean insert(Courier courier);

    Courier findByPhone(String courierphone);

    Boolean update(Courier courier);

    Boolean delete(String number);

    Map<String, Integer> console();
}
