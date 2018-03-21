package org.kingqueen.kiinmis.model.biz.role;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.kingqueen.kiinmis.common.Common;
import org.kingqueen.kiinmis.model.dao.kiin.IKiinDao;
import org.kingqueen.kiinmis.model.dao.role.IRoleDao;
import org.kingqueen.kiinmis.model.eaysui.pojo.RequestDatagrid;
import org.kingqueen.kiinmis.model.eaysui.pojo.ResponseDatagrid;
import org.kingqueen.kiinmis.model.pojo.Role;
import org.kingqueen.kiinmis.model.pojo.User;
import org.kingqueen.kiinmis.model.pojo.UserAndRole;
import org.kingqueen.kiinmis.model.vo.kiin.EasyUiTreeNode;
import org.kingqueen.kiinmis.model.vo.menu.MenuVo;
import org.kingqueen.kiinmis.model.vo.role.RoleVo;
import org.kingqueen.kiinmis.model.vo.user.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.alibaba.fastjson.JSON;

/**
 * 
 * @author 王晓妍
 * @description: 对角色以及权限的业务逻辑层
 * @date 2017年12月21日
 * @version V1.0
 * @ClassName RoleBiz
 */
@Service
@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, readOnly = true)
public class RoleBiz {
	@Autowired
	private IRoleDao iRoleDao;
	@Autowired
	private IKiinDao iKiinDao;
	
	/**
	 * @title: findRole
	 * @description: 对角色显示的业务处理
	 * @throws Exception
	 */
	public ResponseDatagrid findRole(RequestDatagrid requestDatagrid){
		ResponseDatagrid responseDatagrid = new ResponseDatagrid();
		//取得查询条件对象
		RoleVo roleVo = JSON.parseObject(requestDatagrid.getWhereJson(), RoleVo.class);
		//设置数据
		responseDatagrid.setRows(iRoleDao.findAllRole(requestDatagrid, roleVo));
		//设置总行数
		responseDatagrid.setTotal(iRoleDao.findRoleCount(roleVo));
		ResponseDatagrid r = new ResponseDatagrid();
		List<RoleVo> list = new ArrayList<RoleVo>();
		r.setTotal(iRoleDao.findRoleCount(roleVo));
		for(Object r1:iRoleDao.findAllRole(requestDatagrid, roleVo)){
			if (r1 instanceof RoleVo) {
				RoleVo role = new RoleVo();
				RoleVo ro = (RoleVo)r1;
				UserVo u = iKiinDao.findUserById(ro.getRoleCreatorUser());//获得创建人对象
				UserVo u1 = iKiinDao.findUserById(ro.getRoleFinallyModifyTheUser());//获得最后修改用户对象
				if (u1 == null) {//判断最后修改用户对象是否为空
					u1 = new UserVo();
				}
				if (u == null) {//判断创建人对象是否为空
					u = new UserVo();
				}
				role.setRoleCreationTime(ro.getRoleCreationTime());//设置创建时间
				role.setRoleDescription(ro.getRoleDescription());//设置描述
				role.setRoleIsTheRoleAdefault(ro.getRoleIsTheRoleAdefault());//设置判断是否为默认角色
				role.setRoleName(ro.getRoleName());//设置角色名称
				role.setRoleNumber(ro.getRoleNumber());//设置角色编号
				role.setRoleCreateUserName(u.getUserName());//设置创建人名称
				list.add(role);
			}
		}
		r.setRows(list);
		return r;
	}
	
	/**
	 * @title: findRoleNameExit
	 * @description: 判断是否有相同的角色名称存在
	 * @param: roleName
	 * @throws Exception
	 */
	public boolean findRoleNameExit(String roleName){
		RoleVo roleVo = iRoleDao.findroleNameExit(roleName);//根据传过来的角色名称查询
		if (roleVo == null) {//roleVo为空，则没有相同名称的角色
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * @title: findRoleNameExitUpdate
	 * @description: 根据角色ID和角色名称判断该角色名称是否存在
	 * @param: roleName,roleNumber
	 * @throws Exception
	 */
	public boolean findRoleNameExitUpdate(String roleName,String roleNumber){
		try {
			RoleVo roleVo = iRoleDao.findRoleNameExitUpdate(roleName,roleNumber);//根据传过来的角色名称查询
			if (roleVo == null) {//roleVo为空，则没有相同名称的角色
				return true;
			}else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	/**
	 * @title: add
	 * @description: 新增角色信息
	 * @param: Role对象
	 * @throws Exception
	 */
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false)
	public Map<String, Object> add(Role role,User user){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			role.setRoleName(new String(role.getRoleName().getBytes("iso-8859-1"),"utf-8"));
			role.setRoleNumber(Common.getRoleNumber());//设置角色编号
			role.setRoleCreationTime(Common.getNow());//设置创建时间
			role.setRoleCreatorUser(user.getUserNumber());//设置创建人
			iRoleDao.addRole(role);
			map.put("code", 200);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("code", 500);
		}
		return map;
	}
	
	/**
	 * @title: findLastRole
	 * @description: 查询最近一次新增的信息
	 * @throws Exception
	 */
	public RoleVo findLastRole(){
		return iRoleDao.findLastRole();
	}
	
	/**
	 * @title: findLastRole
	 * @description: 查询所有功能权限
	 * @throws Exception
	 */
	public List<EasyUiTreeNode> findMenuAuthority(String theFatherOfMenuId){
		if ("".equals(theFatherOfMenuId) || theFatherOfMenuId == null) {//如果传过来的父功能ID等于""或者等于null,则赋值为null
			theFatherOfMenuId = null;
		}
		List<MenuVo> list = iRoleDao.findMenuAuthority(theFatherOfMenuId);//根据父功能ID去数据库寻找相应的功能
		List<EasyUiTreeNode> eList = new ArrayList<EasyUiTreeNode>();
		for(MenuVo m:list){//循环从数据库找到的功能聚合，将值赋给eList集合
			EasyUiTreeNode easy = new EasyUiTreeNode();
			easy.setId(m.getMenuId().toString());
			easy.setText(m.getMenuName());
			List<MenuVo> l = new ArrayList<MenuVo>();
			l = iRoleDao.findMenuAuthority(m.getMenuId().toString());//判断 该节点下还有没有子节点
			if (l.size() > 0) {//集合数大于0，代表有子节点
				easy.setState("closed");
			}
			eList.add(easy);
		}
		return eList;
	}
	
	
	/**
	 * @title: findDataAuthority
	 * @description: 查询所有数据权限
	 * @throws Exception
	 */
	public List<EasyUiTreeNode> findDataAuthority(String theFatherOfMenuId){
		if ("".equals(theFatherOfMenuId) || theFatherOfMenuId == null) {//如果传过来的父数据ID等于""或者等于null,则赋值为null
			theFatherOfMenuId = null;
		}
		List<MenuVo> list = iRoleDao.findDataAuthority(theFatherOfMenuId);//根据父数据权限ID去数据库寻找相应的数据权限
		List<EasyUiTreeNode> eList = new ArrayList<EasyUiTreeNode>();
		for(MenuVo m:list){//循环从数据库找到的数据聚合，将值赋给eList集合
			EasyUiTreeNode easy = new EasyUiTreeNode();
			easy.setId(m.getMenuId().toString());
			easy.setText(m.getMenuName());
			List<MenuVo> l = new ArrayList<MenuVo>();
			l = iRoleDao.findDataAuthority(m.getMenuId().toString());//判断 该节点下还有没有子节点
			if (l.size() > 0) {
				easy.setState("closed");
			}
			eList.add(easy);
		}
		return eList;
	}
	
	/**
	 * @title: findRoleById
	 * @description: 根据ID查询角色信息
	 * @throws Exception
	 */
	public RoleVo findRoleById(String roleNumber){
		return iRoleDao.findRoleById(roleNumber);
	}
	
	/**
	 * @title: updateRole
	 * @description: 修改角色信息
	 * @throws Exception
	 */
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false)
	public Map<String, Object> updateRole(Role role,User user){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			role.setRoleLastModifiedTime(Common.getNow());//设置最后修改时间
			role.setRoleFinallyModifyTheUser(user.getUserNumber());//设置最后修改用户
			iRoleDao.updateRole(role);
			map.put("code", 200);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("code", 500);
		}
		return map;
	}
	
	/**
	 * @title: findUser
	 * @description: 查询除管理员之外的用户
	 * @throws Exception
	 */
	public List<EasyUiTreeNode> findUser(String roleNumber,User user){
		List<UserVo> list = iRoleDao.findUser(roleNumber,user.getUserNumber());
		List<EasyUiTreeNode> eList = new ArrayList<EasyUiTreeNode>();
		for(UserVo m:list){//循环从数据库找到的数据聚合，将值赋给eList集合
			EasyUiTreeNode easy = new EasyUiTreeNode();
			easy.setId(m.getUserNumber());
			easy.setText(m.getUserName());
			eList.add(easy);
		}
		return eList;
	}
	
	/**
	 * @title: deleteRole
	 * @description: 删除角色
	 * @throws Exception
	 */
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false)
	public Map<String, Object> deleteRole(String roleNumber){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			iRoleDao.deleteRole(roleNumber);
			map.put("code", 200);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("code", 500);
		}
		return map;
	}
	
	/**
	 * @title: addRoleAndUser
	 * @description: 添加用户和角色关联表
	 * @throws Exception
	 */
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false)
	public Map<String, Object> addRoleAndUser(String roleNumber,String userNumber){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<String> ulist = new ArrayList<String>();
			List<String> list = new ArrayList<String>();
			List<UserAndRole> list1 = iRoleDao.judgeUser(roleNumber);//根据角色编号查询角色，用户关联表
			for(int i = 0;i<userNumber.split(",").length;i++){
				if (!list1.contains(userNumber.split(",")[i])) {
					//新增
					list.add(userNumber.split(",")[i]);
				}
			}
			iRoleDao.addRoleAndUser(roleNumber, list);//批量新增用户，角色关联表
			for(int j = 0;j<userNumber.split(",").length;j++){
				ulist.add(userNumber.split(",")[j]);
			}
			List<String> dlist = new ArrayList<String>();
			for(UserAndRole u:list1){
				if (!ulist.contains(u.getUserNumber())) {
					//删除
					dlist.add(u.getUserNumber());
				}
			}
			if (dlist.size()!=0) {//判断需要删除的用户编号集合的长度，如果不为0，证明有需要删除的用户
				iRoleDao.deleteRoleAndUser(roleNumber, dlist);
			}
			map.put("code", 200);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("code", 500);
		}
		return map;
	}
	
	/**
	 * @title: distributeAuthority
	 * @description: 为角色分配权限
	 * @throws Exception
	 */
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false)
	public Map<String, Object> distributeAuthority(String checkedNode,String checkedDataNode,String roleNumber){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<Integer> list = new ArrayList<Integer>();
			//处理功能权限
			if (!"".equals(checkedNode)) {
				for(int i = 0;i<checkedNode.split(",").length;i++){//循环从前台传过来的选中的集合
					list.add(Integer.valueOf(checkedNode.split(",")[i]));//添加到定义的集合中
				}
			}
			//处理数据权限
			if (!"".equals(checkedDataNode)) {
				for(int j = 0;j<checkedDataNode.split(",").length;j++){//循环从前台传过来的选中的集合
					list.add(Integer.valueOf(checkedDataNode.split(",")[j]));//添加到定义的集合中
				}
			}
			if (list.size()>0) {//判断list的长度，如果长度大于0就进数据库进行添加
				iRoleDao.addRoleAndMenu(list, roleNumber);//添加权限和角色的关联
			}
			map.put("code", 200);
		} catch (Exception e) {
			map.put("code", 500);
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * @title: judgeAuthority
	 * @description: 判断该节点下是否有选中
	 * @throws Exception
	 */
	public List<Object> judgeAuthority(String menuId,String roleNumber){
		return iRoleDao.judgeAuthority(Integer.valueOf(menuId),roleNumber);
	}
	
	/**
	 * @title: findMenuByFather
	 * @description: 根据功能ID查询该功能下的子功能
	 * @throws Exception
	 */
	public List<Object> findMenuByFather(String menuId){
		return iRoleDao.findMenuByFather(Integer.valueOf(menuId));
	}
	
	/**
	 * @title: distributeAuthorityAgagin
	 * @description: 为角色的权限分配进行修改
	 * @throws Exception
	 */
	public Map<String, Object> distributeAuthorityAgagin(String roleNumber,String checkedData,String checkedMenu){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String all = "";
			if (!"".equals(checkedData)) {
				all = checkedData +","+ checkedMenu;
			}else {
				all = checkedMenu;
			}
			
			List<Integer> dlist = new ArrayList<Integer>();//要删除的集合
			List<Integer> alist = new ArrayList<Integer>();//要添加的集合
			List<Integer> list = iRoleDao.findRoleAndMenuByRoleNumber(roleNumber);//查询该角色的已有权限
			List<Integer> allList = new ArrayList<Integer>();
			if (!"".equals(all)) {//对权限进行操作
				for(int i = 0;i<all.split(",").length;i++){//循环从前台传过来的集合
					allList.add(Integer.valueOf(all.split(",")[i]));//将从前台传过来的字符串以,分隔，添加到allList集合中
					if (!list.contains(Integer.valueOf(all.split(",")[i]))) {//判断从数据库中读取的集合中是否包含从前台传过来的数据，如果没包含，则添加
						alist.add(Integer.valueOf(all.split(",")[i]));
					}
				}
				for(Integer r:list){//循环从数据库读取的数据
					if (!allList.contains(r)) {//判断从前台传过来的数据中是否包含从数据库读取的数据，如果不包含，则删除数据库中的数据
						dlist.add(r);
					}
				}
			}else {//从前台传过来的选中的值为空
				if (list.size() != 0) {//判断该角色在数据库里，之前有没有权限，如果有，则删除
					for(Integer l:list){
						dlist.add(l);
					}
				}
			}
			if (dlist.size()>0) {
				iRoleDao.deleteRoleAndMenu(dlist);//删除
			}
			if (alist.size()>0) {
				iRoleDao.addRoleAndMenu(alist, roleNumber);//添加
			}
			map.put("code", 200);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("code", 500);
		}
		return map;
	}
	public List<String> judgeUser(String roleNumber){
		List<String> list = new ArrayList<String>();
		for(UserAndRole a:iRoleDao.judgeUser(roleNumber)){
			list.add(a.getUserNumber());
		}
		return list;
	}
}