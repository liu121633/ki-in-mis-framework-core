<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<form method = "post" fit = "true" id = "updateRole_form">
	<input name = "roleNumber" value = "${roleVo.roleNumber}" style = "display:none;"/>
<table style="width: 100%;" cellpadding="10" cellspacing="2" border="1"
	bordercolor="#EOECFF">
	<tr>
		<th align="left">角色名称:</th>
		<td><input id = "roleName" value = "${roleVo.roleName}" name = "roleName" type="text" class="easyui-textbox"
			style="width: 100%;"  required="required"/></td>
	</tr>
	<tr>
		<th align="left">是否为默认角色:</th>
		<td><select id="cc" class="easyui-combobox" name="roleIsTheRoleAdefault"
			style="width: 88%;" required="required" data-options = "editable:false">
				<option value="1" <c:if test = "${roleVo.roleIsTheRoleAdefault == 1}">selected</c:if> >不是</option>
				<option value = "0" <c:if test = "${roleVo.roleIsTheRoleAdefault == 0}">selected</c:if> >是</option>
		</select></td>
	</tr>
	<tr>
		<th align="left">角色描述:</th>
		<td><input value = "${roleVo.roleDescription}" name = "roleDescription" type="text" class="easyui-textbox" data-options = "multiline:true" style="width: 90%;height:100px;" />
		</td>
	</tr>
</table>
</form>