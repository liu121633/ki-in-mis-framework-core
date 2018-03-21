package org.kingqueen.kiinmis.model.vo.HourrecordVo;

public class HourrecordVo {
    //课时余额id
    private String hourrecordid;
    //学生编号
    private String studentNumber;
    //缴费期编号
    private String paymentPeriodNumber;
    //剩余课时
    private String residue;
    //状态
    private String state;
    //修改时间
    private String updatetime;
    //学生名称
    private String studentName;
    //缴费期名称
    private String nameOfPaymentPeriod;
    //棋院名称
    private String kiinName;
    //剩余课时最大多少
    private Integer greaterResidue;
    //剩余课时最少多少
    private Integer lessResidue;
    //棋院编号
    private String chessNumber;

    public Integer getGreaterResidue() {
        return greaterResidue;
    }

    public void setGreaterResidue(Integer greaterResidue) {
        this.greaterResidue = greaterResidue;
    }

    public Integer getLessResidue() {
        return lessResidue;
    }

    public void setLessResidue(Integer lessResidue) {
        this.lessResidue = lessResidue;
    }

    public String getChessNumber() {
        return chessNumber;
    }

    public void setChessNumber(String chessNumber) {
        this.chessNumber = chessNumber;
    }
    public String getHourrecordid() {
        return hourrecordid;
    }

    public void setHourrecordid(String hourrecordid) {
        this.hourrecordid = hourrecordid;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getPaymentPeriodNumber() {
        return paymentPeriodNumber;
    }

    public void setPaymentPeriodNumber(String paymentPeriodNumber) {
        this.paymentPeriodNumber = paymentPeriodNumber;
    }

    public String getResidue() {
        return residue;
    }

    public void setResidue(String residue) {
        this.residue = residue;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getNameOfPaymentPeriod() {
        return nameOfPaymentPeriod;
    }

    public void setNameOfPaymentPeriod(String nameOfPaymentPeriod) {
        this.nameOfPaymentPeriod = nameOfPaymentPeriod;
    }

    public String getKiinName() {
        return kiinName;
    }

    public void setKiinName(String kiinName) {
        this.kiinName = kiinName;
    }
}
