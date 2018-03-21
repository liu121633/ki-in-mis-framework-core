package org.kingqueen.kiinmis.common.util;
public class EasyUiTreeNode {
	private String id;//ID
	private String text;//显示的文本
	private String state;//状态
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
}
