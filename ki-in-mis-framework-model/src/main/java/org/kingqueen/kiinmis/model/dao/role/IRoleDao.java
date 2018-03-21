package org.kingqueen.kiinmis.model.dao.role;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.kingqueen.kiinmis.model.eaysui.pojo.RequestDatagrid;
import org.kingqueen.kiinmis.model.pojo.Role;
import org.kingqueen.kiinmis.model.pojo.UserAndRole;
import org.kingqueen.kiinmis.model.vo.menu.MenuVo;
import org.kingqueen.kiinmis.model.vo.role.RoleVo;
import org.kingqueen.kiinmis.model.vo.user.UserVo;

public interface IRoleDao {
	List<RoleVo> findAllRole(@Param("requestDatagrid")RequestDatagrid requestDatagrid,@Param("roleVo")RoleVo roleVo);//分页查询角色的数据
	Integer findRoleCount(@Param("roleVo")RoleVo roleVo);//查询角色表的总行数
	RoleVo findroleNameExit(@Param("roleName")String roleName);//判断是否有相同的角色名称存在
	RoleVo findRoleNameExitUpdate(@Param("roleName")String roleName,@Param("roleNumber")String roleNumber);//根据角色ID和角色名称判断该角色名称是否存在
	void addRole(Role role);//新增角色信息
	RoleVo findLastRole();//查询最近一次新增的信息
	List<MenuVo> findMenuAuthority(@Param("theFatherOfMenuId")String theFatherOfMenuId);//查询所有功能数据
	List<MenuVo> findDataAuthority(@Param("theFatherOfMenuId")String theFatherOfMenuId);//查询所有数据权限的数据
	RoleVo findRoleById(@Param("roleNumber")String roleNumber);//根据ID查询角色信息
	void updateRole(Role role);//修改角色信息
	List<UserVo> findUser(@Param("roleNumber")String roleNumber,@Param("userNumber")String userNumber);//查询除管理员之外的用户
	void deleteRole(@Param("roleNumber")String roleNumber);//删除角色以及与该角色相关联的数据
	void addRoleAndUser(@Param("roleNumber")String roleNumber,@Param("list")List<String> list);//添加用户和角色关联表
	void addRoleAndMenu(@Param("list")List<Integer> list,@Param("roleNumber")String roleNumber);//为角色权限表添加信息
	List<Object> judgeAuthority(@Param("menuId")Integer menuId,@Param("roleNumber")String roleNumber);//判断该节点下是否有选中
	List<Object> findMenuByFather(@Param("menuId")Integer menuId);//根据功能ID查询该功能下的子功能
	void deleteRoleAndMenu(@Param("list")List<Integer> list);//删除角色和功能表的信息
	List<Integer> findRoleAndMenuByRoleNumber(@Param("roleNumber")String roleNumber);//根据角色编号查询该角色的权限
	List<UserAndRole> judgeUser(@Param("roleNumber")String roleNumber);//根据角色编号查询用户，角色关联表
	void deleteRoleAndUser(@Param("roleNumber")String roleNumber,@Param("list")List<String> list);//根据用户编号和角色编号删除用户，角色关联表
}
