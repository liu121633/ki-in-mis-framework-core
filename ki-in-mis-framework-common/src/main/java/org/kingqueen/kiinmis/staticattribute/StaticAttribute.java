package org.kingqueen.kiinmis.staticattribute;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName StaticAttribute
 * @description 功能描述
 * @author 刘洪君
 * @date 2017年12月18日下午7:34:20
 * @version V1.0
 */
public class StaticAttribute {
	
	
	//在用户登陆的时候 计算出用户能操作的棋院  棋院的编号
	//之后的所有查询语句 前一句应该是  IN 棋院编号 ('qy1','qy2','qy2')
	//保存了用户可以操作的棋院
	public static List<String> kiinIds = new ArrayList<String>();

}
