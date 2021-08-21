package com.xxy.test;

import com.xxy.mvc.ResponseBody;
import com.xxy.mvc.ResponseView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserController {
    @ResponseBody("/login.do")
    public String login(HttpServletRequest req, HttpServletResponse resp) {// HttpServletxxx 别打成Httpxxx
        // 返回中文的话可能出现乱码，后面可以通过过滤器处理，这里没有必要加入到框架中（不灵活）
        return "login successfully";
    }

    @ResponseView("/reg.do")
    public String reg(HttpServletRequest req, HttpServletResponse resp) {
        return "register.jsp";
    }
}
