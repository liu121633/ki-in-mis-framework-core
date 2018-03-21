package org.kingqueen.kiinmis.model.vo.menu;

public class MenuVo {
	// 功能编号
	private Integer menuId;
	// 功能编码
	private String menuCode;
	// 功能value值
	private String menuValue;
	// 功能名称
	private String menuName;
	// 功能路径
	private String menuUrl;
	// 父功能ID
	private Integer theFatherOfMenuId;

	public Integer getMenuId() {
		return menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	public String getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}

	public String getMenuValue() {
		return menuValue;
	}

	public void setMenuValue(String menuValue) {
		this.menuValue = menuValue;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	public Integer getTheFatherOfMenuId() {
		return theFatherOfMenuId;
	}

	public void setTheFatherOfMenuId(Integer theFatherOfMenuId) {
		this.theFatherOfMenuId = theFatherOfMenuId;
	}
}
