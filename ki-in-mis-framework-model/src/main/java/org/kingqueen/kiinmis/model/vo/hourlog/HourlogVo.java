package org.kingqueen.kiinmis.model.vo.hourlog;

public class HourlogVo {

    //操作记录id
    private String hourlogid;
    //课时余额id
    private String hourrecordid;
    //操作时间
    private String operationtime;
    //操作类型
    private String operationtype;
    //操作数量
    private String quantity;
    //赠送数量
    private String donate;
    //用户id
    private String userid;
    //用户名
    private String userName;
    //缴费信息编号
    private String PaymentInformationNumber;

    public String getHourlogid() {
        return hourlogid;
    }

    public void setHourlogid(String hourlogid) {
        this.hourlogid = hourlogid;
    }

    public String getHourrecordid() {
        return hourrecordid;
    }

    public void setHourrecordid(String hourrecordid) {
        this.hourrecordid = hourrecordid;
    }

    public String getOperationtime() {
        return operationtime;
    }

    public void setOperationtime(String operationtime) {
        this.operationtime = operationtime;
    }

    public String getOperationtype() {
        return operationtype;
    }

    public void setOperationtype(String operationtype) {
        this.operationtype = operationtype;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getDonate() {
        return donate;
    }

    public void setDonate(String donate) {
        this.donate = donate;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPaymentInformationNumber() {
        return PaymentInformationNumber;
    }

    public void setPaymentInformationNumber(String paymentInformationNumber) {
        PaymentInformationNumber = paymentInformationNumber;
    }
}
