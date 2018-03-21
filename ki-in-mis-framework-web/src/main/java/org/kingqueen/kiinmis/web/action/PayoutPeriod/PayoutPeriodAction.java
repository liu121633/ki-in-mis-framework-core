package org.kingqueen.kiinmis.web.action.PayoutPeriod;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Colour;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import org.apache.commons.io.FileUtils;
import org.kingqueen.kiinmis.model.biz.PayoutPeriod.PayoutPeriodBiz;
import org.kingqueen.kiinmis.model.eaysui.pojo.RequestDatagrid;
import org.kingqueen.kiinmis.model.eaysui.pojo.ResponseDatagrid;
import org.kingqueen.kiinmis.model.pojo.Grade;
import org.kingqueen.kiinmis.model.pojo.Payoutperiod;
import org.kingqueen.kiinmis.model.pojo.User;
import org.kingqueen.kiinmis.model.vo.Payment.PaymentViewList;
import org.kingqueen.kiinmis.model.vo.PayoutPeriod.PayoutPeriodViewList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;

@Controller
@RequestMapping("PayoutPeriod")
public class PayoutPeriodAction {

    // 缴费期biz对象
    @Autowired
    public PayoutPeriodBiz payoutPeriodBiz;

    // 会话对象
    @Autowired
    public HttpSession session;

    // 请求对象
    @Autowired
    public HttpServletRequest request;

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public Payoutperiod findOne(@PathVariable("id") String id) {
        return payoutPeriodBiz.findOne(id);
    }


    /**
     * @return String
     * @throws Exception
     * @title: 查看缴费详细
     * @description: 查看缴费详细
     */
    @RequestMapping("toPayoutPeriodDetail")
    public String toPayoutPeriodDetail(String id) {
        PayoutPeriodViewList payoutPeriodViewList = payoutPeriodBiz.toPayoutPeriodDetail(id);

        //缴费期的信息
        request.setAttribute("payoutPeriodViewList", payoutPeriodViewList);
        //id 用于到界面 ajax 查询数据
        request.setAttribute("id", id);

        return "PayoutPeriod/PayoutPeriodDetail";
    }

    /**
     * @return String
     * @throws Exception
     * @title: 打印缴费期
     * @description: 功能描述
     */
    @RequestMapping("print")
    @ResponseBody
    public String print(@RequestBody PayoutPeriodViewList payoutPeriodViewList) {
        List<PayoutPeriodViewList> list = payoutPeriodBiz
                .findAll(payoutPeriodViewList);

        String tab = "<table border='1' cellspacing='0' cellpadding='1'>\n";

        tab += "<tr><th>序号</th><th>缴费期名称</th><th>创建用户</th>"
                + "<th>创建时间</th><th>状态</th><th>备注</th><tr>";
        int i = 1;
        for (PayoutPeriodViewList p : list) {
            String status = "";
            if (p.getPaymentPeriodStatus().equals("0")) {
                status = "正常";
            } else {
                status = "注销";
            }
            tab += "<tr>" + "<td>" + i++ + "</td>" + "<td>"
                    + p.getNameOfPaymentPeriod() + "</td>" + "<td>"
                    + p.getUserName() + "</td>" + "<td>"
                    + p.getPaymentPeriodCreationTime() + "</td>" + "<td>"
                    + status + "</td>" + "<td>" + p.getRemarksOnPaymentPeriod()
                    + "</td>" + "</tr>";
        }
        tab += "</table>";
        return tab;
    }

    /**
     * @return String
     * @throws Exception
     * @title: 打开缴费期添加界面
     * @description: 打开缴费期添加界面
     */
    @RequestMapping("toadd")
    public String toadd() {
        return "PayoutPeriod/add";
    }

    /**
     * @return String
     * @throws Exception
     * @title: 打开缴费期修改页面
     * @description: 打开缴费期修改页面
     */
    @RequestMapping("toupdate")
    public String toupdate(String id) {
        request.setAttribute("payoutPeriod", payoutPeriodBiz.findUpdate(id));
        return "PayoutPeriod/update";
    }

    /**
     * @return Map<String                               ,                               String>
     * @throws Exception
     * @title: 缴费期修改
     * @description: 缴费期修改
     */
    @RequestMapping("update")
    @ResponseBody
    public Map<String, String> update(Payoutperiod payoutperiod) {

        User user = (User) session.getAttribute("user");
        //设置修改的用户
        payoutperiod.setTheLastModificationOfTheUser(user.getUserNumber());
        //设置修改时间
        payoutperiod.setLastModificationTimeOfPaymentPeriod(new Timestamp(
                new Date().getTime()));
        return payoutPeriodBiz.update(payoutperiod);

    }

    /**
     * @return String
     * @throws Exception
     * @title: 打开主功能界面
     * @description: 打开主功能界面
     */
    @RequestMapping("toindex")
    public String toPayoutPeriod() {
        return "PayoutPeriod/index";
    }

    /**
     * @return Map<String                               ,                               String>
     * @throws Exception
     * @title: 添加缴费期到数据库
     * @description: 添加缴费期到数据库
     */
    @RequestMapping("save")
    @ResponseBody
    public Map<String, String> savePayoutPeriod(Payoutperiod payoutperiod) {
        // 获取当前登陆的用户
        User user = (User) session.getAttribute("user");
        // 设置创建用户为当前用户
        payoutperiod.setPaymentPeriodCreatesUser(user.getUserNumber());
        // 调用biz
        return payoutPeriodBiz.savePayoutPeriod(payoutperiod);
    }

    /**
     * @return ResponseDatagrid
     * @throws Exception
     * @title: 缴费期分页查询的api
     * @description: 缴费期分页查询的api
     */
    @RequestMapping("find")
    @ResponseBody
    public ResponseDatagrid findPayoutPeriodList(RequestDatagrid datagrid) {
        return payoutPeriodBiz.findPayoutPeriodList(datagrid);
    }

    /**
     * @return Map<String , String>
     * @throws Exception
     * @title: 注销缴费期
     * @description: 注销缴费期
     */
    @RequestMapping("logout")
    @ResponseBody
    public Map<String, String> logout(
            @RequestBody List<PayoutPeriodViewList> payoutPeriodViewLists) {
        return payoutPeriodBiz.logout(payoutPeriodViewLists);
    }

    /**
     * @return ResponseEntity<byte                               [                               ]>
     * @throws Exception
     * @title: 用户传入条件 导出数据Excel
     * @description: 用户传入条件 导出数据Excel
     */
    @RequestMapping("derivationSelWhere")
    public ResponseEntity<byte[]> derivationSelWhere(String jsonStringWhere)
            throws Exception {
        String path = session.getServletContext().getRealPath("/static/Excel");

        PayoutPeriodViewList payoutPeriodViewList = JSON.parseObject(
                jsonStringWhere, PayoutPeriodViewList.class);

        path = path + "/" + UUID.randomUUID() + ".xls";

        File file = payoutPeriodBiz.derivationSelWhere(path,
                payoutPeriodViewList);

        HttpHeaders headers = new HttpHeaders();
        String fileName = new String("缴费期信息.xls".getBytes("UTF-8"),
                "iso-8859-1");// 为了解决中文名称乱码问题
        headers.setContentDispositionFormData("attachment", fileName);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),
                headers, HttpStatus.CREATED);

    }

    /**
     * @return boolean
     * @throws Exception
     * @title: 验证缴费期名称是否存在
     * @description: 验证缴费期名称是否存在
     */
    @RequestMapping("nameExists")
    @ResponseBody
    public boolean nameExists(String name) {
        return payoutPeriodBiz.nameExists(name);
    }

    /**
     * 查询所有未注销的缴费期
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("findlist")
    public List<Payoutperiod> findlist() {
        return payoutPeriodBiz.findlist();
    }

}
