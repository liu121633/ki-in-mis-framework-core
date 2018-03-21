package org.kingqueen.kiinmis.model.pojo;

import java.sql.Timestamp;


public class Position {

	// 职位编号
	private String positionNumber; 
	// 职位名称
	private String positionName; 
	// 职位备注
	private String positionRemarks; 
	// 职位创建用户 引用用户编号
	private String positionCreator; 
	// 职位创建时间
	private Timestamp positionCreationTime; 
	// 职位最后修改用户 引用用户编号
	private String positionFinallyModifyTheUser; 
	// 职位最后修改时间
	private Timestamp positionLastModifiedTime; 
	// 职位状态
	private Integer positionStatus; 
	// 职位备用字段1
	private String positionReserveField1; 
	// 职位备用字段2
	private String positionReserveField2; 
	// 职位备用字段3
	private String positionReserveField3;
	public String getPositionNumber() {
		return positionNumber;
	}
	public void setPositionNumber(String positionNumber) {
		this.positionNumber = positionNumber;
	}
	public String getPositionName() {
		return positionName;
	}
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}
	public String getPositionRemarks() {
		return positionRemarks;
	}
	public void setPositionRemarks(String positionRemarks) {
		this.positionRemarks = positionRemarks;
	}
	public String getPositionCreator() {
		return positionCreator;
	}
	public void setPositionCreator(String positionCreator) {
		this.positionCreator = positionCreator;
	}
	public Timestamp getPositionCreationTime() {
		return positionCreationTime;
	}
	public void setPositionCreationTime(Timestamp positionCreationTime) {
		this.positionCreationTime = positionCreationTime;
	}
	public String getPositionFinallyModifyTheUser() {
		return positionFinallyModifyTheUser;
	}
	public void setPositionFinallyModifyTheUser(String positionFinallyModifyTheUser) {
		this.positionFinallyModifyTheUser = positionFinallyModifyTheUser;
	}
	public Timestamp getPositionLastModifiedTime() {
		return positionLastModifiedTime;
	}
	public void setPositionLastModifiedTime(Timestamp positionLastModifiedTime) {
		this.positionLastModifiedTime = positionLastModifiedTime;
	}
	public Integer getPositionStatus() {
		return positionStatus;
	}
	public void setPositionStatus(Integer positionStatus) {
		this.positionStatus = positionStatus;
	}
	public String getPositionReserveField1() {
		return positionReserveField1;
	}
	public void setPositionReserveField1(String positionReserveField1) {
		this.positionReserveField1 = positionReserveField1;
	}
	public String getPositionReserveField2() {
		return positionReserveField2;
	}
	public void setPositionReserveField2(String positionReserveField2) {
		this.positionReserveField2 = positionReserveField2;
	}
	public String getPositionReserveField3() {
		return positionReserveField3;
	}
	public void setPositionReserveField3(String positionReserveField3) {
		this.positionReserveField3 = positionReserveField3;
	} 



}
