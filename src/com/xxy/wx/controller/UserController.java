package com.xxy.wx.controller;

import com.xxy.bean.Courier;
import com.xxy.bean.Express;
import com.xxy.bean.Message;
import com.xxy.bean.User;
import com.xxy.mvc.ResponseBody;
import com.xxy.service.CourierService;
import com.xxy.service.ExpressService;
import com.xxy.service.UserService;
import com.xxy.util.JSONUtil;
import com.xxy.util.RandomUtil;
import com.xxy.util.SMSUtil;
import com.xxy.util.UserUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UserController {
    @ResponseBody("/wx/loginSms.do")
    public String sendSms(HttpServletRequest request, HttpServletResponse response) {
        String userPhone = request.getParameter("userPhone");
        String code = String.valueOf(RandomUtil.getCode());
        boolean flag = SMSUtil.send(userPhone, code);// 向控制台发送验证码
        Message msg = new Message();
        if (flag) {
            msg.setStatus(0);
            msg.setResult("验证码已发送，请查收");
        } else {
            msg.setStatus(-1);
            msg.setResult("验证码下发失败，请检查手机号或稍后再试");
        }
        UserUtil.setLoginSms(request.getSession(), userPhone, code);// 将手机号对应的验证码信息存入session中
        return JSONUtil.toJSON(msg);
    }

    @ResponseBody("/wx/login.do")
    public String login(HttpServletRequest request, HttpServletResponse response) {
        String userPhone = request.getParameter("userPhone");
        String userCode = request.getParameter("code");
        String sysCode = UserUtil.getLoginSms(request.getSession(), userPhone);// 获取存储在session中的信息
        Message msg = new Message();
        if (sysCode == null) {
            // 该手机号未获取短信
            msg.setStatus(-1);
            msg.setResult("该手机号码未获取短信");
        } else if (sysCode.equals(userCode)) {
            User user = new User();
            user.setUserphone(userPhone);
            // 手机号和验证码一致，登录成功
            if (CourierService.findByPhone(userPhone) != null) {
                // 快递员登录（包含普通用户的权限）
                msg.setStatus(1);
                user.setUser(false);// 这是新添加的属性，用于判断该手机号是用户还是快递员
            } else if (UserService.findByPhone(userPhone) != null) {
                // 普通用户登录
                msg.setStatus(0);
                user.setUser(true);
            } else {
                // 用户不存在
                msg.setStatus(-3);
                msg.setResult("该用户不存在，正在前往注册页面");
                return JSONUtil.toJSON(msg);
            }

            UserUtil.setWxUser(request.getSession(), user);// 将手机号和验证码的对应信息存入session

        } else {
            // 验证码不一致，登录失败
            msg.setStatus(-2);
            msg.setResult("验证码不一致");
        }
        return JSONUtil.toJSON(msg);
    }

    @ResponseBody("/wx/logout.do")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().invalidate();
        Message msg = new Message(0);
        return JSONUtil.toJSON(msg);
    }

    @ResponseBody("/wx/uerInfo.do")
    public String userInfo(HttpServletRequest request, HttpServletResponse response) {
        User user = UserUtil.getWxUser(request.getSession());
        Boolean isUser = user.getUser();
        Message msg = new Message();
        if (isUser)
            msg.setStatus(0);
        else
            msg.setStatus(1);
        msg.setResult(user.getUserphone());
        return JSONUtil.toJSON(msg);
    }

    @ResponseBody("/wx/register.do")
    public String register(HttpServletRequest request, HttpServletResponse response) {
        String role = request.getParameter("role");
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String idcard = request.getParameter("idcard");
        String password = request.getParameter("password");
        System.out.println(role);
        Message msg = new Message();
        if ("1".equals(role)) {
            // 注册为用户
            msg.setStatus(1);
            UserService.insert(new User(name, phone, idcard, password));
            msg.setResult("用户注册成功");
        } else if ("0".equals(role)) {
            // 注册为快递员
            msg.setStatus(0);
            CourierService.insert(new Courier(name, phone, idcard, password));
            msg.setResult("快递员注册成功");
        } else {
            // 验证码不一致，登录失败
            msg.setStatus(-1);
            msg.setResult("无法判断类型，注册失败");
        }
        return JSONUtil.toJSON(msg);
    }

    @ResponseBody("/wx/findExpressByNum.do")
    public String findExpressByNum(HttpServletRequest request, HttpServletResponse response) {
        User user = UserUtil.getWxUser(request.getSession());
        Boolean isUser = user.getUser();
        String expressNum = request.getParameter("expressNum");
        Express express = ExpressService.findByNumber(expressNum);
        Message msg = new Message();
        if (express == null) {
            msg.setStatus(-1);
            msg.setResult("快递单号不存在");
            return JSONUtil.toJSON(msg);
        }
        String expressMsg = "姓名：" + express.getUsername() + "； " +
                "手机号：" + express.getUserphone() + "； " +
                "公司：" + express.getCompany() + "； " +
                "取件码：" + express.getCode() + "； " +
                "状态：" + (express.getStatus() == 0 ? "未出库" : "已出库");

        if (isUser) {
            // 用户查询
            if (express.getUserphone().equals(user.getUserphone())) {
                // 快递是该用户的
                msg.setStatus(0);
                msg.setResult("查询成功");
                msg.setData(expressMsg);
            } else {
                // 快递不是该用户的
                msg.setStatus(-2);
                msg.setResult("该快递不属于此用户");
            }
        } else {
            // 快递员查询
            msg.setStatus(0);
            msg.setResult("查询成功");
            msg.setData(expressMsg);
        }
        return JSONUtil.toJSON(msg);
    }

    @ResponseBody("/wx/getUsername.do")
    public String getUsername(HttpServletRequest request, HttpServletResponse response) {
        User wxUser = UserUtil.getWxUser(request.getSession());
        Message msg = new Message();
        if (null == wxUser.getUserphone()) {
            msg.setStatus(-1);
            msg.setData("--");
        } else {
            msg.setStatus(0);
            if (wxUser.getUsername() == null) {
                if (wxUser.getUser()) {
                    User user = UserService.findByPhone(wxUser.getUserphone());
                    msg.setData(user.getUsername());
                } else {
                    Courier courier = CourierService.findByPhone(wxUser.getUserphone());
                    msg.setData(courier.getCouriername());
                }
            } else {
                msg.setData(wxUser.getUsername());
            }
        }
        return JSONUtil.toJSON(msg);
    }

    @ResponseBody("/wx/updateInfoByPhone.do")
    public String updateInfoByPhone(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");
        String code = request.getParameter("code");
        String sysCode = UserUtil.getLoginSms(request.getSession(), phone);
        User wxUser = UserUtil.getWxUser(request.getSession());
        Message msg = new Message();
        if (sysCode == null) {
            // 该手机号未获取短信
            msg.setStatus(-1);
            msg.setResult("该手机号码未获取短信");
        } else if (!sysCode.equals(code)) {
            msg.setStatus(-2);
            msg.setResult("验证码输入有误");
        } else {
            Boolean isUser = wxUser.getUser();
            boolean flag = false;
            if (isUser) {
                User newUser = UserService.findByPhone(wxUser.getUserphone());
                newUser.setUsername(name);// 修改用户名
                newUser.setUserphone(phone);// 修改手机号
                newUser.setPassword(password);
                flag = UserService.update(newUser);

                UserUtil.setWxUser(request.getSession(), newUser);
            } else {
                Courier newCourier = CourierService.findByPhone(wxUser.getUserphone());
                newCourier.setCouriername(name);
                newCourier.setCourierphone(phone);
                newCourier.setPassword(password);
                flag = CourierService.update(newCourier);

                User user = new User();
                user.setUsername(name);
                user.setUserphone(phone);
                UserUtil.setWxUser(request.getSession(), user);
            }

            if (flag) {
                msg.setStatus(0);
                msg.setResult("信息修改成功");
            } else {
                msg.setStatus(-3);
                msg.setResult("信息修改失败");
            }
        }
        return JSONUtil.toJSON(msg);
    }

    @ResponseBody("/wx/getRankData.do")
    public String getRankData(HttpServletRequest request, HttpServletResponse response) {
        Map<String, ArrayList<String>> data = new HashMap<>();
        Map<String, ArrayList<String>> map1 = ExpressService.getTotalRankData(0, 5);
        Map<String, ArrayList<String>> map2 = ExpressService.getYearRankData(0, 5);
        Map<String, ArrayList<String>> map3 = ExpressService.getMonthRankData(0, 5);
        data.put("nameListTotal", map1.get("nameListTotal"));
        data.put("scoreListTotal", map1.get("scoreListTotal"));
        data.put("nameListYear", map2.get("nameListYear"));
        data.put("scoreListYear", map2.get("scoreListYear"));
        data.put("nameListMonth", map3.get("nameListMonth"));
        data.put("scoreListMonth", map3.get("scoreListMonth"));
        Message msg = new Message();
        msg.setData(data);
        return JSONUtil.toJSON(msg);
    }

}
