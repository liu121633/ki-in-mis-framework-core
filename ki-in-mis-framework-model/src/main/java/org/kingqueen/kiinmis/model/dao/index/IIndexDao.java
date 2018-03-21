package org.kingqueen.kiinmis.model.dao.index;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.kingqueen.kiinmis.model.eaysui.pojo.Tree;
import org.kingqueen.kiinmis.model.pojo.Home;
import org.kingqueen.kiinmis.model.pojo.Kiin;
import org.kingqueen.kiinmis.model.pojo.User;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.Mapping;

/**
 * @ClassName IndexDao
 * @description 功能描述
 * @author 刘洪君
 * @date 2017年12月9日下午3:31:50
 * @version V1.0
 */
public interface IIndexDao {

	public User login(@Param("loginName") String LoginName,
			@Param("pwd") String pwd);

	/**
	 * @title: 取得用户管理的棋院
	 * @description: 功能描述
	 * @throws Exception
	 * @return List<String>
	 */
	public List<String> findUserManagementKiin(@Param("user") User user);

	/**
	 * @title: 取得下级棋院
	 * @description: 功能描述
	 * @throws Exception
	 * @return List<Kiin>
	 */
	public List<Tree> findJuniorKiin(@Param("id") String id);

	/**
	 * @title: 修改密码
	 * @description: 功能描述
	 * @throws Exception
	 */
	public void updatePassWord(@Param("newPass") String newPass,
			@Param("userNumber") String userNumber);

	/**
	 * 用戶修改常用功能
	 * 
	 * @param homes
	 * @return
	 */
	public int saveSetting(@Param("homes") List<Home> homes,
			@Param("userid") String userid);

	public int delSaveSetting(@Param("userid") String userid);
	
	
	public List<Home> findHome(@Param("userid") String userid);

}
