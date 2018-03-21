package org.kingqueen.kiinmis.model.pojo;

public class RoleAndMenu {
	//编号
	private Integer roleAndMenuId;
	//角色编号
	private String roleNumber;
	//功能编号
	private Integer menuId;
	public Integer getRoleAndMenuId() {
		return roleAndMenuId;
	}
	public void setRoleAndMenuId(Integer roleAndMenuId) {
		this.roleAndMenuId = roleAndMenuId;
	}
	public String getRoleNumber() {
		return roleNumber;
	}
	public void setRoleNumber(String roleNumber) {
		this.roleNumber = roleNumber;
	}
	public Integer getMenuId() {
		return menuId;
	}
	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}
	
}
