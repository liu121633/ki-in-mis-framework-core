package org.kingqueen.kiinmis.model.dao.PayoutPeriod;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.kingqueen.kiinmis.model.eaysui.pojo.RequestDatagrid;
import org.kingqueen.kiinmis.model.pojo.Grade;
import org.kingqueen.kiinmis.model.pojo.Payoutperiod;
import org.kingqueen.kiinmis.model.vo.PayoutPeriod.PayoutPeriodViewList;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 刘洪君
 * @version V1.0
 * @ClassName IPayoutPeriodDao
 * @description 针对缴费期的一些操作
 * @date 2017年11月28日下午8:09:03
 */
public interface IPayoutPeriodDao {

    /**
     * @return Payoutperiod
     * @throws Exception
     * @title: findPayoutPeriodOne
     * @description: 查询单条数据
     */
    public PayoutPeriodViewList findPayoutPeriodOne(@Param("id") String id);

    /**
     * @return int
     * @throws Exception
     * @title: nameExists
     * @description: 查询这个名称是否存在
     */
    public int nameExists(@Param("name") String name);

    /**
     * @return int
     * @throws Exception
     * @title: update
     * @description: 修改缴费
     */
    public int update(@Param("p") Payoutperiod payoutperiod);

    /**
     * @return Payoutperiod
     * @throws Exception
     * @title: findOne
     * @description: 查询条数据
     */
    public Payoutperiod findOne(@Param("id") String id);

    /**
     * @return int
     * @throws Exception
     * @title: logout
     * @description: 注销缴费期
     */
    public int logout(
            @Param("ps") List<PayoutPeriodViewList> payoutPeriodViewLists);

    /**
     * 查询这个缴费期 未作废的缴费记录的条数
     * @param payoutPeriodViewList
     * @return
     */
    public int findSonCount(@Param("p") PayoutPeriodViewList payoutPeriodViewList);

    /**
     * @return int 受影响的行数
     * @throws Exception
     * @title: savePayoutPeriod
     * @description: 持久化一个缴费期对象
     */
    public int savePayoutPeriod(@Param("p") Payoutperiod payoutperiod);

    /**
     * @return List<PayoutPeriodViewList>
     * @throws Exception
     * @title: findPayoutPeriodList
     * @description: 分页查询
     */
    public List<PayoutPeriodViewList> findPayoutPeriodList(
            @Param("r") RequestDatagrid requestDatagrid,
            @Param("p") PayoutPeriodViewList payoutPeriodViewList);

    /**
     * @return int
     * @throws Exception
     * @title: findPayoutPeriodCount
     * @description: 查询总行数
     */
    public int findPayoutPeriodCount(
            @Param("p") PayoutPeriodViewList payoutPeriodViewList);


    /**
     * @return List<PayoutPeriodViewList>
     * @throws Exception
     * @title:selAll
     * @description: 查询所有数据
     */
    public List<PayoutPeriodViewList> selAll(
            @Param("p") PayoutPeriodViewList payoutPeriodViewList);

    /**
     * @return String
     * @throws Exception
     * @title:
     * @description: 查询学生的棋院id
     */
    public String findStudentKiin(@Param("userid") String userid);


    /**
     * 查询所有缴费期
     * @return
     */
    public List<Payoutperiod> findlist();
}
