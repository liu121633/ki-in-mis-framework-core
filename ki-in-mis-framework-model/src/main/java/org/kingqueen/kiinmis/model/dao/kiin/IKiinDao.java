package org.kingqueen.kiinmis.model.dao.kiin;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.kingqueen.kiinmis.model.eaysui.pojo.RequestDatagrid;
import org.kingqueen.kiinmis.model.eaysui.pojo.Tree;
import org.kingqueen.kiinmis.model.pojo.Kiin;
import org.kingqueen.kiinmis.model.vo.kiin.KiinVo;
import org.kingqueen.kiinmis.model.vo.user.UserVo;

/**
 * 
 * @ClassName IKiinDao
 * @description 对棋院的相关处理的数据访问层
 * @author 王晓妍
 * @date 2017年12月3日
 * @version V1.0
 */
public interface IKiinDao {
	List<Kiin> findKiinForClassIfy(@Param("number") String number);// 查询棋院，分类

	Integer findKiinCount(@Param("kiinVo") KiinVo kiinVo,@Param("chessNumber")String chessNumber);// 查询棋院的总行数

	List<KiinVo> findKiin(
			@Param("requestDatagrid") RequestDatagrid requestDatagrid,
			@Param("kiinVo") KiinVo kiinVo,@Param("chessNumber")String chessNumber);// 分页查询棋院信息

	void addKiin(Kiin kiin);// 新增棋院

	void updateKiin(Kiin kiin);// 修改棋院

	Kiin findKiinByNumber(@Param("number") String number);// 根据棋院查询棋院信息

	void logOffKiin(@Param("list") List<String> list);// 注销棋院

	UserVo findUserById(@Param("id") String id);// 根据用户ID查询用户信息

	void cancelLog(@Param("number") String number);// 取消注销

	List<KiinVo> findKiinInfo(@Param("kiinVo") KiinVo kiinVo);// 根据导出的要求查询导出的数据

	List<KiinVo> findNextKiinByNumber(@Param("level") Integer level,
			@Param("id") String id, @Param("number") String number);// 根据登录对象的棋院等级查询该棋院下的所有棋院

	Integer findMaxLevel(@Param("number") String number);// 查询登录的用户所属棋院的最高等级的棋院

	List<KiinVo> findKiinByUser(@Param("number") String number);// 查询登录对象的所属棋院

	Integer findMinLevel(@Param("number") String number);// 查询登录的用户所属棋院的最低等级的棋院

	List<KiinVo> findKiinByLevel(@Param("level") Integer level);// 根据棋院等级查询棋院信息

	List<Tree> findUserTreeKinn(@Param("id") String id);//查找用户管理的棋院树
	
	Integer judgeHaveLower(@Param("kiinNumber")String kiinNumber);//判断该棋院下是否还有子棋院
	
	Integer findKiinVoByUserNumber(@Param("userNumber")String userNumber);//根据登录的用户查询该用户所属棋院
}
