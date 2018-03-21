package org.kingqueen.kiinmis.model.pojo;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

public class Payment {

	// 缴费信息编号
	private String paymentInformationNumber;
	// 缴费学员 引用学员编号
	private String nameOfStudentPaid;
	// 缴费信息对应的棋院
	private String pkiin;
	// 缴费应缴金额
	private Float amountPayable;
	// 缴费金额
	private Float amountPaid;
	// 对应的缴费期 引用缴费期编号
	private String theCorrespondingPaymentPeriod;
	// 缴费时间
	private Timestamp paymentTime;
	// 经手人 引用用户编号
	private String paymentUser;
	// 缴费状态
	private Integer paymentStatus;
	//缴费班级
	private String gradenumber;
	// 缴费创建用户 引用用户编号
	private String paymentCreationUser;
	// 创建用户的时间 精确到秒
	private Timestamp paymentCreationTime;
	// 最后操作功能的用户 引用用户编号
	private String paymentModification;
	// 最后操作用户的时间
	private Timestamp lastModificationTimeOfPayment;
	// 缴费备注
	private String paymentRemarks;
	// 缴费备用字段1
	private String paymentReserveField1;
	// 缴费备用字段2
	private String paymentReserveField2;
	// 缴费备用字段3
	private String paymentReserveField3;
	//缴费课时
	private String	paylessonnumber;
	//赠送课时
	private String benefactorlessonnumber;


	public String getPaylessonnumber() {
		return paylessonnumber;
	}

	public void setPaylessonnumber(String paylessonnumber) {
		this.paylessonnumber = paylessonnumber;
	}

	public String getBenefactorlessonnumber() {
		return benefactorlessonnumber;
	}

	public void setBenefactorlessonnumber(String benefactorlessonnumber) {
		this.benefactorlessonnumber = benefactorlessonnumber;
	}

	public String getGradenumber() {
		return gradenumber;
	}
	public void setGradenumber(String gradenumber) {
		this.gradenumber = gradenumber;
	}
	public String getPaymentInformationNumber() {
		return paymentInformationNumber;
	}
	public void setPaymentInformationNumber(String paymentInformationNumber) {
		this.paymentInformationNumber = paymentInformationNumber;
	}
	public String getNameOfStudentPaid() {
		return nameOfStudentPaid;
	}
	public void setNameOfStudentPaid(String nameOfStudentPaid) {
		this.nameOfStudentPaid = nameOfStudentPaid;
	}
	public String getPkiin() {
		return pkiin;
	}
	public void setPkiin(String pkiin) {
		this.pkiin = pkiin;
	}
	public Float getAmountPayable() {
		return amountPayable;
	}
	public void setAmountPayable(Float amountPayable) {
		this.amountPayable = amountPayable;
	}
	public Float getAmountPaid() {
		return amountPaid;
	}
	public void setAmountPaid(Float amountPaid) {
		this.amountPaid = amountPaid;
	}
	public String getTheCorrespondingPaymentPeriod() {
		return theCorrespondingPaymentPeriod;
	}
	public void setTheCorrespondingPaymentPeriod(
			String theCorrespondingPaymentPeriod) {
		this.theCorrespondingPaymentPeriod = theCorrespondingPaymentPeriod;
	}
	public Timestamp getPaymentTime() {
		return paymentTime;
	}
	public void setPaymentTime(Timestamp paymentTime) {
		this.paymentTime = paymentTime;
	}
	public String getPaymentUser() {
		return paymentUser;
	}
	public void setPaymentUser(String paymentUser) {
		this.paymentUser = paymentUser;
	}
	public Integer getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(Integer paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public String getPaymentCreationUser() {
		return paymentCreationUser;
	}
	public void setPaymentCreationUser(String paymentCreationUser) {
		this.paymentCreationUser = paymentCreationUser;
	}
	public Timestamp getPaymentCreationTime() {
		return paymentCreationTime;
	}
	public void setPaymentCreationTime(Timestamp paymentCreationTime) {
		this.paymentCreationTime = paymentCreationTime;
	}
	public String getPaymentModification() {
		return paymentModification;
	}
	public void setPaymentModification(String paymentModification) {
		this.paymentModification = paymentModification;
	}
	public Timestamp getLastModificationTimeOfPayment() {
		return lastModificationTimeOfPayment;
	}
	public void setLastModificationTimeOfPayment(
			Timestamp lastModificationTimeOfPayment) {
		this.lastModificationTimeOfPayment = lastModificationTimeOfPayment;
	}
	public String getPaymentRemarks() {
		return paymentRemarks;
	}
	public void setPaymentRemarks(String paymentRemarks) {
		this.paymentRemarks = paymentRemarks;
	}
	public String getPaymentReserveField1() {
		return paymentReserveField1;
	}
	public void setPaymentReserveField1(String paymentReserveField1) {
		this.paymentReserveField1 = paymentReserveField1;
	}
	public String getPaymentReserveField2() {
		return paymentReserveField2;
	}
	public void setPaymentReserveField2(String paymentReserveField2) {
		this.paymentReserveField2 = paymentReserveField2;
	}
	public String getPaymentReserveField3() {
		return paymentReserveField3;
	}
	public void setPaymentReserveField3(String paymentReserveField3) {
		this.paymentReserveField3 = paymentReserveField3;
	}

	
}
