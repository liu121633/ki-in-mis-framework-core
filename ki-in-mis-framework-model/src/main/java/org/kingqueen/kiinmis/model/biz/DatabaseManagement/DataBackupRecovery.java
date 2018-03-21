package org.kingqueen.kiinmis.model.biz.DatabaseManagement;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kingqueen.kiinmis.model.eaysui.pojo.RequestDatagrid;
import org.kingqueen.kiinmis.model.eaysui.pojo.ResponseDatagrid;
import org.kingqueen.kiinmis.model.vo.DatabaseManagement.DatabaseManagementVo;
import org.springframework.stereotype.Service;

/**
 * 数据备份与恢复
 * 
 * @author liu12
 */
@Service("DataBackupRecovery")
public class DataBackupRecovery {

	private String filePath = "C:/SystemEnvironment.properties";

	public ResponseDatagrid findList(RequestDatagrid requestDatagrid) {

		PropertiesUtil propertiesUtil = new PropertiesUtil(filePath);

		// 得到保存位置
		String backupaddress = propertiesUtil.GetValueByKey("backupaddress");
		String basePath = backupaddress + ":/数据库备份";
		File file = new File(basePath);// File类型可以是文件也可以是文件夹
		if (!file.exists()) {
			file.mkdirs();
		}

		File[] fileList = file.listFiles();// 将该目录下的所有文件放置在一个File类型的数组中

		List<DatabaseManagementVo> list = new ArrayList<DatabaseManagementVo>();

		ResponseDatagrid responseDatagrid = new ResponseDatagrid();

		for (int i = 0; i < fileList.length; i++) {
			try {
				DatabaseManagementVo databaseManagementVo = new DatabaseManagementVo();
				// 文件名称
				databaseManagementVo.setFileName(fileList[i].getName());

				boolean iscontinue = false;
				if (requestDatagrid.getWhereJson() != null
						&& !requestDatagrid.getWhereJson().equals("")) {
					// 用户传入条件
					// 判断这条数据 是否是相匹配的数据
					if (fileList[i].getName().indexOf(
							requestDatagrid.getWhereJson()) > 0) {
						// 匹配到了
						iscontinue = true;
					} else {
						iscontinue = false;
					}
				} else {
					// 如果没有传入条件 放行
					iscontinue = true;
				}
				if (iscontinue) {
					databaseManagementVo.setFileUrl(fileList[i].getPath());
					// 得到创建时间
					String savedate = (String) fileList[i].getName()
							.subSequence(fileList[i].getName().length() - 18,
									fileList[i].getName().length() - 4);
					SimpleDateFormat sdf = new SimpleDateFormat(
							"yyyyMMddHHmmss");
					Date date = sdf.parse(savedate);
					Timestamp timestamp = new Timestamp(date.getTime());

					// 创建时间
					databaseManagementVo.setFileDate(timestamp.toString());

					// 修改时间
					// System.out.println(file2.lastModified());

					list.add(databaseManagementVo);
				}
			} catch (Exception e) {
			}
		}
		responseDatagrid.setRows(list);
		return responseDatagrid;
	}

	/**
	 * 数据备份
	 * 
	 * @return
	 * 
	 * @throws IOException
	 */
	public Map<String, String> dataBackup() throws IOException {

		PropertiesUtil propertiesUtil = new PropertiesUtil(filePath);
		// 得到数据库地址
		String databaseHost = propertiesUtil.GetValueByKey("databaseHost");
		// 得到保存位置
		String backupaddress = propertiesUtil.GetValueByKey("backupaddress");
		// 得到数据库账号
		String databaseUsername = propertiesUtil
				.GetValueByKey("databaseUsername");
		// 得到数据库密码
		String databasePassword = propertiesUtil
				.GetValueByKey("databasePassword");
		// 得到数据库名
		String databaseName = propertiesUtil.GetValueByKey("databaseName");

		String basePath = backupaddress + ":/数据库备份";
		File file = new File(basePath);
		if (!file.exists()) {
			file.mkdirs();
		}

		// 得到时间 作为文件名称
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		Calendar calendar = Calendar.getInstance();
		String datestr = df.format(calendar.getTime());

		String saveUrl = basePath + "/" + databaseName + datestr + ".sql";

		String cmdStr = "cmd /c mysqldump -u" + databaseUsername + " -p"
				+ databasePassword + " -h" + databaseHost + " " + databaseName
				+ "  > " + saveUrl;
		
		
		Process p = Runtime.getRuntime().exec(cmdStr);
		Map<String, String> map = new HashMap<String, String>();
		map.put("code", "300");
		map.put("msg", "备份成功");
		return map;

	}

	/**
	 * 数据恢复
	 * 
	 * @throws IOException
	 */
	public Map<String, String> dataRecover(String url) throws IOException {

		PropertiesUtil propertiesUtil = new PropertiesUtil(filePath);
		// 得到数据库地址
		String databaseHost = propertiesUtil.GetValueByKey("databaseHost");
		// 得到保存位置
		String backupaddress = propertiesUtil.GetValueByKey("backupaddress");
		// 得到数据库账号
		String databaseUsername = propertiesUtil
				.GetValueByKey("databaseUsername");
		// 得到数据库密码
		String databasePassword = propertiesUtil
				.GetValueByKey("databasePassword");
		// 得到数据库名
		String databaseName = propertiesUtil.GetValueByKey("databaseName");

		String basePath = backupaddress + ":/数据库备份";
		url = basePath + "/" + url;
		String cmdStr = "cmd /c mysql  -h" + databaseHost + " -u"
				+ databaseUsername + " -p" + databasePassword + " "
				+ databaseName + " <" + url;
		Process p = Runtime.getRuntime().exec(cmdStr);
		Map<String, String> map = new HashMap<String, String>();
		map.put("code", "300");
		map.put("msg", "恢复成功");
		return map;
	}

	/**
	 * 删除某个备份
	 * 
	 * @param url
	 * @return
	 * @throws IOException
	 */
	public Map<String, String> delbackups(String url) throws IOException {
		PropertiesUtil propertiesUtil = new PropertiesUtil(filePath);
		// 得到数据库地址
		String databaseHost = propertiesUtil.GetValueByKey("databaseHost");
		// 得到保存位置
		String backupaddress = propertiesUtil.GetValueByKey("backupaddress");
		// 得到数据库账号
		String databaseUsername = propertiesUtil
				.GetValueByKey("databaseUsername");
		// 得到数据库密码
		String databasePassword = propertiesUtil
				.GetValueByKey("databasePassword");
		// 得到数据库名
		String databaseName = propertiesUtil.GetValueByKey("databaseName");

		String basePath = backupaddress + ":/数据库备份";
		url = basePath + "/" + url;

		File f = new File(url);
		if (f.exists() && f.isFile()) {
			f.delete();
		}

		Map<String, String> map = new HashMap<String, String>();
		map.put("code", "300");
		map.put("msg", "删除成功");
		return map;
	}

	public Map<String, String> findWhat() {

		PropertiesUtil propertiesUtil = new PropertiesUtil(filePath);
		// 得到数据库地址
		String databaseHost = propertiesUtil.GetValueByKey("databaseHost");
		// 得到保存位置
		String backupaddress = propertiesUtil.GetValueByKey("backupaddress");
		// 得到数据库账号
		String databaseUsername = propertiesUtil
				.GetValueByKey("databaseUsername");
		// 得到数据库密码
		String databasePassword = propertiesUtil
				.GetValueByKey("databasePassword");
		// 得到数据库名
		String databaseName = propertiesUtil.GetValueByKey("databaseName");

		Map<String, String> map = new HashMap<String, String>();
		map.put("databaseHost", databaseHost);
		map.put("backupaddress", backupaddress);
		map.put("databaseUsername", databaseUsername);
		map.put("databasePassword", databasePassword);
		map.put("databaseName", databaseName);
		return map;
	}

	public String update(String databaseHost,
			String backupaddress, String databaseUsername,
			String databasePassword, String databaseName) throws IOException {
		PropertiesUtil propertiesUtil = new PropertiesUtil(filePath);

		// 得到原来的保存位置
		String originalbackupaddress = propertiesUtil
				.GetValueByKey("backupaddress");

		propertiesUtil.WriteProperties("databaseHost", databaseHost);
		propertiesUtil.WriteProperties("backupaddress", backupaddress);
		propertiesUtil.WriteProperties("databaseUsername", databaseUsername);
		propertiesUtil.WriteProperties("databasePassword", databasePassword);
		propertiesUtil.WriteProperties("databaseName", databaseName);

		String msg = "1";//"修改成功";

		if (!originalbackupaddress.equals(backupaddress)) {

			String basePath = originalbackupaddress + ":/数据库备份";
			File file = new File(basePath);// File类型可以是文件也可以是文件夹
			if (!file.exists()) {
				file.mkdirs();
			}
			File[] fileList = file.listFiles();// 将该目录下的所有文件放置在一个File类型的数组中

			if (!new File(backupaddress + ":/数据库备份/").exists()) {
				new File(backupaddress + ":/数据库备份/").mkdirs();
			}
			for (int j = 0; j < fileList.length; j++) {
				copyFile(fileList[j].getPath(), backupaddress + ":/数据库备份/"
						+ fileList[j].getName());
				delFile(fileList[j].getPath());
			}
			file.delete();
			msg ="2";//"修改成功 已将原有数据迁移值新目录下!";
		}
		return msg;
	}

	/**
	 * 复制单个文件
	 * 
	 * @param oldPath
	 *            String 原文件路径 如：c:/fqf.txt
	 * @param newPath
	 *            String 复制后路径 如：f:/fqf.txt
	 * @return boolean
	 */
	public void copyFile(String oldPath, String newPath) {
		try {
			// int bytesum = 0;
			int byteread = 0;
			File oldfile = new File(oldPath);
			if (oldfile.exists()) { // 文件存在时
				InputStream inStream = new FileInputStream(oldPath); // 读入原文件
				FileOutputStream fs = new FileOutputStream(newPath);
				byte[] buffer = new byte[1444];
				// int length;
				while ((byteread = inStream.read(buffer)) != -1) {
					// bytesum += byteread; //字节数 文件大小
					// System.out.println(bytesum);
					fs.write(buffer, 0, byteread);
				}
				inStream.close();
			}
		} catch (Exception e) {
			System.out.println("复制单个文件操作出错");
			e.printStackTrace();

		}

	}

	/**
	 * 删除文件
	 * 
	 * @param filePathAndName
	 *            String 文件路径及名称 如c:/fqf.txt
	 * @param fileContent
	 *            String
	 * @return boolean
	 */
	public void delFile(String filePathAndName) {
		try {
			String filePath = filePathAndName;
			filePath = filePath.toString();
			java.io.File myDelFile = new java.io.File(filePath);
			myDelFile.delete();

		} catch (Exception e) {
			System.out.println("删除文件操作出错");
			e.printStackTrace();
		}
	}
}