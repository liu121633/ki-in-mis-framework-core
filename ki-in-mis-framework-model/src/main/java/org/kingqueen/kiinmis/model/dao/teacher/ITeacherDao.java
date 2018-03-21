package org.kingqueen.kiinmis.model.dao.teacher;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.kingqueen.kiinmis.common.vo.TeacherVo;
import org.kingqueen.kiinmis.model.eaysui.pojo.RequestDatagrid;
import org.kingqueen.kiinmis.model.pojo.Coach;
import org.kingqueen.kiinmis.model.pojo.Student;

public interface ITeacherDao {
	/**
	 * 
	 * @title: queryPerson
	 * @description: 查询棋院
	 * @return
	 */
	
	
	
	
	/**
	 * 
	 * @title: findTeacher
	 * @description:按条件查询教师信息,分页
	 * @return
	 */
	public List<TeacherVo> findTeacher(@Param("tea")TeacherVo tea,@Param("r")RequestDatagrid r);
	
	/**
	 * 
	 * @title:  findTeacherCount
	 * @description:按条件查询教师信息总条数
	 * @return
	 */
	public Integer  findTeacherCount(@Param("tea")TeacherVo tea);
	
	
	/**
	 * 
	 * @title: save
	 * @description:新增教员信息
	 * @return
	 */
	public Integer save(Coach c);
	
	
	/**
	 * 
	 * @title: update
	 * @description:修改教员信息
	 * @return
	 */
	public Integer update(Coach c);
	
	
	
	/**
	 * 
	 * @title: save
	 * @description:删除教员
	 * @return
	 */
	public Integer delete(String id);
	
	
	/**
	 * 
	 * @title: findTeacherDetail
	 * @description:查询教员详情
	 * @return
	 */
	public TeacherVo findTeacherDetail(String id);
	
	/**
	 * 
	 * @title: findTeacherDetail
	 * @description:查询教员所带学生 
	 * @return
	 */
	public List<Student> findStudent(String id);
	
	/**
	 * 
	 * @title: findTeacherDetail
	 * @description:查询所有教员
	 * @return
	 */
	public List<TeacherVo> selAll(@Param("tea")TeacherVo tea);
	
	
	/**
	 * 
	 * @title: cancelLog
	 * @description:取消注销
	 * @return
	 */
	void cancelLog(@Param("id")String id);
}
