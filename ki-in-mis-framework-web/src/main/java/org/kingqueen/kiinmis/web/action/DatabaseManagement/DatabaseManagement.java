package org.kingqueen.kiinmis.web.action.DatabaseManagement;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kingqueen.kiinmis.model.biz.DatabaseManagement.DataBackupRecovery;
import org.kingqueen.kiinmis.model.eaysui.pojo.RequestDatagrid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 数据备份的Action
 */
@Controller
@RequestMapping("DatabaseManagement")
public class DatabaseManagement {

	private static final Exception IOException = null;

	@Autowired
	private DataBackupRecovery dataBackupRecovery;

	@Autowired
	private HttpServletRequest request;

	/**
	 * 打开数据库备份与恢复管理界面
	 * 
	 * @return
	 */
	@RequestMapping("index")
	public String toindex() {
		return "DatabaseManagement/index";
	};

	/**
	 * 分页查找所有备份文件
	 * 
	 * @param requestDatagrid
	 * @return
	 */
	@RequestMapping("findList")
	@ResponseBody
	public List findList(RequestDatagrid requestDatagrid) {
		return dataBackupRecovery.findList(requestDatagrid).getRows();
	}

	/**
	 * 恢复
	 * 
	 * @param url
	 * @return
	 */
	@RequestMapping("recover")
	@ResponseBody
	public Map<String, String> recover(String url) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			return dataBackupRecovery.dataRecover(url);
		} catch (IOException e) {
			map.put("msg", "找不到文件" + url);
			map.put("code", "400");
			System.out.println(e);
		}
		map.put("msg", "未知错误");
		map.put("code", "400");
		return map;
	}

	/**
	 * 执行备份
	 * 
	 * @return
	 */
	@RequestMapping("backup")
	@ResponseBody
	public Map<String, String> backup() {
		Map<String, String> map = new HashMap<String, String>();
		try {
			map = dataBackupRecovery.dataBackup();
			Thread.sleep(2000L);
			return map;
		} catch (Exception e) {
			map.put("msg", "发生未知异常");
			map.put("code", "400");
			System.out.println(e);
		}
		map.put("msg", "未知错误");
		map.put("code", "400");
		return map;
	}

	@RequestMapping("delbackups")
	@ResponseBody
	/**
	 * 刪除某个备份
	 * @param url
	 * @return
	 */
	public Map<String, String> delbackups(String url) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			return dataBackupRecovery.delbackups(url);
		} catch (IOException e) {
			map.put("msg", "发生IO异常");
			map.put("code", "400");
			System.out.println(e);
		}
		map.put("msg", "未知错误");
		map.put("code", "400");
		return map;

	}

	@RequestMapping(value = "update", method = RequestMethod.GET)
	public String toupdeate() {
		request.setAttribute("map", dataBackupRecovery.findWhat());
		return "DatabaseManagement/update";
	}

	/**
	 * 修改配置文件
	 * 
	 * @param databaseHost
	 * @param backupaddress
	 * @param databaseUsername
	 * @param databasePassword
	 * @param databaseName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public String update(String databaseHost, String backupaddress,
			String databaseUsername, String databasePassword,
			String databaseName) throws Exception {
		
		return dataBackupRecovery.update(databaseHost, backupaddress,
				databaseUsername, databasePassword, databaseName);
	}
}
