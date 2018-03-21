package org.kingqueen.kiinmis.model.pojo;

import java.sql.Timestamp;


public class Kiin {

	// 棋院编号
	private String chessNumber; 
	// 棋院名称
	private String kiinName; 
	// 棋院上级棋院编号
	private String theChessChessNumber; 
	// 棋院上级棋院名称
	private String theNameOfKiki; 
	// 棋院级别
	private Integer kiLevel; 
	// 棋院备注
	private String kiNote; 
	// 棋院创建人
	private String createUserChess; 
	// 棋院创建时间
	private Timestamp kiCreationTime; 
	// 棋院最后修改用户名字
	private String kiFinallyModifyTheUserName; 
	// 棋院最后修改时间
	private Timestamp kiLastModificationTime; 
	// 棋院状态
	private Integer kiState; 
	// 棋院备用字段1 
	private String kiReserveField1; 
	// 棋院备用字段2 
	private String kiReserveField2; 
	// 棋院备用字段3 
	private String kiReserveField3;
	public String getChessNumber() {
		return chessNumber;
	}
	public void setChessNumber(String chessNumber) {
		this.chessNumber = chessNumber;
	}
	public String getKiinName() {
		return kiinName;
	}
	public void setKiinName(String kiinName) {
		this.kiinName = kiinName;
	}
	public String getTheChessChessNumber() {
		return theChessChessNumber;
	}
	public void setTheChessChessNumber(String theChessChessNumber) {
		this.theChessChessNumber = theChessChessNumber;
	}
	public String getTheNameOfKiki() {
		return theNameOfKiki;
	}
	public void setTheNameOfKiki(String theNameOfKiki) {
		this.theNameOfKiki = theNameOfKiki;
	}
	public Integer getKiLevel() {
		return kiLevel;
	}
	public void setKiLevel(Integer kiLevel) {
		this.kiLevel = kiLevel;
	}
	public String getKiNote() {
		return kiNote;
	}
	public void setKiNote(String kiNote) {
		this.kiNote = kiNote;
	}
	public String getCreateUserChess() {
		return createUserChess;
	}
	public void setCreateUserChess(String createUserChess) {
		this.createUserChess = createUserChess;
	}
	public Timestamp getKiCreationTime() {
		return kiCreationTime;
	}
	public void setKiCreationTime(Timestamp kiCreationTime) {
		this.kiCreationTime = kiCreationTime;
	}
	public String getKiFinallyModifyTheUserName() {
		return kiFinallyModifyTheUserName;
	}
	public void setKiFinallyModifyTheUserName(String kiFinallyModifyTheUserName) {
		this.kiFinallyModifyTheUserName = kiFinallyModifyTheUserName;
	}
	public Timestamp getKiLastModificationTime() {
		return kiLastModificationTime;
	}
	public void setKiLastModificationTime(Timestamp kiLastModificationTime) {
		this.kiLastModificationTime = kiLastModificationTime;
	}
	public Integer getKiState() {
		return kiState;
	}
	public void setKiState(Integer kiState) {
		this.kiState = kiState;
	}
	public String getKiReserveField1() {
		return kiReserveField1;
	}
	public void setKiReserveField1(String kiReserveField1) {
		this.kiReserveField1 = kiReserveField1;
	}
	public String getKiReserveField2() {
		return kiReserveField2;
	}
	public void setKiReserveField2(String kiReserveField2) {
		this.kiReserveField2 = kiReserveField2;
	}
	public String getKiReserveField3() {
		return kiReserveField3;
	}
	public void setKiReserveField3(String kiReserveField3) {
		this.kiReserveField3 = kiReserveField3;
	} 
	
}
