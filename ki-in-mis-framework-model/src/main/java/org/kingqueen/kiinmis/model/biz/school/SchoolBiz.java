package org.kingqueen.kiinmis.model.biz.school;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.kingqueen.kiinmis.common.Common;
import org.kingqueen.kiinmis.model.dao.school.ISchoolDao;
import org.kingqueen.kiinmis.model.eaysui.pojo.RequestDatagrid;
import org.kingqueen.kiinmis.model.eaysui.pojo.ResponseDatagrid;
import org.kingqueen.kiinmis.model.pojo.School;
import org.kingqueen.kiinmis.model.pojo.User;
import org.kingqueen.kiinmis.model.vo.school.SchoolVo;
import org.kingqueen.kiinmis.model.vo.user.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;

@Service
@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, readOnly = true)
/**
 * 
  * @ClassName SchoolBiz
  * @description 对学校的相关处理的业务逻辑层
  * @author 王晓妍
  * @date 2017年12月1日
  * @version V1.0
 */
public class SchoolBiz {
	@Autowired
	private ISchoolDao ischoolDao;
	/**
	* 
	* @title: findSchool 
	* @description: 按时间倒序查询学校信息
	* @throws Exception
	 */
	public ResponseDatagrid findSchool(RequestDatagrid requestDatagrid){
		ResponseDatagrid responseDatagrid = new ResponseDatagrid();
		//取得查询条件对象
		SchoolVo schoolVo = JSON.parseObject(requestDatagrid.getWhereJson(), SchoolVo.class);
		//设置数据
		responseDatagrid.setRows(ischoolDao.findSchool(requestDatagrid, schoolVo));
		//设置总行数
		responseDatagrid.setTotal(ischoolDao.findSchoolCount(schoolVo));
		ResponseDatagrid r = new ResponseDatagrid();
		r.setTotal(ischoolDao.findSchoolCount(schoolVo));
		List<SchoolVo> list = new ArrayList<SchoolVo>();
		ischoolDao.findSchool(requestDatagrid, schoolVo);
		for(Object s:ischoolDao.findSchool(requestDatagrid, schoolVo)){
			if (s instanceof SchoolVo) {
				SchoolVo sc = new SchoolVo();
				SchoolVo v = (SchoolVo)s;
				UserVo u = ischoolDao.findUserById(v.getSchoolCreateUserName());
				if (u == null) {
					u = new UserVo();
				}
				sc.setSchoolCreateUserName(u.getUserName());//设置创建人
				UserVo u1 = ischoolDao.findUserById(v.getSchoolFinallyModifiesUserName());
				if (u1 == null) {
					u1 = new UserVo();
				}
				sc.setSchoolFinallyModifiesUserName(u1.getUserName());//设置最后修改对象
				sc.setLastRevisionTimeOfSchool(v.getLastRevisionTimeOfSchool());//设置最后修改时间
				sc.setSchoolAddress(v.getSchoolAddress());//设置学校地址
				sc.setSchoolCreationTime(v.getSchoolCreationTime());//设置创建时间
				sc.setSchoolName(v.getSchoolName());//设置学校名称
				sc.setSchoolNotes(v.getSchoolNotes());//设置学校备注
				sc.setSchoolNumber(v.getSchoolNumber());//设置学校编号
				sc.setSchoolState(v.getSchoolState());//设置学校状态
				sc.setSchoolCreateUser(v.getSchoolCreateUser());
				list.add(sc);
			}
		}
		r.setRows(list);
		return r;
	}
	/**
	* 
	* @title: findSchoolCount 
	* @description: 查询学校表的总行数
	* @throws Exception
	 */
	public Integer findSchoolCount(@Param("s")SchoolVo schoolVo){
		return ischoolDao.findSchoolCount(schoolVo);
	}
	/**
	* 
	* @title: addSchool 
	* @description: 新增学校
	* @throws Exception
	 */
	public Map<String, Object> addSchool(School school,User user){
		Map<String, Object> map = new HashMap<String ,Object>();
		try {
			school.setSchoolState(0);//设置学校状态
			school.setSchoolNumber(Common.getSchoolNumber());//设置学校编号
			school.setSchoolCreationTime(Common.getNow());//设置创建时间
			school.setSchoolCreateUserName(user.getUserNumber());//设置创建用户
			ischoolDao.addSchool(school);//新增学校
			map.put("code", 200);//新增成功，返回200
		} catch (Exception e) {
			map.put("code", 500);//新增失败，返回500
		}
		return map;
	}
	/**
	* 
	* @title: findSchoolByID 
	* @description: 根据ID查询学校信息
	* @throws Exception
	 */
	public School findSchoolByID(String id){
		return ischoolDao.findSchoolByID(id);
	}
	/**
	* 
	* @title: updateSchool 
	* @description: 修改学校信息
	* @throws Exception
	 */
	public Map<String, Object> updateSchool(School school,User user){
		Map<String, Object> map = new HashMap<String ,Object>();
		try {
			school.setSchoolState(0);//设置学校状态
			school.setLastRevisionTimeOfSchool(Common.getNow());
			school.setSchoolFinallyModifiesUserName(user.getUserNumber());
			ischoolDao.updateSchool(school);
			map.put("code", 200);//新增成功，返回200
		} catch (Exception e) {
			map.put("code", 500);//新增失败，返回500
		}
		return map;
	}
	/**
	* 
	* @title: findUserById 
	* @description: 根据用户ID查询用户信息
	* @throws Exception
	 */
	public UserVo findUserById(String id){
		return ischoolDao.findUserById(id);
	}
	/**
	* 
	* @title: logOffSchool 
	* @description: 注销学校
	* @throws Exception
	 */
	public Map<String, Object> logOffSchool(String id){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<String> list = new ArrayList<String>();
			//循环从前台传过来的编号，进行注销
			for (int i = 0; i < id.split(",").length; i++) {
				list.add(id.split(",")[i]);
			}
			ischoolDao.logOffSchool(list);
			ischoolDao.updateStudentBySchoolLog(list);
			map.put("code", 200);
		} catch (Exception e) {
			map.put("code", 500);
		}
		return map;
	}
	/**
	* 
	* @title: cancelLogSchool
	* @description: 取消注销学校
	* @throws Exception
	 */
	public Map<String, Object> cancelLogSchool(String number){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			//获取学校编号，取消注销
			ischoolDao.cancelLogSchool(number);
			map.put("code", 200);
		} catch (Exception e) {
			map.put("code", 500);
		}
		return map;
	}
	
	/**
	* 
	* @title: findSchoolByName
	* @description: 取消注销学校
	* @throws Exception
	 */
	public School findSchoolByName(String schoolName){
		return ischoolDao.findSchoolByName(schoolName);
	}
	
	/**
	* 
	* @title: findSchoolInfo
	* @description: 查询学校信息
	* @throws Exception
	 */
	public List<SchoolVo> findSchoolInfo(SchoolVo schoolVo){
		return ischoolDao.findSchoolInfo(schoolVo);
	}
	
}
