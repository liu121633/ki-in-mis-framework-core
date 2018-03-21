package org.kingqueen.kiinmis.model.dao.school;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.kingqueen.kiinmis.model.eaysui.pojo.RequestDatagrid;
import org.kingqueen.kiinmis.model.pojo.School;
import org.kingqueen.kiinmis.model.vo.school.SchoolVo;
import org.kingqueen.kiinmis.model.vo.user.UserVo;

public interface ISchoolDao {
   List<SchoolVo> findSchool(@Param("requestDatagrid")RequestDatagrid requestDatagrid,@Param("schoolVo")SchoolVo schoolVo);//查询所有学校信息
   Integer findSchoolCount(@Param("schoolVo")SchoolVo schoolVo);//查询学校表的总行数
   void addSchool(School school);//新增学校
   School findSchoolByID(@Param("id")String id);//根据学校编号查询学校信息
   void updateSchool(@Param("school")School school);//修改学校
   UserVo findUserById(@Param("id")String id);//根据用户ID查询用户信息
   void logOffSchool(@Param("list")List<String> list);//注销学校
   void cancelLogSchool(@Param("number")String number);//取消注销学校
   School findSchoolByName(@Param("schoolName")String schoolName);//根据学校名称判断是否有相同的学校名称
   List<SchoolVo> findSchoolInfo(@Param("schoolVo")SchoolVo schoolVo);//查询所有学校信息
   void updateStudentBySchoolLog(@Param("list")List<String> list);//通过已被注销的学校，修改学生的备注
}
