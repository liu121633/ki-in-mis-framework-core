package org.kingqueen.kiinmis.model.biz.position;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.kingqueen.kiinmis.model.dao.position.PositionDao;
import org.kingqueen.kiinmis.model.dao.user.UserDao;
import org.kingqueen.kiinmis.model.pojo.Position;
import org.kingqueen.kiinmis.model.vo.position.PositionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("PositionBiz")
@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, readOnly = true)
/**
 * @ClassName PositionBiz
 * @description 职位的逻辑判断类
 * @author wzq
 * @date 2017年12月2日
 * @version v1.0
 */
public class PositionBiz {

	@Autowired
	private PositionDao positionDao;
	
	/**
	 * @title:findPositionDatagrid
	 * @description	分页查询
	 */
	public Map<String, Object> findPositionDatagrid(Integer rows,Integer page,String sort,String order,
			String positionId,String positionName,
			String positionCreateName,String positionCreateDateStrat,
			String positionCreateDateEnd,String positionStatus
			){
		//起始页数
		Integer firstPage = (page-1)*rows;
		List<PositionVo> list = positionDao.findPositionDatagrid(rows,firstPage,sort,order,
				positionId,positionName,
				positionCreateName,positionCreateDateStrat,
				positionCreateDateEnd,positionStatus
				); 
		Integer count = positionDao.findPositionCount(positionId, positionName, positionCreateName, positionCreateDateStrat, positionCreateDateEnd, positionStatus);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("total", count);
		map.put("rows", list);
		return map;
	}
	
	/**
	 * @title:addPosition
	 * @description biz层新增职位的方法
	 */
	public Map<String,Object> addPosition(Position position){
		//查询所有职位
		List<Position> list = positionDao.findPositionAll();
		Map<String,Object> map = new HashMap<String,Object>();
		for (Position position2 : list) {
			if(position2.getPositionName().equals(position.getPositionName())){
				map.put("success",500);
				map.put("message","职位名称重复了!");
				return map;
			}
		}
		map.put("success",200);
		map.put("message","新增成功！");
		positionDao.addPosition(position);
		return map;
	}
	
	/**
	 * @title:findPositionById
	 * @description 按照编号查询职位
	 */
	public PositionVo findPositionById(String positionId){
		List<PositionVo> list = positionDao.findPositionById(positionId);
		if(list==null||list.size()==0){
			return null;
		}
		return list.get(0);
	}
	
	/**
	 * @title:updateLogout
	 * @description 注销职位
	 */
	public void updateLogout(String[] porsitionIdArr,String userNumber){
		positionDao.updateLogout(porsitionIdArr,userNumber);
	};
	
	/**
	 * @title:updatePosition
	 * @description 修改职位
	 */
	public Map<String,Object> updatePosition(Position position){
		List<Position> list = positionDao.findPositionAll();
		Map<String,Object> map = new HashMap<String,Object>();
		for (Position position2 : list) {
			//自己的名称可以重复
			if(!position2.getPositionNumber().equals(position.getPositionNumber())){
				if(position2.getPositionName().equals(position.getPositionName())){
					map.put("success",500);
					map.put("message","职位名称重复了!");
					return map;
				}
			}
		}
		map.put("success",200);
		map.put("message","修改成功！");
		positionDao.updatePosition(position);
		return map;
	}
	
	/**
	 * @title:findPositionVoAll
	 * @description 查询所有放回PositionVo
	 */
	public List<PositionVo> findPositionVoAll(){
		return positionDao.findPositionVoAll();
	}
	/**
	 * @title:findPositionSelect
	 * @description 按照多个,id查询PositionVo
	 */
	public List<PositionVo> findPositionSelect(List<PositionVo> list){
		return positionDao.findPositionSelect(list);
	}
	/**
	 * @title:findPositionCondition
	 * @description 按照条件查询PositionVo
	 */
	public List<PositionVo> findPositionCondition(
			String positionId,String positionName,
			String positionCreateName,String positionCreateDateStrat,
			String positionCreateDateEnd,String positionStatus
			){
		return positionDao.findPositionCondition(positionId, positionName, positionCreateName, positionCreateDateStrat, positionCreateDateEnd, positionStatus);
	}
}
