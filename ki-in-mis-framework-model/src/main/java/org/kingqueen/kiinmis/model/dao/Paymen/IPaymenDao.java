package org.kingqueen.kiinmis.model.dao.Paymen;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.kingqueen.kiinmis.model.eaysui.pojo.RequestDatagrid;
import org.kingqueen.kiinmis.model.pojo.Grade;
import org.kingqueen.kiinmis.model.pojo.Payment;
import org.kingqueen.kiinmis.model.pojo.Payoutperiod;
import org.kingqueen.kiinmis.model.pojo.Student;
import org.kingqueen.kiinmis.model.vo.Payment.PaymentViewList;
import org.kingqueen.kiinmis.model.vo.PayoutPeriod.PayoutPeriodViewList;

/**
 * @author 刘洪君
 * @version V1.0
 * @ClassName IPaymenDao
 * @description 缴费信息DAO
 * @date 2017年12月4日上午10:32:14
 */
public interface IPaymenDao {

    /**
     * @return List<Payoutperiod>
     * @throws Exception
     * @title:查询未被注销的缴费期
     * @description: 查询未被注销的缴费期
     */
    public List<Payoutperiod> findPayoutPeriod();

    public Student findStudent(@Param("id") String id);

    /**
     * @return Payment
     * @throws Exception
     * @title: findWhereID
     * @description: 查询单条数据 根据id
     */
    public PaymentViewList findWhereID(@Param("id") String id);

    /**
     * @return int
     * @throws Exception
     * @title: add
     * @description: 添加缴费信息
     */
    public int add(@Param("p") Payment payment);

    /**
     * @return List<PaymentViewList>
     * @throws Exception
     * @title: findPaymenList
     * @description: 分页查询
     */
    public List<PaymentViewList> findPaymenList(
            @Param("r") RequestDatagrid requestDatagrid,
            @Param("p") PaymentViewList paymentViewList,
            @Param("kiinids") List<String> kiinids);

    /**
     * @return Map<String   ,   String>
     * @throws Exception
     * @title:
     * @description: 缴费信息作废
     */
    public int logout(@Param("ps") List<PaymentViewList> paymentViewList);

    /**
     * @return int
     * @throws Exception
     * @title: update
     * @description: 修改
     */
    public int update(@Param("p") Payment payment);

    /**
     * @return int
     * @throws Exception
     * @title: findPaymenCount
     * @description: 查询行数
     */
    public int findPaymenCount(@Param("p") PaymentViewList paymentViewList,
                               @Param("kiinids") List<String> kiinids);

    /**
     * @return List<PaymentViewList>
     * @throws Exception
     * @title: 根据条件查询所有数据
     * @description: 功能描述
     */
    public List<PaymentViewList> derivationSelWhere(
            @Param("p") PaymentViewList paymentViewList);

    public List<Grade> findGrade();


    /**
     * 查詢所有的繳費期
     * @return
     */
    public List<Payment> findAll();

}
