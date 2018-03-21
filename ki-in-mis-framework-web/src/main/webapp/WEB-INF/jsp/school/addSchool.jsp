<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="easyui-layout" fit = "true">
<form id = "addSchool_form" method = "post">
<input name = "schoolNumber" value = "${school.schoolNumber}" style = "display:none;"/>
		<table id = "addSchool_table" border="1" bordercolor="#EOECFF" cellspacing="1" cellpadding="10" data-options="region:'center'" width="100%">
			<tr>
				<th style = "width:100px;" align = "center">
					<span>学校名称:</span>
				</th>
				<td style = "width:220px;">
					<input data-options = "required:true" style = "width:100%;" class = "easyui-textbox" value = "${school.schoolName}" name = "schoolName" placeholder="学校名称"/>
				</td>
			</tr>
			<tr>
				<th align = "center">
					<span>学校地址:</span>
				</th>
				<td>
					<input data-options = "required:true,multiline:true" class = "easyui-textbox" name = "schoolAddress" placeholder="学校地址" style = "height:50px;width:100%;" value = "${school.schoolAddress}">
				</td>
			</tr>
			<tr>
				<th align = "center">
					<span>备注:</span>
				</th>
				<td>
					<input class = "easyui-textbox" name = "schoolNotes" style= "width:100%;height:100px;"placeholder="备注" value = "${school.schoolNotes}">
				</td>
			</tr>
		</table>
</form>
	</div>