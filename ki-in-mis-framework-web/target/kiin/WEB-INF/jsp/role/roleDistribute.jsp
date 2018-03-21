<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <script src="<%=request.getAttribute("basePath")%>/static/js/role/roleDistribute.js" type="text/javascript" charset="utf-8"></script>
    <input value = "${roleVo.roleNumber}" style = "display:none;" id = "roleNumber"/>
<table style="width: 100%;" cellpadding="10" cellspacing="2" border="1"
	bordercolor="#EOECFF">
	<tr>
		<th style = "width:30%;">角色：</th>
		<td>${roleVo.roleName}</td>
	</tr>
	<tr>
		<th>用户：</th>
		<td>
			<select id = "userNumber" style = "width:100%;"></select>
		</td>
	</tr>
</table>