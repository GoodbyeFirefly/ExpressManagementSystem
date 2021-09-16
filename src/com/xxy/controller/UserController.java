package com.xxy.controller;

import com.xxy.bean.BootstrapTableUser;
import com.xxy.bean.Message;
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
    public String list(HttpServletRequest req, HttpServletResponse resp) {
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

    @ResponseBody("/user/insert.do")
    public String insert(HttpServletRequest req, HttpServletResponse resp) {
        String username = req.getParameter("username");
        String userphone = req.getParameter("userphone");
        String idcard = req.getParameter("idcard");
        String password = req.getParameter("password");
        Boolean result = UserService.insert(new User(username, userphone, idcard, password));
        Message msg = new Message();
        if (result) {
            msg.setStatus(0);
            msg.setResult("插入成功");
        } else {
            msg.setStatus(-1);
            msg.setResult("插入失败");
        }
        return JSONUtil.toJSON(msg);
    }

    @ResponseBody("/user/find.do")
    public String find(HttpServletRequest req, HttpServletResponse resp) {
        String userphone = req.getParameter("userphone");
        User user = UserService.findByPhone(userphone);
        Message msg = new Message();
        if (user != null) {
            msg.setStatus(0);
            msg.setResult("查找成功");
        } else {
            msg.setStatus(-1);
            msg.setResult("查找失败");
        }
        msg.setData(user);
        return JSONUtil.toJSON(msg);
    }

    @ResponseBody("/user/update.do")
    public String update(HttpServletRequest req, HttpServletResponse response) {
        String username = req.getParameter("username");
        String userphone = req.getParameter("userphone");
        String idcard = req.getParameter("idcard");
        String password = req.getParameter("password");
        int number = Integer.parseInt(req.getParameter("number"));
        Boolean result = UserService.update(new User(number, username, userphone, idcard, password));
        Message msg = new Message();
        if (result) {
            msg.setStatus(0);
            msg.setResult("修改成功");
        } else {
            msg.setStatus(-1);
            msg.setResult("修改失败");
        }
        return JSONUtil.toJSON(msg);
    }

    @ResponseBody("/user/delete.do")
    public String delete(HttpServletRequest req, HttpServletResponse response) {
        String number = req.getParameter("number");
        Boolean result = UserService.delete(number);
        Message msg = new Message();
        if (result) {
            msg.setStatus(0);
            msg.setResult("删除成功");
        } else {
            msg.setStatus(-1);
            msg.setResult("删除失败");
        }
        return JSONUtil.toJSON(msg);
    }
}
