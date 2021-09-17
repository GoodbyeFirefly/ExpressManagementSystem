package com.xxy.controller;

import com.xxy.bean.Message;
import com.xxy.mvc.ResponseBody;
import com.xxy.mvc.ResponseView;
import com.xxy.service.AdminService;
import com.xxy.service.CourierService;
import com.xxy.service.ExpressService;
import com.xxy.service.UserService;
import com.xxy.util.JSONUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.Map;

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

            req.getSession().setAttribute("adminUsername", "username");
        } else {
            msg = new Message(-1, "登录失败");
        }
        // 4, 将数据转换为JSON
        String json = JSONUtil.toJSON(msg);
        // 5, 将JSON返回给ajax
        return json;
    }

    @ResponseView("/admin/logout.do")
    public String logout(HttpServletRequest req, HttpServletResponse resp) {
        req.getSession().invalidate();
        return "/admin/login.html";
    }

    @ResponseBody("/admin/console.do")
    public String console(HttpServletRequest req, HttpServletResponse resp) {
        List<Map<String, Integer>> data = ExpressService.console();
        data.add(UserService.console());
        data.add(CourierService.console());
        Message msg = new Message();
        if (data.size() == 0) {
            msg.setStatus(-1);
        } else {
            msg.setStatus(0);
        }
        msg.setData(data);
        String json = JSONUtil.toJSON(msg);
        return json;
    }
}
