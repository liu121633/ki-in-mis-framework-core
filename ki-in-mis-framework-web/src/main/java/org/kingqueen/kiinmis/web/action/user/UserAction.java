package org.kingqueen.kiinmis.web.action.user;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.kingqueen.kiinmis.model.biz.index.IndexBiz;
import org.kingqueen.kiinmis.model.biz.kiin.KiinBiz;
import org.kingqueen.kiinmis.model.biz.user.UserBiz;
import org.kingqueen.kiinmis.model.eaysui.pojo.Tree;
import org.kingqueen.kiinmis.model.pojo.Kiin;
import org.kingqueen.kiinmis.model.pojo.Position;
import org.kingqueen.kiinmis.model.pojo.Role;
import org.kingqueen.kiinmis.model.pojo.User;
import org.kingqueen.kiinmis.model.pojo.Usersofchess;
import org.kingqueen.kiinmis.model.vo.user.TreeVo;
import org.kingqueen.kiinmis.model.vo.user.UserVo;
import org.kingqueen.kiinmis.uitl.UtilWZQ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("user")
/**
 * @ClassName UserAction
 * @description 用户的action类
 * @author wzq
 * @date 2017年12月2日
 * @version v1.0
 */
public class UserAction {

	@Autowired
	private UserBiz userBiz;
	
	@Autowired
	private KiinBiz kiinBiz;
	
	// 会话对象se
	@Autowired
	private HttpSession session;

	@Autowired
	public IndexBiz indexBiz;
	
	/**
	 * @title:toPositionInfo
	 * @description 跳转到用户页面
	 * 
	 */
	@RequestMapping("toUsersManger")
	public String toUsersManger(){
		return "user/UsersManager";
	}
	
	@RequestMapping("updatePassword")
	@ResponseBody
	public Map<String, Object> updatePassWord(HttpSession session,String newPass){
		return indexBiz.updatePassWord(newPass, ((User)session.getAttribute("user")).getUserNumber());
	}
	
	/**
	 * @title:loadUserDatagrid
	 * @description 用户数据的ajax方法datagrid
	 * 
	 */
	@ResponseBody
	@RequestMapping("loadUserDatagrid")
	public Object loadUserDatagrid(String page,String rows,
			String sort,String order,
			String userManagerName,String userManagerLevel,
			String userManagerStart,String userManagerEnd,
			String userManagerLogout,String knumber
			){
		User user = (User) session.getAttribute("user");
		//注销用户，初始化正常，查询默认正常
		userManagerLogout = userManagerLogout==null||"".equals(userManagerLogout)||"-1".equals(userManagerLogout)?"0":userManagerLogout;
		//判断页数页是否输入正确
		Integer rowsInt = rows==null||"".equals(rows)?10:Integer.valueOf(rows);
		//判断当前是否输入正确
		Integer pageInt = page==null||"".equals(page)?1:Integer.valueOf(page);
		if(knumber==null||knumber==""){
		// 通过方法计算出用户 得到用户的棋院
		List<String> kiinids = indexBiz.calculateUserManagementKiin(user);
		 knumber=kiinids.get(0).toString();
		}
		Map<String,Object> map = userBiz.findUserDatagrid(pageInt,rowsInt,sort,order,
				userManagerName,userManagerLevel,
				userManagerStart,userManagerEnd,
				userManagerLogout,knumber);
		//获取当前用户的棋院
		List<String> kiinids1 = indexBiz.calculateUserManagementKiin(user);
		String knumber1=kiinids1.get(0).toString();
		
		//如果是超级管理员就不移除
		if(!"YH-20171212140431982".equals(user.getUserNumber())){
			//判断是否是本棋院的查询如果是本棋院的话就从集合中删除
			List<UserVo> list = (List<UserVo>) map.get("rows");
			Iterator<UserVo> iter = list.iterator();
			while (iter.hasNext()) {
				UserVo temp = iter.next();
				//与当前用户是否是同一个棋院,如果是移除掉
				//获取当前用户的棋院
				User user1 = new User();
				user1.setUserNumber(temp.getUserNumber());
				List<String> kiinStrFor = indexBiz.calculateUserManagementKiin(user1);
				if(kiinStrFor.get(0).equals(knumber1)){
					iter.remove();
				}
			}
			//重新赋值个数 
			map.put("total", list.size());
			map.put("rows", list);
		}
		return map;
	}
	
	/**
	 * @title:loadUserDatagrid
	 * @description 批量恢复密码
	 * 
	 */
	@ResponseBody
	@RequestMapping("recoverPwd")
	public Object recoverPwd(String userArrStr,HttpSession session){
		//获取当前用户的对象
		User user = (User) session.getAttribute("user");
		
		String[] arrId = userArrStr.split(",");
		Map<String,Object> map = new HashMap<String, Object>();
		//判断是否操作的是自己
		if(user.getUserNumber().equals(arrId[0])){
			map.put("success","500");
			map.put("message","不能操作自己!");
			return map;
		}
		
		//判断userArrStr是否为空
		if(userArrStr==null&&"".equals(userArrStr)){
			return "";
		}
		
		//按id查询用户
		 UserVo userVo = userBiz.findUsersVoById(arrId[0]);
		 
		//如果是总部管理登录进入的话查询是否是Admin
			//如果是admin话就能创建总部的管理员
			
			
		//查询用户的部门
		List<String> kiinStrList = userBiz.findUserKiinChildrenId(user.getUserNumber());
		//如果是总部管理登录进入的话查询是否是Admin
		//如果是admin话就能创建总部的管理员
		boolean b = "admin".equals(user.getUserLoginName());
		if(!b){
			if(userVo.getKiinIdStr().indexOf("QY-201712121534")!=-1){
				map.put("success","500");
				map.put("message","不能恢复总部用户密码,请转交给系统管理员来恢复!");
				return map;	
			}else if(kiinStrList.get(0).indexOf(userVo.getKiinIdStr())!=-1){
				map.put("success","500");
				map.put("message","不能恢复本部用户密码,请转交给上级棋院管理人员来恢复!");
				return map;	
			}
		}
		
		userBiz.updateRecoverPwd(arrId,user.getUserNumber());
		
		map.put("success","200");
		map.put("message","恢复成功！");
		return map;
	}
	
	/**
	 * @title:loadUserDatagrid
	 * @description 验证用户名是否重复
	 */ 
	@ResponseBody
	@RequestMapping("validateUserAccount")
	public Object validateUserAccount(String addUserLoginName){
		return userBiz.existUser(addUserLoginName);
	}
	
	/**
	 * @title:logoutUser
	 * @description 批量注销
	 */
	@ResponseBody
	@RequestMapping("logoutUser")
	public Object logoutUser(String userArrStr,HttpSession session){
		User user  = (User) session.getAttribute("user");
		
		String[] arrId = userArrStr.split(",");
		Map<String,Object> map = new HashMap<String, Object>();
		//判断是否操作的是自己
		if(user.getUserNumber().equals(arrId[0])){
			map.put("success","500");
			map.put("message","不能操作自己!");
			return map;
		}
		
		//判断userArrStr是否为空
		if(userArrStr==null&&"".equals(userArrStr)){
			return "";
		}
		
		//按id查询用户
		 UserVo userVo = userBiz.findUsersVoById(arrId[0]);
		 
		//如果是总部管理登录进入的话查询是否是Admin
		//如果是admin话就能创建总部的管理员
		
		
		//查询用户的部门
		List<String> kiinStrList = userBiz.findUserKiinChildrenId(user.getUserNumber());
		//如果是总部管理登录进入的话查询是否是Admin
		//如果是admin话就能创建总部的管理员
		boolean b = "admin".equals(user.getUserLoginName());
		if(!b){
			if(userVo.getKiinIdStr().indexOf("QY-201712121534")!=-1){
				map.put("success","500");
				map.put("message","不能注销总部用户,请转交给系统管理员来创建!");
				return map;	
			}else if(kiinStrList.get(0).indexOf(userVo.getKiinIdStr())!=-1){
				map.put("success","500");
				map.put("message","不能注销本部用户,请转交给上级棋院管理人员来注销!");
				return map;	
			}
		}
		
		userBiz.updateLogout(arrId,user.getUserNumber());
		
		map.put("success","200");
		map.put("message","注销成功！");
		return map;
	}
	
	/**
	 * @title:
	 * @description 跳转取消用户角色分配页面
	 */
	
	/**
	 * @title:toUserResetLogout
	 * @description 跳转取消用户角色分配页面
	 */
	@RequestMapping("toUserResetLogout")
	public String toUserResetLogout(HttpServletRequest request,HttpServletResponse response,String userNumber,Model model){
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		model.addAttribute("userNumber", userNumber);
		//查询所有角色
		List<Role> roleList = userBiz.findRoleAll();
		//把roleList放入Model中
		model.addAttribute("roleList",roleList);
		return "user/UserResetLogout";
	}
	

	/**
	 * @title:doUserResetLogout
	 * @description 取消注销
	 */
	@ResponseBody
	@RequestMapping("doUserResetLogout")
	public Object doUserResetLogout(String UserRestLogoutUserNumber,/*String UserRestLogoutUserRole,*/HttpSession session){
		//获取登录对象
		User currentUser = (User) session.getAttribute("user");
		
		Map<String,Object> map = new HashMap<String,Object>();
		//不能操作自己
		if(currentUser.getUserNumber().equals(UserRestLogoutUserNumber)){
			map.put("success","500");
			map.put("message","不能操作自己!");
			return map;
		}
		//按id查询用户
		 UserVo userVo = userBiz.findUsersVoById(UserRestLogoutUserNumber);
		 
		//如果是总部管理登录进入的话查询是否是Admin
			//如果是admin话就能创建总部的管理员
			
			//查询用户的部门
			List<String> kiinStrList = userBiz.findUserKiinChildrenId(currentUser.getUserNumber());
			//如果是总部管理登录进入的话查询是否是Admin
			//如果是admin话就能创建总部的管理员
			boolean b = "admin".equals(currentUser.getUserLoginName());
			if(!b){
				if(userVo.getKiinIdStr().indexOf("QY-201712121534")!=-1){
					map.put("success","500");
					map.put("message","不能取消注销总部用户,请转交给系统管理员来取消注销!");
					return map;	
					}else if(kiinStrList.get(0).indexOf(userVo.getKiinIdStr())!=-1){
					map.put("success","500");
					map.put("message","不能取消注销本部用户,请转交给上级棋院管理人员来取消注销!");
					return map;	
				}
			}
		
		
		User user = new User();
		user.setUserNumber(UserRestLogoutUserNumber);
//		user.setUserRoles(UserRestLogoutUserRole);
		user.setUserStatus(0);
		//创建角色对象
		Role role = new Role();
		List<String> kiinStrList1 = userBiz.findUserKiinChildrenId(UserRestLogoutUserNumber);
		//判断是否是总部管理员
		if("QY-201712121534".equals(kiinStrList1.get(0))){
			role.setRoleNumber("JS-20180102171224");
		}else{
			role.setRoleNumber("JS-20180102171527");
		}
		
		userBiz.updateUserResetLogout(user,currentUser.getUserNumber(),role);
		
		
		map.put("success",200);
		map.put("message","取消注销成功");
		return map;
	}
	
	/**
	 * @title:toAddUser
	 * @description 跳转到新增用户的页面
	 * 
	 */
	@RequestMapping("toAddUser")
	public String toAddUser(Model model,HttpSession session){
		//获取用户对象
		User user = (User) session.getAttribute("user");
		
		List<String> kiinNameStr = userBiz.findUserKiinChildren(user.getUserNumber());
		model.addAttribute("kiinNameStr",kiinNameStr);
		
		//查询所有职位
		List<Position> positionList = userBiz.findPositionAll();
		//把positionList放入Model中
		model.addAttribute("positionList",positionList);
		
		//查询所有角色
		List<Role> roleList = userBiz.findRoleAll();
		//把roleList放入Model中
		model.addAttribute("roleList",roleList);
		return "user/addUser";
	}
	
	/**
	 * @title:findTreeKiin
	 * @description 树状图的查询
	 * 
	 */
	@ResponseBody
	@RequestMapping("findTreeKiin")
	public Object findTreeKiin(String id){
		
		List<TreeVo> treeList = userBiz.findTreeKiin(id);
		return treeList;
	}
	
	/**
	 * @title: AddUser
	 * @description 新增用户
	 * 
	 */
	@ResponseBody
	@RequestMapping("AddUser")
	public Object AddUser(HttpSession session,
			String addUserName,String addUserPwd,
			/*String addUserRole,*/String addUserLevel,
			String addUserKiin,String addUserPosition,String addUserLoginName){
		//获取当前用户
		//获取用户对象
		User currentUser = (User) session.getAttribute("user");
		
		User userCurrent = (User) session.getAttribute("user");
		Map<String,Object> map = new HashMap<String,Object>();
		if(addUserKiin==null||"".equals(addUserKiin)){
			map.put("success","500");
			map.put("message","部门为空新增失败!");
			return map;
		}
		
		String[] kiinArr = addUserKiin.split(",");
		
		//查询用户的部门
		List<String> kiinStrList = userBiz.findUserKiinChildrenId(userCurrent.getUserNumber());
		//如果是总部管理登录进入的话查询是否是Admin
		//如果是admin话就能创建总部的管理员
		boolean b = "admin".equals(currentUser.getUserLoginName());
		if(!b){
			if("QY-201712121534".equals(kiinArr[0])){
				map.put("success","500");
				map.put("message","不能创建总部用户,请转交给系统管理员来创建!");
				return map;	
			}else if(kiinStrList.get(0).equals(kiinArr[0])){
				map.put("success","500");
				map.put("message","不能操作本部用户,请转交给上级棋院管理人员来创建!");
				return map;	
			}
		}
		
		
		String userNumber =UtilWZQ.getUtilDate();
		//用户赋值
		User user = new User();
		user.setUserNumber("YH-"+userNumber);
		user.setUserCreationTime(new Timestamp(new Date().getTime()));
		
		//用户赋值id 
		user.setUserfounder(userCurrent.getUserNumber());
		
		user.setUserLevel(Integer.valueOf(addUserLevel));
		user.setUserLoginName(addUserLoginName);
		user.setUserName(addUserName);
		user.setUserPassword(addUserPwd);
		user.setUserPosition(addUserPosition);
		/*user.setUserRoles(addUserRole);*/
		user.setUserStatus(0);
		
		//创建角色对象
		Role role = new Role();
		
		//判断是否是总部管理员
		if("QY-201712121534".equals(kiinArr[0])){
			role.setRoleNumber("JS-20180102171224");
		}else{
			role.setRoleNumber("JS-20180102171527");
		}
		//新增用户
		userBiz.addUser(user,role);
		//新增用户的部门
		List<Usersofchess> usersofchessList = new ArrayList<Usersofchess>();
		
		for (String string : kiinArr) {
			Usersofchess usersofchess = new Usersofchess();
			//新增用户部门的用户赋值
			usersofchess.setUserNumber("YH-"+userNumber);
			//新增用户的部门赋值
			usersofchess.setChessNumber(string);
			usersofchessList.add(usersofchess);
		}
		userBiz.addUserKiin(usersofchessList);
		map.put("success","200");
		map.put("message","新增成功！");
		return map;
	}
	
	/**
	 * @title:updateUser
	 * @description 修改用户
	 * 
	 */
	@ResponseBody
	@RequestMapping("updateUser")
	public Object updateUser(HttpSession session,String updateUserId,String updateUserName,
			/*String updateUserRole,*/String updateUserLevel,
			String updateUserKiin,String updateUserPosition){
		User userCurrent = (User) session.getAttribute("user");
		Map<String,Object> map = new HashMap<String,Object>();
		//不能操作自己
		if(userCurrent.getUserNumber().equals(updateUserId)){
			map.put("success","500");
			map.put("message","不能操作自己!");
			return map;
		}
		
		
		if(updateUserKiin==null||"".equals(updateUserKiin)){
			map.put("success","500");
			map.put("message","部门为空修改失败!");
			return map;
		}
		String[] kiinArr = updateUserKiin.split(",");
		//查询用户的部门
		List<String> kiinStrList = userBiz.findUserKiinChildrenId(userCurrent.getUserNumber());
		//如果是总部管理登录进入的话查询是否是Admin
		//如果是admin话就能创建总部的管理员
		boolean b = "admin".equals(userCurrent.getUserLoginName());
		
		//查询修改用户的对象
		UserVo updateUser = userBiz.findUsersVoById(updateUserId);
		
		List<String> kiinStrListUpdate = userBiz.findUserKiinChildrenId(updateUser.getUserNumber());
		
		if(!b){
			if(kiinStrListUpdate.get(0).equals(kiinStrList.get(0))){
				map.put("success","500");
				map.put("message","不能操作本部用户,请转交给上级棋院管理人员来修改!");
				return map;	
			}
		}

		//用户赋值
		User user = new User();
		user.setUserNumber(updateUserId);
		user.setUserLastModificationTime(new Timestamp(new Date().getTime()));
		user.setUserLevel(Integer.valueOf(updateUserLevel));
		//账号不能被修改
		//user.setUserLoginName(updateUserName);
		user.setUserName(updateUserName);
		user.setUserPosition(updateUserPosition);
		/*user.setUserRoles(updateUserRole);*/
		//最后修改人    userCurrent.getUserNumber()
		user.setUserFinallyModifiesTheUser(userCurrent.getUserNumber());
		
		
		userBiz.updateUser(user, kiinArr);
		
		map.put("success","200");
		map.put("message","修改成功！");
		return map;
	}
	
	
	/**
	 * @title:toUpdateUser
	 * @description 跳转到修改用户的页面
	 * 
	 */
	@RequestMapping("toUpdateUser")
	public String toUpdateUser(String userId,Model model){
		
		//获取用户对象
		
		List<String> kiinNameStr = userBiz.findUserKiinChildren(userId);
		model.addAttribute("kiinNameStr",kiinNameStr);
		
		//查询所有职位
		List<Position> positionList = userBiz.findPositionAll();
		//把positionList放入Model中
		model.addAttribute("positionList",positionList);
		
		//查询所有角色
		List<Role> roleList = userBiz.findRoleAll();
		//把roleList放入Model中
		model.addAttribute("roleList",roleList);
		
		//按id查询用户
		UserVo userVo = userBiz.findUsersVoById(userId);
		model.addAttribute("userVo",userVo);
		
		//把userId放入model中
		model.addAttribute("userId", userId);
		
		return "user/updateUser";
	}
	
	
	/**
	 * @title: 查找用户管理的棋院树
	 * @description: 查找用户管理的棋院树
	 * @throws Exception
	 * @return List<Tree>
	 */
	@RequestMapping("findUserTreeKinn")
	@ResponseBody
	public List<Tree> findUserTreeKinn() {
		User user = (User) session.getAttribute("user");
			// 通过方法计算出用户 得到用户的棋院
			List<String> kiinids = indexBiz.calculateUserManagementKiin(user);
		Tree tree = new Tree();
		tree.setChildren(kiinBiz.findUserTreeKinn(kiinids.get(0)));
		Kiin kiin = kiinBiz.findKiinByNumber(kiinids.get(0));
		tree.setId(kiin.getChessNumber());
		tree.setText(kiin.getKiinName());
		tree.setChecked(true);
		List<Tree> trees = new ArrayList<Tree>();
		trees.add(tree);
		return trees;
	}
}
