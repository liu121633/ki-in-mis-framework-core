package org.kingqueen.kiinmis.web.action.Payment;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.ibatis.jdbc.Null;
import org.kingqueen.kiinmis.model.biz.Payment.PaymentBiz;
import org.kingqueen.kiinmis.model.biz.PayoutPeriod.PayoutPeriodBiz;
import org.kingqueen.kiinmis.model.biz.index.IndexBiz;
import org.kingqueen.kiinmis.model.dao.hourrecord.HourrecordMapper;
import org.kingqueen.kiinmis.model.dao.index.IIndexDao;
import org.kingqueen.kiinmis.model.eaysui.pojo.RequestDatagrid;
import org.kingqueen.kiinmis.model.eaysui.pojo.ResponseDatagrid;
import org.kingqueen.kiinmis.model.eaysui.pojo.Tree;
import org.kingqueen.kiinmis.model.pojo.*;
import org.kingqueen.kiinmis.model.vo.Payment.PaymentViewList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSON;

/**
 * @author 刘洪君
 * @version V1.0
 * @ClassName PaymentAction
 * @description 缴费信息action
 * @date 2017年12月3日下午10:33:35
 */
@Controller
@RequestMapping("Payment")
public class PaymentAction {

    // 缴费期biz
    @Autowired
    private PayoutPeriodBiz payoutPeriodBiz;

    // 请求对象
    @Autowired
    public HttpServletRequest request;

    // 会话对象
    @Autowired
    private HttpSession session;

    // 缴费信息biz
    @Autowired
    private PaymentBiz paymentBiz;
    @Autowired
    private IIndexDao indexDao;


    /**
     * @return List<Tree>
     * @throws Exception
     * @title: 获得子棋院
     * @description: 功能描述
     */
    @RequestMapping("t1")
    @ResponseBody
    public List<Tree> query(String id) {
        List<Tree> list = indexDao.findJuniorKiin(id);
        return list;
    }

    /**
     * @return String
     * @throws Exception
     * @title: prInt
     * @description: 打印缴费信息
     */
    @RequestMapping("print")
    @ResponseBody
    public String prInt(@RequestBody String jsonString) {
        User user = (User) session.getAttribute("user");
        // 通过方法计算出用户 得到用户的棋院
        List<String> kiinids = indexBiz.calculateUserManagementKiin(user);
        List<PaymentViewList> paymentViewLists = paymentBiz.findAll(JSON
                .parseObject(jsonString, PaymentViewList.class), kiinids);
        String tab = "<table border='1' cellspacing='0' cellpadding='1'>\n";

        tab += "<tr><th>序号</th><th>学生姓名</th><th>缴费期</th><th>棋院</th>"
                + "<th>班级</th><th>学校</th><th>授课老师</th><th>缴费金额</th>"
                + "<th>缴费时间</th>"
                + "<th>支付方式</th>"
                + "<th>状态</th><th>备注</th>" + "<tr>";
        int i = 1;
        for (PaymentViewList p : paymentViewLists) {
            String status = "";

            if (p.getPaymentStatus().equals("0")) {
                status = "正常";
            } else {
                status = "注销";
            }

            String remarks = "";

            if (p.getPaymentRemarks() != null) {
                remarks = p.getPaymentRemarks();
            }


            if (p.getSchoolName() == null) {
                p.setSchoolName(" ");
            }

            if (p.getCoachName() == null) {
                p.setCoachName(" ");
            }

            tab += "<tr>" + "<td>" + i++ + "</td><td>"
                    + p.getNameOfStudentPaidName() + "</td>" + "<td>"
                    + p.getNameOfPaymentPeriod() + "</td>" + "<td>"
                    + p.getPkiinName() + "</td>" + "<td>" + p.getGradename()
                    + "</td>" + "<td>" + p.getSchoolName() + "</td>" + "<td>"
                    + p.getCoachName() + "</td>" + "<td>"
                    + p.getAmountPaid() + "</td>" + "</td>" + "<td>" + p.getPaymentTime() + "</td>" + "<td>" +
                    p.getPaymentUser() + "</td><td>" + status + "</td>" + "<td>" + remarks + "</td>" + "<tr>";
        }
        tab += "</table>";
        return tab;


    }

    @Autowired
    private IndexBiz indexBiz;

    /**
     * @return String
     * @throws Exception
     * @title: toindex
     * @description: 打开缴费信息主界面
     */
    @RequestMapping("index")
    public String toindex() {
        // 得到班级
        List<Grade> grades = paymentBiz.findGrade();

        request.setAttribute("grades", grades);

        User user = (User) session.getAttribute("user");
        // 通过方法计算出用户 得到用户的棋院
        List<String> kiinids = indexBiz.calculateUserManagementKiin(user);
        request.setAttribute("kiinName", kiinids.get(0));
        return "Payment/index";
    }

    /**
     * @return ResponseEntity<byte                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               [                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               ]>
     * @throws Exception
     * @title:
     * @description: 根据条件导出数据 没有条件就导出全部
     */
    @RequestMapping("derivationSelWhere")
    public ResponseEntity<byte[]> derivationSelWhere(String json)
            throws IOException {


        String path = session.getServletContext().getRealPath("/static/Excel");
        path = path + "/" + UUID.randomUUID() + ".xls";
        PaymentViewList paymentViewList = JSON.parseObject(json,
                PaymentViewList.class);
        User user = (User) session.getAttribute("user");
        // 通过方法计算出用户 得到用户的棋院
        List<String> kiinids = indexBiz.calculateUserManagementKiin(user);
        File file = paymentBiz.derivationSelWhere(paymentViewList, path, kiinids);
        HttpHeaders headers = new HttpHeaders();
        String fileName = new String("缴费记录.xls".getBytes("UTF-8"), "iso-8859-1");// 为了解决中文名称乱码问题
        headers.setContentDispositionFormData("attachment", fileName);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),
                headers, HttpStatus.CREATED);
    }

    /**
     * @return String
     * @throws Exception
     * @title:
     * @description: 打开修改页面
     */
    @RequestMapping("toupdate")
    public String toupdate(String id) {

        session.setAttribute("paymenttoupdate", id);

        PaymentViewList paymentViewList = paymentBiz.findWhereID(id);
        request.setAttribute("payment", paymentViewList);
        return "Payment/update";
    }

    /**
     * @return Map<String                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               ,                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               String>
     * @throws Exception
     * @title:
     * @description: 添加缴费记录
     */
    @RequestMapping("add")
    @ResponseBody
    public Map<String, String> add(Payment payment) {
        User user = (User) session.getAttribute("user");
        // 设置创建用户为当前用户
        payment.setPaymentCreationUser(user.getUserNumber());

        String studentNumber = session.getAttribute("studentNumber").toString();
        payment.setNameOfStudentPaid(studentNumber);
        if (studentNumber == null || studentNumber.equals("")) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("code", "400");
            map.put("msg", "错误的用户编号");
            return map;
        }
        return paymentBiz.add(payment);
    }

    /**
     * 修改缴费期
     *
     * @param payment
     * @return
     */
    @RequestMapping("update")
    @ResponseBody
    public Map<String, String> update(@RequestBody Payment payment) {
        String paymentInformationNumber = (String) session.getAttribute("paymenttoupdate");

        if (paymentInformationNumber == null ||paymentInformationNumber.equals("")) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("code", "400");
            map.put("msg", "超时!请重新打开再试");
            return map;
        }

        payment.setPaymentInformationNumber(paymentInformationNumber);
        User user = (User) session.getAttribute("user");
        payment.setPaymentModification(user.getUserNumber());
        return paymentBiz.update(payment);
    }

    /**
     * @return ResponseDatagrid
     * @throws Exception
     * @title: findPaymentList
     * @description: 缴费信息分页查询的api
     */
    @RequestMapping("find")
    @ResponseBody
    public ResponseDatagrid findPaymentList(RequestDatagrid datagrid) {
        User user = (User) session.getAttribute("user");
        // 通过方法计算出用户 得到用户的棋院
        List<String> kiinids = indexBiz.calculateUserManagementKiin(user);
        return paymentBiz.findPaymentList(datagrid, kiinids);
    }


    /***
     * @title: logout
     * @description: 作废缴费记录
     * @throws Exception
     * @return Map<String                                                               ,                                                               String>
     */
    @RequestMapping("logout")
    @ResponseBody
    public Map<String, String> logout(
            @RequestBody List<PaymentViewList> paymentViewList) {

        User user = (User) session.getAttribute("user");
        return paymentBiz.logout(paymentViewList, user);
    }

    /**
     * @return String
     * @throws Exception
     * @title: toadd
     * @description: 打开缴费记录添加界面
     */
    @RequestMapping("toadd")
    public String toadd(String id, String nameOfPaymentPeriod) {
        List<Payoutperiod> payoutperiods = paymentBiz.findPayoutPeriod();
        request.setAttribute("nameOfPaymentPeriod", nameOfPaymentPeriod);
        if (payoutperiods.size() == 0) {
            return "Payment/NoPayment";
        }
        // 学生信息
        request.setAttribute("student", paymentBiz.findStudent(id));
        // 查询未被注销的缴费期
        request.setAttribute("payoutperiods", payoutperiods);
        session.setAttribute("studentNumber", id);
        return "Payment/add";
    }

}
