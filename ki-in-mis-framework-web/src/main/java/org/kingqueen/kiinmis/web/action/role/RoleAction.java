package org.kingqueen.kiinmis.web.action.role;

import org.kingqueen.kiinmis.model.eaysui.pojo.RequestDatagrid;
import org.kingqueen.kiinmis.model.eaysui.pojo.ResponseDatagrid;
import org.kingqueen.kiinmis.model.pojo.Role;
import org.kingqueen.kiinmis.model.pojo.User;
import org.kingqueen.kiinmis.model.pojo.UserAndRole;
import org.kingqueen.kiinmis.model.vo.kiin.EasyUiTreeNode;
import org.kingqueen.kiinmis.model.vo.role.RoleVo;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.kingqueen.kiinmis.model.biz.role.RoleBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * @author 王晓妍
 * @description: 对角色以及权限的操作类
 * @date 2017年12月21日
 * @version V1.0
 * @ClassName KiinAction
 */
@Controller
@RequestMapping("role")
public class RoleAction {
	@Autowired
	private RoleBiz roleBiz;
	
	// 会话对象
	@Autowired
	public HttpSession session;
	/**
	 * @title: toShowRole
	 * @description: 跳转到显示角色的界面
	 * @throws Exception
	 */
	@RequestMapping("toShowRole")
	public String toShowRole(){
		return "role/roleInfo";
	}
	/**
	 * @title: showRole
	 * @description: 显示角色信息
	 * @param: RequestDatagrid对象
	 * @throws Exception
	 */
	@RequestMapping("showRole")
	@ResponseBody
	public ResponseDatagrid showRole(RequestDatagrid requestDatagrid){
		return roleBiz.findRole(requestDatagrid);
	}
	
	/**
	 * @title: toAddRole
	 * @description: 跳转到新增角色的页面
	 * @throws Exception
	 */
	@RequestMapping("toAddRole")
	public String toAddRole(){
		return "role/addRole";
	}
	
	/**
	 * @title: roleNameExit
	 * @description: 判断是否有相同的角色名称存在
	 * @param: 角色名称
	 * @throws Exception
	 */
	@RequestMapping("roleNameExit")
	@ResponseBody
	public boolean roleNameExit(String roleName){
		return roleBiz.findRoleNameExit(roleName);
	}
	
	/**
	 * @title: findRoleNameExitUpdate
	 * @description: 根据角色ID和角色名称判断该角色名称是否存在
	 * @param: 角色名称
	 * @throws Exception
	 */
	@RequestMapping("findRoleNameExitUpdate")
	@ResponseBody
	public boolean findRoleNameExitUpdate(String roleName,String roleNumber){
		return roleBiz.findRoleNameExitUpdate(roleName,roleNumber);
	}
	/**
	 * @title: addRole
	 * @description: 新增角色
	 * @param: 角色对象
	 * @throws Exception
	 */
	@RequestMapping("addRole")
	@ResponseBody
	public Map<String, Object> addRole(Role role){
		
		return roleBiz.add(role,(User)session.getAttribute("user"));
	}
	
	/**
	 * @title: addRole
	 * @description: 跳转到权限分配的页面
	 * @throws Exception
	 */
	@RequestMapping("distributeAuthority")
	public String distributeAuthority(Model model){
		RoleVo roleVo = roleBiz.findLastRole();
		model.addAttribute("roleVo", roleVo);
		return "role/distributeAuthority";
	}
	
	/**
	 * @title: addRole
	 * @description: 跳转到权限分配的页面
	 * @throws Exception
	 */
	@RequestMapping("distributeAuthorityByCheck")
	public String distributeAuthorityByCheck(String obj,String roleNumber,Model model){
		 model.addAttribute("obj", obj);
		 RoleVo roleVo = roleBiz.findRoleById(roleNumber);
		 model.addAttribute("roleVo", roleVo);
		 model.addAttribute("roleNumber", roleNumber);
		return "role/distributeAuthority";
	}
	
	
	/**
	 * @title: findMenuAuthority
	 * @description: 查询所有功能权限
	 * @throws Exception
	 */
	@RequestMapping("findMenuAuthority")
	@ResponseBody
	public List<EasyUiTreeNode> findMenuAuthority(String id){
		return roleBiz.findMenuAuthority(id);
	}
	
	/**
	 * @title: findMenuAuthority
	 * @description: 查询所有数据权限
	 * @throws Exception
	 */
	@RequestMapping("findDataAuthority")
	@ResponseBody
	public List<EasyUiTreeNode> findDataAuthority(String id){
		return roleBiz.findDataAuthority(id);
	}
	/**
	 * @title: toUpdateRole
	 * @description: 跳转到修改角色的界面
	 * @param: 角色编号
	 * @throws Exception
	 */
	@RequestMapping("toUpdateRole")
	public String toUpdateRole(Model model,String roleNumber){
		RoleVo roleVo = roleBiz.findRoleById(roleNumber);
		model.addAttribute("roleVo", roleVo);
		return "role/updateRole";
	}
	
	/**
	 * @title: updateRole
	 * @description: 修改角色
	 * @param: 角色对象
	 * @throws Exception
	 */
	@RequestMapping("updateRole")
	@ResponseBody
	public Map<String, Object> updateRole(Role role){
		return roleBiz.updateRole(role, (User)session.getAttribute("user"));
	}
	
	/**
	 * @title: toRoleDistribute
	 * @description: 跳转到分配角色的窗口
	 * @param: 角色编号
	 * @throws Exception
	 */
	@RequestMapping("toRoleDistribute")
	public String toRoleDistribute(String roleNumber,Model model){
		RoleVo roleVo = roleBiz.findRoleById(roleNumber);
		model.addAttribute("roleVo", roleVo);
		return "role/roleDistribute";
	}
	
	/**
	 * @title: deleteRole
	 * @description: 删除角色信息
	 * @param: 角色编号
	 * @throws Exception
	 */
	@RequestMapping("deleteRole")
	@ResponseBody
	public Map<String, Object> deleteRole(String roleNumber){
		return roleBiz.deleteRole(roleNumber);
	}
	
	/**
	 * @title: distributeRole
	 * @description: 分配角色
	 * @param: 角色编号,用户编号
	 * @throws Exception
	 */
	@RequestMapping("distributeRole")
	@ResponseBody
	public Map<String, Object> distributeRole(String roleNumber,String userNumber){
		return roleBiz.addRoleAndUser(roleNumber,userNumber);
	}
	
	
	/**
	 * @title: distrubiteAuthority
	 * @description: 为角色分配权限
	 * @param: 选中的功能权限集合，选中的数据权限集合，角色编号
	 * @throws Exception
	 */
	@RequestMapping("distrubiteAuthority")
	@ResponseBody
	public Map<String, Object> distrubiteAuthority(String checkedNode,String checkedDataNode,String roleNumber){
		return roleBiz.distributeAuthority(checkedNode,checkedDataNode,roleNumber);
	}
	
	/**
	 * @title: judgeAuthority
	 * @description: 判断在数据库中该节点下是否有节点选中
	 * @param: 功能ID，角色编号
	 * @throws Exception
	 */
	@RequestMapping("judgeAuthority")
	@ResponseBody
	public Map<String, Object> judgeAuthority(String menuId,Model model,String roleNumber){
		Map<String, Object> map = new HashMap<String, Object>();
		List<Object> list = roleBiz.judgeAuthority(menuId,roleNumber);//判断该节点下是否有选中
		List<Object> elist = roleBiz.findMenuByFather(menuId);//根据功能ID查询该功能下的子功能
		map.put("list", list);
		map.put("elist",elist);
		model.addAttribute("map", map);
		return map;
	}
	
	/**
	 * @title: judgeAuthority
	 * @description: 修改该角色的权限
	 * @param: 选中的功能权限集合，选中的数据权限集合，角色编号
	 * @throws Exception
	 */
	@RequestMapping("distrubiteAuthorityAgain")
	@ResponseBody
	public Map<String, Object> distrubiteAuthorityAgain(String rNumber,String checkedNode,String checkedDataNode){
		return roleBiz.distributeAuthorityAgagin(rNumber,checkedNode,checkedDataNode);
	}
	
	/**
	 * @title: showUser
	 * @description: 根据角色编号查询除超级管理员外，并且该角色还未关联的用户信息
	 * @param: 角色编号
	 * @throws Exception
	 */
	@RequestMapping("showUser")
	@ResponseBody
	public List<EasyUiTreeNode> showUser(String roleNumber){
		return roleBiz.findUser(roleNumber, (User)session.getAttribute("user"));
	}
	
	@RequestMapping("judgeUser")
	@ResponseBody
	public List<String> judgeUser(String roleNumber){
		return roleBiz.judgeUser(roleNumber);
	}
}
