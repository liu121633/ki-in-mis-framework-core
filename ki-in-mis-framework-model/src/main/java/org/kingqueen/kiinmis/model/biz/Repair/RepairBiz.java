package org.kingqueen.kiinmis.model.biz.Repair;

import org.kingqueen.kiinmis.model.dao.Paymen.IPaymenDao;
import org.kingqueen.kiinmis.model.dao.hourlog.HourlogMapper;
import org.kingqueen.kiinmis.model.dao.hourrecord.HourrecordMapper;
import org.kingqueen.kiinmis.model.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("RepairBiz")
public class RepairBiz {

    @Autowired
    private IPaymenDao paymenDao;


    @Autowired
    private HourrecordMapper hourrecordMapper;

    @Autowired
    private HourlogMapper hourlogMapper;

    /**
     * 这个业务发生于更新课时版本时  之前的缴费记录 没有产生课时记录
     * 请不要重复执行这个方法
     *
     * @param user
     * @return
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public Map<String, Object> repairHourrecord(User user) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", 200);
        map.put("msg", "success");
        try {
            //取得所有的缴费期
            List<Payment> payments = paymenDao.findAll();
            //遍历
            for (Payment p : payments) {
                //判断是否拥课时记录
                HourrecordExample hourrecordExample = new HourrecordExample();
                //创建条件 缴费期等于 学生编号等于
                hourrecordExample.or().andStudentnumberEqualTo(p.getNameOfStudentPaid()).andPaymentperiodnumberEqualTo(p.getTheCorrespondingPaymentPeriod());
                //得到记录
                List<Hourrecord> hourrecords = hourrecordMapper.selectByExample(hourrecordExample);
                //==0没有记录 需要创建记录 在添加日志
                if (hourrecords.size() == 0) {
                    Hourrecord hourrecord = new Hourrecord();
                    //设置状态
                    hourrecord.setState(0);
                    //设置缴费期编号
                    hourrecord.setPaymentperiodnumber(p.getTheCorrespondingPaymentPeriod());
                    //设置学生编号
                    hourrecord.setStudentnumber(p.getNameOfStudentPaid());
                    //设置时间
                    hourrecord.setUpdatetime(new Date());
                    //得到这条缴费记录添加的课时数量


                    int paylessonnumber = p.getPaylessonnumber()==null||p.getPaylessonnumber().equals("") ? 0 : Integer.parseInt(p.getPaylessonnumber());
                    int baylessonnumber = p.getBenefactorlessonnumber()==null||p.getBenefactorlessonnumber().equals("") ? 0 : Integer.parseInt(p.getBenefactorlessonnumber());

                    Integer residue = baylessonnumber + paylessonnumber;
                    //设置课时余额
                    hourrecord.setResidue(residue);
                    //添加课时记录
                    hourrecordMapper.insertSelective(hourrecord);
                    //添加日志
                    Hourlog hourlog = new Hourlog();
                    //设置课时编号id
                    hourlog.setHourrecordid(hourrecord.getHourrecordid());


                    //设置日志时间
                    hourlog.setOperationtime(new Date());
                    //设置日志类型
                    hourlog.setOperationtype("缴费");
                    //缴费课时数量
                    hourlog.setQuantity(paylessonnumber);
                    //赠送课时数量
                    hourlog.setDonate(baylessonnumber);
                    //设置用户id
                    hourlog.setUserid(user.getUserNumber());
                    //设置缴费信息id
                    hourlog.setPaymentinformationnumber(p.getPaymentInformationNumber());
                    //保存日志
                    hourlogMapper.insert(hourlog);
                } else {
                    //如果存在  做修改操作 然后添加日志
                    //得到课时记录
                    Hourrecord hourrecord = hourrecords.get(0);
                    //计算课时

                    int paylessonnumber = p.getPaylessonnumber()==null||p.getPaylessonnumber().equals("") ? 0 : Integer.parseInt(p.getPaylessonnumber());
                    int baylessonnumber = p.getBenefactorlessonnumber()==null||p.getBenefactorlessonnumber().equals("") ? 0 : Integer.parseInt(p.getBenefactorlessonnumber());

                    Integer residue = baylessonnumber + paylessonnumber;

                    residue = hourrecord.getResidue() + residue;
                    //设置课时
                    hourrecord.setResidue(residue);
                    //设置时间
                    hourrecord.setUpdatetime(new Date());
                    //修改
                    hourrecordMapper.updateByPrimaryKey(hourrecord);
                    //添加日志
                    Hourlog hourlog = new Hourlog();
                    //设置课时编号id
                    hourlog.setHourrecordid(hourrecord.getHourrecordid());
                    //设置日志时间
                    hourlog.setOperationtime(new Date());
                    //设置日志类型
                    hourlog.setOperationtype("缴费");
                    //缴费课时数量
                    hourlog.setQuantity(paylessonnumber);
                    //赠送课时数量
                    hourlog.setDonate(baylessonnumber);
                    //设置用户id
                    hourlog.setUserid(user.getUserNumber());
                    //设置缴费信息id
                    hourlog.setPaymentinformationnumber(p.getPaymentInformationNumber());
                    //保存日志
                    hourlogMapper.insert(hourlog);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", 500);
            map.put("msg", e.getMessage());
        }
        return map;
    }
}
