<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script src="<%=request.getAttribute("basePath")%>/static/js/coach/teachercommonupdate.js" type="text/javascript" charset="utf-8"></script>
<meta charset="UTF-8">

<style>

</style>
<form id="teacherUpdate">
<table  style="width:100%;align:left;font-size: 14px;" border="1" cellpadding="10" cellspacing="3" bordercolor="#EOECFF">
	<tr>
		<th align="center">老师姓名:</th>
		<td>
			<input type="hidden" id="coachNumber2" value="${d.coachNumber}">
			<input type="text" style="width:200px;" id="coachName2" value="${d.coachName}" data-options="required:true" missingMessage="教员姓名不能为空" class="easyui-textbox"/>
		</td>
		<th align="center">所属棋院:</th>
		<td>
			<select id="theCoachIsKi2" style="width:200px;" data-options="required:true" missingMessage="所属棋院不能为空">
			<option value="${d.theCoachIsKiNumber}" selected="selected">${d.theCoachIsKiName}</option>   
			</select>
		</td>
	</tr>
	<tr>
		<th align="center">老师性别:</th>
		<td>
		
            <select 
								class="easyui-combobox" id="coachSex2" 
								style="height: 25px; width:200px;" panelHeight="60px" data-options="required:true">
									<option value="1" <c:if test ="${d.coachSex== 1}">selected</c:if>>男</option>
									<option value="2" <c:if test ="${d.coachSex== 2}">selected</c:if>>女</option>
							</select>
		</td>
		<th align="center">出生日期:</th>
		<td>
			<input class="easyui-datebox" id="coachBirthDate2"     
        	data-options="editable:false" style="width:200px;" value="<fmt:formatDate value="${d.coachBirthDate}" pattern="yyyy-MM-dd" />">  
		</td>
	</tr>
	<tr>
		<th align="center">身份证号:</th>
		<td>
		<input type="text" class="easyui-textbox"   
data-options="validType:'idcard'" id="trainerIdNumber2" value="${d.trainerIdNumber}" style="width:200px;"/> 
		</td>
	
		<th align="center">联系电话</th>
		<td>
			<input type="text" style="width:200px;" id="coachContactPhone2" value="${d.coachContactPhone}" data-options="required:true,validType:'phoneRex'" class="easyui-textbox" missingMessage="联系电话不能为空"/>
		</td>
	</tr>
	
	<tr>
		<th align="center">段位等级:</th>
		<td>
			<input class="easyui-numberspinner" style="width:200px;" id="coachDanRank2" value="${d.coachDanRank}"  min="1" />
		</td>
		<th align="center">职务:</th>
		<td>
			<input type="text" style="width:200px;" id="coachingPosition2" value="${d.coachingPosition}" class="easyui-textbox"   >
		</td>
	
	</tr>
	
	<tr>
		<th align="center">家庭住址:</th>
		<td colspan="3">
		<input class="easyui-textbox" data-options="multiline:true"  style="width:550px;height:50px" id="coachAddress2" value="${d.coachHomeAddress}">
		</td>
	</tr>
	
	
	<tr>
		<th align="center">备注:</th>
		<td colspan="3">
		<input class="easyui-textbox" data-options="multiline:true"  value="${d.coachRemarks}" style="width:550px;height:100px" id="coachRemarks2">
		
		</td>
	</tr>
	
</table>
</form>
