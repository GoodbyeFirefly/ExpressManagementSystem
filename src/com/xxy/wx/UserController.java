package com.xxy.wx;

import com.xxy.bean.Message;
import com.xxy.bean.User;
import com.xxy.mvc.ResponseBody;
import com.xxy.service.CourierService;
import com.xxy.service.UserService;
import com.xxy.util.JSONUtil;
import com.xxy.util.RandomUtil;
import com.xxy.util.SMSUtil;
import com.xxy.util.UserUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
            } else {
                // 普通用户登录
                msg.setStatus(0);
                user.setUser(true);
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
}
