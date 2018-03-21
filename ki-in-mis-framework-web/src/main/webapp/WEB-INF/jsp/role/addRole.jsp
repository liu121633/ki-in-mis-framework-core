<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<form method = "post" fit = "true" id = "addRole_form">
<table style="width: 100%;" cellpadding="10" cellspacing="2" border="1"
	bordercolor="#EOECFF" id = "addRole_table">
	<tr>
		<th align="left">角色名称:</th>
		<td><input name = "roleName" type="text" class="easyui-textbox"
			style="width: 100%;"  required="required" data-options="validType:{remote:['<%=request.getAttribute("basePath")%>/role/roleNameExit','roleName']},invalidMessage:'此角色名称已经存在!'"/></td>
	</tr>
	<tr>
		<th align="left">是否为默认角色:</th>
		<td><select id="cc" class="easyui-combobox" name="roleIsTheRoleAdefault"
			style="width: 88%;" required="required" data-options = "editable:false">
				<option value="0">是</option>
				<option value = "1">不是</option>
		</select></td>
	</tr>
	<tr>
		<th align="left">角色描述:</th>
		<td><input name = "roleDescription" type="text" class="easyui-textbox" data-options = "multiline:true" style="width: 90%;height:100px;" />
		</td>
	</tr>
</table>
</form>