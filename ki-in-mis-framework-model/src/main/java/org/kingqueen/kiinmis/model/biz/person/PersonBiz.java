package org.kingqueen.kiinmis.model.biz.person;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kingqueen.kiinmis.model.dao.person.IPersonDao;
import org.kingqueen.kiinmis.model.vo.role.RoleVo;
import org.kingqueen.kiinmis.model.vo.user.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, readOnly = true)
/**
 * 
  * @ClassName KiinBiz
  * @description 对个人信息的相关处理的业务逻辑层
  * @author 王晓妍
  * @date 2017年12月3日
  * @version V1.0
 */
public class PersonBiz {
	@Autowired
	private IPersonDao iPersonDao;
	/**
	* 
	* @title: findPersonByUserId 
	* @description: 根据用户编号查询用户信息
	* @throws Exception
	 */
	public Map<String, Object> findPersonByUserId(String userNumber){
		Map<String, Object> map = new HashMap<String, Object>();
		List<String> list = new ArrayList<String>();
		for(RoleVo r:iPersonDao.findRoleByUser(userNumber)){
			list.add(r.getRoleName());
		}
		map.put("userVo", iPersonDao.findPersonByUserId(userNumber));
		map.put("roleName", list);
		return map;
	}
}
