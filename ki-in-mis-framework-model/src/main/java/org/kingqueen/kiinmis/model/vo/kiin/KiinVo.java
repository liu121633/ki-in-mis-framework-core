package org.kingqueen.kiinmis.model.vo.kiin;

import java.sql.Timestamp;
import java.util.List;

import org.kingqueen.kiinmis.model.vo.user.UserVo;

public class KiinVo {
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
	//创建时间尾
	private Timestamp kiCreationTimeEnd;
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
	// 创建人对象
	private UserVo userCreate;
	// 最后修改人对象
	private UserVo userLast;
	//子棋院集合
	private List<KiinVo> kiinChildren;

	public List<KiinVo> getKiinChildren() {
		return kiinChildren;
	}

	public void setKiinChildren(List<KiinVo> kiinChildren) {
		this.kiinChildren = kiinChildren;
	}

	public UserVo getUserCreate() {
		return userCreate;
	}

	public void setUserCreate(UserVo userCreate) {
		this.userCreate = userCreate;
	}

	public UserVo getUserLast() {
		return userLast;
	}

	public void setUserLast(UserVo userLast) {
		this.userLast = userLast;
	}

	public String getChessNumber() {
		return chessNumber;
	}

	public void setChessNumber(String chessNumber) {
		if ("".equals(chessNumber)) {
			this.chessNumber = null;
		} else {
			this.chessNumber = chessNumber;
		}
	}

	public String getKiinName() {
		return kiinName;
	}

	public void setKiinName(String kiinName) {
		if ("".equals(kiinName)) {
			this.kiinName = null;
		} else {
			this.kiinName = kiinName;
		}
	}

	public String getTheChessChessNumber() {
		return theChessChessNumber;
	}

	public void setTheChessChessNumber(String theChessChessNumber) {
		if ("".equals(theChessChessNumber)) {
			this.theChessChessNumber = null;
		} else {
			this.theChessChessNumber = theChessChessNumber;
		}
	}

	public String getTheNameOfKiki() {
		return theNameOfKiki;
	}

	public void setTheNameOfKiki(String theNameOfKiki) {
		if ("".equals(theNameOfKiki)) {
			this.theNameOfKiki = null;
		} else {
			this.theNameOfKiki = theNameOfKiki;
		}
	}

	public Integer getKiLevel() {
		return kiLevel;
	}

	public void setKiLevel(Integer kiLevel) {
		if ("".equals(kiLevel)) {
			this.kiLevel = null;
		} else {
			this.kiLevel = kiLevel;
		}
	}

	public String getKiNote() {
		return kiNote;
	}
	
	public void setKiNote(String kiNote) {
		if ("".equals(kiNote)) {
			this.kiNote = null;
		}else {
			this.kiNote = kiNote;
		}
	}
	
	public Timestamp getKiCreationTimeEnd() {
		return kiCreationTimeEnd;
	}

	public void setKiCreationTimeEnd(Timestamp kiCreationTimeEnd) {
		if ("".equals(kiCreationTimeEnd)) {
			this.kiCreationTimeEnd = null;
		} else {
			this.kiCreationTimeEnd = kiCreationTimeEnd;
		}
	}

	public String getCreateUserChess() {
		return createUserChess;
	}

	public void setCreateUserChess(String createUserChess) {
		if ("".equals(createUserChess)) {
			this.createUserChess = null;
		} else {
			this.createUserChess = createUserChess;
		}
	}

	public Timestamp getKiCreationTime() {
		return kiCreationTime;
	}

	public void setKiCreationTime(Timestamp kiCreationTime) {
		if ("".equals(kiCreationTime)) {
			this.kiCreationTime = null;
		} else {
			this.kiCreationTime = kiCreationTime;
		}
	}

	public String getKiFinallyModifyTheUserName() {
		return kiFinallyModifyTheUserName;
	}

	public void setKiFinallyModifyTheUserName(String kiFinallyModifyTheUserName) {
		if ("".equals(kiFinallyModifyTheUserName)) {
			this.kiFinallyModifyTheUserName = null;
		} else {
			this.kiFinallyModifyTheUserName = kiFinallyModifyTheUserName;
		}
	}

	public Timestamp getKiLastModificationTime() {
		return kiLastModificationTime;
	}

	public void setKiLastModificationTime(Timestamp kiLastModificationTime) {
		if ("".equals(kiLastModificationTime)) {
			this.kiLastModificationTime = null;
		} else {
			this.kiLastModificationTime = kiLastModificationTime;
		}
	}

	public Integer getKiState() {
		return kiState;
	}

	public void setKiState(Integer kiState) {
		if ("".equals(kiState)) {
			this.kiState = null;
		} else {
			this.kiState = kiState;
		}
	}

	public String getKiReserveField1() {
		return kiReserveField1;
	}

	public void setKiReserveField1(String kiReserveField1) {
		if ("".equals(kiReserveField1)) {
			this.kiReserveField1 = null;
		} else {
			this.kiReserveField1 = kiReserveField1;
		}
	}

	public String getKiReserveField2() {
		return kiReserveField2;
	}

	public void setKiReserveField2(String kiReserveField2) {
		if ("".equals(kiReserveField2)) {
			this.kiReserveField2 = kiReserveField2;
		} else {
			this.kiReserveField2 = kiReserveField2;
		}
	}

	public String getKiReserveField3() {
		return kiReserveField3;
	}

	public void setKiReserveField3(String kiReserveField3) {
		if ("".equals(kiReserveField3)) {
			this.kiReserveField3 = null;
		} else {
			this.kiReserveField3 = kiReserveField3;
		}
	}
}
