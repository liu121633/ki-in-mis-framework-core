package org.kingqueen.kiinmis.model.dao.person;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.kingqueen.kiinmis.model.vo.role.RoleVo;
import org.kingqueen.kiinmis.model.vo.user.UserVo;

public interface IPersonDao {
	UserVo findPersonByUserId(@Param("userNumber")String userNumber);//根据用户ID查询用户信息
	List<RoleVo> findRoleByUser(@Param("userNumber")String userNumber);//根据用户ID查询该用户的角色信息
}
