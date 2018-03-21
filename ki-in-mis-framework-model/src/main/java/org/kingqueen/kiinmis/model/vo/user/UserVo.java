package org.kingqueen.kiinmis.model.vo.user;

import java.sql.Timestamp;
import java.util.List;

import org.kingqueen.kiinmis.model.pojo.Kiin;
import org.kingqueen.kiinmis.model.vo.kiin.KiinVo;
import org.kingqueen.kiinmis.model.vo.position.PositionVo;
/**
 * @ClassName UserVo
 * @description 用户的实体类
 * @author wzq
 * @date 2017年12月2日
 * @version v1.0
 */
public class UserVo {

	// 用户编号
	private String userNumber;
	// 用户名
	private String userName;
	// 用户登陆名
	private String userLoginName;
	// 用户密码
	private String userPassword;
	// 用户级别 单选按钮  可通过配置或数据库进行增删，0、1、2分别代表 
	private Integer userLevel;
	// 用户职位
	private String userPosition;
	// 用户角色
	private String userRoles;
	// 用户创建时间
	private Timestamp userCreationTime;
	// 用户最后修改人
	private String userFinallyModifiesTheUser; 
	// 用户最后修改时间
	private Timestamp userLastModificationTime; 
	// 用户状态
	private Integer userStatus; 
	//职位对象
	private PositionVo positionVo;
	//创建用户的编号
	private String userfounder;
	
	//部门的集合
	private List<Kiin> kiinList;
	//棋院的对象
	private KiinVo kiinVo;
	//部门集合的字符
	private String kiinStr;
	
	//部门集合的id集合
	private String kiinIdStr;
	
	//职位的名称
	private String userPositionName;
	
	
	//用户的角色名称
	private String userRolesName;
	
	//创建用户人的名称
	private String userfounderName;
	
	//用户最后修改人名称
	private String userFinallyModifiesTheUserName;
	
	
	// 用户备用字段1
	private String userBackupField1; 
	// 用户备用字段2
	private String userBackupField2; 
	// 用户备用字段3
	private String userBackupField3;
	
	public PositionVo getPositionVo() {
		return positionVo;
	}
	public void setPositionVo(PositionVo positionVo) {
		this.positionVo = positionVo;
	}
	public KiinVo getKiinVo() {
		return kiinVo;
	}
	public void setKiinVo(KiinVo kiinVo) {
		this.kiinVo = kiinVo;
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
	public String getUserLoginName() {
		return userLoginName;
	}
	public void setUserLoginName(String userLoginName) {
		this.userLoginName = userLoginName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public Integer getUserLevel() {
		return userLevel;
	}
	public void setUserLevel(Integer userLevel) {
		this.userLevel = userLevel;
	}
	public String getUserPosition() {
		return userPosition;
	}
	public void setUserPosition(String userPosition) {
		this.userPosition = userPosition;
	}
	public String getUserRoles() {
		return userRoles;
	}
	public void setUserRoles(String userRoles) {
		this.userRoles = userRoles;
	}
	public Timestamp getUserCreationTime() {
		return userCreationTime;
	}
	public void setUserCreationTime(Timestamp userCreationTime) {
		this.userCreationTime = userCreationTime;
	}
	public String getUserFinallyModifiesTheUser() {
		return userFinallyModifiesTheUser;
	}
	public void setUserFinallyModifiesTheUser(String userFinallyModifiesTheUser) {
		this.userFinallyModifiesTheUser = userFinallyModifiesTheUser;
	}
	public Timestamp getUserLastModificationTime() {
		return userLastModificationTime;
	}
	public void setUserLastModificationTime(Timestamp userLastModificationTime) {
		this.userLastModificationTime = userLastModificationTime;
	}
	public Integer getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(Integer userStatus) {
		this.userStatus = userStatus;
	}
	public String getUserBackupField1() {
		return userBackupField1;
	}
	public void setUserBackupField1(String userBackupField1) {
		this.userBackupField1 = userBackupField1;
	}
	public String getUserBackupField2() {
		return userBackupField2;
	}
	public void setUserBackupField2(String userBackupField2) {
		this.userBackupField2 = userBackupField2;
	}
	public String getUserBackupField3() {
		return userBackupField3;
	}
	public void setUserBackupField3(String userBackupField3) {
		this.userBackupField3 = userBackupField3;
	}
	public String getUserfounder() {
		return userfounder;
	}
	public void setUserfounder(String userfounder) {
		this.userfounder = userfounder;
	}

	public String getUserPositionName() {
		return userPositionName;
	}
	public void setUserPositionName(String userPositionName) {
		this.userPositionName = userPositionName;
	}
	public String getUserRolesName() {
		return userRolesName;
	}
	public void setUserRolesName(String userRolesName) {
		this.userRolesName = userRolesName;
	}
	public String getUserfounderName() {
		return userfounderName;
	}
	public void setUserfounderName(String userfounderName) {
		this.userfounderName = userfounderName;
	}
	public String getUserFinallyModifiesTheUserName() {
		return userFinallyModifiesTheUserName;
	}
	public void setUserFinallyModifiesTheUserName(String userFinallyModifiesTheUserName) {
		this.userFinallyModifiesTheUserName = userFinallyModifiesTheUserName;
	}
	public List<Kiin> getKiinList() {
		return kiinList;
	}
	public void setKiinList(List<Kiin> kiinList) {
		this.kiinList = kiinList;
	}
	public String getKiinStr() {
		return kiinStr;
	}
	public void setKiinStr(String kiinStr) {
		this.kiinStr = kiinStr;
	}
	public String getKiinIdStr() {
		return kiinIdStr;
	}
	public void setKiinIdStr(String kiinIdStr) {
		this.kiinIdStr = kiinIdStr;
	}
 
	


}
