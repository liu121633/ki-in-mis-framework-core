package org.kingqueen.kiinmis.model.biz.index;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kingqueen.kiinmis.model.dao.index.IIndexDao;
import org.kingqueen.kiinmis.model.pojo.Home;
import org.kingqueen.kiinmis.model.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName index
 * @description 功能描述
 * @author 刘洪君
 * @date 2017年12月9日下午3:30:23
 * @version V1.0
 */
@Service("IndexBiz")
public class IndexBiz {

	@Autowired
	private IIndexDao indexDao;

	/**
	 * @title: 根据账号密码查询某个用户
	 * @description: 登陆
	 * @throws Exception
	 * @return User
	 */
	public User login(String id, String pwd) {
		return indexDao.login(id, pwd);
	}

	/**
	 * @title: 计算用户能操作的棋院
	 * @description: 功能描述
	 * @throws Exception
	 * @return void
	 */
	public List<String> calculateUserManagementKiin(User user) {
		// 取得用户管理的棋院
		List<String> list = indexDao.findUserManagementKiin(user);

		return list;
	}

	public Map<String, Object> updatePassWord(String newPass, String userNumber) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			indexDao.updatePassWord(newPass, userNumber);
			map.put("code", 200);
		} catch (Exception e) {
			map.put("code", 500);
		}
		return map;
	}

	public Map<String, String> saveSetting(List<Home> homes, String userid) {
		// 刪除他之前保存的功能

		Map<String, String> map = new HashMap<String, String>();

		try {
			int f = indexDao.delSaveSetting(userid);

			if(homes.size()>0){
				int i = indexDao.saveSetting(homes, userid);
			}

			map.put("code", "300");
			map.put("msg", "修改成功!");
		} catch (Exception e) {
			map.put("code", "500");
			map.put("msg", "未知错误!");
		}
		return map;
	}

	public List<Home> findHome(String userid) {
		return indexDao.findHome(userid);
	}

}
