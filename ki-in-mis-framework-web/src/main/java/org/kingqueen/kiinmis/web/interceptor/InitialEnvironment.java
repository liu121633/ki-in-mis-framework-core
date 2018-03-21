package org.kingqueen.kiinmis.web.interceptor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.kingqueen.kiinmis.model.biz.DatabaseManagement.PropertiesUtil;

/**
 * 服务器初始环境
 * 
 * @author liu12
 *
 */
public class InitialEnvironment implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		System.out.println("服务器启动了");

		File file = new File("C:/SystemEnvironment.properties");// File类型可以是文件也可以是文件夹
		if (!file.exists()) {
			System.out.println("没有文件");
			try {
				BufferedWriter writer = new BufferedWriter(new FileWriter(
						"C:/SystemEnvironment.properties"));
				PropertiesUtil propertiesUtil = new PropertiesUtil(
						"C:/SystemEnvironment.properties");
				propertiesUtil.WriteProperties("databaseHost", "localhost");
				propertiesUtil.WriteProperties("backupaddress", "C");
				propertiesUtil.WriteProperties("databaseUsername", "root");
				propertiesUtil.WriteProperties("databasePassword", "123456");
				propertiesUtil.WriteProperties("databaseName", "kiin");
			} catch (IOException e1) {
				
				e1.printStackTrace();
			}
			
		}

	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub

	}

}
