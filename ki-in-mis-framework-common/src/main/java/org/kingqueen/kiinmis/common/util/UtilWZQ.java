package org.kingqueen.kiinmis.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UtilWZQ {
	/**
	 * @title:getUtilDate
	 * @description 获取年月日时分秒
	 */
	public  static String getUtilDate(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		return sdf.format(new Date());
	}
	}

