package org.kingqueen.kiinmis.model.dao.position;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.kingqueen.kiinmis.model.pojo.Position;
import org.kingqueen.kiinmis.model.vo.position.PositionVo;
/**
 * @ClassName PositionDao
 * @description 职位的Dao接口
 * @author wzq
 * @date 2017年12月2日
 * @version v1.0
 */
public interface PositionDao {
	
	/**
	 * @title:findPositionDatagrid
	 * @description 职位分页查询
	 */
	public List<PositionVo> findPositionDatagrid(@Param("rows")Integer rows,@Param("page")Integer page
			,@Param("sort")String sort,@Param("order")String order,
			@Param("positionId")String positionId,@Param("positionName")String positionName,
			@Param("positionCreateName")String positionCreateName,@Param("positionCreateDateStrat")String positionCreateDateStrat,
			@Param("positionCreateDateEnd")String positionCreateDateEnd,@Param("positionStatus")String positionStatus
			);
	
	/**
	 * @title:findPositionCount
	 * @description 查询数量
	 */
	public Integer findPositionCount(@Param("positionId")String positionId,@Param("positionName")String positionName,
			@Param("positionCreateName")String positionCreateName,@Param("positionCreateDateStrat")String positionCreateDateStrat,
			@Param("positionCreateDateEnd")String positionCreateDateEnd,@Param("positionStatus")String positionStatus);
	
	/**
	 * @title:addPosition
	 * @description dao层新增职位的方法
	 */
	public void addPosition(Position position);
	
	/**
	 * @title:findPositionAll
	 * @description 查询所有
	 */
	public List<Position> findPositionAll();
	/**
	 * @title:findPositionById
	 * @description 按照编号查询职位
	 */
	public List<PositionVo> findPositionById(@Param("positionId")String positionId);

	/**
	 * @title:updateLogout
	 * @description 注销职位
	 */
	public void updateLogout(@Param("porsitionIdArr")String[] porsitionIdArr,@Param("userId")String userNumber);
	
	/**
	 * @title:updatePosition
	 * @description 修改职位
	 */
	public void updatePosition(Position position);
	/**
	 * @title:findPositionVoAll
	 * @description 查询所有放回PositionVo
	 */
	public List<PositionVo> findPositionVoAll();
	
	/**
	 * @title:findPositionSelect
	 * @description 按照多个,id查询PositionVo
	 */
	public List<PositionVo> findPositionSelect(List<PositionVo> list);

	/**
	 * @title:findPositionCondition
	 * @description 按照条件查询PositionVo
	 */
	public List<PositionVo> findPositionCondition(
			@Param("positionId")String positionId,@Param("positionName")String positionName,
			@Param("positionCreateName")String positionCreateName,@Param("positionCreateDateStrat")String positionCreateDateStrat,
			@Param("positionCreateDateEnd")String positionCreateDateEnd,@Param("positionStatus")String positionStatus
			);
	
}
