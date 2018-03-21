package org.kingqueen.kiinmis.model.vo.Payment;

/**
 * @ClassName PaymentViewList
 * @description 缴费信息vo
 * @author 刘洪君
 * @date 2017年12月4日下午2:50:58
 * @version V1.0
 */
public class PaymentViewList {

	

	// 缴费信息编号
	private String paymentInformationNumber;
	// 缴费学生编号
	private String nameOfStudentPaid;
	
	//学生id
	private String nameOfStudentPaidId;
	// 缴费学生姓名
	private String nameOfStudentPaidName;
	// 缴费实缴金额
	private String amountPaid;
	// 缴费期编号
	private String paymentPeriodNumber;
	// 缴费期名称
	private String nameOfPaymentPeriod;
	// 创建时间
	private String paymentTime;
	// 经手人姓名
	private String paymentUser;
	//弃用字段
	private String paymentUserName;
	//年级编号
	private String gradenumber;
	//年级名称
	private String gradename;
	// 棋院编号
	private String pkiin;
	// 棋院名称
	private String pkiinName;
	// 创建用户的编号
	private String paymentCreationUser;
	// 创建的用户名称
	private String paymentCreationUserName;
	// 创建数据的用户 的时间
	private String paymentCreationTime;
	// 最后修改用户的编号
	private String paymentModification;
	// 最后修改用户的名称
	private String paymentModificationName;
	// 最后修改的用户的时间
	private String lastModificationTimeOfPayment;
	// 缴费信息备注
	private String paymentRemarks;
	// 缴费信息状态
	private String paymentStatus;
	//缴费学员教练编号
	private String studentCoach;
	//缴费学员教练名称
	private String coachName;
	//学校名称
	private String schoolName;

	//缴费课时
	private String 	paylessonnumber;
	//赠送课时
	private String 	benefactorlessonnumber;

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

	private String updateTimeLater;
	
	private String updateTimeStart;

	public String getGradenumber() {
		return gradenumber;
	}

	public void setGradenumber(String gradenumber) {
		this.gradenumber = gradenumber;
	}

	public String getGradename() {
		return gradename;
	}

	public void setGradename(String gradename) {
		this.gradename = gradename;
	}

	public String getUpdateTimeLater() {
		return updateTimeLater;
	}

	public void setUpdateTimeLater(String updateTimeLater) {
		if (updateTimeLater.equals("")) {
			this.updateTimeLater = null;
			return;
		}
		
		
		this.updateTimeLater = updateTimeLater;
	}

	public String getUpdateTimeStart() {
		return updateTimeStart;
	}

	public void setUpdateTimeStart(String updateTimeStart) {
		
		if (updateTimeStart.equals("")) {
			this.updateTimeStart = null;
			return;
		}
		this.updateTimeStart = updateTimeStart;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		
		if (schoolName.equals("")) {
			this.schoolName=null;
		}else {
			this.schoolName = schoolName;
		}
		
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public String getStudentCoach() {
		return studentCoach;
	}

	public void setStudentCoach(String studentCoach) {
		this.studentCoach = studentCoach;
	}

	public String getCoachName() {
		return coachName;
	}

	public void setCoachName(String coachName) {
		
		if (coachName.equals("")) {
			this.coachName=null;
		}else {
			this.coachName = coachName;
		}
		
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

	public String getNameOfStudentPaidName() {
		return nameOfStudentPaidName;
	}

	public void setNameOfStudentPaidName(String nameOfStudentPaidName) {
		this.nameOfStudentPaidName = nameOfStudentPaidName;
	}



	public String getAmountPaid() {
		return amountPaid;
	}

	public void setAmountPaid(String amountPaid) {
		this.amountPaid = amountPaid;
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

	public String getPaymentTime() {
		return paymentTime;
	}

	public void setPaymentTime(String paymentTime) {
		this.paymentTime = paymentTime;
	}

	public String getPaymentUser() {
		return paymentUser;
	}

	public void setPaymentUser(String paymentUser) {
		this.paymentUser = paymentUser;
	}

	public String getPaymentUserName() {
		return paymentUserName;
	}

	public void setPaymentUserName(String paymentUserName) {
		this.paymentUserName = paymentUserName;
	}

	public String getPkiin() {
		return pkiin;
	}

	public void setPkiin(String pkiin) {
		this.pkiin = pkiin;
	}

	public String getPkiinName() {
		return pkiinName;
	}

	public void setPkiinName(String pkiinName) {
		this.pkiinName = pkiinName;
	}

	public String getPaymentCreationUser() {
		return paymentCreationUser;
	}

	public void setPaymentCreationUser(String paymentCreationUser) {
		this.paymentCreationUser = paymentCreationUser;
	}

	public String getPaymentCreationUserName() {
		return paymentCreationUserName;
	}

	public void setPaymentCreationUserName(String paymentCreationUserName) {
		this.paymentCreationUserName = paymentCreationUserName;
	}

	public String getPaymentCreationTime() {
		return paymentCreationTime;
	}

	public void setPaymentCreationTime(String paymentCreationTime) {
		this.paymentCreationTime = paymentCreationTime;
	}

	public String getPaymentModification() {
		return paymentModification;
	}

	public void setPaymentModification(String paymentModification) {
		this.paymentModification = paymentModification;
	}

	public String getPaymentModificationName() {
		return paymentModificationName;
	}

	public void setPaymentModificationName(String paymentModificationName) {
		this.paymentModificationName = paymentModificationName;
	}

	public String getLastModificationTimeOfPayment() {
		return lastModificationTimeOfPayment;
	}

	public void setLastModificationTimeOfPayment(
			String lastModificationTimeOfPayment) {
		this.lastModificationTimeOfPayment = lastModificationTimeOfPayment;
	}

	public String getPaymentRemarks() {
		
		return paymentRemarks;
	}

	public void setPaymentRemarks(String paymentRemarks) {
		this.paymentRemarks = paymentRemarks;
	}
	public String getNameOfStudentPaidId() {
		return nameOfStudentPaidId;
	}

	public void setNameOfStudentPaidId(String nameOfStudentPaidId) {
		if(("").equals(nameOfStudentPaidId)){
			nameOfStudentPaidId=null;
		}
		this.nameOfStudentPaidId = nameOfStudentPaidId;
	}
}
