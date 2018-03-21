package org.kingqueen.kiinmis.model.dao.student;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.kingqueen.kiinmis.model.pojo.Classtime;
import org.kingqueen.kiinmis.model.pojo.Coach;
import org.kingqueen.kiinmis.model.pojo.Grade;
import org.kingqueen.kiinmis.model.pojo.Kiin;
import org.kingqueen.kiinmis.model.pojo.School;
import org.kingqueen.kiinmis.model.pojo.Student;
import org.kingqueen.kiinmis.model.pojo.Teachingrecord;
import org.kingqueen.kiinmis.model.vo.student.StudentVo;
import org.kingqueen.kiinmis.model.eaysui.pojo.RequestDatagrid;
import org.kingqueen.kiinmis.model.eaysui.pojo.ResponseDatagrid;

/**
 * 
 * @author Administrator
 * 
 */
public interface IStudentDao {
	/**
	* 
	* @title: findKiinForClassIfy 
	* @description: 查询棋院信息，加上用户
	* @throws Exception
	 */
	List<Kiin> findKiinForClassIfy(@Param("number")String number,@Param("usernumber")String usernumber);//查询棋院，分类
	/**
	 * 
	 * @title: queryPerson
	 * @description: 查询棋院
	 * @return
	 */
	
	Kiin findKiinByNumber(@Param("number")String number);//根据棋院查询棋院信息
	
	/**
	 * 
	 * @title: findCoach
	 * @description: 查询棋院下的教练
	 * @return
	 */
	public List<Coach> findCoach1(@Param("id")String id);
	
	/**
	 * 
	 * @title: findSchool
	 * @description: 查询学校
	 * @return
	 */
	public List<School> findSchool1();
	/**
	 * 
	 * @title: findStudent
	 * @description:按条件查询学员信息,分页(学号,姓名,状态,棋院,教练,学校,性别,出生日期,升降)
	 * @return
	 */
	public List<StudentVo> findStudent(@Param("stu")StudentVo stu,@Param("r")RequestDatagrid r);
	
	/**
	 * 
	 * @title: findStudentCount
	 * @description:按条件查询学员信息总条数,分页(学号,姓名,状态,棋院,教练,学校,性别,出生日期,升降)
	 * @return
	 */
	public List<Integer> findStudentCount(@Param("stu")StudentVo stu);
	
	
	/**
	 * 
	 * @title: save
	 * @description:新增学生信息
	 * @return
	 */
	public Integer save(Student stu);
	
	
	
	
	/**
	 * 
	 * @title: save
	 * @description:同时新增中间表
	 * @return
	 */
	public Integer savechigread(Teachingrecord tea);
	
	/**
	 * 
	 * @title: update
	 * @description:修改学生信息
	 * @return
	 */
	public Integer update(Student stu);
	
	/**
	 * 
	 * @title: save
	 * @description:同时修改中间表
	 * @return
	 */
	public Integer updatechigread(String id);
	
	
	public Integer findIsCun(@Param("stuId")String stuId,@Param("coaldId")String coaldId);
	/**
	 * 
	 * @title: save
	 * @description:删除学生
	 * @return
	 */
	public Integer delete(String id);
	
	
	
	/**
	 * 
	 * @title: save
	 * @description:查看学生信息
	 * @return
	 */
	public Student queryStudentDetail(String id);
		
	
	/**
	 * 查询学生所有信息
	 * 
	 */
	public List<StudentVo> selAll(@Param("stu") StudentVo stu);
	
	/**
	 * 
	 * @title: nameExists
	 * @description:判断学校是否存在
	 * @return
	 */
	public School nameExists(@Param("name") String name);
	
	
	/**
	 * 
	 * @title: kiinExists
	 * @description:判断棋院是否存在
	 * @return
	 */
	public Kiin kiinExists(@Param("name") String name);
	
	
	/**
	 * 
	 * @title: cocalExists
	 * @description:判断教练是否存在
	 * @return
	 */
	public Coach cocalExists(@Param("name") String name,@Param("kiin") String kiin);
	
	
	/**
	 * 
	 * @title: gradeExists
	 * @description:判断班级是否存在
	 * @return
	 */
	public Grade gradeExists(String name);
	/**
	 * 
	 * @title: cancelLog
	 * @description:取消注销
	 * @return
	 */
	void cancelLog(@Param("id")String id);
	
	/**
	 * 
	 * @title: findClass
	 * @description:查询班级
	 * @return
	 */
	List<Grade> findClass();
	
	/**
	 * 
	 * @title: queryTime
	 * @description:查询时间
	 * @return
	 */
	public List<Classtime> queryTime(@Param("id")String id,@Param("first")Integer first,@Param("size")Integer size);
	
	/**
	 * 
	 * @title: queryTime
	 * @description:查询时间数量
	 * @return
	 */
	public Integer queryTimeCount(@Param("id")String id);
	
	/**
	 * 
	 * @title: addTime
	 * @description:新增时间
	 * @return
	 */
	public Integer addTime(Classtime time);
	
	
	/**
	 * 
	 * @title: upTime
	 * @description:修改时间
	 * @return
	 */
	public Integer upTime(Classtime time);
	
	/**
	 * 
	 * @title: addTime
	 * @description:删除时间
	 * @return
	 */
	public Integer deleteTime(String str);
	
	/**
	 * 
	 * @title: timeIsCun
	 * @description:查询是否存在
	 * @return
	 */
	public Integer timeIsCun(@Param("whatdayisit")String whatdayisit,@Param("schooltime")String schooltime,@Param("studentnumber")String studentnumber);


	/**
	 * 查询今天过生日的用户
	 * 
	 * @author 刘洪君
	 * @return
	 */
	public List<StudentVo> findTodaysBirthdayStu(
			@Param("r") RequestDatagrid requestDatagrid,
			@Param("userid") String kiinid);

	/**
	 * 查询今天过生日的用户数量
	 * 
	 * @author 刘洪君
	 * @return
	 */
	public int findTodaysBirthdayStuCount(@Param("userid") String kiinid);


}
