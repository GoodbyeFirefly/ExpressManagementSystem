package com.xxy.service;

import com.xxy.bean.Express;
import com.xxy.dao.BaseExpressDao;
import com.xxy.dao.impl.ExpressDaoMysql;
import com.xxy.exception.DuplicateCodeException;
import com.xxy.util.RandomUtil;
import com.xxy.util.SMSUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExpressService {
    private static BaseExpressDao dao = new ExpressDaoMysql();

    /**
     * 用于查询数据库中的全部快递（总数+新增），待取件快递（总数+新增）
     *
     * @return [{size:总数,day:新增},{size:总数,day:新增}]
     */
    public static List<Map<String, Integer>> console() {
        return dao.console();
    }

    /**
     * 用于查询所有快递
     *
     * @param limit      是否分页的标记，true表示分页。false表示查询所有快递
     * @param offset     SQL语句的起始索引
     * @param pageNumber 页查询的数量
     * @return 快递的集合
     */
    public static List<Express> findAll(boolean limit, int offset, int pageNumber) {
        return dao.findAll(limit, offset, pageNumber);
    }

    /**
     * 根据快递单号，查询快递信息
     *
     * @param number 单号
     * @return 查询的快递信息，单号不存在时返回null
     */
    public static Express findByNumber(String number) {
        return dao.findByNumber(number);
    }

    /**
     * 根据快递取件码，查询快递信息
     *
     * @param code 取件码
     * @return 查询的快递信息，取件码不存在时返回null
     */
    public static Express findByCode(String code) {
        return dao.findByCode(code);
    }

    /**
     * 根据用户手机号码，查询他所有的快递信息
     *
     * @param userPhone 手机号码
     * @return 查询的快递信息列表
     */
    public static List<Express> findByUserPhone(String userPhone) {
        return dao.findByUserPhone((userPhone));
    }

    /**
     * 根据录入人手机号码，查询录入的所有记录
     *
     * @param sysPhone 手机号码
     * @return 查询的快递信息列表
     */
    public static List<Express> findBySysPhone(String sysPhone) {
        return dao.findBySysPhone(sysPhone);
    }

    /**
     * 快递的录入
     *
     * @param e 要录入的快递对象
     * @return 录入的结果，true表示成功，false表示失败
     */
    public static boolean insert(Express e) {
        e.setCode(RandomUtil.getCode()+"");
        try {
            boolean insert = dao.insert(e);
            boolean send = false;
            if (insert) {
                send = SMSUtil.send(e.getUserphone(), e.getCode());
            }
            return send;
        } catch (DuplicateCodeException e1) {
            // 捕获到取件码重复后 递归调用插入
            return insert(e);
        }
    }

    /**
     * 快递的修改
     *
     * @param id         要修改的快递id
     * @param newExpress 新的快递对象（number，company,username,userPhone）
     * @return 修改的结果，true表示成功，false表示失败
     */
    public static boolean update(int id, Express newExpress) {
        // 个人理解：当有修改收件人号码的需求时，由于涉及到重发短信的业务，需要重新执行插入（插入时，会向用户发送短信）
        if (newExpress.getUserphone() != null) {
            dao.delete(id);
            return insert(newExpress);
        } else {
            // 这里的逻辑感觉不是很清晰，注释上修改的是userPhone，后面SQL语句中却替换为status，后面有问题再来修改吧
            boolean update = dao.update(id, newExpress);
            Express e = dao.findByNumber(newExpress.getNumber());
            if (newExpress.getStatus() == 1) {
                updateStatus(e.getCode());
            }
            return update;
        }
    }

    /**
     * 更改快递的状态为1，表示取件完成
     *
     * @param code 要修改的快递取件码
     * @return 修改的结果，true表示成功，false表示失败
     */
    public static boolean updateStatus(String code) {
        return dao.updateStatus(code);
    }

    /**
     * 根据id，删除单个快递信息
     *
     * @param id 要删除的快递id
     * @return 删除的结果，true表示成功，false表示失败
     */
    public static boolean delete(int id) {
        return dao.delete(id);
    }

    public static List<Express> findByUserPhoneAndStatus(String userPhone, int status) {
        return dao.findByUserPhoneAndStatus(userPhone, status);
    }

    public static Map<String, ArrayList<String>> getTotalRankData(int offset, int pageNum) {
        return dao.getTotalRank(offset, pageNum);
    }

    public static Map<String, ArrayList<String>> getYearRankData(int offset, int pageNum) {
        return dao.getYearRank(offset, pageNum);
    }

    public static Map<String, ArrayList<String>> getMonthRankData(int offset, int pageNum) {
        return dao.getMonthRank(offset, pageNum);
    }
}
