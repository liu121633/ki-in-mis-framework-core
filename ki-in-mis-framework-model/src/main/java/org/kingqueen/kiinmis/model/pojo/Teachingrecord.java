package org.kingqueen.kiinmis.model.pojo;

import java.sql.Time;

public class Teachingrecord {

	// 教练学生关联编号
	private Integer teachingRecordNumber; 
	// 教练编号 
	private String coachNumber; 
	// 学员编号 学员的唯一标识
	private String studentNumber; 
	// 开始教学日期
	private Time startTeachingDate; 
	// 结束教学日期
	private Time endTheTeachingDate;
	public Integer getTeachingRecordNumber() {
		return teachingRecordNumber;
	}
	public void setTeachingRecordNumber(Integer teachingRecordNumber) {
		this.teachingRecordNumber = teachingRecordNumber;
	}
	public String getCoachNumber() {
		return coachNumber;
	}
	public void setCoachNumber(String coachNumber) {
		this.coachNumber = coachNumber;
	}
	public String getStudentNumber() {
		return studentNumber;
	}
	public void setStudentNumber(String studentNumber) {
		this.studentNumber = studentNumber;
	}
	public Time getStartTeachingDate() {
		return startTeachingDate;
	}
	public void setStartTeachingDate(Time startTeachingDate) {
		this.startTeachingDate = startTeachingDate;
	}
	public Time getEndTheTeachingDate() {
		return endTheTeachingDate;
	}
	public void setEndTheTeachingDate(Time endTheTeachingDate) {
		this.endTheTeachingDate = endTheTeachingDate;
	} 



}
