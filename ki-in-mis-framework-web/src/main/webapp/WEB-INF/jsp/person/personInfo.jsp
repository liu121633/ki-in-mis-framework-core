<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>显示用户信息</title>
</head>
<body>
<table style = "height:100%;width:100%;" border="1" bordercolor="#EOECFF"
	   cellspacing="2" cellpadding="10">
	<tr style = "height:25%;">
		<th style = "width:20%;">用户编号</th>
		<td style = "width:30%;">${map.userVo.userNumber}</td>
		<th style = "width:20%;">用户登录名</th>
		<td style = "width:30%;">${map.userVo.userLoginName}</td>
	</tr>
	<tr style = "height:25%;">
		<th>用户等级</th>
		<td>${map.userVo.userLevel}</td>
		<th>用户角色</th>
		<td>${map.roleName}</td>
	</tr>
	<tr style = "height:25%;">
		<th>用户名称</th>
		<td>${map.userVo.userName}</td>
		<th>用户密码</th>
		<td>******</td>
	</tr>
	<tr style = "height:25%;">
		<th>用户职位</th>
		<td>${map.userVo.positionVo.positionName}</td>
		<th>用户状态</th>
		<td>
			<c:if test="${map.userVo.userStatus == 0}">
				正常
			</c:if>
			<c:if test="${map.userVo.userStatus == 1}">
				注销
			</c:if>
		</td>
	</tr>
</table>
</body>
</html>