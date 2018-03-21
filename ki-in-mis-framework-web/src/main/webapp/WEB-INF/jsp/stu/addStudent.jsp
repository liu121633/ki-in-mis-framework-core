<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<meta charset="UTF-8">

<script src="<%=request.getAttribute("basePath")%>/static/js/stu/stuadd.js"
	type="text/javascript" charset="utf-8"></script>
<style>
#gradeTime .datagrid-toolbar, .datagrid-pager {
     background:#E0ECFF;
}


</style>
<div id="tt" class="easyui-tabs" data-options="fit:true">
	<div title="基本信息" style="display: none;">
		<div class="easyui-layout" fit="true">
			
			<div data-options="region:'center'"
				style="background-color: rgb(255, 255, 255);">
				<div id="main">
					<form id="teacherAdd">
					<table class="table-edit" style="width:100%;align:left;font-size: 12px;" border="1" cellpadding="3" cellspacing="3" bordercolor="#EOECFF">
						<tr>
							<th align="center" style="width:25%">姓名:</th>
							<th><input id="studentName1"
								class="easyui-textbox" type="text"
								style="height: 25px; width: 200px" data-options="required:true" missingMessage="学员姓名不能为空"/></th>
							<th style="height: 35px; width: 25%" align="center";>性别:</th>
							<th><select 
								class="easyui-combobox" id="studentSex1"
								style="height: 25px; width: 200px" panelHeight="60px">
									<option value="1">男</option>
									<option value="2">女</option>
							</select></th>
						</tr>
						<tr>
							<th align="center">出生日期:</th>
							<th style="margin-right: 32px"><input
								class="easyui-datebox" id="studentBirthDate"
								data-options="editable:false"
								style="height: 25px; width: 200px;">

							</th>
							<th style="height: 35px; width: 12%" align="center";>所在班级:</th>
							<th><input id="studentsInTheClass1" 
								style="height: 25px; width: 200px;" data-options="required:true" /></th>
						</tr>

						<tr>
							<th style="width: 25%" align="center">就读学校:</th>
							<th style="margin-right: 32px">
							<select id="schoolNumber1" style="height: 25px; width: 200px;" 	data-options="editable:false">
							 </select> 
							</th>
							<th style="height: 35px; width: 12%" align="center";>联系电话:</th>
							<th style="margin-right: 32px"><input
								id="studentContactPhoneNumber1" type="text"
								style="height: 25px; width: 200px;" data-options="required:true,validType:'phoneRex'" class="easyui-textbox" missingMessage="联系电话不能为空"/></th>
						</tr>
						<tr>
							<th style="width: 25%" align="center">所属棋院:</th>
							<th><input name="theStudentsAreKiin" id="theStudentsAreKiin" data-options="required:true" style="height: 25px; width: 200px;">  </th>
						
							
								<th style="height: 35px; width: 12%" align="center";>带班老师:</th>
							<th><select  class="easyui-combobox" id="studentCoach"
								style="height: 25px; width: 200px;" 	data-options="editable:false">
								
							</select></th>
						</tr>
						<tr>
							<th style="height: 35px; width: 12%" align="center";>家庭住址:</th>
							<th colspan=3>
									<input class="easyui-textbox" data-options="multiline:true"  style="width:600px;height:50px" id="studentHomeAddress">
							</th>
						</tr>
					</table>
					</form>
				</div>
			</div>
		</div>
	</div>
	
	<div title="上课时间" style="display: none;" id="gradeTime">
	 <form id="bgfrom">
	 <table id='bg'></table>	
    </form>
	</div>
	<div title="其它信息" style="display: none;">
		<div class="easyui-layout" fit="true">
			<div data-options="region:'center'"
				style="background-color: rgb(255, 255, 255);">
				<div id="main">
					<table class="table-edit" width="100%" align="center"
						style="font-size: 12px;" border="1" cellpadding="10"
						cellspacing="3" bordercolor="#EOECFF">
						<tr>
							<th style="width: 25%" align="center">父亲姓名：</th>
						
							<th style=""><input id="nameOfStudentFather" class="easyui-textbox"
								type="text" style="height: 25px; width: 200px;" /></th>

							<th style="width: 25%" align="center">父亲电话：</th>
						
							<th style=""><input id="studentFatherPhone" class="easyui-textbox" data-options="validType:'phoneRex'"
								type="text" style="height: 25px; width: 200px;" /></th>
						</tr>

						<tr>
							<th style="width: 25%" align="center">母亲姓名：</th>
						
							<th><input id="nameOfStudentMother" class="easyui-textbox"
								type="text" style="height: 25px; width: 200px;" /></th>

							<th style="width: 25%" align="center">母亲电话：</th>
						
							<th><input id="studentMotherPhone" class="easyui-textbox" data-options="validType:'phoneRex'"
								type="text" style="height: 25px; width: 200px;" /></th>
						</tr>

						<tr>

							<th style="width: 25%" align="center">备注：</th>
						
							<th colspan="3"><input class="easyui-textbox" data-options="multiline:true"  style="width:600px;height:100px" id="studentRemarks"></th>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>