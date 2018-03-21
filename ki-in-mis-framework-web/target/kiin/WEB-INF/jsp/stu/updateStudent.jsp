<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<meta charset="UTF-8">
<script src="<%=request.getAttribute("basePath")%>/static/js/stu/stuupdate.js"
	type="text/javascript" charset="utf-8"></script>
<div id="tt" class="easyui-tabs" data-options="fit:true">
	<div title="基本信息" style="display: none;">
		<div class="easyui-layout" fit="true">
			
			<div data-options="region:'center'"
				style="background-color: rgb(255, 255, 255);">
				<div id="main">
					<form id="teacherAdd">
					<table class="table-edit2"  style="width:100%;align:left;font-size: 14px;" border="1" cellpadding="3" cellspacing="3" bordercolor="#EOECFF">
						<tr>
							<th align="center" style="width: 25%">学员姓名:</th>
							<th style="width: 25%">
							<input type="hidden" id="studentNumber" value="${d.studentNumber}">
							<input id="studentName2"
								class="easyui-textbox" type="text"
								style="height: 25px; width: 200px" value="${d.studentName}" data-options="required:true" missingMessage="学员姓名不能为空"/></th>
							<th style="height: 35px; width: 25%" align="center";>学员性别:</th>
							<th style="width: 25%"><select 
								class="easyui-combobox" id="studentSex2"
								style="height: 25px; width: 200px" panelHeight="60px">
									<option value="1" <c:if test ="${d.studentSex== 1}">selected</c:if>>男</option>
									<option value="2" <c:if test ="${d.studentSex== 2}">selected</c:if>>女</option>
							</select></th>
						</tr>
						<tr>
							<th style="width: 25%" align="center">出生日期:</th>
							<th style="margin-right: 32px"><input
								class="easyui-datebox" id="studentBirthDate2"
								data-options="editable:false"
								value="<fmt:formatDate value="${d.studentBirthDate}" pattern="yyyy-MM-dd" />" style="height: 25px; width: 200px;">

							</th>
							<th style="height: 35px; width: 25%" align="center";>所在班级:</th>
							<th>
							<select  id="studentsInTheClass2" class="easyui-combobox" style="height: 25px; width: 200px"  data-options="editable:false">
							<c:forEach items="${g}" var="g">
							<option value="${g.gradenumber}" <c:if test ="${d.grade.gradenumber==g.gradenumber}">selected</c:if>>${g.gradename}</option>
							</c:forEach>
							</select>
							</th>
						
						</tr>

						<tr>
							<th style="width: 25%" align="center">就读学校:</th>
							<th style="margin-right: 32px">
							
							 <select  id="schoolNumber2" class="easyui-combobox" style="height: 25px; width: 200px"  data-options="editable:false">
							<c:forEach items="${s}" var="g">
							<option value="${g.schoolNumber}" <c:if test ="${d.sc.schoolNumber==g.schoolNumber}">selected</c:if>>${g.schoolName}</option>
							</c:forEach>
							</select>
							</th>
							<th style="height: 35px; width: 25%" align="center";>联系电话:</th>
							<th style="margin-right: 32px"><input
								id="studentContactPhoneNumber2"  value="${d.studentContactPhoneNumber}" type="text"
								style="height: 25px; width: 200px;" data-options="required:true,validType:'phoneRex'" class="easyui-textbox" missingMessage="联系电话不能为空"/></th>
						</tr>
						<tr>
							<th style="width: 25%" align="center">所属棋院:</th>
							<th><select name="theStudentsAreKiin2" id="theStudentsAreKiin2" data-options="required:true" style="height: 25px; width: 200px;" >
							<option selected="selected" value="${d.kiin.chessNumber}">${d.kiin.kiinName}</option>
							</select>
							 </th>
							<th style="height: 35px; width: 25%" align="center">带班老师:</th>
							<th><select id="studentCoach2" class="easyui-combobox"
								style="height: 25px; width: 200px;"  data-options="editable:false">
								<option selected="selected" value="${d.coach1.coachnumber}">${d.coach1.coachname}</option>
								</select>
								</th>
						</tr>
						<tr>
							<th style="height: 35px; width: 25%" align="center";>家庭住址:</th>
							<th colspan=3>
								 <input class="easyui-textbox" data-options="multiline:true"  style="width:600px;height:50px" id="studentHomeAddress1" value="${d.studentHomeAddress}" >
							</th>
						</tr>
					</table>
					</form>
				</div>
			</div>
		</div>
	</div>
	
	
	<div title="上课时间" style="display: none;">
	 <form id="upfrom"><table id='up'></table>	
	<div id='tc' style="background-color: #EBEBEB"></div>
    <table id='pc' style="width:300px" height="200px"></table>
    </form>
	</div>
	
	<div title="其它信息" style="display: none;">
		<div class="easyui-layout" fit="true">
			<div data-options="region:'center'"
				style="background-color: rgb(255, 255, 255);">
				<div id="main">
					<table class="table-edit2" width="100%" align="center"
						style="font-size: 14px;" border="1" cellpadding="3"
						cellspacing="3" bordercolor="#EOECFF">
						<tr>
							<th style="width: 25%" align="center">父亲姓名：</th>
							<!-- <th ><input id="activity_plan_date" class="Wdate" type="text"  style="height: 25px;width:70%;" /></th>  -->
							<th style=""><input id="nameOfStudentFather2" value="${d.nameOfStudentFather}" class="easyui-textbox"
								type="text" style="height: 25px; width: 200px;" /></th>

							<th style="width: 25%" align="center">父亲电话：</th>
							<!-- <th ><input id="activity_plan_date" class="Wdate" type="text"  style="height: 25px;width:70%;" /></th>  -->
							<th style=""><input id="studentFatherPhone2" class="easyui-textbox" data-options="validType:'phoneRex'"
								type="text" style="height: 25px; width: 200px;" value="${d.studentFatherPhone}"/></th>
						</tr>

						<tr>
							<th style="width: 25%" align="center">母亲姓名：</th>
							<!-- <th ><input id="activity_plan_date" class="Wdate" type="text"  style="height: 25px;width:70%;" /></th>  -->
							<th><input id="nameOfStudentMother2" class="easyui-textbox"
								type="text" style="height: 25px; width: 200px;" value="${d.nameOfStudentMother}"/></th>

							<th style="width: 25%" align="center">母亲电话：</th>
							<!-- <th ><input id="activity_plan_date" class="Wdate" type="text"  style="height: 25px;width:70%;" /></th>  -->
							<th><input id="studentMotherPhone2" class="easyui-textbox" data-options="validType:'phoneRex'"
								type="text" style="height: 25px; width: 200px;" value="${d.studentMotherPhone}"/></th>
						</tr>

						<tr>

							<th style="width: 25%" align="center">备注：</th>
							<!-- <th ><input id="activity_plan_date" class="Wdate" type="text"  style="height: 25px;width:70%;" /></th>  -->
							<th colspan="3"><input class="easyui-textbox" 
							value="${d.studentRemarks}"
							data-options="multiline:true"  style="width:600px;height:200px" id="studentRemarks2"></th>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>