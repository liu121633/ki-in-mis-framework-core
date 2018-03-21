package org.kingqueen.kiinmis.model.biz.Payment;

import java.io.File;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.kingqueen.kiinmis.model.dao.Paymen.IPaymenDao;
import org.kingqueen.kiinmis.model.dao.PayoutPeriod.IPayoutPeriodDao;
import org.kingqueen.kiinmis.model.dao.hourlog.HourlogMapper;
import org.kingqueen.kiinmis.model.dao.hourrecord.HourrecordMapper;
import org.kingqueen.kiinmis.model.eaysui.pojo.RequestDatagrid;
import org.kingqueen.kiinmis.model.eaysui.pojo.ResponseDatagrid;
import org.kingqueen.kiinmis.model.pojo.*;
import org.kingqueen.kiinmis.model.vo.Payment.PaymentViewList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 刘洪君
 * @version V1.0
 * @ClassName PaymentBiz
 * @description 缴费信息biz
 * @date 2017年12月4日上午10:30:59
 */
@Service("PaymentBiz")
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS, isolation = Isolation.READ_COMMITTED)
public class PaymentBiz {

    @Autowired
    private IPaymenDao paymenDao;

    @Autowired
    private HourrecordMapper hourrecordMapper;

    @Autowired
    private IPayoutPeriodDao payoutPeriodDao;

    @Autowired
    private HourlogMapper hourlogMapper;

    /**
     * @return List<Payoutperiod>
     * @throws Exception
     * @title:查询未被注销的缴费期
     * @description: 查询未被注销的缴费期
     */
    public List<Payoutperiod> findPayoutPeriod() {
        return paymenDao.findPayoutPeriod();
    }

    /**
     * @return Student
     * @throws Exception
     * @title:
     * @description: id查询学生
     */
    public Student findStudent(String id) {
        return paymenDao.findStudent(id);
    }

    /**
     * @return void
     * @throws Exception
     * @title:
     * @description: 数据转换xls
     */
    public File datederivationSelXls(List<PaymentViewList> paymentViewLists,
                                     String path) {
        try {
            WritableWorkbook book;
            WritableSheet sheet;

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

            Label labelA = new Label(0, 0, "学生姓名");
            Label labelB = new Label(1, 0, "缴费期");
            Label labelC = new Label(2, 0, "棋院");
            Label labelD = new Label(3, 0, "班级");
            Label labelE = new Label(4, 0, "学校");
            Label labelF = new Label(5, 0, "授课老师");
            Label labelG = new Label(6, 0, "缴费金额");
            Label labelH = new Label(7, 0, "支付方式");
            Label labelL = new Label(8, 0, "缴费时间");
            Label labelM = new Label(9, 0, "状态");
            Label labelN = new Label(10, 0, "备注");

            for (int i = 0; i < paymentViewLists.size(); i++) {
                PaymentViewList paymentViewList = paymentViewLists.get(i);
                Label lab1 = new Label(0, i + 1,
                        paymentViewList.getNameOfStudentPaidName());

                Label lab2 = new Label(1, i + 1,
                        paymentViewList.getNameOfPaymentPeriod());
                Label lab3 = new Label(2, i + 1, paymentViewList.getPkiinName());
                Label lab4 = new Label(3, i + 1, paymentViewList.getGradename());
                Label lab5 = new Label(4, i + 1,
                        paymentViewList.getSchoolName());
                Label lab6 = new Label(5, i + 1, paymentViewList.getCoachName());

                Number lab8 = new Number(6, i + 1,
                        Float.parseFloat(paymentViewList.getAmountPaid()));

                Label lab9 = new Label(7, i + 1,
                        paymentViewList.getPaymentUser());

                Label lab10 = new Label(8, i + 1,
                        paymentViewList.getPaymentTime());

                Label lab11 = new Label(9, i + 1,
                        paymentViewList.getPaymentStatus().equals("0") ? "正常" : "注销");

                Label lab12 = new Label(10, i + 1,
                        paymentViewList.getPaymentRemarks());

                sheet.addCell(lab1);
                sheet.addCell(lab2);
                sheet.addCell(lab3);
                sheet.addCell(lab4);
                sheet.addCell(lab5);
                sheet.addCell(lab6);
                sheet.addCell(lab8);
                sheet.addCell(lab9);
                sheet.addCell(lab10);
                sheet.addCell(lab11);
                sheet.addCell(lab12);
            }

            // 将定义好的单元格添加到工作表中
            sheet.addCell(labelA);
            sheet.addCell(labelB);
            sheet.addCell(labelC);
            sheet.addCell(labelD);
            sheet.addCell(labelE);
            sheet.addCell(labelF);
            sheet.addCell(labelG);
            sheet.addCell(labelH);
            sheet.addCell(labelL);
            sheet.addCell(labelM);
            sheet.addCell(labelN);

            book.write();
            book.close();

            // 开始传输文件给用户
            File file = new File(path);

            return file;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }


    /**
     * @return ResponseDatagrid
     * @throws Exception
     * @title:findPaymentList
     * @description: 分页查询
     */
    public ResponseDatagrid findPaymentList(RequestDatagrid requestDatagrid, List<String> kiinids) {
        ResponseDatagrid responseDatagrid = new ResponseDatagrid();

        // 取得查询条件对象
        PaymentViewList paymentViewList = JSON.parseObject(
                requestDatagrid.getWhereJson(), PaymentViewList.class);
        if (paymentViewList.getPkiinName() == null) {
            paymentViewList.setPkiinName(kiinids.get(0));
        }
        // 设置数据
        responseDatagrid.setRows(paymenDao.findPaymenList(requestDatagrid,
                paymentViewList, kiinids));
        // 设置行数量
        responseDatagrid.setTotal(paymenDao.findPaymenCount(paymentViewList, kiinids));
        return responseDatagrid;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public Map<String, String> add(Payment payment) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

        payment.setTheCorrespondingPaymentPeriod(payment
                .getTheCorrespondingPaymentPeriod().replace(",", ""));
        // 获得当前的时间戳
        String str = sdf.format(date);
        // 设置编号
        payment.setPaymentInformationNumber("JFXX-" + str);
        // 设置创建时间
        payment.setPaymentTime(new Timestamp(new Date().getTime()));
        // 设置 状态 默认0
        payment.setPaymentStatus(0);
        // 判断有没有设置棋院
        if (payment.getPkiin() == null) {
            // 没有设置棋院 在这里根据学生id查询棋院
            payment.setPkiin(payoutPeriodDao.findStudentKiin(payment
                    .getNameOfStudentPaid()));
        }
        int i = paymenDao.add(payment);
        Map<String, String> map = new HashMap<String, String>();
        if (i > 0) {

            //创建出一个课时余额对象
            //课时记录对象
            Hourrecord hourrecord = new Hourrecord();
            //缴费期
            hourrecord.setPaymentperiodnumber(payment.getTheCorrespondingPaymentPeriod());
            //学生编号
            hourrecord.setStudentnumber(payment.getNameOfStudentPaid());
            //剩余课时
            hourrecord.setResidue(Integer.parseInt(payment.getPaylessonnumber()) + Integer.parseInt(payment.getBenefactorlessonnumber()));
            //状态
            hourrecord.setState(0);
            //修改时间
            hourrecord.setUpdatetime(new Date());
            //查询的条件
            HourrecordExample hourrecordExample = new HourrecordExample();
            //学生id= and 缴费期id=
            hourrecordExample.or().andStudentnumberEqualTo(hourrecord.getStudentnumber()).andPaymentperiodnumberEqualTo(hourrecord.getPaymentperiodnumber());
            List<Hourrecord> hourrecords = hourrecordMapper.selectByExample(hourrecordExample);
            //缴费成功后 课时表添加记录
            if (hourrecords.size() > 0) {  //判断是否拥有这条记录
                hourrecords.get(0).setResidue(hourrecord.getResidue() + hourrecords.get(0).getResidue());
                hourrecords.get(0).setUpdatetime(new Date());
                hourrecords.get(0).setState(0);
                hourrecordMapper.updateByExample(hourrecords.get(0), hourrecordExample);
                hourrecord = hourrecords.get(0);
            } else {
                //没有记录就新增
                hourrecord.setState(0);
                hourrecordMapper.insert(hourrecord);
            }

            //添加日志
            Hourlog hourlog = new Hourlog();

            hourlog.setHourrecordid(hourrecord.getHourrecordid());
            hourlog.setOperationtime(new Date());
            hourlog.setOperationtype("缴费");
            //缴费课时
            hourlog.setQuantity(Integer.parseInt(payment.getPaylessonnumber()));
            //缴费记录id
            hourlog.setPaymentinformationnumber(payment.getPaymentInformationNumber());
            //赠送课时
            hourlog.setDonate(Integer.parseInt(payment.getBenefactorlessonnumber()));
            hourlog.setUserid(payment.getPaymentCreationUser());
            hourlogMapper.insertSelective(hourlog);

            map.put("code", "200");
            map.put("msg", "缴费成功");
        } else {
            map.put("code", "500");
            map.put("msg", "服务器遇到未知错误,请稍后再试!");
        }
        return map;
    }

    /**
     * 缴费信息作废
     *
     * @param paymentViewList
     * @param user
     * @return
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public Map<String, String> logout(List<PaymentViewList> paymentViewList, User user) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("code", "500");
        map.put("msg", "服务器遇到未知错误,请稍后再试!");
        if (paymentViewList.size() > 0) {

            PaymentViewList paymentView = JSON.parseObject(JSON.toJSONString(paymentViewList.get(0)), PaymentViewList.class);

            HourrecordExample hourrecordExample = new HourrecordExample();
            //得到这个缴费信息的缴费期编号 作为查询条件 查询课课时余额
            hourrecordExample.or().
                    andPaymentperiodnumberEqualTo
                            (paymentView.getPaymentPeriodNumber()).andStudentnumberEqualTo(paymentView.getNameOfStudentPaid());

            List<Hourrecord> hourrecords = hourrecordMapper.selectByExample(hourrecordExample);
            if (hourrecords.size() > 0) {
                //这条缴费记录对应的缴费课时余额
                Hourrecord hourrecord = hourrecords.get(0);
                //缴费课时
                String paylessonnumber = paymentView.getPaylessonnumber();
                //赠送课时
                String benefactorlessonnumber = paymentView.getBenefactorlessonnumber();
                //扣除课时
                int residue = hourrecord.getResidue() - (Integer.parseInt(paylessonnumber) + Integer.parseInt(benefactorlessonnumber));
                //判断剩余课时 是否正常 大于等于0 正常
                if (residue >= 0) {
                    //注销记录
                    int i = paymenDao.logout(paymentViewList);
                    //修改余额
                    hourrecord.setUpdatetime(new Date());
                    hourrecord.setResidue(residue);

                    //如果修改后的課時小于0 修改状态
                    if (hourrecord.getResidue() - 1 < 0) {
                        hourrecord.setState(1);
                    }
                    //开始修改
                    hourrecordMapper.updateByPrimaryKeySelective(hourrecord);
                    //产生日志
                    Hourlog hourlog = new Hourlog();

                    hourlog.setHourrecordid(hourrecord.getHourrecordid());
                    hourlog.setOperationtime(new Date());
                    hourlog.setOperationtype("作废");
                    //缴费课时
                    hourlog.setQuantity(-Integer.parseInt(paylessonnumber));
                    //赠送课时
                    hourlog.setDonate(-Integer.parseInt(benefactorlessonnumber));
                    hourlog.setPaymentinformationnumber(JSON.parseObject(JSON.toJSONString(paymentViewList.get(0)), PaymentViewList.class).getPaymentInformationNumber());
                    hourlog.setUserid(user.getUserNumber());
                    hourlogMapper.insert(hourlog);
                    map.put("code", "200");
                    map.put("msg", "注销成功");
                } else {
                    map.put("msg", "剩余课时不足!无法作废");
                }
            } else {
                map.put("msg", "沒有找到缴费记录对应的课时余额");
            }
        } else {
            map.put("msg", "参数传入错误!");
        }
        return map;
    }

    /**
     * @return Payment
     * @throws Exception
     * @title: findWhereID
     * @description: 查询单条数据 根据id 修改前查询数据
     */
    public PaymentViewList findWhereID(String id) {
        return paymenDao.findWhereID(id);
    }


    /**
     * 修改
     *
     * @param payment
     * @return
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public Map<String, String> update(Payment payment) {
        Map<String, String> map = new HashMap<String, String>();
        try {
            //查询他的值
            PaymentViewList p = paymenDao.findWhereID(payment.getPaymentInformationNumber());
            //缴费课时
            int paylessonnumber = p.getPaylessonnumber() == null || p.getPaylessonnumber().equals("") ? 0 : Integer.parseInt(p.getPaylessonnumber());
            //赠送课时
            int baylessonnumber = p.getBenefactorlessonnumber() == null || p.getBenefactorlessonnumber().equals("") ? 0 : Integer.parseInt(p.getBenefactorlessonnumber());
            //这条记录之前操作的课时数量
            int originalVal = paylessonnumber + baylessonnumber;
            //如果只允许修改异常数据 需要这个判断 原来数据是否异常
            if(originalVal>0){
                map.put("code", "300");
                map.put("msg", "修改失败,错误:不满足修改条件!因为这不是一条异常数据");
                return map;
            }
            //创建查询对象
            HourlogExample hourlogExample = new HourlogExample();
            //日志里面找这条缴费信息的记录 从而取得课时记录编号
            hourlogExample.or().andPaymentinformationnumberEqualTo(payment.getPaymentInformationNumber()).andOperationtypeEqualTo("缴费");
            //执行查询
            List<Hourlog> hourlogs = hourlogMapper.selectByExample(hourlogExample);
            //判断是否有记录数量
            if (hourlogs == null) {
                map.put("code", "300");
                map.put("msg", "日志缺失！修改失败。");
                return map;
            }
            //取得记录
            Hourlog hourlog = hourlogs.get(0);
            //创建查询对象
            HourrecordExample hourrecordExample = new HourrecordExample();
            //设置查询条件
            hourrecordExample.or().andHourrecordidEqualTo(hourlog.getHourrecordid());
            //条件 日志编号
            List<Hourrecord> hourrecords = hourrecordMapper.selectByExample(hourrecordExample);
            //判断是否有记录数量
            if (hourrecords == null) {
                map.put("code", "300");
                map.put("msg", "课时记录缺失！修改失败。");
                return map;
            }
            //取得课时记录
            Hourrecord hourrecord = hourrecords.get(0);
            //先减掉之前记录的课时数量
            hourrecord.setResidue(hourrecord.getResidue()-originalVal);
            //修改后的繳費课时
            paylessonnumber = payment.getPaylessonnumber() == null || payment.getPaylessonnumber().equals("") ? 0 : Integer.parseInt(payment.getPaylessonnumber());
            //修改后的赠送课时
            baylessonnumber = payment.getBenefactorlessonnumber() == null || payment.getBenefactorlessonnumber().equals("") ? 0 : Integer.parseInt(payment.getBenefactorlessonnumber());
            //加上现在课时的数量
            hourrecord.setResidue(hourrecord.getResidue()+paylessonnumber + baylessonnumber);
            //保存修改课时
            hourrecordMapper.updateByPrimaryKeySelective(hourrecord);
            //保存修改日志
            hourlog.setQuantity(paylessonnumber);
            hourlog.setDonate(baylessonnumber);
            hourlogMapper.updateByPrimaryKeySelective(hourlog);
            //保存修改缴费记录
            paymenDao.update(payment);
            map.put("code", "200");
            map.put("msg", "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "500");
            map.put("msg", e.getMessage());
        }
        return map;
    }

    /**
     * @return File
     * @throws Exception
     * @title:
     * @description: 根据条件导出数据 没有条件就导出全部
     */
    public File derivationSelWhere(PaymentViewList paymentViewList, String path, List<String> kiinids) {
        if (paymentViewList.getPkiinName() == null) {
            paymentViewList.setPkiinName(kiinids.get(0));
        }
        List<PaymentViewList> paymentViewLists = paymenDao
                .derivationSelWhere(paymentViewList);

        return datederivationSelXls(paymentViewLists, path);
    }

    /**
     * @return List<PaymentViewList>
     * @throws Exception
     * @title:
     * @description: 根据条件查询所有数据
     */
    public List<PaymentViewList> findAll(PaymentViewList paymentViewList, List<String> kiinids) {
        if (paymentViewList.getPkiinName() == null) {
            paymentViewList.setPkiinName(kiinids.get(0));
        }
        return paymenDao.derivationSelWhere(paymentViewList);
    }

    public List<Grade> findGrade() {
        return paymenDao.findGrade();
    }

}
