package org.kingqueen.kiinmis.common;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * 公共类
 * @author 王晓妍
 * @title: Common
 *@throws Exception
 *
 */
public class Common {
	/**
	* 
	* @title: getNow 
	* @description: 获取当前时间
	* @throws Exception
	 */
	public static Timestamp getNow(){
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = format.format(date);
		Timestamp t = Timestamp.valueOf(time);
		return t;
	}
	
	/**
	* 
	* @title: getKiinNumber 
	* @description: 获取棋院编号
	* @throws Exception
	 */
	public static String getKiinNumber(){
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		String time = format.format(new Date());
		return "QY-"+time;
	}
	
	/**
	* 
	* @title: getSchoolNumber 
	* @description: 获取学校编号
	* @throws Exception
	 */
	public static String getSchoolNumber(){
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		String time = format.format(new Date());
		return "XX-"+time;
	}
	
	/**
	* 
	* @title: getRoleNumber 
	* @description: 获取角色编号
	* @throws Exception
	 */
	public static String getRoleNumber(){
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		String time = format.format(new Date());
		return "JS-"+time;
	}
}
