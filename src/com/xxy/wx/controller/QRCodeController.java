package com.xxy.wx.controller;

import com.xxy.bean.Message;
import com.xxy.mvc.ResponseBody;
import com.xxy.mvc.ResponseView;
import com.xxy.util.JSONUtil;
import com.xxy.util.UserUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class QRCodeController {

    @ResponseView("/wx/createQRCode.do")
    public String createQRCode(HttpServletRequest request, HttpServletResponse response) {
        String type = request.getParameter("type");
        String code = null;
        String userPhone = null;
        String QRCodeContent = null;
        if ("express".equals(type)) {
            code = request.getParameter("code");
            QRCodeContent = "express_".concat(code);
        } else {
            // 这里要先获取微信用户，再获取其电话号码
            userPhone = UserUtil.getWxUser(request.getSession()).getUserphone();
            QRCodeContent = "userPhone_".concat(userPhone);
        }
        HttpSession session = request.getSession();
        session.setAttribute("qrcode", QRCodeContent);
        return "/personQRcode.html";
    }

    @ResponseBody("/wx/qrcode.do")
    public String getQRCode(HttpServletRequest request, HttpServletResponse response) {
        String qrcode = (String) request.getSession().getAttribute("qrcode");
        Message msg = new Message();
        if (qrcode == null) {
            msg.setStatus(-1);
            msg.setResult("取件码获取出错，请用户重新操作");
        } else {
            msg.setStatus(0);
            msg.setResult(qrcode);
        }
        return JSONUtil.toJSON(msg);
    }
}
