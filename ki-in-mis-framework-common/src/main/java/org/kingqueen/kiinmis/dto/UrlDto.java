package org.kingqueen.kiinmis.dto;

/**
 * @ClassName UrlDto
 * @description 功能描述
 * @author 刘洪君
 * @date 2017年12月6日下午3:49:43
 * @version V1.0
 */
public class UrlDto {
	
	//ip地址
	private static  String ip = "localhost";
	//端口号
	private static  String port = "8080";
	//项目名称
	private static  String projectName="kiin";
	//ip地址+端口号+项目名称 =基础路径
	public static String basePath = ip+":"+port+"/"+projectName;
	
	
	

}
