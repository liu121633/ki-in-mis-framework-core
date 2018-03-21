<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <script src="<%=request.getAttribute("basePath")%>/static/js/role/distributeAuthority.js" type="text/javascript" charset="utf-8"></script>
<div id="cc" class="easyui-layout" data-options = "fit:true">
<input value = "${roleNumber}" id = "roleId" style = "display:none;"/>
<input value = "${roleVo.roleNumber}" id = "rId" style = "display:none;"/>
<input value = "${obj}" id = "obj" style = "display:none;"/>
 	<div data-options="region:'north',split:true" style="height:70px;">
 		<center><h2>为${roleVo.roleName}角色分配权限</h2></center>
 	</div> 
    <div data-options="region:'center',title:'数据权限',split:true" style="width:40%;">
    	<div id = "data_tree"></div>
    	<p><a id = "AuthorityGoBack" style = "position: absolute;bottom:0;right: 0;width:70px;height: 30px;" class="easyui-linkbutton" data-options="iconCls:'icon-no'">返回</a></p>
    </div>
    <div data-options="region:'west',title:'功能权限',split:true" style="width:50%;">
    	<div id = "menu_tree"></div>
    	<p><a id = "AuthorityOk" style = "position: absolute;bottom:0;right: 0;width:70px;height: 30px;" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">保存</a></p>
    </div>
</div>