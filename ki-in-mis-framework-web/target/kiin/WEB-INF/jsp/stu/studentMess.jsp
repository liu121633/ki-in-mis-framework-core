<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script src="<%=request.getAttribute("basePath")%>/static/js/stu/stuMess.js"
	type="text/javascript" charset="utf-8"></script>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<meta charset="UTF-8">
<style>
body {
	font-size: 10px;
}

table tr th {
	margin-top: 20%;
}
</style>
<div id="tt" class="easyui-tabs" data-options="fit:true">
	<div title="基本信息" style="display: none;">
		<div class="easyui-layout" fit="true">
		<div data-options="region:'center'"
			style="background-color: rgb(255, 255, 255);">
			<div id="main">
				<table class="table-edit" width="100%" align="left"
					style="font-size: 12px;" border="1" cellpadding="8"
					cellspacing="3" bordercolor="#EOECFF">
					<tr>
						<th style="width: 25%" align="left">学生姓名：</th>
						<!-- <th ><input name="activity_plan_date" class="Wdate" type="text" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true})" style="height: 25px;width:70%;" /></th>  -->
						<td style="width: 25%">${d.studentName}</td>

						<th style="width: 25%" align="left">学校名称：</th>
						<!-- <th ><input name="activity_plan_date" class="Wdate" type="text" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true})" style="height: 25px;width:70%;" /></th>  -->
						<td style="width: 25%">${d.sc.schoolName}</td>
					</tr>

					<tr>
						<th style="width: 12%" align="left">学员性别：</th>

						<td style="">
						<c:if test ="${d.studentSex== 1}">男</c:if>
						<c:if test ="${d.studentSex== 2}">女</c:if>
						</td>

						<th style="width: 12%" align="left">出生日期：</th>

						<td style=""><fmt:formatDate value="${d.studentBirthDate}" pattern="yyyy-MM-dd" /></td>
					</tr>


					<tr>


						

						<th style="width: 12%" align="left">联系电话：</th>

						<td style="">${d.studentContactPhoneNumber}</td>
						
						
						<th style="width: 12%" align="left">状态：</th>

						<td style="">
							<c:if test ="${d.studentStatus==0}">正常</c:if>
							<c:if test ="${d.studentStatus==1}">注销</c:if>
							<c:if test ="${d.studentStatus==2}">未缴费</c:if>
							<c:if test ="${d.studentStatus==3}">欠费</c:if>
							<c:if test ="${d.studentStatus==4}">流失</c:if>
							<c:if test ="${d.studentStatus==5}">休学</c:if>
							
						
						</td>
					</tr>




					<tr>
						<th style="width: 12%" align="left">所属棋院：</th>

						<td style="">${d.kiin.kiinName}</td>
						
						<th style="width: 12%" align="left">带班老师：</th>

						<td style="" colspan="3">${d.coach1.coachname}</td>
						
					</tr>

					<tr>
						<th style="width: 12%" align="left">所在班级：</th>

						<td style="">${d.grade.gradename}</td>
						
						<th style="width: 12%" align="left">入院时间：</th>

						<td style="" colspan="3"><fmt:formatDate value="${d.studentAdmissionTime }" pattern="yyyy-MM-dd" /></td>

					</tr>
					<tr>
						<th style="width: 12%" align="left">家庭住址：</th>

						<td style="" colspan="3">${d.studentHomeAddress}</td>

					</tr>


				</table>
			</div>
		</div>
	</div>
	</div>
	
	<div title="上课时间" style="display: none;">
 	<form id="viewfrom"><table id='bgView'></table>	
	<div id='tc' style="background-color: #EBEBEB"></div>
    <table id='pc' style="width:300px" height="200px"></table>
    </form>
	</div>
	<div title="其它信息" style="display: none;">
		<div class="easyui-layout" fit="true">
		<div data-options="region:'center'"
			style="background-color: rgb(255, 255, 255);">
			<div id="main">
				<table class="table-edit" width="100%" align="left"
					style="font-size: 12px;" border="1" cellpadding="8"
					cellspacing="3" bordercolor="#EOECFF">
					<tr>
						<th style="width: 25%" align="left">父亲姓名：</th>
						<!-- <th ><input name="activity_plan_date" class="Wdate" type="text" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true})" style="height: 25px;width:70%;" /></th>  -->
						<td style="width: 25%">${d.nameOfStudentFather}</td>

						<th style="width: 25%" align="left">父亲电话：</th>
						<!-- <th ><input name="activity_plan_date" class="Wdate" type="text" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true})" style="height: 25px;width:70%;" /></th>  -->
						<td style="width: 25%">${d.studentFatherPhone}</td>
					</tr>

					<tr>
						<th style="width: 12%" align="left">母亲姓名：</th>

						<td style="">${d.nameOfStudentMother}</td>

						<th style="width: 12%" align="left">母亲电话：</th>

						<td style="">${d.studentMotherPhone}</td>
					</tr>


					<tr>


						<th style="width: 12%" align="left">创建用户：</th>

						<td style="">${d.user1.userName}</td>


						<th style="width: 12%" align="left">最后修改用户：</th>

						<td style="">${d.user1.userName}</td>
					</tr>




					<tr>
						<th style="width: 12%" align="left">创建时间：</th>

						<td style="" colspan="3">${d.studentCreationTime}</td>
					</tr>

					<tr>
						<th style="width: 12%" align="left">最后修改时间：</th>

						<td style="" colspan="3">${d.studentFinalModificationTime}</td>


					</tr>
					<tr>
						<th style="width: 12%" align="left">备注:</th>
						<td style="" colspan="3">${d.studentRemarks}</td>
					</tr>


				</table>
			</div>
		</div>
	</div>
	</div>
	<div title="缴费信息" style="display: none;">
	<table id="PaymentDgv">

		</table>
	</div>
	</div>
