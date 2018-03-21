package org.kingqueen.kiinmis.model.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
public class Hourlog implements Serializable {
    /**
     * id自增主键
     */
    private Integer hourlogid;

    /**
     * 对应课时记录id
     */
    private Integer hourrecordid;

    /**
     * 操作时间
     */
    private Date operationtime;

    /**
     * 操作类型 缴费还是作废缴费
     */
    private String operationtype;

    /**
     * 操作数量
     */
    private Integer quantity;

    /**
     * 赠送的课时是多少
     */
    private Integer donate;

    /**
     * 操作用户
     */
    private String userid;

    /**
     * 缴费信息id
     */
    private String paymentinformationnumber;

    private static final long serialVersionUID = 1L;

    public Integer getHourlogid() {
        return hourlogid;
    }

    public void setHourlogid(Integer hourlogid) {
        this.hourlogid = hourlogid;
    }

    public Integer getHourrecordid() {
        return hourrecordid;
    }

    public void setHourrecordid(Integer hourrecordid) {
        this.hourrecordid = hourrecordid;
    }

    public Date getOperationtime() {
        return operationtime;
    }

    public void setOperationtime(Date operationtime) {
        this.operationtime = operationtime;
    }

    public String getOperationtype() {
        return operationtype;
    }

    public void setOperationtype(String operationtype) {
        this.operationtype = operationtype;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getDonate() {
        return donate;
    }

    public void setDonate(Integer donate) {
        this.donate = donate;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPaymentinformationnumber() {
        return paymentinformationnumber;
    }

    public void setPaymentinformationnumber(String paymentinformationnumber) {
        this.paymentinformationnumber = paymentinformationnumber;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Hourlog other = (Hourlog) that;
        return (this.getHourlogid() == null ? other.getHourlogid() == null : this.getHourlogid().equals(other.getHourlogid()))
            && (this.getHourrecordid() == null ? other.getHourrecordid() == null : this.getHourrecordid().equals(other.getHourrecordid()))
            && (this.getOperationtime() == null ? other.getOperationtime() == null : this.getOperationtime().equals(other.getOperationtime()))
            && (this.getOperationtype() == null ? other.getOperationtype() == null : this.getOperationtype().equals(other.getOperationtype()))
            && (this.getQuantity() == null ? other.getQuantity() == null : this.getQuantity().equals(other.getQuantity()))
            && (this.getDonate() == null ? other.getDonate() == null : this.getDonate().equals(other.getDonate()))
            && (this.getUserid() == null ? other.getUserid() == null : this.getUserid().equals(other.getUserid()))
            && (this.getPaymentinformationnumber() == null ? other.getPaymentinformationnumber() == null : this.getPaymentinformationnumber().equals(other.getPaymentinformationnumber()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getHourlogid() == null) ? 0 : getHourlogid().hashCode());
        result = prime * result + ((getHourrecordid() == null) ? 0 : getHourrecordid().hashCode());
        result = prime * result + ((getOperationtime() == null) ? 0 : getOperationtime().hashCode());
        result = prime * result + ((getOperationtype() == null) ? 0 : getOperationtype().hashCode());
        result = prime * result + ((getQuantity() == null) ? 0 : getQuantity().hashCode());
        result = prime * result + ((getDonate() == null) ? 0 : getDonate().hashCode());
        result = prime * result + ((getUserid() == null) ? 0 : getUserid().hashCode());
        result = prime * result + ((getPaymentinformationnumber() == null) ? 0 : getPaymentinformationnumber().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", hourlogid=").append(hourlogid);
        sb.append(", hourrecordid=").append(hourrecordid);
        sb.append(", operationtime=").append(operationtime);
        sb.append(", operationtype=").append(operationtype);
        sb.append(", quantity=").append(quantity);
        sb.append(", donate=").append(donate);
        sb.append(", userid=").append(userid);
        sb.append(", paymentinformationnumber=").append(paymentinformationnumber);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}