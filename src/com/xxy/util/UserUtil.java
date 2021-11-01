package com.xxy.util;

import com.xxy.bean.User;

import javax.servlet.http.HttpSession;

public class UserUtil {
    public static String getUsername(HttpSession session) {
        return (String) session.getAttribute("adminUsername");
    }

    public static String getUserphone(HttpSession session) {
        // 还未存储录入人的信息，这里先暂时代替一下
        User wxUser = (User) session.getAttribute("wxUser");
        if (wxUser == null) return "18888888888";
        return wxUser.getUserphone();
    }
    public static String getLoginSms(HttpSession session, String userPhone) {
        return (String) session.getAttribute(userPhone);
    }
    public static void setLoginSms(HttpSession session, String userPhone, String code) {
        session.setAttribute(userPhone, code);
    }

    public static User getWxUser (HttpSession session) {
        return (User) session.getAttribute("wxUser");
    }
    public static void setWxUser(HttpSession session, User user) {
        session.setAttribute("wxUser", user);
    }
}
