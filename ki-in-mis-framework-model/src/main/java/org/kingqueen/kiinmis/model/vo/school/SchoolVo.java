package org.kingqueen.kiinmis.model.vo.school;

import java.sql.Timestamp;
import org.kingqueen.kiinmis.model.vo.user.UserVo;

public class SchoolVo {
	// 学校编号 自动生成
	private String schoolNumber;
	// 学校名称
	private String schoolName;
	// 学校地址
	private String schoolAddress;
	// 学校备注
	private String schoolNotes;
	// 学校创建用户 引用用户编号
	private String schoolCreateUserName;
	// 学校创建用户 引用用户
	private String schoolCreateUser;
	// 学校创建时间 精确到秒
	private Timestamp schoolCreationTime;
	//学校创建时间尾
	private Timestamp schoolCreationTimeEnd;
	// 学校最后修改用户 引用用户编号
	private String schoolFinallyModifiesUserName;
	//学校最后修改用户 引用用户
	private String schoolFinallyModifiesUser;
	// 学校最后修改时间
	private Timestamp lastRevisionTimeOfSchool;
	//学校最后修改时间尾
	private Timestamp lastRevisionTimeOfSchoolEnd;
	// 学校状态
	private String schoolState;
	// 学校备用字段1
	private String schoolReserveField1;
	// 学校备用字段2
	private String schoolReserveField2;
	// 学校备用字段3
	private String schoolReserveField3;
	// 用户对象
	private UserVo userVo;
	//最后修改的用户对象
	private UserVo userLastVo;
	public Timestamp getSchoolCreationTimeEnd() {
		return schoolCreationTimeEnd;
	}
	public void setSchoolCreationTimeEnd(Timestamp schoolCreationTimeEnd) {
		if ("".equals(schoolCreationTimeEnd)) {
			this.schoolCreationTimeEnd = null;
		} else {
			this.schoolCreationTimeEnd = schoolCreationTimeEnd;
		}
	}
	public String getSchoolCreateUser() {
		return schoolCreateUser;
	}
	public void setSchoolCreateUser(String schoolCreateUser) {
		this.schoolCreateUser = schoolCreateUser;
	}
	public UserVo getUserLastVo() {
		return userLastVo;
	}
	public void setUserLastVo(UserVo userLastVo) {
		this.userLastVo = userLastVo;
	}
	public UserVo getUserVo() {
		return userVo;
	}
	public void setUserVo(UserVo userVo) {
		this.userVo = userVo;
	}
	public String getSchoolNumber() {
		return schoolNumber;
	}
	public void setSchoolNumber(String schoolNumber) {
		if ("".equals(schoolNumber)) {
			this.schoolNumber = null;
		} else {
			this.schoolNumber = schoolNumber;
		}
	}
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		if ("".equals(schoolName)) {
			this.schoolName = null;
		} else {
			this.schoolName = schoolName;
		}
	}
	public String getSchoolFinallyModifiesUser() {
		return schoolFinallyModifiesUser;
	}
	public void setSchoolFinallyModifiesUser(String schoolFinallyModifiesUser) {
		if ("".equals(schoolFinallyModifiesUser)) {
			this.schoolFinallyModifiesUser = null;
		} else {
			this.schoolFinallyModifiesUser = schoolFinallyModifiesUser;
		}
	}
	public String getSchoolAddress() {
		return schoolAddress;
	}
	public void setSchoolAddress(String schoolAddress) {
		if ("".equals(schoolAddress)) {
			this.schoolAddress = null;
		} else {
			this.schoolAddress = schoolAddress;
		}
	}
	public String getSchoolNotes() {
		return schoolNotes;
	}
	public Timestamp getLastRevisionTimeOfSchoolEnd() {
		return lastRevisionTimeOfSchoolEnd;
	}
	public void setLastRevisionTimeOfSchoolEnd(Timestamp lastRevisionTimeOfSchoolEnd) {
		if ("".equals(lastRevisionTimeOfSchoolEnd)) {
			this.lastRevisionTimeOfSchoolEnd = null;
		} else {
			this.lastRevisionTimeOfSchoolEnd = lastRevisionTimeOfSchoolEnd;
		}
	}
	public void setSchoolNotes(String schoolNotes) {
		if ("".equals(schoolNotes)) {
			this.schoolNotes = null;
		} else {
			this.schoolNotes = schoolNotes;
		}
	}
	public String getSchoolCreateUserName() {
		return schoolCreateUserName;
	}
	public void setSchoolCreateUserName(String schoolCreateUserName) {
		if ("".equals(schoolCreateUserName)) {
			this.schoolCreateUserName = null;
		} else {
			this.schoolCreateUserName = schoolCreateUserName;
		}
	}
	public Timestamp getSchoolCreationTime() {
		return schoolCreationTime;
	}
	public void setSchoolCreationTime(Timestamp schoolCreationTime) {
		if ("".equals(schoolCreationTime)) {
			this.schoolCreationTime = null;
		} else {
			this.schoolCreationTime = schoolCreationTime;
		}
	}
	public String getSchoolFinallyModifiesUserName() {
		return schoolFinallyModifiesUserName;
	}
	public void setSchoolFinallyModifiesUserName(String schoolFinallyModifiesUserName) {
		if ("".equals(schoolFinallyModifiesUserName)) {
			this.schoolFinallyModifiesUserName = null;
		} else {
			this.schoolFinallyModifiesUserName = schoolFinallyModifiesUserName;
		}
	}
	public Timestamp getLastRevisionTimeOfSchool() {
		return lastRevisionTimeOfSchool;
	}
	public void setLastRevisionTimeOfSchool(Timestamp lastRevisionTimeOfSchool) {
		if ("".equals(lastRevisionTimeOfSchool)) {
			this.lastRevisionTimeOfSchool = null;
		} else {
			this.lastRevisionTimeOfSchool = lastRevisionTimeOfSchool;
		}
	}
	public String getSchoolState() {
		return schoolState;
	}
	public void setSchoolState(String schoolState) {
		if ("".equals(schoolState)) {
			this.schoolState = null;
		} else {
			this.schoolState = schoolState;
		}
	}
	public String getSchoolReserveField1() {
		return schoolReserveField1;
	}
	public void setSchoolReserveField1(String schoolReserveField1) {
		if ("".equals(schoolReserveField1)) {
			this.schoolReserveField1 = null;
		} else {
			this.schoolReserveField1 = schoolReserveField1;
		}
	}
	public String getSchoolReserveField2() {
		return schoolReserveField2;
	}
	public void setSchoolReserveField2(String schoolReserveField2) {
		if ("".equals(schoolReserveField2)) {
			this.schoolReserveField2 = null;
		} else {
			this.schoolReserveField2 = schoolReserveField2;
		}
	}
	public String getSchoolReserveField3() {
		return schoolReserveField3;
	}
	public void setSchoolReserveField3(String schoolReserveField3) {
		if ("".equals(schoolReserveField3)) {
			this.schoolReserveField3 = null;
		} else {
			this.schoolReserveField3 = schoolReserveField3;
		}
	}

}