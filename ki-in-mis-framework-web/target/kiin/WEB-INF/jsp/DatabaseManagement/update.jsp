<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String basePath = (String) request.getAttribute("basePath");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<form id="update" method="post">
	<table class="table-edit" width="100%" align="rigth"
		style="font-size: 14px;" border="1" cellpadding="10" cellspacing="3"
		bordercolor="#EOECFF">
		<tr>
			<th>文件保存的盘符</th>
			<td><input required="true" class="easyui-textbox"
				id="databaseHost" style="width: 200px" name="backupaddress"
				value="${map.backupaddress}"></td>
		</tr>
		<tr>
			<th>数据库地址</th>
			<td><input required="true" class="easyui-textbox"
				id="databaseHost" style="width: 200px" name="databaseHost"
				value="${map.databaseHost}"></td>
		</tr>
		<tr>
			<th>数据库名称</th>
			<td><input required="true" class="easyui-textbox"
				name="databaseName" style="width: 200px" value="${map.databaseName}"></td>
		</tr>
		<tr>
			<th>数据账号</th>
			<td><input required="true" class="easyui-textbox"
				style="width: 200px" name="databaseUsername"
				value="${map.databaseUsername}"></td>
		</tr>
		<tr>
			<th>数据库密码</th>
			<td><input required="true" class="easyui-textbox"
				style="width: 200px" type="password" name="databasePassword"
				value="${map.databasePassword}"></td>
		</tr>
		<tr>
			<th>自动备份时间</th>
			<td>默认每天0点自动备份</td>
		</tr>
	</table>
</form>

