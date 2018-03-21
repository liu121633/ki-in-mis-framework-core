package org.kingqueen.kiinmis.model.pojo;

import java.sql.Timestamp;


public class School {

	// 学校编号 自动生成
	private String schoolNumber; 
	// 学校名称
	private String schoolName; 
	// 学校地址
	private String schoolAddress; 
	// 学校备注
	private String schoolNotes; 
	// 学校创建用户  引用用户编号
	private String schoolCreateUserName; 
	// 学校创建时间 精确到秒
	private Timestamp schoolCreationTime; 
	// 学校最后修改用户 引用用户编号
	private String schoolFinallyModifiesUserName; 
	// 学校最后修改时间 
	private Timestamp lastRevisionTimeOfSchool; 
	// 学校状态 
	private Integer schoolState; 
	// 学校备用字段1
	private String schoolReserveField1; 
	// 学校备用字段2 
	private String schoolReserveField2; 
	// 学校备用字段3
	private String schoolReserveField3;
	public String getSchoolNumber() {
		return schoolNumber;
	}
	public void setSchoolNumber(String schoolNumber) {
		this.schoolNumber = schoolNumber;
	}
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	public String getSchoolAddress() {
		return schoolAddress;
	}
	public void setSchoolAddress(String schoolAddress) {
		this.schoolAddress = schoolAddress;
	}
	public String getSchoolNotes() {
		return schoolNotes;
	}
	public void setSchoolNotes(String schoolNotes) {
		this.schoolNotes = schoolNotes;
	}
	public String getSchoolCreateUserName() {
		return schoolCreateUserName;
	}
	public void setSchoolCreateUserName(String schoolCreateUserName) {
		this.schoolCreateUserName = schoolCreateUserName;
	}
	public Timestamp getSchoolCreationTime() {
		return schoolCreationTime;
	}
	public void setSchoolCreationTime(Timestamp schoolCreationTime) {
		this.schoolCreationTime = schoolCreationTime;
	}
	public String getSchoolFinallyModifiesUserName() {
		return schoolFinallyModifiesUserName;
	}
	public void setSchoolFinallyModifiesUserName(
			String schoolFinallyModifiesUserName) {
		this.schoolFinallyModifiesUserName = schoolFinallyModifiesUserName;
	}
	public Timestamp getLastRevisionTimeOfSchool() {
		return lastRevisionTimeOfSchool;
	}
	public void setLastRevisionTimeOfSchool(Timestamp lastRevisionTimeOfSchool) {
		this.lastRevisionTimeOfSchool = lastRevisionTimeOfSchool;
	}
	public Integer getSchoolState() {
		return schoolState;
	}
	public void setSchoolState(Integer schoolState) {
		this.schoolState = schoolState;
	}
	public String getSchoolReserveField1() {
		return schoolReserveField1;
	}
	public void setSchoolReserveField1(String schoolReserveField1) {
		this.schoolReserveField1 = schoolReserveField1;
	}
	public String getSchoolReserveField2() {
		return schoolReserveField2;
	}
	public void setSchoolReserveField2(String schoolReserveField2) {
		this.schoolReserveField2 = schoolReserveField2;
	}
	public String getSchoolReserveField3() {
		return schoolReserveField3;
	}
	public void setSchoolReserveField3(String schoolReserveField3) {
		this.schoolReserveField3 = schoolReserveField3;
	} 


}
