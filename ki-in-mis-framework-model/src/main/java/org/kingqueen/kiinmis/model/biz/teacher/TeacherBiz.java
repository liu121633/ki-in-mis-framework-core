package org.kingqueen.kiinmis.model.biz.teacher;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kingqueen.kiinmis.common.vo.TeacherVo;
import org.kingqueen.kiinmis.model.dao.teacher.ITeacherDao;
import org.kingqueen.kiinmis.model.pojo.Coach;
import org.kingqueen.kiinmis.model.pojo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.kingqueen.kiinmis.model.eaysui.pojo.RequestDatagrid;
import org.kingqueen.kiinmis.model.eaysui.pojo.ResponseDatagrid;

import com.alibaba.fastjson.JSON;

@Service
@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, readOnly = true)
public class TeacherBiz {
	@Autowired
	private ITeacherDao dao;

	public ITeacherDao getDao() {
		return dao;
	}

	public void setDao(ITeacherDao dao) {
		this.dao = dao;
	}
	/**
	 * 
	 * @title: findTeacher
	 * @description:查询
	 * @param: Model对象
	 * @throws Exception
	 */
	public ResponseDatagrid findTeacher(RequestDatagrid r,String knumber){
		//获取查询条件
		ResponseDatagrid responseDatagrid=new ResponseDatagrid();
		TeacherVo tea=JSON.parseObject(r.getWhereJson(),TeacherVo.class);
		if(tea==null){
			tea=new TeacherVo();
			tea.setTheCoachIsKiNumber(knumber);
		}else if(tea.getTheCoachIsKiNumber()==null){
			tea.setTheCoachIsKiNumber(knumber);
			
		}
		//设置数据
		responseDatagrid.setRows(dao.findTeacher(tea, r));
		responseDatagrid.setTotal(dao.findTeacherCount(tea));
		return responseDatagrid;
	}
	/**
	 * 
	 * @title: save
	 * @description:新增
	 * @param: Model对象
	 * @throws Exception
	 */
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false)
	public Integer save(Coach c){
		return dao.save(c);
	}
	/**
	 * 
	 * @title: update
	 * @description:修改
	 * @param: Model对象
	 * @throws Exception
	 */
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false)
	public Integer update(Coach c){
		return dao.update(c);
	}
	
	/**
	 * 
	 * @title: delete
	 * @description:离职
	 * @param: Model对象
	 * @throws Exception
	 */
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false)
	public Integer delete(String id){
		return dao.delete(id);
	}
	/**
	 * 
	 * @title: findCoachDetail
	 * @description:查看教练详情
	 * @param: Model对象
	 * @throws Exception
	 */
	public TeacherVo findCoachDetail(String id){
		return dao.findTeacherDetail(id);
	}
	/**
	 * 
	 * @title: findStudent
	 * @description:查询学生
	 * @param: Model对象
	 * @throws Exception
	 */
	public List<Student> findStudent(String id){
		return dao.findStudent(id);
	}
	/**
	 * 
	 * @title: selAll
	 * @description:查询全部教练信息
	 * @param: Model对象
	 * @throws Exception
	 */
	public List<TeacherVo> selAll(TeacherVo tea,String knumber){
		if(tea==null){
			tea=new TeacherVo();
			tea.setTheCoachIsKiNumber(knumber);
		}else if(tea.getTheCoachIsKiNumber()==null){
			tea.setTheCoachIsKiNumber(knumber);
			
		}
		return dao.selAll(tea);
	}
	
	/**
	* 
	* @title: findUserById 
	* @description: 取消注销
	* @param: ID
	* @throws Exception
	 */
	public Map<String, Object> cancelLog(String id){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			dao.cancelLog(id);
			map.put("code", 200);
		} catch (Exception e) {
			map.put("code", 500);
		}
		return map;
	}
}
