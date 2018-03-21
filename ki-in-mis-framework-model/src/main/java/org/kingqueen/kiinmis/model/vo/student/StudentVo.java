package org.kingqueen.kiinmis.model.vo.student;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.kingqueen.kiinmis.model.pojo.Classtime;
import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;

public class StudentVo {
	// *学员编号 学员的唯一标识
	private String studentNumber;
	// *学校名称
	private String schoolName;
	private String schoolNumber;
	// * 学员姓名
	private String studentName;
	// *学员的性别
	private Integer studentSex;
	// * 出生日期 使用指定格式yyyy-MM-dd
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date studentBirthDate;
	// 入院时间
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date studentAdmissionTime;

	// 入院时间查询条件
	private Date studentAdmissionTimeBegin;
	private Date studentAdmissionTimeEnd;

	// 家庭住址
	private String studentHomeAddress;

	// 学员联系电话 联系电话 11位
	private String studentContactPhoneNumber;

	// *学员所属棋院 引用棋院编号 只能为当前用户所属棋院。如为管理员，可选择下级棋院
	private String KiinName;
	private String KiinNumber;

	// 学员所在班级
	private String gradeName;
	
	private String gradeNumber;
	

	// * 学员教练 引用教练编号
	private String CoachName;
	private String CoachNumber;

	// 学员备注
	private String studentRemarks;
	// * 学员状态 0：正常1：未缴费2: 欠费3：流失4：休学10：注销
	private Integer studentStatus;

	private String NameOfStudentFather;
	private String StudentFatherPhone;
	private String NameOfStudentMother;
	private String StudentMotherPhone;
	// 创建的用户 默认为当前用户
	private String studentCreateUserName;

	private String stuUser1;
	// 创建用户的时间 自动获取 精确到秒
	private Timestamp studentCreationTime;
	// 最后操作功能的用户 自动获取 默认为当前用户
	private String theStudentFinallyModifiesTheUser;
	private String stuUser2;

	// 最后操作用户的时间 日期 自动获取 精确到秒
	private Timestamp studentFinalModificationTime;
	
	//周几
	private String whatdayisit;
	
	//时间段
	private String schooltime;
	

	
	private List<Classtime> classtimeList=new ArrayList<Classtime>();

	public List<Classtime> getClasstimeList() {
		return classtimeList;
	}

	public void setClasstimeList(List<Classtime> classtimeList) {
		this.classtimeList = classtimeList;
	}
	public String getWhatdayisit() {
		return whatdayisit;
	}

	public void setWhatdayisit(String whatdayisit) {
		if ("-1".equals(whatdayisit)) {
			whatdayisit = null;
			return;
		}
		this.whatdayisit = whatdayisit;
	}

	public String getSchooltime() {
		return schooltime;
	}

	public void setSchooltime(String schooltime) {
		if ("-1".equals(schooltime)) {
			schooltime = null;
			return;
		}
		this.schooltime = schooltime;
	}

	public String getNameOfStudentFather() {
		return NameOfStudentFather;
	}

	public void setNameOfStudentFather(String nameOfStudentFather) {
		NameOfStudentFather = nameOfStudentFather;
	}

	public String getStudentFatherPhone() {
		return StudentFatherPhone;
	}

	public void setStudentFatherPhone(String studentFatherPhone) {
		StudentFatherPhone = studentFatherPhone;
	}

	public String getNameOfStudentMother() {
		return NameOfStudentMother;
	}

	public void setNameOfStudentMother(String nameOfStudentMother) {
		NameOfStudentMother = nameOfStudentMother;
	}

	public String getStudentMotherPhone() {
		return StudentMotherPhone;
	}

	public void setStudentMotherPhone(String studentMotherPhone) {
		StudentMotherPhone = studentMotherPhone;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		if ("-1".equals(schoolName)||"".equals(schoolName)) {
			this.schoolName = null;
			return;
		}
		this.schoolName = schoolName;
	}

	public String getStudentHomeAddress() {
		return studentHomeAddress;
	}

	public void setStudentHomeAddress(String studentHomeAddress) {
		this.studentHomeAddress = studentHomeAddress;
	}

	public String getStudentContactPhoneNumber() {
		return studentContactPhoneNumber;
	}

	public void setStudentContactPhoneNumber(String studentContactPhoneNumber) {
		if ("".equals(studentContactPhoneNumber)) {
			studentContactPhoneNumber = null;
			return;
		}
		this.studentContactPhoneNumber = studentContactPhoneNumber;
	}

	public String getKiinName() {
		return KiinName;
	}

	public void setKiinName(String kiinName) {
		if (kiinName == "") {
			kiinName = null;
			return;
		}
		KiinName = kiinName;
	}

	

	public String getCoachName() {
		return CoachName;
	}

	public void setCoachName(String coachName) {
		if ("".equals(coachName)) {
			coachName = null;
			return;
		}
		CoachName = coachName;
	}

	public String getStudentRemarks() {
		return studentRemarks;
	}

	public void setStudentRemarks(String studentRemarks) {
		this.studentRemarks = studentRemarks;
	}

	public Integer getStudentStatus() {
		return studentStatus;
	}

	public void setStudentStatus(Integer studentStatus) {
		if (studentStatus == -1) {
			studentStatus = null;
			return;
		}
		this.studentStatus = studentStatus;
	}

	public String getStudentNumber() {
		return studentNumber;
	}

	public void setStudentNumber(String studentNumber) {
		System.out.println(studentNumber + "DS333333333333");
		if ("".equals(studentNumber)) {
			studentNumber = null;
			return;
		}
		this.studentNumber = studentNumber;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		if ("".equals(studentName)) {
			studentName = null;
			return;
		}
		this.studentName = studentName;
	}

	public Integer getStudentSex() {
		return studentSex;
	}

	public void setStudentSex(Integer studentSex) {
		if (studentSex == -1) {
			studentSex = null;
			return;
		}
		this.studentSex = studentSex;
	}

	public String getSchoolNumber() {
		return schoolNumber;
	}

	public void setSchoolNumber(String schoolNumber) {
		if ("".equals(schoolNumber)||"-1".equals(schoolNumber)) {
			schoolNumber = null;
			return;
		}
		this.schoolNumber = schoolNumber;
	}

	public String getKiinNumber() {
		return KiinNumber;
	}

	public void setKiinNumber(String kiinNumber) {
		if ("".equals(kiinNumber)||"-1".equals(kiinNumber)||"全部".equals(kiinNumber)) {
			kiinNumber = null;
			return;
		}
		KiinNumber = kiinNumber;
	}

	public String getCoachNumber() {
		return CoachNumber;
	}

	public void setCoachNumber(String coachNumber) {
		if ("".equals(coachNumber)||"-1".equals(coachNumber)||"全部".equals(coachNumber)) {
			coachNumber = null;
			return;
		}
		CoachNumber = coachNumber;
	}

	public Date getStudentAdmissionTime() {
		return studentAdmissionTime;
	}

	public void setStudentAdmissionTime(Date studentAdmissionTime) {
		this.studentAdmissionTime = studentAdmissionTime;
	}

	public Date getStudentAdmissionTimeBegin() {
		return studentAdmissionTimeBegin;
	}

	public void setStudentAdmissionTimeBegin(Date studentAdmissionTimeBegin) {
		this.studentAdmissionTimeBegin = studentAdmissionTimeBegin;
	}

	public Date getStudentAdmissionTimeEnd() {
		return studentAdmissionTimeEnd;
	}

	public void setStudentAdmissionTimeEnd(Date studentAdmissionTimeEnd) {
		this.studentAdmissionTimeEnd = studentAdmissionTimeEnd;
	}
	public Date getStudentBirthDate() {
		return studentBirthDate;
	}

	public void setStudentBirthDate(Date studentBirthDate) {
		this.studentBirthDate = studentBirthDate;
	}


	public String getStudentCreateUserName() {
		return studentCreateUserName;
	}

	public void setStudentCreateUserName(String studentCreateUserName) {
		this.studentCreateUserName = studentCreateUserName;
	}

	public String getStuUser1() {
		return stuUser1;
	}

	public void setStuUser1(String stuUser1) {
		this.stuUser1 = stuUser1;
	}

	public Timestamp getStudentCreationTime() {
		return studentCreationTime;
	}

	public void setStudentCreationTime(Timestamp studentCreationTime) {
		this.studentCreationTime = studentCreationTime;
	}

	public String getTheStudentFinallyModifiesTheUser() {
		return theStudentFinallyModifiesTheUser;
	}

	public void setTheStudentFinallyModifiesTheUser(
			String theStudentFinallyModifiesTheUser) {
		this.theStudentFinallyModifiesTheUser = theStudentFinallyModifiesTheUser;
	}

	public String getStuUser2() {
		return stuUser2;
	}

	public void setStuUser2(String stuUser2) {
		this.stuUser2 = stuUser2;
	}

	public Timestamp getStudentFinalModificationTime() {
		return studentFinalModificationTime;
	}

	public void setStudentFinalModificationTime(
			Timestamp studentFinalModificationTime) {
		this.studentFinalModificationTime = studentFinalModificationTime;
	}

	public String getGradeName() {
		return gradeName;
	}

	public void setGradeName(String gradeName) {
		
		this.gradeName = gradeName;
	}

	public String getGradeNumber() {
		return gradeNumber;
	}

	public void setGradeNumber(String gradeNumber) {
		if("".equals(gradeNumber)||"-1".equals(gradeNumber)||"全部".equals(gradeNumber)){
			gradeNumber=null;
		}
		this.gradeNumber = gradeNumber;
	}
	
}
