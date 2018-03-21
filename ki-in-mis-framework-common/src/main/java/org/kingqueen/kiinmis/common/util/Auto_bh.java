package org.kingqueen.kiinmis.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Auto_bh {
	public static String getCurrentTime(){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddhhmmss");
		return sdf.format(new Date());
	}
}
