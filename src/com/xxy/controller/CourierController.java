package com.xxy.controller;

import com.xxy.bean.BootstrapTableCourier;
import com.xxy.bean.Courier;
import com.xxy.bean.Message;
import com.xxy.bean.ResultData;
import com.xxy.mvc.ResponseBody;
import com.xxy.service.CourierService;
import com.xxy.util.DateFormatUtil;
import com.xxy.util.JSONUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class CourierController {

    @ResponseBody("/courier/list.do")
    public String list(HttpServletRequest req, HttpServletResponse resp) {
        int offset = Integer.parseInt(req.getParameter("offset"));
        int pageNumber = Integer.parseInt(req.getParameter("pageNumber"));

        List<Courier> list = CourierService.findall(true, offset, pageNumber);
        List<BootstrapTableCourier> list2 = new ArrayList<>();
        for (Courier c : list) {
            String regiterTime = DateFormatUtil.format(c.getRegistertime());
            String loginTime = DateFormatUtil.format(c.getLogintime());
            list2.add(new BootstrapTableCourier(c.getNumber(), c.getCouriername(), c.getCourierphone(), c.getIdcard(), c.getPassword(), c.getCount(), regiterTime, loginTime));
        }
        int total = CourierService.gettotal();
        ResultData<BootstrapTableCourier> data = new ResultData<>();
        data.setRows(list2);
        data.setTotal(total);
        return JSONUtil.toJSON(data);
    }

    @ResponseBody("/courier/insert.do")
    public String insert(HttpServletRequest req, HttpServletResponse resp) {
        String couriername = req.getParameter("couriername");
        String courierphone = req.getParameter("courierphone");
        String idcard = req.getParameter("idcard");
        String password = req.getParameter("password");

        int total = CourierService.gettotal();
        Courier courier = new Courier(total, couriername, courierphone, idcard, password);
        Boolean flag = CourierService.insert(courier);
        Message msg = new Message();
        if (flag) {
            msg.setStatus(0);
            msg.setResult("录入成功");
        } else {
            msg.setStatus(-1);
            msg.setResult("录入失败");
        }
        return JSONUtil.toJSON(msg);
    }

    @ResponseBody("/courier/find.do")
    public String find(HttpServletRequest req, HttpServletResponse resp) {
        String courierphone = req.getParameter("courierphone");
        Courier courier = CourierService.findByPhone(courierphone);
        Message msg = new Message();
        if (courier != null) {
            msg.setResult("查找成功");
            msg.setStatus(0);
        } else {
            msg.setResult("查找失败");
            msg.setStatus(-1);
        }
        msg.setData(courier);
        return JSONUtil.toJSON(msg);
    }

    @ResponseBody("/courier/update.do")
    public String update(HttpServletRequest req, HttpServletResponse resp) {
        int number = Integer.parseInt(req.getParameter("number"));
        String couriername = req.getParameter("couriername");
        String courierphone = req.getParameter("courierphone");
        String idcard = req.getParameter("idcard");
        String password = req.getParameter("password");
        Courier courier = new Courier(number, couriername, courierphone, idcard, password);
        Boolean flag = CourierService.update(courier);
        Message msg = new Message();
        if (flag) {
            msg.setResult("修改成功");
            msg.setStatus(0);
        } else {
            msg.setResult("修改失败");
            msg.setStatus(-1);
        }
        return JSONUtil.toJSON(msg);
    }
    @ResponseBody("/courier/delete.do")
    public String delete(HttpServletRequest req, HttpServletResponse resp) {
        String number = req.getParameter("number");
        Boolean flag = CourierService.delete(number);
        Message msg = new Message();
        if (flag) {
            msg.setStatus(0);
            msg.setResult("删除成功");
        } else {
            msg.setStatus(-1);
            msg.setResult("删除失败");
        }
        return JSONUtil.toJSON(msg);
    }
}
