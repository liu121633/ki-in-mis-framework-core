package org.kingqueen.kiinmis.model.pojo;

import java.sql.Timestamp;
import java.util.Date;

public class Coach {

	// 教练编号
	private String coachNumber; 
	// 教练所属棋院 引用棋院编号
	private String theCoachIsKi; 
	// 教练姓名
	private String coachName; 
	// 教练性别
	private String coachSex; 
	// 教练出生日期
	private Date coachBirthDate; 
	// 教练家庭住址
	private String coachHomeAddress; 
	// 教练身份证号
	private String trainerIdNumber; 
	// 教练联系电话
	private String coachContactPhone; 
	// 教练段位等级 选择：1-9段
	private String coachDanRank; 
	// 教练职务
	private String coachingPosition; 
	// 教练入职时间
	private Date coachInductionTime; 
	// 教练备注
	private String coachRemarks; 
	// 教练状态
	private Integer coachingState; 
	// 教练创建用户 引用用户编号
	private String coachCreatesUserName; 
	// 教练创建时间 
	private Timestamp coachCreationTime; 
	// 教练最后修改用户 引用用户编号
	private String theCoachFinallyModifiesTheUser; 
	// 教练最后修改时间
	private Timestamp coachFinalModificationTime; 
	// 教练备用字段1
	private String coachReserveField1; 
	// 教练备用字段2
	private String coachReserveField2; 
	// 教练备用字段3
	private String coachReserveField3; 


	public String getCoachnumber(){
		return coachNumber;
	}

	public void setCoachnumber(String coachNumber){
		this.coachNumber=coachNumber;
	}

	public String getThecoachiski(){
		return theCoachIsKi;
	}

	public void setThecoachiski(String theCoachIsKi){
		this.theCoachIsKi=theCoachIsKi;
	}

	public String getCoachname(){
		return coachName;
	}

	public void setCoachname(String coachName){
		this.coachName=coachName;
	}

	public String getCoachsex(){
		return coachSex;
	}

	public void setCoachsex(String coachSex){
		this.coachSex=coachSex;
	}

	public Date getCoachbirthdate(){
		return coachBirthDate;
	}

	public void setCoachbirthdate(Date coachBirthDate){
		this.coachBirthDate=coachBirthDate;
	}

	public String getCoachhomeaddress(){
		return coachHomeAddress;
	}

	public void setCoachhomeaddress(String coachHomeAddress){
		this.coachHomeAddress=coachHomeAddress;
	}

	public String getTraineridnumber(){
		return trainerIdNumber;
	}

	public void setTraineridnumber(String trainerIdNumber){
		this.trainerIdNumber=trainerIdNumber;
	}

	public String getCoachcontactphone(){
		return coachContactPhone;
	}

	public void setCoachcontactphone(String coachContactPhone){
		this.coachContactPhone=coachContactPhone;
	}

	public String getCoachdanrank(){
		return coachDanRank;
	}

	public void setCoachdanrank(String coachDanRank){
		this.coachDanRank=coachDanRank;
	}

	public String getCoachingposition(){
		return coachingPosition;
	}

	public void setCoachingposition(String coachingPosition){
		this.coachingPosition=coachingPosition;
	}

	public Date getCoachinductiontime(){
		return coachInductionTime;
	}

	public void setCoachinductiontime(Date coachInductionTime){
		this.coachInductionTime=coachInductionTime;
	}

	public String getCoachremarks(){
		return coachRemarks;
	}

	public void setCoachremarks(String coachRemarks){
		this.coachRemarks=coachRemarks;
	}

	public Integer getCoachingstate(){
		return coachingState;
	}

	public void setCoachingstate(Integer coachingState){
		this.coachingState=coachingState;
	}

	public String getCoachcreatesusername(){
		return coachCreatesUserName;
	}

	public void setCoachcreatesusername(String coachCreatesUserName){
		this.coachCreatesUserName=coachCreatesUserName;
	}

	public Timestamp getCoachcreationtime(){
		return coachCreationTime;
	}

	public void setCoachcreationtime(Timestamp coachCreationTime){
		this.coachCreationTime=coachCreationTime;
	}

	public String getThecoachfinallymodifiestheuser(){
		return theCoachFinallyModifiesTheUser;
	}

	public void setThecoachfinallymodifiestheuser(String theCoachFinallyModifiesTheUser){
		this.theCoachFinallyModifiesTheUser=theCoachFinallyModifiesTheUser;
	}

	public Timestamp getCoachfinalmodificationtime(){
		return coachFinalModificationTime;
	}

	public void setCoachfinalmodificationtime(Timestamp coachFinalModificationTime){
		this.coachFinalModificationTime=coachFinalModificationTime;
	}

	public String getCoachreservefield1(){
		return coachReserveField1;
	}

	public void setCoachreservefield1(String coachReserveField1){
		this.coachReserveField1=coachReserveField1;
	}

	public String getCoachreservefield2(){
		return coachReserveField2;
	}

	public void setCoachreservefield2(String coachReserveField2){
		this.coachReserveField2=coachReserveField2;
	}

	public String getCoachreservefield3(){
		return coachReserveField3;
	}

	public void setCoachreservefield3(String coachReserveField3){
		this.coachReserveField3=coachReserveField3;
	}

}
