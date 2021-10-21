package com.xxy.wx.controller;

import com.xxy.bean.BootstrapTableExpress;
import com.xxy.bean.Express;
import com.xxy.bean.Message;
import com.xxy.bean.User;
import com.xxy.mvc.ResponseBody;
import com.xxy.service.ExpressService;
import com.xxy.service.UserService;
import com.xxy.util.DateFormatUtil;
import com.xxy.util.JSONUtil;
import com.xxy.util.UserUtil;
import sun.plugin2.message.JavaScriptBaseMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class ExpressController {

    @ResponseBody("/wx/findExpressByUserPhone.do")
    public String findByUserPhone(HttpServletRequest request, HttpServletResponse response) {
        User wxUser = UserUtil.getWxUser(request.getSession());
        String userphone = wxUser.getUserphone();
        List<Express> expresses = ExpressService.findByUserPhone(userphone);
        List<BootstrapTableExpress> list2 = new ArrayList<>();
        for(Express e:expresses){
            String inTime = DateFormatUtil.format(e.getIntime());
            String outTime = e.getOuttime()==null?"未出库":DateFormatUtil.format(e.getOuttime());
            String status = e.getStatus()==0?"待取件":"已取件";
            String code = e.getCode()==null?"已取件":e.getCode();
            BootstrapTableExpress e2 = new BootstrapTableExpress(e.getId(),e.getNumber(),e.getUsername(),e.getUserphone(),e.getCompany(),code,inTime,outTime,status,e.getSysPhone());
            list2.add(e2);
        }

        Message msg = new Message();
        if (expresses.size() == 0) {
            msg.setStatus(-1);
        } else {
            msg.setStatus(0);
            // 从对象列表中挑选出status为0的快递对象，并按入库时间进行排序
            Stream<BootstrapTableExpress> status0Express = list2.stream().filter(express -> {
                if (express.getStatus().equals("待取件")) {
                    return true;
                } else {
                    return false;
                }
            }).sorted((o1,o2) -> {
                long o1Time = DateFormatUtil.toTime(o1.getIntime());
                long o2Time = DateFormatUtil.toTime(o2.getIntime());
                return (int)(o1Time-o2Time);
            });
            Stream<BootstrapTableExpress> status1Express = list2.stream().filter(express -> {
                if (express.getStatus().equals("已取件")) {
                    return true;
                } else {
                    return false;
                }
            }).sorted((o1,o2) -> {
                long o1Time = DateFormatUtil.toTime(o1.getIntime());
                long o2Time = DateFormatUtil.toTime(o2.getIntime());
                return (int)(o1Time-o2Time);
            });
            Object[] s0 = status0Express.toArray();
            Object[] s1 = status1Express.toArray();
            Map data = new HashMap<>();
            data.put("status0",s0);
            data.put("status1",s1);
            msg.setData(data);
        }
        return JSONUtil.toJSON(msg);
    }

    @ResponseBody(("/wx/UserExpress.do"))
    public String userList(HttpServletRequest request, HttpServletResponse response) {
        String userPhone = request.getParameter("userPhone");
        List<Express> list = ExpressService.findByUserPhoneAndStatus(userPhone, 0);
        List<BootstrapTableExpress> list2 = new ArrayList<>();
        for(Express e:list){
            String inTime = DateFormatUtil.format(e.getIntime());
            String outTime = e.getOuttime()==null?"未出库":DateFormatUtil.format(e.getOuttime());
            String status = e.getStatus()==0?"待取件":"已取件";
            String code = e.getCode()==null?"已取件":e.getCode();
            BootstrapTableExpress e2 = new BootstrapTableExpress(e.getId(),e.getNumber(),e.getUsername(),e.getUserphone(),e.getCompany(),code,inTime,outTime,status,e.getSysPhone());
            list2.add(e2);
        }
        Message msg = new Message();
        if (list.size() == 0) {
            msg.setStatus(-1);
            msg.setResult("未查询到快递");
        } else {
            msg.setStatus(0);
            msg.setResult("查询成功");
            msg.setData(list2);
        }
        return JSONUtil.toJSON(msg);
    }

    @ResponseBody("/wx/updateStatus.do")
    public String updateExpressStatus(HttpServletRequest request,HttpServletResponse response){
        String code = request.getParameter("code");
        boolean flag = ExpressService.updateStatus(code);
        Message msg = new Message();
        if(flag){
            msg.setStatus(0);
            msg.setResult("取件成功");
        }else{
            msg.setStatus(-1);
            msg.setResult("取件码不存在,请用户更新二维码");
        }
        String json = JSONUtil.toJSON(msg);
        return json;
    }

    @ResponseBody("/wx/findExpressByCode.do")
    public String findExpressByCode(HttpServletRequest request, HttpServletResponse response) {
        String code = request.getParameter("code");
        Express e = ExpressService.findByCode(code);
        BootstrapTableExpress e2 =null;
        if(e!=null){
            e2 = new BootstrapTableExpress(e.getId(), e.getNumber(), e.getUsername()
                    , e.getUserphone(), e.getCompany(), e.getCode(),
                    DateFormatUtil.format(e.getIntime()), e.getOuttime()==null?"未出库":DateFormatUtil.format(e.getOuttime()),e.getStatus()==0?"待取件":"已取件", e.getSysPhone());
        }
        Message msg = new Message();
        if (e == null) {
            msg.setStatus(-1);
            msg.setResult("取件码不存在");
        } else {
            msg.setStatus(0);
            msg.setResult("查询成功");
            msg.setData(e2);
        }
        return JSONUtil.toJSON(msg);
    }

    @ResponseBody("/wx/insertExpress.do")
    public String insertExpress(HttpServletRequest request, HttpServletResponse response) {
        String number = request.getParameter("number");
        String company = request.getParameter("company");
        String username = request.getParameter("username");
        String userphone = request.getParameter("userphone");
        boolean insert = ExpressService.insert(new Express(number, username, userphone, company, UserUtil.getUserphone(request.getSession())));
        Message msg = new Message();
        if (insert) {
            msg.setStatus(0);
            msg.setResult("快递录入成功");
        } else {
            msg.setStatus(-1);
            msg.setResult("快递录入失败");
        }

        return JSONUtil.toJSON(msg);
    }

    @ResponseBody("/wx/findExpressBySysPhone.do")
    public String findBySysPhone(HttpServletRequest request, HttpServletResponse response) {
        User wxUser = UserUtil.getWxUser(request.getSession());
        String userphone = wxUser.getUserphone();
        List<Express> expresses = ExpressService.findBySysPhone(userphone);
        List<BootstrapTableExpress> list2 = new ArrayList<>();
        for(Express e:expresses){
            String inTime = DateFormatUtil.format(e.getIntime());
            String outTime = e.getOuttime()==null?"未出库":DateFormatUtil.format(e.getOuttime());
            String status = e.getStatus()==0?"待取件":"已取件";
            String code = e.getCode()==null?"已取件":e.getCode();
            BootstrapTableExpress e2 = new BootstrapTableExpress(e.getId(),e.getNumber(),e.getUsername(),e.getUserphone(),e.getCompany(),code,inTime,outTime,status,e.getSysPhone());
            list2.add(e2);
        }

        Message msg = new Message();
        if (expresses.size() == 0) {
            msg.setStatus(-1);
        } else {
            msg.setStatus(0);
            // 从对象列表中挑选出status为0的快递对象，并按入库时间进行排序
            Stream<BootstrapTableExpress> status0Express = list2.stream().filter(express -> {
                if (express.getStatus().equals("待取件")) {
                    return true;
                } else {
                    return false;
                }
            }).sorted((o1,o2) -> {
                long o1Time = DateFormatUtil.toTime(o1.getIntime());
                long o2Time = DateFormatUtil.toTime(o2.getIntime());
                return (int)(o1Time-o2Time);
            });
            Stream<BootstrapTableExpress> status1Express = list2.stream().filter(express -> {
                if (express.getStatus().equals("已取件")) {
                    return true;
                } else {
                    return false;
                }
            }).sorted((o1,o2) -> {
                long o1Time = DateFormatUtil.toTime(o1.getIntime());
                long o2Time = DateFormatUtil.toTime(o2.getIntime());
                return (int)(o1Time-o2Time);
            });
            Object[] s0 = status0Express.toArray();
            Object[] s1 = status1Express.toArray();
            Map data = new HashMap<>();
            data.put("status0",s0);
            data.put("status1",s1);
            msg.setData(data);
        }
        return JSONUtil.toJSON(msg);
    }
}


