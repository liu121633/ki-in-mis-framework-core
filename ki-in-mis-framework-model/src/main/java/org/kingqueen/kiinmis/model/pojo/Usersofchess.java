package org.kingqueen.kiinmis.model.pojo;

public class Usersofchess {

	// id自增
	private Integer id; 
	// 用户的唯一标示，内部使用
	private String userNumber; 
	// 棋院编号
	private String chessNumber;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserNumber() {
		return userNumber;
	}
	public void setUserNumber(String userNumber) {
		this.userNumber = userNumber;
	}
	public String getChessNumber() {
		return chessNumber;
	}
	public void setChessNumber(String chessNumber) {
		this.chessNumber = chessNumber;
	} 




}
