package org.kingqueen.kiinmis.model.pojo;

import java.sql.Timestamp;

public class Grade {
	private String gradenumber;
	private String gradename;
	private String gradefounder;
	private Timestamp creationtime;
	private String modifytheuser;
	private Timestamp modificationtime;
	public String getGradenumber() {
		return gradenumber;
	}
	public void setGradenumber(String gradenumber) {
		this.gradenumber = gradenumber;
	}
	public String getGradename() {
		return gradename;
	}
	public void setGradename(String gradename) {
		this.gradename = gradename;
	}
	public String getGradefounder() {
		return gradefounder;
	}
	public void setGradefounder(String gradefounder) {
		this.gradefounder = gradefounder;
	}
	public Timestamp getCreationtime() {
		return creationtime;
	}
	public void setCreationtime(Timestamp creationtime) {
		this.creationtime = creationtime;
	}
	public String getModifytheuser() {
		return modifytheuser;
	}
	public void setModifytheuser(String modifytheuser) {
		this.modifytheuser = modifytheuser;
	}
	public Timestamp getModificationtime() {
		return modificationtime;
	}
	public void setModificationtime(Timestamp modificationtime) {
		this.modificationtime = modificationtime;
	}
}
