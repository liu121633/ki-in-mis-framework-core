package org.kingqueen.kiinmis.model.vo.role;

import java.sql.Timestamp;

import org.kingqueen.kiinmis.model.vo.user.UserVo;

public class RoleVo {
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
	//角色创建人名称
	private String roleCreateUserName;
	// 角色创建时间头
	private Timestamp roleCreationTime;
	//创建时间尾
	private Timestamp roleCreationTimeLast;
	// 角色最后修改用户 应用用户编号
	private String roleFinallyModifyTheUser;
	// 角色最后修改时间
	private Timestamp roleLastModifiedTime;
	//创建人对象
	private UserVo createUser;
	//最后修改用户对象
	private UserVo lastUser;
	// 角色备用字段1
	private String roleReserveField1;
	// 角色备用字段2
	private String roleReserveField2;
	// 角色备用字段3
	private String roleReserveField3;

	public String getRoleCreateUserName() {
		return roleCreateUserName;
	}

	public void setRoleCreateUserName(String roleCreateUserName) {
		if ("".equals(roleCreateUserName)) {
			this.roleCreateUserName = null;
		} else {
			this.roleCreateUserName = roleCreateUserName;
		}
		
	}

	public Timestamp getRoleCreationTimeLast() {
		return roleCreationTimeLast;
	}

	public void setRoleCreationTimeLast(Timestamp roleCreationTimeLast) {
		if ("".equals(roleCreationTimeLast)) {
			this.roleCreationTimeLast = null;
		} else {
			this.roleCreationTimeLast = roleCreationTimeLast;
		}
		
	}

	public String getRoleNumber() {
		return roleNumber;
	}

	public void setRoleNumber(String roleNumber) {
		if ("".equals(roleNumber)) {
			this.roleNumber = null;
		} else {
			this.roleNumber = roleNumber;
		}
	}

	public UserVo getCreateUser() {
		return createUser;
	}

	public void setCreateUser(UserVo createUser) {
		this.createUser = createUser;
	}

	public UserVo getLastUser() {
		return lastUser;
	}

	public void setLastUser(UserVo lastUser) {
		this.lastUser = lastUser;
	}
	
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		if ("".equals(roleName)) {
			this.roleName = null;
		} else {
			this.roleName = roleName;
		}
	}

	public String getRoleDescription() {
		return roleDescription;
	}

	public void setRoleDescription(String roleDescription) {
		if ("".equals(roleDescription)) {
			this.roleDescription = null;
		} else {
			this.roleDescription = roleDescription;
		}
	}

	public Integer getRoleIsTheRoleAdefault() {
		return roleIsTheRoleAdefault;
	}

	public void setRoleIsTheRoleAdefault(Integer roleIsTheRoleAdefault) {
		if ("".equals(roleIsTheRoleAdefault)) {
			this.roleIsTheRoleAdefault = null;
		} else {
			this.roleIsTheRoleAdefault = roleIsTheRoleAdefault;
		}
	}

	public String getRoleCreatorUser() {
		return roleCreatorUser;
	}

	public void setRoleCreatorUser(String roleCreatorUser) {
		if ("".equals(roleCreatorUser)) {
			this.roleCreatorUser = null;
		} else {
			this.roleCreatorUser = roleCreatorUser;
		}
	}

	public Timestamp getRoleCreationTime() {
		return roleCreationTime;
	}

	public void setRoleCreationTime(Timestamp roleCreationTime) {
		if ("".equals(roleCreationTime)) {
			this.roleCreationTime = null;
		} else {
			this.roleCreationTime = roleCreationTime;
		}
	}

	public String getRoleFinallyModifyTheUser() {
		return roleFinallyModifyTheUser;
	}

	public void setRoleFinallyModifyTheUser(String roleFinallyModifyTheUser) {
		if ("".equals(roleFinallyModifyTheUser)) {
			this.roleFinallyModifyTheUser = roleFinallyModifyTheUser;
		} else {
			this.roleFinallyModifyTheUser = roleFinallyModifyTheUser;
		}
	}

	public Timestamp getRoleLastModifiedTime() {
		return roleLastModifiedTime;
	}

	public void setRoleLastModifiedTime(Timestamp roleLastModifiedTime) {
		if ("".equals(roleLastModifiedTime)) {
			this.roleLastModifiedTime = null;
		} else {
			this.roleLastModifiedTime = roleLastModifiedTime;
		}
	}

	public String getRoleReserveField1() {
		return roleReserveField1;
	}

	public void setRoleReserveField1(String roleReserveField1) {
		if ("".equals(roleReserveField1)) {
			this.roleReserveField1 = null;
		} else {
			this.roleReserveField1 = roleReserveField1;
		}
	}

	public String getRoleReserveField2() {
		return roleReserveField2;
	}

	public void setRoleReserveField2(String roleReserveField2) {
		if ("".equals(roleReserveField2)) {
			this.roleReserveField2 = null;
		} else {
			this.roleReserveField2 = roleReserveField2;
		}
	}

	public String getRoleReserveField3() {
		return roleReserveField3;
	}

	public void setRoleReserveField3(String roleReserveField3) {
		if ("".equals(roleReserveField3)) {
			this.roleReserveField3 = null;
		} else {
			this.roleReserveField3 = roleReserveField3;
		}
	}

}
