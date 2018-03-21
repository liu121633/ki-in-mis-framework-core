package org.kingqueen.kiinmis.model.vo.position;

import java.security.Timestamp;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
/**
 * @ClassName PositionVo
 * @description 职位的Vo类
 * @author wzq
 * @date 2017年12月2日
 * @version v1.0
 */
public class PositionVo {

	// 职位编号
	private String positionNumber; 
	// 职位名称
	private String positionName; 
	// 职位备注
	private String positionRemarks; 
	// 职位创建用户 引用用户编号
	private String positionCreator; 
	// 职位创建时间
	@JSONField(format = "yyyy-MM-dd hh:mm:ss")
	private Date positionCreationTime; 
	// 职位最后修改用户 引用用户编号
	private String positionFinallyModifyTheUser; 
	// 职位最后修改时间
	@JSONField(format = "yyyy-MM-dd hh:mm:ss")  
	private Date positionLastModifiedTime; 
	// 职位状态
	private Integer positionStatus; 
	
	
	//创建人名称
	private String PositionCreatorName;
	
	//最后修改人名称
	private String positionFinallyModifyTheUserName;
	
	public String getPositionCreatorName() {
		return PositionCreatorName;
	}
	public void setPositionCreatorName(String positionCreatorName) {
		PositionCreatorName = positionCreatorName;
	}
	public String getPositionFinallyModifyTheUserName() {
		return positionFinallyModifyTheUserName;
	}
	public void setPositionFinallyModifyTheUserName(String positionFinallyModifyTheUserName) {
		this.positionFinallyModifyTheUserName = positionFinallyModifyTheUserName;
	}
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
	
	public String getPositionFinallyModifyTheUser() {
		return positionFinallyModifyTheUser;
	}
	public void setPositionFinallyModifyTheUser(String positionFinallyModifyTheUser) {
		this.positionFinallyModifyTheUser = positionFinallyModifyTheUser;
	}
	
	public Date getPositionCreationTime() {
		return positionCreationTime;
	}
	public void setPositionCreationTime(Date positionCreationTime) {
		this.positionCreationTime = positionCreationTime;
	}
	public Date getPositionLastModifiedTime() {
		return positionLastModifiedTime;
	}
	public void setPositionLastModifiedTime(Date positionLastModifiedTime) {
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
