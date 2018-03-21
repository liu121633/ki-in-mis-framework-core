package org.kingqueen.kiinmis.model.biz.student;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.kingqueen.kiinmis.model.eaysui.pojo.RequestDatagrid;
import org.kingqueen.kiinmis.model.eaysui.pojo.ResponseDatagrid;
import org.kingqueen.kiinmis.model.vo.student.StudentVo;
import org.kingqueen.kiinmis.model.dao.student.IStudentDao;
import org.kingqueen.kiinmis.model.pojo.Classtime;
import org.kingqueen.kiinmis.model.pojo.Coach;
import org.kingqueen.kiinmis.model.pojo.Grade;
import org.kingqueen.kiinmis.model.pojo.Kiin;
import org.kingqueen.kiinmis.model.pojo.School;
import org.kingqueen.kiinmis.model.pojo.Student;
import org.kingqueen.kiinmis.model.pojo.Teachingrecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.alibaba.fastjson.JSON;

@Service
@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, readOnly = true)
public class StudentBiz {
	@Autowired
	private IStudentDao dao;

	public IStudentDao getDao() {
		return dao;
	}

	public void setDao(IStudentDao dao) {
		this.dao = dao;
	}
	/**
	 * 
	 * @title: findStudent
	 * @description: 查询学生信息
	 * @return
	 */
	
	public ResponseDatagrid findStudent(RequestDatagrid r, String knumber) {
		// 获取查询条件
		ResponseDatagrid responseDatagrid = new ResponseDatagrid();
		StudentVo stu = JSON.parseObject(r.getWhereJson(), StudentVo.class);
		if(stu==null){
			stu=new StudentVo();
			stu.setKiinNumber(knumber);
		}else if(stu.getKiinNumber()==null){
			stu.setKiinNumber(knumber);
			
		}
		// 设置数据
		
			responseDatagrid.setRows(dao.findStudent(stu,r));
		
		responseDatagrid.setTotal(dao.findStudentCount(stu).size());
		return responseDatagrid;
	}
	/**
	 * 
	 * @title: save
	 * @description: 新增学生
	 * @return
	 */
	
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false)
	public Map<String, String> save(Student stu) {
		Map<String, String> message = new HashMap<String, String>();
		if(("0").equals(stu.getSchoolNumber())||("").equals(stu.getSchoolNumber())){
			stu.setSchoolNumber(null);
		}
		if(("0").equals(stu.getStudentCoach())){
			stu.setStudentCoach(null);
		}
		if(("0").equals(stu.getStudentsInTheClass())){
			stu.setStudentsInTheClass(null);
		}
		Kiin k = dao.kiinExists(stu.getTheStudentsAreKiin());
		Grade g = dao.gradeExists(stu.getStudentsInTheClass());
		
		if (k == null) {
			message.put("code", "500");
			message.put("msg", "棋院不存在");
		}
		if (g == null) {
			message.put("code", "500");
			message.put("msg", "班级不存在");
		}
		if (dao.save(stu) >= 1) {
			Teachingrecord tea = new Teachingrecord();
			tea.setCoachNumber(stu.getStudentCoach());
			tea.setStudentNumber(stu.getStudentNumber());
			dao.savechigread(tea);
			message.put("code", "200");
			message.put("msg", "ok");
			return message;
		}
		message.put("code", "500");
		message.put("msg","新增失败");
		return message;
	}
	
	
	/**
	 * 
	 * @title: update
	 * @description: 修改学生
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false)
	public Map<String, String> update(Student stu) {
		Map<String, String> message = new HashMap<String, String>();
		if(("0").equals(stu.getSchoolNumber())||("").equals(stu.getSchoolNumber())){
			stu.setSchoolNumber(null);
		}
		if(("0").equals(stu.getStudentCoach())){
			stu.setStudentCoach(null);
		}
		if(("0").equals(stu.getStudentsInTheClass())){
			stu.setStudentsInTheClass(null);
		}
		Kiin k = dao.kiinExists(stu.getTheStudentsAreKiin());
		Grade g = dao.gradeExists(stu.getStudentsInTheClass());
		
		if (k == null) {
			message.put("code", "500");
			message.put("msg", "棋院不存在");
		}
		if (g == null) {
			message.put("code", "500");
			message.put("msg", "班级不存在");
		}
		if (dao.update(stu) >= 1) {
			Teachingrecord tea = new Teachingrecord();

			dao.updatechigread(stu.getStudentNumber());
			Teachingrecord tea1 = new Teachingrecord();
			tea1.setCoachNumber(stu.getStudentCoach());
			tea1.setStudentNumber(stu.getStudentNumber());
			dao.savechigread(tea1);
			message.put("code", "200");
			message.put("msg", "ok");
			return message;
		}

		message.put("code", "500");
		message.put("msg","新增失败");
		return message;
	}
	/**
	 * 
	 * @title: delete
	 * @description: 删除学生
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false)
	public Integer delete(String id) {
		return dao.delete(id);
	}
	/**
	 * 
	 * @title: queryStudentDetail
	 * @description:查看学生详情
	 * @return
	 */
	public Student queryStudentDetail(String id) {
		return dao.queryStudentDetail(id);
	}
	/**
	 * 
	 * @title: selAll
	 * @description: 查询所有学生信息
	 * @return
	 */
	public List<StudentVo> selAll(StudentVo stu,String knumber) {
		if(stu==null){
			stu=new StudentVo();
			stu.setKiinNumber(knumber);
		}else if(stu.getKiinNumber()==null){
			stu.setKiinNumber(knumber);
			
		}
		
		return dao.selAll(stu);
	}
	
	/**
	* 
	* @title: addKiin 
	* @description: 新增棋院
	* @param: 棋院编号
	* @throws Exception
	 */
	public Kiin findKiinByNumber(String number){
		return dao.findKiinByNumber(number);
	}
	/**
	* 
	* @title: findKiinForClassIfy 
	* @description: 查询棋院信息，分类
	* @throws Exception
	 */
	public List<Kiin> findKiinForClassIfy(String number,String usernumber){
		return dao.findKiinForClassIfy(number,usernumber);
	}
	/**
	 * 
	 * @title: findCoach
	 * @description: 查询棋院下的教练
	 * @return
	 */
	public List<Coach> findCoach(String id){
		List<Coach> list=dao.findCoach1(id);
		return list;
	}
	/**
	 * 
	 * @title: nameExists
	 * @description: 查询学校是否存在
	 * @return
	 */
	public School nameExists(@Param("name") String name){
		return dao.nameExists(name);
		
	}

	
	/**
	 * 
	 * @title: findSchool
	 * @description: 查询学校
	 * @return
	 */
	public List<School> findSchool(){
		List<School> list=dao.findSchool1();
		return list;
		
	}
	/**
	 * 
	 * @title: kiinExists
	 * @description:判断棋院是否存在
	 * @return
	 */
	public Kiin kiinExists(String name){
		return dao.kiinExists(name);
	}
	
	
	/**
	 * 
	 * @title: cocalExists
	 * @description:判断教练是否存在
	 * @return
	 */
	public Coach cocalExists(String name,String kiin){
		return dao.cocalExists(name, kiin);
	}
	
	
	/**
	 * 
	 * @title: cocalExists
	 * @description:判断教练是否存在
	 * @return
	 */
	public Grade gradeExists(String name){
		return dao.gradeExists(name);
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
	
	/**
	 * 
	 * @title: findClass
	 * @description:查询班级
	 * @return
	 */
	public List<Grade> findClass(){
		return dao.findClass();
	}
	
	/**
	 * 
	 * @title: queryTime
	 * @description:查询时间
	 * @return
	 */
	public List<Classtime> queryTime(String id,String rows,String page){
		int currentpage = Integer.parseInt((page == null || page == "0") ? "1": page);//第几页
		int pagesize = Integer.parseInt((rows == null || rows == "0") ? "10": rows);//每页多少行
		
		List list =dao.queryTime(id,(currentpage - 1) * pagesize,pagesize);
			

		return list;
	}
	/**
	 * 
	 * @title: queryTimeCount
	 * @description:查询时间数量
	 * @return
	 */
	public Integer queryTimeCount(@Param("id")String id){
		return dao.queryTimeCount(id);
	}
	
	
	
	
	/**
	 * 
	 * @title: addTime
	 * @description:新增时间
	 * @return
	 */
	public Integer addTime(Classtime time){
		
		return dao.addTime(time);
		
	}
	
	
	/**
	 * 
	 * @title: upTime
	 * @description:修改时间
	 * @return
	 */
	public Integer upTime(Classtime time){
		
		return dao.upTime(time);
	
	}
	
	
	/**
	 * 
	 * @title: addTime
	 * @description:删除时间
	 * @return
	 */
	public Integer deleteTime(String str){
		return dao.deleteTime(str);
	}
	
	/**
	 * 
	 * @title: timeIsCun
	 * @description:查询是否存在
	 * @return
	 */
	public Integer timeIsCun(String whatdayisit,String schooltime,String studentnumber){
		return dao.timeIsCun(whatdayisit, schooltime,studentnumber);
	}
	
	
	/**
	 * 查询今天过生日的用户
	 * 
	 * @author 刘洪君
	 * @return
	 */
	public ResponseDatagrid findTodaysBirthdayStu(
			RequestDatagrid requestDatagrid, String kiinid) {
		ResponseDatagrid responseDatagrid = new ResponseDatagrid();
		// 设置数据
		responseDatagrid.setRows(dao.findTodaysBirthdayStu(requestDatagrid,
				kiinid));

		// 设置行数量
		responseDatagrid.setTotal(dao.findTodaysBirthdayStuCount(kiinid));

		return responseDatagrid;
	}
}
