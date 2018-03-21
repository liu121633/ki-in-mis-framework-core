package org.kingqueen.kiinmis.model.pojo;

import java.sql.Timestamp;


/**
 * @author 刘洪君
 * @version V1.0
 * @ClassName Payoutperiod
 * @description 功能描述
 * @date 2017年11月29日
 */
public class Payoutperiod {

    // 缴费期编号
    private String paymentPeriodNumber;
    // 缴费期名称
    private String nameOfPaymentPeriod;
    // 缴费期备注
    private String remarksOnPaymentPeriod;
    // 缴费期创建用户 引用用户编号
    private String paymentPeriodCreatesUser;
    // 缴费期创建时间
    private Timestamp paymentPeriodCreationTime;
    // 缴费期最后修改用户 引用用户编号
    private String theLastModificationOfTheUser;
    // 缴费期最后修改时间
    private Timestamp lastModificationTimeOfPaymentPeriod;
    // 缴费期状态
    private Integer paymentPeriodStatus;
    // 备用字段1
    private String paymentPeriodReserveField1;
    // 备用字段2
    private String paymentPeriodReserveField2;
    // 备用字段3
    private String paymentPeriodReserveField3;
    //缴费金额
    private String amountPayable;
    //缴费课时
    private Integer paylessonnumber;
    //赠送课时
    private Integer benefactorlessonnumber;

    public String getAmountPayable() {
        return amountPayable;
    }
    public void setAmountPayable(String amountPayable) {
        this.amountPayable = amountPayable;
    }

    public Integer getPaylessonnumber() {
        return paylessonnumber;
    }

    public void setPaylessonnumber(Integer paylessonnumber) {
        this.paylessonnumber = paylessonnumber;
    }

    public Integer getBenefactorlessonnumber() {
        return benefactorlessonnumber;
    }

    public void setBenefactorlessonnumber(Integer benefactorlessonnumber) {
        this.benefactorlessonnumber = benefactorlessonnumber;
    }

    public String getPaymentPeriodNumber() {
        return paymentPeriodNumber;
    }

    public void setPaymentPeriodNumber(String paymentPeriodNumber) {
        this.paymentPeriodNumber = paymentPeriodNumber;
    }

    public String getNameOfPaymentPeriod() {
        return nameOfPaymentPeriod;
    }

    public void setNameOfPaymentPeriod(String nameOfPaymentPeriod) {
        this.nameOfPaymentPeriod = nameOfPaymentPeriod;
    }

    public String getRemarksOnPaymentPeriod() {
        return remarksOnPaymentPeriod;
    }

    public void setRemarksOnPaymentPeriod(String remarksOnPaymentPeriod) {
        this.remarksOnPaymentPeriod = remarksOnPaymentPeriod;
    }

    public String getPaymentPeriodCreatesUser() {
        return paymentPeriodCreatesUser;
    }

    public void setPaymentPeriodCreatesUser(String paymentPeriodCreatesUser) {
        this.paymentPeriodCreatesUser = paymentPeriodCreatesUser;
    }

    public Timestamp getPaymentPeriodCreationTime() {
        return paymentPeriodCreationTime;
    }

    public void setPaymentPeriodCreationTime(Timestamp paymentPeriodCreationTime) {
        this.paymentPeriodCreationTime = paymentPeriodCreationTime;
    }

    public String getTheLastModificationOfTheUser() {
        return theLastModificationOfTheUser;
    }

    public void setTheLastModificationOfTheUser(String theLastModificationOfTheUser) {
        this.theLastModificationOfTheUser = theLastModificationOfTheUser;
    }

    public Timestamp getLastModificationTimeOfPaymentPeriod() {
        return lastModificationTimeOfPaymentPeriod;
    }

    public void setLastModificationTimeOfPaymentPeriod(
            Timestamp lastModificationTimeOfPaymentPeriod) {
        this.lastModificationTimeOfPaymentPeriod = lastModificationTimeOfPaymentPeriod;
    }

    public Integer getPaymentPeriodStatus() {
        return paymentPeriodStatus;
    }

    public void setPaymentPeriodStatus(Integer paymentPeriodStatus) {
        this.paymentPeriodStatus = paymentPeriodStatus;
    }

    public String getPaymentPeriodReserveField1() {
        return paymentPeriodReserveField1;
    }

    public void setPaymentPeriodReserveField1(String paymentPeriodReserveField1) {
        this.paymentPeriodReserveField1 = paymentPeriodReserveField1;
    }

    public String getPaymentPeriodReserveField2() {
        return paymentPeriodReserveField2;
    }

    public void setPaymentPeriodReserveField2(String paymentPeriodReserveField2) {
        this.paymentPeriodReserveField2 = paymentPeriodReserveField2;
    }

    public String getPaymentPeriodReserveField3() {
        return paymentPeriodReserveField3;
    }

    public void setPaymentPeriodReserveField3(String paymentPeriodReserveField3) {
        this.paymentPeriodReserveField3 = paymentPeriodReserveField3;
    }

}
