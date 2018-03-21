package org.kingqueen.kiinmis.model.biz.PayoutPeriod;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import jxl.Workbook;
import jxl.format.Colour;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.commons.io.FileUtils;
import org.kingqueen.kiinmis.model.dao.PayoutPeriod.IPayoutPeriodDao;
import org.kingqueen.kiinmis.model.eaysui.pojo.RequestDatagrid;
import org.kingqueen.kiinmis.model.eaysui.pojo.ResponseDatagrid;
import org.kingqueen.kiinmis.model.pojo.Grade;
import org.kingqueen.kiinmis.model.pojo.Payoutperiod;
import org.kingqueen.kiinmis.model.pojo.User;
import org.kingqueen.kiinmis.model.vo.PayoutPeriod.PayoutPeriodViewList;
import org.kingqueen.kiinmis.uitl.ExcelUitl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;

/**
 * @author 刘洪君
 * @version V1.0
 * @ClassName PayoutPeriodBiz
 * @description 缴费期业务处理
 * @date 2017年11月28日下午9:45:06
 */
@Service("PayoutPeriodBiz")
public class PayoutPeriodBiz {

    @Autowired
    private IPayoutPeriodDao payoutPeriodDao;

    /**
     * @return int
     * @throws Exception
     * @title: nameExists
     * @description: 查询这个名称是否存在
     */
    public boolean nameExists(String name) {
        int i = payoutPeriodDao.nameExists(name);

        if (i > 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * @return List<Payoutperiod>
     * @throws Exception
     * @title: findAll
     * @description: 查询所有缴费期
     */
    public List<PayoutPeriodViewList> findAll(
            PayoutPeriodViewList payoutPeriodViewList) {
        return payoutPeriodDao.selAll(payoutPeriodViewList);
    }

    public Map<String, String> importingPayoutPeriodPlan(MultipartFile file,
                                                         String filePath, User user) {

        if (!file.isEmpty()) {
            // 先将文件保存到服务器

            StringBuffer sb = new StringBuffer(file.getOriginalFilename());

            sb.replace(0, file.getOriginalFilename().lastIndexOf("."), UUID
                    .randomUUID().toString());
            filePath = filePath + "/" + sb;

            try {
                file.transferTo(new File(filePath));
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            ExcelUitl excelUitl = new ExcelUitl();

            List<Payoutperiod> payoutperiods = JSON.parseArray(
                    excelUitl.readExcel(filePath), Payoutperiod.class);

            Map<String, String> map = new HashMap<String, String>();

            String msg = "一共读取到" + payoutperiods.size() + "条数据";

            // 统计成功的数据
            int i = 0;

            // 统计重复的数据
            int j = 0;

            for (Payoutperiod payoutperiod : payoutperiods) {
                if (!nameExists(payoutperiod.getNameOfPaymentPeriod())) {
                    if (j == 0) {
                        msg += "其中 ";
                    }
                    msg += payoutperiod.getNameOfPaymentPeriod() + ",";
                    j++;
                    continue;
                }

                // 设置id
                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                // 获得当前的时间戳
                String str = sdf.format(date);
                // 设置编号
                payoutperiod.setPaymentPeriodNumber("JFQ-" + str + i);
                // 设置默认状态
                payoutperiod.setPaymentPeriodStatus(0);
                // 设置创建时间
                payoutperiod.setPaymentPeriodCreationTime(new Timestamp(
                        new Date().getTime()));
                // 设置创建用户为当前用户
                payoutperiod.setPaymentPeriodCreatesUser(user.getUserNumber());

                if (payoutPeriodDao.savePayoutPeriod(payoutperiod) > 0) {
                    i++;
                }
            }

            if (j > 0) {
                msg += "服务器已经存在,共添加成功" + i + "条数据";
            } else {
                msg += "共添加成功" + i + "条数据";
            }

            map.put("msg", msg);
            return map;

        }

        return null;
    }

    /**
     * @return Map<String                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               ,                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               String>
     * @throws Exception
     * @title: update
     * @description: 修改缴费期
     */
    public Map<String, String> update(Payoutperiod payoutperiod) {

        Map<String, String> map = new HashMap<String, String>();
        int i = payoutPeriodDao.update(payoutperiod);
        if (i > 0) {
            map.put("code", "200");
            map.put("msg", "修改成功");
        } else {
            map.put("code", "500");
            map.put("msg", "服务器遇到未知错误,请稍后再试!");
        }
        return map;

    }

    /**
     * @return Payoutperiod
     * @throws Exception
     * @title: findUpdate
     * @description: 修改前的操作 用于查询 要修改的数据
     */
    public Payoutperiod findUpdate(String id) {
        return payoutPeriodDao.findOne(id);
    }

    /**
     * 根据id查询缴费期
     *
     * @param id
     * @return
     */
    public Payoutperiod findOne(String id) {
        return payoutPeriodDao.findOne(id);
    }

    /**
     * @return Map<String                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               ,                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               String>
     * @throws Exception
     * @title: logout
     * @description: 注销缴费期
     */
    public Map<String, String> logout(
            List<PayoutPeriodViewList> payoutPeriodViewLists) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("code", "500");
        map.put("msg", "服务器遇到未知错误,请稍后再试!");
        //判断用户传入的id
        int i = 0;
        if (payoutPeriodViewLists.size() > 0) {
            //判斷是否有关联的缴费记录
            PayoutPeriodViewList payoutPeriodViewList = JSON.parseObject(JSON.toJSONString(payoutPeriodViewLists.get(0)), PayoutPeriodViewList.class);
            i = payoutPeriodDao.findSonCount(payoutPeriodViewList);
        } else {
            map.put("msg", "错误的参数传入");
        }
        if (i > 0) {
            map.put("msg", "注销失败,这个缴费期有" + i + "条缴费期,不能注销,请先注销缴费记录");
        } else {
            //注销缴费期
            i = payoutPeriodDao.logout(payoutPeriodViewLists);
            if (i > 0) {
                map.put("code", "200");
                map.put("msg", "注销成功");
            }
        }
        return map;
    }

    /**
     * @return Map<String               ,               String>
     * @throws Exception
     * @title: savePayoutPeriod
     * @description: 缴费期对象持久化
     */
    public Map<String, String> savePayoutPeriod(Payoutperiod payoutperiod) {

        // 设置id
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        // 获得当前的时间戳
        String str = sdf.format(date);
        // 设置编号
        payoutperiod.setPaymentPeriodNumber("JFQ-" + str);
        // 设置默认状态
        payoutperiod.setPaymentPeriodStatus(0);
        // 设置创建时间
        payoutperiod.setPaymentPeriodCreationTime(new Timestamp(new Date()
                .getTime()));

        int i = payoutPeriodDao.savePayoutPeriod(payoutperiod);
        Map<String, String> map = new HashMap<String, String>();
        if (i > 0) {
            map.put("code", "200");
            map.put("msg", "添加成功");
        } else {
            map.put("code", "500");
            map.put("msg", "服务器遇到未知错误,请稍后再试!");
        }
        return map;
    }

    /**
     * @return ResponseDatagrid
     * @throws Exception
     * @title:findPayoutPeriodList
     * @description: 分页查询
     */
    public ResponseDatagrid findPayoutPeriodList(RequestDatagrid requestDatagrid) {
        ResponseDatagrid responseDatagrid = new ResponseDatagrid();

        // 取得查询条件对象
        PayoutPeriodViewList payoutPeriodViewList = JSON.parseObject(
                requestDatagrid.getWhereJson(), PayoutPeriodViewList.class);

        // 设置数据
        responseDatagrid.setRows(payoutPeriodDao.findPayoutPeriodList(
                requestDatagrid, payoutPeriodViewList));

        // 设置行数量
        responseDatagrid.setTotal(payoutPeriodDao
                .findPayoutPeriodCount(payoutPeriodViewList));

        return responseDatagrid;
    }

    public File derivationSel(List<PayoutPeriodViewList> payoutPeriodViewLists,
                              String path) throws Exception {

        return establishXSL(payoutPeriodViewLists, path);
    }

    /**
     * @return ResponseEntity<byte                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               [                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               ]>
     * @throws Exception
     * @title: 导出全部数据
     * @description: 功能描述
     */
    public File derivation(String path) throws Exception {

        List<PayoutPeriodViewList> payoutPeriodViewLists = payoutPeriodDao
                .selAll(null);

        return establishXSL(payoutPeriodViewLists, path);
    }

    /**
     * @return ResponseEntity<byte                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               [                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               ]>
     * @throws Exception
     * @title: derivationSelWhere
     * @description: 用户传入条件 导出数据
     */
    public File derivationSelWhere(String path,
                                   PayoutPeriodViewList payoutPeriodViewList) throws Exception {
        List<PayoutPeriodViewList> payoutPeriodViewLists = payoutPeriodDao
                .selAll(payoutPeriodViewList);
        return establishXSL(payoutPeriodViewLists, path);
    }

    private File establishXSL(List<PayoutPeriodViewList> payoutPeriodViewLists,
                              String path) throws Exception {

        WritableWorkbook book;
        WritableSheet sheet;
        WritableFont normalFont;

        WritableFont diffFont;
        WritableCellFormat normalFormat;
        WritableCellFormat diffFormat;

        File os = new File(path);
        if (!os.isFile()) {
            // 如果指定文件不存在，则新建该文件
            os.createNewFile();
        }
        {
            // 如果存在 删除了在创建
            os.delete();
            os.createNewFile();
        }

        book = Workbook.createWorkbook(os);
        // 生成名为"第一页"的工作表，参数0表示这是第一页
        sheet = book.createSheet("第一页", 0);
        // 设置字体为宋体,11号字,不加粗,颜色为红色
        normalFont = new WritableFont(WritableFont.createFont("宋体"), 11,
                WritableFont.NO_BOLD);
        // 设置字体为宋体,11号字,不加粗,颜色为红色
        diffFont = new WritableFont(WritableFont.createFont("宋体"), 11,
                WritableFont.NO_BOLD);
        diffFont.setColour(Colour.RED);

        normalFormat = new WritableCellFormat(normalFont);
        normalFormat.setAlignment(jxl.format.Alignment.CENTRE);
        normalFormat.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);

        diffFormat = new WritableCellFormat(diffFont);
        diffFormat.setAlignment(jxl.format.Alignment.CENTRE);
        diffFormat.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);

        Label labelA = new Label(0, 0, "缴费期名称", normalFormat);
        Label labelB = new Label(1, 0, "创建用户", normalFormat);
        Label labelC = new Label(2, 0, "创建时间", normalFormat);
        Label labelD = new Label(3, 0, "状态", normalFormat);
        Label labelE = new Label(4, 0, "备注", normalFormat);

        for (int i = 0; i < payoutPeriodViewLists.size(); i++) {

            PayoutPeriodViewList payoutPeriodViewList = payoutPeriodViewLists
                    .get(i);

            Label lab1 = new Label(0, i + 1,
                    payoutPeriodViewList.getNameOfPaymentPeriod());

            Label lab2 = new Label(1, i + 1,
                    payoutPeriodViewList.getUserName());

            Label lab3 = new Label(2, i + 1,
                    payoutPeriodViewList.getPaymentPeriodCreationTime());

            Label lab4 = new Label(3, i + 1, payoutPeriodViewList
                    .getPaymentPeriodStatus().equals("0") ? "正常" : "注销");

            Label lab5 = new Label(4, i + 1,
                    payoutPeriodViewList.getRemarksOnPaymentPeriod());

            sheet.addCell(lab1);
            sheet.addCell(lab2);
            sheet.addCell(lab3);
            sheet.addCell(lab4);
            sheet.addCell(lab5);
        }

        // 将定义好的单元格添加到工作表中
        sheet.addCell(labelA);
        sheet.addCell(labelB);
        sheet.addCell(labelC);
        sheet.addCell(labelD);
        sheet.addCell(labelE);
        book.write();
        book.close();
        // 开始传输文件给用户
        File file = new File(path);
        return file;
    }

    /**
     * @return String
     * @throws Exception
     * @title: toPayoutPeriodDetail
     * @description: 查看缴费详细
     */
    public PayoutPeriodViewList toPayoutPeriodDetail(String id) {
        return payoutPeriodDao.findPayoutPeriodOne(id);
    }

    /**
     * 查询所有缴费期
     * @return
     */
    public List<Payoutperiod> findlist() {
        return payoutPeriodDao.findlist();
    }
}
