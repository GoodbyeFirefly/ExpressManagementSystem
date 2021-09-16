package com.xxy.controller;

import com.xxy.bean.BootstrapTableUser;
import com.xxy.bean.ResultData;
import com.xxy.bean.User;
import com.xxy.mvc.ResponseBody;
import com.xxy.service.UserService;
import com.xxy.util.DateFormatUtil;
import com.xxy.util.JSONUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class UserController {

    @ResponseBody("/user/list.do")
    public String list(HttpServletRequest req, HttpServletResponse response) {
        int offset = Integer.parseInt(req.getParameter("offset"));
        int pageNumber = Integer.parseInt(req.getParameter("pageNumber"));
        List<User> list = UserService.findAll(true, offset, pageNumber);
        List<BootstrapTableUser> list2 = new ArrayList<>();
        for (User u : list) {
            String registertime = DateFormatUtil.format(u.getRegistertime());
            String logintime = DateFormatUtil.format(u.getLogintime());
            list2.add(new BootstrapTableUser(u.getNumber(), u.getUsername(), u.getUserphone(), u.getIdcard(), u.getPassword(), registertime, logintime));
        }
        int total = UserService.getTotal();
        ResultData<BootstrapTableUser> data = new ResultData();
        data.setTotal(total);
        data.setRows(list2);
        return JSONUtil.toJSON(data);
    }
}
