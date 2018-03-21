package org.kingqueen.kiinmis.model.pojo;

/**
 * 常用功能po
 * 
 * @author 刘洪君
 *
 */
public class Home {
	//编号自增
	private String id;
	//用户编号
	private String userid;
	//功能名称
	private String menuname;
	//功能地址
	private String menuurl;
	//img 地址
	private String imgurl;
	
	
	
	
	
	
	public String getImgurl() {
		return imgurl;
	}
	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getMenuname() {
		return menuname;
	}
	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}
	public String getMenuurl() {
		return menuurl;
	}
	public void setMenuurl(String menuurl) {
		this.menuurl = menuurl;
	}
	
	
	

}
