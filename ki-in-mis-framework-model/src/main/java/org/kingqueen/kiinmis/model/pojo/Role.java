package org.kingqueen.kiinmis.model.pojo;

import java.sql.Timestamp;

public class Role {

	// 角色编号
	private String roleNumber; 
	// 角色名称
	private String roleName; 
	// 角色描述
	private String roleDescription; 
	// 角色是否为默认角色
	private Integer roleIsTheRoleAdefault; 
	// 角色创建人 应用用户编号
	private String roleCreatorUser; 
	// 角色创建时间
	private Timestamp roleCreationTime; 
	// 角色最后修改用户 应用用户编号
	private String roleFinallyModifyTheUser; 
	// 角色最后修改时间
	private Timestamp roleLastModifiedTime; 
	// 角色备用字段1
	private String roleReserveField1; 
	// 角色备用字段2
	private String roleReserveField2; 
	// 角色备用字段3
	private String roleReserveField3;
	public String getRoleNumber() {
		return roleNumber;
	}
	public void setRoleNumber(String roleNumber) {
		this.roleNumber = roleNumber;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRoleDescription() {
		return roleDescription;
	}
	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}
	public Integer getRoleIsTheRoleAdefault() {
		return roleIsTheRoleAdefault;
	}
	public void setRoleIsTheRoleAdefault(Integer roleIsTheRoleAdefault) {
		this.roleIsTheRoleAdefault = roleIsTheRoleAdefault;
	}
	public String getRoleCreatorUser() {
		return roleCreatorUser;
	}
	public void setRoleCreatorUser(String roleCreatorUser) {
		this.roleCreatorUser = roleCreatorUser;
	}
	public Timestamp getRoleCreationTime() {
		return roleCreationTime;
	}
	public void setRoleCreationTime(Timestamp roleCreationTime) {
		this.roleCreationTime = roleCreationTime;
	}
	public String getRoleFinallyModifyTheUser() {
		return roleFinallyModifyTheUser;
	}
	public void setRoleFinallyModifyTheUser(String roleFinallyModifyTheUser) {
		this.roleFinallyModifyTheUser = roleFinallyModifyTheUser;
	}
	public Timestamp getRoleLastModifiedTime() {
		return roleLastModifiedTime;
	}
	public void setRoleLastModifiedTime(Timestamp roleLastModifiedTime) {
		this.roleLastModifiedTime = roleLastModifiedTime;
	}
	public String getRoleReserveField1() {
		return roleReserveField1;
	}
	public void setRoleReserveField1(String roleReserveField1) {
		this.roleReserveField1 = roleReserveField1;
	}
	public String getRoleReserveField2() {
		return roleReserveField2;
	}
	public void setRoleReserveField2(String roleReserveField2) {
		this.roleReserveField2 = roleReserveField2;
	}
	public String getRoleReserveField3() {
		return roleReserveField3;
	}
	public void setRoleReserveField3(String roleReserveField3) {
		this.roleReserveField3 = roleReserveField3;
	} 


}
