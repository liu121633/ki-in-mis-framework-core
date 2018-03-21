package org.kingqueen.kiinmis.model.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
public class Hourrecord implements Serializable {
    /**
     * 编号主键自增
     */
    private Integer hourrecordid;

    /**
     * 学生编号
     */
    private String studentnumber;

    /**
     * 缴费期编号
     */
    private String paymentperiodnumber;

    /**
     * 剩余课时
     */
    private Integer residue;

    /**
     * 状态
     */
    private Integer state;

    /**
     * 最后修改时间
     */
    private Date updatetime;

    private static final long serialVersionUID = 1L;

    public Integer getHourrecordid() {
        return hourrecordid;
    }

    public void setHourrecordid(Integer hourrecordid) {
        this.hourrecordid = hourrecordid;
    }

    public String getStudentnumber() {
        return studentnumber;
    }

    public void setStudentnumber(String studentnumber) {
        this.studentnumber = studentnumber;
    }

    public String getPaymentperiodnumber() {
        return paymentperiodnumber;
    }

    public void setPaymentperiodnumber(String paymentperiodnumber) {
        this.paymentperiodnumber = paymentperiodnumber;
    }

    public Integer getResidue() {
        return residue;
    }

    public void setResidue(Integer residue) {
        this.residue = residue;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}