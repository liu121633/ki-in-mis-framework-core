package org.kingqueen.kiinmis.model.biz.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.kingqueen.kiinmis.model.dao.user.UserDao;
import org.kingqueen.kiinmis.model.pojo.Kiin;
import org.kingqueen.kiinmis.model.pojo.Position;
import org.kingqueen.kiinmis.model.pojo.Role;
import org.kingqueen.kiinmis.model.pojo.User;
import org.kingqueen.kiinmis.model.pojo.Usersofchess;
import org.kingqueen.kiinmis.model.vo.user.TreeVo;
import org.kingqueen.kiinmis.model.vo.user.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mysql.fabric.xmlrpc.base.Array;


/**
 * @ClassName UserBiz
 * @description 用户的逻辑判断类
 * @author wzq
 * @date 2017年12月2日
 * @version v1.0
 */
@Service("userBiz")
@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, readOnly = true)
public class UserBiz {

	@Autowired
	private  UserDao userDao;

	
	
	
	
	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	/**
	 * @title:findPositionDatagrid
	 * @description 用户分页查询
	 */
	public Map<String, Object> findUserDatagrid(Integer page, Integer rows, String sort, String order,
			String userManagerName, String userManagerLevel, String userManagerStart, String userManagerEnd,
			String userManagerLogout,String knumber) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 起始页数
		Integer firstPage = (page - 1) * rows;
		// 查询用户信息
		List<UserVo> list = userDao.findUserDatagrid(firstPage, rows, sort, order, userManagerName, userManagerLevel,
				userManagerStart, userManagerEnd, userManagerLogout,knumber);
		// 用户的条数
		Integer count = userDao.findUserCount(userManagerName, userManagerLevel, userManagerStart, userManagerEnd,
				userManagerLogout,knumber);
		// 判断是否查找到用户信息
		if (list != null && list.size() > 0) {
			// 循环查询用户的集合
			for (UserVo userVo : list) {
				
				//查询用户的所有角色
				List<String> rolesList = userDao.findRolesByUserName(userVo.getUserLoginName());
				String roleStrTemp = "";
				if(rolesList!=null||rolesList.size()>0){
					for (int i =0;i<rolesList.size();i++) {
						if(i==rolesList.size()-1){
							roleStrTemp += rolesList.get(i);
						}else{
							roleStrTemp += rolesList.get(i)+",";
						}
					}
				}
				userVo.setUserRolesName(roleStrTemp);
				// 查询创建人
				// 判断是否有创建人
				if (userVo.getUserfounder() != null && !"".equals(userVo.getUserfounder())) {
					List<User> userList = userDao.findUserById(userVo.getUserfounder());
					if (userList != null && userList.size() > 0) {
						User userTemp = userList.get(0);
						// 把创始人的姓名放入UserVo中
						userVo.setUserfounderName(userTemp.getUserName());
					}

				}
				// 查询最后修改人
				// 判断是否有最后修改人
				if (userVo.getUserFinallyModifiesTheUser() != null
						&& !"".equals(userVo.getUserFinallyModifiesTheUser())) {
					List<User> userList = userDao.findUserById(userVo.getUserFinallyModifiesTheUser());
					if (userList != null && userList.size() > 0) {
						User userTemp = userList.get(0);
						// 把最后修改人的姓名放入UserVo中
						userVo.setUserFinallyModifiesTheUserName(userTemp.getUserName());
					}
				}
				// 查询用户的部门
				List<Kiin> kiinList = userDao.findUserKiin(userVo.getUserNumber());
				// 判断是否找到用户的部门
				if (kiinList != null && kiinList.size() > 0) {
					// 循环部门
					String kiinStr = "";
					for (int i = 0; i < kiinList.size(); i++) {
						if (i == kiinList.size() - 1) {
							// 是最后一个部门不加逗号分割
							kiinStr += kiinList.get(i).getKiinName();
						} else {
							kiinStr += kiinList.get(i).getKiinName() + ",";
						}
					}
					// 把循环出的部门字符串放入UserVo对应集合中
					userVo.setKiinStr(kiinStr);
				}
			}
		}
		
		map.put("total", count);
		map.put("rows", list);
		return map;
	}

	/**
	 * @title:updateRecoverPwd
	 * @description 批量恢复密码
	 */
	public void updateRecoverPwd(String[] list,String userNumber) {
		/*List<Kiin> kiin = userDao.findUserKiin("YH-20171212155812687");
		foreacha(kiin.get(0));
		for (String string : set) {
			System.out.println(string);
		}
*/		
		userDao.updateRecoverPwd(list,userNumber);
	}

	/**
	 * @title:updateLogout
	 * @description 批量注销
	 */
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false)
	public void updateLogout(String[] list,String userNumber) {
		userDao.deleteUserAndRole(list[0]);
		userDao.updateLogout(list,userNumber);
	}

	/**
	 * @title:findRoleAll
	 * @description 查询所有角色
	 */
	public List<Role> findRoleAll() {
		return userDao.findRoleAll();
	}

	/**
	 * @title:updateUserResetLogout
	 * @description 取消注销
	 */
	@Transactional
	public void updateUserResetLogout(User user,String userNumber,Role role) {
		userDao.updateUserResetLogout(user,userNumber);
		userDao.addUserRole(role.getRoleNumber(), user.getUserNumber());
		//查询默认角色
		/*List<Role> roleList = userDao.queryDefaultRole();
		if(roleList==null||roleList.size()<=0){
			return;
		}
		for (Role role : roleList) {
			userDao.addUserRole(role.getRoleNumber(), user.getUserNumber());
		}*/
	}

	/**
	 * @title:findPositionAll
	 * @description 查询职位
	 */
	public List<Position> findPositionAll() {
		return userDao.findPositionAll();
	}

	/**
	 * @title:findTreeKiin
	 * @description 树状图的查询
	 * 
	 */
	public List<TreeVo> findTreeKiin(String id) {
		/*// 判断是否为空如果空就赋值为0为总部
		if (id == null || "".equals(id)) {
			id = "0";
		}*/
		List<TreeVo> list = userDao.findTreeKiin(id);
		// 查询部门
		// 查询是否有子部门
		for (TreeVo treeVo : list) {
			List<TreeVo> childrenList = userDao.findTreeKiin(treeVo.getId());
			if (childrenList != null && childrenList.size() > 0) {
				treeVo.setState("closed");
			}
		}
		return list;
	}

	/**
	 * @title:addUser
	 * @description 新增用户
	 * 
	 */
	public void addUser(User user,Role role) {
		userDao.addUser(user);
		userDao.addUserRole(role.getRoleNumber(),user.getUserNumber());
		//查询默认角色
		/*List<Role> roleList = userDao.queryDefaultRole();
		if(roleList!=null&&roleList.size()>0){
			for (Role role : roleList) {
				userDao.addUserRole(role.getRoleNumber(),user.getUserNumber());
			}
		}*/
	}

	/**
	 * @title:addUserKiin
	 * @description 新增用户的部门
	 * 
	 */
	public void addUserKiin(List<Usersofchess> list) {
		for (Usersofchess usersofchess : list) {
			userDao.addUserKiin(usersofchess);
		}
	}

	/**
	 * @title:findUsersVoById
	 * @description 按id查询用户UserVo
	 * 
	 */
	public UserVo findUsersVoById(String userId) {
		List<UserVo> list = userDao.findUsersVoById(userId);
		if (list == null || list.size() <= 0) {
			return null;
		}
		UserVo userVo = list.get(0);
		// 查询用户的部门
		List<Kiin> kiinList = userDao.findUserKiin(userVo.getUserNumber());
		// 判断是否找到用户的部门
		if (kiinList != null && kiinList.size() > 0) {
			// 循环部门
			String kiinStr = "";
			String kiinIdStr = "";
			for (int i = 0; i < kiinList.size(); i++) {
				if (i == kiinList.size() - 1) {
					// 是最后一个部门不加逗号分割
					kiinStr += kiinList.get(i).getKiinName();
					kiinIdStr += kiinList.get(i).getChessNumber();
				} else {
					kiinStr += kiinList.get(i).getKiinName() + ",";
					kiinIdStr += kiinList.get(i).getChessNumber() + ",";
				}
			}
			// 把循环出的部门字符串放入UserVo对应集合中
			userVo.setKiinStr(kiinStr);
			userVo.setKiinIdStr(kiinIdStr);
		}

		return userVo;
	}

	/**
	 * @title:updateUser
	 * @description 修改用户
	 * 
	 */
	public void updateUser(User user, String[] kiinArr) {
		userDao.updateUser(user);
		// 删除用户的部门
		userDao.deleteUserLinkKiin(user.getUserNumber());
		// 新增用户用户部门
		for (String string : kiinArr) {
			Usersofchess usersofchess = new Usersofchess();
			usersofchess.setUserNumber(user.getUserNumber());
			usersofchess.setChessNumber(string);
			userDao.addUserKiin(usersofchess);
		}
	}

	/**
	 * @title:findUserVoAll
	 * @description 查询所有
	 */
	public List<UserVo> findUserVoAll(String userManagerName, String userManagerLevel, String userManagerStart,
			String userManagerEnd, String userManagerLogout) {
		// 查询用户信息
		List<UserVo> list = userDao.findUserVoCondition(userManagerName, userManagerLevel, userManagerStart,
				userManagerEnd, userManagerLogout);
		// 判断是否查找到用户信息
		if (list != null && list.size() > 0) {
			// 循环查询用户的集合
			for (UserVo userVo : list) {
				// 查询创建人
				// 判断是否有创建人
				if (userVo.getUserfounder() != null && !"".equals(userVo.getUserfounder())) {
					List<User> userList = userDao.findUserById(userVo.getUserfounder());
					if (userList != null && userList.size() > 0) {
						User userTemp = userList.get(0);
						// 把创始人的姓名放入UserVo中
						userVo.setUserfounderName(userTemp.getUserName());
					}

				}
				// 查询最后修改人
				// 判断是否有最后修改人
				if (userVo.getUserFinallyModifiesTheUser() != null
						&& !"".equals(userVo.getUserFinallyModifiesTheUser())) {
					List<User> userList = userDao.findUserById(userVo.getUserFinallyModifiesTheUser());
					if (userList != null && userList.size() > 0) {
						User userTemp = userList.get(0);
						// 把最后修改人的姓名放入UserVo中
						userVo.setUserFinallyModifiesTheUserName(userTemp.getUserName());
					}
				}
				// 查询用户的部门
				List<Kiin> kiinList = userDao.findUserKiin(userVo.getUserNumber());
				// 判断是否找到用户的部门
				if (kiinList != null && kiinList.size() > 0) {
					// 循环部门
					String kiinStr = "";
					for (int i = 0; i < kiinList.size(); i++) {
						if (i == kiinList.size() - 1) {
							// 是最后一个部门不加逗号分割
							kiinStr += kiinList.get(i).getKiinName();
						} else {
							kiinStr += kiinList.get(i).getKiinName() + ",";
						}
					}
					// 把循环出的部门字符串放入UserVo对应集合中
					userVo.setKiinStr(kiinStr);
				}
			}

		}
		return list;
	}
	
	/**
	 * @title:findUserVoSelect
	 * @description 查询保函UserId的集合返回UserVO
	 */
	public List<UserVo> findUserVoSelect(List<UserVo> list1){
		// 查询用户信息
				List<UserVo> list = userDao.findUserVoSelect(list1);
				// 判断是否查找到用户信息
				if (list != null && list.size() > 0) {
					// 循环查询用户的集合
					for (UserVo userVo : list) {
						// 查询创建人
						// 判断是否有创建人
						if (userVo.getUserfounder() != null && !"".equals(userVo.getUserfounder())) {
							List<User> userList = userDao.findUserById(userVo.getUserfounder());
							if (userList != null && userList.size() > 0) {
								User userTemp = userList.get(0);
								// 把创始人的姓名放入UserVo中
								userVo.setUserfounderName(userTemp.getUserName());
							}

						}
						// 查询最后修改人
						// 判断是否有最后修改人
						if (userVo.getUserFinallyModifiesTheUser() != null
								&& !"".equals(userVo.getUserFinallyModifiesTheUser())) {
							List<User> userList = userDao.findUserById(userVo.getUserFinallyModifiesTheUser());
							if (userList != null && userList.size() > 0) {
								User userTemp = userList.get(0);
								// 把最后修改人的姓名放入UserVo中
								userVo.setUserFinallyModifiesTheUserName(userTemp.getUserName());
							}
						}
						// 查询用户的部门
						List<Kiin> kiinList = userDao.findUserKiin(userVo.getUserNumber());
						// 判断是否找到用户的部门
						if (kiinList != null && kiinList.size() > 0) {
							// 循环部门
							String kiinStr = "";
							for (int i = 0; i < kiinList.size(); i++) {
								if (i == kiinList.size() - 1) {
									// 是最后一个部门不加逗号分割
									kiinStr += kiinList.get(i).getKiinName();
								} else {
									kiinStr += kiinList.get(i).getKiinName() + ",";
								}
							}
							// 把循环出的部门字符串放入UserVo对应集合中
							userVo.setKiinStr(kiinStr);
						}
					}

				}
				return list;
	}
	
	/**
	 * @title:validateUserAccount
	 * @description 查询保函UserId的集合返回UserVO
	 * @return  Boolean
	 */
	public Boolean existUser(String account){
		User user = userDao.existUser(account);
		if(user==null){
			return	true;
		}
		return false;
		
	}

	public User findUserByName(String userName){
		User user = userDao.existUser(userName);
		return user;
	}

	/**
	 * @title:findRolesByUserName
	 * @description 查询用户的所有角色
	 * @return List<String>
	 */
	public Set<String> findRolesByUserName(String userName){
		Set<String> set = new HashSet<String>();
		List<String> list = userDao.findRolesByUserName(userName);
		for (String string : list) {
			set.add(string);
		}
		return set;
	}
	
	/**
	 * @title:findPermissionsByRoles
	 * @description 查询用户的所有权限
	 * @param list
	 * @return List<String>
	 */
	public Set<String> findPermissionsByRoles(Set<String> list){
			List<String> listTemp = new ArrayList<String>();
			for (String string : list) {
				listTemp.add(string);
			}
			
			Set<String> set = new HashSet<String>();
			List<String> listResult = userDao.findPermissionsByRoles(listTemp);
			for (String string : listResult) {
				set.add(string);
			}
			return set;
	}
	
	public List<String> findUserKiinChildren(String userId){
		List<Kiin> kiinList = userDao.findUserKiin(userId);
		//查询用户棋院的子棋院
		List<String> kiinStr = new ArrayList<String>();
		for (Kiin string : kiinList) {
			kiinStr.add(string.getKiinName());
		}
		return kiinStr;
	}
	
	public List<String> findUserKiinChildrenId(String userId){
		List<Kiin> kiinList = userDao.findUserKiin(userId);
		//查询用户棋院的子棋院
		List<String> kiinStr = new ArrayList<String>();
		for (Kiin string : kiinList) {
			kiinStr.add(string.getChessNumber());
		}
		return kiinStr;
	}
	
	public  Set<String> set;
	
	public  void foreacha(Kiin kiin){
		set.add(kiin.getChessNumber());
		//查询下级棋院
		List<Kiin> list = userDao.queryKiinChildren(kiin);
		for (Kiin kiin2 : list) {
			set.add(kiin2.getChessNumber());
			List<Kiin> listChildren = userDao.queryKiinChildren(kiin2);
			//查询是否还有下级棋院如果有就回调
			if(listChildren.size()>0){
				for (Kiin kiin3 : listChildren) {
					foreacha(kiin3);
				}
			}
			
			
		}
	}
	
	public String findKiinUserStr(String userId){
		set = new HashSet<String>();
		List<Kiin> kiin = userDao.findUserKiin(userId);
		foreacha(kiin.get(0));
		String kiinStr = "";
		for (String string : set) {
			kiinStr+=string+",";
			System.out.println(string);
		}
		return kiinStr;
	}
	
}
