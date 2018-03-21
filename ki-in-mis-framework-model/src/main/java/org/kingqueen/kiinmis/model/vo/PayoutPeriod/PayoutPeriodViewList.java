package org.kingqueen.kiinmis.model.vo.PayoutPeriod;

/**
 * @ClassName PayoutPeriodViewList
 * @description 功能描述
 * @author 刘洪君
 * @date 2017年11月30日上午12:55:26
 * @version V1.0
 */
public class PayoutPeriodViewList {

	// 缴费期编号
	private String paymentPeriodNumber;
	// 缴费期名称
	private String nameOfPaymentPeriod;
	// 缴费期创建用户编号
	private String userNumber;
	// 缴费期创建用户名称
	private String userName;
	// 创建时间在这个时间后
	private String creationTimelater;
	// 缴费期创建时间
	private String paymentPeriodCreationTime;
	// 创建时间在这个时间前
	private String creationTimeStart;

	// 缴费期修改用户编号
	private String updateUserNumber;

	// 缴费期修改用户名称
	private String updateUserName;

	// 缴费期修改时间
	private String lastModificationTimeOfPaymentPeriod;

	// 缴费期状态
	private String paymentPeriodStatus;
	// 缴费期备注

	private String remarksOnPaymentPeriod;

	public String getUpdateUserNumber() {
		return updateUserNumber;
	}

	public void setUpdateUserNumber(String updateUserNumber) {
		this.updateUserNumber = updateUserNumber;
	}

	public String getUpdateUserName() {
		return updateUserName;
	}

	public void setUpdateUserName(String updateUserName) {
		this.updateUserName = updateUserName;
	}

	public String getLastModificationTimeOfPaymentPeriod() {
		return lastModificationTimeOfPaymentPeriod;
	}

	public void setLastModificationTimeOfPaymentPeriod(
			String lastModificationTimeOfPaymentPeriod) {
		this.lastModificationTimeOfPaymentPeriod = lastModificationTimeOfPaymentPeriod;
	}

	public String getCreationTimelater() {
		return creationTimelater;
	}

	public void setCreationTimelater(String creationTimelater) {
		this.creationTimelater = creationTimelater;
	}

	public String getCreationTimeStart() {
		return creationTimeStart;
	}

	public void setCreationTimeStart(String creationTimeStart) {
		this.creationTimeStart = creationTimeStart;
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

	public String getUserNumber() {
		return userNumber;
	}

	public void setUserNumber(String userNumber) {
		this.userNumber = userNumber;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPaymentPeriodCreationTime() {
		return paymentPeriodCreationTime;
	}

	public void setPaymentPeriodCreationTime(String paymentPeriodCreationTime) {
		this.paymentPeriodCreationTime = paymentPeriodCreationTime;
	}

	public String getPaymentPeriodStatus() {
		return paymentPeriodStatus;
	}

	public void setPaymentPeriodStatus(String paymentPeriodStatus) {
		this.paymentPeriodStatus = paymentPeriodStatus;
	}

	public String getRemarksOnPaymentPeriod() {
		return remarksOnPaymentPeriod;
	}

	public void setRemarksOnPaymentPeriod(String remarksOnPaymentPeriod) {

		this.remarksOnPaymentPeriod = remarksOnPaymentPeriod;
	}

}
