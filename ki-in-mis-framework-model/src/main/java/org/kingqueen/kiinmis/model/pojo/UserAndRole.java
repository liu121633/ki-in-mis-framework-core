package org.kingqueen.kiinmis.model.pojo;

public class UserAndRole {
	//编号
	private Integer userAndRoleId;
	//用户编号
	private String userNumber;
	//角色编号
	private String roleNumber;
	public Integer getUserAndRoleId() {
		return userAndRoleId;
	}
	public void setUserAndRoleId(Integer userAndRoleId) {
		this.userAndRoleId = userAndRoleId;
	}
	public String getUserNumber() {
		return userNumber;
	}
	public void setUserNumber(String userNumber) {
		this.userNumber = userNumber;
	}
	public String getRoleNumber() {
		return roleNumber;
	}
	public void setRoleNumber(String roleNumber) {
		this.roleNumber = roleNumber;
	}
	
}
