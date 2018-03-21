package org.kingqueen.kiinmis.model.pojo;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



public class Student {

	// 学员编号 学员的唯一标识
	private String studentNumber;
	// 学校编号 自动生成
	private String schoolNumber;
	
	private School sc;
	// 学员姓名
	private String studentName;
	// 学员的性别
	private Integer studentSex;
	// 出生日期 使用指定格式yyyy-MM-dd
	private Date studentBirthDate;
	// 家庭住址
	private String studentHomeAddress;
	// 学员就读学校 引用学校管理 下拉选择
	private String studentsAttendingSchool;
	// 学员联系电话 联系电话 11位
	private String studentContactPhoneNumber;
	// 学员父亲姓名
	private String nameOfStudentFather;
	// 学员父亲电话
	private String studentFatherPhone;
	// 学员母亲姓名
	private String nameOfStudentMother;
	// 学员母亲电话
	private String studentMotherPhone;
	
	
	private String stuPhone;
	public String getStuPhone() {
		return stuPhone;
	}

	public void setStuPhone(String stuPhone) {
		this.stuPhone = stuPhone;
	}

	// 学员所属棋院 引用棋院编号 只能为当前用户所属棋院。如为管理员，可选择下级棋院
	private String theStudentsAreKiin;
	
	private Kiin kiin;
	// 学员入院时间 入院时间 使用指定格式yyyy-MM-dd 默认当前日期
	private Date studentAdmissionTime;
	// 学员所在班级
	private String studentsInTheClass;
	
	private Grade grade;
	// 学员教练 引用教练编号
	private String studentCoach;
	
	private Coach coach1;
	public Coach getCoach1() {
		return coach1;
	}

	public void setCoach1(Coach coach1) {
		this.coach1 = coach1;
	}

	// 学员备注
	private String studentRemarks;
	// 学员状态 0：正常1：未缴费2: 欠费3：流失4：休学10：注销
	private Integer studentStatus;
	// 创建的用户 默认为当前用户
	private String studentCreateUserName;
	
	private User user1;
	// 创建用户的时间 自动获取 精确到秒
	private Timestamp studentCreationTime;
	// 最后操作功能的用户 自动获取 默认为当前用户
	private String theStudentFinallyModifiesTheUser;
	
	private User user2;
	// 最后操作用户的时间 日期 自动获取 精确到秒
	private Timestamp studentFinalModificationTime;
	// 学员备用字段1
	private String studentReserveField1;
	// 学员备用字段2
	private String studentReserveField2;
	// 学员备用字段3
	private String studentReserveField3;
	
	
	private List<Classtime> classtimeList=new ArrayList<Classtime>();

	public List<Classtime> getClasstimeList() {
		return classtimeList;
	}

	public void setClasstimeList(List<Classtime> classtimeList) {
		this.classtimeList = classtimeList;
	}

	public String getStudentNumber() {
		return studentNumber;
	}

	public void setStudentNumber(String studentNumber) {
		this.studentNumber = studentNumber;
	}

	public String getSchoolNumber() {
		return schoolNumber;
	}

	public void setSchoolNumber(String schoolNumber) {
		this.schoolNumber = schoolNumber;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public Integer getStudentSex() {
		return studentSex;
	}

	public void setStudentSex(Integer studentSex) {
		this.studentSex = studentSex;
	}

	

	public String getStudentHomeAddress() {
		return studentHomeAddress;
	}

	public void setStudentHomeAddress(String studentHomeAddress) {
		this.studentHomeAddress = studentHomeAddress;
	}

	public String getStudentsAttendingSchool() {
		return studentsAttendingSchool;
	}

	public void setStudentsAttendingSchool(String studentsAttendingSchool) {
		this.studentsAttendingSchool = studentsAttendingSchool;
	}


	public String getStudentContactPhoneNumber() {
		return studentContactPhoneNumber;
	}

	public void setStudentContactPhoneNumber(String studentContactPhoneNumber) {
		this.studentContactPhoneNumber = studentContactPhoneNumber;
	}

	public String getNameOfStudentFather() {
		return nameOfStudentFather;
	}

	public void setNameOfStudentFather(String nameOfStudentFather) {
		this.nameOfStudentFather = nameOfStudentFather;
	}

	public String getStudentFatherPhone() {
		return studentFatherPhone;
	}

	public void setStudentFatherPhone(String studentFatherPhone) {
		this.studentFatherPhone = studentFatherPhone;
	}

	public String getNameOfStudentMother() {
		return nameOfStudentMother;
	}

	public void setNameOfStudentMother(String nameOfStudentMother) {
		this.nameOfStudentMother = nameOfStudentMother;
	}

	public String getStudentMotherPhone() {
		return studentMotherPhone;
	}

	public void setStudentMotherPhone(String studentMotherPhone) {
		this.studentMotherPhone = studentMotherPhone;
	}

	public String getTheStudentsAreKiin() {
		return theStudentsAreKiin;
	}

	public void setTheStudentsAreKiin(String theStudentsAreKiin) {
		this.theStudentsAreKiin = theStudentsAreKiin;
	}

	

	public Date getStudentBirthDate() {
		return studentBirthDate;
	}

	public void setStudentBirthDate(Date studentBirthDate) {
		this.studentBirthDate = studentBirthDate;
	}

	public Date getStudentAdmissionTime() {
		return studentAdmissionTime;
	}

	public void setStudentAdmissionTime(Date studentAdmissionTime) {
		this.studentAdmissionTime = studentAdmissionTime;
	}

	public String getStudentsInTheClass() {
		return studentsInTheClass;
	}

	public void setStudentsInTheClass(String studentsInTheClass) {
		this.studentsInTheClass = studentsInTheClass;
	}

	public String getStudentCoach() {
		return studentCoach;
	}

	public void setStudentCoach(String studentCoach) {
		this.studentCoach = studentCoach;
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
		this.studentStatus = studentStatus;
	}

	public String getStudentCreateUserName() {
		return studentCreateUserName;
	}

	public void setStudentCreateUserName(String studentCreateUserName) {
		this.studentCreateUserName = studentCreateUserName;
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

	public Timestamp getStudentFinalModificationTime() {
		return studentFinalModificationTime;
	}

	public void setStudentFinalModificationTime(
			Timestamp studentFinalModificationTime) {
		this.studentFinalModificationTime = studentFinalModificationTime;
	}

	public String getStudentReserveField1() {
		return studentReserveField1;
	}

	public void setStudentReserveField1(String studentReserveField1) {
		this.studentReserveField1 = studentReserveField1;
	}

	public String getStudentReserveField2() {
		return studentReserveField2;
	}

	public void setStudentReserveField2(String studentReserveField2) {
		this.studentReserveField2 = studentReserveField2;
	}

	public String getStudentReserveField3() {
		return studentReserveField3;
	}

	public void setStudentReserveField3(String studentReserveField3) {
		this.studentReserveField3 = studentReserveField3;
	}
	
	public School getSc() {
		return sc;
	}

	public void setSc(School sc) {
		this.sc = sc;
	}

	public Kiin getKiin() {
		return kiin;
	}

	public void setKiin(Kiin kiin) {
		this.kiin = kiin;
	}

	public Grade getGrade() {
		return grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}

	
	public User getUser1() {
		return user1;
	}

	public void setUser1(User user1) {
		this.user1 = user1;
	}

	public User getUser2() {
		return user2;
	}

	public void setUser2(User user2) {
		this.user2 = user2;
	}

}
