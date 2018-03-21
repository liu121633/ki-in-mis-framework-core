package org.kingqueen.kiinmis.model.dao.user;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;
import org.kingqueen.kiinmis.model.pojo.Kiin;
import org.kingqueen.kiinmis.model.pojo.Position;
import org.kingqueen.kiinmis.model.pojo.Role;
import org.kingqueen.kiinmis.model.pojo.User;
import org.kingqueen.kiinmis.model.pojo.Usersofchess;
import org.kingqueen.kiinmis.model.vo.user.TreeVo;
import org.kingqueen.kiinmis.model.vo.user.UserVo;

/**
 * @ClassName UserDao
 * @description 用户的Dao接口
 * @author wzq
 * @date 2017年12月2日
 * @version v1.0
 */
public interface UserDao {

	/**
	 * @title:findPositionDatagrid
	 * @description 用户分页查询
	 */
	public List<UserVo> findUserDatagrid(@Param("page")Integer page,@Param("rows")Integer rows,
			@Param("sort")String sort,@Param("order")String order,
			@Param("userManagerName")String userManagerName,@Param("userManagerLevel")String userManagerLevel,
			@Param("userManagerStart")String userManagerStart,@Param("userManagerEnd")String userManagerEnd,
			@Param("userManagerLogout")String userManagerLogout,@Param("knumber")String knumber);
	
	/**
	 * @title:findUserKiin
	 * @description 查询用户所属的部门
	 */
	public List<Kiin> findUserKiin(@Param("userId")String userId);
	
	/**
	 * @title:findUserCount
	 * @description 查询用户的条数
	 */
	public Integer findUserCount(@Param("userManagerName")String userManagerName,@Param("userManagerLevel")String userManagerLevel,
			@Param("userManagerStart")String userManagerStart,@Param("userManagerEnd")String userManagerEnd,
			@Param("userManagerLogout")String userManagerLogout,@Param("knumber")String knumber);
	/**
	 * @title:findUserById
	 * @description 根据id查询用户
	 */
	public List<User> findUserById(@Param("userId")String userId);
	
	/**
	 * @title:updateRecoverPwd
	 * @description 批量恢复密码
	 */
	public void updateRecoverPwd(@Param("list")String[] list,@Param("userNumber")String userNumber);
	
	/**
	 * @title:updateLogout
	 * @description 批量注销
	 */
	public void updateLogout(@Param("list")String[] list,@Param("userNumber")String userNumber);
	

	/**
	 * @title:findRoleAll
	 * @description 查询所有角色
	 */
	public List<Role> findRoleAll();
	
	/**
	 * @title:updateUserResetLogout
	 * @description 取消注销
	 */
	public void updateUserResetLogout(@Param("user")User user,@Param("userId")String userNumber);
	
	/**
	 * @title:findPositionAll
	 * @description 查询职位
	 */
	public List<Position> findPositionAll();
	/**
	 * @title:findTreeKiin
	 * @description 树状图的查询
	 * 
	 */
	public List<TreeVo> findTreeKiin(@Param("id")String id);
	
	/**
	 * @title:addUser
	 * @description 新增用户
	 * 
	 */
	public void addUser(User user);
	
	/**
	 * @title:addUserKiin
	 * @description 新增用户的部门
	 * 
	 */
	public void addUserKiin(Usersofchess usersofchess);
	
	/**
	 * @title:findUsersVoById
	 * @description 按id查询用户UserVo
	 * 
	 */
	public List<UserVo> findUsersVoById(@Param("userId")String userId);
	
	/**
	 * @title:updateUser
	 * @description 修改用户
	 * 
	 */
	public void updateUser(User user);
	
	/**
	 * @title:deleteUserLinkKiin
	 * @description 删除用户的部门
	 * 
	 */
	public void deleteUserLinkKiin(@Param("userId")String userId);
	
	/**
	 * @title:findUserVoCondition
	 * @description 查询所有
	 */
	public List<UserVo> findUserVoCondition(
			@Param("userManagerName")String userManagerName,@Param("userManagerLevel")String userManagerLevel,
			@Param("userManagerStart")String userManagerStart,@Param("userManagerEnd")String userManagerEnd,
			@Param("userManagerLogout")String userManagerLogout);
	
	/**
	 * @title:findUserVoSelect
	 * @description 查询保函UserId的集合返回UserVO
	 */
	public List<UserVo> findUserVoSelect(List<UserVo> list);
	
	/**
	 * @title:existUser
	 * @description 按用户名查询
	 */
	public User existUser(@Param("account")String account);

	/**
	 * @title:findRolesByUserName
	 * @description 查询用户的所有角色
	 * @return List<String>
	 */
	public List<String> findRolesByUserName(@Param("userName")String userName);
	
	
	/**
	 * @title:findPermissionsByRoles
	 * @description 查询用户的所有权限
	 * @param list
	 * @return List<String>
	 */
	public List<String> findPermissionsByRoles(@Param("list")List<String> list);
	
	
	/**
	 * @title:queryDefaultRole
	 * @description 查询是否有默认角色
	 * @param list
	 * @return List<Role>
	 */
	public List<Role> queryDefaultRole();
	
	/**
	 * @title:addUserRole
	 * @description 新增用户角色
	 * @param list
	 * @return List<Role>
	 */
	public void addUserRole(@Param("roleId")String roleId,@Param("userId")String userId);
	
	

	/**
	 * @title:deleteUserAndRole
	 * @description 注销角色
	 * @param String
	 * @return void
	 */
	public void deleteUserAndRole(@Param("userId")String userId);
	
	
	public List<Kiin> queryKiinChildren(Kiin kiin);
	
	
}
