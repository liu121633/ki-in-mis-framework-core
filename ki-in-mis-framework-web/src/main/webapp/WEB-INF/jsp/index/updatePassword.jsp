<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<input value = "${user.userPassword}" id = "userPassWord" style = "display:none;"/>
<table style = "width:100%;" border="1" bordercolor="#EOECFF" cellspacing="1" cellpadding="10">
	<tr>
		<th style = "width:25%;">原密码:</th>
		<td style = "width:73%;"><input type="password" id = "oldPass" class = "easyui-textbox" style = "width:100%;"/></td>
	</tr>
	<tr>
		<th style = "width:25%;">新密码:</th>
		<td style = "width:73%;"><input type="password" name="newPass" id = "newPass" class = "easyui-textbox" style = "width:100%;"/></td>
	</tr>
	<tr>
		<th style = "width:30%;">确定密码:</th>
		<td style = "width:73%;"><input type="password" id = "Pass" class = "easyui-textbox" style = "width:100%;"/></td>
	</tr>
</table>