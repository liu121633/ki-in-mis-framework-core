package org.kingqueen.kiinmis.common.vo;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;



public class TeacherVo {
	// 教练编号
			private String coachNumber; 
			
			// 教练所属棋院 引用棋院编号
			private String theCoachIsKiNumber; 
			// 教练所属棋院 引用棋院名称
			private String theCoachIsKiName; 
			// 教练姓名
			private String coachName; 
			// 教练性别
			private String coachSex; 
			// 教练出生日期
			@DateTimeFormat(pattern="yyyy-MM-dd")
			@JSONField(format="yyyy-MM-dd")
			private Date coachBirthDate; 
			// 教练家庭住址
			private String coachHomeAddress; 
			// 教练身份证号
			private String trainerIdNumber; 
			public String getTrainerIdNumber() {
				return trainerIdNumber;
			}
			public void setTrainerIdNumber(String trainerIdNumber) {
				this.trainerIdNumber = trainerIdNumber;
			}
			// 教练联系电话
			private String coachContactPhone; 
			// 教练段位等级 选择：1-9段
			private String coachDanRank; 
			// 教练职务
			private String coachingPosition; 
			// 教练入职时间
			@DateTimeFormat(pattern="yyyy-MM-dd")
			@JSONField(format="yyyy-MM-dd")
			private Date coachInductionTime; 
			
			private Date coachInductionTimeBegin; 
			
			private Date coachInductionTimeEnd; 
			// 教练备注
			private String coachRemarks; 
			
			private Integer coachingState; 
			
			public String getCoachNumber() {
				return coachNumber;
			}
			public void setCoachNumber(String coachNumber) {
				this.coachNumber = coachNumber;
			}
			public String getTheCoachIsKiName() {
				return theCoachIsKiName;
			}
			public void setTheCoachIsKiName(String theCoachIsKiName) {
				if("".equals(theCoachIsKiName)){
					theCoachIsKiName=null;
					return;
				}
				this.theCoachIsKiName = theCoachIsKiName;
			}
			public String getCoachName() {
				return coachName;
			}
			public void setCoachName(String coachName) {
				if("".equals(coachName)){
					coachName=null;
					return;
				}
				this.coachName = coachName;
			}
			public String getCoachSex() {
				return coachSex;
			}
			public void setCoachSex(String coachSex) {
				if("".equals(coachSex)){
					coachSex=null;
					return;
				}
				this.coachSex = coachSex;
			}
			public Date getCoachBirthDate() {
				return coachBirthDate;
			}
			public void setCoachBirthDate(Date coachBirthDate) {
				this.coachBirthDate = coachBirthDate;
			}
			public String getCoachHomeAddress() {
				return coachHomeAddress;
			}
			public void setCoachHomeAddress(String coachHomeAddress) {
				this.coachHomeAddress = coachHomeAddress;
			}
			
			public String getCoachContactPhone() {
				return coachContactPhone;
			}
			public void setCoachContactPhone(String coachContactPhone) {
				this.coachContactPhone = coachContactPhone;
			}
			public String getCoachDanRank() {
				return coachDanRank;
			}
			public void setCoachDanRank(String coachDanRank) {
				if("".equals(coachDanRank)){
					coachDanRank=null;
					return;
				}
				this.coachDanRank = coachDanRank;
			}
			public String getCoachingPosition() {
				return coachingPosition;
			}
			public void setCoachingPosition(String coachingPosition) {
				this.coachingPosition = coachingPosition;
			}
			public Date getCoachInductionTime() {
				return coachInductionTime;
			}
			public void setCoachInductionTime(Date coachInductionTime) {
				this.coachInductionTime = coachInductionTime;
			}
			public String getCoachRemarks() {
				return coachRemarks;
			}
			public void setCoachRemarks(String coachRemarks) {
				this.coachRemarks = coachRemarks;
			}
			public Integer getCoachingState() {
				return coachingState;
			}
			public void setCoachingState(Integer coachingState) {
				this.coachingState = coachingState;
			}
			public String getTheCoachIsKiNumber() {
				return theCoachIsKiNumber;
			}
			public void setTheCoachIsKiNumber(String theCoachIsKiNumber) {
				if("".equals(theCoachIsKiNumber)||"全部".equals(theCoachIsKiNumber)){
					theCoachIsKiNumber=null;
					return;
				}
				this.theCoachIsKiNumber = theCoachIsKiNumber;
			}
			public Date getCoachInductionTimeBegin() {
				return coachInductionTimeBegin;
			}
			public void setCoachInductionTimeBegin(Date coachInductionTimeBegin) {
				this.coachInductionTimeBegin = coachInductionTimeBegin;
			}
			public Date getCoachInductionTimeEnd() {
				return coachInductionTimeEnd;
			}
			public void setCoachInductionTimeEnd(Date coachInductionTimeEnd) {
				this.coachInductionTimeEnd = coachInductionTimeEnd;
			}
	
}
