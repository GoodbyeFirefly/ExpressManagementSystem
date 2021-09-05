package com.xxy.controller;

import com.xxy.bean.Message;
import com.xxy.mvc.ResponseBody;
import com.xxy.service.AdminService;
import com.xxy.util.JSONUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

public class AdminController {

    @ResponseBody("/admin/login.do")
    public String login(HttpServletRequest req, HttpServletResponse resp) {
        // 1, 接收参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        // 2, 调用service传递参数，并获取结果
        boolean result = AdminService.login(username, password);
        // 3, 根据结果准备不同的返回数据
        Message msg = null;
        if (result) {
            msg = new Message(0, "登录成功");
            Date date = new Date();
            String ip = req.getRemoteAddr();
            AdminService.updateLoginTimeAndIP(username, date, ip);
        } else {
            msg = new Message(-1, "登录失败");
        }
        // 4, 将数据转换为JSON
        String json = JSONUtil.toJSON(msg);
        // 5, 将JSON返回给ajax
        return json;

    }
}
