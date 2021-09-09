package com.xxy.util;

import javax.servlet.http.HttpSession;

public class UserUtil {
    public static String getUsername(HttpSession session) {
        return "";
    }

    public static String getUserphone(HttpSession session) {
        // 还未存储录入人的信息，这里先暂时代替一下
        return "18888888888";
    }
}
