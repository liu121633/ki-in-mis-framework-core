<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<link rel="stylesheet" type="text/css" href="<%=request.getAttribute("basePath")%>/static/easyui-1.5/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getAttribute("basePath")%>/static/easyui-1.5/themes/icon.css" />
<script src="<%=request.getAttribute("basePath")%>/static/easyui-1.5/jquery-3.2.1.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=request.getAttribute("basePath")%>/static/easyui-1.5/jquery.easyui.min.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=request.getAttribute("basePath")%>/static/easyui-1.5/locale/easyui-lang-zh_CN.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=request.getAttribute("basePath")%>/static/js/role/roleInfo.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=request.getAttribute("basePath")%>/static/js/common.js" type="text/javascript" charset="utf-8"></script>

<!-- 加载中 -->
<div id='Loading' style="position:absolute;z-index:1000;top:0px;left:0px;width:100%;height:100%;background:#fff url('style/images/bodybg.jpg');text-align:center;padding-top: 20%;"><h1><img src = "../static/easyui-1.5/themes/default/images/1.gif" /><!-- <font color="#15428B">加载中···</font> --></h1></div>
<script>
function closes(){
	$("#Loading").fadeOut("normal",function(){
		$(this).remove();
	});
}
	var pc;
	$.parser.onComplete = function(){
		if(pc) clearTimeout(pc);
		pc = setTimeout(closes, 1000);
	}
</script>

<!-- 弹出角色分配的窗口 -->
<div id = "roleDistribute"></div>
	<div id="roleLayout" class="easyui-layout" fit="true">
	<form id = "roleSearch_form">
	<table id = "roleSearch" style = "width:100%;">
			<tr>
				<th>角色名称:</th>
				<td><input name = "roleName" type="text" class = "easyui-textbox"/></td>
				<th>默认角色:</th>
				<td>
					<select id = "roleIsTheRoleAdefault" class="easyui-combobox" name="roleIsTheRoleAdefault" style="width: 152px;" >   
						<option value = "">全部</option>
						<option value="0">是</option>
						<option value = "1">否</option>
					</select>
				</td>
			</tr>
			<tr>
				<th>创建人:</th>
				<td><input name = "roleCreateUserName" type="text" class = "easyui-textbox"/></td>
				<th>创建时间</th>
				<td><input id = "roleCreationTime" name = "roleCreationTime" class="easyui-datetimebox">至<input id = "roleCreationTimeLast" class="easyui-datetimebox" name="roleCreationTimeLast"></td>
				<td><a id="roleInfo_search" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a></td>
			</tr>
		</table>
	</form>
	<div data-options="region:'center',title:'角色数据'">
		<table id="roleTable" pagination="true"></table>
	</div>
</div>
<!--弹出新增的div-->
<div id="addRoleDialog"></div>
<!--弹出修改的框-->
<div id="updateRoleDialog"></div>

</body>
</html>