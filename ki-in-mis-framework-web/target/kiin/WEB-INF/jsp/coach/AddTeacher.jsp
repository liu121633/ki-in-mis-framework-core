<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<meta charset="UTF-8">
<script src="<%=request.getAttribute("basePath")%>/static/js/coach/teachercommon.js" type="text/javascript" charset="utf-8"></script>
<style>

</style>
<form id="teacherAdd">
<table  style="width:100%;align:left;font-size: 14px;" border="1" cellpadding="10" cellspacing="3" bordercolor="#EOECFF">
	<tr>
		<th align="center">老师姓名:</th>
		<td>
			<input type="text" style="width:200px;" id="coachName" data-options="required:true" missingMessage="教员姓名不能为空" class="easyui-textbox"/>
		</td>
		<th align="center">所属棋院:</th>
		<td>
			<select class="easyui-combobox" id="theCoachIsKi1" style="width:200px;" data-options="required:true" missingMessage="所属棋院不能为空">  
			</select>  
		</td>
	</tr>
	<tr>
		<th align="center">老师性别:</th>
		<td>
            <select 
								class="easyui-combobox" id="coachSex" id="coachSex" data-options="required:true"
								style="height: 25px; width:200px;" panelHeight="60px">
									<option value="1">男</option>
									<option value="2">女</option>
							</select>
		</td>
		<th align="center">出生日期:</th>
		<td>
			<input class="easyui-datebox" id="coachBirthDate"     
        	data-options="editable:false"   style="width:200px;" >  
		</td>
	</tr>
	<tr>
		<th align="center">身份证号:</th>
		<td>
		<input type="text" class="easyui-textbox"   
data-options="validType:'idcard'" id="trainerIdNumber"  style="width:200px;"/> 
			
		</td>
	
		<th align="center">联系电话</th>
		<td>
			<input type="text" style="width:200px;" id="coachContactPhone" data-options="validType:'phoneRex',required:true" class="easyui-textbox"  missingMessage="联系电话不能为空"/>
		</td>
	</tr>
	
	<tr>
		<th align="center">段位等级:</th>
		<td>
			<input type="number" style="width:200px;" id="coachDanRank" class="easyui-numberspinner" min="1" />
		</td>
		<th align="center">职&nbsp;&nbsp;&nbsp;&nbsp;务:</th>
		<td>
			<input type="text" style="width:200px;" id="coachingPosition" class="easyui-textbox"   >
		</td>
	
	</tr>
	
	<tr>
		<th align="center">家庭住址:</th>
		<td colspan="3">
		<input class="easyui-textbox" data-options="multiline:true"  style="width:550px;height:50px" id="CoachAddress">
		</td>
	</tr>
	
	
	<tr>
		<th align="center">备注:</th>
		<td colspan="3">
		<input class="easyui-textbox" data-options="multiline:true"  style="width:550px;height:100px" id="coachRemarks">
		
		</td>
	</tr>
	
</table>
</form>
<script type="text/javascript">

</script>